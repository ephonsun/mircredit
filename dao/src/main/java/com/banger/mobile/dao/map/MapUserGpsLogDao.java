/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :用户地理位置日志接口
 * Author     :yangy
 * Create Date:2012-5-17
 */
package com.banger.mobile.dao.map;

import com.banger.mobile.domain.model.map.MapUserGps;
import com.banger.mobile.domain.model.map.MapUserGpsLog;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yangy
 * Date: 13-7-8
 * Time: 下午4:22
 * To change this template use File | Settings | File Templates.
 */
public interface MapUserGpsLogDao {

    /**
     * 新增用户GPS地址
     *
     * @param mapUserGpsLog
     */
    public void addMapUserGpsLog(MapUserGpsLog mapUserGpsLog);

    /**
     * 查询用户的GPS位置
     *
     * @param map 查询条件
     * @return
     */
    public List<MapUserGpsLog> getUserGpsLogByCondition(Map<String, Object> map);

    /**
     * 删除用户一个月前的坐标
     * @param userGpsId
     */
    public void delMapUserGpsLog(Integer userGpsId);
}
