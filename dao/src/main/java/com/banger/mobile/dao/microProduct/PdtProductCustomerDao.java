/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品意向客户-Dao-接口
 * Author     :QianJie
 * Create Date:Nov 12, 2012
 */
package com.banger.mobile.dao.microProduct;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.microProduct.PdtProductCustomer;
import com.banger.mobile.domain.model.microProduct.PdtProductCustomerBean;

/**
 * @author QianJie
 * @version $Id: PdtProductCustomerDao.java,v 0.1 Nov 12, 2012 4:21:52 PM QianJie Exp $
 */
public interface PdtProductCustomerDao {
    /**
     * 添加产品意向客户
     * @param pdtProductCustomer
     * @return
     */
    public int addPdtProductCustomer(PdtProductCustomer pdtProductCustomer);
    
    /**
     * 删除产品意向客户
     * @param productCustomerId
     */
    public void delPdtProductCustomerById(int productCustomerId);
    

    /**
     * 编辑产品意向客户
     * @param pdtProductCustomer
     * @return
     */
    public int editPdtProductCustomer(PdtProductCustomer pdtProductCustomer);
    
    /**
     * 得到单个产品意向客户数据
     * @param productCustomerId
     * @return
     */
    public PdtProductCustomer getPdtProductCustomerById(int productCustomerId);
    
    /**
     * 得到所有产品意向客户数据
     * @param conds
     * @return
     */
    public List<PdtProductCustomer> getAllPdtProductCustomerByConds(Map<String, Object> conds);
    
    /**
     * 根据参数得到所有产品意向客户数据
     * @param conds
     * @return
     */
    public List<PdtProductCustomerBean> getAllCustomerBeanByConds(Map<String, Object> conds);
    
    /**
     * 查询产品意向客户（分页查询）
     * @param conds
     * @param page
     * @return
     */
    public PageUtil<PdtProductCustomerBean> getPdtProductCustomerBeanPage(Map<String, Object> conds, Page page);

    Integer getProductCustomerCount(Map<String, Object> paramMap);
}
