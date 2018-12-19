package com.banger.mobile.dao.loan.ibatis;

import com.banger.mobile.dao.loan.AnaCoBorrowerAndGuarantorDao;
import com.banger.mobile.domain.model.base.loan.BaseAnaCoBorrower;
import com.banger.mobile.domain.model.base.loan.BaseAnaGuarantor;
import com.banger.mobile.ibatis.GenericDaoiBatis;

import java.util.List;
import java.util.Map;

/**
 * @author zhangfp
 * @version $Id: AnaCoBorrowerAndGuarantorDaoiBatis v 0.1 ${} 下午3:13 zhangfp Exp $
 *
 * 上会分析表的共同借贷人和担保人dao
 */
public class AnaCoBorrowerAndGuarantorDaoiBatis extends GenericDaoiBatis implements AnaCoBorrowerAndGuarantorDao {
    /**
     * Constructor that takes in a class to see which type of entity to persist
     *
     * @param persistentClass the class type you'd like to persist
     */
    public AnaCoBorrowerAndGuarantorDaoiBatis(Class persistentClass) {
        super(persistentClass);
    }

    public AnaCoBorrowerAndGuarantorDaoiBatis(){
        super(BaseAnaCoBorrower.class);
    }

    /**
     * 得到贷款所有共同借贷人信息（属：上会分析表）
     * 
     * @param paramMap
     * @return
     */
    public List<BaseAnaCoBorrower> getAllCoBorrowerListByLoan(Map<String, Object> paramMap){
       return (List<BaseAnaCoBorrower>)this.getSqlMapClientTemplate().queryForList("getAllCoBorrowerListByLoan",paramMap);
    }

    /**
     * 得到贷款所有担保人信息（属：上会分析表）
     * 
     * @param paramMap
     * @return
     */
    public List<BaseAnaGuarantor> getAllGuarantorListByLoan(Map<String,Object> paramMap){
        return (List<BaseAnaGuarantor>)this.getSqlMapClientTemplate().queryForList("getAllGuarantorListByLoan",paramMap);
    }

    /**
     * 删除贷款共同借贷人
     * @param loanId
     */
    public void delAllCoBorrowerByLoan(Integer loanId){
        this.getSqlMapClientTemplate().delete("delAllCoBorrowerByLoan",loanId);
    }

    /**
     * 删除贷款担保人
     * @param loanId
     */
    public void delAllGuarantorByLoan(Integer loanId){
        this.getSqlMapClientTemplate().delete("delAllGuarantorByLoan",loanId);
    }

    /**
     *
     * @param anaCoBorrower
     */
    public void insertCoBorrowerOfLoan(BaseAnaCoBorrower anaCoBorrower){
        this.getSqlMapClientTemplate().insert("insertCoBorrowerOfLoan",anaCoBorrower);
    }

    /**
     *
     * @param baseAnaGuarantor
     */
    public void insertGuarantorOfLoan(BaseAnaGuarantor baseAnaGuarantor){
        this.getSqlMapClientTemplate().insert("insertGuarantorOfLoan",baseAnaGuarantor);
    }

    /**
     * 批量插入
     * @param baseAnaCoBorrowerList
     */
    public void insertCoBorrowerOfLoanBatch(List<BaseAnaCoBorrower> baseAnaCoBorrowerList){
        this.exectuteBatchInsert("insertCoBorrowerOfLoan",baseAnaCoBorrowerList);
    }

    /**
     * 批量插入
     * @param baseAnaGuarantorList
     */
    public void insertGuarantorOfLoanBatch(List<BaseAnaGuarantor> baseAnaGuarantorList){
        this.exectuteBatchInsert("insertGuarantorOfLoan",baseAnaGuarantorList);
    }
}
