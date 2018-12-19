/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :ad域配置action
 * Author     :xuhj
 * Version    :1.0
 * Create Date:May 3, 2013
 */
package com.banger.mobile.webapp.action.adsync;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.banger.mobile.domain.model.adsync.SyncAdNode;
import com.banger.mobile.domain.model.adsync.SyncAdPcUsersSetting;
import com.banger.mobile.domain.model.dept.CusBelongToBean;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.facade.adsync.AdSyncConfigService;
import com.banger.mobile.facade.adsync.AdUserSyncService;
import com.banger.mobile.facade.dept.DeptService;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;

public class AdsyncAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6659158062214174986L;

	private AdUserSyncService 						adUserSyncService;
	private AdSyncConfigService						adSyncConfigService;
	private SyncAdPcUsersSetting					syncAdPcUsersSetting;
	private DeptService       						deptService;  //机构service
	
	public AdsyncAction(){
		this.syncAdPcUsersSetting = new SyncAdPcUsersSetting();
	}
	
	/**
	 * 加载AD域配置界面
	 * @return
	 */
	public String showAdSyncPage(){
		try {
			syncAdPcUsersSetting = adSyncConfigService.getAdSyncConfig();
			if(syncAdPcUsersSetting!=null&&syncAdPcUsersSetting.getAdActived().equals(1)){
				List<SyncAdNode> list = adUserSyncService.getOUList(syncAdPcUsersSetting);
				if(list!=null&&list.size()>0){
					JSONArray jsonArray =listToJsonArray(list, syncAdPcUsersSetting);
					request.setAttribute("treeJsonArray", jsonArray);
					
					JSONArray deptJson = getAdminDeptJson(syncAdPcUsersSetting);
					
					request.setAttribute("deptJson", deptJson);
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	private JSONArray getAdminDeptJson(SyncAdPcUsersSetting syncAdPcUsersSetting){
		JSONArray jsonArray = new JSONArray();
		List<SysDept> depts = deptService.getAdminInCharegeDeptTreeList();
		if(depts!=null&&depts.size()>0){
			Integer deptId = syncAdPcUsersSetting.getAdSyncDeptId();
			for(SysDept dept : depts){
				Map<String, Object> map = new HashMap<String, Object>();
				if(deptId!=null && deptId>0 && deptId.equals(dept.getDeptId())){
					map.put("selected", true);
				}
				map.put("id", dept.getDeptId());
                map.put("pId", dept.getDeptParentId());
                map.put("name", dept.getDeptName());
                map.put("deptCode", dept.getDeptCode());
                if(dept.getDeptParentId().equals(2)){
                    map.put("open", true);
                }else{
                    map.put("open", false);
                }
                jsonArray.add(map);
			}
		}
		return jsonArray;
	}
	
	private JSONArray listToJsonArray(List<SyncAdNode> list, SyncAdPcUsersSetting syncAdPcUsersSetting){
		JSONArray jsonArray = new JSONArray();
		String fullName = syncAdPcUsersSetting.getAdSyncDeptNode();
		
		for(SyncAdNode node : list){
			JSONObject obj = new JSONObject();
			if(!StringUtil.isEmpty(fullName)&&fullName.equals(node.getFullName())){
				obj.put("selected", true);
			}
			obj.put("id", node.getFullName());
			obj.put("name", node.getName());
			obj.put("pId", node.getParentName());
			jsonArray.add(obj);
		}
		
		return jsonArray;
	}
	/**
	 * 连接AD域服务器 获取用户数据
	 */
	public void connectAdService(){
		HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        
		try {
			List<SyncAdNode> list = adUserSyncService.getOUList(syncAdPcUsersSetting);
			if(list.size()>0){
				JSONArray jsonArray =listToJsonArray(list, syncAdPcUsersSetting);
				
				JSONArray deptJson = getAdminDeptJson(syncAdPcUsersSetting);
				
				JSONArray array = new JSONArray();
				
				array.add(jsonArray);
				array.add(deptJson);
				
				PrintWriter out = response.getWriter();
				out.print(array);
			}
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * 手动同步
	 * @throws IOException 
	 */
	public void handSyncAdService() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();
        try {
        	String result = adUserSyncService.syncAdAllNode(syncAdPcUsersSetting);
			out.print(result);
		} catch (Exception e) {
			out.print("同步异常");
		}
	}
	
	/**
	 * 保存AD域配置
	 */
	public void saveAdSyncConfig(){
		HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
		try {
			String syncTime = request.getParameter("syncTime");
			if(syncAdPcUsersSetting.getAdSyncMode().equalsIgnoreCase("time") && !StringUtil.isEmpty(syncTime)){
				SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				Date time = formatDate.parse(syncTime);
				syncAdPcUsersSetting.setAdSyncTime(time);
			}
			adSyncConfigService.save(syncAdPcUsersSetting);
			Integer adid = syncAdPcUsersSetting.getAdId();
			PrintWriter out = response.getWriter();
			out.print(adid);
			if(syncAdPcUsersSetting.getAdSyncMode().equalsIgnoreCase("time")){
				adUserSyncService.removeSyncJob(syncAdPcUsersSetting);
				adUserSyncService.AddSyncJob(syncAdPcUsersSetting);
			}
		} catch (Exception e) {
		}
	}
	
	public void setAdUserSyncService(AdUserSyncService adUserSyncService) {
		this.adUserSyncService = adUserSyncService;
	}

	public void setAdSyncConfigService(AdSyncConfigService adSyncConfigService) {
		this.adSyncConfigService = adSyncConfigService;
	}

	public SyncAdPcUsersSetting getSyncAdPcUsersSetting() {
		return syncAdPcUsersSetting;
	}

	public void setSyncAdPcUsersSetting(SyncAdPcUsersSetting syncAdPcUsersSetting) {
		this.syncAdPcUsersSetting = syncAdPcUsersSetting;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}
	
}
