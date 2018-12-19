/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :贷款模块webservice
 * Author     :zhangfp
 * Create Date:2013-3-14
 */
package com.banger.mobile.facade.webservice;



import javax.jws.WebService;

/**
 * @author zhangfp
 * @version $Id: LoanWebService.java v 0.1 ${} 上午11:42 Administrator Exp $
 */
@WebService
public interface LoanWebService {

    // 3.67 待提交贷款列表
    String getNeedSubmitLoanList(String account, String input, Integer pageNumber);

    String getNeedAssignLoanList(String account, String input, Integer submitUserId,
                                 Integer pageNumber);

    String getNeedResearchLoanList(String account, String input, Integer pageNumber);

    String getNeedCheckoutLoanList(String account, String input, Integer pageNumber,Integer appLoanTypeId,Integer date,Integer notMonitoredType);

    String getNeedRepaymentLoanList(String account, String input, Integer repaymentStatus,
                                    Integer pageNumber);

    String getExceptionDunLoanList(String account, String input, Integer pageNumber);

    String getAllLoanList(String account, String input, Integer loanStatusId, Integer pageNumber);

    String getLoanDetail(String account, Integer loanId);

    String addLoan(String account, String loanJsonString);

    String editLoan(String account, String loanJsonString);

    String assignLoan(String account, Integer loanId, String remark, Integer surveyUserId);

    String assignRefuseLoan(String account, Integer loanId, String remark, Integer cancelReasonId,
                      String cancelReasonOther);

    String checkoutLoan(String account, Integer loanId, String remark);

    String setNogoodCustomer(String account, Integer loanId,String remark);

    String dunLoan(String account, Integer loanId,Integer dunLogId, String remark);

    String dunExceptionLoan(String account, Integer loanId, Integer exceptionDunLogId, String remark);
    
    String getLoanType(String account);

    String getCustomerLoanList(Integer customerId, Integer pageNumber);

    /**
     * 得到撤销原因
     * 
     * @param account
     * @return
     */
    //public String getCancelReason(String account);
    
    public String refuseLoan(String account,Integer loanId,String refuseType,String refuseReason,String reasonOtherValue);//贷款拒绝
    
    // 删除待提交贷款图片
    public String  deletePhoto(String account, String uuid);

    String applyLoan(String account, Integer assignUserId, Integer loanId, String remark);

    String getAssignUserList(String account,Integer loanId);

    public String togetherSurveySubmit(String name, String password,String account,Integer loanId);//陪调提交
    
    public String saveSurveyForm(String account,Integer loanId,String adviceMoney,String adviceLimitYear,String adviceRate,String adviceRepayDate,String adviceLendWayId,String adviceRepayWayId,String adviceOther, String adviceVerdict);//调查保存
    public String saveResultForm(String account, Integer loanId,String resultNote,String isPass,String isShow,String resultJson);

        public String submitWaitApprove(String account, Integer loanId, Integer chooseSubmitType);
    
    public Integer getNewLoanId();

    public Integer getNewDunLogId();
    
    public Integer getNewExceptionDunLogId();

    public String deleteLoan(String account, Integer loanId);

    String getStatusLoanCount(String account);

    String isLoanFail(String account, Integer customerId);

    String isNoGood(String account, Integer customerId);

    String numNowNeedSubmitLoan(String account, Integer time,Integer time1);

    String numNewNeedAssignLoan(String account);

    String numNowNeedAssignLoan(String account, Integer time,Integer time1);

    String numNewBeenAssignedLoan(String account);

    String numNowNeedResearchLoan(String account, Integer time,Integer time1);

    String numNowNeedRepaymentLoan(String account, Integer time,Integer time1);

    String queryCustomerByFilter(String account,String type, String idCard, String customerName, Integer pageNumber, String filter);

    // 申请贷款-搜索客户（身份证&姓名）
    public String queryCustomerByIdCardPhone(String account, String idCard, String customerName,
                                             Integer pageNumber);
    
    // 更换贷款人-搜索客户（身份证&联系电话，姓名）
    public String queryCustomerByIdCardPhoneName(String account, String idCard, String customerName,
                                             String phone,Integer pageNumber);

    String  getLendingLoanList(String account, String input, Integer page);

    String  getApprovalLoanList(String account, String input, Integer page);

    String  searchApprovalLoan(String account, String condition);

    String applyRefuse(String account, Integer loanId, String remark, Integer cancelReasonId, String cancelReasonOther,String refuseType);

    String  surveyRefuse(String account, Integer loanId, String remark, Integer cancelReasonId, String cancelReasonOther);

    String  approvalPass(String account, Integer loanId, String remark, String otherAccount, String approvalUser1Date);

    String approvalReject(String account, Integer loanId, String remark, String otherAccount, String approvalUser1Date);

    String  approvalRefuse(String account, Integer loanId,String remark, Integer cancelReasonId,
                           String cancelReasonOther,String otherAccount,String approvalTime);

    //验证共同审批人
    public String getApprovalUser(String account,Integer loanId,String approvalAccount,String password);

    String  changeLoan(String account, String loanJsonString);

    String  writeLoanHistory(String account, Integer loanId, Integer loanStatusId, String remark);

    String addEditPhotoRemark(String account, String uuid, String remark);

    String editRecordRemark(String account, String uuid, String remark);

    String editVideoRemark(String account, String uuid, String remark);

    String addEditPhotoType(String account, String uuid, Integer typeId,String customerName,Integer eventId);

    String  deleteGua(String account, Integer loanId, Integer customerId);

    String addGua(String account, Integer loanId,Integer guaCustomerId,Integer oldGuaCustomerId,Integer isKownLoan,Integer isException,String checkMessage, String companyAddress, String petitionerRelate,String company);

    String  markLoanRemainEnough(String account, Integer loanId, Integer isEnough);

    String getLoanInfos(Integer loanId);

    String queryRepaymentPlanList(String loanId, String money, String month, String day, String rate,String type);

    String saveRepaymentPlan(String loanIdStr, String dayStr, String account, String jsonString);

    String checkRepaymentPlan(String loanIdStr, String moneyStr, String monthStr, String dayStr);

    String  getFillingList(String account, Integer days, Integer sort);

    public String saveAppForm(String account,Integer loanId,String type, String appFormJson);
    

    public String getAppForm (String account,Integer loanId);

    public String getAppFormDics(String type);

    String getManagerUserList(String account);

    public String getEnlendTotal(String account);

    public String getEnlendList(String account,String level, String manageLevel, String input, Integer page, Integer order);

    public String addEnlendHistory(String account, Integer enlendingId, String operateWay, String afterStatus, String remark);

    public String quitEnlend(String account, Integer enlendingId, String quitReason, String remark);

    public String getEnlendHistoryList(String account, Integer enlendingId);

    public String getCmsAllFileList(Integer loanId);

    public String editCmsPhotoType(String  datUuid, Integer photoTypeId);

    public String uploadFileToCms(Integer loanId);

    public String saveRejectCustomer(String account, String customerName, String mobilePhone, String idCard, String useage, String remark);

    public String saveResultInfo(String account,Integer loanId,String resultLimitYear,String resultMoney,String resultRate,String resultPoint,String lowerRate);

    public String getResultInfo(String account,Integer loanId);

    public String getLoanRemarkHistory(String account,Integer loanId);

    public String getAppFormGua(String account,Integer loanId);

    public String updateApplyCusInfo(String account,Integer loanId,String customerName,String idcard,String phone);

    public String requestCreditRef(String account, Integer loanId, Integer customerId, Integer customerType, String requestReason, Integer idauthFlag, String remark);

    public String getRequestResult(String account, Integer loanId, Integer customerId,Integer customerType);

    public String getRequestResultHtml(String account, Integer loanId, Integer customerId);

    public String addFeedBack(String account,String titleName,String modular,String description,String filename);

    public String getOnLnFilling(String account,Integer fillingId);

    public String deleteFiles(String account);

    public String changePassword(String account,String password);

    public String checkCustomer(String account,String loanType,String cusIdType,String cusIdCard);

    public String getXDJG(String account);//获取个人信息 信贷机构号 机构名称

    public String addCob(String account, Integer loanId, Integer cobCustomerId, Integer oldCobCustomerId,Integer isKownLoan,Integer isException,String checkMessage, String companyAddress, String petitionerRelate,String company);//新增共同借款人

    public String deleteCob(String account, Integer loanId, Integer customerId);//删除共同借款人
    
    public String addPledge(Integer pledgeId, Integer loanId, String goods, String goodsValue, String ownerName, String titleCertificate,String pledgeRate,String goodsAmount, String goodsRemark); //添加抵制价物
    
    public String deletePledge(Integer pledgeId);//删除抵质押物
    
    public String getPledgeList(Integer loanId); //查询抵质押物列表
    
    public String commonProblem(String account,String mobile);

    public String getXDStatus(String account,String loanId);
    
    /**
     * 获取拒绝原因列表
     * @param account
     * @param type 银行拒绝：YHJJ|客户拒绝：KHJJ
     * @return
     */
    public String getCancelReason (String account,String type);
    
    /**
     * 获取检查客户信息
     * @param account
     * @param loanId
     * @return
     */
    public String checkHistoryInfo(String account,Integer loanId);
    
    /**
     * 添加信贷记录
     * @param account
     * @param loanId
     * @param chJson
     * @return
     */
    public String addCh(String account,Integer loanId,  String chJson);
    
    /**
     * 修改信贷记录
     * @param account
     * @param loanId
     * @param chJson
     * @return
     */
    public String editCh(String account,Integer loanId,  String chJson);
    
    /**
     * 删除信贷记录
     * @param account
     * @param loanId
     * @param chId
     * @return
     */
    public String delCh(String account,Integer loanId,  Integer chId);
    
    /**
     * 获取信贷记录
     * @param account
     * @param loanId
     * @return
     */
    public String getChInfo(String account, Integer loanId);
    
    
    public String getAssignUserList(String account);

    /**
     * 贷款分配时获取用户列表
     * @param account
     * @param loanId
     * @return
     */
    public String searchUser(String account,Integer loanId);
    
    public String getLoanMonitorList(String account,Integer loanId);
    
    public String saveLoanMonitor(String account,Integer loanMonId,Integer loanId,Integer monType, Integer revisitType,String userResult,String customerStatus,String bizStatus,
                                  String financeStatus,String guarantorStatus,String ledgeStatus);

    /**
     * 
     * @param
     * @param loanId
     * @return
     */
    public String assignTheallback(String account,Integer loanId);
    
    public String goBackLoanSurvey(String account,Integer loanId);
    
    public String getIsCustomerManageTag(String account);

    //充盈管理
    String getFullingLoanList(String account, String cusName, String contractNo);

    //贷款统计
    String getLoanStatistics(String account);

    //转贷管理 --搜索和查看结清贷款
    String getCleanLoanList(String account, String cusName, String contractNo);

    //转贷管理 --客户分级 查询 a存量客户数量	b未转贷客户	c退出客户
    String getLoanCustomerList(String account, String cusName, String level);


    String getCustomerLevel(String account);

    public String setCustomerLevel(Integer customerId,String flag );

    public String delLoan(Integer loanId);

    public String getLoanCountAndAmountList();

    public String getApprovalData(Integer loanId);

}


