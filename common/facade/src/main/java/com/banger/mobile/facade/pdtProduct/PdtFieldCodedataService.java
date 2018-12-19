/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-12-27
 */
package com.banger.mobile.facade.pdtProduct;

import java.util.List;

import com.banger.mobile.domain.model.pdtProduct.PdtFieldCodedata;

/**
 * @author cheny
 * @version $Id: PdtFieldCodedataService.java,v 0.1 2012-12-27 上午11:09:59 cheny Exp $
 */
public interface PdtFieldCodedataService {
    /**
     * 新增下拉字段项
     */
    public Integer addPdtFieldCodedata(PdtFieldCodedata codedata);
    /**
     * 根据字段id查询所有的下拉项
     */
    public List<PdtFieldCodedata> getFieldCodeDataListByFieldId(int feildId);
    /**
     * 编辑下拉项
     */
    public void updateFieldCodeData(PdtFieldCodedata codedata);
 
    /**
     * 根据ID查找对象
     */
    public PdtFieldCodedata getFieldCodeDataById(Integer codedataId);
}
