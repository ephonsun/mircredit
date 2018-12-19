package com.banger.mobile.dao;

import com.banger.mobile.dao.map.MapUserGpsLogDao;
import com.banger.mobile.domain.model.map.MapUserGps;
import com.banger.mobile.domain.model.map.MapUserGpsLog;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-8
 * Time: 下午4:56
 * To change this template use File | Settings | File Templates.
 */
public class MapUserGpsLogDaoTest  extends BaseDaoTestCase{

    private MapUserGpsLogDao mapUserGpsLogDao;

    public void setMapUserGpsLogDao(MapUserGpsLogDao mapUserGpsLogDao) {
        this.mapUserGpsLogDao = mapUserGpsLogDao;
    }

    /**
     * 非空测试
     * @throws Exception
     */
    public void testDaoNotNull()throws Exception{
        assertNotNull(mapUserGpsLogDao);
    }
    /**
     *新增
     * @throws Exception
     */
    public void testAddMapUserGpsLog()throws Exception{
        MapUserGpsLog bean=new MapUserGpsLog();
        bean.setGpsLat("30.2880768");
        bean.setGpsLng("120.12264");
        bean.setRemark("");
        bean.setUserGpsId(10);
        mapUserGpsLogDao.addMapUserGpsLog(bean);
    }
}
