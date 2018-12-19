/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :贷款子类型
 * Author     :zhangfp
 * Create Date:2013-3-14
 */
package com.banger.mobile.domain.model.pad;

import java.io.Serializable;

/**
 * @author Administrator
 * @version $Id: PadLoanSubType.java v 0.1 ${} 下午5:11 Administrator Exp $
 */
public class PadLoanSubType implements Serializable {
    private static final long serialVersionUID = -2492678581044326151L;

    private Integer loanSubTypeId;
    private Integer loanTypeId;
    private String loanSubTypeName;

    public Integer getLoanSubTypeId() {
        return loanSubTypeId;
    }

    public void setLoanSubTypeId(Integer loanSubTypeId) {
        this.loanSubTypeId = loanSubTypeId;
    }

    public String getLoanSubTypeName() {
        return loanSubTypeName;
    }

    public void setLoanSubTypeName(String loanSubTypeName) {
        this.loanSubTypeName = loanSubTypeName;
    }

    public Integer getLoanTypeId() {
        return loanTypeId;
    }

    public void setLoanTypeId(Integer loanTypeId) {
        this.loanTypeId = loanTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PadLoanSubType that = (PadLoanSubType) o;

        if (loanSubTypeId != null ? !loanSubTypeId.equals(that.loanSubTypeId) : that.loanSubTypeId != null)
            return false;
        if (loanSubTypeName != null ? !loanSubTypeName.equals(that.loanSubTypeName) : that.loanSubTypeName != null)
            return false;
        if (loanTypeId != null ? !loanTypeId.equals(that.loanTypeId) : that.loanTypeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = loanSubTypeId != null ? loanSubTypeId.hashCode() : 0;
        result = 31 * result + (loanTypeId != null ? loanTypeId.hashCode() : 0);
        result = 31 * result + (loanSubTypeName != null ? loanSubTypeName.hashCode() : 0);
        return result;
    }
}
