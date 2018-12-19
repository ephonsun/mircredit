/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :证件类型serviceimpl
 * Author     :yujh
 * Create Date:Jul 17, 2012
 */
package com.banger.mobile.facade.impl.plan;

import java.util.List;

import com.banger.mobile.dao.CardType.CardTypeDao;
import com.banger.mobile.domain.model.cardType.CardType;
import com.banger.mobile.facade.CardType.CardTypeService;

/**
 * @author yujh
 * @version $Id: CardTypeServiceImpl.java,v 0.1 Jul 17, 2012 4:56:05 PM Administrator Exp $
 */
public class CardTypeServiceImpl implements CardTypeService{
    private     CardTypeDao     cardTypeDao;


    public void setCardTypeDao(CardTypeDao cardTypeDao) {
        this.cardTypeDao = cardTypeDao;
    }


    public List<CardType> getCardTypes() {
        return this.cardTypeDao.getCardTypes();
    }

}
