/*
* banger Inc.
* Copyright (c) 2009-2012 All Rights Reserved.
* ToDo       :用户地理位置业务实现类...
* Author     :yangy
* Create Date:2013-3-14
*/
package com.banger.mobile.facade.impl.map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.map.MapCustomerGpsDao;
import com.banger.mobile.dao.map.MapUserGpsDao;
import com.banger.mobile.domain.model.map.MapCustomerGps;
import com.banger.mobile.domain.model.map.MapUserGps;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.loan.LnLoanService;
import com.banger.mobile.facade.map.MapCustomerGpsService;
import com.banger.mobile.facade.map.MapUserGpsService;
import com.banger.mobile.facade.microTask.TskMicroTaskProgressService;
import com.banger.mobile.facade.user.SysUserService;
import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import net.sf.json.JSONArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: yangy
 * Date: 13-3-14
 * Time: 下午4:28
 */
public class MapUserGpsServiceImpl implements MapUserGpsService {

    protected final Log log = LogFactory.getLog(getClass());
    private LnLoanService lnLoanService;                     //贷款业务类
    private MapCustomerGpsDao mapCustomerGpsDao;            //客户GPS Service
    private SysUserService sysUserService;                          //用户Service
    private DeptFacadeService deptFacadeService;                    //机构 Service
    private TskMicroTaskProgressService tskMicroTaskProgressService; //用户任务进度业务类

    public TskMicroTaskProgressService getTskMicroTaskProgressService() {
        return tskMicroTaskProgressService;
    }

    public void setTskMicroTaskProgressService(TskMicroTaskProgressService tskMicroTaskProgressService) {
        this.tskMicroTaskProgressService = tskMicroTaskProgressService;
    }

    public LnLoanService getLnLoanService() {
        return lnLoanService;
    }

    public void setLnLoanService(LnLoanService lnLoanService) {
        this.lnLoanService = lnLoanService;
    }

    public DeptFacadeService getDeptFacadeService() {
        return deptFacadeService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    public SysUserService getSysUserService() {

        return sysUserService;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    private MapUserGpsDao mapUserGpsDao;

    public MapCustomerGpsDao getMapCustomerGpsDao() {
        return mapCustomerGpsDao;
    }

    public void setMapCustomerGpsDao(MapCustomerGpsDao mapCustomerGpsDao) {
        this.mapCustomerGpsDao = mapCustomerGpsDao;
    }

    public void setMapUserGpsDao(MapUserGpsDao mapUserGpsDao) {
        this.mapUserGpsDao = mapUserGpsDao;
    }

    public MapUserGpsDao getMapUserGpsDao() {
        return mapUserGpsDao;
    }

    /**
     * 查询用户的GPS位置
     *
     * @param map 查询条件
     * @return
     */
    public List<MapUserGps> getUserGpsByCondition(Map<String, Object> map) {
        return mapUserGpsDao.getUserGpsByCondition(map);
    }



    /**
     * 取出列表中客户ID集合
     *
     * @param crmList
     * @return
     */
    public String getCustomerIds(List<MapCustomerGps> crmList) {
        StringBuilder sb = new StringBuilder();
        String cusIds = "";
        for (MapCustomerGps crm : crmList) {
            sb.append(crm.getCustomerId() + ",");
        }
        if (sb.length() > 0) {
            cusIds = sb.substring(0, sb.length() - 1);
        }
        return cusIds;
    }


    /**
     * 包装客户内容
     *
     * @param tempMap
     * @param crm
     */
    public void packageCustomer(Map<String, Object> tempMap, MapCustomerGps crm) {
        tempMap.put("gpsLat", crm.getGpsLat());
        tempMap.put("gpsLng", crm.getGpsLng());
        tempMap.put("type", 2);
        tempMap.put("address", crm.getAddress());
        tempMap.put("phoneNo", crm.getPhoneNo() == null ? "" : crm.getPhoneNo());
        tempMap.put("customerName", crm.getCustomerName() == null ? "" : crm.getCustomerName());
        tempMap.put("customerTitle", crm.getCustomerTitle() == null ? "" : crm.getCustomerTitle());
        tempMap.put("gpsId", "customer," + crm.getCustomerGpsId());
        tempMap.put("customerId", crm.getCustomerId());
        tempMap.put("customerGpsId", crm.getCustomerGpsId() == null ? "" : crm.getCustomerGpsId());
    }

    /**
     * 包装用户内容
     *
     * @param tempMap
     * @param gps
     */
    public void packapeUser(Map<String, Object> tempMap, MapUserGps gps) {
        tempMap.put("gpsLat", gps.getGpsLat());
        tempMap.put("gpsLng", gps.getGpsLng());
        tempMap.put("type", 1);
        tempMap.put("userName", gps.getUserName() == null ? "" : gps.getUserName());
        tempMap.put("leadName", gps.getLeadName());
        tempMap.put("account", gps.getAccount());
        tempMap.put("userGpsId", gps.getUserGpsId());
        tempMap.put("gpsId", "user," + gps.getUserGpsId());
    }

    /**
     * 查询客户用户的GPS位置
     *
     * @param map
     * @return
     */
    public JSONArray getCustomerOrUserGps(Map<String, Object> map) {
        Map<String, Object> tempMap = new HashMap<String, Object>();
        JSONArray jsonArray = new JSONArray();
//        map.put("InChargeUserIds", getCurrentInChargeUserIds());
//        map.put("InChargeDeptIds",getCurrentInChargeDeptIds());
        map.put("InChargeUserIds",deptFacadeService.getUserIdsBelongToManager(this.getUserLoginInfo().getRoles(),"customerInfo"));
        Map<String, Object> repeatMap = new HashMap<String, Object>();
        if (map.get("customerId") != null && !map.get("customerId").equals("")) {
            //查询客户的位置
            List<MapCustomerGps> crmList = mapCustomerGpsDao.getCustomerGpsByCondition(map);
            String cusIds = getCustomerIds(crmList);
            Map<Integer, String> cusIdsMap = lnLoanService.getRecentlyLoanStatusByCusIds(cusIds);
            for (MapCustomerGps crm : crmList) {
                tempMap.clear();
                packageCustomer(tempMap, crm);
                tempMap.put("loanFlat", cusIdsMap.get(crm.getCustomerId()) == null ? 1 : 2);       //取不到贷款状态设置贷款图标
                tempMap.put("size", crmList.size());
                if (repeatMap.get(crm.getGpsLat() + crm.getGpsLng()) == null)
                    jsonArray.add(tempMap);
                repeatMap.put(crm.getGpsLat() + crm.getGpsLng(), crm.getGpsLat() + crm.getGpsLng());
            }
        } else {
            if (!map.get("customerName").equals("") && map.get("userName").equals("")) {
                //查询客户的位置
                List<MapCustomerGps> crmList = mapCustomerGpsDao.getCustomerGpsByCondition(map);
                String cusIds = getCustomerIds(crmList);
                Map<Integer, String> cusIdsMap = lnLoanService.getRecentlyLoanStatusByCusIds(cusIds);
                for (MapCustomerGps crm : crmList) {
                    tempMap.clear();
                    packageCustomer(tempMap, crm);
                    tempMap.put("loanFlat", cusIdsMap.get(crm.getCustomerId()) == null ? 1 : 2);       //取不到贷款状态设置贷款图标
                    tempMap.put("size", crmList.size());
                    if (repeatMap.get(crm.getGpsLat() + crm.getGpsLng()) == null)
                        jsonArray.add(tempMap);
                    repeatMap.put(crm.getGpsLat() + crm.getGpsLng(), crm.getGpsLat() + crm.getGpsLng());
                }
            } else if (map.get("customerName").equals("") && (!map.get("userName").equals(""))) {
                if (deptFacadeService.isInChargeOfDepartment()) { //业务主管
                    //查询用户的位置
                    List<MapUserGps> userGpsList = mapUserGpsDao.getUserGpsByCondition(map);
                    String workPlan = "";
                    repeatMap.clear();
                    for (MapUserGps gps : userGpsList) {
                        workPlan = tskMicroTaskProgressService.getTaskScheduleByUserId(gps.getUserId());
                        tempMap.clear();
                        packapeUser(tempMap, gps);
                        tempMap.put("workPlan", workPlan);
                        tempMap.put("size", userGpsList.size());
                        if (repeatMap.get(gps.getGpsLat() + gps.getGpsLng()) == null)
                            jsonArray.add(tempMap);
                        repeatMap.put(gps.getGpsLat() + gps.getGpsLng(), gps.getGpsLat() + gps.getGpsLng());
                    }
                }
            } else if ((!map.get("customerName").equals("") && (!map.get("userName").equals(""))) || (map.get("customerName").equals("") && map.get("userName").equals("") && !map.get("beginGpsLng").equals(""))) {
                //查询客户的位置
                List<MapCustomerGps> crmList = mapCustomerGpsDao.getCustomerGpsByCondition(map);
                StringBuilder sb = new StringBuilder();
                String cusIds = getCustomerIds(crmList);
                Map<Integer, String> cusIdsMap = lnLoanService.getRecentlyLoanStatusByCusIds(cusIds);
                for (MapCustomerGps crm : crmList) {
                    tempMap.clear();
                    packageCustomer(tempMap, crm);
                    tempMap.put("loanFlat", cusIdsMap.get(crm.getCustomerId()) == null ? 1 : 2);       //取不到贷款状态设置贷款图标
                    tempMap.put("size", crmList.size());
                    if (repeatMap.get(crm.getGpsLat() + crm.getGpsLng()) == null)
                        jsonArray.add(tempMap);
                    repeatMap.put(crm.getGpsLat() + crm.getGpsLng(), crm.getGpsLat() + crm.getGpsLng());
                }
                if (deptFacadeService.isInChargeOfDepartment()) {//业务主管
                    //查询用户的位置
                    List<MapUserGps> userGpsList = mapUserGpsDao.getUserGpsByCondition(map);
                    String workPlan = "";
                    repeatMap.clear();
                    for (MapUserGps gps : userGpsList) {
                        workPlan = tskMicroTaskProgressService.getTaskScheduleByUserId(gps.getUserId());
                        tempMap.clear();
                        packapeUser(tempMap, gps);
                        tempMap.put("workPlan", workPlan);
                        tempMap.put("size", userGpsList.size());
                        if (repeatMap.get(gps.getGpsLat() + gps.getGpsLng()) == null)
                            jsonArray.add(tempMap);
                        repeatMap.put(gps.getGpsLat() + gps.getGpsLng(), gps.getGpsLat() + gps.getGpsLng());
                    }
                }
            }
        }
        return jsonArray;
    }

    /**
     * 查询客户用户的具体信息
     *
     * @param id
     * @return
     */
    public JSONArray getCustomerOrUserInfo(String id) {
        Map<String, Object> tempMap = new HashMap<String, Object>();
        JSONArray jsonArray = new JSONArray();
        String[] arr = id.split(",");
        if (!arr[0].equals("user")) {
            MapCustomerGps mapCustomerGps = mapCustomerGpsDao.getCustomerGpsById(Integer.parseInt(arr[1]));
            Map<Integer, String> cusIdsMap = lnLoanService.getRecentlyLoanStatusByCusIds("" + mapCustomerGps.getCustomerId());
            String loanStatus = (cusIdsMap.get(mapCustomerGps.getCustomerId()) == null ? "" : cusIdsMap.get(mapCustomerGps.getCustomerId()));
            tempMap.put("loanStatus", loanStatus);
            tempMap.put("userName", mapCustomerGps.getUserName() == null ? "" : mapCustomerGps.getUserName());
            tempMap.put("phoneNo", mapCustomerGps.getPhoneNo() == null ? "" : mapCustomerGps.getPhoneNo());
            tempMap.put("customerName", mapCustomerGps.getCustomerName() == null ? "" : mapCustomerGps.getCustomerName());
            tempMap.put("customerTitle", mapCustomerGps.getCustomerTitle() == null ? "" : mapCustomerGps.getCustomerTitle());
            tempMap.put("gpsLat", mapCustomerGps.getGpsLat());
            tempMap.put("gpsLng", mapCustomerGps.getGpsLng());
            tempMap.put("address", mapCustomerGps.getAddress());
            tempMap.put("customerId", mapCustomerGps.getCustomerId());
            tempMap.put("isNogood",mapCustomerGps.getIsNogood());
            tempMap.put("customerGpsId", mapCustomerGps.getCustomerGpsId());
            tempMap.put("loanFlat", cusIdsMap.get(mapCustomerGps.getCustomerId()) == null ? 1 : 2);       //取不到贷款状态设置贷款图标
            jsonArray.add(tempMap);
        } else {
            MapUserGps mapUserGps = mapUserGpsDao.getUserGpsById(Integer.parseInt(arr[1]));
            conversionRoleName(mapUserGps);
            String workPlan = tskMicroTaskProgressService.getTaskScheduleByUserId(mapUserGps.getUserId());
            SysUser sysUser = sysUserService.getSysUserById(this.getUserLoginInfo().getUserId());
            tempMap.put("userId", mapUserGps.getUserId());
            tempMap.put("gpsLat", mapUserGps.getGpsLat());
            tempMap.put("gpsLng", mapUserGps.getGpsLng());
            tempMap.put("userName", mapUserGps.getUserName() == null ? "" : mapUserGps.getUserName());
            tempMap.put("leadName", sysUser.getUserName());
            tempMap.put("account", mapUserGps.getAccount());
            tempMap.put("workPlan", workPlan);
            tempMap.put("userGpsId", mapUserGps.getUserGpsId());
            jsonArray.add(tempMap);
        }
        return jsonArray;
    }

    /**
     * 根据客户用户的GPS地址
     *
     * @param map
     * @return
     */
    public JSONArray getUserGpsByLngLat(Map<String, Object> map) {
        Map<String, Object> tempMap = new HashMap<String, Object>();
//        map.put("InChargeUserIds", getCurrentInChargeUserIds());
//        map.put("InChargeDeptIds",getCurrentInChargeDeptIds());
        map.put("InChargeUserIds",deptFacadeService.getUserIdsBelongToManager(this.getUserLoginInfo().getRoles(),"customerInfo"));
        JSONArray jsonArray = new JSONArray();
        if (!map.get("type").equals("user")) {
            List<MapCustomerGps> mapCustomerGpsList = mapCustomerGpsDao.getCustomerGpsByCondition(map);
            StringBuilder sb = new StringBuilder();
            for (MapCustomerGps crm : mapCustomerGpsList) {
                sb.append(crm.getCustomerId() + ",");
            }
            String cusIds = "";
            if (sb.length() > 0) {
                cusIds = sb.substring(0, sb.length() - 1);
            }
            Map<Integer, String> cusIdsMap = new HashMap<Integer, String>();
            cusIdsMap = lnLoanService.getRecentlyLoanStatusByCusIds(cusIds);
            for (MapCustomerGps mapCustomerGps : mapCustomerGpsList) {
                tempMap.clear();
                String loanStatus = (cusIdsMap.get(mapCustomerGps.getCustomerId()) == null ? "" : cusIdsMap.get(mapCustomerGps.getCustomerId()));
                log.error("贷款状态是"+loanStatus);
                tempMap.put("loanStatus", loanStatus);
                tempMap.put("userName", mapCustomerGps.getUserName() == null ? "" : mapCustomerGps.getUserName());
                tempMap.put("phoneNo", mapCustomerGps.getPhoneNo() == null ? "" : mapCustomerGps.getPhoneNo());
                tempMap.put("customerName", mapCustomerGps.getCustomerName() == null ? "" : mapCustomerGps.getCustomerName());
                tempMap.put("customerTitle", mapCustomerGps.getCustomerTitle() == null ? "" : mapCustomerGps.getCustomerTitle());
                tempMap.put("gpsLat", mapCustomerGps.getGpsLat());
                tempMap.put("gpsLng", mapCustomerGps.getGpsLng());
                tempMap.put("type", 2);
                tempMap.put("address", mapCustomerGps.getAddress());
                tempMap.put("customerId", mapCustomerGps.getCustomerId());
                tempMap.put("isNogood",mapCustomerGps.getIsNogood());
                tempMap.put("customerGpsId", mapCustomerGps.getCustomerGpsId());
                jsonArray.add(tempMap);
            }
        } else {
            List<MapUserGps> mapUserGpsList = mapUserGpsDao.getUserGpsByCondition(map);
            for (MapUserGps mapUserGps : mapUserGpsList) {
                tempMap.clear();
                conversionRoleName(mapUserGps);
                String workPlan = tskMicroTaskProgressService.getTaskScheduleByUserId(mapUserGps.getUserId());
                tempMap.put("userId", mapUserGps.getUserId());
                tempMap.put("gpsLat", mapUserGps.getGpsLat());
                tempMap.put("gpsLng", mapUserGps.getGpsLng());
                tempMap.put("type", 1);
                tempMap.put("userName", mapUserGps.getUserName() == null ? "" : mapUserGps.getUserName());
                tempMap.put("leadName", mapUserGps.getLeadName());
                tempMap.put("account", mapUserGps.getAccount());
                tempMap.put("workPlan", workPlan);
                jsonArray.add(tempMap);
            }
        }
        return jsonArray;
    }

    public void addMapUserGps(MapUserGps mapUserGps) {
        mapUserGpsDao.addMapUserGps(mapUserGps);
    }

    public void updateMapUserGps(MapUserGps mapUserGps) {
        mapUserGpsDao.updateMapUserGps(mapUserGps);
    }

    public PageUtil<MapUserGps> getUserGpsByLngLat(Map<String, Object> map, Page page) {
        map.put("InChargeUserIds", getCurrentInChargeUserIds());
        return mapUserGpsDao.getUserGpsByLngLat(map, page);
    }

    public PageUtil<MapUserGps> getUserGpsByLngLatAll(Map<String, Object> map, Page page) {
        return mapUserGpsDao.getUserGpsByLngLat(map, page);
    }

    public Integer getUserListCount(Map<String,Object> map){
        map.put("InChargeUserIds", getCurrentInChargeUserIds());
        return mapUserGpsDao.getUserListCount(map);
    }

    public Integer getUserListCountAll(Map<String,Object> map){
        return mapUserGpsDao.getUserListCount(map);
    }

    /**
     * 地图定位页面初始化根据西南，东北坐标确定范围
     *
     * @param map
     * @return
     */
    public JSONArray initCustomerOrUserGps(Map<String, Object> map) {
        Map<String, Object> tempMap = new HashMap<String, Object>();
        JSONArray jsonArray = new JSONArray();
//        map.put("InChargeUserIds", getCurrentInChargeUserIds());
//        map.put("InChargeDeptIds", getCurrentInChargeDeptIds());
        map.put("InChargeUserIds",deptFacadeService.getUserIdsBelongToManager(this.getUserLoginInfo().getRoles(),"customerInfo"));
        Map<String, Object> repeatMap = new HashMap<String, Object>();
        List<MapCustomerGps> mapCustomerGpsList = mapCustomerGpsDao.initCustomerGpsPage(map);
        StringBuilder sb = new StringBuilder();
        for (MapCustomerGps crm : mapCustomerGpsList) {
            sb.append(crm.getCustomerId() + ",");
        }
        String cusIds = "";
        if (sb.length() > 0) {
            cusIds = sb.substring(0, sb.length() - 1);
        }
        Map<Integer, String> cusIdsMap = new HashMap<Integer, String>();
        cusIdsMap = lnLoanService.getRecentlyLoanStatusByCusIds(cusIds);
        for (MapCustomerGps mapCustomerGps : mapCustomerGpsList) {
            tempMap.clear();
            String loanStatus = (cusIdsMap.get(mapCustomerGps.getCustomerId()) == null ? "" : cusIdsMap.get(mapCustomerGps.getCustomerId()));
            tempMap.put("loanStatus", loanStatus);
            tempMap.put("userName", mapCustomerGps.getUserName() == null ? "" : mapCustomerGps.getUserName());
            tempMap.put("phoneNo", mapCustomerGps.getPhoneNo() == null ? "" : mapCustomerGps.getPhoneNo());
            tempMap.put("customerName", mapCustomerGps.getCustomerName() == null ? "" : mapCustomerGps.getCustomerName());
            tempMap.put("customerTitle", mapCustomerGps.getCustomerTitle() == null ? "" : mapCustomerGps.getCustomerTitle());
            tempMap.put("gpsLat", mapCustomerGps.getGpsLat());
            tempMap.put("gpsLng", mapCustomerGps.getGpsLng());
            tempMap.put("gpsId", "customer," + mapCustomerGps.getCustomerGpsId());
            tempMap.put("loanFlat", cusIdsMap.get(mapCustomerGps.getCustomerId()) == null ? 1 : 2);       //取不到贷款状态设置贷款图标
            tempMap.put("type", 2);
            tempMap.put("address", mapCustomerGps.getAddress());
            tempMap.put("size", mapCustomerGpsList.size());
            tempMap.put("customerId", mapCustomerGps.getCustomerId());
            tempMap.put("customerGpsId", mapCustomerGps.getCustomerGpsId());
            if (repeatMap.get(mapCustomerGps.getGpsLat() + mapCustomerGps.getGpsLng()) == null)
                jsonArray.add(tempMap);
            repeatMap.put(mapCustomerGps.getGpsLat() + mapCustomerGps.getGpsLng(), mapCustomerGps.getGpsLat() + mapCustomerGps.getGpsLng());
        }
//        map.put("inChargeUserIds", getCurrentInChargeUserIds());
        map.put("InChargeUserIds",deptFacadeService.getUserIdsBelongToManager(this.getUserLoginInfo().getRoles(),"customerInfo"));
        List<MapUserGps> mapUserGpsList = mapUserGpsDao.initUserGps(map);
        repeatMap.clear();
        if (deptFacadeService.isInChargeOfDepartment()) {//业务主管
            for (MapUserGps mapUserGps : mapUserGpsList) {
                tempMap.clear();
                conversionRoleName(mapUserGps);
                String workPlan = tskMicroTaskProgressService.getTaskScheduleByUserId(mapUserGps.getUserId());
                tempMap.put("userId", mapUserGps.getUserId());
                tempMap.put("gpsLat", mapUserGps.getGpsLat());
                tempMap.put("gpsLng", mapUserGps.getGpsLng());
                tempMap.put("type", 1);
                tempMap.put("userName", mapUserGps.getUserName() == null ? "" : mapUserGps.getUserName());
                tempMap.put("leadName", mapUserGps.getLeadName());
                tempMap.put("size", mapUserGpsList.size());
                tempMap.put("gpsId", "user," + mapUserGps.getUserGpsId());
                tempMap.put("account", mapUserGps.getAccount());
                tempMap.put("userGpsId", mapUserGps.getUserGpsId());
                tempMap.put("workPlan", workPlan);
                if (repeatMap.get(mapUserGps.getGpsLat() + mapUserGps.getGpsLng()) == null)
                    jsonArray.add(tempMap);
                repeatMap.put(mapUserGps.getGpsLat() + mapUserGps.getGpsLng(), mapUserGps.getGpsLat() + mapUserGps.getGpsLng());
            }
        }
        return jsonArray;
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
     * 用户GPS结果中对角色名称进行增加
     *
     * @param mapUserGps
     * @return
     */
    public void conversionRoleName(MapUserGps mapUserGps) {
        try {
            if (!mapUserGps.getUserId().equals("")) {
                Map<Integer, String> leadNamesMap = sysUserService.getLeadNamesByUserId("" + mapUserGps.getUserId());
                mapUserGps.setLeadName(leadNamesMap.get(mapUserGps.getUserId()));
            }
        } catch (Exception e) {
            log.error("conversionRoleName action error:" + e.getMessage());
        }
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
