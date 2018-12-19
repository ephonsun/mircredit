package com.banger.mobile.domain.model.base.tskMarketing;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * TskMarketingPlan entity. @author MyEclipse Persistence Tools
 */
public class BaseTskMarketingPlan extends BaseObject {
    
    private static final long serialVersionUID = -3412056346834770851L;

    // Fields    

    private Integer           planId;
    private Integer           marketingId;
    private Date              planDate;
    private Integer           executeUserId;

    // Constructors

    /** default constructor */
    public BaseTskMarketingPlan() {
    }

    /** full constructor */
    public BaseTskMarketingPlan(Integer planId, Integer marketingId, Date planDate,
                            Integer executeUserId) {
        this.planId = planId;
        this.marketingId = marketingId;
        this.planDate = planDate;
        this.executeUserId = executeUserId;
    }

    // Property accessors
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

    public Date getPlanDate() {
        return this.planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    public Integer getExecuteUserId() {
        return this.executeUserId;
    }

    public void setExecuteUserId(Integer executeUserId) {
        this.executeUserId = executeUserId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((executeUserId == null) ? 0 : executeUserId.hashCode());
        result = prime * result + ((marketingId == null) ? 0 : marketingId.hashCode());
        result = prime * result + ((planDate == null) ? 0 : planDate.hashCode());
        result = prime * result + ((planId == null) ? 0 : planId.hashCode());
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
        BaseTskMarketingPlan other = (BaseTskMarketingPlan) obj;
        if (executeUserId == null) {
            if (other.executeUserId != null)
                return false;
        } else if (!executeUserId.equals(other.executeUserId))
            return false;
        if (marketingId == null) {
            if (other.marketingId != null)
                return false;
        } else if (!marketingId.equals(other.marketingId))
            return false;
        if (planDate == null) {
            if (other.planDate != null)
                return false;
        } else if (!planDate.equals(other.planDate))
            return false;
        if (planId == null) {
            if (other.planId != null)
                return false;
        } else if (!planId.equals(other.planId))
            return false;
        return true;
    }

}