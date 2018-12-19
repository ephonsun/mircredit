package com.banger.mobile.facade.impl.map;

import com.banger.mobile.dao.map.MapUserGpsLogDao;
import com.banger.mobile.domain.model.map.MapUserGpsLog;
import com.banger.mobile.facade.map.MapUserGpsLogService;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-8
 * Time: 下午4:52
 * To change this template use File | Settings | File Templates.
 */
public class MapUserGpsLogServiceImpl implements MapUserGpsLogService {

    private MapUserGpsLogDao mapUserGpsLogDao;

    public MapUserGpsLogDao getMapUserGpsLogDao() {
        return mapUserGpsLogDao;
    }

    public void setMapUserGpsLogDao(MapUserGpsLogDao mapUserGpsLogDao) {
        this.mapUserGpsLogDao = mapUserGpsLogDao;
    }

    public void addMapUserGpsLog(MapUserGpsLog mapUserGpsLog) {
        mapUserGpsLogDao.addMapUserGpsLog(mapUserGpsLog);
    }

    @Override
    public void delMapUserGpsLog(Integer userGpsId) {
        mapUserGpsLogDao.delMapUserGpsLog(userGpsId);
    }

    @Override
    public List<MapUserGpsLog> getUserGpsLogList(Map<String, Object> map) {
        return mapUserGpsLogDao.getUserGpsLogByCondition(map);
    }
}
