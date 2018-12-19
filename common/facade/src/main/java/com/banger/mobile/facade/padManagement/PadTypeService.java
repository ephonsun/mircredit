/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :PAD品牌型号service
 * Author     :liyb
 * Create Date:2013-6-18
 */
package com.banger.mobile.facade.padManagement;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.padManagement.PadBrands;
import com.banger.mobile.domain.model.padManagement.PadModel;

/**
 * @author liyb
 * @version $Id: PadTypeService.java,v 0.1 2013-6-18 下午12:46:40 liyb Exp $
 */
public interface PadTypeService {
    /**
     * 查询所有的PAD品牌
     * @return
     */
    public List<PadBrands> getPadBrandsList(Map<String,Object> map);
    
    /**
     * 添加Pad品牌
     * @param brand
     */
    public void insertPadBrands(PadBrands brand);
    
    /**
     * 编辑Pad品牌
     * @param brand
     */
    public void updatePadBrands(PadBrands brand);
    
    /**
     * 删除Pad品牌
     * @param brandTypeId
     */
    public void deletePadBrands(Integer brandTypeId);
    
    /**
     * 根据PAD品牌名称查询品牌信息
     * @param brandName
     * @return
     */
    public PadBrands getPadBrandsByName(String brandName);
    
    /**
     * 根据PAD品牌ID查询品牌信息
     * @param brandTypeId
     * @return
     */
    public PadBrands getPadBrandsById(Integer brandTypeId);
    
    /**
     * 上移、下移PAD品牌
     * @param brandTypeId
     * @param moveType
     */
    public void movePadBrandsItems(Integer brandTypeId,String moveType);
    
    /**
     * 启用、停用Pad品牌
     * @param brandTypeId pad品牌ID
     * @param isActived 1:启用 0:停用
     */
    public void enableOrDisPadBrands(Integer brandTypeId,Integer isActived);
    
    
    /**
     * 查询PAD某个品牌型号
     * @param brandTypeId
     * @param isActived
     * @param isDel
     * @return
     */
    public List<PadModel> getPadModelList(Integer brandTypeId,Integer isActived,Integer isDel);
    
    /**
     * 添加PAD型号
     * @param padModel
     */
    public void insertPadModel(PadModel padModel);
    
    /**
     * 删除PAD型号
     * @param padModel
     */
    public void deletePadModel(PadModel padModel);
    
    /**
     * 根据条件查询PAD型号信息
     * @param padModelId
     * @return
     */
    public PadModel getPadModelById(PadModel padModel);
    
    /**
     * 编辑PAD型号
     * @param padModel
     */
    public void updatePadModel(PadModel padModel);
    
    /**
     * 上移、下移PAD型号
     * @param brandTypeId
     * @param padSubTypeId
     * @param moveType
     */
    public void movePadModelItems(Integer brandTypeId,Integer padSubTypeId,String moveType);
    
    /**
     * 启用、停用Pad型号
     * @param padSubTypeId pad型号ID
     * @param isActived 1:启用 0:停用
     */
    public void enableOrDisPadModel(Integer padSubTypeId,Integer isActived);
}
