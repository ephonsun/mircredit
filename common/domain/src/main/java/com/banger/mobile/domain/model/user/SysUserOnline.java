/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yangy
 * Create Date:2012-8-13
 */
package com.banger.mobile.domain.model.user;

import com.banger.mobile.domain.model.base.user.BaseSysUserOnline;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author yangyang
 * @version $Id: SysUserOnline.java,v 0.1 2012-8-13 下午3:14:30 yangyong Exp $
 */
public class SysUserOnline extends BaseSysUserOnline {

    private static final long serialVersionUID = -8178744928430206550L;
    public SysUserOnline() {
        super();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1328314559, -484057241).appendSuper(super.hashCode())
            .toHashCode();
    }
    
}
