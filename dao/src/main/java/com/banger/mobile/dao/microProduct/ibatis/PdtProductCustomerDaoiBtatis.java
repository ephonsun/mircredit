/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品意向客户-Dao-接口实现
 * Author     :QianJie
 * Create Date:Nov 12, 2012
 */
package com.banger.mobile.dao.microProduct.ibatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.microProduct.PdtProductCustomerDao;
import com.banger.mobile.domain.model.microProduct.PdtProductCustomer;
import com.banger.mobile.domain.model.microProduct.PdtProductCustomerBean;
import com.banger.mobile.ibatis.GenericDaoiBatis;
import com.banger.mobile.util.StringUtil;

/**
 * @author QianJie
 * @version $Id: PdtProductCustomerDaoiBtatis.java,v 0.1 Nov 12, 2012 4:23:00 PM QianJie Exp $
 */
public class PdtProductCustomerDaoiBtatis extends GenericDaoiBatis implements PdtProductCustomerDao {

    public PdtProductCustomerDaoiBtatis() {
        super(PdtProductCustomer.class);
    }
    public PdtProductCustomerDaoiBtatis(Class persistentClass) {
        super(PdtProductCustomer.class);
    }
    /**
     * 添加产品意向客户
     * @param pdtProductCustomer
     * @return
     * @see com.banger.mobile.dao.product.PdtProductCustomerDao#addPdtProductCustomer(com.banger.mobile.domain.model.product.PdtProductCustomer)
     */
    public int addPdtProductCustomer(PdtProductCustomer pdtProductCustomer) {
        return ((Integer)this.getSqlMapClientTemplate().insert("addPdtProductCustomer",pdtProductCustomer)).intValue();
    }

    /**
     * 删除产品意向客户
     * @param productCustomerId
     * @see com.banger.mobile.dao.product.PdtProductCustomerDao#delPdtProductCustomerById(int)
     */
    public void delPdtProductCustomerById(int productCustomerId) {
        this.getSqlMapClientTemplate().update("delPdtProductCustomerById",productCustomerId);
    }

    /**
     * 编辑产品意向客户
     * @param pdtProductCustomer
     * @return
     * @see com.banger.mobile.dao.product.PdtProductCustomerDao#editPdtProductCustomer(com.banger.mobile.domain.model.product.PdtProductCustomer)
     */
    public int editPdtProductCustomer(PdtProductCustomer pdtProductCustomer) {
        this.getSqlMapClientTemplate().update("editPdtProductCustomer",pdtProductCustomer);
        return pdtProductCustomer.getProductCustomerId();
    }

    /**
     * 根据参数得到所有产品意向客户数据(匹配选取客户信息)
     * @param conds
     * @return
     * @see com.banger.mobile.dao.product.PdtProductCustomerDao#getAllCustomerBeanByConds(java.util.Map)
     */
    public List<PdtProductCustomerBean> getAllCustomerBeanByConds(Map<String, Object> conds) {
        Map<String,Object> fConds = new HashMap<String, Object>();
        for(Map.Entry<String, Object> entry : conds.entrySet())
        {
            if(entry.getValue() instanceof String){
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString())));
            }else{
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        return this.getSqlMapClientTemplate().queryForList("getAllCustomerBeanByConds",fConds);
    }

    /**
     * 得到单个产品意向客户数据
     * @param productCustomerId
     * @return
     * @see com.banger.mobile.dao.product.PdtProductCustomerDao#getPdtProductCustomerById(int)
     */
    public PdtProductCustomer getPdtProductCustomerById(int productCustomerId) {
        return (PdtProductCustomer)this.getSqlMapClientTemplate().queryForObject("getPdtProductCustomerById",productCustomerId);
    }

    /**
     * 得到所有产品意向客户数据
     * @param conds
     * @return
     * @see com.banger.mobile.dao.product.PdtProductCustomerDao#getAllPdtProductCustomerByConds(java.util.Map)
     */
    public List<PdtProductCustomer> getAllPdtProductCustomerByConds(Map<String, Object> conds) {
        Map<String,Object> fConds = new HashMap<String, Object>();
        for(Map.Entry<String, Object> entry : conds.entrySet())
        {
            if(entry.getValue() instanceof String){
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString())));
            }else{
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        return this.getSqlMapClientTemplate().queryForList("getAllPdtProductCustomerByConds",fConds);
    }

    /**
     * 查询产品意向客户（分页查询）
     * @param conds
     * @param page
     * @return
     * @see com.banger.mobile.dao.product.PdtProductCustomerDao#getPdtProductCustomerPage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<PdtProductCustomerBean> getPdtProductCustomerBeanPage(Map<String, Object> conds,
                                                                          Page page) {
        Map<String,Object> fConds = new HashMap<String, Object>();
        for(Map.Entry<String, Object> entry : conds.entrySet())
        {
            if(entry.getValue() instanceof String){
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString())));
            }else{
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        ArrayList<PdtProductCustomerBean> list=(ArrayList<PdtProductCustomerBean>) this.findQueryPage("getPdtProductCustomerBeanPageByConds", "getPdtProductCustomerBeanPageCountByConds",fConds, page);
        return new PageUtil<PdtProductCustomerBean>(list, page);
    }

    /**
     * 得到意向客户产品的数量,主要用于验证添加意向客户时，是否有重复添加现象
     *
     * @param paramMap
     * @return
     */
    @Override
    public Integer getProductCustomerCount(Map<String,Object> paramMap){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getProductCustomerCount",paramMap);
    }
}
