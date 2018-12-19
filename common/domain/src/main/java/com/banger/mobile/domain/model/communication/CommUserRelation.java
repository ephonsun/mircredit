/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :CommUserRelation用户/交流关系表实体类
 * Author     :liyb
 * Create Date:2012-12-24
 */
package com.banger.mobile.domain.model.communication;

import com.banger.mobile.domain.model.base.communication.BaseCommUserRelation;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author liyb
 * @version $Id: CommUserRelation.java,v 0.1 2012-12-24 下午02:20:56 liyb Exp $
 */
public class CommUserRelation extends BaseCommUserRelation {

    private static final long serialVersionUID = 5935180841202092498L;
    
    private String userName;
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(371593625, -1749168979).appendSuper(super.hashCode())
            .toHashCode();
    }
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
