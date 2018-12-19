/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :PAD型号实体Bean
 * Author     :liyb
 * Create Date:2013-6-18
 */
package com.banger.mobile.domain.model.padManagement;

import com.banger.mobile.domain.model.base.padManagement.BasePadModel;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author liyb
 * @version $Id: PadModel.java,v 0.1 2013-6-18 上午10:03:53 liyb Exp $
 */
public class PadModel extends BasePadModel {

    private static final long serialVersionUID = 8003934704184698584L;

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-817562247, 1020516243).appendSuper(super.hashCode())
            .toHashCode();
    }

}
