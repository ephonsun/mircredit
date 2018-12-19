/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品销售明细统计表Bean
 * Author     :liyb
 * Create Date:2013-1-4
 */
package com.banger.mobile.domain.model.report;

import java.io.Serializable;

/**
 * @author liyb
 * @version $Id: ProductReportBean.java,v 0.1 2013-1-4 下午02:36:57 liyb Exp $
 */
public class ProductReportBean implements Serializable {

    private static final long serialVersionUID = 8205104743414713031L;

    private Integer productId;
    private String productCode;
    private String productName;
    private Integer templateId;
    private String templateName;
    private Integer templateFieldId;
    private String fieldCodeDataValue;
    private Integer userId;
    private String userName;
    private Double buyMoney;
    private Integer deptId;
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public String getProductCode() {
        return productCode;
    }
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Integer getTemplateId() {
        return templateId;
    }
    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }
    public String getTemplateName() {
        return templateName;
    }
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
    public Integer getTemplateFieldId() {
        return templateFieldId;
    }
    public void setTemplateFieldId(Integer templateFieldId) {
        this.templateFieldId = templateFieldId;
    }
    public String getFieldCodeDataValue() {
        return fieldCodeDataValue;
    }
    public void setFieldCodeDataValue(String fieldCodeDataValue) {
        this.fieldCodeDataValue = fieldCodeDataValue;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Double getBuyMoney() {
        return buyMoney;
    }
    public void setBuyMoney(Double buyMoney) {
        this.buyMoney = buyMoney;
    }
    public Integer getDeptId() {
        return deptId;
    }
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
}
