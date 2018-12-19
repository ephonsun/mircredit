/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :TskTaskTarget任务目标客户实体类
 * Author     :liyb
 * Create Date:2012-7-16
 */
package com.banger.mobile.domain.model.task;

import com.banger.mobile.domain.model.base.task.BaseTskTaskTarget;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author liyb
 * @version $Id: TskTaskTarget.java,v 0.1 2012-7-16 下午01:01:42 liyb Exp $
 */
public class TskTaskTarget extends BaseTskTaskTarget {

    private static final long serialVersionUID = 3924949945780079886L;
    public TskTaskTarget(){}
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1174389029, 145206987).appendSuper(super.hashCode())
            .toHashCode();
    }
}
