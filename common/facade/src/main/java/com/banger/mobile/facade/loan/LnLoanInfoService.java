package com.banger.mobile.facade.loan;

import com.banger.mobile.domain.model.loan.LnLoanInfo;
import com.banger.mobile.domain.model.pad.PadLoanInfo;

import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ouyl
 * @version $Id: LnLoanInfoService.java,v 0.1 13-6-20 ouyl Exp $
 */
public interface LnLoanInfoService {

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
     * @param param
     * @return
     */
    public List<LnLoanInfo> selectLoanInfoList(Map<String, Object> param);

    /**
     * 根据字符串添加贷款表中客户信息
     * @param loan
     */
    public void addLoanInfoByLoan(String loan) throws Exception;

    /**
     * 更新贷款账号字段
     * @param param
     */
    public void updateLoanInfoLoanAccount(Map<String, Object> param);

    /**
     * 同步贷款状态
     * @param msg
     * @throws Exception
     */
    public void synchronousLoanStatus(String msg) throws Exception;

    void insertLnLoanInfoBatch(List<LnLoanInfo> lnLoanInfoList);

    void updateLnLoanInfoBatch(List<LnLoanInfo> lnLoanInfoList);

    @Transactional
    void execLoanInfo(List<Map<String, Object>> paramMapList, List<LnLoanInfo> lnLoanInfoList);

    void updateLnLoanInfoWhitOutNull(LnLoanInfo lnLoanInfo);

    void updateLnLoanInfoWhitOutNullBatch(List<LnLoanInfo> lnLoanInfoList);

    
	PadLoanInfo getPanLoanInfoById(int loanId);

	
	/**
	 * 
	 * @param lnLoanInfo
	 */
	public void insertLnLoanInfoSelective(LnLoanInfo lnLoanInfo);

	/**
	 * 备份申请资料信息
	 * @param loanId
	 */
	void bakAppForm(Integer loanId);
	
	public String getAppLoanTypeIdByLoanId(Integer loanId);
	public String getAdviceMoneyByLoanId(Integer loanId);
}
