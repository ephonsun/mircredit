/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品-Service-接口
 * Author     :QianJie
 * Create Date:Nov 12, 2012
 */
package com.banger.mobile.facade.microProduct;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.microProduct.PdtProduct;

/**
 * @author QianJie
 * @version $Id: PdtProductService.java,v 0.1 Nov 12, 2012 4:17:49 PM QianJie Exp $
 */
public interface PdtProductService {
    /**
     * 添加产品
     * @param pdtProduct
     * @return
     */
    public int addPdtProduct(PdtProduct pdtProduct);
    
    /**
     * 删除产品
     * @param productId
     */
    public void delPdtProductById(int productId);
    

    /**
     * 编辑产品
     * @param pdtProduct
     * @return
     */
    public int editPdtProduct(PdtProduct pdtProduct);
    
    /**
     * 得到单个产品数据
     * @param productId
     * @return
     */
    public PdtProduct getPdtProductById(int productId);
    
    /**
     * 根据参数得到所有产品数据
     * @param conds
     * @return
     */
    public List<PdtProduct> getAllPdtProductByConds(Map<String, Object> conds);
    
    /**
     * 查询产品（分页查询）
     * @param conds
     * @param page
     * @return
     */
    public PageUtil<PdtProduct> getPdtProductPage(Map<String, Object> conds, Page page);
}
