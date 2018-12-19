/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :TaskTarget任务目标实体类
 * Author     :liyb
 * Create Date:2012-5-25
 */
package com.banger.mobile.domain.model.task;

import com.banger.mobile.domain.model.base.task.BaseTaskTarget;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author liyb
 * @version $Id: TaskTarget.java,v 0.1 2012-5-25 下午01:16:56 liyb Exp $
 */
public class TaskTarget extends BaseTaskTarget {

    private static final long serialVersionUID = -6297913304292556120L;
    public TaskTarget(){}
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1832523187, -748436349).appendSuper(super.hashCode())
            .toHashCode();
    }
}
