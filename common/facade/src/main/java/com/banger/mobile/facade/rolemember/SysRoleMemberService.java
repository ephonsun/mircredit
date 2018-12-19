/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :角色管理...
 * Author     :yangy
 * Create Date:2012-5-28
 */
package com.banger.mobile.facade.rolemember;

import java.util.List;

import com.banger.mobile.domain.model.user.SysRoleMember;

/**
 * @author yangyang
 * @version $Id: SysRoleMemberService.java,v 0.1 2012-5-28 下午3:21:29 yangyong Exp $
 */
public interface SysRoleMemberService {

     /**
     * 添加一条用户角色信息
     * @param recordInfo
     */
    public void addSysRoleMember(SysRoleMember sysRoleMember);

    /**
     * 彻底删除用户角色信息
     * @param recordInfo
     */
    public void delSysRoleMember(Integer userId);
    
    /**
     * 更新用户角色信息
     * @param userid
     * @param roleids
     * @param createUserId
     */
    public void sysRoleMemberUpdateRole(Integer userid,String roleids,Integer createUserId);
    /**
     * 根据用户编号 返回角色编号列表
     * @return
     */
    public List<Integer> getRoleIdByUserIds(String userIds);

    public Integer getRoleIdByUserId(Integer userId);
    
    /**
     * 根据角色编号，返回用户编号列表
     * 
     * @return
     */
    //public List<Integer> getUserIdByRole(String roleId);
    public List<Integer> getUserIdByRole(String roleId,String approvalValue);
}
