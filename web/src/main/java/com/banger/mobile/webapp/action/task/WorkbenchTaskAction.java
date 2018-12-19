/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :工作台任务提醒Action
 * Author     :liyb
 * Create Date:2012-8-30
 */
package com.banger.mobile.webapp.action.task;

/**
 * @author liyb
 * @version $Id: WorkbenchTaskAction.java,v 0.1 2012-8-30 上午10:42:13 liyb Exp $
 */
public class WorkbenchTaskAction extends TskTaskAction {

    private static final long serialVersionUID = 4623811375275828923L;
    private Integer workTskFlag;//工作台任务提醒类型    2:今天  5:已过期

    /**
     * 加载工作台任务提醒页
     * @return
     */
    public String initTask(){
        return SUCCESS;
    }
    
    /**
     * 初始化任务提醒
     * @return
     */
    public String initWorkTask(){
        try {
            findTskTaskMsg(workTskFlag);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("initWorkTask action error:"+e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 工作台，更多任务
     * @return
     */
    public String getMoreTask(){
        try {
            findTskTaskMsg(workTskFlag);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("getMoreTask action error:"+e.getMessage());
            return ERROR;
        }
    }

    public Integer getWorkTskFlag() {
        return workTskFlag;
    }

    public void setWorkTskFlag(Integer workTskFlag) {
        this.workTskFlag = workTskFlag;
    }
}
