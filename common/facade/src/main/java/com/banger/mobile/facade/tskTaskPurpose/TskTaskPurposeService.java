/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :联系目的service
 * Author     :yujh
 * Create Date:Nov 7, 2012
 */
package com.banger.mobile.facade.tskTaskPurpose;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.tskTaskPurpose.TskTaskPurpose;

/**
 * @author yujh
 * @version $Id: TskTaskPurposeService.java,v 0.1 Nov 7, 2012 5:14:52 PM Administrator Exp $
 */
public interface TskTaskPurposeService {
    /**
     * 查询联系目的列表(不包含停用)
     * @return
     */
    public List<TskTaskPurpose> getAllTskTaskPurpose();
    
    /**
     * 查询联系目的列表
     * @param isContainStop true 包含停用，false不包含
     * @return
     */
    public List<TskTaskPurpose> getAllTskTaskPurpose(boolean isContainStop);
    /**
     * 新建联系目的
     * @param tskTaskPurpose
     */
    public void addTskTaskPurpose(TskTaskPurpose tskTaskPurpose);

    /**
     * 根据id删除联系目的
     * @param id
     */
    public void deleteTskTaskPose(int id);

    /**
     * 根据id查询联系目的
     * @param id
     * @return
     */
    public TskTaskPurpose getTskTaskPurposeById(int id);

    /**
     * 更新
     * @param tskTaskPurpose
     */
    public void updateTskTaskPurpose(TskTaskPurpose tskTaskPurpose);

    /**
     * 查询需要移动的对象
     * @param parameters
     * @return
     */
    public TskTaskPurpose getNeedToMoveTskTaskPurpose(Map<String, Object> parameters);

    /**
     * 查询名字相同的对象
     * @param tskTaskPurpose
     * @return
     */
    public List<TskTaskPurpose> getTskTaskPurposeBySameName(TskTaskPurpose tskTaskPurpose);

    /**
     * /改变状态
     * @param tskTaskPurpose
     */
    public void changeState(TskTaskPurpose tskTaskPurpose);
    /**
     * 上移下移
     * @param id
     * @param moveType
     * @return
     */
    public String moveTypeItems(int id, String moveType);
}
