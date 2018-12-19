/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-7-20
 */
package com.banger.mobile.domain.model.pad;

import java.io.Serializable;

/**
 * @author yuanme
 * @version Product.java,v 0.1 2012-7-20 上午11:32:49
 */
public class MicroProduct implements Serializable {
    private static final long serialVersionUID = -6978511862291378175L;

    private String            pic;
    private Integer           productId;
    private String            productName;
    private String            productCode;
    private String            productExplain;
    private String            productFeature;
    private String            suitableUser;
    private String            applyData;

    public String getProductExplain() {
        return productExplain;
    }

    public void setProductExplain(String productExplain) {
        this.productExplain = productExplain;
    }

    public String getProductFeature() {
        return productFeature;
    }

    public void setProductFeature(String productFeature) {
        this.productFeature = productFeature;
    }

    public String getSuitableUser() {
        return suitableUser;
    }

    public void setSuitableUser(String suitableUser) {
        this.suitableUser = suitableUser;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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

    public String getApplyData() {
        return applyData;
    }

    public void setApplyData(String applyData) {
        this.applyData = applyData;
    }

}
