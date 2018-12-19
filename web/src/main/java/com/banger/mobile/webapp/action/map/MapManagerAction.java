/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :地图定位管理...
 * Author     :yangy
 * Create Date:2013-3-11
 */
package com.banger.mobile.webapp.action.map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.map.MapCustomerGps;
import com.banger.mobile.domain.model.map.MapUserGps;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.loan.LnLoanService;
import com.banger.mobile.facade.log.OpeventLoginLogService;
import com.banger.mobile.facade.map.MapCustomerGpsService;
import com.banger.mobile.facade.map.MapUserGpsService;
import com.banger.mobile.facade.microTask.TskMicroTaskProgressService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;
import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: yangy
 * Date: 13-3-14
 * Time: 下午4:41
 */
public class MapManagerAction extends BaseAction {

    private static final long serialVersionUID = 2034018860194085757L;
    private CrmCustomerService crmCustomerService;                                 //客户Service
    private MapUserGpsService mapUserGpsService;                                   //用户位置GPS Service
    private OpeventLoginLogService opeventLoginLogService;                         //操作日志service
    private MapCustomerGpsService mapCustomerGpsService;                         //客户GPS Service
    private SysUserService sysUserService;
    private PageUtil<MapUserGps> userGpsList;            //用户位置实体集合
    private PageUtil<MapCustomerGps> customerGpsList;        //客户位置实体集合
    private TskMicroTaskProgressService tskMicroTaskProgressService;
    private DeptFacadeService deptFacadeService;
    private LnLoanService lnLoanService;                     //贷款业务类
    public DeptFacadeService getDeptFacadeService() {
        return deptFacadeService;
    }


    public LnLoanService getLnLoanService() {
        return lnLoanService;
    }

    public void setLnLoanService(LnLoanService lnLoanService) {
        this.lnLoanService = lnLoanService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    public TskMicroTaskProgressService getTskMicroTaskProgressService() {
        return tskMicroTaskProgressService;
    }

    public void setTskMicroTaskProgressService(TskMicroTaskProgressService tskMicroTaskProgressService) {
        this.tskMicroTaskProgressService = tskMicroTaskProgressService;
    }

    public MapCustomerGpsService getMapCustomerGpsService() {
        return mapCustomerGpsService;
    }

    public void setMapCustomerGpsService(MapCustomerGpsService mapCustomerGpsService) {
        this.mapCustomerGpsService = mapCustomerGpsService;
    }

    public PageUtil<MapCustomerGps> getCustomerGpsList() {
        return customerGpsList;
    }

    public void setCustomerGpsList(PageUtil<MapCustomerGps> customerGpsList) {
        this.customerGpsList = customerGpsList;
    }

    public PageUtil<MapUserGps> getUserGpsList() {
        return userGpsList;
    }

    public void setUserGpsList(PageUtil<MapUserGps> userGpsList) {
        this.userGpsList = userGpsList;
    }

    public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
        this.crmCustomerService = crmCustomerService;
    }

    public void setOpeventLoginLogService(OpeventLoginLogService opeventLoginLogService) {
        this.opeventLoginLogService = opeventLoginLogService;
    }

    public SysUserService getSysUserService() {
        return sysUserService;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }


    public MapUserGpsService getMapUserGpsService() {
        return mapUserGpsService;
    }

    public CrmCustomerService getCrmCustomerService() {
        return crmCustomerService;
    }

    public OpeventLoginLogService getOpeventLoginLogService() {
        return opeventLoginLogService;
    }

    public void setMapUserGpsService(MapUserGpsService mapUserGpsService) {
        this.mapUserGpsService = mapUserGpsService;
    }

    /**
     * 初始化地图定位
     *
     * @return
     */
    public String toLocationMapPage() {
        initParameter();
        request.setAttribute("proxy", 0);//为控制拔号问题0为非代理，1为代理模式
        return SUCCESS;
    }


    public void initParameter(){
        //String cityCoding = mapCustomerGpsService.getCityCoding();
        String cityLngLat=mapCustomerGpsService.getCityLngLat();
        String mapIp=mapCustomerGpsService.getMapIp();
        //String mapKey=mapCustomerGpsService.getMapKey();
        //String mapVersion=mapCustomerGpsService.getMapVersion();
        //request.setAttribute("cityCoding", cityCoding);
        request.setAttribute("mapIp", mapIp);
        //request.setAttribute("mapKey", mapKey);
        //request.setAttribute("mapVersion", mapVersion);
        request.setAttribute("cityLngLat", cityLngLat);
        String[] roles = this.getLoginInfo().getRoles();
        request.setAttribute("roles",roles[0]);
        //String roleStr="";
        //if(deptFacadeService.isCommon())
           // roleStr="common";
       // if(deptFacadeService.isInChargeOfDepartment())
        String roleStr="inChargeOfDepartment";
        request.setAttribute("roleStr",roleStr);
        Map<String,Object> map = new HashMap<String, Object>();
        if(roles[0].equals("7")){
            map.put("userId",this.getLoginInfo().getUserId());
        }
        if(roles[0].equals("3")){//获取所有用户客户数量
            Integer customerCount = mapCustomerGpsService.getMapCustomerGpsListCountAll(map);
            Integer userCount = mapUserGpsService.getUserListCountAll(map);
            request.setAttribute("customerCount",customerCount);
            request.setAttribute("userCount",userCount);
        }else{
            Integer customerCount = mapCustomerGpsService.getMapCustomerGpsListCount(map);
            log.info("客户数量为："+customerCount);
            request.setAttribute("customerCount",customerCount);

            Map<String,Object>  useMap = new HashMap<String, Object>();

            Integer userCount = mapUserGpsService.getUserListCount(map);
            request.setAttribute("userCount",userCount);
        }


    }

    /**
     * OCX代理中转页面
     * @return
     */
    public String toLocationMap() {
    	initParameter();
        return "notproxy";
        //String proxyIp = mapCustomerGpsService.getProxyIp();
        //boolean proxyFlag=mapCustomerGpsService.isProxyFlag();
        //request.setAttribute("proxyIp", proxyIp);
        //request.setAttribute("proxyFlag", proxyFlag);
        		/*
        request.setAttribute("loginAccount",getLoginInfo().getAccount());
        String customerId = request.getParameter("customerId");
        if (customerId != null){
            BaseCrmCustomer crm= crmCustomerService.getCrmCustomerById(Integer.parseInt(customerId));
            Map<String,Object> queryMap=new HashMap<String, Object>();
            queryMap.put("customerId",customerId);
            List<MapCustomerGps> list=mapCustomerGpsService.getCustomerGpsByAddress(queryMap);
            String roleStr="";
            if(deptFacadeService.isCommon())
                roleStr="common";
            if(deptFacadeService.isInChargeOfDepartment())
                roleStr="inChargeOfDepartment";
            request.setAttribute("roleStr",roleStr);
            request.setAttribute("customerId",customerId);
            request.setAttribute("custName",crm.getCustomerName());
            request.setAttribute("custAddress",crm.getAddress());
            request.setAttribute("gpsFlat",list.size()>0?list.get(0).getCustomerGpsId():0);//此用户是否有定位过

            request.setAttribute("proxy", 0);//为控制拔号问题0为非代理，1为代理模式
            return "notproxy";
        }
        initParameter();
        return SUCCESS;
        */
        /*
        if (!proxyFlag){//如果是非代理
            initParameter();
            request.setAttribute("proxy", 0);//为控制拔号问题0为非代理，1为代理模式
            return "notproxy";
        }else{
            request.setAttribute("proxy", 1);
            return SUCCESS;
        }
        */
    }





    /**
     * 所有贷款到跳转到地图定位
     * @return
     */
    public String loanToLocationMapPage() {
        String customerId=request.getParameter("customerId");
        BaseCrmCustomer crm= crmCustomerService.getCrmCustomerById(Integer.parseInt(customerId));
        Map<String,Object> queryMap=new HashMap<String, Object>();
        queryMap.put("customerId",customerId);
        List<MapCustomerGps> list=mapCustomerGpsService.getCustomerGpsByAddress(queryMap);
        request.setAttribute("customerId",customerId);
        request.setAttribute("custName",crm.getCustomerName());
        request.setAttribute("custAddress",crm.getAddress());
        request.setAttribute("gpsFlat",list.size()>0?list.get(0).getCustomerGpsId():0);//此用户是否有定位过
        initParameter();
        return SUCCESS;
    }

    /**
     * 查询用户客户GPS位置，
     *
     * @return
     */
    public String getCustomerOrUserGps() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            String customerName = request.getParameter("customerName");
            String userName = request.getParameter("userName");
            String customerId = request.getParameter("customerId");
            String gpsId = request.getParameter("gpsId");
            Map<String, Object> map = new HashMap<String, Object>();
            //String bounds = request.getParameter("bounds");
            /**if (bounds != null&&!bounds.equals("")) {
                map.put("beginGpsLng", bounds.split(";")[0].split(",")[0]);
                map.put("beginGpsLat", bounds.split(";")[0].split(",")[1]);
                map.put("endGpsLng", bounds.split(";")[1].split(",")[0]);
                map.put("endGpsLat", bounds.split(";")[1].split(",")[1]);
            }*/
            //map.put("userName", userName);
            //map.put("customerId", customerId);
            //map.put("customerName", customerName);
            JSONArray jsonArray = mapUserGpsService.getCustomerOrUserInfo(gpsId); //查询用户客户GPS
            out.println(jsonArray);
            out.write("");
            return null;
        } catch (Exception e) {
            log.error("getCustomerOrUserGps action error:" + e.getMessage());
            return null;
        }
    }

    /**
     * 查询相关客户GPS位置
     * @return
     */
    public String getCustomerGpsByCondition(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try{
            PrintWriter out = response.getWriter();
            JSONArray jsonArray = new JSONArray();
            Map<String,Object> map = new HashMap<String, Object>();
            String[] roles = this.getLoginInfo().getRoles();
            if(roles[0].equals("7")){
                map.put("userId",this.getLoginInfo().getUserId());
            }
            List<MapCustomerGps> listCus = mapCustomerGpsService.getCustomerGpsByCondition(map);
            Map<String,Object> tempMap = new HashMap<String, Object>();
            if(listCus.size()>0){
                for(MapCustomerGps mapCustomerGps:listCus){
                    Map<Integer, String> cusIdsMap = lnLoanService.getRecentlyLoanStatusByCusIds("" + mapCustomerGps.getCustomerId());
                    String loanStatus = (cusIdsMap.get(mapCustomerGps.getCustomerId()) == null ? "" : cusIdsMap.get(mapCustomerGps.getCustomerId()));
                    log.error("贷款状态是"+loanStatus);
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
                }
                out.println(jsonArray);
                out.write("");
            }
            return null;
        }catch (Exception e){
            return null;
        }

    }

    /**
     * 查询相关用户GPS位置
     * @return
     */
    public String getUserGpsByCondition(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try{
            PrintWriter out = response.getWriter();
            JSONArray jsonArray = new JSONArray();
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("InChargeUserIds",this.getLoginInfo().getUserId());
            List<MapUserGps> listUser = mapUserGpsService.getUserGpsByCondition(map);
            Map<String,Object> tempMap = new HashMap<String, Object>();
            if(listUser.size()>0){
                for(MapUserGps mapUserGps:listUser){
                    String workPlan = tskMicroTaskProgressService.getTaskScheduleByUserId(mapUserGps.getUserId());
                    SysUser sysUser = sysUserService.getSysUserById(this.getLoginInfo().getUserId());
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
                out.println(jsonArray);
                out.write("");
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }


    /**
     * 获取标记信息
     *
     * @return
     */
    public String getMarkerInfo() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            String gpsId = request.getParameter("gpsId");
            JSONArray jsonArray = mapUserGpsService.getCustomerOrUserInfo(gpsId);
            out.println(jsonArray);
            out.write("");
            return null;
        } catch (Exception e) {
            log.error("getMarkerInfo action error:" + e.getMessage());
            return null;
        }
    }

    /**
     * 查询客户列表
     *
     * @return
     */
    public String getCustomerList() {
        Map<String, Object> map = new HashMap<String, Object>();
        String customerName = request.getParameter("customerName");
        String customerId = request.getParameter("customerId");
        String LngLat = request.getParameter("lnglat");
        if (customerName != null){
            map.put("customerName", StringUtil.ReplaceSQLChar(customerName.trim()));
        }
        map.put("customerId", customerId);
        if (LngLat != null&&!LngLat.equals("")) {
            map.put("gpsId", LngLat.split(",")[1]);
        }
        String[] roles = this.getLoginInfo().getRoles();
        if(roles[0].equals("7")){
            map.put("userId",this.getLoginInfo().getUserId());
        }
       // String bounds = request.getParameter("bounds");
       // if (bounds != null&&!bounds.equals("")) {
           // map.put("beginGpsLng", bounds.split(";")[0].split(",")[0]);
           // map.put("beginGpsLat", bounds.split(";")[0].split(",")[1]);

           // map.put("endGpsLng", bounds.split(";")[1].split(",")[0]);
           // map.put("endGpsLat", bounds.split(";")[1].split(",")[1]);
       // }
        Page pag=this.getPage();
        pag.setPageSize(5);
        if(roles[0].equals("3")){
            customerGpsList = mapCustomerGpsService.getMapCustomerGpsByLngLatAll(map,pag);//业务主管查所有客户
        }else{
            customerGpsList = mapCustomerGpsService.getMapCustomerGpsByLngLat(map, pag);
        }

        for (MapCustomerGps mapCustomerGps : customerGpsList.getItems()) {
            if(mapCustomerGps.getCustomerGpsId()==null&&mapCustomerGps.getAddress()!=null){
                String str=mapCustomerGps.getAddress().replace("\\","\\\\\\\\");
                mapCustomerGps.setAddress(str);
            }
        }
        return SUCCESS;
    }



    /**
     * 查询用户列表
     * @return
     */
    public String getUserList() {
        Map<String, Object> map = new HashMap<String, Object>();
        String userName = request.getParameter("userName");
        String address = request.getParameter("address");
        String LngLat = request.getParameter("lnglat");
        String[] roles = this.getLoginInfo().getRoles();
        if (userName != null){
            map.put("userName", StringUtil.ReplaceSQLChar(userName.trim()));
        }
        if (address != null){
            map.put("address", StringUtil.ReplaceSQLChar(address.trim()));
        }
        if (LngLat != null&&!LngLat.equals("")) {
            map.put("gpsId", LngLat.split(",")[1]);
        }
       // String bounds = request.getParameter("bounds");
        //if (bounds != null&&!bounds.equals("")) {
         //   map.put("beginGpsLng", bounds.split(";")[0].split(",")[0]);
         //   map.put("beginGpsLat", bounds.split(";")[0].split(",")[1]);

        //    map.put("endGpsLng", bounds.split(";")[1].split(",")[0]);
          //  map.put("endGpsLat", bounds.split(";")[1].split(",")[1]);
       // }
        Page pag=this.getPage();
        pag.setPageSize(5);
        if(roles[0].equals("7")){
            map.put("userId",this.getLoginInfo().getUserId());//客户经理只查自己
        }

        if(roles[0].equals("3")){
            userGpsList = mapUserGpsService.getUserGpsByLngLatAll(map,pag);//业务主管查所有用户
        }else{
            userGpsList = mapUserGpsService.getUserGpsByLngLat(map, pag);
        }

        //request.setAttribute("userCount",pag.getTotalRowsAmount());
        for (MapUserGps mapUserGps :userGpsList.getItems()){
            String workPlan = tskMicroTaskProgressService.getTaskScheduleByUserId(mapUserGps.getUserId());
            mapUserGps.setWorkPlan(workPlan);
        }
        return SUCCESS;
    }


    /**
     * 查询多个用户在一个点的情况
     *
     * @return
     */
    public String getMapCustomerGpsList() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            Map<String, Object> map = new HashMap<String, Object>();
            String LngLat = request.getParameter("gpsId");
            String userName = request.getParameter("userName");
            String customerName = request.getParameter("customerName");
            map.put("type", LngLat.split(",")[0]);
            map.put("gpsId", LngLat.split(",")[1]);
            map.put("userName", userName);
            map.put("customerName",customerName);
            JSONArray jsonArray = mapUserGpsService.getUserGpsByLngLat(map);
            out.println(jsonArray);
            out.write("");
            return null;
        } catch (Exception e) {
            log.error("getMapCustomerGpsList action error:" + e.getMessage());
            return null;
        }
    }

    /**
     * 新增客户坐标
     * @return
     */
    public String addMapCustomerGps(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            MapCustomerGps map=new MapCustomerGps();
            String customerId=request.getParameter("customerId");
            Map<String,Object> queryMap=new HashMap<String, Object>();
            queryMap.put("customerId",customerId);
            List<MapCustomerGps> list=mapCustomerGpsService.getCustomerGpsByAddress(queryMap);
            String lng=request.getParameter("lng");
            String lat=request.getParameter("lat");
            String address=request.getParameter("address");
            BaseCrmCustomer crmCustomer=crmCustomerService.getCrmCustomerById(Integer.parseInt(customerId));
            if (crmCustomer.getAddress() == null||crmCustomer.getAddress().equals("")){
                queryMap.put("address",address);
                crmCustomerService.updateCrmCustomerAddress(queryMap);//地址不为空则更新客户地址
            }
            map.setCustomerId(Integer.parseInt(customerId));
            map.setUpdateUser(getLoginInfo().getUserId());
            map.setCreateUser(getLoginInfo().getUserId());
            map.setRemark("");
            map.setGpsLat(lat);
            map.setGpsLng(lng);
            if (list.size() > 0){ //查询到客户
                map.setCustomerGpsId(list.get(0).getCustomerGpsId());
                mapCustomerGpsService.updateMapCustomerGps(map);
            }else
                mapCustomerGpsService.addMapCustomerGps(map);

            out.write(""+map.getCustomerGpsId());
            return null;
        } catch (Exception e) {
            log.error("addMapCustomerGps action error:" + e.getMessage());
            return null;
        }
    }

    /**
     *地图定位页面初始化
     * @return
     */
    public  String initCustomerOrUserGps(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            Map<String, Object> map = new HashMap<String, Object>();
            String LngLat = request.getParameter("bounds");
            String customerName = request.getParameter("customerName");
            String userName = request.getParameter("userName");

            map.put("beginGpsLng", LngLat.split(";")[0].split(",")[0]);
            map.put("beginGpsLat", LngLat.split(";")[0].split(",")[1]);

            map.put("endGpsLng", LngLat.split(";")[1].split(",")[0]);
            map.put("endGpsLat", LngLat.split(";")[1].split(",")[1]);
            map.put("userName", userName);
            map.put("customerName", customerName);
            map.put("userId",getLoginInfo().getUserId());
            JSONArray jsonArray = mapUserGpsService.initCustomerOrUserGps(map);
            out.println(jsonArray);
            out.write("");
            return null;
        } catch (Exception e) {
            log.error("initCustomerOrUserGps action error:" + e.getMessage());
            return null;
        }
    }

}

