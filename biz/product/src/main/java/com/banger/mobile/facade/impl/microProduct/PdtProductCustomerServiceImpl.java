/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品意向客户-Service-接口实现
 * Author     :QianJie
 * Create Date:Nov 12, 2012
 */
package com.banger.mobile.facade.impl.microProduct;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.microProduct.PdtProductCustomerDao;
import com.banger.mobile.domain.model.microProduct.PdtProductCustomer;
import com.banger.mobile.domain.model.microProduct.PdtProductCustomerBean;
import com.banger.mobile.facade.microProduct.PdtProductCustomerService;

/**
 * @author QianJie
 * @version $Id: PdtProductCustomerServiceImpl.java,v 0.1 Nov 12, 2012 4:21:52 PM QianJie Exp $
 */
public class PdtProductCustomerServiceImpl implements PdtProductCustomerService {

    private PdtProductCustomerDao pdtProductCustomerDao;

    public void setPdtProductCustomerDao(PdtProductCustomerDao pdtProductCustomerDao) {
        this.pdtProductCustomerDao = pdtProductCustomerDao;
    }

    /**
     * 添加产品意向客户
     * @param pdtProductCustomer
     * @return
     * @see com.banger.mobile.facade.product.PdtProductCustomerService#addPdtProductCustomer(com.banger.mobile.domain.model.product.PdtProductCustomer)
     */
    public int addPdtProductCustomer(PdtProductCustomer pdtProductCustomer) {
        return pdtProductCustomerDao.addPdtProductCustomer(pdtProductCustomer);
    }

    /**
     * 删除产品意向客户
     * @param productCustomerId
     * @see com.banger.mobile.facade.product.PdtProductCustomerService#delPdtProductCustomerById(int)
     */
    public void delPdtProductCustomerById(int productCustomerId) {
        pdtProductCustomerDao.delPdtProductCustomerById(productCustomerId);
    }

    /**
     * 编辑产品意向客户
     * @param pdtProductCustomer
     * @return
     * @see com.banger.mobile.facade.product.PdtProductCustomerService#editPdtProductCustomer(com.banger.mobile.domain.model.product.PdtProductCustomer)
     */
    public int editPdtProductCustomer(PdtProductCustomer pdtProductCustomer) {
        return pdtProductCustomerDao.editPdtProductCustomer(pdtProductCustomer);
    }

    /**
     * 根据参数得到所有意向客户数据
     * @param conds
     * @return
     * @see com.banger.mobile.facade.product.PdtProductCustomerService#getAllCustomerBeanByConds(java.util.Map)
     */
    public List<PdtProductCustomerBean> getAllCustomerBeanByConds(Map<String, Object> conds) {
        return pdtProductCustomerDao.getAllCustomerBeanByConds(conds);
    }

    /**
     * 得到单个产品意向客户数据
     * @param productCustomerId
     * @return
     * @see com.banger.mobile.facade.product.PdtProductCustomerService#getPdtProductCustomerById(int)
     */
    public PdtProductCustomer getPdtProductCustomerById(int productCustomerId) {
        return pdtProductCustomerDao.getPdtProductCustomerById(productCustomerId);
    }

    /**
     * 得到所有产品意向客户数据
     * @param conds
     * @return
     * @see com.banger.mobile.facade.product.PdtProductCustomerService#getAllPdtProductCustomerByConds(java.util.Map)
     */
    public List<PdtProductCustomer> getAllPdtProductCustomerByConds(Map<String, Object> conds) {
        return pdtProductCustomerDao.getAllPdtProductCustomerByConds(conds);
    }
    
    /**
     * 查询产品意向客户（分页查询）
     * @param conds
     * @param page
     * @return
     * @see com.banger.mobile.facade.product.PdtProductCustomerService#getPdtProductCustomerPage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<PdtProductCustomerBean> getPdtProductCustomerBeanPage(Map<String, Object> conds,
                                                                  Page page) {
        return pdtProductCustomerDao.getPdtProductCustomerBeanPage(conds, page);
    }

    /**
     * 得到意向客户产品的数量,主要用于验证添加意向客户时，是否有重复添加现象
     *
     * @param paramMap
     * @return
     */
    public Integer getProductCustomerCount(Map<String, Object> paramMap){
        return pdtProductCustomerDao.getProductCustomerCount(paramMap);
    }
}
