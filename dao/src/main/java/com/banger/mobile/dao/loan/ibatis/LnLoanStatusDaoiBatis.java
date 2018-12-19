package com.banger.mobile.dao.loan.ibatis;

import com.banger.mobile.dao.loan.LnLoanStatusDao;
import com.banger.mobile.domain.model.loan.LnLoanStatus;
import com.banger.mobile.ibatis.GenericDaoiBatis;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-6
 * Time: 下午3:40
 * To change this template use File | Settings | File Templates.
 */
public class LnLoanStatusDaoiBatis extends GenericDaoiBatis implements LnLoanStatusDao {
    /**
     * Constructor that takes in a class to see which type of entity to persist
     */
    public LnLoanStatusDaoiBatis() {
        super(LnLoanStatus.class);
    }

    public LnLoanStatusDaoiBatis(Class persistentClass) {
        super(LnLoanStatus.class);
    }

    public List<LnLoanStatus> getLoanStatusList() {
        return this.getSqlMapClientTemplate().queryForList("getLoanStatusList");
    }

    public String getLoanStatusName(String loanStatusId){
        return (String)this.getSqlMapClientTemplate().queryForObject("getLoanStatusName",loanStatusId);
    }
}
