/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :组织机构互调模块
 * Author     :cheny
 * Create Date:2012-5-24
 */
package com.banger.mobile.facade.dept;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.dept.CusBelongToBean;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.domain.model.user.SysUserBean;

import net.sf.json.JSONArray;

/**
 * @author cheny
 * @version $Id: DeptFacadeService.java,v 0.1 2012-5-24 下午3:48:30 cheny Exp $
 */
public interface DeptFacadeService {
    /**
     * 获得登录用户所管理的机构树json
     * @return
     */
   public JSONArray getInChargeOfDeptJson();
   
   /**
    * 查询业务主管所负责的机构树 List
    */
   public List<SysDept> getBusinessManagerInCharegeDeptTreeList();
   
   /**
    * 获得所在机构及下属机构 和 人员list
    */
   public List<CusBelongToBean> getBelongToUserAndDeptTreeList(String deptIds);


    /**
     * 获得admin用户管理机构和用户（除机构管理员和admin）
     * @return
     */
   public List<CusBelongToBean> getAdminToUserAndDeptTreeList();
   
   /**
    * 登录业务主管所负责机构的用户集合(包含删除用户和停用用户，不包含纯机构管理员和admin)
    * @return
    */
   public List<SysUser> getInChargeOfUserList();
   
   /**
   * 
   * 登录业务主管所负责机构的用户集合(不包含删除和停用的用户)
   */
  public List<SysUser> getBusinessManagerInCharegeOfUsers();
   
   /**
    * 获得（机构系统管理员）所管理的机构树
    * @return
    */
   public List<SysDept> getInChargeOfDeptList();

   /**
    * 获得登录用户所管理的部门id集合
    * @return
    */
   public Integer[] getInChargeOfDeptIds();
   /**
    * 获得登录用户所管理的部门用户的id集合
    * @return
    */
   public Integer[] getInChargeOfDeptUserIds();

    /**
     * 获得登录用户所管理的部门用户的id集合（除删除状态）
     * @return
     */
    public Integer[] getInChargeOfDeptUserNotDelIds();

   /**
    * 根据用户userId获取所管理的部门id集合
    * @return
    */
   public Integer[] getInChargeOfDeptIds(int userId);
   /**
    * 根据用户userId获取所管理的部门用户的id集合
    * @return
    */
   public Integer[] getInChargeOfDeptUserIds(int userId);
   /**
    * 获得登录用户所管理的部门用户(包含伪删除用户)的id集合
    * @return flag 为true是包含  为false不包含
    */
   public Integer[] getInChargeOfDeptUserIds(boolean flag);

   /**
    * 登录用户是否是团队主管
    * @return
    */
   public boolean isInChargeOfDepartment();
   /**
    * 登录用户是否是后台
    * @return
    */
   public boolean isInManOfDepartment();
  
   /**
    * 登录用户是否是系统管理员
    * @return
    */
   public boolean isSystemAdmin();
   /**
    * 登录用户是否是机构系统管理员
    * @return
    */
   public boolean isDeptAdmin();
   /**
    * 登录用户是否是客户经理
    * @return
    */
   public boolean isCommon();
   /**
    * admin 树转换为json
    * @return
    */
   public JSONArray getAdminDeptJson();
   /**
    * 登录业务主管 json树  添加默认根节点
    * @return
    */
   public JSONArray getBusinessManagerDeptJsonAddRoot();
   
   /**
    * 查询所在机构用户数据
    * 
    * 不包含删除和停用用户
    */
   public List<SysUser> searchUser(int deptId);
   /**
    *  登录业务主管所负责机构的用户id集合(包含删除和停用用户，不包含纯机构管理员和admin)
    * @return
    */
   public Integer[] getManagerInChargeOfUserIds();
   /**
    * 业务主管和客户经理 同级机构及上级机构json(短信彩信审核人)
    */
   public JSONArray getExamineDeptJson();
   /**
    * 获得登录用户id
    * @return
    */
   public Integer getLoginUserId();
   /**
    * 查询总行机构及下属人员列表
    */
     public List<CusBelongToBean> getBelongToUserAll();
     /**
      * 根据登陆的机构管理员获取所 管理的部门用户数
      * @return
      */
     public Integer[] getInChargeOfDeptUsersCount();
     /**
      * 根据deptId查询下属机构ids
      */
     public String getInChargeDeptIdsByDeptId(int deptId);
     /**
      *根据deptIds查询机构下的人员
      * 包含删除、停用
      * 不包含纯机构管理员、admin
      */
     public String getStringUserIdContainsByDeptIds(String deptIds);
     /**
      * 根据deptId字符串的到机构集合(包含下属)
      */
     public List<SysDept> getContainDeptListByDeptIds(String deptIds);
     /**
      * 根据deptId获得机构的完全路径
      * @return
      */
     public String getDeptFullPath(int deptId);
     
     /**
      * 根据deptIds查询实体集合 
      */
     public List<SysDept> getDeptsByDeptIds(String ids);
     /**
      * 根据用户ID集合取出用户对应用户对象
      * @return
      */
     public List<SysUserBean> getUsersByUserIds(String ids);
     
     /**
      * 根据userId判断用户是否是业务主管
      */
     public boolean isInChargeOfDepartment(String userid);
     /**
      * 查询客户经理及营销人员机构列表
      */
     public List<SysDept> getCounterUserDeptList();
     /**
      * 业务主管 json树（不添加默认根节点）
      * 
      */
     public JSONArray getBusinessManagerDeptJsonNoFilter();
     
     /**
      * 营销人员 json树 （不添加默认根节点）（不包含没有人员的机构）
      * 
      */
     public JSONArray getCounterUserDeptJson();
     /**
      * 客户经理所管理的柜台人员
      */
     public String getCounterUserBelongCommon();
     /**
      * 业务主管所管理的柜台人员
      */
     public String getCounterUserBelongManager();
     /**
      * 查询业务主管所管理的用户id (包含删除和停用及自己)
      */
     public String getUserIdsBelongToManager();

    /**
     * 查询业务主管所管理的用户id (包含删除和停用及自己)
     * @param dataCode 模块名如：客户信息
     * @return
     */
    public String getUserIdsBelongToManager(String[] roleIds,String dataCode);
    
    /**
     * 查询业务主管所管理的用户id (包含删除和停用及自己) for PAD， pad端登陆没有session,所以需要传userId进去
     * @param dataCode 模块名如：客户信息
     * @return
     */
    public String getUserIdsBelongToManagerForPAD(String[] roleIds,String dataCode, Integer userId);

    /**
     * 查询业务主管是否有特殊数据权限
     * @param dataCode 模块名如：客户信息
     * @return
     */
    public Boolean isSpecialData(String[] roleIds,String dataCode);

    
     /**
      * 客户经理所管理的柜台人员
      */
     public String getCounterUserBelongCommon(int userId);
     /**
      * 业务主管所管理的柜台人员
      */
     public String getCounterUserBelongManager(int userId);
     /**
      * 查询业务主管所负责的机构 List
      */
     public List<SysDept> getBusinessManagerInCharegeDeptTreeList(int userId);
     
     /**
      * 机构管理员或业务主管所负责的机构树list
      * roleId=2 机构管理员  roleId=3 业务主管
      */
     public List<SysDept> getDeptsForManger(int roleId,int userId);
     
     /**
      * 业务主管所管辖的人员ids (不包含删除和停用)
      * userId 当前业务主管的userId
      * flag==0 下属人员（包含自己） flag==1 下属客户经理（自己又是客户经理则包含自己）
      */
     public String getInChargeOfDeptUserIds(int userId,int flag);
     
     /**
      * 查询admin所管理的机构树 List
      */
     public List<SysDept> getAdminInCharegeDeptTreeList();
     
     /**
      * 查询业务主管所管辖的所有包含客户经理角色的人员（包括删除，停用）
      * @return
      */
     public List<SysUser> getInchargeUserAllManager();
     
     /**
      * 所有机构及人员（下属的）list，包含柜台人员
      * @return
      */
     public List<CusBelongToBean> getAllCusBelongBeanCounterList(String deptIds);
     
    /**
     * 业务主管下属人员(业务主管和客户经理)(包含停用 不包含删除)
     * @return
     */
     public Integer[] getInChargeOfDeptUserIdsNoDel();
     /**
      * 根据deptIds查询机构下的人员
      * 包含停用
      * 不包含删除、纯机构管理员、admin
      */
     public String getStringUserIdContainsByDeptIdsWithOutDel(String deptIds);
     /**机构id集合
      * 
      * 
      * 获得业务主管所负责机构id 字符串
      * @return
      */
       public String getInChargeOfStringDeptIds(int userId);

    /**
     * 获得当前登录用户直接负责的根机构Id集合
     * @param userId
     * @return
     */
    public List<Integer> getRootDeptIdByLoginId(int userId);
    /**
     * 业务主管下属人员(业务主管和客户经理)(不包含删除和停用 )
     * @param userId
     * @return
     */
    List<SysUser> getManagerUserList(int userId);

    Map<Integer,SysDept> getSysDeptMap();

    Map<Integer,SysUser> getSysUserMap();

    Map<Integer, List<SysDept>> getAllDeptForMap();

    List<SysDept> getExamineCommonList(SysUser sysUser);
}
