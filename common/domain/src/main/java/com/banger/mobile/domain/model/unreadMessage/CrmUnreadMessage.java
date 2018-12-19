/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yangy
 * Create Date:2012-6-11
 */
package com.banger.mobile.domain.model.unreadMessage;

import com.banger.mobile.domain.model.base.unreadMessage.BaseCrmUnreadMessage;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author yangyang
 * @version $Id: CrmUnreadMessage.java,v 0.1 2012-6-11 下午3:36:42 yangyong Exp $
 */
public class CrmUnreadMessage extends BaseCrmUnreadMessage{

    private static final long serialVersionUID = -3627310965121398378L;

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-400118455, -795761377).appendSuper(super.hashCode())
            .toHashCode();
    }
    public CrmUnreadMessage(){
        
    }

}
