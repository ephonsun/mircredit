/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :全局参数dao实现类
 * Author     :yujh
 * Create Date:May 25, 2012
 */
package com.banger.mobile.dao.param.ibatis;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.param.SysParamDao;
import com.banger.mobile.domain.model.param.SysParam;
import com.banger.mobile.domain.model.param.SysParamFlow;
import com.banger.mobile.ibatis.GenericDaoiBatis;
import com.banger.mobile.util.FileUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.util.SystemUtil;

/**
 * @author yujh
 * @version $Id: SysParamDaoiBatis.java,v 0.1 May 25, 2012 10:48:55 AM Administrator Exp $
 */
public class SysParamDaoiBatis extends GenericDaoiBatis implements SysParamDao{
    private Map<String,Object> caches;
    
    public SysParamDaoiBatis(Class persistentClass) {
        super(SysParam.class);
    }
    public SysParamDaoiBatis(){
        super(SysParam.class);
    }

    /**
     * 查询流量控制参数
     * @return
     */
    public Map<String, Object> getFlowControlParam() {
        Map<String,Object> map= new HashMap<String,Object>();
         List<SysParamFlow> list=this.getSqlMapClientTemplate().queryForList("getFlowControlParam");
         for(SysParamFlow param: list){
             map.put(param.getParamKey(), param.getParamValue());
         }
         return map;
         
         
    }

    /**
     * 设置是否启用限流
     */
    public void updateIsActive(int num) {
        this.getSqlMapClientTemplate().update("updateIsActive", num);
        this.refreshCache();
    }
    /**
     * web端是否启用限流
     * @param num
     */
    public void updateIsActiveForWeb(int num) {
        this.getSqlMapClientTemplate().update("updateIsActiveForWeb", num);
        this.refreshCache();
    }

    /**
     * 设置最大流量
     */
    public void updateMaxFlow(int maxBPS) {
        this.getSqlMapClientTemplate().update("updateMaxFlow", maxBPS);
        this.refreshCache();
    }

    /**
     * 查询系统全局参数
     * return Map<String,Object>
     */
	public Map<String,Object> querySysParam(){
        if(this.caches==null){
        	this.refreshCache();
        }
        return this.caches;
    }
    
	/**
	 * 刷新缓存
	 */
    @SuppressWarnings("unchecked")
	private void refreshCache(){
    	this.caches = new HashMap<String,Object>();
    	List<SysParamFlow> list=this.getSqlMapClientTemplate().queryForList("getSysParam");
        for(SysParamFlow param: list){
        	caches.put(param.getParamKey(), param.getParamValue());
        }
    }
    
    /**
     * 修改系统全局参数
     * param map
     */
    public void updateSysParam(Map<String, Object> map){
        this.getSqlMapClientTemplate().update("updateSysParam", map);
        this.refreshCache();
    }
    
    /**
     * 查询不被拦截的action集合
     */
    public String getExcludeActions(){
        return (String)this.getSqlMapClientTemplate().queryForObject("getExcludeActions");
    }
    
    /**
     * 获取系统录音路径
     */
    public String getRecordPath(){
        String path = (String)this.getSqlMapClientTemplate().queryForObject("getRecordPath");
        path = this.createRecordPath(path);//每次请求前判断路径 不存在则创建
        if(this.caches==null){
            this.refreshCache();
        }
        return path;
    }

    @Override
    public String getParamValueByKey(String key){
        String value = (String)this.getSqlMapClientTemplate().queryForObject("getParamValueByKey",key);
        return value;
    }
   
    /**
     * 获取录音存储预警值
     */
    public Integer getCueSize(){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getCueSize");
    }
    
    @Override
	public String getMaxAppLoanMoney() {
		return (String)this.getSqlMapClientTemplate().queryForObject("getMaxAppLoanMoney");
	}
    @Override
 	public String getMaxDunLoanTime() {
 		return (String)this.getSqlMapClientTemplate().queryForObject("getMaxDunLoanTime");
 	}
    
    
    public String getXFDDoubleApprovalTag(){
    	return (String)this.getSqlMapClientTemplate().queryForObject("getXFDDoubleApprovalTag");
    }
    public String getXFDDoubleApprovalValue(){
    	return (String)this.getSqlMapClientTemplate().queryForObject("getXFDDoubleApprovalValue");
    }
    public String getJYDDoubleApprovalTag(){
    	return (String)this.getSqlMapClientTemplate().queryForObject("getJYDDoubleApprovalTag");
    }
    public String getJYDDoubleApprovalValue(){
    	return (String)this.getSqlMapClientTemplate().queryForObject("getJYDDoubleApprovalValue");
    }
	/**
     * 根据录音路径判断路径是否有效存在，不存在则创建
     */
    private String createRecordPath(String path){
        if(StringUtil.isEmpty(path)){
            Map<String, Object> map =new HashMap<String, Object>();
            map.put("paramKey", "RECORD_PATH");
            if(SystemUtil.isWindows()){//是否windows系统
                map.put("paramValue", "c:/record");
                path = "c:/record";
            }else{//是否linux系统
                map.put("paramValue", "/home/record");
                path = "/home/record";
            }
            this.updateSysParam(map); //当系统路径为空时 创建默认路径
            File f = new File(path);//如果路径保存完不存在则创建路径
            if(!f.exists()){
            	f.mkdirs();
            }
        }else{
        	 File file = new File(path);
             if(!file.exists())file.mkdirs();
        }
//        if(FileUtil.isValidSystemFilePath(path)){}
           
        return path;
    }
}
