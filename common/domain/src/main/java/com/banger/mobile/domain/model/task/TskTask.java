/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :TskTask任务实体类
 * Author     :liyb
 * Create Date:2012-7-16
 */
package com.banger.mobile.domain.model.task;

import com.banger.mobile.domain.model.base.task.BaseTskTask;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author liyb
 * @version $Id: TskTask.java,v 0.1 2012-7-16 上午09:57:27 liyb Exp $
 */
public class TskTask extends BaseTskTask {

    private static final long serialVersionUID = -550697884545698238L;

    public TskTask(){}

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(276170225, -803985953).appendSuper(super.hashCode())
            .toHashCode();
    };
}
