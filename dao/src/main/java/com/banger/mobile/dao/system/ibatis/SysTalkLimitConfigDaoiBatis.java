package com.banger.mobile.dao.system.ibatis;

import java.util.ArrayList;
import java.util.List;

import com.banger.mobile.dao.system.SysTalkLimitConfigDao;
import com.banger.mobile.domain.model.base.system.BaseSysTalklimitConfig;
import com.banger.mobile.domain.model.system.SysTalklimitConfig;
import com.banger.mobile.ibatis.GenericDaoiBatis;

public class SysTalkLimitConfigDaoiBatis extends GenericDaoiBatis implements
		SysTalkLimitConfigDao {

	public SysTalkLimitConfigDaoiBatis(Class persistentClass) {
		super(persistentClass);
	}

	public SysTalkLimitConfigDaoiBatis() {
		super(SysTalkLimitConfigDaoiBatis.class);
	}

	/**
	 * 查询有效通话量
	 */
	@SuppressWarnings("unchecked")
	public List<SysTalklimitConfig> getTalkLimitConfigs() {
		return (List<SysTalklimitConfig>) this.getSqlMapClientTemplate()
				.queryForList("getTalkLimitConfigs");
	}

	/**
	 * 保存有效通话量
	 */
	public boolean saveTalklimitConfig(List<SysTalklimitConfig> list) {
		
		deleteTalklimitConfig();
		if (list.size() > 0)
			this.exectuteBatchInsert("insertTalkLimitConfig", list);
		/*
		List<SysTalklimitConfig> updateList = new ArrayList<SysTalklimitConfig>();
		List<SysTalklimitConfig> insertList = new ArrayList<SysTalklimitConfig>();
		this.getSqlMapClientTemplate().queryForList("","");
		for (SysTalklimitConfig sysTalklimitConfig : list) {
			BaseSysTalklimitConfig existConfig = (BaseSysTalklimitConfig) this
					.getSqlMapClientTemplate().queryForObject(
							"getTalkLimitConfig",
							sysTalklimitConfig.getUserid());
			if (null != existConfig) {
				// this.getSqlMapClientTemplate().update("updateTalkLimitConfig",
				// sysTalklimitConfig);
				updateList.add(sysTalklimitConfig);
			} else {
				// this.getSqlMapClientTemplate().insert("insertTalkLimitConfig",
				// sysTalklimitConfig);
				insertList.add(sysTalklimitConfig);
			}

		}
		if (insertList.size() > 0)
			this.exectuteBatchInsert("insertTalkLimitConfig", insertList);
		if (updateList.size() > 0)
			this.exectuteBatchInsert("updateTalkLimitConfig", updateList);
		*/
		return true;
	}

	/**
	 * 配置有效通话量提醒开关
	 */
	public boolean setIsTalklimitConfigRemind(boolean isRemind) {
		this.getSqlMapClientTemplate().update("updateTalkLimitConfigRun",
				isRemind ? "1" : "0");
		return true;
	}

	/**
	 * 查询用户是否需要提醒
	 */
	public boolean isTalklimitConfigRemind(int userId) {
		Boolean isTalklimitConfigRemind = (((String) this
				.getSqlMapClientTemplate().queryForObject(
						"getIsTalkLimitConfigRun")).equals("0")) ? false : true;
		if (isTalklimitConfigRemind) {// 设置启动,查询是否限量中
			Integer lastDayCount = (Integer) this.getSqlMapClientTemplate()
					.queryForObject("getLastDayTalkCount", userId);
			BaseSysTalklimitConfig existConfig = (BaseSysTalklimitConfig) this
					.getSqlMapClientTemplate().queryForObject(
							"getTalkLimitConfig", userId);
			if (null != existConfig) {

				if ((lastDayCount < existConfig.getLowerlimit())
						|| (lastDayCount > existConfig.getToplimit() && existConfig
								.getToplimit() > 0)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 查询系统是否开启有效通话量
	 */
	public boolean isTalklimitConfigOpen() {
		Boolean isTalklimitConfigRemind = (((String) this
				.getSqlMapClientTemplate().queryForObject(
						"getIsTalkLimitConfigRun")).equals("0")) ? false : true;

		return isTalklimitConfigRemind;
	}
	
	public boolean deleteTalklimitConfig(){
		this.getSqlMapClientTemplate().delete("deleteTalkLimitConfig");
		return true;
	}

	public boolean deleteTalklimitConfig(List<SysTalklimitConfig> list) {
		String delIds = "";
		for (SysTalklimitConfig sysTalklimitConfig : list) {
			String conifId = sysTalklimitConfig.getTalklimitConifId()
					.toString();
			if ("".equals(delIds)) {
				delIds = conifId;
			} else {
				delIds = delIds + "," + conifId;
			}
		}
		if (!"".equals(delIds)) {
			this.getSqlMapClientTemplate().delete("deleteTalkLimitConfig",
					delIds);
		}
		return true;
	}

}
