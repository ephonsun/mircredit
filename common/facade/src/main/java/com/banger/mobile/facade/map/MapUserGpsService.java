/*
* banger Inc.
* Copyright (c) 2009-2012 All Rights Reserved.
* ToDo       :用户地理位置接口...
* Author     :yangy
* Create Date:2013-3-19
*/
package com.banger.mobile.facade.map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.map.MapUserGps;
import net.sf.json.JSONArray;

import java.util.List;
import java.util.Map;

/**
 * User: yangy
 * Date: 13-3-14
 * Time: 下午2:20
 */
public interface MapUserGpsService {
    /**
     * 查询用户的GPS位置
     * @param map 查询条件  l
     * @return
     */
    public List<MapUserGps> getUserGpsByCondition(Map<String, Object> map);

    /**
     * 查询客户用户的GPS位置
     * @param map 查询条件  l
     * @return
     */
    public JSONArray getCustomerOrUserGps(Map<String, Object> map);


    /**
     *  查询客户用户的具体信息
     * @param id
     * @return
     */
    public JSONArray getCustomerOrUserInfo(String id);

    /**
     *  查询客户用户的具体信息
     * @param map
     * @return
     */
    public JSONArray getUserGpsByLngLat(Map<String, Object> map);

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

    /**
     * 根据经纬度查用户信息
     * @param map
     * @param page
     * @return
     */
    public PageUtil<MapUserGps> getUserGpsByLngLatAll(Map<String, Object> map,Page page);

    /**
     * 地图定位页面初始化根据西南，东北坐标确定范围
     * @param map
     * @return
     */
    public  JSONArray initCustomerOrUserGps(Map<String,Object> map);

    /**
     * 获取用户数量
     * @param map
     * @return
     */
    public Integer getUserListCount(Map<String,Object> map);

    /**
     * 获取所有用户数量
     * @param map
     * @return
     */
    public Integer getUserListCountAll(Map<String,Object> map);
}
