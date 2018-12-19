/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :金融单位
 * Author     :yujh
 * Create Date:Aug 14, 2012
 */
package com.banger.mobile.domain.model.system;

import com.banger.mobile.domain.model.base.system.BaseMoneyUnit;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author yujh
 * @version $Id: CdMoneyUnit.java,v 0.1 Aug 14, 2012 3:28:04 PM Administrator Exp $
 */
public class CdMoneyUnit extends BaseMoneyUnit{

    private static final long serialVersionUID = 4967038735334217964L;
    
    public CdMoneyUnit(){
        super();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(2125357039, -650940821).appendSuper(super.hashCode())
            .toHashCode();
    }
    
}
