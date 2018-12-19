/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-5-31
 */
package com.banger.mobile.domain.model.funcAuth;

import com.banger.mobile.domain.model.base.func.BaseSysFunc;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author cheny
 * @version $Id: SysFunc.java,v 0.1 2012-5-31 上午11:36:40 cheny Exp $
 */
public class SysFunc extends BaseSysFunc{

    private static final long serialVersionUID = -6256929395733747545L;

    /**
     * 
     */
   public SysFunc(){
       
   }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(394144413, -851511625).appendSuper(super.hashCode())
            .toHashCode();
    }
    
    
}
