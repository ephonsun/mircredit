/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :通知-Domain-扩展1
 * Author     :QianJie
 * Create Date:Dec 11, 2012
 */
package com.banger.mobile.domain.model.microProduct;

import com.banger.mobile.domain.model.base.microProduct.BasePdtNotice;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author QianJie
 * @version $Id: PdtNotice.java,v 0.1 Dec 11, 2012 1:40:34 PM QianJie Exp $
 */
public class PdtNotice extends BasePdtNotice {

    private static final long serialVersionUID = -2426784574351371649L;
    private Integer           isRead;                                  //是否已读

    public PdtNotice() {
        super();
    }

    public Integer getIsRead() {
        return (this.isRead == null) ? 0 : isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1435128425, -843938725).appendSuper(super.hashCode()).append(
            this.isRead).toHashCode();
    }

}
