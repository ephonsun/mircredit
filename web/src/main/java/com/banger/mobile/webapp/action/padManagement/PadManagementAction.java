/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :PAD管理Action
 * Author     :liyb
 * Create Date:2013-6-17
 */
package com.banger.mobile.webapp.action.padManagement;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.Enum.recbistype.EnumRecbizType;
import com.banger.mobile.domain.model.base.padManagement.BasePadInfoCopy;
import com.banger.mobile.domain.model.padManagement.PadBrands;
import com.banger.mobile.domain.model.padManagement.PadInfo;
import com.banger.mobile.domain.model.padManagement.PadInfoCopy;
import com.banger.mobile.domain.model.padManagement.PadUseLogBean;
import com.banger.mobile.facade.map.MapCustomerGpsService;
import com.banger.mobile.facade.padManagement.PadInfoService;
import com.banger.mobile.facade.padManagement.PadTypeService;
import com.banger.mobile.facade.padManagement.SysPadLogService;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;
import net.sf.json.JSONArray;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liyb
 * @version $Id: PadManagement.java,v 0.1 2013-6-17 上午11:48:07 liyb Exp $
 */
public class PadManagementAction extends BaseAction {

    private static final long serialVersionUID = 9125368256496899403L;
    private Map<Integer,String> activedMap=new LinkedHashMap<Integer,String>();//按照插入的顺序排列
    private Map<Integer,String> padLinkStatus=new LinkedHashMap<Integer,String>();//PAD使用状态
    private PadTypeService padTypeService;
    private PadInfoService padInfoService;
    private List<PadBrands> brandList;
    private PadInfo padInfo;
    private PadInfoCopy  padInfoCopy;
    private String flag;
    private PageUtil<PadInfo> padInfoPage;
    private List<BasePadInfoCopy> basePadInfoCopy;
    private JSONArray json=new JSONArray();
    private Integer belongUserId;
    private String oldPadCode;
    private String oldSerialNumber;
    private Integer isActived;
    
    private SysPadLogService sysPadLogService;
    private List<PadUseLogBean> padBean;
    private List<PadUseLogBean> logBean;
    private MapCustomerGpsService mapCustomerGpsService;

    public void setMapCustomerGpsService(MapCustomerGpsService mapCustomerGpsService) {
        this.mapCustomerGpsService = mapCustomerGpsService;
    }
    /**
     * 查询参数
     */
    private Integer linkStatus;//pad连接状态
    private String startDate;//最近登录开始时间
    private String endDate;//最近登录结束时间
    private String loginDate;//最近登录时间
    
    /**
     * 初始化PAD状态
     */
    public void initPadActived(){
        activedMap.put(1, EnumRecbizType.PAD_ACTIVED_ENABLED.getValue());//启用
        activedMap.put(0, EnumRecbizType.PAD_ACTIVED_DISABLED.getValue());//禁用
       // activedMap.put(3, EnumRecbizType.PAD_ACTIVED_STOP.getValue());//停用
        
        padLinkStatus.put(0, EnumRecbizType.PAD_LINK_CONNECTED.getValue());//已连接
        padLinkStatus.put(1, EnumRecbizType.PAD_LINK_NOT_CONNECTED.getValue());//未连接
    }
    /**
     * 初始化PAD品牌
     */
    public void initPadType(){
        Map<String,Object> map=new HashMap<String,Object>();
        if(isActived!=null){
            map.put("isActived", isActived);
        }
        brandList=padTypeService.getPadBrandsList(map);
    }
    
    public void getPadDeptUserTree(){
        try {
            PrintWriter out = this.getResponse().getWriter();
            json=padInfoService.getPadDeptUserTree(flag, belongUserId);
            out.print(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * PAD管理列表
     * @return
     */
    public String padManagementList(){
        try {
            initPadActived();
//            initPadType();
            padInfoQuery();
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("padManagementList action error:"+e);
            return ERROR;
        }
    }
    
    /**
     * PAD列表查询
     * @return
     */
    public String padInfoQuery(){
        try {
            Map<String,Object> map=new HashMap<String, Object>();

            if(padInfoCopy!=null){
                if(!StringUtil.isBlank(padInfoCopy.getPadCode())){
                    map.put("padCode", padInfoCopy.getPadCode());
                }
                if(!StringUtil.isBlank(padInfoCopy.getSerialNumber())){
                    map.put("serialNumber", padInfoCopy.getSerialNumber());
                }
                if(!StringUtil.isBlank(padInfoCopy.getBrandType())){
                    map.put("brandType", padInfoCopy.getBrandType());
                }
                if(!StringUtil.isBlank(padInfoCopy.getBrandSubType())){
                    map.put("brandSubType", padInfoCopy.getBrandSubType());
                }
                if(padInfoCopy.getUserId()!=null){
                    map.put("userId", padInfoCopy.getUserId());
                }
                if(padInfoCopy.getStatus()!=null){
                    map.put("status", padInfoCopy.getStatus());
                }
            }
            if(linkStatus!=null){
                if(linkStatus==0){
                    map.put("logIn", 0);
                }else if(linkStatus==1){
                    map.put("logOut", 1);
                }
            }
            if(!StringUtil.isBlank(startDate)){
                map.put("startDate", startDate);
            }
            if(!StringUtil.isBlank(endDate)){
                map.put("endDate", endDate);
            }
           // basePadInfoCopy=padInfoService.queryAllInfoPage(map);
           //List<BasePadInfoCopy> list= (List<BasePadInfoCopy>) padInfoService.getPadInfoPage(map, this.getPage());
           //List<BasePadInfoCopy> list=padInfoService.queryAllInfoPage(map);
             padInfoPage=padInfoService.getPadInfoPage(map, this.getPage());
          // padInfoPage=(PageUtil) padInfoService.queryAllInfoPage(map);
            //basePadInfoCopy= list;

            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("padInfoQuery action error:"+e);
            return ERROR;
        }
    }
    
    /**
     * 添加pad信息
     * @return
     */
    public String addPadInfo(){
        try {
            initPadType();
            if(padInfo==null){
                return "toAddPadInfo";
            }else{
                PrintWriter out = this.getResponse().getWriter();
                padInfo.setCreateUser(this.getLoginInfo().getUserId());
                PadInfo info=new PadInfo();
                info=padInfoService.getPadInfoByPadCode(padInfo);
                if(info!=null){
                    out.print("codeExists");//编号已存在
                }else{
                    info=padInfoService.getPadInfoBySerialNumber(padInfo);
                    if(info!=null){
                        out.print("numExists");//序列号已存在
                    }else{
                        padInfoService.insertPadInfo(padInfo);
                        if(flag.equals("save")){
                            out.print("save");
                        }else out.print("toAdd");
                    }
                }
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("addPadInfo action error:"+e);
            return ERROR;
        }
    }
    
    /**
     * 编辑pad信息
     */
    public void updatePadInfo(){
        try {
            int m=0;
            PrintWriter out = this.getResponse().getWriter();
            padInfo.setUpdateUser(this.getLoginInfo().getUserId());
            PadInfo info=new PadInfo();
            if(!oldPadCode.equals(padInfo.getPadCode())){
                info=padInfoService.getPadInfoByPadCode(padInfo);
                if(info!=null){
                    out.print("codeExists");//编号已存在
                    m=1;
                }
            }
            
            if(m==0){
                if(!oldSerialNumber.equals(padInfo.getSerialNumber())){
                    info=padInfoService.getPadInfoBySerialNumber(padInfo);
                    if(info!=null){
                        out.print("numExists");//序列号已存在
                        m=1;
                    }
                }
            }
            if(m==0){
                out.print("save");
                padInfoService.updatePadInfo(padInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("updatePadInfo action error:"+e);
        }
    }
    
    /**
     * 跳转编辑pad信息页面
     * @return
     */
    public String toUpdatePadInfo(){
        try {
            initPadType();
            padInfo=padInfoService.getPadInfoById(padInfo);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("toUpdatePadInfo action error:"+e);
            return ERROR;
        }
    }
    
    /**
     * 查看pad信息
     * @return
     */
    public String viewPadInfo(){
        try {
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
            padInfo=padInfoService.getPadInfoById(padInfo);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("viewPadInfo action error:"+e);
            return ERROR;
        }
    }
    
    /**
     * PAD使用记录
     * @return
     */
    public String padUseLog(){
        try {
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("padInfoId", padInfo.getPadInfoId());
            map.put("startDate", startDate);
            map.put("endDate", endDate);
            padBean=sysPadLogService.getByPadDateLogGroup(map);
            logBean=sysPadLogService.getPadLogGroup(map);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("padUseLog action error:"+e);
            return ERROR;
        }
    }
    
    /**
     * 启用、禁用
     */
    public void changePadStatus(){
        try {
            padInfo.setUpdateUser(this.getLoginInfo().getUserId());
            padInfoService.changePadStatus(padInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 停用PAD
     */
    public void stopPad(){
        try {
            padInfo.setUpdateUser(this.getLoginInfo().getUserId());
            padInfoService.changePadStatus(padInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 删除PAD
     */
    public void deletePadInfo(){
        try {
            PrintWriter out = this.getResponse().getWriter();
            boolean bool=sysPadLogService.getPadLogCount(padInfo.getPadInfoId());
            if(bool){//存在使用记录
                out.print("1");
            }else{
                out.print("0");
                padInfo.setUpdateUser(this.getLoginInfo().getUserId());
                padInfoService.changePadStatus(padInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<BasePadInfoCopy> getBasePadInfoCopy() {
        return basePadInfoCopy;
    }

    public void setBasePadInfoCopy(List<BasePadInfoCopy> basePadInfoCopy) {
        this.basePadInfoCopy = basePadInfoCopy;
    }

    public Map<Integer, String> getActivedMap() {
        return activedMap;
    }

    public void setActivedMap(Map<Integer, String> activedMap) {
        this.activedMap = activedMap;
    }

    public Map<Integer, String> getPadLinkStatus() {
        return padLinkStatus;
    }

    public void setPadLinkStatus(Map<Integer, String> padLinkStatus) {
        this.padLinkStatus = padLinkStatus;
    }

    public List<PadBrands> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<PadBrands> brandList) {
        this.brandList = brandList;
    }

    public void setPadTypeService(PadTypeService padTypeService) {
        this.padTypeService = padTypeService;
    }
    public void setPadInfoService(PadInfoService padInfoService) {
        this.padInfoService = padInfoService;
    }
    public PadInfo getPadInfo() {
        return padInfo;
    }
    public void setPadInfo(PadInfo padInfo) {
        this.padInfo = padInfo;
    }
    public String getFlag() {
        return flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }
    public PageUtil<PadInfo> getPadInfoPage() {
        return padInfoPage;
    }
    public void setPadInfoPage(PageUtil<PadInfo> padInfoPage) {
        this.padInfoPage = padInfoPage;
    }
    public JSONArray getJson() {
        return json;
    }
    public void setJson(JSONArray json) {
        this.json = json;
    }
    public Integer getBelongUserId() {
        return belongUserId;
    }
    public void setBelongUserId(Integer belongUserId) {
        this.belongUserId = belongUserId;
    }
    public Integer getLinkStatus() {
        return linkStatus;
    }
    public void setLinkStatus(Integer linkStatus) {
        this.linkStatus = linkStatus;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getOldPadCode() {
        return oldPadCode;
    }
    public void setOldPadCode(String oldPadCode) {
        this.oldPadCode = oldPadCode;
    }
    public String getOldSerialNumber() {
        return oldSerialNumber;
    }
    public void setOldSerialNumber(String oldSerialNumber) {
        this.oldSerialNumber = oldSerialNumber;
    }
    public List<PadUseLogBean> getPadBean() {
        return padBean;
    }
    public void setPadBean(List<PadUseLogBean> padBean) {
        this.padBean = padBean;
    }
    public void setSysPadLogService(SysPadLogService sysPadLogService) {
        this.sysPadLogService = sysPadLogService;
    }
    public List<PadUseLogBean> getLogBean() {
        return logBean;
    }
    public void setLogBean(List<PadUseLogBean> logBean) {
        this.logBean = logBean;
    }
    public Integer getIsActived() {
        return isActived;
    }
    public void setIsActived(Integer isActived) {
        this.isActived = isActived;
    }


    public PadInfoCopy getPadInfoCopy() {
        return padInfoCopy;
    }

    public void setPadInfoCopy(PadInfoCopy padInfoCopy) {
        this.padInfoCopy = padInfoCopy;
    }
}
