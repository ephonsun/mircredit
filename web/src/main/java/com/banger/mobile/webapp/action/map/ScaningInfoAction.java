/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :扫街信息...
 * Author     :yangy
 * Create Date:2013-3-11
 */
package com.banger.mobile.webapp.action.map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.map.MapCustomerGps;
import com.banger.mobile.domain.model.map.MapRecordGps;
import com.banger.mobile.domain.model.map.MapUserGps;
import com.banger.mobile.facade.loan.LnLoanService;
import com.banger.mobile.facade.log.OpeventLoginLogService;
import com.banger.mobile.facade.map.MapCustomerGpsService;
import com.banger.mobile.facade.map.MapRecordGpsService;
import com.banger.mobile.facade.map.MapUserGpsService;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;
import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @version $Id: ScaningInfoAction.java,v 0.1 2013-3-11 下午2:26 yangyong Exp $
 * @author: yangy
 * Date: 13-3-11
 * Time: 下午2:26
 */
public class ScaningInfoAction extends BaseAction {

    private OpeventLoginLogService opeventLoginLogService;                         //操作日志service
    private MapRecordGpsService mapRecordGpsService;                               //录音地图service
    private MapCustomerGpsService mapCustomerGpsService;                           //客户GPS Service
    private PageUtil<MapCustomerGps> customerGpsList;                              //客户位置实体集合
    private PageUtil<MapRecordGps> mapRecordGpsList;                              //录音位置实体集合
    private MapUserGpsService mapUserGpsService;
    private LnLoanService lnLoanService;                     //贷款业务类

    public LnLoanService getLnLoanService() {
        return lnLoanService;
    }

    public void setLnLoanService(LnLoanService lnLoanService) {
        this.lnLoanService = lnLoanService;
    }

    public OpeventLoginLogService getOpeventLoginLogService() {
        return opeventLoginLogService;
    }

    public MapUserGpsService getMapUserGpsService() {
        return mapUserGpsService;
    }

    public void setMapUserGpsService(MapUserGpsService mapUserGpsService) {
        this.mapUserGpsService = mapUserGpsService;
    }

    public PageUtil<MapRecordGps> getMapRecordGpsList() {
        return mapRecordGpsList;
    }

    public void setMapRecordGpsList(PageUtil<MapRecordGps> mapRecordGpsList) {
        this.mapRecordGpsList = mapRecordGpsList;
    }

    public PageUtil<MapCustomerGps> getCustomerGpsList() {
        return customerGpsList;
    }

    public void setCustomerGpsList(PageUtil<MapCustomerGps> customerGpsList) {
        this.customerGpsList = customerGpsList;
    }

    public MapCustomerGpsService getMapCustomerGpsService() {
        return mapCustomerGpsService;
    }

    public void setMapCustomerGpsService(MapCustomerGpsService mapCustomerGpsService) {
        this.mapCustomerGpsService = mapCustomerGpsService;
    }

    public MapRecordGpsService getMapRecordGpsService() {
        return mapRecordGpsService;
    }

    public void setMapRecordGpsService(MapRecordGpsService mapRecordGpsService) {
        this.mapRecordGpsService = mapRecordGpsService;
    }

    public void setOpeventLoginLogService(OpeventLoginLogService opeventLoginLogService) {
        this.opeventLoginLogService = opeventLoginLogService;
    }

    /**
     * 初始化信息
     */
    public void initParameter() {
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
    }

    /**
     * 初始化扫街信息
     *
     * @return
     */
    public String toScaningInfoPage() {
        request.setAttribute("proxy", 0);//为控制拔号问题0为非代理，1为代理模式
        initParameter();
        return SUCCESS;
    }

    /**
     * OCX代理中转页面
     * @return
     */
    public String toScaningInfo() {
    	initParameter();
    	request.setAttribute("loginAccount",getLoginInfo().getAccount());
    	request.setAttribute("proxyFlag", mapCustomerGpsService.isProxyFlag());
    	request.setAttribute("proxy", 0);//为控制拔号问题
    	return "notproxy";
    	/*
        String proxyIp = mapCustomerGpsService.getProxyIp();
        boolean proxyFlag=mapCustomerGpsService.isProxyFlag();
        request.setAttribute("proxyIp", proxyIp);
        request.setAttribute("proxyFlag", proxyFlag);
        request.setAttribute("loginAccount",getLoginInfo().getAccount());
        if (!proxyFlag){//如果是非代理
            initParameter();
            request.setAttribute("proxy", 0);//为控制拔号问题
            return "notproxy";
        }else
            return SUCCESS;
            */
    }

    /**
     * 初始化活动轨迹页面
     * @return
     */
    public String toActivityLocusPage() {
        initParameter();
        String userId =request.getParameter("userId");
        if(userId!=null)
             request.setAttribute("userId", userId);

        return SUCCESS;
    }


    /**
     * 按时间查询Pad活动轨迹
     * @return
     */
    public String queryActivityLocus(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            Map<String, Object> map = new HashMap<String, Object>();
            String beginTime = request.getParameter("beginTime");
            String endTime = request.getParameter("endTime");
            String startTime = request.getParameter("startTime");
            String userId = request.getParameter("userId");
            String gpsId = request.getParameter("gpsId");
            if (!StringUtil.isNullOrEmpty(beginTime)){
                map.put("beginTime", StringUtil.isNullOrEmpty(startTime)?beginTime+" 00:00:00" : beginTime+" "+startTime);
                map.put("endTime", StringUtil.isNullOrEmpty(endTime)?beginTime+" 23:59:59" : beginTime+" "+endTime);
            }
            if (!StringUtil.isNullOrEmpty(gpsId))
                map.put("userId", gpsId);
            JSONArray jsonArray = mapRecordGpsService.getActivityLocusJson(map);
            out.println(jsonArray);
            out.write("");
            return null;
        } catch (Exception e) {
            log.error("getMapCustomerGpsList action error:" + e.getMessage());
            return null;
        }
    }


    /**
     * 按时间查询Pad活动轨迹
     * @return
     */
    public String initActivityLocus(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            Map<String, Object> map = new HashMap<String, Object>();
            String userId = request.getParameter("userId");
            Date time = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            map.put("beginTime", sdf.format(time) + " 00:00:00");
            map.put("endTime",sdf.format(time)+" 23:59:59");
            map.put("userId", userId);
            JSONArray jsonArray = mapRecordGpsService.getActivityLocusJson(map);
            out.println(jsonArray);
            out.write("");
            return null;
        } catch (Exception e) {
            log.error("initActivityLocus action error:" + e.getMessage());
            return null;
        }
    }

    /**
     * 查询用户客户GPS位置，
     *
     * @return
     */
    public String getCustomerOrRecordGps() {
        log.info("查询用户客户GPS位置");
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            Map<String, Object> map = new HashMap<String, Object>();
            String loanStatus = request.getParameter("loanStatus");
            //String bounds = request.getParameter("bounds");
            map.put("beginGpsLng", request.getParameter("beginGpsLng") );//bounds.split(";")[0].split(",")[0]);
            map.put("beginGpsLat", request.getParameter("beginGpsLat") );//bounds.split(";")[0].split(",")[1]);

            map.put("endGpsLng", request.getParameter("endGpsLng") );//bounds.split(";")[1].split(",")[0]);
            map.put("endGpsLat", request.getParameter("endGpsLat") );//bounds.split(";")[1].split(",")[1]);
            JSONArray jsonArray = mapRecordGpsService.getCustomerOrRecordGps(map,loanStatus); //查询录音和客户GPS
            out.println(jsonArray);
            out.write("");
            return null;
        } catch (Exception e) {
            log.error("getCustomerOrRecordGps action error:" + e.getMessage());
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
            JSONArray jsonArray = mapRecordGpsService.getCustomerOrRecprdInfo(gpsId);
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
        Map<Integer, String> cusIdsMap = new HashMap<Integer, String>();
        String LngLat = request.getParameter("lnglat");
        String status = request.getParameter("loanStatus");
        if (LngLat != null && !LngLat.equals("")) {
            map.put("gpsId", LngLat.split(",")[1]);
        } else {
            //String bounds = request.getParameter("bounds");
            map.put("beginGpsLng", request.getParameter("beginGpsLng") );//bounds.split(";")[0].split(",")[0]);
            map.put("beginGpsLat", request.getParameter("beginGpsLat") );//bounds.split(";")[0].split(",")[1]);

            map.put("endGpsLng", request.getParameter("endGpsLng") );//bounds.split(";")[1].split(",")[0]);
            map.put("endGpsLat", request.getParameter("endGpsLat") );//bounds.split(";")[1].split(",")[1]);
        }
        Page pag = this.getPage();
        pag.setPageSize(5);

        PageUtil<MapCustomerGps> customerGpsListNew = mapCustomerGpsService.getScaningCustomerList(map, pag);
        List<MapCustomerGps> customerList = new ArrayList<MapCustomerGps>();
        //获取客户ID
        if (customerGpsListNew.getItems().size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (MapCustomerGps crm : customerGpsListNew.getItems()) {
                sb.append(crm.getCustomerId() + ",");
            }
            String cusIds = "";
            if (sb.length() > 0) {
                cusIds = sb.substring(0, sb.length() - 1);
            }
            cusIdsMap = lnLoanService.getRecentlyLoanStatusByCusIds(cusIds);

            for (MapCustomerGps crm : customerGpsListNew.getItems()) {
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
                //根据贷款状态筛选客户
                if(status.equals("")){
                    customerList.add(crm);
                }else if(loanStatusNew.equals(status)){
                    customerList.add(crm);
                }

            }
            request.setAttribute("customerList",customerList);
        }
        return SUCCESS;
    }

    /**
     * 查询录音列表
     *
     * @return
     */
    public String getRecordList() {
        Map<String, Object> map = new HashMap<String, Object>();
        String LngLat = request.getParameter("lnglat");
        if (LngLat != null) {
            map.put("gpsId", LngLat.split(",")[1]);
        } else {
            //String bounds = request.getParameter("bounds");

            map.put("beginGpsLng", request.getParameter("beginGpsLng") );//bounds.split(";")[0].split(",")[0]);
            map.put("beginGpsLat", request.getParameter("beginGpsLat") );//bounds.split(";")[0].split(",")[1]);
            map.put("endGpsLng", request.getParameter("endGpsLng") );//bounds.split(";")[1].split(",")[0]);
            map.put("endGpsLat", request.getParameter("endGpsLat") );//bounds.split(";")[1].split(",")[1]);
        }
        Page pag = this.getPage();
        pag.setPageSize(5);
        mapRecordGpsList = mapRecordGpsService.getMapRecordGpsList(map, pag);
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
            map.put("type", LngLat.split(",")[0]);
            map.put("gpsId", LngLat.split(",")[1]);
            JSONArray jsonArray = mapRecordGpsService.getRecordGpsByLngLat(map);
            out.println(jsonArray);
            out.write("");
            return null;
        } catch (Exception e) {
            log.error("getMapCustomerGpsList action error:" + e.getMessage());
            return null;
        }
    }
}
