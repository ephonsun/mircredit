/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :机构对外接口...
 * Author     :cheny
 * Create Date:2012-5-29
 */
package com.banger.mobile.facade.impl.system.dept;

import com.banger.mobile.dao.dataAuth.DataAuthDao;
import com.banger.mobile.dao.dept.DeptDao;
import com.banger.mobile.dao.user.SysDeptAuthDao;
import com.banger.mobile.dao.user.SysRoleMemberDao;
import com.banger.mobile.dao.user.SysUserDao;
import com.banger.mobile.domain.Enum.dept.EnumDept;
import com.banger.mobile.domain.model.dept.CusBelongToBean;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.user.*;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.specialDataAuth.SpecialDataAuthService;
import com.banger.mobile.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author cheny
 * @version $Id: DeptFacadeServiceImpl.java,v 0.1 2012-5-29 上午10:24:29 cheny Exp $
 */
public class DeptFacadeServiceImpl implements DeptFacadeService {
    private static final Logger logger = Logger.getLogger(DeptFacadeServiceImpl.class);

    private DeptDao deptDao;
    private DataAuthDao dataAuthDao;
    private SysUserDao userDao;
    private SysRoleMemberDao sysRoleMemberDao;
    private SysDeptAuthDao sysDeptAuthDao; //机构权限dao
    private SpecialDataAuthService specialDataAuthService;

    public void setSpecialDataAuthService(SpecialDataAuthService specialDataAuthService) {
        this.specialDataAuthService = specialDataAuthService;
    }

    public void setDeptDao(DeptDao deptDao) {
        this.deptDao = deptDao;
    }

    public void setDataAuthDao(DataAuthDao dataAuthDao) {
        this.dataAuthDao = dataAuthDao;
    }

    public void setUserDao(SysUserDao userDao) {
        this.userDao = userDao;
    }

    public void setSysRoleMemberDao(SysRoleMemberDao sysRoleMemberDao) {
        this.sysRoleMemberDao = sysRoleMemberDao;
    }

    public void setSysDeptAuthDao(SysDeptAuthDao sysDeptAuthDao) {
        this.sysDeptAuthDao = sysDeptAuthDao;
    }

    /**
     * 获得登录信息
     *
     * @return
     */
    private IUserInfo getUserLoginInfo() {
        HttpServletRequest req = org.apache.struts2.ServletActionContext.getRequest();
        HttpSession session = req.getSession();
        return (IUserInfo) session.getAttribute("LoginInfo");
    }

    /**
     * 获取角色id集合
     *
     * @return
     */
    private Integer[] getRolesId() {
        String[] strs = getUserLoginInfo().getRoles();
        Integer[] roleIds = null;
        if (strs != null && strs.length > 0) {
            roleIds = new Integer[strs.length];
            for (int i = 0; i < strs.length; i++) {
                roleIds[i] = Integer.valueOf(strs[i]);
            }
            return roleIds;
        }
        return null;

    }

    /**
     * 登录用户是否是团队主管
     */
    public boolean isInChargeOfDepartment() {
        Integer[] roleIds = getRolesId();
        if (roleIds != null && roleIds.length > 0) {
            for (Integer id : roleIds) {
                if (id.equals(5)||id.equals(3)||id.equals(1)) return true;
            }
        }
        return false;
    }
    /**
     * 登录用户是否是后台
     */
    public boolean isInManOfDepartment() {
        Integer[] roleIds = getRolesId();
        if (roleIds != null && roleIds.length > 0) {
            for (Integer id : roleIds) {
                if (id.equals(6)) return true;
            }
        }
        return false;
    }
    //登录用户是否是客户经理
    public boolean isCommon() {
        Integer[] roleIds = getRolesId();
        if (roleIds != null && roleIds.length > 0) {
            for (Integer id : roleIds) {
                if (id.equals(7)) return true;
            }
        }
        return false;
    }

    /**
     * 根据userId判断用户是否是业务主管
     */
    public boolean isInChargeOfDepartment(String userid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", Integer.valueOf(userid));
        List<SysRoleMember> members = sysRoleMemberDao.getSysRoleMemberList("GetRoleMemberByUserIds", map);
        if (members != null && members.size() > 0) {
            for (SysRoleMember sysRoleMember : members) {
                if (sysRoleMember.getRoleId().equals(3)) return true;
            }
        }
        return false;
    }
    
    /**
     * 登录用户是否是系统管理员
     */
    public boolean isSystemAdmin() {
        Integer[] roleIds = getRolesId();
        if (roleIds != null && roleIds.length > 0) {
            for (Integer id : roleIds) {
                if (id.equals(1)) return true;
            }
        }
        return false;
    }

    /**
     * 登录用户是否是机构系统管理员
     */
    public boolean isDeptAdmin() {
        Integer[] roleIds = getRolesId();
        if (roleIds != null && roleIds.length > 0) {
            for (Integer id : roleIds) {
                if (id.equals(2)) return true;
            }
        }
        return false;
    }

    //登录用户是否是客户经理
    public boolean isCommon(int userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        List<SysRoleMember> members = sysRoleMemberDao.getSysRoleMemberList("GetRoleMemberByUserIds", map);
        if (members != null && members.size() > 0) {
            for (SysRoleMember sysRoleMember : members) {
                if (sysRoleMember.getRoleId().equals(4)) return true;
            }
        }
        return false;
    }

    public Integer getLoginUserId() {
        return this.getUserLoginInfo().getUserId();
    }
    /********************************************添加缓存**********************************************/
    /**
     * 缓存机构树对象map
     *
     * @return
     */
    public Map<Integer, List<SysDept>> getAllDeptForMap() {
        Map<Integer, List<SysDept>> map = new HashMap<Integer, List<SysDept>>();
        List<SysDept> deptList = deptDao.getAllDeptForCache();
        for (SysDept dept:deptList){
            if (!map.containsKey(dept.getDeptId())) {//是否已经有了此deptid  没有者新加
                map.put(dept.getDeptId(), new ArrayList<SysDept>());
                map.get(dept.getDeptId()).add(dept);
            }
        }
        for (SysDept dept : deptList) {
            this.addParentDept(dept, dept.getDeptParentId(), map);
        }
        return map;
    }

    /**
     * 添加父机构
     *
     * @param dept
     * @param parentId
     * @param map
     */
    private void addParentDept(SysDept dept, Integer parentId, Map<Integer, List<SysDept>> map) {
        //父机构存在
        if (map.containsKey(parentId)) {
            List<SysDept> pDepts = map.get(parentId);//从map里面得到父结构list
            pDepts.add(dept);
            if (pDepts.get(0).getDeptId().equals(parentId)) addParentDept(dept, pDepts.get(0).getDeptParentId(), map);
        }
    }

    /**
     * 缓存所有的机构权限deptAuth
     */
    private List<SysDeptAuth> getAllDeptAuthForCache() {
        return sysDeptAuthDao.getAllDeptAuthForCache();
    }

    /**
     * (机构管理员或业务主管)的机构权限 根机构deptId
     * 机构管理员roleId=2 业务主管roleId=3
     */
    private List<Integer> getRootDeptIdByLogin(int roleId, int userId) {
        List<Integer> list = new ArrayList<Integer>();
        List<SysDeptAuth> auths = getAllDeptAuthForCache();
        for (SysDeptAuth auth : auths) {
            if (auth.getUserId().equals(userId) && auth.getRoleId().equals(roleId)) {
                list.add(auth.getDeptId());
            }
        }
        return list;
    }

    /**
     * 机构管理员或业务主管所负责的机构树list
     * roleId=2 机构管理员  roleId=3 业务主管
     */
    public List<SysDept> getDeptsForManger(int roleId, int userId) {
        List<SysDept> deptList = new ArrayList<SysDept>();
        List<Integer> inChargeDeptIds = getRootDeptIdByLogin(roleId, userId);
        if (inChargeDeptIds != null && inChargeDeptIds.size() > 0) {
            Map<Integer, List<SysDept>> map = getAllDeptForMap();
            for (Integer deptId : inChargeDeptIds) {
                if (map.containsKey(deptId)) {
                    List<SysDept> depts = map.get(deptId);
                    if (depts != null && depts.size() > 0) {
                        for (SysDept d : depts) {
                            deptList.add(d);
                        }
                    }
                }
            }
        }
        return deptList;

    }

    /**
     * 缓存所有的客户经理和业务主管
     *
     * @return
     */
    private List<SysUser> getAllUserForCache() {
        return userDao.getAllUserForCache();
    }


    /**
     * 获得admin用户管理机构和用户（除机构管理员和admin）
     *
     * @return
     */
    public List<CusBelongToBean> getAdminToUserAndDeptTreeList() {
        List<CusBelongToBean> allList = getAllCusBelongBeanList();
        return allList;
    }

    /**
     * 业务主管下属人员(业务主管和客户经理)(不包含删除和停用 )
     *
     * @param userId
     * @return
     */
    public List<SysUser> getManagerUserList(int userId) {
        List<SysUser> userList = new ArrayList<SysUser>();
        List<Integer> deptIds = getRootDeptIdByLogin(3, userId);
        if (deptIds != null && deptIds.size() > 0) {
            Map<Integer, List<SysDept>> map = getAllDeptForMap();
            List<SysUser> users = getAllUserForCache();
            Map<Integer, List<SysUser>> uMap = getActivedAllUserMap(users);
            for (Integer deptId : deptIds) {
                if (map.containsKey(deptId)) {
                    List<SysDept> depts = map.get(deptId);
                    if (depts != null && depts.size() > 0) {
                        for (SysDept d : depts) {
                            if (uMap.containsKey(d.getDeptId())) {
                                userList.addAll(uMap.get(d.getDeptId()));
                            }
                        }
                    }
                }
            }
        }
        return userList;
    }

    /**
     * 业务主管下属人员(业务主管和客户经理)(不包含删除和停用 )
     *
     * @param userId
     * @return
     */
    public List<SysUser> getManagerUserListNotDel(int userId) {
        List<SysUser> userList = new ArrayList<SysUser>();
        List<Integer> deptIds = getRootDeptIdByLogin(3, userId);
        if (deptIds != null && deptIds.size() > 0) {
            Map<Integer, List<SysDept>> map = getAllDeptForMap();
            List<SysUser> users = getAllUserForCache();
            Map<Integer, List<SysUser>> uMap = getNotDelUserMap(users);
            for (Integer deptId : deptIds) {
                if (map.containsKey(deptId)) {
                    List<SysDept> depts = map.get(deptId);
                    if (depts != null && depts.size() > 0) {
                        for (SysDept d : depts) {
                            if (uMap.containsKey(d.getDeptId())) {
                                userList.addAll(uMap.get(d.getDeptId()));
                            }
                        }
                    }
                }
            }
        }
        return userList;
    }

    /**
     * 获得所有可用的用户
     *
     * @param users
     * @return
     */
    private Map<Integer, List<SysUser>> getActivedAllUserMap(List<SysUser> users) {
        Map<Integer, List<SysUser>> map = new HashMap<Integer, List<SysUser>>();
        for (SysUser user : users) {
            if (user.getIsDel().equals(0) && user.getIsActived().equals(1)) {
                if (!map.containsKey(user.getDeptId())) map.put(user.getDeptId(), new ArrayList<SysUser>());
                map.get(user.getDeptId()).add(user);
            }
        }
        return map;
    }

    /**
     * 获得所有可用除删除的用户
     *
     * @param users
     * @return
     */
    private Map<Integer, List<SysUser>> getNotDelUserMap(List<SysUser> users) {
        Map<Integer, List<SysUser>> map = new HashMap<Integer, List<SysUser>>();
        for (SysUser user : users) {
            if (user.getIsDel().equals(0)) {
                if (!map.containsKey(user.getDeptId())) map.put(user.getDeptId(), new ArrayList<SysUser>());
                map.get(user.getDeptId()).add(user);
            }
        }
        return map;
    }

    /**
     * 根据deptIds查询机构下人员list（包含删除停用）
     *
     * @param deptIds
     * @return
     */
    private List<SysUser> getUnderDeptUserList(String deptIds) {
        List<SysUser> userList = new ArrayList<SysUser>();
        String[] inchargeIds = null;
        if (deptIds != null && !deptIds.equals("")) {
            inchargeIds = deptIds.split(",");
        }
        if (inchargeIds != null && inchargeIds.length > 0) {
            for (String sdeptId : inchargeIds) {
                Integer deptId = Integer.valueOf(sdeptId);
                Map<Integer, List<SysDept>> map = getAllDeptForMap();
                if (map.containsKey(deptId)) {
                    List<SysDept> depts = map.get(deptId);
                    if (depts != null && depts.size() > 0) {
                        SysDept d = null;
                        for (SysDept sysDept : depts) {
                            if (sysDept.getDeptId().equals(deptId)) {
                                d = sysDept;
                                break;
                            }
                        }
                        if (d != null) {
                            List<SysUser> users = getAllUserForCache();
                            if (users != null && users.size() > 0) {
                                for (SysUser user : users) {
                                    if (user.getDeptId().equals(d.getDeptId())) {
                                        userList.add(user);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return userList;
    }

    /**
     * 根据deptIds查询机构下人员list（包含停用不包含删除）
     *
     * @param deptIds
     * @return
     */
    private List<SysUser> getUnderDeptUserListWithOutDel(String deptIds) {
        List<SysUser> userList = new ArrayList<SysUser>();
        String[] inchargeIds = null;
        if (deptIds != null && !deptIds.equals("")) {
            inchargeIds = deptIds.split(",");
        }
        if (inchargeIds != null && inchargeIds.length > 0) {
            for (String sdeptId : inchargeIds) {
                Integer deptId = Integer.valueOf(sdeptId);
                Map<Integer, List<SysDept>> map = getAllDeptForMap();
                if (map.containsKey(deptId)) {
                    List<SysDept> depts = map.get(deptId);
                    if (depts != null && depts.size() > 0) {
                        SysDept d = null;
                        for (SysDept sysDept : depts) {
                            if (sysDept.getDeptId().equals(deptId)) {
                                d = sysDept;
                                break;
                            }
                        }
                        if (d != null) {
                            List<SysUser> users = getAllUserForCache();
                            if (users != null && users.size() > 0) {
                                for (SysUser user : users) {
                                    if (user.getIsDel().equals(0) && user.getDeptId().equals(d.getDeptId())) {
                                        userList.add(user);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return userList;
    }

    /**
     * 业务主管下属人员(业务主管和客户经理)(包含删除和停用 )
     *
     * @param userId
     * @return
     */
    private List<SysUser> getManagerUserListContainDel(int userId) {
        List<SysUser> userList = new ArrayList<SysUser>();
        List<Integer> deptIds = getRootDeptIdByLogin(3, userId);
        Map<Integer, List<SysDept>> map = getAllDeptForMap();
        if (deptIds != null && deptIds.size() > 0) {
            for (Integer deptId : deptIds) {
                if (map.containsKey(deptId)) {
                    List<SysDept> depts = map.get(deptId);
                    if (depts != null && depts.size() > 0) {
                        for (SysDept d : depts) {
                            List<SysUser> users = getAllUserForCache();
                            if (users != null && users.size() > 0) {
                                for (SysUser user : users) {
                                    if (user.getDeptId().equals(d.getDeptId())) {
                                        userList.add(user);
                                    }
                                }
                            }
                        }
                    }
                }
                logger.info(deptId);
            }
        }
        return userList;
    }

    /**
     * 业务主管所负责的机构及人员（下属的）list
     *
     * @param deptIds
     * @return
     */
    private List<CusBelongToBean> getCusBelongBeanList(String deptIds) {
        List<CusBelongToBean> beanList = new ArrayList<CusBelongToBean>();
        List<CusBelongToBean> allList = getAllCusBelongBeanList();
        List<CusBelongToBean> someList = getCusBelongBeanListNoSort(deptIds);
        for (CusBelongToBean bean : allList) {
            if (someList != null && someList.size() > 0) {
                for (CusBelongToBean cus : someList) {
                    if (bean.getId().equals(cus.getId())) beanList.add(cus);
                }
            }
        }
        return beanList;
    }

    /**
     * 获得下属归属机构人员列表
     *
     * @param deptIds
     * @return
     */
    private List<CusBelongToBean> getCusBelongBeanListNoSort(String deptIds) {
        List<CusBelongToBean> beanList = new ArrayList<CusBelongToBean>();
        String[] inChargeDeptIds = null;
        if (deptIds != null && !deptIds.equals("")) inChargeDeptIds = deptIds.split(",");
        List<CusBelongToBean> userList = new ArrayList<CusBelongToBean>();
        CusBelongToBean bean = null;
        if (inChargeDeptIds != null && inChargeDeptIds.length > 0) {
            Map<Integer, List<SysDept>> map = getAllDeptForMap();
            for (String sdeptId : inChargeDeptIds) {
                Integer deptId = Integer.valueOf(sdeptId);
                if (map.containsKey(deptId)) {
                    List<SysDept> depts = map.get(deptId);
                    if (depts != null && depts.size() > 0) {
                        SysDept d = null;
                        for (SysDept sysDept : depts) {
                            if (sysDept.getDeptId().equals(deptId)) {
                                d = sysDept;
                            }
                        }
                        if (d != null) {
                            bean = new CusBelongToBean();
                            bean.setId(d.getDeptId());
                            bean.setTextName(d.getDeptName());
                            bean.setParentId(d.getDeptParentId());
                            bean.setType("D");
                            bean.setDeptName(d.getDeptName());
                            beanList.add(bean);
                            List<SysUser> users = getAllUserForCache();
                            if (users != null && users.size() > 0) {
                                for (SysUser user : users) {
                                    if (user.getIsDel().equals(0) && user.getIsActived().equals(1) && user.getDeptId().equals(d.getDeptId())) {
                                        bean = new CusBelongToBean();
                                        bean.setId(user.getUserId());
                                        bean.setTextName(user.getUserName());
                                        bean.setParentId(user.getDeptId());
                                        bean.setType("U");
                                        bean.setDeptName(d.getDeptName());
                                        userList.add(bean);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (userList != null && userList.size() > 0) {
                for (CusBelongToBean cu : userList) {
                    beanList.add(cu);
                }
            }
        }
        return beanList;
    }

    /**
     * 所有机构及人员（下属的）list
     *
     * @return
     */
    private List<CusBelongToBean> getAllCusBelongBeanList() {
        List<CusBelongToBean> beanList = new ArrayList<CusBelongToBean>();
        CusBelongToBean bean = null;
        List<SysDept> deptList = deptDao.getAllDeptForCache();
        for (SysDept d : deptList) {
            bean = new CusBelongToBean();
            bean.setId(d.getDeptId());
            bean.setTextName(d.getDeptName());
            bean.setParentId(d.getDeptParentId());
            bean.setType("D");
            bean.setDeptName(d.getDeptName());
            beanList.add(bean);
        }
        List<SysUser> users = getAllUserForCache();
        Map<Integer, List<SysUser>> uMap = getActivedAllUserMap(users);
        for (SysDept d : deptList) {
            if (uMap.containsKey(d.getDeptId())) {
                List<SysUser> uList = uMap.get(d.getDeptId());
                for (SysUser user : uList) {
                    bean = new CusBelongToBean();
                    bean.setId(user.getUserId());
                    bean.setTextName(user.getUserName());
                    bean.setParentId(user.getDeptId());
                    bean.setType("U");
                    bean.setDeptName(d.getDeptName());
                    beanList.add(bean);
                }
            }
        }
        return beanList;
    }


    /**
     * 判断部门下是否包含人员
     *
     * @param users
     * @param deptId
     * @return
     */
    private Integer getDeptUserCount(List<SysUserBean> users, Integer deptId) {
        int count = 0;
        for (SysUserBean user : users) {
            if (user.getDeptId().equals(deptId)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 缓存所有用户
     *
     * @return
     */
    private List<SysUserBean> getAllUserAndCounterUserForCache(String deptIds) {
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("userId", getLoginUserId());
        condition.put("deptIds", deptIds);
        return userDao.getSysUserAndCounterUser(condition);
    }

    /**
     * 获得所有可用用户
     *
     * @param users
     * @return
     */
    private Map<Integer, List<SysUserBean>> getActivedAllUserBeanMap(List<SysUserBean> users) {
        Map<Integer, List<SysUserBean>> map = new HashMap<Integer, List<SysUserBean>>();
        for (SysUserBean user : users) {
            if (!map.containsKey(user.getDeptId()))
                map.put(user.getDeptId(), new ArrayList<SysUserBean>());
            map.get(user.getDeptId()).add(user);
        }
        return map;
    }

    /**
     * * 查询所在机构用户数据
     * <p/>
     * 不包含删除和停用用户
     */
    private List<SysUser> getOnDeptUserList(int deptId) {
        List<SysUser> userList = new ArrayList<SysUser>();
        List<SysUser> users = getAllUserForCache();
        if (users != null && users.size() > 0) {
            for (SysUser user : users) {
                if (user.getIsDel().equals(0) && user.getIsActived().equals(1) && user.getDeptId().equals(deptId)) {
                    userList.add(user);
                }

            }
        }
        return userList;
    }

    /*****************************缓存处理***********************/

    /**
     * 查询admin所管理的机构树 List
     */
    public List<SysDept> getAdminInCharegeDeptTreeList() {
        return deptDao.getAllDeptForCache();
    }

    /**
     * 查询机构管理员所管理的机构树 List
     */
    public List<SysDept> getDeptAdminInCharegeDeptTreeList() {
        return getDeptsForManger(2, getLoginUserId());

    }


    /**
     * 查询业务主管所负责的机构 List
     */
    public List<SysDept> getBusinessManagerInCharegeDeptTreeList() {
        return getDeptsForManger(3, getLoginUserId());
    }

    /**
     * 查询业务主管所负责的机构 List
     */
    public List<SysDept> getBusinessManagerInCharegeDeptTreeList(int userId) {
        return getDeptsForManger(3, userId);

    }

    /**
     * 获得登录用户(admin,机构管理员)所管理的机构树List
     */
    public List<SysDept> getInChargeOfDeptList() {
        if (this.isSystemAdmin()) return getAdminInCharegeDeptTreeList();
        if (this.isDeptAdmin()) return getDeptAdminInCharegeDeptTreeList();
        return null;
    }

    /**
     * 机构id集合
     * <p/>
     * <p/>
     * 获得登录业务主管所负责机构id集合
     *
     * @return
     */
    public Integer[] getInChargeOfDeptIds() {
        List<SysDept> depts = getBusinessManagerInCharegeDeptTreeList();
        if (depts != null && depts.size() > 0) {
            Set<Integer> set = new HashSet<Integer>();
            for (SysDept d : depts) {
                set.add(d.getDeptId());
            }
            if (set.size() > 0) {
                Integer[] deptIds = new Integer[set.size()];
                Iterator<Integer> it = set.iterator();
                int i = 0;
                while (it.hasNext()) {
                    deptIds[i] = it.next();
                    i++;
                }
                return deptIds;
            }
        }
        return null;

    }

    /**
     * 机构id集合
     * <p/>
     * <p/>
     * 获得业务主管所负责机构id 字符串
     *
     * @return
     */
    public String getInChargeOfStringDeptIds(int userId) {
        String str = "0";
        List<SysDept> depts = getBusinessManagerInCharegeDeptTreeList(userId);
        if (depts != null && depts.size() > 0) {
            for (SysDept d : depts) {
                str += d.getDeptId() + ",";
            }
        }
        if (!str.equals("0")) str = str.substring(0, str.length() - 1);
        return str;
    }

    /**
     * admin 树转换为json
     *
     * @return
     */
    public JSONArray getAdminDeptJson() {
        return this.getDeptJson(getAdminInCharegeDeptTreeList());
    }

    /**
     * 机构管理员 json树 （不添加默认根节点）
     *
     * @return
     */
    public JSONArray getDeptAdminDeptJson() {
        List<SysDept> depts = getDeptAdminInCharegeDeptTreeList();
        if (depts == null) return null;
        if (depts.size() > 0) return this.getDeptJson(depts);
        return null;

    }

    /**
     * json 树（没默认节点）
     * <p/>
     * <p/>
     * 获得登录用户(admin,机构管理员)所管理的机构树json
     */
    public JSONArray getInChargeOfDeptJson() {
        if (this.isSystemAdmin()) return getAdminDeptJson();
        if (this.isDeptAdmin()) return getDeptAdminDeptJson();
        return getAdminDeptJson();
    }

    /**
     * 业务主管 json树（不添加默认根节点）(包含没有人员的机构)
     */
    public JSONArray getBusinessManagerDeptJsonNoFilter() {
        List<SysDept> depts = getBusinessManagerInCharegeDeptTreeList();
        if (depts != null && depts.size() > 0) {
            return this.getDeptJson(depts);
        }
        return null;
    }

    /**
     * 登录业务主管 json树  (添加默认根节点)(包含没有人员的机构)
     *
     * @return
     */
    public JSONArray getBusinessManagerDeptJsonAddRoot() {
        try {
            List<SysDept> depts = getBusinessManagerInCharegeDeptTreeList();
            if (depts == null) return null;
            Set<Integer> deptNameList = new HashSet<Integer>();
            for (SysDept sysDept : depts) {
                deptNameList.add(sysDept.getDeptId());
            }
            Map<String, Object> map = new HashMap<String, Object>();
            JSONArray jsonArray = new JSONArray();
            int i = 0;
            String codes = "";
            if (depts.size() > 0) {
                for (SysDept dept : depts) {
                    map.put("id", dept.getDeptId());
                    if (!deptNameList.contains(dept.getDeptParentId())) {
                        map.put("pId", 2);
                        i++;
                        codes += dept.getDeptSearchCode() + ",";
                    } else {
                        map.put("pId", dept.getDeptParentId());
                    }
                    map.put("name", dept.getDeptName());
                    map.put("searchCode", dept.getDeptSearchCode());
                    jsonArray.add(map);
                }
                if (i >= 2) {//有虚拟节点
                    map.put("id", 2);
                    map.put("pId", 0);
                    map.put("name", EnumDept.DEPTMANROOT_NAME.getValue());
                    map.put("searchCode", codes);
                    map.put("open", true);
                    jsonArray.add(map);
                } else {
                    if (jsonArray.size() > 0) {
                        for (int j = 0; j < jsonArray.size() - 1; j++) {
                            JSONObject o = jsonArray.getJSONObject(j);
                            if (o.get("pId").equals(2)) {//根节点
                                o.put("open", true);
                            } else {
                                o.put("open", false);
                            }
                        }
                    }

                }
            }
            return jsonArray;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 树转换为json
     *
     * @return
     */
    public JSONArray getDeptJson(List<SysDept> depts) {
        try {
            if (depts == null) return null;
            Set<Integer> deptNameList = new HashSet<Integer>();
            int i = 0;
            for (SysDept sysDept : depts) {
                deptNameList.add(sysDept.getDeptId());
            }
            for (SysDept sysDept : depts) {
                if (!deptNameList.contains(sysDept.getDeptParentId())) i++;
            }
            Map<String, Object> map = new HashMap<String, Object>();
            JSONArray jsonArray = new JSONArray();
            if (depts.size() > 0) {
                for (SysDept dept : depts) {
                    map.put("id", dept.getDeptId());
                    if (!deptNameList.contains(dept.getDeptParentId())) {
                        map.put("pId", 2);
                        if (i == 1) map.put("open", true);
                    } else {
                        map.put("pId", dept.getDeptParentId());
                        map.put("open", false);
                    }
                    map.put("name", dept.getDeptName());
                    jsonArray.add(map);
                }
            }
            return jsonArray;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }


    /**
     * 业务主管下属人员List
     * <p/>
     * <p/>
     * 登录业务主管所负责机构的用户集合(不包含删除和停用用户和纯机构管理员及admin)
     */
    public List<SysUser> getBusinessManagerInCharegeOfUsers() {
        return getManagerUserList(getLoginUserId());
    }

    /**
     * 获得登录业务主管所负责机构的用户id集合(不包含删除和停用用户和纯机构管理员及admin)
     *
     * @return
     */
    public Integer[] getInChargeOfDeptUserIds() {
        List<SysUser> users = getBusinessManagerInCharegeOfUsers();
        if (users != null && users.size() > 0) {
            Integer[] userIds = new Integer[users.size() + 1];
            for (int i = 0; i < users.size(); i++) {
                userIds[i] = users.get(i).getUserId();
            }
            if (this.getUserLoginInfo().getUserId() != null) {
                userIds[users.size()] = this.getUserLoginInfo().getUserId();
            }
            return userIds;
        } else {
            Integer[] userIds = new Integer[1];
            userIds[0] = this.getUserLoginInfo().getUserId();
            return userIds;
        }
    }

    /**
     * 获得登录业务主管所负责机构的用户id集合(不包含删除和纯机构管理员及admin)
     *
     * @return
     */
    public Integer[] getInChargeOfDeptUserNotDelIds() {
        List<SysUser> users = getManagerUserListNotDel(getLoginUserId());
        if (users != null && users.size() > 0) {
            Integer[] userIds = new Integer[users.size() + 1];
            for (int i = 0; i < users.size(); i++) {
                userIds[i] = users.get(i).getUserId();
            }
            if (this.getUserLoginInfo().getUserId() != null) {
                userIds[users.size()] = this.getUserLoginInfo().getUserId();
            }
            return userIds;
        } else {
            Integer[] userIds = new Integer[1];
            userIds[0] = this.getUserLoginInfo().getUserId();
            return userIds;
        }
    }

    /**
     * 根据用户ID获得用户的角色ids
     */
    public Integer[] getRoleIds(int userId) {
        try {
            HashMap<String, Object> pc = new HashMap<String, Object>();
            pc.put("USER_ID", userId);
            List<SysRoleMember> lsr = sysRoleMemberDao.getSysRoleMemberList("GetRoleMember", pc);
            if (lsr != null && lsr.size() > 0) {
                Integer[] roleIds = new Integer[lsr.size()];
                for (int i = 0; i < lsr.size(); i++) {
                    roleIds[i] = lsr.get(i).getRoleId();
                }
                return roleIds;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 查询所在机构用户数据
     * <p/>
     * 不包含删除和停用用户
     */
    public List<SysUser> searchUser(int deptId) {
        return getOnDeptUserList(deptId);
    }


    /**
     * 用户集合
     * <p/>
     * <p/>
     * 登录业务主管所负责机构的用户集合(包含删除和停用用户，不包含纯机构管理员和admin)
     *
     * @return
     */
    public List<SysUser> getInChargeOfUserList() {
        return getManagerUserListContainDel(getLoginUserId());
    }

    /**
     * 用户id集合
     * <p/>
     * <p/>
     * 登录业务主管所负责机构的用户id集合(包含删除和停用用户，不包含纯机构管理员和admin)
     *
     * @return
     */
    public Integer[] getManagerInChargeOfUserIds() {
        List<SysUser> users = getInChargeOfUserList();
        if (users != null && users.size() > 0) {
            Integer[] userIds = new Integer[users.size()];
            for (int i = 0; i < users.size(); i++) {
                userIds[i] = users.get(i).getUserId();
            }
            return userIds;
        } else {
            return null;
        }
    }


    /**
     * 查询同级机构及上级机构集合(去除没有业务主管的机构)
     */
    private List<SysDept> getExamineDeptList() {
        int deptParentId = deptDao.getDeptById(this.getUserLoginInfo().getDeptId()).getDeptParentId();
        return deptDao.getExamineDeptList(deptParentId);
    }

    public List<SysDept> getExamineCommonList(SysUser sysUser) {
        List<SysDept> list = new ArrayList<SysDept>();
        int deptId = sysUser.getDeptId();
        String deptCode = deptDao.getDeptById(deptId).getDeptSearchCode();
        int j = deptCode.length() / 3;
        for (int i = 0; i < j; i++) {
            String code = deptCode.substring(0, deptCode.length() - 3 * i);
            SysDept dept = deptDao.getExamineCommonList(code);
            if (dept != null) {
                list.add(dept);
            }
        }
        return list;
    }

    /**
     * 客户经理短信彩信审核人
     */
    private List<SysDept> getExamineCommonList() {
        List<SysDept> list = new ArrayList<SysDept>();
        int deptId = this.getUserLoginInfo().getDeptId();
        String deptCode = deptDao.getDeptById(deptId).getDeptSearchCode();
        int j = deptCode.length() / 3;
        for (int i = 0; i < j; i++) {
            String code = deptCode.substring(0, deptCode.length() - 3 * i);
            SysDept dept = deptDao.getExamineCommonList(code);
            if (dept != null) {
                list.add(dept);
                break;
            }
        }
        return list;
    }

    /**
     * 同级机构及上级机构json(短信彩信审核人)
     */
    public JSONArray getExamineDeptJson() {
        if (isInChargeOfDepartment()) return this.getDeptJson(getExamineDeptList());//业务主管
        else if (isCommon()) return this.getDeptJson(getExamineCommonList());//客户经理
        else return null;
    }

    /**
     * 业务主管根据用户userId获取所管理的部门id集合
     *
     * @return
     */
    public Integer[] getInChargeOfDeptIds(int userId) {
        List<SysDept> deptList = getDeptsForManger(3, userId);
        if (deptList != null && deptList.size() > 0) {
            Integer[] deptIds = new Integer[deptList.size()];
            int i = 0;
            for (SysDept sysDept : deptList) {
                deptIds[i++] = sysDept.getDeptId();
            }
            return deptIds;
        }
        return null;
    }

    /**
     * 根据业务主管用户userId获取所负责的部门用户的id集合(不包含删除和停用)
     *
     * @return
     */
    public Integer[] getInChargeOfDeptUserIds(int userId) {
        List<SysUser> users = getManagerUserList(userId);
        if (users != null && users.size() > 0) {
            Integer[] userIds = new Integer[users.size()];
            for (int i = 0; i < users.size(); i++) {
                userIds[i] = users.get(i).getUserId();
            }
            return userIds;
        }
        return null;
    }


    /**
     * 获得登录业务主管所管理的部门用户id集合
     *
     * @return flag 为true是包含删除停用用户，不包含纯机构管理员和admin
     *         为false则不包含删除停用用户和纯机构管理员及admin
     */
    public Integer[] getInChargeOfDeptUserIds(boolean flag) {
        if (flag) return getManagerInChargeOfUserIds();
        else return getInChargeOfDeptUserIds();

    }

    /**
     * 获得所在机构及下属机构 和 人员list
     */
    public List<CusBelongToBean> getBelongToUserAndDeptTreeList(String deptIds) {
        return getCusBelongBeanList(deptIds);
    }

    /**
     * 查询总行机构及下属人员列表
     */
    public List<CusBelongToBean> getBelongToUserAll() {
        return getAllCusBelongBeanList();
    }

    /**
     * 查询管理的根机构serachKey
     */
    private List<String> getInCharegeSearchKey(Map<String, Object> map) {
        List<String> keys = deptDao.getInCharegeSearchKey(map);
        if (keys == null) return null;
        return keys;
    }

    /**
     * 根据登陆的机构管理员获取所 管理的部门用户数
     *
     * @return
     */
    public Integer[] getInChargeOfDeptUsersCount() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", getLoginUserId());
        map.put("roleId", 2);
        List<String> keys = this.getInCharegeSearchKey(map);
        if (keys != null && keys.size() > 0) {
            String codes = "";
            for (String key : keys) {
                codes += "DEPT_SEARCH_CODE like" + " " + "'" + key + "%" + "'" + " " + "or" + " ";
            }
            codes = "( " + codes.substring(0, codes.lastIndexOf("or")) + " )";
            Map<String, Object> hmap = new HashMap<String, Object>();
            hmap.put("searchKey", codes);
            hmap.put("isDel", 0);
            hmap.put("noDeptAdmin", "0");
            List<SysUser> userList = userDao.getInChargeDeptUsers(hmap);
            if (userList != null && userList.size() > 0) {
                Integer[] userIds = new Integer[userList.size()];
                for (int i = 0; i < userList.size(); i++) {
                    userIds[i] = userList.get(i).getUserId();
                }
                return userIds;
            }
        }
        return null;
    }

    /**
     * 根据deptId查询下属机构ids
     */
    public String getInChargeDeptIdsByDeptId(int deptId) {
        String ids = "";
        Map<Integer, List<SysDept>> map = getAllDeptForMap();
        if (map.containsKey(deptId)) {
            List<SysDept> depts = map.get(deptId);
            if (depts != null && depts.size() > 0) {
                for (SysDept sysDept : depts) {
                    ids += sysDept.getDeptId() + ",";
                }
                ids = ids.substring(0, ids.length() - 1);
                return ids;
            }
        }
        return null;
    }


    /**
     * deptIds 根机构id字符串
     * <p/>
     * 根据deptId字符串的到机构集合(包含下属)
     */
    public List<SysDept> getContainDeptListByDeptIds(String deptIds) {
        List<SysDept> deptList = new ArrayList<SysDept>();
        if (deptIds != null && !deptIds.equals("")) {
            String[] ids = deptIds.split(",");
            Map<Integer, List<SysDept>> map = getAllDeptForMap();
            for (String id : ids) {
                Integer deptId = Integer.valueOf(id);
                if (map.containsKey(deptId)) {
                    List<SysDept> depts = map.get(deptId);
                    for (SysDept sysDept : depts) {
                        deptList.add(sysDept);
                    }
                }
            }
        }
        return deptList;
    }

    /**
     * 根据deptId获得机构的完全路径
     *
     * @return
     */
    public String getDeptFullPath(int deptId) {
        String name = "";
        String codes = "";
        String deptCode = deptDao.getDeptById(deptId).getDeptSearchCode();
        int j = deptCode.length() / 3;
        for (int i = j - 1; i >= 0; i--) {
            String code = deptCode.substring(0, deptCode.length() - 3 * i);
            codes += "'" + code + "'" + ",";
        }
        codes = codes.substring(0, codes.length() - 1);
        List<String> searchCodes = deptDao.getDeptNameByDeptSearchCode(codes);
        if (searchCodes != null && searchCodes.size() > 0) {
            for (String str : searchCodes) {
                name += str + "\\";
            }
            name = name.substring(0, name.length() - 1);
        }
        return name;
    }

    /**
     * 根据deptIds查询实体集合
     */
    public List<SysDept> getDeptsByDeptIds(String ids) {
        List<SysDept> deptList = new ArrayList<SysDept>();
        List<SysDept> depts = deptDao.getAllDeptForCache();
        if (ids != null && !ids.equals("")) {
            String[] deptIds = ids.split(",");
            for (String id : deptIds) {
                for (SysDept dept : depts) {
                    if (dept.getDeptId().equals(Integer.valueOf(id))) deptList.add(dept);
                }
            }
        }
        return deptList;
    }

    /**
     * 查询业务主管所管理的用户id (包含删除和停用及自己)
     */
    public String getUserIdsBelongToManager() {
        String ids = "";
        List<SysUser> users = getManagerUserListContainDel(getLoginUserId());
        List<Integer> list = new ArrayList<Integer>();
        if (users != null && users.size() > 0) {
            for (SysUser user : users) {
                list.add(user.getUserId());
            }
        }
        if (list != null && list.size() > 0) {
            if (!list.contains(getLoginUserId())) list.add(getLoginUserId());
            for (Integer id : list) {
                ids += id + ",";
            }
            ids = ids.substring(0, ids.length() - 1);
        }
        if (StringUtil.isEmpty(ids)) {
            ids = getLoginUserId().toString();
        }
        return ids;
    }

    /**
     * 查询业务主管所管理的用户id (包含删除和停用及自己)
     */
    public String getUserIdsBelongToManager(String[] roleIds, String dataCode) {
        String roleStr = StringUtil.getIdsString(roleIds);
        boolean flag = specialDataAuthService.getSysDataAuthCondition(roleStr, dataCode);
        String ids = "";
        if (flag) {//如果没有特殊数据权限则查自己的
            ids = getLoginUserId().toString();
        } else {
            List<SysUser> users = getManagerUserListContainDel(getLoginUserId());
            List<Integer> list = new ArrayList<Integer>();
            if (users != null && users.size() > 0) {
                for (SysUser user : users) {
                    list.add(user.getUserId());
                }
            }
            if (list != null && list.size() > 0) {
                if (!list.contains(getLoginUserId())) list.add(getLoginUserId());
                for (Integer id : list) {
                    ids += id + ",";
                }
                ids = ids.substring(0, ids.length() - 1);
            }
            if (StringUtil.isEmpty(ids)) {
                ids = getLoginUserId().toString();
            }
        }
        return ids;
    }

    /**
     * 查询业务主管所管理的用户id (包含删除和停用及自己)FOR PAD
     * pad端登陆没有session,所以需要传userId进去
     */
    public String getUserIdsBelongToManagerForPAD(String[] roleIds, String dataCode, Integer userId) {
        String roleStr = StringUtil.getIdsString(roleIds);
        boolean flag = specialDataAuthService.getSysDataAuthCondition(roleStr, dataCode);
        String ids = "";
        if (flag) {//如果没有特殊数据权限则查自己的
            ids = userId.toString();
        } else {
            List<SysUser> users = getManagerUserListContainDel(userId);
            List<Integer> list = new ArrayList<Integer>();
            if (users != null && users.size() > 0) {
                for (SysUser user : users) {
                    list.add(user.getUserId());
                }
            }
            if (list != null && list.size() > 0) {
                if (!list.contains(userId)) list.add(userId);
                for (Integer id : list) {
                    ids += id + ",";
                }
                ids = ids.substring(0, ids.length() - 1);
            }
            if (StringUtil.isEmpty(ids)) {
                ids = userId.toString();
            }
        }
        return ids;
    }

    /**
     * 查询业务主管是否有特殊数据权限
     *
     * @param roleIds
     * @param dataCode 模块名如：客户信息
     *                 * @return     如果返回true说明没有勾选对应的特殊数据
     *                 如果返回false说明勾选了对应特殊数据权限。
     */
    public Boolean isSpecialData(String[] roleIds, String dataCode) {
        String roleStr = StringUtil.getIdsString(roleIds);
        return specialDataAuthService.getSysDataAuthCondition(roleStr, dataCode);
    }

    /*************************************以下缓存没处理************************/
    /**
     * 根据用户ID集合取出用户对应用户对象
     *
     * @return
     */
    public List<SysUserBean> getUsersByUserIds(String ids) {
        return userDao.getUserListByIds(ids);
    }


    /**
     * 查询客户经理及营销人员机构列表
     */
    public List<SysDept> getCounterUserDeptList() {
        return deptDao.getCounterUserDeptList();
    }

    /**
     * 营销人员 json树 （不添加默认根节点）（不包含没有人员的机构）
     */
    public JSONArray getCounterUserDeptJson() {
        List<SysDept> depts = getCounterUserDeptList();
        if (depts != null && depts.size() > 0) {
            return this.getDeptJson(depts);
        }
        return null;
    }

    /**
     * 客户经理所管理的柜台人员
     */
    public String getCounterUserBelongCommon() {
        String ids = "";
        List<Integer> list = deptDao.getCounterUserBelongCommon(getLoginUserId());
        if (list != null && list.size() > 0) {
            for (Integer id : list) {
                ids += id + ",";
            }
            ids = ids.substring(0, ids.length() - 1);
        }
        return ids;
    }

    /**
     * 客户经理所管理的柜台人员
     */
    public String getCounterUserBelongCommon(int userId) {
        String ids = "";
        List<Integer> list = deptDao.getCounterUserBelongCommon(userId);
        if (list != null && list.size() > 0) {
            for (Integer id : list) {
                ids += id + ",";
            }
            ids = ids.substring(0, ids.length() - 1);
        }
        return ids;
    }

    /**
     * 业务主管所管理的柜台人员
     */
    public String getCounterUserBelongManager() {
        String ids = "";
        String deptIds = "";
        List<SysDept> depts = getBusinessManagerInCharegeDeptTreeList();
        if (depts != null && depts.size() > 0) {
            for (SysDept dept : depts) {
                deptIds += dept.getDeptId() + ",";
            }
            deptIds = deptIds.substring(0, deptIds.length() - 1);
        }
        if (deptIds.length() > 0) {
            List<Integer> list = deptDao.getCounterUserBelongManager(deptIds);
            if (list != null && list.size() > 0) {
                for (Integer id : list) {
                    ids += id + ",";
                }
                ids = ids.substring(0, ids.length() - 1);
            }
        }
        return ids;
    }

    /**
     * 业务主管所管理的柜台人员
     */
    public String getCounterUserBelongManager(int userId) {
        String ids = "";
        String deptIds = "";
        List<SysDept> depts = getBusinessManagerInCharegeDeptTreeList(userId);
        if (depts != null && depts.size() > 0) {
            for (SysDept dept : depts) {
                deptIds += dept.getDeptId() + ",";
            }
            deptIds = deptIds.substring(0, deptIds.length() - 1);
        }
        if (deptIds.length() > 0) {
            List<Integer> list = deptDao.getCounterUserBelongManager(deptIds);
            if (list != null && list.size() > 0) {
                for (Integer id : list) {
                    ids += id + ",";
                }
                ids = ids.substring(0, ids.length() - 1);
            }
        }
        return ids;
    }

    /*******************************中信*********************************/
    /**
     * 业务主管所管辖的人员ids (不包含删除和停用)
     * userId 当前业务主管的userId
     * flag==0 下属人员（包含自己） flag==1 下属客户经理（自己又是客户经理则包含自己）
     */
    public String getInChargeOfDeptUserIds(int userId, int flag) {
        String userIds = "";//管辖的客户经理
        if (flag == 1) {
            Integer[] ids = getInChargeOfDeptUserIds(userId);//所管辖人员
            List<Integer> commonIds = userDao.getCommonUserIdList();//系统中的客户经理
            if (ids != null && ids.length > 0) {
                for (Integer id : ids) {
                    if (commonIds.contains(id)) userIds += id + ",";
                }
            }
            if (!userIds.equals("")) {
                if (isCommon(userId)) userIds = userIds + this.getLoginUserId();
                else userIds = userIds.substring(0, userIds.length() - 1);
            } else {
                if (isCommon(userId)) userIds = userIds + this.getLoginUserId();
            }
        }
        if (flag == 0) {
            Integer[] ids = getInChargeOfDeptUserIds(userId);//所管辖人员
            if (ids != null && ids.length > 0) {
                for (Integer id : ids) {
                    userIds += id + ",";
                }
                userIds = userIds + this.getLoginUserId();
            } else userIds = this.getLoginUserId() + "";
        }
        return userIds.equals("") ? "0" : userIds;
    }

    /**
     * 查询业务主管所管辖的所有包含客户经理角色的人员（包括删除，停用）
     *
     * @return
     */
    public List<SysUser> getInchargeUserAllManager() {
        List<SysUser> comUserList = new ArrayList<SysUser>();
        List<SysUser> userList = this.getInChargeOfUserList();
        List<Integer> commonIds = userDao.getCommonUserIdList();//系统中的客户经理
        for (SysUser u : userList) {
            if (commonIds.contains(u.getUserId())) {
                comUserList.add(u);
            }
        }
        return comUserList;
    }

    //业务主管下属人员(业务主管和客户经理)(包含停用 不包含删除)
    private List<SysUser> getManagerUserListNoContainDel(int userId) {
        List<SysUser> userList = new ArrayList<SysUser>();
        List<Integer> deptIds = getRootDeptIdByLogin(3, userId);
        if (deptIds != null && deptIds.size() > 0) {
            for (Integer deptId : deptIds) {
                Map<Integer, List<SysDept>> map = getAllDeptForMap();
                if (map.containsKey(deptId)) {
                    List<SysDept> depts = map.get(deptId);
                    if (depts != null && depts.size() > 0) {
                        for (SysDept d : depts) {
                            List<SysUser> users = getAllUserForCache();
                            if (users != null && users.size() > 0) {
                                for (SysUser user : users) {
                                    if (user.getIsDel() == 0 && user.getDeptId().equals(d.getDeptId())) {
                                        userList.add(user);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return userList;
    }

    //业务主管下属人员(业务主管和客户经理)(包含停用 不包含删除)
    public Integer[] getInChargeOfDeptUserIdsNoDel() {
        Integer[] str = null;
        List<SysUser> users = getManagerUserListNoContainDel(this.getLoginUserId());
        if (users != null && users.size() > 0) {
            str = new Integer[users.size()];
            for (int i = 0; i < users.size(); i++) {
                str[i] = users.get(i).getUserId();
            }
        }
        return str;
    }

    /**
     * 所有机构及人员（下属的）list，包含柜台人员
     *
     * @return
     */
    public List<CusBelongToBean> getAllCusBelongBeanCounterList(String deptIds) {
        List<CusBelongToBean> beanList = new ArrayList<CusBelongToBean>();
        CusBelongToBean bean = null;
        List<SysDept> deptList = deptDao.getAllDeptForCache();
        List<SysUserBean> users = getAllUserAndCounterUserForCache(deptIds);
        Map<Integer, List<SysUserBean>> uMap = getActivedAllUserBeanMap(users);
        for (SysDept d : deptList) {
            if (getDeptUserCount(users, d.getDeptId()) >= 1) {
                bean = new CusBelongToBean();
                bean.setId(d.getDeptId());
                bean.setTextName(d.getDeptName());
                bean.setParentId(d.getDeptParentId());
                bean.setType("D");
                bean.setDeptName(d.getDeptName());
                beanList.add(bean);
            } else {
                List<SysDept> dlist = this.getContainDeptListByDeptIds("" + d.getDeptId());
                for (SysDept sysDept : dlist) {
                    if (getDeptUserCount(users, sysDept.getDeptId()) >= 1) {
                        bean = new CusBelongToBean();
                        bean.setId(d.getDeptId());
                        bean.setTextName(d.getDeptName());
                        bean.setParentId(d.getDeptParentId());
                        bean.setType("D");
                        bean.setDeptName(d.getDeptName());
                        beanList.add(bean);
                    }
                }
            }
        }
        beanList = removeDuplicateTwo(beanList);
        for (SysDept d : deptList) {
            if (uMap.containsKey(d.getDeptId())) {
                List<SysUserBean> uList = uMap.get(d.getDeptId());
                for (SysUserBean user : uList) {
                    bean = new CusBelongToBean();
                    bean.setId(user.getUserId());
                    bean.setTextName(user.getUserName());
                    bean.setParentId(user.getDeptId());
                    bean.setType("U");
                    bean.setDeptName(d.getDeptName());
                    beanList.add(bean);
                }
            }
        }
        return beanList;
    }

    /**
     * 2、循环list中的所有元素然后删除重复
     */
    public static List<CusBelongToBean> removeDuplicateTwo(List<CusBelongToBean> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if ((list.get(j).getDeptName()).equals(list.get(i).getDeptName())) {
                    list.remove(j);
                }
            }
        }
        return list;
    }

    /**
     * 根据deptIds查询机构下的人员
     * 包含删除、停用、
     * 不包含 纯机构管理员、admin
     */
    public String getStringUserIdContainsByDeptIds(String deptIds) {
        String userIds = "";
        List<SysUser> users = getUnderDeptUserList(deptIds);
        if (users != null && users.size() > 0) {
            for (SysUser sysUser : users) {
                userIds += sysUser.getUserId() + ",";
            }
            userIds = userIds.substring(0, userIds.length() - 1);
            return userIds;
        }
        return null;
    }

    /**
     * 根据deptIds查询机构下的人员
     * 包含停用
     * 不包含删除、纯机构管理员、admin
     */
    public String getStringUserIdContainsByDeptIdsWithOutDel(String deptIds) {
        String userIds = "";
        List<SysUser> users = getUnderDeptUserListWithOutDel(deptIds);
        if (users != null && users.size() > 0) {
            for (SysUser sysUser : users) {
                userIds += sysUser.getUserId() + ",";
            }
            userIds = userIds.substring(0, userIds.length() - 1);
            return userIds;
        }
        return null;
    }

    /**
     * 获得当前登录用户直接负责的根机构Id集合
     *
     * @param userId
     * @return
     * @see com.banger.mobile.facade.dept.DeptFacadeService#getRootDeptIdByLoginId(int)
     */
    public List<Integer> getRootDeptIdByLoginId(int userId) {
        List<Integer> list = new ArrayList<Integer>();
        List<SysDeptAuth> auths = getAllDeptAuthForCache();
        for (SysDeptAuth auth : auths) {
            if (auth.getUserId().equals(userId) && auth.getRoleId().equals(3)) {
                list.add(auth.getDeptId());
            }
        }
        return list;
    }

    /**
     * 获取系统机构map缓存  key-value:deptId-->sysDept
     * @return
     */
    public Map<Integer,SysDept> getSysDeptMap(){
        Map<Integer, SysDept> map = new HashMap<Integer, SysDept>();
        List<SysDept> deptList = deptDao.getAllDeptForCache();
        for (SysDept dept : deptList) {
            if (!map.containsKey(dept.getDeptId())) {
                map.put(dept.getDeptId(), dept);
            }
        }
        return map;
    }
    /**
     * 获取系统机构map缓存  key-value:deptId-->sysDept
     * @return
     */
    public Map<Integer,SysUser> getSysUserMap(){
        Map<Integer, SysUser> map = new HashMap<Integer, SysUser>();
        List<SysUser> userList = userDao.getAllUserForCache();
        for (SysUser user : userList) {
            if (!map.containsKey(user.getUserId())) {
                map.put(user.getUserId(), user);
            }
        }
        return map;
    }
}
