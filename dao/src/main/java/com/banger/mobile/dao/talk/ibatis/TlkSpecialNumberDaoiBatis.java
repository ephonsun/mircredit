package com.banger.mobile.dao.talk.ibatis;

import java.util.List;

import com.banger.mobile.dao.talk.TlkSpecialNumberDao;
import com.banger.mobile.domain.model.talk.TlkSpecialNumber;
import com.banger.mobile.ibatis.GenericDaoiBatis;

@SuppressWarnings("rawtypes")
public class TlkSpecialNumberDaoiBatis extends GenericDaoiBatis implements TlkSpecialNumberDao {

	@SuppressWarnings("unchecked")
	public TlkSpecialNumberDaoiBatis() {
		super(TlkSpecialNumber.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 得到所有特殊号码
	 */
	@SuppressWarnings("unchecked")
	public List<TlkSpecialNumber> getSpecialNumbers(){
		return (List<TlkSpecialNumber>)this.getSqlMapClientTemplate().queryForList("getSpecialNumbers");
	}
}
