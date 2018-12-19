/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :快速规划实体类
 * Author     :yujh
 * Create Date:Jul 16, 2012
 */
package com.banger.mobile.domain.model.plnFastPlan;

import com.banger.mobile.domain.model.base.plnFastPlan.BasePlnFastPlan;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author yujh
 * @version $Id: PlnFastPlan.java,v 0.1 Jul 16, 2012 5:42:22 PM Administrator Exp $
 */
public class PlnFastPlan extends BasePlnFastPlan{
    private static final long serialVersionUID = 6684919001958476361L;

    public PlnFastPlan(){
        
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(616987433, 412697151).appendSuper(super.hashCode()).toHashCode();
    }
    
}
