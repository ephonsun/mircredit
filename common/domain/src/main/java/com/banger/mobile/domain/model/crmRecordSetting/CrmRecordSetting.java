/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音设置
 * Author     :yujh
 * Create Date:Sep 3, 2012
 */
package com.banger.mobile.domain.model.crmRecordSetting;

import com.banger.mobile.domain.model.base.crmRecordSetting.BaseCrmRecordSetting;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author yujh
 * @version $Id: CrmRecordSetting.java,v 0.1 Sep 3, 2012 4:14:43 PM Administrator Exp $
 */
public class CrmRecordSetting  extends BaseCrmRecordSetting{

    private static final long serialVersionUID = -6340210963754791143L;
    
    public CrmRecordSetting(){
        
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-624382211, -657848805).appendSuper(super.hashCode())
            .toHashCode();
    }
    
}
