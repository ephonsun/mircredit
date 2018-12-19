/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品-Dao-接口实现
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
import com.banger.mobile.dao.microProduct.PdtProductDao;
import com.banger.mobile.domain.model.microProduct.PdtLibrary;
import com.banger.mobile.domain.model.microProduct.PdtLibraryBean;
import com.banger.mobile.domain.model.microProduct.PdtProduct;
import com.banger.mobile.ibatis.GenericDaoiBatis;
import com.banger.mobile.util.StringUtil;

/**
 * @author QianJie
 * @version $Id: PdtProductDaoiBatis.java,v 0.1 Nov 12, 2012 4:20:07 PM QianJie Exp $
 */
public class PdtProductDaoiBatis extends GenericDaoiBatis implements PdtProductDao {

    public PdtProductDaoiBatis() {
        super(PdtProduct.class);
    }

    public PdtProductDaoiBatis(Class persistentClass) {
        super(PdtProduct.class);
    }

    /**
     * 添加产品
     * @param pdtProduct
     * @return
     * @see com.banger.mobile.dao.product.PdtProductDao#addPdtProduct(com.banger.mobile.domain.model.product.PdtProduct)
     */
    public int addPdtProduct(PdtProduct pdtProduct) {
        return ((Integer)this.getSqlMapClientTemplate().insert("addPdtProduct",pdtProduct)).intValue();
    }

    /**
     * 删除产品
     * @param productId
     * @see com.banger.mobile.dao.product.PdtProductDao#delPdtProductById(int)
     */
    public void delPdtProductById(int productId) {
        this.getSqlMapClientTemplate().update("delPdtProductById",productId);
    }

    /**
     * 编辑产品
     * @param pdtProduct
     * @return
     * @see com.banger.mobile.dao.product.PdtProductDao#editPdtProduct(com.banger.mobile.domain.model.product.PdtProduct)
     */
    public int editPdtProduct(PdtProduct pdtProduct) {
        this.getSqlMapClientTemplate().update("editPdtProduct",pdtProduct);
        return pdtProduct.getProductId();
    }

    /**
     * 根据参数得到所有产品数据
     * @param conds
     * @return
     * @see com.banger.mobile.dao.product.PdtProductDao#getAllPdtProductByConds(java.util.Map)
     */
    public List<PdtProduct> getAllPdtProductByConds(Map<String, Object> conds) {
        Map<String,Object> fConds = new HashMap<String, Object>();
        for(Map.Entry<String, Object> entry : conds.entrySet())
        {
            if(entry.getValue() instanceof String){
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString())));
            }else{
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        return this.getSqlMapClientTemplate().queryForList("getAllPdtProductByConds",fConds);
    }

    /**
     * 得到单个产品数据
     * @param productId
     * @return
     * @see com.banger.mobile.dao.product.PdtProductDao#getPdtProductById(int)
     */
    public PdtProduct getPdtProductById(int productId) {
        return (PdtProduct)this.getSqlMapClientTemplate().queryForObject("getPdtProductById",productId);
    }

    /**
     * 查询产品（分页查询）
     * @param conds
     * @param page
     * @return
     * @see com.banger.mobile.dao.product.PdtProductDao#getPdtProductPage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<PdtProduct> getPdtProductPage(Map<String, Object> conds, Page page) {
        Map<String,Object> fConds = new HashMap<String, Object>();
        for(Map.Entry<String, Object> entry : conds.entrySet())
        {
            if(entry.getValue() instanceof String){
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString())));
            }else{
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        ArrayList<PdtProduct> list=(ArrayList<PdtProduct>) this.findQueryPage("getPdtProductPageByConds", "getPdtProductPageCountByConds",fConds, page);
        return new PageUtil<PdtProduct>(list, page);
    }

}
