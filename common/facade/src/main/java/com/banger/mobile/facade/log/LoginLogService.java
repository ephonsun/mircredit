/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :安全日志service
 * Author     :yujh
 * Create Date:May 21, 2012
 */
package com.banger.mobile.facade.log;

import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.log.LoginLog;
import com.banger.mobile.domain.model.log.LoginLogInfo;

/**
 * @author yujh
 * @version $Id: LoginLogService.java,v 0.1 May 21, 2012 1:38:45 PM Administrator Exp $
 */
public interface LoginLogService {
   /**
    * 添加安全日志
    * @param userId 
    * @param ip
    * @param type 登陆类型 1登陆系统，0退出系统
    * @param device 登陆设备，1：web 2:pad
    */
    public void addLoginLog(int userId,String ip,int type,int device);
    /**
     * 分页
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<LoginLogInfo> getLoginLogPage(Map<String, Object> parameters, Page page);
}
