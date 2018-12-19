/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-12-20
 */
package com.banger.mobile.domain.model.tskContact;

import java.util.Date;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author cheny
 * @version $Id: TskPlanDateTarget.java,v 0.1 2012-12-20 上午9:20:09 cheny Exp $
 */
public class TskPlanDateTarget extends TskPlanTarget{

    private static final long serialVersionUID = 4313854399901612738L;

    private Date taskPlanDate;

    /**
     * 
     */
    public TskPlanDateTarget() {
        super();
    }

    public Date getTaskPlanDate() {
        return taskPlanDate;
    }

    public void setTaskPlanDate(Date taskPlanDate) {
        this.taskPlanDate = taskPlanDate;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1635640521, -698170347).appendSuper(super.hashCode())
            .append(this.taskPlanDate).toHashCode();
    }
    
    
}
