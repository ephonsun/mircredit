/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-5-17
 */
package com.banger.mobile.domain.model.dept;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author cheny
 * @version $Id: DeptUserBean.java,v 0.1 2012-5-17 下午1:12:35 cheny Exp $
 */
public class UserSubordinateBean extends DeptUserBean implements Serializable {

    
    private static final long serialVersionUID = 5611887112085911518L;
    private Integer isCancel; //是否撤销
    private Integer isOK; //个人日志是否填写

    public Integer getIsOK() {
        return isOK;
    }

    public void setIsOK(Integer isOK) {
        this.isOK = isOK;
    }

    public Integer getIsCancel() {
        return isCancel;
    }
    public void setIsCancel(Integer isCancel) {
        this.isCancel = isCancel;
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1067215697, 1127050657).appendSuper(super.hashCode())
            .append(this.isCancel).toHashCode();
    }
    
    
}
