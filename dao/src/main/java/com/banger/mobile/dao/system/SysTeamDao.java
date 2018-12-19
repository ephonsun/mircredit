package com.banger.mobile.dao.system;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.base.system.BaseSysTeam;
import com.banger.mobile.domain.model.system.SysTeam;
import com.banger.mobile.domain.model.system.SysTeamInfo;

public interface SysTeamDao {

	/**
	 * 新增团队
	 * @param sysTeam
	 */
	public void addSysTeam(BaseSysTeam sysTeam);
	
	/**
	 * 删除团队
	 * @param id
	 */
	public void deleteSysTeam(Integer id);
	
	/**
	 * 修改团队
	 * @param sysTeam
	 */
	public void updateSysTeam(BaseSysTeam sysTeam);
	
	/**
	 * 分页查询团队
	 * @param parameters
	 * @param page
	 * @return
	 */
	public PageUtil<BaseSysTeam> getSysTeamPage(Map<String, Object> parameters, Page page);
	/**
	 * 估计teamid查询团队成员
	 * @param teamId
	 * @return
	 */
    public List<SysTeamInfo> getTeamInfoList(Integer teamId);
    
   
    /**
     * 根据条件返回角色信息
     * @param teamName
     * @return
     */
    public BaseSysTeam getSysTeamByName(String teamName);
    /**
     * 根据条件返回角色信息
     * @param teamName
     * @return
     */
    public BaseSysTeam getSysTeamByUserId(Integer userId);
    
	/**
	 * 
	 * @return
	 */
	public List<SysTeam> getSysTeamList();
	
	public List<BaseSysTeam> getBaseSysTeamList() ;
}
