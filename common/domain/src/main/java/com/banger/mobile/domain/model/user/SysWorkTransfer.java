/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yangy
 * Create Date:2012-8-13
 */
package com.banger.mobile.domain.model.user;

import com.banger.mobile.domain.model.base.user.BaseSysWorkTransfer;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author yangyang
 * @version $Id: SysWorkTransfer.java,v 0.1 2012-8-13 下午5:31:49 yangyong Exp $
 */
public class SysWorkTransfer extends BaseSysWorkTransfer {

    private static final long serialVersionUID = -1518172811179165042L;
    public SysWorkTransfer() {
        super();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1511383183, -1037539921).appendSuper(super.hashCode())
            .toHashCode();
    }
    
}
