package com.banger.mobile.webapp.action.loan;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.loan.LnLoan;
import com.banger.mobile.domain.model.loan.LnLoanAppFormBak;
import com.banger.mobile.domain.model.loan.LnLoanApproveBakMsg;
import com.banger.mobile.domain.model.loan.LnLoanInfo;
import com.banger.mobile.domain.model.loan.LnLoanInfoDictionary;
import com.banger.mobile.domain.model.loan.LnOpHistory;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.loan.LnLoanInfoDictionaryService;
import com.banger.mobile.facade.loan.LnLoanInfoService;
import com.banger.mobile.facade.loan.LnLoanService;
import com.banger.mobile.facade.loan.LnLoanTypeService;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author ouyl
 * @version $Id: NotMakeLoanAction.java v 0.1 ${} 上午11:42 ouyl Exp $
 *
 * 待审批贷款Action
 */

public class UnAssessLoanAction extends BaseAction {

    private static Logger logger = Logger.getLogger(NotMakeLoanAction.class);

    private LnLoanService lnLoanService;
    private String cusName;
    private Date approvalStartDate;
    private Date approvalEndDate;
    private Date startCreateDate;
    private Date endCreateDate;
    private Integer loanType;
    private SysUserService sysUserService;
    private LnLoanTypeService lnLoanTypeService;
    private DeptFacadeService deptFacadeService;
    private LnLoanInfoDictionaryService lnLoanInfoDictionaryService;
    private SysParamService sysParamService;
    private int develop;
    private LnLoanInfoService lnLoanInfoService;
    
    private LnLoanInfo lnLoanInfo;

    public String unAssessLoanList(){
        try{
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 进入放贷贷款菜单...");
            //贷款类型
        	// 贷款类型列表
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("dictionaryName", "DKLX");
			List<LnLoanInfoDictionary> typeList = lnLoanInfoDictionaryService
					.selectLoanInfoDictionaryList(paramMap);
			
            Map<String, Object> conds = new HashMap<String, Object>();
            String[] roles = this.getLoginInfo().getRoles();
            if(roles!=null && roles.length > 0 && roles[0].equals("5")){ //主管审批 
            	conds.put("approveDirectorUserId", this.getLoginInfo().getUserId());
            	request.setAttribute("role", "manager");
            }else {//审批中心审批APPROVE_CENTER_USER_ID1
            	conds.put("approveCenterUserId1", this.getLoginInfo().getUserId());
            	request.setAttribute("role", "center");
            }
            
            conds.put("loanStatusId", 4);   // 4表示待审批

            //待审批公用未放贷sql
            PageUtil<LnLoan> dataList = lnLoanService.getMakeLoanPage(conds, this.getPage());
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 共搜索出"+dataList.getItems().size()+"条符合条件的放贷贷款");

            request.setAttribute("dataList", dataList.getItems());
            request.setAttribute("recordCount", dataList.getItems().size());
            request.setAttribute("typeList", typeList);
            request.setAttribute("sysUserId",this.getLoginInfo().getUserId());

            return SUCCESS;
        }catch(Exception e){
            logger.error("notMakeLoanList", e);
            e.printStackTrace();
            return ERROR;
        }
    }

    public String unAssessLoanListQuery(){
        try{
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 按条件搜索放贷贷款...");
            //列表数据
            Map<String, Object> conds = new HashMap<String, Object>();
            String[] roles = this.getLoginInfo().getRoles();
            if(roles!=null && roles.length > 0 && roles[0].equals("5")){ //主管审批 
            	conds.put("approveDirectorUserId", this.getLoginInfo().getUserId());
            	request.setAttribute("role", "manager");
            }else {//审批中心审批APPROVE_CENTER_USER_ID1
            	conds.put("approveCenterUserId1", this.getLoginInfo().getUserId());
            	request.setAttribute("role", "center");
            }
            conds.put("loanStatusId", 4);   // 4表示待审批
            conds.put("cusName", cusName);
            conds.put("loanTypeId", loanType);

            endCreateDate = lnLoanService.addSecondsForDate(endCreateDate,59);
            conds.put("applyStartDate", startCreateDate);
            conds.put("applyEndDate", endCreateDate);

            //待审批公用未放贷sql
            PageUtil<LnLoan> dataList = lnLoanService.getMakeLoanPage(conds, this.getPage());
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 总共搜索出"+dataList.getItems().size()+"条符合条件的放贷贷款");
            request.setAttribute("dataList", dataList.getItems());
            request.setAttribute("recordCount", dataList.getPage().getTotalRowsAmount());
            request.setAttribute("sysUserId",this.getLoginInfo().getUserId());

            return SUCCESS;
        }catch (Exception e){
            logger.error("notMakeLoanListQuery", e);
            return ERROR;
        }
    }

    /**
     * 当前用户的下属用户
     *
     * @return
     */
    private List<Integer> getSysUserIds() {
        //当前用户的下属用户
//        List<SysUser> sysUsers = deptFacadeService.getBusinessManagerInCharegeOfUsers();
//        List<Integer> applyUserIds = new ArrayList<Integer>();
//        for (SysUser sysUser : sysUsers) {
//            applyUserIds.add(sysUser.getUserId());
//        }
//        return applyUserIds;
        List<Integer> applyUserIds = new ArrayList<Integer>();
        String belongUserIds = deptFacadeService.getUserIdsBelongToManager(this.getLoginInfo().getRoles(),"loanInfo");
        if (StringUtils.isNotEmpty(belongUserIds)){
            String[] belongUserIdArr = belongUserIds.split(",");
            if (belongUserIdArr != null && belongUserIdArr.length > 0){
                for (String belongUserId : belongUserIdArr){
                    applyUserIds.add(Integer.parseInt(belongUserId));
                }
            }
        }
        return applyUserIds;
    }

    /**
     * 获取查询条件时的申请人id(提交人id)列表
     *
     * @param applyUser
     * @return
     */
    private List<Integer> stringToList(String applyUser) {
        if (StringUtils.isNotEmpty(applyUser)) {
            String[] paramArray = applyUser.split(",");
            List<Integer> resultList = new ArrayList<Integer>();
            for (String str : paramArray) {
                resultList.add(Integer.parseInt(str));
            }
            return resultList;
        }
        return null;
    }

    public String unAssessLoanEdit(){
        try{
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 进入待审批贷款编辑界面...");
            String strLoanId = request.getParameter("loanId");
            Integer loanId = Integer.parseInt(strLoanId.trim());
          //  LnLoan loan = lnLoanService.getLnLoanById(loanId);
         //   String isEdit = request.getParameter("isEdit");
            String role = request.getParameter("role");
            request.setAttribute("role", role);
            request.setAttribute("loanId",loanId);
           // request.setAttribute("loan",loan);
            return SUCCESS;
        }catch (Exception e){
            logger.error("addLendRemark", e);
            return ERROR;
        }
    }

    //主管审批
    public void managerAssessLoan(){
    	/**
    	 * 
    	 * 1.判断当前的贷款是是否驳回过
    	 *    a)是： 取出备份字段内容，更新
    	 *    b)否: 
    	 * 		1.同时随机选出两位审批者人员
    	 * 		2.任意一个人员都可以进入审批，但必须通过另一个人的登入确认才能完成审批操作
    	 * @author Alber
    	 */
    	
    	PrintWriter out = null;
    	try{
	    	out = this.getResponse().getWriter();
	    	if(lnLoanInfo.getLoanId() == null && lnLoanInfo.getLoanId() < 0){
	    		out.print("当前贷款信息查询错误，刷新后重试！");
	    		return;
	    	}
	    	
	    	Boolean isDoubleApproval = isDoubleApproval(lnLoanInfoService.getAppLoanTypeIdByLoanId(lnLoanInfo.getLoanId()));//是否双人审批
	    	
	    	LnLoan lnLoan = lnLoanService.selectLoanById(lnLoanInfo.getLoanId());
	    	
	    	LnLoan lnLoanUpdate = new LnLoan();
	    	lnLoanUpdate.setLoanId(lnLoanInfo.getLoanId());
	    	lnLoanUpdate.setApproveDirectorPassDate(new Date());
	    	
	    	if(lnLoan.getApproveProcessId()==1){ //线上审批
	    		if(lnLoan.getApproveBakMsg() != null && !lnLoan.getApproveBakMsg().trim().equals("")){
		    		String approveBakMsg = lnLoan.getApproveBakMsg();
		    		JSONObject jsonObject = JSONObject.fromObject(approveBakMsg);
		    		LnLoanApproveBakMsg loanApproveBakMsg = (LnLoanApproveBakMsg) JSONObject.toBean(jsonObject, LnLoanApproveBakMsg.class);
		    		
		    		lnLoanUpdate.setApproveCenterUserId1(loanApproveBakMsg.getApproveCenterUserId1());
			    	lnLoanUpdate.setApproveCenterUserId2(loanApproveBakMsg.getApproveCenterUserId2());
			    	
		    		if(loanApproveBakMsg.getApproveCenterUserId1() <= 0){ //说明当时是从主管驳回
		    			List<Integer> approveCenterUser = sysUserService.getReadomApproveUserId(isDoubleApproval,lnLoanInfoService.getAdviceMoneyByLoanId(lnLoanInfo.getLoanId()));
				    	if(approveCenterUser == null || approveCenterUser.size() == 0){
				    		out.print("提交失败，审批者人数不够，请增加审批人员！");
				    		return;
				    	}
				    	
				    	if(isDoubleApproval){//双人审批
				    		lnLoanUpdate.setApproveCenterUserId1(approveCenterUser.get(0));
					    	lnLoanUpdate.setApproveCenterUserId2(approveCenterUser.get(1));
				    	}else{
				    		lnLoanUpdate.setApproveCenterUserId1(approveCenterUser.get(0));
				    	}
		    		}
		    		
		    	}else{
		    		List<Integer> approveCenterUser = sysUserService.getReadomApproveUserId(isDoubleApproval,lnLoanInfoService.getAdviceMoneyByLoanId(lnLoanInfo.getLoanId()));
			    	if(approveCenterUser == null || approveCenterUser.size() == 0){
			    		out.print("提交失败，审批者人数不够，请增加审批人员！");
			    		return;
			    	}
			    	if(isDoubleApproval){
			    		lnLoanUpdate.setApproveCenterUserId1(approveCenterUser.get(0));
				    	lnLoanUpdate.setApproveCenterUserId2(approveCenterUser.get(1));
			    	}else{
			    		lnLoanUpdate.setApproveCenterUserId1(approveCenterUser.get(0));
			    	}		    		
		    	}		    		
	    	}else{
	    		int isReject =(lnLoan.getIsReject() == null ? 0 : lnLoan.getIsReject());
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

	    	
	    	    	
	    	
	    	LnOpHistory lnOpHistory = new LnOpHistory();
	        lnOpHistory.setUserId(this.getLoginInfo().getUserId());
	        lnOpHistory.setOpHistoryDate(new Date());
	        lnOpHistory.setBeforeStatusId(4);
	        lnOpHistory.setAfterStatusId(4);
	        lnOpHistory.setRemark(lnLoanInfo.getResultNote());
	        lnOpHistory.setLoanId(lnLoanInfo.getLoanId());
	        lnOpHistory.setContent("主管审批通过");
	        lnLoanService.submitApproveLoan(lnLoanUpdate,lnLoanInfo,lnOpHistory);
	        out.print("success");
    	}catch(Exception e){
    		e.printStackTrace();
    		out.print("系统异常，请重试或联系管理员！");
    	}
    }
    
    /***
     * 判断是否需要双人审批
     * @param appLoanTypeId
     * @return
     */
    private Boolean isDoubleApproval(String appLoanTypeId){
    	Boolean returnValue  = false;
    	if(appLoanTypeId.equals("1")){//经营贷
    		returnValue = sysParamService.getJYDDoubleApprovalTag().equals("0")?false:true;
    	}else if (appLoanTypeId.equals("2")){//消费贷款
    		returnValue = sysParamService.getXFDDoubleApprovalTag().equals("0")?false:true;
    	}else{
    		returnValue= true;
    	}
    	return returnValue;
    }
    
    //审批中心审批
    public void centerAssessLoan(){
    	
    	PrintWriter out = null;
    	try{
	    	out = this.getResponse().getWriter();;
	    	if(lnLoanInfo.getLoanId() == null && lnLoanInfo.getLoanId() < 0){
	    		out.print("当前贷款信息查询错误，刷新后重试！");
	    	}
	    	
	    	LnLoan lnLoan = lnLoanService.getLnLoanById(lnLoanInfo.getLoanId()); 
	    	lnLoan.setApproveCenterPassDate(new Date());
	    	lnLoan.setLoanStatusId(5);
	    	lnLoan.setEventId(5);
	    	lnLoan.setLendUserId(sysUserService.getReadomTeamManagerUserId(lnLoan.getSurveyUserId(),"lendUserId"));
	    	LnOpHistory lnOpHistory = new LnOpHistory();
	        lnOpHistory.setUserId(this.getLoginInfo().getUserId());
	        lnOpHistory.setOpHistoryDate(new Date());
	        lnOpHistory.setBeforeStatusId(4);
	        lnOpHistory.setAfterStatusId(5);
	        lnOpHistory.setRemark(lnLoanInfo.getResultNote());
	        lnOpHistory.setLoanId(lnLoanInfo.getLoanId());
	        lnOpHistory.setContent("审批中心通过");
	        lnLoanService.submitApproveLoan(lnLoan,lnLoanInfo,lnOpHistory);
	        out.print("success");
    	}catch(Exception e){
    		e.printStackTrace();
    		out.print("系统异常，请重试或联系管理员！");
    	}
    }
    
    //贷审会审批
    public void backerAssessLoan(){
    	PrintWriter out = null;
    	try{
	    	out = this.getResponse().getWriter();;
	    	if(lnLoanInfo.getLoanId() == null && lnLoanInfo.getLoanId() < 0){
	    		out.print("当前贷款信息查询错误，刷新后重试！");
	    	}
	    	LnLoan lnLoan = new LnLoan();
	    	lnLoan.setLoanId(lnLoanInfo.getLoanId());
	    	lnLoan.setApproveBackerPassDate(new Date());
	    	lnLoan.setLoanStatusId(5);
	    	lnLoan.setEventId(5);
	    	lnLoan.setLendUserId(sysUserService.getReadomTeamManagerUserId(this.getLoginInfo().getUserId(),"lendUserId"));
	    	
	    	LnOpHistory lnOpHistory = new LnOpHistory();
	        lnOpHistory.setUserId(this.getLoginInfo().getUserId());
	        lnOpHistory.setOpHistoryDate(new Date());
	        lnOpHistory.setBeforeStatusId(4);
	        lnOpHistory.setAfterStatusId(5);
	        lnOpHistory.setRemark(lnLoanInfo.getResultNote());
	        lnOpHistory.setLoanId(lnLoanInfo.getLoanId());
	        lnOpHistory.setContent("贷审会通过");
	        
	        lnLoanService.submitApproveLoan(lnLoan,lnLoanInfo,lnOpHistory);
	        out.print("success");
    	}catch(Exception e){
    		e.printStackTrace();
    		out.print("系统异常，请重试或联系管理员！");
    	}
    }

    public void addUnAssessRemark(){
        logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 添加放贷贷款备注...");

        LnLoan lnLoan = lnLoanService.getLnLoanById(lnLoanInfo.getLoanId());
        String strLoanId = request.getParameter("loanId");
        Integer loanId = Integer.parseInt(strLoanId.trim());
        String lendRemark = request.getParameter("lendRemark");
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("loanId",loanId);
        param.put("lendRemark",lendRemark);

        LnOpHistory lnOpHistory = new LnOpHistory();
        lnOpHistory.setUserId(this.getLoginInfo().getUserId());
        lnOpHistory.setOpHistoryDate(new Date());
        lnOpHistory.setBeforeStatusId(4);
        lnOpHistory.setAfterStatusId(4);
        lnOpHistory.setRemark(lendRemark);
        lnOpHistory.setLoanId(loanId);
        lnOpHistory.setContent("添加备注");
        logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 添加待审批贷款备注，修改贷款表并插入历史记录...");
        lnLoanService.checkoutLoan(param,lnOpHistory);
        lnLoanInfoService.updateLnLoanInfo(lnLoanInfo);
        logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 添加放贷贷款备注完成！");
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

    public void setLnLoanTypeService(LnLoanTypeService lnLoanTypeService) {
        this.lnLoanTypeService = lnLoanTypeService;
    }

    public LnLoanTypeService getLnLoanTypeService() {
        return lnLoanTypeService;
    }

    public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public Date getApprovalStartDate() {
        return approvalStartDate;
    }

    public void setApprovalStartDate(Date approvalStartDate) {
        this.approvalStartDate = approvalStartDate;
    }

    public Date getApprovalEndDate() {
        return approvalEndDate;
    }

    public void setApprovalEndDate(Date approvalEndDate) {
        this.approvalEndDate = approvalEndDate;
    }

    public Date getStartCreateDate() {
        return startCreateDate;
    }

    public void setStartCreateDate(Date startCreateDate) {
        this.startCreateDate = startCreateDate;
    }

    public Date getEndCreateDate() {
        return endCreateDate;
    }

    public void setEndCreateDate(Date endCreateDate) {
        this.endCreateDate = endCreateDate;
    }

    public Integer getLoanType() {
        return loanType;
    }

    public void setLoanType(Integer loanType) {
        this.loanType = loanType;
    }

    public int getDevelop() {
        return develop;
    }

    public void setDevelop(int develop) {
        this.develop = develop;
    }

	public void setLnLoanInfoDictionaryService(
			LnLoanInfoDictionaryService lnLoanInfoDictionaryService) {
		this.lnLoanInfoDictionaryService = lnLoanInfoDictionaryService;
	}

	public LnLoanInfo getLnLoanInfo() {
		return lnLoanInfo;
	}

	public void setLnLoanInfo(LnLoanInfo lnLoanInfo) {
		this.lnLoanInfo = lnLoanInfo;
	}

	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	public SysParamService getSysParamService() {
		return sysParamService;
	}

	public void setSysParamService(SysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}

	public LnLoanInfoService getLnLoanInfoService() {
		return lnLoanInfoService;
	}

	public void setLnLoanInfoService(LnLoanInfoService lnLoanInfoService) {
		this.lnLoanInfoService = lnLoanInfoService;
	}

}
