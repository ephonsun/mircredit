/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户地理位置接口
 * Author     :yangy
 * Create Date:2012-5-17
 */
package com.banger.mobile.dao.map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.map.MapCustomerGps;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yangy
 * Date: 13-3-19
 * Time: 下午2:12
 * To change this template use File | Settings | File Templates.
 */
public interface MapCustomerGpsDao {
    /**
     * 查询客户的GPS位置
     * @param map 查询条件
     * @return
     */
    public List<MapCustomerGps> getCustomerGpsByCondition(Map<String, Object> map);

    /**
     * 初始化查询客户的GPS位置
     * @param map 查询条件
     * @return
     */
    public List<MapCustomerGps> initCustomerGpsPage(Map<String, Object> map);

    /**
     * 查询客户的GPS位置
     * @param map 查询条件
     * @return
     */
    public List<MapCustomerGps> getCustomerGpsByAddress(Map<String, Object> map);


    /**
     * 查询Pad客户的GPS位置
     * @param map 查询条件
     * @return
     */
    public List<MapCustomerGps> getPadCustomerGpsByCondition(Map<String, Object> map);

    /**
     * 查询客户GPS内的具体信息
     * @param id
     * @return
     */
    public MapCustomerGps getCustomerGpsById(Integer id);

    public MapCustomerGps getCustomerGpsByCustomerId (Integer customerId);
    /**
     * 新增客户GPS位置
     * @param mapCustomerGps
     */
    public void addMapCustomerGps(MapCustomerGps mapCustomerGps);

    /**
     * 更新客户GPS地址
     * @param mapCustomerGps
     */
    public void updateMapCustomerGps(MapCustomerGps mapCustomerGps);

    /**
     * 根据经纬度查客户信息
     * @param map
     * @return
     */
    public PageUtil<MapCustomerGps> getMapCustomerGpsByLngLat(Map<String, Object> map,Page page);

    /**
     * 客户数量
     * @param map
     * @return
     */
    public Integer getMapCustomerGpsListCount(Map<String, Object> map);


    /**
     * 根据经纬度查客户信息
     * @param map
     * @return
     */
    public PageUtil<MapCustomerGps> getScaningCustomerList(Map<String, Object> map,Page page);
}
