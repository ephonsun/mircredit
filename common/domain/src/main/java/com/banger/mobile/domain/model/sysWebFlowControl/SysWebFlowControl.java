/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :web端限流控制
 * Author     :yujh
 * Create Date:Aug 24, 2012
 */
package com.banger.mobile.domain.model.sysWebFlowControl;

import com.banger.mobile.domain.model.base.sysWebFlowControl.BaseSysWebFlowControl;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author yujh
 * @version $Id: SysWebFlowControl.java,v 0.1 Aug 24, 2012 5:29:56 PM Administrator Exp $
 */
public class SysWebFlowControl extends BaseSysWebFlowControl{

    private static final long serialVersionUID = 4007631395918717093L;
    public SysWebFlowControl(){
        
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-783051809, -177408897).appendSuper(super.hashCode())
            .toHashCode();
    }
    
}
