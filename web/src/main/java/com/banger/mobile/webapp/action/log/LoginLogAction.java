/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :安全日志action
 * Author     :yujh
 * Create Date:May 21, 2012
 */
package com.banger.mobile.webapp.action.log;

import java.util.HashMap;
import java.util.Map;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.log.LoginLogInfo;
import com.banger.mobile.facade.log.LoginLogService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author yujh
 * @version $Id: LoginLogAction.java,v 0.1 May 21, 2012 2:02:11 PM Administrator Exp $
 */
public class LoginLogAction extends BaseAction {

    private static final long      serialVersionUID = 1094394207035955334L;

    private LoginLogService        loginLogService;

    private PageUtil<LoginLogInfo> loginLogList;

    private LoginLogInfo           loginLogInfo;

    private Integer                 logCount;
    
    /**
     * 显示登录日志列表页面
     * @return
     */
    public String showLoginLogListPage() {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            String startTime = (String) request.getParameter("startTime");//查询开始时间
            String endTime = (String) request.getParameter("endTime"); //查询结束时间
            map.put("startLogDate", startTime);
            map.put("endLogDate", endTime);
            request.setAttribute("startLogDate", startTime);
            request.setAttribute("endLogDate", endTime);
            loginLogList = loginLogService.getLoginLogPage(map, this.getPage());
            logCount=this.getPage().getTotalRowsAmount();
            return SUCCESS;
        } catch (Exception e) {
            log.error("showLoginLogListPage action error:" + e.getMessage());
            return ERROR;
        }
    }

    public LoginLogService getLoginLogService() {
        return loginLogService;
    }

    public void setLoginLogService(LoginLogService loginLogService) {
        this.loginLogService = loginLogService;
    }

    public PageUtil<LoginLogInfo> getLoginLogList() {
        return loginLogList;
    }

    public void setLoginLogList(PageUtil<LoginLogInfo> loginLogList) {
        this.loginLogList = loginLogList;
    }

    public LoginLogInfo getLoginLogInfo() {
        return loginLogInfo;
    }

    public void setLoginLogInfo(LoginLogInfo loginLogInfo) {
        this.loginLogInfo = loginLogInfo;
    }

    public Integer getLogCount() {
        return logCount;
    }

    public void setLogCount(Integer logCount) {
        this.logCount = logCount;
    }
    
}
