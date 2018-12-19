/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :铃声设置
 * Author     :yujh
 * Create Date:Aug 29, 2012
 */
package com.banger.mobile.domain.model.crmRingSetting;

import com.banger.mobile.domain.model.base.crmRingSetting.BaseCrmRingSetting;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author yujh
 * @version $Id: CrmRingSetting.java,v 0.1 Aug 29, 2012 5:59:31 PM Administrator Exp $
 */
public class CrmRingSetting extends BaseCrmRingSetting{

    private static final long serialVersionUID = -1806155417643410201L;
    public CrmRingSetting(){
        
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-393169665, -874479079).appendSuper(super.hashCode())
            .toHashCode();
    }
    
}
