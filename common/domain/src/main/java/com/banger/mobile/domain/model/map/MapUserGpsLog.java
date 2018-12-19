/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :用户地理位置历史记录实体内...
 * Author     :yangy
 * Create Date:2013-3-13
 */
package com.banger.mobile.domain.model.map;

import com.banger.mobile.domain.model.base.map.BaseMapUserGpsLog;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author yangyang
 * @version $Id: MapUserGpsLog.java,v 0.1 2013-3-13 下午2:13:19 yangyong Exp $
 */
public class MapUserGpsLog extends BaseMapUserGpsLog {

    private static final long serialVersionUID = -4073213553508899603L;

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1084871739, 2086235671).appendSuper(super.hashCode())
            .toHashCode();
    }

    public MapUserGpsLog() {
        super();
    }
}
