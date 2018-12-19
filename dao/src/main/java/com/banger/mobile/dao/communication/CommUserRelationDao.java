/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :用户/交流关系Dao接口
 * Author     :liyb
 * Create Date:2012-12-26
 */
package com.banger.mobile.dao.communication;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.communication.CommUserRelation;

/**
 * @author liyb
 * @version $Id: CommUserRelationDao.java,v 0.1 2012-12-26 下午03:23:36 liyb Exp $
 */
public interface CommUserRelationDao {

    /**
     * 添加收藏/投票数据
     * @param relation
     */
    public void insertUserRelation(CommUserRelation relation);
    
    /**
     * 用户是否投过票/收藏过
     * @param map
     * @return
     */
    public Integer isUserRelation(Map<String,Object> map);

    
    /**
     * 添加投票数据
     * @param relation
     */
    Integer insertUserRelations(List<CommUserRelation> list);
    
    
    List<CommUserRelation> getUserRelationList(Map<String, Object> map) ;

    
    /**
     * 删除收藏
     * @param map
     * @return
     */
    public Integer deleteCollect(Map<String,Object> map);

}
