package com.banger.mobile.webapp.action.loan;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.Enum.contract.EnumContract;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.base.customer.BaseFamilyName;
import com.banger.mobile.domain.model.contract.*;
import com.banger.mobile.domain.model.customer.*;
import com.banger.mobile.domain.model.data.LoanData;
import com.banger.mobile.domain.model.loan.*;
import com.banger.mobile.domain.model.pad.PadLoanInfo;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.data.CustomerDataService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.impl.customer.CrmCustomerServiceImpl;
import com.banger.mobile.facade.loan.*;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.util.FileUtil;
import com.banger.mobile.util.ServerRealPathUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import ognl.IntHashMap;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.impl.common.ConcurrentReaderHashMap;


import javax.servlet.ServletOutputStream;
import javax.swing.text.TableView;
import javax.swing.text.html.HTMLDocument;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.text.DateFormat;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ouyl
 * @version $Id: NotMakeLoanAction.java v 0.1 ${} 上午11:42 ouyl Exp $
 *
 * 未放贷贷款Action
 */

public class NotMakeLoanAction extends BaseAction {

    private static Logger logger = Logger.getLogger(NotMakeLoanAction.class);

    private LnLoanService lnLoanService;
    private LnLoanInfoService lnLoanInfoService;
    private String customer;
    private Date approvalStartDate;
    private Date approvalEndDate;
    private Date startCreateDate;
    private Date endCreateDate;
    private Integer loanType;
    private LnLoanTypeService lnLoanTypeService;
    private DeptFacadeService deptFacadeService;
    private LnLoanInfoDictionaryService lnLoanInfoDictionaryService;
    private int develop;
    private SysUserService sysUserService;
    private List<LnLoanGuarantorBean> loanGuarantorList;
    private List<LnPledge> lnPledgeList;

    private LnLoanGuarantorService lnLoanGuarantorService;
    private LnLoanPledgeService lnLoanPledgeService;
    private ContCommMain contCommMain;
    private ContTopMain contTopMain;
    private ContCardMain contCardMain;
    private Iou iou ;
    private LnLoanDetailService  lnLoanDetailService;
    private ContCommGuaranty contCommGuaranty;
    private ContTopMortgage contTopMortgage;
    private ContTopPledge contTopPledge;


    private ContTopGuaranty contTopGuaranty;
    private ContCommMortgage contCommMortgage;
    private ContCommPledge contCommPledge;
    private ContCardGuaranty contCardGuaranty;
    private ContCardMortgage contCardMortgage;


    private LnRepaymentPlanService LnRepaymentPlanService;
    private LnLoanContractService lnLoanContractService;
    private CrmCustomerService crmCustomerService; // 客户Service

    private CustomerDataService customerDataService;


    public Iou getIou() {
        return iou;
    }

    public void setIou(Iou iou) {
        this.iou = iou;
    }

    public ContCardMortgage getContCardMortgage() {
        return contCardMortgage;
    }

    public void setContCardMortgage(ContCardMortgage contCardMortgage) {
        this.contCardMortgage = contCardMortgage;
    }

    public ContCardGuaranty getContCardGuaranty() {
        return contCardGuaranty;
    }

    public void setContCardGuaranty(ContCardGuaranty contCardGuaranty) {
        this.contCardGuaranty = contCardGuaranty;
    }

    public ContCardMain getContCardMain() {
        return contCardMain;
    }

    public void setContCardMain(ContCardMain contCardMain) {
        this.contCardMain = contCardMain;
    }

    public ContTopPledge getContTopPledge() {
        return contTopPledge;
    }

    public void setContTopPledge(ContTopPledge contTopPledge) {
        this.contTopPledge = contTopPledge;
    }

    public ContTopMortgage getContTopMortgage() {
        return contTopMortgage;
    }

    public void setContTopMortgage(ContTopMortgage contTopMortgage) {
        this.contTopMortgage = contTopMortgage;
    }

    public ContTopGuaranty getContTopGuaranty() {
        return contTopGuaranty;
    }

    public void setContTopGuaranty(ContTopGuaranty contTopGuaranty) {
        this.contTopGuaranty = contTopGuaranty;
    }

    public ContTopMain getContTopMain() {
        return contTopMain;
    }

    public void setContTopMain(ContTopMain contTopMain) {
        this.contTopMain = contTopMain;
    }

    public ContCommPledge getContCommPledge() {
        return contCommPledge;
    }

    public void setContCommPledge(ContCommPledge contCommPledge) {
        this.contCommPledge = contCommPledge;
    }

    public List<LnPledge> getLnPledgeList() {
        return lnPledgeList;
    }

    public void setLnPledgeList(List<LnPledge> lnPledgeList) {
        this.lnPledgeList = lnPledgeList;
    }

    public ContCommMortgage getContCommMortgage() {
        return contCommMortgage;
    }

    public void setContCommMortgage(ContCommMortgage contCommMortgage) {
        this.contCommMortgage = contCommMortgage;
    }

    public CustomerDataService getCustomerDataService() {
        return customerDataService;
    }

    public void setCustomerDataService(CustomerDataService customerDataService) {
        this.customerDataService = customerDataService;
    }

    public List<LnLoanGuarantorBean> getLoanGuarantorList() {
        return loanGuarantorList;
    }

    public void setLoanGuarantorList(List<LnLoanGuarantorBean> loanGuarantorList) {
        this.loanGuarantorList = loanGuarantorList;
    }

    public ContCommGuaranty getContCommGuaranty() {
        return contCommGuaranty;
    }

    public void setContCommGuaranty(ContCommGuaranty contCommGuaranty) {
        this.contCommGuaranty = contCommGuaranty;
    }

    public LnLoanDetailService getLnLoanDetailService() {
        return lnLoanDetailService;
    }

    public void setLnLoanDetailService(LnLoanDetailService lnLoanDetailService) {
        this.lnLoanDetailService = lnLoanDetailService;
    }

    public CrmCustomerService getCrmCustomerService() {
        return crmCustomerService;
    }

    public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
        this.crmCustomerService = crmCustomerService;
    }

    public LnLoanContractService getLnLoanContractService() {
        return lnLoanContractService;
    }

    public void setLnLoanContractService(LnLoanContractService lnLoanContractService) {
        this.lnLoanContractService = lnLoanContractService;
    }

    public LnRepaymentPlanService getLnRepaymentPlanService() {
        return LnRepaymentPlanService;
    }

    public void setLnRepaymentPlanService(LnRepaymentPlanService lnRepaymentPlanService) {
        LnRepaymentPlanService = lnRepaymentPlanService;
    }

    public ContCommMain getContCommMain() {
        return contCommMain;
    }

    public void setContCommMain(ContCommMain contCommMain) {
        this.contCommMain = contCommMain;
    }

    public LnLoanPledgeService getLnLoanPledgeService() {
        return lnLoanPledgeService;
    }

    public void setLnLoanPledgeService(LnLoanPledgeService lnLoanPledgeService) {
        this.lnLoanPledgeService = lnLoanPledgeService;
    }

    public LnLoanGuarantorService getLnLoanGuarantorService() {
        return lnLoanGuarantorService;
    }

    public void setLnLoanGuarantorService(LnLoanGuarantorService lnLoanGuarantorService) {
        this.lnLoanGuarantorService = lnLoanGuarantorService;
    }

    public SysUserService getSysUserService() {
        return sysUserService;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public LnLoanInfoService getLnLoanInfoService() {
        return lnLoanInfoService;
    }

    public void setLnLoanInfoService(LnLoanInfoService lnLoanInfoService) {
        this.lnLoanInfoService = lnLoanInfoService;
    }

    public String notMakeLoanList(){
        try{
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 进入放贷贷款菜单...");
            //贷款类型
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("dictionaryName", "DKLX");
            List<LnLoanInfoDictionary> typeList = lnLoanInfoDictionaryService
                    .selectLoanInfoDictionaryList(paramMap);


            Map<String, Object> conds = new HashMap<String, Object>();

            conds.put("lendUserId", this.getLoginInfo().getUserId());
            conds.put("loanStatusId", 5);   // 5表示未放贷
            PageUtil<LnLoan> dataList = lnLoanService.getMakeLoanPage(conds, this.getPage());
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 共搜索出"+dataList.getItems().size()+"条符合条件的放贷贷款");

            request.setAttribute("dataList", dataList.getItems());
            request.setAttribute("recordCount", dataList.getPage().getTotalRowsAmount());
            request.setAttribute("typeList", typeList);
            request.setAttribute("sysUserId",this.getLoginInfo().getUserId());

            return SUCCESS;
        }catch(Exception e){
            logger.error("notMakeLoanList", e);
            return ERROR;
        }
    }


    public String notMakeLoanListQuery(){
        try{
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 按条件搜索放贷贷款...");
            //列表数据
            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("lendUserId", this.getLoginInfo().getUserId());
            conds.put("loanStatusId", 5);   // 5表示未放贷
            conds.put("cusName", customer);
            conds.put("loanTypeId", loanType);
            conds.put("approvalStartDate", approvalStartDate);
            approvalEndDate = lnLoanService.addSecondsForDate(approvalEndDate,59);
            conds.put("approvalEndDate", approvalEndDate);
            conds.put("applyStartDate", startCreateDate);
            endCreateDate = lnLoanService.addSecondsForDate(endCreateDate,59);
            conds.put("applyEndDate", endCreateDate);
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 搜索条件，客户："+customer+",贷款类型："+loanType+",审批时间："+approvalStartDate+"——"+approvalEndDate+",提交时间："
                    +startCreateDate+"——"+endCreateDate);
            PageUtil<LnLoan> dataList = lnLoanService.getMakeLoanPage(conds, this.getPage());
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 总共搜索出"+dataList.getItems().size()+"条符合条件的放贷贷款");
            request.setAttribute("dataList", dataList.getItems());
            request.setAttribute("recordCount", dataList.getPage().getTotalRowsAmount());
            request.setAttribute("sysUserId",this.getLoginInfo().getUserId());

            return SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
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

    public String notMakeLoanEdit(){
        try{
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 进入放贷贷款编辑界面...");
            String strLoanId = request.getParameter("loanId");
            Integer loanId = Integer.parseInt(strLoanId.trim());
            request.setAttribute("loanId",loanId);
            request.setAttribute("isEdit", "false");
            request.setAttribute("isSurvey", "onlyShow");
            request.setAttribute("isApprove", "onlyShow");

            return SUCCESS;
        }catch (Exception e){
            logger.error("addLendRemark", e);
            return ERROR;
        }
    }

    public String contractTypePage(){
        try{
            HashMap<String,Object> checkBoxMessageMap = buildLoanCheckBoxMessage("HTLX");
            request.setAttribute("checkBoxMessage", checkBoxMessageMap);
            String strLoanId = request.getParameter("loanId");
            Integer loanId = Integer.parseInt(strLoanId.trim());

            LnLoanContract lnLoanContract = lnLoanContractService.getLnLoanContractById(loanId);
            if(null!=lnLoanContract&&StringUtils.isNotBlank(lnLoanContract.getContractType())){
                request.setAttribute("contractType",lnLoanContract.getContractType());
            }

            request.setAttribute("loanId",loanId);
            return SUCCESS;
        }catch (Exception e){
            logger.error("contractTypePage", e);
            return ERROR;
        }
    }

    public String downContractPage(){
        try{
            HashMap<String,Object> checkBoxMessageMap = buildLoanCheckBoxMessage("JXFS","JXR","HBFS","AYHKFS","ZYJJFS");
            request.setAttribute("checkBoxMessage", checkBoxMessageMap);
            String strLoanId = request.getParameter("loanId");
            Integer loanId = Integer.parseInt(strLoanId.trim());
            request.setAttribute("loanId",loanId);

            String code = request.getParameter("code");
            request.setAttribute("code",code);

            LnLoanContract lnLoanContract = lnLoanContractService.getLnLoanContractById(loanId);

            //普通 主合同
            if(code.equals(EnumContract.CONT_COMMON_MAIN.getCode())){
                if(null!=lnLoanContract&&StringUtils.isNotBlank(lnLoanContract.getContCommMain())){
                    contCommMain = (ContCommMain)getObject(lnLoanContract.getContCommMain(),new ContCommMain());
                    request.setAttribute("readonly","true");
                }else{
                    //查询贷款信息
                    Map<String,Object> param = new HashMap<String, Object>();
                    param.put("loanId", loanId);
                    LnLoanInfo lnLoanInfo = lnLoanInfoService.selectLoanInfoList(param).get(0);
                    lnLoanInfo.setAppGuarantorWayId(lnLoanInfoDictionaryService.getDictionaryValue("DBFS",lnLoanInfo.getAppGuarantorWayId()));
                    List<LnRepaymentPlan> repaymentPlans = LnRepaymentPlanService.queryLnRepaymentPlan(loanId); //还款计划
                    contCommMain = new ContCommMain(lnLoanInfo,repaymentPlans);
                }
                request.setAttribute("contCommMain",contCommMain);
             }
            //最高额主合同
            if(code.equals(EnumContract.CONT_TOP_MAIN.getCode())){
                if(null!=lnLoanContract&&StringUtils.isNotBlank(lnLoanContract.getContTopMain())){
                    contTopMain = (ContTopMain)getObject(lnLoanContract.getContTopMain(),new ContTopMain());
                    request.setAttribute("readonly","true");
                }else{
                    //查询贷款信息
                    Map<String,Object> param = new HashMap<String, Object>();
                    param.put("loanId", loanId);
                    LnLoanInfo lnLoanInfo = lnLoanInfoService.selectLoanInfoList(param).get(0);
                    lnLoanInfo.setAppGuarantorWayId(lnLoanInfoDictionaryService.getDictionaryValue("DBFS",lnLoanInfo.getAppGuarantorWayId()));
                    List<LnRepaymentPlan> repaymentPlans = LnRepaymentPlanService.queryLnRepaymentPlan(loanId); //还款计划
                    contTopMain = new ContTopMain(lnLoanInfo,repaymentPlans);
                }
                request.setAttribute("contTopMain",contTopMain);
             }
            // CONT_CARD_MAIN("31","3","最高额借款合同(卡贷宝)" ,"contCardMain.doc"),  ContCardMain
            if(code.equals(EnumContract.CONT_CARD_MAIN.getCode())){
                if(null!=lnLoanContract&&StringUtils.isNotBlank(lnLoanContract.getContCardMain())){
                    contCardMain = (ContCardMain)getObject(lnLoanContract.getContCardMain(),new ContCardMain());
                    request.setAttribute("readonly","true");
                }else{
                    //查询贷款信息
                    Map<String,Object> param = new HashMap<String, Object>();
                    param.put("loanId", loanId);
                    LnLoanInfo lnLoanInfo = lnLoanInfoService.selectLoanInfoList(param).get(0);
                    lnLoanInfo.setAppGuarantorWayId(lnLoanInfoDictionaryService.getDictionaryValue("DBFS",lnLoanInfo.getAppGuarantorWayId()));
                    List<LnRepaymentPlan> repaymentPlans = LnRepaymentPlanService.queryLnRepaymentPlan(loanId); //还款计划
                    contCardMain = new ContCardMain(lnLoanInfo,repaymentPlans);
                }
                request.setAttribute("contCardMain",contCardMain);
            }

            // 借据   ContCommIou  contCommIou
            if(code.equals("15")){
                if(null!=lnLoanContract&&StringUtils.isNotBlank(lnLoanContract.getIou())){
                    Map<String,Object> param = new HashMap<String, Object>();
                    param.put("loanId", loanId);
                    LnLoanInfo lnLoanInfo = lnLoanInfoService.selectLoanInfoList(param).get(0);
                    List<LnRepaymentPlan> repaymentPlans = LnRepaymentPlanService.queryLnRepaymentPlan(loanId); //还款计划
                    iou = (Iou)getObject(lnLoanContract.getIou(),new Iou());
                    iou.setLoanBeginTime(lnLoanInfo.getRegisterLoanDate());
                  if(repaymentPlans.size()!=0){

                      iou.setLoanEndTime(repaymentPlans.get(repaymentPlans.size()-1).getPlanDate());
                  }
                    request.setAttribute("readonly", "true");
                }else{
                    //查询贷款信息
                    Map<String,Object> param = new HashMap<String, Object>();
                    param.put("loanId", loanId);
                    LnLoanInfo lnLoanInfo = lnLoanInfoService.selectLoanInfoList(param).get(0);
                    List<LnRepaymentPlan> repaymentPlans = LnRepaymentPlanService.queryLnRepaymentPlan(loanId); //还款计划
                    iou = new Iou(lnLoanInfo,repaymentPlans);
                }
                request.setAttribute("iou",iou);
            }


             //TODO 其他合同内容

            //保证担保合同
            if(code.equals(EnumContract.CONT_COMMON_GUARANTY.getCode())){
                if(null!=lnLoanContract&&StringUtils.isNotBlank(lnLoanContract.getContCommGuaranty())){
                    contCommGuaranty = (ContCommGuaranty)getObject(lnLoanContract.getContCommGuaranty(),new ContCommGuaranty());
                    request.setAttribute("readonly","true");
                }else{
                    //查询担保人信息
                    Map<String,Object> photoMap = new HashMap<String, Object>();
                    photoMap.put("loanId",loanId);
                    photoMap.put("statistics",1);
                    LnLoanInfo lnLoanInfo = lnLoanInfoService.selectLoanInfoList(photoMap).get(0);
                    List<LoanData> list = customerDataService.getAllLoanDataById(photoMap);
                    loanGuarantorList = lnLoanDetailService.getLoanGuList(loanId, list);
                    contCommGuaranty = new ContCommGuaranty(lnLoanInfo,loanGuarantorList);
                }
                request.setAttribute("contCommGuaranty",contCommGuaranty);
            }
            //最高额担保合同
            if(code.equals(EnumContract.CONT_TOP_GUARANTY.getCode())){
                if(null!=lnLoanContract&&StringUtils.isNotBlank(lnLoanContract.getContTopGuaranty())){
                    contTopGuaranty = (ContTopGuaranty)getObject(lnLoanContract.getContTopGuaranty(),new ContTopGuaranty());
                    request.setAttribute("readonly","true");
                }else{
                    //查询担保人信息
                    Map<String,Object> photoMap = new HashMap<String, Object>();
                    photoMap.put("loanId",loanId);
                    photoMap.put("statistics",1);
                    LnLoanInfo lnLoanInfo = lnLoanInfoService.selectLoanInfoList(photoMap).get(0);
                    List<LoanData> list = customerDataService.getAllLoanDataById(photoMap);
                    loanGuarantorList = lnLoanDetailService.getLoanGuList(loanId, list);
                    contTopGuaranty = new ContTopGuaranty(lnLoanInfo,loanGuarantorList);
                }
                request.setAttribute("contTopGuaranty",contTopGuaranty);
            }

            // CONT_CARD_GUARANTY("32","3","最高额保证合同(卡贷宝)" ,"contCardGuaranty.doc"),ContCardGuaranty
            if(code.equals(EnumContract.CONT_CARD_GUARANTY.getCode())){
                if(null!=lnLoanContract&&StringUtils.isNotBlank(lnLoanContract.getContCardGuaranty())){
                    contCardGuaranty = (ContCardGuaranty)getObject(lnLoanContract.getContCardGuaranty(),new ContCardGuaranty());
                    request.setAttribute("readonly","true");
                }else{
                    //查询担保人信息
                    Map<String,Object> photoMap = new HashMap<String, Object>();
                    photoMap.put("loanId",loanId);
                    photoMap.put("statistics",1);
                    LnLoanInfo lnLoanInfo = lnLoanInfoService.selectLoanInfoList(photoMap).get(0);
                    List<LoanData> list = customerDataService.getAllLoanDataById(photoMap);
                    loanGuarantorList = lnLoanDetailService.getLoanGuList(loanId, list);
                    contCardGuaranty = new ContCardGuaranty(lnLoanInfo,loanGuarantorList);
                }
                request.setAttribute("contCardGuaranty",contCardGuaranty);
            }
            //抵押合同
            if(code.equals(EnumContract.CONT_COMMON_MORTGAGE.getCode())){
                if(null!=lnLoanContract&&StringUtils.isNotBlank(lnLoanContract.getContCommMortgage())){
                    contCommMortgage = (ContCommMortgage)getObject(lnLoanContract.getContCommMortgage(),new ContCommMortgage());
                    request.setAttribute("readonly","true");
                }else{
                    //查询抵押
                    Map<String,Object> photoMap = new HashMap<String, Object>();
                    photoMap.put("loanId",loanId);
                    LnLoanInfo lnLoanInfo = lnLoanInfoService.selectLoanInfoList(photoMap).get(0);
                    lnPledgeList = lnLoanPledgeService.getLnLoanPledgeByLoanId(loanId);//抵押人
                    contCommMortgage = new ContCommMortgage(lnLoanInfo,lnPledgeList);
                }
                request.setAttribute("contCommMortgage",contCommMortgage);
            }
            //最高额抵押合同
            if(code.equals(EnumContract.CONT_TOP_MORTGAGE.getCode())){
                if(null!=lnLoanContract&&StringUtils.isNotBlank(lnLoanContract.getContTopMortgage())){
                    contTopMortgage = (ContTopMortgage)getObject(lnLoanContract.getContTopMortgage(),new ContTopMortgage());
                    request.setAttribute("readonly","true");
                }else{
                    //查询抵押
                    Map<String,Object> photoMap = new HashMap<String, Object>();
                    photoMap.put("loanId",loanId);
                    LnLoanInfo lnLoanInfo = lnLoanInfoService.selectLoanInfoList(photoMap).get(0);
                    lnPledgeList = lnLoanPledgeService.getLnLoanPledgeByLoanId(loanId);//抵押人
                    contTopMortgage = new ContTopMortgage(lnLoanInfo,lnPledgeList);
                }
                request.setAttribute("contTopMortgage",contTopMortgage);
            }
            // CONT_CARD_MORTGAGE("33","3","最高额抵押担保合同(卡贷宝)" ,"contCardMortgage.doc"),  ContCardMortgage
            if(code.equals(EnumContract.CONT_CARD_MORTGAGE.getCode())){
                if(null!=lnLoanContract&&StringUtils.isNotBlank(lnLoanContract.getContCardMortgage())){
                    contCardMortgage = (ContCardMortgage)getObject(lnLoanContract.getContCardMortgage(),new ContCardMortgage());
                    request.setAttribute("readonly","true");
                }else{
                    //查询抵押
                    Map<String,Object> photoMap = new HashMap<String, Object>();
                    photoMap.put("loanId",loanId);
                    LnLoanInfo lnLoanInfo = lnLoanInfoService.selectLoanInfoList(photoMap).get(0);
                    lnPledgeList = lnLoanPledgeService.getLnLoanPledgeByLoanId(loanId);//抵押人
                    contCardMortgage = new ContCardMortgage(lnLoanInfo,lnPledgeList);
                }
                request.setAttribute("contCardMortgage",contCardMortgage);
            }

            //质押合同
            if(code.equals(EnumContract.CONT_COMMON_PLEDGE.getCode())){
                if(null!=lnLoanContract&&StringUtils.isNotBlank(lnLoanContract.getContCommPledge())){
                    contCommPledge = (ContCommPledge)getObject(lnLoanContract.getContCommPledge(),new ContCommPledge());
                    request.setAttribute("readonly","true");
                }else{
                    //查询抵押
                    Map<String,Object> photoMap = new HashMap<String, Object>();
                    photoMap.put("loanId",loanId);
                    LnLoanInfo lnLoanInfo = lnLoanInfoService.selectLoanInfoList(photoMap).get(0);
                    lnPledgeList = lnLoanPledgeService.getLnLoanPledgeByLoanId(loanId);//抵押人
                    contCommPledge = new ContCommPledge(lnLoanInfo,lnPledgeList);
                }
                request.setAttribute("contCommPledge",contCommPledge);
            }
            //  CONT_TOP_PLEDGE("24","2","最高额质押担保合同" ,"contTopPledge.doc"), ContTopPledge
            if(code.equals(EnumContract.CONT_TOP_PLEDGE.getCode())){
                if(null!=lnLoanContract&&StringUtils.isNotBlank(lnLoanContract.getContTopPledge())){
                    contTopPledge = (ContTopPledge)getObject(lnLoanContract.getContTopPledge(),new ContTopPledge());
                    request.setAttribute("readonly","true");
                }else{
                    //查询抵押
                    Map<String,Object> photoMap = new HashMap<String, Object>();
                    photoMap.put("loanId",loanId);
                    LnLoanInfo lnLoanInfo = lnLoanInfoService.selectLoanInfoList(photoMap).get(0);
                    lnPledgeList = lnLoanPledgeService.getLnLoanPledgeByLoanId(loanId);//抵押人
                    contTopPledge = new ContTopPledge(lnLoanInfo,lnPledgeList);
                }
                request.setAttribute("contTopPledge",contTopPledge);
            }
            request.setAttribute("contName", EnumContract.getEnumByCode(code).getFileName_CN());
            return SUCCESS;
        }catch (Exception e){
            logger.error("downContractPage", e);
            return ERROR;
        }
    }

    /**
     * 保存合同内容
     */
    public void saveContract() {
        try {
            logger.info("保存合同内容...");
            //贷款id
            String loanId =  request.getParameter("loanId");
            String code =  request.getParameter("code");

            LnLoanContract lnLoanContract = lnLoanContractService.getLnLoanContractById(Integer.valueOf(loanId));

            List<Integer> gurantorList = lnLoanGuarantorService.getCusIdListByLoanId(Integer.valueOf(loanId));
            List<GuarantorInfo> guarantorInfoList = new ArrayList<GuarantorInfo>();
            for(Integer cusId:gurantorList){
                CrmCustomer crmCustomer = crmCustomerService.getCustomerInfo(cusId);
                GuarantorInfo guarantorInfo = new GuarantorInfo();
                guarantorInfo.setGuarantorName(crmCustomer.getCustomerName());
                guarantorInfo.setGuarantorCreditNo(crmCustomer.getIdCard());
                guarantorInfo.setGuarantorAddress(crmCustomer.getLivingAddress());
                guarantorInfoList.add(guarantorInfo);
            }

            if(null!=lnLoanContract){ // 修改
                lnLoanContract.setUpdateUser(this.getLoginInfo().getUserId());
                lnLoanContract.setUpdateDate(new Date());
                // 普通主合同
                if(code.equals(EnumContract.CONT_COMMON_MAIN.getCode())){
                    if(null!=contCommMain){
                        lnLoanContract.setContCommMain( getJsonString(contCommMain));
                    }
                    lnLoanContract.setCommMainCount(null!=lnLoanContract.getCommMainCount()?(lnLoanContract.getCommMainCount()+1):1);
                }
                // CONT_TOP_MAIN("21","2","最高额借款合同（个人业务）" ,"contTopMain.doc"),
                if(code.equals(EnumContract.CONT_TOP_MAIN.getCode())){
                    if(null!=contTopMain){
                        lnLoanContract.setContTopMain(getJsonString(contTopMain));
                    }
                   lnLoanContract.setTopMainCount(null != lnLoanContract.getTopMainCount() ? (lnLoanContract.getTopMainCount() + 1) : 1);
                }
                //  CONT_CARD_MAIN("31","3","最高额借款合同(卡贷宝)" ,"contCardMain.doc"),ContCardMain
                if(code.equals(EnumContract.CONT_CARD_MAIN.getCode())){
                    if(null!=contCardMain){
                        lnLoanContract.setContCardMain(getJsonString(contCardMain));
                    }
                   lnLoanContract.setCardMainCount(null != lnLoanContract.getCardMainCount() ? (lnLoanContract.getCardMainCount() + 1) : 1);
                }
                //借据  ContCommIou   contCommIou
                if(code.equals("15")){
                 /*   String repaymentInfoList = request.getParameter("repaymentInfoList");*/
                 /*   if(StringUtils.isNotBlank(repaymentInfoList) &&null!=iou){
                       try{
                           JSONArray ja = JSONArray.fromObject(repaymentInfoList);
                           iou.setRepaymentInfoList(JSONArray.toList(ja,new RepaymentInfo(),new JsonConfig()));
                       }catch (Exception e){
                           e.printStackTrace();
                       }
                        if(null!=iou){
                            lnLoanContract.setIou(getJsonString(iou));
                        }
                       // lnLoanContract.setIou(null != lnLoanContract.getIou() ? (lnLoanContract.getIou() + 1) : "1");

                    }*/
                    if(null!=iou){
                        lnLoanContract.setIou( getJsonString(iou));
                    }


                }
                //TODO 其他合同内容
                //担保合同
                if(code.equals(EnumContract.CONT_COMMON_GUARANTY.getCode())){
//                    String guarantorInfos = request.getParameter("guarantorInfoList");
//                    if(StringUtils.isNotBlank(guarantorInfos) &&null!=contCommGuaranty){
//                        try {
//                            JSONArray ja = JSONArray.fromObject(guarantorInfos);
//                            contCommGuaranty.setGuarantorInfoList( JSONArray.toList(ja,new GuarantorInfo(),new JsonConfig()));
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//                    }
                    contCommGuaranty.setGuarantorInfoList(guarantorInfoList);
                    if(null!=contCommGuaranty){
                        lnLoanContract.setContCommGuaranty( getJsonString(contCommGuaranty));
                    }
                    lnLoanContract.setCommGuarantyCount(null != lnLoanContract.getCommGuarantyCount() ? (lnLoanContract.getCommGuarantyCount() + 1) : 1);
                }


                //担保最高额合同
                if(code.equals(EnumContract.CONT_TOP_GUARANTY.getCode())){
//                    String guarantorInfoList = request.getParameter("guarantorInfoList");
//                    if(StringUtils.isNotBlank(guarantorInfoList)  && null!=contTopGuaranty){
//                        try {
//                            JSONArray ja = JSONArray.fromObject(guarantorInfoList);
//                            contTopGuaranty.setGuarantorInfoList( JSONArray.toList(ja,new GuarantorInfo(),new JsonConfig()));
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//                    }
                    contTopGuaranty.setGuarantorInfoList(guarantorInfoList);
                    if(null!=contTopGuaranty){
                        lnLoanContract.setContTopGuaranty(getJsonString(contTopGuaranty));
                    }
                    lnLoanContract.setTopGuarantyCount(null != lnLoanContract.getTopGuarantyCount() ? (lnLoanContract.getTopGuarantyCount() + 1) : 1);
                }
                // CONT_CARD_GUARANTY("32","3","最高额保证合同(卡贷宝)" ,"contCardGuaranty.doc"),  ContCardGuaranty
                if(code.equals(EnumContract.CONT_CARD_GUARANTY.getCode())){
//                    String guarantorInfoList = request.getParameter("guarantorInfoList");
//                    if(StringUtils.isNotBlank(guarantorInfoList)&&null!=contCardGuaranty){
//                        try {
//                            JSONArray ja = JSONArray.fromObject(guarantorInfoList);
//                            contCardGuaranty.setGuarantorInfoList( JSONArray.toList(ja,new GuarantorInfo(),new JsonConfig()));
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//                    }
                    contCommGuaranty.setGuarantorInfoList(guarantorInfoList);
                    if(null!=contCardGuaranty){
                        lnLoanContract.setContCardGuaranty(getJsonString(contCardGuaranty));
                    }
                    lnLoanContract.setCardGuarantyCount(null != lnLoanContract.getCardGuarantyCount() ? (lnLoanContract.getCardGuarantyCount() + 1) : 1);
                }


                //抵押合同    CONT_COMMON_MORTGAGE("13","1","抵押担保合同" ,"contCommMortgage.doc"),
                if(code.equals(EnumContract.CONT_COMMON_MORTGAGE.getCode())){
                    String mortgagorInfoList = request.getParameter("mortgagorInfoList");
                    String mortgageGoodsInfoList = request.getParameter("mortgageGoodsInfoList");
                    if(StringUtils.isNotBlank(mortgagorInfoList) && StringUtils.isNotBlank(mortgageGoodsInfoList)){
                        try {
                            JSONArray ja = JSONArray.fromObject(mortgagorInfoList);
                            JSONArray jb = JSONArray.fromObject(mortgageGoodsInfoList);
                           if(null!=contCommMortgage){
                               contCommMortgage.setMortgagorInfoList(JSONArray.toList(ja, new MortgagorInfo(), new JsonConfig()));
                               contCommMortgage.setMortgageGoodsInfoList(JSONArray.toList(jb, new MortgageGoodsInfo(), new JsonConfig()));
                           }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    if(null!=contCommMortgage){
                        lnLoanContract.setContCommMortgage(getJsonString(contCommMortgage));
                    }
                    lnLoanContract.setCommMortgageCount(null != lnLoanContract.getCommMortgageCount() ? (lnLoanContract.getCommMortgageCount() + 1) : 1);
                }

                // CONT_TOP_MORTGAGE("23","2","最高额抵押担保合同" ,"contTopMortgage.doc"),ContTopMortgage
                if(code.equals(EnumContract.CONT_TOP_MORTGAGE.getCode())){
                    String mortgagorInfoList = request.getParameter("mortgagorInfoList");
                    String mortgageGoodsInfoList = request.getParameter("mortgageGoodsInfoList");
                    if(StringUtils.isNotBlank(mortgagorInfoList) && StringUtils.isNotBlank(mortgageGoodsInfoList)&&null!=contTopMortgage){
                        try {
                            JSONArray ja = JSONArray.fromObject(mortgagorInfoList);
                            JSONArray jb = JSONArray.fromObject(mortgageGoodsInfoList);
                            contTopMortgage.setMortgagorInfoList(JSONArray.toList(ja, new MortgagorInfo(), new JsonConfig()));
                            contTopMortgage.setMortgageGoodsInfoList(JSONArray.toList(jb, new MortgageGoodsInfo(), new JsonConfig()));
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    if(null!=contTopMortgage){
                        lnLoanContract.setContTopMortgage(getJsonString(contTopMortgage));
                    }
                    lnLoanContract.setTopMortgageCount(null != lnLoanContract.getTopMortgageCount() ? (lnLoanContract.getTopMortgageCount() + 1) : 1);
                }

                //  CONT_CARD_MORTGAGE("33","3","最高额抵押担保合同(卡贷宝)" ,"contCardMortgage.doc"),  ContCardMortgage
                if(code.equals(EnumContract.CONT_CARD_MORTGAGE.getCode())){
                    String mortgagorInfoList = request.getParameter("mortgagorInfoList");
                    String mortgageGoodsInfoList = request.getParameter("mortgageGoodsInfoList");
                    if(StringUtils.isNotBlank(mortgagorInfoList) && StringUtils.isNotBlank(mortgageGoodsInfoList)&& null!=contCardMortgage){
                        try {
                            JSONArray ja = JSONArray.fromObject(mortgagorInfoList);
                            JSONArray jb = JSONArray.fromObject(mortgageGoodsInfoList);
                            contCardMortgage.setMortgagorInfoList(JSONArray.toList(ja, new MortgagorInfo(), new JsonConfig()));
                            contCardMortgage.setMortgageGoodsInfoList(JSONArray.toList(jb, new MortgageGoodsInfo(), new JsonConfig()));
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    if(null!=contCardMortgage){
                        lnLoanContract.setContCardMortgage(getJsonString(contCardMortgage));
                    }
                    lnLoanContract.setCardMortgageCount(null != lnLoanContract.getCardMortgageCount() ? (lnLoanContract.getCardMortgageCount() + 1) : 1);
                }
                // CONT_COMMON_PLEDGE("14","1","质押担保合同" ,"contCommPledge.doc"),
                if(code.equals(EnumContract.CONT_COMMON_PLEDGE.getCode())){
                    String pledgorInfoList = request.getParameter("pledgorInfoList");
                    if(StringUtils.isNotBlank(pledgorInfoList) && null!=contCommPledge ){
                        try {
                            JSONArray ja = JSONArray.fromObject(pledgorInfoList);
                            contCommPledge.setPledgorInfoList(JSONArray.toList(ja, new PledgorInfo(), new JsonConfig()));
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    if(null!=contCommPledge){
                        lnLoanContract.setContCommPledge(getJsonString(contCommPledge));
                    }
                    lnLoanContract.setCommPledgeCount(null != lnLoanContract.getCommPledgeCount() ? (lnLoanContract.getCommPledgeCount() + 1) : 1);
                }

                //  CONT_TOP_PLEDGE("24","2","最高额质押担保合同" ,"contTopPledge.doc"),ContTopPledge
                if(code.equals(EnumContract.CONT_TOP_PLEDGE.getCode())){
                    String pledgorInfoList = request.getParameter("pledgorInfoList");
                    if(StringUtils.isNotBlank(pledgorInfoList) ){
                        try {
                            JSONArray ja = JSONArray.fromObject(pledgorInfoList);
                            contTopPledge.setPledgorInfoList(JSONArray.toList(ja, new PledgorInfo(), new JsonConfig()));
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    if(null!=contTopPledge){
                        lnLoanContract.setContTopPledge(getJsonString(contTopPledge));
                    }
                    lnLoanContract.setTopPledgeCount(null != lnLoanContract.getTopPledgeCount() ? (lnLoanContract.getTopPledgeCount() + 1) : 1);
                }

                lnLoanContractService.updateLnLoanContractById(lnLoanContract);

            }else{//新建
                lnLoanContract = new LnLoanContract();
                lnLoanContract.setLoanId(Integer.valueOf(loanId));
                lnLoanContract.setContractType(EnumContract.getEnumByCode(code).getType());
                lnLoanContract.setCreateUser(this.getLoginInfo().getUserId());
                lnLoanContract.setCreateDate(new Date());
                // 普通主合同
                if(code.equals(EnumContract.CONT_COMMON_MAIN.getCode())){
                    lnLoanContract.setContCommMain(getJsonString(contCommMain));
                }

                if(code.equals(EnumContract.CONT_TOP_MAIN.getCode())){
                    lnLoanContract.setContTopMain(getJsonString(contTopMain));
                }
                // CONT_CARD_MAIN("31","3","最高额借款合同(卡贷宝)" ,"contCardMain.doc"),ContCardMain
                if(code.equals(EnumContract.CONT_CARD_MAIN.getCode())){
                    lnLoanContract.setContCardMain(getJsonString(contCardMain));
                }
                //借据  ContCommIou   contCommIou
                if(code.equals("15")){
                    if(null!=iou){
                        lnLoanContract.setIou( getJsonString(iou));
                    }
                  //lnLoanContract.setIou(null != lnLoanContract.getIou() ? (lnLoanContract.getIou() + 1):"1");
                }
                //担保人信息

                //TODO 其他合同内容
                //担保合同
                if(code.equals(EnumContract.CONT_COMMON_GUARANTY.getCode())){
//                    String guarantorInfos = request.getParameter("guarantorInfoList");
//                    if(StringUtils.isNotBlank(guarantorInfos)){
//                        try {
//                            JSONArray ja = JSONArray.fromObject(guarantorInfos);
//                            contCommGuaranty.setGuarantorInfoList( JSONArray.toList(ja,new GuarantorInfo(),new JsonConfig()));
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//                    }
                    contCommGuaranty.setGuarantorInfoList(guarantorInfoList);
                    if(null!=contCommGuaranty) {
                        lnLoanContract.setContCommGuaranty(getJsonString(contCommGuaranty));
                    }
                }

                //抵押合同
                if(code.equals(EnumContract.CONT_COMMON_MORTGAGE.getCode())){
                    String mortgagorInfoList = request.getParameter("mortgagorInfoList");
                    String mortgageGoodsInfoList = request.getParameter("mortgageGoodsInfoList");
                    if(StringUtils.isNotBlank(mortgagorInfoList)  && StringUtils.isNotBlank(mortgageGoodsInfoList)){
                        try {
                            JSONArray ja = JSONArray.fromObject(mortgagorInfoList);
                            JSONArray jb = JSONArray.fromObject(mortgageGoodsInfoList);

                          contCommMortgage.setMortgagorInfoList(JSONArray.toList(ja, new MortgagorInfo(), new JsonConfig()));
                          contCommMortgage.setMortgageGoodsInfoList(JSONArray.toList(jb, new MortgageGoodsInfo(), new JsonConfig()));
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    if(null!=contCommMortgage) {
                        lnLoanContract.setContCommMortgage(getJsonString(contCommMortgage));
                    }

                }

                //担保最高额合同
                if(code.equals(EnumContract.CONT_TOP_GUARANTY.getCode())){
//                    String guarantorInfoList = request.getParameter("guarantorInfoList");
//
//                    if(StringUtils.isNotBlank(guarantorInfoList)  ){
//                        try {
//                            JSONArray ja = JSONArray.fromObject(guarantorInfoList);
//                            contTopGuaranty.setGuarantorInfoList(JSONArray.toList(ja, new GuarantorInfo(), new JsonConfig()));
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//                    }
                    contTopGuaranty.setGuarantorInfoList(guarantorInfoList);
                    if(null!=contTopGuaranty) {
                        lnLoanContract.setContTopGuaranty(getJsonString(contTopGuaranty));
                    }
                }
                // CONT_CARD_GUARANTY("32","3","最高额保证合同(卡贷宝)" ,"contCardGuaranty.doc"),  ContCardGuaranty
                if(code.equals(EnumContract.CONT_CARD_GUARANTY.getCode())){
//                    String guarantorInfoList = request.getParameter("guarantorInfoList");
//                    if(StringUtils.isNotBlank(guarantorInfoList)  ){
//                        try {
//                            JSONArray ja = JSONArray.fromObject(guarantorInfoList);
//                            contCardGuaranty.setGuarantorInfoList(JSONArray.toList(ja, new GuarantorInfo(), new JsonConfig()));
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//                    }
                    contCardGuaranty.setGuarantorInfoList(guarantorInfoList);
                    if(null!=contCardGuaranty) {
                        lnLoanContract.setContCardGuaranty(getJsonString(contCardGuaranty));
                    }
                }
                //CONT_TOP_MORTGAGE("23","2","最高额抵押担保合同" ,"contTopMortgage.doc"),ContTopMortgage
                if(code.equals(EnumContract.CONT_TOP_MORTGAGE.getCode())){
                    String mortgagorInfoList = request.getParameter("mortgagorInfoList");
                    String mortgageGoodsInfoList = request.getParameter("mortgageGoodsInfoList");
                    if(StringUtils.isNotBlank(mortgagorInfoList)  && StringUtils.isNotBlank(mortgageGoodsInfoList)){
                        try {
                            JSONArray ja = JSONArray.fromObject(mortgagorInfoList);
                            JSONArray jb = JSONArray.fromObject(mortgageGoodsInfoList);

                            contTopMortgage.setMortgagorInfoList(JSONArray.toList(ja, new MortgagorInfo(), new JsonConfig()));
                            contTopMortgage.setMortgageGoodsInfoList(JSONArray.toList(jb, new MortgageGoodsInfo(), new JsonConfig()));
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    if(null!=contTopMortgage) {
                        lnLoanContract.setContTopMortgage(getJsonString(contTopMortgage));
                    }
                }


                // CONT_CARD_MORTGAGE("33","3","最高额抵押担保合同(卡贷宝)" ,"contCardMortgage.doc"), ContCardMortgage
                if(code.equals(EnumContract.CONT_CARD_MORTGAGE.getCode())){
                    String mortgagorInfoList = request.getParameter("mortgagorInfoList");
                    String mortgageGoodsInfoList = request.getParameter("mortgageGoodsInfoList");
                    if(StringUtils.isNotBlank(mortgagorInfoList)  && StringUtils.isNotBlank(mortgageGoodsInfoList)){
                        try {
                            JSONArray ja = JSONArray.fromObject(mortgagorInfoList);
                            JSONArray jb = JSONArray.fromObject(mortgageGoodsInfoList);

                            contCardMortgage.setMortgagorInfoList(JSONArray.toList(ja, new MortgagorInfo(), new JsonConfig()));
                            contCardMortgage.setMortgageGoodsInfoList(JSONArray.toList(jb, new MortgageGoodsInfo(), new JsonConfig()));
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    if(null!=contCardMortgage) {
                        lnLoanContract.setContCardMortgage(getJsonString(contCardMortgage));
                    }
                }
                //CONT_COMMON_PLEDGE("14","1","质押担保合同" ,"contCommPledge.doc"),
                if(code.equals(EnumContract.CONT_COMMON_PLEDGE.getCode())){
                    String pledgorInfoList = request.getParameter("pledgorInfoList");
                    if(StringUtils.isNotBlank(pledgorInfoList) ){
                        try {
                            JSONArray ja = JSONArray.fromObject(pledgorInfoList);
                            contCommPledge.setPledgorInfoList(JSONArray.toList(ja, new PledgorInfo(), new JsonConfig()));
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    if(null!=contCommPledge){
                        lnLoanContract.setContCommPledge(getJsonString(contCommPledge));
                    }
                    lnLoanContract.setContCommPledge(null != lnLoanContract.getContCommPledge() ? (lnLoanContract.getContCommPledge() + 1) : "1");
                }
                //  CONT_TOP_PLEDGE("24","2","最高额质押担保合同" ,"contTopPledge.doc"), ContTopPledge
                if(code.equals(EnumContract.CONT_TOP_PLEDGE.getCode())){
                    String pledgorInfoList = request.getParameter("pledgorInfoList");
                    if(StringUtils.isNotBlank(pledgorInfoList) ){
                        try {
                            JSONArray ja = JSONArray.fromObject(pledgorInfoList);
                            contTopPledge.setPledgorInfoList(JSONArray.toList(ja, new PledgorInfo(), new JsonConfig()));
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    if(null!=contTopPledge){
                        lnLoanContract.setContTopPledge(getJsonString(contTopPledge));
                    }

                }

                lnLoanContractService.insertLnLoanContract(lnLoanContract);
            }
            PrintWriter out = this.getResponse().getWriter();
            out.print(true);
            logger.info("保存合同内容成功!");

        } catch (Exception e) {
            log.error("saveContract action error:", e);
            e.printStackTrace();
        }
    }

    /**
     * 下载合同
     */
    public void downContract() {
        try {
            logger.info("下载合同...");
            //贷款id
            String loanId =  request.getParameter("loanId");
            String code =  request.getParameter("code");
            String cnName = EnumContract.getEnumByCode(code).getFileName_CN();

            String timePath = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());
            String path = FileUtil.contact(this.getSession().getServletContext().getRealPath("/"), "temp_file/export/"+ cnName + timePath + ".doc");
            String dir = path.substring(0, path.lastIndexOf("/")); // 取出目录
            File file = new File(dir);
            // 检查文件是否存在
            File obj = new File(path);
            if (!file.exists()) { // 目录不存在，则创建相应的目录
                file.mkdirs();
                if (!obj.exists()) // 接下来创建具体文件
                    obj.createNewFile(); // 就是在这个点抛出异常
            }

            getContractExportFile(loanId, path, code);

            // 写流文件到前端浏览器
            ServletOutputStream out = this.getResponse().getOutputStream();
            this.getResponse().setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(cnName+".doc", "UTF-8"));
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;

            bis = new BufferedInputStream(new FileInputStream(path));
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            bis.close();
            bos.close();
            obj.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getContractExportFile(String loanId, String path, String code) {
        try{
            //取得贷款信息
            LnLoanContract lnLoanContract = lnLoanContractService.getLnLoanContractById(Integer.valueOf(loanId));
            String rtefPath = request.getSession().getServletContext().getResource("/ini").getFile();
            if (!FileUtil.isExistFilePath(rtefPath)) {
                rtefPath = ServerRealPathUtil.getServerRealPath() + "ini";
            }
            rtefPath = rtefPath + File.separator + EnumContract.getEnumByCode(code).getFileName_EN();
            HWPFDocument hdt = null;
            XWPFDocument document = null;
            //普通主合同
            if(code.equals(EnumContract.CONT_COMMON_MAIN.getCode())){
                if(null!=lnLoanContract&&StringUtils.isNotBlank(lnLoanContract.getContCommMain())){
                    contCommMain = (ContCommMain)getObject(lnLoanContract.getContCommMain(),new ContCommMain());
                    FileInputStream in = new FileInputStream(new File(rtefPath));
                    hdt = new HWPFDocument(in);
                    Range range = hdt.getRange();
                    //range.replaceText();
//                    Table table = range.insertTableBefore((short)4,4);
//                    table.getRow(1).getCell(1).getParagraph(0).getCharacterRun(0).insertBefore("123123123");
                    Map<String, String> map = objectToMap(contCommMain);
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        if (StringUtils.isBlank(entry.getValue())) {
                            entry.setValue("  ");
                        }
                        range.replaceText("${" + entry.getKey() + "}", entry.getValue());
                    }
                }
            }
            //最高额主合同
            if(code.equals(EnumContract.CONT_TOP_MAIN.getCode())){
                if(null!=lnLoanContract&&StringUtils.isNotBlank(lnLoanContract.getContTopMain())){
                    contTopMain = (ContTopMain)getObject(lnLoanContract.getContTopMain(),new ContTopMain());
                    FileInputStream in = new FileInputStream(new File(rtefPath));
                    hdt = new HWPFDocument(in);
                    Range range = hdt.getRange();
                    Map<String, String> map = objectToMap(contTopMain);
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        if (StringUtils.isBlank(entry.getValue())) {
                            entry.setValue("  ");
                        }
                        range.replaceText("${" + entry.getKey() + "}", entry.getValue());
                    }
                }
            }
            // CONT_CARD_MAIN("31","3","最高额借款合同(卡贷宝)" ,"contCardMain.doc"), ContCardMain
            if(code.equals(EnumContract.CONT_CARD_MAIN.getCode())){
                if(null!=lnLoanContract&&StringUtils.isNotBlank(lnLoanContract.getContCardMain())){
                    contCardMain = (ContCardMain)getObject(lnLoanContract.getContCardMain(),new ContCardMain());
                    FileInputStream in = new FileInputStream(new File(rtefPath));
                    hdt = new HWPFDocument(in);
                    Range range = hdt.getRange();
                    Map<String, String> map = objectToMap(contCardMain);
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        if (StringUtils.isBlank(entry.getValue())) {
                            entry.setValue("  ");
                        }
                        range.replaceText("${" + entry.getKey() + "}", entry.getValue());
                    }
                }
            }

            //担保合同
            if(code.equals(EnumContract.CONT_COMMON_GUARANTY.getCode())){
                if(null!=lnLoanContract&&StringUtils.isNotBlank(lnLoanContract.getContCommGuaranty())){
                    contCommGuaranty = (ContCommGuaranty)getObject(lnLoanContract.getContCommGuaranty(),new ContCommGuaranty());
                    Map<String, String> map = objectToMap(contCommGuaranty);
                    document = new XWPFDocument(POIXMLDocument.openPackage(rtefPath));
                    List<XWPFParagraph> list = document.getParagraphs();
                    StringBuffer sbf = new StringBuffer("");
                    List<GuarantorInfo> gailist= contCommGuaranty.getGuarantorInfoList();
                    String s1[] ={"一","二","三","四","五","六","七","八","九","十"};
                    JSONArray ja = JSONArray.fromObject(gailist);
                    int j=1;
                    contCommGuaranty.setGuarantorInfoList( JSONArray.toList(ja,new GuarantorInfo(),new JsonConfig()));
                    if(CollectionUtils.isNotEmpty(contCommGuaranty.getGuarantorInfoList())){
                      for (GuarantorInfo gi : contCommGuaranty.getGuarantorInfoList()) {
                            sbf.append("("+s1[j]+")");
                            sbf.append("保证人：").append(gi.getGuarantorName()).append("                                                                ");
                            sbf.append("法定代表人：").append(gi.getGuarantorCorporation()).append("                                                          ");
                            sbf.append("住所地：").append(gi.getGuarantorAddress()).append("                                                         ");
                            sbf.append("组织机构代码/统一社会信用代码/身份证号码/：").append(gi.getGuarantorCreditNo()).append("                                                           ");
                            j++;
                        }
                    }
                    StringBuffer sf = new StringBuffer("");
                    int m=0;
                    if(CollectionUtils.isNotEmpty(contCommGuaranty.getGuarantorInfoList())){
                        for (GuarantorInfo gi : contCommGuaranty.getGuarantorInfoList()) {
                            sf.append("(" + s1[m]+")");
                            sf.append("保证人: ").append(gi.getGuarantorName()).append("                                                       ");
                            sf.append("送达地址：").append(gi.getGuarantorAddress()).append("                                                         ");
                            sf.append("法定代表人：").append(gi.getGuarantorCorporation()).append("                                                                             ");
                            sf.append("电话：").append(gi.getGuarantorPhoneNum()).append("                      ").append("传真").append(gi.getGuarantorFaxNum()).append("                                                    ");
                            sf.append("广东法院诉讼文书接收专用免费电子邮箱:  ").append(gi.getGuarantorEmail()).append("@sd.gdcourts.gov.cn").append("                                                               ");
                            m++;
                        }
                    }
                    map.put("guBaseList",sbf.toString());
                    map.put("lastguBaseList",sf.toString());
                    replace(list, map);
                }
            }
            //担保最高额合同
            if(code.equals(EnumContract.CONT_TOP_GUARANTY.getCode())){
                if(null!=lnLoanContract&&StringUtils.isNotBlank(lnLoanContract.getContTopGuaranty())){
                    contTopGuaranty = (ContTopGuaranty)getObject(lnLoanContract.getContTopGuaranty(),new ContTopGuaranty());
                    FileInputStream in = new FileInputStream(new File(rtefPath));
                    hdt = new HWPFDocument(in);
                    Range range = hdt.getRange();
                    Map<String, String> map = objectToMap(contTopGuaranty);
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        if (StringUtils.isBlank(entry.getValue())) {
                            entry.setValue("  ");
                        }
                        range.replaceText("${" + entry.getKey() + "}", entry.getValue());
                    }
                    StringBuffer sbf = new StringBuffer("");
                    List<GuarantorInfo> list= contTopGuaranty.getGuarantorInfoList();
                    String s1[] ={"一","二","三","四","五","六","七","八","九","十"};
                    JSONArray ja = JSONArray.fromObject(list);
                    int j=1;
                    contTopGuaranty.setGuarantorInfoList( JSONArray.toList(ja,new GuarantorInfo(),new JsonConfig()));
                    if(CollectionUtils.isNotEmpty(contTopGuaranty.getGuarantorInfoList())){
                        for (GuarantorInfo gi : contTopGuaranty.getGuarantorInfoList()) {
                            sbf.append("("+s1[j]+")");
                            sbf.append("保证人：").append(gi.getGuarantorName()).append("\r\n");
                            sbf.append("法定代表人：").append(gi.getGuarantorCorporation()).append("\r\n");
                            sbf.append("住所地：").append(gi.getGuarantorAddress()).append("\r\n");
                            sbf.append("组织机构代码/统一社会信用代码/身份证号码/：").append(gi.getGuarantorCreditNo()).append("\r\n");
                            j++;
                        }
                    }
                    range.replaceText("${guBaseList}",sbf.toString());
                    StringBuffer sf = new StringBuffer("");
                    int m=0;
                    if(CollectionUtils.isNotEmpty(contTopGuaranty.getGuarantorInfoList())){
                        for (GuarantorInfo gi : contTopGuaranty.getGuarantorInfoList()) {
                            sf.append("("+s1[m]+")");
                            sf.append("保证人: ").append(gi.getGuarantorName()).append("\r\n");
                            sf.append("送达地址：").append(gi.getGuarantorAddress()).append("\r\n");
                            sf.append("法定代表人：").append(gi.getGuarantorCorporation()).append("\r\n");
                            sf.append("电话：").append(gi.getGuarantorPhoneNum()).append("\t").append("传真").append(gi.getGuarantorFaxNum()).append("\r\n");
                            sf.append("广东法院诉讼文书接收专用免费电子邮箱:  ").append(gi.getGuarantorEmail()).append("@sd.gdcourts.gov.cn").append("\r\n");
                            m++;
                        }
                    }
                    range.replaceText("${lastguBaseList}",sf.toString());
                }
            }
            // CONT_CARD_GUARANTY("32","3","最高额保证合同(卡贷宝)" ,"contCardGuaranty.doc"),  ContCardGuaranty
            if(code.equals(EnumContract.CONT_CARD_GUARANTY.getCode())){
                if(null!=lnLoanContract&&StringUtils.isNotBlank(lnLoanContract.getContCardGuaranty())){
                    contCardGuaranty = (ContCardGuaranty)getObject(lnLoanContract.getContCardGuaranty(),new ContCardGuaranty());
                    FileInputStream in = new FileInputStream(new File(rtefPath));
                    hdt = new HWPFDocument(in);
                    Range range = hdt.getRange();
                    Map<String, String> map = objectToMap(contCardGuaranty);
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        if (StringUtils.isBlank(entry.getValue())) {
                            entry.setValue("  ");
                        }
                        range.replaceText("${" + entry.getKey() + "}", entry.getValue());
                    }
                    List<GuarantorInfo> list= contCardGuaranty.getGuarantorInfoList();
                    String s1[] ={"一","二","三","四","五","六","七","八","九","十"};
                    JSONArray ja = JSONArray.fromObject(list);
                    int j=1;
                    contCardGuaranty.setGuarantorInfoList( JSONArray.toList(ja,new GuarantorInfo(),new JsonConfig()));
                    StringBuffer sf = new StringBuffer("");
                    if(CollectionUtils.isNotEmpty(contCardGuaranty.getGuarantorInfoList())){
                        for (GuarantorInfo gi : contCardGuaranty.getGuarantorInfoList()) {
                            sf.append("保证人: ").append(gi.getGuarantorName()).append("\r\n");
                            sf.append("送达地址：").append(gi.getGuarantorSendAddress()).append("\r\n");
                            sf.append("法定代表人：").append(gi.getGuarantorCorporation()).append("\r\n");
                            sf.append("电话：").append(gi.getGuarantorPhoneNum()).append("\t").append("传真").append(gi.getGuarantorFaxNum()).append("\r\n");
                            sf.append("广东法院诉讼文书接收专用免费电子邮箱:  ").append(gi.getGuarantorEmail()).append("@sd.gdcourts.gov.cn").append("\r\n");
                        }
                    }
                    range.replaceText("${lastguBaseList}",sf.toString());
                }
            }
            //普通抵押合同
            if(code.equals(EnumContract.CONT_COMMON_MORTGAGE.getCode())){
                if(null!=lnLoanContract&&StringUtils.isNotBlank(lnLoanContract.getContCommMortgage())){
                    contCommMortgage = (ContCommMortgage)getObject(lnLoanContract.getContCommMortgage(),new ContCommMortgage());
                    Map<String, String> map = objectToMap(contCommMortgage);
                    document = new XWPFDocument(POIXMLDocument.openPackage(rtefPath));
                    List<XWPFParagraph> list = document.getParagraphs();
                    StringBuffer ipg = new StringBuffer("");
                    StringBuffer sbff = new StringBuffer("");
                    List<MortgagorInfo> inFolist= contCommMortgage.getMortgagorInfoList();
                    List<MortgageGoodsInfo> inGoodFolist= contCommMortgage.getMortgageGoodsInfoList();
                    String s1[] ={"一","二","三","四","五","六","七","八","九","十"};
                    JSONArray ja = JSONArray.fromObject(inFolist);
                    JSONArray jb = JSONArray.fromObject(inGoodFolist);
                    int j=1;
                    contCommMortgage.setMortgagorInfoList(JSONArray.toList(ja, new MortgagorInfo(), new JsonConfig()));
                    contCommMortgage.setMortgageGoodsInfoList(JSONArray.toList(jb, new MortgageGoodsInfo(), new JsonConfig()));
                    List<String> pifList=new ArrayList<String>();
                    //填充表格数据
                    for (int i=0;i<contCommMortgage.getMortgageGoodsInfoList().size();i++){
                        pifList.clear();
                        pifList.add(contCommMortgage.getMortgageGoodsInfoList().get(i).getGoodsNo());
                        pifList.add(contCommMortgage.getMortgageGoodsInfoList().get(i).getGoodsType());
                        pifList.add(contCommMortgage.getMortgageGoodsInfoList().get(i).getGoodsOwnershipName());
                        pifList.add(contCommMortgage.getMortgageGoodsInfoList().get(i).getGoodsOwnershipNo());
                        pifList.add(contCommMortgage.getMortgageGoodsInfoList().get(i).getGoodsValue());
                        pifList.add(contCommMortgage.getMortgageGoodsInfoList().get(i).getGoodsOwnershipNo());
                        pifList.add(contCommMortgage.getMortgageGoodsInfoList().get(i).getIsLease());
                        pifList.add(contCommMortgage.getMortgageGoodsInfoList().get(i).getLeaseBeginDate());
                        addTableRow(document, 2, pifList);
                    }
                   if(CollectionUtils.isNotEmpty(contCommMortgage.getMortgagorInfoList())){
                        for (MortgagorInfo gi : contCommMortgage.getMortgagorInfoList()) {
                            ipg.append("("+s1[j]+")");
                            ipg.append("抵押人：").append(gi.getMortgagorName()).append("                                                                   ");
                            ipg.append("法定代表人：").append(gi.getMortgagorCorporation()).append("                                                                    ");
                            ipg.append("住所地：").append(gi.getMortgagorAddress()).append("                                                                    ");
                            ipg.append("组织机构代码/统一社会信用代码/身份证号码：").append(gi.getMortgagorCreditNo()).append("                                                                    ");
                            j++;
                        }
                    }
                    j=0;
                    if(CollectionUtils.isNotEmpty(contCommMortgage.getMortgagorInfoList())){
                        for (MortgagorInfo gi : contCommMortgage.getMortgagorInfoList()) {
                            sbff.append("("+s1[j]+")");
                            sbff.append("抵押人、抵押财产共有人（如有）：").append(gi.getMortgagorName()).append("                                                                    ");
                            sbff.append("送达地址：").append(gi.getMortgagorSendAddress()).append("                                                                    ");
                            sbff.append("法定代表人：").append(gi.getMortgagorCorporation()).append("                                                                    ");
                            sbff.append("电话： ").append(gi.getMortgagorPhoneNum()).append("     ");
                            sbff.append("传真： ").append(gi.getMortgagorFaxNum()).append("                                                                    ");
                            sbff.append("广东法院诉讼文书接收专用免费电子邮箱：").append(gi.getMortgagorEmail()).append("@sd.gdcourts.gov.cn").append("                                                                    ");
                            j++;
                        }
                    }
                    map.put("contMortgagorInfoList",ipg.toString());
                    map.put("contInfoList",sbff.toString());
                    replace(list,map);
                    change(document,map);
                }
            }
            //CONT_TOP_MORTGAGE("23","2","最高额抵押担保合同" ,"contTopMortgage.doc"),ContTopMortgage
            if(code.equals(EnumContract.CONT_TOP_MORTGAGE.getCode())){
                if(null!=lnLoanContract&&StringUtils.isNotBlank(lnLoanContract.getContTopMortgage())){
                    contTopMortgage = (ContTopMortgage)getObject(lnLoanContract.getContTopMortgage(),new ContTopMortgage());
                    Map<String, String> map = objectToMap(contTopMortgage);
                    document = new XWPFDocument(POIXMLDocument.openPackage(rtefPath));
                    List<XWPFParagraph> list = document.getParagraphs();
                    StringBuffer ipg = new StringBuffer("");
                    StringBuffer sbff = new StringBuffer("");
                    List<MortgagorInfo> mgilist= contTopMortgage.getMortgagorInfoList();
                    List<MortgageGoodsInfo> mggilist= contTopMortgage.getMortgageGoodsInfoList();
                    JSONArray ja = JSONArray.fromObject(mgilist);
                    JSONArray jb = JSONArray.fromObject(mggilist);
                    int j=1;
                    contTopMortgage.setMortgagorInfoList(JSONArray.toList(ja, new MortgagorInfo(), new JsonConfig()));
                    contTopMortgage.setMortgageGoodsInfoList(JSONArray.toList(jb, new MortgageGoodsInfo(), new JsonConfig()));
                    List<String> pifList=new ArrayList<String>();
                    //填充表格数据
                    for (int i=0;i<contTopMortgage.getMortgageGoodsInfoList().size();i++){
                        pifList.clear();
                        pifList.add(contTopMortgage.getMortgageGoodsInfoList().get(i).getGoodsNo());
                        pifList.add(contTopMortgage.getMortgageGoodsInfoList().get(i).getGoodsType());
                        pifList.add(contTopMortgage.getMortgageGoodsInfoList().get(i).getGoodsOwnershipName());
                        pifList.add(contTopMortgage.getMortgageGoodsInfoList().get(i).getGoodsOwnershipNo());
                        pifList.add(contTopMortgage.getMortgageGoodsInfoList().get(i).getGoodsValue());
                        pifList.add(contTopMortgage.getMortgageGoodsInfoList().get(i).getGoodsOwnershipNo());
                        pifList.add(contTopMortgage.getMortgageGoodsInfoList().get(i).getIsLease());
                        pifList.add(contTopMortgage.getMortgageGoodsInfoList().get(i).getLeaseBeginDate());
                        addTableRow(document, 2, pifList);
                    }
                    String s1[] ={"一","二","三","四","五","六","七","八","九","十"};
                    if(CollectionUtils.isNotEmpty(contTopMortgage.getMortgagorInfoList())){
                        for (MortgagorInfo gi : contTopMortgage.getMortgagorInfoList()) {
                            ipg.append("("+s1[j]+")");
                            ipg.append("抵押人：").append(gi.getMortgagorName()).append("                                                         ");
                            ipg.append("法定代表人：").append(gi.getMortgagorCorporation()).append("                                                    ");
                            ipg.append("住所地：").append(gi.getMortgagorAddress()).append("                                                       ");
                            ipg.append("组织机构代码/统一社会信用代码/身份证号码：").append(gi.getMortgagorCreditNo()).append("                                                         ");
                            j++;
                        }
                    }
                    j=0;
                    if(CollectionUtils.isNotEmpty(contTopMortgage.getMortgagorInfoList())){
                        for (MortgagorInfo gi : contTopMortgage.getMortgagorInfoList()) {
                            sbff.append("("+s1[j]+")");
                            sbff.append("抵押人、抵押财产共有人（如有）：").append(gi.getMortgagorName()).append("                                                ");
                            sbff.append("送达地址：").append(gi.getMortgagorSendAddress()).append("                                                          ");
                            sbff.append("法定代表人：").append(gi.getMortgagorCorporation()).append("                                               ");
                            sbff.append("电话： ").append(gi.getMortgagorPhoneNum()).append("                                                                ");
                            sbff.append("传真： ").append(gi.getMortgagorFaxNum()).append("                                                                   ");
                            sbff.append("广东法院诉讼文书接收专用免费电子邮箱：").append(gi.getMortgagorEmail()).append("@sd.gdcourts.gov.cn").append("                                                     ");
                            j++;
                        }
                    }
                    map.put("contTopMortgage_mortgagorInfoList",ipg.toString());
                    map.put("contTopMortgagemortgagorInfoList",sbff.toString());
                    replace(list,map);
                    change(document,map);
                }
            }

            // CONT_CARD_MORTGAGE("33","3","最高额抵押担保合同(卡贷宝)" ,"contCardMortgage.doc"),  ContCardMortgage
            if(code.equals(EnumContract.CONT_CARD_MORTGAGE.getCode())){
                if(null!=lnLoanContract&&StringUtils.isNotBlank(lnLoanContract.getContCardMortgage())){
                    contCardMortgage = (ContCardMortgage)getObject(lnLoanContract.getContCardMortgage(),new ContCardMortgage());
                    FileInputStream in = new FileInputStream(new File(rtefPath));
                    hdt = new HWPFDocument(in);
                    Range range = hdt.getRange();
                    Map<String, String> map = objectToMap(contCardMortgage);
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        if (StringUtils.isBlank(entry.getValue())) {
                            entry.setValue("  ");
                        }
                        range.replaceText("${" + entry.getKey() + "}", entry.getValue());
                    }
                    StringBuffer ipg = new StringBuffer("");
                    StringBuffer sbff = new StringBuffer("");
                    List<MortgagorInfo> list= contCardMortgage.getMortgagorInfoList();
                    JSONArray ja = JSONArray.fromObject(list);
                    contCardMortgage.setMortgagorInfoList(JSONArray.toList(ja, new MortgagorInfo(), new JsonConfig()));
                    if(CollectionUtils.isNotEmpty(contCardMortgage.getMortgagorInfoList())){
                        for (MortgagorInfo gi : contCardMortgage.getMortgagorInfoList()) {
                            sbff.append("抵押人、抵押财产共有人（如有）：").append(gi.getMortgagorName()).append("\r\n");
                            sbff.append("送达地址：").append(gi.getMortgagorSendAddress()).append("\r\n");
                            sbff.append("法定代表人：").append(gi.getMortgagorCorporation()).append("\r\n");
                            sbff.append("电话： ").append(gi.getMortgagorPhoneNum()).append("\r\n");
                            sbff.append("传真： ").append(gi.getMortgagorFaxNum()).append("\r\n");
                            sbff.append("广东法院诉讼文书接收专用免费电子邮箱：").append(gi.getMortgagorEmail()).append("@sd.gdcourts.gov.cn").append("\r\n");
                        }
                    }
                    range.replaceText("${contCardMortgage_mortgagorInfoList}",ipg.toString());
                    range.replaceText("${contCardMortgagemortgagorInfoList}",sbff.toString());
                }
            }


            //质押担保  CONT_COMMON_PLEDGE("14","1","质押担保合同" ,"contCommPledge.doc"),
            if(code.equals(EnumContract.CONT_COMMON_PLEDGE.getCode())){
                if(null!=lnLoanContract&&StringUtils.isNotBlank(lnLoanContract.getContCommPledge())){
                    contCommPledge = (ContCommPledge) getObject(lnLoanContract.getContCommPledge(), new ContCommPledge());
                    Map<String, String> map = objectToMap(contCommPledge);
                    document = new XWPFDocument(POIXMLDocument.openPackage(rtefPath));
                    List<XWPFParagraph> list = document.getParagraphs();
                    /**
                     * document docx对象
                     * tableNum 获取word第几个表格 从0开始
                     * list 表格中增加一行  list为增加行 从左到右 对应列的值
                     * */
                    StringBuffer ipg = new StringBuffer("");
                    StringBuffer ccp = new StringBuffer("");
                    List<PledgorInfo> pledgorInfoList= contCommPledge.getPledgorInfoList();
                    JSONArray ja = JSONArray.fromObject(pledgorInfoList);
                    contCommPledge.setPledgorInfoList(JSONArray.toList(ja, new PledgorInfo(), new JsonConfig()));
                    List<String> pifList=new ArrayList<String>();
                    //填充表格数据
                    for (int i=0;i<pledgorInfoList.size();i++){
                        pifList.clear();
                        pifList.add(contCommPledge.getPledgorInfoList().get(i).getPledgeNo());
                        pifList.add(contCommPledge.getPledgorInfoList().get(i).getPledgeType());
                        pifList.add(contCommPledge.getPledgorInfoList().get(i).getPledgeOwnershipName());
                        pifList.add( contCommPledge.getPledgorInfoList().get(i).getPledgeOwnershipNo());
                        pifList.add("1");
                        pifList.add(contCommPledge.getPledgorInfoList().get(i).getGoodsAmount());
                        pifList.add(contCommPledge.getPledgorInfoList().get(i).getGoodsValue());
                        addTableRow(document, 2, pifList);
                    }
                    //表格     从5 开始计算表格数量
                   if(contCommPledge.getPledgorInfoList().size()> 5){
                       for(int yu= 5;yu<contCommPledge.getPledgorInfoList().size(); yu++ ){
                           pifList.clear();
                           pifList.add("出质人（签章）：<hx><hx><hx>法定代表人（签字）：<hx>（授权代理人）<hx> <hx> ");
                           pifList.add("出质人（签章）：<hx><hx><hx>法定代表人（签字）：<hx>（授权代理人）<hx> <hx> ");
                         insertTableRows(document, 0, pifList);
                           yu++;
                       }
                   }
                   String s1[] ={"一","二","三","四","五","六","七","八","九","十"};
                    int j=1;
                    if(CollectionUtils.isNotEmpty(contCommPledge.getPledgorInfoList())){
                        for (PledgorInfo gi : contCommPledge.getPledgorInfoList()) {
                            ipg.append("("+s1[j]+")");
                            ipg.append("出质人：").append(gi.getPledgorName()).append("                                                            ");
                            ipg.append("法定代表人：").append(gi.getPledgorCorporation()).append("                                                            ");
                            ipg.append("住所地：").append(gi.getPledgorAddress()).append("                                                            ");
                            ipg.append("组织机构代码/统一社会信用代码/身份证号码：").append(gi.getPledgorCreditNo()).append("                                                            ");
                            j++;
                        }
                    }
                    j=0;
                    if(CollectionUtils.isNotEmpty(contCommPledge.getPledgorInfoList())){
                        for (PledgorInfo gi : contCommPledge.getPledgorInfoList()) {
                            ccp.append("("+s1[j]+")");
                            ccp.append("出质人、质押财产共有人（如有）：").append(gi.getPledgorName()).append("                                 ");
                            ccp.append("送达地址：").append(gi.getPledgorSendAddress()).append("                                                          ");
                            ccp.append(" 法定代表人：").append(gi.getPledgorCorporation()).append("                                                      ");
                            ccp.append(" 电话：").append(gi.getPledgorPhoneNum()).append("                  ");
                            ccp.append(" 传真：").append(gi.getPledgorFaxNum()).append("                                                                      ");
                            ccp.append("广东法院诉讼文书接收专用免费电子邮箱：").append(gi.getPledgorEmail()).append("@sd.gdcourts.gov.cn").append("                                                   ");
                            j++;
                        }
                    }
                    map.put("contCommPledgepledgorInfolist",ccp.toString());
                    map.put("contInfolist",ipg.toString());
                    //普通文本
                    replace(list,map);
                    change(document,map);
                }
            }
            //  CONT_TOP_PLEDGE("24","2","最高额质押担保合同" ,"contTopPledge.doc"),ContTopPledge
            if(code.equals(EnumContract.CONT_TOP_PLEDGE.getCode())){
                if(null!=lnLoanContract&&StringUtils.isNotBlank(lnLoanContract.getContTopPledge())){
                    contTopPledge = (ContTopPledge)getObject(lnLoanContract.getContTopPledge(),new ContTopPledge());
                    Map<String, String> map = objectToMap(contTopPledge);
                    document = new XWPFDocument(POIXMLDocument.openPackage(rtefPath));
                    List<XWPFParagraph> list = document.getParagraphs();

                    StringBuffer ipg = new StringBuffer("");
                    StringBuffer ccp = new StringBuffer("");
                    List<PledgorInfo> pledGolist= contTopPledge.getPledgorInfoList();


                    String s1[] ={"一","二","三","四","五","六","七","八","九","十"};
                    JSONArray ja = JSONArray.fromObject(pledGolist);
                    int j=1;
                    contTopPledge.setPledgorInfoList(JSONArray.toList(ja, new PledgorInfo(), new JsonConfig()));
                    List<String> pifList=new ArrayList<String>();
                    //填充表格数据
                    for (int i=0;i<contTopPledge.getPledgorInfoList().size();i++){
                        pifList.clear();
                        pifList.add(contTopPledge.getPledgorInfoList().get(i).getPledgeNo());
                        pifList.add(contTopPledge.getPledgorInfoList().get(i).getPledgeType());
                        pifList.add(contTopPledge.getPledgorInfoList().get(i).getPledgeOwnershipName());
                        pifList.add( contTopPledge.getPledgorInfoList().get(i).getPledgeOwnershipNo());
                        pifList.add("1");
                        pifList.add(contTopPledge.getPledgorInfoList().get(i).getGoodsAmount());
                        pifList.add(contTopPledge.getPledgorInfoList().get(i).getGoodsValue());
                        addTableRow(document, 2, pifList);
                    }
                    if(CollectionUtils.isNotEmpty(contTopPledge.getPledgorInfoList())){
                        for (PledgorInfo gi : contTopPledge.getPledgorInfoList()) {
                            ipg.append("("+s1[j]+")");
                            ipg.append("出质人：").append(gi.getPledgorName()).append("                                               ");
                            ipg.append("法定代表人：").append(gi.getPledgorCorporation()).append("                                            ");
                            ipg.append("住所地：").append(gi.getPledgorAddress()).append("                                                   ");
                            ipg.append("组织机构代码/统一社会信用代码/身份证号码：").append(gi.getPledgorCreditNo()).append("                            ");
                            j++;
                        }
                    }
                    j=0;
                    if(CollectionUtils.isNotEmpty(contTopPledge.getPledgorInfoList())){
                        for (PledgorInfo gi : contTopPledge.getPledgorInfoList()) {
                            ccp.append("("+s1[j]+")");
                            ccp.append("出质人、质押财产共有人（如有）：").append(gi.getPledgorName()).append("                                             ");
                            ccp.append("送达地址：").append(gi.getPledgorSendAddress()).append("                                                      ");
                            ccp.append(" 法定代表人：").append(gi.getPledgorCorporation()).append("                                           ");
                            ccp.append(" 电话：").append(gi.getPledgorPhoneNum()).append("                                                   ");
                            ccp.append(" 传真：").append(gi.getPledgorFaxNum()).append("                                                      ");
                            ccp.append("广东法院诉讼文书接收专用免费电子邮箱：").append(gi.getPledgorEmail()).append("@sd.gdcourts.gov.cn").append("                                   ");
                            j++;
                        }
                    }
                    map.put("contPledInfolist",ipg.toString());
                    map.put("contTopPledInfolist",ccp.toString());
                    replace(list,map);
                    change(document,map);
                }
            }
            FileOutputStream os = new FileOutputStream(new File(path));
            if(null!=hdt){
                hdt.write(os);
            }
            if(null!=document){
                document.write(os);
            }
            os.close();
        }catch(Exception e){
            logger.error("", e);
        }


    }

    /**
     * document docx对象
     * tableNum 获取word第几个表格 从0开始
     * list 表格中增加一行  list为增加行 从左到右 对应列的值
     * */
    private void insertTableRows(XWPFDocument document,int tableNum, List<String> list){
        List<XWPFTable> tables = document.getTables();
        XWPFTable table = tables.get(tableNum);
        XWPFTableRow row = table.createRow();
        for (int i =0; i <list.size(); i++) {
            XWPFParagraph para = document.createParagraph();
            XWPFRun run = para.createRun();
            String[] strList = list.get(i).split("<hx>");
            for (int j = 0; j < strList.length ; j++) {
                run.setText(strList[j]);
                run.addBreak();
                para.addRun(run);
                row.getCell(i).setParagraph(para);
            }
        }
    }
    private void addTableRow(XWPFDocument document,int tableNum, List<String> list){
        List<XWPFTable> tables = document.getTables();
        XWPFTable table = tables.get(tableNum);
        XWPFTableRow row = table.createRow();
        for (int i = 0; i < list.size(); i++) {
            row.getCell(i).setText(list.get(i));
        }
    };

private void change(XWPFDocument document,Map<String, String> map){
    //替换表格中的文字，这里需要直接写标记，不用${}包括
    Iterator<XWPFTable> itTable=document.getTablesIterator();
    int po=0;
    while(itTable.hasNext()){
        XWPFTable table =(XWPFTable)itTable.next();
        int rcount =table.getNumberOfRows();
        for (int i = 0; i <rcount ; i++) {
            po= table.getRows().size();
            if(po==1){continue;}
            if(po==i){continue;}
            XWPFTableRow row=table.getRow(i);
            List<XWPFTableCell> cells=row.getTableCells();
            for(XWPFTableCell cell :cells ){
                String  cellTestString =cell.getText();
                for(Map.Entry<String, String> e :map.entrySet()){
                    if(cellTestString.contains(e.getKey())){
                        cellTestString=cellTestString.replace("${" + e.getKey() + "}",e.getValue());
                        cell.removeParagraph(0);
                        cell.setText(cellTestString);
                    }
                }
            }
        }
    }
}
    private void replace( List<XWPFParagraph> list,Map<String, String> map){
        if (list != null && list.size() > 0) {
            for (XWPFParagraph paragraph : list) {
                String s = paragraph.getParagraphText();
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    String text = run.getText(0);
                    if (text != null) {
                        for (Map.Entry<String, String> entry : map.entrySet()) {
                            String key = entry.getKey();
                            if (text.indexOf( key ) != -1) {
                                Object value = entry.getValue();
                                if (value instanceof String) {//文本替换
                                    text = text.replace("${" + key + "}", value.toString());
                                    run.setText(text, 0);
                                    // run.addCarriageReturn();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public static Map<String, String> objectToMap(Object obj) throws Exception {


        if(obj == null)
            return null;

        Map<String, String> map = new HashMap<String, String>();
        try{
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (key.compareToIgnoreCase("class") == 0) {
                    continue;
                }
                Method getter = property.getReadMethod();
                Object value = getter!=null ? getter.invoke(obj) : null;
                if(null!=value&&value instanceof Date){
                    SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
                    map.put(key, sf.format(value));
                }else{
                    map.put(key, null!=value?value.toString():null);
                }
            }
        }catch (Exception e){

        }

        return map;
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

    public void submitLend(){

        PrintWriter out = null;
        try{
            out = this.getResponse().getWriter();
            Integer loanId = Integer.parseInt(request.getParameter("loanId"));
            String lendContractNum = request.getParameter("lendContractNum");
            String lendConfrimContractNum = request.getParameter("lendConfrimContractNum");
            String lendNote = request.getParameter("lendNote");

            LnLoanInfo loanInfo = new LnLoanInfo();
            loanInfo.setLoanId(loanId);
            loanInfo.setLendConfrimContractNum(lendConfrimContractNum);
            loanInfo.setLendContractNum(lendContractNum);
            loanInfo.setLendNote(lendNote);

            LnLoan lnLoan = new LnLoan();
            lnLoan.setLoanId(loanId);
            lnLoan.setLendSaveDate(new Date());

            LnOpHistory lnOpHistory = new LnOpHistory();
            lnOpHistory.setUserId(this.getLoginInfo().getUserId());
            lnOpHistory.setOpHistoryDate(new Date());
            lnOpHistory.setBeforeStatusId(5);
            lnOpHistory.setAfterStatusId(5);
            lnOpHistory.setRemark(lendNote);
            lnOpHistory.setLoanId(loanId);
            lnOpHistory.setContent("贷款放款保存");

            lnLoanService.submitApproveLoan(lnLoan,loanInfo,lnOpHistory);
            out.print("success");
        }catch(Exception e){
            e.printStackTrace();
            out.print("系统异常，请刷新重试或联系管理员！");
        }
    }
    public void addLendRemark(){
        logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 添加放贷贷款备注...");
        String strLoanId = request.getParameter("loanId");
        Integer loanId = Integer.parseInt(strLoanId.trim());
        String lendRemark = request.getParameter("lendRemark");
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("loanId",loanId);
        param.put("lendRemark",lendRemark);

        LnOpHistory lnOpHistory = new LnOpHistory();
        lnOpHistory.setUserId(this.getLoginInfo().getUserId());
        lnOpHistory.setOpHistoryDate(new Date());
        lnOpHistory.setBeforeStatusId(5);
        lnOpHistory.setAfterStatusId(5);
        lnOpHistory.setRemark(lendRemark);
        lnOpHistory.setLoanId(loanId);
        lnOpHistory.setContent("添加备注");
        logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 添加放贷贷款备注，修改贷款表并插入历史记录...");
        lnLoanService.checkoutLoan(param,lnOpHistory);
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

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
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


    public LnLoanInfoDictionaryService getLnLoanInfoDictionaryService() {
        return lnLoanInfoDictionaryService;
    }


    public void setLnLoanInfoDictionaryService(
            LnLoanInfoDictionaryService lnLoanInfoDictionaryService) {
        this.lnLoanInfoDictionaryService = lnLoanInfoDictionaryService;
    }

    /**
     * 下载Excel模板
     * 审批表
     * @return
     */
    public void downResultExcel() {
        try {
            //贷款id
            String loanId =  request.getParameter("loanId");

            String timePath = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());
            String path = FileUtil.contact(this.getSession().getServletContext().getRealPath("/"), "temp_file/export/贷款审批表" + timePath + ".xls");
            String dir = path.substring(0, path.lastIndexOf("/")); // 取出目录
            File file = new File(dir);
            // 检查文件是否存在
            File obj = new File(path);
            if (!file.exists()) { // 目录不存在，则创建相应的目录
                file.mkdirs();
                if (!obj.exists()) // 接下来创建具体文件
                    obj.createNewFile(); // 就是在这个点抛出异常
            }
            getResolutionTableExportFile(loanId , path);
            // 写流文件到前端浏览器
            ServletOutputStream out = this.getResponse().getOutputStream();
            this.getResponse().setHeader("Content-Disposition",
                    "attachment; filename=" + java.net.URLEncoder.encode("贷款审批表.xls", "UTF-8"));
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;

            bis = new BufferedInputStream(new FileInputStream(path));
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            bis.close();
            bos.close();
            obj.delete();
        } catch (Exception e) {
            log.error("", e);
        }

    }



    public void getResolutionTableExportFile(String loanId ,String path){
        try{
            //取得贷款信息
            Map<String,Object> param = new HashMap<String, Object>();
            param.put("loanId", loanId);
            LnLoanInfo lnLoanInfo = lnLoanInfoService.selectLoanInfoList(param).get(0);

            LnLoan lnLoan = lnLoanService.getLnLoanById(Integer.parseInt(loanId));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

            String rtefPath = request.getSession().getServletContext().getResource("/ini").getFile();
            if (!FileUtil.isExistFilePath(rtefPath)) {
                rtefPath = ServerRealPathUtil.getServerRealPath() + File.separator + "ini";
            }
            rtefPath = rtefPath + File.separator + "dksp.xls";

            //取Excel模板
            POIFSFileSystem fs=new POIFSFileSystem(new FileInputStream(rtefPath));
            HSSFWorkbook book = new HSSFWorkbook(fs);

            //填充第一个工作表
            HSSFSheet sheet = book.getSheetAt(0);
            //填充第三行数据
            HSSFRow row2 = sheet.getRow(2);
            row2.getCell(1).setCellValue(lnLoanInfo.getRegisterRecommendBank());
            row2.getCell(11).setCellValue(sysUserService.getSysUserById(lnLoan.getSurveyUserId()).getUserName());

            //填充第四行数据
            HSSFRow row3 = sheet.getRow(3);
            row3.getCell(1).setCellValue(lnLoanInfo.getCusName());
            row3.getCell(7).setCellValue(lnLoanInfo.getCompanyNature());
            row3.getCell(15).setCellValue(lnLoanInfo.getCusIdcard());

            //填充第五行数据//调查员建议
            HSSFRow row4 = sheet.getRow(4);
            if("0".equals(lnLoanInfo.getAdviceVerdict())){
                String col1 = "√建议拒绝";
                row4.getCell(1).setCellValue(col1);
            }else if("1".equals(lnLoanInfo.getAdviceVerdict())){
                String col2 = "√建议放款    金额："+lnLoanInfo.getAdviceMoney()+"元    期限："+lnLoanInfo.getAdviceLimitYear()+"个月";
                row4.getCell(5).setCellValue(col2);
            }

            //填充第六七行数据//审贷会决议
            HSSFRow row5 = sheet.getRow(5);
            HSSFRow row6 = sheet.getRow(6);
            String resultVerdict = lnLoanInfo.getResultVerdict();
            if ("1".equals(resultVerdict)){//批准贷款申请无改动
                row5.getCell(1).setCellValue("√批准贷款申请无改动");
            }else if("2".equals(resultVerdict)){//批准贷款申请有变更
                row5.getCell(5).setCellValue("√批准贷款申请有变更  金额："+lnLoanInfo.getResultMoney()+"元  期限："+lnLoanInfo.getResultLimitYear()+"个月");
            }else if("3".equals(resultVerdict)){//拒绝贷款申请
                row6.getCell(1).setCellValue("√拒绝贷款申请");
            }else if("4".equals(resultVerdict)){//暂时拒绝贷款申请
                row6.getCell(5).setCellValue("√暂时拒绝贷款申请");
            }else if("5".equals(resultVerdict)){//其它待议内容
                row6.getCell(11).setCellValue("√其它待议内容");
            }

            //填充第九行数据
            HSSFRow row8 = sheet.getRow(8);
            row8.getCell(2).setCellValue(lnLoanInfo.getCusName());
            row8.getCell(5).setCellValue(lnLoanInfo.getCusMobilePhone());
            row8.getCell(10).setCellValue(lnLoanInfo.getCusIdcard());

            //填充第十 ...行数据//担保人
            Map<String,Object> condition = new HashMap<String, Object>();
            condition.put("loanId",loanId);
            List<LnLoanGuarantorBean> guarantorList = lnLoanGuarantorService.getAllLnLoanGuarantorBeanByConds(condition);
            if(CollectionUtils.isNotEmpty(guarantorList)){
                int rowNum = 9;
                for (LnLoanGuarantorBean guarantor : guarantorList ) {
                    HSSFRow row = sheet.getRow(rowNum);
                    row.getCell(2).setCellValue(guarantor.getCustomerName());
                    row.getCell(5).setCellValue(guarantor.getCphNumber());
                    row.getCell(10).setCellValue(guarantor.getIdCard());
                    rowNum++;
                }
            }


            //填充第十四 ...行数据//抵押
            List<LnPledge> pledgeList = lnLoanPledgeService.getLnLoanPledgeByLoanId(Integer.valueOf(loanId));
            if(CollectionUtils.isNotEmpty(pledgeList)){
                int rowNum = 13;
                for (LnPledge pledge : pledgeList ) {
                    HSSFRow row = sheet.getRow(rowNum);
                    row.getCell(2).setCellValue(pledge.getGoods());
                    row.getCell(5).setCellValue(pledge.getOwnerName());
                    row.getCell(10).setCellValue(pledge.getGoodsRemark());
//                    row.getCell(18).setCellValue(pledge.getGoodsValue());
                    row.getCell(19).setCellValue(pledge.getGoodsAmount());
                    rowNum++;
                }
            }

            //填充第十七十八行数据//批准金额
            HSSFRow row16 = sheet.getRow(16);
            HSSFRow row17 = sheet.getRow(17);
            row16.getCell(2).setCellValue(lnLoanInfo.getResultMoney());
            row16.getCell(8).setCellValue(lnLoanInfo.getResultLimitYear());
            if("1".equals(lnLoanInfo.getLnBenefit())){
                row16.getCell(10).setCellValue("利率优惠：   √是   □否");
            }else if("0".equals(lnLoanInfo.getLnBenefit())){
                row16.getCell(10).setCellValue("利率优惠：   □是   √否");
            }
            row17.getCell(14).setCellValue(lnLoanInfo.getOriginalRate());
            row17.getCell(20).setCellValue(lnLoanInfo.getResultRate());

            //填充第十九行数据//担保方式
            HSSFRow row18 = sheet.getRow(18);
            row18.getCell(3).setCellValue(lnLoanInfoDictionaryService.getDictionaryValue("DBFS", lnLoanInfo.getAppGuarantorWayId()));
            if("01".equals(lnLoanInfo.getAppGuarantorWayId())||"02".equals(lnLoanInfo.getAppGuarantorWayId())) {
                row18.getCell(10).setCellValue(lnLoanInfoDictionaryService.getDictionaryValue("DY", lnLoanInfo.getAppPledgeId()));
            }
            //增加放款方式 第十九行
            HSSFRow row19 = sheet.getRow(19);
            row19.getCell(3).setCellValue(lnLoanInfoDictionaryService.getDictionaryValue("FKFS",lnLoanInfo.getAdviceLendWayId()));

            //填充第二十行数据//还款方式
            HSSFRow row20 = sheet.getRow(20);
            row20.getCell(3).setCellValue(lnLoanInfoDictionaryService.getDictionaryValue("HKFS", lnLoanInfo.getResultRepayWayId()));

//            FileInputStream in = new FileInputStream(new File("D:\\需要读取的word.doc"));
//            HWPFDocument hdt = new HWPFDocument(in);
//            Range range = hdt.getRange();

            //填充第二十一行数据//贷款用途
            HSSFRow row21 = sheet.getRow(21);
            HSSFRow row22 = sheet.getRow(22);
            row21.getCell(3).setCellValue(lnLoanInfoDictionaryService.getDictionaryValue("DKYT",lnLoanInfo.getResultPurpose()));
            row22.getCell(3).setCellValue("具体描述："+lnLoanInfo.getAppLoanPurpose());

            //填充第二十三行数据//放款条件
            HSSFRow row23 = sheet.getRow(23);
            row23.getCell(3).setCellValue(lnLoanInfo.getResultLnPremiss());

            //填充第二十四行数据//审批拒绝原因
            StringBuffer refuseReason = new StringBuffer();
            if(lnLoan.getLoanStatusId()==14){
                if("1".equals(lnLoanInfo.getRefuseReasonTypeId())){//银行拒绝
                    refuseReason.append("银行拒绝，").append(lnLoanInfoDictionaryService.getDictionaryValue("YYJJ",lnLoanInfo.getRefuseReasonSubTypeId()));
                }else if("2".equals(lnLoanInfo.getRefuseReasonTypeId())){//客户拒绝
                    refuseReason.append("客户拒绝，").append(lnLoanInfoDictionaryService.getDictionaryValue("KHJJ",lnLoanInfo.getRefuseReasonSubTypeId()));
                }
                if(null!=refuseReason){
                    HSSFRow row24 = sheet.getRow(24);
                    row24.getCell(3).setCellValue(refuseReason.toString());
                }
            }

            //填充第二十五行数据//提前还款
            HSSFRow row25 = sheet.getRow(25);
            if("1".equals(lnLoanInfo.getAdvanceRepay())){
                row25.getCell(3).setCellValue("√同意 □拒绝 ");
            }else if("0".equals(lnLoanInfo.getAdvanceRepay())){
                row25.getCell(3).setCellValue("□同意 √拒绝 ");
            }

            //填充第二十六行数据//是否算任务  新增|转贷
            HSSFRow row26 = sheet.getRow(26);
            if("1".equals(lnLoanInfo.getIsCrmTask())){
                row26.getCell(3).setCellValue("是否算任务  √是 □否");
            }else if("0".equals(lnLoanInfo.getIsCrmTask())){
                row26.getCell(3).setCellValue("是否算任务  □是 √否");
            }

            if("1".equals(lnLoanInfo.getLnMode())){
                row26.getCell(13).setCellValue("√新增    □转贷");
            }else if("2".equals(lnLoanInfo.getLnMode())){
                row26.getCell(13).setCellValue("□新增    √转贷");
            }
            HSSFRow row31 = sheet.getRow(31);
            row31.getCell(2).setCellValue(sdf.format(lnLoan.getApproveCenterPassDate()));

            /*
            //填充第七列数据
            HSSFRow row6 = sheet.getRow(6);
            HSSFRow rowCheckBox = sheet.getRow(208);
            if(lnLoanInfo.getManagerAdvice()!=null&& lnLoanInfo.getManagerAdvice().equals("1")){
                rowCheckBox.createCell(0).setCellValue(true);
                rowCheckBox.createCell(1).setCellValue(false);
            }else if (lnLoanInfo.getManagerAdvice()!=null&& lnLoanInfo.getManagerAdvice().equals("2")){
                rowCheckBox.createCell(0).setCellValue(false);
                rowCheckBox.createCell(1).setCellValue(true);
            }else{
                rowCheckBox.createCell(0).setCellValue(false);
                rowCheckBox.createCell(1).setCellValue(false);
            }
            row6.getCell(3).setCellValue("授信用途:  "+lnLoanInfo.getCreditApplication());

            */
            FileOutputStream os = new FileOutputStream(new File(path));
            book.write(os);
            os.close();
        }catch(Exception e){
            logger.error("", e);
        }
    }

    private String getJsonString(Object object) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new com.banger.mobile.util.JsonDateValueProcessor());
        String result = JSONObject.fromObject(object, jsonConfig).toString();
        return result;
    }

    private Object getObject(String jsonString,Object object) {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        return JSONObject.toBean(jsonObject, object.getClass());
    }

    /*
    * 导出贷款信息
    *
    * */

    public  void downLoanExcel(){
        try {
            //贷款id
            String loanId =  request.getParameter("loanId");

            String timePath = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());
            String path = FileUtil.contact(this.getSession().getServletContext().getRealPath("/"), "temp_file/export/贷款信息表" + timePath + ".xls");
            String dir = path.substring(0, path.lastIndexOf("/")); // 取出目录
            File file = new File(dir);
            // 检查文件是否存在
            File obj = new File(path);
            if (!file.exists()) { // 目录不存在，则创建相应的目录
                file.mkdirs();
                if (!obj.exists()) // 接下来创建具体文件
                    obj.createNewFile(); // 就是在这个点抛出异常
            }
            getdownLoanExcel(loanId, path);
            // 写流文件到前端浏览器
            ServletOutputStream out = this.getResponse().getOutputStream();
            this.getResponse().setHeader("Content-Disposition",
                    "attachment; filename=" + java.net.URLEncoder.encode("贷款信息表.xls", "UTF-8"));
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;

            bis = new BufferedInputStream(new FileInputStream(path));
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            bis.close();
            bos.close();
            obj.delete();
        } catch (Exception e) {
            log.error("", e);
        }
    }
    public void getdownLoanExcel(String loanId ,String path){
        try{
            //取得贷款信息
            Map<String,Object> param = new HashMap<String, Object>();
            param.put("loanId", loanId);
            LnLoanInfo lnLoanInfo = lnLoanInfoService.selectLoanInfoList(param).get(0);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Map<String,Object> condition = new HashMap<String, Object>();
            condition.put("loanId",loanId);
            List<LnPledge> pledgeList = lnLoanPledgeService.getLnLoanPledgeByLoanId(Integer.valueOf(loanId));
            List<LnLoanGuarantorBean> guarantorList = lnLoanGuarantorService.getAllLnLoanGuarantorBeanByConds(condition);
            CrmCustomer myboss= new CrmExportBean();
            List<LnRepaymentPlan> list =new ArrayList<LnRepaymentPlan>();
            list=LnRepaymentPlanService.queryLnRepaymentPlan(Integer.parseInt(loanId));


            myboss=crmCustomerService.getCustomerInfo(lnLoanInfo.getCustomerId());
            Map<String,Object> photoMap = new HashMap<String, Object>();
            photoMap.put("loanId",loanId);
            photoMap.put("statistics",1);
            lnLoanInfo = lnLoanInfoService.selectLoanInfoList(photoMap).get(0);
            List<LoanData> lists = customerDataService.getAllLoanDataById(photoMap);
            loanGuarantorList = lnLoanDetailService.getLoanGuList(Integer.parseInt(loanId), lists);

            LnLoanGuarantorBean lnLoanGuarantorBean=new LnLoanGuarantorBean();
          if(guarantorList.size()!=0){
              lnLoanGuarantorBean =guarantorList.get(0);
          }
            LnLoan lnLoan = lnLoanService.getLnLoanById(Integer.parseInt(loanId));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

            String rtefPath = request.getSession().getServletContext().getResource("/ini").getFile();
            if (!FileUtil.isExistFilePath(rtefPath)) {
                rtefPath = ServerRealPathUtil.getServerRealPath() + File.separator + "ini";
            }
            rtefPath = rtefPath + File.separator + "dlsp.xls";

            //取Excel模板
            POIFSFileSystem fs=new POIFSFileSystem(new FileInputStream(rtefPath));
            HSSFWorkbook book = new HSSFWorkbook(fs);

            //填充第一个工作表
            HSSFSheet sheet = book.getSheetAt(0);
            row(4,1,sheet,lnLoanInfo.getCusName());
            StringBuffer sstr=new StringBuffer();
            if(guarantorList.size()!=0){
                for(int i=0;i<guarantorList.size();i++){
                    if(i!=0){
                        sstr.append("，").append(guarantorList.get(i).getCustomerName());
                    }else{
                        sstr.append(guarantorList.get(i).getCustomerName());
                    }
                }
            }
            row(4,11,sheet,String.valueOf(sstr));
            row(5,1,sheet,String.valueOf(lnLoanInfo.getAppMoney()));
            row(5,11,sheet,lnLoanInfo.getAppLimitYear());
            if(StringUtils.isNotBlank(lnLoanInfo.getResultRepayDate())){
                row(6,1,sheet,("每个月"+lnLoanInfo.getResultRepayDate().toString()+"号"));
            }
            row(6,11,sheet,lnLoanInfo.getResultRate());
            loanDown(myboss,sheet,lnLoanInfo,1);
            Integer iop=11;
            if(loanGuarantorList.size()!=0){
                for(int i=0;i<loanGuarantorList.size() ;i++){
                    myboss=crmCustomerService.getCustomerInfo(loanGuarantorList.get(i).getCustomerId());
                    loanDown1(sheet,iop-2);
                    loanDown(myboss,sheet,lnLoanInfo,iop);
                    iop=iop+12;
                }
            }
            int rowNum = 74;
            if(pledgeList.size()!=0){
                for (int i=0;i< pledgeList.size();i++){
               /* loanDown2(Integer rowNum,  HSSFSheet sheet,LnLoanInfo lnLoanInfo, List<LnLoanGuarantorBean> guarantorList,List<LnPledge> pledgeList,List<LnRepaymentPlan> list,Integer i){*/
                    loanDown2(rowNum,sheet,lnLoanInfo,guarantorList,pledgeList,list,i);
                    rowNum = rowNum+30;
                }
            }
            // row(str+7,1,sheet,df.format(lnLoanInfo.getRegisterLoanDate()));
           FileOutputStream os = new FileOutputStream(new File(path));
            book.write(os);
            os.close();
        }catch(Exception e){
            logger.error("", e);
        }
    }


    public  void downTemplateExcel(){
        try {
            //贷款id
            String loanId =  request.getParameter("loanId");

            String timePath = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());
            String path = FileUtil.contact(this.getSession().getServletContext().getRealPath("/"), "temp_file/export/合同借据表" + timePath + ".xls");
            String dir = path.substring(0, path.lastIndexOf("/")); // 取出目录
            File file = new File(dir);
            // 检查文件是否存在
            File obj = new File(path);
            if (!file.exists()) { // 目录不存在，则创建相应的目录
                file.mkdirs();
                if (!obj.exists()) // 接下来创建具体文件
                    obj.createNewFile(); // 就是在这个点抛出异常
            }
            getdownTemplateExcel(loanId, path);
            // 写流文件到前端浏览器
            ServletOutputStream out = this.getResponse().getOutputStream();
            this.getResponse().setHeader("Content-Disposition",
                    "attachment; filename=" + java.net.URLEncoder.encode("合同借据表.xls", "UTF-8"));
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;

            bis = new BufferedInputStream(new FileInputStream(path));
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            bis.close();
            bos.close();
            obj.delete();
        } catch (Exception e) {
            log.error("", e);
        }
    }
    public void getdownTemplateExcel(String loanId ,String path){
        try{
            //取得贷款信息
            Map<String,Object> param = new HashMap<String, Object>();
            param.put("loanId", loanId);
            LnLoanInfo lnLoanInfo = lnLoanInfoService.selectLoanInfoList(param).get(0);
            List<LnRepaymentPlan> repaymentPlans = LnRepaymentPlanService.queryLnRepaymentPlan(Integer.parseInt(loanId)); //还款计划

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");

            LnLoanContract lnLoanContract = lnLoanContractService.getLnLoanContractById(Integer.parseInt(loanId));


            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

            String rtefPath = request.getSession().getServletContext().getResource("/ini").getFile();
            if (!FileUtil.isExistFilePath(rtefPath)) {
                rtefPath = ServerRealPathUtil.getServerRealPath() + File.separator + "ini";
            }
            rtefPath = rtefPath + File.separator + "template.xls";
            int mvp=0;
            int cqp=0;
            int i = 0;
            iou = (Iou)getObject(lnLoanContract.getIou(),new Iou());

            iou.setLoanBeginTime(lnLoanInfo.getRegisterLoanDate());
            if(repaymentPlans.size()!=1&& repaymentPlans.size()!=0){
                iou.setLoanEndTime(repaymentPlans.get(repaymentPlans.size()-1).getPlanDate());
            }
            POIFSFileSystem fs=new POIFSFileSystem(new FileInputStream(rtefPath));
            HSSFWorkbook book = new HSSFWorkbook(fs);

            if(iou!=null){
               //取Excel模板

               //填充第一个工作表
               HSSFSheet sheet = book.getSheetAt(0);
               row(0,11,sheet,iou.getProductCode());
               row(0,17,sheet,iou.getCode());
               row(1,4,sheet,iou.getCusName());
               row(1,15,sheet,iou.getCusIdCard());
               row(2,4,sheet,iou.getLoanType());
               row(2,11,sheet,iou.getLoanPurpose());
               row(2,22,sheet,"10.00");
               row(3,4,sheet,iou.getLoanBankAccount());
               row(3,14,sheet,iou.getDepositBankAccount());
               row(4,5,sheet,iou.getAmountWords());
               mvp= Integer.parseInt(iou.getAmountFigures());
               while(mvp>=10){
                   mvp=mvp/10;
                   i++;
               }
               mvp=Integer.parseInt(iou.getAmountFigures());
               for(int j=23;j>= 23-i;j--){
                   cqp=mvp%10;
                   mvp=mvp/10;
                   row(5,j,sheet,String.valueOf(cqp));
               }
               row(5,23-i-1,sheet,"￥");
              /* row(6,2,sheet,sf.format(iou.getLoanBeginTime()));

                if(repaymentPlans.size()!=1&& repaymentPlans.size()!=0){
                    row(6,9,sheet,sf.format(repaymentPlans.get(repaymentPlans.size()-1).getPlanDate()));
                }*/

                Calendar cb=Calendar.getInstance();
                cb.setTime(iou.getLoanBeginTime());
                row(6,2,sheet,String.valueOf(cb.get(Calendar.YEAR))+"   "+String.valueOf(cb.get(Calendar.MONTH)+1)+"    "+String.valueOf(cb.get(Calendar.DAY_OF_MONTH))+"     ");

                if(repaymentPlans.size()!=1&& repaymentPlans.size()!=0){
                    cb.clear();
                    Date po=repaymentPlans.get(repaymentPlans.size()-1).getPlanDate();
                    cb.setTime(po);
                    row(6,9,sheet,String.valueOf(cb.get(Calendar.YEAR))+"   "+String.valueOf(cb.get(Calendar.MONTH)+1)+"    "+String.valueOf(cb.get(Calendar.DAY_OF_MONTH))+"     ");
                }


               row(6,18,sheet,iou.getRepaymentType());
               int p=9;
               Calendar ca =Calendar.getInstance();
                List<RepaymentInfo> list= iou.getRepaymentInfoList();
                JSONArray ja = JSONArray.fromObject(list);
                iou.setRepaymentInfoList( JSONArray.toList(ja,new RepaymentInfo(),new JsonConfig()));
            /*   if(iou.getRepaymentInfoList()!=null){
                   Date da=sf.parse(iou.getRepaymentInfoList().get(0).getPlanDate());
                   Date td=sf.parse(iou.getRepaymentInfoList().get(0).getRepaymentDate());

                   for(int k =0;k<iou.getRepaymentInfoList().size();k++){
                       ca.clear();
                       ca.setTime(da);
                       ca.add(Calendar.MONTH, k);
                       row(p, 0, sheet, String.valueOf(ca.get(Calendar.YEAR)));
                       row(p, 1, sheet, String.valueOf(ca.get(Calendar.MONTH)+1));
                       row(p, 3, sheet, String.valueOf(ca.get(Calendar.DAY_OF_MONTH)));
                       row(p, 5, sheet, iou.getRepaymentInfoList().get(k).getPlanAmount());
                       ca.setTime(td);
                       ca.add(Calendar.MONTH,k);
                       row(p, 6, sheet, String.valueOf(ca.get(Calendar.YEAR)));
                       row(p, 7, sheet, String.valueOf(ca.get(Calendar.MONTH)+1));
                       row(p, 9, sheet, String.valueOf(ca.get(Calendar.DAY_OF_MONTH)));
                       row(p, 10, sheet, iou.getRepaymentInfoList().get(k).getRepaymentPrincipal());
                       row(p, 13,sheet,iou.getRepaymentInfoList().get(k).getRepaymentPrincipal());
                       row(p, 17,sheet,iou.getRepaymentInfoList().get(k).getRepaymentRemaining());
                       row(p, 22,sheet,iou.getRepaymentInfoList().get(k).getUserName());
                       p++;
                   }
               }*/
               row(15, 0,sheet,"兹根据：   "+iou.getContractNo()+"    合同办理此笔贷款，到期请凭此据收回贷款\r\n借款人：\r\n（盖章）\r\n法定代表人签章：\r\n（授权委托人）\r\n地址\r\n                   年       月       日 ");
               row(15,12,sheet,"银行审查意见:  \r\n\r\n法人代表签章\r\n（授权委托人）\r\n\r\n信贷员签章：\r\n                   (公章)\r\n                 年      月      日 ");
             }

            // row(str+7,1,sheet,df.format(lnLoanInfo.getRegisterLoanDate()));
            FileOutputStream os = new FileOutputStream(new File(path));
            book.write(os);
            os.close();
        }catch(Exception e){
            logger.error("", e);
            e.printStackTrace();
        }
    }

    public void row(int i,int j,HSSFSheet sheet ,String lnloaninfo){
        HSSFRow row1 = sheet.getRow(i);
        row1.getCell(j).setCellValue(lnloaninfo);
    }


    public static void main(String[] args){

        try {
        String encoding = "gb2312";
        File file = new File("C:\\Users\\Administrator\\Desktop\\中山农商行\\接口\\接口20070810\\tda_xw_cif.all");

        if(file.isFile()){

            InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            int i = 0;
            while (StringUtils.isNotBlank(lineTxt=bufferedReader.readLine())) {
                if(i>=100){
                    break;
                }
                i++;
                System.out.println(lineTxt);
            }
        }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
        public void loanDown(CrmCustomer myboss ,HSSFSheet sheet,LnLoanInfo lnLoanInfo,Integer kong){

            SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
           /* row(8,kong,sheet,myboss.getCustomerNo());*/
            row(8,kong,sheet,myboss.getCustomerTypeName());
            row(9,kong,sheet,myboss.getCustomerName());
            row(11,kong,sheet,myboss.getSex());

            row(12,kong,sheet,"中华人民共和国");

            if(lnLoanInfo.getCusIdtypeId()!=null){
                if(lnLoanInfo.getCusIdtypeId().equals("1")){  row(13,kong,sheet,"身份证");}
                if(lnLoanInfo.getCusIdtypeId().equals("2")){  row(13,kong,sheet,"户口本");}
                if(lnLoanInfo.getCusIdtypeId().equals("3")){  row(13,kong,sheet,"护照");}
                if(lnLoanInfo.getCusIdtypeId().equals("4")){  row(13,kong,sheet,"军官证");}
                if(lnLoanInfo.getCusIdtypeId().equals("5")){  row(13,kong,sheet,"士兵证");}
                if(lnLoanInfo.getCusIdtypeId().equals("6")){  row(13,kong,sheet,"港澳居民来往内地通行证");}
                if(lnLoanInfo.getCusIdtypeId().equals("7")){  row(13,kong,sheet,"台湾同胞来往内地通行证");}
                if(lnLoanInfo.getCusIdtypeId().equals("8")){  row(13,kong,sheet,"临时身份证");}
                if(lnLoanInfo.getCusIdtypeId().equals("9")){  row(13,kong,sheet,"外国人居留证");}
                if(lnLoanInfo.getCusIdtypeId().equals("10")){  row(13,kong,sheet,"警官证");}
                if(lnLoanInfo.getCusIdtypeId().equals("11")){  row(13,kong,sheet,"其他证件");}
            }
            row(14,kong,sheet,myboss.getIdCard());
            row(22,kong,sheet,myboss.getCensusRegister());
            row(23,kong, sheet, myboss.getAddress());
            if(myboss.getBirthday()!=null){
                row(24,kong,sheet,sf.format(myboss.getBirthday()));
            }
            Integer  str=myboss.getEducationalHistoryId();
            String  srt=myboss.getEducationalHistoryName();
            Integer io=myboss.getEducationalHistoryId();
            if(myboss.getEducationalHistoryId()!=null) {
                row(26, kong, sheet, myboss.getEducationalHistoryName());
            }
            if(StringUtils.isNotBlank(myboss.getCustomerIndustryName())){
                row(28,kong,sheet,"有");
            }else{
                row(28,kong,sheet,"无");
            }

            if(StringUtils.isNotBlank(myboss.getMaritalStatusName())){
                row(32,kong,sheet,myboss.getMaritalStatusName());
            }else{
                row(32,kong,sheet,"未婚");
            }
            if(StringUtils.isNotBlank(myboss.getMaritalStatusName())){
                if(myboss.getMaritalStatusName().equals("已婚")){
                    row(33,kong,sheet,"身份证");
                    row(34,kong,sheet,myboss.getSpouseIdCard());
                    row(36,kong,sheet,myboss.getSpouseName());

                    Integer inP= 0;
                    if(myboss.getSpousePosition()!=null){
                        inP= Integer.parseInt(myboss.getSpousePosition());
                    }

                    switch(inP){
                        case 1: row(38, kong, sheet,"法定代表人");break;
                        case 2: row(38, kong, sheet,"个体工商户负责人");break;
                        case 3: row(38, kong, sheet,"科长");break;
                        case 4: row(38, kong, sheet,"处长");break;
                        case 5: row(38, kong, sheet,"科员");break;
                        case 6: row(38, kong, sheet,"业务员");break;
                        case 7: row(38, kong, sheet,"行长");break;
                        case 8: row(38, kong, sheet,"副行长");break;
                        case 9: row(38, kong, sheet,"行长助理");break;
                        case 10: row(38,kong, sheet,"董事长");break;
                        case 11: row(38,kong, sheet,"总经理");break;
                        case 12: row(38,kong, sheet,"副总经理");break;
                        case 13: row(38,kong, sheet,"经理");break;
                        case 14: row(38,kong, sheet,"主管");break;
                        case 15: row(38,kong, sheet,"财务科长");break;
                        case 16: row(38,kong, sheet,"财务经理");break;
                        case 17: row(38,kong, sheet,"会计");break;
                        case 18: row(38,kong, sheet,"出纳");break;
                        case 19: row(38,kong, sheet,"厂长");break;
                        case 20: row(38,kong, sheet,"校长");break;
                        case 21: row(38,kong, sheet,"院长");break;
                        case 22: row(38,kong, sheet,"总务处长");break;
                        case 23: row(38,kong, sheet,"主任");break;
                        default:row(38, kong, sheet,"无");
                    }
                    row(39,kong,sheet,myboss.getSpouseCompany());
                    Integer ino=0;
                    if(myboss.getSpouseRank()!=null){
                        ino= Integer.parseInt(myboss.getSpouseRank());
                    }
                    switch(ino){
                        case 1: row(41, kong, sheet,"无");break;
                        case 2: row(41, kong, sheet,"技术员");break;
                        case 3: row(41, kong, sheet,"助理工程师");break;
                        case 4: row(41, kong, sheet,"工程师");break;
                        case 5: row(41, kong, sheet,"高级工程师");break;
                        case 6: row(41, kong, sheet,"会计员");break;
                        case 7: row(41, kong, sheet,"助理会计师");break;
                        case 8: row(41, kong, sheet,"会计师");break;
                        case 9: row(41, kong, sheet,"经济员");break;
                        case 10: row(41,kong, sheet,"助理经济师");break;
                        case 11: row(41,kong, sheet,"经济师");break;
                        case 12: row(41,kong, sheet,"高级经济师");break;
                        case 13: row(41,kong, sheet,"讲师");break;
                        case 14: row(41,kong, sheet,"副教授");break;
                        case 15: row(41,kong, sheet,"教授");break;
                        default:row(41,kong, sheet,"无");
                    }
                    row(42,kong, sheet, myboss.getSpouseMobilePhone());
                    row(43,kong,sheet,myboss.getSpouseCompanyPhone());
                    if(StringUtils.isNotBlank(lnLoanInfo.getCusMateMonthlyIncomingId())){
                        if(lnLoanInfo.getCusMateMonthlyIncomingId().equals("01")){   row(44,kong,sheet,"1000-2000");   }
                        if(lnLoanInfo.getCusMateMonthlyIncomingId().equals("02")){   row(44,kong,sheet,"2000-3000");   }
                        if(lnLoanInfo.getCusMateMonthlyIncomingId().equals("03")){   row(44,kong,sheet,"3000-4000");   }
                        if(lnLoanInfo.getCusMateMonthlyIncomingId().equals("04")){   row(44,kong,sheet,"4000-5000");   }
                        if(lnLoanInfo.getCusMateMonthlyIncomingId().equals("05")){   row(44,kong,sheet,"5000以上");   }
                    }
                    row(45,kong,sheet,myboss.getSpouseWorkingSeniority());
                }
            }
            row(47,kong,sheet,myboss.getLivingAddress());
            row(48,kong,sheet,myboss.getDwellCode());
            row(49,kong,sheet,myboss.getLivingAddress());
            if(myboss.getLivingConditionId()!=null){
                switch(myboss.getLivingConditionId()){
                    case 1: row(50, kong, sheet,"自置");break;
                    case 2: row(50, kong, sheet,"按揭");break;
                    case 3: row(50, kong, sheet,"亲属楼宇");break;
                    case 4: row(50, kong, sheet,"集体宿舍");break;
                    case 5: row(50, kong, sheet,"租房");break;
                    case 6: row(50, kong, sheet,"共有住宅");break;
                    case 7: row(50, kong, sheet,"其他");break;
                    case 8: row(50, kong, sheet,"未知");break;
                }
            }
            row(53,kong,sheet,myboss.getDwellCode());
            row(54,kong,sheet,myboss.getPhone());
            row(55,kong, sheet, myboss.getSpouseMobilePhone());
            row(56,kong,sheet,myboss.getFax());
            row(57,kong,sheet,myboss.getEmail());
            Integer inP= 0;
            if(myboss.getSpousePosition()!=null&& !myboss.getSpousePosition().equals("")){
                inP= Integer.parseInt(myboss.getSpousePosition());
            }
            switch(inP){
                case 1: row(59, kong, sheet,"法定代表人");break;
                case 2: row(59, kong, sheet,"个体工商户负责人");break;
                case 3: row(59, kong, sheet,"科长");break;
                case 4: row(59, kong, sheet,"处长");break;
                case 5: row(59, kong, sheet,"科员");break;
                case 6: row(59, kong, sheet,"业务员");break;
                case 7: row(59, kong, sheet,"行长");break;
                case 8: row(59, kong, sheet,"副行长");break;
                case 9: row(59, kong, sheet,"行长助理");break;
                case 10: row(59,kong, sheet,"董事长");break;
                case 11: row(59,kong, sheet,"总经理");break;
                case 12: row(59,kong, sheet,"副总经理");break;
                case 13: row(59,kong, sheet,"经理");break;
                case 14: row(59,kong, sheet,"主管");break;
                case 15: row(59,kong, sheet,"财务科长");break;
                case 16: row(59,kong, sheet,"财务经理");break;
                case 17: row(59,kong, sheet,"会计");break;
                case 18: row(59,kong, sheet,"出纳");break;
                case 19: row(59,kong, sheet,"厂长");break;
                case 20: row(59,kong, sheet,"校长");break;
                case 21: row(59,kong, sheet,"院长");break;
                case 22: row(59,kong, sheet,"总务处长");break;
                case 23: row(59,kong, sheet,"主任");break;
                default:row(59, kong, sheet,"无");
            }
            row(60,kong,sheet,myboss.getSpouseCompany());
            row(61,kong,sheet,myboss.getUnitProperty());
            row(62,kong,sheet,myboss.getCustomerIndustryName());
            row(64,kong,sheet,myboss.getManageAddress());
            row(66,kong,sheet,myboss.getWorktel());
            row(68,kong,sheet,myboss.getMarketingManager());
            row(69,kong,sheet,myboss.getBgnyear());
            Integer inw=0;
            if(myboss.getPosition()!=null){
                inw= Integer.parseInt(myboss.getPosition());
            }
            switch(inw){
                case 1: row(70, kong, sheet,"法定代表人");break;
                case 2: row(70, kong, sheet,"个体工商户负责人");break;
                case 3: row(70, kong, sheet,"科长");break;
                case 4: row(70, kong, sheet,"处长");break;
                case 5: row(70, kong, sheet,"科员");break;
                case 6: row(70, kong, sheet,"业务员");break;
                case 7: row(70, kong, sheet,"行长");break;
                case 8: row(70, kong, sheet,"副行长");break;
                case 9: row(70, kong, sheet,"行长助理");break;
                case 10: row(70,kong, sheet,"董事长");break;
                case 11: row(70,kong, sheet,"总经理");break;
                case 12: row(70,kong, sheet,"副总经理");break;
                case 13: row(70,kong, sheet,"经理");break;
                case 14: row(70,kong, sheet,"主管");break;
                case 15: row(70,kong, sheet,"财务科长");break;
                case 16: row(70,kong, sheet,"财务经理");break;
                case 17: row(70,kong, sheet,"会计");break;
                case 18: row(70,kong, sheet,"出纳");break;
                case 19: row(70,kong, sheet,"厂长");break;
                case 20: row(70,kong, sheet,"校长");break;
                case 21: row(70,kong, sheet,"院长");break;
                case 22: row(70,kong, sheet,"总务处长");break;
                case 23: row(70,kong, sheet,"主任");break;
                default:row(70, kong, sheet,"无");
            }

            Integer inq= 0;
            if(myboss.getRank()!=null){
                inq= Integer.parseInt(myboss.getRank());
            }
            switch(inq){
                case 1: row(71, kong, sheet,"无");break;
                case 2: row(71, kong, sheet,"技术员");break;
                case 3: row(71, kong, sheet,"助理工程师");break;
                case 4: row(71, kong, sheet,"工程师");break;
                case 5: row(71, kong, sheet,"高级工程师");break;
                case 6: row(71, kong, sheet,"会计员");break;
                case 7: row(71, kong, sheet,"助理会计师");break;
                case 8: row(71, kong, sheet,"会计师");break;
                case 9: row(71, kong, sheet,"经济员");break;
                case 10: row(71,kong, sheet,"助理经济师");break;
                case 11: row(71,kong, sheet,"经济师");break;
                case 12: row(71,kong, sheet,"高级经济师");break;
                case 13: row(71,kong, sheet,"讲师");break;
                case 14: row(71,kong, sheet,"副教授");break;
                case 15: row(71,kong, sheet,"教授");break;
                default:row(71,kong, sheet,"无");
            }
        }

        public void loanDown1(HSSFSheet sheet,Integer kong){

                row(8, kong, sheet, "客户类型*");
                row(9, kong, sheet, "姓名*");
                row(10, kong, sheet, "曾用名");
                row(11, kong, sheet, "性别");
                row(12, kong, sheet, "国别");
                row(13, kong, sheet, "证件类型*");
                row(14, kong, sheet, "证件号码*");
                row(15, kong, sheet, "证件到期日*");
                row(16, kong, sheet, "是否农户*");
                row(17, kong, sheet, "与我行的关系");
                row(18, kong, sheet, "拥有我行股份金额（元）");
                row(19, kong, sheet, "在我行职务");

                row(21, kong, sheet, "民族*");
                row(22, kong, sheet, "籍贯*");
                row(23, kong, sheet, "户籍地址*");
                row(24, kong, sheet, "出生日期*");
                row(25, kong, sheet, "政治面貌");
                row(26, kong, sheet, "最高学历*");
                row(27, kong, sheet, "最高学位");
                row(28, kong, sheet, "是否有工作单位*");
                row(29, kong, sheet, "下岗证号");
                row(30, kong, sheet, "健康状况*");

                row(32, kong, sheet, "婚姻状况");
                row(33, kong, sheet, "配偶证件类型*");
                row(34, kong, sheet, "配偶证件号码*");
                row(35, kong, sheet, "配偶客户号");
                row(36, kong, sheet, "配偶姓名");
                row(37, kong, sheet, "结婚证号\n" +
                        "（户口簿号）");
                row(38, kong, sheet, "职业");
                row(39, kong, sheet, "工作单位");
                row(40, kong, sheet, "职务");
                row(41, kong, sheet, "职称");
                row(42, kong, sheet, "手机号码");
                row(43, kong, sheet, "单位电话");
                row(44, kong, sheet, "年收入");
                row(45, kong, sheet, "参加工作年份");

                row(47, kong, sheet, "通讯地址*");
                row(48, kong, sheet, "邮政编码*");
                row(49, kong, sheet, "居住地址*");
                row(50, kong, sheet, "居住状况*");
                row(51, kong, sheet, "区域编号");
                row(52, kong, sheet, "区域名称");
                row(53, kong, sheet, "居住地邮政编号");
                row(54, kong, sheet, "住宅电话");
                row(55, kong, sheet, "手机电话");
                row(56, kong, sheet, "传真");
                row(57, kong, sheet, "Email地址");

                row(59, kong, sheet, "职业*");
                row(60, kong, sheet, "工作单位*");
                row(61, kong, sheet, "单位性质*");
                row(62, kong, sheet, "单位所属行业*");
                row(63, kong, sheet, "所属行业名称*");
                row(64, kong, sheet, "单位地址*");
                row(65, kong, sheet, "单位邮编*");
                row(66, kong, sheet, "单位电话");
                row(67, kong, sheet, "单位传真");
                row(68, kong, sheet, "单位联系人");
                row(69, kong, sheet, "单位工作起始年");
                row(70, kong, sheet, "职务");
                row(71, kong, sheet, "职称");
                row(72, kong, sheet, "年收入");
                row(73, kong, sheet, "工资账户开户行");
                row(74, kong, sheet, "工资账号");
                row(75, kong, sheet, "个人简历");
        }
        public void loanDown2(Integer rowNum,  HSSFSheet sheet,LnLoanInfo lnLoanInfo, List<LnLoanGuarantorBean> guarantorList,List<LnPledge> pledgeList,List<LnRepaymentPlan> list,Integer i){
            LnLoanGuarantorBean lnLoanGuarantorBean=new LnLoanGuarantorBean();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(guarantorList.size()!=0){
                lnLoanGuarantorBean =guarantorList.get(0);
            }
            row(rowNum, 0, sheet, "抵押物类型*");
            if( lnLoanInfo.getAppPledgeId()!=null) {
                if (lnLoanInfo.getAppPledgeId().equals("01")) {   row(rowNum, 1, sheet, "自然人持有的不动产");            }
                if (lnLoanInfo.getAppPledgeId().equals("02")) {   row(rowNum, 1, sheet, "商品住宅");           }
                if (lnLoanInfo.getAppPledgeId().equals("03")) {   row(rowNum, 1, sheet, "公司法人持有的不动产");   }
                if (lnLoanInfo.getAppPledgeId().equals("04")) {  row(rowNum, 1, sheet, "标准厂房");   }
                if (lnLoanInfo.getAppPledgeId().equals("05")) {  row(rowNum, 1, sheet, "机器设备");     }
                if (lnLoanInfo.getAppPledgeId().equals("06")) {  row(rowNum, 1, sheet, "车辆");        }
                if (lnLoanInfo.getAppPledgeId().equals("07")) { row(rowNum, 1, sheet, "其他"); }
            }

            row(rowNum+1,0,sheet,"抵押物主类*");
            row(rowNum+1,9,sheet,"抵押物子类*");
            row(rowNum+2,0,sheet,"抵押物细类*");
            row(rowNum+3,0,sheet,"抵押物名称*");
            if(lnLoanInfo.getCusLivingTypeId()!=null ){
                if(lnLoanInfo.getCusLivingTypeId().equals("01")){ row(rowNum+3,1,sheet,"高档商住楼");}
                if(lnLoanInfo.getCusLivingTypeId().equals("02")){ row(rowNum+3,1,sheet,"市区普通住宅");}
                if(lnLoanInfo.getCusLivingTypeId().equals("03")){ row(rowNum+3,1,sheet,"郊外小区");}
                if(lnLoanInfo.getCusLivingTypeId().equals("04")){ row(rowNum+3,1,sheet,"城中村");}
                if(lnLoanInfo.getCusLivingTypeId().equals("05")){ row(rowNum+3,1,sheet,"其他");}
            }
            row(rowNum+4,0,sheet,"抵押房地产座落");
            row(rowNum+5,0,sheet,"抵押人全称*");                 row(rowNum+5,1,sheet,lnLoanInfo.getCusName());
            row(rowNum+6,0,sheet,"身份证号码");                  row(rowNum+6,1,sheet,lnLoanInfo.getCusIdcard());
            row(rowNum+7,0,sheet,"抵押人贷款卡号");
            row(rowNum+8,0,sheet,"抵押人配偶");                  row(rowNum+8,1,sheet,lnLoanInfo.getCusMateName());
            row(rowNum+9,0,sheet,"房产共有权证号");
            row(rowNum+10,0,sheet,"联系电话");                   row(rowNum+10,1,sheet,lnLoanGuarantorBean.getCphNumber());
            row(rowNum+10,9,sheet,"抵押人法定代表人");          row(rowNum+10,11,sheet, lnLoanInfo.getCusName());
            row(rowNum+11,0,sheet,"房产所有权证号");
            row(rowNum+11,9,sheet,"土地使用权证号");
            row(rowNum+12,0,sheet,"不动产证号");                 row(rowNum + 12, 1, sheet, pledgeList.get(i).getTitleCertificate());
            row(rowNum+13,0,sheet,"抵押物的其他共有人");
            row(rowNum+13,9,sheet,"抵押物状况");
            row(rowNum+14,0,sheet,"土地权属性质");
            row(rowNum+14,9,sheet,"土地用途");
            row(rowNum+15,0,sheet,"土地使用权取得方式");
            row(rowNum+15,9,sheet,"是评估或是协议");
            row(rowNum+16,0,sheet,"土地使用期限开始日期");
            row(rowNum+16,9,sheet,"土地使用期限截止日期");
            row(rowNum+17,0,sheet,"土地面积(平方米)");
            row(rowNum+17,9,sheet,"抵押房地产建筑面积(平方米)*");
            row(rowNum+18,0,sheet,"是否在建工程");
            row(rowNum+18,9,sheet,"拖欠工程款额(万元)");
            row(rowNum+19,0,sheet,"项目预计总投入(万元)");
            row(rowNum+19,9,sheet,"项目实际投入(万元)");
            row(rowNum+20,0,sheet,"是否已出租");
            row(rowNum+20,9,sheet,"月租均价    (元/㎡)");
            row(rowNum+21,0,sheet,"出租年限开始日期");
            row(rowNum+21,9,sheet,"出租年限     截止日期");
            row(rowNum+22,0,sheet,"评估价值(万元)*");
            row(rowNum+22,9,sheet,"抵押物市值   (万元)");
            row(rowNum+23,0,sheet,"房产评估价值(万元)*");
            row(rowNum+23,9,sheet,"房产市值    (万元)*");
            row(rowNum+24,0,sheet,"土地评估价值(万元)*");
            row(rowNum+24,9,sheet,"土地市值    (万元)*");
            row(rowNum+25,0,sheet,"抵押物原值(万元)");
            row(rowNum+25,9,sheet,"抵押物净值   (万元)");
            row(rowNum+26,0,sheet,"建筑已办证面积  (平方米)");
            row(rowNum+26,9,sheet,"土地已办证面积(平方米)");
            row(rowNum+27,0,sheet,"评估单位及评估人*");
            row(rowNum+28,0,sheet,"评估时间*");                      row(rowNum+28,1,sheet,df.format(lnLoanInfo.getUpdateDate()));
            row(rowNum+29,0,sheet,"抵押物情况说明");                row(rowNum + 29, 1, sheet, pledgeList.get(i).getGoodsRemark());
            row(rowNum+30,0,sheet,"抵押登记情况：");
            row(rowNum+31,0,sheet,"他项权证号：");
            row(rowNum+31,9,sheet,"抵押登记部门");
            row(rowNum+32,0,sheet,"抵押登记金额");
            row(rowNum+33,0,sheet,"权属备案书号");
            row(rowNum+33,9,sheet,"对抵/质押物的评价*");
            row(rowNum+34,0,sheet,"是否被查封或有争议");
            rowNum= rowNum+36;

            rowNum=36+36*pledgeList.size();
            row(rowNum,0,sheet,"保险情况");
            row(rowNum+1,0,sheet,"保单号码");
            row(rowNum+2,0,sheet,"保险期限开始日期");
            row(rowNum+2,9,sheet,"保险期限     截止日期");
            row(rowNum+3,0,sheet,"保险金额(万元)");
            row(rowNum+3,9,sheet,"第一受偿人");
            row(rowNum+4,0,sheet,"保险种类");
            row(rowNum+4,9,sheet,"保险人");
            row(rowNum+5,0,sheet,"公证情况：");
            row(rowNum+6,0,sheet,"公证书号码：");
            row(rowNum+6,9,sheet,"是否具有强制执行效力");
            row(rowNum+7,0,sheet,"公证强制执行时效");
            row(rowNum+8,0,sheet,"公证机关");
            row(rowNum+9,0,sheet,"公证员");
            row(rowNum+10,0,sheet,"贷款合同要素：");
            row(rowNum+11,0,sheet,"主合同类型*");
            row(rowNum+12,0,sheet,"内部合同编号*");
            row(rowNum+13,0,sheet,"产品类型*");
            if (lnLoanInfo.getAppLoanTypeId().equals("1")){ row(rowNum+13,1,sheet,"经营贷");}//84
            if (lnLoanInfo.getAppLoanTypeId().equals("2")){ row(rowNum+13,1,sheet,"消费贷");}
            row(rowNum+14,0,sheet,"合同编号*");            row(rowNum+14,1,sheet,lnLoanInfo.getLendContractNum());
            row(rowNum+14,9,sheet,"借款种类*");
            row(rowNum+15,0,sheet,"贷款人*");              row(rowNum+15,1,sheet,"中山农商银行");
            row(rowNum+15,9,sheet,"借款人*");              row(rowNum+15,11,sheet,lnLoanInfo.getCusName());
            row(rowNum+16,0,sheet,"贷款金额(元)");        row(rowNum+16,1,sheet,lnLoanInfo.getResultMoney());
            row(rowNum+16,9,sheet,"币种*");                row(rowNum+16,11,sheet,"人民币");
            row(rowNum+17,0,sheet,"贷款期限(月)");        row(rowNum+17,1,sheet,lnLoanInfo.getResultLimitYear());
            row(rowNum+17,9,sheet,"担保方式");
            if(lnLoanInfo.getAppGuarantorWayId()!=null) {
                if (lnLoanInfo.getAppGuarantorWayId().equals("01")) {
                    row(rowNum+17, 11, sheet, "抵押");
                }
                if (lnLoanInfo.getAppGuarantorWayId().equals("02")) {
                    row(rowNum+17, 11, sheet, "质押");
                }
                if (lnLoanInfo.getAppGuarantorWayId().equals("03")) {
                    row(rowNum+17, 11, sheet, "保证");
                }
                if (lnLoanInfo.getAppGuarantorWayId().equals("04")) {
                    row(rowNum+17, 11, sheet, "信用");
                }
            }
            row(rowNum+18,0,sheet,"基准利率%*");
            row(rowNum+18,9,sheet,"利率浮动幅度%*");
            row(rowNum+19,0,sheet,"贷款利率%");       row(rowNum+19,1, sheet,lnLoanInfo.getResultRate());
            row(rowNum+19,9,sheet,"贷款用途*");       row(rowNum+19,11, sheet,lnLoanInfo.getAppLoanPurpose());
            row(rowNum+20,0,sheet,"其他发放贷款的前提条件");row( rowNum+20, 1, sheet, df.format(lnLoanInfo.getRegisterLoanDate()));//69
            row(rowNum+21,0,sheet,"开户银行");
            row(rowNum+22,0,sheet,"账户户名");
            row(rowNum+22,9,sheet,"账户账号");
            row(rowNum+23,0,sheet,"贷款开始日期*");row(rowNum+23,1,sheet,df.format(lnLoanInfo.getRegisterLoanDate()));
            row(rowNum+23,9,sheet,"贷款结束日期*");
            if(list.size()!=0){
                list.get(list.size()-1).getPlanDate();
                row(rowNum+23, 11, sheet, df.format(list.get(list.size()-1).getPlanDate()));//69
            }
            row(rowNum+24,0,sheet,"还款方式*");
            if(lnLoanInfo.getAdviceRepayWayId()!=null) {
                if (lnLoanInfo.getAdviceRepayWayId().equals("1")) {
                    row(rowNum+24, 1, sheet, "等额本息");
                }
                if (lnLoanInfo.getAdviceRepayWayId().equals("2")) {
                    row(rowNum+24, 1, sheet, "等额本金");
                }
                if (lnLoanInfo.getAdviceRepayWayId().equals("3")) {
                    row(rowNum+24, 1, sheet, "按月还息，到期还本");
                }
                if (lnLoanInfo.getAdviceRepayWayId().equals("4")) {
                    row(rowNum+24, 1, sheet, "约定还款");
                }
                if (lnLoanInfo.getAdviceRepayWayId().equals("5")) {
                    row(rowNum+24, 1, sheet, "随借随还");
                }
            }
            row(rowNum+24,9,sheet,"还款周期*");

            if(lnLoanInfo.getResultRepayDate().toString()!=null){
                row(rowNum+24,11,sheet,("每个月"+lnLoanInfo.getResultRepayDate().toString()+"号"));

            }
            row(rowNum+25,0,sheet,"本金逾期浮动比例%");
            row(rowNum+25,9,sheet,"复息利率浮动比例%");
            row(rowNum+26,0,sheet,"挤占挪用利率浮动比例%");
            row(rowNum+26,9,sheet,"贷款人违约利率浮动比例%");
            row(rowNum+27,0,sheet,"借款人违约的     违约金比例%*");
            row(rowNum+28,0,sheet,"签约地点");
            row(rowNum+29,9,sheet,"签约时间*");
            row(rowNum+30,0,sheet,"合同份数");
            row(rowNum+30,9,sheet,"经办信贷员");
            row(rowNum+31,0,sheet,"其他事项约定");
        }

}
