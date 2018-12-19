/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :Administrator
 * Create Date:Jul 17, 2012
 */
package com.banger.mobile.service;

import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.CardType.CardTypeService;

/**
 * @author yujh
 * @version $Id: CardServiceTest.java,v 0.1 Jul 17, 2012 5:00:52 PM Administrator Exp $
 */
public class CardServiceTest extends BaseServiceTestCase {
    private     CardTypeService     cardTypeService;

    public void setCardTypeService(CardTypeService cardTypeService) {
        this.cardTypeService = cardTypeService;
    }
//    public void testNotNull(){
//        assertNotNull(cardTypeService);
//    }
    public void testQuery(){
        System.out.println(this.cardTypeService.getCardTypes().size());
    }
}
