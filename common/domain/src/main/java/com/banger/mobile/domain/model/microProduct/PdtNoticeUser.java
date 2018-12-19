/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :通知-用户-Domain-扩展1
 * Author     :QianJie
 * Create Date:Dec 18, 2012
 */
package com.banger.mobile.domain.model.microProduct;

import com.banger.mobile.domain.model.base.microProduct.BasePdtNoticeUser;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author QianJie
 * @version $Id: PdtNoticeUser.java,v 0.1 Dec 18, 2012 3:28:32 PM QianJie Exp $
 */
public class PdtNoticeUser extends BasePdtNoticeUser{

    private static final long serialVersionUID = -828437132161504388L;

    public PdtNoticeUser() {
        super();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(683093637, -777601893).appendSuper(super.hashCode())
            .toHashCode();
    }

}
