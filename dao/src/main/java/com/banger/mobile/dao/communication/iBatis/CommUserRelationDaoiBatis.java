/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :liyb
 * Create Date:2012-12-26
 */
package com.banger.mobile.dao.communication.iBatis;

import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.communication.CommUserRelationDao;
import com.banger.mobile.domain.model.communication.CommUserRelation;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author liyb
 * @version $Id: COmmUserRelationDaoiBatis.java,v 0.1 2012-12-26 下午03:32:03 liyb Exp $
 */
public class CommUserRelationDaoiBatis extends GenericDaoiBatis implements CommUserRelationDao {
    
    public CommUserRelationDaoiBatis(){
        super(CommUserRelation.class);
    }

    /**
     * 添加收藏/投票数据
     * @param relation
     */
    public void insertUserRelation(CommUserRelation relation) {
        this.getSqlMapClientTemplate().insert("InsertUserRelation",relation);
    }

    /**
     * 用户是否投过票/收藏过
     * @param map
     * @return
     */
    public Integer isUserRelation(Map<String, Object> map) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("IsUserRelation",map);
    }


	public Integer insertUserRelations(List<CommUserRelation> list) {
		return this.exectuteBatchInsert("InsertUserRelation", list);
	}

	public List<CommUserRelation> getUserRelationList(Map<String, Object> map) {
		return this.getSqlMapClientTemplate().queryForList("getUserRelationList",map);
	}


    /**
     * 删除收藏
     * @param map
     * @return
     */
    public Integer deleteCollect(Map<String, Object> map) {
        return this.getSqlMapClientTemplate().delete("DeleteCollect", map);
    }


}
