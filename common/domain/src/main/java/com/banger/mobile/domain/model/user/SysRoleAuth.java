/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yangy
 * Create Date:2012-8-13
 */
package com.banger.mobile.domain.model.user;

import com.banger.mobile.domain.model.base.user.BaseSysRoleAuth;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author yangyang
 * @version $Id: SysRoleAuth.java,v 0.1 2012-8-13 下午3:17:17 yangyong Exp $
 */
public class SysRoleAuth extends BaseSysRoleAuth {

    private static final long serialVersionUID = 5548973950020310582L;
    public SysRoleAuth() {
        super();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-943892487, -1708332699).appendSuper(super.hashCode())
            .toHashCode();
    }
    
}
