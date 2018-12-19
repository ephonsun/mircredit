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
public class UserOnlineBean extends DeptUserBean implements Serializable {

    
    private static final long serialVersionUID = 5611887112085911518L;
    private String            loginState;                            //登陆状态  
    public String getLoginState() {
        return loginState;
    }
    public void setLoginState(String loginState) {
        this.loginState = loginState;
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1784311307, 1301033667).appendSuper(super.hashCode())
            .append(this.loginState).toHashCode();
    }
    
}
