/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品-Domain
 * Author     :QianJie
 * Create Date:Nov 12, 2012
 */
package com.banger.mobile.domain.model.base.microProduct;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author QianJie
 * @version $Id: BasePdtProduct.java,v 0.1 Nov 12, 2012 3:39:13 PM QianJie Exp $
 */
public class BasePdtProduct extends BaseObject implements Serializable {

    private static final long serialVersionUID = 889649701471741186L;

    private Integer           productId;                             //主键
    private String            productName;                           //产品名称
    private String            productCode;                           //产品编号
    private String            productCoverImage;                     //产品logo
    private String            remark;                                //简要说明
    private String            feature;                               //产品特点
    private String            suitableUser;                          //适用对象
    private String            applyNeedData;                         //申请资料
    private Integer           isDel;                                 //是否删除
    private Date              createDate;                            //创建时间
    private Date              updateDate;                            //更新时间
    private Integer           createUser;                            //创建用户
    private Integer           updateUser;                            //更新用户
    
    
    public BasePdtProduct() {
        super();
    }

    public BasePdtProduct(Integer productId, String productName, String productCode,
                          String productCoverImage, String remark, String feature,
                          String suitableUser, String applyNeedData, Integer isDel,
                          Date createDate, Date updateDate, Integer createUser, Integer updateUser) {
        super();
        this.productId = productId;
        this.productName = productName;
        this.productCode = productCode;
        this.productCoverImage = productCoverImage;
        this.remark = remark;
        this.feature = feature;
        this.suitableUser = suitableUser;
        this.applyNeedData = applyNeedData;
        this.isDel = isDel;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }



    public Integer getProductId() {
        return productId;
    }


    public void setProductId(Integer productId) {
        this.productId = productId;
    }


    public String getProductName() {
        return productName;
    }


    public void setProductName(String productName) {
        this.productName = productName;
    }


    public String getProductCode() {
        return productCode;
    }


    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }


    public String getProductCoverImage() {
        return productCoverImage;
    }


    public void setProductCoverImage(String productCoverImage) {
        this.productCoverImage = productCoverImage;
    }


    public String getRemark() {
        return remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getFeature() {
        return feature;
    }


    public void setFeature(String feature) {
        this.feature = feature;
    }


    public String getSuitableUser() {
        return suitableUser;
    }


    public void setSuitableUser(String suitableUser) {
        this.suitableUser = suitableUser;
    }


    public String getApplyNeedData() {
        return applyNeedData;
    }


    public void setApplyNeedData(String applyNeedData) {
        this.applyNeedData = applyNeedData;
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
        if (!(object instanceof BasePdtProduct)) {
            return false;
        }
        BasePdtProduct rhs = (BasePdtProduct) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.productCode,
            rhs.productCode).append(this.productId, rhs.productId).append(this.productName,
            rhs.productName).append(this.remark, rhs.remark).append(this.suitableUser,
            rhs.suitableUser).append(this.productCoverImage, rhs.productCoverImage).append(
            this.updateDate, rhs.updateDate).append(this.applyNeedData, rhs.applyNeedData).append(
            this.isDel, rhs.isDel).append(this.createDate, rhs.createDate).append(this.feature,
            rhs.feature).append(this.createUser, rhs.createUser).append(this.updateUser,
            rhs.updateUser).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1439835137, 1184168479).appendSuper(super.hashCode()).append(
            this.productCode).append(this.productId).append(this.productName).append(this.remark)
            .append(this.suitableUser).append(this.productCoverImage).append(this.updateDate)
            .append(this.applyNeedData).append(this.isDel).append(this.createDate).append(
                this.feature).append(this.createUser).append(this.updateUser).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("applyNeedData", this.applyNeedData).append(
            "startRow", this.getStartRow()).append("productName", this.productName).append(
            "updateDate", this.updateDate).append("productCoverImage", this.productCoverImage)
            .append("endRow", this.getEndRow()).append("updateUser", this.updateUser).append(
                "createUser", this.createUser).append("suitableUser", this.suitableUser).append(
                "productId", this.productId).append("feature", this.feature).append("isDel",
                this.isDel).append("productCode", this.productCode).append("remark", this.remark)
            .append("createDate", this.createDate).toString();
    } 
    
}
