package com.banger.mobile.dao.loan.ibatis;

import com.banger.mobile.dao.loan.AnaApplyInfoDao;
import com.banger.mobile.domain.model.loan.AnaApplyInfo;
import com.banger.mobile.domain.model.loan.AnaChildren;
import com.banger.mobile.domain.model.loan.ApplyAddressInfo;
import com.banger.mobile.ibatis.GenericDaoiBatis;

import java.util.List;
import java.util.Map;

/**
 * @author zhangfp
 * @version $Id: AnaApplyInfoDaoiBatis v 0.1 ${} 上午10:32 zhangfp Exp $
 *
 * 上会分析表--申请人信息dao
 */
public class AnaApplyInfoDaoiBatis extends GenericDaoiBatis implements AnaApplyInfoDao{
    /**
     * Constructor that takes in a class to see which type of entity to persist
     *
     * @param persistentClass the class type you'd like to persist
     */
    public AnaApplyInfoDaoiBatis(Class persistentClass) {
        super(persistentClass);
    }

    public AnaApplyInfoDaoiBatis(){
        super(AnaApplyInfo.class);
    }

    /**
     * 查询某贷款的上会分析表的申请人信息
     * 
     * @param paramMap
     * @return
     */
    public AnaApplyInfo selectApplyInfoByLoanId(Map<String, Object> paramMap){
        return (AnaApplyInfo)this.getSqlMapClientTemplate().queryForObject("selectApplyInfoByLoanId",paramMap);
    }

    /**
     * 查询具体地址情况
     * 
     * @param paramMap
     * @return
     */
    public List<ApplyAddressInfo> selectApplyAddressInfoList(Map<String, Object> paramMap){
        return (List<ApplyAddressInfo>)this.getSqlMapClientTemplate().queryForList("selectApplyAddressInfo", paramMap);
    }

    /**
     * 家庭成员信息
     * 
     * @param applyInfoId
     * @return
     */
    public List<AnaChildren> selectChildrenInfoList(Integer applyInfoId){
        return (List<AnaChildren>)this.getSqlMapClientTemplate().queryForList("selectChildrenInfoList",applyInfoId);
    }

    /**
     * 获取下一个申请人信息id序列号
     * 
     * @return
     */
    public Integer getNextApplyInfoId(){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getNextApplyInfoId");
    }

    /**
     *
     * @param anaApplyInfo
     */
    public void insertApplyInfo(AnaApplyInfo anaApplyInfo){
        this.getSqlMapClientTemplate().insert("insertApplyInfo",anaApplyInfo);
    }

    /**
     *
     * @param anaChildren
     */
    public void insertAnaChildren(AnaChildren anaChildren){
        this.getSqlMapClientTemplate().insert("insertAnaChildren",anaChildren);
    }

    /**
     * 批量插入家庭成员信息
     * 
     * @param anaChildrenList
     */
    public void insertAnaChildrenBatch(List<AnaChildren> anaChildrenList){
        this.exectuteBatchInsert("insertAnaChildren",anaChildrenList);
    }

    /**
     *
     * @param anaApplyInfo
     */
    public void updateApplyInfo(AnaApplyInfo anaApplyInfo){
        this.getSqlMapClientTemplate().update("updateApplyInfo",anaApplyInfo);
    }

    /**
     *
     * @param anaChildren
     */
    public void updateAnaChildren(AnaChildren anaChildren){
        this.getSqlMapClientTemplate().update("updateAnaChildren",anaChildren);
    }

    /**
     * 批量信息家底成员信息表
     * @param anaChildrenList
     */
    public void updateAnaChildrenBatch(List<AnaChildren> anaChildrenList){
        this.executeBatchUpdate("updateAnaChildren",anaChildrenList);
    }

    /**
     * 删除申请人家庭成员
     * @param applyInfoId
     */
    public void deleteAnaChildren(Integer applyInfoId){
        this.getSqlMapClientTemplate().delete("deleteAnaChildren",applyInfoId);
    }
}
