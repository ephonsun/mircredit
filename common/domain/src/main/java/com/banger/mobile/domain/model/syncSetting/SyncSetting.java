/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :SyncSetting客户资料同步设置实体类
 * Author     :liyb
 * Create Date:2012-6-1
 */
package com.banger.mobile.domain.model.syncSetting;

import com.banger.mobile.domain.model.base.syncSetting.BaseSyncSetting;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author liyb
 * @version $Id: SyncSetting.java,v 0.1 2012-6-1 下午01:17:30 liyb Exp $
 */
public class SyncSetting extends BaseSyncSetting {

    private static final long serialVersionUID = -2574662472303803043L;

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-121401517, 258267923).appendSuper(super.hashCode())
            .toHashCode();
    }
    
}
