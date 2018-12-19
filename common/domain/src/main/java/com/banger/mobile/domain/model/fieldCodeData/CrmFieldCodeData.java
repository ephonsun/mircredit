/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :QianJie
 * Create Date:May 28, 2012
 */
package com.banger.mobile.domain.model.fieldCodeData;

import com.banger.mobile.domain.model.base.fieldCodeData.BaseCrmFieldCodeData;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author QianJie
 * @version $Id: CrmFieldCodeData.java,v 0.1 May 28, 2012 2:55:27 PM QianJie Exp $
 */
public class CrmFieldCodeData extends BaseCrmFieldCodeData{

    private static final long serialVersionUID = 3391632258592185433L;

    public CrmFieldCodeData() {
        super();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1250271027, -494077263).appendSuper(super.hashCode())
            .toHashCode();
    }
    

}
