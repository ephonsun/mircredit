/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :用户记录实体数据实现
 * Author     :yangy
 * Create Date:2012-5-17
 */
package com.banger.mobile.dao.user.ibatis;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.user.SysUserDao;
import com.banger.mobile.domain.model.user.SysTalkUserBean;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.domain.model.user.SysUserBean;
import com.banger.mobile.domain.model.user.UserRoleName;
import com.banger.mobile.ibatis.GenericDaoiBatis;


import org.apache.log4j.Logger;

/**
 * @author Administrator
 * @version $Id: SysUserDaoiBatis.java,v 0.1 2012-5-17 上午11:57:19 Administrator Exp $
 */
@SuppressWarnings("rawtypes")
public class SysUserDaoiBatis  extends GenericDaoiBatis  implements SysUserDao {
    private static final Logger logger = Logger.getLogger(SysUserDaoiBatis.class);

    @SuppressWarnings("unchecked")
    public SysUserDaoiBatis(Class persistentClass) {
        super(SysUser.class);
    }

    @SuppressWarnings("unchecked")
    public SysUserDaoiBatis() {
        super(SysUser.class);
    }

    /**
     * @param sysUser
     * @see com.banger.mobile.dao.user.SysUserDao#addSysUser(com.banger.mobile.domain.model.user.SysUser)
     */
    public void addSysUser(SysUser sysUser) {    	
        this.getSqlMapClientTemplate().insert("addSysUser",sysUser);
    }

    /**
     * @param id
     * @see com.banger.mobile.dao.user.SysUserDao#deleteSysUser(int)
     */
    public void deleteSysUser(int id) {

        this.getSqlMapClientTemplate().delete("deleteSysUserById",id);
    }

    /**
     * @param sysUser
     * @see com.banger.mobile.dao.user.SysUserDao#updateSysUser(com.banger.mobile.domain.model.user.SysUser)
     */
    public void updateSysUser(SysUser sysUser) {
        this.getSqlMapClientTemplate().update("updateSysUser",sysUser);
    }

    /**
     * @param id
     * @return
     * @see com.banger.mobile.dao.user.SysUserDao#getSysUserById(int)
     */
    public SysUser getSysUserById(int id) {
        return (SysUser) this.getSqlMapClientTemplate().queryForObject("getSysUserById", id);
    }

    /**
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.dao.user.SysUserDao#getSysUserPage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<SysUserBean> getSysUserPage(Map<String, Object> parameters, Page page) {
        @SuppressWarnings("unchecked")
        ArrayList<SysUserBean> list = (ArrayList<SysUserBean>) this.findQueryPage(
                "getSysUserParameterPageMap", "getSysUserCount", parameters, page);
        if (list == null) {
            list = new ArrayList<SysUserBean>();
        }
        return new PageUtil<SysUserBean>(list, page);
    }
    /**
     * 统计记录条数
     * @param str
     * @param codition
     * @return
     * @see com.banger.mobile.dao.user.SysUserDao#getQueryCount(java.lang.String, java.lang.Object)
     */
    public Integer getQueryCount(String str,  Map<String, Object> parameters) {
        return (Integer)this.getSqlMapClientTemplate().queryForObject(str, parameters);
    }

    /**
     * 用户信息集合
     * @param parameters
     * @param currentPage
     * @param pageSize
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<SysUser> getSysUserList(String str, Map<String, Object> parameters) {
        return this.getSqlMapClientTemplate().queryForList(str, parameters);
    }

    /**
     * 用户信息集合
     */
    public List<SysUser> getSysUserList(String str, String parameters) {
        return this.getSqlMapClientTemplate().queryForList(str, parameters);
    }

    /**
     * 根据部门ID取下属用户集合
     */
    public List<SysUserBean> getDeptBelongUserList(String str, String userIds) {
        return this.getSqlMapClientTemplate().queryForList(str, userIds);
    }
    /**
     * 查询所在机构用户数据
     */
    public List<SysUser> getOnDeptData(int deptId){
        return this.getSqlMapClientTemplate().queryForList("getOnDeptData",deptId);
    }
    /**
     *  查询下属机构的用户数据
     */
    public List<SysUser> getInDeptData(Map<String,Object> map){
        return this.getSqlMapClientTemplate().queryForList("getInDeptData",map);
    }
    /**
     * 查询本机构及下属机构的用户数据 
     */
    public List<SysUser> getContainDeptData(String deptSearchCode){
        return this.getSqlMapClientTemplate().queryForList("getContainDeptData",deptSearchCode);
    }

    /**
     * 查询所有的用户数据 
     */
    public List<SysUser> getAllData(){
        return this.getSqlMapClientTemplate().queryForList("getAllData");
    }

    /**
     * 根据用户名取用户
     * @param account
     * @return
     * @see com.banger.mobile.facade.user.SysUserService#getUserByAccount(java.lang.String)
     */
    public SysUser getUserByAccount(String account) {
        try {
            List<SysUser> ls=this.getSysUserList("getAccount",  account);
            if(ls.size()>0)
                return (SysUser)ls.get(0);
            else
                return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
    /**
     * 根据用户名取用户
     */
    public SysUser getAllUserByAccount(String account) {
        try {
            List<SysUser> users=this.getSysUserList("getAllUserByAccount",  account);
            if(users.size()>0)
            {
                for(SysUser user : users)
                {
                    if(user.getIsDel().equals(0))
                    {
                        return user;
                    }
                }
                return users.get(0);
            }
            else
                return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public  List<SysUser> getAllUserByAccountMap(Map<String,Object> map){
        try {
            List<SysUser> users=this.getSqlMapClientTemplate().queryForList("getAllUserByAccountMap",map);
            if(users.size()>0)
            {

                return users;
            }
            else
                return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public  List<SysUser> getAllUserByAccountAndName(Map<String,Object> map){
        try {
            List<SysUser> users=this.getSqlMapClientTemplate().queryForList("getAllUserByAccountAndName",map);
            if(users.size()>0)
            {

                return users;
            }
            else
                return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 查询所在机构用户数据
     */
    public List<SysUser> getOnDeptData(int deptId,boolean flag){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("deptId", deptId);
        if(flag){
            return this.getSqlMapClientTemplate().queryForList("getOnDeptDataHasDel",deptId);
        }else{
            map.put("isActived", 1);
            return this.getSqlMapClientTemplate().queryForList("getOnDeptData",map);
        }
    }
    /**
     *  查询下属机构的用户数据
     */
    public List<SysUser> getInDeptData(Map<String, Object> map,boolean flag){
        if(flag){
            return this.getSqlMapClientTemplate().queryForList("getInDeptDataHasDel",map);
        }else{
            return this.getSqlMapClientTemplate().queryForList("getInDeptData",map);
        }
    }

    /**
     * 查询本机构及下属机构的用户数据 
     */
    public List<SysUser> getContainDeptData(String deptSearchCode,boolean flag){
        if(flag){
            return this.getSqlMapClientTemplate().queryForList("getContainDeptDataHasDel",deptSearchCode);
        }else{
            return this.getSqlMapClientTemplate().queryForList("getContainDeptData",deptSearchCode);
        }
    }
    /**
     * 查询所有的用户数据 
     */
    public List<SysUser> getAllData(boolean flag){
        if(flag){
            return this.getSqlMapClientTemplate().queryForList("getAllDataHasDel");
        }else{
            return this.getSqlMapClientTemplate().queryForList("getAllData");
        }
    }
    /**
     * 查询所管理机构下的用户 不包含伪删除和admin
     * @return
     */
    public List<SysUser> getInChargeDeptUsers(Map<String,Object> map){
        return this.getSqlMapClientTemplate().queryForList("getInChargeDeptUsers",map);
    }

    /**
     * @param map
     * @return
     * @see com.banger.mobile.dao.user.SysUserDao#getSuperiorUserList(java.util.Map)
     */
    public List<SysUserBean> getSuperiorUserList(Map<String, Object> map) {
        return this.getSqlMapClientTemplate().queryForList("getSuperiorUserList",map);
    }

    /**
     * @param map
     * @return
     * @see com.banger.mobile.dao.user.SysUserDao#getDeptBelongUserTaskList(java.util.Map)
     */
    public List<SysUser> getDeptBelongUserTaskList(Map<String, Object> map) {
        return this.getSqlMapClientTemplate().queryForList("getDeptBelongUserTaskList",map);
    }

    /**
     * @param map
     * @return
     * @see com.banger.mobile.dao.user.SysUserDao#getDeptUserList(java.util.Map)
     */
    public List<SysUser> getDeptUserList(Map<String, Object> map) {
        return this.getSqlMapClientTemplate().queryForList("getUserByDeptIdList",map);
    }

    /**
     * 得到来电转接用户
     * @param condition
     * @param page
     * @return
     */
    @SuppressWarnings("unchecked")
    public PageUtil<SysTalkUserBean> getTalkForwardUsers(Map<String, Object> condition,Page page) {
        ArrayList<SysTalkUserBean> list = (ArrayList<SysTalkUserBean>) this.findQueryPage(
                "getCallForwardUsers", "getCallForwardUserCount", condition, page);
        if (list == null) {
            list = new ArrayList<SysTalkUserBean>();
        }
        return new PageUtil<SysTalkUserBean>(list, page);
    }

    /**
     * @param userIds
     * @return
     * @see com.banger.mobile.dao.user.SysUserDao#getUserListByIds(java.lang.String)
     */
    public List<SysUserBean> getUserListByIds(String userIds) {
        if("".equals(userIds)){
            userIds = "0";
        }
        return this.getSqlMapClientTemplate().queryForList("getUserListByIds",userIds);
    }
    /**
     * 缓存所有的客户经理和业务主管 
     * @return
     */
    public List<SysUser> getAllUserForCache(){
        return this.getSqlMapClientTemplate().queryForList("getAllUserForCache");
    }
    /**
     * 根据userIds字符串查询角色名称
     */
    public List<UserRoleName> getRoleNamesByUserIds(String userIds){
        return this.getSqlMapClientTemplate().queryForList("getRoleNamesByUserIds",userIds);
    }

    /**
     * @return
     * @see com.banger.mobile.dao.user.SysUserDao#getAllUser()
     */
    public List<SysUser> getAllUser() {
        return this.getSqlMapClientTemplate().queryForList("getAllUser");
    }

    public List<Integer> getCommonUserIdList() {
        return this.getSqlMapClientTemplate().queryForList("getCommonUserIdList");
    }

    /**
     * @param userId
     * @return
     * @see com.banger.mobile.dao.user.SysUserDao#getSysUserAndCounterUser(java.lang.Integer)
     */
    public List<SysUserBean> getSysUserAndCounterUser(Map<String, Object> condition) {
        return this.getSqlMapClientTemplate().queryForList("getSysUserAndCounterUser",condition);
    }

    /**
     * 查用户的直属领导
     * @param userIds
     * @return
     */
    public List<SysUserBean> getLeadNamesByUserId(String userIds) {
        return this.getSqlMapClientTemplate().queryForList("getLeadNamesByUserId",userIds);
    }

    /**
     * 根据信贷操作号查用户
     * @param operateCode
     * @return
     */
    public SysUser getSysUserByOperateCode(String operateCode) {
        try {
            List<SysUser> users = this.getSysUserList("getSysUserByOperateCode", operateCode);
            if (users.size() > 0) {
                return users.get(0);
            } else
                return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public List<SysUser> getAllManagerUser() {
        return this.getSqlMapClientTemplate().queryForList("getAllManagerUser");
    }

    /**
     * 获取用户在信贷机构信息
     * @param account
     * @return
     */
    @Override
    public  Map<String,Map<String,String>>  getUserJGINFO(String account) {
        return this.getSqlMapClientTemplate().queryForMap("getUserJGINFO",account,"jgm");
    }

    @Override
    public List<SysUser> getSysUserTeamCheifByUserId(Integer userId) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("userId", userId);
        return this.getSqlMapClientTemplate().queryForList("getSysUserTeamCheifByUserId", param);
    }

    @Override
    public List<SysUser> getUserListBelongToSysTeamByUserId(Integer userId) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("userId", userId);
        return this.getSqlMapClientTemplate().queryForList("getUserNewListBelongToSysTeamByUserId", param);
//		return this.getSqlMapClientTemplate().queryForList("getUserListBelongToSysTeamByUserId", param);
    }

    @Override
    public List<Integer> getManagerSysUserUserIdListByTeamMemberUserId(
            Integer userId) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("userId", userId);
        return this.getSqlMapClientTemplate().queryForList("getManagerSysUserUserIdListByTeamMemberUserId", param);
    }

    public Integer getUserLeaveTag(Integer userId){
        Object obj = this.getSqlMapClientTemplate().queryForObject("getUserLeaveTag",userId);
        if(obj == null){
            return 0;
        }
        return (Integer)obj;
    }

    public Double getApprovalValue(Integer userId){
        Object obj = this.getSqlMapClientTemplate().queryForObject("getApprovalValue",userId);
        if(obj == null){
            return 0.0;
        }
        return (Double)obj;
    }

    @Override
    public List<SysUser> getManagerByUserIdAndRoleId(Map<String, Object> param) {
        return this.getSqlMapClientTemplate().queryForList("getManagerByUserIdAndRoleId", param);
    }

	@Override
	public Map<Integer, String> getColumnNames(String sql) throws SQLException {
		Connection conn = this.getSqlMapClientTemplate().getDataSource().getConnection();
		Map<Integer,String> map= null;
		try{
		ResultSet rs = conn.createStatement().executeQuery(sql);
		ResultSetMetaData rsd = rs.getMetaData();
		map = new LinkedHashMap<Integer, String>();
		for(int i=1;i<=rsd.getColumnCount();i++){
			map.put(i, rsd.getColumnName(i));
		}
		}catch(SQLException e){
			throw e;
		}finally{
			conn.close();
		}
		return map;
	}

	@Override
	public List<Map> getResultData(String sql) throws SQLException {
		Connection conn = this.getSqlMapClientTemplate().getDataSource().getConnection();
		List<Map> result = null;
		try{
			ResultSet rs = conn.createStatement().executeQuery(sql);
			ResultSetMetaData rsd =rs.getMetaData();
			result = new ArrayList<Map>();
			while(rs.next()){
			Map<String,String> map = new LinkedHashMap<String,String>();
			for(int i=1;i<=rsd.getColumnCount();i++){
				map.put(rsd.getColumnName(i), rs.getString(i));
			}
				result.add(map);
		}
		}catch(SQLException e){
				throw e;
		}finally{
				conn.close();
		}
		return result;
	}

	@Override
	public int[] excute(String sqlString) throws SQLException {
		Connection conn = this.getSqlMapClientTemplate().getDataSource().getConnection();
		int [] results = null;
		try {
			Statement sta = conn.createStatement();
			String[] sqls = sqlString.split(";");
			System.out.println(sqls);
			for(String sql:sqls){
				sta.addBatch(sql);
		}
			results=sta.executeBatch();
			
		}catch(SQLException e){
			throw e;
		}finally{
			conn.close();
		}
		return results;
	}

}
