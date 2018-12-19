package com.banger.mobile.dao.map.ibatis;

import com.banger.mobile.dao.map.MapUserGpsLogDao;
import com.banger.mobile.domain.model.map.MapUserGpsLog;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.ibatis.GenericDaoiBatis;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-8
 * Time: 下午4:23
 * To change this template use File | Settings | File Templates.
 */
public class MapUserGpsLogDaoiBatis  extends GenericDaoiBatis implements MapUserGpsLogDao {

    public MapUserGpsLogDaoiBatis(Class persistentClass) {
        super(MapUserGpsLog.class);
    }

    public MapUserGpsLogDaoiBatis() {
        super(MapUserGpsLog.class);
    }
    public void addMapUserGpsLog(MapUserGpsLog mapUserGpsLog) {
        this.getSqlMapClientTemplate().insert("addMapUserGpsLog",mapUserGpsLog);
    }

    public List<MapUserGpsLog> getUserGpsLogByCondition(Map<String, Object> map) {
        return this.getSqlMapClientTemplate().queryForList("getUserGpsLogByCondition",map);
    }

    /**
     * 删除用户一个月前的坐标
     * @param userGpsId
     */
    public void delMapUserGpsLog(Integer userGpsId){
        this.getSqlMapClientTemplate().delete("delMapUserGpsLog",userGpsId);
    }

}
