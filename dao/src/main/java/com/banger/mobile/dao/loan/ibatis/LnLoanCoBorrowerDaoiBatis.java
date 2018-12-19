/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :共同借贷人-Dao-接口实现
 * Author     :QianJie
 * Create Date:Feb 17, 2013
 */
package com.banger.mobile.dao.loan.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.loan.LnLoanCoBorrowerDao;
import com.banger.mobile.domain.model.loan.LnLoanCoBorrower;
import com.banger.mobile.domain.model.loan.LnLoanCoBorrowerBean;
import com.banger.mobile.ibatis.GenericDaoiBatis;
import com.banger.mobile.util.StringUtil;

/**
 * @author QianJie
 * @version $Id: LnLoanCoBorrowerDaoiBatis.java,v 0.1 Feb 17, 2013 2:54:46 PM QianJie Exp $
 */
public class LnLoanCoBorrowerDaoiBatis extends GenericDaoiBatis implements LnLoanCoBorrowerDao {
    
    public LnLoanCoBorrowerDaoiBatis() {
        super(LnLoanCoBorrower.class);
    }
    /**
     * @param persistentClass
     */
    public LnLoanCoBorrowerDaoiBatis(Class persistentClass) {
        super(LnLoanCoBorrower.class);
    }

    /**
     * 查询所有共同借贷人数据
     * @param conds
     * @return
     * @see com.banger.mobile.dao.loan.LnLoanCoBorrowerDao#getAllLnLoanCoBorrowerBeanByConds(java.util.Map)
     */
    public List<LnLoanCoBorrowerBean> getAllLnLoanCoBorrowerBeanByConds(Map<String, Object> conds) {
        Map<String,Object> fConds = new HashMap<String, Object>();
        for(Map.Entry<String, Object> entry : conds.entrySet())   
        { 
            if(entry.getValue() instanceof String){
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString())));
            }else{
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        return this.getSqlMapClientTemplate().queryForList("getAllLnLoanCoBorrowerBeanByConds",fConds);
    }

    public void addLnLoanCoBorrower(LnLoanCoBorrower co) {
        this.getSqlMapClientTemplate().insert("addLnLoanCoBorrower", co);
    }

    public void deleteLnLoanCoBorrower(Integer coId) {
        this.getSqlMapClientTemplate().delete("deleteLnLoanCoBorrower", coId);
    }

    /**
     * 批量插入贷款共同借贷人
     * @param coBorrowerList
     * @return
     */
    @Override
    public int addLnLoanCoBorrowerBatch(List<LnLoanCoBorrower> coBorrowerList){
        return this.exectuteBatchInsert("addLnLoanCoBorrower",coBorrowerList);
    }

    /**
     * 批量更新共同借贷人
     * @param coBorrowerList
     * @return
     */
    @Override
    public int updateLoanCoBorrowerBatch(List<LnLoanCoBorrower> coBorrowerList){
        return this.executeBatchUpdate("updateCoBorrowerById",coBorrowerList);
    }

    /**
     * 根据贷款ID删除相关的共同借贷人
     * 
     * @param loanId
     */
    @Override
    public void deleteLoanCoBorrowerById(Integer loanId){
        this.getSqlMapClientTemplate().delete("deleteCoBorrowerById",loanId);
    }

    /**
     *
     * @param paramMap
     * @return
     */
    @Override
    public Integer getCoBorrowerCount(Map<String,Object> paramMap){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getCoBorrowerCount",paramMap);
    }

    @Override
    public int updateCoBorrower(Map<String,Object> paramMap){
        return (Integer)this.getSqlMapClientTemplate().update("updateCoBorrower",paramMap);
    }


    @Override
    public LnLoanCoBorrower seleteCoBorrower(Map<String, Object> map) {
        return (LnLoanCoBorrower)this.getSqlMapClientTemplate().queryForObject("seleteCoBorrower",map);
    }
}
