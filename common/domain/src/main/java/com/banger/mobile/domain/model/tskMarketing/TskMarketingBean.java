/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :QianJie
 * Create Date:Jan 7, 2013
 */
package com.banger.mobile.domain.model.tskMarketing;

import java.math.BigDecimal;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author QianJie
 * @version $Id: TskMarketingBean.java,v 0.1 Jan 7, 2013 3:18:42 PM QianJie Exp $
 */
public class TskMarketingBean extends TskMarketing {

    private static final long serialVersionUID = 5392735661284817679L;

    private String            productTarget;                          //产品指标
    private BigDecimal        taskTarget;                             //任务目标
    private BigDecimal        buyMoneys;                              //购买金额
    private BigDecimal        comPercent;                             //完成百分比
    private String            assignUserName;                         //分配者名称
    private String            marketingGradeName;                     //任务目标等级名称

    public TskMarketingBean() {
        super();
    }

    public String getProductTarget() {
        return productTarget;
    }

    public void setProductTarget(String productTarget) {
        this.productTarget = productTarget;
    }

    public BigDecimal getTaskTarget() {
        return taskTarget;
    }

    public void setTaskTarget(BigDecimal taskTarget) {
        this.taskTarget = taskTarget;
    }

    public BigDecimal getBuyMoneys() {
        return buyMoneys;
    }

    public void setBuyMoneys(BigDecimal buyMoneys) {
        this.buyMoneys = buyMoneys;
    }

    public BigDecimal getComPercent() {
        return comPercent;
    }

    public void setComPercent(BigDecimal comPercent) {
        this.comPercent = comPercent;
    }

    public String getAssignUserName() {
        return assignUserName;
    }

    public void setAssignUserName(String assignUserName) {
        this.assignUserName = assignUserName;
    }

    public String getMarketingGradeName() {
        return marketingGradeName;
    }

    public void setMarketingGradeName(String marketingGradeName) {
        this.marketingGradeName = marketingGradeName;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1834940361, 564134657).appendSuper(super.hashCode()).append(
            this.productTarget).append(this.marketingGradeName).append(this.buyMoneys).append(
            this.assignUserName).append(this.comPercent).append(this.taskTarget).toHashCode();
    }

   
}
