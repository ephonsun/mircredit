package com.banger.mobile.webapp.action.loan;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;

import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.loan.*;
import com.banger.mobile.domain.model.user.SysDeptAuth;
import com.banger.mobile.facade.dept.DeptService;
import com.banger.mobile.facade.loan.*;
import com.banger.mobile.facade.user.SysDeptAuthService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.base.loan.BaseLnLoanMonitor;
import com.banger.mobile.domain.model.data.Form;
import com.banger.mobile.domain.model.data.LoanData;
import com.banger.mobile.domain.model.data.Photo;
import com.banger.mobile.domain.model.system.SysTeam;
import com.banger.mobile.domain.model.uploadFile.SysUploadFile;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.data.CustomerDataService;
import com.banger.mobile.facade.data.DataFormService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.system.team.SysTeamService;
import com.banger.mobile.facade.system.team.SysTeamUserService;
import com.banger.mobile.facade.uploadFile.SysUploadFileService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.webapp.action.BaseAction;

public class ConfirmLoanAction extends BaseAction {

    private static Logger logger = Logger.getLogger(ConfirmLoanAction.class);
    private SysTeamUserService sysTeamUserService;
    private LnLoanDetailService lnLoanDetailService;
    private SysUploadFileService sysUploadFileService;
    private LnOpHistoryService lnOpHistoryService;
    private LnLoanService lnLoanService;
    private LnLoanTypeService lnLoanTypeService;
    private LnLoanMonitorService lnLoanMonitorService;
    private LnRepaymentPlanService lnRepaymentPlanService;
    private DataFormService dataFormService; //表单service
    private LnDunLogService          lnDunLogService;
    private CustomerDataService customerDataService; //贷款资料service
    private String customer;
    private BigDecimal loanRemaining;
    private Date lendStartDate;
    private Date lendEndDate;
    private Integer loanType;
    private Integer loanIsCheckout;
    private String 				  resultRepayDate;
    private String 				  monitored;
    private String  montype;
    private String nomontype;
    // 查询条件
    private Integer loanId;
    private List<LnLoanCoBorrowerBean> loanCoBorrowerList;       //共同借贷人列表
    private List<LnLoanGuarantorBean> loanGuarantorList;        //担保人列表
    private List<LnCreditHistory> lnCreditHistoryList;
    private List<LnLoanMonitor> lnLoanMonitorList;//贷后监控
    private LnLoanInfoService lnLoanInfoService;
    private LnLoanInfoDictionaryService lnLoanInfoDictionaryService;
    private LnCreditHistoryService lnCreditHistoryService;
    //private LnLoanInfo lnLoanInfo;
    private SysTeamService sysTeamService;
    private SysUserService sysUserService;

    private LnBusinessModelService lnBusinessmodelService;
    private LnLoanHistoryService lnLoanHistoryService;
    private LnRecordInterviewService lnRecordInterviewService;
    private LnLoanBalanceService lnLoanBalanceService;
    private LnLoanBalanceAssetService lnLoanBalanceAssetService;
    private LnLoanBalanceDebtService  lnLoanBalanceDebtService;
    private LnLoanBalanceVehicleService lnLoanBalanceVehicleService;
    private LnLoanBalanceOtherService lnLoanBalanceOtherService;
    private LnLoanBalanceHousingService lnLoanBalanceHousingService;
    private LnLoanBalanceReceivableService lnLoanBalanceReceivableService;
    private LnLoanProfitandlossService lnLoanProfitandlossService;
    private LnLoanProfitandlossJyDetailService lnLoanProfitandlossJyDetailService;
    private LnLoanProfitandlossXfDetailService  lnLoanProfitandlossXfDetailService;
    private JSONArray deptJson; // 机构树json
    private JSONArray deptManagerJson;


    public String getMontype() {
        return montype;
    }

    public void setMontype(String montype) {
        this.montype = montype;
    }

    public String getNomontype() {
        return nomontype;
    }

    public void setNomontype(String nomontype) {
        this.nomontype = nomontype;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public JSONArray getDeptJson() {
        return deptJson;
    }

    public void setDeptJson(JSONArray deptJson) {
        this.deptJson = deptJson;
    }

    public JSONArray getDeptManagerJson() {
        return deptManagerJson;
    }

    public void setDeptManagerJson(JSONArray deptManagerJson) {
        this.deptManagerJson = deptManagerJson;
    }

    private SysDeptAuthService sysDeptAuthService; // 可管理机构

    private DeptService deptService; // 机构service
    private DeptFacadeService deptFacadeService;

    public SysUserService getSysUserService() {
        return sysUserService;
    }

    public SysDeptAuthService getSysDeptAuthService() {
        return sysDeptAuthService;
    }

    public void setSysDeptAuthService(SysDeptAuthService sysDeptAuthService) {
        this.sysDeptAuthService = sysDeptAuthService;
    }

    public DeptService getDeptService() {
        return deptService;
    }

    public void setDeptService(DeptService deptService) {
        this.deptService = deptService;
    }

    public LnBusinessModelService getLnBusinessmodelService() {
        return lnBusinessmodelService;
    }

    public void setLnBusinessmodelService(LnBusinessModelService lnBusinessmodelService) {
        this.lnBusinessmodelService = lnBusinessmodelService;
    }

    public LnLoanHistoryService getLnLoanHistoryService() {
        return lnLoanHistoryService;
    }

    public void setLnLoanHistoryService(LnLoanHistoryService lnLoanHistoryService) {
        this.lnLoanHistoryService = lnLoanHistoryService;
    }

    public LnRecordInterviewService getLnRecordInterviewService() {
        return lnRecordInterviewService;
    }

    public void setLnRecordInterviewService(LnRecordInterviewService lnRecordInterviewService) {
        this.lnRecordInterviewService = lnRecordInterviewService;
    }

    public LnLoanBalanceService getLnLoanBalanceService() {
        return lnLoanBalanceService;
    }

    public void setLnLoanBalanceService(LnLoanBalanceService lnLoanBalanceService) {
        this.lnLoanBalanceService = lnLoanBalanceService;
    }

    public LnLoanBalanceAssetService getLnLoanBalanceAssetService() {
        return lnLoanBalanceAssetService;
    }

    public void setLnLoanBalanceAssetService(LnLoanBalanceAssetService lnLoanBalanceAssetService) {
        this.lnLoanBalanceAssetService = lnLoanBalanceAssetService;
    }

    public LnLoanBalanceDebtService getLnLoanBalanceDebtService() {
        return lnLoanBalanceDebtService;
    }

    public void setLnLoanBalanceDebtService(LnLoanBalanceDebtService lnLoanBalanceDebtService) {
        this.lnLoanBalanceDebtService = lnLoanBalanceDebtService;
    }

    public LnLoanBalanceVehicleService getLnLoanBalanceVehicleService() {
        return lnLoanBalanceVehicleService;
    }

    public void setLnLoanBalanceVehicleService(LnLoanBalanceVehicleService lnLoanBalanceVehicleService) {
        this.lnLoanBalanceVehicleService = lnLoanBalanceVehicleService;
    }

    public LnLoanBalanceOtherService getLnLoanBalanceOtherService() {
        return lnLoanBalanceOtherService;
    }

    public void setLnLoanBalanceOtherService(LnLoanBalanceOtherService lnLoanBalanceOtherService) {
        this.lnLoanBalanceOtherService = lnLoanBalanceOtherService;
    }

    public LnLoanBalanceHousingService getLnLoanBalanceHousingService() {
        return lnLoanBalanceHousingService;
    }

    public void setLnLoanBalanceHousingService(LnLoanBalanceHousingService lnLoanBalanceHousingService) {
        this.lnLoanBalanceHousingService = lnLoanBalanceHousingService;
    }

    public LnLoanBalanceReceivableService getLnLoanBalanceReceivableService() {
        return lnLoanBalanceReceivableService;
    }

    public void setLnLoanBalanceReceivableService(LnLoanBalanceReceivableService lnLoanBalanceReceivableService) {
        this.lnLoanBalanceReceivableService = lnLoanBalanceReceivableService;
    }

    public LnLoanProfitandlossService getLnLoanProfitandlossService() {
        return lnLoanProfitandlossService;
    }

    public void setLnLoanProfitandlossService(LnLoanProfitandlossService lnLoanProfitandlossService) {
        this.lnLoanProfitandlossService = lnLoanProfitandlossService;
    }

    public LnLoanProfitandlossJyDetailService getLnLoanProfitandlossJyDetailService() {
        return lnLoanProfitandlossJyDetailService;
    }

    public void setLnLoanProfitandlossJyDetailService(LnLoanProfitandlossJyDetailService lnLoanProfitandlossJyDetailService) {
        this.lnLoanProfitandlossJyDetailService = lnLoanProfitandlossJyDetailService;
    }

    public LnLoanProfitandlossXfDetailService getLnLoanProfitandlossXfDetailService() {
        return lnLoanProfitandlossXfDetailService;
    }

    public void setLnLoanProfitandlossXfDetailService(LnLoanProfitandlossXfDetailService lnLoanProfitandlossXfDetailService) {
        this.lnLoanProfitandlossXfDetailService = lnLoanProfitandlossXfDetailService;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public SysTeamService getSysTeamService() {
        return sysTeamService;
    }

    public void setSysTeamService(SysTeamService sysTeamService) {
        this.sysTeamService = sysTeamService;
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

    public Date getLendStartDate() {
        return lendStartDate;
    }

    public void setLendStartDate(Date lendStartDate) {
        this.lendStartDate = lendStartDate;
    }

    public Date getLendEndDate() {
        return lendEndDate;
    }

    public void setLendEndDate(Date lendEndDate) {
        this.lendEndDate = lendEndDate;
    }

    public Integer getLoanType() {
        return loanType;
    }

    public void setLoanType(Integer loanType) {
        this.loanType = loanType;
    }

    public Integer getLoanIsCheckout() {
        return loanIsCheckout;
    }

    public void setLoanIsCheckout(Integer loanIsCheckout) {
        this.loanIsCheckout = loanIsCheckout;
    }

    public DeptFacadeService getDeptFacadeService() {
        return deptFacadeService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    public String getResultRepayDate() {
        return resultRepayDate;
    }

    public void setResultRepayDate(String resultRepayDate) {
        this.resultRepayDate = resultRepayDate;
    }

    public String getMonitored() {
        return monitored;
    }

    public void setMonitored(String monitored) {
        this.monitored = monitored;
    }

    /**
     * 贷后监控资料列表
     * @return
     */
    public String loanMoniorUploadList(){


        try{
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 进入编辑贷后管理贷款编辑界面...");
            String loanMonId = request.getParameter("loanMonId");
            BaseLnLoanMonitor loanMonitor= lnLoanMonitorService.getLoanMonitor(Integer.valueOf(loanMonId));
            if(!"".equals(loanMonitor.getFileIds())){
                String[] fileIds = loanMonitor.getFileIds().split(",");
                List<SysUploadFile> files=new ArrayList<SysUploadFile>();
                for(int i=0;i<fileIds.length;i++){

                    SysUploadFile file = sysUploadFileService.getSysUploadFileById(Integer.valueOf(fileIds[i]));
                    SysUser user = sysUserService.getSysUserById(file.getCreateUser());
                    file.setUserName(user.getUserName());
                    files.add(file);
                }

                this.getRequest().setAttribute("files",files);
            }
            return  SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("confirmLoanEdit",e);
            return ERROR;
        }
    }

    public String confirmLoanList(){
        //TODO:修改
        //return SUCCESS;

        try{
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 进入贷后管理菜单列表...");
            Map<String, Object> conds = new HashMap<String, Object>();
            // 是否是业务主管
            //  Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment();
            String belongUserIds = "";
            String[] roles = this.getLoginInfo().getRoles();
            boolean isInChargeOf = false;
            for (String roleId : roles) {
                if(roleId.equals("5") || roleId.equals("6")){
                    isInChargeOf = true;
                    break;
                }
            }
            if (isInChargeOf) {
                // 当前用户的团队底下的成员
                List<String> belongUserId = sysTeamUserService.getUserIdsByChiefUserId(this.getLoginInfo().getUserId());
                int i = 0;
                for (String str : belongUserId) {
                    if(i == 0){
                        belongUserIds +=  str;
                        i=1;
                    }else{
                        belongUserIds += "," + str;
                    }
                }
                conds.put("belongUserIds", belongUserIds); // 当前用户所管理的提交申请用户
            } else {

                String role = roles[0];
                if(role.equals("7")){  // 客户经理
                    conds.put("surveyUserId", this.getLoginInfo().getUserId());
                }else if(role.equals("4")){
                    conds.put("approveBackerUserId", this.getLoginInfo().getUserId());
                }
            }



            //贷款类型
            List<LnLoanType> typeList = lnLoanTypeService.getLoanTypeList();

            //Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment();
           /* if(isInChargeOf){
                //业务主管当前用户的下属用户
                List<Integer> belongUserIds = this.getSysUserIds();
                conds.put("surveyUserIds", belongUserIds); //当前用户所管理的提交申请用户
            } else{
                //客户经理
                conds.put("surveyUserId", this.getLoginInfo().getUserId());
            }
            
            conds.put("isConfirm","isConfirm");*/
            conds.put("allLoanOrderBy",1);
            if(loanIsCheckout!=null&&loanIsCheckout!=0){
                conds.put("loanStatusId", loanIsCheckout);
            }else{
                conds.put("loanStatusId","6,7");   // 6表示还款中
            }
            //conds.put("customer","张");
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 开始搜索初始状态下的贷后贷款...");
            PageUtil<LnLoan> dataList = lnLoanService.getMakeLoanPage(conds, this.getPage());
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 搜索贷后贷款完成，总共搜索到"+dataList.getItems().size()+"条符合条件的贷后贷款");
            request.setAttribute("dataList", dataList.getItems());
            request.setAttribute("recordCount", dataList.getPage().getTotalRowsAmount());
            request.setAttribute("typeList", typeList);
            request.setAttribute("sysUserId",this.getLoginInfo().getUserId());
            HashMap<String,Object> checkBoxMessageMap = buildLoanCheckBoxMessage("DKLX","DBFS","DY","ZY","FLXS","HYZK","JYCD","JYCS","JZCSLX","JZZK","XXLY","YSRSP","ZJLX","ZXQK","ZY","ZZXS","JKLX","HFLX","CSFS");
            request.setAttribute("checkBoxMessage", checkBoxMessageMap);
            /* if(dataList != null && dataList.getItems() != null){
                for (LnLoan lnLoan : dataList.getItems()) {
                    Date lendDate = lnLoan.getLendContractCheckDate();
                    Date nowDate = new Date();
                    if(lendDate!=null){
                        if((nowDate.getTime() - lendDate.getTime()) >= 1000 * 60 * 60 * 24 * 11){
                            if(lnLoan.getl()!=1){
                            //2周未落实超时前3天,前端在高亮显示
                                lnLoan.setIsWillPast(1);
                            }
                        }
                    }
                }
            }*/
            return SUCCESS;
        }catch(Exception e){
            e.printStackTrace();
            logger.error("ConfirmLoanAction % confirmLoanList", e);
            return ERROR;
        }
    }

    public String confirmLoanListQuery(){
        //TODO:修改


        try{
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 按条件搜索贷后管理贷款...");
            //列表数据
            Map<String, Object> conds = new HashMap<String, Object>();
            // 是否是业务主管
            //  Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment();
            String belongUserIds = "";
            String[] roles = this.getLoginInfo().getRoles();
            boolean isInChargeOf = false;
            for (String roleId : roles) {
                if(roleId.equals("5") || roleId.equals("6")){
                    isInChargeOf = true;
                    break;
                }
            }
            if (isInChargeOf) {
                // 当前用户的团队底下的成员
                List<String> belongUserId = sysTeamUserService.getUserIdsByChiefUserId(this.getLoginInfo().getUserId());
                int i = 0;
                for (String str : belongUserId) {
                    if(i == 0){
                        belongUserIds +=  str;
                        i=1;
                    }else{
                        belongUserIds += "," + str;
                    }
                }
                conds.put("belongUserIds", belongUserIds); // 当前用户所管理的提交申请用户
            } else {

                String role = roles[0];
                if(role.equals("7")){  // 客户经理
                    conds.put("surveyUserId", this.getLoginInfo().getUserId());
                }else if(role.equals("4")){
                    conds.put("approveBackerUserId", this.getLoginInfo().getUserId());
                }
            }
            
            
           /* Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment();
            if(isInChargeOf){
                //业务主管当前用户的下属用户
                List<Integer> belongUserIds = this.getSysUserIds();
                conds.put("surveyUserIds", belongUserIds); //当前用户所管理的提交申请用户
            } else{
                //客户经理
                conds.put("surveyUserId", this.getLoginInfo().getUserId());
            }*/
            if(loanIsCheckout!=null&&loanIsCheckout!=0){
                conds.put("loanStatusId", loanIsCheckout);
            }else{
                conds.put("loanStatusId","6,7");   // 6表示还款中
            }
            if (resultRepayDate != null && !resultRepayDate.equals("")){
                conds.put("resultRepayDate", resultRepayDate);
                logger.info("决议还款日："+resultRepayDate);
            }
            if (monitored != null && !monitored.equals("")){
                logger.info("是否检查："+monitored);
                conds.put("monitored", monitored);
            }
            if (montype != null && !montype.equals("")){
                logger.info("已完成监控类型："+montype);
                conds.put("montype", montype);
            }
            if (nomontype != null && !nomontype.equals("")){
                logger.info("未完成监控类型："+nomontype);
                conds.put("nomontype", nomontype);
            }
            conds.put("customer", customer);
            conds.put("loanTypeId", loanType);
            conds.put("lendStartDate", lendStartDate);
            lendEndDate = lnLoanService.addSecondsForDate(lendEndDate,59);
            conds.put("lendEndDate", lendEndDate);
            conds.put("isConfirm","isConfirm");
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 搜索条件，客户："+customer+",贷款类型："+loanType+",贷后状态："+loanIsCheckout+",放贷时间："+lendStartDate+"——"+lendEndDate+"是否检查："+monitored+"决议还款日："+resultRepayDate+"已完成监控类型："+montype+"未完成监控类型："+nomontype);
            PageUtil<LnLoan> dataList = lnLoanService.getMakeLoanPage(conds, this.getPage());
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 根据条件，总共搜索到"+dataList.getItems().size()+"条贷后管理贷款");
            HashMap<String,Object> checkBoxMessageMap = buildLoanCheckBoxMessage("DKLX","DBFS","DY","ZY","FLXS","HYZK","JYCD","JYCS","JZCSLX","JZZK","XXLY","YSRSP","ZJLX","ZXQK","ZY","ZZXS","JKLX","HFLX","CSFS");
            request.setAttribute("checkBoxMessage", checkBoxMessageMap);
            /* if(dataList != null && dataList.getItems() != null){
                for (LnLoan lnLoan : dataList.getItems()) {
                    Date lendDate = lnLoan.getLendDate();
                    Date nowDate = new Date();
                    if(lendDate !=null){
                        if((nowDate.getTime() - lendDate.getTime()) >= 1000 * 60 * 60 * 24 * 11){
                            if(lnLoan.getIsCheckout()!=1){
                                //2周未落实超时前3天,前端在高亮显示
                                lnLoan.setIsWillPast(1);
                            }
                        }
                    }
                }
            }*/
            request.setAttribute("dataList", dataList.getItems());
            request.setAttribute("recordCount", dataList.getPage().getTotalRowsAmount());
            request.setAttribute("sysUserId",this.getLoginInfo().getUserId());

            return SUCCESS;
        }catch (Exception e){
            logger.error("confirmLoanListQuery", e);

            
            return ERROR;
        }
    }

    public  String edit(){
        try{
            /**
             * 改为放贷是eventID 贷款状态改为5  合同号变为空 删除还款计划
             */

           String loanStatusId = request.getParameter("loanStatusId");
           String  lendConfrimContractNum =  request.getParameter("lendConfrimContractNum");
           String  loanId = request.getParameter("loanId");
           logger.info("修改贷款信息 贷款状态为："+loanStatusId+"贷款合同号为："+lendConfrimContractNum+"贷款id为："+loanId);
           Map<String, Object> map  = new HashMap<String, Object>();
           if (loanStatusId != null && !loanStatusId.equals("")){
                map.put("loanStatusId",loanStatusId);
           }
           if(loanId!=null && !loanId.equals("")){
               map.put("loanId",loanId);
           }
           if("5".equals(loanStatusId)){
               map.put("loanStatusId",loanStatusId);
               map.put("eventId",5);
               //更新贷款主表贷款状态
               lnLoanService.updateLnLoanById(map);
               //将贷款信息表中的确认合同号置为空
               LnLoanInfo lnLoanInfo = lnLoanInfoService.getPanLoanInfoById(Integer.parseInt(loanId));
               lnLoanInfo.setLendConfrimContractNum("   ");
               lnLoanInfoService.updateLnLoanInfo(lnLoanInfo);
               //删除该贷款下的还款计划
               List<Integer> loanIdList = new LinkedList<Integer>();
               loanIdList.add(Integer.parseInt(loanId));
               lnRepaymentPlanService.deletePlanByLoanIdBatch(loanIdList);
           }else if ("4".equals(loanStatusId)){
               map.put("loanStatusId",loanStatusId);
               map.put("eventId",4);
               //更新贷款主表贷款状态
               lnLoanService.updateLnLoanById(map);
            }else{
               if(lendConfrimContractNum!=null && !lendConfrimContractNum.equals("")){
                   //修改合同号
                   LnLoanInfo lnLoanInfo = lnLoanInfoService.getPanLoanInfoById(Integer.parseInt(loanId));
                   lnLoanInfo.setLendConfrimContractNum(lendConfrimContractNum);
                   lnLoanInfoService.updateLnLoanInfo(lnLoanInfo);
               }
           }


           logger.info("贷款修改成功");
           return SUCCESS;
        }catch (Exception e){
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

    //编辑已落实贷款
    public String confirmLoanEdit(){
        //TODO:修改

        try{
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 进入编辑贷后管理贷款编辑界面...");
            String strLoanId = request.getParameter("loanId");
            String isConfirm =request.getParameter("isConfirm");
            Integer loanId= Integer.parseInt(strLoanId.trim());
            LnLoan lnLoan = lnLoanService.getLnLoanById(loanId);
            /*Integer isNogood =lnLoan.getIsNogood();
            String nogoodRemark =lnLoan.getNogoodRemark();*/
            String[] roles = this.getLoginInfo().getRoles();
            boolean isCustomerManage = false; //是否为客户经理标志
            for (String roleId : roles) {
                if(roleId.equals("7") ){
                    isCustomerManage = true;
                    break;
                }
            }
            request.setAttribute("roles",roles[0]);
            request.setAttribute("isCustomerManage", isCustomerManage);


            List<LnRepaymentPlan> queryList= lnRepaymentPlanService.queryLnRepaymentPlan(loanId);
            LnRepaymentPlan queryPlan= lnRepaymentPlanService.selectCurLnRepaymentPlanById(loanId);
            //查询贷款余额
            request.setAttribute("queryPlan", queryPlan);
            Map<String,Object> paramMap = new HashMap<String, Object>();
            paramMap.put("eventId", LoanConstants.LOAN_DUN_EVENT); //贷后流程
            paramMap.put("loanId",lnLoan.getLoanId());
            //贷款表单附件信息
            List<Form> formAttachmentList = dataFormService.selectFormAttachment(paramMap);
            request.setCharacterEncoding("UTF-8");
            request.setAttribute("lnRepaymentPlanList", queryList);
            request.setAttribute("loanId", loanId);
         
           /* request.setAttribute("isNogood",isNogood);
            request.setAttribute("nogoodRemark",nogoodRemark);*/
            request.setAttribute("loan",lnLoan);
            request.setAttribute("formAttachmentList",formAttachmentList);
            request.setAttribute("isConfirm",isConfirm);
            List<SysTeam> sysTeamList = sysTeamService.getSysTeamList();
            request.setAttribute("sysTeamList", sysTeamList);

            List<LnDunLog> lnDunLogList = null;
            // 正常催收
            lnDunLogList = lnDunLogService.getDunLogByLoanId(loanId);  //已优化
            /* for (LnDunLog lnDunLog : lnDunLogList) {
                //web端电话催收特殊处理
                if (lnDunLog.getUpdateUser() != null) {
                    RecordDetail d = recordInfoService.getRecordInfoById(lnDunLog.getUpdateUser());
                    if (d != null) {
                        lnDunLog.setRemark(d.getRemark());
                    }
                }

                if (lnDunLog.getCustomerDataId() != null && !lnDunLog.getCustomerDataId().equals(0)){
                    customerDataIdList.add(lnDunLog.getCustomerDataId());
                }
            }*/
            request.setAttribute("lnDunLogList",lnDunLogList);

            // 所有资料

            Map<String, Object> param = new HashMap<String, Object>();
            param.put("loanId", loanId);
            LnLoanInfo lnLoanInfo = lnLoanInfoService.selectLoanInfoList(param).get(0);

            request.setAttribute("lnLoanInfo",lnLoanInfo);
            request.setAttribute("lnLoan",lnLoan);
            request.setAttribute("eventId", lnLoan.getLoanStatusId());
            request.setAttribute("loanStatusId", lnLoan.getLoanStatusId());
            // 查询陪调人员
            if (lnLoanInfo.getTogetherSurveyUserId() != null
                    && lnLoanInfo.getTogetherSurveyUserId() > 0) {
                SysUser sysUser = sysUserService.getSysUserById(lnLoanInfo
                        .getTogetherSurveyUserId());
                request.setAttribute("togetherSurveyUsername",
                        sysUser.getUserName());
            }

            //查询贷后监控记录
            lnLoanMonitorList= lnLoanMonitorService.getLoanMonitorList(loanId);
            for(LnLoanMonitor lm:lnLoanMonitorList){
                if(StringUtils.isNotEmpty(lm.getFileIds())){
                    List<SysUploadFile>  fileIdList = new ArrayList<SysUploadFile> ();
                    SysUploadFile flie = new SysUploadFile();
                    if(lm.getFileIds().contains(",")){
                        for(String id:lm.getFileIds().split(",")){
                            flie= sysUploadFileService.getSysUploadFileById(Integer.parseInt(id));
                            fileIdList.add(flie);
                        }
                    }else{
                        flie= sysUploadFileService.getSysUploadFileById(Integer.parseInt(lm.getFileIds()));
                        fileIdList.add(flie);
                    }
                    lm.setFileIdList(fileIdList);
                }
            }
            request.setAttribute("lnLoanMonitorList", lnLoanMonitorList);
            //查询历史操作记录
            List<LnOpHistory> hisList = lnOpHistoryService.getAllOpHistoryListByLoanId(loanId);
            request.setAttribute("hisList", hisList);

            //所有资料
            Map<String,Object> photoMap = new HashMap<String, Object>();
            photoMap.put("loanId",loanId);
            photoMap.put("eventId",LoanConstants.LOAN_DUN_EVENT);
            photoMap.put("statistics",1);
            List<LoanData> dataList = customerDataService.getAllLoanDataById(photoMap);
            List<Photo> photoList = new ArrayList<Photo>();
            //申请人照片资料
            for (LoanData d : dataList) {
                if (d.getDataType().equals(1)){
                    if (d.getCustomerId().equals(lnLoan.getCustomerId())){
                        String photoUrl = d.getFilePath() + "/" + d.getFileName();
                        Photo photo = new Photo();
                        photo.setFileId(d.getFileId());
                        photo.setPhotoId(d.getDataId());
                        photo.setFilePath(photoUrl);

                        photoList.add(photo);
                    }
                }
            }
            request.setAttribute("photoList",photoList);

            HashMap<String,Object> checkBoxMessageMap = buildLoanCheckBoxMessage("DKLX","DBFS","DY","ZY","FLXS","HYZK","JYCD","JYCS","JZCSLX","JZZK","XXLY","YSRSP","ZJLX","ZXQK","ZY","ZZXS","JKLX","HFLX","CSFS");
            request.setAttribute("checkBoxMessage", checkBoxMessageMap);
            // 共同借贷人
            loanCoBorrowerList = lnLoanDetailService.getLoanCoList(loanId, dataList);
            // 担保人
            loanGuarantorList = lnLoanDetailService.getLoanGuList(loanId, dataList);
            //信贷历史
            lnCreditHistoryList = lnCreditHistoryService.getCreditHistoryByLoanId(loanId);
            String[] sourcesIds = lnLoanInfo.getRegisterInfoSourceIds().split(",");
            HashMap<String,Integer> sourcesIdMap = new HashMap<String,Integer>();
            for (String string : sourcesIds) {
                sourcesIdMap.put(string, 1);
            }
            request.setAttribute("soucesIdMap", sourcesIdMap);
            request.setAttribute("isEdit","yes");

            // 树形控件
            initParameter();
            if (lnLoan.getLoanStatusId() > 2) {
                String appLoanBak = lnLoanInfo.getAppFormBak();
                if(appLoanBak != null && !appLoanBak.equals("")){
                    JSONObject jsonObject = JSONObject.fromObject(appLoanBak);
                    //	String[] dateFormats = new String[]{"yyyy-MM-dd HH:mm:ss"};
                    //   JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));
                    LnLoanAppFormBak lnLoanAppFormBak = (LnLoanAppFormBak) JSONObject
                            .toBean(jsonObject, LnLoanAppFormBak.class);
                    request.setAttribute("lnLoanInfoBak", lnLoanAppFormBak);
                    request.setAttribute("showBak", "true");

                    String[] sourcesIdsBak = lnLoanAppFormBak.getLnLoanInfo().getRegisterInfoSourceIds()
                            .split(",");
                    HashMap<String, Integer> sourcesIdMapBak = new HashMap<String, Integer>();
                    for (String string : sourcesIdsBak) {
                        sourcesIdMapBak.put(string, 1);
                    }
                    request.setAttribute("sourcesIdsBak", sourcesIdMapBak);
                }
                //经营贷
                if("1".equals(lnLoanInfo.getAppLoanTypeId())){
                    //经营模式
                    LnBusinessModel lnBusinessModel=lnBusinessmodelService.selectByPrimary(loanId);
                    //软信息
                    if(lnBusinessModel!=null&&!StringUtils.isBlank(lnBusinessModel.getSorftInfo())){
                        String[] sorftInfoIds = lnBusinessModel.getSorftInfo().split(",");
                        HashMap<String, Integer> sorftInfoIdsMap = new HashMap<String, Integer>();
                        for (String string : sorftInfoIds) {
                            sorftInfoIdsMap.put(string, 1);
                        }
                        request.setAttribute("sorftInfoMap", sorftInfoIdsMap);

                    }
                    request.setAttribute("lnBusinessModel", lnBusinessModel);

                }

                //发展历史，访谈记录
                if("2".equals(lnLoanInfo.getAppLoanTypeId())){
                    //发展历史
                    LnLoanHistory loanHistory=lnLoanHistoryService.selectByPrimary(loanId);
                    //家庭软信息
                    if(loanHistory!=null&&!StringUtils.isBlank(loanHistory.getFamilyInfo())){
                        String[] familyInfoIds = loanHistory.getFamilyInfo().split(",");
                        HashMap<String, Integer> familyInfoIdsMap = new HashMap<String, Integer>();
                        for (String string : familyInfoIds) {
                            familyInfoIdsMap.put(string, 1);
                        }
                        request.setAttribute("familyInfoIdsMap", familyInfoIdsMap);
                    }
                    //但保软信息
                    if(loanHistory!=null&&!StringUtils.isBlank(loanHistory.getGuaranteeInfo())){
                        String[] guaranteeIds = loanHistory.getGuaranteeInfo().split(",");
                        HashMap<String, Integer> guaranteeIdsMap = new HashMap<String, Integer>();
                        for (String string : guaranteeIds) {
                            guaranteeIdsMap.put(string, 1);
                        }
                        request.setAttribute("guaranteeIdsMap", guaranteeIdsMap);

                    }
                    request.setAttribute("lnLoanHistory", loanHistory);
                    //访谈记录
                    LnRecordInterview lnRecordInterview=lnRecordInterviewService.selectByPrimary(loanId);
                    request.setAttribute("lnRecordInterview", lnRecordInterview);
                }

                //资产负债表
                LnLoanBalance lnLoanBalance=lnLoanBalanceService.selectBalanceByPrimary(loanId);

                request.setAttribute("lnLoanBalance", lnLoanBalance);

                if(lnLoanBalance!=null){
                    Integer loanBalanceId= lnLoanBalance.getLoanBalanceId();
                    //经营贷
                    if("1".equals(lnLoanInfo.getAppLoanTypeId())){
                        //房产
                        List<LnLoanBalanceHousing> housingList = lnLoanBalanceHousingService.selectHousingByPrimary(loanBalanceId);
                        this.getRequest().setAttribute("lnLoanBalanceHousingList", housingList);

                        //机动车
                        List<LnLoanBalanceVehicle> vehicleList = lnLoanBalanceVehicleService.selectVehicleByPrimary(loanBalanceId);

                        this.getRequest().setAttribute("lnLoanBalanceVehicleList", vehicleList);

                        //应收账明细
                        List<LnLoanBalanceReceivable> receivableList = lnLoanBalanceReceivableService.selectReceivableByPrimary(loanBalanceId);

                        this.getRequest().setAttribute("lnLoanBalanceReceivableList", receivableList);
                        //可变成本及其他交叉检验
                        List<LnLoanBalanceOther> otherList = lnLoanBalanceOtherService.selectOtherByPrimary(loanBalanceId);

                        this.getRequest().setAttribute("lnLoanBalanceOtherList", otherList);
                    }

                    //资产负债表资产
                    List<LnLoanBalanceAsset> assetList = lnLoanBalanceAssetService.selectAssetByPrimary(loanBalanceId);

                    this.getRequest().setAttribute("lnLoanBalanceAssetList", assetList);

                    //负债
                    List<LnLoanBalanceDebt> debtList = lnLoanBalanceDebtService.selectDebtByPrimary(loanBalanceId);

                    this.getRequest().setAttribute("lnLoanBalanceDedtList", debtList);

                }

                //损益表
                List<LnLoanProfitandloss> loanProfitandlossList=lnLoanProfitandlossService.selectProfitandlossByPrimary(loanId);


                if(loanProfitandlossList!=null&&loanProfitandlossList.size()>0){
                    LnLoanProfitandloss lnLoanProfitandloss	= loanProfitandlossList.get(0);
                    //经营贷
                    if("1".equals(lnLoanInfo.getAppLoanTypeId())){

                        Map<String,Object> profitandlossJyDetailMap=lnLoanProfitandlossJyDetailService.profitandlossJyDetail(lnLoanProfitandloss.getLoanProfitandlossId());
                        this.getRequest().setAttribute("profitandlossJyDetailMap", profitandlossJyDetailMap);
                    }

                    //消费贷
                    if("2".equals(lnLoanInfo.getAppLoanTypeId())){

                        Map<String,Object> profitandlossXfDetailMap=lnLoanProfitandlossXfDetailService.profitandlossXfDetail(lnLoanProfitandloss.getLoanProfitandlossId());
                        this.getRequest().setAttribute("profitandlossXfDetailMap", profitandlossXfDetailMap);
                    }
                    this.getRequest().setAttribute("lnLoanProfitandloss", lnLoanProfitandloss);
                }

                //调查基本信息权限
                if(lnLoan.getLoanStatusId()==3&&getLoginInfo().getUserId().equals(lnLoan.getSurveyUserId())){
                    request.setAttribute("write","1");
                }

            }
            return  SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("confirmLoanEdit",e);
            return ERROR;
        }
    }

    public void confirmLoan(){
        try{
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 落实贷款...");
            String strLoanId = request.getParameter("loanId");
            String nogoodRemark = request.getParameter("nogoodRemark");
            Integer loanId = Integer.parseInt(strLoanId.trim());

            //插入历史记录
            LnOpHistory lnOpHistory = new LnOpHistory();
            lnOpHistory.setUserId(this.getLoginInfo().getUserId());
            lnOpHistory.setOpHistoryDate(new Date());
            lnOpHistory.setBeforeStatusId(6);
            lnOpHistory.setAfterStatusId(6);
            lnOpHistory.setContent("贷款落实");
            lnOpHistory.setRemark(nogoodRemark);
            lnOpHistory.setLoanId(loanId);

            //更新贷款表为已落实
            Map<String,Object> paramMap = new HashMap<String, Object>();
            paramMap.put("loanId",loanId);
            paramMap.put("isCheckout",1);
            paramMap.put("checkoutDate",new Date());
            paramMap.put("checkoutUserId",this.getLoginInfo().getUserId());
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 开始落实贷款并插入历史记录表...");
            lnLoanService.dunLoan(paramMap,lnOpHistory);
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 落实贷款完成！");
        }catch (Exception e){
            logger.error("confirmLoan", e);
        }
    }

    public void hasLoanData(){
        try {
            //得到当前贷款相当的所有资料
            String strLoanId = request.getParameter("loanId");
            if (StringUtils.isNotEmpty(strLoanId)){
                PrintWriter out = this.getResponse().getWriter();
                Integer loanId = Integer.parseInt(strLoanId);
                Map<String,Object> photoMap = new HashMap<String, Object>();
                photoMap.put("loanId",loanId);
                photoMap.put("statistics",1);
                photoMap.put("eventId",LoanConstants.LOAN_DUN_EVENT);
                List<LoanData> loanDataList = customerDataService.getAllLoanDataById(photoMap);
                if (loanDataList != null && loanDataList.size() > 0){
                    //有资料，可以落实
                    out.print("true");
                }else{
                    out.print("false");
                }
            }
        }catch (Exception e){
            logger.error("hasLoanData", e);
        }
    }

    /**
     * 初始化信息
     */
    public void initParameter() {
        deptJson = deptService.getAllDeptJson();
        deptManagerJson = deptFacadeService.getInChargeOfDeptJson();
        getDeptAdminDeptJsonRemRoot(1);
    }

    /**
     * 机构管理员 (不 添加默认根节点) json树
     *
     * @return
     */
    public void getDeptAdminDeptJsonRemRoot(Integer userId) {
        try {

            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("userId", userId);
            Map<String, Integer> admintree = new HashMap<String, Integer>();// 机构管理员管理机构
            Map<String, Integer> businesstree = new HashMap<String, Integer>();// 业务主管管理机构
            List<SysDeptAuth> sysDeptAuthList = sysDeptAuthService
                    .getSysDeptAuthList(parameters);
            int i = 0;
            for (SysDeptAuth sysDeptAuth : sysDeptAuthList) {
                if (sysDeptAuth.getRoleId().equals(2)) {
                    admintree.put("" + sysDeptAuth.getDeptId(),
                            sysDeptAuth.getDeptId());
                }
                if (sysDeptAuth.getRoleId().equals(3)) {
                    businesstree.put("" + sysDeptAuth.getDeptId(),
                            sysDeptAuth.getDeptId());
                }
            }
            List<SysDept> depts = deptService.getUserJsonTree();
            Set<Integer> deptNameList = new HashSet<Integer>();
            for (SysDept sysDept : depts) {
                deptNameList.add(sysDept.getDeptId());
                if (!deptNameList.contains(sysDept.getDeptParentId()))
                    i++;// 控制节点展开
            }

            Map<String, Object> map = new HashMap<String, Object>();
            Map<String, Object> Vmap = new HashMap<String, Object>();
            JSONArray jsonArray = new JSONArray();
            JSONArray adminArray = new JSONArray();
            if (depts.size() > 0) {
                for (SysDept dept : depts) {
                    map.put("id", dept.getDeptId());
                    Vmap.put("id", dept.getDeptId());
                    if (!deptNameList.contains(dept.getDeptParentId())) {
                        map.put("pId", 2);
                        Vmap.put("pId", 2);
                        if (i == 1) {
                            map.put("open", true);
                            Vmap.put("open", true);
                        }
                    } else {
                        map.put("pId", dept.getDeptParentId());
                        Vmap.put("pId", dept.getDeptParentId());
                        map.put("open", false);
                        Vmap.put("open", false);
                    }
                    map.put("name", dept.getDeptName());
                    Vmap.put("name", dept.getDeptName());
                    if (admintree.get("" + dept.getDeptId()) != null) {
                        map.put("bool", true);
                    } else {
                        map.put("bool", false);
                    }
                    if (businesstree.get("" + dept.getDeptId()) != null) {
                        Vmap.put("bool", true);
                    } else {
                        Vmap.put("bool", false);
                    }
                    adminArray.add(map);
                    jsonArray.add(Vmap);
                }
            }
            request.setAttribute("adminJsonArray", adminArray);// 机构管理员 树
            request.setAttribute("businessJsonArray", jsonArray);// 业务主管 树
        } catch (Exception e) {
        }
    }




    public void setLnLoanService(LnLoanService lnLoanService) {
        this.lnLoanService = lnLoanService;
    }

    public LnLoanService getLnLoanService() {
        return lnLoanService;
    }

    public LnRepaymentPlanService getLnRepaymentPlanService() {
        return lnRepaymentPlanService;
    }

    public void setLnRepaymentPlanService(LnRepaymentPlanService lnRepaymentPlanService) {
        this.lnRepaymentPlanService = lnRepaymentPlanService;
    }

    public LnDunLogService getLnDunLogService() {
        return lnDunLogService;
    }

    public void setLnDunLogService(LnDunLogService lnDunLogService) {
        this.lnDunLogService = lnDunLogService;
    }

    public List<LnLoanCoBorrowerBean> getLoanCoBorrowerList() {
        return loanCoBorrowerList;
    }

    public void setLoanCoBorrowerList(List<LnLoanCoBorrowerBean> loanCoBorrowerList) {
        this.loanCoBorrowerList = loanCoBorrowerList;
    }

    public List<LnLoanGuarantorBean> getLoanGuarantorList() {
        return loanGuarantorList;
    }

    public void setLoanGuarantorList(List<LnLoanGuarantorBean> loanGuarantorList) {
        this.loanGuarantorList = loanGuarantorList;
    }

    public List<LnCreditHistory> getLnCreditHistoryList() {
        return lnCreditHistoryList;
    }

    public void setLnCreditHistoryList(List<LnCreditHistory> lnCreditHistoryList) {
        this.lnCreditHistoryList = lnCreditHistoryList;
    }
    /**
     * 获取贷款所需要的下拉框信息
     *
     * @param param
     * @return
     */
    private HashMap<String,Object> buildLoanCheckBoxMessage(String...param){

        HashMap<String,Object> paramMap = new HashMap<String,Object>();
        HashMap<String,Object> resultMap = new HashMap<String,Object>();
        for (int i = 0; i < param.length; i++) {
            paramMap.put("dictionaryName", param[i]);
            List<LnLoanInfoDictionary> lnLoanInfoDictionaryList = lnLoanInfoDictionaryService.selectLoanInfoDictionaryList(paramMap);
            resultMap.put(param[i], lnLoanInfoDictionaryList);
            paramMap.remove("dictionaryKey");
        }
        return resultMap;
    }

    public LnLoanDetailService getLnLoanDetailService() {
        return lnLoanDetailService;
    }

    public void setLnLoanDetailService(LnLoanDetailService lnLoanDetailService) {
        this.lnLoanDetailService = lnLoanDetailService;
    }

    public LnLoanInfoService getLnLoanInfoService() {
        return lnLoanInfoService;
    }

    public void setLnLoanInfoService(LnLoanInfoService lnLoanInfoService) {
        this.lnLoanInfoService = lnLoanInfoService;
    }

    public LnLoanInfoDictionaryService getLnLoanInfoDictionaryService() {
        return lnLoanInfoDictionaryService;
    }

    public void setLnLoanInfoDictionaryService(
            LnLoanInfoDictionaryService lnLoanInfoDictionaryService) {
        this.lnLoanInfoDictionaryService = lnLoanInfoDictionaryService;
    }

    public LnCreditHistoryService getLnCreditHistoryService() {
        return lnCreditHistoryService;
    }

    public void setLnCreditHistoryService(
            LnCreditHistoryService lnCreditHistoryService) {
        this.lnCreditHistoryService = lnCreditHistoryService;
    }



    public LnOpHistoryService getLnOpHistoryService() {
        return lnOpHistoryService;
    }

    public void setLnOpHistoryService(LnOpHistoryService lnOpHistoryService) {
        this.lnOpHistoryService = lnOpHistoryService;
    }

    public LnLoanMonitorService getLnLoanMonitorService() {
        return lnLoanMonitorService;
    }

    public void setLnLoanMonitorService(LnLoanMonitorService lnLoanMonitorService) {
        this.lnLoanMonitorService = lnLoanMonitorService;
    }

    public List<LnLoanMonitor> getLnLoanMonitorList() {
        return lnLoanMonitorList;
    }

    public void setLnLoanMonitorList(List<LnLoanMonitor> lnLoanMonitorList) {
        this.lnLoanMonitorList = lnLoanMonitorList;
    }

    public BigDecimal getLoanRemaining() {
        return loanRemaining;
    }

    public void setLoanRemaining(BigDecimal loanRemaining) {
        this.loanRemaining = loanRemaining;
    }

    public SysUploadFileService getSysUploadFileService() {
        return sysUploadFileService;
    }

    public void setSysUploadFileService(SysUploadFileService sysUploadFileService) {
        this.sysUploadFileService = sysUploadFileService;
    }

    public SysTeamUserService getSysTeamUserService() {
        return sysTeamUserService;
    }

    public void setSysTeamUserService(SysTeamUserService sysTeamUserService) {
        this.sysTeamUserService = sysTeamUserService;
    }

}
