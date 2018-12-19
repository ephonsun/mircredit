/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :Administrator
 * Create Date:Aug 25, 2012
 */
package com.banger.mobile.domain.model.crmVasSetting;

import com.banger.mobile.domain.model.base.vasSetting.BaseCrmVasSetting;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author Administrator
 * @version $Id: CrmVasSetting.java,v 0.1 Aug 25, 2012 2:22:15 PM Administrator Exp $
 */
public class CrmVasSetting extends BaseCrmVasSetting {
    private  String userName;
    private static final long serialVersionUID = -7633033068694110804L;
    public CrmVasSetting(){
        
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1364072121, 1229225651).appendSuper(super.hashCode())
            .toHashCode();
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
}
