/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品类型维护类...
 * Author     :Administrator
 * Create Date:Aug 15, 2012
 */
package com.banger.mobile.facade.impl.system.basicsdata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.system.ProductTypeDao;
import com.banger.mobile.domain.model.system.ProductType;
import com.banger.mobile.facade.system.ProductTypeService;

/**
 * @author yujh
 * @version $Id: ProductTypeServiceImpl.java,v 0.1 Aug 15, 2012 3:38:37 PM Administrator Exp $
 */
public class ProductTypeServiceImpl implements ProductTypeService {
    private ProductTypeDao productTypeDao;

    public void setProductTypeDao(ProductTypeDao productTypeDao) {
        this.productTypeDao = productTypeDao;
    }

    /**
     * 添加
     */
    public void addProductType(ProductType productType) {
        ProductType min = this.productTypeDao.getMinSortNoType();
        if (min == null) {
            productType.setSortNo(1);
        } else {
            productType.setSortNo(min.getSortNo() + 1);
        }
        this.productTypeDao.addProductType(productType);
    }

    /**
     * 根据Id删除
     */
    public void deleteProductType(int id) {
        this.productTypeDao.deleteProductType(id);
    }

    /**
     * 获取所有产品类型
     */
    public List<ProductType> getAllProductType() {
        return this.productTypeDao.getAllProductType();
    }

    /**
     * id查询
     */
    public ProductType getProductTypeById(int id) {
        return this.productTypeDao.getTypeById(id);
    }

    /**
     * 获取相同名称的对象
     */
    public List<ProductType> getProductTypeBySameName(ProductType productType) {
        return this.productTypeDao.getProductTypeBySameName(productType);
    }

    /**
     * 上移或下移
     */
    public String moveTypeItems(int id, String moveType) {
        ProductType productType = this.productTypeDao.getTypeById(id);
        if (productType != null) {
            int typeNo = productType.getSortNo();
            if (moveType.equals("Up")) {
                ProductType maxNo = this.productTypeDao.getMaxSortNoType();
                if (typeNo != maxNo.getSortNo()) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("moveType", "Up");
                    map.put("sortNo", typeNo);
                    ProductType needMove = this.productTypeDao.getNeedToMoveRskProductType(map);
                    productType.setSortNo(needMove.getSortNo());
                    needMove.setSortNo(typeNo);
                    this.productTypeDao.updateProductType(productType);
                    this.productTypeDao.updateProductType(needMove);
                    return "SUCCESS";
                } else {
                    return "ERROR";
                }
            } else {
                ProductType minNo = this.productTypeDao.getMinSortNoType();
                if (typeNo != minNo.getSortNo()) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("moveType", "Down");
                    map.put("sortNo", typeNo);
                    ProductType needMove = this.productTypeDao.getNeedToMoveRskProductType(map);
                    productType.setSortNo(needMove.getSortNo());
                    needMove.setSortNo(typeNo);
                    this.productTypeDao.updateProductType(productType);
                    this.productTypeDao.updateProductType(needMove);
                    return "SUCCESS";
                } else {
                    return "ERROR";
                }
            }
        } else {
            return "SUCCESS";
        }

    }

    /**
     * 修改
     */
    public void updateProductType(ProductType productType) {
        this.productTypeDao.updateProductType(productType);
    }

}
