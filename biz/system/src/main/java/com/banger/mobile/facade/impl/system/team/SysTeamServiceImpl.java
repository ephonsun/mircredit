package com.banger.mobile.facade.impl.system.team;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.dept.DeptDao;
import com.banger.mobile.dao.system.SysTeamDao;
import com.banger.mobile.domain.model.base.system.BaseSysTeam;
import com.banger.mobile.domain.model.system.SysTeam;
import com.banger.mobile.domain.model.system.SysTeamInfo;
import com.banger.mobile.facade.system.team.SysTeamService;
import com.banger.mobile.util.StringUtil;

public class SysTeamServiceImpl implements SysTeamService {
	private SysTeamDao sysTeamDao;
	private DeptDao deptDao;

	public DeptDao getDeptDao() {
		return deptDao;
	}

	public void setDeptDao(DeptDao deptDao) {
		this.deptDao = deptDao;
	}

	public SysTeamDao getSysTeamDao() {
		return sysTeamDao;
	}

	public void setSysTeamDao(SysTeamDao sysTeamDao) {
		this.sysTeamDao = sysTeamDao;
	}

	@Override
	public PageUtil<SysTeam> getSysTeamPage(SysTeam sysTeam, Page page) {
		Map<String,Object> map=new HashMap<String, Object>();
		if(sysTeam!=null){
			if(sysTeam.getTeamName()!=null&&!sysTeam.getTeamName().equals("")){
				map.put("teamName", StringUtil.ReplaceSQLChar(sysTeam.getTeamName()));
			}
		}
		PageUtil<BaseSysTeam> pub =  sysTeamDao.getSysTeamPage(map, page);
		PageUtil<SysTeam> pus = new PageUtil<SysTeam>(null, null);
		List<SysTeam> items = new ArrayList<SysTeam>();
		for(BaseSysTeam bTeam:pub.getItems()){
			List<SysTeamInfo>  lists =sysTeamDao.getTeamInfoList(bTeam.getTeamId());
			SysTeam st = new SysTeam();
			for(SysTeamInfo info:lists){
				st.setTeamId(bTeam.getTeamId());
				st.setTeamName(bTeam.getTeamName());
				if(info.getRoleName().equals("客户经理")){
					if(StringUtil.isNotEmpty(st.getClientManageUserNames())){
						st.setClientManageUserNames(st.getClientManageUserNames()+","+info.getUserName());
					}else{
						st.setClientManageUserNames(info.getUserName());
					}
				}else if(info.getRoleName().equals("后台人员")){
					if(StringUtil.isNotEmpty(st.getBackgroundUserNames())){
						st.setBackgroundUserNames(st.getBackgroundUserNames()+","+info.getUserName());
					}else{
						st.setBackgroundUserNames(info.getUserName());
					}
				}else if(info.getRoleName().equals("团队主管")){
					if(StringUtil.isNotEmpty(st.getTeamLeaderUserName())){
						st.setTeamLeaderUserName(st.getTeamLeaderUserName()+","+info.getUserName());
					}else{
						st.setTeamLeaderUserName(info.getUserName());
					}
				}
			}
			if(sysTeam!=null&&StringUtil.isNotEmpty(sysTeam.getUserName())){
				String userName = sysTeam.getUserName();
				if((StringUtil.isNotEmpty(st.getBackgroundUserNames())&&st.getBackgroundUserNames().contains(userName))||
						((StringUtil.isNotEmpty(st.getTeamLeaderUserName())&&st.getTeamLeaderUserName().contains(userName)))||
						((StringUtil.isNotEmpty(st.getClientManageUserNames())&&st.getClientManageUserNames().contains(userName)))){
					items.add(st);
				}
			}else{
				items.add(st);
			}
		}
		pus.setItems(items);
		return pus;
	}

	@Override
	public void updateSysTeam(BaseSysTeam systeam) {
		systeam.setUpdateDate(new Date());
		//祛除前后空格
		if(StringUtil.isNotEmpty(systeam.getTeamName())){
			systeam.setTeamName(systeam.getTeamName().trim());
		}
		sysTeamDao.updateSysTeam(systeam);
	}

	@Override
	public BaseSysTeam getSysTeamByName(SysTeam sysTeam) {
		// TODO Auto-generated method stub
		return sysTeamDao.getSysTeamByName(sysTeam.getTeamName());
	}
	@Override
	public BaseSysTeam getSysTeamByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return sysTeamDao.getSysTeamByUserId(userId);
	}
	@Override
	public void insertSysTeam(SysTeam sysTeam) {
		// TODO Auto-generated method stub
		BaseSysTeam sbt = new BaseSysTeam();
		sbt.setCreateDate(new Date());
		sbt.setUpdateDate(new Date());
		sbt.setIsDel(0);
		sbt.setTeamName(sysTeam.getTeamName());
		sbt.setCreateUser(sysTeam.getCreateUser());
		sbt.setUpdateUser(sysTeam.getCreateUser());
		sysTeamDao.addSysTeam(sbt);
	}

	@Override
	public List<SysTeamInfo> getSysTeamById(Integer teamId) {
		return sysTeamDao.getTeamInfoList(teamId);
	}

	@Override
	public List<SysTeam> getSysTeamList() {
		return sysTeamDao.getSysTeamList();
	}

	public List<BaseSysTeam> getBaseSysTeamList(){
		return sysTeamDao.getBaseSysTeamList();
	}
}
