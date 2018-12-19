package com.banger.mobile.facade.impl.system.team;

import java.util.Date;
import java.util.List;

import com.banger.mobile.dao.system.SysTeamUserDao;
import com.banger.mobile.domain.model.base.system.BaseSysTeamUser;
import com.banger.mobile.domain.model.system.SysTeamUser;
import com.banger.mobile.facade.system.team.SysTeamUserService;

public class SysTeamUserServiceImpl implements SysTeamUserService{
	private SysTeamUserDao sysTeamUserDao;
	@Override
	public void insertSysTeamUser(SysTeamUser sysTeamUser) {
		BaseSysTeamUser   stu = new BaseSysTeamUser();
		stu.setCreateDate(new Date());
		stu.setCreateUser(sysTeamUser.getCreateUser());
		stu.setTeamId(sysTeamUser.getTeamId());
		stu.setUpdateDate(new Date());
		stu.setUpdateUser(sysTeamUser.getUpdateUser());
		stu.setUserId(sysTeamUser.getUserId());
		sysTeamUserDao.addSysTeamUser(stu);
	}
	public SysTeamUserDao getSysTeamUserDao() {
		return sysTeamUserDao;
	}
	public void setSysTeamUserDao(SysTeamUserDao sysTeamUserDao) {
		this.sysTeamUserDao = sysTeamUserDao;
	}
	@Override
	public List<BaseSysTeamUser> getUserListByTeamId(Integer teamId) {
		// TODO Auto-generated method stub
		return sysTeamUserDao.getUserListByTeamId(teamId);
	}
	@Override
	public void deleteSysTeamUser(SysTeamUser sysTeamUser) {
		sysTeamUserDao.deleteSysTeamUserByInfo(sysTeamUser);
		
	}
	
	
	@Override
	public List<String> getUserIdsByChiefUserId(Integer userId) {
		return sysTeamUserDao.getUserIdsByChiefUserId(userId);
	}
	@Override
	public BaseSysTeamUser getUserListById(Integer teamUserId) {
		// TODO Auto-generated method stub
		return sysTeamUserDao.getUserListById(teamUserId);
	}
	@Override
	public List<Integer> getUsedUserList() {
		// TODO Auto-generated method stub
		return sysTeamUserDao.getUsedUserList();
	}
	

}
