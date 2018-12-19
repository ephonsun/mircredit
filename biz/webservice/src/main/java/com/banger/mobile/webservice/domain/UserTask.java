/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-7-20
 */
package com.banger.mobile.webservice.domain;

import java.io.Serializable;

/**
 * @author yuanme
 * @version Product.java,v 0.1 2012-7-20 上午11:32:49
 */
public class UserTask implements Serializable {

    private static final long serialVersionUID = 3220851069143683114L;

    private Integer           userId;
    private String            userName;
    private Integer           taskTarget;
    private Integer           finishCount;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
