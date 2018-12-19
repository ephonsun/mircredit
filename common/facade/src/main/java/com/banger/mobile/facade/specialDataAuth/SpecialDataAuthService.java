/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :数据权限...
 * Author     :yangy
 * Create Date:2012-5-31
 */
package com.banger.mobile.facade.specialDataAuth;

import com.banger.mobile.domain.model.specialData.SpecialDataAuthBean;
import com.banger.mobile.domain.model.specialData.SpecialDataAuth;
import com.banger.mobile.domain.model.menuAuth.SysMenuAuth;
import net.sf.json.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangy
 * @version $Id: SpecialDataAuthService.java,v 0.1 2012-5-31 下午1:56:38 yangy Exp $
 */
public interface SpecialDataAuthService {

    /**
     * 获得数据权限树集合
     */
    public List<SpecialDataAuthBean> getDataAuthTree();

    /**
     * 权限树转换为json
     *
     * @return
     */
    public JSONArray getAllDataJson();

    /**
     * 新增角色数据权限 -->
     */
    public void insertDataAuth(SpecialDataAuth DataAuth);

    /**
     * 删除角色数据权限  -->
     */
    public void deleteDataAuth(int roleId);

    /**
     * 根据roleId查询数据权限
     *
     * @param map 角色ID集合,业务模块
     * @return
     */
    public List<SpecialDataAuth> getDataAuthByMap(Map<String, Object> map);

    /**
     * 根据roleId查询数据权限
     *
     * @param roleId 角色ID
     * @return
     */
    public List<SpecialDataAuth> getDataAuthByRoleId(int roleId);

    /**
     * 修改数据权限
     */
    public void updateDataAuth(int roleId, List<Integer> DataIds, int userId);

    /**
     * 查看角色对应的数据权限 -->
     */
    public List<SpecialDataAuthBean> getDetailDataByRoleId(int roleId);

    /**
     *  根据条件查询用户数据权限
     * @param roleIds  用户的角色集合
     * @param dataCode 不同模块的数据编号
     * @return     如果返回true说明没有勾选对应的特殊数据
                   如果返回false说明勾选了对应特殊数据权限。
     */

    public Boolean getSysDataAuthCondition(String roleIds,String dataCode);

    /**
     * 查看角色数据权限树
     */
    public JSONArray getRoleDetailDataJson(int roleId);

    /**
     * 返回角色对应数据权限节点的id集合
     */
    public List<Integer> getTreeNodeIds(int roleId);

    /**
     * 登录用户返回数据操作的id集合
     */
    public String[] getDataIdsByRolesIds(String[] roleIds);

    /**
     * 根据数据操作删除权限
     *
     * @param DataId
     */
    public void deleteDataAuthByDataId(int DataId);
}
