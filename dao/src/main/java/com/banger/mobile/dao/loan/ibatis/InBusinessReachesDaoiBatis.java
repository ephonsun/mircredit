/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :担保人-Dao-接口实现
 * Author     :QianJie
 * Create Date:Feb 17, 2013
 */
package com.banger.mobile.dao.loan.ibatis;


import com.banger.mobile.dao.loan.InBusinessReachesDao;
import com.banger.mobile.domain.model.loan.InBusinessReaches;
import com.banger.mobile.ibatis.GenericDaoiBatis;


import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author QianJie
 * @version $Id: LnLoanGuarantorDaoiBatis.java,v 0.1 Feb 17, 2013 2:54:46 PM QianJie Exp $
 */


        public class InBusinessReachesDaoiBatis extends GenericDaoiBatis implements InBusinessReachesDao {


    /**
     * Constructor that takes in a class to see which type of entity to persist
     *
     * @param persistentClass the class type you'd like to persist
     */
    public InBusinessReachesDaoiBatis(Class persistentClass) {

        super(persistentClass);
    }

    public InBusinessReachesDaoiBatis() {
        super(InBusinessReachesDaoiBatis.class);
    }

    @Override
    public void  insertBusinessReaches(InBusinessReaches inBusinessReaches) {
        this.getSqlMapClientTemplate().insert("insertInBusinessReaches",inBusinessReaches);

    }

    @Override
    public List<InBusinessReaches> selectByyReachesType(Integer reachesType ,Integer loanId) {
        List<InBusinessReaches> listInBusinessReaches = new ArrayList<InBusinessReaches>();
       // listInBusinessReaches=this.getSqlMapClientTemplate().queryForList("selectByReachesType",reachesType);
        Map<String,Object> paramMap=new HashMap<String, Object>();
        paramMap.put("reachesType",reachesType);
        paramMap.put("loanId",loanId);
        listInBusinessReaches=this.getSqlMapClientTemplate().queryForList("selectByReachesType",paramMap);

      return listInBusinessReaches ;
    }

    @Override
    public List<InBusinessReaches> selectbReachesType(Integer loanId) {
        List<InBusinessReaches> listInBusinessReaches = new ArrayList<InBusinessReaches>();
        listInBusinessReaches=this.getSqlMapClientTemplate().queryForList("selectByLoanId",loanId);
        return listInBusinessReaches;
    }

    @Override
    public void  deleteInBusinessReaches(Integer id) {
        this.getSqlMapClientTemplate().delete("dropInBusinessReaches",id);
    }
}
