/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :系统设置  审批额度配置 Action
 * Author     :huangk
 * Create Date:2013-3-1
 */
package com.banger.mobile.webapp.action.loan;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.dept.DeptUserBean;
import com.banger.mobile.domain.model.loan.LnApproveLimitRole;
import com.banger.mobile.domain.model.loan.LnApproveLimitUser;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.dept.DeptService;
import com.banger.mobile.facade.loan.LnApproveLimitRoleService;
import com.banger.mobile.facade.loan.LnApproveLimitUserService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author huangk
 * @version $Id: LnApproveLimitAction.java,v 0.1 2013-3-1 下午1:09:14 huangk Exp $
 */
public class LnApproveLimitAction extends BaseAction{
	
	private static final long serialVersionUID = -5856945543940937827L;

	private LnApproveLimitRoleService lnApproveLimitRoleService;
	private LnApproveLimitUserService lnApproveLimitUserService;
	private LnApproveLimitRole lnApproveLimitRole;	//角色实体
	private LnApproveLimitUser lnApproveLimitUser;	//角色实体
	private PageUtil<LnApproveLimitRole> lnApproveLimitRoleList;
	private PageUtil<LnApproveLimitUser> LnApproveLimitUserList;
	
	private DeptFacadeService       deptFacadeService;
	private DeptService       deptService;         
	private SysUserService    sysUserService;
	
	private JSONArray              deptJson;      //机构树json
	private PageUtil<DeptUserBean> deptUserList;  //活动分页对象
	private int                    totalAmount;   //用户总记录数
	private int                    deptId;
	private int                    flagInt;
	private DeptUserBean           deptUserBean;      //列表对象
	private String				   account;		  //帐号
	private int					   roleCount;	  //角色总记录数
	
	/**
	 * 跳转到审核额度配置首页
	 * @return
	 */
	public String showLnApproveLimit(){
		try {
			request.setAttribute("isDeptAdmin", deptFacadeService.isDeptAdmin()?getLoginInfo().getAccount():"yes");
            deptJson = deptService.getAllDeptJson();
            for(int i=deptJson.size()-1;i>=0;i--){
                JSONObject obj = deptJson.getJSONObject(i);
                if(obj.get("pId").equals(2)) {
                    deptId = (Integer)obj.get("id");
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("deptId", deptId);
                    LnApproveLimitUserList = lnApproveLimitUserService.getLimitUserByDeptId(map, this.getPage());
                    totalAmount = LnApproveLimitUserList.getPage().getTotalRowsAmount();//记录的总数
                    break;
                }
            }
			 return SUCCESS;
         } catch (Exception e) {
             log.error("showLnApproveLimit action error:" + e.getMessage());
             return ERROR;
         }
	}
	
	public String getLimitUsers() {
		try {
            request.setAttribute("isDeptAdmin", deptFacadeService.isDeptAdmin()?getLoginInfo().getAccount():"yes");
            deptJson = deptService.getAllDeptJson();
            for(int i=deptJson.size()-1;i>=0;i--){
                JSONObject obj = deptJson.getJSONObject(i);
                if(obj.get("pId").equals(2)) {
                    deptId = (Integer)obj.get("id");
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("deptId", deptId);
                    LnApproveLimitUserList = lnApproveLimitUserService.getLimitUserByDeptId(map, this.getPage());
                    totalAmount = LnApproveLimitUserList.getPage().getTotalRowsAmount();//记录的总数
                    break;
                }
            }
            return SUCCESS;
        } catch (Exception e) {
            log.error("getLimitUsers action error:" + e.getMessage());
            return ERROR;
        }
    }
	
    public String getLimitUserPageList() {
        request.setAttribute("isDeptAdmin", deptFacadeService.isDeptAdmin()?getLoginInfo().getAccount():"yes");
        return this.getDeptUser();//本机构
    }
	
    /**
     * 查询本部门用户
     * @return
     */
    public String getDeptUser() {
        try {
            deptJson = deptService.getAllDeptJson();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("deptId", deptId);
            if(StringUtil.isNotEmpty(account))map.put("account",StringUtil.ReplaceSQLChar(account.trim()));
            LnApproveLimitUserList = lnApproveLimitUserService.getLimitUserByDeptId(map, this.getPage());
            totalAmount = LnApproveLimitUserList.getPage().getTotalRowsAmount();//记录的总数
            return SUCCESS;
        } catch (Exception e) {
            log.error("getDeptUser action error:" + e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 根据id修改审核额度（用户）
     */
    public void updateLimitUserById(){
    	try {
			String id = request.getParameter("id");
			String value = request.getParameter("value");
			String userId = request.getParameter("userId");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("value", value);
			if(lnApproveLimitUserService.getUserSetCountByUserId(Integer.parseInt(userId))==0){//根据userId判断数据库里是否存有，大于0则是修改，否则添加（用此判断主要解决页面上值改变后页面不刷新时多次改变用时方便）
				lnApproveLimitUser = new LnApproveLimitUser();
				lnApproveLimitUser.setUserId(Integer.parseInt(userId));
				BigDecimal bd=new BigDecimal(value); 
				lnApproveLimitUser.setLimitMoney(bd);
				lnApproveLimitUser.setCreateUser(this.getLoginInfo().getUserId());
				lnApproveLimitUser.setUpdateUser(this.getLoginInfo().getUserId());
				lnApproveLimitUserService.saveLimitUser(lnApproveLimitUser);
			}else{
				map.put("id", id);
				lnApproveLimitUserService.updateLimitUserById(map);
			}
		} catch (Exception e) {
			log.error("updateLimitUserById action error:" + e.getMessage());
		}
    }
    
	/**
	 * 查询角色配置
	 * @return
	 */
	public String getLimitRoles(){
		try {   
			Map<String, Object> map = new HashMap<String, Object>();
			lnApproveLimitRoleList = lnApproveLimitRoleService.queryRoleSet(map, this.getPage());
			roleCount = lnApproveLimitRoleList.getPage().getTotalRowsAmount();
			request.setAttribute("roleCount", roleCount);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}
	
	/**
     * 根据id修改审核额度（角色）
     */
    public void updateLimitRoleById(){
    	try {
			String id = request.getParameter("id");
			String value = request.getParameter("value");
			String roleId = request.getParameter("roleId");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("value", value);
			if(lnApproveLimitRoleService.getRoleSetCountByRoleId(Integer.parseInt(roleId))==0){//根据roleId判断数据库里是否存有，大于0则是修改，否则添加（用此判断主要解决页面上值改变后页面不刷新时多次改变用时方便）
				lnApproveLimitRole = new LnApproveLimitRole();
				lnApproveLimitRole.setRoleId(Integer.parseInt(roleId));
				BigDecimal bd=new BigDecimal(value); 
				lnApproveLimitRole.setLimitMoney(bd);
				lnApproveLimitRole.setCreateUser(this.getLoginInfo().getUserId());
				lnApproveLimitRole.setUpdateUser(this.getLoginInfo().getUserId());
				lnApproveLimitRoleService.saveLimitRole(lnApproveLimitRole);
			}else{
				map.put("id", id);
				lnApproveLimitRoleService.updateLimitRoleById(map);
			}
		} catch (Exception e) {
			log.error("updateLimitRoleById action error:" + e.getMessage());
		}
    }
	
	
	public void setLnApproveLimitRoleService(
			LnApproveLimitRoleService lnApproveLimitRoleService) {
		this.lnApproveLimitRoleService = lnApproveLimitRoleService;
	}
	public void setLnApproveLimitUserService(
			LnApproveLimitUserService lnApproveLimitUserService) {
		this.lnApproveLimitUserService = lnApproveLimitUserService;
	}

	public LnApproveLimitRole getLnApproveLimitRole() {
		return lnApproveLimitRole;
	}

	public void setLnApproveLimitRole(LnApproveLimitRole lnApproveLimitRole) {
		this.lnApproveLimitRole = lnApproveLimitRole;
	}

	public PageUtil<LnApproveLimitRole> getLnApproveLimitRoleList() {
		return lnApproveLimitRoleList;
	}

	public void setLnApproveLimitRoleList(
			PageUtil<LnApproveLimitRole> lnApproveLimitRoleList) {
		this.lnApproveLimitRoleList = lnApproveLimitRoleList;
	}

	public DeptFacadeService getDeptFacadeService() {
		return deptFacadeService;
	}

	public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
		this.deptFacadeService = deptFacadeService;
	}

	public DeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public SysUserService getSysUserService() {
		return sysUserService;
	}

	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	public JSONArray getDeptJson() {
		return deptJson;
	}

	public void setDeptJson(JSONArray deptJson) {
		this.deptJson = deptJson;
	}

	public PageUtil<DeptUserBean> getDeptUserList() {
		return deptUserList;
	}

	public void setDeptUserList(PageUtil<DeptUserBean> deptUserList) {
		this.deptUserList = deptUserList;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public LnApproveLimitRoleService getLnApproveLimitRoleService() {
		return lnApproveLimitRoleService;
	}

	public LnApproveLimitUserService getLnApproveLimitUserService() {
		return lnApproveLimitUserService;
	}

	public int getFlagInt() {
		return flagInt;
	}

	public void setFlagInt(int flagInt) {
		this.flagInt = flagInt;
	}

	public DeptUserBean getDeptUserBean() {
		return deptUserBean;
	}

	public void setDeptUserBean(DeptUserBean deptUserBean) {
		this.deptUserBean = deptUserBean;
	}

	public LnApproveLimitUser getLnApproveLimitUser() {
		return lnApproveLimitUser;
	}

	public void setLnApproveLimitUser(LnApproveLimitUser lnApproveLimitUser) {
		this.lnApproveLimitUser = lnApproveLimitUser;
	}

	public PageUtil<LnApproveLimitUser> getLnApproveLimitUserList() {
		return LnApproveLimitUserList;
	}

	public void setLnApproveLimitUserList(
			PageUtil<LnApproveLimitUser> lnApproveLimitUserList) {
		LnApproveLimitUserList = lnApproveLimitUserList;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getRoleCount() {
		return roleCount;
	}

	public void setRoleCount(int roleCount) {
		this.roleCount = roleCount;
	}
	
	
}
