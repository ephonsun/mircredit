/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :贷款类型
 * Author     :zhangfp
 * Create Date:2013-3-14
 */
package com.banger.mobile.domain.model.pad;

import java.io.Serializable;

/**
 * @author Administrator
 * @version $Id: PadLoanType.java v 0.1 ${} 下午5:11 Administrator Exp $
 */
public class PadLoanType implements Serializable {
    private static final long serialVersionUID = -1600261088363093202L;

    private Integer loanTypeId;
    private String loanTypeName;

    public Integer getLoanTypeId() {
        return loanTypeId;
    }

    public void setLoanTypeId(Integer loanTypeId) {
        this.loanTypeId = loanTypeId;
    }

    public String getLoanTypeName() {
        return loanTypeName;
    }

    public void setLoanTypeName(String loanTypeName) {
        this.loanTypeName = loanTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PadLoanType that = (PadLoanType) o;

        if (loanTypeId != null ? !loanTypeId.equals(that.loanTypeId) : that.loanTypeId != null) return false;
        if (loanTypeName != null ? !loanTypeName.equals(that.loanTypeName) : that.loanTypeName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = loanTypeId != null ? loanTypeId.hashCode() : 0;
        result = 31 * result + (loanTypeName != null ? loanTypeName.hashCode() : 0);
        return result;
    }
}
