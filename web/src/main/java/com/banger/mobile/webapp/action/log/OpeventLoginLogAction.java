/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :系统日志action
 * Author     :yujh
 * Create Date:Aug 16, 2012
 */
package com.banger.mobile.webapp.action.log;

import java.util.HashMap;
import java.util.Map;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.log.OpeventLoginLogInfo;
import com.banger.mobile.facade.log.OpeventLoginLogService;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author yujh
 * @version $Id: OpeventLoginLogAction.java,v 0.1 Aug 16, 2012 1:43:58 PM Administrator Exp $
 */
public class OpeventLoginLogAction extends BaseAction{

    private static final long serialVersionUID = 0L;
    private     OpeventLoginLogService          opeventLoginLogService;
    private     OpeventLoginLogInfo             opeventLoginLogInfo;
    private     PageUtil<OpeventLoginLogInfo>   logList;
    private     Integer                         count;
    /**
     * 分页，查询 
     * @return
     */
    public String listAllSysLog(){
        try{
            Map<String, Object> map = new HashMap<String, Object>();
            String startTime=(String) request.getParameter("startTime");//查询开始时间
            String endTime=(String) request.getParameter("endTime");    //查询结束时间
            map.put("startLogDate", StringUtil.ReplaceSQLChar(startTime));
            map.put("endLogDate", StringUtil.ReplaceSQLChar(endTime));
            if(request.getParameter("opeventObj")!=null){
                map.put("opeventObj", StringUtil.ReplaceSQLChar(request.getParameter("opeventObj").trim()));
            }
            if(request.getParameter("userName")!=null){
                map.put("userName",StringUtil.ReplaceSQLChar(request.getParameter("userName").trim()));
            }
            
            request.setAttribute("startLogDate", startTime);
            request.setAttribute("endLogDate", endTime);
            request.setAttribute("opeventObj", request.getParameter("opeventObj"));
            request.setAttribute("userName", request.getParameter("userName"));
            logList=opeventLoginLogService.getLoginLogPage(map, this.getPage());
            count =this.getPage().getTotalRowsAmount();
        return SUCCESS;
        }catch (Exception e) {
            log.error("listAllSysLog action error:" + e.getMessage());
            return ERROR;
        }
    }
    /**
     * 清空日志
     * @return
     */
    public String deleteAllSysLog(){
        this.opeventLoginLogService.deleteLog();
        return SUCCESS;
    }
    
    
    public OpeventLoginLogInfo getOpeventLoginLogInfo() {
        return opeventLoginLogInfo;
    }
    public void setOpeventLoginLogInfo(OpeventLoginLogInfo opeventLoginLogInfo) {
        this.opeventLoginLogInfo = opeventLoginLogInfo;
    }
    public PageUtil<OpeventLoginLogInfo> getLogList() {
        return logList;
    }
    public void setLogList(PageUtil<OpeventLoginLogInfo> logList) {
        this.logList = logList;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    public void setOpeventLoginLogService(OpeventLoginLogService opeventLoginLogService) {
        this.opeventLoginLogService = opeventLoginLogService;
    }

}
