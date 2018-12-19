/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :Task任务实体类
 * Author     :liyb
 * Create Date:2012-5-25
 */
package com.banger.mobile.domain.model.task;

import com.banger.mobile.domain.model.base.task.BaseCrmTask;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author liyb
 * @version $Id: Task.java,v 0.1 2012-5-25 上午10:44:38 liyb Exp $
 */
public class CrmTask extends BaseCrmTask {

    private static final long serialVersionUID = -4695112514748151787L;
    public CrmTask(){}
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-63874561, -1994452673).appendSuper(super.hashCode())
            .toHashCode();
    }
    
}
