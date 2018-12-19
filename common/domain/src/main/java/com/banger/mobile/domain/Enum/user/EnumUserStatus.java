/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yangy
 * Create Date:2012-8-30
 */
package com.banger.mobile.domain.Enum.user;

/**
 * @author yangyang
 * @version $Id: EnumUserStatus.java,v 0.1 2012-8-30 下午1:18:48 yangyong Exp $
 */
public enum EnumUserStatus {
    ENABLED("enabled","有效"),
    DELETED("deleted","删除"),
    DISABLED("disabled","禁用");
    private String code;
    private String name;

    EnumUserStatus(String code, String name) {
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
