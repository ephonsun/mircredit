/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :导出excel
 * Author     :cheny
 * Create Date:2012-9-10
 */
package com.banger.mobile.facade.impl.customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.banger.mobile.dao.crmModuleExport.CrmModuleExportDao;
import com.banger.mobile.domain.model.crmModuleExport.CrmModuleExport;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.facade.crmModuleExport.CrmModuleExportService;

/**
 * @author cheny
 * @version $Id: CrmModuleExportServiceImpl.java,v 0.1 2012-9-10 上午11:45:03 cheny Exp $
 */
public class CrmModuleExportServiceImpl implements CrmModuleExportService{

    private CrmModuleExportDao crmModuleExportDao;

    public void setCrmModuleExportDao(CrmModuleExportDao crmModuleExportDao) {
        this.crmModuleExportDao = crmModuleExportDao;
    }
    
    /**
     * 获得登录信息
     * @return
     */
    private IUserInfo getUserLoginInfo(){
        HttpServletRequest req = org.apache.struts2.ServletActionContext.getRequest();
        HttpSession session = req.getSession();
        return (IUserInfo)session.getAttribute("LoginInfo");
    }
    /**
     * 记录 导入导出 字段
     */
	public void insertCrmModuleExport(CrmModuleExport crmModuleExport){
	    crmModuleExportDao.insertCrmModuleExport(crmModuleExport);
	}
    
	/**
	 * 获取登录用户上次导入导出字段 记录
	 */
    public List<CrmModuleExport>  getCrmModuleExportList(String moduleName){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userId", getUserLoginInfo().getUserId());
        map.put("moduleName", moduleName);
        return crmModuleExportDao.getCrmModuleExportList(map);
    }
    
    /**
     * 删除 导入导出 记录字段
     */
    public void delCrmModuleExportById(int id){
        crmModuleExportDao.delCrmModuleExportById(id);
    }
    
    /**
     * 页面返回的字段   
     */
    public List<String> getFileValues(String data){
        List<String> values = new ArrayList<String>();
        if(data !=null && data !=""){
            String[] codes = data.split(":");
            for (String code : codes) {
                if(!code.contains(","))  {//基础字段
                    values.add(code);
                }
                else{
                    String[] cd = code.split(",");
                    int n = Integer.valueOf(cd[0]);
                    if(n<1) {//自定义字段
                        values.add(cd[3]);
                    }else{//扩展字段
                        values.add(cd[2]);
                    }
                }
            }
        }
        return values;
    }
    
    
    /**
     * 保存字段
     */
    public void saveExportField(String data,String moduleName){
        List<String> feilds = getFileValues(data);
        List<CrmModuleExport> oldFeilds = getCrmModuleExportList(moduleName);
        CrmModuleExport crmModuleExport = null;
        if(oldFeilds!=null && oldFeilds.size() >0){
            //原来权限没有的要新增
            for(String feildName : feilds){
                crmModuleExport = new CrmModuleExport();
                boolean flag = false;
                for(CrmModuleExport sdata : oldFeilds){
                    if(feildName.equals(sdata.getFeildName())){
                        flag = true;
                    }
                }
                if(!flag){
                    crmModuleExport.setUserId(getUserLoginInfo().getUserId());
                    crmModuleExport.setCreateUser(getUserLoginInfo().getUserId());
                    crmModuleExport.setModuleName(moduleName);
                    crmModuleExport.setFeildName(feildName);
                    insertCrmModuleExport(crmModuleExport);
                }
            }
            //删除修改前的权限
            for(CrmModuleExport mExport : oldFeilds){
                crmModuleExport = mExport;
                boolean flag = false;
                for(String feildName : feilds){
                    if(feildName.equals(crmModuleExport.getFeildName())){
                        flag = true;
                    }
                }
                if(!flag){
                    delCrmModuleExportById(crmModuleExport.getModuleExportID());
                }
            }
            
        }else{
            //数据库里面没有全部新增
            for(String feildName : feilds){
                crmModuleExport = new CrmModuleExport();
                crmModuleExport.setUserId(getUserLoginInfo().getUserId());
                crmModuleExport.setCreateUser(getUserLoginInfo().getUserId());
                crmModuleExport.setModuleName(moduleName);
                crmModuleExport.setFeildName(feildName);
                insertCrmModuleExport(crmModuleExport);
            }  
        }
        
        
        
    }

    /**
     * 模糊匹配登录用户上次导入导出字段 记录
     */
    public List<CrmModuleExport> getCrmModuleLikeList(String moduleName) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userId", getUserLoginInfo().getUserId());
        map.put("moduleName", moduleName);
        return crmModuleExportDao.getCrmModuleLikeList(map);
    }
 
    
}
