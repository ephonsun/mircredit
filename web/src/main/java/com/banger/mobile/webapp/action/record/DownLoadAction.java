/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音下载action
 * Author     :hk
 * Create Date:Mar 29, 2012
 */
package com.banger.mobile.webapp.action.record;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;

import com.banger.mobile.webapp.action.BaseAction;


/**
 * @author hk
 * @version $Id: RecRecordInfoAction.java,v 0.1 Mar 29, 2012 2:19:30 PM hk Exp $
 */
public class DownLoadAction extends BaseAction{
    private static final long serialVersionUID = 3591774394616332842L;
    private int isSuccess=1;//操作日志插入是否成功状态   1:成功  0:失败
    private String path;
    
    /**
     * 获得目标文件流
     * @return
     * @throws Exception
     */
    public InputStream getTargetFile() throws Exception
    {
    	path = URLDecoder.decode(path,"UTF-8");
        return new FileInputStream(path);
    }
    /**
     * 提供转换编码后的供下载用的文件名
     * @return
     */
    public String getDownloadFileName() { 
    	 String downFileName = path.substring(path.lastIndexOf("/")+1, path.length()); 
         try { 
//            downFileName = URLDecoder.decode(path,"UTF-8");
            downFileName = new String(downFileName.getBytes(), "ISO8859-1"); 
        } catch (Exception e) { 
            e.printStackTrace(); 
            isSuccess=0;
            log.error("DownLoadAction action error:" + e.getMessage());
        }
//        opeventLogFacade.sysOpeventLogSave("录音信息管理", "下载录音"+fileName, isSuccess, "进行录音下载操作!");
        return downFileName; 
    }
    /**
     * 导出
     */
    public String execute(){
        if(!(new File(path)).exists()){
            log.error("导出文件不存在");
        }
        return SUCCESS;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
}
