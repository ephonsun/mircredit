/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品类型
 * Author     :yujh
 * Create Date:Aug 14, 2012
 */
package com.banger.mobile.dao.system.ibatis;

import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.system.ProductTypeDao;
import com.banger.mobile.domain.model.system.ProductType;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author yujh
 * @version $Id: ProductTypeDaoiBatis.java,v 0.1 Aug 14, 2012 4:00:44 PM Administrator Exp $
 */
public class ProductTypeDaoiBatis extends GenericDaoiBatis implements ProductTypeDao {

    public ProductTypeDaoiBatis(Class persistentClass) {
        super(persistentClass);
    }
    public ProductTypeDaoiBatis(){
        super(ProductTypeDaoiBatis.class);
    }

    /**
     * 添加
     */
    public void addProductType(ProductType productType) {
        this.getSqlMapClientTemplate().insert("insertProductType", productType);
    }

    /**
     * 删除
     */
    public void deleteProductType(int id) {
        this.getSqlMapClientTemplate().delete("deleteProductType", id);
    }

    /**
     * 列表
     */
    public List<ProductType> getAllProductType() {
        return this.getSqlMapClientTemplate().queryForList("getAllProductType");
    }

    /**
     * 获取序号最大的对象
     */
    public ProductType getMaxSortNoType() {
        return (ProductType)this.getSqlMapClientTemplate().queryForObject("getMaxNoProductType");
    }

    /**
     * 获取序号最小的对象
     */
    public ProductType getMinSortNoType() {
        return (ProductType)this.getSqlMapClientTemplate().queryForObject("getMinNoProductType");
    }

    /**
     * 获取需要移动的对象
     */
    public ProductType getNeedToMoveRskProductType(Map<String, Object> parameters) {
        return (ProductType)this.getSqlMapClientTemplate().queryForObject("getNeedToMoveProductType", parameters);
    }

    /**
     * 获取相同名称的对象
     */
    public List<ProductType> getProductTypeBySameName(ProductType productType) {
        return this.getSqlMapClientTemplate().queryForList("getProductBySameName",productType );
    }

    /**
     * id查询
     */
    public ProductType getTypeById(int id) {
        return (ProductType)this.getSqlMapClientTemplate().queryForObject("getProductTypeById", id);
    }

    /**
     * 更新
     */
    public void updateProductType(ProductType productType) {
        this.getSqlMapClientTemplate().update("updProductType", productType);
    }

}
