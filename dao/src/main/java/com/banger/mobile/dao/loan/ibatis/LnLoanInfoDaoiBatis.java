package com.banger.mobile.dao.loan.ibatis;

import com.banger.mobile.dao.loan.LnLoanInfoDao;
import com.banger.mobile.domain.model.loan.LnLoanInfo;
import com.banger.mobile.ibatis.GenericDaoiBatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ouyl
 * @version $Id: LnLoanInfoDaoiBatis.java,v 0.1 13-6-25 ouyl Exp $
 */
public class LnLoanInfoDaoiBatis extends GenericDaoiBatis implements LnLoanInfoDao {

    public LnLoanInfoDaoiBatis(){
        super(LnLoanInfo.class);
    }

    public LnLoanInfoDaoiBatis(Class persistentClass) {
        super(persistentClass);
    }
    /**
     * 插入贷款信息
     * @param lnLoanInfo
     */
    public void insertLnLoanInfo(LnLoanInfo lnLoanInfo) {
        this.getSqlMapClientTemplate().insert("insertLnLoanInfo",lnLoanInfo);
    }

    /**
     * 更新贷款信息
     * @param lnLoanInfo
     */
    public void updateLnLoanInfo(LnLoanInfo lnLoanInfo) {
        this.getSqlMapClientTemplate().update("updateLnLoanInfo",lnLoanInfo);
    }

    /**
     * 根据流水号查找贷款信息
     * @param param
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<LnLoanInfo> selectLoanInfoList(Map<String, Object> param){
        return this.getSqlMapClientTemplate().queryForList("selectLoanInfoList",param);
    }

    /**
     * 更新贷款账号字段
     * @param param
     */
    public void updateLoanInfoLoanAccount(Map<String, Object> param){
        this.getSqlMapClientTemplate().update("updateLoanInfoLoanAccount",param);
    }

    /**
     *批量插入贷款申请信息
     * @param lnLoanInfoList
     */
    public void insertLnLoanInfoBatch(List<LnLoanInfo> lnLoanInfoList) {
        this.exectuteBatchInsert("insertLnLoanInfo",lnLoanInfoList);
    }

    /**
     * 批量更新
     * @param lnLoanInfoList
     */
    public void updateLnLoanInfoBatch(List<LnLoanInfo> lnLoanInfoList) {
        this.executeBatchUpdate("updateLnLoanInfo",lnLoanInfoList);
    }

    @Override
    public void updateLnLoanInfoWhitOutNull(LnLoanInfo lnLoanInfo) {
        this.getSqlMapClientTemplate().update("updateLnLoanInfoWhitOutNull",lnLoanInfo);
    }

    /**
     * 批处理
     * @param lnLoanInfoList
     */
    @Override
    public void updateLnLoanInfoWhitOutNullBatch(List<LnLoanInfo> lnLoanInfoList) {
        this.executeBatchUpdate("updateLnLoanInfoWhitOutNull",lnLoanInfoList);
    }

	@Override
	public void insertLnLoanInfoSelective(LnLoanInfo lnLoanInfo) {
		this.getSqlMapClientTemplate().insert("insertLnLoanInfoSelective", lnLoanInfo);
	}

	@Override
	public void deleteLoan(Integer loanId) {
		this.getSqlMapClientTemplate().delete("deleteLoanInfo", loanId);
	}
	
	public String getAppLoanTypeIdByLoanId(Integer loanId){
		Object obj = this.getSqlMapClientTemplate().queryForObject("getAppLoanTypeIdByLoanId",loanId);
		if(obj == null){
			return "";
		}		
    	return (String)obj;
	}
	public String getAdviceMoneyByLoanId(Integer loanId){
		Object obj = this.getSqlMapClientTemplate().queryForObject("getAdviceMoneyByLoanId",loanId);
		if(obj == null){
			return "";
		}		
    	return (String)obj;
	}

}
