/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务执行明细-Domain-扩展1
 * Author     :QianJie
 * Create Date:Mar 2, 2013
 */
package com.banger.mobile.domain.model.microTask;

import com.banger.mobile.domain.model.base.microTask.BaseTskMicroTaskExecute;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author QianJie
 * @version $Id: TskMicroTaskExecute.java,v 0.1 Mar 2, 2013 10:59:06 AM QianJie Exp $
 */
public class TskMicroTaskExecute extends BaseTskMicroTaskExecute {

    private static final long serialVersionUID = -6095431195694339508L;

    public TskMicroTaskExecute() {
        super();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-228800439, -898119423).appendSuper(super.hashCode())
            .toHashCode();
    }

    
}
