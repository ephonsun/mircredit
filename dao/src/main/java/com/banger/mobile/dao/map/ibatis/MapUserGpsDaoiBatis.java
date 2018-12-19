/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :用户地理位置实现类
 * Author     :yangy
 * Create Date:2012-8-20
 */
package com.banger.mobile.dao.map.ibatis;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.map.MapUserGpsDao;
import com.banger.mobile.domain.model.map.MapCustomerGps;
import com.banger.mobile.domain.model.map.MapUserGps;
import com.banger.mobile.ibatis.GenericDaoiBatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: yangy
 * Date: 13-3-14
 * Time: 下午1:41
 */
public class MapUserGpsDaoiBatis  extends GenericDaoiBatis implements MapUserGpsDao {

    public MapUserGpsDaoiBatis(Class persistentClass) {
        super(MapUserGps.class);
    }

    public MapUserGpsDaoiBatis() {
        super(MapUserGps.class);
    }
    /**
     * 查询用户的GPS位置
     * @param map 查询条件  l
     * @return
     */
    public List<MapUserGps> getUserGpsByCondition(Map<String, Object> map) {
        return this.getSqlMapClientTemplate().queryForList("getUserGpsByCondition",map);
    }

    public List<MapUserGps> initUserGps(Map<String, Object> map) {
        return this.getSqlMapClientTemplate().queryForList("initUserGps",map);
    }

    /**
     * 查询用户GPS内的具体信息
     * @param id
     * @return
     */
    public MapUserGps getUserGpsById(Integer id) {
        return (MapUserGps)this.getSqlMapClientTemplate().queryForObject("getUserGpsById",id);
    }
    /**
     * 新增用户GPS地址
     * @param mapUserGps
     */
    public void addMapUserGps(MapUserGps mapUserGps) {
        this.getSqlMapClientTemplate().insert("addMapUserGps",mapUserGps);
    }
    /**
     * 更新用户GPS地址
     * @param mapUserGps
     */
    public void updateMapUserGps(MapUserGps mapUserGps) {
        this.getSqlMapClientTemplate().update("updateMapUserGps",mapUserGps);
    }

    /**
     * 根据坐标找用户信息
     * @param map
     * @return
     */
    public PageUtil<MapUserGps> getUserGpsByLngLat(Map<String, Object> map,Page page) {
        ArrayList<MapUserGps> list = (ArrayList<MapUserGps>) this.findQueryPage(
                "getUserList", "getUserListCount", map, page);
        if (list == null) {
            list = new ArrayList<MapUserGps>();
        }
        return new PageUtil<MapUserGps>(list, page);
    }

    /**
     * 获取用户数量
     * @param map
     * @return
     */
    public Integer getUserListCount(Map<String,Object> map){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getUserListCount",map);
    }


}
