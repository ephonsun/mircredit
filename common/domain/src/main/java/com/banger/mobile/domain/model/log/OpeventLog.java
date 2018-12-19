/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :操作日志实体类
 * Author     :yujh
 * Create Date:May 23, 2012
 */
package com.banger.mobile.domain.model.log;

import com.banger.mobile.domain.model.base.log.BaseOpeventLog;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author yujh
 * @version $Id: OpeventLog.java,v 0.1 May 23, 2012 10:41:33 AM Administrator Exp $
 */
public class OpeventLog extends BaseOpeventLog{

    private static final long serialVersionUID = -377204351850101508L;

    public OpeventLog() {
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1586178809, -1246322767).appendSuper(super.hashCode())
            .toHashCode();
    }
    
    
    
}
