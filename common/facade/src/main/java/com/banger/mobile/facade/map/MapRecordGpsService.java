/*
* banger Inc.
* Copyright (c) 2009-2012 All Rights Reserved.
* ToDo       :录音地理位置接口...
* Author     :yangy
* Create Date:2013-3-23
*/
package com.banger.mobile.facade.map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.map.MapRecordGps;
import net.sf.json.JSONArray;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yangy
 * Date: 13-3-23
 * Time: 下午4:23
 * To change this template use File | Settings | File Templates.
 */
public interface MapRecordGpsService {
    /**
     * 查询录音的GPS位置
     *
     * @param map 查询条件
     * @return
     */
    public List<MapRecordGps> getMapRecordGpsByCondition(Map<String, Object> map);

    /**
     * 查询录音GPS内的具体信息
     *
     * @param id
     * @return
     */
    public MapRecordGps getMapRecordGpsById(Integer id);

    /**
     * 新增录音GPS地址
     *
     * @param mapRecordGps
     */
    public void addMapRecordGps(MapRecordGps mapRecordGps);

    /**
     * 更新录音GPS地址
     *
     * @param mapRecordGps
     */
    public void updateMapRecordGps(MapRecordGps mapRecordGps);

    /**
     * 查询客户录音的GPS位置
     * @param map
     * @return
     */
    public JSONArray getCustomerOrRecordGps(Map<String, Object> map,String status) ;

    /**
     *  查询客户录音的具体信息
     * @param id
     * @return
     */
    public JSONArray getCustomerOrRecprdInfo(String id);

    /**
     * 查询客户和录音的Json数据
     * @param map
     * @return
     */
    public JSONArray getRecordGpsByLngLat(Map<String, Object> map);

    /**
     * 按时间查询Pad活动轨迹
     * @param map
     * @return
     */
    public JSONArray getActivityLocusJson(Map<String, Object> map);
    /**
     * 查询录音数据集合
     * @param map
     * @param page
     * @return
     */
    public PageUtil<MapRecordGps> getMapRecordGpsList(Map<String, Object> map,Page page);




}
