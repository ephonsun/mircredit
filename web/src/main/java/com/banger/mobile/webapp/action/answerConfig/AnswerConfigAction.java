/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :答录配置action
 * Author     :yujh
 * Create Date:Jun 4, 2012
 */
package com.banger.mobile.webapp.action.answerConfig;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.banger.mobile.domain.model.answerConfig.AnswerConfig;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.encryption.MD5Util;
import com.banger.mobile.facade.answerConfig.AnswerConfigService;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.util.FileUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author yujh
 * @version $Id: AnswerConfigAction.java,v 0.1 Jun 4, 2012 5:10:58 PM yujh Exp $
 */
public class AnswerConfigAction extends BaseAction {

    private static final long   serialVersionUID = 3545109953696030578L;
    private DateFormat          df               = new SimpleDateFormat("yyyyMMdd");
    private AnswerConfig        answerConfig;
    private AnswerConfigService answerConfigService;
    private static String       ANSWERCONFIGDIR  = "/answerConfigDir";
    private static final int    BUFFERED_SIZE    = 5 * 1024;
    private String              fileName;
    private String              afilePath;

    private File                uploadFile;
    private String              myFileFileName;
    private String              myFileContentType;
    private SysParamService     sysParamService;                                    //个人文件夹路径

    public void setSysParamService(SysParamService sysParamService) {
        this.sysParamService = sysParamService;
    }

    /**
     * 查看配置参数
     * @return 
     */
    public String showAnswerConfigPage() {
        try {
            HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
            HttpSession session = request.getSession();

            int userId = ((IUserInfo) session.getAttribute("LoginInfo")).getUserId(); //用户id
            answerConfig = this.answerConfigService.queryAnswerConfig(userId); //查询出答录音信息
            request.setAttribute("userId", userId);
            return SUCCESS;
        } catch (Exception e) {
            log.error(" showAnswerConfigPage action error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 修改答录音配置地址信息并上传文件
     * @return
     */

    public String updateAnswerConfig() {
        AnswerConfig config = this.answerConfigService.queryAnswerConfig(this.getLoginInfo().getUserId());
        config.setIsMute(answerConfig.getIsMute());
        config.setRingCount(answerConfig.getRingCount());
        this.answerConfigService.updateAnswerConfig(config);
        return SUCCESS;
    }

    /**
     * 根据服务器文件地址判断是否有该文件
     * String afilePath 答录音的本地地址 
     * String rfilePath 提示音的本地地址 
     */
    public void isFile() {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            if (StringUtil.isNotEmpty(afilePath)) {
                String path = request.getSession().getServletContext().getRealPath(ANSWERCONFIGDIR)
                              + File.separator + splitFileName(afilePath);
                File f1 = new File(afilePath);
                if (f1.exists()) {//如果服务器有该答录音则复制到临时播放目录下 并返回录音文件名
                    String rpath = request.getSession().getServletContext().getRealPath(
                        File.separator)
                                   + "Records";
                    File newFile = new File(rpath + File.separator + f1.getName());
                    FileUtils.copyFile(f1, newFile); //复制文件到服务器临时播放文件下
                    out.print((URLEncoder.encode(newFile.getName(), "UTF-8")));
                } else {
                    out.print("");
                }
            }
            out.close();
        } catch (Exception e) {
            log.error(" isFile action error:" + e.getMessage());
        }
    }

    /**
     * 截取文件名
     * @param fileName
     * @return
     */
    public String splitFileName(String fileName) {
        return fileName.substring(fileName.lastIndexOf(File.separator) + 1, fileName.length());
    }

    public String splitFileNameToo(String fileName) {
        return fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length());
    }

    /**
     * 上传
     */
    public void doUpload() {
        if (uploadFile != null) {
            try {
                HttpServletResponse response = ServletActionContext.getResponse();
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                String userId = this.getLoginInfo().getUserId().toString();
                String savePath = sysParamService.getUserPhonePath() + "/" + userId;

                File file = new File(savePath);
                if (!file.exists()) {// 文件不存在则创建
                    file.mkdirs();
                }

                // 生成guid 文件名
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                String newFileName = uuid + "." + "wav";
                // 将文件上传到服务器
                File imageFile = new File(savePath + "/" + newFileName);
                copy(uploadFile, imageFile);
                
                String localFileName = FileUtil.getFileName(URLDecoder.decode(request.getParameter("localFileName"),"UTF-8"));
                
                AnswerConfig config = answerConfigService.queryAnswerConfig(this.getLoginInfo()
                    .getUserId());
                config.setFileName(localFileName);
                config.setVoiceFilePath(savePath + "/" + newFileName);
                String fileVersion = MD5Util.getFileMD5(new File(config.getVoiceFilePath()));
                config.setFileMd5(fileVersion);
                config.setIsMute(answerConfig.getIsMute());
                config.setRingCount(answerConfig.getRingCount());
                answerConfigService.updateAnswerConfig(config);
                out.print(answerConfig.getVoiceFilePath());
            } catch (IOException e) {
                log.error("", e);
            }
        }

    }

    /**
         * 复制文件
         * @param src
         * @param target
         */
    private void copy(File src, File target) {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(src), BUFFERED_SIZE);
            out = new BufferedOutputStream(new FileOutputStream(target), BUFFERED_SIZE);
            byte[] bs = new byte[BUFFERED_SIZE];
            int i;
            while ((i = in.read(bs)) > 0) {
                out.write(bs, 0, i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    in.close();
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getAfilePath() {
        return afilePath;
    }

    public void setAfilePath(String afilePath) {
        this.afilePath = afilePath;
    }

    public AnswerConfig getAnswerConfig() {
        return answerConfig;
    }

    public void setAnswerConfig(AnswerConfig answerConfig) {
        this.answerConfig = answerConfig;
    }

    public void setAnswerConfigService(AnswerConfigService answerConfigService) {
        this.answerConfigService = answerConfigService;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    

    public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getMyFileFileName() {
        return myFileFileName;
    }

    public void setMyFileFileName(String myFileFileName) {
        this.myFileFileName = myFileFileName;
    }

    public String getMyFileContentType() {
        return myFileContentType;
    }

    public void setMyFileContentType(String myFileContentType) {
        this.myFileContentType = myFileContentType;
    }

}
