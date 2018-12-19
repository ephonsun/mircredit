/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :电话通话
 * Author     :zsw
 * Create Date:2012-5-24
 */
package com.banger.mobile.webapp.action.talk;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.facade.data.CustomerDataService;
import com.banger.mobile.facade.data.DataAudioService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.constants.CallTypeConstants;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.customer.CustomerExtendFieldBean;
import com.banger.mobile.domain.model.microTask.TskMicroTaskTarget;
import com.banger.mobile.domain.model.phoneConfig.PhoneConfig;
import com.banger.mobile.domain.model.record.PhoneRecordBean;
import com.banger.mobile.domain.model.record.RecordDetail;
import com.banger.mobile.domain.model.system.CdCity;
import com.banger.mobile.domain.model.templateField.CrmTemplateField;
import com.banger.mobile.domain.model.tskContact.TalkTask;
import com.banger.mobile.domain.model.tskTaskPlan.TalkTaskPlan;
import com.banger.mobile.domain.model.user.SysTalkUserBean;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.microTask.TskMicroTaskTargetService;
import com.banger.mobile.facade.phoneConfig.PhoneConfigService;
import com.banger.mobile.facade.recbiztype.RecbizTypeService;
import com.banger.mobile.facade.record.RecordInfoService;
import com.banger.mobile.facade.sysWhiteList.SysWhiteListService;
import com.banger.mobile.facade.system.CdSystemService;
import com.banger.mobile.facade.system.CommProgressService;
import com.banger.mobile.facade.talk.TelephoneTalkService;
import com.banger.mobile.facade.template.CrmTemplateService;
import com.banger.mobile.facade.tskContact.TskContactService;
import com.banger.mobile.facade.tskContact.TskPlanService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.facade.user.SysUserStatusService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.DebugUtil;
import com.banger.mobile.util.JsUtil;
import com.banger.mobile.util.ReflectorUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author zsw
 * @version $Id: DailNumberAction.java,v 0.1 Aug 22, 2012 3:05:35 PM zsw Exp $
 */
public class TelephoneTalkAction extends BaseAction {
	private static final long serialVersionUID = 3767962603982908636L;
	private final String AccountKey = "account"; // 帐号
	private final String NumberKey = "number"; // 来电号码
	private final String TypeKey = "talktype"; // 通话类型
	private final String TabKey = "tabId"; // 选项卡
	private final String CusIdKey = "cusId";
	private final String CodeKey = "code";
	private String tabId; // 页卡的ID
	private CdSystemService cd; // 代码表
	private CustomerExtendFieldBean exField; // 自定义
	private CrmCustomer crmCustomer; // 客户
	private TelephoneTalkService talk; // 通话服务类
	private CrmTemplateService temp; // 模板
	private DeptFacadeService dept; // 机构
	private SysUserService login; // 登入服务
	private SysUserStatusService status; // 登入状态
	private List<CrmCustomer> custs; // 客户
	private PhoneRecordBean record; // 通话信息
	private PageUtil<SysTalkUserBean> forwardUsers; // 来电转接用户
	private RecbizTypeService bizType; // 业务类型service
	private CommProgressService progress; // 沟通进度service
	private RecordInfoService recordService; // 录音信息service
	private CrmCustomerService crmCustomerService; // 客户Service
	private PhoneConfigService phoneConfigService; // 电话配置Service
	private String tabTitle; // 页卡标题
	private Integer isMobile; // 是否为手机
	private String phoneAreaName; // 来电号码地区名称
	private Integer hasPerssion; // 有没有权限
	private Boolean isShareCus; // 是否为共享客户
	private String renumber; // 回拨号码
	private String account;
	private PhoneConfig phoneConfig; // 话机配置
	private Integer taskId; // 任务执行ID
	private String custJson;
	private RecordDetail recordDetail;
	private String phoneNumber;
	private Boolean isWhiteTelNumber; // 是否为通话白名单号码
	private Integer closeType; // 通话结束关闭类型
	private TskPlanService tskPlanService; // 联系计划service
	private TskContactService tskContactService; // 联系任务service
	private SysWhiteListService sysWhiteListService; // 通话白名单
	private TalkTaskPlan taskPlan;
	private TalkTask tskTask;
	private TskMicroTaskTargetService tskMicroTaskTargetService;
    private CustomerDataService customerDataService;
    private DataAudioService dataAudioService;

    public void setDataAudioService(DataAudioService dataAudioService) {
        this.dataAudioService = dataAudioService;
    }

    public void setCustomerDataService(CustomerDataService customerDataService) {
        this.customerDataService = customerDataService;
    }

	public Integer getCloseType()
	{
		return closeType;
	}

	public void setCloseType(Integer closeType)
	{
		this.closeType = closeType;
	}

	public String getActionType()
	{
		return "modify";
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public TalkTaskPlan getTaskPlan()
	{
		return taskPlan;
	}

	public void setTaskPlan(TalkTaskPlan taskPlan)
	{
		this.taskPlan = taskPlan;
	}

	public TalkTask getTskTask()
	{
		return tskTask;
	}

	public void setTskTask(TalkTask tskTask)
	{
		this.tskTask = tskTask;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public RecordDetail getRecordDetail()
	{
		return recordDetail;
	}

	public void setRecordDetail(RecordDetail recordDetail)
	{
		this.recordDetail = recordDetail;
	}

	public String getCustJson()
	{
		return custJson;
	}

	public TskPlanService getTskPlanService()
	{
		return tskPlanService;
	}

	public void setTskPlanService(TskPlanService tskPlanService)
	{
		this.tskPlanService = tskPlanService;
	}

	public TskContactService getTskContactService()
	{
		return tskContactService;
	}

	public void setTskContactService(TskContactService tskContactService)
	{
		this.tskContactService = tskContactService;
	}

	public void setCustJson(String custJson)
	{
		this.custJson = custJson;
	}

	public Integer getTaskId()
	{
		return taskId;
	}

	public void setTaskId(Integer taskId)
	{
		this.taskId = taskId;
	}

	public Boolean getShareCus()
	{
		return this.isShareCus;
	}

	public Boolean getWhiteTelNumber()
	{
		return this.isWhiteTelNumber;
	}

	public String getAccount()
	{
		return account;
	}

	public void setAccount(String account)
	{
		this.account = account;
	}

	public String getRenumber()
	{
		return renumber;
	}

	public void setRenumber(String renumber)
	{
		this.renumber = renumber;
	}

	public Integer getHasPerssion()
	{
		return hasPerssion;
	}

	public void setHasPerssion(Integer hasPerssion)
	{
		this.hasPerssion = hasPerssion;
	}

	public String getPhoneAreaName()
	{
		return phoneAreaName;
	}

	public void setPhoneAreaName(String phoneAreaName)
	{
		this.phoneAreaName = phoneAreaName;
	}

	public String getCityCode()
	{
		return this.getPhoneConfig().getCityCode();
	}

	public Integer getIsMobile()
	{
		return isMobile;
	}

	public void setIsMobile(Integer isMobile)
	{
		this.isMobile = isMobile;
	}

	public String getTabTitle()
	{
		return tabTitle;
	}

	public void setTabTitle(String tabTitle)
	{
		this.tabTitle = tabTitle;
	}

	public PhoneConfigService getPhoneConfigService()
	{
		return phoneConfigService;
	}

	public void setPhoneConfigService(PhoneConfigService phoneConfigService)
	{
		this.phoneConfigService = phoneConfigService;
	}

	public String getTabId()
	{
		return tabId;
	}

	public void setTabId(String tabId)
	{
		this.tabId = tabId;
	}

	public PhoneRecordBean getRecord()
	{
		return record;
	}

	public void setRecord(PhoneRecordBean record)
	{
		this.record = record;
	}

	public CrmCustomerService getCrmCustomerService()
	{
		return crmCustomerService;
	}

	public void setCrmCustomerService(CrmCustomerService crmCustomerService)
	{
		this.crmCustomerService = crmCustomerService;
	}

	public RecbizTypeService getBizType()
	{
		return bizType;
	}

	public void setBizType(RecbizTypeService bizType)
	{
		this.bizType = bizType;
	}

	public CommProgressService getProgress()
	{
		return progress;
	}

	public void setProgress(CommProgressService progress)
	{
		this.progress = progress;
	}

	public List<CrmCustomer> getCusts()
	{
		return custs;
	}

	public void setCusts(List<CrmCustomer> custs)
	{
		this.custs = custs;
	}

	public SysUserService getLogin()
	{
		return login;
	}

	public void setLogin(SysUserService login)
	{
		this.login = login;
	}

	public DeptFacadeService getDept()
	{
		return dept;
	}

	public void setDept(DeptFacadeService dept)
	{
		this.dept = dept;
	}

	public CrmTemplateService getTemp()
	{
		return temp;
	}

	public void setTemp(CrmTemplateService temp)
	{
		this.temp = temp;
	}

	public TelephoneTalkService getTalk()
	{
		return talk;
	}

	public void setTalk(TelephoneTalkService talk)
	{
		this.talk = talk;
	}

	public CrmCustomer getCrmCustomer()
	{
		return crmCustomer;
	}

	public void setCrmCustomer(CrmCustomer crmCustomer)
	{
		this.crmCustomer = crmCustomer;
	}

	public CdSystemService getCd()
	{
		return cd;
	}

	public void setCd(CdSystemService cd)
	{
		this.cd = cd;
	}

	public CustomerExtendFieldBean getExField()
	{
		return exField;
	}

	public void setExField(CustomerExtendFieldBean exField)
	{
		this.exField = exField;
	}

	public RecordInfoService getRecordService()
	{
		return recordService;
	}

	public void setRecordService(RecordInfoService recordService)
	{
		this.recordService = recordService;
	}

	public SysUserStatusService getStatus()
	{
		return status;
	}

	public void setStatus(SysUserStatusService status)
	{
		this.status = status;
	}

	public PageUtil<SysTalkUserBean> getForwardUsers()
	{
		return forwardUsers;
	}

	public void setForwardUsers(PageUtil<SysTalkUserBean> forwardUsers)
	{
		this.forwardUsers = forwardUsers;
	}

	public void setSysWhiteListService(SysWhiteListService sysWhiteListService)
	{
		this.sysWhiteListService = sysWhiteListService;
	}

	public TskMicroTaskTargetService getTskMicroTaskTargetService()
	{
		return tskMicroTaskTargetService;
	}

	public void setTskMicroTaskTargetService(
			TskMicroTaskTargetService tskMicroTaskTargetService)
	{
		this.tskMicroTaskTargetService = tskMicroTaskTargetService;
	}

	public Integer getCustCount()
	{
		if (this.custs != null)
			return this.custs.size();
		else
			return 0;
	}

	public TelephoneTalkAction()
	{
		crmCustomer = new CrmCustomer(); // 客户
		crmCustomer.setCustomerId(0);
		exField = new CustomerExtendFieldBean();
		custs = new ArrayList<CrmCustomer>();
		record = new PhoneRecordBean();
		isMobile = new Integer(0);
		isShareCus = false;
		isWhiteTelNumber = false;
		phoneAreaName = "";
		hasPerssion = new Integer(1);
	}

	/**
	 * 显示通话窗体
	 * 
	 * @return
	 */
	public String getCallInPage()
	{
		return SUCCESS;
	}

	/**
	 * 得到来电提示页面
	 * 
	 * @return
	 */
	public String getTalkIncomingPopup()
	{
		String number = this.request.getParameter(NumberKey);
		if (!StringUtil.isNullOrEmpty(number))
		{
			String telNum = this.formatIncomingNumber(number);
			this.custs = talk.getCrmCustomerByTel(telNum);

			if (this.custs.size() == 1)
			{
				this.crmCustomer = this.custs.get(0);
			} else
			{
				for (CrmCustomer cust : this.custs)
				{
					if (!StringUtil.isNullOrEmpty(cust.getCompany()))
						this.crmCustomer = cust;
				}
			}

			if (this.crmCustomer.getCustomerId() > 0)
			{
				this.exField = crmCustomerService
						.getCustomizedFieldById(this.crmCustomer
								.getCustomerId());

				this.hasPerssion = crmCustomerService.checkPermission(
						crmCustomer, dept.getInChargeOfDeptIds());
				this.isShareCus = crmCustomerService.checkShareCus(crmCustomer
						.getCustomerId(), this.getLoginInfo().getUserId());
				String customerTypeName = cd.getCusTypeNameById(crmCustomer
						.getCustomerTypeId());
				crmCustomer.setCustomerTypeName(customerTypeName);
				String customerIndustryName = cd
						.getIndustryNameById(crmCustomer
								.getCustomerIndustryId());
				crmCustomer.setCustomerIndustryName(customerIndustryName);
			}

			this.record.setIncomingNumber(telNum);
			String areaName = "";
			if (talk.isMobileNumber(telNum))
			{
				this.isMobile = new Integer(1);
				String areaCode = talk.getAreaCodeByNumber(telNum.substring(0,
						7));
				if (!StringUtil.isNullOrEmpty(areaCode))
					areaName = talk.getPhoneAreaNameByCode(areaCode);
			} else if (telNum.length() > 6)
			{
				areaName = talk.getPhoneAreaNameByCode(telNum.substring(0, 4));
				if (StringUtil.isNullOrEmpty(areaName))
					areaName = talk.getPhoneAreaNameByCode(telNum.substring(0,
							3));
			}
			if (!StringUtil.isNullOrEmpty(areaName))
				this.phoneAreaName = areaName;
			return SUCCESS;
		}
		return ERROR;
	}

	/**
	 * 选择客户时，得到Json数据
	 * 
	 * @return
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public String getCustomerJson() throws IOException,
			IllegalArgumentException, IllegalAccessException
	{
		Integer cusId = Integer.parseInt(this.request.getParameter("id"));
		crmCustomer = (CrmCustomer) crmCustomerService
				.getCrmCustomerById(cusId);
		exField = crmCustomerService.getCustomizedFieldById(cusId);
		DateUtil.format(crmCustomer); // 格式化日期属性
		DateUtil.format(exField); // 格式化日期属性
		JsUtil.escapeEntity(crmCustomer);

		JSONObject jo = new JSONObject();

		Map<String, Object> cusMap = new HashMap<String, Object>();
		cusMap.put("customerId", crmCustomer.getCustomerId());
		cusMap.put("templateIds", crmCustomer.getTemplateIds());
		cusMap.put("customerName", crmCustomer.getCustomerName());
		cusMap.put("sex", crmCustomer.getSex());
		cusMap.put("customerNo", crmCustomer.getCustomerNo());
		cusMap.put("customerTitle", crmCustomer.getCustomerTitle());
		cusMap.put("age", crmCustomer.getAge());
		String birthday = (crmCustomer.getBirthday() != null) ? crmCustomer
				.getBirthday().toString() : "";
		cusMap.put("birthday", birthday);
		cusMap.put("customerTypeId", crmCustomer.getCustomerTypeId());
		cusMap.put("customerIndustryId", crmCustomer.getCustomerIndustryId());
		cusMap.put("idCard", crmCustomer.getIdCard());
		cusMap.put("company", crmCustomer.getCompany());
		cusMap.put("remark", crmCustomer.getRemark());
		cusMap.put("province", crmCustomer.getProvince());
		cusMap.put("city", crmCustomer.getCity());
		cusMap.put("address", crmCustomer.getAddress());
		cusMap.put("mobilePhone1", crmCustomer.getMobilePhone1());
		cusMap.put("mobilePhone2", crmCustomer.getMobilePhone2());
		cusMap.put("phone", crmCustomer.getPhone());
		cusMap.put("defaultPhoneType", crmCustomer.getDefaultPhoneType());
		cusMap.put("phoneExt", crmCustomer.getPhoneExt());
		cusMap.put("fax", crmCustomer.getFax());
		cusMap.put("faxExt", crmCustomer.getFaxExt());
		cusMap.put("email", crmCustomer.getEmail());
		cusMap.put("deptName", crmCustomer.getDeptName());
		cusMap.put("userName", crmCustomer.getUserName());
		cusMap.put("belongDeptId", crmCustomer.getBelongDeptId());
		cusMap.put("belongUserId", crmCustomer.getBelongUserId());
		cusMap.put("headshow", crmCustomer.getHeadshow());
		cusMap.put("isReceiveSms", crmCustomer.getIsReceiveSms());

		String customerTypeName = cd.getCusTypeNameById(crmCustomer
				.getCustomerTypeId());
		crmCustomer.setCustomerTypeName(customerTypeName);
		String customerIndustryName = cd.getIndustryNameById(crmCustomer
				.getCustomerIndustryId());
		crmCustomer.setCustomerIndustryName(customerIndustryName);
		cusMap.put("customerIndustryName",
				crmCustomer.getCustomerIndustryName());
		cusMap.put("customerTypeName", crmCustomer.getCustomerTypeName());

		/* 是否有客户权限 */
		Integer hasRight = crmCustomerService.checkPermission(crmCustomer,
				dept.getInChargeOfDeptIds());
		this.isShareCus = crmCustomerService.checkShareCus(
				crmCustomer.getCustomerId(), this.getLoginInfo().getUserId());
		cusMap.put("hasPerssion", hasRight);
		cusMap.put("shareCus", this.isShareCus);

		JSONObject cusjo = new JSONObject();
		cusjo.putAll(cusMap);

		Map<String, Object> exMap = new HashMap<String, Object>(); // 自定义字段
		List<CrmTemplateField> fields = temp.getAllTemplateFields();
		for (CrmTemplateField field : fields)
		{
			Object exVal = ReflectorUtil.getPropertyStringValue(exField,
					field.getFieldName());
			exMap.put(field.getFieldName(), exVal);
		}

		JSONObject exjo = new JSONObject();
		exjo.putAll(exMap);

		JSONArray cityja = new JSONArray();

		if (crmCustomer.getProvince() != null
				&& !"".equals(crmCustomer.getProvince())) // 城市
		{
			List<CdCity> cList = cd.getCitys(crmCustomer.getProvince());
			Map<String, Object> map = new HashMap<String, Object>();

			for (CdCity c : cList)
			{
				map.put("code", c.getCode());
				map.put("name", c.getShortName());
				cityja.add(map);
			}
		}

		jo.put("customer", cusjo);
		jo.put("fields", exjo);
		jo.put("city", cityja);

		PrintWriter out = this.getResponse().getWriter();
		out.print(jo.toString());
		return null;
	}

	/**
	 * 保存通话信息
	 * 
	 * @return
	 * @throws IOException
	 */
	public String saveTelephoneTalk() throws IOException
	{
		try
		{
			PrintWriter out = this.getResponse().getWriter();
			// 是否在行客户
			if (crmCustomer.getCustomerId() == null
					|| crmCustomer.getCustomerId().intValue() < 1)
			{
				crmCustomer.setBelongDeptId(this.getLoginInfo().getDeptId());
				crmCustomer.setBelongUserId(this.getLoginInfo().getUserId());
			}
			DebugUtil
					.write("---------------------------通话窗口保存客户信息-----------------------------------");
			this.saveCustomer();

			DebugUtil
					.write("---------------------------通话窗口保存联系记录-----------------------------------");
			this.saveTalkRecord();
			DebugUtil
					.write("---------------------------通话窗口完成计划-----------------------------------");
			// saveNextConnectTaskPlan();
			DebugUtil
					.write("---------------------------更新任务目标信息-----------------------------------");
			if (taskId != null && taskId > 0)
			{
				this.saveTaskTarget();
			}
			out.print("success");
		} catch (Exception e)
		{
			e.printStackTrace();
			PrintWriter out = this.getResponse().getWriter();
			out.print("error");
		}
		return null;
	}

	/**
	 * 执行营销任务拨打非在行客户时 将新增的customerId赋给任务目标
	 */
	private void saveTaskTarget()
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("taskTargetId", taskId);
		params.put("cusId", this.record.getCustomerId());
		tskMicroTaskTargetService.editTskTaskTargetInfo(params);
	}

	/**
	 * 关闭通话窗口保存记录
	 * 
	 * @return
	 * @throws IOException
	 */
	public String closeSaveTelephoneTalk() throws IOException
	{
		try
		{
			PrintWriter out = this.getResponse().getWriter();
			DebugUtil
					.write("---------------------------关闭通话窗口保存联系记录-----------------------------------");
			this.saveTalkRecord();
			DebugUtil
					.write("---------------------------关闭通话窗口完成计划-----------------------------------");
			// saveNextConnectTaskPlan();
			DebugUtil
					.write("---------------------------更新任务目标信息-----------------------------------");
			if (taskId != null && taskId > 0)
				this.saveTaskTarget();
			out.print("success");
		} catch (Exception e)
		{
			e.printStackTrace();
			PrintWriter out = this.getResponse().getWriter();
			out.print("error");
		}
		return null;
	}

	// private void saveNextConnectTaskPlan() {
	// /***********************保存下次联系************************/
	// try {
	// Integer customerId=-1; //客户id
	// if(crmCustomer.getCustomerId() != null && crmCustomer.getCustomerId() >
	// 0){//有客户id
	// customerId=crmCustomer.getCustomerId();
	// }else if(record.getCustomerId()!=null && record.getCustomerId()>0){
	// customerId=record.getCustomerId();
	// }
	//
	// if (record.getRemark() != null && !record.getRemark().equals(""))
	// {//有联系备注
	// String fflag = request.getParameter("talkFinishFalg");//完成标志
	// // TskPlanTarget target =
	// tskPlanService.getTskPlanTargetByRecordNo(record.getRecordNo());
	// if(target!=null){//联系记录先上传的
	// if (fflag != null && fflag.equals("finish")) {
	// target.setFinishLevel(2);//难以联系
	// }else{
	// target.setFinishLevel(1);//完成
	// }
	// target.setUpdateUser(this.getLoginInfo().getUserId());
	// tskPlanTargetService.updateTskPlanTarget(target);
	// DebugUtil.write("TelephoneTalkAction 完成计划update 成功：recordNo=="+record.getRecordNo()+",targetId=="+target.getTargetId());
	// }else{
	// PhoneRecordBean bean =
	// recordInfoService.getRecordByNo(record.getRecordNo());
	// Integer time = sysParamService.getEffectiveTalkTime();//有效通话时长
	// time = time == null ? 0 : time;
	// Integer callTime = 0;
	// if (bean != null && bean.getIsDel() == 0) {
	// if (bean.getCallTime() != null && bean.getCallTime() > 0)
	// callTime = bean.getCallTime();
	// if (callTime >= time) {
	// Integer level = 0;
	// if (fflag != null && fflag.equals("finish")) {//难以联系
	// level=2;
	// }else level=1;
	// if (record.getCustomerId() != null && record.getCustomerId() > 0) {
	// tskPlanService.finishPlanInTalk(record.getCustomerId(), level,
	// record.getRecordNo(),this.getLoginInfo().getUserId(),bean.getStartDate());
	// //完成计划
	// tskContactService.finishTaskInTalk(record.getCustomerId());//完成任务
	// DebugUtil.write("TelephoneTalkAction 完成计划 内部客户 finish成功：recordNo=="+record.getRecordNo()+",customerId=="+record.getCustomerId()+",userId=="+this.getLoginInfo().getUserId());
	// } else {
	// if (record.getRemotePhone() != null &&
	// !record.getRemotePhone().equals("")) {
	// tskPlanService.finishPlanInTalk(record.getRemotePhone(), level,
	// record.getRecordNo(),this.getLoginInfo().getUserId(),bean.getStartDate());
	// //计划完成
	// tskContactService.finishTaskInTalk(record.getRemotePhone());
	// DebugUtil.write("TelephoneTalkAction 完成计划 外部客户 finish成功：recordNo=="+record.getRecordNo()+",phoneNo=="+record.getRemotePhone()+",userId=="+this.getLoginInfo().getUserId());
	// }
	// }
	// }
	// }
	// }
	//
	// String sflag = request.getParameter("talkSaveNextFalg");
	// if (sflag != null && sflag.equals("plan")) {//下次联系计划
	// if (taskPlan != null && taskPlan.getPlanDate() != null
	// && !taskPlan.getPlanDate().equals("") && taskPlan.getRemark() != null
	// && !taskPlan.getRemark().equals("")) {
	// if(customerId>0) tskPlanService.insertTalkTaskPlan(taskPlan, customerId);
	// }
	// }
	// }
	// String tflag = request.getParameter("talkNextTaskFalg");
	// if (tflag != null && tflag.equals("task")) {//下次联系任务
	// if (crmCustomer.getCustomerId() != null && tskTask != null
	// && tskTask.getStartDate() != null && tskTask.getEndDate() != null) {
	// if(customerId>0) tskContactService.saveTaskInTalk(tskTask,customerId);
	// }
	// }
	// } catch (Exception e) {
	// DebugUtil.write("TelephoneTalkAction 完成计划 失败：recordNo=="+record.getRecordNo());
	// DebugUtil.write(SerializeUtil.toXML(record));
	// for (StackTraceElement er : e.getStackTrace()) {
	// DebugUtil.write(er.toString());
	// }
	// }
	// }
	/**
	 * 处理外拨号码
	 * 
	 * @return
	 * @throws IOException
	 */
	public String getDialNumber() throws IOException
	{
		String number = this.request.getParameter(NumberKey);
		if (!StringUtil.isNullOrEmpty(number))
		{
			String tel = this.outNumber(number);
			PrintWriter out = this.getResponse().getWriter();
			out.print(tel);
		}
		return null;
	}

	/**
	 * 外拨号码处理
	 * 
	 * @param number
	 * @return
	 */
	private String outNumber(String number)
	{
		if (number != null && !"".equals(number))
		{
			Integer userId = this.getLoginInfo().getUserId();
			PhoneConfig config = phoneConfigService.query(userId);

			String outNumber = "";
			boolean inline = false; // 是否为内线
			if (talk.isMobileNumber(number)) // 判断是否为手机号码
			{
				if (number.length() == 11)
				{
					String areaCode = talk.getAreaCodeByNumber(number
							.substring(0, 7));
					if (config.getCityCode() != null && areaCode != null
							&& !"".equals(config.getCityCode())) // 判断是否为本地手机
					{
						if (!config.getCityCode().equals(areaCode))
						{
							outNumber = "0" + number;
						}
					}
				}
				if (outNumber.equals(""))
					outNumber = number;
			} else if (number.length() > 6)
			{
				if (config.getCityCode() != null
						&& !"".equals(config.getCityCode()))
				{
					int cityLen = config.getCityCode().length();
					if (number.length() > cityLen
							&& number.substring(0, cityLen).equals(
									config.getCityCode()))
					{
						outNumber = number.substring(cityLen);
					} else
					{
						outNumber = number;
					}
				} else
				{
					outNumber = number;
				}
			} else
			{
				int inLen = 0;
				if (config.getInsiseExtLength() != null
						&& !"".equals(config.getInsiseExtLength()))
					inLen = Integer.parseInt(config.getInsiseExtLength());
				if (number.length() == inLen)
					inline = true;
				outNumber = number;
			}

			if (inline) // 是否拨分机
			{
				return outNumber;
			} else
			{
				if (config.getIsIpCall() != null
						&& config.getIsIpCall().intValue() > 0)
				{
					if (config.getIpNumber() != null
							&& !"".equals(config.getIpNumber()))
					{
						outNumber = config.getIpNumber() + outNumber;
					}
				}
				if (config.getOutsideCallCode() != null
						&& !"".equals(config.getOutsideCallCode()))
				{
					outNumber = config.getOutsideCallCode() + outNumber;
				}
			}
			return outNumber;
		}
		return "";
	}

	/**
	 * 格式化外拨号码
	 * 
	 * @param number
	 * @return
	 */
	private String formatOutNumber(String number)
	{
		if (number != null && !"".equals(number))
		{
			number = number.replace("#", "").replace("*", "");
			Integer userId = this.getLoginInfo().getUserId();
			PhoneConfig config = phoneConfigService.query(userId);

			String outNumber = number;
			boolean inline = false; // 是否为内线

			if (config.getOutsideCallCode() != null
					&& !"".equals(config.getOutsideCallCode()))
			{
				int outLen = config.getOutsideCallCode().length();
				if (number.length() > outLen
						&& number.substring(0, outLen).equals(
								config.getOutsideCallCode()))
				{
					outNumber = number.substring(outLen);
				}
			}

			if (outNumber.length() < 7)
			{
				int inLen = 0;
				if (config.getInsiseExtLength() != null
						&& !"".equals(config.getInsiseExtLength()))
					inLen = Integer.parseInt(config.getInsiseExtLength());
				if (outNumber.length() == inLen)
					inline = true;
			}

			if (inline) // 是否拨分机
			{
				return outNumber;
			} else
			{
				if (config.getIsIpCall() != null
						&& config.getIsIpCall().intValue() > 0)
				{
					if (config.getIpNumber() != null
							&& !"".equals(config.getIpNumber()))
					{
						int ipLen = config.getIpNumber().length();
						if (outNumber.length() > ipLen
								&& outNumber.substring(0, ipLen).equals(
										config.getIpNumber()))
						{
							outNumber = outNumber.substring(ipLen);
						}
					}
				}
				if (talk.isMobileNumber(outNumber)) // 判断是否为手机号码
				{
					outNumber = (outNumber.length() == 12) ? outNumber
							.substring(1) : outNumber;
				} else if (outNumber.length() > 6)
				{
					if (config.getCityCode() != null
							&& !"".equals(config.getCityCode()))
					{
						int cityLen = config.getCityCode().length();
						if (outNumber.length() > cityLen
								&& outNumber.substring(0, cityLen).equals(
										config.getCityCode()))
						{
							outNumber = outNumber.substring(cityLen);
						}
					}
				}

			}
			return outNumber;
		}
		return "";
	}

	/**
	 * 获得通话配置
	 * 
	 * @return
	 */
	private PhoneConfig getPhoneConfig()
	{
		if (this.phoneConfig == null)
		{
			Integer userId = this.getLoginInfo().getUserId();
			this.phoneConfig = phoneConfigService.query(userId);
		}
		return this.phoneConfig;
	}

	/**
	 * 处理来电号码
	 * 
	 * @param number
	 * @return
	 */
	private String formatIncomingNumber(String number)
	{
		if (number != null && !"".equals(number))
		{
			number = number.replace("#", "").replace("*", "");
			PhoneConfig config = this.getPhoneConfig();

			String outNumber = (config.getOutNumber() != null) ? config
					.getOutNumber() : "";
			int outLen = outNumber.length();
			String inNumber = (outLen > 0 && number.length() > outLen && number
					.substring(0, outLen).equals(outNumber)) // 移除来电出局号
			? number.substring(outLen)
					: number;
			if (talk.isMobileNumber(inNumber)) // 判断是否为手机号码
			{
				return (inNumber.length() == 12) ? inNumber.substring(1)
						: inNumber;
			} else if (inNumber.length() > 6) // 是否为固话
			{
				if (config.getCityCode() != null
						&& !"".equals(config.getCityCode())) // 判断是否为本地固话
				{
					int cityLen = config.getCityCode().length();
					if (inNumber.length() > cityLen
							&& inNumber.substring(0, cityLen).equals(
									config.getCityCode()))
						return inNumber.substring(cityLen);
				}
				return inNumber;
			} else
			{
				return inNumber;
			}
		}
		return "";
	}

	/**
	 * 保存客户
	 */
	private void saveCustomer()
	{
		try
		{
			if (crmCustomer != null)
			{
				if (crmCustomer.getCustomerId() > 0)
				{
					this.hasPerssion = crmCustomerService.checkPermission(
							crmCustomer, dept.getInChargeOfDeptIds());
					this.isShareCus = crmCustomerService.checkShareCus(
							crmCustomer.getCustomerId(), this.getLoginInfo()
									.getUserId());
					if (this.hasPerssion.intValue() > 0 || this.isShareCus)
					{
						crmCustomerService.saveCustomer(crmCustomer, exField);
						DebugUtil.write("customerId==="
								+ crmCustomer.getCustomerId()
								+ "customerName==="
								+ crmCustomer.getCustomerName() + ",修改客户成功");
					}
				} else
				{
					// crmCustomer.setIsImport(0);
					crmCustomerService.saveCustomer(crmCustomer, exField);
					DebugUtil.write("customerId==="
							+ crmCustomer.getCustomerId() + "customerName==="
							+ crmCustomer.getCustomerName() + ",新增客户成功");
				}

				this.record.setCustomerId(crmCustomer.getCustomerId());
				recordService.relationRecord(0, crmCustomer.getCustomerId(),
						false);
				this.record.setCustomerName(crmCustomer.getCustomerName());

                // 关联客户资料
                PhoneRecordBean r = recordService.getRecordByNo(this.record.getRecordNo());
                customerDataService.relationRecord(r.getRecordInfoId(), crmCustomer.getCustomerId());
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			DebugUtil.write("customerId===" + crmCustomer.getCustomerId()
					+ "customerName===" + crmCustomer.getCustomerName()
					+ ",保存操作出错");
			for (StackTraceElement se : e.getStackTrace())
			{
				DebugUtil.write(se.toString());
			}
		}
	}

	/**
	 * 保存沟通记录
	 */
	private void saveTalkRecord()
	{
		// String fflag = request.getParameter("talkFinishFalg");//完成标志
		// JSONObject joo = new JSONObject();
		// if (fflag != null && fflag.equals("finish")) {
		// joo.put("task_finish_level", 2);//难以联系
		// }else{
		// joo.put("task_finish_level", 1);//完成
		// }
		// record.setRecordExtendInfo(joo.toString());

		this.record.setUserId(this.getLoginInfo().getUserId());
		this.record.setIsDel(1);
		this.record.setIsArchived(0);
		this.record.setCreateUser(this.getLoginInfo().getUserId());
		this.record.setUpdateUser(this.getLoginInfo().getUserId());
		this.record.setIsRead(0);
		// this.record.setBizType(0);
		// if(record.getIsSpeak()==null) record.setIsSpeak(0);
		if (this.crmCustomer.getCustomerId() > 0)
			this.record.setCustomerId(this.crmCustomer.getCustomerId());

		this.recordService.saveTalkRecordByNo(this.record);// 保存联系记录

        //更新客户资料录音备注
        dataAudioService.updateAudioRemarkByRecordNo(this.record.getRecordNo(), this.record.getRemark());

		/************** 保存业务类型 ***************/
		// if(record!=null && record.getBizTypeName()!=null &&
		// !"".equals(record.getBizTypeName())){
		// PhoneRecordBean newRecord =
		// recordService.getRecordByNo(record.getRecordNo());
		// if(newRecord!=null && newRecord.getRecordInfoId()!=null){
		// recbizTypeService.addRecordBiztypeByRecId(newRecord.getRecordInfoId(),
		// record.getBizTypeName());
		// }
		// }

		if (this.record.getCustomerId() != null
				&& this.record.getCustomerId().intValue() > 0)
		{
			String lastContactType = StringUtil
					.getNotNullValue(CallTypeConstants.callTypeMap
							.get(this.record.getCallType() + ""));
			crmCustomerService.updateLastContactDate(
					this.record.getCustomerId(), new Date(), lastContactType);
			// added by huyb 2013-04-22 关联到访记录
			// if(this.record.getCallType() == 4){
			// PhoneRecordBean newRecord =
			// recordService.getRecordByNo(record.getRecordNo());
			// if(newRecord!=null && newRecord.getRecordInfoId()!=null){
			// Map<String,Object> parameters = new HashMap<String,Object>();
			// parameters.put("userId", this.getLoginInfo().getUserId());
			// parameters.put("discussionId", newRecord.getRecordInfoId());
			// parameters.put("cusId", this.record.getCustomerId());
			// recordService.relatDiscussion(parameters);
			// }
			// }
		}
	}

	/**
	 * 检查用户登入状态
	 * 
	 * @return
	 */
	public String checkUserLoginStatus()
	{
		try
		{
			PrintWriter out = this.getResponse().getWriter();
			String result = this.status.checkLoginStatus(this.account);
			out.write(result);
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 得到来电通话页面
	 * 
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public String getTelephoneTalkPage() throws IllegalArgumentException,
			IllegalAccessException
	{
		if (this.request.getParameterMap().containsKey(AccountKey))
		{
			this.account = this.request.getParameter(AccountKey);
			if (!StringUtil.isNullOrEmpty(this.account))
			{
				PhoneConfig config = this.getPhoneConfig();

				this.closeType = config.getCallOverPopUp();

				this.tabId = this.request.getParameter(TabKey);

				Integer talkId = recordService.newTalkId();
				this.record.setRecordInfoId(talkId); // 设置一个新的通话ID

				String code = this.request.getParameter(CodeKey);
				this.record.setRecordNo(code);

				String cusId = this.request.getParameter(CusIdKey);

				String type = this.request.getParameter(TypeKey);

				if (type.equalsIgnoreCase("spotRec"))
				{
					this.record.setCallType(4);

					if (!StringUtil.isNullOrEmpty(cusId))
					{
						Integer customerId = Integer.parseInt(cusId);
						this.crmCustomer = this.crmCustomerService
								.getCustomerInfo(customerId);
						this.exField = crmCustomerService
								.getCustomizedFieldById(customerId);
						this.tabTitle = this.crmCustomer.getCustomerName();
						this.custs = new ArrayList<CrmCustomer>();
						this.custs.add(this.crmCustomer);
						this.hasPerssion = crmCustomerService.checkPermission(
								crmCustomer, dept.getInChargeOfDeptIds());
						this.isShareCus = crmCustomerService.checkShareCus(
								crmCustomer.getCustomerId(), this
										.getLoginInfo().getUserId());
						if (this.hasPerssion > 0)
						{
							this.isShareCus = false;
						}
					} else
					{
						this.tabTitle = "未知客户";
						this.custs = new ArrayList<CrmCustomer>();
					}
				} else
				{
					if (type.equalsIgnoreCase("incoming"))
					{
						this.record.setCallType(1);
					} else
						this.record.setCallType(2);

					String number = this.request.getParameter(NumberKey);

					if (!StringUtil.isNullOrEmpty(number))
					{
						String telNum = "";

						if (this.record.getCallType().intValue() == 1)
						{
							telNum = this.formatIncomingNumber(number);
							this.renumber = this.outNumber(telNum);
						} else
						{
							telNum = this.formatOutNumber(number);
							this.renumber = number;
						}

						this.record.setIncomingNumber(telNum);
						this.record.setRemotePhone(telNum);
						if (isContainNumber(telNum))
							this.isWhiteTelNumber = sysWhiteListService
									.queryByPhoneNo(telNum);

						if (StringUtil.isNullOrEmpty(cusId))
						{
							if (taskId == null)
							{
								if (isContainNumber(telNum))
									this.custs = talk.getCrmCustomerByTel(
											telNum, config.getCityCode());
								if (custs != null && custs.size() > 0) {
									this.crmCustomer = this.custs.get(0);//默认选择第一项
									for (CrmCustomer cust : this.custs) {//如果客户含有公司资料作为默认选择项
										if (!StringUtil.isNullOrEmpty(cust
												.getCompany())) {
											this.crmCustomer = cust;
											break;
										}
									}
								}
							}

						} else
						{
							Integer customerId = Integer.parseInt(cusId);
							this.crmCustomer = this.crmCustomerService
									.getCustomerInfo(customerId);
							this.tabTitle = crmCustomer.getCustomerName();
							this.custs = new ArrayList<CrmCustomer>();
							this.custs.add(this.crmCustomer);
						}

						if (this.crmCustomer.getCustomerId() > 0)
						{
							this.exField = crmCustomerService
									.getCustomizedFieldById(this.crmCustomer
											.getCustomerId());

							this.hasPerssion = crmCustomerService
									.checkPermission(crmCustomer,
											dept.getInChargeOfDeptIds());
							this.isShareCus = crmCustomerService.checkShareCus(
									crmCustomer.getCustomerId(), this
											.getLoginInfo().getUserId());
							if (this.hasPerssion > 0)
							{
								this.isShareCus = false;
							}
							String customerTypeName = cd
									.getCusTypeNameById(crmCustomer
											.getCustomerTypeId());
							crmCustomer.setCustomerTypeName(customerTypeName);
							String customerIndustryName = cd
									.getIndustryNameById(crmCustomer
											.getCustomerIndustryId());
							crmCustomer
									.setCustomerIndustryName(customerIndustryName);
						}

						if (this.crmCustomer != null)
							JsUtil.escapeEntity(this.crmCustomer);

						String areaName = "";
						if (talk.isMobileNumber(telNum))
						{
							this.isMobile = new Integer(1);
							String areaCode = talk.getAreaCodeByNumber(telNum
									.substring(0, 7));
							if (!StringUtil.isNullOrEmpty(areaCode))
								areaName = talk
										.getPhoneAreaNameByCode(areaCode);
						} else if (telNum.length() > 6)
						{
							areaName = talk.getPhoneAreaNameByCode(telNum
									.substring(0, 4));
							if (StringUtil.isNullOrEmpty(areaName))
								areaName = talk.getPhoneAreaNameByCode(telNum
										.substring(0, 3));
						}
						if (taskId != null && taskId > 0)
						{
							if (StringUtil.isNullOrEmpty(cusId))
							{
								TskMicroTaskTarget tsk = tskMicroTaskTargetService
										.getMicroTaskTargetById(taskId);
								if (tsk != null)
								{
									if(tsk.getCustomerId() != null){//如果是在行客户
										CrmCustomer crmCustomer = new CrmCustomer();
										crmCustomer = crmCustomerService.getCustomerById(tsk.getCustomerId());
										this.tabTitle = crmCustomer.getCustomerName();
										this.crmCustomer.setCustomerId(0);
										this.crmCustomer.setCustomerName(crmCustomer
												.getCustomerName());
										if(crmCustomer.getCustomerName()!=null)this.custs.add(this.crmCustomer);
									}else{//如果是非在行客户
										this.tabTitle = tsk.getCustomerName();
										this.crmCustomer.setCustomerId(0);
										this.crmCustomer.setCustomerName(tsk
												.getCustomerName());
										this.custs.add(this.crmCustomer);
									}
								}
							}
						} else
						{
							if (this.custs != null && this.custs.size() == 1)
							{
								this.tabTitle = this.crmCustomer
										.getCustomerName();
							} else
							{
								this.tabTitle = (!StringUtil
										.isNullOrEmpty(this.crmCustomer
												.getCustomerName())) ? this.crmCustomer
										.getCustomerName() : telNum;
							}
						}
						if ((!StringUtil.isNullOrEmpty(areaName)))
							this.phoneAreaName = areaName;
					}
				}
				// 绑定用户是否有客户经理角色
				request.setAttribute("isCommon", dept.isCommon() ? 1 : 0);
				return SUCCESS;
			}

		}
		return ERROR;
	}

	/**
	 * 判断号码
	 */
	private boolean isContainNumber(String s)
	{

		boolean flag = false;
		if (s == null || s.equals(""))
		{
			return flag;
		} else
		{
			for (int i = 0; i < s.length(); i++)
			{
				if ((s.charAt(i) >= '0') && (s.charAt(i) <= '9'))
				{
					flag = true;
					break;
				}
			}
			return flag;
		}
	}

	/**
	 * 得到来电转接页面
	 * 
	 * @return
	 */
	public String getCallForwardPage()
	{
		Page page = this.getPage();
		page.setPageSize(10);
		this.forwardUsers = talk.getTalkForwardUsers(
				new HashMap<String, Object>(), this.getPage());
		return SUCCESS;
	}

	/**
	 * 来电转接页面分页
	 * 
	 * @return
	 */
	public String getCallForwardGrid()
	{
		Page page = this.getPage();
		page.setPageSize(10);
		this.forwardUsers = talk.getTalkForwardUsers(
				new HashMap<String, Object>(), this.getPage());
		return SUCCESS;
	}

	/**
	 * 得到屏幕取词页面
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String getWordsPage() throws UnsupportedEncodingException
	{
		try
		{
			this.account = URLDecoder.decode(request.getParameter(AccountKey),
					"UTF-8");
			this.phoneNumber = this.request.getParameter(NumberKey);
			this.custJson = this.talk.getRecentlyCustomer(this.account,
					this.phoneNumber).toString();
		} catch (Exception e)
		{

		}
		return SUCCESS;
	}

	/**
	 * 屏幕取词列表
	 * 
	 * @return
	 * @throws IOException
	 */
	public String getWordsGrid() throws IOException
	{
		try
		{
			this.account = URLDecoder.decode(request.getParameter(AccountKey),
					"UTF-8");
			this.phoneNumber = this.request.getParameter(NumberKey);
			this.custJson = this.talk.getRecentlyCustomer(this.account,
					this.phoneNumber).toString();
			PrintWriter out = this.getResponse().getWriter();
			out.print(this.custJson);
		} catch (Exception e)
		{

		}
		return null;
	}

	/**
	 * 替换已有号码
	 * 
	 * @return
	 */
	public String getTalkNumberChange()
	{
		return SUCCESS;
	}

	public static void main(String[] args)
	{

	}
}
