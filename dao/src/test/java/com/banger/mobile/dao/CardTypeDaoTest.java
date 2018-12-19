/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :Administrator
 * Create Date:Jul 17, 2012
 */
package com.banger.mobile.dao;

import com.banger.mobile.dao.CardType.CardTypeDao;

/**
 * @author Administrator
 * @version $Id: CardTypeDaoTest.java,v 0.1 Jul 17, 2012 4:41:35 PM Administrator Exp $
 */
public class CardTypeDaoTest extends BaseDaoTestCase{
    private         CardTypeDao     cardTypeDao;

    public void setCardTypeDao(CardTypeDao cardTypeDao) {
        this.cardTypeDao = cardTypeDao;
    }
    public void testNotNull(){
        assertNotNull(cardTypeDao);
    }
    public void testQuery(){
        System.out.println(this.cardTypeDao.getCardTypes());
    }
}   
