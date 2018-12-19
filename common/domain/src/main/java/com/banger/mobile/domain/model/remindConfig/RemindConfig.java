/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :消息提醒配置实体类
 * Author     :yujh
 * Create Date:Jun 1, 2012
 */
package com.banger.mobile.domain.model.remindConfig;

import com.banger.mobile.domain.model.base.remindConfig.BaseRemindConfig;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author yujh
 * @version $Id: RemindConfig.java,v 0.1 Jun 1, 2012 12:54:32 PM Administrator Exp $
 */
public class RemindConfig extends BaseRemindConfig{

    private static final long serialVersionUID = -7436824493784130890L;
    
    public RemindConfig(){
        
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(88914395, -1034528887).appendSuper(super.hashCode())
            .toHashCode();
    }
    
}
