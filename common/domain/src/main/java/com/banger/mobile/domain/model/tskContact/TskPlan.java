/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-12-15
 */
package com.banger.mobile.domain.model.tskContact;

import com.banger.mobile.domain.model.base.tskContact.BaseTskPlan;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author cheny
 * @version $Id: TskPlan.java,v 0.1 2012-12-15 下午2:24:34 cheny Exp $
 */
public class TskPlan extends BaseTskPlan{

    private static final long serialVersionUID = 1231656032965827215L;

    /**
     * 
     */
    public TskPlan() {
        super();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1024288461, 649409249).appendSuper(super.hashCode())
            .toHashCode();
    }
    
    
    

}
