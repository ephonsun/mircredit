/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :操作日志action
 * Author     :yujh
 * Create Date:May 23, 2012
 */
package com.banger.mobile.webapp.action.log;

import java.util.HashMap;
import java.util.Map;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.log.OpeventLogInfo;
import com.banger.mobile.facade.log.OpeventLogService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author yujh
 * @version $Id: OpeventLogAction.java,v 0.1 May 23, 2012 1:13:27 PM Administrator Exp $
 */
public class OpeventLogAction extends BaseAction{

    private static final long serialVersionUID = 6695909197790540913L;
    
    private OpeventLogService opeventLogService;
    
    private PageUtil<OpeventLogInfo> logList;
    
    private Integer logCount;
    /**
     * 分页&查询
     * @return
     */
    public String showOpeventLogListPage(){
        try{
            Map<String, Object> map = new HashMap<String, Object>();
            String startTime=(String) request.getParameter("startTime");//查询开始时间
            String endTime=(String) request.getParameter("endTime");    //查询结束时间
            map.put("startLogDate", startTime);
            map.put("endLogDate", endTime);
            request.setAttribute("startLogDate", startTime);
            request.setAttribute("endLogDate", endTime);
            logList=opeventLogService.getOpeventLogPage(map, this.getPage());
            logCount =this.getPage().getTotalRowsAmount();
        return SUCCESS;
        }catch (Exception e) {
            log.error("showOpeventLogListPage action error:" + e.getMessage());
            return ERROR;
        }
    }

    public PageUtil<OpeventLogInfo> getLogList() {
        return logList;
    }

    public void setLogList(PageUtil<OpeventLogInfo> logList) {
        this.logList = logList;
    }

    public OpeventLogService getOpeventLogService() {
        return opeventLogService;
    }

    public void setOpeventLogService(OpeventLogService opeventLogService) {
        this.opeventLogService = opeventLogService;
    }

    public Integer getLogCount() {
        return logCount;
    }

    public void setLogCount(Integer logCount) {
        this.logCount = logCount;
    }
    
}
