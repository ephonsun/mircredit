/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :RecbizType业务类型实体类
 * Author     :liyb
 * Create Date:2012-5-17
 */
package com.banger.mobile.domain.model.recbistype;

import com.banger.mobile.domain.model.base.recbiztype.BaseRecbizType;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author liyb
 * @version $Id: RecbizType.java,v 0.1 2012-5-17 下午03:51:13 liyb Exp $
 */
public class RecbizType extends BaseRecbizType {

    private static final long serialVersionUID = -6119064985562381731L;
    
    public RecbizType(){}
    
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-730528713, -106810361).appendSuper(super.hashCode())
            .toHashCode();
    }

}
