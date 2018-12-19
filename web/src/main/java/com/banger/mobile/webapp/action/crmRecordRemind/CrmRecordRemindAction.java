/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音提示音
 * Author     :yujh
 * Create Date:Aug 30, 2012
 */
package com.banger.mobile.webapp.action.crmRecordRemind;

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
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.banger.mobile.domain.model.crmRecordRemind.CrmRecordRemind;
import com.banger.mobile.domain.model.crmRingSetting.CrmRingSetting;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.encryption.MD5Util;
import com.banger.mobile.facade.crmRecordRemind.CrmRecordRemindService;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.FileUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author yujh
 * @version $Id: CrmRecordRemindAction.java,v 0.1 Aug 30, 2012 11:14:28 AM Administrator Exp $
 */
public class CrmRecordRemindAction extends BaseAction {

    private static final long      serialVersionUID      = -4196755467101663058L;

    private static String          CRM_RECORD_REMIND_DIR = "/crmRecordRemindDir";
    private static final int       BUFFERED_SIZE         = 5 * 1024;

    private CrmRecordRemindService crmRecordRemindService;
    private CrmRecordRemind        crmRecordRemind;
    private String                 fileName;
    private String                 voiceFilePath;
    private File uplodeFile;

	public File getUplodeFile() {
		return uplodeFile;
	}

	public void setUplodeFile(File uplodeFile) {
		this.uplodeFile = uplodeFile;
	}
	
    private SysParamService        sysParamService;                               //个人文件夹路径

    public void setSysParamService(SysParamService sysParamService) {
        this.sysParamService = sysParamService;
    }

    /**
     * 查询
     * @return
     */
    public String showCrmRecordRemindPage() {
        int userId = this.getLoginInfo().getUserId();
        crmRecordRemind = this.crmRecordRemindService.getCrmRecordRemindByUserId(userId);
        request.setAttribute("userId", userId);
        return SUCCESS;
    }

    /**
     * 更新
     * @return
     */
    public String updateRecordRemind() {
        MD5Util md5 = new MD5Util();
        try {
            HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
            HttpSession session = request.getSession();

            int userId = this.getLoginInfo().getUserId();
            crmRecordRemind.setUserId(userId);
            if (StringUtil.isNotEmpty(crmRecordRemind.getFilePath())) {
                crmRecordRemind.setFilePath(new String(crmRecordRemind.getFilePath().getBytes(
                    "ISO-8859-1"), "UTF-8"));
                fileName = crmRecordRemind.getFilePath();
                crmRecordRemind.setFilePath(sysParamService.getUserPhonePath() + File.separator
                                            + userId + File.separator
                                            + splitFileNameToo(crmRecordRemind.getFilePath()));
                CrmRecordRemind crr = this.crmRecordRemindService
                    .getCrmRecordRemindByUserId(userId);
                doUpload();
                crmRecordRemind.setFileMd5(md5.getFileMD5(new File(crmRecordRemind.getFilePath())));//md5
                this.crmRecordRemindService.updateCrmRecordRemind(crmRecordRemind);

            }
            return SUCCESS;
        } catch (Exception e) {
            log.error(" updateRingSetting action error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 判断文件在服务器上是否存在并返回
     */
    public void isFile() {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            if (StringUtil.isNotEmpty(voiceFilePath)) {
                String path = request.getSession().getServletContext().getRealPath(
                    CRM_RECORD_REMIND_DIR)
                              + File.separator + splitFileName(voiceFilePath);
                File f1 = new File(voiceFilePath);
                if (f1.exists()) { //如果服务器有该答录音则复制到临时播放目录下 并返回录音文件名
                    String rpath = request.getSession().getServletContext().getRealPath(
                        File.separator)
                                   + "Records";
                    File newFile = new File(rpath + File.separator + f1.getName());
                    FileUtils.copyFile(f1, newFile); //复制文件到服务器临时播放文件下
                    out.print(URLEncoder.encode(newFile.getName(), "UTF-8"));
                } else {
                    out.print(voiceFilePath);
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

    public CrmRecordRemindService getCrmRecordRemindService() {
        return crmRecordRemindService;
    }

    public void setCrmRecordRemindService(CrmRecordRemindService crmRecordRemindService) {
        this.crmRecordRemindService = crmRecordRemindService;
    }

    public CrmRecordRemind getCrmRecordRemind() {
        return crmRecordRemind;
    }

    public void setCrmRecordRemind(CrmRecordRemind crmRecordRemind) {
        this.crmRecordRemind = crmRecordRemind;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getVoiceFilePath() {
        return voiceFilePath;
    }

    public void setVoiceFilePath(String voiceFilePath) {
        this.voiceFilePath = voiceFilePath;
    }

    /**
     * 上传
     */
	public void doUpload() {
        if (uplodeFile != null) {
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
                
                String localFileName = FileUtil.getFileName(URLDecoder.decode(request.getParameter("localFileName"),"UTF-8"));
                // 将文件上传到服务器
                File imageFile = new File(savePath + "/" + newFileName);
                copy(uplodeFile, imageFile);
                crmRecordRemind = crmRecordRemindService.getCrmRecordRemindByUserId(this.getLoginInfo()
                    .getUserId());
                if(crmRecordRemind==null){
                    crmRecordRemind = new CrmRecordRemind();
                    crmRecordRemind.setFileName(localFileName);
                    crmRecordRemind.setUserId(this.getLoginInfo().getUserId());
                    crmRecordRemind.setFilePath(savePath + "/" + newFileName);
                    String fileVersion = MD5Util.getFileMD5(new File(crmRecordRemind.getFilePath()));
	                if(!StringUtil.isNullOrEmpty(fileVersion)){
	                    crmRecordRemind.setFileMd5(fileVersion);			//2012-11-20修改
	                }
	                crmRecordRemindService.insertCrmRecordRemind(crmRecordRemind);
                }
                else
                {
                	crmRecordRemind.setFileName(localFileName);
	                crmRecordRemind.setFilePath(savePath + "/" + newFileName);
	                String fileVersion = MD5Util.getFileMD5(new File(crmRecordRemind.getFilePath()));
	                if(!StringUtil.isNullOrEmpty(fileVersion)){
	                    crmRecordRemind.setFileMd5(fileVersion);			//2012-11-20修改
	                }
	                crmRecordRemindService.updateCrmRecordRemind(crmRecordRemind);
                }
                out.print(crmRecordRemind.getFilePath());
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

}
