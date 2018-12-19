/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户管理Action
 * Author     :xuhj
 * Version    :1.0
 * Create Date:May 3, 2012
 */
package com.banger.mobile.webapp.action.customer;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.Enum.customer.EnumCustomer;
import com.banger.mobile.domain.model.base.customer.BaseFamilyName;
import com.banger.mobile.domain.model.customer.*;
import com.banger.mobile.domain.model.dept.CusBelongToBean;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.loan.LnLoanInfo;
import com.banger.mobile.domain.model.loan.LnLoanInfoDictionary;
import com.banger.mobile.domain.model.system.CrmCustomerType;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.crmModuleExport.CrmModuleExportService;
import com.banger.mobile.facade.customer.AdvancedCustomerService;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.customer.CrmCustomerShareService;
import com.banger.mobile.facade.customer.CustomerExportService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.impl.customer.CustomerExportServiceImpl;
import com.banger.mobile.facade.loan.LnLoanInfoDictionaryService;
import com.banger.mobile.facade.loan.LnLoanInfoService;
import com.banger.mobile.facade.loan.LnLoanService;
import com.banger.mobile.facade.log.OpeventLoginLogService;
import com.banger.mobile.facade.microTask.TskScheduleService;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.facade.record.RecordInfoService;
import com.banger.mobile.facade.role.SysRoleService;
import com.banger.mobile.facade.rolemember.SysRoleMemberService;
import com.banger.mobile.facade.specialDataAuth.SpecialDataAuthService;
import com.banger.mobile.facade.system.CdSystemService;
import com.banger.mobile.facade.system.CrmCustomerTypeService;
import com.banger.mobile.facade.template.CrmTemplateService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.FileType;
import com.banger.mobile.util.JsUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.util.CollectionUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLDecoder;
import java.util.*;

/**
 * 
 * @author xuhj
 * @version $Id: CustomerAction.java,v 0.1 Sep 4, 2012 3:05:13 PM xuhj Exp $
 */
public class CustomerAction extends BaseAction {

	private static final long serialVersionUID = 1571949684153996822L;
	private static final int BUFFERED_SIZE = 5 * 1024;
	private CrmCustomerService crmCustomerService; // 客户Service
	private CrmCustomerTypeService crmCustomerTypeService; // 客户类型Service
	private DeptFacadeService deptFacadeService; // 机构Service
	private OpeventLoginLogService opeventLoginLogService; // 操作日志service
	private RecordInfoService recordInfoService; // 联系记录service
	private AdvancedCustomerService advancedCustomerService;// 高级搜索客户service
	private CrmCustomerShareService crmCustomerShareService;// 共享客户service
	private CustomerExportService customerExportService; // 客户导出service
	private CrmModuleExportService crmModuleExportService; // 导出字段service
	private  SysRoleService sysRoleService;
	private SysRoleMemberService sysRoleMemberService;
	private LnLoanService lnLoanService; // 贷款信息service
	private TskScheduleService tskScheduleService; // 日程信息service
	private SysParamService sysParamService;
	private CdSystemService cd; // 代码表
	private PageUtil<CrmCustomer> customerList; // 客户列表
	private CrmCustomer crmCustomer; // 客户实体
	private List<CrmCustomerType> cusTypelist; // 客户类型集合
	private Map<String, Object> parameterMap; // 客户查询MAP
	private Integer recordCount; // 总客户记录数
	private String actionType; // 新增、保存并关闭、保存并继续
	private Boolean isInChargeof; // 是否是业务主管
	private CustomerExtendFieldBean exField; // 自定义
	private CrmTemplateService temp; // 模板
	private Integer callType;
	private Integer hasPerssion;
	private String recId;
	private String isSelectCus;
	private Integer currentUserId; // 当前登录用户id
	private List<CrmUserQueryBean> queryMenus; // 自定义搜索列表
	private File headImage;
	private List<CrmCustomer> recentCustomers; // 最近联系过的客户
	private String telNum; // 首页输入查询
    private SpecialDataAuthService specialDataAuthService;  //特殊数据权限
	private SysUserService sysUserService;
	private LnLoanInfoDictionaryService lnLoanInfoDictionaryService;
	private LnLoanInfoService lnLoanInfoService;

	public LnLoanInfoService getLnLoanInfoService() {
		return lnLoanInfoService;
	}

	public void setLnLoanInfoService(LnLoanInfoService lnLoanInfoService) {
		this.lnLoanInfoService = lnLoanInfoService;
	}

	public SysRoleMemberService getSysRoleMemberService() {
		return sysRoleMemberService;
	}

	public void setSysRoleMemberService(SysRoleMemberService sysRoleMemberService) {
		this.sysRoleMemberService = sysRoleMemberService;
	}

	public SysRoleService getSysRoleService() {
		return sysRoleService;
	}

	public void setSysRoleService(SysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}

	public LnLoanInfoDictionaryService getLnLoanInfoDictionaryService() {
		return lnLoanInfoDictionaryService;
	}

	public void setLnLoanInfoDictionaryService(LnLoanInfoDictionaryService lnLoanInfoDictionaryService) {
		this.lnLoanInfoDictionaryService = lnLoanInfoDictionaryService;
	}

	public SysUserService getSysUserService() {
		return sysUserService;
	}

	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	public void setSpecialDataAuthService(SpecialDataAuthService specialDataAuthService) {
        this.specialDataAuthService = specialDataAuthService;
    }
	public List<CrmCustomer> getRecentCustomers() {
		return recentCustomers;
	}

	public void setRecentCustomers(List<CrmCustomer> recentCustomers) {
		this.recentCustomers = recentCustomers;
	}

	public File getHeadImage() {
		return headImage;
	}

	public void setHeadImage(File headImage) {
		this.headImage = headImage;
	}

	public SysParamService getSysParamService() {
		return sysParamService;
	}

	public void setSysParamService(SysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}

	public String getIsSelectCus() {
		return isSelectCus;
	}

	public void setIsSelectCus(String isSelectCus) {
		this.isSelectCus = isSelectCus;
	}

	public Integer getCurrentUserId() {
		return currentUserId;
	}

	public void setCurrentUserId(Integer currentUserId) {
		this.currentUserId = currentUserId;
	}

	public String getRecId() {
		return recId;
	}

	public void setRecId(String recId) {
		this.recId = recId;
	}

	public RecordInfoService getRecordInfoService() {
		return recordInfoService;
	}

	public void setRecordInfoService(RecordInfoService recordInfoService) {
		this.recordInfoService = recordInfoService;
	}

	public OpeventLoginLogService getOpeventLoginLogService() {
		return opeventLoginLogService;
	}

	public void setOpeventLoginLogService(
			OpeventLoginLogService opeventLoginLogService) {
		this.opeventLoginLogService = opeventLoginLogService;
	}

	public void setCustomerExportService(
			CustomerExportService customerExportService) {
		this.customerExportService = customerExportService;
	}

	public Integer getHasPerssion() {
		return hasPerssion;
	}

	public void setHasPerssion(Integer hasPerssion) {
		this.hasPerssion = hasPerssion;
	}

	public Integer getCallType() {
		return callType;
	}

	public void setCallType(Integer callType) {
		this.callType = callType;
	}

	public CrmTemplateService getTemp() {
		return temp;
	}

	public void setTemp(CrmTemplateService temp) {
		this.temp = temp;
	}

	public CustomerExtendFieldBean getExField() {
		return exField;
	}

	public void setExField(CustomerExtendFieldBean exField) {
		this.exField = exField;
	}

	public String getActionType() {
		return actionType;
	}

	public Boolean getIsInChargeof() {
		return isInChargeof;
	}

	public void setIsInChargeof(Boolean isInChargeof) {
		this.isInChargeof = isInChargeof;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public Integer getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
	}

	public CrmCustomerTypeService getCrmCustomerTypeService() {
		return crmCustomerTypeService;
	}

	public List<CrmCustomerType> getCusTypelist() {
		return cusTypelist;
	}

	public void setCrmModuleExportService(
			CrmModuleExportService crmModuleExportService) {
		this.crmModuleExportService = crmModuleExportService;
	}

	public void setCusTypelist(List<CrmCustomerType> cusTypelist) {
		this.cusTypelist = cusTypelist;
	}

	public void setCrmCustomerTypeService(
			CrmCustomerTypeService crmCustomerTypeService) {
		this.crmCustomerTypeService = crmCustomerTypeService;
	}

	public DeptFacadeService getDeptFacadeService() {
		return deptFacadeService;
	}

	public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
		this.deptFacadeService = deptFacadeService;
	}

	public CrmCustomer getCrmCustomer() {
		return crmCustomer;
	}

	public Map<String, Object> getParameterMap() {
		return parameterMap;
	}

	public void setParameterMap(Map<String, Object> parameterMap) {
		this.parameterMap = parameterMap;
	}

	public void setCrmCustomer(CrmCustomer crmCustomer) {
		this.crmCustomer = crmCustomer;
	}

	public PageUtil<CrmCustomer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(PageUtil<CrmCustomer> customerList) {
		this.customerList = customerList;
	}

	public CdSystemService getCd() {
		return cd;
	}

	public void setCd(CdSystemService cd) {
		this.cd = cd;
	}

	public void setCrmCustomerService(CrmCustomerService rmCustomerService) {
		this.crmCustomerService = rmCustomerService;
	}

	public AdvancedCustomerService getAdvancedCustomerService() {
		return advancedCustomerService;
	}

	public void setAdvancedCustomerService(
			AdvancedCustomerService advancedCustomerService) {
		this.advancedCustomerService = advancedCustomerService;
	}

	public List<CrmUserQueryBean> getQueryMenus() {
		return queryMenus;
	}

	public void setQueryMenus(List<CrmUserQueryBean> queryMenus) {
		this.queryMenus = queryMenus;
	}

	public CrmCustomerShareService getCrmCustomerShareService() {
		return crmCustomerShareService;
	}

	public void setCrmCustomerShareService(
			CrmCustomerShareService crmCustomerShareService) {
		this.crmCustomerShareService = crmCustomerShareService;
	}

	public String getTelNum() {
		return telNum;
	}

	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	public void setLnLoanService(LnLoanService lnLoanService) {
		this.lnLoanService = lnLoanService;
	}

	public void setTskScheduleService(TskScheduleService tskScheduleService) {
		this.tskScheduleService = tskScheduleService;
	}

	/**
	 * 构造函数
	 */
	public CustomerAction() {
		this.crmCustomer = new CrmCustomer();
		this.parameterMap = new HashMap<String, Object>();
		this.exField = new CustomerExtendFieldBean();
	}

	/**
	 * 第一次 查询
	 * 
	 * @return
	 */
	public String FirstLoad() {
		cusTypelist = crmCustomerTypeService.getAllCrmCustomerType();
		return showCrmCustomerListPage();
	}

	/*
	 * 查询
	 * 
	 * @return
	 */
	public String DoQuery() {
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
		    request.setAttribute("dataCode", this.getLoginInfo().getDataCompetence());
		    String roleIds=StringUtil.getIdsString(getLoginInfo().getRoles());
            boolean flag=specialDataAuthService.getSysDataAuthCondition(roleIds,"customerInfo");
            request.setAttribute("dataflag", flag);
			isInChargeof = false;//deptFacadeService.isInChargeOfDepartment();
			beforeQueryEntityList();
			customerList = crmCustomerService.getCrmCustomerPage(parameterMap,
					this.getPage());
			recordCount = this.getPage().getTotalRowsAmount();
			Integer userId = this.getLoginInfo().getUserId();
			Integer roleID = sysRoleMemberService.getRoleIdByUserId(userId);
			request.setAttribute("roleID",roleID);
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
		// 处理选择客户界面显示 （隐藏一些按钮）
		isSelectCus = request.getParameter("isSelectCus");
		if (isSelectCus == null)
			isSelectCus = "False";
		// 选择客户是单选还是多选
		String selType = request.getParameter("selType");
		if (selType == null) {
			selType = "multi";
		}
		request.setAttribute("selType", selType);
		request.setAttribute("userName", this.getLoginInfo().getUserName());

		if (crmCustomer != null) {
			if (crmCustomer.getIsTrash() != null) {
				parameterMap.put("isTrash", crmCustomer.getIsTrash());
			} else {
				parameterMap.put("isTrash", 0);
			}
			if (crmCustomer.getCustomerTypeId() != null) {
				parameterMap.put("customerTypeId", crmCustomer
						.getCustomerTypeId());
			}
			if (!StringUtil.isEmpty(crmCustomer.getCustomerName())) {
				parameterMap.put("customerName", StringUtil
						.ReplaceSQLChar(crmCustomer.getCustomerName()));
			}
			if (!StringUtil.isEmpty(crmCustomer.getLastContactDateStr())) {
				String lastContactdate = crmCustomer.getLastContactDateStr();
				request.setAttribute("lastContactDateStr", lastContactdate);
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
			// 客户归属
			String belongToType = request.getParameter("BelongToType");
			if (!StringUtil.isEmpty(belongToType)) {
				parameterMap.put("BelongTo", belongToType);
				if (isInChargeof) {
					inChargeOfDeptIds = getCurrentUserInChargeOfDeptIds();
					if (StringUtil.isEmpty(inChargeOfDeptIds)) {
						inChargeOfDeptIds = "-1";
					}
					inChargeOfUserIds = getCurrentInChargeUserIds();
				} else {
					inChargeOfDeptIds = "-1";
					inChargeOfUserIds = this.getLoginInfo().getUserId().toString();
				}

				if (belongToType.equals("brAll")) { // 所有
					// inChargeOfUserIds =
					// this.getLoginInfo().getUserId().toString();
					parameterMap.put("ContainsShare", "ContainsShare");
				} else if (belongToType.equals("brMine")) { // 我的
					inChargeOfUserIds = this.getLoginInfo().getUserId().toString();
					parameterMap.put("ContainsShare", "ContainsShare");
					// 处理共享给别人的客户
					parameterMap.put("QueryUserIds", inChargeOfUserIds);
				} else if (belongToType.equals("brUser")) { // 下属
					inChargeOfUserIds = request.getParameter("userIds");
					if (StringUtil.isEmpty(inChargeOfUserIds)) { // 没有选择人员的时候
						parameterMap.put("BelongTo", "brAll");
						inChargeOfDeptIds = getCurrentUserInChargeOfDeptIds();
						if (StringUtil.isEmpty(inChargeOfDeptIds)) {
							inChargeOfDeptIds = "-1";
						}
						inChargeOfUserIds = this.getLoginInfo().getUserId().toString();
					}
					parameterMap.put("ContainsShare", "ContainsShare");
					// 处理共享给别人的客户
					parameterMap.put("QueryUserIds", inChargeOfUserIds);
				} else if (belongToType.equals("brDept")) { // 机构
					inChargeOfDeptIds = request.getParameter("deptIds");
					if (StringUtil.isEmpty(inChargeOfDeptIds)) { // 没有选择机构的时候
						parameterMap.put("BelongTo", "brAll");
						inChargeOfDeptIds = getCurrentUserInChargeOfDeptIds();
						if (StringUtil.isEmpty(inChargeOfDeptIds)) {
							inChargeOfDeptIds = "-1";
						}
						inChargeOfUserIds = this.getLoginInfo().getUserId()
								.toString();
					}
					String containSub = request.getParameter("containSub");
					if (!StringUtil.isEmpty(containSub)
							&& containSub.equals("1")) {
						inChargeOfDeptIds = getContainSubDeptIds(inChargeOfDeptIds);
					}
					parameterMap.put("ContainsShare", "ContainsShare");
					// 处理共享给别人的客户
					parameterMap.put("QueryDeptIds", inChargeOfDeptIds);
				} else if (belongToType.equals("brUnAllocate")) { // 待分配
					inChargeOfDeptIds = getCurrentUserInChargeOfDeptIds();
					inChargeOfUserIds = "0";
					// 处理共享给别人的客户
					parameterMap.put("QueryUserIds", "0");
					parameterMap.put("QueryDeptIds", inChargeOfDeptIds);
				}
				parameterMap.put("InChargeOfUserIds", inChargeOfUserIds.equals("")?"-1":inChargeOfUserIds);
				Integer deptId = this.getLoginInfo().getDeptId();
				if(null==deptId){
					deptId = -1;
				}
//				deptId = null;
				parameterMap.put("InChargeOfDeptIds", (inChargeOfDeptIds.equals("-1")||StringUtils.isBlank(inChargeOfDeptIds)) ? deptId : inChargeOfDeptIds);
				parameterMap.put("UserId", this.getLoginInfo().getUserId());
			}
			// 共享
			String shareFlag = request.getParameter("shareFlag");
			if (!StringUtil.isEmpty(shareFlag)) {
				if (shareFlag.equals("brShareToOthers")) {
					parameterMap.put("BelongTo", "ShareToOthers");

					List<CusShareUserBean> shareDropUsers = crmCustomerShareService
							.selectCustomerShareUser(getCurrentInChargeUserIds());
					request.setAttribute("shareDropUsers", shareDropUsers);

					String shareUserId = request.getParameter("shareUserId");
					if (!StringUtil.isEmpty(shareUserId))
						parameterMap.put("SharePresentUserId", shareUserId);
				} else if (shareFlag.equals("brShareToMe")) {
					parameterMap.put("BelongTo", "ShareToMe");
					parameterMap.put("InChargeOfUserIds",
							getCurrentInChargeUserIds());
					parameterMap.put("UserId", this.getLoginInfo().getUserId()
							.toString());

					List<CusShareUserBean> shareDropUsers = crmCustomerShareService
							.selectShareToMeUser(getCurrentInChargeUserIds(),
									this.getLoginInfo().getUserId().toString());
					request.setAttribute("shareDropUsers", shareDropUsers);

					String shareUserId = request.getParameter("shareUserId");
					if (!StringUtil.isEmpty(shareUserId))
						parameterMap.put("SharePresentUserId", shareUserId);
				}
			}

			// 未联系客户提醒 工作台
			String unAllocation = request.getParameter("unAllocation");
			if (!StringUtil.isEmpty(unAllocation)) {
				inChargeOfUserIds = this.getLoginInfo().getUserId().toString();
				parameterMap.put("BelongTo", "brUser");
				parameterMap.put("InChargeOfUserIds", inChargeOfUserIds);
				parameterMap.put("InChargeOfDeptIds", "-1");
				parameterMap.put("UserId", inChargeOfUserIds);
			}
		}
		String searchFlag=request.getParameter("searchFlag");
		if(searchFlag==null){
		    String roleIds=StringUtil.getIdsString(getLoginInfo().getRoles());
	        boolean flag=specialDataAuthService.getSysDataAuthCondition(roleIds,"customerInfo");
	        if(flag){
	            parameterMap.put("InChargeOfDeptIds","-1");
	            parameterMap.put("InChargeOfUserIds",getLoginInfo().getUserId().toString());
	        }
		}
	}

	/**
	 * 包含本身选中的
	 * 
	 * @param deptids
	 * @return 下属机构id集合
	 */
	private String getContainSubDeptIds(String deptids) {
		List<SysDept> deptList = deptFacadeService
				.getContainDeptListByDeptIds(deptids);
		String newDeptIds = "";
		for (SysDept dept : deptList) {
			if (newDeptIds.equals("")) {
				newDeptIds = dept.getDeptId().toString();
			} else {
				newDeptIds = newDeptIds + "," + dept.getDeptId().toString();
			}
		}
		return newDeptIds;
	}

	/**
	 * 获取当前用户有权限的 用户ids
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
	 * 设置拜访客户
	 */
	public void SetVisitCustomer() {
		if (crmCustomer != null) {
			if ((crmCustomer.getCustomerId() != null)
					&& (crmCustomer.getIsVisit() != null)) {
				crmCustomerService.updateCrmCustomerByVisit(crmCustomer);
			}
		}
	}

	/**
	 * 客户新增
	 * 
	 * @return
	 */
	public String addCustomer() {
		actionType = request.getParameter("actionType");



		if (actionType.equals("insert")) {
			HashMap<String, Object> checkBoxMessageMap = buildLoanCheckBoxMessage("CUSTOMER_HOUSEATT","CUSTOMER_POSITION", "CUSTOMER_RANK", "CUSTOMER_OCCUPATION");
			request.setAttribute("checkBoxMessage", checkBoxMessageMap);
			setCustomerOfInsert();
			/*------------ begin 处理保存并新建的问题---------*/
			String deptid = request.getParameter("deptid");
			String userid = request.getParameter("userid");
			if (deptid != null && Integer.parseInt(deptid) > 0) {
				crmCustomer.setBelongDeptId(Integer.parseInt(deptid));
				if (userid != null)
					crmCustomer.setBelongUserId(Integer.parseInt(userid));
				String belongTo = getCustomerBelongTo(crmCustomer);
				parameterMap.put("BelongTo", belongTo);
				try {
					request.setAttribute("deptname", URLDecoder.decode(request
							.getParameter("deptname"), "UTF-8"));
					request.setAttribute("username", URLDecoder.decode(request
							.getParameter("username"), "UTF-8"));
				} catch (Exception e) {
					return "error";
				}
			}
			/*------------ end ----------------------------*/

			return "addCustomer";
		} else if ((actionType.equals("saveAndClose"))
				|| (actionType.equals("saveAndContinue"))) {
			try {

				if(null!=crmCustomer.getCredentialTypeId()&& StringUtils.isNotBlank(crmCustomer.getIdCard())){
					Map<String,Object> map = new HashMap<String, Object>();
					map.put("idCard",crmCustomer.getIdCard());
					map.put("credentialTypeId",crmCustomer.getCredentialTypeId());
					if(!CollectionUtils.isEmpty(crmCustomerService.getCrmCustomerByIdCard(map))){
						HttpServletResponse response = ServletActionContext.getResponse();
						response.setCharacterEncoding("UTF-8");
						PrintWriter out = response.getWriter();
						out.print("-1");
//                       客户证件号码已存在，请重新输入!
						return null;
					}
				}


				crmCustomerService.saveCustomer(crmCustomer, exField);
				/*------------ begin 关联联系记录 ---------*/
				String typeList = request.getParameter("typeList");
				if (!StringUtil.isEmpty(typeList) && typeList.equals("isScene")) {
					recordInfoService.relationRecord(
							StringUtil.isEmpty(recId) ? 0 : Integer
									.parseInt(recId), crmCustomer
									.getCustomerId(), true);
				} else {
					recordInfoService.relationRecord(
							StringUtil.isEmpty(recId) ? 0 : Integer
									.parseInt(recId), crmCustomer
									.getCustomerId(), false);
				}
				/*------------ end ----------------------------*/
				opeventLoginLogService.addLog(1, EnumCustomer.MODEL_ACTION_SAVE
						.getValue(), 1, 1);
			} catch (Exception e) {
				opeventLoginLogService.addLog(1, EnumCustomer.MODEL_ACTION_SAVE
						.getValue(), 1, 0);
			}
			return null;
		} else
			return "error";

	}

	/**
	 * 编辑客户
	 * 
	 * @return
	 */
	public String editCustomer() {
		actionType = request.getParameter("actionType");


		if ((actionType.equals("modify")) || (actionType.equals("browse"))) {
			HashMap<String, Object> checkBoxMessageMap = buildLoanCheckBoxMessage("CUSTOMER_HOUSEATT","CUSTOMER_POSITION", "CUSTOMER_RANK", "CUSTOMER_OCCUPATION");
			request.setAttribute("checkBoxMessage", checkBoxMessageMap);

			currentUserId = this.getLoginInfo().getUserId();
			parameterMap.put("currentUserId", currentUserId);
			parameterMap.put("currentUserName", this.getLoginInfo()
					.getUserName());
			parameterMap.put("currentDeptId", this.getLoginInfo().getDeptId());
			parameterMap.put("currentDeptName", this.getLoginInfo()
					.getDeptname());

			/*------------ begin 处理垃圾箱客户列表拨号按钮---------*/
			Boolean isDustbin = false;
			String dust = request.getParameter("isDustbin");
			if (!StringUtil.isEmpty(dust)) {
				isDustbin = true;
			}
			request.setAttribute("isDustbin", isDustbin);
			request.setAttribute("isBrowseLayer", request
					.getParameter("isBrowseLayer"));
			/*--------------------- end ---------------------------*/

			/* --------------begin 处理查看客户是否要链接----------------- */
			String needLine = request.getParameter("needLine");
			String phoneLine = request.getParameter("phoneLine");
			if(phoneLine==null){
			    if (actionType.equals("browse")||actionType.equals("modify")) {
	                if(needLine!=null && needLine.equals("0")){
	                    needLine = "0";
	                }else{
	                    needLine = "1";
	                }
	            }
			    
			}
			request.setAttribute("needLine", needLine == null ? 1 : Integer
					.parseInt(needLine));
			/*---------------end-----------------------------------------*/

			//isInChargeof = deptFacadeService.isInChargeOfDepartment();
			isInChargeof =false;
			try {
				crmCustomer = (CrmCustomer) crmCustomerService .getCrmCustomerById(crmCustomer.getCustomerId());

				Boolean isShareCus = crmCustomerService.checkShareCus( crmCustomer.getCustomerId(), currentUserId);

				String belongTo = getCustomerBelongTo(crmCustomer);
				parameterMap.put("BelongTo", belongTo);
				Integer[] arr = isInChargeof ? deptFacadeService
						.getInChargeOfDeptIds() : null;
				hasPerssion = crmCustomerService.checkPermission(crmCustomer,
						arr);

				request.setAttribute("isShareCus", isShareCus);
				exField = crmCustomerService.getCustomizedFieldById(crmCustomer
						.getCustomerId());
				return "editCustomer";
			} catch (Exception e) {
				return "error";
			}
		} else if (actionType.equals("saveAndClose")) {
			try {
				crmCustomerService.saveCustomer(crmCustomer, exField);
				//同步修改贷款信息表的借款人信息
				updateLnloanInfoData(crmCustomer);

				/*------------ begin 关联联系记录 ---------*/
				String typeList = request.getParameter("typeList");
				if (!StringUtil.isEmpty(typeList) && typeList.equals("isScene")) {
					recordInfoService.relationRecord(
							StringUtil.isEmpty(recId) ? 0 : Integer
									.parseInt(recId), crmCustomer
									.getCustomerId(), true);
				} else {
					recordInfoService.relationRecord(
							StringUtil.isEmpty(recId) ? 0 : Integer
									.parseInt(recId), crmCustomer
									.getCustomerId(), false);
				}
				/*------------ end ----------------------------*/

				opeventLoginLogService.addLog(1,
						EnumCustomer.MODEL_ACTION_UPDATE.getValue(), 1, 1);
			} catch (Exception e) {
				opeventLoginLogService.addLog(1,
						EnumCustomer.MODEL_ACTION_UPDATE.getValue(), 1, 0);
			}
			return null;
		} else
			return "error";
	}

	private void updateLnloanInfoData(CrmCustomer crmCustomer){
		try {
			Map map = new HashMap();
			map.put("customerId", crmCustomer.getCustomerId());
			List<LnLoanInfo> list = lnLoanInfoService.selectLoanInfoList(map);
			for (LnLoanInfo info : list) {
				info.setCusName(crmCustomer.getCustomerName());
				info.setCusIdcard(crmCustomer.getIdCard());
				info.setCusIdtypeId(String.valueOf(crmCustomer.getCredentialTypeId()));
				info.setCusSex(crmCustomer.getSex());
				lnLoanInfoService.updateLnLoanInfo(info);
			}
		}catch (Exception e){
		}
	}

	/**
	 * 新增客户设置默认数据
	 */
	private void setCustomerOfInsert() {
		/*------------ begin 联系记录通话记录新增客户---------*/
		String phoneNumber = request.getParameter("phoneNumber");
		String typeList = request.getParameter("typeList");
		if (!StringUtil.isEmpty(typeList) && typeList.equals("isNotScene")) {
			if (!StringUtil.isEmpty(phoneNumber)) {
				if ((phoneNumber.length() == 11)
						&& (phoneNumber.substring(0, 1).equals("1"))) {
					crmCustomer.setMobilePhone1(phoneNumber);
					crmCustomer.setDefaultPhoneType(1);
				} else {
					crmCustomer.setPhone(phoneNumber);
					crmCustomer.setDefaultPhoneType(3);
				}
			} else {
				crmCustomer.setDefaultPhoneType(0);
			}
		} else {
			crmCustomer.setDefaultPhoneType(0);
		}
		request.setAttribute("typeList", typeList);

		/*------------ end -------------------------------------*/

		currentUserId = this.getLoginInfo().getUserId();
		crmCustomer.setBelongDeptId(this.getLoginInfo().getDeptId());
		crmCustomer.setBelongUserId(currentUserId);
		crmCustomer.setDeptName(this.getLoginInfo().getDeptname());
		crmCustomer.setUserName(this.getLoginInfo().getUserName());

		parameterMap.put("currentUserId", currentUserId);
		parameterMap.put("currentUserName", this.getLoginInfo().getUserName());
		parameterMap.put("currentDeptId", this.getLoginInfo().getDeptId());
		parameterMap.put("currentDeptName", this.getLoginInfo().getDeptname());

		isInChargeof = false;//deptFacadeService.isInChargeOfDepartment();
	}

	/**
	 * 获得客户的归属类型-----编辑客户
	 * 
	 * @param crmCustomer
	 * @return
	 */
	private String getCustomerBelongTo(CrmCustomer crmCustomer) {
		if (crmCustomer == null) {
			return "brMine";
		}
		if (crmCustomer.getBelongUserId() == null
				|| crmCustomer.getBelongUserId().equals(0)) {
			// 归属用户id为空 或0 则表示是归属机构的客户
			return "brDept";
		} else if (crmCustomer.getBelongUserId().equals(
				this.getLoginInfo().getUserId())) {
			// 归属用户id为当前用户 则表示是归属我的客户
			return "brMine";
		} else {
			return "brUser";
		}
	}

	/**
	 * 校验客户编号是否唯一
	 * 
	 * @return
	 * @throws IOException
	 */
	public String checkCustomerNo() throws IOException {
		String customerNo = request.getParameter("customerNo");
		String cusId = request.getParameter("customerId");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		if (StringUtil.isEmpty(customerNo)) {
			out.print("");
		} else {
			CrmCustomer cus = crmCustomerService
					.getCustomerByCustomerNo(customerNo);
			if (cus != null) {
				if ((!StringUtil.isEmpty(cusId))
						&& (cusId.equals(cus.getCustomerId().toString()))) {
					out.print("");
				} else {
					isInChargeof = false;//deptFacadeService.isInChargeOfDepartment();
					Integer[] arr = isInChargeof ? deptFacadeService
							.getInChargeOfDeptIds() : null;
					Integer haspms = crmCustomerService.checkPermission(cus,
							arr);
					Boolean isShareCus = crmCustomerService.checkShareCus(cus
							.getCustomerId(), this.getLoginInfo().getUserId());
					if (isShareCus) {
						haspms = 1;
					}

					String outStr = "";
					if (haspms == 1) {
						if (cus.getBelongUserId() <= 0
								|| cus.getBelongUserId() == null) {
							outStr = "已存在相同客户编号的客户\"" + cus.getCustomerName()
									+ "\"，此客户的归属机构为\"" + cus.getDeptName()
									+ "\"!";
						} else {
							outStr = "已存在相同客户编号的客户\"" + cus.getCustomerName()
									+ "\"!";
						}
					} else {
						if (cus.getBelongUserId() <= 0
								|| cus.getBelongUserId() == null) {
							outStr = "已存在相同客户编号的客户\"" + cus.getCustomerName()
									+ "\"，此客户的归属机构为\"" + cus.getDeptName()
									+ "\"!";
						} else {
							outStr = "已存在相同客户编号的客户\"" + cus.getCustomerName()
									+ "\"，此客户的归属机构为\"" + cus.getDeptName()
									+ "\"，归属人员为\"" + cus.getUserName() + "\"!";
						}
					}
					out.print(outStr);
				}
			} else {
				out.print("");
			}
		}
		return null;
	}

	/**
	 * 获取客户称谓
	 * 
	 * @return
	 * @throws IOException
	 */
	public String getNickName() throws IOException {
		String cusName = request.getParameter("cusName");
		String cusSex = request.getParameter("cusSex");
		String tempNickName = "";
		if ((!StringUtil.isEmpty(cusName)) && (!StringUtil.isEmpty(cusSex))) {
			String familyNameTemp = cusName;
			long begintime = System.currentTimeMillis();
			List<BaseFamilyName> familyNameList = crmCustomerService
					.getNickName();
			long endtime = System.currentTimeMillis();
			long costTime = (endtime - begintime);
			System.err.println(costTime);
			System.out.println(costTime);
			for (BaseFamilyName familyName : familyNameList) {
				if (cusName.indexOf(familyName.getFamilyName()) == 0) {
					familyNameTemp = familyName.getFamilyName();
					break;
				}
			}
			if (cusSex.equals("男")) {
				tempNickName = familyNameTemp + "先生";
			}
			if (cusSex.equals("女")) {
				tempNickName = familyNameTemp + "女士";
			}
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(tempNickName);
		return null;
	}

	/**
	 * 删除客户至垃圾箱
	 * 
	 * @return
	 */
	public String delCustomers() {
		String customerIds = request.getParameter("customerIds");
		try {
			if (!StringUtil.isEmpty(customerIds)) {
				customerIds = lnLoanService.getNoLoanUserList(customerIds);
				crmCustomerService.delCustomerByCustomerIds(customerIds);
			}
			opeventLoginLogService.addLog(1, EnumCustomer.MODEL_ACTION_DEL.getValue(), 1, 1);
		} catch (Exception e) {
			opeventLoginLogService.addLog(1, EnumCustomer.MODEL_ACTION_DEL.getValue(), 1, 0);
		}
		return null;
	}

	public String getDelCusMsg() {
		String customerIds = request.getParameter("customerIds");
		String delType = request.getParameter("delType");
		String cusName = request.getParameter("cusName");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		try {
			String type = "confirm";
			String msg = "你确定要将选择的客户，删除至垃圾箱吗？";
			if (!StringUtil.isEmpty(customerIds)) {
			    int flag=0;
				String noLoanCusIds = lnLoanService.getNoLoanUserList(customerIds);
//				String noScheduleCusIds = tskScheduleService.getNoScheduleCusIds(customerIds);
				
				Map<String,Object> schedMap=new HashMap<String, Object>();
				String[] cusIdArr = customerIds.split(",");
	            List<Integer> cusIdList = null;
	            if(cusIdArr != null){
	                cusIdList = new ArrayList<Integer>();
	                for (String cusId : cusIdArr){
	                    cusIdList.add(Integer.parseInt(cusId));
	                    flag++;
	                }
	            }
	            schedMap.put("customerIds", cusIdList);
	            //0：有日程    >0：没有日程
				Integer noScheduleCusIds=tskScheduleService.judgeScheduleByCustomerId(schedMap);
				
				boolean hasLoan = false; // 选择的客户是否有贷款
				boolean hasSchedule = false; // 选择的客户是否有日程

				if (noLoanCusIds == null || !noLoanCusIds.equals(customerIds)) {
                    hasLoan = true;
                }
//                if (noScheduleCusIds == null || !noScheduleCusIds.equals(customerIds)) {
//                    //true:没有日程
//                    hasSchedule = true;
//                }
				if (noScheduleCusIds > 0) {
                    //true:没有日程
                    hasSchedule = true;
                }
				if (!hasLoan && !hasSchedule) {
					// 选择的客户没有一个有 关联贷款 和 日程信息
					if (delType != null && delType.equals("single")) {
						msg = "您确定要删除客户“" + cusName + "”至垃圾箱吗？";
					} else {
						msg = "你确定要将选择的客户，删除至垃圾箱吗？";
					}
				}
				if (hasLoan && !hasSchedule) {
					if (StringUtil.isEmpty(noLoanCusIds)) {
						// 选择的客户全部都是有 关联贷款 且没有日程信息
						msg = "客户有关联的贷款，不能删除客户信息！";
						type = "Prompt";
					} else {
						// 选择的客户部分有 关联贷款 且没有日程信息
						msg = "您选择的部分客户有关联的贷款，此操作只对无贷款记录的客户生效!";
					}
				}
				if (!hasLoan && hasSchedule) {
//					if (StringUtil.isEmpty(noScheduleCusIds.toString())) {
				    if (flag==1) {
						// 选择的客户全部有 日程信息 且 没有关联贷款
						msg = "客户有未完成的日程，删除客户会同时删除客户的日程安排！";
					} else {
						// 选择的客户部分有 日程信息 且 没有关联贷款
						msg = "您选择的客户部分有未完成的日程，删除客户会同时删除客户的日程安排!";
					}
				}
				if (hasLoan && hasSchedule) {
					if (StringUtil.isEmpty(noLoanCusIds)) {
						// 选择的客户全部有 关联贷款 则不管是否有日程信息 这里处理有日程信息的时候
						msg = "客户有关联的贷款，不能删除客户信息！";
						type = "Prompt";
					} else {
						// 选择的客户部分有 关联贷款 部分有日程信息
						msg = "客户有未完成的日程，删除客户会同时删除客户的日程安排？删除操作只对无关联贷款的客户生效！";
					}
				}
			}
			PrintWriter out = response.getWriter();
			JSONObject json = new JSONObject();
			json.put("type", type);
			json.put("Msg", msg);
			out.print(json.toString());
		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * 彻底删除客户
	 * 
	 * @return
	 */
	public String delCusComplete() {
		String customerIds = request.getParameter("customerIds");
		try {
			if (!StringUtil.isEmpty(customerIds)) {
				crmCustomerService.delCusComplete(customerIds);
			}
			opeventLoginLogService.addLog(1,
					EnumCustomer.MODEL_ACTION_DELCOMPLETE.getValue(), 1, 1);
			return null;
		} catch (Exception e) {
			opeventLoginLogService.addLog(1,
					EnumCustomer.MODEL_ACTION_DELCOMPLETE.getValue(), 1, 0);
			return ERROR;
		}
	}

	/**
	 * 清空垃圾箱
	 * 
	 * @return
	 */
	public String delCustomersComplete() {
		String inChargeOfDeptIds = "", inChargeOfUserIds = "";
		isInChargeof = false;//deptFacadeService.isInChargeOfDepartment();
		if (isInChargeof) {
			inChargeOfDeptIds = getCurrentUserInChargeOfDeptIds();
		}
		inChargeOfUserIds = this.getLoginInfo().getUserId().toString();
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put("InChargeOfUserIds", inChargeOfUserIds);
		if (!StringUtil.isEmpty(inChargeOfDeptIds)) {
			parameter.put("InChargeOfDeptIds", inChargeOfDeptIds);
		}
		try {
			crmCustomerService.delCustomersComplete(parameter);
			opeventLoginLogService.addLog(1,
					EnumCustomer.MODEL_ACTION_CLEANDUSTBIN.getValue(), 1, 1);
			return null;
		} catch (Exception e) {
			opeventLoginLogService.addLog(1,
					EnumCustomer.MODEL_ACTION_CLEANDUSTBIN.getValue(), 1, 0);
			return ERROR;
		}
	}

	/**
	 * 还原垃圾箱
	 * 
	 * @return
	 */
	public String restoreCustomerByCustomerIds() {
		String inChargeOfDeptIds = "", inChargeOfUserIds = "";
		isInChargeof = false;//deptFacadeService.isInChargeOfDepartment();
		if (isInChargeof) {
			inChargeOfDeptIds = getCurrentUserInChargeOfDeptIds();
		}
		inChargeOfUserIds = this.getLoginInfo().getUserId().toString();
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put("InChargeOfUserIds", inChargeOfUserIds);
		if (!StringUtil.isEmpty(inChargeOfDeptIds)) {
			parameter.put("InChargeOfDeptIds", inChargeOfDeptIds);
		}
		try {
			crmCustomerService.restoreCustomerByCustomerIds(parameter);
			opeventLoginLogService.addLog(1,
					EnumCustomer.MODEL_ACTION_RESTOREDUSTBIN.getValue(), 1, 1);
			return null;
		} catch (Exception e) {
			opeventLoginLogService.addLog(1,
					EnumCustomer.MODEL_ACTION_RESTOREDUSTBIN.getValue(), 1, 0);
			return ERROR;
		}
	}

	/**
	 * 还原客户
	 * 
	 * @return
	 */
	public String restoreCustomers() {
		String customerIds = request.getParameter("customerIds");
		try {
			if (!StringUtil.isEmpty(customerIds)) {
				crmCustomerService.restoreCustomers(customerIds);
			}
			opeventLoginLogService.addLog(1, EnumCustomer.MODEL_ACTION_RESTORE
					.getValue(), 1, 1);
			return null;
		} catch (Exception e) {
			opeventLoginLogService.addLog(1, EnumCustomer.MODEL_ACTION_RESTORE
					.getValue(), 1, 0);
			return ERROR;
		}
	}

	/**
	 * 选择客户页面
	 * 
	 * @return
	 */
	public String customerSelect() {
		Integer userId = this.getLoginInfo().getUserId();
		String selType = this.request.getParameter("selType");
		// 是否执行任务界面的在行客户添加
		String isVonExe = this.request.getParameter("isVonExe");
		if (selType != null && !"".equals(selType)) {
			this.request.setAttribute("selType", selType);
		} else {
			this.request.setAttribute("selType", "multi");
		}
		if (isVonExe != null && !"".equals(isVonExe)) {
			isInChargeof = false;
			this.request.setAttribute("isVonExe",isVonExe);
		} else {
			isInChargeof = false;//deptFacadeService.isInChargeOfDepartment();
		}
		this.queryMenus = advancedCustomerService.getUserQueryMenuList(userId);
		request.setAttribute("batchType", request.getParameter("batchType"));
		request.setAttribute("targetId", request.getParameter("targetId"));
		return SUCCESS;
	}

	/**
	 * 显示分配客户归属界面
	 * 
	 * @return
	 */
	public String showCusBelongTo() {
		String belongDeptId = this.getLoginInfo().getDeptId().toString();
		String belongUserId = this.getLoginInfo().getUserId().toString();
		String belongUserName = this.getLoginInfo().getUserName();
		String isWorkTransfer = request.getParameter("isWorkTransfer");
		if (isWorkTransfer == null) { // 处理工作移交
			isWorkTransfer = "0";
		} else {
			request.setAttribute("transferUserid", request
					.getParameter("transferUserid"));
		}
		List<SysDept> list = deptFacadeService
				.getBusinessManagerInCharegeDeptTreeList();
		JSONArray deptArray = this.toTreeJson(list, "insert", "");

		request.setAttribute("belongDeptId", belongDeptId);
		request.setAttribute("belongUserId", belongUserId);
		request.setAttribute("belongUserName", belongUserName);
		request.setAttribute("deptArray", deptArray);
		request.setAttribute("isWorkTransfer", isWorkTransfer);
		request.setAttribute("cusIds", request.getParameter("cusIds"));
		return SUCCESS;
	}

	/**
	 * 批量分配客户归属
	 * 
	 * @return
	 */
	public String modifyCusBelongTo() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("cusIds", request.getParameter("cusIds"));
		map.put("belongDeptId", request.getParameter("belongDeptId"));
		map.put("belongUserId", request.getParameter("belongUserId"));
		try {
			crmCustomerService.modifyCusBelongTo(map);
			opeventLoginLogService.addLog(1,
					EnumCustomer.MODEL_ACTION_MODIFYCUSBELONGTO.getValue(), 1,
					1);
			return null;
		} catch (Exception e) {
			opeventLoginLogService.addLog(1,
					EnumCustomer.MODEL_ACTION_MODIFYCUSBELONGTO.getValue(), 1,
					0);
			return ERROR;
		}
	}

	/**
	 * 批量分配客户归属
	 * 
	 * @return
	 */
	public String modifyCusBelongToAll() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("transferUserid", request.getParameter("transferUserid"));
		map.put("belongDeptId", request.getParameter("belongDeptId"));
		map.put("belongUserId", request.getParameter("belongUserId"));
		try {
			crmCustomerService.modifyCusBelongToAll(map);
			opeventLoginLogService.addLog(1,
					EnumCustomer.MODEL_ACTION_MODIFYCUSBELONGTO.getValue(), 1,
					1);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			opeventLoginLogService.addLog(1,
					EnumCustomer.MODEL_ACTION_MODIFYCUSBELONGTO.getValue(), 1,
					0);
			return ERROR;
		}
	}

	/**
	 * 查询用户----批量分配客户归属
	 * 
	 * @return
	 */
	public String searchUser() {
		int deptId = Integer.parseInt(request.getParameter("deptId"));    //3
		String isWorkTransfer = request.getParameter("isWorkTransfer");
		try {
			List<SysUser> userList = deptFacadeService.searchUser(deptId);
			if (isWorkTransfer.equals("1")) { // 去除被移交的人员
				SysUser deluser = null;
				String transferUserid = request.getParameter("transferUserid");
				for (SysUser user : userList) {
					if (user.getUserId().toString().equals(transferUserid)) {
						deluser = user;
					}
				}
				if (deluser != null) {
					userList.remove(deluser);
				}
			}
			request.setAttribute("userList", userList);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	/**
	 * 实体集合-----------选择客户
	 */
	public void selectCusByIds() {
		String customerIds = request.getParameter("customerIds");
		try {
			if (!StringUtil.isEmpty(customerIds)) {
				JSONArray jsonArray = new JSONArray();
				List<CrmCustomer> cusList = crmCustomerService
						.selectCusByIds(customerIds);
				if (cusList.size() > 0) {
					jsonArray = JSONArray.fromObject(cusList);
				}
				HttpServletResponse response = ServletActionContext
						.getResponse();
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.print(jsonArray.toString());
			}
		} catch (Exception e) {

		}
	}

	/**
	 * 客户归属机构树Json
	 * 
	 * @return
	 * @throws IOException
	 */
	public void getCusBelongToJson() throws IOException {
		String flag = "insert";
		String belongToType = request.getParameter("BelongToType");
		String belongDeptId = request.getParameter("belongDeptId");
		String belongUserId = request.getParameter("belongUserId");
//		if (belongDeptId != null && !belongDeptId.equals("0") && !belongDeptId.equals("")) {
//			flag = "modify"; // 编辑的情况
//		}

		if(StringUtils.isBlank(belongDeptId)){
			belongDeptId = this.getLoginInfo().getDeptId()+"";
		}
		if(StringUtils.isBlank(belongUserId)){
			belongUserId = this.getLoginInfo().getUserId()+"";
		}

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if (belongToType.equalsIgnoreCase("brDept")) {
			List<SysDept> list = deptFacadeService.getDeptsByDeptIds("2");
			JSONArray array = this.toTreeJson(list, flag, belongDeptId);
			out.print(array);
		} else if (belongToType.equalsIgnoreCase("brUser")) {
			JSONArray array = this.toUserOrDeptJson(flag, belongUserId);
			out.print(array);
		} else if (belongToType.equalsIgnoreCase("brShareUser")) {
			flag = "shareUser";
			JSONArray array = this.toUserOrDeptJson(flag, belongUserId);
			out.print(array);
		}else if (belongToType.equalsIgnoreCase("toAllot")) {
			JSONArray array = this.toAllotJson();
			out.print(array);
		}

	}
	private JSONArray toAllotJson(){
		JSONArray array = new JSONArray();
		List<SysUser> sysUserList = sysUserService.getUserListBelongToSysTeamByUserId(this.getLoginInfo().getUserId());
		for(int i=0;i<sysUserList.size();i++){
			if(sysUserList.get(i).getUserId().equals(this.getLoginInfo().getUserId())){
				sysUserList.remove(i);
			}
		}
		for(SysUser sys:sysUserList) {
				JSONObject obj = new JSONObject();
				obj.put("id", sys.getUserId());
				obj.put("pId", sys.getDeptId());
				obj.put("name", sys.getAccount());
				obj.put("deptName",sys.getDeptId());
				array.add(obj);
		}
//		List<CusBelongToBean> deptlist = deptlist = deptFacadeService.getBelongToUserAndDeptTreeList(this.getLoginInfo().getDeptId()+"");
//		for(int i=0;i<deptlist.size();i++){
//			if(deptlist.get(i).getId().equals(this.getLoginInfo().getUserId())){
//				deptlist.remove(i);
//			}
//		}
//		for(CusBelongToBean dept : deptlist){
//			JSONObject obj = new JSONObject();
//			obj.put("name", dept.getTextName());
//			obj.put("id", dept.getId());
//			obj.put("pId", dept.getParentId());
//			obj.put("deptName", dept.getDeptName());
//			array.add(obj);
//		}

		return array;
	}

	/**
	 * 下属人员树
	 * 
	 * @param flag
	 * @param belongUserId
	 * @return 人员树json
	 */
	private JSONArray toUserOrDeptJson(String flag, String belongUserId) {
		JSONArray array = new JSONArray();
		List<CusBelongToBean> deptlist = null;
		if (flag.equals("shareUser")) {
			deptlist = deptFacadeService.getBelongToUserAll();
		} else {
			deptlist = deptFacadeService.getBelongToUserAndDeptTreeList(this.getLoginInfo().getDeptId()+"");
		}
		Set<Integer> deptSet = new HashSet<Integer>();
		int ii = 0;
		for (CusBelongToBean dept : deptlist) {
			deptSet.add(dept.getId());
		}
		for (CusBelongToBean dept : deptlist) {
			if (!deptSet.contains(dept.getParentId())) {
				ii++;
			}
		}

		for (CusBelongToBean dept : deptlist) {
			JSONObject obj = new JSONObject();
			obj.put("id", dept.getId());
			if (!deptSet.contains(dept.getParentId())) {
				obj.put("pId", 0);
				if (ii == 1) {
					obj.put("open", true);
				}
			} else {
				obj.put("pId", dept.getParentId());
			}

			obj.put("name", dept.getTextName());
			obj.put("flag", dept.getType());
			obj.put("deptName", dept.getDeptName());
			if (dept.getType().equals("D")) {
				obj.put("icon", "../images/deptTreeImage/1.png");
			} else {
				obj.put("icon", "../images/deptTreeImage/2.png");
			}
			if ((flag.equals("modify"))
					&& (dept.getId().toString().equals(belongUserId))
					&& (dept.getType().equals("U"))) {
				obj.put("selected", true);
			}
			array.add(obj);
		}
		return array;
	}

	/**
	 * 下属机构树
	 * 
	 * @param depts
	 * @param flag
	 * @param belongDeptId
	 * @return 机构json
	 */
	public JSONArray toTreeJson(List<SysDept> depts, String flag,
			String belongDeptId) {
		try {
			Set<Integer> deptSet = new HashSet<Integer>();
			int ii = 0;
			for (SysDept sysDept : depts) {
				deptSet.add(sysDept.getDeptId());
			}
			for (SysDept sysDept : depts) {
				if (!deptSet.contains(sysDept.getDeptParentId())) {
					ii++;
				}
			}

			JSONArray jsonArray = new JSONArray();
			if (depts.size() > 0) {
				for (SysDept dept : depts) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", dept.getDeptId());
					if (!deptSet.contains(dept.getDeptParentId())) {
						map.put("pId", 0);
						if (ii == 1) {
							map.put("open", true);
						}
					} else {
						map.put("pId", dept.getDeptParentId());
					}
					map.put("name", dept.getDeptName());
					if (flag.equals("modify")&& dept.getDeptId().toString().equals(belongDeptId)) {
						map.put("selected", true);
					}
					jsonArray.add(map);
				}
			}
			return jsonArray;
		} catch (Exception e) {
			// add by zhangxiang 2012-08-09
			log.error("CustomerBelongToAction toTreeJson action error:"
					+ e.getMessage());
			return null;
		}
	}

	/**
	 * 客户是否已删除
	 */
	public void isDeleteCus() {
		String cusId = request.getParameter("cusId");
		try {
			crmCustomer = (CrmCustomer) crmCustomerService
					.getCrmCustomerById(Integer.parseInt(cusId));
			crmCustomer.setCustomerName(JsUtil.escapeText(crmCustomer
					.getCustomerName()));
			JSONArray jsonArray = new JSONArray();
			jsonArray = JSONArray.fromObject(crmCustomer);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jsonArray);
		} catch (Exception e) {
		}
	}

	/**
	 * 显示共享客户界面
	 * 
	 * @return
	 */
	public String showShareCustomer() {
		String cusIds = request.getParameter("cusIds");
		actionType = request.getParameter("actionType");
		if (actionType.equals("modify")) {
			List<CusShareUserBean> shareUsers = crmCustomerShareService
					.getShareUsersByCusId(cusIds);
			JSONArray jsonArray = new JSONArray();
			jsonArray = JSONArray.fromObject(shareUsers);
			request.setAttribute("shareUsers", jsonArray);
		}
		List<CrmCustomer> cusList = crmCustomerService.selectCusByIds(cusIds);
		request.setAttribute("cusCount", cusList.size());
		request.setAttribute("cusList", cusList);
		return SUCCESS;
	}

	/**
	 * 保存客户共享
	 * 
	 * @return
	 */
	public void saveShareCustomer() {
		PrintWriter out = null;
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("cusIds", request.getParameter("customerIds"));
			map.put("shareUsers", request.getParameter("shareUsers"));
			map.put("type", request.getParameter("actionType"));
			String back = crmCustomerService.saveShareCustomer(map);
			if (back != null) {
				opeventLoginLogService.addLog(1, EnumCustomer.MODEL_ACTION_MODIFYCUSBELONGTO.getValue(), 1, 0);
			} else {
				opeventLoginLogService.addLog(1, EnumCustomer.MODEL_ACTION_MODIFYCUSBELONGTO.getValue(), 1, 1);
			}

			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.print(back);
		} catch (IOException e) {
			e.printStackTrace();
			if(null!=out){
				out.print("error");
			}
		}
	}

	/**
	 * 取消共享客户
	 * 
	 * @return
	 */
	public String cancelShareCus() {
		String cusIds = request.getParameter("cusIds");
		try {
			if (!StringUtil.isEmpty(cusIds)) {
				crmCustomerShareService.cancelShareCus(cusIds);
			}
			opeventLoginLogService.addLog(1,
					EnumCustomer.MODEL_ACTION_CANCELSHARECUS.getValue(), 1, 1);
			return null;
		} catch (Exception e) {
			opeventLoginLogService.addLog(1,
					EnumCustomer.MODEL_ACTION_CANCELSHARECUS.getValue(), 1, 0);
			return ERROR;
		}

	}

	/**
	 * 取消共享用户
	 * 
	 * @return
	 */
	public String cancelShareUser() {
		String cusIds = request.getParameter("cusIds");
		String userIds = request.getParameter("userIds");
		try {
			if (!StringUtil.isEmpty(cusIds) && !StringUtil.isEmpty(userIds)) {
				crmCustomerShareService.cancelShareUser(userIds, cusIds);
			}
			opeventLoginLogService.addLog(1,
					EnumCustomer.MODEL_ACTION_CANCELSHAREUSER.getValue(), 1, 1);
			return null;
		} catch (Exception e) {
			opeventLoginLogService.addLog(1,
					EnumCustomer.MODEL_ACTION_CANCELSHAREUSER.getValue(), 1, 0);
			return ERROR;
		}
	}

	/**
	 * 取消共享用户人员列表
	 * 
	 * @return
	 */
	public String showCancelShareUsers() {
		String cusIds = request.getParameter("cusIds");
		try {
			List<CusShareUserBean> cancelUsers = crmCustomerShareService
					.getShareUsersByCusId(cusIds);
			request.setAttribute("cancelUsers", cancelUsers);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	/**
	 * 导出客户
	 * 
	 * @return
	 */
	public String showExportCustomer() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter out = response.getWriter();
			isInChargeof = false;//deptFacadeService.isInChargeOfDepartment();
			String data = request.getParameter("crmPostData");
			beforeQueryEntityList();
			HSSFWorkbook workbook = new HSSFWorkbook();
			CustomerExportContext ctx = customerExportService.getContext();
			int pageNum = 1;// 页数
			int pageSize = ctx.getPageSize();// 每页大小
			int recordNum = pageSize;// 当前页的记录数
			ctx.setData(data);
			List<CrmExportBean> list = null;
			Integer userId = this.getLoginInfo().getUserId();
			// parameterMap所有参数组装完毕
			Map<Integer, CustomerExportBar> bars = CustomerExportServiceImpl
					.getCustomerExportBar();
			CustomerExportBar bar = new CustomerExportBar();
			bar.setIsRun(1);
			bar.setIsStop(0);
			bar.setCuurRow(0);
			bars.put(userId, bar);

			while (recordNum == pageSize) {
				if (bars.containsKey(userId)
						&& bars.get(userId).getIsStop() > 0)
					return null;
				int offset = (pageNum - 1) * pageSize;
				int limit = pageNum * pageSize;
				ctx.setBatchCount(pageNum);
				list = crmCustomerService.queryExportCustomer(parameterMap,
						offset + 1, limit);
				customerExportService.insertRow(ctx, workbook, list);
				recordNum = list.size();
				list.clear();
				pageNum++;
			}

			if (bar.getIsStop().equals(0)) {
				File file = customerExportService.createFile(request);
				DataOutputStream dos = new DataOutputStream(
						new BufferedOutputStream(new FileOutputStream(file)));
				workbook.write(dos);
				dos.close();
				// 保存勾选字段
				crmModuleExportService.saveExportField(data, "customer");
				out.print("over");
			}
			bar.setIsRun(0);
			bar.setIsStop(1);
			out.close();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("showExportCustomer action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 选择所有客户
	 * 
	 * @throws CloneNotSupportedException
	 */
	public void getCusByCondition() throws CloneNotSupportedException {
		isInChargeof = false;//deptFacadeService.isInChargeOfDepartment();
		beforeQueryEntityList();
		List<Integer> list = crmCustomerService.getCusByCondition(parameterMap);
		;

		String batchType = request.getParameter("batchType");
		int targetId = Integer.parseInt(request.getParameter("targetId"));
	}

	/**
	 * 生日提醒
	 * 
	 * @return
	 */
	public String birthdayRemind() {
		try {
			PageUtil<CrmCustomerBirthday> cusList = crmCustomerService
					.queryBirthdayRemind(this.getPage());
			request.setAttribute("cusList", cusList);
			request.setAttribute("recordCount", this.getPage()
					.getTotalRowsAmount());
			return SUCCESS;
		} catch (Exception e) {
		    e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 未联系客户提醒
	 * 
	 * @return
	 */
	public String unAllocationRemind() {
		parameterMap.put("isBenchRemind", "1");
		return showCrmCustomerListPage();
	}

	/**
	 * 上传头像
	 */
	public void upLoadHead() {
		String type = FileType.getFileByFile(headImage);
		Map<String, String> map = new HashMap<String, String>();
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");

			PrintWriter out = response.getWriter();
		
			if (type == null
					|| (!type.toLowerCase().equals("jpg")
							&& !type.toLowerCase().equals("jpeg")
							&& !type.toLowerCase().equals("gif")
							&& !type.toLowerCase().equals("png") && !type
							.toLowerCase().equals("bmp"))) {
				map.put("error", "目前只支持bmp、jpg、jpeg、gif、png图像文件格式上传！");
				out.append(JSONObject.fromObject(map).toString());
				return;
			}
			
			if (headImage.length() > BUFFERED_SIZE * 1024) {
				map.put("error", "目前不支持超过5M的头像上传！");
				out.append(JSONObject.fromObject(map).toString());
				return;
			}
			String now = DateUtil.convertDateToString("yyyyMMdd", new Date());
			String savePath = sysParamService.getCustomerHeadShowPath() + File.separator
					+ now;

			File file = new File(savePath);
			if (!file.exists()) {// 目录不存在则创建
				file.mkdirs();
			}

			// 生成guid 文件名
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			String newFileName = uuid + "." + type;

			// 将文件上传到服务器
			File imageFile = new File(savePath + File.separator + newFileName);
			copy(headImage, imageFile);

			// 得到图片长宽
			BufferedImage buff = ImageIO.read(headImage);
			map.put("width", buff.getWidth() + "");
			map.put("height", buff.getHeight() + "");

			// 返回成功信息
			map.put("fname", newFileName);
			map.put("folder", now);
			map.put("fsize", headImage.length() + "");
			map.put("fullPath", savePath + File.separator + newFileName);
			out.append(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			try {
				HttpServletResponse response = ServletActionContext
						.getResponse();
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				map.put("error", "上传失败");
				out.append(JSONObject.fromObject(map).toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 复制文件
	 * 
	 * @param src
	 * @param target
	 */
	private void copy(File src, File target) {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(src),
					BUFFERED_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(target),
					BUFFERED_SIZE);
			byte[] bs = new byte[BUFFERED_SIZE];
			int i;
			while ((i = in.read(bs)) > 0) {
				out.write(bs, 0, i);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 得到联系客户
	 * 
	 * @return
	 */
	public String getRecentlyCustomer() {
		isInChargeof =false;// deptFacadeService.isInChargeOfDepartment();
		String userIds = "";
		String deptIds = "";
		if (isInChargeof) {
			deptIds = getCurrentUserInChargeOfDeptIds();
			if (StringUtil.isEmpty(deptIds)) {
				deptIds = "-1";
			}
			userIds = getCurrentInChargeUserIds();
		} else {
			deptIds = "-1";
			userIds = this.getLoginInfo().getUserId().toString();
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userIds", userIds);
		map.put("deptIds", deptIds);
		map.put("key", telNum);
		this.recentCustomers = this.crmCustomerService
				.getRecentlyCustomers(map);

		if (this.recentCustomers.size() > 0)
			return SUCCESS;
		else
			return null;
	}
	
	/**
	 * 判断客户是否有未结算的贷款
	 */
	public void isNotEndLoan(){
	    try {
	        HttpServletResponse response = ServletActionContext.getResponse();
	        PrintWriter out = response.getWriter();
	        //true 表示 还有未结清  false 表示都结清了
	        String cuIds=request.getParameter("cusIds");
	        if(!StringUtil.isBlank(cuIds)){
	            String[] cusIds=cuIds.split(",");
	            for (int i = 0; i < cusIds.length; i++) {
	                boolean bool=lnLoanService.isNotEndLoan(Integer.parseInt(cusIds[i]));
	                if(bool){
	                    out.print(bool);
	                    break;
	                }
	            }
	        }
        } catch (Exception e) {e.printStackTrace();}
	}

	private HashMap<String, Object> buildLoanCheckBoxMessage(String... param) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		for (int i = 0; i < param.length; i++) {
			paramMap.put("dictionaryName", param[i]);
			List<LnLoanInfoDictionary> lnLoanInfoDictionaryList = lnLoanInfoDictionaryService
					.selectLoanInfoDictionaryList(paramMap);
			resultMap.put(param[i], lnLoanInfoDictionaryList);
			paramMap.remove("dictionaryKey");
		}
		return resultMap;
	}
}
