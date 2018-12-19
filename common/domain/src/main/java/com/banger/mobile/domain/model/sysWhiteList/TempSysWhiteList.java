/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :通话白名单临时bean
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
public class TempSysWhiteList extends BaseSysWhiteList{

    private static final long serialVersionUID = -3957666296675220502L;
    
    public TempSysWhiteList(){
        
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-633721783, 1193172503).appendSuper(super.hashCode())
            .toHashCode();
    }
    
}
