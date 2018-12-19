/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yangy
 * Create Date:2012-8-13
 */
package com.banger.mobile.domain.model.user;

import com.banger.mobile.domain.model.base.user.BaseSysDeptAuth;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author yangyang
 * @version $Id: SysDeptAuth.java,v 0.1 2012-8-13 下午3:13:08 yangyong Exp $
 */
public class SysDeptAuth extends BaseSysDeptAuth {

    private static final long serialVersionUID = -5161264912159427139L;
    public SysDeptAuth() {
        super();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1824026201, -1016002757).appendSuper(super.hashCode())
            .toHashCode();
    }

}
