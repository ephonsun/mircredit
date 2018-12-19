package com.banger.mobile.dao.loan.ibatis;

import com.banger.mobile.dao.loan.LnProfitLossBaseDAO;
import com.banger.mobile.domain.model.loan.LnProfitLossBase;
import com.banger.mobile.ibatis.GenericDaoiBatis;


import java.util.List;

/**
 * Created by ygb on 2017/8/1.
 */
public class LnProfitLossBaseDAOImpl extends GenericDaoiBatis implements LnProfitLossBaseDAO {


    public LnProfitLossBaseDAOImpl() {
        super(LnProfitLossBase.class);

    }

    public LnProfitLossBaseDAOImpl(Class persistentClass) {
        super(LnProfitLossBase.class);

    }
    @Override
    public LnProfitLossBase selectProfitLossBaseByPrimary(LnProfitLossBase lnProfitLossBase) {
        return (LnProfitLossBase)this.getSqlMapClientTemplate().queryForObject("selectProfitLossBaseByPrimary",lnProfitLossBase);

    }

    @Override
    public void insertLnProfitLossBase(LnProfitLossBase lnProfitLossBase) {
        this.getSqlMapClientTemplate().insert("insertLnProfitLossBase",lnProfitLossBase);
    }

    @Override
    public void updateLnProfitLossBase(LnProfitLossBase lnProfitLossBase) {
        this.getSqlMapClientTemplate().update("updateLnProfitLossBase", lnProfitLossBase);
    }

    @Override
    public void deleteLnProfitLossBase(LnProfitLossBase lnProfitLossBase) {
        this.getSqlMapClientTemplate().delete("deleteLnProfitLossBase", lnProfitLossBase);
    }
}
