package com.banger.mobile.facade.loan;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.loan.*;
import com.banger.mobile.domain.model.pad.PadLoan;
import com.banger.mobile.domain.model.pad.PadLoanInfo;
import com.banger.mobile.domain.model.report.LoanCountReportBean;
import com.banger.mobile.domain.model.report.LoanTypeTotalReportBean;
import com.banger.mobile.domain.model.user.IUserInfo;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-6
 * Time: 上午9:38
 * To change this template use File | Settings | File Templates.
 *
 * 贷款主表
 */
public interface LnLoanService {
    //分页查询
    PageUtil<LnLoan> getLnLoanListPage(Map<String, Object> parameterMap, Page page);

    //根据id查找
    LnLoan getLnLoanById(Integer loanId);

    LnLoan selectLoanById(Integer loanId);
    void updateLnLoanById(Map<String, Object> paramMap);

    @Transactional(propagation = Propagation.REQUIRED)
    void cancelLoan(Map<String, Object> paramMap, LnOpHistory lnOpHistory);

    /**
     * 根据参数得到模糊查找的客户数据
     * @param conds
     * @return
     */
    public List<LnLoanCustomerBean> getAllCustomerBeanByConds(Map<String, Object> conds);
    
    /**
     * 得到贷款 NEXT SEQ 值 
     * @return
     */
    public Integer getNextLoanId();

    @Transactional(propagation = Propagation.REQUIRED)
    void allotLoanBatch(List<Map<String, Object>> list, Map<String,String> cusParamMap,List<LnOpHistory> lnOpHistoryList);

    @Transactional(propagation = Propagation.REQUIRED)
    void allotLoanSingle(Map<String, Object> paramMap, CrmCustomer customer,LnOpHistory lnOpHistory);

    void insertLoan(LnLoan lnLoan);

    void insertLoanBatch(List<LnLoan> lnloans);

    Integer getLoanStatusById(Map<String, Object> paramMap);

    Integer queryChangedLoanStatusCount(Map<String,Object> paramMap);
    
    @Transactional(propagation = Propagation.REQUIRED)
    void deleteLoan(Integer loanId);

    List<LnLoan> queryLnLoanRelation(Map<String,Object> paramMap);
    
    /**
     * 查询所有贷款客户数据信息根据参数
     * @param paramMap
     * @return
     */
    public List<LnLoan> getAllLoanByConds(Map<String, Object> paramMap);

    @Transactional(propagation = Propagation.REQUIRED)
    void submitApproval(LnLoan lnLoan, LnOpHistory lnOpHistory);

    Integer selectLoanCount(Map<String, Object> paramMap);

    LnLoan selectLoanList(Map<String, Object> paramMap);

    List<LnLoan> selectAllLoanList(Map<String,Object> paramMap);

    /**
     * 查看用户所能审批的最大贷款额度
     * @param userId 用户id(优先查询)
     * @param roleIds 角色id(其次查询)
     * @return 贷款额度
     */
    BigDecimal approveLimitMoney(Integer userId,List<Integer> roleIds);

    @Transactional(propagation = Propagation.REQUIRED)
    void approveLoan(Map<String, Object> paramMap, LnOpHistory lnOpHistory);

    @Transactional(propagation = Propagation.REQUIRED)
    void addLoanForPad(LnLoan lnLoan, LnLoanInfo lnLoanInfo ,LnOpHistory lnOpHistory);

    @Transactional(propagation = Propagation.REQUIRED)
    void editLoanForPad(Map<String, Object> loanParamMap, List<LnLoanCoBorrower> coBorrowerList, List<LnLoanGuarantor> guarantorList,Boolean isNew,Integer userId);

    @Transactional(propagation = Propagation.REQUIRED)
    void checkoutLoan(Map<String, Object> loanParamMap, LnOpHistory lnOpHistory);

    @Transactional(propagation = Propagation.REQUIRED)
    void setNogoodCustomer(Map<String, Object> loanParamMap, LnOpHistory lnOpHistory, Map<String, Object> customerParamMap);

    @Transactional(propagation = Propagation.REQUIRED)
    void editRepaymentStatus(Map<String, Object> loanParamMap,Map<String, Object> eLoanParamMap, LnOpHistory lnOpHistory);

    @Transactional(propagation = Propagation.REQUIRED)
    void assignExpDunLoan(List<LnExceptionDunAssign> dunAssign, List<LnOpHistory> opHistory);
    @Transactional(propagation = Propagation.REQUIRED)
    void editPaidMoney(Map<String, Object> loanParamMap,Map<String, Object> eLoanParamMap);

    @Transactional(propagation = Propagation.REQUIRED)
    void editPaidPrincipal(Map<String, Object> loanParamMap,Map<String, Object> eLoanParamMap);

    @Transactional(propagation = Propagation.REQUIRED)
    void reAssignExpDunLoan(List<Integer> loanIdList, List<LnExceptionDunAssign> dunAssignList, List<LnOpHistory> lnOpHistoryList);

    String getNoLoanUserList(String customerIds);

    //添加审计备注
    @Transactional(propagation = Propagation.REQUIRED)
    void addVerifyRemark(Map<String, Object> param,LnVerifyHistory lnVerifyHistory);

    PageUtil<LnLoan> getAllLoanByCusId(Map<String,Object> paramMap, Page page);

    List<LnLoan> getAllLoanByCusIds(String cusIds);

    void mergeCusLoan(String needMergeCusIds, String mergeEndCusId);

    @Transactional(propagation = Propagation.REQUIRED)
    void applyLoan(Map<String, Object> loanParamMap, LnOpHistory lnOpHistory);

    Map<Integer,String> getRecentlyLoanStatusByCusIds(String customerIds);

    @Transactional(propagation = Propagation.REQUIRED)
    void syncLoan(Map<String, Object> paramMap, LnOpHistory lnOpHistory);

    Integer getLoanCountByCusId(Integer customerId);

    //统计贷款任务进度
    Integer getLoanCountByTask(Map<String, Object> param);

    List<LnLoan> getCountByStatus(Map<String, Object> paramMap);

    Map<Integer,Integer> getLoanCountByLoanStatus(Integer userId);

    List<LnLoan> getCountByLoanStatusId(Map<String, Object> paramMap);

    Integer getLoanCountByLoanStatusId(Map<String, Object> paramMap);

    List<LnLoan> getAllLoanByUserId(Integer userId);

    List<LnLoan> getLoanListByStatus(Integer userId, Integer status);

    Boolean isApproveFail(Map<String, Object> paramMap);

    List<LnLoan> getNowNeedHandleLoanList(Map<String, Object> paramMap, Integer hour,Integer afterHour);

    List<LnLoan> getNewLoanForWarning(Map<String, Object> paramMap);

    @Transactional(propagation = Propagation.REQUIRED)
    void dunLoan(Map<String,Object> lnLoanMap, LnOpHistory lnOpHistory);

    PageUtil<LnLoanCustomerBean> getAllCustomerBeanByConds(Map<String,Object> conds, Page page);

    void updateLoanBatch(List<Map<String, Object>> list);

    @Transactional(propagation = Propagation.REQUIRED)
    void submit(String fileAttachments,CustomerData data,Integer dataCustomerId,Integer userId,Map<String, Object> paramMap, LnOpHistory lnOpHistory);

    @Transactional(propagation = Propagation.REQUIRED)
    void approve(String fileAttachments, CustomerData data, Integer datCustomerDataId, Integer userId, Map<String, Object> paramMap, LnOpHistory lnOpHistory);

    @Transactional(propagation = Propagation.REQUIRED)
    void refuseLoan(String fileIds, CustomerData data, Integer datCustomerDataId, Integer userId, Map<String, Object> paramMap, LnOpHistory lnOpHistory);

    void rmCoBorrower(Integer coId, Integer loanId, Integer customerId);

    void rmGuarantor(Integer guId, Integer loanId, Integer customerId);

    @Transactional
    void changeLoanPerson(Integer loanId, Integer oldCustomerId, Integer customerId,LnOpHistory lnOpHistory);

    @Transactional
    void relatedLoanAttachment(Integer loanId, Integer customerId, Integer fileId, Integer userId,Integer eventId);

    Integer getCustomerLoanCount(Integer customerId);

    LnLoan getLastLoanByCustomer(Integer customerId);

    Map<Integer,Integer> getLoanCountByLoanStatus(Integer userId, Integer loanStatusId);

    Integer getVerifyAssignCount(Map<String ,Object> paramMap);

     List<LnLoan> getVerifyAssignList (Map<String ,Object> paramMap);

    @Transactional(propagation = Propagation.REQUIRED)
    void assignVerify(Map<String ,Object> paramMap,List<LnVerifyHistory> lnVerifyHistoryList);

    Integer getLoanCountByStatusId(Integer loanStatusId);

    List<LnLoanInfo> getLoanByStatusId(Map<String, Object> paramMap);

    Integer updateLoanPartFieldBatch(List<LnLoan> lnLoanList);

    Boolean isNotEndLoan(Integer customerId);

    Integer getLoanId(Map<String, Object> param);

    Integer updateLoanOwnerByCustomerIdBatch(List<Map<String, Object>> mapList);

    List<LnLoan> getLoanByCustomerId(Integer customerId);

    void changeLoanOwner(Integer customerId);

    void proCusAuthority(Boolean inChargeOf, String belongUserIds, PageUtil<LnLoan> dataList, IUserInfo userInfo);

    void proCusAuthority(Boolean inChargeOf, String belongUserIds, List<LnLoan> dataList, IUserInfo userInfo);

    void backLoan(Map<String, Object> paramMap, LnOpHistory opHistory);

    PageUtil<LnLoan> getApplyLoanByPage(Map<String, Object> parameterMap, Page page);

    LnLoan getAssessLoanAlone(Map<String, Object> paramMap);

    Integer getAssessLoanCount(Map<String, Object> paramMap);

    PageUtil<LnLoan> getAllLoanByPage(Map<String, Object> parameterMap, Page page);

    Integer getMakeLoanCount(Map<String, Object> paramMap);

    PageUtil<LnLoan> getMakeLoanPage(Map<String, Object> parameterMap, Page page);
    Integer getMakExloanCont(Map<String, Object> parameterMap);
    PageUtil<LnLoan> getMakeExLoanPage(Map<String, Object> parameterMap, Page page);
    Integer getVerifyLoanCount(Map<String, Object> paramMap);

    PageUtil<LnLoan> getVerifyLoanPage(Map<String, Object> parameterMap, Page page);

    @Transactional
    void addGu(Integer loanId, Integer customerId, Integer userId,Integer isKownLoan,Integer isException,String checkMessage, String companyAddress, String petitionerRelate);

    Integer selectDunLoanCount(Map<String, Object> paramMap);

    void updateLoanByRepaymentPlan(LnLoan lnLoan);

    Date addSecondsForDate(Date tempDate, Integer seconds);

    Date addHMSForDate(Date tempDate, Integer hour, Integer minute, Integer seconds);

    File getLoanExportDataFile(Map<String, Object> paramMap, Integer loanId) ;

    List<NoLoanInfo> getNoLoanInfoLoanList(Map<String, Object> paramMap);

    Integer getNoLoanInfoLoanCount();

    List<LoanCountReportBean> selectLoanCountReportByStatus(Map<String, Object> paramMap);

    void updateLnLoanByIdBatch(List<Map<String, Object>> paramMapList);

    void updateGu(Integer loanId, Integer oldCustomerId, Integer newCustomerId, Integer eventId, Integer userId, Integer isKownLoan, Integer isException, String checkMessage, String companyAddress, String petitionerRelate);

    List<LnLoan> getAllLoanNoPage(Map<String, Object> parameterMap);

    void getLoanExportListFile(List<LnLoan> dataList, String path);

    void updateLoanByLoanId(LnLoan lnLoan);

    void updateLoanByLnLoanBatch(List<LnLoan> lnLoanList);

    List<Integer> getPartDELLoanIdByMap(Integer userId);

    List<Integer> getAllDELLoanIdByMap(Integer userId);

    void addCob(Integer loanId, Integer customerId, Integer userId,Integer isKownLoan,Integer isException,String checkMessage, String companyAddress, String petitionerRelate);

    void updateCob(Integer loanId, Integer oldCustomerId, Integer newCustomerId, Integer eventId, Integer userId,Integer isKownLoan,Integer isException,String checkMessage, String companyAddress, String petitionerRelate);

    void removeCob(Integer guId, Integer loanId, Integer customerId);

    PageUtil<PadLoan> getPadLoanByPage(Map<String, Object> parameterMap, Page page);

	PadLoan getPanLoanById(int loanId);
	
	int getPadLoanCountByLoanStatus(Map<String, Object> parameterMap);
	
	void insertLoanSelective(LnLoan lnLoan);

	/**
	 * 查询当前用户正在审批贷款的数量
	 * 
	 * @param userId
	 * @return
	 */
	public Integer countApproveLoanBySysUserId(Map<String, Object> parameterMap);

	/**
	 * 
	 * @param lnLoan
	 * @param lnLoanInfo
	 * @param lnOpHistory
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	void submitApproveLoan(LnLoan lnLoan, LnLoanInfo lnLoanInfo,
			LnOpHistory lnOpHistory);
	
	void doSyncDBJob();
	void editLoanForPad (Map<String, Object> loanParamMap, LnLoanInfo lnLoanInfo, LnOpHistory lnOpHistory);

	/**
	 * 身份验证
	 * @return
	 */
	List<CusCheck> cusCheck(String cusIds,Integer loanId);

	/**
	 * 清空流程审批内容,同时备份数据
	 * @param loanId
	 */
	void clearApproveDataAndBakMsg(String loanId);
	
	void clearApproveData(Integer loanId);
	
	String saveApproveDataBakMsg(String loanId);
	
	/**
	 * 贷款调查回退
	 * @param loanId
	 */
	void updateGoBackLoanSurveyById(Integer loanId);

	void updateAssign(Integer loanId);

	void submitLend(Integer loanId);

    LnLoan getUnCheckLoanByContractNo(String contNo);

    List<LnLoan> selectListByUserAndDate(Map map);

    BigDecimal selectAmountByStatus(Map map);

    /**
     * 得到经营贷/消费贷的笔数
     * @param map 贷款类型、期限
     */
    LoanTypeTotalReportBean getLoanTypeTotalSum(Map map);

    /**
     * 得到经营贷/消费贷的期限金额
     * @param map
     * @return
     */
    BigDecimal getLoanAmountType(Map map);

    LoanTypeTotalReportBean getAllMonthLoanBal(String start,String end,Integer loanType,Integer repayWayId);

    LoanTypeTotalReportBean getKDBMonthLoanBal(String start,String end,Integer loanType,String flag);
}
