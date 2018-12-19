/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :操作日志service实现类
 * Author     :yujh
 * Create Date:May 23, 2012
 */
package com.banger.mobile.facade.impl.log;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import banger.web.action.Current;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.opeventLog.OpeventLogDao;
import com.banger.mobile.domain.model.log.OpeventLog;
import com.banger.mobile.domain.model.log.OpeventLogInfo;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.facade.log.OpeventLogService;

/**
 * @author yujh
 * @version $Id: OpeventLogServiceImpl.java,v 0.1 May 23, 2012 11:57:14 AM Administrator Exp $
 */
public class OpeventLogServiceImpl implements OpeventLogService{
    
    protected final Log   log = LogFactory.getLog(getClass());
    
    private OpeventLogDao opeventLogDao;
    
    public void setOpeventLogDao(OpeventLogDao opeventLogDao) {
        this.opeventLogDao = opeventLogDao;
    }

    /**
     * 添加操作日志
     * @param obj           操作对象
     * @param act           操作信息
     * @param state         操作状态，1成功，0失败
     * @param remark        备注
     * @see com.banger.mobile.facade.log.OpeventLogService#addOpeventLog()
     */
    
    public void addOpeventLog(String obj,String act,int state,String remark) {
        
        OpeventLog opeventLog   =     new OpeventLog();
        Date       opeventTime  =     new Date();
        
        HttpServletRequest req = org.apache.struts2.ServletActionContext.getRequest();
        HttpSession session = req.getSession();

        int userId=((IUserInfo)session.getAttribute("LoginInfo")).getUserId();       //用户id
        String  ip=((IUserInfo)session.getAttribute("LoginInfo")).getIP();           //ip
        
        opeventLog.setUserId(userId);
        opeventLog.setOpeventIp(ip);
        opeventLog.setOpeventObject(obj);
        opeventLog.setOpeventAction(act);
        opeventLog.setState(state);
        opeventLog.setRemark(remark);
        opeventLog.setOpeventTime(opeventTime);
        
        this.opeventLogDao.addOpeventLog(opeventLog);
    }

    /**
     * 分页&查询
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.facade.log.OpeventLogService#getOpeventLogPage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<OpeventLogInfo> getOpeventLogPage(Map<String, Object> parameters, Page page) {
        return this.opeventLogDao.getOpeventLogPage(parameters, page);
    }

}
