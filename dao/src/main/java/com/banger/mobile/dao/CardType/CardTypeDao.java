/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :证件类型dao
 * Author     :yujh
 * Create Date:Jul 17, 2012
 */
package com.banger.mobile.dao.CardType;

import java.util.List;

import com.banger.mobile.domain.model.cardType.CardType;

/**
 * @author yujh
 * @version $Id: CardTypeDao.java,v 0.1 Jul 17, 2012 4:30:44 PM Administrator Exp $
 */
public interface CardTypeDao {
    
	/**
	 * 证件类型列表
	 * @return
	 */
    public List<CardType> getCardTypes();
}
