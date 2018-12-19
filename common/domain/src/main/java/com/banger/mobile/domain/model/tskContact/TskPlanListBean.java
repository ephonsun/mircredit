/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-12-15
 */
package com.banger.mobile.domain.model.tskContact;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author cheny
 * @version $Id: TskPlanListBean.java,v 0.1 2012-12-15 下午3:25:06 cheny Exp $
 */
public class TskPlanListBean extends BaseObject{

    private static final long serialVersionUID = 7873335436550278154L;
    
    private Integer     planId;             //计划id
    private String      tableName;          //表名
    private String      executeUser;        //执行者name
    private Integer     execUserId;         //执行者id
    private Integer     totalConnect;       //总联系量
    private Integer     finishConnect;      //有效联系量
    private Date        taskPlanDate;       //执行日期
    /**
     * 
     */
    public TskPlanListBean() {
        super();
    }
    public Integer getPlanId() {
        return planId;
    }
    public void setPlanId(Integer planId) {
        this.planId = planId;
    }
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public String getExecuteUser() {
        return executeUser;
    }
    public void setExecuteUser(String executeUser) {
        this.executeUser = executeUser;
    }
    public Integer getExecUserId() {
        return execUserId;
    }
    public void setExecUserId(Integer execUserId) {
        this.execUserId = execUserId;
    }
    public Integer getTotalConnect() {
        return totalConnect;
    }
    public void setTotalConnect(Integer totalConnect) {
        this.totalConnect = totalConnect;
    }
    public Integer getFinishConnect() {
        return finishConnect;
    }
    public void setFinishConnect(Integer finishConnect) {
        this.finishConnect = finishConnect;
    }
    public Date getTaskPlanDate() {
        return taskPlanDate;
    }
    public void setTaskPlanDate(Date taskPlanDate) {
        this.taskPlanDate = taskPlanDate;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof TskPlanListBean)) {
            return false;
        }
        TskPlanListBean rhs = (TskPlanListBean) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.executeUser, rhs.executeUser).append(this.totalConnect, rhs.totalConnect)
            .append(this.tableName, rhs.tableName).append(this.execUserId, rhs.execUserId)
            .append(this.taskPlanDate, rhs.taskPlanDate)
            .append(this.finishConnect, rhs.finishConnect).append(this.planId, rhs.planId)
            .isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1051135443, -75772515).appendSuper(super.hashCode())
            .append(this.executeUser).append(this.totalConnect).append(this.tableName)
            .append(this.execUserId).append(this.taskPlanDate).append(this.finishConnect)
            .append(this.planId).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("taskPlanDate", this.taskPlanDate)
            .append("totalConnect", this.totalConnect).append("execUserId", this.execUserId)
            .append("planId", this.planId).append("finishConnect", this.finishConnect)
            .append("endRow", this.getEndRow()).append("tableName", this.tableName)
            .append("executeUser", this.executeUser).append("startRow", this.getStartRow())
            .toString();
    }
    
    
    
    

}
