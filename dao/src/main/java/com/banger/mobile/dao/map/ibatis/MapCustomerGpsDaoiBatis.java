/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户地理位置实现类
 * Author     :yangy
 * Create Date:2013-3-19
 */
package com.banger.mobile.dao.map.ibatis;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.map.MapCustomerGpsDao;
import com.banger.mobile.domain.model.dept.DeptUserBean;
import com.banger.mobile.domain.model.map.MapCustomerGps;
import com.banger.mobile.ibatis.GenericDaoiBatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yangy
 * Date: 13-3-19
 * Time: 下午2:14
 * To change this template use File | Settings | File Templates.
 */
public class MapCustomerGpsDaoiBatis extends GenericDaoiBatis implements MapCustomerGpsDao {

    public MapCustomerGpsDaoiBatis(Class persistentClass) {
        super(MapCustomerGps.class);
    }

    public MapCustomerGpsDaoiBatis() {
        super(MapCustomerGps.class);
    }

    /**
     * 查询客户的GPS位置
     *
     * @param map 查询条件
     * @return
     */
    public List<MapCustomerGps> getCustomerGpsByCondition(Map<String, Object> map) {
        return this.getSqlMapClientTemplate().queryForList("getCustomerGpsByCondition", map);
    }

    /**
     * 初始化查询客户的GPS位置
     * @param map 查询条件
     * @return
     */
    public List<MapCustomerGps> initCustomerGpsPage(Map<String, Object> map) {
        return this.getSqlMapClientTemplate().queryForList("initCustomerGpsPage", map);
    }

    /**
     * 查询客户的GPS位置
     *
     * @param map 查询条件
     * @return
     */
    public List<MapCustomerGps> getCustomerGpsByAddress(Map<String, Object> map) {
        return this.getSqlMapClientTemplate().queryForList("getCustomerGpsByAddress", map);
    }

    /**
     * 查询PAD客户的GPS位置
     *
     * @param map 查询条件
     * @return
     */
    public List<MapCustomerGps> getPadCustomerGpsByCondition(Map<String, Object> map) {
        return this.getSqlMapClientTemplate().queryForList("getPadCustomerGpsByCondition", map);
    }

    /**
     * 查询客户GPS内的具体信息
     *
     * @param id
     * @return
     */
    public MapCustomerGps getCustomerGpsById(Integer id) {
        return (MapCustomerGps) this.getSqlMapClientTemplate().queryForObject("getCustomerGpsById", id);
    }

    public MapCustomerGps getCustomerGpsByCustomerId (Integer customerId){
        return (MapCustomerGps) this.getSqlMapClientTemplate().queryForObject("getCustomerGpsByCustomerId", customerId);
    }
    /**
     * 新增客户GPS位置
     *
     * @param mapCustomerGps
     */
    public void addMapCustomerGps(MapCustomerGps mapCustomerGps) {
        this.getSqlMapClientTemplate().insert("addMapCustomerGps", mapCustomerGps);
    }

    /**
     * 更新客户GPS位置
     *
     * @param mapCustomerGps
     */
    public void updateMapCustomerGps(MapCustomerGps mapCustomerGps) {
        this.getSqlMapClientTemplate().update("updateMapCustomerGps", mapCustomerGps);
    }


    /**
     * 根据经纬度查录音信息
     * @param map
     * @return
     */
    public PageUtil<MapCustomerGps> getMapCustomerGpsByLngLat(Map<String, Object> map, Page page) {
        ArrayList<MapCustomerGps> list = (ArrayList<MapCustomerGps>) this.findQueryPage(
                "getMapCustomerGpsList", "getMapCustomerGpsListCount", map, page);
        if (list == null) {
            list = new ArrayList<MapCustomerGps>();
        }
        return new PageUtil<MapCustomerGps>(list, page);
    }

    /**
     * 客户数量
     * @param map
     * @return
     */
    public Integer getMapCustomerGpsListCount(Map<String, Object> map){
        return  (Integer)this.getSqlMapClientTemplate().queryForObject("getMapCustomerGpsListCount", map);
    }

    public PageUtil<MapCustomerGps> getScaningCustomerList(Map<String, Object> map, Page page) {
        ArrayList<MapCustomerGps> list = (ArrayList<MapCustomerGps>) this.findQueryPage(
                "getScaningCustomerList", "getScaningCustomerListCount", map, page);
        if (list == null) {
            list = new ArrayList<MapCustomerGps>();
        }
        return new PageUtil<MapCustomerGps>(list, page);
    }
}
