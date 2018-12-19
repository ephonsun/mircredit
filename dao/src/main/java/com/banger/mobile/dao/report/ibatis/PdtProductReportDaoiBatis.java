/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品报表Dao实现
 * Author     :liyb
 * Create Date:2013-1-5
 */
package com.banger.mobile.dao.report.ibatis;

import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.report.PdtProductReportDao;
import com.banger.mobile.domain.model.report.ProductReportBean;
import com.banger.mobile.domain.model.report.ProductTypeTotalBean;
import com.banger.mobile.domain.model.report.ProductTypeTreeBean;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author liyb
 * @version $Id: ProductReportDaoiBatis.java,v 0.1 2013-1-5 下午01:03:06 liyb Exp $
 */
public class PdtProductReportDaoiBatis extends GenericDaoiBatis implements PdtProductReportDao {
    
    public PdtProductReportDaoiBatis(){
        super(ProductTypeTreeBean.class);
    }

    /**
     * 查询产品类型树
     * @param map
     * @return
     */
    public List<ProductTypeTreeBean> getProductTypeTree(Map<String,Object> map) {
        return this.getSqlMapClientTemplate().queryForList("GetProductTypeTreeList",map);
    }

    /**
     * 产品报表列表
     * @param parames
     * @return
     */
    public List<ProductReportBean> getProductReportList(Map<String, Object> parames) {
        String sqlId="GetProductReportList";
        if(parames.get("belongType").equals("dept")){//机构
            sqlId="GetProductReportDeptList";
        }
        return this.getSqlMapClientTemplate().queryForList(sqlId,parames);
    }

    /**
     * 查询有购买产品记录的用户以及有营销产品的用户
     * @param parames
     * @return
     */
    public List<ProductTypeTreeBean> getProductUserList(Map<String, Object> parames) {
        String sqlId="GetProductUserList";
        if(parames.get("belongType").equals("dept")){//机构
            sqlId="GetProductDeptList";
        }
        return this.getSqlMapClientTemplate().queryForList(sqlId,parames);
    }

    /**
     * 查询用户的产品大类的销售额
     * @param parames
     * @return
     */
    public List<ProductTypeTotalBean> getProductTypeTotalList(Map<String, Object> parames) {
        String sqlId="GetProductTypeTotalList";
        if(parames.get("belongType").equals("dept")){//机构
            sqlId="GetProductTypeTotalDeptList";
        }
        return this.getSqlMapClientTemplate().queryForList(sqlId,parames);
    }

    /**
     * 查询用户的产品子类型的销售额
     * @param parames
     * @return
     */
    public List<ProductTypeTotalBean> getProductSubTypeTotalList(Map<String, Object> parames) {
        String sqlId="GetProductSubTypeTotalList";
        if(parames.get("belongType").equals("dept")){//机构
            sqlId="GetProductSubTypeDeptTotalList";
        }
        return this.getSqlMapClientTemplate().queryForList(sqlId,parames);
    }

}
