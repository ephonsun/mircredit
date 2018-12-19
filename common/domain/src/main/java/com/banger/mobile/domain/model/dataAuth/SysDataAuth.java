/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :数据访问权限实体
 * Author     :cheny
 * Create Date:2012-5-29
 */
package com.banger.mobile.domain.model.dataAuth;

import com.banger.mobile.domain.model.base.dataAuth.BaseSysDataAuth;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author cheny
 * @version $Id: DataAuth.java,v 0.1 2012-5-29 上午10:43:51 cheny Exp $
 */
public class SysDataAuth extends BaseSysDataAuth{

    private static final long serialVersionUID = 125632292225153218L;

    /**
     * 
     */
    public SysDataAuth() {
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-2134822123, 725584673).appendSuper(super.hashCode())
            .toHashCode();
    }
    
    
}
