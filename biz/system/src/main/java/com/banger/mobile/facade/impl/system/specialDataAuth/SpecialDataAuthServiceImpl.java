/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :特殊数据权限...
 * Author     :yangy
 * Create Date:2012-5-31
 */
package com.banger.mobile.facade.impl.system.specialDataAuth;

import com.banger.mobile.dao.specialDataAuth.SpecialDataAuthDao;
import com.banger.mobile.domain.model.specialData.SpecialDataAuthBean;
import com.banger.mobile.domain.model.specialData.SpecialDataAuth;
import com.banger.mobile.domain.model.menuAuth.SysMenuAuth;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.facade.specialDataAuth.SpecialDataAuthService;
import net.sf.json.JSONArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yang
 * @version $Id: SpecialDataAuthServiceImpl.java,v 0.1 2012-5-31 下午1:58:09 yangy Exp $
 */
public class SpecialDataAuthServiceImpl implements SpecialDataAuthService {

    private SpecialDataAuthDao specialDataAuthDao;

    public SpecialDataAuthDao getSpecialDataAuthDao() {
        return specialDataAuthDao;
    }

    public void setSpecialDataAuthDao(SpecialDataAuthDao specialDataAuthDao) {
        this.specialDataAuthDao = specialDataAuthDao;
    }

    /**
     * 获得功能权限树集合
     */
    public List<SpecialDataAuthBean> getDataAuthTree() {
        return specialDataAuthDao.getDataAuthTree();
    }

    /**
     * 树转换为json
     *
     * @return
     */
    public JSONArray getAllDataJson() {
        Map<String, Object> map = new HashMap<String, Object>();
        JSONArray jsonArray = new JSONArray();
        List<SpecialDataAuthBean> beans = this.getDataAuthTree();
        if (beans.size() > 0) {
            for (SpecialDataAuthBean Data : beans) {
                map.put("id", Data.getDataId());
                map.put("pId", Data.getDataParentId());
                map.put("name", Data.getDataName());
                map.put("authType", Data.getType());
                if (Data.getDataParentId().equals(0)) {
                    map.put("open", true);
                }
                jsonArray.add(map);
            }
        }
        return jsonArray;
    }

    /**
     * 新增角色功能权限 -->
     */
    public void insertDataAuth(SpecialDataAuth DataAuth) {
        specialDataAuthDao.insertDataAuth(DataAuth);
    }

    /**
     * 删除角色功能权限  -->
     */
    public void deleteDataAuth(int roleId) {
        specialDataAuthDao.deleteDataAuth(roleId);
    }

    public List<SpecialDataAuth> getDataAuthByMap(Map<String, Object> map) {
        return specialDataAuthDao.getDataAuthByRoleId(map);
    }

    /**
     * 根据功能操作删除权限
     *
     * @param DataId
     */
    public void deleteDataAuthByDataId(int DataId) {
        specialDataAuthDao.deleteDataAuthByDataId(DataId);
    }

    /**
     * 修改功能权限
     */
    public void updateDataAuth(int roleId, List<Integer> DataIds, int userId) {
        List<SpecialDataAuth> list = this.getDataAuthByRoleId(roleId);
        SpecialDataAuth DataAuth = new SpecialDataAuth();
        if (list.size() > 0) {
            //原来权限没有的要新增
            for (Integer DataId : DataIds) {
                boolean flag = false;
                for (SpecialDataAuth sysDataAuth : list) {
                    if (sysDataAuth.getDataId().equals(DataId)) {
                        flag = true;
                    }
                }
                if (!flag) {
                    DataAuth.setRoleId(roleId);
                    DataAuth.setDataId(DataId);
                    DataAuth.setCreateUser(userId);
                    this.insertDataAuth(DataAuth);
                }
            }
            //删除修改前的权限
            for (SpecialDataAuth sysDataAuth : list) {
                DataAuth = sysDataAuth;
                boolean flag = false;
                for (Integer DataId : DataIds) {
                    if (sysDataAuth.getDataId().equals(DataId)) {
                        flag = true;
                    }
                }
                if (!flag) {
                    deleteDataAuthByDataId(DataAuth.getDataAuthId());
                }
            }
        } else {
            //数据库里面没有全部新增
            for (Integer DataId : DataIds) {
                DataAuth.setCreateUser(userId);
                DataAuth.setDataId(DataId);
                DataAuth.setRoleId(roleId);
                this.insertDataAuth(DataAuth);
            }
        }


    }

    /**
     * 根据roleId查询功能权限
     */
    public List<SpecialDataAuth> getDataAuthByRoleId(int roleId) {
        return specialDataAuthDao.getDataAuthByRoleId(roleId);
    }

    /**
     * 查看角色对应的功能权限 -->
     */
    public List<SpecialDataAuthBean> getDetailDataByRoleId(int roleId) {
        return specialDataAuthDao.getDetailDataByRoleId(roleId);
    }

    /**
     * 是否有此数据权限
     *
     * @param roleIds  角色集合
     * @param dataCode 模块类型
     * @return
     */
    public Boolean getSysDataAuthCondition(String roleIds, String dataCode) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roleIds", roleIds);
        map.put("dataCode", dataCode);
        return specialDataAuthDao.getSysDataAuthCondition(map).size() > 0 ? false : true;
    }

    /**
     * 查看角色功能权限树
     */
    public JSONArray getRoleDetailDataJson(int roleId) {
        Map<String, Object> map = new HashMap<String, Object>();
        JSONArray jsonArray = new JSONArray();
        List<SpecialDataAuthBean> beans = this.getDetailDataByRoleId(roleId);
        if (beans.size() > 0) {
            for (SpecialDataAuthBean Data : beans) {
                map.put("id", Data.getDataId());
                map.put("pId", Data.getDataParentId());
                map.put("name", Data.getDataName());
                map.put("authType", Data.getType());
                if (Data.getDataParentId().equals(0)) {
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
    public List<Integer> getTreeNodeIds(int roleId) {
        List<Integer> nodeIds = new ArrayList<Integer>();
        List<SpecialDataAuthBean> beans = this.getDetailDataByRoleId(roleId);
        for (int i = 0; i < beans.size(); i++) {
            nodeIds.add(beans.get(i).getDataId());
        }
        return nodeIds;
    }


    /**
     * 登录用户返回功能操作的id集合
     */
    public String[] getDataIdsByRolesIds(String[] roleIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        for (String roleId : roleIds) {
            List<SpecialDataAuthBean> beans = this.getDetailDataByRoleId(Integer.valueOf(roleId));
            if (beans.size() > 0) {
                for (SpecialDataAuthBean SpecialDataAuthBean : beans) {
//                        if (SpecialDataAuthBean.geturl() != null && !SpecialDataAuthBean.geturl().equals("")) {
//                            map.put(SpecialDataAuthBean.getFuncId().toString(), 1);
//                        }
                }
            }
        }
        String[] DataIds = new String[map.size()];
        int i = 0;
        for (String str : map.keySet()) {
            DataIds[i++] = str;
        }
        return DataIds;
    }


    /**
     * 获得登录信息
     *
     * @return
     */
    private IUserInfo getLoginUserInfo() {
        HttpServletRequest req = org.apache.struts2.ServletActionContext.getRequest();
        HttpSession session = req.getSession();
        return (IUserInfo) session.getAttribute("LoginInfo");
    }

    /**
     * 获得角色id集合
     *
     * @return
     */
    private String getRoleIdStrings() {
        String ids = "";
        String[] roles = getLoginUserInfo().getRoles();
        if (roles != null && roles.length > 0) {
            for (String id : roles) {
                ids += id + ",";
            }
            ids = ids.substring(0, ids.length() - 1);
        }
        return ids;
    }


}

