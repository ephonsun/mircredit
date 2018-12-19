package com.banger.mobile.webapp.action.loan;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.loan.LnLoan;
import com.banger.mobile.domain.model.loan.LnLoanStatus;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.loan.LnLoanService;
import com.banger.mobile.facade.loan.LnLoanStatusService;
import com.banger.mobile.facade.specialDataAuth.SpecialDataAuthService;
import com.banger.mobile.webapp.action.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ouyl
 * Date: 13-3-19
 * Time: 上午9:22
 * To change this template use File | Settings | File Templates.
 *
 * 审计未通过贷款Action
 */
public class VerifyNotPassAction extends BaseAction {
    private static Logger logger = Logger.getLogger(LnVerifyAllLoanAction.class);
    private DeptFacadeService deptFacadeService;
    private LnLoanService lnLoanService;
    private LnLoanStatusService lnLoanStatusService;
    private SpecialDataAuthService specialDataAuthService;

    private Date startDate;                    //审计时间
    private Date endDate;                      //审计时间
    private String customer;                  // 客户信息
    private String userIds;                   //归属人员


    /**
     * 首页列表
     *
     * @return
     */
    public String verifyNotPassLoanList(){
        try {
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            //是否是业务主管
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
            parameterMap.put("loanIsVerify",1);     // 1-已审计
            parameterMap.put("loanIsVerifyPass",3); // 3-审计不合格
            parameterMap.put("verifySort","true");   //按审计时间排序

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
            logger.error("verifyNotPassLoanList", e);
            return ERROR;
        }
    }


    /**
     * 查询列表
     *
     * @return
     */
    public String verifyNotPassLoanListQuery() {
        try {
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            //是否是业务主管
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

            parameterMap.put("verifyStartDate",startDate);         //审计时间
            endDate = lnLoanService.addSecondsForDate(endDate,59);
            parameterMap.put("verifyEndDate",endDate);           //审计时间
            parameterMap.put("loanIsVerify",1);     // 1-已审计
            parameterMap.put("loanIsVerifyPass",3); // 3-审计不合格
            parameterMap.put("customer", customer);
            parameterMap.put("verifySort","true");   //按审计时间排序

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
//            parameterMap.put("cusBelongUserIds",userIds);

            PageUtil<LnLoan> dataList = lnLoanService.getVerifyLoanPage(parameterMap, this.getPage());

            request.setAttribute("dataList", dataList.getItems());
            request.setAttribute("recordCount", dataList.getPage().getTotalRowsAmount());

            return SUCCESS;
        } catch (Exception e) {
            logger.error("verifyNotPassLoanListQuery", e);
            return ERROR;
        }
    }




    public DeptFacadeService getDeptFacadeService() {
        return deptFacadeService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    public LnLoanService getLnLoanService() {
        return lnLoanService;
    }

    public void setLnLoanService(LnLoanService lnLoanService) {
        this.lnLoanService = lnLoanService;
    }

    public LnLoanStatusService getLnLoanStatusService() {
        return lnLoanStatusService;
    }

    public void setLnLoanStatusService(LnLoanStatusService lnLoanStatusService) {
        this.lnLoanStatusService = lnLoanStatusService;
    }

    public SpecialDataAuthService getSpecialDataAuthService() {
        return specialDataAuthService;
    }

    public void setSpecialDataAuthService(SpecialDataAuthService specialDataAuthService) {
        this.specialDataAuthService = specialDataAuthService;
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

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }
}
