/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :全局参数service实现类
 * Author     :yujh
 * Create Date:May 25, 2012
 */
package com.banger.mobile.facade.impl.system.param;

import java.io.File;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.banger.mobile.constants.FileUploadPathConstants;
import com.banger.mobile.constants.TransportConstants;
import com.banger.mobile.dao.param.SysParamDao;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.util.FileUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.util.SystemUtil;

/**
 * @author yujh
 * @version $Id: SysParamServiceImpl.java,v 0.1 May 25, 2012 11:07:18 AM Administrator Exp $
 */
public class SysParamServiceImpl implements SysParamService {

    protected final Log log = LogFactory.getLog(getClass());
    private SysParamDao sysParamDao;

    public void setSysParamDao(SysParamDao sysParamDao) {
        this.sysParamDao = sysParamDao;
    }

    /**
     * 查询流量控制参数
     */
    public Map<String, Object> getFlowControlParam() {
        return this.sysParamDao.getFlowControlParam();
    }

    /**
     * 设置是否启用限流
     */
    public void updateIsActive(int num) {
        this.sysParamDao.updateIsActive(num);
    }

    /**
     * 设置最大流量
     */
    public void updateMaxFlow(int maxBPS) {
        this.sysParamDao.updateMaxFlow(maxBPS);
    }

    /**
     * 查询系统全局参数
     * return list sysparm
     */
    public Map<String, Object> querySysParam() {
        return this.sysParamDao.querySysParam();
    }

    /**
     * 修改系统全局参数
     * param map
     */
    public void updateSysParam(Map<String, Object> map) {
        this.sysParamDao.updateSysParam(map);
    }

    /**
     * 查询不被拦截的action集合
     */
    public String getExcludeActions() {
        return this.sysParamDao.getExcludeActions();
    }

    /**
     * 
     * @return
     * @see com.banger.mobile.facade.param.SysParamService#getTaskAttachmentPath()
     */
    public String getTaskAttachmentPath() {
        String result = "";
        Map<String, Object> map = this.sysParamDao.querySysParam();
        if (map != null) {
            Object path = map.get(FileUploadPathConstants.TASK_ATTACHMENT_PATH);
            if (path != null) {
                result = path.toString();
            }
        }
        if (result.equals("")) {
            if (SystemUtil.isWindows()) {
                result = TransportConstants.RECORD_FILE_STORE_PATH_WIN;
            } else {
                result = TransportConstants.RECORD_FILE_STORE_PATH_LINUX;
            }
        }
        FileUtil.createDir(result);
        return result;
    }

    /**
     * 
     * @return
     * @see com.banger.mobile.facade.param.SysParamService#getTaskAttachmentPath()
     */
    public String getCustomerHeadShowPath() {
        String result = "";
        Map<String, Object> map = this.sysParamDao.querySysParam();
        if (map != null) {
            Object path = map.get(FileUploadPathConstants.CUSTOMER_HEADSHOW_PATH);
            if (path != null) {
                result = path.toString();
            }
        }
        if (result.equals("")) {
            if (SystemUtil.isWindows()) {
                result = TransportConstants.RECORD_FILE_STORE_PATH_WIN;
            } else {
                result = TransportConstants.RECORD_FILE_STORE_PATH_LINUX;
            }
        }
        FileUtil.createDir(result);
        return result;
    }

    /**
     * 
     * @return
     * @see com.banger.mobile.facade.param.SysParamService#getTaskAttachmentPath()
     */
    public String getMmsImagePath() {
        String result = "";
        Map<String, Object> map = this.sysParamDao.querySysParam();
        if (map != null) {
            Object path = map.get(FileUploadPathConstants.MMS_IMAGE_PATH);
            if (path != null) {
                result = path.toString();
            }
        }
        if (result.equals("")) {
            if (SystemUtil.isWindows()) {
                result = TransportConstants.RECORD_FILE_STORE_PATH_WIN;
            } else {
                result = TransportConstants.RECORD_FILE_STORE_PATH_LINUX;
            }
        }
        FileUtil.createDir(result);
        return result;
    }
    
    /**
     * 
     * @return
     * @see com.banger.mobile.facade.param.SysParamService#getSmsFtpPath()
     */
    public String getSmsFtpPath(){
        String result = "";
        Map<String, Object> map = this.sysParamDao.querySysParam();
        if (map != null) {
            Object path = map.get(FileUploadPathConstants.SMS_FTP_PATH);
            if (path != null) {
                result = path.toString();
            }
        }
        if (result.equals("")) {
            if (SystemUtil.isWindows()) {
                result = TransportConstants.RECORD_FILE_STORE_PATH_WIN;
            } else {
                result = TransportConstants.RECORD_FILE_STORE_PATH_LINUX;
            }
        }
        FileUtil.createDir(result);
        return result;
    }
    
    /**
     * 
     * @return
     * @see com.banger.mobile.facade.param.SysParamService#getUserPhonePath()
     */
    public String getUserPhonePath() {
        String result = "";
        Map<String, Object> map = this.sysParamDao.querySysParam();
        if (map != null) {
            Object path = map.get

            (FileUploadPathConstants.USER_PHONE_PATH);
            if (path != null) {
                result = path.toString();
            }
        }
        if (result.equals("")) {
            if (SystemUtil.isWindows()) {
                result =

                TransportConstants.RECORD_FILE_STORE_PATH_WIN;
            } else {
                result =

                TransportConstants.RECORD_FILE_STORE_PATH_LINUX;
            }
        }
        FileUtil.createDir(result);
        return result;
    }

    /**
     * 
     * @return
     * @see com.banger.mobile.facade.param.SysParamService#getAuthFilePath()
     */
    public String getAuthFilePath() {
        String result = "";
        Map<String, Object> map = this.sysParamDao.querySysParam();
        if (map != null) {
            Object path = map.get

            (FileUploadPathConstants.AUTH_FILE_PATH);
            if (path != null) {
                result = path.toString();
            }
        }
        if (result.equals("")) {
            if (SystemUtil.isWindows()) {
                result =

                TransportConstants.RECORD_FILE_STORE_PATH_WIN;
            } else {
                result =

                TransportConstants.RECORD_FILE_STORE_PATH_LINUX;
            }
        }
        FileUtil.createDir(result);
        return result;
    }

    /**
     * 获取系统录音路径
     */
    public String getRecordPath() {
        return sysParamDao.getRecordPath();
    }

    @Override
    public String getParamValueByKey(String key){
        return sysParamDao.getParamValueByKey(key);
    }

    /**
     * 判断系统录音路径是否存在
     */
    public boolean isPathExist(){
        String path = sysParamDao.getRecordPath();
        if(FileUtil.isValidSystemFilePath(path)){
            File f = new File(path);
            return f.exists();
        }
        return false;
    }
    
    /**
     * 获取录音存储预警值
     */
    public Integer getCueSize(){
        return sysParamDao.getCueSize();
    }
    
    public void updateIsActiveForWeb(int num) {
        this.sysParamDao.updateIsActiveForWeb(num);
    }
    
    public String getArticleAttachmentPath() {
    	String result = "";
        Map<String, Object> map = this.sysParamDao.querySysParam();
        if (map != null) {
            Object path = map.get(FileUploadPathConstants.TASK_ATTACHMENT_PATH);
            if (path != null) {
                result = path.toString();
            }
        }
        if (result.equals("")) {
            if (SystemUtil.isWindows()) {
                result = TransportConstants.RECORD_FILE_STORE_PATH_WIN;
            } else {
                result = TransportConstants.RECORD_FILE_STORE_PATH_LINUX;
            }
        }
        FileUtil.createDir(result);
        return result;
	}
    
    /**
     * 获取自动导入的文件存放路径
     * @return
     */
    public String getAutoImportFilePath(){
    	String result = "";
        Map<String, Object> map = this.sysParamDao.querySysParam();
        if (map != null) {
            Object path = map.get(FileUploadPathConstants.AUTO_IMPORT_PATH);
            if (path != null) {
                result = path.toString();
            }
        }
        if (result.equals("")) {
            if (SystemUtil.isWindows()) {
                result = TransportConstants.AUTO_IMPORT_PATH_WIN;
            } else {
                result = TransportConstants.AUTO_IMPORT_PATH_LINUX;
            }
        }
        if(!FileUtil.isExistFilePath(result))
        	FileUtil.createDir(result);
        
        return result;
    }

	@Override
	public String getMaxAppLoanMoney() {
		return this.sysParamDao.getMaxAppLoanMoney();
	}
    
	@Override
	public String getMaxDunLoanTime() {
		return this.sysParamDao.getMaxDunLoanTime();
	}
    
	 public String getXFDDoubleApprovalTag(){
		 return this.sysParamDao.getXFDDoubleApprovalTag();
	 }
	 public String getXFDDoubleApprovalValue(){
		 return this.sysParamDao.getXFDDoubleApprovalValue();
	 }
	 public String getJYDDoubleApprovalTag(){
		 return this.sysParamDao.getJYDDoubleApprovalTag();
	 }
	 public String getJYDDoubleApprovalValue(){
		 return this.sysParamDao.getJYDDoubleApprovalValue();
	 }
}
