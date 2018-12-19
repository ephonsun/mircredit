/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :QianJie
 * Create Date:Jun 8, 2012
 */
package com.banger.mobile.domain.Enum.template;

/**
 * @author QianJie
 * @version $Id: EnumTemplate.java,v 0.1 Jun 8, 2012 11:42:02 AM QianJie Exp $
 */
public enum EnumTemplate {
    AddAndUpdateValidation("addandupdate",""),
    ACTIVE_TEMP_ERROR("active_temp_error","该产品类型正被使用，不能停用！"),
    DEL_TEMP_ERROR("del_temp_error","该产品类型已被使用，不能删除！"),
    SAME_TEMP_NAME("same_temp_name","已存在相同名称的产品类型！");
    
    private String code;
    private String name;
    EnumTemplate(String code, String name) {
        this.code = code;
        this.name = name;
    }
    public String getKey() {
        return this.code;
    }
    public String getValue() {
        return this.name;
    }
}
