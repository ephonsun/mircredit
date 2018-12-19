/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :证件类型
 * Author     :yujh
 * Create Date:Jul 17, 2012
 */
package com.banger.mobile.domain.model.base.cardType;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author yujh
 * @version $Id: BaseCardType.java,v 0.1 Jul 17, 2012 4:22:22 PM Administrator Exp $
 */
public class BaseCardType extends BaseObject{

    private static final long serialVersionUID = 2228741565444954704L;
    
    private         Integer         idTypeId;
    private         String          idTypeName;
    public Integer getIdTypeId() {
        return idTypeId;
    }
    public void setIdTypeId(Integer idTypeId) {
        this.idTypeId = idTypeId;
    }
    public String getIdTypeName() {
        return idTypeName;
    }
    public void setIdTypeName(String idTypeName) {
        this.idTypeName = idTypeName;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseCardType)) {
            return false;
        }
        BaseCardType rhs = (BaseCardType) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.idTypeName,
            rhs.idTypeName).append(this.idTypeId, rhs.idTypeId).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-122580831, 1296370945).appendSuper(super.hashCode()).append(
            this.idTypeName).append(this.idTypeId).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("idTypeId", this.idTypeId).append("startRow",
            this.getStartRow()).append("idTypeName", this.idTypeName).append("endRow",
            this.getEndRow()).toString();
    }
    
}
