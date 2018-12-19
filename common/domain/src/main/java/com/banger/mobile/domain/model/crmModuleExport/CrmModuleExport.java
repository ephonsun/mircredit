/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-9-10
 */
package com.banger.mobile.domain.model.crmModuleExport;

import com.banger.mobile.domain.model.base.crmModuleExport.BaseCrmModuleExport;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author cheny
 * @version $Id: CrmModuleExport.java,v 0.1 2012-9-10 上午10:56:47 cheny Exp $
 */
public class CrmModuleExport extends BaseCrmModuleExport{

    private static final long serialVersionUID = -6736880427076639607L;

    /**
     * 
     */
    public CrmModuleExport() {
        super();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(2040084409, -857529319).appendSuper(super.hashCode())
            .toHashCode();
    }
    
    

}
