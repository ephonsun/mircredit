/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-12-25
 */
package com.banger.mobile.dao.pdtProduct.ibatis;

import java.util.List;

import com.banger.mobile.dao.pdtProduct.PdtFieldCodedataDao;
import com.banger.mobile.domain.model.pdtProduct.PdtFieldCodedata;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author cheny
 * @version $Id: PdtFieldCodedataDaoiBatis.java,v 0.1 2012-12-25 上午11:11:47 cheny Exp $
 */
public class PdtFieldCodedataDaoiBatis extends GenericDaoiBatis implements PdtFieldCodedataDao{
    
    public PdtFieldCodedataDaoiBatis(){
        super(PdtFieldCodedata.class);
    }
    
    /**
     * 获取所有字段代码数据
     * @return
     */
	public List<PdtFieldCodedata> getAllPdtFieldCodedata(){
		return this.getSqlMapClientTemplate().queryForList("getAllPdtFieldCodedata");
	}
    
    /**
     * 新增下拉字段项
     */
    public Integer addPdtFieldCodedata(PdtFieldCodedata codedata){
        return (Integer) this.getSqlMapClientTemplate().insert("addPdtFieldCodedata",codedata);
    }
    
    /**
     * 根据字段id查询所有的下拉项
     */
    public List<PdtFieldCodedata> getFieldCodeDataListByFieldId(int feildId){
        return this.getSqlMapClientTemplate().queryForList("getFieldCodeDataListByFieldId",feildId);
    }
    /**
     * 编辑下拉项
     */
    public void updateFieldCodeData(PdtFieldCodedata codedata){
        this.getSqlMapClientTemplate().update("updateFieldCodeData",codedata);
    }
    /**
     * 根据字段id删除下拉项
     */
    public void updateFieldCodeDataByFeildId(PdtFieldCodedata codedata){
        this.getSqlMapClientTemplate().update("updateFieldCodeDataByFeildId",codedata);
    }

    /**
     * 根据ID查找对象
     * @param codedataId
     * @return
     * @see com.banger.mobile.dao.pdtProduct.PdtFieldCodedataDao#getFieldCodeDataListById(java.lang.Integer)
     */
    public PdtFieldCodedata getFieldCodeDataById(Integer codedataId) {
        return (PdtFieldCodedata) 
                this.getSqlMapClientTemplate().queryForObject("getFieldCodeDataById", codedataId);
    }
}
