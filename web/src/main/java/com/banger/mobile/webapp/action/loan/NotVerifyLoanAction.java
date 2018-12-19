package com.banger.mobile.webapp.action.loan;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.dept.CusBelongToBean;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.loan.*;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.loan.*;
import com.banger.mobile.facade.specialDataAuth.SpecialDataAuthService;
import com.banger.mobile.webapp.action.BaseAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ouyl
 * Date: 13-3-15
 * Time: 下午3:52
 * To change this template use File | Settings | File Templates.
 *
 * 未审计贷款Action
 */
public class NotVerifyLoanAction extends BaseAction {
    private static Logger logger = Logger.getLogger(LnVerifyAllLoanAction.class);

    //services
    private LnLoanService lnLoanService; //贷款service
    private DeptFacadeService deptFacadeService; //部门组织结构service
    private LnLoanTypeService lnLoanTypeService; //贷款类型service
    private LnLoanStatusService lnLoanStatusService;
    private LnCancelReasonService lnCancelReasonService; //撤销贷款原因service
    private LnOpHistoryService lnOpHistoryService; //贷款操作历史记录service
    private LnRepaymentPlanService lnRepaymentPlanService;  //还款计划service
    private LnExceptionRepaymentPlanService lnExceptionRepaymentPlanService;  //异常还款计划service
    private SpecialDataAuthService specialDataAuthService;

    //查询条件
    private String customer;                  //客户
    private Integer loanStatus;               //贷款状态
    private Date startDate;                   //申请时间
    private Date endDate;                     //申请时间
    private Integer checkoutStatus;           //落实状态
    private String userIds;                   //归属人员

    /**
     * 首页列表
     *
     * @return
     */
    public String notVerifyLoanList() {
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

            parameterMap.put("loanIsVerify",0);     // 0-未审计
            parameterMap.put("notQueryStatusId",1);          //不查询贷款状态为待提交
            parameterMap.put("randSort","true");

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
            logger.error("notVerifyLoanList", e);
            return ERROR;
        }
    }

    /**
     * 查询列表
     *
     * @return
     */
    public String notVerifyLoanListQuery() {
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

            parameterMap.put("verifyAllLoan","true");         //标记为审计
            parameterMap.put("notQueryStatusId",1);          //不查询贷款状态为待提交
            parameterMap.put("applyStartDate",startDate);         //申请时间
            endDate = lnLoanService.addSecondsForDate(endDate,59);
            parameterMap.put("applyEndDate",endDate);           //申请时间
            parameterMap.put("loanIsVerify",0);     // 0-未审计
            parameterMap.put("loanStatusId", loanStatus); //贷款状态
            parameterMap.put("customer", customer);
            Integer[] loanStatusIds={6,7};
            if (checkoutStatus!=null){
                parameterMap.put("loanIsCheckout", checkoutStatus);
                parameterMap.put("loanStatusIds",loanStatusIds);
            }
            parameterMap.put("randSort","true");

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
            logger.error("notVerifyLoanListQuery", e);
            return ERROR;
        }
    }


    public String loanVerify(){
        try{
            String strLoanId = request.getParameter("loanId");
            String NoGuarantee = request.getParameter("NoGuarantee");
            Integer loanId= Integer.parseInt(strLoanId.trim());
            List<LnRepaymentPlan> queryList= lnRepaymentPlanService.queryLnRepaymentPlan(loanId);
            List<LnExceptionRepaymentPlan> eQueryList =lnExceptionRepaymentPlanService.queryLnExceptionRepaymentPlan(loanId);
            request.setAttribute("queryList", queryList);
            request.setAttribute("eQueryList", eQueryList);
            request.setAttribute("loanId", loanId);
            request.setAttribute("NoGuarantee",NoGuarantee);
            return SUCCESS;
        }catch (Exception e){
            logger.error("loanVerify",e);
            return ERROR;
        }
    }

    //贷款审计通过
    public void LoanVerifyPass(){
        String StrloanId=request.getParameter("loanId");
        Integer loanId = Integer.parseInt(StrloanId.trim());
        String verifyRemark = request.getParameter("verifyRemark");
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("loanId",loanId);
        param.put("loanIsVerify",1);         //1.已审计
        param.put("loanIsVerifyPass",2);     //2.审计通过
        param.put("verifyDate",new Date());  //审计时间
        param.put("verifyRemark",verifyRemark);     //审计备注

        //插入审计历史记录
        LnVerifyHistory lnVerifyHistory= new LnVerifyHistory();
        lnVerifyHistory.setUserId(this.getLoginInfo().getUserId());
        lnVerifyHistory.setLoanId(loanId);
        lnVerifyHistory.setContent("审计通过");
        lnVerifyHistory.setRemark(verifyRemark);
        lnVerifyHistory.setVerifyHistoryIdDate(new Date());
        lnLoanService.addVerifyRemark(param,lnVerifyHistory);
    }

    //贷款审计不通过
    public void LoanVerifyNotPass(){
        String StrloanId=request.getParameter("loanId");
        Integer loanId = Integer.parseInt(StrloanId.trim());
        String verifyRemark = request.getParameter("verifyRemark");
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("loanId",loanId);
        param.put("loanIsVerify",1);               //1.已审计
        param.put("loanIsVerifyPass",3);             //3.审计不通过
        param.put("verifyDate",new Date());         //审计时间
        param.put("verifyRemark",verifyRemark);     //审计备注

        //插入审计历史记录
        LnVerifyHistory lnVerifyHistory= new LnVerifyHistory();
        lnVerifyHistory.setUserId(this.getLoginInfo().getUserId());
        lnVerifyHistory.setLoanId(loanId);
        lnVerifyHistory.setContent("审计不通过");
        lnVerifyHistory.setRemark(verifyRemark);
        lnVerifyHistory.setVerifyHistoryIdDate(new Date());
        lnLoanService.addVerifyRemark(param,lnVerifyHistory);
    }

    public String allotVerifyLoanPage(){
        try{
            String flag = "insert";
            String belongToType = request.getParameter("BelongToType");
            String belongDeptId = request.getParameter("belongDeptId");
            String belongUserId = request.getParameter("belongUserId");
            if(belongDeptId!=null && !belongDeptId.equals("0")&&!belongDeptId.equals("")){
                flag = "modify";	//编辑的情况
            }
            if(belongToType.equalsIgnoreCase("brDept")){
                List<SysDept> list = deptFacadeService.getBusinessManagerInCharegeDeptTreeList();
                JSONArray array = this.toTreeJson(list, flag, belongDeptId);
                request.setAttribute("array",array);
            }else if(belongToType.equalsIgnoreCase("brUser")){
                JSONArray array = this.toUserOrDeptJson(flag, belongUserId);
                request.setAttribute("array", array);
            }else if(belongToType.equalsIgnoreCase("brShareUser")){
                flag = "shareUser";
                JSONArray array = this.toUserOrDeptJson(flag, belongUserId);
                request.setAttribute("array", array);
            }
            request.setAttribute("userName",this.getLoginInfo().getUserName());
            request.setAttribute("userId",this.getLoginInfo().getUserId());
            return SUCCESS;
        }catch (Exception e){
            logger.error("NotVerifyLoanAction % allotVerifyLoanPage",e);
            return ERROR;
        }
    }

    public void allotVerifyLoan(){
        try{
            String loanIds = request.getParameter("loanIds");
            String userId = request.getParameter("assignUserIds");
            String userName = request.getParameter("assignUserNames");

            List<Integer> loanIdList = this.stringToIntegerList(loanIds);

            List<LnExceptionDunAssign> dunAssignList = new ArrayList<LnExceptionDunAssign>();
            List<LnVerifyHistory> lnVerifyHistoryList = new ArrayList<LnVerifyHistory>();
            for (Integer loanId : loanIdList){
                LnVerifyHistory lnVerifyHistory= new LnVerifyHistory();
                lnVerifyHistory.setUserId(this.getLoginInfo().getUserId());
                lnVerifyHistory.setVerifyHistoryIdDate(new Date());
                lnVerifyHistory.setContent("贷款分配给" + userName + "审计");
                lnVerifyHistory.setLoanId(loanId);

                lnVerifyHistoryList.add(lnVerifyHistory);
            }

            Map<String, Object> param = new HashMap<String ,Object>();
            param.put("loanIds",loanIds);
            param.put("verifyUserId",userId);

            lnLoanService.assignVerify(param,lnVerifyHistoryList);

        }catch (Exception e){
            logger.error("RecordWebServicesImpl % allotExceptionLoan",e);
        }
    }

    /**
     * 下属人员树
     * @param flag
     * @param belongUserId
     * @return 人员树json
     */
    private JSONArray toUserOrDeptJson(String flag, String belongUserId){
        JSONArray array = new JSONArray();
        List<CusBelongToBean> deptlist = null;
        if(flag.equals("shareUser")){
            deptlist = deptFacadeService.getBelongToUserAll();
        }else{
            deptlist = deptFacadeService.getBelongToUserAndDeptTreeList(getCurrentUserInChargeOfDeptIds());
        }
        Set<Integer> deptSet = new HashSet<Integer>();
        int ii = 0;
        for (CusBelongToBean dept : deptlist) {
            deptSet.add(dept.getId());
        }
        for(CusBelongToBean dept : deptlist){
            if(!deptSet.contains(dept.getParentId())){
                ii++;
            }
        }

        for(CusBelongToBean dept : deptlist){
            JSONObject obj = new JSONObject();
            obj.put("id", dept.getId());
            if(!deptSet.contains(dept.getParentId())){
                obj.put("pId", 0);
                if(ii==1){
                    obj.put("open", true);
                }
            }else{
                obj.put("pId", dept.getParentId());
            }

            obj.put("name", dept.getTextName());
            obj.put("flag", dept.getType());
            obj.put("deptName", dept.getDeptName());
            if(dept.getType().equals("D")){
                obj.put("icon", "../images/deptTreeImage/1.png");
            }else{
                obj.put("icon", "../images/deptTreeImage/2.png");
            }
            if((flag.equals("modify"))
                    && (dept.getId().toString().equals(belongUserId))
                    && (dept.getType().equals("U"))){
                obj.put("selected", true);
            }
            array.add(obj);
        }
        return array;
    }

    /**
     * 当前用户有权限的      机构ids
     * @return
     */
    private String getCurrentUserInChargeOfDeptIds(){
        String deptIds = "";
        Integer[] arr = deptFacadeService.getInChargeOfDeptIds();
        if(arr!=null){
            for(int i=0; i<arr.length; i++){
                if(deptIds.equals(""))
                    deptIds = arr[i].toString();
                else
                    deptIds = deptIds + "," + arr[i];
            }
        }
        return deptIds;
    }


    /**
     * 下属机构树
     * @param depts
     * @param flag
     * @param belongDeptId
     * @return 机构json
     */
    public JSONArray toTreeJson( List<SysDept> depts, String flag, String belongDeptId){
        try {
            Set<Integer> deptSet = new HashSet<Integer>();
            int ii = 0;
            for (SysDept sysDept : depts) {
                deptSet.add(sysDept.getDeptId());
            }
            for(SysDept sysDept : depts){
                if(!deptSet.contains(sysDept.getDeptParentId())){
                    ii++;
                }
            }

            JSONArray jsonArray = new JSONArray();
            if(depts.size()>0){
                for (SysDept dept : depts) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("id", dept.getDeptId());
                    if(!deptSet.contains(dept.getDeptParentId())){
                        map.put("pId", 0);
                        if(ii==1){
                            map.put("open", true);
                        }
                    }else{
                        map.put("pId", dept.getDeptParentId());
                    }
                    map.put("name", dept.getDeptName());
                    if(flag.equals("modify") && dept.getDeptId().toString().equals(belongDeptId)){
                        map.put("selected", true);
                    }
                    jsonArray.add(map);
                }
            }
            return jsonArray;
        } catch (Exception e) {
            //add by zhangxiang 2012-08-09
            log.error("CustomerBelongToAction toTreeJson action error:"+e.getMessage());
            return null;
        }
    }

    public void needAssignLoan(){
        try{
            String loanIds = request.getParameter("loanIds");
            Map<String ,Object> param = new HashMap<String, Object>();
            List<Integer> loanIdList = this.stringToIntegerList(loanIds);
            param.put("loanIds",loanIdList);
            List<LnLoan> lnLoanList = lnLoanService.getVerifyAssignList(param);
            List<Integer> needAssignLoanList = new ArrayList<Integer>();
            for(LnLoan loan: lnLoanList){
                needAssignLoanList.add(loan.getLoanId());
            }
            List<Integer> needAssing = compare(needAssignLoanList,loanIdList);
            StringBuffer sb = new StringBuffer();
            for(Integer t :needAssing){
                if(sb.length() ==0){
                    sb.append(t);
                }else{
                    sb.append(",");
                    sb.append(t);
                }
            }

            HttpServletResponse response = this.getResponse();
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();

            out.print(sb.toString());

            if(out != null){
                out.flush();
                out.close();
            }
        }catch (Exception e){
            log.error(e);
        }
    }

    private List<Integer> stringToIntegerList(String str){
        if(StringUtils.isNotEmpty(str)){
            List<Integer> idList = new ArrayList<Integer>();
            String[] ids = str.split(",");
            for (String id : ids){
                idList.add(Integer.parseInt(id));
            }
            return idList ;
        }
        return null;
    }

    /**
     * 验证选择的贷款是否已经被分配了异常催收记录
     */
    public void checkVerifyLoan(){
        try{
            String loanIds = request.getParameter("loanIds");
            List<Integer> loanIdList = this.stringToIntegerList(loanIds);
            Map<String,Object> paramMap = new HashMap<String, Object>();
            paramMap.put("loanIds",loanIdList);
            //检验当前选择的贷款是否已被分配
            Integer count = lnLoanService.getVerifyAssignCount(paramMap);

            HttpServletResponse response = this.getResponse();
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();

            if (count != null && count.intValue() > 0){
                if(loanIdList != null && loanIdList.size() == 1){
                    //只选择了一个贷款分配
                    out.print("true_single");
                }else if(loanIdList != null && loanIdList.size() == count){
                    //选择了多个贷款分配  并全部已分配
                    out.print("true_all");
                }else {
                    //选择了多个贷款分配  并部分已分配
                    out.print("true_multi");
                }
            }else if(count != null && count.intValue() == 0){
                out.print("false");
            }
            if(out != null){
                out.flush();
                out.close();
            }
        }catch (Exception e){
            logger.error("ExceptionDunLoanAction % checkExceptionDunLoan",e);
        }
    }

    public static <T> List<Integer> compare(List<Integer> t1, List<Integer> t2) {
        List<Integer> list2 = new ArrayList<Integer>();
        for (Integer t : t2) {
            if (!t1.contains(t)) {
                list2.add(t);
            }
        }
        return list2;
    }


    public SpecialDataAuthService getSpecialDataAuthService() {
        return specialDataAuthService;
    }

    public void setSpecialDataAuthService(SpecialDataAuthService specialDataAuthService) {
        this.specialDataAuthService = specialDataAuthService;
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
}
