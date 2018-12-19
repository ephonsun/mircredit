/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :Administrator
 * Create Date:Aug 29, 2012
 */
package com.banger.mobile.domain.model.crmRecordRemind;

import com.banger.mobile.domain.model.base.crmRecordRemind.BaseCrmRecordRemind;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author Administrator
 * @version $Id: CrmRecordRemind.java,v 0.1 Aug 29, 2012 5:59:18 PM Administrator Exp $
 */
public class CrmRecordRemind extends BaseCrmRecordRemind {

    private static final long serialVersionUID = 5813173125295267767L;
    public CrmRecordRemind(){
        
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1917785619, 1843591837).appendSuper(super.hashCode())
            .toHashCode();
    }
    
}
