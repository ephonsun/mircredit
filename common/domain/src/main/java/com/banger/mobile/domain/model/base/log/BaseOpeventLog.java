/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :操作日志基类
 * Author     :yujh
 * Create Date:May 23, 2012
 */
package com.banger.mobile.domain.model.base.log;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author yujh
 * @version $Id: BaseOpeventLog.java,v 0.1 May 23, 2012 10:33:00 AM Administrator Exp $
 */
public class BaseOpeventLog extends BaseObject implements Serializable {

    private static final long serialVersionUID = -1151291580101521798L;

    private Integer           opeventId;                               //操作日志编号
    private Integer           userId;                                  //用户id
    private String            opeventObject;                           //操作模块
    private String            opeventAction;                           //操作动作
    private Date              opeventTime;                             //操作日期
    private String            opeventIp;                               //操作ip
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

    public String getOpeventObject() {
        return opeventObject;
    }

    public void setOpeventObject(String opeventObject) {
        this.opeventObject = opeventObject;
    }

    public String getOpeventAction() {
        return opeventAction;
    }

    public void setOpeventAction(String opeventAction) {
        this.opeventAction = opeventAction;
    }

    public Date getOpeventTime() {
        return opeventTime;
    }

    public void setOpeventTime(Date opeventTime) {
        this.opeventTime = opeventTime;
    }

    public String getOpeventIp() {
        return opeventIp;
    }

    public void setOpeventIp(String opeventIp) {
        this.opeventIp = opeventIp;
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
        if (!(object instanceof BaseOpeventLog)) {
            return false;
        }
        BaseOpeventLog rhs = (BaseOpeventLog) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.opeventAction,
            rhs.opeventAction).append(this.opeventObject, rhs.opeventObject).append(this.userId,
            rhs.userId).append(this.opeventTime, rhs.opeventTime).append(this.opeventId,
            rhs.opeventId).append(this.opeventIp, rhs.opeventIp).append(this.state, rhs.state)
            .append(this.remark, rhs.remark).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1409396971, -188566509).appendSuper(super.hashCode()).append(
            this.opeventAction).append(this.opeventObject).append(this.userId).append(
            this.opeventTime).append(this.opeventId).append(this.opeventIp).append(this.state)
            .append(this.remark).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("state", this.state).append("opeventIp",
            this.opeventIp).append("opeventId", this.opeventId).append("startRow",
            this.getStartRow()).append("opeventTime", this.opeventTime).append("endRow",
            this.getEndRow()).append("remark", this.remark).append("opeventAction",
            this.opeventAction).append("userId", this.userId).append("opeventObject",
            this.opeventObject).toString();
    }

}
