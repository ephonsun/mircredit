package com.banger.mobile.dao.loan;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.loan.CusCheck;
import com.banger.mobile.domain.model.loan.LnLoan;
import com.banger.mobile.domain.model.loan.LnLoanCustomerBean;
import com.banger.mobile.domain.model.loan.LnLoanInfo;
import com.banger.mobile.domain.model.loan.NoLoanInfo;
import com.banger.mobile.domain.model.report.LoanCountReportBean;
import com.banger.mobile.domain.model.report.LoanTypeTotalReportBean;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-6
 * Time: 上午9:13
 * To change this template use File | Settings | File Templates.
 */
public interface LnLoanDao {
    PageUtil<LnLoan> getLnLoanListPage(Map<String, Object> parameterMap, Page page);
    /**
     * 根据参数得到模糊查找的客户数据
     * @param conds
     * @return
     */
    public List<LnLoanCustomerBean> getAllCustomerBeanByConds(Map<String, Object> conds);

    public LnLoan selectLoanById(Integer loanId);
    public LnLoan getLnLoanById(Integer loanId);

    void updateLnLoanById(Map<String, Object> paramMap);
    
    /**
     * 得到贷款 NEXT SEQ 值 
     * @return
     */
    public Integer getNextLoanId();

    void updateLnLoanBatch(List<Map<String, Object>> list);

    void insertLoan(LnLoan lnLoan);

    void insertLoanBatch(List<LnLoan> lnLoans);

    Integer getLoanStatusById(Map<String, Object> paramMap);

    Integer queryChangedLoanStatusCount(Map<String,Object> paramMap);

    void deleteLoan(Integer loanId);

    List<LnLoan> queryLnLoanRelation(Map<String,Object> paramMap);
    
    /**
     * 查询所有贷款客户数据信息根据参数
     * @param paramMap
     * @return
     */
    public List<LnLoan> getAllLoanByConds(Map<String, Object> paramMap);

    Integer selectLoanCount(Map<String, Object> paramMap);

    LnLoan selectLoanList(Map<String, Object> paramMap);

    List<LnLoan> selectAllLoanList(Map<String,Object> paramMap);

    List<Integer> getLoanUserList(Map<String, Object> paramMap);

    PageUtil<LnLoan> getAllLoanByCusId(Map<String, Object> paramMap, Page page);

    List<LnLoan> getAllLoanByCusIds(Map<String, Object> paramMap);

    void mergeCusLoan(Map<String, Object> paramMap);

    List<LnLoan> getRecentlyLoanStatusByCusIds(Map<String, Object> paramMap);

    Integer getLoanCountByCusId(Map<String,Object> paras);

    Integer getLoanCountByTask(Map<String, Object> param);

    List<LnLoan> getCountByStatus(Map<String, Object> paramMap);

    List<LnLoan> getLoanCountByLoanStatus(Map<String, Object> paramMap);

    List<LnLoan> getCountByLoanStatusId(Map<String, Object> paramMap);

    List<LnLoan> getAllLoanByUserId(Map<String, Object> paramMap);

    List<LnLoan> getLoanCountByLoanStatusId(Map<String, Object> paramMap);

    Integer getApproveFailCount(Map<String, Object> paramMap);

    List<LnLoan> getNowNeedHandleLoanList(Map<String, Object> paramMap);

    List<LnLoan> getNewLoanForWarning(Map<String, Object> paramMap);

    PageUtil<LnLoanCustomerBean> getAllCustomerBeanByConds(Map<String,Object> conds, Page page);

    Integer getCustomerLoanCount(Map<String, Object> paramMap);

    LnLoan getLastLoanByCustomer(Integer customerId);

    Integer getVerifyAssignCount(Map<String ,Object> paramMap);

    List<LnLoan> getVerifyAssignList (Map<String ,Object> paramMap);

    Integer getLoanCountByStatusId(Integer loanStatusId);

    List<LnLoanInfo> getLoanByStatusId(Map<String, Object> paramMap);

    Integer updateLoanPartFieldBatch(List<LnLoan> lnLoanList);

    Integer getNotEndLoanCount(Integer customerId);

    Integer getLoanId(Map<String, Object> param);

    List<LnLoan> getLoanByCustomerId(Integer customerId);

    Integer updateLoanOwnerByCustomerIdBatch(List<Map<String, Object>> mapList);

    PageUtil<LnLoan> getApplyLoanByPage(Map<String, Object> parameterMap, Page page);

    Integer getAssessLoanCount(Map<String, Object> paramMap);

    LnLoan getAssessLoanAlone(Map<String, Object> paramMap);

    List<LnLoan> getLoanCountByLoanStatusIdOptimize(Map<String, Object> paramMap);

    List<LnLoan> getWPLoanListOptimize(Map<String, Object> paramMap);

    PageUtil<LnLoan> getAllLoanByPage(Map<String, Object> parameterMap, Page page);

    Integer getMakeLoanCount(Map<String, Object> paramMap);

    PageUtil<LnLoan> getMakeLoanPage(Map<String, Object> parameterMap, Page page);
    PageUtil<LnLoan> getMakeExLoanPage(Map<String, Object> parameterMap, Page page);
    Integer getVerifyLoanCount(Map<String, Object> paramMap);

    PageUtil<LnLoan> getVerifyLoanPage(Map<String, Object> parameterMap, Page page);

    Integer selectDunLoanCount(Map<String, Object> paramMap);

    void updateLoanByRepaymentPlan(LnLoan lnLoan);

    Integer getNoLoanInfoLoanCount();

    List<NoLoanInfo> getNoLoanInfoLoanList(Map<String, Object> paramMap);

    List<LoanCountReportBean> selectLoanCountReportByStatus(Map<String, Object> paramMap);

    void updateLnLoanByIdBatch(List<Map<String, Object>> paramMapList);

    void updateLoanByLoanId (LnLoan lnLoan);

    void updateLoanBatch(List<LnLoan> lnLoanList);

    List<LnLoan> getAllLoanNoPage(Map<String, Object> parameterMap);

    List<Integer> getPartDELLoanIdByMap(Integer userId);

    List<Integer> getAllDELLoanIdByMap(Integer userId);
	/**
	 * 
	 * @param lnLoan
	 */
	void insertLoanSelective(LnLoan lnLoan);
	
	/**
	 * 查询当前用户正在审批贷款的数量
	 * @param userId
	 * @return
	 */
	Integer countApproveLoanBySysUserId(Map<String, Object> parameterMap);
	
	void doSyncDBJob();
	/**
	 * 身份验证
	 * @return
	 */
	List<CusCheck> cusCheck(String cusIds,Integer loanId);
	/**
	 * 清空流程审批内容,同时备份数据
	 * @param lnLoan
	 */
	void clearApproveDataAndBakMsg(LnLoan lnLoan);
	
	void clearApproveData(Integer loanId);
	
	void saveApproveDataBakMsg(LnLoan loanId);
	
	/**
	 * 异常催收
	 * @param parameterMap
	 * @return
	 */
	Integer getMakExLoanCont(Map<String, Object> parameterMap);

	
	/**
	 * 贷款调查回退
	 * @param lnloanId
	 */
	void updateGoBackLoanSurveyById(Integer lnloanId);

	
	
	void updateAssign(Integer loanId);

	public void submitLend(Integer loanId);

    LnLoan getUnCheckLoanByContractNo(String contNo);

    List<LnLoan> selectListByUserAndDate(Map map);

    BigDecimal selectAmountByStatus(Map map);

    LoanTypeTotalReportBean getLoanTypeTotalSum(Map map);
    BigDecimal getLoanAmountType(Map map);

    LoanTypeTotalReportBean getAllMonthLoanBal(Map map);

    LoanTypeTotalReportBean getKDBMonthLoanBal(Map map);
}
