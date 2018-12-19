/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品报表Dao接口
 * Author     :liyb
 * Create Date:2013-1-5
 */
package com.banger.mobile.dao.report;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.report.ProductReportBean;
import com.banger.mobile.domain.model.report.ProductTypeTotalBean;
import com.banger.mobile.domain.model.report.ProductTypeTreeBean;

/**
 * @author liyb
 * @version $Id: ProductReportDao.java,v 0.1 2013-1-5 下午12:59:52 liyb Exp $
 */
public interface PdtProductReportDao {
    
    /**
     * 查询产品类型树
     * @param map
     * @return
     */
    public List<ProductTypeTreeBean> getProductTypeTree(Map<String,Object> map);
    
    /**
     * 产品报表列表
     * @param parames
     * @return
     */
    public List<ProductReportBean> getProductReportList(Map<String,Object> parames);
    
    /**
     * 查询有购买产品记录的用户以及有营销产品的用户
     * @param parames
     * @return
     */
    public List<ProductTypeTreeBean> getProductUserList(Map<String,Object> parames);
    
    /**
     * 查询用户的产品大类的销售额
     * @param parames
     * @return
     */
    public List<ProductTypeTotalBean> getProductTypeTotalList(Map<String,Object> parames);
    
    /**
     * 查询用户的产品子类型的销售额
     * @param parames
     * @return
     */
    public List<ProductTypeTotalBean> getProductSubTypeTotalList(Map<String,Object> parames);
}
