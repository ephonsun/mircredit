/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :营销任务明细表-Domain-扩展1
 * Author     :QianJie
 * Create Date:Dec 27, 2012
 */
package com.banger.mobile.domain.model.tskMarketing;

import com.banger.mobile.domain.model.base.tskMarketing.BaseTskMarketingExecute;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author QianJie
 * @version $Id: TskMarketingExecute.java,v 0.1 Dec 27, 2012 11:35:54 AM QianJie Exp $
 */
public class TskMarketingExecute extends BaseTskMarketingExecute {

    private static final long serialVersionUID = 1048895784351552799L;

    public TskMarketingExecute() {
        super();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(239106903, -573749561).appendSuper(super.hashCode())
            .toHashCode();
    }

    
}
