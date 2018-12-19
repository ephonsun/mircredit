package com.banger.mobile.domain.model.base.tskMarketing;

import java.math.BigDecimal;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * TskMarketingPlanDetail entity. @author MyEclipse Persistence Tools
 */
public class BaseTskMarketingPlanDetail extends BaseObject {

    private static final long serialVersionUID = -4161014052427322226L;

    // Fields    

    private Integer           planDetailId;
    private Integer           planId;
    private Integer           marketingId;
    private Integer           productId;
    private BigDecimal        planDetailTarget;
    private String            remark;

    // Constructors

    /** default constructor */
    public BaseTskMarketingPlanDetail() {
    }

    /** minimal constructor */
    public BaseTskMarketingPlanDetail(Integer planDetailId, Integer planId, Integer marketingId,
                                      Integer productId) {
        this.planDetailId = planDetailId;
        this.planId = planId;
        this.marketingId = marketingId;
        this.productId = productId;
    }

    /** full constructor */
    public BaseTskMarketingPlanDetail(Integer planDetailId, Integer planId, Integer marketingId,
                                      Integer productId, BigDecimal planDetailTarget, String remark) {
        this.planDetailId = planDetailId;
        this.planId = planId;
        this.marketingId = marketingId;
        this.productId = productId;
        this.planDetailTarget = planDetailTarget;
        this.remark = remark;
    }

    // Property accessors
    public Integer getPlanDetailId() {
        return this.planDetailId;
    }

    public void setPlanDetailId(Integer planDetailId) {
        this.planDetailId = planDetailId;
    }

    public Integer getPlanId() {
        return this.planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Integer getMarketingId() {
        return this.marketingId;
    }

    public void setMarketingId(Integer marketingId) {
        this.marketingId = marketingId;
    }

    public Integer getProductId() {
        return this.productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getPlanDetailTarget() {
        return this.planDetailTarget;
    }

    public void setPlanDetailTarget(BigDecimal planDetailTarget) {
        this.planDetailTarget = planDetailTarget;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((marketingId == null) ? 0 : marketingId.hashCode());
        result = prime * result + ((planDetailId == null) ? 0 : planDetailId.hashCode());
        result = prime * result + ((planDetailTarget == null) ? 0 : planDetailTarget.hashCode());
        result = prime * result + ((planId == null) ? 0 : planId.hashCode());
        result = prime * result + ((productId == null) ? 0 : productId.hashCode());
        result = prime * result + ((remark == null) ? 0 : remark.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseTskMarketingPlanDetail other = (BaseTskMarketingPlanDetail) obj;
        if (marketingId == null) {
            if (other.marketingId != null)
                return false;
        } else if (!marketingId.equals(other.marketingId))
            return false;
        if (planDetailId == null) {
            if (other.planDetailId != null)
                return false;
        } else if (!planDetailId.equals(other.planDetailId))
            return false;
        if (planDetailTarget == null) {
            if (other.planDetailTarget != null)
                return false;
        } else if (!planDetailTarget.equals(other.planDetailTarget))
            return false;
        if (planId == null) {
            if (other.planId != null)
                return false;
        } else if (!planId.equals(other.planId))
            return false;
        if (productId == null) {
            if (other.productId != null)
                return false;
        } else if (!productId.equals(other.productId))
            return false;
        if (remark == null) {
            if (other.remark != null)
                return false;
        } else if (!remark.equals(other.remark))
            return false;
        return true;
    }

}