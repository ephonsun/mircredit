/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品-Service-接口实现
 * Author     :QianJie
 * Create Date:Nov 12, 2012
 */
package com.banger.mobile.facade.impl.microProduct;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.microProduct.PdtProductDao;
import com.banger.mobile.domain.model.microProduct.PdtProduct;
import com.banger.mobile.facade.microProduct.PdtProductService;

/**
 * @author QianJie
 * @version $Id: PdtProductServiceImpl.java,v 0.1 Nov 12, 2012 4:17:49 PM QianJie Exp $
 */
public class PdtProductServiceImpl implements PdtProductService {

    private PdtProductDao pdtProductDao;

    public void setPdtProductDao(PdtProductDao pdtProductDao) {
        this.pdtProductDao = pdtProductDao;
    }

    /**
     * 添加产品
     * @param pdtProduct
     * @return
     * @see com.banger.mobile.facade.product.PdtProductService#addPdtProduct(com.banger.mobile.domain.model.product.PdtProduct)
     */
    public int addPdtProduct(PdtProduct pdtProduct) {
        return pdtProductDao.addPdtProduct(pdtProduct);
    }

    /**
     * 删除产品
     * @param productId
     * @see com.banger.mobile.facade.product.PdtProductService#delPdtProductById(int)
     */
    public void delPdtProductById(int productId) {
        pdtProductDao.delPdtProductById(productId);
    }

    /**
     * 编辑产品
     * @param pdtProduct
     * @return
     * @see com.banger.mobile.facade.product.PdtProductService#editPdtProduct(com.banger.mobile.domain.model.product.PdtProduct)
     */
    public int editPdtProduct(PdtProduct pdtProduct) {
        return pdtProductDao.editPdtProduct(pdtProduct);
    }

    /**
     * 根据参数得到所有产品数据
     * @param conds
     * @return
     * @see com.banger.mobile.facade.product.PdtProductService#getAllPdtProductByConds(java.util.Map)
     */
    public List<PdtProduct> getAllPdtProductByConds(Map<String, Object> conds) {
        return pdtProductDao.getAllPdtProductByConds(conds);
    }

    /**
     * 得到单个产品数据
     * @param productId
     * @return
     * @see com.banger.mobile.facade.product.PdtProductService#getPdtProductById(int)
     */
    public PdtProduct getPdtProductById(int productId) {
        return pdtProductDao.getPdtProductById(productId);
    }

    /**
     * 查询产品（分页查询）
     * @param conds
     * @param page
     * @return
     * @see com.banger.mobile.facade.product.PdtProductService#getPdtProductPage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<PdtProduct> getPdtProductPage(Map<String, Object> conds, Page page) {
        return pdtProductDao.getPdtProductPage(conds, page);
    }
}
