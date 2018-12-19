package com.banger.mobile.facade.system;

import java.util.List;

import com.banger.mobile.domain.model.system.SysTalklimitConfig;

/**
 * 有效通话量设置接口
 * @author huyb
 *
 */
public interface SysTalkLimitConfigService {
	
	/**
	 * 获取所有用户的有效通话量设置
	 */
	List<SysTalklimitConfig> getTalkLimitConfigs();
	
	/**
	 * 保存设置
	 */
	boolean saveTalklimitConfig(List<SysTalklimitConfig> list);
	/**
	 * 删除设置
	 */
	boolean deleteTalklimitConfig(List<SysTalklimitConfig> list);
	
	/**
	 * 设置有效通话量是否开启
	 * @param isRemind
	 * @return
	 */
	boolean setIsTalklimitConfigRemind(boolean isRemind);
	
	/**
	 * 查询有效通话量是否开启
	 * @return
	 */
	boolean getIsTalklimitConfigOpen();
	
	/**
	 * 判断用户是否需要提醒 
	 * @param userid
	 * @return
	 */
	boolean isTalklimitConfigRemind(int userid);
	
}
