/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :担保人-Dao-接口实现
 * Author     :QianJie
 * Create Date:Feb 17, 2013
 */
package com.banger.mobile.dao.loan.ibatis;

import com.banger.mobile.dao.loan.LnLoanGuarantorDao;
import com.banger.mobile.domain.model.loan.LnLoanGuarantor;
import com.banger.mobile.domain.model.loan.LnLoanGuarantorBean;
import com.banger.mobile.ibatis.GenericDaoiBatis;
import com.banger.mobile.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author QianJie
 * @version $Id: LnLoanGuarantorDaoiBatis.java,v 0.1 Feb 17, 2013 2:54:46 PM QianJie Exp $
 */
public class LnLoanGuarantorDaoiBatis extends GenericDaoiBatis implements LnLoanGuarantorDao {
    
    public LnLoanGuarantorDaoiBatis() {
        super(LnLoanGuarantor.class);
    }
    /**
     * @param persistentClass
     */
    public LnLoanGuarantorDaoiBatis(Class persistentClass) {
        super(LnLoanGuarantor.class);
    }
    
    /**
     * 查询所有担保人数据
     * @param conds
     * @return
     * @see com.banger.mobile.dao.loan.LnLoanGuarantorDao#getAllLnLoanGuarantorBeanByConds(java.util.Map)
     */
    public List<LnLoanGuarantorBean> getAllLnLoanGuarantorBeanByConds(Map<String, Object> conds) {
        Map<String,Object> fConds = new HashMap<String, Object>();
        for(Map.Entry<String, Object> entry : conds.entrySet())   
        { 
            if(entry.getValue() instanceof String){
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString())));
            }else{
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        List<LnLoanGuarantorBean> list =this.getSqlMapClientTemplate().queryForList("getAllLnLoanGuarantorBeanByConds",fConds);
        return list;
    }

    public void addLnLoanGuarantor(LnLoanGuarantor gu) {
        this.getSqlMapClientTemplate().insert("addLnLoanGuarantor", gu);
    }

    public void deleteLnLoanGuarantor(Integer guId) {
        this.getSqlMapClientTemplate().delete("deleteLnLoanGuarantor", guId);
    }

    /**
     * 批量插入担保人
     * @param guarantorList
     * @return
     */
    @Override
    public int addLnLoanGuarantorBatch(List<LnLoanGuarantor> guarantorList){
        return this.exectuteBatchInsert("addLnLoanGuarantor",guarantorList);
    }

    /**
     * 批量更新担保人
     * @param guarantorList
     * @return
     */
    @Override
    public int updateLoanGuarantorBatch(List<LnLoanGuarantor> guarantorList){
        return this.executeBatchUpdate("updateLoanGuarantorById",guarantorList);
    }

    /**
     * 根据贷款ID删除其相关的担保人
     * 
     * @param loanId
     */
    @Override
    public void deleteLoanGuarantor(Integer loanId){
        this.getSqlMapClientTemplate().delete("deleteLoanGuarantorById",loanId);
    }

    @Override
    public Integer getGuarantorCount(Map<String,Object> paramMap){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getGuarantorCount",paramMap);
    }

    @Override
    public Integer updateLoanGuarantor(Map<String,Object> paramMap){
        return (Integer) this.getSqlMapClientTemplate().update("updateLoanGuarantor",paramMap);
    }

    /**
     * 根据贷款和客户查找惟一的担保人
     * @param paramMap
     * @return
     */
    public LnLoanGuarantor selectGuarantorSingle(Map<String,Object> paramMap){
        return (LnLoanGuarantor)this.getSqlMapClientTemplate().queryForObject("selectGuarantorSingle",paramMap);
    }

    /**
     * 根据贷款的相关客户删除担保人
     * @param paramMap
     */
    public void delGuaByLoanCustomerId(Map<String,Object> paramMap){
        this.getSqlMapClientTemplate().delete("delGuaByLoanCustomerId",paramMap);
    }

    /**
     * 根据客户id列表删除不在该列表中的担保人
     * @param paramMap
     */
    public void delDeletedGuaByCusList(Map<String,Object> paramMap){
        this.getSqlMapClientTemplate().delete("delDeletedGuaByCusList",paramMap);
    }

    /**
     * 搜索某贷款的所有客户id
     * 
     * @param loanId
     * @return
     */
    public List<Integer> getCusIdListByLoanId(Integer loanId){
        return (List<Integer>)this.getSqlMapClientTemplate().queryForList("getCusIdListByLoanId",loanId);
    }

    /**
     *
     * @param paramMap
     * @return
     */
    public Integer updateLoanGuarantorByLoan(Map<String,Object> paramMap){
        return (Integer)this.getSqlMapClientTemplate().update("updateLoanGuarantorByLoan",paramMap);
    }
	@Override
	public void deleteLnLoanGuarantorByLoanId(Integer loanId) {
		this.getSqlMapClientTemplate().delete("deleteLnLoanGuarantorByLoanId", loanId);
	}
    
    
}
