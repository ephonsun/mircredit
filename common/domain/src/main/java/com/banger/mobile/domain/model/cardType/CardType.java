/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :证件类型实体类
 * Author     :yujh
 * Create Date:Jul 17, 2012
 */
package com.banger.mobile.domain.model.cardType;

import com.banger.mobile.domain.model.base.cardType.BaseCardType;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author yujh
 * @version $Id: CardType.java,v 0.1 Jul 17, 2012 4:27:59 PM Administrator Exp $
 */
public class CardType extends BaseCardType{

    private static final long serialVersionUID = 7618653755354684831L;
    public CardType(){
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(120821503, 605718547).appendSuper(super.hashCode()).toHashCode();
    }
    
}
