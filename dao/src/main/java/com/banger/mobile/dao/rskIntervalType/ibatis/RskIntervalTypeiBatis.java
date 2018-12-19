/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :投资偏好dao实现类
 * Author     :yujh
 * Create Date:Jul 16, 2012
 */
package com.banger.mobile.dao.rskIntervalType.ibatis;

import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.rskIntervalType.RskIntervalTypeDao;
import com.banger.mobile.domain.model.rskIntervalType.RskIntervalType;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author yujh
 * @version $Id: RskIntervalTypeiBatis.java,v 0.1 Jul 16, 2012 3:34:56 PM Administrator Exp $
 */
public class RskIntervalTypeiBatis extends GenericDaoiBatis implements RskIntervalTypeDao{

    public RskIntervalTypeiBatis() {
        super(RskIntervalType.class);
    }
    public RskIntervalTypeiBatis(Class persistentClass){
        super(RskIntervalType.class);
    }
    /**
     * 添加投资偏好类型
     */
    public void addRskIntervalType(RskIntervalType rskIntervalType) {
        this.getSqlMapClientTemplate().insert("insertRskIntervalType", rskIntervalType);
    }
    /**
     * 删除
     */
    public void deleteRskIntervalType(int id) {
        this.getSqlMapClientTemplate().delete("deleteRskIntervalType", id);
    }
    /**
     * 列表显示
     */
    public List<RskIntervalType> getAllRskIntervalType() {
        return this.getSqlMapClientTemplate().queryForList("getAllRskIntervalType");
    }
    /**
     * 获取所有投资偏好类型集合
     */
    public List<RskIntervalType> getAllRskIntervalTypes() {
        return this.getSqlMapClientTemplate().queryForList("getAllRskIntervalTypes");
    }
    /**
     * 获取最大排序号的对象
     */
    public RskIntervalType getMaxSortNoRskIntervalType() {
        return (RskIntervalType)this.getSqlMapClientTemplate().queryForObject("getMaxSortNoTye");
    }
    /**
     * 获取最小排序号的对象
     */
    public RskIntervalType getMinSortNoRskIntervalType() {
        return (RskIntervalType)this.getSqlMapClientTemplate().queryForObject("getMinSortNoTye");
    }
    /**
     * 查询需要移动的对象
     */
    public RskIntervalType getNeedToMoveRskIntervalType(Map<String, Object> parameters) {
        return (RskIntervalType)this.getSqlMapClientTemplate().queryForObject("getNeedToMoveType", parameters);
    }
    /**
     * 根据id查询
     */
    public RskIntervalType getTypeById(int id) {
        return (RskIntervalType)this.getSqlMapClientTemplate().queryForObject("getTypeById", id);
    }
    /**
     * 更新
     */
    public void updateRskIntervalType(RskIntervalType rskIntervalType) {
        this.getSqlMapClientTemplate().update("updRskIntervalType", rskIntervalType);
    }
    /**
     * 获取相同名称的对象
     */
    public List<RskIntervalType> getRskIntervalTypeBySameName(RskIntervalType rskIntervalType) {
        return this.getSqlMapClientTemplate().queryForList("getRskIntervalTypeBySameName", rskIntervalType);
    }
    /**
     * 改变状态
     */
    public void changeState(RskIntervalType rskIntervalType) {
       this.getSqlMapClientTemplate().update("changeState", rskIntervalType);
    }

}
