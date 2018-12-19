/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务附件-Domain-扩展1
 * Author     :QianJie
 * Create Date:Mar 2, 2013
 */
package com.banger.mobile.domain.model.microTask;

import com.banger.mobile.domain.model.base.microTask.BaseTskMicroTaskAttachment;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author QianJie
 * @version $Id: TskMicroTaskAttachment.java,v 0.1 Mar 2, 2013 10:51:35 AM QianJie Exp $
 */
public class TskMicroTaskAttachment extends BaseTskMicroTaskAttachment{

    private static final long serialVersionUID = -8055560223448691688L;

    public TskMicroTaskAttachment() {
        super();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1449199281, -353226869).appendSuper(super.hashCode())
            .toHashCode();
    }

    
}
