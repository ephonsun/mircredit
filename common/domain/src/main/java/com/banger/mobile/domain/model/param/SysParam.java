/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :全局参数实体类
 * Author     :yujh
 * Create Date:May 25, 2012
 */
package com.banger.mobile.domain.model.param;

import com.banger.mobile.domain.model.base.param.BaseSysParam;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author yujh
 * @version $Id: SysParam.java,v 0.1 May 25, 2012 10:19:02 AM Administrator Exp $
 */
public class SysParam extends BaseSysParam {

    private static final long serialVersionUID = 3336531933382820373L;
    
    public SysParam(){
        
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(777010323, 1103407817).appendSuper(super.hashCode())
            .toHashCode();
    }
    
}
