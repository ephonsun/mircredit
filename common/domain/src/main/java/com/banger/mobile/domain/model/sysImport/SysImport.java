/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :导入记录表...
 * Author     :yangy
 * Create Date:2012-8-27
 */
package com.banger.mobile.domain.model.sysImport;

import com.banger.mobile.domain.model.base.sysImport.BaseSysImport;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author yangyang
 * @version $Id: SysImport.java,v 0.1 2012-8-27 上午11:06:08 yangyong Exp $
 */
public class SysImport extends BaseSysImport {

    private static final long serialVersionUID = 4003900458473919063L;

    public SysImport() {
        super();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(191703219, 1829698289).appendSuper(super.hashCode())
            .toHashCode();
    }
    
}
