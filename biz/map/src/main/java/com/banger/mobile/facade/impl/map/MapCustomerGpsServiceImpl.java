/*
* banger Inc.
* Copyright (c) 2009-2012 All Rights Reserved.
* ToDo       :客户地理位置业务实现类...
* Author     :yangy
* Create Date:2013-3-19
*/
package com.banger.mobile.facade.impl.map;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.map.MapCustomerGpsDao;
import com.banger.mobile.domain.model.map.MapCustomerGps;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.map.MapCustomerGpsService;
import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;

/**
 * Created with IntelliJ IDEA.
 * User: yangy
 * Date: 13-3-19
 * Time: 下午3:35
 */
public class MapCustomerGpsServiceImpl  implements MapCustomerGpsService {

    private DeptFacadeService deptFacadeService;                    //机构 Service
    //private String cityCoding;                                      //配置文件中的目标城市
    private String cityLngLat;                                      //配置文件中目标城市中心坐标
    //private String proxyIp;                                         //配置文件中代理服务器IP
    private boolean proxyFlag;                                      //配置文件中代理服务器开关
    private String mapIp;                                           //配置文件中MapJS引用IP
    //private String mapVersion;                                      //配置文件中地图版本
    //private String mapKey;                                          //配置文件中地图Key码
    private String reportUrl154;
    private String reportUrl155;
    private String reportUrlTest;
    
    
    
    public String getReportUrl154() {
		return reportUrl154;
	}

	public void setReportUrl154(String reportUrl154) {
		this.reportUrl154 = reportUrl154;
	}

	public String getReportUrl155() {
		return reportUrl155;
	}

	public void setReportUrl155(String reportUrl155) {
		this.reportUrl155 = reportUrl155;
	}

	public String getReportUrlTest() {
		return reportUrlTest;
	}

	public void setReportUrlTest(String reportUrlTest) {
		this.reportUrlTest = reportUrlTest;
	}

	public String getMapIp() {
        return mapIp;
    }

    public void setMapIp(String mapIp) {
        this.mapIp = mapIp;
    }
    public boolean isProxyFlag() {
        return proxyFlag;
    }

    public void setProxyFlag(boolean proxyFlag) {
        this.proxyFlag = proxyFlag;
    }
    /*
    public String getMapVersion() {
        return mapVersion;
    }

    public void setMapVersion(String mapVersion) {
        this.mapVersion = mapVersion;
    }

    public String getMapKey() {
        return mapKey;
    }

    public void setMapKey(String mapKey) {
        this.mapKey = mapKey;
    }

    

    public String getProxyIp() {
        return proxyIp;
    }

    public void setProxyIp(String proxyIp) {
        this.proxyIp = proxyIp;
    }

    public String getCityCoding() {
        return cityCoding;
    }

    public void setCityCoding(String cityCoding) {
        this.cityCoding = cityCoding;
    }
*/
    public String getCityLngLat() {
        return cityLngLat;
    }

    public void setCityLngLat(String cityLngLat) {
        this.cityLngLat = cityLngLat;
    }

    public DeptFacadeService getDeptFacadeService() {
        return deptFacadeService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    protected final Log log = LogFactory.getLog(getClass());
    private MapCustomerGpsDao mapCustomerGpsDao;

    public void setMapCustomerGpsDao(MapCustomerGpsDao mapCustomerGpsDao) {
        this.mapCustomerGpsDao = mapCustomerGpsDao;
    }

    public MapCustomerGpsDao getMapCustomerGpsDao() {
        return mapCustomerGpsDao;
    }

    public List<MapCustomerGps> getCustomerGpsByCondition(Map<String, Object> map) {
        map.put("InChargeUserIds",getCurrentInChargeUserIds());
        map.put("InChargeDeptIds",getCurrentInChargeDeptIds());
        return mapCustomerGpsDao.getCustomerGpsByCondition(map);
    }

    public List<MapCustomerGps> getCustomerGpsByConditionForPad(Map<String, Object> map) {
        return mapCustomerGpsDao.getCustomerGpsByCondition(map);
    }

    public MapCustomerGps getCustomerGpsByCustomerId (Integer customerId){
    	return mapCustomerGpsDao.getCustomerGpsByCustomerId(customerId);
    }
    public List<MapCustomerGps> getPadCustomerGpsByCondition(Map<String, Object> map) {
        return mapCustomerGpsDao.getPadCustomerGpsByCondition(map);
    }

    public void addMapCustomerGps(MapCustomerGps mapCustomerGps) {
        mapCustomerGpsDao.addMapCustomerGps(mapCustomerGps);
    }

    public void updateMapCustomerGps(MapCustomerGps mapCustomerGps) {
        mapCustomerGpsDao.updateMapCustomerGps(mapCustomerGps);
    }

    public PageUtil<MapCustomerGps> getMapCustomerGpsByLngLat(Map<String, Object> map,Page page) {
        //map.put("InChargeUserIds", getCurrentInChargeUserIds());
        //map.put("InChargeDeptIds",getCurrentInChargeDeptIds());
    	map.put("InChargeUserIds",deptFacadeService.getUserIdsBelongToManager(this.getUserLoginInfo().getRoles(),"customerInfo"));
        return  mapCustomerGpsDao.getMapCustomerGpsByLngLat(map,page);
    }

    public PageUtil<MapCustomerGps> getMapCustomerGpsByLngLatAll(Map<String, Object> map,Page page) {

        return  mapCustomerGpsDao.getMapCustomerGpsByLngLat(map,page);
    }

    @Override
    public Integer getMapCustomerGpsListCount(Map<String, Object> map) {
        map.put("InChargeUserIds",deptFacadeService.getUserIdsBelongToManager(this.getUserLoginInfo().getRoles(),"customerInfo"));
        return mapCustomerGpsDao.getMapCustomerGpsListCount(map);
    }

    @Override
    public Integer getMapCustomerGpsListCountAll(Map<String, Object> map) {
        return mapCustomerGpsDao.getMapCustomerGpsListCount(map);
    }

    public List<MapCustomerGps> getCustomerGpsByAddress(Map<String, Object> map) {
//        map.put("InChargeUserIds",getCurrentInChargeUserIds());
//        map.put("InChargeDeptIds",getCurrentInChargeDeptIds());
    	map.put("InChargeUserIds",deptFacadeService.getUserIdsBelongToManager(this.getUserLoginInfo().getRoles(),"customerInfo"));
        return  mapCustomerGpsDao.getCustomerGpsByAddress(map);
    }

    public PageUtil<MapCustomerGps> getScaningCustomerList(Map<String, Object> map, Page page) {
//        map.put("InChargeUserIds",getCurrentInChargeUserIds());
//        map.put("InChargeDeptIds",getCurrentInChargeDeptIds());
    	map.put("InChargeUserIds",deptFacadeService.getUserIdsBelongToManager(this.getUserLoginInfo().getRoles(),"customerInfo"));
        return mapCustomerGpsDao.getScaningCustomerList(map,page);
    }

    /**
     * 当前用户有权限的      用户ids
     *
     * @return
     */
    private String getCurrentInChargeUserIds() {
        String userIds = "";
        Integer[] arr = deptFacadeService.getInChargeOfDeptUserIds();
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                if (userIds.equals(""))
                    userIds = arr[i].toString();
                else
                    userIds = userIds + "," + arr[i];
            }
        }
        if (userIds.equals("")) {
            userIds = getUserLoginInfo().getUserId().toString();
        } else {
            userIds = userIds + "," + getUserLoginInfo().getUserId();
        }
        return userIds;
    }
    /**
     * 当前用户有权限的      部门ids
     *
     * @return
     */
    private String getCurrentInChargeDeptIds() {
        String deptIds = "";
        Integer[] arr = deptFacadeService.getInChargeOfDeptIds();
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                if (deptIds.equals(""))
                    deptIds = arr[i].toString();
                else
                    deptIds = deptIds + "," + arr[i];
            }
        }
        if(deptIds.equals(""))
            deptIds="-1";
        return deptIds;
    }
    /**
     * 获得登录信息
     *
     * @return
     */
    private IUserInfo getUserLoginInfo() {
        HttpServletRequest req = org.apache.struts2.ServletActionContext.getRequest();
        HttpSession session = req.getSession();
        return (IUserInfo) session.getAttribute("LoginInfo");
    }
}
