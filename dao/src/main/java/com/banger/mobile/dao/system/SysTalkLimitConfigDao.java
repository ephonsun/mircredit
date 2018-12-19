package com.banger.mobile.dao.system;

import java.util.List;

import com.banger.mobile.domain.model.system.SysTalklimitConfig;

public interface SysTalkLimitConfigDao {

	List<SysTalklimitConfig> getTalkLimitConfigs();

	boolean saveTalklimitConfig(List<SysTalklimitConfig> list);

	boolean setIsTalklimitConfigRemind(boolean isRemind);
	
	boolean isTalklimitConfigRemind(int userId);
	
	boolean isTalklimitConfigOpen();
	
	boolean deleteTalklimitConfig(List<SysTalklimitConfig> list);
}
