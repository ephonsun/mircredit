package com.banger.mobile.webapp.action.loan;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.data.Form;
import com.banger.mobile.domain.model.data.LoanData;
import com.banger.mobile.domain.model.loan.*;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.domain.model.user.SysUserBean;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.data.CustomerDataService;
import com.banger.mobile.facade.data.DataFormService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.impl.data.CaseHelperServiceImpl;
import com.banger.mobile.facade.loan.*;
import com.banger.mobile.facade.uploadFile.SysUploadFileService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;
import sun.rmi.runtime.Log;


import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA. User: Zhangfp Date: 13-2-21 Time: 上午10:09 To
 * change this template use File | Settings | File Templates.
 *
 * 待调查贷款
 */
public class UnExamLoanAction extends BaseAction {

	private static Logger logger = Logger.getLogger(UnExamLoanAction.class);

	// service
	private LnLoanService lnLoanService; // 贷款service
	private LnExceptionRepaymentPlanService lnExceptionRepaymentPlanService;
	private LnRepaymentPlanService LnRepaymentPlanService;
	private DeptFacadeService deptFacadeService; // 部门组织结构service
	private LnLoanTypeService lnLoanTypeService; // 贷款类型service
	private CustomerDataService customerDataService; // 贷款资料service
	private SysUploadFileService sysUploadFileService; // 文件上传service
	private DataFormService dataFormService; // 表单service
	private CrmCustomerService crmCustomerService; // 客户service
	private LnLoanCoBorrowerService lnLoanCoBorrowerService; // 共同借贷人service
	private LnLoanGuarantorService lnLoanGuarantorService; // 担保人service
	private CaseHelperServiceImpl caseHelperService;
	private LnLoanInfoService lnLoanInfoService;
	private LnLoanPledgeService lnLoanPledgeService;
	private SysUserService sysUserService;
	// 查询条件
	private String customer;
	private Integer loanType;
	private Date startAssignDate;
	private Date endAssignDate;
	private LnLoanInfoDictionaryService lnLoanInfoDictionaryService;
	private LnPledge lnPledge;
	// private String approveUser;
	private File fileInput;
	private String fileInputFileName;
	private Integer pledgeId;
	private Integer loanId;
	private Integer approveProcessId;
	private BigDecimal owedPrincipal;
	private BigDecimal paidPrincipal;;

	public LnRepaymentPlanService getLnRepaymentPlanService() {
		return LnRepaymentPlanService;
	}

	public void setLnRepaymentPlanService(LnRepaymentPlanService lnRepaymentPlanService) {
		LnRepaymentPlanService = lnRepaymentPlanService;
	}

	private Date planDate;

	public Integer getApproveProcessId() {
		return approveProcessId;
	}


	public void setApproveProcessId(Integer approveProcessId) {
		this.approveProcessId = approveProcessId;
	}

	public Integer getLoanId() {
		return loanId;
	}

	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}

	public Integer getPledgeId() {
		return pledgeId;
	}

	public void setPledgeId(Integer pledgeId) {
		this.pledgeId = pledgeId;
	}

	public SysUserService getSysUserService() {
		return sysUserService;
	}

	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;


	}

	// public void setApproveUser(String approveUser) {
	// this.approveUser = approveUser;
	// }
	//
	// public String getApproveUser() {
	// return approveUser;
	// }

	public void setLnLoanInfoService(LnLoanInfoService lnLoanInfoService) {
		this.lnLoanInfoService = lnLoanInfoService;
	}

	public CaseHelperServiceImpl getCaseHelperService() {
		return caseHelperService;
	}

	public void setCaseHelperService(CaseHelperServiceImpl caseHelperService) {
		this.caseHelperService = caseHelperService;
	}

	public LnLoanCoBorrowerService getLnLoanCoBorrowerService() {
		return lnLoanCoBorrowerService;
	}

	public void setLnLoanCoBorrowerService(
			LnLoanCoBorrowerService lnLoanCoBorrowerService) {
		this.lnLoanCoBorrowerService = lnLoanCoBorrowerService;
	}

	public LnLoanGuarantorService getLnLoanGuarantorService() {
		return lnLoanGuarantorService;
	}

	public void setLnLoanGuarantorService(
			LnLoanGuarantorService lnLoanGuarantorService) {
		this.lnLoanGuarantorService = lnLoanGuarantorService;
	}

	public File getFileInput() {
		return fileInput;
	}

	public void setFileInput(File fileInput) {
		this.fileInput = fileInput;
	}

	public String getFileInputFileName() {
		return fileInputFileName;
	}

	public void setFileInputFileName(String fileInputFileName) {
		this.fileInputFileName = fileInputFileName;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Integer getLoanType() {
		return loanType;
	}

	public void setLoanType(Integer loanType) {
		this.loanType = loanType;
	}

	public Date getStartAssignDate() {
		return startAssignDate;
	}

	public void setStartAssignDate(Date startAssignDate) {
		this.startAssignDate = startAssignDate;
	}

	public Date getEndAssignDate() {
		return endAssignDate;
	}

	public void setEndAssignDate(Date endAssignDate) {
		this.endAssignDate = endAssignDate;
	}

	public SysUploadFileService getSysUploadFileService() {
		return sysUploadFileService;
	}

	public void setSysUploadFileService(
			SysUploadFileService sysUploadFileService) {
		this.sysUploadFileService = sysUploadFileService;
	}

	public LnLoanService getLnLoanService() {
		return lnLoanService;
	}

	public void setLnLoanService(LnLoanService lnLoanService) {
		this.lnLoanService = lnLoanService;
	}

	public DeptFacadeService getDeptFacadeService() {
		return deptFacadeService;
	}

	public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
		this.deptFacadeService = deptFacadeService;
	}

	public LnLoanTypeService getLnLoanTypeService() {
		return lnLoanTypeService;
	}

	public void setLnLoanTypeService(LnLoanTypeService lnLoanTypeService) {
		this.lnLoanTypeService = lnLoanTypeService;
	}

	public CustomerDataService getCustomerDataService() {
		return customerDataService;
	}

	public void setCustomerDataService(CustomerDataService customerDataService) {
		this.customerDataService = customerDataService;
	}

	public DataFormService getDataFormService() {
		return dataFormService;
	}

	public void setDataFormService(DataFormService dataFormService) {
		this.dataFormService = dataFormService;
	}

	public CrmCustomerService getCrmCustomerService() {
		return crmCustomerService;
	}

	public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
		this.crmCustomerService = crmCustomerService;
	}

	/**
	 * 贷款调查回退
	 *
	 * @return
	 */
	public void goBackLoanSurvey() {

		HttpServletResponse response = this.getResponse();
		PrintWriter out=null;
		try{
			out = response.getWriter();
			response.setContentType("text/html");
			lnLoanService.updateGoBackLoanSurveyById(loanId);
			out.println("success");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			out.println("error");
		}finally {

			out.flush();
			out.close();

		}
	}


	/**
	 * 待调查贷款首页列表
	 *
	 * @return
	 */
	public String unExamLoanList() {
		try {
			logger.info("-----当前登录用户：" + this.getLoginInfo().getAccount()
					+ "----- 进入调查贷款菜单...");
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			// 是否是业务主管
			Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment();
			if (isInChargeOf) {
				String belongUserIds = deptFacadeService
						.getUserIdsBelongToManager(this.getLoginInfo()
								.getRoles(), "loanInfo");
				parameterMap.put("unExamNeedUserIds", belongUserIds);
			} else {
				parameterMap.put("unExamNeedUserId", this.getLoginInfo()
						.getUserId());
			}
			// 当前登录用户id
			parameterMap.put("loanStatusId", 3); // 贷款状态：待调查

			// 分页查询出ln_loan表中的分配给当前用户的待调查贷款记录
			PageUtil<LnLoan> dataList = lnLoanService.getApplyLoanByPage(
					parameterMap, this.getPage());
			logger.info("-----当前登录用户：" + this.getLoginInfo().getAccount()
					+ "----- 总共查询出" + dataList.getItems().size() + "条符合条件的调查贷款");
			if (dataList != null && dataList.getItems() != null) {
				for (LnLoan lnLoan : dataList.getItems()) {
					Date assignDate = lnLoan.getAssignSubmitDate(); // 分配时间
					Date nowDate = new Date();
					if (assignDate != null) {
						if (nowDate.getTime() - assignDate.getTime() >= 1000 * 60 * 60 * 46) {
							// 超时前2小时
							lnLoan.setIsWillPast(1);
						}
					}
				}
			}

			// 贷款类型列表
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("dictionaryName", "DKLX");
			List<LnLoanInfoDictionary> typeList = lnLoanInfoDictionaryService
					.selectLoanInfoDictionaryList(paramMap);

			request.setAttribute("typeList", typeList);
			request.setAttribute("dataList", dataList.getItems());
			request.setAttribute("recordCount", dataList.getPage()
					.getTotalRowsAmount());
			request.setAttribute("sysUserId", this.getLoginInfo().getUserId());

			return SUCCESS;
		} catch (Exception e) {
			logger.error("UnExamLoanAction % unExamLoanList", e);
			return ERROR;
		}
	}

	/**
	 * 查询待调查贷款列表
	 *
	 * @return
	 */
	public String unExamLoanListQuery() {
		try {
			logger.info("-----当前登录用户：" + this.getLoginInfo().getAccount()
					+ "----- 按条件搜索调查贷款...");
			Map<String, Object> conds = new HashMap<String, Object>();

			// 是否是业务主管
			Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment();
			if (isInChargeOf) {
				// 当前用户的下属用户
				String belongUserIds = deptFacadeService
						.getUserIdsBelongToManager(this.getLoginInfo()
								.getRoles(), "loanInfo");
				conds.put("unExamNeedUserIds", belongUserIds);
			} else {
				conds.put("unExamNeedUserId", this.getLoginInfo().getUserId());
			}

			conds.put("loanStatusId", 3); // 2为待调查状态
			conds.put("customer", customer);
			conds.put("loanTypeId", loanType);
			conds.put("startAssignDate", startAssignDate);
			endAssignDate = lnLoanService.addSecondsForDate(endAssignDate, 59);
			conds.put("endAssignDate", endAssignDate);
			logger.info("-----当前登录用户：" + this.getLoginInfo().getAccount()
					+ "----- 搜索条件，客户：" + customer + ",贷款类型：" + loanType
					+ ",分配时间：" + startAssignDate + "——" + endAssignDate);
			PageUtil<LnLoan> dataList = lnLoanService.getApplyLoanByPage(conds,
					this.getPage());
			logger.info("总共查询出" + dataList.getItems().size() + "条符合条件的调查贷款");
			if (dataList != null && dataList.getItems() != null) {
				for (LnLoan lnLoan : dataList.getItems()) {
					Date assignDate = lnLoan.getAssignSubmitDate(); // 分配时间
					Date nowDate = new Date();
					if (assignDate != null) {
						if (nowDate.getTime() - assignDate.getTime() >= 1000 * 60 * 60 * 46) {
							// 超时前2小时
							lnLoan.setIsWillPast(1);
						}
					}
				}
			}

			request.setAttribute("dataList", dataList.getItems());
			request.setAttribute("recordCount", dataList.getPage()
					.getTotalRowsAmount());
			request.setAttribute("sysUserId", this.getLoginInfo().getUserId());
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("UnExamLoanAction % unExamLoanListQuery", e);
			return ERROR;
		}
	}

	/**
	 * 查看临时被改变的贷款状态的数量
	 */
	public void unExamLoanStatusChanged() {
		try {
			String loanIdStr = request.getParameter("loanId");
			if (StringUtils.isNotEmpty(loanIdStr)) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("loanStatusId", LoanConstants.LOAN_EXAM_STATUS);
				paramMap.put("loanId", Integer.parseInt(loanIdStr));

				Integer changedCount = lnLoanService
						.queryChangedLoanStatusCount(paramMap);

				HttpServletResponse response = this.getResponse();
				response.setContentType("text/html;charset=utf-8");

				PrintWriter out = response.getWriter();
				try {
					out.print(changedCount);
				} finally {
					if (out != null) {
						out.flush();
						out.close();
					}
				}
			}
		} catch (Exception e) {
			logger.error("UnExamLoanAction % unExamLoanStatusChanged", e);
		}
	}

	/**
	 * 普通调查编辑页面
	 *
	 * @return
	 */
	public String commExamPage() {
		try {
			logger.info("-----当前登录用户：" + this.getLoginInfo().getAccount()
					+ "----- 进入普通调查贷款编辑页面...");
			String loanId = request.getParameter("loanId");

			// 贷款基本信息
			LnLoan loan = lnLoanService.getLnLoanById(Integer.parseInt(loanId));

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("eventId", 3);
			paramMap.put("loanId", loan.getLoanId());
			paramMap.put("customerId", loan.getCustomerId());
			// 贷款表单附件信息
			List<Form> formAttachmentList = dataFormService
					.selectFormAttachment(paramMap);

			request.setAttribute("loanId", loanId);
			request.setAttribute("customerId", loan.getCustomerId());
			request.setAttribute("loan", loan);
			request.setAttribute("formAttachmentList", formAttachmentList);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("UnExamLoanAction % editUnExamLoanPage", e);
			return ERROR;
		}
	}

	// 添加信贷历史初始化
	public String toAddPledge() {
		request.setAttribute("lnLoanId", request.getParameter("lnLoanId"));
		return SUCCESS;
	}

	public void addPledge() {
		try {
			lnPledge.setCreateDate(new Date());
			lnLoanPledgeService.insertLnLoanPledge(lnPledge);
			this.getResponse().setContentType("text/html;charset=utf-8");
			PrintWriter out = this.getResponse().getWriter();
			out.print("success");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 移除信贷历史
	public void removePledge() {
		try {
			this.getResponse().setContentType("text/html;charset=utf-8");
			PrintWriter out = this.getResponse().getWriter();
			if (pledgeId == null) {
				out.print("fail");
			}
			lnLoanPledgeService.rmLnLoanPledgeById(pledgeId);
			out.print("success");
		} catch (Exception e) {
			log.error("addCo action error:", e);
		}
	}

	public String queryLoanPledge() {
		try {
			List<LnPledge> lnPledgeList = lnLoanPledgeService.getLnLoanPledgeByLoanId(loanId);
			request.setAttribute("lnPledgeList", lnPledgeList);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	public String queryCustomerLoanPledge() {
		try {
			List<LnPledge> lnPledgeList = lnLoanPledgeService
					.getLnLoanPledgeByLoanId(loanId);
			request.setAttribute("lnPledgeList", lnPledgeList);

			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}
	//查询是否存在抵质押物
	public void queryHavingPledge(){
		try {
			PrintWriter out = this.getResponse().getWriter();
			List<LnPledge> lnPledgeList = lnLoanPledgeService
					.getLnLoanPledgeByLoanId(loanId);
			request.setAttribute("lnPledgeList", lnPledgeList);
			if(CollectionUtils.isEmpty(lnPledgeList)){
				out.print("false");
			}

		} catch (Exception e) {
			logger.error("查询抵质押物失败",e);
		}

	}

	// 陪调
	public String togetherSurvey() {
		try {
			request.setAttribute("loanId", request.getParameter("loanId"));
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}


	public String toHkfs() {
		try {
			String limitYear = request.getParameter("limitYear");
			String repayDate = request.getParameter("repayDate");
			String money = request.getParameter("money");
			String loanId = request.getParameter("loanId");
			String registerLoanDate = request.getParameter("registerLoanDate");
			String repaRate = request.getParameter("repaRate");
			String adviceRepayWayId = request.getParameter("adviceRepayWayId");
			String resultRepayWayId = request.getParameter("resultRepayWayId");
			Map<String, Object> param =new HashMap<String, Object>();
			param.put("loanId",loanId);
			String str =null;
			String opq =null;
			String repaRepayWayId=null;
			LnLoanInfo lnLoanInfo = lnLoanInfoService.selectLoanInfoList(param).get(0);
			List<LoanCalculatorBean>  lcb=new ArrayList<LoanCalculatorBean>();


			if(lnLoanInfo!=null){
				if(adviceRepayWayId!=null){
					repaRepayWayId = adviceRepayWayId;
					str=lnLoanInfo.getAdviceRepayWayId();
					opq=lnLoanInfo.getAdviceMoney();
				}

				if(resultRepayWayId!=null){
					if(lnLoanInfo.getResultRepayWayId().equals("undefined")){
						lnLoanInfo.setResultRepayWayId(lnLoanInfo.getAdviceRepayWayId());
					}
					repaRepayWayId = resultRepayWayId;
					str=lnLoanInfo.getResultRepayWayId();
					opq=lnLoanInfo.getResultMoney();
				}
			}
			request.setAttribute("loanId", loanId);
			request.setAttribute("money", money);
			request.setAttribute("limitYear", limitYear);
			request.setAttribute("repayDate", repayDate);
			request.setAttribute("registerLoanDate", registerLoanDate);
			request.setAttribute("repaRate", repaRate);
			request.setAttribute("adviceRepayWayId", adviceRepayWayId);
			request.setAttribute("resultRepayWayId", resultRepayWayId);
			request.setAttribute("isLend",request.getParameter("isLend"));
			List<LnRepaymentPlan> list = new ArrayList<LnRepaymentPlan>();
			if(repaRepayWayId.equals("2")){
				lcb=calculatorBeanList2(Double.parseDouble(money), Double.parseDouble(repaRate),Integer.parseInt(limitYear) );
			}
			if(repaRepayWayId.equals("1")){
				lcb=calculatorBeanList(Double.parseDouble(money), Double.parseDouble(repaRate),Integer.parseInt(limitYear) );
			}
			if(repaRepayWayId.equals("3")){
				lcb=calculatorBeanList3(Double.parseDouble(money), Double.parseDouble(repaRate),Integer.parseInt(limitYear) );
			}
			if(repaRepayWayId.equals("4")){
				lcb=calculatorBeanList2(Double.parseDouble(money), Double.parseDouble(repaRate), Integer.parseInt(limitYear));
			}
			//根据 loanid查询还款计划
			//if 存在还款计划
			BigDecimal bigMoney = new BigDecimal(0.00);
			BigDecimal bigavgSum= new BigDecimal(0.00);
			BigDecimal bigacountSum= new BigDecimal(0.00);

			if (StringUtils.isNotBlank(loanId) && StringUtils.isNumeric(loanId)) {
				list = LnRepaymentPlanService.queryLnRepaymentPlan(Integer.valueOf(loanId));
				BigDecimal bigSum=new BigDecimal(0.00);
				BigDecimal bigAvgSum=new BigDecimal(0.00);
				if(list!=null){
					for (int i=0;i<list.size();i++){
						if(null!=list.get(i).getInterest()){
							bigSum=bigSum.add(list.get(i).getInterest());
						}
						if(null!=list.get(i).getPrincipal()){
							bigAvgSum=bigAvgSum.add(list.get(i).getPrincipal());
						}
					}
				}
				Integer rt=0;
				if(!str.equals(repaRepayWayId)){
					rt++;
				}
				BigDecimal avgSum=new BigDecimal(0.0);
				BigDecimal amount = new BigDecimal(0.0);
				for(int kk=0;kk<lcb.size();kk++){
					avgSum=avgSum.add(BigDecimal.valueOf(lcb.get(kk).getNumMonthInterest()));
					amount=amount.add(BigDecimal.valueOf(lcb.get(kk).getNumMonthCapital()));
				}

				Integer ui =avgSum.compareTo(bigSum);
				if(repaRepayWayId.equals("4")){

					ui =0;
				}
				if(repaRepayWayId.equals("3")){
					amount=BigDecimal.valueOf(Integer.parseInt(money));
				}
				Integer op =amount.compareTo(bigAvgSum);
				Integer pan=0;
				if ((CollectionUtils.isEmpty(list) || list.size() != Integer.valueOf(limitYear))  || ui!=0   ||rt!= 0 || op!=0
						&& StringUtils.isNotBlank(limitYear) && StringUtils.isNumeric(limitYear) && StringUtils.isNotBlank(money) && StringUtils.isNumeric(money)) {
					list.clear();
					pan=1;
					LnRepaymentPlan plan;
					Date loanDate = null;
					if(StringUtils.isNotBlank(registerLoanDate)){
						loanDate = new SimpleDateFormat("yyyy-MM-dd").parse(registerLoanDate);
					}
					Integer repayDateOne = 1;
					if(StringUtils.isNotBlank(repayDate)&&StringUtils.isNumeric(repayDate)){
						repayDateOne = Integer.valueOf(repayDate);
					}
					bigMoney = new BigDecimal(money);

					for (int i = 0; i < Integer.valueOf(limitYear); i++) {
						plan = new LnRepaymentPlan();
						plan.setSortno(i + 1);
						plan.setPrincipal(BigDecimal.valueOf(lcb.get(i).getNumMonthCapital()));
						bigacountSum=bigacountSum.add(plan.getPrincipal());
						plan.setPlanDate(loanDateCalender(loanDate, repayDateOne, Integer.valueOf(i)));
						plan.setInterest(BigDecimal.valueOf(lcb.get(i).getNumMonthInterest()));
						bigavgSum=bigavgSum.add(plan.getInterest());
						list.add(plan);
					}
				}
				BigDecimal count=new BigDecimal(0.00);
				if(pan!=0){
					request.setAttribute("avg", bigavgSum);
					request.setAttribute("amount",bigacountSum);
				}else{
					request.setAttribute("avg",bigSum.setScale(3,BigDecimal.ROUND_HALF_UP) );
					request.setAttribute("amount",bigAvgSum.setScale(2,BigDecimal.ROUND_HALF_UP));
				}
				request.setAttribute("planList", list);

			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}


	    //等额本息
		public  List<LoanCalculatorBean> calculatorBeanList(double max,double numInterest,int numPeriod){//本金    利息   期数
			double monthInterest=numInterest/12/100;
			double numMonthPay=0;
			DecimalFormat df   = new DecimalFormat("######0.00");
			DecimalFormat dpf   = new DecimalFormat("######0.000");
			List<LoanCalculatorBean>  list=new ArrayList<LoanCalculatorBean>();
			for (int i = 0; i <numPeriod ; i++) {
				LoanCalculatorBean bean=new LoanCalculatorBean();
				//月供
				if(numMonthPay==0){
					numMonthPay=Math.pow((1+monthInterest),numPeriod)*monthInterest/(Math.pow((1+monthInterest),numPeriod)-1)*max;
				}
				//月供利息
				double numMonthInterest=max*monthInterest;
				//月供本金
				double numMonthCapital=numMonthPay-numMonthInterest;
				//剩余本金
				max=max-numMonthCapital;
				//给相关的bean设置参数
				bean.setNumPeriod(i+1);
				bean.setNumMonthPay(numMonthPay);
				bean.setNumMonthCapital(Double.parseDouble(df.format(numMonthCapital)));
				bean.setNumMonthInterest(Double.parseDouble(dpf.format(numMonthInterest)));
				bean.setNumLeftCapital(max);
				list.add(bean);
				/*Log.e("zzzz", "calculatorBeanList: " + bean.toString());*/

			}
			return list;
		}
	     //等额本金
		public  List<LoanCalculatorBean> calculatorBeanList2(double max,double numInterest,int numPeriod){
			double monthInterest=numInterest/12/100;
			//月供本金
			double numMonthCapital=0;
			DecimalFormat df   = new DecimalFormat("######0.00");
			DecimalFormat dpf   = new DecimalFormat("######0.000");
			List<LoanCalculatorBean>  list=new ArrayList<LoanCalculatorBean>();
			for (int i = 0; i <numPeriod ; i++) {
				LoanCalculatorBean bean=new LoanCalculatorBean();
				//月供本金只算一次
				if(numMonthCapital==0){
					numMonthCapital=max/numPeriod;
				}
				//月供利息
				double numMonthInterest=max*monthInterest;
				//月供金额
				double numMonthPay=numMonthCapital+numMonthInterest;

				//剩余本金
				max=max-numMonthCapital;
				//给相关的bean设置参数
				bean.setNumPeriod(i+1);
				bean.setNumMonthPay(numMonthPay);
				bean.setNumMonthCapital(Double.parseDouble(df.format(numMonthCapital)));
				bean.setNumMonthInterest(Double.parseDouble(dpf.format(numMonthInterest)));
				bean.setNumLeftCapital(max);
				list.add(bean);
				/*Log.e("zzzz", "calculatorBeanList: "+ bean.toString());*/

			}
			return list;
		}
	    //按月还息，到期还本
	    public  List<LoanCalculatorBean> calculatorBeanList3(double max,double numInterest,int numPeriod){
			double monthInterest=numInterest/12/100;//月利息
			//月供本金
			double numMonthCapital=0.0;
			DecimalFormat df   = new DecimalFormat("######0.00");
			DecimalFormat dpf   = new DecimalFormat("######0.000");
			List<LoanCalculatorBean>  list=new ArrayList<LoanCalculatorBean>();
			//月供利息
			double numMonthInterest=max*monthInterest;
			double numMonthPay=0.0;
			for (int i = 0; i <numPeriod ; i++) {
				LoanCalculatorBean bean=new LoanCalculatorBean();
				//月供本金只算一次
				//月供金额
				if(i==numPeriod-1){
					numMonthCapital=max;
				}

				//剩余本金
				max=max-numMonthCapital;
				//给相关的bean设置参数
				bean.setNumPeriod(i+1);
				bean.setNumMonthPay(numMonthPay);
				bean.setNumMonthCapital(Double.parseDouble(df.format(numMonthCapital)));
				bean.setNumMonthInterest(Double.parseDouble(dpf.format(numMonthInterest)));
				bean.setNumLeftCapital(max);
				list.add(bean);
					/*Log.e("zzzz", "calculatorBeanList: "+ bean.toString());*/

		}
		return list;
	}
		//约定还款
		public  List<LoanCalculatorBean> calculatorBeanList4(double max,double numInterest,int numPeriod){
			double monthInterest=numInterest/12/100;
			double numMonthPay=0;
			DecimalFormat df    = new DecimalFormat("######0.00");
			DecimalFormat dpf   = new DecimalFormat("######0.000");
			List<LoanCalculatorBean>  list=new ArrayList<LoanCalculatorBean>();
			for (int i = 0; i <numPeriod ; i++) {
				LoanCalculatorBean bean=new LoanCalculatorBean();
				//月供
				if(numMonthPay==0){
					numMonthPay=Math.pow((1+monthInterest),numPeriod)*monthInterest/(Math.pow((1+monthInterest),numPeriod)-1)*max;
				}
				//月供利息
				double numMonthInterest=max*monthInterest;
				//月供本金
				double numMonthCapital=numMonthPay-numMonthInterest;
				//剩余本金
				max=max-numMonthCapital;
				bean.setNumPeriod(i + 1);
				bean.setNumMonthPay(numMonthPay);
				bean.setNumMonthCapital(Double.parseDouble(df.format(numMonthCapital)));
				bean.setNumMonthInterest(Double.parseDouble(dpf.format(numMonthInterest)));
				bean.setNumLeftCapital(max);
				list.add(bean);
				/*Log.e("zzzz", "calculatorBeanList: " + bean.toString());*/
		}
		return list;
	}
		public  String  getDoubleTwo(double num){
			BigDecimal bg=new BigDecimal(num).setScale(2, BigDecimal.ROUND_HALF_UP);
			return ""+bg.doubleValue();
		}





	//校验还款计划
	public void jyToHkfs() {
		PrintWriter out = null;

		try {
			out = this.getResponse().getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//获取页面上的还款日期，还款金额，还款期限
		String loanId = request.getParameter("loanId");
		String adviceMoney = request.getParameter("adviceMoney");
		String limitYear = request.getParameter("limitYear");
		String adviceRepayDate = request.getParameter("adviceRepayDate");
		String repaRate = request.getParameter("repaRate");
		String repaRepayWayId =null;
		String resultRepayWayId=request.getParameter("resultRepayWayId");
		String adviceRepayWayId=request.getParameter("adviceRepayWayId");
		String str =null;

		request.setAttribute("loanId", loanId);
		request.setAttribute("limitYear", limitYear);
		request.setAttribute("adviceMoney", adviceMoney);
		request.setAttribute("adviceRepayDate", adviceRepayDate);
		request.setAttribute("repaRepayWayId", repaRepayWayId);


		BigDecimal bigMoney = new BigDecimal(adviceMoney);
		Map<String, Object> param =new HashMap<String, Object>();
		param.put("loanId",loanId);
		LnLoanInfo lnLoanInfo = lnLoanInfoService.selectLoanInfoList(param).get(0);
		List<LoanCalculatorBean>  lcb=new ArrayList<LoanCalculatorBean>();

		if(lnLoanInfo!=null){
			if(adviceRepayWayId!=null){
				repaRepayWayId = adviceRepayWayId;
				str=lnLoanInfo.getAdviceRepayWayId();

			}else{
				repaRepayWayId = resultRepayWayId;
				str=lnLoanInfo.getResultRepayWayId();
			}
		}
		//查询数据库
		List<LnRepaymentPlan> list = new ArrayList<LnRepaymentPlan>();
		list = LnRepaymentPlanService.queryLnRepaymentPlan(Integer.valueOf(loanId));
		if(list.size()!=0){
			//比较日期
			Date date = null;
			Date date1 = null;
			date = list.get(0).getPlanDate();//数据库中的日期
			int day2 = Integer.valueOf(adviceRepayDate);
			Calendar cal = Calendar.getInstance();
			//数据库里的时间
			cal.setTime(date);
			int day = cal.get(cal.DAY_OF_MONTH);
			//页面上的时间
			BigDecimal bigX=new BigDecimal(0.000);
			BigDecimal bigF=new BigDecimal(0.000);
			if(list!=null){
				for (int i=0;i<list.size();i++){
					bigX=bigX.add(list.get(i).getInterest());
					bigF=bigF.add(list.get(i).getPrincipal());
				}
			}
			BigDecimal bigSumAvg=new BigDecimal(0.000);
			BigDecimal bigSumAcount=new BigDecimal(0.000);
			if(repaRepayWayId.equals("1")){
				lcb=calculatorBeanList(Double.parseDouble(adviceMoney), Double.parseDouble(repaRate),Integer.parseInt(limitYear));
			}
			if(repaRepayWayId.equals("2")){
				lcb=calculatorBeanList2(Double.parseDouble(adviceMoney), Double.parseDouble(repaRate), Integer.parseInt(limitYear));
			}
			if(repaRepayWayId.equals("3")){
				lcb=calculatorBeanList3(Double.parseDouble(adviceMoney), Double.parseDouble(repaRate), Integer.parseInt(limitYear));
			}
			if(repaRepayWayId.equals("4")){
				lcb=calculatorBeanList2(Double.parseDouble(adviceMoney), Double.parseDouble(repaRate), Integer.parseInt(limitYear));
			}

			for (int i = 0; i <lcb.size() ; i++) {
				bigSumAvg=bigSumAvg.add(BigDecimal.valueOf(lcb.get(i).getNumMonthInterest()));
				bigSumAcount=bigSumAcount.add(BigDecimal.valueOf(lcb.get(i).getNumMonthCapital()));
			}
			Integer avgPI=bigX.compareTo(bigSumAvg);
			if(repaRepayWayId.equals("4")){
				avgPI=0;
			}
			Integer acountPI=bigF.compareTo(bigSumAcount);
			Integer moneyPI=0;
			if(repaRepayWayId.equals("5")){
				 moneyPI=bigF.setScale(1,BigDecimal.ROUND_HALF_UP).compareTo(BigDecimal.valueOf(Integer.parseInt(adviceMoney)));
			}
			if (moneyPI==0 && list.size() == Integer.valueOf(limitYear) && day == day2 && avgPI==0&& acountPI==0  && str.equals(repaRepayWayId) ) {
				out.print("true");
				return ;
			}
			if(repaRepayWayId.equals("5")){
				out.print("true");
				return ;
			} else{
				out.print("false");
				return ;
			}
		}
		if(repaRepayWayId.equals("5")){
			out.print("true");
		} else{
			out.print("false");
		}
	}




	public void togetherSurveySubmit() {
		PrintWriter out = null;
		try {
			out = this.getResponse().getWriter();
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			SysUser sysUser = sysUserService.checkNameAndPasswordIsValid(name,
					password);
			if (sysUser == null) {
				out.print("用户名或密码错误，请重试");
			} else {
				// 判断当前用户是否与当前用户相同
				if (sysUser.getUserId().equals(this.getLoginInfo().getUserId())) {
					out.print("不能使用当前的用户作为陪调人员！");
					return;
				}
				// 更新贷款表加入陪调人员Id
				Integer loanId = Integer.parseInt(request
						.getParameter("loanId"));
				LnLoanInfo lnLoanInfo = new LnLoanInfo();
				lnLoanInfo.setLoanId(loanId);
				lnLoanInfo.setTogetherSurveyUserId(sysUser.getUserId());
				lnLoanInfo.setTogetherSurveyDate(new Date());
				lnLoanInfoService.updateLnLoanInfo(lnLoanInfo);
				out.print(sysUser.getUserName());
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.print("交易异常，请刷新后重试或联系管理员！");
		}

	}

	// 共同审批
	public String centerUserTogetherApprove() {

		try {

			Integer loanId = Integer.parseInt(request.getParameter("loanId"));
			LnLoan lnLoan = lnLoanService.getLnLoanById(loanId);
			SysUserBean sysUserBean = null;
			if (lnLoan.getApproveCenterUserId1().equals(
					this.getLoginInfo().getUserId())) {
				sysUserBean = sysUserService.getUserListByIds(
						lnLoan.getApproveCenterUserId2() + "").get(0);
			} else {
				sysUserBean = sysUserService.getUserListByIds(
						lnLoan.getApproveCenterUserId1() + "").get(0);
			}

			request.setAttribute("userName", sysUserBean.getUserName());

			request.setAttribute("loanId", request.getParameter("loanId"));
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public void centerUserTogetherApproveSubmit() {
		PrintWriter out = null;
		try {
			out = this.getResponse().getWriter();
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			SysUser sysUser = sysUserService.checkNameAndPasswordIsValid(name,
					password);
			if (sysUser == null) {
				out.print("用户名或密码错误，请重试");
			} else {
				// 判断当前用户是否与当前用户相同
				if (sysUser.getUserId().equals(this.getLoginInfo().getUserId())) {
					out.print("不能使用当前的用户作为审批人员!");
					return;
				}
				Integer loanId = Integer.parseInt(request
						.getParameter("loanId"));
				LnLoan lnLoan = lnLoanService.getLnLoanById(loanId);
				if (!lnLoan.getApproveCenterUserId1().equals(
						sysUser.getUserId())
						&& !lnLoan.getApproveCenterUserId2().equals(
						sysUser.getUserId())) {
					out.print("请使用系统提示的人员进行登入!");
					return;
				}

				out.print("success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.print("交易异常，请刷新后重试或联系管理员！");
		}
	}

	/**
	 * 提交审批
	 */
	public void submitApproval() {

		/**
		 * 1.判断当前用户是否是从驳回调查
		 * 		a)在驳回调查中，不能再次修改 审批中心或待审会
		 * 		b)驳回调查提交后，需要清空之前审批的字段
		 * 		c)后续判断当前审批是否是驳回状态，是则通过备份字段获取流程信息
		 * 2.判断当前提交审批是审批中心还是待审会 
		 * 		a)审批中心 ：先提交团队主管审核，再由团队主管审核通过后提交给审批中人员，随机分配
		 * 		b)待审会：提交给调查人员所在的后台人员，随机分配
		 *
		 * @author Alber
		 */
		PrintWriter out = null;
		try {
			HttpServletResponse response = this.getResponse();
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
			String loanId = request.getParameter("loanId");
			LnLoan lnLoan = lnLoanService.selectLoanById(Integer.parseInt(loanId));
			LnLoan lnLoanUpdate = new LnLoan();
			if (lnLoan.getLoanStatusId() != 3) {// 判断当前贷款状态是否满足
				out.write("当前贷款状态不是为调查状态！无法提交审批！");
				return;
			}


			int isReject =(lnLoan.getIsReject() == null ? 0 : lnLoan.getIsReject());
			String strApproveBakMag = lnLoan.getApproveBakMsg();
			if(isReject == 1){ //当前调查为驳回				
				if(lnLoan.getApproveBakMsg() == null||lnLoan.getApproveBakMsg().trim().equals("")){
					strApproveBakMag =lnLoanService.saveApproveDataBakMsg(loanId);
				}else{
					JSONObject jsonObject = JSONObject.fromObject(lnLoan.getApproveBakMsg());
					LnLoanApproveBakMsg loanApproveBakMsg = (LnLoanApproveBakMsg) JSONObject.toBean(jsonObject, LnLoanApproveBakMsg.class);
					if(loanApproveBakMsg.getApproveCenterUserId1()==null || loanApproveBakMsg.getApproveCenterUserId1()==0){
						strApproveBakMag=lnLoanService.saveApproveDataBakMsg(loanId);
					}
				}
				lnLoanService.clearApproveData(Integer.parseInt(loanId));
			}
			//公共参数-------------
			lnLoanUpdate.setIsReject(0);
			lnLoanUpdate.setSurveySubmitDate(new Date());
			lnLoanUpdate.setEventId(4);
			lnLoanUpdate.setLoanStatusId(4);
			lnLoanUpdate.setApproveProcessId(approveProcessId);
			lnLoanUpdate.setLoanId(Integer.parseInt(loanId));
			List<SysUser> l=sysUserService.getSysUserTeamChiefByUserId(this.getLoginInfo().getUserId());
			SysUser sysUser = sysUserService.getSysUserTeamChiefByUserId(this.getLoginInfo().getUserId()).get(0);
			lnLoanUpdate.setApproveDirectorUserId(sysUser.getUserId());
			/*
			if (approveProcessId == 1) { // 审批中心  主管编号不会改变，所以不需要判断是否当前是驳回
				SysUser sysUser = sysUserService.getSysUserTeamChiefByUserId(this.getLoginInfo().getUserId()).get(0);
				lnLoanUpdate.setApproveDirectorUserId(sysUser.getUserId());
			} else if (approveProcessId == 2) {// 待审会
				Integer managerUserId = sysUserService.getReadomTeamManagerUserId(this.getLoginInfo().getUserId(),"approveBackerUserId");
				if(isReject == 1){ //当前调查为驳回
					//引用之前的后台人员编号
					if(lnLoan.getApproveBackerUserId()==null ||lnLoan.getApproveBackerUserId().equals(0)){
						lnLoanUpdate.setApproveBackerUserId(managerUserId);
					}else{
						lnLoanUpdate.setApproveBackerUserId(lnLoan.getApproveBackerUserId());
					}
				}else{

					lnLoanUpdate.setApproveBackerUserId(managerUserId);
				}
			}
			*/
			// 插入贷款历史操作记录
			LnOpHistory lnOpHistory = new LnOpHistory();
			lnOpHistory.setUserId(this.getLoginInfo().getUserId());
			lnOpHistory.setOpHistoryDate(new Date());
			lnOpHistory.setBeforeStatusId(3); // 待分配
			lnOpHistory.setAfterStatusId(4); // 待调查
			//lnOpHistory.setContent("贷款提交审批");
			if (approveProcessId == 1) {
				lnOpHistory.setContent("贷款提交审批(审批中心)_PC");
			}else{
				lnOpHistory.setContent("贷款提交审批(审贷会)_PC");
			}
			//lnOpHistory.setRemark(strApproveBakMag==null ? "":strApproveBakMag); // 历史记录备注
			lnOpHistory.setRemark("");
			lnOpHistory.setLoanId(Integer.parseInt(loanId));

			lnLoanService.submitApproval(lnLoanUpdate, lnOpHistory);
			out.print("success");
		} catch (Exception e) {
			e.printStackTrace();
			out.write("提交审批失败，请重试或联系管理员！");
		}
	}

	/**
	 * 打开更换贷款人页面
	 *
	 * @return
	 */
	public String changeLoanPersonPage() {
		try {
			String loanId = request.getParameter("loanId");
			String customerIdStr = request.getParameter("customerId");
			if (StringUtils.isNotEmpty(customerIdStr)) {
				Integer customerId = Integer.parseInt(customerIdStr);
				BaseCrmCustomer baseCrmCustomer = crmCustomerService
						.getCrmCustomerById(customerId);
				request.setAttribute("customerName",
						baseCrmCustomer.getCustomerName());
			}
			request.setAttribute("loanId", loanId);
			request.setAttribute("customerId", customerIdStr);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("UnExamLoanAction % changeLoanPersonPage", e);
			return ERROR;
		}
	}





	/**
	 * 验证是否更换为共同借贷人或担保人
	 */
	public void isCoBorrowerOrGuarantor() {
		this.getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = this.getResponse().getWriter();
			String loanIdStr = request.getParameter("loanId");
			String customerIdStr = request.getParameter("customerId");
			if (StringUtils.isNotEmpty(loanIdStr)) {
				Integer loanId = Integer.parseInt(loanIdStr);
				if (StringUtils.isNotEmpty(customerIdStr)) {
					Integer customerId = Integer.parseInt(customerIdStr);
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("loanId", loanId);
					paramMap.put("customerId", customerId);
					Integer coBorrowerCount = lnLoanCoBorrowerService
							.getCoBorrowerCount(paramMap);
					if (coBorrowerCount.equals(1)) {
						out.print("coBorrower");
					} else if (coBorrowerCount.equals(0)) {
						Integer guarantorCount = lnLoanGuarantorService
								.getGuarantorCount(paramMap);
						if (guarantorCount.equals(1)) {
							out.print("guarantor");
						} else if (guarantorCount.equals(0)) {
							out.print("loan");
						}
					}
				} else {
					// 完全新建客户
					out.print("loan");
				}
			}
			out.close();
		} catch (Exception e) {
			if (out != null) {
				out.close();
			}
			logger.error("UnExamLoanAction % isCoBorrowerOrGuarantor", e);
		}
	}

	//添加还款计划
	public  void  addRepaymentPlan(){
		try {
			this.getResponse().setContentType("text/html;charset=utf-8");
			PrintWriter out = this.getResponse().getWriter();
			String loanIdstr = request.getParameter("loanId");
			String jsonString = request.getParameter("jsonString");
			String interestString = request.getParameter("interestString");
			String registerLoanDate = request.getParameter("registerLoanDate");
			String repayDateStr = request.getParameter("repayDate");
			String money = request.getParameter("money");
			String repaRate = request.getParameter("repaRate");
			String resultRepayWayId = request.getParameter("resultRepayWayId");
			String adviceRepayWayId = request.getParameter("adviceRepayWayId");
			String  avg = request.getParameter("avg");
			LnLoanInfo lnLoanInfo =new LnLoanInfo();
			if(StringUtils.isNotBlank(adviceRepayWayId)){
				lnLoanInfo.setAdviceRepayWayId(adviceRepayWayId);
			}
			if(StringUtils.isNotBlank(resultRepayWayId)){
				lnLoanInfo.setResultRepayWayId(resultRepayWayId);
			}
			lnLoanInfo.setLoanId(Integer.parseInt(loanIdstr));
			lnLoanInfoService.updateLnLoanInfo(lnLoanInfo);
			Integer loanId = 0;
			if(StringUtils.isNotBlank(loanIdstr)&&StringUtils.isNumeric(loanIdstr)){
				loanId = Integer.valueOf(loanIdstr);
			}
			Date loanDate = null;
			if(StringUtils.isNotBlank(registerLoanDate)){
//				2017-06-12
				loanDate = new SimpleDateFormat("yyyy-MM-dd").parse(registerLoanDate);
			}
			Integer repayDate = 1;
			if(StringUtils.isNotBlank(repayDateStr)&&StringUtils.isNumeric(repayDateStr)){
				repayDate = Integer.valueOf(repayDateStr);
			}

			if(StringUtils.isNotBlank(jsonString)&&loanId>0&&null!=loanDate  &&StringUtils.isNotBlank(interestString)){
				//删除该笔贷款还款计划
				List<Integer> loanIdList = new ArrayList<Integer>();
				loanIdList.add(loanId);
				LnRepaymentPlanService.deletePlanByLoanIdBatch(loanIdList);

				//计算贷款时间
				JSONObject jsonObject = JSONObject.fromObject(jsonString);
				Iterator<String> jsonIter = jsonObject.keys();//遍历JSON串，String-->jsopn


				JSONObject jsoninsert = JSONObject.fromObject(interestString);
				Iterator<String> insertIter = jsoninsert.keys();//遍历JSON串，String-->jsopn

				LnRepaymentPlan plan = new LnRepaymentPlan();
				BigDecimal bigInterest=new BigDecimal(0.000);
				BigDecimal bigMoney=new BigDecimal(money);
				BigDecimal bigLimitYear=new BigDecimal(repaRate);
				BigDecimal chu=new BigDecimal(12.0000);
				BigDecimal avge=new BigDecimal(avg);

				while(jsonIter.hasNext()){
					String key = jsonIter.next();
					String one =insertIter.next();
					String two=jsoninsert.getString(one);
					String value = jsonObject.getString(key);
					plan.setLoanId(loanId);
					plan.setPlanDate(loanDateCalender(loanDate, repayDate, Integer.valueOf(key)));
					plan.setPrincipal(new BigDecimal(value).setScale(2, BigDecimal.ROUND_UP));
					plan.setSortno(Integer.valueOf(key));
					plan.setPaidTag("0");
					plan.setCreateDate(new Date());
					plan.setCreateUser(this.getLoginInfo().getUserId());
					plan.setInterest(new BigDecimal(two).setScale(3,BigDecimal.ROUND_UP));
					LnRepaymentPlanService.addRepaymentPlan(plan);
				}

			}

		}catch (Exception e){
			logger.error("addExceptionRepaymentPlan",e);
		}
	}

	private Date loanDateCalender(Date loanDate,Integer repayDate,Integer addMonth){

		//贷款时间
		Calendar calender = Calendar.getInstance();
		calender.setTime(loanDate);
		int day = calender.get(Calendar.DAY_OF_MONTH);

		//
		if(repayDate<=day||(repayDate-day)<15){
			//第一次还款时间
			calender.add(Calendar.MONTH,1);
			calender.set(Calendar.DAY_OF_MONTH, repayDate);
			//
			Calendar calenderLoanDate = Calendar.getInstance();
			calenderLoanDate.setTime(loanDate);

			//
			if((calender.get(Calendar.DAY_OF_YEAR)-calenderLoanDate.get(Calendar.DAY_OF_YEAR)<15)){
				calender.add(Calendar.MONTH,1);
			}
		}else{
			calender.set(Calendar.DAY_OF_MONTH, repayDate);
		}

		calender.add(Calendar.MONTH,addMonth-1);


		return  calender.getTime();

	}




	/**
	 * 更换贷款人
	 */
	public void changeLoanPerson() {
		PrintWriter out = null;
		try {
			out = this.getResponse().getWriter();
			String loanIdStr = request.getParameter("loanId");
			String oldCustomerIdStr = request.getParameter("oldCustomerId");
			String customerIdStr = request.getParameter("customerId");
			String loanCustomerName = request.getParameter("loanCustomerName");
			String changeLoanCustomerName = request
					.getParameter("changeLoanCustomerName");

			if (StringUtils.isNotEmpty(loanIdStr)
					&& StringUtils.isNotEmpty(oldCustomerIdStr)
					&& StringUtils.isNotEmpty(customerIdStr)) {
				Integer loanId = Integer.parseInt(loanIdStr);
				Integer oldCustomerId = Integer.parseInt(oldCustomerIdStr);
				Integer customerId = Integer.parseInt(customerIdStr);

				LnOpHistory lnOpHistory = new LnOpHistory();
				lnOpHistory.setUserId(this.getLoginInfo().getUserId());
				lnOpHistory.setOpHistoryDate(new Date());
				lnOpHistory.setBeforeStatusId(3); // 待调查
				lnOpHistory.setAfterStatusId(3); // 待调查
				// lnOpHistory.setContent("贷款人由" + loanCustomerName + "改为" +
				// changeLoanCustomerName);
				lnOpHistory.setLoanId(loanId);

				// 更换贷款人操作
				lnLoanService.changeLoanPerson(loanId, oldCustomerId,
						customerId, lnOpHistory);
			}
			out.print("success");
			out.close();
		} catch (Exception e) {
			if (out != null) {
				out.close();
			}
			logger.error("UnExamLoanAction % changeLoanPerson", e);
		}
	}

	/**
	 * 判断当前贷款(状态：待调查)是否可以提交审批
	 *
	 * @param loanId
	 * @return
	 */
	private Map<String, Object> isSubmitable(Integer loanId, Integer customerId) {
		logger.info("-----当前登录用户：" + this.getLoginInfo().getAccount()
				+ "----- 验证调查贷款是否满足提交审批的条件...");
		// 得到当前贷款相当的所有资料
		Map<String, Object> photoMap = new HashMap<String, Object>();
		photoMap.put("loanId", loanId);
		photoMap.put("statistics", 1);
		List<LoanData> loanDataList = customerDataService
				.getAllLoanDataById(photoMap);
		// 查看贷款人以及他的共同借贷人和担保人资料信息
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanId", loanId);
		List<LnLoan> lnLoanList = lnLoanService.queryLnLoanRelation(paramMap);
		// 所有共同借贷人
		// List<LnLoanCoBorrowerBean> coBorrowerCustomerList = new
		// ArrayList<LnLoanCoBorrowerBean>();
		// 所有担保人
		List<LnLoanGuarantorBean> guarantorCustomerList = new ArrayList<LnLoanGuarantorBean>();

		for (LnLoan lnLoan : lnLoanList) {
			// LnLoanCoBorrowerBean lnLoanCoBorrowerBean =
			// lnLoan.getLnLoanCoBorrowerBean();
			// if(lnLoanCoBorrowerBean.getCustomerId() != null){
			// coBorrowerCustomerList.add(lnLoanCoBorrowerBean);
			// }
			LnLoanGuarantorBean lnLoanGuarantorBean = lnLoan
					.getLnLoanGuarantorBean();
			if (lnLoanGuarantorBean.getCustomerId() != null) {
				guarantorCustomerList.add(lnLoanGuarantorBean);
			}
		}

		Map<String, Object> retMap = new HashMap<String, Object>();

		// 验证贷款人资料
		Integer loanDataResult = this.processLoanPersonData(loanDataList,
				customerId);
		if (loanDataResult == null || loanDataResult.intValue() != 0) {
			retMap.put("promptType", loanDataResult);
			return retMap;
		}
		// 验证共同借贷人资料
		// if(coBorrowerCustomerList.size() > 0){
		// Map<String,Object> coBorrowerDataResult =
		// this.processCoBorrowerData(loanDataList,coBorrowerCustomerList);
		// Integer promptType = (Integer)coBorrowerDataResult.get("promptType");
		// if(promptType == null || promptType.intValue() != 0){
		// return coBorrowerDataResult;
		// }
		// }
		// 验证担保人资料
		if (guarantorCustomerList.size() > 0) {
			Map<String, Object> guarantorDataResult = this
					.processGuarantorData(loanDataList, guarantorCustomerList);
			Integer promptType = (Integer) guarantorDataResult
					.get("promptType");
			if (promptType == null || promptType.intValue() != 0) {
				return guarantorDataResult;
			}
		}
		retMap.put("promptType", 0);
		logger.info("-----当前登录用户：" + this.getLoginInfo().getAccount()
				+ "----- 验证调查贷款是否满足提交审批的条件完成!");
		return retMap;
	}

	/**
	 * 判断贷款人是否有满足最低贷款要求的‘调查录音’和‘相关照片’
	 *
	 * @param loanDataList
	 * @param loanCusId
	 * @return
	 */
	private Integer processLoanPersonData(List<LoanData> loanDataList,
										  Integer loanCusId) {
		// 是否存在贷款人的录音资料 0、不存在;1、存在
		int hasAudioForLoanPerson = 0;
		// 是否存在贷款人的附件资料 0表示不存在;1表示存在
		// int hasAttachmentForLoanPerson = 0;
		// // 户口簿照片张数(至少1张)
		// int householdPhotoCountForLoanPerson = 0;
		// // 家庭照片张数(至少3张)
		// int homePhotoCountForLoanPerson = 0;
		// // 经营地照片张数(至少3张)
		// int manageDirPhotoCountForLoanPerson = 0;
		for (LoanData loanData : loanDataList) {
			Integer dataType = loanData.getDataType();
			Integer queryCusId = loanData.getCustomerId();
			Integer eventId = loanData.getEventId();
			if (!loanCusId.equals(queryCusId)) {
				continue;
			}
			// Integer photoTypeId = loanData.getPhotoTypeId();
			// if (dataType != null && dataType == 1) {
			// // 照片
			// // 贷款人照片
			// if (photoTypeId != null && photoTypeId.intValue() == 4) {
			// // 户口簿照片(至少1张)
			// // if (householdPhotoCountForLoanPerson != 1) {
			// // householdPhotoCountForLoanPerson = 1;
			// // }
			// householdPhotoCountForLoanPerson += 1;
			// } else if (photoTypeId != null && photoTypeId.intValue() == 5) {
			// // 家庭照片(至少3张)
			// // if (homePhotoCountForLoanPerson != 3) {
			// // homePhotoCountForLoanPerson += 1;
			// // }
			// homePhotoCountForLoanPerson += 1;
			// } else if (photoTypeId != null && photoTypeId.intValue() == 6) {
			// // 经营地照片(至少3张)
			// // if (manageDirPhotoCountForLoanPerson != 3) {
			// // manageDirPhotoCountForLoanPerson += 1;
			// // }
			// manageDirPhotoCountForLoanPerson += 1;
			// }
			// } else
			if (dataType != null && dataType == 2 && eventId != null
					&& LoanConstants.LOAN_EXAM_EVENT.equals(eventId)) {
				// 录音
				// 找到了贷款人的录音资料
				if (hasAudioForLoanPerson != 1) {
					hasAudioForLoanPerson = 1;
				}
			}
			// if (dataType != null && dataType == 4 && eventId != null &&
			// LoanConstants.LOAN_EXAM_EVENT.equals(eventId)) {
			// // 录音
			// // 找到了贷款人的附件资料
			// if (hasAttachmentForLoanPerson != 1) {
			// hasAttachmentForLoanPerson = 1;
			// break;
			// }
			// }
		}
		// 判断贷款人是否有完整的资料
		if (hasAudioForLoanPerson == 0) {
			// 贷款人没有调查录音资料
			return 1;// 提示：贷款审批至少需有一段调查录音！
		}
		// if (hasAttachmentForLoanPerson == 0){
		// //贷款人没有调查附件资料
		// return 7;//提示：贷款提交到审批环节至少需要上传一个附件！
		// }
		// if ((householdPhotoCountForLoanPerson < 1) ||
		// (homePhotoCountForLoanPerson < 3)
		// || (manageDirPhotoCountForLoanPerson < 3)) {
		// return 2;// 提示：贷款审批至少需要一张户籍照片、三张家庭照片、三张经营地照片！
		// }
		return 0;
	}

	/**
	 * 判断共同借贷人是否有满足最低贷款要求的‘调查录音’和‘相关照片’
	 *
	 * @param loanDataList
	 *            该贷款相关的所有资料
	 * @param lnLoanCoBorrowerBeanList
	 *            所有共同借贷人
	 * @return
	 */
	private Map<String, Object> processCoBorrowerData(
			List<LoanData> loanDataList,
			List<LnLoanCoBorrowerBean> lnLoanCoBorrowerBeanList) {
		Map<String, Object> retMap = new HashMap<String, Object>();

		for (LnLoanCoBorrowerBean borrowerBean : lnLoanCoBorrowerBeanList) {
			// 共同贷款人资料

			// 是否存在共同贷款人的录音资料
			int hasAudioForCommLoanPerson = 0;
			// // 户口簿照片张数(至少1张)
			// int householdPhotoCountForCommLoanPerson = 0;
			// // 家庭照片张数(至少3张)
			// int homePhotoCountForCommLoanPerson = 0;
			// // 头像照片张数
			// int headPhotoCountForCommLoanPerson = 0;
			// // 身份证正面张数
			// int cardPhotoPlusCountForCommLoanPerson = 0;
			// // 身份证背面张数
			// int cardPhotoMinusCountForCommLoanPerson = 0;

			Integer customerId = borrowerBean.getCustomerId();
			for (LoanData loanData : loanDataList) {
				Integer queryCusId = loanData.getCustomerId();
				if (!customerId.equals(queryCusId)) {
					continue;
				}
				Integer dataType = loanData.getDataType();
				// Integer photoTypeId = loanData.getPhotoTypeId();
				// if (dataType.intValue() == 1) {
				// // 照片
				// // 共同贷款人照片
				// if (photoTypeId.intValue() == 4) {
				// // 户口簿照片(至少1张)
				// // if (householdPhotoCountForCommLoanPerson != 1) {
				// // householdPhotoCountForCommLoanPerson = 1;
				// // }
				// householdPhotoCountForCommLoanPerson += 1;
				// } else if (photoTypeId.intValue() == 5) {
				// // 家庭照片(至少3张)
				// // if (homePhotoCountForCommLoanPerson != 3) {
				// // homePhotoCountForCommLoanPerson += 1;
				// // }
				// homePhotoCountForCommLoanPerson += 1;
				// } else if (photoTypeId.intValue() == 1) {
				// // 头像照片
				// // if (headPhotoCountForCommLoanPerson != 1) {
				// // headPhotoCountForCommLoanPerson = 1;
				// // }
				// headPhotoCountForCommLoanPerson += 1;
				// } else if (photoTypeId.intValue() == 2) {
				// // 身份证正面
				// // if (cardPhotoPlusCountForCommLoanPerson != 1) {
				// // cardPhotoPlusCountForCommLoanPerson = 1;
				// // }
				// cardPhotoPlusCountForCommLoanPerson += 1;
				// } else if (photoTypeId.intValue() == 3) {
				// // 身份证背面
				// // if (cardPhotoMinusCountForCommLoanPerson != 1) {
				// // cardPhotoMinusCountForCommLoanPerson = 1;
				// // }
				// cardPhotoMinusCountForCommLoanPerson += 1;
				// }
				// } else
				if (dataType != null && dataType.equals(2)) {
					// 录音
					// 找到了共同贷款人录音资料
					// if (hasAudioForCommLoanPerson != 1) {
					// hasAudioForCommLoanPerson = 1;
					// }
					hasAudioForCommLoanPerson += 1;
				} else if (dataType.intValue() > 2) {
					break;
				}
			}

			// 判断共同贷款人是否有完整的资料
			if (hasAudioForCommLoanPerson == 0) {
				retMap.put("promptType", 3); // 提示：贷款至少需有一段共同借贷人录音
				retMap.put("promptCustomerName", borrowerBean.getCustomerName());
				return retMap;
			}
			// if ((householdPhotoCountForCommLoanPerson == 0)
			// || (homePhotoCountForCommLoanPerson < 3) ||
			// (headPhotoCountForCommLoanPerson == 0)
			// || (cardPhotoPlusCountForCommLoanPerson == 0)
			// || (cardPhotoMinusCountForCommLoanPerson == 0)) {
			// retMap.put("promptType", 4); //
			// 提示：贷款审批至少需要共同借贷人三张身份证照片、一张户籍照片、三张家庭照片！
			// retMap.put("promptCustomerName", borrowerBean.getCustomerName());
			// return retMap;
			// }
		}
		retMap.put("promptType", 0);
		return retMap;
	}

	/**
	 * 判断担保人是否有满足最低贷款要求的‘调查录音’和‘相关照片’
	 *
	 * @param loanDataList
	 *            该贷款相关的所有资料
	 * @param lnLoanGuarantorBeanList
	 *            所有担保人
	 * @return
	 */
	private Map<String, Object> processGuarantorData(
			List<LoanData> loanDataList,
			List<LnLoanGuarantorBean> lnLoanGuarantorBeanList) {
		Map<String, Object> retMap = new HashMap<String, Object>();

		for (LnLoanGuarantorBean guarantorBean : lnLoanGuarantorBeanList) {
			// 担保人资料
			// 是否存在担保人的录音资料
			int hasAudioForGuarantor = 0;
			// // 户口簿照片(至少1张)
			// int householdPhotoCountForGuarantor = 0;
			// // 家庭照片(至少3张)
			// int homePhotoCountForGuarantor = 0;
			// // 经营地照片张数(至少3张)
			// int manageDirPhotoCountForLoanPerson = 0;
			// // 头像照片
			// int headPhotoCountForGuarantor = 0;
			// // 身份证正面
			// int cardPhotoPlusForGuarantor = 0;
			// // 身份证背面
			// int cardPhotoMinusForGuarantor = 0;
			Integer customerId = guarantorBean.getCustomerId();
			for (LoanData loanData : loanDataList) {
				Integer queryCusId = loanData.getCustomerId();
				if (!customerId.equals(queryCusId)) {
					continue;
				}
				Integer dataType = loanData.getDataType();
				// Integer photoTypeId = loanData.getPhotoTypeId();
				// if (dataType.intValue() == 1) {
				// // 照片
				// // 担保人照片
				// if (photoTypeId.intValue() == 4) {
				// // 户口簿照片(至少1张)
				// // if (householdPhotoCountForGuarantor != 1) {
				// // householdPhotoCountForGuarantor = 1;
				// // }
				// householdPhotoCountForGuarantor += 1;
				// } else if ((photoTypeId.intValue() == 5)) {
				// // 家庭照片or经营地照片(至少1张)
				// // if (homePhotoCountForGuarantor != 1) {
				// // homePhotoCountForGuarantor = 1;
				// // }
				// homePhotoCountForGuarantor += 1;
				// } else if (photoTypeId.intValue() == 6){
				// manageDirPhotoCountForLoanPerson += 1;
				// }else if (photoTypeId.intValue() == 1) {
				// // 头像照片
				// // if (headPhotoCountForGuarantor != 1) {
				// // headPhotoCountForGuarantor = 1;
				// // }
				// headPhotoCountForGuarantor += 1;
				// } else if (photoTypeId.intValue() == 2) {
				// // 身份证正面
				// // if (cardPhotoPlusForGuarantor != 1) {
				// // cardPhotoPlusForGuarantor = 1;
				// // }
				// cardPhotoPlusForGuarantor += 1;
				// } else if (photoTypeId.intValue() == 3) {
				// // 身份证背面
				// // if (cardPhotoMinusForGuarantor != 1) {
				// // cardPhotoMinusForGuarantor = 1;
				// // }
				// cardPhotoMinusForGuarantor += 1;
				// }
				// } else
				if (dataType != null && dataType.equals(2)) {
					// 录音
					// 找到了担保人录音资料
					if (hasAudioForGuarantor != 1) {
						hasAudioForGuarantor = 1;
					}
				} else if (dataType.intValue() > 2) {
					break;
				}
			}
			// 判断担保人是否有完整的资料
			if (hasAudioForGuarantor != 1) {
				retMap.put("promptType", 5); // 提示：贷款至少需有一段担保人录音！
				retMap.put("promptCustomerName",
						guarantorBean.getCustomerName());
				return retMap;
			}
			// if ((householdPhotoCountForGuarantor == 0) ||
			// (homePhotoCountForGuarantor < 3 &&
			// manageDirPhotoCountForLoanPerson < 3)
			// || (headPhotoCountForGuarantor == 0) ||
			// (cardPhotoPlusForGuarantor == 0)
			// || (cardPhotoMinusForGuarantor == 0)) {
			// retMap.put("promptType", 6); //
			// 提示：贷款审批至少需要担保人三张身份证照片、一张户籍照片、三张家庭照片或经营地照片！
			// retMap.put("promptCustomerName",
			// guarantorBean.getCustomerName());
			// return retMap;
			// }
		}
		retMap.put("promptType", 0);
		return retMap;
	}

	/**
	 * 驳回调查编辑页面
	 *
	 * @return
	 */
	public String rejectExamPage() {
		// TODO:修改
		return SUCCESS;
		/*
		 * try { logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+
		 * "----- 进入审批驳回后的调查贷款页面..."); String loanId =
		 * request.getParameter("loanId"); String customerId =
		 * request.getParameter("customerId");
		 * 
		 * // 贷款基本信息 LnLoan loan =
		 * lnLoanService.getLnLoanById(Integer.parseInt(loanId));
		 * 
		 * Map<String,Object> paramMap = new HashMap<String, Object>();
		 * paramMap.put("eventId",3); paramMap.put("loanId",loan.getLoanId());
		 * //贷款表单附件信息 List<Form> formAttachmentList =
		 * dataFormService.selectFormAttachment(paramMap);
		 * request.setAttribute("loanId",loanId);
		 * request.setAttribute("approveRemark",loan.getApproveRemark());
		 * request.setAttribute("customerId",customerId);
		 * request.setAttribute("loan",loan);
		 * request.setAttribute("formAttachmentList",formAttachmentList); return
		 * SUCCESS; }catch (Exception e){
		 * logger.error("UnExamLoanAction % rejectExamPage",e); return ERROR; }
		 */}

	public void autoUploadAttachment() {
		// TODO:修改
		logger.info("-----当前登录用户：" + this.getLoginInfo().getAccount()
				+ "----- 上传调查贷款附件...");
		this.getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			String loanCusIdStr = request.getParameter("loanCusId");
			if (StringUtils.isNotEmpty(loanCusIdStr)) {
				String[] loanCusIdArr = loanCusIdStr.split(",");
				if (loanCusIdArr.length == 3) {
					Integer userId = this.getLoginInfo().getUserId();

					if (fileInputFileName != null
							&& !fileInputFileName.equals("")) {
						String strLoanId = loanCusIdArr[0];
						String caseNo = "";
						CustomerData customerData = new CustomerData();
						if (StringUtils.isNotEmpty(strLoanId)) {
							Integer loanId = Integer.parseInt(strLoanId);
							customerData.setLoanId(loanId);
							customerData.setCreateUser(userId);
							customerData.setUploadUserId(userId);
							LnLoan lnLoan = lnLoanService.getLnLoanById(loanId);
							customerData.setCustomerId(lnLoan.getCustomerId());
							caseNo = caseHelperService.getCaseNo(customerData);
						}
						logger.info("-----当前登录用户：" + this.getLoginInfo().getAccount() + "----- 正式上传调查贷款附件...");
						Integer fileId = sysUploadFileService.saveFile( fileInput, fileInputFileName, userId, true, caseNo, customerData);
//						Integer fileId = sysUploadFileService.saveStaticFile( fileInput, fileInputFileName, userId, true, caseNo, customerData);
						logger.info("-----当前登录用户：" + this.getLoginInfo().getAccount() + "----- 调查贷款附件上传成功！");

						out = this.getResponse().getWriter();
						Integer loanId = Integer.parseInt(loanCusIdArr[0]);
						Integer customerId = Integer.parseInt(loanCusIdArr[1]);
						Integer eventId = null;
						if (loanCusIdArr[2].equals("1")) {
							eventId = LoanConstants.LOAN_APPLY_EVENT;
						} else if (loanCusIdArr[2].equals("2")) {
							eventId = LoanConstants.LOAN_DISTRIBUTION_EVENT;
						} else if (loanCusIdArr[2].equals("3")) {
							eventId = LoanConstants.LOAN_EXAM_EVENT;
						} else if (loanCusIdArr[2].equals("4")) {
							eventId = LoanConstants.LOAN_APPROVE_EVENT;
						} else if (loanCusIdArr[2].equals("5")) {
							// 放贷
							eventId = LoanConstants.LOAN_LENDED_EVENT;
						} else if (loanCusIdArr[2].equals("6")) {
							// 贷后管理
							eventId = LoanConstants.LOAN_DUN_EVENT;
						}
						logger.info("-----当前登录用户："
								+ this.getLoginInfo().getAccount()
								+ "----- 调查贷款附件上传业务处理..."); // 在上传附件时，就把附件与贷款关联起来
						lnLoanService.relatedLoanAttachment(loanId, customerId,
								fileId, userId, eventId);
						logger.info("-----当前登录用户："
								+ this.getLoginInfo().getAccount()
								+ "----- 调查贷款附件上传业务处理成功！");
						out.write(fileId);
						out.close();
					}
				}
			}
		} catch (Exception e) {
			if (out != null) {
				out.close();
			}
			logger.error("UnExamLoanAction % autoUploadAttachment", e);
		}

	}

	public String getAttachmentList() {
		try {
			String loanIdStr = request.getParameter("loanId");
			String customerIdStr = request.getParameter("customerId");
			String loanStatusIdStr = request.getParameter("loanStatusId");
			if (StringUtils.isNotEmpty(loanIdStr)
					&& StringUtils.isNotEmpty(customerIdStr)
					&& StringUtils.isNotEmpty(loanStatusIdStr)) {
				Integer userId = this.getLoginInfo().getUserId();

				Integer loanId = Integer.parseInt(loanIdStr);
				Integer customerId = Integer.parseInt(customerIdStr);

				Map<String, Object> paramMap = new HashMap<String, Object>();
				if (loanStatusIdStr.equals("1")) {
					paramMap.put("eventId", LoanConstants.LOAN_APPLY_EVENT);
				} else if (loanStatusIdStr.equals("2")) {
					paramMap.put("eventId",
							LoanConstants.LOAN_DISTRIBUTION_EVENT);
				} else if (loanStatusIdStr.equals("3")) {
					//判断调查是否已经上传附件
					request.setAttribute("isExcitForm4", "true");
					paramMap.put("eventId", LoanConstants.LOAN_EXAM_EVENT);
					//	request.setAttribute("isExcitForm4", "true");
				} else if (loanStatusIdStr.equals("4")) {
					paramMap.put("eventId", LoanConstants.LOAN_APPROVE_EVENT);
					// 判断是否已经上传附件
					//	request.setAttribute("isExcitForm4", "true");
				} else if (loanStatusIdStr.equals("5")) {
					//判断审批是否已经上传附件
					//request.setAttribute("isExcitForm4", "true");
				}else if (loanStatusIdStr.equals("5")) {
					// 放贷
					paramMap.put("eventId", LoanConstants.LOAN_LENDED_EVENT);
					//	request.setAttribute("isExcitForm4", "true");
				} else if (loanStatusIdStr.equals("6")) {
					// 贷后管理
					paramMap.put("eventId", LoanConstants.LOAN_DUN_EVENT);
				}
				paramMap.put("loanId", loanId);
				paramMap.put("customerId", customerId);
				// 贷款表单附件信息
				List<Form> formAttachmentList = dataFormService
						.selectFormAttachment(paramMap);

				request.setAttribute("formAttachmentList", formAttachmentList);
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("UnExamLoanAction % getAttachmentList", e);
			return ERROR;
		}
	}

	/**
	 * 上传附件
	 *
	 * @throws java.io.IOException
	 */
	public void uploadAttachment() {
		// PrintWriter out = null;
		//
		// try {
		// try {
		// out = this.getResponse().getWriter();
		// Integer userId = this.getLoginInfo().getUserId();
		//
		// if (fileInputFileName != null && !fileInputFileName.equals("")) {
		// Integer fileId =
		// sysUploadFileService.saveFile(fileInput,fileInputFileName,userId,
		// true,"");
		//
		// //返回成功信息
		// out.write(fileId.toString());
		// }
		// }finally {
		// if (out != null){
		// out.close();
		// }
		// }
		// }catch (Exception e){
		// logger.error("UnExamLoanAction % uploadAttachment",e);
		// }
	}

	/**
	 * 删除表单附件(目前只删除记录，不删除实际文件)
	 */
	public void delFormAttachment() {
		PrintWriter out = null;
		try {
			try {
				out = this.getResponse().getWriter();
				Map<String, Object> conds = new HashMap<String, Object>();
				String formIdStr = request.getParameter("formId");
				if (!StringUtil.isNullOrEmpty(formIdStr)) {
					Integer formId = Integer.parseInt(formIdStr);
					conds.put("formId", formId);
					dataFormService.delFormAttachment(conds);

					// 查询还有几个附件
					String loanIdStr = request.getParameter("loanId");
					String customerIdStr = request.getParameter("customerId");
					if (StringUtils.isNotEmpty(loanIdStr)
							&& StringUtils.isNotEmpty(customerIdStr)) {
						Integer loanId = Integer.parseInt(loanIdStr);
						Integer customerId = Integer.parseInt(customerIdStr);
						Map<String, Object> paramMap = new HashMap<String, Object>();
						paramMap.put("loanId", loanId);
						paramMap.put("customerId", customerId);
						// 贷款表单附件信息
						List<Form> formAttachmentList = dataFormService
								.selectFormAttachment(paramMap);
						if (formAttachmentList.size() < 2) {
							out.write("success2");
						} else {
							out.write("success");
						}
					} else {
						out.write("success");
					}
				} else {
					out.write("删除失败");
				}
			} finally {
				if (out != null)
					out.close();
			}
		} catch (Exception e) {
			out.write("删除失败");
			if (out != null)
				out.close();
			logger.error("UnExamLoanAction % delFormAttachment", e);
		}
	}

	/**
	 * 下载表单附件文件
	 */
	public void downloadFormAttachment() {
		try {
			String fileIdStr = request.getParameter("fileId");
			if (!StringUtil.isNullOrEmpty(fileIdStr)) {
				CustomerData data = new CustomerData();
				data.setCreateUser(this.getLoginInfo().getUserId());
				Integer fileId = Integer.parseInt(fileIdStr);
				sysUploadFileService.downFile(fileId, data);
			}
		} catch (Exception e) {
			logger.error("UnExamLoanAction % downloadFormAttachment", e);
		}
	}

	public String approveReject() {
		request.setAttribute("loanId", request.getParameter("loanId"));
		return SUCCESS;
	}

	public String backReject() {
		return SUCCESS;
	}


	/**
	 * 贷款驳回
	 */
	public void approveRejectSubmit() {
		PrintWriter out = null;
		try {
			out = this.getResponse().getWriter();
			int loanId = Integer.parseInt(request.getParameter("loanId"));
			String resultNote = request.getParameter("resultNote");

			/**
			 * 1.将当前贷款状态更新为调查状态 2.将驳回理由更新到贷款info表中 3.插入贷款历史记录
			 */

			LnLoan lnLoan = lnLoanService.selectLoanById(loanId);

			if (lnLoan.getApproveProcessId() == 1) { // 审批中心
				if (lnLoan.getApproveCenterUserId1() != null
						&& lnLoan.getApproveCenterUserId1() > 0) {// 已从主管审批交由审批中心
					lnLoan.setApproveCenterRejectDate(new Date());
				} else {
					lnLoan.setApproveDirectorRejectDate(new Date());
				}
			} else { // 贷审会
				lnLoan.setApproveBackerRejectDate(new Date());
			}
			lnLoan.setIsReject(1);

			LnLoanInfo lnLoanInfo = new LnLoanInfo();
			lnLoanInfo.setResultNote(resultNote);
			lnLoanInfo.setLoanId(loanId);

			// 贷款历史操作
			LnOpHistory lnOpHistory = new LnOpHistory();
			lnOpHistory.setLoanId(loanId);
			lnOpHistory.setOpHistoryDate(Calendar.getInstance().getTime());
			lnOpHistory.setUserId(this.getLoginInfo().getUserId());
			lnOpHistory.setContent("审批驳回拒绝");
			lnOpHistory.setRemark(resultNote);
			lnOpHistory.setBeforeStatusId(4);
			lnOpHistory.setAfterStatusId(3);

			lnLoan.setLoanStatusId(3);
			lnLoan.setEventId(3);
			lnLoanService.submitApproveLoan(lnLoan, lnLoanInfo, lnOpHistory);
			out.print("success");
		} catch (Exception e) {
			e.printStackTrace();
			out.print("请求失败，请刷新系统后重试或联系管理员！");
		}

	}
	//贷款撤回
	public void backRejectSubmit() {
		PrintWriter out = null;
		try {
			out = this.getResponse().getWriter();
			int loanId = Integer.parseInt(request.getParameter("loanId"));
			String resultNote = request.getParameter("resultNote");

			/**
			 * 1.将当前贷款状态更新为调查状态 2.将驳回理由更新到贷款info表中 3.插入贷款历史记录
			 */

			LnLoan lnLoan = lnLoanService.selectLoanById(loanId);
			Integer s1 = lnLoan.getApproveCenterUserId1();
			Integer s2 = lnLoan.getApproveCenterUserId2();
			if (lnLoan.getApproveProcessId() == 1) { // 审批中心
				if (	  s1== null	&& s2== null	) {// 已从主管审批交由审批中心
					lnLoan.setApproveCenterRejectDate(new Date());
					lnLoan.setIsReject(2);
					LnLoanInfo lnLoanInfo = new LnLoanInfo();
					lnLoanInfo.setResultNote(resultNote);
					lnLoanInfo.setLoanId(loanId);
					// 贷款历史操作
					LnOpHistory lnOpHistory = new LnOpHistory();
					lnOpHistory.setLoanId(loanId);
					lnOpHistory.setOpHistoryDate(Calendar.getInstance().getTime());
					lnOpHistory.setUserId(this.getLoginInfo().getUserId());
					lnOpHistory.setContent("审批撤回");
					lnOpHistory.setRemark(resultNote);
					lnOpHistory.setBeforeStatusId(4);
					lnOpHistory.setAfterStatusId(3);

					lnLoan.setLoanStatusId(3);
					lnLoan.setEventId(3);
					lnLoanService.submitApproveLoan(lnLoan, lnLoanInfo, lnOpHistory);
					out.print("success");

				} else {
					lnLoan.setApproveDirectorRejectDate(new Date());
					out.print("false");
				}
			} else { // 贷审会
				lnLoan.setApproveBackerRejectDate(new Date());
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.print("请求失败，请刷新系统后重试或联系管理员！");
		}

	}

	public void setLnLoanInfoDictionaryService(
			LnLoanInfoDictionaryService lnLoanInfoDictionaryService) {
		this.lnLoanInfoDictionaryService = lnLoanInfoDictionaryService;
	}

	public LnPledge getLnPledge() {
		return lnPledge;
	}

	public void setLnPledge(LnPledge lnPledge) {
		this.lnPledge = lnPledge;
	}

	public void setLnLoanPledgeService(LnLoanPledgeService lnLoanPledgeService) {
		this.lnLoanPledgeService = lnLoanPledgeService;
	}
	public LnExceptionRepaymentPlanService getLnExceptionRepaymentPlanService() {
		return lnExceptionRepaymentPlanService;
	}

	public void setLnExceptionRepaymentPlanService(LnExceptionRepaymentPlanService lnExceptionRepaymentPlanService) {
		this.lnExceptionRepaymentPlanService = lnExceptionRepaymentPlanService;
	}


}