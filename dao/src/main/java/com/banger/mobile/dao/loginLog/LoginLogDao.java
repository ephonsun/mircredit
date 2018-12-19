/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :安全日志dao
 * Author     :yujh
 * Create Date:May 21, 2012
 */
package com.banger.mobile.dao.loginLog;

import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.log.LoginLog;
import com.banger.mobile.domain.model.log.LoginLogInfo;

/**
 * @author yujh
 * @version $Id: LoginLogDao.java,v 0.1 May 21, 2012 5:04:23 PM Administrator Exp $
 */
public interface LoginLogDao {
    /**
     * 添加安全日志
     * @param userId        用户id
     * @param ip            登陆ip    
     * @param type          登陆类型
     * @param device        登陆设备
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
