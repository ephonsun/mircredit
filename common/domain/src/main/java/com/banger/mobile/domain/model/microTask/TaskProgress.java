/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2013-1-28
 */
package com.banger.mobile.domain.model.microTask;

/**
 * @author yuanme
 * @version $Id: TaskProgress.java,v 0.1 2013-1-28 上午11:52:26 Administrator Exp $
 */
public class TaskProgress {
    private Integer taskTarget;
    private Integer finishCount;
    private Integer newCustomerCount;
    private Integer oldCustomerCount;

    public Integer getTaskTarget() {
        return taskTarget;
    }

    public void setTaskTarget(Integer taskTarget) {
        this.taskTarget = taskTarget;
    }

    public Integer getFinishCount() {
        return finishCount;
    }

    public void setFinishCount(Integer finishCount) {
        this.finishCount = finishCount;
    }

    public Integer getNewCustomerCount() {
        return newCustomerCount;
    }

    public void setNewCustomerCount(Integer newCustomerCount) {
        this.newCustomerCount = newCustomerCount;
    }

    public Integer getOldCustomerCount() {
        return oldCustomerCount;
    }

    public void setOldCustomerCount(Integer oldCustomerCount) {
        this.oldCustomerCount = oldCustomerCount;
    }
}
