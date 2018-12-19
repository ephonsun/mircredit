/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yangy
 * Create Date:2012-5-21
 */
package com.banger.mobile.domain.model.user;

import com.banger.mobile.domain.model.base.user.BaseSysRoleMember;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author Administrator
 * @version $Id: SysRoleMember.java,v 0.1 2012-5-21 上午11:28:19 Administrator Exp $
 */
public class SysRoleMember extends BaseSysRoleMember {

    private static final long serialVersionUID = 7234332803657035623L;

    public SysRoleMember() {
        super();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-228093519, -550632005).appendSuper(super.hashCode())
            .toHashCode();
    }

}
