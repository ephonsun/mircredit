/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :高级搜索客户Action
 * Author     :zsw
 * Create Date:2012-5-24
 */
package com.banger.mobile.webapp.action.customer;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.customer.AdvanceQueryBean;
import com.banger.mobile.domain.model.customer.CrmExportBean;
import com.banger.mobile.domain.model.customer.CrmUserQueryBean;
import com.banger.mobile.domain.model.customer.CustomerBean;
import com.banger.mobile.domain.model.customer.CustomerExportBar;
import com.banger.mobile.domain.model.customer.CustomerExportContext;
import com.banger.mobile.domain.model.customer.CustomerExtendFieldBean;
import com.banger.mobile.domain.model.system.CdCity;
import com.banger.mobile.facade.crmModuleExport.CrmModuleExportService;
import com.banger.mobile.facade.customer.AdvancedCustomerService;
import com.banger.mobile.facade.customer.CustomerExportService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.impl.customer.CustomerExportServiceImpl;
import com.banger.mobile.facade.specialDataAuth.SpecialDataAuthService;
import com.banger.mobile.facade.system.CdSystemService;
import com.banger.mobile.facade.template.CrmTemplateService;
import com.banger.mobile.facade.tskContact.TskContactTargetService;
import com.banger.mobile.facade.tskContact.TskPlanService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.SerializeUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author zsw
 * @version $Id: AdvancedCustomerAction.java,v 0.1 2012-5-24 下午02:30:34 liyb Exp
 *          $
 */
public class AdvancedCustomerAction extends BaseAction {

	private static final long serialVersionUID = 3714881424912358812L;
	private AdvancedCustomerService advancedCustomerService;// 高级搜索客户service
	private CustomerExportService customerExportService; // 客户导出service
	private CrmModuleExportService crmModuleExportService; // 导出字段service
	private TskContactTargetService tskContactTargetService; // 任务
	private TskPlanService tskPlanService;
	private PageUtil<CustomerBean> customerPage;// 客户列表
	private AdvanceQueryBean condition; // 高级查询条件对像
	private CdSystemService cd; // 代码表
	private DeptFacadeService dept; // 机构
	private CrmTemplateService temp; // 模板
	private CustomerExtendFieldBean exField; // 自定义
	private CrmUserQueryBean query; // 自定义条件对像
	private Integer queryId; // 自定义条件Id
	private String userName; // 登入用户名称
	private Integer userId; // 用户Id
	private PageUtil<CrmUserQueryBean> queryList; // 自定义搜索列表
	private String target; // 其他模块调用类型
	private boolean showBelongTo;
	private boolean isInChargeof;
	private String selType; // 选取方式
	private SpecialDataAuthService specialDataAuthService;  //特殊数据权限

	public String getSelType() {
		return selType;
	}

	public void setSelType(String selType) {
		this.selType = selType;
	}

	public boolean getInChargeof() {
		return isInChargeof;
	}

	public void setInChargeof(boolean isInChargeof) {
		this.isInChargeof = isInChargeof;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public PageUtil<CrmUserQueryBean> getQueryList() {
		return queryList;
	}

	public void setCrmModuleExportService(
			CrmModuleExportService crmModuleExportService) {
		this.crmModuleExportService = crmModuleExportService;
	}

	public void setCustomerExportService(
			CustomerExportService customerExportService) {
		this.customerExportService = customerExportService;
	}

	public void setQueryList(PageUtil<CrmUserQueryBean> queryList) {
		this.queryList = queryList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getQueryId() {
		return queryId;
	}

	public void setQueryId(Integer queryId) {
		this.queryId = queryId;
	}

	public CrmUserQueryBean getQuery() {
		return query;
	}

	public void setQuery(CrmUserQueryBean query) {
		this.query = query;
	}

	public CustomerExtendFieldBean getExField() {
		return exField;
	}

	public void setExField(CustomerExtendFieldBean exField) {
		this.exField = exField;
	}

	public CrmTemplateService getTemp() {
		return temp;
	}

	public void setTemp(CrmTemplateService temp) {
		this.temp = temp;
	}

	public DeptFacadeService getDept() {
		return dept;
	}

	public void setDept(DeptFacadeService dept) {
		this.dept = dept;
	}

	public CdSystemService getCd() {
		return cd;
	}

	public void setCd(CdSystemService cd) {
		this.cd = cd;
	}

	public AdvanceQueryBean getCondition() {
		return condition;
	}

	public void setCondition(AdvanceQueryBean condition) {
		this.condition = condition;
	}

	public boolean isShowBelongTo() {
		return showBelongTo;
	}

	public void setShowBelongTo(boolean showBelongTo) {
		this.showBelongTo = showBelongTo;
	}

	public void setTskContactTargetService(
			TskContactTargetService tskContactTargetService) {
		this.tskContactTargetService = tskContactTargetService;
	}

	public void setTskPlanService(TskPlanService tskPlanService) {
		this.tskPlanService = tskPlanService;
	}

	/**
	 * 类构造方法
	 */
	public AdvancedCustomerAction() {
		condition = new AdvanceQueryBean();
		condition.setShareTo("unShare");
		exField = new CustomerExtendFieldBean();
		query = new CrmUserQueryBean();
		target = "";
		selType = "multi";
		queryId = -1;
	}

	/**
	 * 高级客户列表
	 * 
	 * @return
	 */
	public String showCustomerPage() {
		try {
			this.userName = this.getLoginInfo().getUserName();
			this.userId = this.getLoginInfo().getUserId();
			String roleIds=StringUtil.getIdsString(getLoginInfo().getRoles());
            boolean flag=specialDataAuthService.getSysDataAuthCondition(roleIds,"customerInfo");
            if(!flag){
                this.showBelongTo = this.dept.isInChargeOfDepartment();
            }else{
                this.showBelongTo = false; 
            }
			
			this.isInChargeof = showBelongTo;
			// 执行任务时添加在行客户
			String isVonExe = this.request.getParameter("isVonExe");
			/**
			 * 如果是执行任务时添加在行客户，只显示归属于自己的客户
			 */
			if (isVonExe != null && !"".equals(isVonExe)) {
				//无论是否业务主管都设置为不是
				showBelongTo=false;
				this.condition.setBelongTo("my");
			} else {
				this.condition.setBelongTo(this.showBelongTo ? "all" : "my");
			}
			if (this.queryId > 0) {
				this.query = this.advancedCustomerService
						.getUserQueryConditionById(this.queryId);
				if (this.query.getQueryDetail() != null
						&& !"".equals(this.query.getQueryDetail())) {
					this.condition = (AdvanceQueryBean) SerializeUtil
							.formXML(this.query.getQueryDetail());
					if (this.condition.getExFields() != null)
						this.exField = this.condition.getExFields();
				}
			}
			Map<String, Object> map = this.getConditionMap();
			customerPage = advancedCustomerService.getAdvancedCustomerPage(map,
					this.getPage());
			return SUCCESS;
		} catch (Exception e) {
			log.error("showCustomerPage action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 删除高级搜索查询结果
	 * 
	 * @return
	 */
	public String delAdvancedQueryResult() {
		this.advancedCustomerService.delUserQueryById(this.getQueryId());
		return null;
	}

	/**
	 * 保存自定义查询结果
	 * 
	 * @return
	 */
	public String saveAdvancedResult() {
		try {
			this.condition.setExFields(this.exField);
			this.condition.setLoginId(this.getLoginInfo().getUserId()
					.toString());
			this.condition.setSubDeptIds(this.condition.getDeptIds());
			this.condition.setSubUserIds(this.condition.getUserIds());

			String xml = SerializeUtil.toXML(this.condition);

			query.setCreateUser(this.getLoginInfo().getUserId());
			query.setUpdateUser(this.getLoginInfo().getUserId());
			query.setCreateDate(new Date());
			query.setUpdateDate(new Date());
			query.setUserId(this.getLoginInfo().getUserId());
			query.setQueryDetail(xml);
			query.setQueryType(new Integer(1));

			PrintWriter out = this.getResponse().getWriter();

			if (this.advancedCustomerService.existUserQueryResultByName(query)) {
				out.write("samename");
			}
//			else if(this.advancedCustomerService.customResultLength(query)){
//                out.write("exist");
//            }
			else {
				Integer queryId = this.advancedCustomerService
						.addUserQueryCondition(query);
				out.write(queryId.toString());
			}

			return null;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("saveAdvancedResult action error:" + e.getMessage());
			return ERROR;
		}
	}

	
	/**
	 * 保存自定义查询结果
	 * 
	 * @return
	 */
	public String saveAdvancedQueryTempResult() {
		try {
			this.condition.setExFields(this.exField);
			this.condition.setLoginId(this.getLoginInfo().getUserId()
					.toString());
			this.condition.setSubDeptIds(this.condition.getDeptIds());
			this.condition.setSubUserIds(this.condition.getUserIds());

			String xml = SerializeUtil.toXML(this.condition);

			query.setCreateUser(this.getLoginInfo().getUserId());
			query.setUpdateUser(this.getLoginInfo().getUserId());
			query.setCreateDate(new Date());
			query.setUpdateDate(new Date());
			query.setUserId(this.getLoginInfo().getUserId());
			query.setQueryDetail(xml);
			query.setQueryType(new Integer(2));

			PrintWriter out = this.getResponse().getWriter();

			Integer id = this.advancedCustomerService
					.addUserQueryCondition(query);

			out.write("{\"id\":" + id + ",\"name\":\"" + query.getQueryName()
					+ "\"}");

			return null;
		} catch (Exception e) {
			log.error("saveAdvancedResult action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 复盖自定义查询结果
	 * 
	 * @return
	 */
	public String editAdvancedResult() {
		try {
			this.condition.setExFields(this.exField);
			this.condition.setSubDeptIds(this.condition.getDeptIds());
			this.condition.setSubUserIds(this.condition.getUserIds());

			String xml = SerializeUtil.toXML(this.condition);
			query.setUpdateUser(this.getLoginInfo().getUserId());
			query.setUpdateDate(new Date());
			query.setQueryDetail(xml);
			query.setUserId(this.getLoginInfo().getUserId());

			PrintWriter out = this.getResponse().getWriter();

			if (this.advancedCustomerService.existUserQueryResultByName(query)) {
				out.write("samename");
			} else {
				this.advancedCustomerService.editUserQueryConditionName(query);
				out.write("success");
			}
			return null;
		} catch (Exception e) {
			log.error("editAdvancedResult action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 处理客户归属条件
	 * 
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> getConditionMap()
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, InstantiationException {
		condition.setLoginId(this.getLoginInfo().getUserId().toString());
		DateUtil.format(condition);
		this.advancedCustomerService.setBelongToCondition(condition, this
				.getLoginInfo().getUserId());
		AdvanceQueryBean tempCondition = (AdvanceQueryBean) BeanUtils
				.cloneBean(condition);
		this.advancedCustomerService.filterCondition(tempCondition);
		Map<String, Object> map = BeanUtils.describe(tempCondition);
		String exCondition = this.advancedCustomerService
				.getExtendFieldCondition(this.exField);
		if (!StringUtil.isNullOrEmpty(exCondition))
			map.put("exCondition", exCondition);
		String phoneRules = this.advancedCustomerService
				.getPhoneRuleCondition(tempCondition);
		if (!StringUtil.isNullOrEmpty(phoneRules))
			map.put("rules", phoneRules);
		return map;
	}

	/**
	 * 自定义搜索管理页面
	 * 
	 * @return
	 */
	public String getUserQueryList() {
		this.userId = this.getLoginInfo().getUserId();
		this.queryList = this.advancedCustomerService.getUserQueryList(userId,
				this.getPage());
		return SUCCESS;
	}

	/**
	 * 导出数据
	 * 
	 * @return
	 */
	public String showExportCustomer() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter out = response.getWriter();
			String data = request.getParameter("crmPostData");
			Map<String, Object> map = this.getConditionMap();// 组装查询条件
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
				list = advancedCustomerService.getExportAdvancedCustomers(map,
						offset + 1, limit);
				customerExportService.insertRow(ctx, workbook, list);
				recordNum = list.size();
				list.clear();
				pageNum++;
			}
			if (bar.getIsStop().equals(0)) {
				File file = customerExportService.createFile(request);// 在服务器生成文件
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
			log.error("showCustomerPage action error:" + e.getMessage());
			return ERROR;
		}

	}

	public String getAllSelectCustomer() {
		String type = request.getParameter("batchType");
		String targetId = request.getParameter("targetId");
		Map<String, Object> map;
		try {
			map = this.getConditionMap();
			List<Integer> ids = advancedCustomerService
					.getAllSelectCustomerIds(map);
			if ("task".equals(type)) {
				tskContactTargetService.addBatchTskContactTarget(Integer
						.parseInt(targetId), ids);
			} else if ("tskPlan".equals(type)) {
				tskPlanService.addBatchNoTaskCustForPlan(Integer
						.parseInt(targetId), ids);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllSelectCustomer error:" + e.getMessage());
			return ERROR;
		}

		return null;
	}

	/**
	 * 无刷新查询
	 * 
	 * @return
	 */
	public String getAdvancedCustomerGrid() {
		try {
			Map<String, Object> map = this.getConditionMap();
			customerPage = advancedCustomerService.getAdvancedCustomerPage(map,
					this.getPage());
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("showCustomerPage action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 得到城市数据
	 * 
	 * @return
	 */
	public String getCityJson() {
		try {

			String provCode = this.request.getParameter("provCode");
			List<CdCity> cList = cd.getCitys(provCode);
			this.getResponse().setContentType("text/html;charset=utf-8");
			PrintWriter out = this.getResponse().getWriter();
			Map<String, Object> map = new HashMap<String, Object>();
			JSONArray ja = new JSONArray();
			for (CdCity c : cList) {
				map.put("code", c.getCode());
				map.put("name", c.getShortName());
				ja.add(map);
			}
			out.print(ja.toString());
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("showCustomerPage action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 上移
	 * 
	 * @return
	 */
	public String upAdvancedQueryResult() {
		if (this.queryId > 0) {
			Integer userId = this.getLoginInfo().getUserId();
			this.advancedCustomerService.upUserQueryById(queryId, userId);
		}
		return null;
	}

	/**
	 * 下移
	 * 
	 * @return
	 */
	public String downAdvancedQueryResult() {
		if (this.queryId > 0) {
			Integer userId = this.getLoginInfo().getUserId();
			this.advancedCustomerService.downUserQueryById(queryId, userId);
		}
		return null;
	}

	public AdvancedCustomerService getAdvancedCustomerService() {
		return advancedCustomerService;
	}

	public void setAdvancedCustomerService(
			AdvancedCustomerService advancedCustomerService) {
		this.advancedCustomerService = advancedCustomerService;
	}

	public PageUtil<CustomerBean> getCustomerPage() {
		return customerPage;
	}

	public void setCustomerPage(PageUtil<CustomerBean> customerPage) {
		this.customerPage = customerPage;
	}

    public void setSpecialDataAuthService(SpecialDataAuthService specialDataAuthService) {
        this.specialDataAuthService = specialDataAuthService;
    }
}
