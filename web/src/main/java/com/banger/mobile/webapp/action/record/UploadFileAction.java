/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :上传文件Action
 * Author     :Administrator
 * Version    :1.0
 * Create Date:May 3, 2012
 */
package com.banger.mobile.webapp.action.record;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.struts2.ServletActionContext;
import com.banger.mobile.facade.log.OpeventLogService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author huangkai
 * @version $Id: UploadFileAction.java,v 0.1 May 3, 2012 2:22:36 PM huangkai Exp $
 */
public class UploadFileAction extends BaseAction{
    //上传文件存放路径   
    private final static String UPLOADDIR = "/upload";   
    
    private OpeventLogService opeventLogService;//操作日志service
    
    private String fileName;//上传文件名
  
    public void setOpeventLogService(OpeventLogService opeventLogService) {
        this.opeventLogService = opeventLogService;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
  
    /**
     * 文件上传
     */
    public String execute() throws Exception {  
        try {
            fileName=request.getParameter("fileName");
            fileName= new String(fileName.getBytes("ISO-8859-1"), "utf-8");
            File f=new File(fileName);
            //循环上传每个文件   
            uploadFile(f);
            return SUCCESS;
        } catch (Exception e) {
            log.error(e.getMessage());
            return ERROR;
        }
    }   

    /**
     * 执行上传功能   
     * @param f
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void uploadFile(File f) throws FileNotFoundException, IOException {   
        try {   
            InputStream in = new FileInputStream(f);   
            String dir = ServletActionContext.getRequest().getRealPath(UPLOADDIR);   
            String fname=f.toString();
            File uploadDir=new File(dir);
            if(!uploadDir.isDirectory()){
                uploadDir.mkdirs();
            }
            File uploadFile = new File(dir,fname.substring(fname.lastIndexOf("\\")+1, fname.length()));   
            OutputStream out = new FileOutputStream(uploadFile);   
            byte[] buffer = new byte[1024 * 1024];   
            int length;   
            while ((length = in.read(buffer)) > 0) {   
                out.write(buffer, 0, length);   
            }
            in.close();   
            out.close();   
            opeventLogService.addOpeventLog("录音设置", "上传提示音操作", 1, "");
        } catch (Exception ex) {  
            opeventLogService.addOpeventLog("录音设置", "上传提示音操作", 0, "");
            ex.printStackTrace();
        }   
    }
}
