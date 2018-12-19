/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :功能权限...
 * Author     :cheny
 * Create Date:2012-5-31
 */
package com.banger.mobile.facade.impl.system.funcAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import com.banger.mobile.dao.funcAuth.FuncAuthDao;
import com.banger.mobile.domain.model.funcAuth.FuncAuthBean;
import com.banger.mobile.domain.model.funcAuth.SysFuncAuth;
import com.banger.mobile.domain.model.menuAuth.SysMenuAuth;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.facade.funcAuth.FuncAuthService;

/**
 * @author cheny
 * @version $Id: FuncAuthServiceImpl.java,v 0.1 2012-5-31 下午1:58:09 cheny Exp $
 */
public class FuncAuthServiceImpl implements FuncAuthService{

    private FuncAuthDao funcAuthDao;

    public void setFuncAuthDao(FuncAuthDao funcAuthDao) {
        this.funcAuthDao = funcAuthDao;
    }
    
    /**
     * 获得功能权限树集合
     */
    public List<FuncAuthBean> getFuncAuthTree(){
        return funcAuthDao.getFuncAuthTree();
    }
    
    /**
     * 树转换为json
     * @return
     */
    public JSONArray getAllFuncJson(){
        Map<String, Object> map = new HashMap<String, Object>();
        JSONArray jsonArray = new JSONArray();
        List<FuncAuthBean> beans = this.getFuncAuthTree();
        if(beans.size()>0){
            for (FuncAuthBean func : beans) {
              map.put("id", func.getFuncId());
              map.put("pId", func.getFuncParentId());
              map.put("name", func.getFuncName());
              map.put("authType", func.getType());
              if(func.getFuncParentId().equals(0)){
                  map.put("open", true);
              }
              jsonArray.add(map);
          }
        }
        return jsonArray;
    }

    /**
     *  新增角色功能权限 -->
     */
   public void insertFuncAuth(SysFuncAuth funcAuth){
       funcAuthDao.insertFuncAuth(funcAuth);
   }
   /**
    * 删除角色功能权限  -->
    */
   public void deleteFuncAuth(int roleId){
       funcAuthDao.deleteFuncAuth(roleId);
   }
   /**
    *  新增角色菜单权限 -->
    */
   public void insertMenuAuth(SysMenuAuth menuAuth){
       funcAuthDao.insertMenuAuth(menuAuth);
   }
   /**
    * 根据功能操作删除权限
    * @param funcId
    */
   public void deleteFuncAuthByFuncId(int funcId){
       funcAuthDao.deleteFuncAuthByFuncId(funcId);
   }
   /**
    * 修改功能权限
    */
   public void updateFuncAuth(int roleId,List<Integer> funcIds,int userId){
       List<SysFuncAuth> list = this.getFuncAuthByRoleId(roleId);
       SysFuncAuth funcAuth = new SysFuncAuth();
       if(list.size() > 0){
           //原来权限没有的要新增
           for (Integer funcId : funcIds) {
               boolean flag = false;
               for (SysFuncAuth sysFuncAuth : list) {
                   if(sysFuncAuth.getFuncId().equals(funcId)){
                       flag = true;
                   }
               }
               if(!flag){
                   funcAuth.setRoleId(roleId);
                   funcAuth.setFuncId(funcId);
                   funcAuth.setCreateUser(userId);
                   this.insertFuncAuth(funcAuth);
               }
           }
           //删除修改前的权限
           for (SysFuncAuth sysFuncAuth : list) {
               funcAuth = sysFuncAuth;
               boolean flag = false;
               for (Integer funcId : funcIds) {
                   if(sysFuncAuth.getFuncId().equals(funcId)){
                       flag = true;
                   }
               }
               if(!flag){
                   deleteFuncAuthByFuncId(funcAuth.getFuncAuthId());
               }
           }
       }else{
           //数据库里面没有全部新增
           for (Integer funcId : funcIds) {
               funcAuth.setCreateUser(userId);
               funcAuth.setFuncId(funcId);
               funcAuth.setRoleId(roleId);
               this.insertFuncAuth(funcAuth);
           }
       }
       
       
   }
   /**
    * 根据roleId查询功能权限
    */
   public List<SysFuncAuth> getFuncAuthByRoleId(int roleId){
       return funcAuthDao.getFuncAuthByRoleId(roleId);
   }
   
   /**
    * 删除角色菜单权限  -->
    */
   public void deleteMenuAuth(int roleId){
       funcAuthDao.deleteMenuAuth(roleId);
   }
   /**
    *  查看角色对应的功能权限 -->
    */
  public List<FuncAuthBean> getDetailFuncByRoleId(int roleId){
      return funcAuthDao.getDetailFuncByRoleId(roleId);
  }
  /**
   * 查看角色功能权限树
   */
  public JSONArray getRoleDetailFuncJson(int roleId){
      Map<String, Object> map = new HashMap<String, Object>();
      JSONArray jsonArray = new JSONArray();
      List<FuncAuthBean> beans = this.getDetailFuncByRoleId(roleId);
      if(beans.size()>0){
          for (FuncAuthBean func : beans) {
            map.put("id", func.getFuncId());
            map.put("pId", func.getFuncParentId());
            map.put("name", func.getFuncName());
            map.put("authType", func.getType());
            if(func.getFuncParentId().equals(0)){
                map.put("open", true);
            }
            jsonArray.add(map);
        }
      }
      return jsonArray;
  }
  /**
   * 返回角色对应功能权限节点的id集合
   */
  public List<Integer> getTreeNodeIds(int roleId){
      List<Integer> nodeIds = new ArrayList<Integer>();
      List<FuncAuthBean> beans = this.getDetailFuncByRoleId(roleId);
      for (int i=0;i<beans.size();i++) {
          nodeIds.add(beans.get(i).getFuncId());
      }
      return nodeIds; 
  }
  
  /**
   * 登录用户返回菜单url
   */
  public List<String> getMenuUrlByRoleIds(String[] roleIds){
      Map<String,Object> map = new HashMap<String,Object>();
      for (String roleId : roleIds) {
         List<FuncAuthBean> beans = this.getDetailFuncByRoleId(Integer.valueOf(roleId));
         for (FuncAuthBean funcAuthBean : beans) {
            if(funcAuthBean.getType().equals("MENU")){
                if(!funcAuthBean.geturl().equals("")){
                    map.put(funcAuthBean.geturl(), 1);
                  }
            }
        }  
    }
      List<String> urls = new ArrayList<String>(map.size());
      for (String str : map.keySet()) {
          urls.add(str);
      }
      return urls;
  }
  
  /**
   * 登录用户返回功能操作url
   */
  public List<String> getFuncUrlByRoleIds(String[] roleIds){
        Map<String, Object> map = new HashMap<String, Object>();
        for (String roleId : roleIds) {
            List<FuncAuthBean> beans = this.getDetailFuncByRoleId(Integer.valueOf(roleId));
            if (beans.size() > 0) {
                for (FuncAuthBean funcAuthBean : beans) {
                        if (funcAuthBean.geturl() != null && !funcAuthBean.geturl().equals("")) {
                            map.put(funcAuthBean.geturl(), 1);
                        }
                }
            }
        }
      List<String> urls = new ArrayList<String>(map.size());
      for (String str : map.keySet()) {
          urls.add(str);
      }
      return urls;
  }
  /**
   * 登录用户返回功能操作的id集合
   */
  public String[] getFuncIdsByRolesIds(String[] roleIds){
      Map<String,Object> map = new HashMap<String,Object>();
      for (String roleId : roleIds) {
          List<FuncAuthBean> beans = this.getDetailFuncByRoleId(Integer.valueOf(roleId));
            if (beans.size() > 0) {
                for (FuncAuthBean funcAuthBean : beans) {
                        if (funcAuthBean.geturl() != null && !funcAuthBean.geturl().equals("")) {
                            map.put(funcAuthBean.getFuncId().toString(), 1);
                        }
                }
            }
      }
      String[] funcIds = new String[map.size()];
      int i=0;
      for(String str : map.keySet()){
          funcIds[i++] = str;
      }
    return funcIds;
  }
  
  
  /**
   * 获得登录信息
   * @return
   */
  private IUserInfo getLoginUserInfo(){
      HttpServletRequest req = org.apache.struts2.ServletActionContext.getRequest();
      HttpSession session = req.getSession();
      return (IUserInfo)session.getAttribute("LoginInfo");
  }
  /**
   * 获得角色id集合
   * @return
   */
  private String getRoleIdStrings(){
      String ids="";
      String[] roles = getLoginUserInfo().getRoles();
      if(roles != null && roles.length>0){
          for (String id : roles) {
            ids += id+",";
        }
          ids = ids.substring(0,ids.length()-1);
      }
      return ids;
  }
  
  
  /**
   * 通话过程是否自动录音
   */
  public boolean isAutoRecord(){
      String roleIds = getRoleIdStrings();
      if(!roleIds.equals("")){
          List<Integer> funcIds = funcAuthDao.getFuncIdListByRoleId(roleIds);
          if(funcIds != null && funcIds.size()>0){
             if(funcIds.contains(51)) return true;
          }
      }
      return false;
  } 
  
  /**
   * 来电时是否自动播放录音提示音 
   */
  public boolean isPlayRecord(){
      String roleIds = getRoleIdStrings();
      if(!roleIds.equals("")){
          List<Integer> funcIds = funcAuthDao.getFuncIdListByRoleId(roleIds);
          if(funcIds != null && funcIds.size()>0){
             if(funcIds.contains(52)) return true;
          }
      }
      return false;
  } 
  /**
   * 是否有新建风险测评操作
   */
  public boolean isRiskEvaluation(){
      String roleIds = getRoleIdStrings();
      if(!roleIds.equals("")){
          List<Integer> funcIds = funcAuthDao.getFuncIdListByRoleId(roleIds);
          if(funcIds != null && funcIds.size()>0){
             if(funcIds.contains(111)) return true;
          }
      }
      return false;
  }
  /**
   * 是否有新建理财规划操作
   */
  public boolean isFinancialPlan(){
      String roleIds = getRoleIdStrings();
      if(!roleIds.equals("")){
          List<Integer> funcIds = funcAuthDao.getFuncIdListByRoleId(roleIds);
          if(funcIds != null && funcIds.size()>0){
             if(funcIds.contains(115)) return true;
          }
      }
      return false;
  }
  /**
   * 发送短信无需审核  true 是，false 否
   */
  public boolean isAuditedSms(){
      String roleIds = getRoleIdStrings();
      if(!roleIds.equals("")){
          List<Integer> funcIds = funcAuthDao.getFuncIdListByRoleId(roleIds);
          if(funcIds != null && funcIds.size()>0){
             if(funcIds.contains(74)) return true;
          }
      }
      return false;
  } 
  
  /**
   * 发送彩信无需审核 true 是，false 否
   */
  public boolean isAuditedMms(){
      String roleIds = getRoleIdStrings();
      if(!roleIds.equals("")){
          List<Integer> funcIds = funcAuthDao.getFuncIdListByRoleId(roleIds);
          if(funcIds != null && funcIds.size()>0){
             if(funcIds.contains(83)) return true;
          }
      }
      return false;
  } 
  
}

