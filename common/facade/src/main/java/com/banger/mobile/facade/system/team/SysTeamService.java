package com.banger.mobile.facade.system.team;

import java.util.List;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.base.system.BaseSysTeam;
import com.banger.mobile.domain.model.system.SysTeam;
import com.banger.mobile.domain.model.system.SysTeamInfo;

public interface SysTeamService {
	/**
     * 角色分页列表
     * @param parameters 查询条件Map集合
     * @param page
     * @return
     */
    public PageUtil<SysTeam> getSysTeamPage(SysTeam sysTeam, Page page);
    /**
     * 修改团队
     * @param systeam
     */
    public void updateSysTeam(BaseSysTeam systeam);
    /**
     * 根据条件返回团队信息
     * @param sysTeam
     * @return
     */
    public BaseSysTeam getSysTeamByName(SysTeam sysTeam);
    /**
     * 根据条件返回团队信息
     * @param sysTeam
     * @return
     */
    public List<SysTeamInfo> getSysTeamById(Integer teamId);
    /**
     * 添加团队
     * @param sysTeam
     */
	public void insertSysTeam(SysTeam sysTeam);
	
	/**
	 * 查询微贷事业中心 
	 * @return
	 */
	public List<SysTeam> getSysTeamList();
	
	public List<BaseSysTeam> getBaseSysTeamList();
	 /**
     * 根据条件返回团队信息
     * @param sysTeam
     * @return
     */
    public BaseSysTeam getSysTeamByUserId(Integer userId);
}
