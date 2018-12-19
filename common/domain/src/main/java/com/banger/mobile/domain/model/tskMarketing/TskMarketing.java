/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :营销任务主表-Domain-扩展1
 * Author     :QianJie
 * Create Date:Dec 27, 2012
 */
package com.banger.mobile.domain.model.tskMarketing;

import com.banger.mobile.domain.model.base.tskMarketing.BaseTskMarketing;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author QianJie
 * @version $Id: TskMarketing.java,v 0.1 Dec 27, 2012 11:25:19 AM QianJie Exp $
 */
public class TskMarketing extends BaseTskMarketing {

    private static final long serialVersionUID = -1559787034007888166L;

    public TskMarketing() {
        super();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-757958047, -11309395).appendSuper(super.hashCode())
            .toHashCode();
    }

    
}
