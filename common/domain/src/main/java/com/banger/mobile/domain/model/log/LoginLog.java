/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :安全日志实体类
 * Author     :yujh
 * Create Date:May 21, 2012
 */
package com.banger.mobile.domain.model.log;


import com.banger.mobile.domain.model.base.log.BaseLoginLog;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author yujh
 * @version $Id: LoginLog.java,v 0.1 May 21, 2012 10:01:21 AM Administrator Exp $
 */
public class LoginLog extends BaseLoginLog{

    private static final long serialVersionUID = -4291500599772478635L;

    public LoginLog(){
        
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-580521279, -1415799845).appendSuper(super.hashCode())
            .toHashCode();
    };


}
