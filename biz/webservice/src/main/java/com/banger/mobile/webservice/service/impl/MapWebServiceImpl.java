package com.banger.mobile.webservice.service.impl;

import com.banger.mobile.domain.model.map.MapCustomerGps;
import com.banger.mobile.domain.model.microTask.TskSchedule;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.loan.LnLoanService;
import com.banger.mobile.facade.map.MapCustomerGpsService;
import com.banger.mobile.facade.map.MapUserGpsLogService;
import com.banger.mobile.facade.microTask.TskScheduleService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.facade.webservice.MapWebService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.JsonDateValueProcessor;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import javax.jws.WebService;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-4-9
 * Time: 上午11:31
 * To change this template use File | Settings | File Templates.
 */
@WebService(serviceName = "BangerCrmMapService", endpointInterface = "com.banger.mobile.facade.webservice.MapWebService")
public class MapWebServiceImpl implements MapWebService {

    private MapCustomerGpsService mapCustomerGpsService;                         //客户GPS Service
    private DeptFacadeService deptFacadeService;                    //机构 Service
    private LnLoanService lnLoanService;
    private SysUserService sysUserService;  //系统用户service
    private static final Logger logger = Logger.getLogger(MapWebServiceImpl.class);
    private TskScheduleService tskScheduleService;
    private MapUserGpsLogService mapUserGpsLogService;

    public MapUserGpsLogService getMapUserGpsLogService() {
        return mapUserGpsLogService;
    }

    public void setMapUserGpsLogService(MapUserGpsLogService mapUserGpsLogService) {
        this.mapUserGpsLogService = mapUserGpsLogService;
    }

    public TskScheduleService getTskScheduleService() {
        return tskScheduleService;
    }

    public void setTskScheduleService(TskScheduleService tskScheduleService) {
        this.tskScheduleService = tskScheduleService;
    }

    public MapCustomerGpsService getMapCustomerGpsService() {
        return mapCustomerGpsService;
    }

    public SysUserService getSysUserService() {
        return sysUserService;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public DeptFacadeService getDeptFacadeService() {
        return deptFacadeService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }
    public void setMapCustomerGpsService(MapCustomerGpsService mapCustomerGpsService) {
        this.mapCustomerGpsService = mapCustomerGpsService;
    }

    public LnLoanService getLnLoanService() {
        return lnLoanService;
    }

    public void setLnLoanService(LnLoanService lnLoanService) {
        this.lnLoanService = lnLoanService;
    }

    /**
     * PAD地图标注添加客户
     *
     * @param account
     * @param customerJson
     * @return
     */
    public String addCustomerLngLat(String account, String customerJson) {
        try {
            logger.info("pad端地图接口addCustomerLngLat开始,account:"+account+",customerJson:"+customerJson);
            JSONObject jsonObject = JSONObject.fromObject(customerJson);
            MapCustomerGps bean = (MapCustomerGps) JSONObject.toBean(jsonObject, MapCustomerGps.class);
            Integer userId=sysUserService.getUserByAccount(account).getUserId();
            MapCustomerGps oldBean = mapCustomerGpsService.getCustomerGpsByCustomerId(bean.getCustomerId());
            if(oldBean==null){
            	bean.setRemark("");
            	bean.setCreateUser(userId);
            	bean.setCreateDate(Calendar.getInstance().getTime());
            	bean.setUpdateUser(userId);
            	bean.setUpdateDate(Calendar.getInstance().getTime());
            	mapCustomerGpsService.addMapCustomerGps(bean);            	
            }else{
            	oldBean.setGpsLat(bean.getGpsLat());
            	oldBean.setGpsLng(bean.getGpsLng());
            	oldBean.setUpdateUser(userId);
            	oldBean.setUpdateDate(Calendar.getInstance().getTime());
            	mapCustomerGpsService.updateMapCustomerGps(oldBean);
            }
            logger.info("pad端地图接口addCustomerLngLat完成,account:"+account+",customerJson:"+customerJson);
            return "1";
        } catch (Exception e) {
            logger.error("MapWebServiceImpl % addCustomerLngLat", e);
            return "0";
        }
    }

    /**
     * PAD地图我的客户/搜索客户
     *
     * @param account
     * @param condition
     * @return
     */
    public String getMyMapCustomer(String account, String condition) {
        try {
            logger.info("pad端地图接口getMyMapCustomer开始,account:"+account+",condition:"+condition);
            SysUser u=sysUserService.getUserByAccount(account);
            if(u==null){
                return null;
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("customerName", condition);
            map.put("InChargeUserIds", u.getUserId());
           // map.put("InChargeDeptIds",getCurrentInChargeDeptIds(u.getUserId()));
            List<MapCustomerGps> list = mapCustomerGpsService.getPadCustomerGpsByCondition(map);

            //给MapCustomerGps对象添加一些日程相关的信息
            setCusListForPad(list);

            StringBuilder sb = new StringBuilder();
            for (MapCustomerGps crm : list) {
                sb.append(crm.getCustomerId() + ",");
            }
            String cusIds = "";
            if (sb.length() > 0) {
                cusIds = sb.substring(0, sb.length() - 1);
            }
            Map<Integer, String> cusIdsMap = new HashMap<Integer, String>(); //存储客户的对应贷款状态
            cusIdsMap = lnLoanService.getRecentlyLoanStatusByCusIds(cusIds);
            Map<String, Object> tempMap = new HashMap<String, Object>();
            JSONArray jsonArray = new JSONArray();
            for (MapCustomerGps crm : list) {
                tempMap.clear();
                tempMap.put("gpsLat", crm.getGpsLat());
                tempMap.put("gpsLng", crm.getGpsLng());
                tempMap.put("address", crm.getAddress());
                tempMap.put("phone", crm.getPhoneNo() == null ? "" : crm.getPhoneNo());
                tempMap.put("customerName", crm.getCustomerName() == null ? "" : crm.getCustomerName());
                tempMap.put("customerTitle", crm.getCustomerTitle() == null ? "" : crm.getCustomerTitle());
                tempMap.put("customerId", crm.getCustomerId());
                tempMap.put("userName", crm.getUserName() == null ? "" : crm.getUserName());
                tempMap.put("loanStatus", cusIdsMap.get(crm.getCustomerId()));
                tempMap.put("isNoGood", crm.getIsNogood());
                //追加的日程信息
                tempMap.put("scheduleInfo",crm.getScheduleInfo());
                tempMap.put("scheduleStatus",crm.getScheduleStatus());
                jsonArray.add(tempMap);
            }
            logger.info("pad端地图接口getMyMapCustomer完成,account:"+account+",condition:"+condition);
            return jsonArray.toString();
        } catch (Exception e) {
            logger.error("MapWebServiceImpl % getMyMapCustomer", e);
            return "false";
        }
    }

    private void setCusListForPad(List<MapCustomerGps> list) {
        //追加日程信息
        String cusIds = "";
        if (list.size() > 0){
            for (MapCustomerGps cus : list){
                if (cusIds.equals("")){
                    cusIds = cus.getCustomerId().toString();
                }else {
                    cusIds = cusIds + "," + cus.getCustomerId().toString();
                }
            }
        }

        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("customerIds",cusIds);
        paramMap.put("isMap","true");
        List<TskSchedule> scheduleList = tskScheduleService.getScheduleByCusIds(paramMap);
        Map<String, TskSchedule> scheduleMap = new HashMap<String, TskSchedule>();
        if(scheduleList!=null){
            for(TskSchedule schedule :scheduleList){
                schedule.setDiffTime(schedule.getContactDate().getTime() - new Date().getTime());
            }

            for (TskSchedule schedule : scheduleList){
                String key = schedule.getCustomerId().toString();
                if (scheduleMap.containsKey(key)) {
                    TskSchedule oldSchedule = scheduleMap.get(key);
                    if (oldSchedule.getDiffTime() < 0){
                        oldSchedule.setDiffTime(-oldSchedule.getDiffTime());
                    }
                    if (schedule.getDiffTime() < 0){
                        schedule.setDiffTime(-schedule.getDiffTime());
                    }
                    Long oldDiffTime = oldSchedule.getDiffTime();
                    Long curDiffTime = schedule.getDiffTime();
                    Long diffTime = oldDiffTime - curDiffTime;
                    if (diffTime < 0){
                        scheduleMap.put(key,oldSchedule);
                    }else {
                        scheduleMap.put(key,schedule);
                    }
                }else {
                    scheduleMap.put(key,schedule);
                }
            }
        }

        String cid = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (list.size() > 0) {
            for (MapCustomerGps cus : list) {
                cid = cus.getCustomerId().toString();
                if(scheduleMap.containsKey(cid)){
                    String dataStr = scheduleMap.get(cid).getContactDate() == null ? "" : sdf.format(scheduleMap.get(cid).getContactDate());
                    String visitType = scheduleMap.get(cid).getContactTypeName() == null ?"": scheduleMap.get(cid).getContactTypeName();
                    cus.setScheduleInfo(dataStr + "  " + visitType);
                    cus.setScheduleStatus(scheduleMap.get(cid).getStatus());
                }
            }
        }
    }

    /**
     * 当前用户有权限的      部门ids
     *
     * @return
     */
    private String getCurrentInChargeDeptIds(Integer userId) {
        String deptIds = "";
        Integer[] arr = deptFacadeService.getInChargeOfDeptIds(userId);
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                if (deptIds.equals(""))
                    deptIds = arr[i].toString();
                else
                    deptIds = deptIds + "," + arr[i];
            }
        }
        return deptIds;
    }


    /**
     * PAD根据客户id获取经纬度
     * @param customerId
     * @return
     */
    public String getCustomerLngLat(Integer customerId) {

        //Map<String, Object> map = new HashMap<String, Object>();
        //map.put("customerId", customerId);
        //MapCustomerGps bean = mapCustomerGpsService.getCustomerGpsByConditionForPad(map).get(0);
        MapCustomerGps bean = mapCustomerGpsService.getCustomerGpsByCustomerId(customerId);
        String str="";
        if(bean!=null){
        	str="{\"gpsLng\":"+bean.getGpsLng()+",\"gpsLat\":"+bean.getGpsLat()+"}";
        }
        else{
        	str="0";        	
        }        
        logger.info("pad端地图接口getCustomerLngLat完成,customerId:"+customerId);
        return str;
    }

    @Override
    public String getUserLocationList(String account, String user) {

        try{
            logger.info("pad端地图接口getUserLocationList开始,account:"+account);

            SysUser sysUser = sysUserService.getUserByAccount(account.trim()); // 得到系统用户信息

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("user", user);
            map.put("roleId", 7);

            List<SysUser> userList = sysUserService.getAllUserByAccountAndName(map);
            if(!CollectionUtils.isEmpty(userList)){
                map.clear();
                SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
                String beginTime = sf1.format(new Date());
                String endTime = sf1.format(DateUtil.addDay(new Date(), +1));
                map.put("beginTime",beginTime);
                map.put("endTime", endTime);
                for (int i = 0; i < userList.size(); i++) {
                    map.put("userId", userList.get(i).getUserId());
                    userList.get(i).setGpsLogList(mapUserGpsLogService.getUserGpsLogList(map));
                }
            }

            JSONArray jsonArray = new JSONArray();
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
            String result = jsonArray.fromObject(userList, jsonConfig).toString();

            logger.info("pad端地图接口getUserLocationList完成,result:"+result);

            return result;

        }catch (Exception e){
            logger.error("pad端地图接口getUserLocationList异常：" + e);
            return null;
        }

    }
}
