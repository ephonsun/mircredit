/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品类型营销额
 * Author     :liyb
 * Create Date:2013-1-7
 */
package com.banger.mobile.domain.model.report;

import java.io.Serializable;

/**
 * @author liyb
 * @version $Id: ProductTypeTotalBean.java,v 0.1 2013-1-7 上午10:41:13 liyb Exp $
 */
public class ProductTypeTotalBean implements Serializable {

    private static final long serialVersionUID = 5673441410115427599L;

    private Integer           userId;                                  //用户ID
    private Integer           templateId;                              //类型ID
    private String            templateName;                            //类型名称
    private Double            buyMoney;                                //销售额
    private Integer           deptId;                                  //部门ID
    
    private Integer           fartherId;
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
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
    public Integer getFartherId() {
        return fartherId;
    }
    public void setFartherId(Integer fartherId) {
        this.fartherId = fartherId;
    }
}
