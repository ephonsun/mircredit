/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :流量限制类...
 * Author     :yuanme
 * Create Date:2012-4-5
 */
package com.banger.mobile.transport;

import com.banger.mobile.constants.TransportConstants;
import com.banger.mobile.domain.model.sysWebFlowControl.SysWebFlowControl;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.facade.sysWebFlowControl.SysWebFlowControlService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.util.FileUtil;
import com.banger.mobile.util.SpringContextUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.Properties;

/**
 * @author yuanme
 * @version ServerSocketTask.java,v 0.1 2012-4-5 上午10:04:41
 */
public class ServerSocketTask implements Runnable {
    private static final Logger logger = Logger.getLogger(ServerSocketTask.class);
    private Socket              socket = null;

    public ServerSocketTask(Socket socket) {
        this.socket = socket;
    }

    /**
     * 取得PAD端限流设置
     * @return
     */
    private int getPadMaxbps() {
        // 取得流量控制参数
        int speed = -1;
        SysParamService sysParamService = (SysParamService) SpringContextUtil
            .getBean("sysParamService");
        if (sysParamService != null) {
            Map<String, Object> map = sysParamService.querySysParam();
            if (map != null) {
                if (map.get(TransportConstants.FLOW_CONTROL_ISACTIVE) != null) {
                    String isActive = map.get(TransportConstants.FLOW_CONTROL_ISACTIVE).toString();
                    if (isActive.equals("1")) { //1启用
                        speed = TransportConstants.SERVER_SPEED_LIMIT;
                        if (map.get(TransportConstants.FLOW_CONTROL_MAXBPS) != null) {
                            String maxbpsString = map.get(TransportConstants.FLOW_CONTROL_MAXBPS)
                                .toString();
                            int maxbps = Integer.valueOf(maxbpsString);
                            if (maxbps > 0) {
                                speed = maxbps * 1000;
                            }
                        }
                    }
                }
            }
        }
        return speed;
    }

    /**
     * 取得本地电话流量控制参数
     * @return
     */
    private int getLocalPhoneMaxbps(String account) {
        int speed = -1;
        SysParamService sysParamService = (SysParamService) SpringContextUtil
            .getBean("sysParamService");
        if (sysParamService != null) {
            Map<String, Object> map = sysParamService.querySysParam();
            if (map != null) {
                if (map.get(TransportConstants.WEB_FLOW_CONTROL_ISACTIVE) != null) {
                    String isActive = map.get(TransportConstants.WEB_FLOW_CONTROL_ISACTIVE).toString();
                    if (isActive.equals("1")) { //1启用
                        // 得到该账号机构流量设置
                        SysWebFlowControlService sysWebFlowControlService = (SysWebFlowControlService) SpringContextUtil
                            .getBean("sysWebFlowControlService");
                        SysWebFlowControl info = sysWebFlowControlService
                            .getFlowControlByAccount(account);
                        if (info != null && info.getIsActive() != null && info.getIsActive() == 1) {
                            if (info.getMaxBps() != null && info.getMaxBps() > 0) {
                                speed = info.getMaxBps() * 1000;

                                //取得当前机构下正在传输的用户数量
                                int count = TransportUtil.getDeptUserCount(info.getDeptId());
                                if (count >= 0) {
                                    speed = speed / (count + 1);
                                }

                                //更新机构下用户数量
                                TransportUtil.updateDeptUserCount(info.getDeptId(), count + 1);
                            }
                        }
                    }
                }
            }
        }

        logger.info("getLocalPhoneMaxbps : " + speed);
        return speed;
    }

    /**
     * 录音存放目录
     * @return
     */
    private String getRecordDir() {
        // 上传文件临时路径
        SysParamService sysParamService = (SysParamService) SpringContextUtil
            .getBean("sysParamService");
        String recordDir = TransportUtil.getRecordPath(sysParamService)
                           + TransportConstants.UPLOAD_TEMP_DIR;
        FileUtil.createDir(recordDir);
        return recordDir;
    }

    public void run() {
        DataInputStream dataInputStream = null;
        DataOutputStream datasOutputStream = null;
        DataOutputStream dataOutputStream = null;
        FlowControlInputStream fciStream = null;
        RandomAccessFile fileff = null;
        int maxbps = -1;
        SysUser user = null;
        String recordDir = getRecordDir();
        try {
            String remoteIp = socket.getRemoteSocketAddress().toString();
            Resource resource = new ClassPathResource("/global.properties");
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            String exclusion = props.getProperty("socket.remote.ip.exclusion");
            boolean isExclusion = false;
            if (StringUtils.isNotEmpty(exclusion) && StringUtils.isNotEmpty(remoteIp)) {
                String[] exclusions = exclusion.split(",");
                for(String exc : exclusions) {
                    if (remoteIp.contains(exc)) {
                        isExclusion = true;
                        break;
                    }
                }
            }

            if (isExclusion) {
//                logger.info("socket.remote.ip.exclusion..." + remoteIp);
            } else {
                socket.setSoTimeout(TransportConstants.READ_TIMEOUT);
                dataInputStream = new DataInputStream(socket.getInputStream());
                datasOutputStream = new DataOutputStream(socket.getOutputStream());

                String fileNameSizeAccount = null;
                try {
                    fileNameSizeAccount = dataInputStream.readUTF();
                } catch (EOFException eof) {
                }
                if (fileNameSizeAccount != null && fileNameSizeAccount.length() > 0) {
                    String fileName = fileNameSizeAccount.split(":")[0];//文件名
                    long fileSize = Long.valueOf(fileNameSizeAccount.split(":")[1]);//客户端文件大小
                    String account = "";//客户端上传账号
                    if (fileNameSizeAccount.split(":").length > 2) {
                        account = fileNameSizeAccount.split(":")[2];
                    }
                    if (StringUtils.isNotEmpty(account)) {
                        //如果文件有账号，则表示是话机上传的文件
                        maxbps = getLocalPhoneMaxbps(account);
                        SysUserService sysUserService = (SysUserService) SpringContextUtil
                                .getBean("sysUserService");
                        user = sysUserService.getUserByAccount(account);
                    } else {
                        //账号为空，则是PAD上传的文件
                        maxbps = getPadMaxbps();
                    }

                    logger.info(fileName + " start receive.");

                    // 如果在服务器上不存在文件平则创建
                    File file = new File(recordDir + "/" + fileName);
                    if (file.exists()) {
                        logger.info(fileName + " exists, continued transmission.");
                        // 得到当前服务器端文件大小
                        long filelength = file.length();

                        // 把该文件大小通过socket告诉客户端
                        datasOutputStream.writeUTF(filelength + "");
                        datasOutputStream.flush();

                        // 通过RandomAccessFile可以从当前文件结尾处写入文件
                        fileff = new RandomAccessFile(file, "rw");
                        byte[] by = new byte[TransportConstants.BUFF_LENGTH];
                        int amount;
                        int haveReceive = 0;
                        fileff.seek(filelength);

                        // 后续客户端发送过来的数据就是当前文件未完成部分
                        // 增加流量控制
                        if (maxbps != -1) {
                            fciStream = new FlowControlInputStream(dataInputStream, maxbps);
                            logger.info(fileName + " current speed:" + fciStream.check() / 1000 + "kbps");

                            while ((haveReceive < (fileSize - filelength))
                                    && (amount = fciStream.read(by)) != -1) {
                                fileff.write(by, 0, amount);
                                haveReceive += amount;
                            }
                            fileff.close();
                            logger.info(fileName + " finished.");
                            datasOutputStream.writeUTF("ok");
                            datasOutputStream.flush();
                        } else {
                            while ((haveReceive < (fileSize - filelength))
                                    && (amount = dataInputStream.read(by)) != -1) {
                                fileff.write(by, 0, amount);
                                haveReceive += amount;
                            }
                            fileff.close();
                            logger.info(fileName + " finished.");
                            datasOutputStream.writeUTF("ok");
                            datasOutputStream.flush();
                        }
                    } else {
                        logger.info(fileName + " no exists, direct transmission.");
                        datasOutputStream.writeUTF("0");
                        datasOutputStream.flush();
                        dataOutputStream = new DataOutputStream(new FileOutputStream(recordDir + "/"
                                + fileName));
                        byte[] by = new byte[TransportConstants.BUFF_LENGTH];
                        int amount;

                        // 增加流量控制
                        if (maxbps != -1) {
                            fciStream = new FlowControlInputStream(dataInputStream, maxbps);
                            logger.info(fileName + " current speed:" + fciStream.check() / 1000 + "kbps");
                            while ((dataOutputStream.size() < fileSize)
                                    && (amount = fciStream.read(by)) != -1) {
                                dataOutputStream.write(by, 0, amount);
                                dataOutputStream.flush();
                            }
                            dataOutputStream.close();
                            logger.info(fileName + " finished.");
                            datasOutputStream.writeUTF("ok");
                            datasOutputStream.flush();
                        } else {
                            while ((dataOutputStream.size() < fileSize)
                                    && (amount = dataInputStream.read(by)) != -1) {
                                dataOutputStream.write(by, 0, amount);
                                dataOutputStream.flush();
                            }
                            dataOutputStream.close();
                            logger.info(fileName + " finished.");
                            datasOutputStream.writeUTF("ok");
                            datasOutputStream.flush();
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("ServerSocketTask file receiver error:", e);
        } finally {
            if (user != null) {
                TransportUtil.updateDeptUserCountAfter(user.getDeptId());
            }
            try {
                if (socket != null) {
                    socket.close();
                    socket = null;
                }
                if (dataInputStream != null) {
                    dataInputStream.close();
                    dataInputStream = null;
                }
                if (datasOutputStream != null) {
                    datasOutputStream.close();
                    datasOutputStream = null;
                }
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                    dataOutputStream = null;
                }
                if (fciStream != null) {
                    fciStream.close();
                    fciStream = null;
                }
                if (fileff != null) {
                    fileff.close();
                    fileff = null;
                }
            } catch (IOException e) {
                logger.error("ServerSocketTask stream close error:", e);
            }
        }
    }
}