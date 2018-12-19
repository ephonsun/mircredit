package com.banger.mobile.facade.system.team;

import java.util.List;

import com.banger.mobile.domain.model.base.system.BaseSysTeamUser;
import com.banger.mobile.domain.model.system.SysTeamUser;

public interface SysTeamUserService {
	/**
     * 添加团队成员
     * @param sysTeam
     */
	public void insertSysTeamUser(SysTeamUser sysTeamUser);
	/**
	 * 查找已经加入团队的团队主管用户编号
	 * @param teamId
	 * @return
	 */
	public List<Integer> getUsedUserList();
	/**
	 * 查找团队成员列表
	 * @param teamId
	 * @return
	 */
	public List<BaseSysTeamUser> getUserListByTeamId(Integer teamId);
	/**
	 * 查找团队成员列表
	 * @param teamId
	 * @return
	 */
	public BaseSysTeamUser getUserListById(Integer sysTeamUserId);
    /**
     * 删除团队成员用户
     * @param stu
     */
    public void deleteSysTeamUser(SysTeamUser sysTeamUser);
    
	/**
	 * 根据团队主管编号，查询组员底下所有人员的编号
	 * @param userId
	 * @return
	 */
	public List<String> getUserIdsByChiefUserId(Integer userId);
}
