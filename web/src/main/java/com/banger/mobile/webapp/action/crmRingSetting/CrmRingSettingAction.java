/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :铃声设置
 * Author     :yujh
 * Create Date:Aug 30, 2012
 */
package com.banger.mobile.webapp.action.crmRingSetting;

import java.awt.image.BufferedImage;
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
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.banger.mobile.domain.model.crmRingSetting.CrmRingSetting;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.encryption.MD5Util;
import com.banger.mobile.facade.crmRingSetting.CrmRingSettingService;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.FileType;
import com.banger.mobile.util.FileUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author yujh
 * @version $Id: CrmRingSettingAction.java,v 0.1 Aug 30, 2012 11:14:44 AM Administrator Exp $
 */
public class CrmRingSettingAction extends BaseAction {

    private static final long     serialVersionUID     = -4270459392599928189L;
    private static String         CRM_RING_SETTING_DIR = "/crmRingSettingDir";
    private static final int      BUFFERED_SIZE        = 5 * 1024;
    private CrmRingSettingService crmRingSettingService;                       //铃声设置service
    private CrmRingSetting        crmRingSetting;                              //铃声配置对象
    private String                ringVoicePath;                               //页面铃声路径
    private String                fileName;
    private File                  uplodeFile;
    private String                myFileFileName;
    private String                myFileContentType;
    private SysParamService       sysParamService;                             //个人文件夹路径

    public void setSysParamService(SysParamService sysParamService) {
        this.sysParamService = sysParamService;
    }

    /**
     * 显示铃声配置页面
     * @return
     */
    public String showCrmRingSettingPage() {
        int userId = this.getLoginInfo().getUserId();
        crmRingSetting = this.crmRingSettingService.getCrmRingSettingByUserId(userId);
        request.setAttribute("userId", userId);
        return SUCCESS;
    }

    /**
     * 更新
     * @return
     */
    public String updateRingSetting() {
        MD5Util md5 = new MD5Util();
        try {
            HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
            HttpSession session = request.getSession();

            int userId = ((IUserInfo) session.getAttribute("LoginInfo")).getUserId();
            crmRingSetting.setUserId(userId);
            if (StringUtil.isNotEmpty(crmRingSetting.getFilePath())) {
                crmRingSetting.setFilePath(new String(crmRingSetting.getFilePath().getBytes(
                    "ISO-8859-1"), "UTF-8"));
                fileName = crmRingSetting.getFilePath();
                crmRingSetting.setFilePath(sysParamService.getUserPhonePath() + File.separator
                                           + userId + File.separator
                                           + splitFileNameToo(crmRingSetting.getFilePath()));
                CrmRingSetting crs = this.crmRingSettingService.getCrmRingSettingByUserId(userId);
                crmRingSetting.setFileMd5(md5.getFileMD5(new File(crmRingSetting.getFilePath())));//生成文件MD5
                this.crmRingSettingService.updateCrmRingSetting(crmRingSetting);
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
            if (StringUtil.isNotEmpty(ringVoicePath)) {
                String path = request.getSession().getServletContext().getRealPath(
                    CRM_RING_SETTING_DIR)
                              + File.separator + splitFileName(ringVoicePath);
                File f1 = new File(ringVoicePath);
                if (f1.exists()) { //如果服务器有该答录音则复制到临时播放目录下 并返回录音文件名
                    String rpath = request.getSession().getServletContext().getRealPath(
                        File.separator)
                                   + "Records";
                    File newFile = new File(rpath + File.separator + f1.getName());
                    FileUtils.copyFile(f1, newFile); //复制文件到服务器临时播放文件下
                    out.print(URLEncoder.encode(newFile.getName(), "UTF-8"));
                } else {
                    out.print(ringVoicePath);
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
    /**
     * 截取文件名
     * @param fileName
     * @return
     */
    public String splitFileNameToo(String fileName) {
        return fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length());
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
                // 将文件上传到服务器
                File imageFile = new File(savePath + "/" + newFileName);
                copy(uplodeFile, imageFile);
                
                String localFileName = FileUtil.getFileName(URLDecoder.decode(request.getParameter("localFileName"),"UTF-8"));
                
                crmRingSetting = crmRingSettingService.getCrmRingSettingByUserId(this.getLoginInfo()
                    .getUserId());
                if(crmRingSetting==null)
                {
                	crmRingSetting = new CrmRingSetting();
                	crmRingSetting.setFileName(localFileName);
                	crmRingSetting.setFilePath(savePath + "/" + newFileName);
                	String fileVersion = MD5Util.getFileMD5(new File(crmRingSetting.getFilePath()));
                	crmRingSetting.setFileMd5(fileVersion);
                	crmRingSettingService.insertCrmRingSetting(crmRingSetting);
                }
                else
                {
                	crmRingSetting.setFileName(localFileName);
                	crmRingSetting.setFilePath(savePath + "/" + newFileName);
                	String fileVersion = MD5Util.getFileMD5(new File(crmRingSetting.getFilePath()));
                	crmRingSetting.setFileMd5(fileVersion);
                	crmRingSettingService.updateCrmRingSetting(crmRingSetting);
                }
                out.print(crmRingSetting.getFilePath());
            } catch (IOException e) {
                log.error("", e);
            }
        }
    }

    public CrmRingSettingService getCrmRingSettingService() {
        return crmRingSettingService;
    }

    public void setCrmRingSettingService(CrmRingSettingService crmRingSettingService) {
        this.crmRingSettingService = crmRingSettingService;
    }

    public CrmRingSetting getCrmRingSetting() {
        return crmRingSetting;
    }

    public void setCrmRingSetting(CrmRingSetting crmRingSetting) {
        this.crmRingSetting = crmRingSetting;
    }

    public String getRingVoicePath() {
        return ringVoicePath;
    }

    public void setRingVoicePath(String ringVoicePath) {
        this.ringVoicePath = ringVoicePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    

    public File getUplodeFile() {
		return uplodeFile;
	}

	public void setUplodeFile(File uplodeFile) {
		this.uplodeFile = uplodeFile;
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
