/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :QianJie
 * Create Date:May 28, 2012
 */
package com.banger.mobile.domain.model.fieldMatch;

import com.banger.mobile.domain.model.base.fieldMatch.BaseCrmFieldMatch;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author QianJie
 * @version $Id: CrmFieldMatch.java,v 0.1 May 28, 2012 4:31:56 PM QianJie Exp $
 */
public class CrmFieldMatch extends BaseCrmFieldMatch{

    private static final long serialVersionUID = -8177836479864964978L;

    public CrmFieldMatch() {
        super();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(770418269, -1130866253).appendSuper(super.hashCode())
            .toHashCode();
    }

}
