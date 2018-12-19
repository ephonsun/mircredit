/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yangy
 * Create Date:2012-8-13
 */
package com.banger.mobile.domain.model.user;

import com.banger.mobile.domain.model.base.user.BaseSysUserOnlineMax;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author yangyang
 * @version $Id: SysUserOnlineMax.java,v 0.1 2012-8-13 下午5:30:14 yangyong Exp $
 */
public class SysUserOnlineMax extends BaseSysUserOnlineMax {

    private static final long serialVersionUID = -576850127936312876L;

    public SysUserOnlineMax() {
        super();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(2035788367, 259516143).appendSuper(super.hashCode())
            .toHashCode();
    }
    
}
