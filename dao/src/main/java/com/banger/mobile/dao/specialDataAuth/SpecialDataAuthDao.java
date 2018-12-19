/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :数据权限
 * Author     :yangy
 * Create Date:2012-5-31
 */
package com.banger.mobile.dao.specialDataAuth;

import com.banger.mobile.domain.model.specialData.SpecialDataAuthBean;
import com.banger.mobile.domain.model.specialData.SpecialDataAuth;
import com.banger.mobile.domain.model.menuAuth.SysMenuAuth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangy
 * @version $Id: SpecialDataAuthDao.java,v 0.1 2012-5-31 下午1:10:14 cheny Exp $
 */
public interface SpecialDataAuthDao {

    /**
     * 获得功能权限树集合
     */
    public List<SpecialDataAuthBean> getDataAuthTree();

    /**
     * 新增角色功能权限 -->
     */
    public void insertDataAuth(SpecialDataAuth DataAuth);

    /**
     * 删除角色功能权限  -->
     */
    public void deleteDataAuth(int roleId);

    /**
     * 查看角色对应的功能权限 -->
     */
    public List<SpecialDataAuthBean> getDetailDataByRoleId(int roleId);

    /**
     * 根据roleId查询功能权限
     */
    public List<SpecialDataAuth> getDataAuthByRoleId(int roleId);

    /**
     * 根据roleId查询功能权限
     */
    public List<SpecialDataAuth> getDataAuthByRoleId(Map<String,Object> map);

    /**
     * 根据条件查询用户数据权限
     */
    public List<SpecialDataAuth> getSysDataAuthCondition(Map<String,Object> map);



    /**
     * 根据功能操作删除权限
     *
     * @param DataId
     */
    public void deleteDataAuthByDataId(int DataId);

    /**
     * 根据角色id集合查询功能id
     */
    public List<Integer> getDataIdListByRoleId(String roleIds);
}
