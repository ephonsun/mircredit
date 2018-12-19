/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :安全日志service实现类
 * Author     :yujh
 * Create Date:May 21, 2012
 */
package com.banger.mobile.facade.impl.log;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.loginLog.LoginLogDao;
import com.banger.mobile.domain.model.log.LoginLog;
import com.banger.mobile.domain.model.log.LoginLogInfo;
import com.banger.mobile.facade.log.LoginLogService;

/**
 * @author yujh
 * @version $Id: LoginLogServiceImpl.java,v 0.1 May 21, 2012 1:52:04 PM Administrator Exp $
 */
public class LoginLogServiceImpl implements LoginLogService {
    
    protected final Log   log = LogFactory.getLog(getClass());
    
    private LoginLogDao loginLogDao;

    public void setLoginLogDao(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }

   /**
    * 添加安全日志
    * @param userId 用户id
    * @param ip     登陆ip    
    * @param type   登陆类型
    * @param device 登陆设备
    * @see com.banger.mobile.facade.log.LoginLogService#addLoginLog(int, java.lang.String, int, int)
    */
    public void addLoginLog(int userId,String ip,int type,int device) {
        this.loginLogDao.addLoginLog(userId,ip,type,device);
    }

    /**
     * 分页&查询
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.facade.log.LoginLogService#getLoginLogPage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<LoginLogInfo> getLoginLogPage(Map<String, Object> parameters, Page page) {
        return loginLogDao.getLoginLogPage(parameters, page);
    }





}
