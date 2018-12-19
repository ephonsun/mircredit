/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :风险测评模板类型
 * Author     :yujh
 * Create Date:Jul 16, 2012
 */
package com.banger.mobile.domain.model.rskTempType;

import com.banger.mobile.domain.model.base.RskTempType.BaseRskTempType;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author yujh
 * @version $Id: RskTempType.java,v 0.1 Jul 16, 2012 9:52:01 AM Administrator Exp $
 */
public class RskTempType extends BaseRskTempType {

    private static final long serialVersionUID = -8676958194570368089L;
    
    public RskTempType(){
        
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-835422705, 927325461).appendSuper(super.hashCode())
            .toHashCode();
    }
    
}
