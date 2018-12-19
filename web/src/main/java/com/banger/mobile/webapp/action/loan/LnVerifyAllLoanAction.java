package com.banger.mobile.webapp.action.loan;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.loan.LnDunLog;
import com.banger.mobile.domain.model.loan.LnLoan;
import com.banger.mobile.domain.model.loan.LnLoanStatus;
import com.banger.mobile.domain.model.loan.LnVerifyHistory;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.loan.*;
import com.banger.mobile.facade.specialDataAuth.SpecialDataAuthService;
import com.banger.mobile.webapp.action.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ouyl
 * Date: 13-3-13
 * Time: 上午9:48
 * To change this template use File | Settings | File Templates.
 */

//审计模块下所有贷款
public class LnVerifyAllLoanAction extends BaseAction {
    private static Logger logger = Logger.getLogger(LnVerifyAllLoanAction.class);

    //services
    private LnLoanService lnLoanService; //贷款service
    private DeptFacadeService deptFacadeService; //部门组织结构service
    private LnLoanTypeService lnLoanTypeService; //贷款类型service
    private LnLoanStatusService lnLoanStatusService;   //贷款类型service
    private LnCancelReasonService lnCancelReasonService; //撤销贷款原因service
    private LnOpHistoryService lnOpHistoryService; //贷款操作历史记录service
    private LnRepaymentPlanService lnRepaymentPlanService;  //还款计划service
    private LnExceptionRepaymentPlanService lnExceptionRepaymentPlanService;  //异常还款计划service
    private LnDunLogService lnDunLogService;      //催收记录service
    private SpecialDataAuthService specialDataAuthService;

    //查询条件
    private String customer;                  //客户
    private Integer loanStatus;               //贷款状态
    private Integer verifyStatus;              //审计状态
    private Integer checkoutStatus;           //落实状态
    private String userIds;                   //归属人员
    private Date startDate;                   //创建时间
    private Date endDate;                     //创建时间

    /**
     * 首页列表
     *
     * @return
     */
    public String verifyAllLoanList() {
        try {
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment();
            if (isInChargeOf) {
                //当前用户的下属用户
                String belongUserIds = deptFacadeService.getUserIdsBelongToManager(getLoginInfo().getRoles(),"audit");
                parameterMap.put("belongUserIds", belongUserIds); //当前用户所管理的提交申请用户
                parameterMap.put("assignverifyUserIds",belongUserIds);
            } else {
                //客户经理
                parameterMap.put("belongUserId",this.getLoginInfo().getUserId());
                parameterMap.put("assignverifyUserId",this.getLoginInfo().getUserId());
            }
            parameterMap.put("applySort","true");    //按提交时间排序
            parameterMap.put("notQueryStatusId",1);   //不查询贷款状态为待提交
            //分页查询
            PageUtil<LnLoan> dataList = lnLoanService.getVerifyLoanPage(parameterMap, this.getPage());

            //贷款状态
            List<LnLoanStatus> statusList = lnLoanStatusService.getLoanStatusList();

            request.setAttribute("isInChargeOf", isInChargeOf);
            request.setAttribute("statusList", statusList);
            request.setAttribute("dataList", dataList.getItems());
            request.setAttribute("recordCount", dataList.getPage().getTotalRowsAmount());

//            String roleIds= StringUtil.getIdsString(getLoginInfo().getRoles());
//            boolean flag=specialDataAuthService.getSysDataAuthCondition(roleIds,"audit");
//            if (flag){
//                request.setAttribute("flag",flag);
//                request.setAttribute("loginUserId",this.getLoginInfo().getUserId());
//                request.setAttribute("loginUserAccount",this.getLoginInfo().getAccount());
//            }
            request.setAttribute("userName",this.getLoginInfo().getUserName());
            request.setAttribute("dataCode", this.getLoginInfo().getDataCompetence()); //用户数据权限

            return SUCCESS;
        } catch (Exception e) {
            logger.error("allLoanList", e);
            return ERROR;
        }
    }

    /**
     * 查询列表
     *
     * @return
     */
    public String verifyAllLoanListQuery() {
        try {
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment();
            if (isInChargeOf) {
                //当前用户的下属用户
                String belongUserIds = deptFacadeService.getUserIdsBelongToManager(getLoginInfo().getRoles(),"audit");
                parameterMap.put("belongUserIds", belongUserIds); //当前用户所管理的提交申请用户
                parameterMap.put("assignverifyUserIds",belongUserIds);
            } else {
                //客户经理
                parameterMap.put("belongUserId",this.getLoginInfo().getUserId());
                parameterMap.put("assignverifyUserId",this.getLoginInfo().getUserId());
            }


            parameterMap.put("notQueryStatusId",1);   //不查询贷款状态为待提交
            parameterMap.put("applySort","true");    //按提交时间排序
            parameterMap.put("verifyAllLoan","true");
            parameterMap.put("loanStatusId", loanStatus); //贷款状态
            parameterMap.put("customer", customer);
            parameterMap.put("createStartDate",startDate);       //创建时间
            parameterMap.put("createEndDate",endDate);           //创建时间
            Integer[] loanStatusIds={6,7};
            if (checkoutStatus!=null){
                parameterMap.put("loanIsCheckout", checkoutStatus);
                parameterMap.put("loanStatusIds",loanStatusIds);
            }
            if(verifyStatus !=null){
                if(verifyStatus==0){
                    parameterMap.put("loanIsVerify", verifyStatus);
                }else if(verifyStatus==2){
                    parameterMap.put("loanIsVerify", 1);
                    parameterMap.put("loanIsVerifyPass", 2);
                }else if(verifyStatus==3){
                    parameterMap.put("loanIsVerify", 1);
                    parameterMap.put("loanIsVerifyPass", 3);
                }
            }

            String belongToType = request.getParameter("BelongToType");
            if (StringUtils.isNotEmpty(belongToType)){
                if (belongToType.equals("brAll")){
                    //所有，包括下属用户和下属机构
//                    parameterMap.put("cusBelongUserIds",belongUserIds);
                }else if (belongToType.equals("brMine")){
                    //我的
                    parameterMap.put("cusBelongUserIds",this.getLoginInfo().getUserId().toString());
                }else if (belongToType.equals("brUser")){
                    //下属用户
                    parameterMap.put("cusBelongUserIds",userIds);
                }else if (belongToType.equals("brDept")){
                    //机构的
                    String deptIds = request.getParameter("deptIds");
                    parameterMap.put("cusBelongDeptIds",deptIds);
                }else if (belongToType.equals("brUnAllocate")){
                    //未分配
                    parameterMap.put("unAllocate","1");
                }
            }

            PageUtil<LnLoan> dataList = lnLoanService.getVerifyLoanPage(parameterMap, this.getPage());

            request.setAttribute("dataList", dataList.getItems());
            request.setAttribute("recordCount", dataList.getPage().getTotalRowsAmount());

            return SUCCESS;
        } catch (Exception e) {
            logger.error("vAllLoanListQuery", e);
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
        List<SysUser> sysUsers = deptFacadeService.getBusinessManagerInCharegeOfUsers();
        List<Integer> applyUserIds = new ArrayList<Integer>();
        for (SysUser sysUser : sysUsers) {
            applyUserIds.add(sysUser.getUserId());
        }
        return applyUserIds;
    }



    public String verifyAllLoanView(){
        try{
            String strLoanId = request.getParameter("loanId");
            Integer loanId= Integer.parseInt(strLoanId.trim());
//            List<LnExceptionRepaymentPlan> eQueryList =lnExceptionRepaymentPlanService.queryLnExceptionRepaymentPlan(loanId);
            List<LnDunLog> lnDunLogList =lnDunLogService.getDunLogByLoanId(loanId);
            LnLoan loan = lnLoanService.getLnLoanById(loanId);
//            request.setAttribute("eQueryList", eQueryList);
            request.setAttribute("lnDunLogList",lnDunLogList);
            request.setAttribute("loanId", loanId);
            request.setAttribute("loan",loan);
            return SUCCESS;
        }catch (Exception e){
            logger.error("verifyAllLoanView",e);
            return ERROR;
        }
    }

    public void addVerifyRemark(){
        String strLoanId = request.getParameter("loanId");
        Integer loanId = Integer.parseInt(strLoanId.trim());
        String verifyRemark = request.getParameter("verifyRemark");
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("loanId",loanId);
        param.put("verifyRemark",verifyRemark);

        LnVerifyHistory lnVerifyHistory= new LnVerifyHistory();
        lnVerifyHistory.setUserId(this.getLoginInfo().getUserId());
        lnVerifyHistory.setLoanId(loanId);
        lnVerifyHistory.setContent("添加备注");
        lnVerifyHistory.setRemark(verifyRemark);
        lnVerifyHistory.setVerifyHistoryIdDate(new Date());
        lnLoanService.addVerifyRemark(param,lnVerifyHistory);

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

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Integer getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(Integer verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public LnCancelReasonService getLnCancelReasonService() {
        return lnCancelReasonService;
    }

    public void setLnCancelReasonService(LnCancelReasonService lnCancelReasonService) {
        this.lnCancelReasonService = lnCancelReasonService;
    }

    public LnOpHistoryService getLnOpHistoryService() {
        return lnOpHistoryService;
    }

    public void setLnOpHistoryService(LnOpHistoryService lnOpHistoryService) {
        this.lnOpHistoryService = lnOpHistoryService;
    }

    public void setLnLoanStatusService(LnLoanStatusService lnLoanStatusService) {
        this.lnLoanStatusService = lnLoanStatusService;
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

    public SpecialDataAuthService getSpecialDataAuthService() {
        return specialDataAuthService;
    }

    public void setSpecialDataAuthService(SpecialDataAuthService specialDataAuthService) {
        this.specialDataAuthService = specialDataAuthService;
    }

    public void setLoanStatus(Integer loanStatus) {
        this.loanStatus = loanStatus;
    }

    public void setCheckoutStatus(Integer checkoutStatus) {
        this.checkoutStatus = checkoutStatus;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public LnDunLogService getLnDunLogService() {
        return lnDunLogService;
    }

    public void setLnDunLogService(LnDunLogService lnDunLogService) {
        this.lnDunLogService = lnDunLogService;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
