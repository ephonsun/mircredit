/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :PAD品牌实体Bean
 * Author     :liyb
 * Create Date:2013-6-18
 */
package com.banger.mobile.domain.model.padManagement;

import com.banger.mobile.domain.model.base.padManagement.BasePadBrands;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author liyb
 * @version $Id: PadBrands.java,v 0.1 2013-6-18 上午10:02:57 liyb Exp $
 */
public class PadBrands extends BasePadBrands {

    private static final long serialVersionUID = -2704798199099806934L;

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-186867787, 1136581499).appendSuper(super.hashCode())
            .toHashCode();
    }


}
