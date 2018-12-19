/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品类型dao
 * Author     :yujh
 * Create Date:Jul 16, 2012
 */
package com.banger.mobile.dao.system;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.system.ProductType;


/**
 * @author yujh
 * @version $Id: RskIntervalTypeDao.java,v 0.1 Jul 16, 2012 3:06:55 PM Administrator Exp $
 */
public interface ProductTypeDao {
	/**
	 * 列表
	 * @return
	 */
    public List<ProductType> getAllProductType();

    /**
     * 添加
     * @param productType
     */
    public void addProductType(ProductType productType);

    /**
     * 删除
     * @param id
     */
    public void deleteProductType(int id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public ProductType getTypeById(int id);

    /**
     * 更新
     * @param productType
     */
    public void updateProductType(ProductType productType);

    /**
     * 查询排序号最大的
     * @return
     */
    public ProductType getMaxSortNoType();

    /**
     * 查询排序号最小的
     * @return
     */
    public ProductType getMinSortNoType();

    /**
     * 查询需要移动的对象
     * @param parameters
     * @return
     */
    public ProductType getNeedToMoveRskProductType(Map<String, Object> parameters);
    
    /**
     * 查询名字相同的对象
     * @param productType
     * @return
     */
    public List<ProductType> getProductTypeBySameName(ProductType productType);

}
