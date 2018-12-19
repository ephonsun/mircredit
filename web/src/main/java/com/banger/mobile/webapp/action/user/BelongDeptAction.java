/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :下属管理...
 * Author     :yangy
 * Create Date:2012-8-18
 */
package com.banger.mobile.webapp.action.user;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.Enum.user.EnumUserType;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.dept.CusBelongToBean;
import com.banger.mobile.domain.model.dept.DeptUserBean;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.dept.UserSubordinateBean;
import com.banger.mobile.domain.model.role.SysRole;
import com.banger.mobile.domain.model.system.CrmCustomerType;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.domain.model.user.SysWorkDelegate;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.dept.DeptService;
import com.banger.mobile.facade.log.OpeventLoginLogService;
import com.banger.mobile.facade.role.SysRoleService;
import com.banger.mobile.facade.system.CrmCustomerTypeService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.facade.user.SysWorkDelegateService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author yangyang
 * @version $Id: BelongDeptAction.java,v 0.1 2012-8-18 下午3:27:25 yangyong Exp $
 */
public class BelongDeptAction extends BaseAction {

	private static final long serialVersionUID = 488778573275089071L;

	private DeptService deptService; // 机构service
	private OpeventLoginLogService opeventLoginLogService; // 操作日志service
	private CrmCustomerTypeService crmCustomerTypeService; // 客户类型Service
	private SysWorkDelegateService sysWorkDelegateService;
	private PageUtil<CrmCustomer> customerList; // 客户列表
	private SysRoleService sysRoleService; // 角色service
	private CrmCustomer crmCustomer = new CrmCustomer(); // 客户实体
	private Map<String, Object> parameterMap = new HashMap<String, Object>(); // 客户查询MAP
	private SysUserService sysUserService;
	private Boolean isInChargeof; // 是否是业务主管
	private DeptUserBean deptUserBean; // 列表对象
	private PageUtil<UserSubordinateBean> deptUserList; // 活动分页对象
	private CrmCustomerService crmCustomerService; // 客户Service
	private int deptId;
	private JSONArray deptJson; // 机构树json
	private String deptName; // 页面输出deptName
	private int totalAmount; // 总记录数
	private int flag; // 包含下属标识
	private SysDept dept; // 机构对象 新增 修改
	private int flagInt;
	private DeptFacadeService deptFacadeService; // 机构Service
	private String isSelectCus;
	private Integer recordCount; // 总客户记录数
	private List<CrmCustomerType> cusTypelist; // 客户类型集合
	private Integer recordId; // 客户归属id
	private String recordName; // 客户归属name
	private List<SysRole> sysRoleList;
	private List<SysWorkDelegate> sysWorkDelegateList;

	public Boolean getIsInChargeof() {
		return isInChargeof;
	}

	public void setIsInChargeof(Boolean isInChargeof) {
		this.isInChargeof = isInChargeof;
	}

	public List<SysWorkDelegate> getSysWorkDelegateList() {
		return sysWorkDelegateList;
	}

	public void setSysWorkDelegateList(List<SysWorkDelegate> sysWorkDelegateList) {
		this.sysWorkDelegateList = sysWorkDelegateList;
	}

	public SysWorkDelegateService getSysWorkDelegateService() {
		return sysWorkDelegateService;
	}

	public void setSysWorkDelegateService(
			SysWorkDelegateService sysWorkDelegateService) {
		this.sysWorkDelegateService = sysWorkDelegateService;
	}

	public List<SysRole> getSysRoleList() {
		return sysRoleList;
	}

	public void setSysRoleList(List<SysRole> sysRoleList) {
		this.sysRoleList = sysRoleList;
	}

	public String getIsSelectCus() {
		return isSelectCus;
	}

	public void setIsSelectCus(String isSelectCus) {
		this.isSelectCus = isSelectCus;
	}

	public Integer getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public String getRecordName() {
		return recordName;
	}

	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}

	public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
		this.deptFacadeService = deptFacadeService;
	}

	public PageUtil<CrmCustomer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(PageUtil<CrmCustomer> customerList) {
		this.customerList = customerList;
	}

	public CrmCustomer getCrmCustomer() {
		return crmCustomer;
	}

	public void setCrmCustomer(CrmCustomer crmCustomer) {
		this.crmCustomer = crmCustomer;
	}

	public Map<String, Object> getParameterMap() {
		return parameterMap;
	}

	public void setParameterMap(Map<String, Object> parameterMap) {
		this.parameterMap = parameterMap;
	}

	public List<CrmCustomerType> getCusTypelist() {
		return cusTypelist;
	}

	public void setCusTypelist(List<CrmCustomerType> cusTypelist) {
		this.cusTypelist = cusTypelist;
	}

	public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
		this.crmCustomerService = crmCustomerService;
	}

	public void setCrmCustomerTypeService(
			CrmCustomerTypeService crmCustomerTypeService) {
		this.crmCustomerTypeService = crmCustomerTypeService;
	}

	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	public void setOpeventLoginLogService(
			OpeventLoginLogService opeventLoginLogService) {
		this.opeventLoginLogService = opeventLoginLogService;
	}

	public SysDept getDept() {
		return dept;
	}

	public void setDept(SysDept dept) {
		this.dept = dept;
	}

	public int getFlagInt() {
		return flagInt;
	}

	public void setFlagInt(int flagInt) {
		this.flagInt = flagInt;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public DeptUserBean getDeptUserBean() {
		return deptUserBean;
	}

	public void setDeptUserBean(DeptUserBean deptUserBean) {
		this.deptUserBean = deptUserBean;
	}

	public PageUtil<UserSubordinateBean> getDeptUserList() {
		return deptUserList;
	}

	public void setDeptUserList(PageUtil<UserSubordinateBean> deptUserList) {
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

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public void setSysRoleService(SysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}

	/**
	 * 机构树显示
	 * 
	 * @return
	 */
	public String showDeptList() {
		List<SysRole> tempList = sysRoleService.getAllRoleName();
		sysRoleList = new ArrayList<SysRole>();
		// 删除系统管理员和机构系统管理员后将数据放入sysRoleList
		for (SysRole sr : tempList) {
			if (!sr.getRoleId().equals(1) && !sr.getRoleId().equals(2)) {
				sysRoleList.add(sr);
			}
		}
		try {
			deptJson = deptFacadeService.getBusinessManagerDeptJsonAddRoot();
			String codes = "";
			for (int i = deptJson.size() - 1; i >= 0; i--) {
				JSONObject obj = deptJson.getJSONObject(i);
				if (obj.get("pId").equals(0)) {// 包含虚拟根节点
					codes = (String) obj.get("searchCode");
					String[] searchCodes = codes.split(",");
					Map<String, Object> map = new HashMap<String, Object>();
					String deptCodes = "";
					for (String deptSearchCode : searchCodes) {
						deptCodes += "DEPT_SEARCH_CODE like" + " " + "'"
								+ deptSearchCode + "%" + "'" + " " + "or" + " ";
					}
					deptCodes = "( "
							+ deptCodes.substring(0,
									deptCodes.lastIndexOf("or")) + " )";
					map.put("searchMany", deptCodes);
					map.put("isActived", 1);
					map.put("roleMemberId", 2);
					map.put("userId", this.getLoginInfo().getUserId());
					deptUserList = conversionRoleName(deptService
							.getSubsUserSubordinateList(map, this.getPage()));
					totalAmount = deptUserList.getPage().getTotalRowsAmount();
					break;
				} else {// 根节点不是虚拟节点
					if (obj.get("pId").equals(2)) {
						deptId = (Integer) obj.get("id");
						Map<String, Object> parameterMap = new HashMap<String, Object>();
						parameterMap.put("deptId", deptId);
						parameterMap.put("isActived", 1);
						parameterMap.put("roleMemberId", 2);
						parameterMap.put("userId", this.getLoginInfo()
								.getUserId());
						deptUserList = conversionRoleName(deptService
								.getUserSubordinatePage(parameterMap,
										this.getPage()));
						totalAmount = deptUserList.getPage()
								.getTotalRowsAmount();// 记录的总数
						break;
					}

				}
			}
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("isCancel", 1);
			sysWorkDelegateList = sysWorkDelegateService
					.getSysWorkDelegateList(parameters);
			return SUCCESS;
		} catch (Exception e) {
			log.error("showDeptList action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 工作托管页面初始化
	 * 
	 * @return
	 */
	public String toWorkDelegatePage() {
		SysUser user = sysUserService.getSysUserById(Integer
				.parseInt(this.request.getParameter("userId")));
		request.setAttribute("userId", user.getUserId());
		request.setAttribute("userName", user.getUserName());
		JSONArray array = this.toUserOrDeptJson(user.getUserId());
		deptJson = array;
		return SUCCESS;
	}

	/**
	 * 判断用户是否是业务主管
	 * 
	 * @return
	 */
	public String checkUserDepartment() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			Integer shareUserId = Integer.parseInt(request
					.getParameter("shareUserId"));
			SysUser sysUser = sysUserService.getSysUserById(shareUserId);
			SysDept sysDept = deptService.getDeptById(shareUserId);
			Integer flag = 0;
			if (sysDept != null) {
				out.print(EnumUserType.DEPT_NOT_SUB.getValue());
			} else {
				String[] roles = sysUserService.getRoleIds(sysUser.getUserId())
						.split(",");
				for (String string : roles) {
					if (string.equals("3"))
						flag++;
				}
				if (flag != 0)
					out.print(sysUser.getUserName()
							+ EnumUserType.USER_IS_BUSS.getValue());
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	/**
	 * 判断托管的对象是否可以托管
	 * 
	 * @return
	 */
	public String valationWorkDelegate() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			Integer shareUserId = Integer.parseInt(request
					.getParameter("shareUserId"));
			SysUser sysUser = sysUserService.getSysUserById(shareUserId);
			SysDept sysDept = deptService.getDeptById(shareUserId);
			if (sysDept != null) {
				out.print(EnumUserType.DEPT_NOT_SUB.getValue());
			} else {
				if (sysUser != null) {
					String[] roles = sysUserService.getRoleIds(
							sysUser.getUserId()).split(",");
					Integer flag = 0;
					for (String string : roles) {
						if (string.equals("3"))
							flag++;
					}
					if (flag != 0)
						out.print(sysUser.getUserName()
								+ EnumUserType.USER_IS_BUSS.getValue());

					Integer userId = Integer.parseInt(request
							.getParameter("userId"));
					Map<String, Object> parameters = new HashMap<String, Object>();
					parameters.put("userId", userId);
					List<SysWorkDelegate> list = sysWorkDelegateService
							.getSysWorkDelegateList(parameters);
					if (list.size() > 0) {
						SysWorkDelegate sysWorkDelegate = list.get(0);
						SysUser delegateUser = sysUserService
								.getSysUserById(list.get(0).getDelegateUserId());
						SysUser user = sysUserService.getSysUserById(list
								.get(0).getUserId());
						if (sysWorkDelegate.getDelegateUserId().equals(
								shareUserId)
								&& sysWorkDelegate.getUserId().equals(userId)
								&& sysWorkDelegate.getIsCancel().equals(1)) {// 用户“U1”工作托管给用户“U2”，请重新选择！
							out.print(EnumUserType.USER_NAME.getValue()
									+ delegateUser.getUserName()
									+ EnumUserType.DEPT_WORKDELEGATE.getValue()
									+ user.getUserName()
									+ EnumUserType.REPEAT_SELECT.getValue());
						} else if (!sysWorkDelegate.getDelegateUserId().equals(
								shareUserId)
								&& sysWorkDelegate.getUserId().equals(userId)) {// 用户“U1”已将工作托管给用户“U2”，是否仅托管“U2”的工作？
							out.print("yes|"
									+ EnumUserType.USER_NAME.getValue()
									+ delegateUser.getUserName()
									+ EnumUserType.WORKDELEGATE_USER.getValue()
									+ user.getUserName()
									+ EnumUserType.WORKDELEGATE_WHETHER
											.getValue() + user.getUserName()
									+ EnumUserType.WORK.getValue());
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("valationWorkDelegate action error:" + e.getMessage());
			return null;
		}
		return null;
	}

	/**
	 * 下属人员---------机构树json
	 * 
	 * @param userId
	 * @return
	 */
	private JSONArray toUserOrDeptJson(Integer userId) {
		JSONArray array = new JSONArray();
		List<CusBelongToBean> deptlist = null;
		Integer[] ids = deptFacadeService.getInChargeOfDeptIds();
		String deptIds = "";
		if (ids != null) {
			for (int i = 0; i < ids.length; i++) {
				deptIds += ids[i] + ",";
			}
			deptIds = deptIds.substring(0, deptIds.length() - 1);
		}
		deptlist = deptFacadeService.getBelongToUserAndDeptTreeList(deptIds);
		Set<Integer> deptSet = new HashSet<Integer>();
		for (CusBelongToBean dept : deptlist) {
			deptSet.add(dept.getId());
		}
		for (CusBelongToBean dept : deptlist) {
			if (dept.getId().equals(userId))
				continue;
			JSONObject obj = new JSONObject();
			obj.put("id", dept.getId());
			if (dept.getParentId() == 0) {
				obj.put("pId", 0);
				obj.put("open", true);
			} else {
				if (!deptSet.contains(dept.getParentId())) {
					obj.put("pId", 0);
					obj.put("open", true);
				} else {
					obj.put("pId", dept.getParentId());
				}
			}
			obj.put("name", dept.getTextName());
			obj.put("flag", dept.getType());
			obj.put("deptName", dept.getDeptName());
			array.add(obj);
		}
		return array;
	}

	/**
	 * 执行工作托管
	 * 
	 * @return
	 */
	public String execWorkDelegate() {
		try {
			String userId = request.getParameter("userId");
			String shareUserId = request.getParameter("shareUserId");
			SysWorkDelegate sysWorkDelegate = new SysWorkDelegate();
			sysWorkDelegate.setDelegateDate(new Date());
			sysWorkDelegate.setDelegateUserId(Integer.parseInt(userId));
			sysWorkDelegate.setManagerUserId(this.getLoginInfo().getUserId());
			sysWorkDelegate.setUserId(Integer.parseInt(shareUserId));
			sysWorkDelegate.setIsCancel(1);
			sysWorkDelegate.setCreateUser(this.getLoginInfo().getUserId());
			sysWorkDelegate.setUpdateUser(this.getLoginInfo().getUserId());
			sysWorkDelegateService.addSysWorkDelegate(sysWorkDelegate);
			crmCustomerService.workTrusteeship(userId, shareUserId);
			opeventLoginLogService.addLog(9,
					EnumUserType.ADD_WORKDELEGATE.getValue(), 1, 1);
			return SUCCESS;
		} catch (Exception e) {
			log.error("execWorkDelegate action error:" + e.getMessage());
			opeventLoginLogService.addLog(9,
					EnumUserType.ADD_WORKDELEGATE.getValue(), 1, 0);
			return ERROR;
		}
	}

	/**
	 * 撤销工作托管
	 * 
	 * @return
	 */
	public String cancelWorkDelegate() {
		try {
			String userId = request.getParameter("userId");
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("delegateUserId", userId);
			parameters.put("isCancel", 1);
			sysWorkDelegateList = sysWorkDelegateService
					.getSysWorkDelegateList(parameters);
			SysWorkDelegate sysWorkDelegate = sysWorkDelegateList.get(0);
			sysWorkDelegate.setIsCancel(0);
			sysWorkDelegateService.updateSysWorkDelegate(sysWorkDelegate);
			crmCustomerService.cancelWorkTrusteeship(userId);
			opeventLoginLogService.addLog(9,
					EnumUserType.CANCEL_WORKDELEGATE.getValue(), 1, 1);
		} catch (Exception e) {
			log.error("cancelWorkDelegate action error:" + e.getMessage());
			opeventLoginLogService.addLog(9,
					EnumUserType.CANCEL_WORKDELEGATE.getValue(), 1, 0);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 工作转交初始化
	 * 
	 * @return
	 */
	public String toWorkTransferPage() {
		SysUser user = sysUserService.getSysUserById(Integer
				.parseInt(this.request.getParameter("userId")));
		this.request.setAttribute("userName", user.getUserName());
		this.request.setAttribute("userId", user.getUserId());
		cusTypelist = crmCustomerTypeService.getAllCrmCustomerType();
		String aa = showCrmCustomerListPage();
		return aa;
	}

	/**
	 * 如没有移交客户
	 * 
	 * @return
	 */
	public String checkUserBelongCount() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			isInChargeof = deptFacadeService.isInChargeOfDepartment();
			beforeQueryEntityList();
			customerList = crmCustomerService.getCrmCustomerPage(parameterMap,
					this.getPage());
			PrintWriter out = response.getWriter();
			if (customerList.getItems().size() == 0) {
				out.print("yes");
			}
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 查询转交客户
	 * 
	 * @return
	 */
	public String queryCustomerList() {
		cusTypelist = crmCustomerTypeService.getAllCrmCustomerType();
		return showCrmCustomerListPage();
	}

	/**
	 * 查询客户
	 * 
	 * @return
	 */
	public String showCrmCustomerListPage() {
		try {
			isInChargeof = deptFacadeService.isInChargeOfDepartment();
			beforeQueryEntityList();
			customerList = crmCustomerService.getCrmCustomerPage(parameterMap,
					this.getPage());
			recordCount = this.getPage().getTotalRowsAmount();
			return SUCCESS;
		} catch (Exception e) {
			log.error("showRecordInfoListPage action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * before查询客户
	 */
	private void beforeQueryEntityList() {
		String inChargeOfUserIds = "";
		String inChargeOfDeptIds = "";
		isSelectCus = request.getParameter("isSelectCus");
		if (isSelectCus == null)
			isSelectCus = "False";
		if (crmCustomer != null) {
			if (crmCustomer.getIsTrash() != null) {
				parameterMap.put("isTrash", crmCustomer.getIsTrash());
			} else {
				parameterMap.put("isTrash", 0);
			}
			if (crmCustomer.getCustomerTypeId() != null) {
				parameterMap.put("customerTypeId",
						crmCustomer.getCustomerTypeId());
			}
			if (!StringUtil.isEmpty(crmCustomer.getCustomerName())) {
				parameterMap.put("customerName", StringUtil
						.ReplaceSQLChar(crmCustomer.getCustomerName()));
			}
			if (!StringUtil.isEmpty(crmCustomer.getLastContactDateStr())) {
				String lastContactdate = crmCustomer.getLastContactDateStr();
				Date dd = new Date();
				if (lastContactdate.equals("UnContactAWeek")) {
					dd = DateUtil.addDay(dd, -7);
				} else if (lastContactdate.equals("UnContactTwoWeek")) {
					dd = DateUtil.addDay(dd, -14);
				} else if (lastContactdate.equals("UnContactMonth")) {
					dd = DateUtil.addMonth(dd, -1);
				} else if (lastContactdate.equals("UnContactNever")) {
					dd = null;
				}
				parameterMap.put("lastContactDate", dd);
				parameterMap.put("lastContactDateStr", lastContactdate);
			}
			if (crmCustomer.getIsVisit() != null) {
				parameterMap.put("isVisit", crmCustomer.getIsVisit());
			}
		}
		parameterMap.put("BelongTo", "brMine");
		if (isInChargeof) {
			inChargeOfDeptIds = getCurrentUserInChargeOfDeptIds();
			if (StringUtil.isEmpty(inChargeOfDeptIds)) {
				inChargeOfDeptIds = "-1";
			}
		} else {
			inChargeOfDeptIds = "-1";
		}
		inChargeOfUserIds = request.getParameter("userId");
		parameterMap.put("InChargeOfUserIds", inChargeOfUserIds);
		parameterMap.put("InChargeOfDeptIds", inChargeOfDeptIds);
		parameterMap.put("UserId", this.getLoginInfo().getUserId());
	}

	/**
	 * 当前用户有权限的 用户ids
	 * 
	 * @return
	 */
	private String getCurrentInChargeUserIds() {
		String userIds = "";
		Integer[] arr = deptFacadeService.getInChargeOfDeptUserIds();
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				if (userIds.equals(""))
					userIds = arr[i].toString();
				else
					userIds = userIds + "," + arr[i];
			}
		}
		if (userIds.equals("")) {
			userIds = this.getLoginInfo().getUserId().toString();
		} else {
			userIds = userIds + "," + this.getLoginInfo().getUserId();
		}
		return userIds;
	}

	/**
	 * 当前用户有权限的 机构ids
	 * 
	 * @return
	 */
	private String getCurrentUserInChargeOfDeptIds() {
		String deptIds = "";
		Integer[] arr = deptFacadeService.getInChargeOfDeptIds();
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				if (deptIds.equals(""))
					deptIds = arr[i].toString();
				else
					deptIds = deptIds + "," + arr[i];
			}
		}
		return deptIds;
	}

	/**
	 * 虚拟根节点分页
	 */
	public String getRootPage() {
		try {
			deptJson = deptFacadeService.getBusinessManagerDeptJsonAddRoot();
			String codes = "";
			JSONObject obj = deptJson.getJSONObject(deptJson.size() - 1);
			codes = (String) obj.get("searchCode");
			String[] searchCodes = codes.split(",");
			Map<String, Object> map = new HashMap<String, Object>();
			String deptCodes = "";
			for (String deptSearchCode : searchCodes) {
				deptCodes += "DEPT_SEARCH_CODE like" + " " + "'"
						+ deptSearchCode + "%" + "'" + " " + "or" + " ";
			}
			deptCodes = deptCodes.substring(0, deptCodes.lastIndexOf("or"));
			if (deptCodes != null && !deptCodes.equals(""))
				map.put("searchMany", deptCodes);
			if (deptUserBean != null && deptUserBean.getAccount() != null
					&& !deptUserBean.getAccount().equals(""))
				map.put("account", deptUserBean.getAccount());
			if (deptUserBean != null && deptUserBean.getUserName() != null
					&& !deptUserBean.getUserName().equals(""))
				map.put("userName", deptUserBean.getUserName());
			if (deptUserBean != null && deptUserBean.getIsActived() != null
					&& !deptUserBean.getIsActived().equals(""))
				map.put("roleId", deptUserBean.getIsActived());
			map.put("isActived", 1);
			map.put("roleMemberId", 2);
			map.put("userId", this.getLoginInfo().getUserId());

			deptUserList = conversionRoleName(deptService
					.getSubsUserSubordinateList(map, this.getPage()));
			totalAmount = deptUserList.getPage().getTotalRowsAmount();
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("isCancel", 1);
			sysWorkDelegateList = sysWorkDelegateService
					.getSysWorkDelegateList(parameters);
			return SUCCESS;
		} catch (Exception e) {
			log.error("getRootPage action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 查询本部门用户
	 * 
	 * @return
	 */
	public String getDeptUser() {
		try {
			deptJson = deptFacadeService.getBusinessManagerDeptJsonAddRoot();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("deptId", deptId);
			map.put("isActived", 1);
			map.put("roleMemberId", 2);
			map.put("userId", this.getLoginInfo().getUserId());
			deptUserList = conversionRoleName(deptService
					.getUserSubordinatePage(map, this.getPage()));
			totalAmount = deptUserList.getPage().getTotalRowsAmount();// 记录的总数
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("isCancel", 1);
			sysWorkDelegateList = sysWorkDelegateService
					.getSysWorkDelegateList(parameters);
			return SUCCESS;
		} catch (Exception e) {
			log.error("getDeptUser action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 分页入口
	 * 
	 * @return
	 */
	public String getUserPageList() {
		if (flagInt == 1)
			return this.getDeptUser();// 本机构
		else if (flagInt == 2)
			return this.getCoditions();// 下属机构
		return this.getRootPage();// 虚拟节点
	}

	/**
	 * 模糊查询用户
	 * 
	 * @return
	 */
	public String getCoditions() {
		try {
			if (deptId == 0)
				deptId = 2;
			dept = deptService.getDeptById(deptId);
			deptJson = deptFacadeService.getBusinessManagerDeptJsonAddRoot();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("deptSearchCode", dept.getDeptSearchCode());
			map.put("account",
					StringUtil.ReplaceSQLChar(deptUserBean.getAccount()));
			map.put("userName",
					StringUtil.ReplaceSQLChar(deptUserBean.getUserName()));
			if (deptUserBean.getIsActived() != null)
				map.put("roleId", deptUserBean.getIsActived());
			map.put("isActived", 1);
			map.put("roleMemberId", 2);
			map.put("userId", this.getLoginInfo().getUserId());
			if (flag == 0) {
				// 本机构用户
				map.put("deptId", deptId);
				deptUserList = conversionRoleName(deptService
						.getUserSubordinatePage(map, this.getPage()));
				totalAmount = deptUserList.getPage().getTotalRowsAmount();// 记录的总数
			} else {
				// 包含下属机构用户
				deptUserList = conversionRoleName(deptService
						.getSubsUserSubordinateList(map, this.getPage()));
				totalAmount = deptUserList.getPage().getTotalRowsAmount();
			}
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("isCancel", 1);
			sysWorkDelegateList = sysWorkDelegateService
					.getSysWorkDelegateList(parameters);
			return SUCCESS;
		} catch (Exception e) {
			log.error("getCoditions action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 组织机构对角色名称进行增加
	 * 
	 * @param beans
	 * @return
	 */
	public PageUtil<UserSubordinateBean> conversionRoleName(
			PageUtil<UserSubordinateBean> beans) {
		try {
			List<UserSubordinateBean> bean = new ArrayList<UserSubordinateBean>();
			UserSubordinateBean item = null;
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

}
