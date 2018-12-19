package com.banger.mobile.dao.system;

import java.util.List;

import com.banger.mobile.domain.model.base.system.BaseSysTeamUser;
import com.banger.mobile.domain.model.system.SysTeamUser;

public interface SysTeamUserDao {

	/**
	 * 新增团队用户
	 * @param sysTeamUser
	 */
	public void addSysTeamUser(BaseSysTeamUser sysTeamUser);
	
	/**
	 * 删除团队用户
	 * @param id
	 */
	public void deleteSysTeamUser(Integer id);

	/**
	 * 删除团队用户
	 * @param sysTeamUser
	 */
	public void deleteSysTeamUserByInfo(SysTeamUser sysTeamUser);
	
	/**
	 * 查找团队成员列表
	 * @param teamId
	 * @return
	 */
	public List<BaseSysTeamUser> getUserListByTeamId(Integer teamId);
	/**
	 * 查找团队成员列表
	 * @param teamUserId
	 * @return
	 */
	public BaseSysTeamUser getUserListById(Integer teamUserId);
	/**
	 * 查找已参加团队的团队主管编号
	 * @return
	 */
	public List<Integer> getUsedUserList();

	/**
	 * 根据团队主管编号，查询团队底下所有人员的编号信息
	 * @param userId
	 * @return
	 */
	public List<String> getUserIdsByChiefUserId(Integer userId);
}
