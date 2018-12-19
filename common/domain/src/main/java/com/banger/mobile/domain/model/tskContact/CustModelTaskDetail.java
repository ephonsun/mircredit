/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-12-13
 */
package com.banger.mobile.domain.model.tskContact;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author cheny
 * @version $Id: CustModelTaskDetail.java,v 0.1 2012-12-13 上午11:33:25 cheny Exp $
 */
public class CustModelTaskDetail extends TskTaskListBean{

    private static final long serialVersionUID = -7148825049146667893L;

    private String remark;

    
    
    /**
     * 
     */
    public CustModelTaskDetail() {
        super();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-851304451, -1514770643).appendSuper(super.hashCode())
            .append(this.remark).toHashCode();
    }
    
    
}
