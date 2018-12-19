/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音记录实体数据接口
 * Author     :zhangxiang
 * Version    :1.0
 * Create Date:May 2, 2012
 */
package com.banger.mobile.dao.customer.ibatis;


import java.util.List;

import com.banger.mobile.dao.customer.FamilyNameDao;
import com.banger.mobile.domain.model.base.customer.BaseFamilyName;
import com.banger.mobile.ibatis.GenericDaoiBatis;


public class FamilyNameDaoiBatis extends GenericDaoiBatis implements FamilyNameDao {

	public FamilyNameDaoiBatis() {
        super(BaseFamilyName.class);
    }
	
	public FamilyNameDaoiBatis(Class persistentClass) {
		super(BaseFamilyName.class);
	}

	/**
	    * 获得所有百家姓
	    * @return
	    */
	public List<BaseFamilyName> getNickName() {
		return this.getSqlMapClientTemplate().queryForList("getNickName");
	}

}
