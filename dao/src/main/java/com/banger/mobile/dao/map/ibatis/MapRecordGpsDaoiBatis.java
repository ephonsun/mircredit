/*
* banger Inc.
* Copyright (c) 2009-2012 All Rights Reserved.
* ToDo       :录音地理位置实现类
* Author     :yangy
* Create Date:2012-8-20
*/
package com.banger.mobile.dao.map.ibatis;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.map.MapRecordGpsDao;
import com.banger.mobile.domain.model.map.MapCustomerGps;
import com.banger.mobile.domain.model.map.MapRecordGps;
import com.banger.mobile.ibatis.GenericDaoiBatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-3-23
 * Time: 下午4:15
 * To change this template use File | Settings | File Templates.
 */
public class MapRecordGpsDaoiBatis  extends GenericDaoiBatis implements MapRecordGpsDao {

    public MapRecordGpsDaoiBatis(Class persistentClass) {
        super(MapRecordGps.class);
    }

    public MapRecordGpsDaoiBatis() {
        super(MapRecordGps.class);
    }

    public List<MapRecordGps> getMapRecordGpsByCondition(Map<String, Object> map) {
        return this.getSqlMapClientTemplate().queryForList("getMapRecordGpsByCondition",map);
    }

    public List<MapRecordGps> getMapRecordGpsByLnglat(Map<String, Object> map) {
        return this.getSqlMapClientTemplate().queryForList("getMapRecordGpsByLngLat",map);
    }

    public MapRecordGps getMapRecordGpsById(Integer id) {
        return (MapRecordGps)this.getSqlMapClientTemplate().queryForObject("getMapRecordGpsById",id);
    }

    public void addMapRecordGps(MapRecordGps mapRecordGps) {
        this.getSqlMapClientTemplate().insert("addMapRecordGps",mapRecordGps);
    }

    public void updateMapRecordGps(MapRecordGps mapRecordGps) {
        this.getSqlMapClientTemplate().update("updateMapRecordGps",mapRecordGps);
    }

    public PageUtil<MapRecordGps> getMapRecordGpsList(Map<String, Object> map, Page page) {
        ArrayList<MapRecordGps> list = (ArrayList<MapRecordGps>) this.findQueryPage(
                "getMapRecordGpsList", "getMapRecordGpsListCount", map, page);
        if (list == null) {
            list = new ArrayList<MapRecordGps>();
        }
        return new PageUtil<MapRecordGps>(list, page);
    }
}
