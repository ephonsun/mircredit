/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :用户/交流关系Service接口实现
 * Author     :liyb
 * Create Date:2012-12-26
 */
package com.banger.mobile.facade.impl.communication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.communication.CommUserRelationDao;
import com.banger.mobile.domain.model.communication.CommUserRelation;
import com.banger.mobile.facade.communication.CommUserRelationService;

/**
 * @author liyb
 * @version $Id: CommUserRelationServiceImpl.java,v 0.1 2012-12-26 下午03:44:57
 *          liyb Exp $
 */
public class CommUserRelationServiceImpl implements CommUserRelationService {
	private CommUserRelationDao commUserRelationDao;

	public void setCommUserRelationDao(CommUserRelationDao commUserRelationDao) {
		this.commUserRelationDao = commUserRelationDao;
	}

	/**
	 * 添加收藏/投票数据
	 * 
	 * @param relation
	 */
	public void insertUserRelation(CommUserRelation relation) {
		commUserRelationDao.insertUserRelation(relation);
	}

	/**
	 * 用户是否投过票/收藏过
	 * 
	 * @param themeType
	 *            帖子类型(1:主题 2:投票)
	 * @param themeId
	 *            帖子ID
	 * @return
	 */
	public Integer isUserRelation(Integer themeType, Integer themeId,
			Integer userId, Integer relationType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("relationType", themeType);
		map.put("commId", themeId);
		if (null != userId) {
			map.put("userId", userId);
		}
		if(relationType == 1){
			map.put("isCollect", 1);
		}else if(relationType == 2){
			map.put("isVote", 1);
		}
		return commUserRelationDao.isUserRelation(map);
	}

	public Integer insertUserRelations(List<CommUserRelation> list) {
		return commUserRelationDao.insertUserRelations(list);
	}

	public List<CommUserRelation> getUserRelationList(Map<String, Object> map) {
		return commUserRelationDao.getUserRelationList(map);
	}

	

    /**
     * 删除收藏
     * @param themeIds
     * @param userId
     * @return
     */
    public Integer deleteCollect(String themeIds, Integer userId) {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("themeIds", themeIds.substring(0,themeIds.lastIndexOf(",")));
        map.put("userId", userId);
        return commUserRelationDao.deleteCollect(map);
    }


}
