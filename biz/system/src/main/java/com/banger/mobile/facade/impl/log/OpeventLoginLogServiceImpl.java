/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :日志service
 * Author     :yujh
 * Create Date:Aug 14, 2012
 */
package com.banger.mobile.facade.impl.log;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.log.OpeventLoginLogDao;
import com.banger.mobile.domain.model.log.OpeventLoginLog;
import com.banger.mobile.domain.model.log.OpeventLoginLogInfo;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.facade.log.OpeventLoginLogService;

/**
 * @author yujh
 * @version $Id: OpeventLoginLogServiceImpl.java,v 0.1 Aug 14, 2012 11:01:02 AM Administrator Exp $
 */
public class OpeventLoginLogServiceImpl implements OpeventLoginLogService {
    private OpeventLoginLogDao opeventLoginLogDao;

    public void setOpeventLoginLogDao(OpeventLoginLogDao opeventLoginLogDao) {
        this.opeventLoginLogDao = opeventLoginLogDao;
    }

    /**
     * 添加日志
     */
    public void addLog(int opeventObjId, String opeventAction,
                        int clientTypeId, int state) {
        
        HttpServletRequest req = org.apache.struts2.ServletActionContext.getRequest();
        HttpSession session = req.getSession();
        int userId=((IUserInfo)session.getAttribute("LoginInfo")).getUserId();       //用户id
        String  ip=((IUserInfo)session.getAttribute("LoginInfo")).getIP();           //ip
        
        
        OpeventLoginLog log = new OpeventLoginLog();
        log.setUserId(userId);
        log.setOpeventObjectId(opeventObjId);
        log.setOpeventAction(opeventAction);
        log.setOpeventIp(ip);
        log.setClientTypeId(clientTypeId);
        log.setState(state);
        log.setRemark("");
        log.setOpeventDate(new Date());
        this.opeventLoginLogDao.addLog(log);
    }

    public PageUtil<OpeventLoginLogInfo> getLoginLogPage(Map<String, Object> parameters, Page page) {
        return this.opeventLoginLogDao.getLogPage(parameters, page);
    }

    public void deleteLog() {
        this.opeventLoginLogDao.dropLog();
    }

}
