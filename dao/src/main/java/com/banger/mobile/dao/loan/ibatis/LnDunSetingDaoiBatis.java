package com.banger.mobile.dao.loan.ibatis;

import java.util.HashMap;
import java.util.Map;

import com.banger.mobile.dao.loan.LnDunSetingDao;
import com.banger.mobile.domain.model.adsync.SyncAdPcUsersSetting;
import com.banger.mobile.domain.model.loan.LnDunSeting;
import com.banger.mobile.ibatis.GenericDaoiBatis;

@SuppressWarnings("rawtypes")
public class LnDunSetingDaoiBatis extends GenericDaoiBatis implements LnDunSetingDao {

	@SuppressWarnings("unchecked")
	public LnDunSetingDaoiBatis(){
		super(LnDunSeting.class);
	}
	@SuppressWarnings("unchecked")
	public LnDunSetingDaoiBatis(Class persistentClass) {
		super(LnDunSeting.class);
	}

	/**
	 * 修改催收设置
	 * @param lnDunSeting
	 */
	public void updateLnDunSeting(LnDunSeting lnDunSeting){
		this.getSqlMapClientTemplate().update("updateLnDunSeting", lnDunSeting);
	}
	/**
	 * 查询催收设置
	 * @return
	 */
	public LnDunSeting getLnDunSeting(Integer flag){
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("flag", flag);
		return (LnDunSeting)this.getSqlMapClientTemplate().queryForObject("getLnDunSeting", condition);
	}
}
