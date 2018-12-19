package com.banger.mobile.dao.communication.iBatis;

import java.util.List;

import com.banger.mobile.dao.communication.CommThemeOptionDao;
import com.banger.mobile.domain.model.communication.CommThemeOption;
import com.banger.mobile.ibatis.GenericDaoiBatis;

public class CommThemeOptionDaoiBatis extends GenericDaoiBatis implements
		CommThemeOptionDao {

	public CommThemeOptionDaoiBatis() {
		super(CommThemeOptionDaoiBatis.class);
	}

	public Integer insertOption(List<CommThemeOption> list) {
		return this.exectuteBatchInsert("addCommOption", list);
	}

	public Integer deleteOption(Integer themeId) {
		return this.getSqlMapClientTemplate().delete("deleteCommOption",themeId);
	}

	public List<CommThemeOption> getOptionList(Integer themeId) {
		return this.getSqlMapClientTemplate().queryForList("getCommOptionById", themeId);
	}

	public Integer updateOption(List<CommThemeOption> list) {
		return this.executeBatchUpdate("updateCommOption", list);
	}

}
