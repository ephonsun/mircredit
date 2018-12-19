/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :营销任务附件-Domain-扩展1
 * Author     :QianJie
 * Create Date:Dec 26, 2012
 */
package com.banger.mobile.domain.model.tskMarketing;

import com.banger.mobile.domain.model.base.tskMarketing.BaseTskMarketingAttachment;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author QianJie
 * @version $Id: TskMarketingAttachment.java,v 0.1 Dec 26, 2012 3:49:10 PM QianJie Exp $
 */
public class TskMarketingAttachment extends BaseTskMarketingAttachment {

    private static final long serialVersionUID = -7437658896253839344L;

    public TskMarketingAttachment() {
        super();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1861035981, -2084331349).appendSuper(super.hashCode())
            .toHashCode();
    }

    
}
