/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :性别代码表
 * Author     :zsw
 * Create Date:May 21, 2012
 */
package com.banger.mobile.dao.system.ibatis;

import java.util.List;
import com.banger.mobile.domain.model.system.CdSex;
import com.banger.mobile.ibatis.GenericDaoiBatis;
import com.banger.mobile.dao.system.CdSexDao;

@SuppressWarnings("rawtypes")
public class CdSexDaoiBatis extends GenericDaoiBatis implements CdSexDao {

	@SuppressWarnings("unchecked")
	public CdSexDaoiBatis() {
		super(CdSex.class);
	}

	/**
	 * 反回性别列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CdSex> getSexs() {
		return (List<CdSex>)this.getSqlMapClientTemplate().queryForList("getCdSex");
	}

}
