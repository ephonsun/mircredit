package com.banger.mobile.dao.loan.ibatis;

import com.banger.mobile.dao.loan.LnProfitLossDetailDAO;
import com.banger.mobile.domain.model.loan.LnProfitLossDetail;
import com.banger.mobile.ibatis.GenericDaoiBatis;

import java.util.List;

/**
 * Created by Administrator on 2017/8/3.
 */
public class LnProfitLossDetailDAOImpl extends GenericDaoiBatis implements LnProfitLossDetailDAO {



    /**
     * Constructor that takes in a class to see which type of entity to persist
     *
     * @param persistentClass the class type you'd like to persist
     */
    public LnProfitLossDetailDAOImpl(Class persistentClass) {
        super(LnProfitLossDetail.class);
    }
    public LnProfitLossDetailDAOImpl(){
        super(LnProfitLossDetail.class);
    }

    @Override
    public List<LnProfitLossDetail> selectDetailsByItemId(Integer itemId) {
        return this.getSqlMapClientTemplate().queryForList("selectDetailsByItemId",itemId);
    }

    @Override
    public void deleteDetailsByItemId(Integer itemId) {
        this.getSqlMapClientTemplate().delete("deleteDetailsByItemId",itemId);
    }

    @Override
    public void deleteDetailsByLoanId(Integer loanId) {
        this.getSqlMapClientTemplate().delete("deleteDetailsByLoanId",loanId);
    }

    @Override
    public void insertDetails(LnProfitLossDetail lnProfitLossDetail) {
        this.getSqlMapClientTemplate().insert("insertDetails",lnProfitLossDetail);
    }
    @Override
    public void updateDetails(LnProfitLossDetail lnProfitLossDetail) {
        this.getSqlMapClientTemplate().insert("updateDetails",lnProfitLossDetail);
    }
}
