package com.banger.mobile.facade.impl.system.TalkLimitConfig;

import java.util.List;

import com.banger.mobile.dao.system.SysTalkLimitConfigDao;
import com.banger.mobile.domain.model.system.SysTalklimitConfig;
import com.banger.mobile.facade.system.SysTalkLimitConfigService;

public class SysTalkLimitConfigServiceImpl implements SysTalkLimitConfigService {

	private SysTalkLimitConfigDao sysTalkLimitConfigDao;

	public SysTalkLimitConfigDao getSysTalkLimitConfigDao() {
		return sysTalkLimitConfigDao;
	}

	public void setSysTalkLimitConfigDao(
			SysTalkLimitConfigDao sysTalkLimitConfigDao) {
		this.sysTalkLimitConfigDao = sysTalkLimitConfigDao;
	}

	public List<SysTalklimitConfig> getTalkLimitConfigs() {

		return sysTalkLimitConfigDao.getTalkLimitConfigs();
	}

	public boolean saveTalklimitConfig(List<SysTalklimitConfig> list) {

		return sysTalkLimitConfigDao.saveTalklimitConfig(list);
	}

	public boolean setIsTalklimitConfigRemind(boolean isRemind) {

		return sysTalkLimitConfigDao.setIsTalklimitConfigRemind(isRemind);
	}

	public boolean getIsTalklimitConfigOpen() {
		return sysTalkLimitConfigDao.isTalklimitConfigOpen();
	}

	public boolean deleteTalklimitConfig(List<SysTalklimitConfig> list) {
		// TODO Auto-generated method stub
		return sysTalkLimitConfigDao.deleteTalklimitConfig(list);
	}

	public boolean isTalklimitConfigRemind(int userid) {
		return sysTalkLimitConfigDao.isTalklimitConfigRemind(userid);
	}

}
