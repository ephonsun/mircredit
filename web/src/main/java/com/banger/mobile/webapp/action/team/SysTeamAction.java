package com.banger.mobile.webapp.action.team;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.Enum.role.EnumSysRole;
import com.banger.mobile.domain.model.base.system.BaseSysTeam;
import com.banger.mobile.domain.model.base.system.BaseSysTeamUser;
import com.banger.mobile.domain.model.dept.DeptUserBean;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.system.SysTeam;
import com.banger.mobile.domain.model.system.SysTeamInfo;
import com.banger.mobile.domain.model.system.SysTeamUser;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.dept.DeptService;
import com.banger.mobile.facade.funcAuth.FuncAuthService;
import com.banger.mobile.facade.log.OpeventLoginLogService;
import com.banger.mobile.facade.menuAuth.MenuAuthService;
import com.banger.mobile.facade.role.SysRoleService;
import com.banger.mobile.facade.rolemember.SysRoleMemberService;
import com.banger.mobile.facade.specialDataAuth.SpecialDataAuthService;
import com.banger.mobile.facade.system.team.SysTeamService;
import com.banger.mobile.facade.system.team.SysTeamUserService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * 团队管理
 * 
 * @author linkin
 * @version $Id: SysTeamAction.java, v 0.1 2016-1-4 下午2:40:38 linkin Exp $
 */
public class SysTeamAction extends BaseAction {

	private SysRoleService sysRoleService;// 角色service
	private SysRoleMemberService sysRoleMemberService;// 角色service
	private SysTeamService sysTeamService;// 团队service
	private SysTeamUserService sysTeamUserService;// 团队成员service
	private FuncAuthService funcAuthService; // 功能权限service
	private MenuAuthService menuAuthService; // 菜单权限service
	private DeptService deptService; // 机构service
	private SysUserService sysUserService;
	private DeptFacadeService deptFacadeService;
	private SpecialDataAuthService specialDataAuthService;// 特殊数据权限
	private String userIds;
	private PageUtil<SysTeam> teamPage;// 角色分页对象
	private SysTeam sysTeam;// 团队实体
	private SysTeamUser sysTeamUser;// 团队成员实体
	private String saveFlag;// 保存标识
	private String teamName;
	private Integer flag;
	private String oldName;
	private String roleIdDel;
	private OpeventLoginLogService opeventLoginLogService; // 操作日志service

	private JSONArray fancAuthTreeJson; // 功能权限树json
	private JSONArray menuAuthTreeJson; // 菜单权限树json
	private JSONArray dataAuthTreeJson; // 特殊数据权限树json
	private String funcIds; // 功能id集合

	private List<Integer> nodeIds;
	private List<Integer> menuNodeIds;
	private List<Integer> dataNodeIds;

	private Integer adminOrDeptAdimn;

	// input
	private SysDept dept; // 机构对象 新增 修改
	private int deptId;
	private String deptParentName;
	private int flagInt;
	private DeptUserBean deptUserBean; // 列表对象
	// output
	private PageUtil<DeptUserBean> deptUserList; // 活动分页对象
	private JSONArray deptJson; // 机构树json
	private String deptName; // 页面输出deptName
	private int totalAmount; // 总记录数
	private int root; // 根节点
	private List<DeptUserBean> checkdeptUserList; // 活动分页对象
	private List<Integer> usedUserIdList; // 已参加团队主管编号
	private List<Integer> checkUserList; // 已选团队用户编号

	/**
	 * 
	 */
	private static final long serialVersionUID = 4121433365559821260L;

	public String showSysTeamPage() {
		try {
			String[] roleIds = this.getLoginInfo().getRoles();
			if (roleIds.length > 0) {
				for (String id : roleIds) {
					if (id.equals("1")) {
						adminOrDeptAdimn = 1;
						break;
					}
					if (id.equals("2")) {
						adminOrDeptAdimn = 2;
						break;
					}
				}
			}
			teamPage = sysTeamService.getSysTeamPage(sysTeam, this.getPage());
			return SUCCESS;
		} catch (Exception e) {
			log.error("showSysRolePage action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 编辑角色
	 * 
	 * @return
	 */
	public String updateSysTeam() {
		try {
			if (flag != null && flag == 1) {// 跳转编辑页面
				List<BaseSysTeamUser> list = new ArrayList<BaseSysTeamUser>();
				if (sysTeamUser.getTeamUserId() != null) {
					BaseSysTeamUser baseSysTeamUser = sysTeamUserService
							.getUserListById(sysTeamUser.getTeamUserId());
					sysTeam.setTeamId(baseSysTeamUser.getTeamId());
				}
				list = sysTeamUserService.getUserListByTeamId(sysTeam
						.getTeamId());
				if (list.size() > 0) {
					List<SysTeamInfo> teamList = sysTeamService
							.getSysTeamById(sysTeam.getTeamId());
					sysTeam.setTeamName(teamList.get(0).getTeamName());
					sysTeam.setTeamId(teamList.get(0).getTeamId());
				
					String userIdss = "";
					for (BaseSysTeamUser stu : list) {
						userIdss = userIdss + "," + stu.getUserId();
					}
					sysTeamUser.setTeamUserId(list.get(0).getTeamUserId());
					
					if (userIds != null && !userIds.equals("1")) {
						userIdss = userIdss+","+userIds;
						checkdeptUserList = deptService
								.getDeptUserByIds(userIdss.substring(1));
					} else {
						checkdeptUserList = deptService.getDeptUserByIds(userIdss
								.substring(1));
					}
					userIds = userIdss.substring(1);
				}
				return "toUpdataPage";
			} else {// 提交编辑信息
				BaseSysTeam bsysTeam = new BaseSysTeam();
				bsysTeam.setCreateUser(this.getLoginInfo().getUserId());
				bsysTeam.setUpdateUser(this.getLoginInfo().getUserId());
				bsysTeam.setIsDel(0);
				bsysTeam.setTeamId(sysTeam.getTeamId());
				bsysTeam.setTeamName(sysTeam.getTeamName());
				sysTeamService.updateSysTeam(bsysTeam);
				// 修改功能权限
				if (userIds != null && !userIds.equals("1")) {
					List<BaseSysTeamUser> list = sysTeamUserService
							.getUserListByTeamId(sysTeam.getTeamId());
					for (BaseSysTeamUser stu : list) {
						SysTeamUser sysTeamUser = new SysTeamUser();
						sysTeamUser.setUserId(stu.getUserId());
						sysTeamUser.setTeamId(sysTeam.getTeamId());
						sysTeamUserService.deleteSysTeamUser(sysTeamUser);
					}
					for (String userid : userIds.split(",")) {
						SysTeamUser sysTeamUser = new SysTeamUser();
						sysTeamUser.setUserId(Integer.parseInt(userid));
						sysTeamUser.setTeamId(sysTeam.getTeamId());
						sysTeamUser.setCreateUser(this.getLoginInfo()
								.getUserId());
						sysTeamUser.setUpdateUser(this.getLoginInfo()
								.getUserId());
						sysTeamUserService.insertSysTeamUser(sysTeamUser);
					}

				}

				opeventLoginLogService.addLog(10,
						EnumSysRole.MODEL_ACTION_UPDATE.getValue(), 1, 1);
				return SUCCESS;
			}
		} catch (Exception e) {
			// e.printStackTrace();
			opeventLoginLogService.addLog(10,
					EnumSysRole.MODEL_ACTION_UPDATE.getValue(), 1, 0);
			log.error("updateSysRole action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 分页
	 */
	public String getSysTeamPage() {
		try {
			String[] roleIds = this.getLoginInfo().getRoles();
			if (roleIds.length > 0) {
				for (String id : roleIds) {
					if (id.equals("1")) {
						adminOrDeptAdimn = 1;
						break;
					}
					if (id.equals("2")) {
						adminOrDeptAdimn = 2;
						break;
					}
				}
			}
			teamPage = sysTeamService.getSysTeamPage(sysTeam, this.getPage());
			return SUCCESS;
		} catch (Exception e) {
			log.error("showSysTeamPage action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 添加团队
	 * 
	 * @return
	 */
	public String saveSysTeam() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		if (StringUtil.isEmpty(teamName) && StringUtil.isNotEmpty(userIds)) {
			if(userIds.startsWith(",")){
				userIds=userIds.substring(1);
			}
			checkdeptUserList = deptService.getDeptUserByIds(userIds);
			if (sysTeam!=null&&sysTeam.getTeamName() != null) {
				try{
				sysTeam.setTeamName(new String(sysTeam.getTeamName().getBytes(
						"ISO-8859-1"), "UTF-8"));
				
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			return "toAddPage";
		}
		if (StringUtil.isNotEmpty(teamName) || StringUtil.isNotEmpty(userIds)) {
			PrintWriter out = null;
			List<Integer>  idlist=	sysRoleMemberService.getRoleIdByUserIds(userIds);
			boolean isExitSix = false;
			for(Integer id:idlist){
				if(id==6){
					isExitSix =true;
				}
			}
			if(!isExitSix){
				try{
				out = response.getWriter();
				out.print("没有后台人员");
				return "";
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			//2.团队主管和客户经理是否有 并且是否还未加入任何团队
			SysTeam st = new SysTeam();
			st.setTeamName(teamName);
			st.setCreateUser(this.getLoginInfo().getUserId());
			st.setUpdateUser(this.getLoginInfo().getUserId());
			sysTeamService.insertSysTeam(st);
			BaseSysTeam team = sysTeamService.getSysTeamByName(st);
			for (String userId : userIds.split(",")) {
				SysTeamUser stu = new SysTeamUser();
				stu.setCreateUser(this.getLoginInfo().getUserId());
				stu.setUpdateUser(this.getLoginInfo().getUserId());
				stu.setUserId(Integer.parseInt(userId));
				stu.setTeamId(team.getTeamId());
				sysTeamUserService.insertSysTeamUser(stu);
			}
			return "saveTeam";
		}

		return "toAddPage";
	}
	/**
	 * 添加团队
	 * 
	 * @return
	 */
	public void saveSysTeamUser() {
		try {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
	
		// 添加团队之前判断
		// 1.后台管理是否有
		PrintWriter out = null;
		List<Integer> idlist = sysRoleMemberService.getRoleIdByUserIds(userIds);
		boolean isExitBG = false;//后台人员
		boolean isExitMG = false;//团队主管
		boolean isExitCM = false;//客户经理
		for (Integer id : idlist) {
			if (id == 6) {
				isExitBG = true;
			}
			if(id==5){
				if(isExitMG){
					out = response.getWriter();
					out.print("团队主管只能有一个");
					return;
				}
				isExitMG =true;
			}
			if(id==7){
				isExitCM=true;
			}
		}
		if (!isExitBG) {
				out = response.getWriter();
				out.print("没有后台人员");
				return;
		}
		// 2.团队主管和客户经理是否有 并且是否还未加入任何团队
		if (!isExitMG) {
			out = response.getWriter();
			out.print("没有团队主管");
			return;
		}
		if (!isExitCM) {
				out = response.getWriter();
				out.print("没有客户经理");
				return;
		}
		if(!StringUtils.isNotEmpty(teamName)){
			out = response.getWriter();
			out.print("团队名不能为空");
			return;
		}
		if(sysTeam.getTeamId()!=null &&sysTeam.getTeamId()!=0){
			BaseSysTeam bsysTeam = new BaseSysTeam();
			bsysTeam.setCreateUser(this.getLoginInfo().getUserId());
			bsysTeam.setUpdateUser(this.getLoginInfo().getUserId());
			bsysTeam.setIsDel(0);
			bsysTeam.setTeamId(sysTeam.getTeamId());
			bsysTeam.setTeamName(teamName);
			sysTeamService.updateSysTeam(bsysTeam);
			// 修改功能权限
			if (userIds != null && !userIds.equals("1")) {
				List<BaseSysTeamUser> list = sysTeamUserService
						.getUserListByTeamId(sysTeam.getTeamId());
				for (BaseSysTeamUser stu : list) {
					SysTeamUser sysTeamUser = new SysTeamUser();
					sysTeamUser.setUserId(stu.getUserId());
					sysTeamUser.setTeamId(sysTeam.getTeamId());
					sysTeamUserService.deleteSysTeamUser(sysTeamUser);
				}
				for (String userid : userIds.split(",")) {
					SysTeamUser sysTeamUser = new SysTeamUser();
					sysTeamUser.setUserId(Integer.parseInt(userid));
					sysTeamUser.setTeamId(sysTeam.getTeamId());
					sysTeamUser.setCreateUser(this.getLoginInfo()
							.getUserId());
					sysTeamUser.setUpdateUser(this.getLoginInfo()
							.getUserId());
					sysTeamUserService.insertSysTeamUser(sysTeamUser);
				}
			}
		}else{
			SysTeam st = new SysTeam();
			st.setTeamName(teamName);
			st.setCreateUser(this.getLoginInfo().getUserId());
			st.setUpdateUser(this.getLoginInfo().getUserId());
			sysTeamService.insertSysTeam(st);
			BaseSysTeam team = sysTeamService.getSysTeamByName(st);
			for (String userId : userIds.split(",")) {
				SysTeamUser stu = new SysTeamUser();
				stu.setCreateUser(this.getLoginInfo().getUserId());
				stu.setUpdateUser(this.getLoginInfo().getUserId());
				stu.setUserId(Integer.parseInt(userId));
				stu.setTeamId(team.getTeamId());
				sysTeamUserService.insertSysTeamUser(stu);
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除验证是否在使用,执行删除
	 */
	public void validateDel() {
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			BaseSysTeam bsysTeam = new BaseSysTeam();
			bsysTeam.setCreateUser(this.getLoginInfo().getUserId());
			bsysTeam.setUpdateUser(this.getLoginInfo().getUserId());
			bsysTeam.setIsDel(1);
			bsysTeam.setTeamId(sysTeam.getTeamId());
			sysTeamService.updateSysTeam(bsysTeam);
			opeventLoginLogService.addLog(10,
					EnumSysRole.TEAM_ACTION_DEL.getValue(), 1, 1);
		} catch (Exception e) {
			opeventLoginLogService.addLog(10,
					EnumSysRole.TEAM_ACTION_DEL.getValue(), 1, 0);
			out.print(EnumSysRole.DEL_FAIL.getValue());
		}
	}

	/**
	 * 删除验证是否在使用,执行删除
	 */
	public void validateDelTeamUser() {
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			sysTeamUserService.deleteSysTeamUser(sysTeamUser);

			opeventLoginLogService.addLog(10,
					EnumSysRole.TEAMUSER_ACTION_DEL.getValue(), 1, 1);
		} catch (Exception e) {
			opeventLoginLogService.addLog(10,
					EnumSysRole.TEAMUSER_ACTION_DEL.getValue(), 1, 0);
			out.print(EnumSysRole.DEL_FAIL.getValue());
		}
	}
	/**
	 * 搜索用户
	 * 
	 * @return
	 */
	public String getUserPageList() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userName", request.getParameter("userName"));
			map.put("deptName", request.getParameter("deptName"));
			deptUserList = conversionRoleName(deptService.getDeptSubsUserPage(
					map, this.getPage()));
			totalAmount = deptUserList.getPage().getTotalRowsAmount();
			//已选
			String tuserids = request.getParameter("teamUserIds");
			checkUserList = new ArrayList<Integer>();
			if(StringUtils.isNotEmpty(tuserids)){
				for (String userId : tuserids.split(",")) {
					checkUserList.add(Integer.parseInt(userId));
				}
			}
			if (userIds != null) {
				checkdeptUserList = deptService.getDeptUserByIds(userIds);
			}
			usedUserIdList = sysTeamUserService.getUsedUserList();
			if (sysTeamUser != null) {
				sysTeamUser.setUpdateUser(1);
			}
			if (sysTeam != null) {
				String tname = new String(sysTeam.getTeamName().getBytes(
						"ISO-8859-1"), "UTF-8");
				sysTeam.setTeamName(tname);
			}
			userIds =request.getParameter("teamUserIds");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("showDeptList action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 机构树显示
	 * 
	 * @return
	 */
	public String showDeptList() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userName", request.getParameter("userName"));
			map.put("deptName", request.getParameter("deptName"));
			deptUserList = conversionRoleName(deptService.getDeptSubsUserPage(
					map, this.getPage()));
			totalAmount = deptUserList.getPage().getTotalRowsAmount();
			//已选
			String tuserids = request.getParameter("teamUserIds");
			checkUserList = new ArrayList<Integer>();
			if(StringUtils.isNotEmpty(tuserids)){
				for (String userId : tuserids.split(",")) {
					checkUserList.add(Integer.parseInt(userId));
				}
			}
			if (userIds != null) {
				checkdeptUserList = deptService.getDeptUserByIds(userIds);
				
			}
			//已加入团队的客户经理和团队主管
			usedUserIdList = sysTeamUserService.getUsedUserList();
			if (sysTeamUser != null) {
				sysTeamUser.setUpdateUser(1);
			}
			if (sysTeam != null) {
				String tname = new String(sysTeam.getTeamName().getBytes(
						"ISO-8859-1"), "UTF-8");
				sysTeam.setTeamName(tname);
			}
			userIds =request.getParameter("teamUserIds");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("showDeptList action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 机构树显示
	 * 
	 * @return
	 */
	public String showUserDeptList() {
		try {
			checkdeptUserList = deptService.getDeptUserByIds(userIds);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("showDeptList action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 机构树显示
	 * 
	 * @return
	 */
	public String getTeamUserPageList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			deptUserList = conversionRoleName(deptService.getDeptSubsUserPage(
					map, this.getPage()));
			totalAmount = deptUserList.getPage().getTotalRowsAmount();
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("showDeptList action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 组织机构对角色名称进行增加
	 * 
	 * @param beans
	 * @return
	 */
	public PageUtil<DeptUserBean> conversionRoleName(
			PageUtil<DeptUserBean> beans) {
		try {
			List<DeptUserBean> bean = new ArrayList<DeptUserBean>();
			DeptUserBean item = null;
			String userIds = "";
			for (int i = 0; i < beans.getItems().size(); i++) {
				userIds += beans.getItems().get(i).getUserId() + ",";
			}
			if (userIds.length() > 0)
				userIds = userIds.substring(0, userIds.length() - 1);
			if (!userIds.equals("")) {
				Map<Integer, String> map = sysUserService
						.getRoleNamesByUserId(userIds);
				for (int i = 0; i < beans.getItems().size(); i++) {
					item = beans.getItems().get(i);
					if (map.containsKey(item.getUserId()))
						item.setRoleNames(map.get(item.getUserId()));
					bean.add(item);
				}
				beans.setItems(bean);
			}
		} catch (Exception e) {
			log.error("conversionRoleName action error:" + e.getMessage());
		}
		return beans;
	}

	/**
	 * 验证角色是否存在
	 */
	public void validateTeam() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter out = response.getWriter();
			JSONObject jso = new JSONObject();
			BaseSysTeam team = sysTeamService.getSysTeamByName(sysTeam);

			/*
			 * if (oldName != null) {//修改验证 if
			 * (!oldName.equals(sysTeam.getTeamName())) { team = } } else
			 * {//添加验证 role = sysRoleService.getSysRoleById(sysRole); }
			 */
			if (team != null) {
				if (sysTeam.getTeamId() != null) {
					List<SysTeamInfo> list = sysTeamService
							.getSysTeamById(sysTeam.getTeamId());
					if (list.size() > 0
							&& !list.get(0).getTeamName()
									.equals(team.getTeamName())) {
						jso.put("team_name",
								EnumSysRole.VALIDATION_TEAM_NAME.getValue());
					}
				} else {
					jso.put("team_name",
							EnumSysRole.VALIDATION_TEAM_NAME.getValue());
				}
			}
			out.print(jso.toString());
			out.close();
		} catch (IOException e) {
		}
	}

	public SysRoleService getSysRoleService() {
		return sysRoleService;
	}

	public void setSysRoleService(SysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}

	public FuncAuthService getFuncAuthService() {
		return funcAuthService;
	}

	public void setFuncAuthService(FuncAuthService funcAuthService) {
		this.funcAuthService = funcAuthService;
	}

	public MenuAuthService getMenuAuthService() {
		return menuAuthService;
	}

	public void setMenuAuthService(MenuAuthService menuAuthService) {
		this.menuAuthService = menuAuthService;
	}

	public SpecialDataAuthService getSpecialDataAuthService() {
		return specialDataAuthService;
	}

	public void setSpecialDataAuthService(
			SpecialDataAuthService specialDataAuthService) {
		this.specialDataAuthService = specialDataAuthService;
	}

	public String getSaveFlag() {
		return saveFlag;
	}

	public void setSaveFlag(String saveFlag) {
		this.saveFlag = saveFlag;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public String getRoleIdDel() {
		return roleIdDel;
	}

	public void setRoleIdDel(String roleIdDel) {
		this.roleIdDel = roleIdDel;
	}

	public OpeventLoginLogService getOpeventLoginLogService() {
		return opeventLoginLogService;
	}

	public void setOpeventLoginLogService(
			OpeventLoginLogService opeventLoginLogService) {
		this.opeventLoginLogService = opeventLoginLogService;
	}

	public JSONArray getFancAuthTreeJson() {
		return fancAuthTreeJson;
	}

	public void setFancAuthTreeJson(JSONArray fancAuthTreeJson) {
		this.fancAuthTreeJson = fancAuthTreeJson;
	}

	public JSONArray getMenuAuthTreeJson() {
		return menuAuthTreeJson;
	}

	public void setMenuAuthTreeJson(JSONArray menuAuthTreeJson) {
		this.menuAuthTreeJson = menuAuthTreeJson;
	}

	public JSONArray getDataAuthTreeJson() {
		return dataAuthTreeJson;
	}

	public void setDataAuthTreeJson(JSONArray dataAuthTreeJson) {
		this.dataAuthTreeJson = dataAuthTreeJson;
	}

	public String getFuncIds() {
		return funcIds;
	}

	public void setFuncIds(String funcIds) {
		this.funcIds = funcIds;
	}

	public List<Integer> getNodeIds() {
		return nodeIds;
	}

	public void setNodeIds(List<Integer> nodeIds) {
		this.nodeIds = nodeIds;
	}

	public List<Integer> getMenuNodeIds() {
		return menuNodeIds;
	}

	public void setMenuNodeIds(List<Integer> menuNodeIds) {
		this.menuNodeIds = menuNodeIds;
	}

	public List<Integer> getDataNodeIds() {
		return dataNodeIds;
	}

	public void setDataNodeIds(List<Integer> dataNodeIds) {
		this.dataNodeIds = dataNodeIds;
	}

	public Integer getAdminOrDeptAdimn() {
		return adminOrDeptAdimn;
	}

	public void setAdminOrDeptAdimn(Integer adminOrDeptAdimn) {
		this.adminOrDeptAdimn = adminOrDeptAdimn;
	}

	public SysTeamService getSysTeamService() {
		return sysTeamService;
	}

	public void setSysTeamService(SysTeamService sysTeamService) {
		this.sysTeamService = sysTeamService;
	}

	public PageUtil<SysTeam> getTeamPage() {
		return teamPage;
	}

	public void setTeamPage(PageUtil<SysTeam> teamPage) {
		this.teamPage = teamPage;
	}

	public SysTeam getSysTeam() {
		return sysTeam;
	}

	public void setSysTeam(SysTeam sysTeam) {
		this.sysTeam = sysTeam;
	}

	public DeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public SysUserService getSysUserService() {
		return sysUserService;
	}

	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	public DeptFacadeService getDeptFacadeService() {
		return deptFacadeService;
	}

	public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
		this.deptFacadeService = deptFacadeService;
	}

	public SysDept getDept() {
		return dept;
	}

	public void setDept(SysDept dept) {
		this.dept = dept;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getDeptParentName() {
		return deptParentName;
	}

	public void setDeptParentName(String deptParentName) {
		this.deptParentName = deptParentName;
	}

	public int getFlagInt() {
		return flagInt;
	}

	public void setFlagInt(int flagInt) {
		this.flagInt = flagInt;
	}

	public DeptUserBean getDeptUserBean() {
		return deptUserBean;
	}

	public void setDeptUserBean(DeptUserBean deptUserBean) {
		this.deptUserBean = deptUserBean;
	}

	public PageUtil<DeptUserBean> getDeptUserList() {
		return deptUserList;
	}

	public void setDeptUserList(PageUtil<DeptUserBean> deptUserList) {
		this.deptUserList = deptUserList;
	}

	public JSONArray getDeptJson() {
		return deptJson;
	}

	public void setDeptJson(JSONArray deptJson) {
		this.deptJson = deptJson;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getRoot() {
		return root;
	}

	public void setRoot(int root) {
		this.root = root;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public List<DeptUserBean> getCheckdeptUserList() {
		return checkdeptUserList;
	}

	public void setCheckdeptUserList(List<DeptUserBean> checkdeptUserList) {
		this.checkdeptUserList = checkdeptUserList;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public SysTeamUserService getSysTeamUserService() {
		return sysTeamUserService;
	}

	public void setSysTeamUserService(SysTeamUserService sysTeamUserService) {
		this.sysTeamUserService = sysTeamUserService;
	}

	public SysTeamUser getSysTeamUser() {
		return sysTeamUser;
	}

	public void setSysTeamUser(SysTeamUser sysTeamUser) {
		this.sysTeamUser = sysTeamUser;
	}

	public List<Integer> getUsedUserIdList() {
		return usedUserIdList;
	}

	public void setUsedUserIdList(List<Integer> usedUserIdList) {
		this.usedUserIdList = usedUserIdList;
	}

	public List<Integer> getCheckUserList() {
		return checkUserList;
	}

	public void setCheckUserList(List<Integer> checkUserList) {
		this.checkUserList = checkUserList;
	}

	public SysRoleMemberService getSysRoleMemberService() {
		return sysRoleMemberService;
	}

	public void setSysRoleMemberService(SysRoleMemberService sysRoleMemberService) {
		this.sysRoleMemberService = sysRoleMemberService;
	}

}
