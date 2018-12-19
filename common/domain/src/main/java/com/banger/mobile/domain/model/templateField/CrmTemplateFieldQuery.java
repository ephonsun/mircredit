/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :QianJie
 * Create Date:Jun 4, 2012
 */
package com.banger.mobile.domain.model.templateField;

/**
 * @author QianJie
 * @version $Id: CrmTemplateFieldQuery.java,v 0.1 Jun 4, 2012 1:35:03 PM QianJie Exp $
 */
public class CrmTemplateFieldQuery extends CrmTemplateField{

    private static final long serialVersionUID = 5584246551892833725L;
    private String fieldTypeName;    //模版字段数据类型名称
    public String getFieldTypeName() {
        return fieldTypeName;
    }

    public void setFieldTypeName(String fieldTypeName) {
        this.fieldTypeName = fieldTypeName;
    }
    
    public CrmTemplateFieldQuery() {
        super();
    }
}
