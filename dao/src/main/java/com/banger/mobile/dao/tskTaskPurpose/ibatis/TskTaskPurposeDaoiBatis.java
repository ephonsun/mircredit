/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :联系目的dao实现类
 * Author     :yujh
 * Create Date:Nov 7, 2012
 */
package com.banger.mobile.dao.tskTaskPurpose.ibatis;

import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.tskTaskPurpose.TskTaskPurposeDao;
import com.banger.mobile.domain.model.tskTaskPurpose.TskTaskPurpose;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author yujh
 * @version $Id: TskTaskPurposeDaoiBatis.java,v 0.1 Nov 7, 2012 5:05:13 PM Administrator Exp $
 */
public class TskTaskPurposeDaoiBatis  extends GenericDaoiBatis implements TskTaskPurposeDao {

    public TskTaskPurposeDaoiBatis(Class persistentClass) {
        super(TskTaskPurpose.class);
    }
    public TskTaskPurposeDaoiBatis() {
        super(TskTaskPurpose.class);
    }

    //添加联系目的
    public void addTskTaskPurpose(TskTaskPurpose tskTaskPurpose) {
        this.getSqlMapClientTemplate().insert("addTskTaskPurpose", tskTaskPurpose);
    }

    //改变状态
    public void changeState(TskTaskPurpose tskTaskPurpose) {
        this.getSqlMapClientTemplate().update("changePState", tskTaskPurpose);
    }

    //删除联系目的
    public void deleteTskTaskPose(int id) {
        this.getSqlMapClientTemplate().delete("deleteTskTaskPose", id);
    }

    //联系目的列表
    public List<TskTaskPurpose> getAllTskTaskPurpose(boolean isContainStop) {
    	if(isContainStop){
    		return this.getSqlMapClientTemplate().queryForList("getAllTskTaskPurpose");
    	}else {
			return this.getSqlMapClientTemplate().queryForList("getEnableTskTaskPurpose");
		}
    }

    //得到排序号最大的
    public TskTaskPurpose getMaxSortNoTskTaskPurpose() {
        return (TskTaskPurpose)this.getSqlMapClientTemplate().queryForObject("getMaxSortNoTskTaskPurpose");
    }

    //得到排序号最小的
    public TskTaskPurpose getMinSortNoTskTaskPurpose() {
        return (TskTaskPurpose)this.getSqlMapClientTemplate().queryForObject("getMinSortNoTskTaskPurpose");
    }

    //得到需要移动的对象
    public TskTaskPurpose getNeedToMoveTskTaskPurpose(Map<String, Object> parameters) {
        return (TskTaskPurpose)this.getSqlMapClientTemplate().queryForObject("getNeedToMoveTskTaskPurpose", parameters);
    }

    //id查询
    public TskTaskPurpose getTskTaskPurposeById(int id) {
        return (TskTaskPurpose)this.getSqlMapClientTemplate().queryForObject("getTskTaskPurposeById", id);
    }

    //得到重名的对象
    public List<TskTaskPurpose> getTskTaskPurposeBySameName(TskTaskPurpose tskTaskPurpose) {
        return this.getSqlMapClientTemplate().queryForList("getTskTaskPurposeBySameName", tskTaskPurpose);
    }

    //更新
    public void updateTskTaskPurpose(TskTaskPurpose tskTaskPurpose) {
        this.getSqlMapClientTemplate().update("updateTskTaskPurpose", tskTaskPurpose);
    }

}
