package com.banger.mobile.webapp.action.loan;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.loan.LnLoan;
import com.banger.mobile.domain.model.loan.LnLoanType;
import com.banger.mobile.domain.model.loan.LnOpHistory;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.loan.LnCancelReasonService;
import com.banger.mobile.facade.loan.LnLoanService;
import com.banger.mobile.facade.loan.LnLoanTypeService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * Created with IntelliJ IDEA.
 * User: ouyl
 * Date: 13-2-21
 * Time: 下午4:00
 * To change this template use File | Settings | File Templates.
 *
 * 待撤销贷款Action
 */
public class CancelLoanAction extends BaseAction {
    private static Logger logger = Logger.getLogger(CancelLoanAction.class);
    private LnLoanService lnLoanService;
    private LnLoanTypeService  lnLoanTypeService;                                     //贷款类型service
    private LnCancelReasonService lnCancelReasonService;                              //撤销原因service
    private String customer;
    private Integer loanType;
    private Integer loanStatus;
    private Date cancelStartDate;
    private Date cancelEndDate;
    private Date startCreateDate;
    private Date endCreateDate;
    private DeptFacadeService deptFacadeService;                                     //部门组织结构service


    public String cancelLoanList(){
        try {
            //贷款类型
            List<LnLoanType> typeList = lnLoanTypeService.getLoanTypeList();
            Map<String, Object> conds = new HashMap<String, Object>();
            Integer deptId = this.getLoginInfo().getDeptId();
            String cancelUserIds = deptFacadeService.getStringUserIdContainsByDeptIdsWithOutDel(deptId.toString());
            conds.put("cancelUserIds",cancelUserIds);

            Integer[] loanStatusIds={8,9};
            conds.put("loanStatusIds", loanStatusIds);   // 8表示待撤销 9表示已撤销
            PageUtil<LnLoan> dataList = lnLoanService.getLnLoanListPage(conds, this.getPage());
            request.setAttribute("dataList", dataList.getItems());
            request.setAttribute("recordCount", dataList.getPage().getTotalRowsAmount());
            request.setAttribute("typeList", typeList);
            return SUCCESS;
        }catch (Exception e){
            logger.error("cancelLoanList",e);
            return ERROR;
        }
    }

    public String cancelLoanListQuery(){
        try {
            //列表数据
            Map<String, Object> conds = new HashMap<String, Object>();
            Integer deptId = this.getLoginInfo().getDeptId();
            String cancelUserIds = deptFacadeService.getStringUserIdContainsByDeptIdsWithOutDel(deptId.toString());
            conds.put("cancelUserIds",cancelUserIds);
            Integer[] loanStatusIds={8,9};
            if(loanStatus==null){
                conds.put("loanStatusIds",loanStatusIds) ;
            }else{
                conds.put("loanStatusId",loanStatus);
            }
            conds.put("customer", customer);
            conds.put("loanTypeId", loanType);
            conds.put("applyStartDate",startCreateDate);
            conds.put("applyEndDate",endCreateDate);
            conds.put("cancelStartDate",cancelStartDate);
            conds.put("cancelEndDate",cancelEndDate);
            PageUtil<LnLoan> dataList = lnLoanService.getLnLoanListPage(conds, this.getPage());
            request.setAttribute("dataList", dataList.getItems());
            request.setAttribute("recordCount", dataList.getPage().getTotalRowsAmount());
            return SUCCESS;

        }catch (Exception e){
            logger.error("cancelLoanListQuery",e);
            return ERROR;
        }
    }




    public String cancelLoanEdit(){
    	//TODO:修改
    	return SUCCESS;
    	/*
        try{
            String strLoanId = request.getParameter("loanId");
            Integer loanId= Integer.parseInt(strLoanId.trim());
            LnLoan lnLoan = lnLoanService.getLnLoanById(loanId);
            Integer cancelReasonId =lnLoan.getCancelReasonId();
            String cancelReasonOther =lnLoan.getCancelReasonOther();
            if(cancelReasonId != null){
                String cancelReason = lnCancelReasonService.getCancelReasonById(cancelReasonId).getCancelReasonName();
                request.setAttribute("cancelReason",cancelReason);
            }
            request.setAttribute("loanId", loanId);
            request.setAttribute("cancelReasonId",cancelReasonId);
            request.setAttribute("cancelReasonOther",cancelReasonOther);

            return  SUCCESS;
        }catch (Exception e){
            logger.error("canceLoanEdit",e);
            return ERROR;
        }
    */}

    public void cancelSubmit(){
        try{
            String strLoanId = request.getParameter("loanId");
            String remark = request.getParameter("remark");
            Map<String,Object> paramMap = new HashMap<String, Object>();
            List<LnOpHistory> lnOpHistoryList = new ArrayList<LnOpHistory>();
            String loanCheck = request.getParameter("loanCheck");
            if(loanCheck ==null){
                /*paramMap.put("loanStatusId",9);
                paramMap.put("loanId",Integer.parseInt(strLoanId.trim()));
                paramMap.put("confirmCancelRemark",remark);
                paramMap.put("confirmCancelUserId",this.getLoginInfo().getUserId());
                */
                LnLoan lnLoan = new LnLoan();
                lnLoan.setLoanId(Integer.parseInt(strLoanId.trim()));
                lnLoan.setLoanStatusId(9);
                
                
                LnOpHistory lnOpHistory = new LnOpHistory();
                lnOpHistory.setUserId(this.getLoginInfo().getUserId());
                lnOpHistory.setOpHistoryDate(new Date());
                lnOpHistory.setBeforeStatusId(8);  //待撤销
                lnOpHistory.setAfterStatusId(9);   //撤销成功
                lnOpHistory.setContent("贷款撤销");
                lnOpHistory.setRemark(remark);
                lnOpHistory.setLoanId(Integer.parseInt(strLoanId.trim()));

                lnLoanService.submitApproval(lnLoan,lnOpHistory);
            } else {
                String[] loanIds =loanCheck.split(",");
                for(String loanId:loanIds){
                    paramMap.put("loanStatusId",9);
                    paramMap.put("loanId",Integer.parseInt(loanId.trim()));
                    paramMap.put("confirmCancelUserId",this.getLoginInfo().getUserId());

                    LnOpHistory lnOpHistory = new LnOpHistory();
                    lnOpHistory.setUserId(this.getLoginInfo().getUserId());
                    lnOpHistory.setOpHistoryDate(new Date());
                    lnOpHistory.setBeforeStatusId(8);  //待撤销
                    lnOpHistory.setAfterStatusId(9);   //撤销成功
                    lnOpHistory.setContent("贷款撤销");
                    lnOpHistory.setLoanId(Integer.parseInt(loanId.trim()));

                    lnLoanService.cancelLoan(paramMap,lnOpHistory);
                }
            }
        }catch (Exception e){
            logger.error("cancelSubmit", e);
        }
    }

    public String cancelLoanView(){
    	//TODO:修改
    	return SUCCESS;
    	/*
        try{
            String strLoanId = request.getParameter("loanId");
            Integer loanId= Integer.parseInt(strLoanId.trim());
            LnLoan lnloan = lnLoanService.getLnLoanById(loanId);
            Integer cancelReasonId =lnloan.getCancelReasonId();
            String cancelReasonOther =lnloan.getCancelReasonOther();
            String confirmCancelRemark =lnloan.getConfirmCancelRemark();
            if(cancelReasonId != null){
                String cancelReason = lnCancelReasonService.getCancelReasonById(cancelReasonId).getCancelReasonName();
                request.setAttribute("cancelReason",cancelReason);
            }
            request.setAttribute("loanId", loanId);
            request.setAttribute("cancelReasonId",cancelReasonId);
            request.setAttribute("cancelReasonOther",cancelReasonOther);
            request.setAttribute("confirmCancelRemark",confirmCancelRemark);

            return  SUCCESS;
        }catch (Exception e){
            logger.error("confirmLoanView",e);
            return ERROR;
        }
    */}

    public LnLoanService getLnLoanService() {
        return lnLoanService;
    }

    public void setLnLoanService(LnLoanService lnLoanService) {
        this.lnLoanService = lnLoanService;
    }

    public LnLoanTypeService getLnLoanTypeService() {
        return lnLoanTypeService;
    }

    public void setLnLoanTypeService(LnLoanTypeService lnLoanTypeService) {
        this.lnLoanTypeService = lnLoanTypeService;
    }

    public LnCancelReasonService getLnCancelReasonService() {
        return lnCancelReasonService;
    }

    public void setLnCancelReasonService(LnCancelReasonService lnCancelReasonService) {
        this.lnCancelReasonService = lnCancelReasonService;
    }

    public DeptFacadeService getDeptFacadeService() {
        return deptFacadeService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
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

    public Integer getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(Integer loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Date getCancelStartDate() {
        return cancelStartDate;
    }

    public void setCancelStartDate(Date cancelStartDate) {
        this.cancelStartDate = cancelStartDate;
    }

    public Date getCancelEndDate() {
        return cancelEndDate;
    }

    public void setCancelEndDate(Date cancelEndDate) {
        this.cancelEndDate = cancelEndDate;
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

}
