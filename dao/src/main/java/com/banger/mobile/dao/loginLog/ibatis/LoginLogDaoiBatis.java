/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :安全日志dao实现类
 * Author     :yujh
 * Create Date:May 21, 2012
 */
package com.banger.mobile.dao.loginLog.ibatis;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.loginLog.LoginLogDao;
import com.banger.mobile.domain.model.log.LoginLog;
import com.banger.mobile.domain.model.log.LoginLogInfo;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author yujh
 * @version $Id: LoginLogDaoiBatis.java,v 0.1 May 21, 2012 5:08:36 PM Administrator Exp $
 */
public class LoginLogDaoiBatis extends GenericDaoiBatis implements LoginLogDao{

    public LoginLogDaoiBatis(Class persistentClass) {
        super(persistentClass);
    }
    public LoginLogDaoiBatis(){
        super(LoginLog.class);
    }
    /**
     * 添加日志
     * @param userId
     * @param ip
     * @param type
     * @param device
     * @see com.banger.mobile.dao.loginLog.LoginLogDao#addLoginLog(int, java.lang.String, int, int)
     */
    public void addLoginLog(int userId,String ip,int type,int device) {
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(userId);
        loginLog.setLogIp(ip);
        loginLog.setLogType(type);
        loginLog.setLoginDevice(device);
        loginLog.setLogDate(new Date());
        this.getSqlMapClientTemplate().insert("addLoginLog", loginLog);
    }
    /**
     * 分页，查询
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.dao.loginLog.LoginLogDao#getLoginLogPage(java.util.Map, com.banger.mobile.Page)
     */
    @SuppressWarnings("unchecked")
    public PageUtil<LoginLogInfo> getLoginLogPage(Map<String, Object> parameters, Page page) {
        ArrayList<LoginLogInfo> list = (ArrayList<LoginLogInfo>) this.findQueryPage(
                "getLoginLogPageMap", "getLoginLogCount", parameters, page);
        if (list == null) {
            list = new ArrayList<LoginLogInfo>();
        }
        return new PageUtil<LoginLogInfo>(list, page);
    }

}
