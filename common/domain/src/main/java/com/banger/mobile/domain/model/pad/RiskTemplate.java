/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-7-17
 */
package com.banger.mobile.domain.model.pad;

/**
 * @author yuanme
 * @version RiskTemplate.java,v 0.1 2012-7-17 下午5:20:25
 */
public class RiskTemplate {
    private Integer templateId;
    private String templateNo;
    private String templateName;
    private String  templateTypeName;
    
    public Integer getTemplateId() {
        return templateId;
    }
    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }
    public String getTemplateNo() {
        return templateNo;
    }
    public void setTemplateNo(String templateNo) {
        this.templateNo = templateNo;
    }
    public String getTemplateName() {
        return templateName;
    }
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
    public String getTemplateTypeName() {
        return templateTypeName;
    }
    public void setTemplateTypeName(String templateTypeName) {
        this.templateTypeName = templateTypeName;
    }
}
