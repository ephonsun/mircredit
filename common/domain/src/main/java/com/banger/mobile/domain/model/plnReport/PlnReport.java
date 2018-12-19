/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :理财规划报告实体类
 * Author     :yujh
 * Create Date:Jul 25, 2012
 */
package com.banger.mobile.domain.model.plnReport;

import com.banger.mobile.domain.model.base.plnReport.BasePlnReport;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author yujh
 * @version $Id: PlnReport.java,v 0.1 Jul 25, 2012 11:02:46 AM Administrator Exp $
 */
public class PlnReport extends  BasePlnReport{

    private static final long serialVersionUID = -6229410144043596278L;
    
    public PlnReport(){
        
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1919750127, 2044712199).appendSuper(super.hashCode())
            .toHashCode();
    }
    
}
