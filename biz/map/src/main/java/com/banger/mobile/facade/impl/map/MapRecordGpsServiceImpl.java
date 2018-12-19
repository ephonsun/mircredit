/*
* banger Inc.
* Copyright (c) 2009-2012 All Rights Reserved.
* ToDo       :录音地理位置业务实现类...
* Author     :yangy
* Create Date:2013-3-14
*/
package com.banger.mobile.facade.impl.map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.map.MapCustomerGpsDao;
import com.banger.mobile.dao.map.MapRecordGpsDao;
import com.banger.mobile.dao.map.MapUserGpsDao;
import com.banger.mobile.dao.map.MapUserGpsLogDao;
import com.banger.mobile.domain.model.map.MapCustomerGps;
import com.banger.mobile.domain.model.map.MapRecordGps;
import com.banger.mobile.domain.model.map.MapUserGps;
import com.banger.mobile.domain.model.map.MapUserGpsLog;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.loan.LnLoanService;
import com.banger.mobile.facade.map.MapRecordGpsService;
import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import net.sf.json.JSONArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yangy
 * Date: 13-3-23
 * Time: 下午4:26
 * To change this template use File | Settings | File Templates.
 */
public class MapRecordGpsServiceImpl implements MapRecordGpsService {

    protected final Log log = LogFactory.getLog(getClass());
    private MapRecordGpsDao mapRecordGpsDao;
    private MapCustomerGpsDao mapCustomerGpsDao;
    private LnLoanService lnLoanService;                     //贷款业务类
    private DeptFacadeService deptFacadeService;                    //机构 Service
    private MapUserGpsLogDao mapUserGpsLogDao;

    public void setMapUserGpsLogDao(MapUserGpsLogDao mapUserGpsLogDao) {
        this.mapUserGpsLogDao = mapUserGpsLogDao;
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

    public MapCustomerGpsDao getMapCustomerGpsDao() {
        return mapCustomerGpsDao;
    }

    public void setMapCustomerGpsDao(MapCustomerGpsDao mapCustomerGpsDao) {
        this.mapCustomerGpsDao = mapCustomerGpsDao;
    }

    public MapRecordGpsDao getMapRecordGpsDao() {
        return mapRecordGpsDao;
    }

    public void setMapRecordGpsDao(MapRecordGpsDao mapRecordGpsDao) {
        this.mapRecordGpsDao = mapRecordGpsDao;
    }

    public List<MapRecordGps> getMapRecordGpsByCondition(Map<String, Object> map) {
        return mapRecordGpsDao.getMapRecordGpsByCondition(map);
    }

    public MapRecordGps getMapRecordGpsById(Integer id) {
        return mapRecordGpsDao.getMapRecordGpsById(id);
    }

    public void addMapRecordGps(MapRecordGps mapRecordGps) {
        mapRecordGpsDao.addMapRecordGps(mapRecordGps);
    }

    public void updateMapRecordGps(MapRecordGps mapRecordGps) {
        mapRecordGpsDao.updateMapRecordGps(mapRecordGps);
    }


    /**
     * 查询客户录音的GPS位置
     *
     * @param map
     * @return
     */
    public JSONArray getCustomerOrRecordGps(Map<String, Object> map,String status) {
        Map<String, Object> tempMap = new HashMap<String, Object>();
        Map<String, Object> repeatMap = new HashMap<String, Object>();
        Map<Integer, String> cusIdsMap = new HashMap<Integer, String>();
        JSONArray jsonArray = new JSONArray();
//        map.put("InChargeUserIds", getCurrentInChargeUserIds());
//        map.put("InChargeDeptIds", getCurrentInChargeDeptIds());
        map.put("InChargeUserIds",deptFacadeService.getUserIdsBelongToManager(this.getUserLoginInfo().getRoles(),"customerInfo"));
        List<MapCustomerGps> crmList = mapCustomerGpsDao.initCustomerGpsPage(map);
        if (crmList.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (MapCustomerGps crm : crmList) {
                sb.append(crm.getCustomerId() + ",");
            }
            String cusIds = "";
            if (sb.length() > 0) {
                cusIds = sb.substring(0, sb.length() - 1);
            }
            cusIdsMap = lnLoanService.getRecentlyLoanStatusByCusIds(cusIds);

            for (MapCustomerGps crm : crmList) {
                tempMap.clear();
                String loanStatus = (cusIdsMap.get(crm.getCustomerId()) == null ? "" : cusIdsMap.get(crm.getCustomerId()));
                String loanStatusNew = "";
                if(loanStatus.equals("申请")||loanStatus.equals("分配")||loanStatus.equals("调查")||loanStatus.equals("审批")||loanStatus.equals("放贷")){
                    loanStatusNew="尚在贷款阶段";

                }else if(loanStatus.equals("营销")){
                    loanStatusNew="营销阶段";

                }else if(loanStatus.equals("催收")){
                    loanStatusNew="存量放款客户";

                }else if(loanStatus.equals("结清")){
                    loanStatusNew="结清客户";

                }else if(loanStatus.equals("申请拒绝")||loanStatus.equals("分配拒绝")||loanStatus.equals("调查拒绝")){
                    loanStatusNew="拒绝客户";

                }
                tempMap.put("userName", crm.getUserName() == null ? "" : crm.getUserName());
                tempMap.put("loanStatus", loanStatus);
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
                tempMap.put("loanFlat", cusIdsMap.get(crm.getCustomerId()) == null ? 1 : 2);       //取不到贷款状态设置贷款图标
                tempMap.put("size", crmList.size());
                //根据贷款状态筛选客户
                if(status.equals("")){
                    if (repeatMap.get(crm.getGpsLat() + crm.getGpsLng()) == null)
                        jsonArray.add(tempMap);
                    repeatMap.put(crm.getGpsLat() + crm.getGpsLng(), crm.getGpsLat() + crm.getGpsLng());
                }else if(loanStatusNew.equals(status)){
                    if (repeatMap.get(crm.getGpsLat() + crm.getGpsLng()) == null)
                        jsonArray.add(tempMap);
                    repeatMap.put(crm.getGpsLat() + crm.getGpsLng(), crm.getGpsLat() + crm.getGpsLng());

                }

            }
        }
        List<MapRecordGps> recordList = mapRecordGpsDao.getMapRecordGpsByCondition(map);
        repeatMap.clear();
        for (MapRecordGps recordGps : recordList) {
            tempMap.clear();
            tempMap.put("gpsLat", recordGps.getGpsLat());
            tempMap.put("gpsLng", recordGps.getGpsLng());
            tempMap.put("type", 1);
            tempMap.put("recordAddress", recordGps.getRecordAddress());
            tempMap.put("recordTime", recordGps.getRecordTime().toLocaleString());
            tempMap.put("recordName", recordGps.getRecordName());
            tempMap.put("recordUploadUser", recordGps.getRecordUploadUser());
            tempMap.put("fileSize", recordGps.getFileSize());
            tempMap.put("recordGpsId", recordGps.getRecordGpsId());
            tempMap.put("gpsId", "record," + recordGps.getRecordGpsId());
            tempMap.put("size", recordList.size());
            if (repeatMap.get(recordGps.getGpsLat() + recordGps.getGpsLng()) == null)
                jsonArray.add(tempMap);
            repeatMap.put(recordGps.getGpsLat() + recordGps.getGpsLng(), recordGps.getGpsLat() + recordGps.getGpsLng());
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
     * 查询客户录音的具体信息
     *
     * @param id
     * @return
     */
    public JSONArray getCustomerOrRecprdInfo(String id) {
        Map<String, Object> tempMap = new HashMap<String, Object>();
        JSONArray jsonArray = new JSONArray();
        String[] arr = id.split(",");
        if (!arr[0].equals("record")) {
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
            tempMap.put("isNogood",mapCustomerGps.getIsNogood());
            tempMap.put("customerId", mapCustomerGps.getCustomerId());
            tempMap.put("customerGpsId", mapCustomerGps.getCustomerGpsId());
            tempMap.put("loanFlat", cusIdsMap.get(mapCustomerGps.getCustomerId()) == null ? 1 : 2);       //取不到贷款状态设置贷款图标
            jsonArray.add(tempMap);
        } else {
            MapRecordGps mapRecordGps = mapRecordGpsDao.getMapRecordGpsById(Integer.parseInt(arr[1]));
            tempMap.put("recordAddress", mapRecordGps.getRecordAddress());
            tempMap.put("recordTime", mapRecordGps.getRecordTime().toLocaleString());
            tempMap.put("recordName", mapRecordGps.getRecordName());
            tempMap.put("recordUploadUser", mapRecordGps.getRecordUploadUser());
            tempMap.put("fileSize", mapRecordGps.getFileSize());
            tempMap.put("gpsLat", mapRecordGps.getGpsLat());
            tempMap.put("gpsLng", mapRecordGps.getGpsLng());
            tempMap.put("recordGpsId", mapRecordGps.getRecordGpsId());
            jsonArray.add(tempMap);
        }
        return jsonArray;
    }

    /**
     * 查询客户和录音的Json数据
     *
     * @param map
     * @return
     */
    public JSONArray getRecordGpsByLngLat(Map<String, Object> map) {
        Map<String, Object> tempMap = new HashMap<String, Object>();
//        map.put("InChargeUserIds", getCurrentInChargeUserIds());
//        map.put("InChargeDeptIds", getCurrentInChargeDeptIds());
        map.put("InChargeUserIds",deptFacadeService.getUserIdsBelongToManager(this.getUserLoginInfo().getRoles(),"customerInfo"));
        JSONArray jsonArray = new JSONArray();
        if (!map.get("type").equals("record")) {
            List<MapCustomerGps> mapCustomerGpsList = mapCustomerGpsDao.getCustomerGpsByAddress(map);
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
                tempMap.put("type", 2);
                tempMap.put("address", mapCustomerGps.getAddress());
                tempMap.put("customerId", mapCustomerGps.getCustomerId());
                tempMap.put("isNogood",mapCustomerGps.getIsNogood());
                tempMap.put("customerGpsId", mapCustomerGps.getCustomerGpsId());
                jsonArray.add(tempMap);
            }
        } else {
            List<MapRecordGps> recordList = mapRecordGpsDao.getMapRecordGpsByCondition(map);
            for (MapRecordGps mapRecordGps : recordList) {
                tempMap.clear();
                tempMap.put("recordAddress", mapRecordGps.getRecordAddress());
                tempMap.put("recordTime", mapRecordGps.getRecordTime().toLocaleString());
                tempMap.put("recordName", mapRecordGps.getRecordName());
                tempMap.put("recordUploadUser", mapRecordGps.getRecordUploadUser());
                tempMap.put("fileSize", mapRecordGps.getFileSize());
                tempMap.put("type", 1);
                tempMap.put("recordGpsId", mapRecordGps.getRecordGpsId());
                jsonArray.add(tempMap);
            }
        }
        return jsonArray;
    }

    /**
     * 按时间查询Pad活动轨迹
     * @param map
     * @return
     */
    public JSONArray getActivityLocusJson(Map<String, Object> map) {
        Map<String, Object> tempMap = new HashMap<String, Object>();
        JSONArray jsonArray = new JSONArray();
        List<MapUserGpsLog> userGpsList = mapUserGpsLogDao.getUserGpsLogByCondition(map);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (MapUserGpsLog bean : userGpsList) {
            tempMap.clear();
            tempMap.put("gpsLat", bean.getGpsLat());
            tempMap.put("gpsLng", bean.getGpsLng());
            tempMap.put("update",formatter.format(bean.getUpdateDate()));
            tempMap.put("userGpsLogId", bean.getUserGpsLogId());
            jsonArray.add(tempMap);
        }
        return jsonArray;
    }

    /**
     * 查询录音数据集合
     *
     * @param map
     * @param page
     * @return
     */
    public PageUtil<MapRecordGps> getMapRecordGpsList(Map<String, Object> map, Page page) {
        map.put("InChargeUserIds",getCurrentInChargeUserIds());
        return mapRecordGpsDao.getMapRecordGpsList(map, page);
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
