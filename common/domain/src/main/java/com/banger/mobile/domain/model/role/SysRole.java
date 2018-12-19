/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :SysRole角色实体类
 * Author     :liyb
 * Create Date:2012-5-22
 */
package com.banger.mobile.domain.model.role;

import com.banger.mobile.domain.model.base.role.BaseSysRole;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author liyb
 * @version $Id: SysRole.java,v 0.1 2012-5-22 下午01:19:28 liyb Exp $
 */
public class SysRole extends BaseSysRole {

    private static final long serialVersionUID = -2589320545243084166L;
    public SysRole(){}
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1066211383, 1930461547).appendSuper(super.hashCode())
            .toHashCode();
    }
}
