package com.banger.mobile.dao.communication;

import java.util.List;

import com.banger.mobile.domain.model.communication.CommThemeOption;

public interface CommThemeOptionDao {
	/**
	 * 插入投票
	 * @param list
	 * @return
	 */
	Integer insertOption(List<CommThemeOption> list);
	
	
	/**
	 * 删除投票
	 * @param themeId
	 * @return
	 */
	Integer deleteOption(Integer themeId);
	
	
	/**
	 * 根据主题ID获取投票列表
	 * @param themeId
	 * @return
	 */
	List<CommThemeOption>   getOptionList(Integer themeId);
	
	
	 Integer updateOption(List<CommThemeOption> list);
}
