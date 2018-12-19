/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :数据访问权限接口实现类
 * Author     :cheny
 * Create Date:2012-5-29
 */
package com.banger.mobile.facade.impl.system.dataAuth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.dataAuth.DataAuthDao;
import com.banger.mobile.domain.model.dataAuth.SysDataAuth;
import com.banger.mobile.domain.model.role.SysRole;
import com.banger.mobile.facade.dataAuth.DataAuthService;

/**
 * @author cheny
 * @version $Id: DataAuthServiceImpl.java,v 0.1 2012-5-29 上午10:40:55 cheny Exp $
 */
public class DataAuthServiceImpl implements DataAuthService{
    
    private DataAuthDao dataAuthDao;

    public void setDataAuthDao(DataAuthDao dataAuthDao) {
        this.dataAuthDao = dataAuthDao;
    }

    /**
     * 新增数据访问权限
     * @param dataAuth
     */
    public void saveDataAuth(SysDataAuth dataAuth){
       dataAuthDao.saveDataAuth(dataAuth);
    }
    /**
     * 删除数据访问权限
     * @param roleId
     */
    public void deleteDataAuth(SysDataAuth dataAuth){
        dataAuthDao.deleteDataAuth(dataAuth);
    }    
    /**
     * 根据roleId查询数据访问权限
     * @param roleId
     * @return
     */
    public List<SysDataAuth> getDataAuthByRoleId(int roleId){
        return dataAuthDao.getDataAuthByRoleId(roleId);
    }
    /**添加数据访问权限
     * 
     */
    public void addDataAuth(SysRole role, Map<String,Object> map,int userId){
        SysDataAuth data = new SysDataAuth();
        if(!map.get("UserData").equals("") && map.get("DeptData").equals("")){
            String selfData = (String)map.get("UserData");
            data.setDataAuthCode(selfData);
            data.setRoleId(role.getRoleId());
            data.setCreateUser(userId);
            saveDataAuth(data);
        }else if(!map.get("DeptData").equals("") && map.get("UserData").equals("")){
            String dataCode = (String)map.get("DeptData");
            data.setDataAuthCode(dataCode);
            data.setRoleId(role.getRoleId());
            data.setCreateUser(userId);
            saveDataAuth(data);
        }else{
            String selfData = (String)map.get("UserData");
            String dataCode = (String)map.get("DeptData");
            data.setRoleId(role.getRoleId());
            data.setDataAuthCode(selfData);
            data.setCreateUser(userId);
            saveDataAuth(data);
            SysDataAuth auth = new SysDataAuth();
            auth.setRoleId(role.getRoleId());
            auth.setDataAuthCode(dataCode);
            auth.setCreateUser(userId);
            saveDataAuth(auth);
        }
    }
    
    /**设置数据访问权限
     * 
     */
    public Map<String,Object> setDataAuth(SysRole role){
        Map<String,Object> map = new HashMap<String,Object>();
        List<SysDataAuth> dataAuths = getDataAuthByRoleId(role.getRoleId());
        if(dataAuths.size() == 2){
            map.put("Checked", 1);
            for (SysDataAuth sysDataAuth : dataAuths) {
                if(sysDataAuth.getDataAuthCode().equals("SELF")){
                    map.put("UserData", "SELF");
                }else{
                    map.put("DeptData", sysDataAuth.getDataAuthCode());
                }
            }
        }else if(dataAuths.size()==1){
            SysDataAuth data = dataAuths.get(0);
            if(data.getDataAuthCode().equals("SELF")){
                map.put("UserData", "SELF");
                map.put("Checked", 0);
            }else{
                map.put("Checked", 1);
                map.put("DeptData", data.getDataAuthCode());
            }  
        }else{
            map.put("Checked", 0);
        }  
        return map;
    }
    /**修改数据访问权限
     * 
     */
     public void updateDataAuthList(int roleId,  Map<String,Object> map,int userId){
          List<SysDataAuth> dataList = this.getDataAuthByRoleId(roleId);
          SysDataAuth dataAuth = null;
          if(dataList.size() >0){
              //原来权限没有的要新增
              for(String code : map.keySet()){
                  dataAuth = new SysDataAuth();
                  boolean flag = false;
                  for(SysDataAuth sdata : dataList){
                      if(code.equals(sdata.getDataAuthCode())){
                          flag = true;
                      }
                  }
                  if(!flag){
                      dataAuth.setRoleId(roleId);
                      dataAuth.setDataAuthCode(code);
                      dataAuth.setCreateUser(userId);
                      saveDataAuth(dataAuth);
                  }
              }
              //删除修改前的权限
              for (SysDataAuth sysDataAuth : dataList) {
                  dataAuth = sysDataAuth;
                  boolean flag = false;
                  for(String code : map.keySet()){
                      if(code.equals(dataAuth.getDataAuthCode())){
                          flag = true;
                      }
                  }
                  if(!flag){
                      deleteDataAuth(dataAuth);
                  }
              }
              
          }else{
              //数据库里面没有全部新增
              for(String code : map.keySet()){
                  dataAuth = new SysDataAuth();
                  dataAuth.setRoleId(roleId);
                  dataAuth.setDataAuthCode(code);
                  dataAuth.setCreateUser(userId);
                  saveDataAuth(dataAuth);
              }  
          }
          
     }  
}
