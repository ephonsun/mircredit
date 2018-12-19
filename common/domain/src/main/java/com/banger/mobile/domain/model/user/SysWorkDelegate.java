/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yangy
 * Create Date:2012-8-13
 */
package com.banger.mobile.domain.model.user;

import com.banger.mobile.domain.model.base.user.BaseSysWorkDelegate;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author yangyang
 * @version $Id: SysWorkDelegate.java,v 0.1 2012-8-13 下午5:31:10 yangyong Exp $
 */
public class SysWorkDelegate extends BaseSysWorkDelegate {

    private static final long serialVersionUID = 4679156207355338328L;

    public SysWorkDelegate() {
        super();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(737648787, -524191453).appendSuper(super.hashCode())
            .toHashCode();
    }
    
}
