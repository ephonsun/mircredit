/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :联系目的实体类
 * Author     :yujh
 * Create Date:Nov 7, 2012
 */
package com.banger.mobile.domain.model.tskTaskPurpose;

import com.banger.mobile.domain.model.base.tskTaskPurpose.BaseTskTaskPurpose;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author yujh
 * @version $Id: TskTaskPurpose.java,v 0.1 Nov 7, 2012 3:33:21 PM Administrator Exp $
 */
public class TskTaskPurpose extends BaseTskTaskPurpose{

    private static final long serialVersionUID = -7393712279857066762L;
    public TskTaskPurpose(){
        
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(921607761, -50033189).appendSuper(super.hashCode()).toHashCode();
    }
    
}
