/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :导入通话白名单失败bean
 * Author     :yujh
 * Create Date:Aug 22, 2012
 */
package com.banger.mobile.domain.model.sysWhiteList;

import com.banger.mobile.domain.model.base.sysWhiteList.BaseSysWhiteList;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author yujh
 * @version $Id: SysWhiteList.java,v 0.1 Aug 22, 2012 1:24:23 PM Administrator Exp $
 */
public class ImportSysWhiteListResultBean extends BaseSysWhiteList{
    private String  failReason;
    private Integer rowNumber;

    private static final long serialVersionUID = -3957666296675220502L;
    
    public ImportSysWhiteListResultBean(){
        
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }
    
    
}
