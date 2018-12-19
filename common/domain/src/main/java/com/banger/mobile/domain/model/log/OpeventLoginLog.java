/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :操作事件
 * Author     :yujh
 * Create Date:Aug 14, 2012
 */
package com.banger.mobile.domain.model.log;

import com.banger.mobile.domain.model.base.log.BaseOpeventLoginLog;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author yujh
 * @version $Id: OpeventLoginLog.java,v 0.1 Aug 14, 2012 9:33:32 AM Administrator Exp $
 */
public class OpeventLoginLog extends BaseOpeventLoginLog {

    private static final long serialVersionUID = 4741330568324346596L;
    
    public OpeventLoginLog(){}

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-738981697, 216070367).appendSuper(super.hashCode())
            .toHashCode();
    };
    
    
}
