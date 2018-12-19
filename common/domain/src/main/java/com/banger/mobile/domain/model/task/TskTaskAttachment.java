/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :TskTaskAttachment任务附件实体类
 * Author     :liyb
 * Create Date:2012-8-13
 */
package com.banger.mobile.domain.model.task;

import com.banger.mobile.domain.model.base.task.BaseTskTaskAttachment;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author liyb
 * @version $Id: TskTaskAttachment.java,v 0.1 2012-8-13 上午09:59:39 liyb Exp $
 */
public class TskTaskAttachment extends BaseTskTaskAttachment {

    private static final long serialVersionUID = -7262238177103161204L;

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-161813133, 1520366275).appendSuper(super.hashCode())
            .toHashCode();
    }

}
