/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :收益类型
 * Author     :yujh
 * Create Date:Aug 14, 2012
 */
package com.banger.mobile.dao.system.ibatis;

import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.system.ProfitTypeDao;
import com.banger.mobile.domain.model.system.ProfitType;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author yujh
 * @version $Id: ProfitTypeDaoiBatis.java,v 0.1 Aug 14, 2012 4:01:12 PM Administrator Exp $
 */
public class ProfitTypeDaoiBatis extends GenericDaoiBatis implements ProfitTypeDao{

    public ProfitTypeDaoiBatis(Class persistentClass) {
        super(persistentClass);
    }
    public ProfitTypeDaoiBatis(){
        super(ProfitTypeDaoiBatis.class);
    }

    /**
     * 添加收益类型
     */
    public void addProfitType(ProfitType profitType) {
        this.getSqlMapClientTemplate().insert("insertProfitType",profitType );
    }

    /**
     * 删除
     */
    public void deleteProfitType(int id) {
        this.getSqlMapClientTemplate().delete("deleteProfitType", id);
    }

    /**
     * 列表
     */
    public List<ProfitType> getAllProfitType() {
        return this.getSqlMapClientTemplate().queryForList("getAllProfitType");
    }

    /**
     * 获取序号最大的对象
     */
    public ProfitType getMaxSortNoType() {
        return (ProfitType)this.getSqlMapClientTemplate().queryForObject("getMaxNoProfitType");
    }

    /**
     * 获取序号最小的对象
     */
    public ProfitType getMinSortNoType() {
        return (ProfitType)this.getSqlMapClientTemplate().queryForObject("getMinNoProfitType");
    }

    /**
     * 获取需要移动的对象
     */
    public ProfitType getNeedToMoveProfitType(Map<String, Object> parameters) {
        return (ProfitType)this.getSqlMapClientTemplate().queryForObject("getNeedMoveProfitType",parameters );
    }

    /**
     * 获取相同名称的对象
     */
    public List<ProfitType> getProfitTypeBySameName(ProfitType profitType) {
        return this.getSqlMapClientTemplate().queryForList("getProfitTypeBySameName", profitType);
    }

    /**
     * id查询
     */
    public ProfitType getTypeById(int id) {
        return (ProfitType)this.getSqlMapClientTemplate().queryForObject("getProfitTypeById", id);
    }

    /**
     * 更新
     */
    public void updateProfitType(ProfitType profitType) {
        this.getSqlMapClientTemplate().update("updProfitType", profitType);
    }

}
