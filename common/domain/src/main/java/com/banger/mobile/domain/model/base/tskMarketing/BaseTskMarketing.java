/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :营销任务主表-Domain
 * Author     :QianJie
 * Create Date:Dec 27, 2012
 */
package com.banger.mobile.domain.model.base.tskMarketing;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author QianJie
 * @version $Id: BaseTskMarketing.java,v 0.1 Dec 27, 2012 11:08:16 AM QianJie Exp $
 */
public class BaseTskMarketing extends BaseObject {

    private static final long serialVersionUID = -3758439353043802989L;
    private Integer           marketingId;                             //主键
    private Integer           gradeId;                                 //等级编码
    private String            marketingTitle;                          //任务标题
    private Date              startDate;                               //开始时间
    private Date              endDate;                                 //结束时间
    private String            remark;                                  //任务备注
    private Integer           templateId;                              //产品大类
    private String            subTemplateName;                         //产品子类名称-冗余
    private Integer           productId;                               //产品ID
    private Integer           assignUserId;                            //分配者
    private Integer           isSuspend;                               //是否中止 0:正常   1:已中止
    private Integer           isDel;                                   //是否删除 1:已删除  0:未删除
    private Date              createDate;                              //创建时间
    private Date              updateDate;                              //更新时间
    private Integer           createUser;                              //创建用户
    private Integer           updateUser;                              //更新用户
    
    public BaseTskMarketing() {
        super();
    }

    public BaseTskMarketing(Integer marketingId, Integer gradeId, String marketingTitle,
                            Date startDate, Date endDate, String remark, Integer templateId,
                            String subTemplateName, Integer productId, Integer assignUserId,
                            Integer isSuspend, Integer isDel, Date createDate, Date updateDate,
                            Integer createUser, Integer updateUser) {
        super();
        this.marketingId = marketingId;
        this.gradeId = gradeId;
        this.marketingTitle = marketingTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.remark = remark;
        this.templateId = templateId;
        this.subTemplateName = subTemplateName;
        this.productId = productId;
        this.assignUserId = assignUserId;
        this.isSuspend = isSuspend;
        this.isDel = isDel;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }

    public Integer getMarketingId() {
        return marketingId;
    }

    public void setMarketingId(Integer marketingId) {
        this.marketingId = marketingId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getMarketingTitle() {
        return marketingTitle;
    }

    public void setMarketingTitle(String marketingTitle) {
        this.marketingTitle = marketingTitle;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getSubTemplateName() {
        return subTemplateName;
    }

    public void setSubTemplateName(String subTemplateName) {
        this.subTemplateName = subTemplateName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getAssignUserId() {
        return assignUserId;
    }

    public void setAssignUserId(Integer assignUserId) {
        this.assignUserId = assignUserId;
    }

    public Integer getIsSuspend() {
        return isSuspend;
    }

    public void setIsSuspend(Integer isSuspend) {
        this.isSuspend = isSuspend;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseTskMarketing)) {
            return false;
        }
        BaseTskMarketing rhs = (BaseTskMarketing) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.marketingId,
            rhs.marketingId).append(this.gradeId, rhs.gradeId)
            .append(this.startDate, rhs.startDate).append(this.productId, rhs.productId).append(
                this.remark, rhs.remark).append(this.updateDate, rhs.updateDate).append(
                this.templateId, rhs.templateId).append(this.assignUserId, rhs.assignUserId)
            .append(this.subTemplateName, rhs.subTemplateName)
            .append(this.isSuspend, rhs.isSuspend).append(this.isDel, rhs.isDel).append(
                this.marketingTitle, rhs.marketingTitle).append(this.endDate, rhs.endDate).append(
                this.createDate, rhs.createDate).append(this.createUser, rhs.createUser).append(
                this.updateUser, rhs.updateUser).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(865530141, -641231939).appendSuper(super.hashCode()).append(
            this.marketingId).append(this.gradeId).append(this.startDate).append(this.productId)
            .append(this.remark).append(this.updateDate).append(this.templateId).append(
                this.assignUserId).append(this.subTemplateName).append(this.isSuspend).append(
                this.isDel).append(this.marketingTitle).append(this.endDate)
            .append(this.createDate).append(this.createUser).append(this.updateUser).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("startRow", this.getStartRow()).append(
            "updateDate", this.updateDate).append("endRow", this.getEndRow()).append(
            "marketingTitle", this.marketingTitle).append("updateUser", this.updateUser).append(
            "createUser", this.createUser).append("isSuspend", this.isSuspend).append("endDate",
            this.endDate).append("templateId", this.templateId).append("assignUserId",
            this.assignUserId).append("subTemplateName", this.subTemplateName).append("productId",
            this.productId).append("startDate", this.startDate).append("isDel", this.isDel).append(
            "marketingId", this.marketingId).append("gradeId", this.gradeId).append("createDate",
            this.createDate).append("remark", this.remark).toString();
    }
}
