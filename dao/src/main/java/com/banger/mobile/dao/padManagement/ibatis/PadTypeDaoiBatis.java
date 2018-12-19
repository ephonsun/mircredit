/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :liyb
 * Create Date:2013-6-18
 */
package com.banger.mobile.dao.padManagement.ibatis;

import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.padManagement.PadTypeDao;
import com.banger.mobile.domain.model.padManagement.PadBrands;
import com.banger.mobile.domain.model.padManagement.PadModel;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author liyb
 * @version $Id: PadTypeDaoiBatis.java,v 0.1 2013-6-18 上午11:38:25 liyb Exp $
 */
public class PadTypeDaoiBatis extends GenericDaoiBatis implements PadTypeDao {
    
    public PadTypeDaoiBatis(){
        super(PadBrands.class);
    }

    /**
     * 删除Pad品牌
     * @param brandTypeId
     */
    public void deletePadBrands(Integer brandTypeId) {
        this.getSqlMapClientTemplate().update("DeletePadBrands",brandTypeId);
    }

    /**
     * 查询所有的PAD品牌
     * @return
     */
    public List<PadBrands> getPadBrandsList(Map<String,Object> map) {
        return this.getSqlMapClientTemplate().queryForList("GetPadBrandsList",map);
    }

    /**
     * 添加Pad品牌
     * @param brand
     */
    public void insertPadBrands(PadBrands brand) {
        this.getSqlMapClientTemplate().insert("InsertPadBrands",brand);
    }

    /**
     * 编辑Pad品牌
     * @param brand
     */
    public void updatePadBrands(PadBrands brand) {
        this.getSqlMapClientTemplate().update("UpdatePadBrands",brand);
    }

    /**
     * 根据PAD品牌ID查询品牌信息
     * @param brandTypeId
     * @return
     */
    public PadBrands getPadBrandsById(Integer brandTypeId) {
        return (PadBrands) this.getSqlMapClientTemplate().queryForObject("GetPadBrandsById",brandTypeId);
    }

    /**
     * 根据PAD品牌名称查询品牌信息
     * @param brandName
     * @return
     */
    public PadBrands getPadBrandsByName(String brandName) {
        return (PadBrands) this.getSqlMapClientTemplate().queryForObject("GetPadBrandsByName",brandName);
    }

    /**
     * 上移、下移PAD品牌
     * @param brands
     */
    public void movePadBrandsItems(List<PadBrands> brands) {
        this.executeBatchUpdate("UpdatePadBrands", brands);
    }

    /**
     * 删除PAD型号
     * @param padModel
     */
    public void deletePadModel(PadModel padModel) {
        this.getSqlMapClientTemplate().update("UpdatePadModel",padModel);
    }

    /**
     * 查询PAD某个品牌型号
     * @param map
     * @return
     */
    public List<PadModel> getPadModelList(Map<String,Object> map) {
        return this.getSqlMapClientTemplate().queryForList("GetPadModelList",map);
    }

    /**
     * 添加PAD型号
     * @param padModel
     */
    public void insertPadModel(PadModel padModel) {
        this.getSqlMapClientTemplate().insert("InsertPadModel",padModel);
    }

    /**
     * 根据条件查询PAD型号信息
     * @param padModelId
     * @return
     */
    public PadModel getPadModelById(PadModel padModel) {
        return (PadModel) this.getSqlMapClientTemplate().queryForObject("GetPadModelById",padModel);
    }

    /**
     * 编辑PAD型号
     * @param padModel
     */
    public void updatePadModel(PadModel padModel) {
        this.getSqlMapClientTemplate().update("UpdatePadModel",padModel);
    }

    /**
     * 上移、下移PAD型号
     * @param padModel
     */
    public void movePadModelItems(List<PadModel> padModel) {
        this.executeBatchUpdate("UpdatePadModel", padModel);
    }

}
