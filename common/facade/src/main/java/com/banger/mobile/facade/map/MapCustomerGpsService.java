/*
* banger Inc.
* Copyright (c) 2009-2012 All Rights Reserved.
* ToDo       :客户地理位置接口...
* Author     :yangy
* Create Date:2013-3-19
*/
package com.banger.mobile.facade.map;

import banger.json.JsonArray;
import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.map.MapCustomerGps;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yangy
 * Date: 13-3-19
 * Time: 下午3:32
 * To change this template use File | Settings | File Templates.
 */
public interface MapCustomerGpsService {
    /**
     * 查询客户的GPS位置
     * @param map 查询条件
     * @return
     */
    public List<MapCustomerGps> getCustomerGpsByCondition(Map<String, Object> map);
    /**
     * 查询客户的GPS位置
     * @param map 查询条件
     * @return
     */
    public List<MapCustomerGps> getCustomerGpsByConditionForPad(Map<String, Object> map);

    /**
     * 取代理服务器IP地址
     * @return
     */
    //public String getProxyIp();

    /**
     * 取代理服务器开启状态
     * @return
     */
    public boolean isProxyFlag();

    /**
     * 查询Pad客户的GPS位置
     * @param map 查询条件
     * @return
     */
    public List<MapCustomerGps> getPadCustomerGpsByCondition(Map<String, Object> map);

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
     * 根据经纬度查所有客户信息
     * @param map
     * @param page
     * @return
     */
    public PageUtil<MapCustomerGps> getMapCustomerGpsByLngLatAll(Map<String, Object> map,Page page);
    /**
     * 查询客户数量
     * @param map
     * @return
     */
    public Integer getMapCustomerGpsListCount(Map<String, Object> map);

    /**
     * 查询所有客户数量
     * @param map
     * @return
     */
    public Integer getMapCustomerGpsListCountAll(Map<String, Object> map);
    /**
     * 查询客户的GPS位置
     * @param map 查询条件
     * @return
     */
    public List<MapCustomerGps> getCustomerGpsByAddress(Map<String, Object> map);

    /**
     * 根据经纬度查客户信息
     * @param map
     * @return
     */
    public PageUtil<MapCustomerGps> getScaningCustomerList(Map<String, Object> map,Page page);

    /**
     * 获得目标城市
     * @return
     */
    //public String getCityCoding();

    /**
     * 返回目标城市坐标
     * @return
     */
    public String getCityLngLat();

    /**
     *返回 配置文件中地图版本
     * @return
     */
    //public String getMapVersion();

    /**
     * 返回 配置文件中地图Key码
     * @return
     */
    //public String getMapKey();

    /**
     * 返回配置文件中MapJS引用IP
     * @return
     */
    public String getMapIp();
    
    public String getReportUrl154();
    
    public String getReportUrl155();
    
    public String getReportUrlTest();
    
    public MapCustomerGps getCustomerGpsByCustomerId (Integer customerId);
}
