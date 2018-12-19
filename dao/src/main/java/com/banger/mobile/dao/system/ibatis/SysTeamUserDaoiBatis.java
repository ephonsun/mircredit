package com.banger.mobile.dao.system.ibatis;

import java.util.List;

import com.banger.mobile.dao.system.SysTeamUserDao;
import com.banger.mobile.domain.model.base.system.BaseSysTeam;
import com.banger.mobile.domain.model.base.system.BaseSysTeamUser;
import com.banger.mobile.domain.model.system.SysTeamUser;
import com.banger.mobile.ibatis.GenericDaoiBatis;

public class SysTeamUserDaoiBatis extends GenericDaoiBatis implements
		SysTeamUserDao {

	public SysTeamUserDaoiBatis(){
		super(SysTeamUser.class);
	}
	
	public SysTeamUserDaoiBatis(Class persistentClass) {
		super(SysTeamUser.class);
	}

	@Override
	public void addSysTeamUser(BaseSysTeamUser sysTeamUser) {
		this.getSqlMapClientTemplate().insert("addSysTeamUser",sysTeamUser);
	}

	@Override
	public void deleteSysTeamUser(Integer id) {
		this.getSqlMapClientTemplate().delete("deleteSysTeamUserById",id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BaseSysTeamUser> getUserListByTeamId(Integer teamId) {
		return this.getSqlMapClientTemplate().queryForList("getUserListByTeamId",teamId);
	}

	@Override
	public void deleteSysTeamUserByInfo(SysTeamUser sysTeamUser) {
		this.getSqlMapClientTemplate().delete("deleteSysTeamUserByInfo",sysTeamUser);
		
	}

	@Override
	public BaseSysTeamUser getUserListById(Integer teamUserId) {
		// TODO Auto-generated method stub
		 return (BaseSysTeamUser) this.getSqlMapClientTemplate().queryForObject("getUserListById",teamUserId);
	}

	@Override
	public List<Integer> getUsedUserList() {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("getUsedUserList");
	}

	@Override
	public List<String> getUserIdsByChiefUserId(Integer userId) {
		return this.getSqlMapClientTemplate().queryForList("getUserIdsByChiefUserId",userId);
	}
	
	
}
