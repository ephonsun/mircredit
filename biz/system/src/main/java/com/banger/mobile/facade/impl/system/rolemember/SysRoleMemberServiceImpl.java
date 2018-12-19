/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :系统角色管理类...
 * Author     :yangy
 * Create Date:2012-5-28
 */
package com.banger.mobile.facade.impl.system.rolemember;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.banger.mobile.dao.role.SysRoleDao;
import com.banger.mobile.dao.user.SysRoleMemberDao;
import com.banger.mobile.domain.model.role.SysRole;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.domain.model.user.SysRoleMember;
import com.banger.mobile.facade.rolemember.SysRoleMemberService;

/**
 * @author yangyang
 * @version $Id: SysRoleMemberServiceImpl.java,v 0.1 2012-5-28 下午3:23:33 yangyong Exp $
 */
public class SysRoleMemberServiceImpl implements SysRoleMemberService {

    private SysRoleMemberDao sysRoleMemberDao;
    private SysRoleDao sysRoleDao;
    
    public void setSysRoleMemberDao(SysRoleMemberDao sysRoleMemberDao) {
        this.sysRoleMemberDao = sysRoleMemberDao;
    }
    

    public void setSysRoleDao(SysRoleDao sysRoleDao) {
        this.sysRoleDao = sysRoleDao;
    }


    /**
     *  添加一条用户角色信息
     * @param sysRoleMember
     * @see com.banger.mobile.facade.rolemember.SysRoleMemberService#addSysRoleMember(com.banger.mobile.domain.model.user.SysRoleMember)
     */
    public void addSysRoleMember(SysRoleMember sysRoleMember) {
        sysRoleMember.setCreateDate(new Timestamp(new Date().getTime()));
        sysRoleMember.setCreateUser(1);
        sysRoleMemberDao.addSysRoleMember(sysRoleMember);
    }

    /**
     *  更新用户角色信息
     * @param userid
     * @param roleids
     * @param createUserId
     * @see com.banger.mobile.facade.rolemember.SysRoleMemberService#sysRoleMemberUpdateRole(java.lang.Integer, java.lang.String, java.lang.Integer)
     */
    public void sysRoleMemberUpdateRole(Integer userid, String roleids, Integer createUserId) {
        HashMap<String, Object> pc=new HashMap<String, Object>();
        pc.put("USER_ID", userid);
        List<SysRoleMember> lsr=sysRoleMemberDao.getSysRoleMemberList("GetRoleMember", pc);
        String[] newrole=roleids.split(",");
        SysRoleMember srm=null;
        SysRole sysRole=null;
        if(lsr.size()>0){
            //原来角色中不包含的则新增
            for (String roleid : newrole) {
                sysRole=new SysRole();
                srm= new SysRoleMember();
                sysRole.setRoleId(Integer.parseInt(roleid));
                sysRole=sysRoleDao.getSysRoleById(sysRole);
                boolean found=false;
                for (SysRoleMember sysRm : lsr) {
                    if(sysRole.getRoleId().equals(""+sysRm.getRoleId())){
                        found=true;
                    }
                }
                if(!found){
                  srm.setRoleId(sysRole.getRoleId());
                  srm.setUserId(userid);
                  srm.setCreateUser(createUserId);
                  srm.setUpdateUser(createUserId);
                  sysRoleMemberDao.addSysRoleMember(srm);  
                }
            }
             //删除不包含的新角色。
            for (SysRoleMember sysRm :  lsr) {
                srm=sysRm;
                boolean found=false;
                for (String oldrole : newrole) {
                    sysRole=new SysRole();
                    sysRole.setRoleId(Integer.parseInt(oldrole));
                    sysRole=sysRoleDao.getSysRoleById(sysRole);
                    if(sysRole.getRoleId().equals(""+srm.getRoleId())){
                        found=true;
                    }
                }
                if(!found){
                    if(getUserLoginInfo().getAccount().equals("admin")){
                        sysRoleMemberDao.deleteSysRoleMember(srm.getRoleMemberId());
                    }else{
                        if(!sysRm.getRoleId().equals(2)){
                            sysRoleMemberDao.deleteSysRoleMember(srm.getRoleMemberId());
                        }
                    }  
                }
            }
            
        }else{
            for (String temp : newrole) {
                srm= new SysRoleMember();
                sysRole=new SysRole();
                sysRole.setRoleId(Integer.parseInt(temp));
                sysRole=sysRoleDao.getSysRoleById(sysRole);
                srm.setRoleId(sysRole.getRoleId());
                srm.setUserId(userid);
                srm.setCreateUser(createUserId);
                srm.setUpdateUser(createUserId);
                sysRoleMemberDao.addSysRoleMember(srm);  
            }
        }
    }
    /**
     * 获得登录信息
     * @return
     */
    private IUserInfo getUserLoginInfo() {
        HttpServletRequest req = org.apache.struts2.ServletActionContext.getRequest();
        HttpSession session = req.getSession();
        return (IUserInfo) session.getAttribute("LoginInfo");
    }


    /**
     * 
     * @param userId
     * @see com.banger.mobile.facade.rolemember.SysRoleMemberService#delSysRoleMember(java.lang.Integer)
     */
    public void delSysRoleMember(Integer userId) {
        sysRoleMemberDao.delSysRoleMember(userId);
    }


	@Override
	public List<Integer> getRoleIdByUserIds(String userIds) {
		// TODO Auto-generated method stub
		return sysRoleMemberDao.getRoleIdByUserIds(userIds);
	}
    @Override
    public Integer getRoleIdByUserId(Integer userId){
        return sysRoleMemberDao.getRoleIdByUserId(userId);
    }

	@Override
	public List<Integer> getUserIdByRole(String roleId,String approvalValue) {
		// TODO Auto-generated method stub
		return sysRoleMemberDao.getUserIdByRole(roleId,approvalValue);
	}
    
	
}
