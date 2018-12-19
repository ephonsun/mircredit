/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-12-15
 */
package com.banger.mobile.domain.model.tskContact;

import com.banger.mobile.domain.model.base.tskContact.BaseTskPlanTarget;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author cheny
 * @version $Id: TskPlanTarget.java,v 0.1 2012-12-15 下午2:26:16 cheny Exp $
 */
public class TskPlanTarget extends BaseTskPlanTarget{

    private static final long serialVersionUID = -3640123284955868698L;

    /**
     * 
     */
    public TskPlanTarget() {
        super();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(896199579, -673396191).appendSuper(super.hashCode())
            .toHashCode();
    }

    
    
}
