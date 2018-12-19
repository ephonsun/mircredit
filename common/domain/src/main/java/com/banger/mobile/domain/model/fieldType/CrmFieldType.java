/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :QianJie
 * Create Date:May 28, 2012
 */
package com.banger.mobile.domain.model.fieldType;

import com.banger.mobile.domain.model.base.field.BaseCrmFieldType;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author QianJie
 * @version $Id: CrmFieldType.java,v 0.1 May 28, 2012 11:11:34 AM QianJie Exp $
 */
public class CrmFieldType extends BaseCrmFieldType{

    private static final long serialVersionUID = -470178723430840082L;

    public CrmFieldType() {
        super();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-185385853, 839484575).appendSuper(super.hashCode())
            .toHashCode();
    }

    
}
