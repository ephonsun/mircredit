package com.banger.mobile.dao.loan;

import com.banger.mobile.domain.model.loan.LnLoanInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ouyl
 * @version $Id: LnLoanInfoDao.java,v 0.1 13-6-25 ouyl Exp $
 */
public interface LnLoanInfoDao {

    /**
     * 插入贷款信息
     * @param lnLoanInfo
     */
    public void insertLnLoanInfo(LnLoanInfo lnLoanInfo);

    /**
     * 更新贷款信息
     * @param lnLoanInfo
     */
    public void updateLnLoanInfo(LnLoanInfo lnLoanInfo);

    /**
     * 根据流水号查找贷款信息
     * @param serialNumber
     * @return
     */
    public List<LnLoanInfo> selectLoanInfoList(Map<String, Object> param);

    /**
     * 更新贷款账号字段
     * @param param
     */
    public void updateLoanInfoLoanAccount(Map<String, Object> param);

    void insertLnLoanInfoBatch(List<LnLoanInfo> lnLoanInfoList);

     void updateLnLoanInfoBatch(List<LnLoanInfo> lnLoanInfoList);

     void updateLnLoanInfoWhitOutNull(LnLoanInfo lnLoanInfo);

    void updateLnLoanInfoWhitOutNullBatch(List<LnLoanInfo> lnLoanInfoList);

	/**
	 * 
	 * @param lnLoanInfo
	 */
	public void insertLnLoanInfoSelective(LnLoanInfo lnLoanInfo);

	/**
	 * 
	 * @param loanId
	 */
	public void deleteLoan(Integer loanId);

	public String getAppLoanTypeIdByLoanId(Integer loanId);
	public String getAdviceMoneyByLoanId(Integer loanId);
}
