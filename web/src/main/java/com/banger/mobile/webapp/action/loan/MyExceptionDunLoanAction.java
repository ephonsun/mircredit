package com.banger.mobile.webapp.action.loan;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.loan.*;
import com.banger.mobile.facade.loan.*;
import com.banger.mobile.webapp.action.BaseAction;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-3-7
 * Time: 下午6:02
 * To change this template use File | Settings | File Templates.
 */
public class MyExceptionDunLoanAction extends BaseAction {

    private static Logger logger = Logger.getLogger(MyExceptionDunLoanAction.class);

    private LnLoanService lnLoanService;
    private LnLoanTypeService lnLoanTypeService;
    private LnExceptionDunAssignService lnExceptionDunAssignService;
    private LnRepaymentPlanService lnRepaymentPlanService;
    private LnExceptionRepaymentPlanService lnExceptionRepaymentPlanService;
    private String customer;
    private Integer loanType;
    private Date repaymentStartDate;
    private Date repaymentEndDate;
    private Integer repaymentStatus;
    private Date planDate;

    public LnLoanService getLnLoanService() {
        return lnLoanService;
    }

    public void setLnLoanService(LnLoanService lnLoanService) {
        this.lnLoanService = lnLoanService;
    }

    public LnLoanTypeService getLnLoanTypeService() {
        return lnLoanTypeService;
    }

    public LnExceptionDunAssignService getLnExceptionDunAssignService() {
        return lnExceptionDunAssignService;
    }

    public void setLnExceptionDunAssignService(LnExceptionDunAssignService lnExceptionDunAssignService) {
        this.lnExceptionDunAssignService = lnExceptionDunAssignService;
    }

    public LnRepaymentPlanService getLnRepaymentPlanService() {
        return lnRepaymentPlanService;
    }

    public void setLnRepaymentPlanService(LnRepaymentPlanService lnRepaymentPlanService) {
        this.lnRepaymentPlanService = lnRepaymentPlanService;
    }

    public LnExceptionRepaymentPlanService getLnExceptionRepaymentPlanService() {
        return lnExceptionRepaymentPlanService;
    }

    public void setLnExceptionRepaymentPlanService(LnExceptionRepaymentPlanService lnExceptionRepaymentPlanService) {
        this.lnExceptionRepaymentPlanService = lnExceptionRepaymentPlanService;
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

    public Date getRepaymentStartDate() {
        return repaymentStartDate;
    }

    public void setRepaymentStartDate(Date repaymentStartDate) {
        this.repaymentStartDate = repaymentStartDate;
    }

    public Date getRepaymentEndDate() {
        return repaymentEndDate;
    }

    public void setRepaymentEndDate(Date repaymentEndDate) {
        this.repaymentEndDate = repaymentEndDate;
    }

    public Integer getRepaymentStatus() {
        return repaymentStatus;
    }

    public void setRepaymentStatus(Integer repaymentStatus) {
        this.repaymentStatus = repaymentStatus;
    }

    public void setLnLoanTypeService(LnLoanTypeService lnLoanTypeService) {
        this.lnLoanTypeService = lnLoanTypeService;

    }

    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    public String myExceptionDunLoanList(){
    	//TODO:修改
    	return SUCCESS;
    	/*
        try {
            logger.info("web端我的异常催收贷款myExceptionDunLoanList开始，当前登录用户“"+this.getLoginInfo().getAccount());
            //贷款类型
            List<LnLoanType> typeList = lnLoanTypeService.getLoanTypeList();
            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("isNogood",1) ;
            conds.put("loanStatusId",6);
            conds.put("dunUserId",this.getLoginInfo().getUserId());
            PageUtil<LnExceptionDunAssign> dataList = lnExceptionDunAssignService.selectMyExceptionDunLoanList(conds, this.getPage());
            if(dataList != null && dataList.getItems() != null){
                for (LnExceptionDunAssign lnExceptionDunAssign : dataList.getItems()) {
                    Date repaymentDate = lnExceptionDunAssign.getLnLoan().getRepaymentDate();
                    Date nowDate = new Date();
                    if(repaymentDate !=null){
                        if((nowDate.getTime() - repaymentDate.getTime()) >= 0){
                            //超过还款时间的,前端在高亮显示
                            lnExceptionDunAssign.getLnLoan().setIsWillPast(1);
                        }
                    }
                }
            }
            request.setAttribute("dataList",dataList.getItems());
            request.setAttribute("recordCount", dataList.getPage().getTotalRowsAmount());
            request.setAttribute("typeList",typeList);
            logger.info("web端我的异常催收贷款myExceptionDunLoanList完成，当前登录用户“"+this.getLoginInfo().getAccount());
            return SUCCESS;
        }catch (Exception e){
            logger.error("myExceptionDunLoanList",e);
            return ERROR;
        }
    */}

    public String myExceptionDunLoanListQuery(){
    	//TODO:修改
    	return SUCCESS;
    	/*
        try {
            logger.info("web端我的异常催收贷款myExceptionDunLoanListQuery开始，当前登录用户“"+this.getLoginInfo().getAccount());
            //列表数据
            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("isNogood",1) ;
            conds.put("customer",customer);
            conds.put("loanTypeId",loanType);
            conds.put("repaymentStartDate",repaymentStartDate);
            repaymentEndDate = lnLoanService.addHMSForDate(repaymentEndDate,23,59,59);
            conds.put("repaymentEndDate",repaymentEndDate);
            conds.put("repaymentStatus",repaymentStatus);
            conds.put("loanStatusId",6);
            conds.put("dunUserId",this.getLoginInfo().getUserId());
            logger.info("web端我的异常催收贷款myExceptionDunLoanListQuery...，当前登录用户“"+this.getLoginInfo().getAccount()+",搜索条件,isNogood:1," +
                    "customer:"+customer+",loanTypeId:"+loanType+",repaymentStartDate:"+repaymentStartDate+",repaymentEndDate:"+
                    repaymentEndDate+",dunUserId:"+this.getLoginInfo().getUserId());
            PageUtil<LnExceptionDunAssign> dataList = lnExceptionDunAssignService.selectMyExceptionDunLoanList(conds, this.getPage());
            if(dataList != null && dataList.getItems() != null){
                for (LnExceptionDunAssign lnExceptionDunAssign : dataList.getItems()) {
                    Date repaymentDate = lnExceptionDunAssign.getLnLoan().getRepaymentDate();
                    Date nowDate = new Date();
                    if(repaymentDate !=null){
                        if((nowDate.getTime() - repaymentDate.getTime()) >= 0){
                            //超过还款时间的,前端在高亮显示
                            lnExceptionDunAssign.getLnLoan().setIsWillPast(1);
                        }
                    }
                }
            }
            request.setAttribute("dataList", dataList.getItems());
            request.setAttribute("recordCount", dataList.getPage().getTotalRowsAmount());
            logger.info("web端我的异常催收贷款myExceptionDunLoanListQuery完成，当前登录用户“"+this.getLoginInfo().getAccount());
            return SUCCESS;
        }catch (Exception e){
            logger.error("myExceptionDunLoanListQuery",e);
            return ERROR;
        }
    */}

    /**
     * 编辑异常催收贷款页面
     *
     * @return
     */
    public String myExceptionDunLoanView(){
        try{
            String strLoanId = request.getParameter("loanId");
            logger.info("web端我的异常催收贷款myExceptionDunLoanView开始，当前登录用户“"+this.getLoginInfo().getAccount()+",loanId:"+strLoanId);
            Integer loanId= Integer.parseInt(strLoanId.trim());
            List<LnRepaymentPlan> queryList= lnRepaymentPlanService.queryLnRepaymentPlan(loanId);
            List<LnExceptionRepaymentPlan> eQueryList =lnExceptionRepaymentPlanService.queryLnExceptionRepaymentPlan(loanId);
            Integer repaymentStatus = lnLoanService.getLnLoanById(loanId).getRepaymentStatus();
            String customerName=lnLoanService.getLnLoanById(loanId).getCrmCustomer().getCustomerName();
            LnLoan loan = lnLoanService.getLnLoanById(loanId);
            request.setAttribute("loan", loan);
            request.setAttribute("queryList", queryList);
            request.setAttribute("eQueryList", eQueryList);
            request.setAttribute("loanId", loanId);
            request.setAttribute("repaymentStatus",repaymentStatus);
            request.setAttribute("customerName",customerName);
            logger.info("web端我的异常催收贷款myExceptionDunLoanView完成，当前登录用户“"+this.getLoginInfo().getAccount()+",loanId:"+strLoanId);
            return  SUCCESS;
        }catch (Exception e){
            logger.error("myExceptionDunLoanEdit",e);
            return ERROR;
        }
    }

}


