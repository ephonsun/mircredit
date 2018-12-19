/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-5-17
 */
package com.banger.mobile.domain.model.dept;

import com.banger.mobile.domain.model.base.dept.BaseSysDept;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author cheny
 * @version $Id: SysDept.java,v 0.1 2012-5-17 上午11:55:10 cheny Exp $
 */
public class SysDept extends BaseSysDept{

    private static final long serialVersionUID = 5586250618778522980L;

    /**
     * 
     */
    public SysDept() {
        
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1372279103, 71377745).appendSuper(super.hashCode())
            .toHashCode();
    }
    

}
