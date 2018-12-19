/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :柜台人员
 * Author     :yujh
 * Create Date:Sep 4, 2012
 */
package com.banger.mobile.domain.model.crmCounterUser;

import com.banger.mobile.domain.model.base.crmCounterUser.BaseCrmCounterUser;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author yujh
 * @version $Id: CrmCounterUser.java,v 0.1 Sep 4, 2012 11:37:37 AM Administrator Exp $
 */
public class CrmCounterUser extends BaseCrmCounterUser{

    private static final long serialVersionUID = -6162092102345192092L;
    public CrmCounterUser (){
        
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1993083299, 2095645471).appendSuper(super.hashCode())
            .toHashCode();
    }
    
}
