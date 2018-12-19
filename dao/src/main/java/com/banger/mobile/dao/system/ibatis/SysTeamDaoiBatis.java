package com.banger.mobile.dao.system.ibatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.system.SysTeamDao;
import com.banger.mobile.domain.model.base.system.BaseSysTeam;
import com.banger.mobile.domain.model.system.SysTeam;
import com.banger.mobile.domain.model.system.SysTeamInfo;
import com.banger.mobile.ibatis.GenericDaoiBatis;

public class SysTeamDaoiBatis extends GenericDaoiBatis implements SysTeamDao {

	public SysTeamDaoiBatis() {
		super(SysTeam.class);
	}

	public SysTeamDaoiBatis(Class persistentClass) {
		super(SysTeam.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addSysTeam(BaseSysTeam sysTeam) {
		this.getSqlMapClientTemplate().insert("addSysTeam",sysTeam);
	}

	@Override
	public void deleteSysTeam(Integer id) {
		this.getSqlMapClientTemplate().update("deleteSysTeamById",id);

	}

	@Override
	public void updateSysTeam(BaseSysTeam sysTeam) {
		this.getSqlMapClientTemplate().update("updateSysTeam",sysTeam);
	}

	@Override
	public PageUtil<BaseSysTeam> getSysTeamPage(Map<String, Object> parameters,	Page page) {
		List<BaseSysTeam> list = (List<BaseSysTeam>) this.findQueryPage(
				"getSysTeamParameterPageMap", "getSysTeamCount",parameters, page);
		if (list == null) {
			list = new ArrayList<BaseSysTeam>();
		}
		return new PageUtil<BaseSysTeam>(list, page);
	}

	@Override
	public List<SysTeamInfo> getTeamInfoList(Integer teamId) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("getTeamInfoList",teamId);
	}

	@Override
	public BaseSysTeam getSysTeamByName(String teamName) {
		// TODO Auto-generated method stub
		return (BaseSysTeam) this.getSqlMapClientTemplate().queryForObject("getSysTeamByName",teamName);
	}
	@Override
	public BaseSysTeam getSysTeamByUserId(Integer userid) {
		// TODO Auto-generated method stub
		return (BaseSysTeam) this.getSqlMapClientTemplate().queryForObject("getSysTeamByUserId",userid);
	}
	@Override
	public List<SysTeam> getSysTeamList() {
		return this.getSqlMapClientTemplate().queryForList("getSysTeamList");
	}


	public List<BaseSysTeam> getBaseSysTeamList() {
		return this.getSqlMapClientTemplate().queryForList("getSysTeamList");
	}


}
