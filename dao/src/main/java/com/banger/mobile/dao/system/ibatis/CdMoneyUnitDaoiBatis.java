/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :金融单位
 * Author     :yujh
 * Create Date:Aug 14, 2012
 */
package com.banger.mobile.dao.system.ibatis;

import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.system.CdMoneyUnitDao;
import com.banger.mobile.domain.model.system.CdMoneyUnit;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author yujh
 * @version $Id: CdMoneyUnitDaoiBatis.java,v 0.1 Aug 14, 2012 3:59:51 PM Administrator Exp $
 */
public class CdMoneyUnitDaoiBatis extends GenericDaoiBatis implements CdMoneyUnitDao{

    public CdMoneyUnitDaoiBatis(Class persistentClass) {
        super(persistentClass);
    }
    public CdMoneyUnitDaoiBatis(){
        super(CdMoneyUnitDaoiBatis.class);
    }

    /**
     * 添加
     */
    public void addMoneyUnit(CdMoneyUnit moneyUnit) {
        this.getSqlMapClientTemplate().insert("insertMoneyUnit", moneyUnit);
    }

    /**
     * 删除
     */
    public void deleteMoneyUnit(int id) {
        this.getSqlMapClientTemplate().delete("deleteMoneyUnit", id);
    }

    /**
     * 列表
     */
    public List<CdMoneyUnit> getAllMoneyUnit() {
        return this.getSqlMapClientTemplate().queryForList("getMoneyUnitList");
    }

    /**
     * 取得最大序号对象
     */
    public CdMoneyUnit getMaxSortNo() {
        return (CdMoneyUnit)this.getSqlMapClientTemplate().queryForObject("getMaxSortNoMoneyUnit");
    }

    /**
     * 取得最小序号对象
     */
    public CdMoneyUnit getMinSortNo() {
        return (CdMoneyUnit)this.getSqlMapClientTemplate().queryForObject("getMinSortNoMoneyUnit");
    }

    /**
     * id查询
     */
    public CdMoneyUnit getMoneyUnitById(int id) {
        return (CdMoneyUnit)this.getSqlMapClientTemplate().queryForObject("getMoneyUnitById", id);
    }

    /**
     * 取得相同名称的单位
     */
    public List<CdMoneyUnit> getMoneyUnitBySameName(CdMoneyUnit moneyUnit) {
        return this.getSqlMapClientTemplate().queryForList("getSameNameMoneyUnit", moneyUnit);
    }

    /**
     * 取得需要移动的对象
     */
    public CdMoneyUnit getNeedToMoveMoneyUnit(Map<String, Object> parameters) {
        return (CdMoneyUnit)this.getSqlMapClientTemplate().queryForObject("getNeedMoveMoneyUnit", parameters);
    }

    /**
     * 更新
     */
    public void updateMoneyUnit(CdMoneyUnit moneyUnit) {
        this.getSqlMapClientTemplate().update("updateMoneyUnit", moneyUnit);
    }
    
    /**
     * 查询是否是最后一条募集单位
     */
    public Integer queryLaseMoneyUnit(){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("queryLaseMoneyUnit");
    }

}
