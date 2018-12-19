/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :投资类型dao
 * Author     :yujh
 * Create Date:Jul 16, 2012
 */
package com.banger.mobile.dao.rskIntervalType;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.rskIntervalType.RskIntervalType;

/**
 * @author yujh
 * @version $Id: RskIntervalTypeDao.java,v 0.1 Jul 16, 2012 3:06:55 PM Administrator Exp $
 */
public interface RskIntervalTypeDao {
	/**
	 * 投资偏好列表
	 * @return
	 */
    public List<RskIntervalType> getAllRskIntervalType();
    
    /**
     * 获取所有投资偏好类型集合
     */
    public List<RskIntervalType> getAllRskIntervalTypes();

    /**
     * 添加
     * @param rskIntervalType
     */
    public void addRskIntervalType(RskIntervalType rskIntervalType);

    /**
     * 删除
     * @param id
     */
    public void deleteRskIntervalType(int id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public RskIntervalType getTypeById(int id);

    /**
     * 更新
     * @param rskIntervalType
     */
    public void updateRskIntervalType(RskIntervalType rskIntervalType);

    /**
     * 查询排序号最大的
     * @return
     */
    public RskIntervalType getMaxSortNoRskIntervalType();

    /**
     * 查询排序号最小的
     * @return
     */
    public RskIntervalType getMinSortNoRskIntervalType();

    /**
     * 查询需要移动的对象
     * @param parameters
     * @return
     */
    public RskIntervalType getNeedToMoveRskIntervalType(Map<String, Object> parameters);
    
    /**
     * 查询名字相同的对象
     * @param rskIntervalType
     * @return
     */
    public List<RskIntervalType> getRskIntervalTypeBySameName(RskIntervalType rskIntervalType);
    /**
     * 改变状态
     * @param rskIntervalType
     */
    public void changeState(RskIntervalType rskIntervalType);
}
