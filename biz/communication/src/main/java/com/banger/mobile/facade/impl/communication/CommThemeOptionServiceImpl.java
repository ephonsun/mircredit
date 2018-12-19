package com.banger.mobile.facade.impl.communication;

import java.util.List;

import com.banger.mobile.dao.communication.CommThemeOptionDao;
import com.banger.mobile.domain.model.communication.CommThemeOption;
import com.banger.mobile.facade.communication.CommThemeOptionService;

public class CommThemeOptionServiceImpl implements CommThemeOptionService {

	private CommThemeOptionDao commThemeOptionDao;

	public Integer insertOption(List<CommThemeOption> list) {
		return commThemeOptionDao.insertOption(list);
	}

	public Integer deleteOption(Integer themeId) {
		return commThemeOptionDao.deleteOption(themeId);
	}

	public List<CommThemeOption> getOptionList(Integer themeId) {
		return commThemeOptionDao.getOptionList(themeId);
	}

	public CommThemeOptionDao getCommThemeOptionDao() {
		return commThemeOptionDao;
	}

	public void setCommThemeOptionDao(CommThemeOptionDao commThemeOptionDao) {
		this.commThemeOptionDao = commThemeOptionDao;
	}

	public Integer updateOption(List<CommThemeOption> list) {
		return commThemeOptionDao.updateOption(list);
	}
}
