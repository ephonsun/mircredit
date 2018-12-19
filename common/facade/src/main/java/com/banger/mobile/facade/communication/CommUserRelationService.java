/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :用户/交流关系Service接口
 * Author     :liyb
 * Create Date:2012-12-26
 */
package com.banger.mobile.facade.communication;


import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.communication.CommUserRelation;

/**
 * @author liyb
 * @version $Id: CommUserRelationService.java,v 0.1 2012-12-26 下午03:41:12 liyb Exp $
 */
public interface CommUserRelationService {
    /**
     * 添加收藏/投票数据
     * @param relation
     */
    public void insertUserRelation(CommUserRelation relation);
    
    /**
     * 用户是否投过票/收藏过
     * @param themeType 帖子类型(1:主题 2:投票)
     * @param themeId 帖子ID
     * @param userId 用户ID
     * @parma relationType 类型1为收藏,2为投票
     * @return
     */
    public Integer isUserRelation(Integer themeType,Integer themeId,Integer userId,Integer relationType);
    
    /**
     * 批量添加投票数据
     * @param list
     * @return
     */
    public Integer insertUserRelations(List<CommUserRelation> list);
    
    /**
     * 获取投票数据列表
     * @param map
     * @return
     */
    public List<CommUserRelation> getUserRelationList(Map<String,Object> map);
    
    /**
     * 删除收藏
     * @param themeIds
     * @param userId
     * @return
     */
    public Integer deleteCollect(String themeIds,Integer userId);
}
