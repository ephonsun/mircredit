/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :Administrator
 * Create Date:Jul 17, 2012
 */
package com.banger.mobile.dao.CardType.ibatis;

import java.util.List;

import com.banger.mobile.dao.CardType.CardTypeDao;
import com.banger.mobile.domain.model.cardType.CardType;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author Administrator
 * @version $Id: CardTypeDaoiBatis.java,v 0.1 Jul 17, 2012 4:33:09 PM Administrator Exp $
 */
public class CardTypeDaoiBatis extends GenericDaoiBatis implements CardTypeDao {

    
    public CardTypeDaoiBatis(Class persistentClass) {
        super(CardType.class);
    }
    public CardTypeDaoiBatis(){
        super(CardType.class);
    }

    /**
	 * 证件类型列表
	 * @return
	 */
    public List<CardType> getCardTypes() {
        return this.getSqlMapClientTemplate().queryForList("getAllCardTypes");
    }

}
