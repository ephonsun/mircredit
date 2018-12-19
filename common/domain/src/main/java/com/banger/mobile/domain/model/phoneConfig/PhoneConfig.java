/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :通话基础配置实体类
 * Author     :yujh
 * Create Date:Jun 1, 2012
 */
package com.banger.mobile.domain.model.phoneConfig;

import com.banger.mobile.domain.model.base.phoneConfig.BasePhoneConfig;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author yujh
 * @version $Id: PhoneConfig.java,v 0.1 Jun 1, 2012 9:52:56 AM Administrator Exp $
 */
public class PhoneConfig extends BasePhoneConfig {
    
    private static final long serialVersionUID = -3079641855506563175L;

    public PhoneConfig (){
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1233003205, 440705883).appendSuper(super.hashCode())
            .toHashCode();
    }
    
}
