/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :CommThemeOption投票贴选项实体类
 * Author     :liyb
 * Create Date:2012-12-24
 */
package com.banger.mobile.domain.model.communication;

import com.banger.mobile.domain.model.base.communication.BaseCommThemeOption;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author liyb
 * @version $Id: CommThemeOption.java,v 0.1 2012-12-24 下午02:15:53 liyb Exp $
 */
public class CommThemeOption extends BaseCommThemeOption {

    private static final long serialVersionUID = 1772915382960951151L;
    
    private String percent;

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1628364005, 1919760065).appendSuper(super.hashCode())
            .toHashCode();
    }

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

}
