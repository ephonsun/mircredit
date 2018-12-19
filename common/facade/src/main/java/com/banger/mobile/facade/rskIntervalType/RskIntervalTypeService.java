/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :投资偏好类型service
 * Author     :yujh
 * Create Date:Jul 17, 2012
 */
package com.banger.mobile.facade.rskIntervalType;

import java.util.List;

import com.banger.mobile.domain.model.rskIntervalType.RskIntervalType;

/**
 * @author yujh
 * @version $Id: RskIntervalTypeService.java,v 0.1 Jul 17, 2012 9:55:31 AM Administrator Exp $
 */
public interface RskIntervalTypeService {
    /**
     * 获取所有投资偏好类型
     * @return
     */
    public List<RskIntervalType> getAllRskIntervalType();
    
    /**
     * 获取所有投资偏好类型集合
     * @return
     */
    public List<RskIntervalType> getAllRskIntervalTypes();

    /**
     * 根据Id删除类型
     * @param id
     */
    public void deleteRskIntervalrType(int id);

    /**
     * 添加类型
     * @param 
     */
    public void addRskIntervalType(RskIntervalType rskIntervalType);

    /**
     * 
     * @return
     */
    public RskIntervalType getRskIntervalTypeById(int id);

    /**
     * 修改类型
     * @param 
     */
    public void updateRskIntervalType(RskIntervalType rskIntervalType);

    /**
     * 上移或下移类型
     * @param id
     * @param moveType
     */
    public String moveTypeItems(int id, String moveType);
    /**
     * 获取相同名称的对象
     * @param rskIntervalType
     * @return
     */
    public List<RskIntervalType> getRskIntervalTypeBySameName(RskIntervalType rskIntervalType);
    /**
     * 启用停用
     * @param rskIntervalType
     */
    public void changeState(RskIntervalType rskIntervalType);
}
