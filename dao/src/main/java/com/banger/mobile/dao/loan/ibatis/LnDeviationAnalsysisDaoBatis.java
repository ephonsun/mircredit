/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :担保人-Dao-接口实现
 * Author     :QianJie
 * Create Date:Feb 17, 2013
 */
package com.banger.mobile.dao.loan.ibatis;


import com.banger.mobile.dao.loan.LnDeviationAnalsysisDao;
import com.banger.mobile.domain.model.loan.LnDeviationAnalsysis;
import com.banger.mobile.ibatis.GenericDaoiBatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author QianJie
 * @version $Id: LnLoanGuarantorDaoiBatis.java,v 0.1 Feb 17, 2013 2:54:46 PM QianJie Exp $
 */


public class LnDeviationAnalsysisDaoBatis extends GenericDaoiBatis implements LnDeviationAnalsysisDao {


    /**
     * Constructor that takes in a class to see which type of entity to persist
     *
     * @param persistentClass the class type you'd like to persist
     */
    public LnDeviationAnalsysisDaoBatis(Class persistentClass) {

        super(persistentClass);
    }

    public LnDeviationAnalsysisDaoBatis() {
        super(LnDeviationAnalsysisDaoBatis.class);
    }


    @Override
    public void insertLnDeviationAnalsysis(Map<String,Object> paramMap ) {
        this.getSqlMapClientTemplate().insert("addLnDeviationAnalsysis", paramMap);

    }

    @Override
    public LnDeviationAnalsysis  queryOneLnDeviationAnalsysis(Integer loanId) {
       LnDeviationAnalsysis listInBusinessReaches = new LnDeviationAnalsysis();
        return  (LnDeviationAnalsysis) this.getSqlMapClientTemplate().queryForObject("selectLnDeviationAnalsysisByLoanId", loanId);
    }

    @Override
    public void updateLnDeviationAnalsysis(Map<String, Object> paramMap) {
        this.getSqlMapClientTemplate().update("UpdateLnDeviationAnalsysis",paramMap);
    }
}
