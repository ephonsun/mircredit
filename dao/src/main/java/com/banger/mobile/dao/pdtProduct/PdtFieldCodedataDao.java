/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-12-25
 */
package com.banger.mobile.dao.pdtProduct;

import java.util.List;

import com.banger.mobile.domain.model.pdtProduct.PdtFieldCodedata;

/**
 * @author cheny
 * @version $Id: PdtFieldCodedataDao.java,v 0.1 2012-12-25 上午11:11:35 cheny Exp $
 */
public interface PdtFieldCodedataDao {

    /**
     * 获取所有字段代码数据
     * @return
     */
	public List<PdtFieldCodedata> getAllPdtFieldCodedata();
	
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
     * 根据字段id删除下拉项
     */
    public void updateFieldCodeDataByFeildId(PdtFieldCodedata codedata);

    /**
     * 更加ID查找对象
     * @param codedataId
     * @return
     */
    public PdtFieldCodedata getFieldCodeDataById(Integer codedataId);
}
