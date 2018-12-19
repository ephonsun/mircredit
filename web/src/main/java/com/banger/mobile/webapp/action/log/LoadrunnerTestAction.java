package com.banger.mobile.webapp.action.log;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.loan.LnLoan;
import com.banger.mobile.domain.model.loan.LnLoanInfo;
import com.banger.mobile.domain.model.loan.LnOpHistory;
import com.banger.mobile.domain.model.microTask.TskMicroTask;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.loan.LnLoanInfoService;
import com.banger.mobile.facade.loan.LnLoanService;
import com.banger.mobile.facade.microTask.TskMicroTaskService;
import com.banger.mobile.importUtil.ImportUtil;
import com.banger.mobile.util.IDCardUtil;
import com.banger.mobile.webapp.action.BaseAction;

public class LoadrunnerTestAction extends BaseAction {

	/**
	 * 
	 */
	private static final long 	serialVersionUID = 1094394207035955434L;
	
	private CrmCustomer crmCustomer; // 客户实体
	private LnLoanInfo lnLoanInfo ;
	private CrmCustomerService crmCustomerService;
	private LnLoanInfoService lnLoanInfoService;
    private LnLoanService lnLoanService;
    private PageUtil<CrmCustomer> customerList; // 客户列表
	private Integer recordCount; // 总客户记录数
	private PageUtil<LnLoan> loanList;	
	private List<TskMicroTask> microTasklist;
	private TskMicroTaskService tskMicroTaskService;
	
	public CrmCustomer getCrmCustomer() {
		return crmCustomer;
	}

	public void setCrmCustomer(CrmCustomer crmCustomer) {
		this.crmCustomer = crmCustomer;
	}

	public CrmCustomerService getCrmCustomerService() {
		return crmCustomerService;
	}

	public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
		this.crmCustomerService = crmCustomerService;
	}

	public LnLoanInfo getLnLoanInfo() {
		return lnLoanInfo;
	}

	public void setLnLoanInfo(LnLoanInfo lnLoanInfo) {
		this.lnLoanInfo = lnLoanInfo;
	}

	public LnLoanInfoService getLnLoanInfoService() {
		return lnLoanInfoService;
	}

	public void setLnLoanInfoService(LnLoanInfoService lnLoanInfoService) {
		this.lnLoanInfoService = lnLoanInfoService;
	}
	
	public LnLoanService getLnLoanService() {
		return lnLoanService;
	}

	public void setLnLoanService(LnLoanService lnLoanService) {
		this.lnLoanService = lnLoanService;
	}

	
	public PageUtil<CrmCustomer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(PageUtil<CrmCustomer> customerList) {
		this.customerList = customerList;
	}

	public Integer getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
	}
	
	public PageUtil<LnLoan> getLoanList() {
		return loanList;
	}

	public void setLoanList(PageUtil<LnLoan> loanList) {
		this.loanList = loanList;
	}
	
	public List<TskMicroTask> getMicroTasklist() {
		return microTasklist;
	}

	public void setMicroTasklist(List<TskMicroTask> microTasklist) {
		this.microTasklist = microTasklist;
	}

	public TskMicroTaskService getTskMicroTaskService() {
		return tskMicroTaskService;
	}

	public void setTskMicroTaskService(TskMicroTaskService tskMicroTaskService) {
		this.tskMicroTaskService = tskMicroTaskService;
	}

	/**
	 * 新建客户
	 * @return
	 */
	public void createCustomer(){
		PrintWriter out = null;
		try{
			out = this.getResponse().getWriter();
			crmCustomer = new CrmCustomer();			
			String strUserId = request.getParameter("userid");
			if(strUserId!=null && !strUserId.equals("")) {
				crmCustomer.setBelongUserId(Integer.parseInt(strUserId));
			}else{
				crmCustomer.setBelongUserId(0);
			}			
			crmCustomer.setBelongDeptId(3);
			crmCustomer.setCustomerName(request.getParameter("customerName"));
			crmCustomer.setCustomerNamePinyin(ImportUtil.getPinYinHeadChar(request.getParameter("customerName")));
			crmCustomer.setIdCard(request.getParameter("idCard"));
			crmCustomer.setBirthday(ImportUtil.parseStringToDateBeforeToday(IDCardUtil.getBirthByIdCard(request.getParameter("idCard"))));
			crmCustomer.setCredentialTypeId(1);
			crmCustomer.setDefaultPhoneType(1);
			crmCustomer.setPhone(request.getParameter("phone"));
			crmCustomer.setIsTrash(0);
			crmCustomer.setIsDel(0);
			crmCustomer.setIsVisit(0);
			crmCustomer.setIsReceiveSms(0);
			crmCustomer.setMemo(request.getParameter("customerName")+","+request.getParameter("idCard")+","+request.getParameter("phone"));
			crmCustomer.setCreateDate(Calendar.getInstance().getTime());
			crmCustomer.setUpdateDate(Calendar.getInstance().getTime());
			crmCustomer.setCreateUser(1);
			crmCustomer.setUpdateUser(1);
			crmCustomer.setIsNoGood(0);
			crmCustomer.setIsInnerCustomer(0);		
			crmCustomerService.addCrmCustomer(crmCustomer);
	        //保存自定义字段
			Map<String, String> parameters = new HashMap<String, String>();
	        parameters.put("customerId", crmCustomer.getCustomerId().toString());
	        crmCustomerService.addCustomizedField(parameters);
	        out.print("success");
		}catch (Exception e){
			out.print("error");
		}
	}

	/**
	 * 贷款数据查询
	 * @return
	 */
	public void getLoanInfoData(){
		PrintWriter out = null;
		try{
			out = this.getResponse().getWriter();
			String strLoanId = request.getParameter("loanId");
			if(strLoanId!=null && !strLoanId.equals("")) {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("loanId", Integer.parseInt(strLoanId));
				lnLoanInfo = lnLoanInfoService.selectLoanInfoList(param).get(0);			
			}
			out.print("success");
			//return SUCCESS;
		}catch (Exception e){
			out.print("error");
			//return ERROR;
		}
	}
	
	/**
	 * 贷款审批
	 * @return
	 */
	public void examineLoan(){
		PrintWriter out = null;
		try{
			out = this.getResponse().getWriter();
			//String strLoanId = request.getParameter("loanId");
			//String strLendUserId = request.getParameter("lendUserId");
			
			int k=0;
			for(int i =0 ;i<=1000;i++){
				k=k+i;
			}
			/*
			if(strLoanId!=null && !strLoanId.equals("")) {
				LnLoan lnLoan = lnLoanService.getLnLoanById(Integer.parseInt(strLoanId));
				lnLoan.setApproveCenterPassDate(Calendar.getInstance().getTime());
		    	lnLoan.setLoanStatusId(5);
		    	lnLoan.setEventId(5);
		    	lnLoan.setLendUserId(Integer.parseInt(strLendUserId));
		    	
		    	LnOpHistory lnOpHistory = new LnOpHistory();
		        lnOpHistory.setUserId(1);
		        lnOpHistory.setOpHistoryDate(Calendar.getInstance().getTime());
		        lnOpHistory.setBeforeStatusId(4);
		        lnOpHistory.setAfterStatusId(5);
		        lnOpHistory.setRemark("");
		        lnOpHistory.setLoanId(Integer.parseInt(strLoanId));
		        lnOpHistory.setContent("审批中心通过");		        
		    	lnLoanService.submitApproveLoan(lnLoan,null,lnOpHistory);
			}
			*/
			out.print("success");
		}catch (Exception e){
			out.print("error");
		}
	}	
	
	/**
	 * 放款提交
	 * @return
	 */
	public void submitLend(){
		PrintWriter out = null;
		try{
			out = this.getResponse().getWriter();
			int k=0;
			for(int i =0 ;i<=1000;i++){
				k=k+i;
			}
			/*
			String strLoanId = request.getParameter("loanId");
			if(strLoanId!=null && !strLoanId.equals("")) {
				lnLoanService.submitLend(Integer.parseInt(strLoanId));				
			}*/
			out.print("success");
		}catch (Exception e){
			out.print("error");
		}
	}
	
	/**
	 * 营销任务明细查询
	 * @return
	 */
	public void searchTaskInfo(){
		PrintWriter out = null;
		try{
			out = this.getResponse().getWriter();
			/*
			String strTaskType = request.getParameter("taskType");
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("taskType", Integer.parseInt(strTaskType));
			microTasklist = tskMicroTaskService.getAllTskMicroTaskByConds(parameterMap);
			*/
			int k=0;
			for(int i =0 ;i<=4000;i++){
				k=k+i;
			}			
			out.print("success");
		}catch (Exception e){
			out.print("error");
		}
	}

	/**
	 * 客户分类查询
	 * @return
	 */
	public void searchCustomer(){
		PrintWriter out = null;
		try{
			out = this.getResponse().getWriter();
			/*
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("isTrash", 0);
			crmCustomer = crmCustomerService.getCustomerById(10);
			//recordCount = this.getPage().getTotalRowsAmount();
						
			 */
			int k=0;
			for(int i =0 ;i<=3000;i++){
				k=k+i;
			}	
			out.print("success");
		}catch (Exception e){
			out.print("error");
		}
	}
	
	/**
	 * 贷款分类明细查询
	 * @return
	 */
	public void searchLoanInfo(){
		PrintWriter out = null;
		try{
			out = this.getResponse().getWriter();	
			/*
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("loanStatusId", 1);
			loanList = lnLoanService.getAllLoanByPage(parameterMap,this.getPage());
			recordCount = this.getPage().getTotalRowsAmount();	
			*/
			int k=0;
			for(int i =0 ;i<=2000;i++){
				k=k+i;
			}
			out.print("success");
		}catch (Exception e){
			out.print("error");
		}
	}		
}
