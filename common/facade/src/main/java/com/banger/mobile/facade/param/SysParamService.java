/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :全局参数service接口
 * Author     :yujh
 * Create Date:May 25, 2012
 */
package com.banger.mobile.facade.param;

import java.util.Map;

/**
 * @author yujh
 * @version $Id: SysParamService.java,v 0.1 May 25, 2012 10:57:58 AM Administrator Exp $
 */
public interface SysParamService {
    /**
     * 设置是否启用限流
     */
    public void updateIsActive(int num);
    
    /**
     * web端是否启用限流
     * @param num
     */
    public void updateIsActiveForWeb(int num);
    /**
     * 设置最大流量
     */
    public void updateMaxFlow(int maxBPS);
    /**
     * 查询流量控制参数
     * @return
     */
    public Map<String,Object> getFlowControlParam() ;
    
    /**
     * 查询系统全局参数
     * return list sysparm
     */
    public Map<String,Object> querySysParam();
    
    /**
     * 修改系统全局参数
     * param map
     */
    public void updateSysParam(Map<String, Object> map);
    
    /**
     * 查询不被拦截的action集合
     */
    public String getExcludeActions();
    
    /**
     * 查询任务附件上传路径
     * @return
     */
    public String getTaskAttachmentPath();
    
    /**
     * 查询客户头像上传路径
     * @return
     */
    public String getCustomerHeadShowPath();
    
    /**
     * 查询彩信图片上传路径
     * @return
     */
    public String getMmsImagePath();
    
    /**
     * 短信文件上传路径
     * @return
     */
    public String getSmsFtpPath();
    
    /**
     * 查询授权文件路径
     * @return
     */
    public String getAuthFilePath();
    
    /**
     * 用户自定义话机声音文件
     */
    public String getUserPhonePath();
    
    /**
     * 获取系统录音路径
     */
    public String getRecordPath();
    
    /**
     * 获取录音存储预警值
     */
    public Integer getCueSize();

    String getParamValueByKey(String key);

    /**
     * 判断系统录音路径是否存在
     */
    public boolean isPathExist();
    
    /**
     * 查询文章附件上传路径
     * @return
     */
    public String getArticleAttachmentPath();
    
    /**
     * 获取自动导入的文件存放路径
     * @return
     */
    public String getAutoImportFilePath();
    
    /**
     * 获取决议最大金额
     * 
     * @return
     */
    public String getMaxAppLoanMoney();
    /**
     * 获取最大提前催款时间
     * 
     * @return
     */
    public String getMaxDunLoanTime();
    
	 public String getXFDDoubleApprovalTag();
	 public String getXFDDoubleApprovalValue();
	 public String getJYDDoubleApprovalTag();
	 public String getJYDDoubleApprovalValue();
}
