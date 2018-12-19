/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :用户GPS位置dao测试类
 * Author     :yangy
 * Create Date:May 23, 2012
 */
package com.banger.mobile.dao;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.map.MapUserGpsDao;
import com.banger.mobile.dao.opeventLog.OpeventLogDao;
import com.banger.mobile.domain.model.map.MapUserGps;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: yangy
 * Date: 13-3-14
 * Time: 下午2:27
 * To change this template use File | Settings | File Templates.
 */
public class MapUserGpsDaoTest extends BaseDaoTestCase{
    private MapUserGpsDao mapUserGpsDao;

    public void setMapUserGpsDao(MapUserGpsDao mapUserGpsDao) {
        this.mapUserGpsDao = mapUserGpsDao;
    }

    /**
     * 非空测试
     * @throws Exception
     */
    public void testDaoNotNull()throws Exception{
        assertNotNull(mapUserGpsDao);
    }
    /**
     * 分页&查询测试
     * @throws Exception
     */
    public void testGetUserGpsByCondition()throws Exception{
        Map<String,Object> map= new HashMap<String,Object>();
        map.put("userName", "yang");
        List<MapUserGps> list=mapUserGpsDao.getUserGpsByCondition(map);
        System.out.println(list.size());

    }
    /**
     *新增
     * @throws Exception
     */
    public void testAddMapUserGps()throws Exception{
        MapUserGps bean=new MapUserGps();
        bean.setGpsLat("30.2880768");
        bean.setGpsLng("120.12264");
        bean.setRemark("");
        bean.setUserId(50);
        mapUserGpsDao.addMapUserGps(bean);
    }
    /**
     *新增
     * @throws Exception
     */
    public void testUpdateMapUserGps()throws Exception{
        MapUserGps bean=new MapUserGps();
        bean.setGpsLat("30.2880768");
        bean.setGpsLng("120.12266");
        bean.setRemark("");
        bean.setUserId(50);
        mapUserGpsDao.updateMapUserGps(bean);
    }

    public  void testGetMapCustomerGpsByLngLat()throws  Exception{
        Map<String,Object> map= new HashMap<String,Object>();
        map.put("gpsLng","120.120306");
        map.put("gpsLat","30.2815280");
        PageUtil<MapUserGps> list=mapUserGpsDao.getUserGpsByLngLat(map, new Page());
        System.out.println(list.getItems().size());
    }
}
