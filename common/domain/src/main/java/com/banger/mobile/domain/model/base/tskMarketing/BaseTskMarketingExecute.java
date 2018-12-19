/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :营销任务明细表-Domain
 * Author     :QianJie
 * Create Date:Dec 27, 2012
 */
package com.banger.mobile.domain.model.base.tskMarketing;

import java.math.BigDecimal;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author QianJie
 * @version $Id: BaseTskMarketingExecute.java,v 0.1 Dec 27, 2012 11:35:28 AM QianJie Exp $
 */
public class BaseTskMarketingExecute extends BaseObject {

    private static final long serialVersionUID = 7625034197173212039L;

    private Integer           executeId;                              //主键
    private Integer           marketingId;                            //任务ID
    private Integer           deptId;                                 //部门ID
    private Integer           userId;                                 //人员ID  执行者ID
    private BigDecimal        marketingTarget;                        //营销目标
    private BigDecimal        deptUnassign;                           //未分配

    public BaseTskMarketingExecute() {
        super();
    }

    
    public BaseTskMarketingExecute(Integer executeId, Integer marketingId, Integer deptId,
                                   Integer userId, BigDecimal marketingTarget,
                                   BigDecimal deptUnassign) {
        super();
        this.executeId = executeId;
        this.marketingId = marketingId;
        this.deptId = deptId;
        this.userId = userId;
        this.marketingTarget = marketingTarget;
        this.deptUnassign = deptUnassign;
    }


    public Integer getExecuteId() {
        return executeId;
    }

    public void setExecuteId(Integer executeId) {
        this.executeId = executeId;
    }

    public Integer getMarketingId() {
        return marketingId;
    }

    public void setMarketingId(Integer marketingId) {
        this.marketingId = marketingId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getMarketingTarget() {
        return marketingTarget;
    }

    public void setMarketingTarget(BigDecimal marketingTarget) {
        this.marketingTarget = marketingTarget;
    }

    public BigDecimal getDeptUnassign() {
        return deptUnassign;
    }

    public void setDeptUnassign(BigDecimal deptUnassign) {
        this.deptUnassign = deptUnassign;
    }


    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseTskMarketingExecute)) {
            return false;
        }
        BaseTskMarketingExecute rhs = (BaseTskMarketingExecute) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.deptUnassign,
            rhs.deptUnassign).append(this.marketingId, rhs.marketingId).append(
            this.marketingTarget, rhs.marketingTarget).append(this.userId, rhs.userId).append(
            this.deptId, rhs.deptId).append(this.executeId, rhs.executeId).isEquals();
    }


    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1784854315, -1214653451).appendSuper(super.hashCode()).append(
            this.deptUnassign).append(this.marketingId).append(this.marketingTarget).append(
            this.userId).append(this.deptId).append(this.executeId).toHashCode();
    }


    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("deptId", this.deptId).append("startRow",
            this.getStartRow()).append("endRow", this.getEndRow()).append("marketingTarget",
            this.marketingTarget).append("deptUnassign", this.deptUnassign).append("marketingId",
            this.marketingId).append("executeId", this.executeId).append("userId", this.userId)
            .toString();
    }

    

}
