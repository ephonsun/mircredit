/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-5-31
 */
package com.banger.mobile.domain.model.menuAuth;

import com.banger.mobile.domain.model.base.menuAuth.BaseSysMenuAuth;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author cheny
 * @version $Id: SysMenuAuth.java,v 0.1 2012-5-31 上午11:44:18 cheny Exp $
 */
public class SysMenuAuth extends BaseSysMenuAuth{

    private static final long serialVersionUID = 6436759916445460964L;

    public SysMenuAuth(){
        
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1116075071, -63450193).appendSuper(super.hashCode())
            .toHashCode();
    }
    
    
}
