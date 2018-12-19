/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :操作事件实体类
 * Author     :yujh
 * Create Date:Aug 14, 2012
 */
package com.banger.mobile.domain.model.base.log;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * @author Administrator
 * @version $Id: BaseOpeventLoginLog.java,v 0.1 Aug 14, 2012 9:29:59 AM Administrator Exp $
 */
public class BaseOpeventLoginLog extends BaseObject implements Serializable{
    private static final long serialVersionUID = 2552045224489743038L;
    private Integer           opeventId;                               //操作日志编号
    private Integer           userId;                                  //用户id
    private Integer           opeventObjectId;                         //操作模块
    private String            opeventAction;                           //操作动作
    private Date              opeventDate;                             //操作日期
    private String            opeventIp;                               //操作ip
    private Integer           clientTypeId;                            //终端类型id
    private Integer           state;                                   //操作状态，1成功，0失败
    private String            remark;                                  //操作备注
    public Integer getOpeventId() {
        return opeventId;
    }
    public void setOpeventId(Integer opeventId) {
        this.opeventId = opeventId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getOpeventObjectId() {
        return opeventObjectId;
    }
    public void setOpeventObjectId(Integer opeventObjectId) {
        this.opeventObjectId = opeventObjectId;
    }
    public String getOpeventAction() {
        return opeventAction;
    }
    public void setOpeventAction(String opeventAction) {
        this.opeventAction = opeventAction;
    }
    public Date getOpeventDate() {
        return opeventDate;
    }
    public void setOpeventDate(Date opeventDate) {
        this.opeventDate = opeventDate;
    }
    public String getOpeventIp() {
        return opeventIp;
    }
    public void setOpeventIp(String opeventIp) {
        this.opeventIp = opeventIp;
    }
    public Integer getClientTypeId() {
        return clientTypeId;
    }
    public void setClientTypeId(Integer clientTypeId) {
        this.clientTypeId = clientTypeId;
    }
    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseOpeventLoginLog)) {
            return false;
        }
        BaseOpeventLoginLog rhs = (BaseOpeventLoginLog) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.opeventAction,
            rhs.opeventAction).append(this.opeventDate, rhs.opeventDate).append(this.userId,
            rhs.userId).append(this.opeventId, rhs.opeventId).append(this.opeventIp, rhs.opeventIp)
            .append(this.state, rhs.state).append(this.clientTypeId, rhs.clientTypeId).append(
                this.opeventObjectId, rhs.opeventObjectId).append(this.remark, rhs.remark)
            .isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(49289011, -1533594715).appendSuper(super.hashCode()).append(
            this.opeventAction).append(this.opeventDate).append(this.userId).append(this.opeventId)
            .append(this.opeventIp).append(this.state).append(this.clientTypeId).append(
                this.opeventObjectId).append(this.remark).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("state", this.state).append("opeventIp",
            this.opeventIp).append("opeventId", this.opeventId).append("opeventObjectId",
            this.opeventObjectId).append("opeventDate", this.opeventDate).append("clientTypeId",
            this.clientTypeId).append("remark", this.remark).append("opeventAction",
            this.opeventAction).append("userId", this.userId).toString();
    }
    
}
