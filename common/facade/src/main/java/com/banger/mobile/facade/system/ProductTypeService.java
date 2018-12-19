/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品类型
 * Author     :yujh
 * Create Date:Aug 15, 2012
 */
package com.banger.mobile.facade.system;

import java.util.List;

import com.banger.mobile.domain.model.system.ProductType;


/**
 * @author yujh
 * @version $Id: ProductTypeService.java,v 0.1 Aug 15, 2012 3:04:07 PM Administrator Exp $
 */
public interface ProductTypeService {
    /**
     * 获取所有产品类型
     * @return
     */
    public List<ProductType> getAllProductType();

    /**
     * 根据Id删除
     * @param id
     */
    public void deleteProductType(int id);

    /**
     * 添加
     * @param 
     */
    public void addProductType(ProductType productType);

    /**
     * id查询
     * @return
     */
    public ProductType getProductTypeById(int id);

    /**
     * 修改
     * @param 
     */
    public void updateProductType(ProductType productType);

    /**
     * 上移或下移
     * @param id
     * @param moveType
     */
    public String moveTypeItems(int id, String moveType);
    /**
     * 获取相同名称的对象
     * @return
     */
    public List<ProductType> getProductTypeBySameName(ProductType productType);
}
