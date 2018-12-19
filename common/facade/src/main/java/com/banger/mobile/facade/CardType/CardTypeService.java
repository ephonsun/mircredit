/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :证件类型service
 * Author     :yujh
 * Create Date:Jul 17, 2012
 */
package com.banger.mobile.facade.CardType;

import java.util.List;

import com.banger.mobile.domain.model.cardType.CardType;

/**
 * @author yujh
 * @version $Id: CardTypeService.java,v 0.1 Jul 17, 2012 4:51:14 PM Administrator Exp $
 */
public interface CardTypeService {
    public List<CardType> getCardTypes();
}
