/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :用户地理位置日志接口
 * Author     :yangy
 * Create Date:2012-5-17
 */
package com.banger.mobile.facade.map;

import com.banger.mobile.domain.model.map.MapUserGpsLog;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-8
 * Time: 下午4:51
 * To change this template use File | Settings | File Templates.
 */
public interface MapUserGpsLogService {

    /**
     * 新增用户GPS地址
     *
     * @param mapUserGpsLog
     */
    public void addMapUserGpsLog(MapUserGpsLog mapUserGpsLog);

    /**
     * 删除用户一个月前的坐标
     * @param userGpsId
     */
    public void delMapUserGpsLog(Integer userGpsId);

    /**
     * 查询用户当天gps列表
     * @param
     */
    public List<MapUserGpsLog> getUserGpsLogList(Map<String, Object> map);
}
