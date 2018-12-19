/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :答录配置实体类
 * Author     :yujh
 * Create Date:Jun 4, 2012
 */
package com.banger.mobile.domain.model.answerConfig;

import com.banger.mobile.domain.model.base.answerConfig.BaseAnswerConfig;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author yujh
 * @version $Id: AnswerConfig.java,v 0.1 Jun 4, 2012 3:58:54 PM Administrator Exp $
 */
public class AnswerConfig extends BaseAnswerConfig{

    private static final long serialVersionUID = 2266328953282516037L;
    
    public AnswerConfig(){
        
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-663200813, 1945896033).appendSuper(super.hashCode())
            .toHashCode();
    }

}
