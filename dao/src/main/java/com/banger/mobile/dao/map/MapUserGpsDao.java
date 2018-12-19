/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :用户地理位置接口
 * Author     :yangy
 * Create Date:2012-5-17
 */
package com.banger.mobile.dao.map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.map.MapUserGps;

import java.util.List;
import java.util.Map;

/**
 * User: Administrator
 * Date: 13-3-14
 * Time: 下午1:29
 */
public interface MapUserGpsDao {
    /**
     * 查询用户的GPS位置
     *
     * @param map 查询条件
     * @return
     */
    public List<MapUserGps> getUserGpsByCondition(Map<String, Object> map);

    /**
     * 查询用户的GPS位置
     *
     * @param map 查询条件
     * @return
     */
    public List<MapUserGps> initUserGps(Map<String, Object> map);

    /**
     * 查询用户GPS内的具体信息
     *
     * @param id
     * @return
     */
    public MapUserGps getUserGpsById(Integer id);

    /**
     * 新增用户GPS地址
     *
     * @param mapUserGps
     */
    public void addMapUserGps(MapUserGps mapUserGps);

    /**
     * 更新用户GPS地址
     *
     * @param mapUserGps
     */
    public void updateMapUserGps(MapUserGps mapUserGps);

    /**
     * 根据经纬度查用户信息
     * @param map
     * @return
     */
    public PageUtil<MapUserGps> getUserGpsByLngLat(Map<String, Object> map,Page page);

    public Integer getUserListCount(Map<String,Object> map);

}

