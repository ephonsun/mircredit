/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :柜台人员
 * Author     :yujh
 * Create Date:Sep 4, 2012
 */
package com.banger.mobile.facade.impl.system.crmCounterUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.crmCounterUser.CrmCounterUserDao;
import com.banger.mobile.domain.model.crmCounterUser.CrmCounterUser;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.facade.crmCounterUser.CrmCounterUserService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.util.StringUtil;

/**
 * @author yujh
 * @version $Id: CrmCounterUserServiceImpl.java,v 0.1 Sep 4, 2012 1:27:07 PM Administrator Exp $
 */
public class CrmCounterUserServiceImpl implements CrmCounterUserService {
    private CrmCounterUserDao crmCounterUserDao;
    private DeptFacadeService deptFacadeService;

    public void setCrmCounterUserDao(CrmCounterUserDao crmCounterUserDao) {
        this.crmCounterUserDao = crmCounterUserDao;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    /**
     * 删除柜台人员
     */
    public void deleteCounterUser(int id) {
        this.crmCounterUserDao.deleteCounterUser(id);
    }

    /**
     * 新增柜台人员
     */
    public void insertCounterUser(CrmCounterUser user) {
        this.crmCounterUserDao.insertCounterUser(user);
    }

    /**
     * 根据ID查询柜台人员信息
     */
    public CrmCounterUser queryById(int id) {
        return this.crmCounterUserDao.queryById(id);
    }

    /**
     * 修改柜台人员信息
     */
    public void updateCounterUser(CrmCounterUser user) {
        this.crmCounterUserDao.updateCounterUser(user);
    }

    /**
     * 分页
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.facade.crmCounterUser.CrmCounterUserService#getAllCrmCounterUser(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<CrmCounterUser> getAllCrmCounterUser(Map<String, Object> parameters, Page page) {
        return this.crmCounterUserDao.getAllCrmCounterUser(parameters, page);
    }

    /**
     * 验证
     */
    public List<CrmCounterUser> validateCrmCounterUser(CrmCounterUser crmCounterUser) {
        return this.crmCounterUserDao.validateCrmCounterUser(crmCounterUser);
    }

    /**
     * 当前用户管辖的柜台人员id集合
     */
    public Integer[] getAllCrmCounterIds(int roleId, int userId) {

        List<Integer> deptIdList = new ArrayList<Integer>();
        List<Integer> userIdList = new ArrayList<Integer>();
        List<CrmCounterUser> crmCounterUserList = new ArrayList<CrmCounterUser>();
        String ids = "";
        List<SysDept> deptList = deptFacadeService.getDeptsForManger(3, userId);
        for (SysDept dept : deptList) {
            deptIdList.add(dept.getDeptId());
            ids += dept.getDeptId() + ",";
        }
        if(!ids.equals("")){
            ids = ids.substring(0, ids.length() - 1);
	        crmCounterUserList = this.crmCounterUserDao
	            .getAllCrmCounterUserForList(ids);
        }
        Integer[] userIds = new Integer[crmCounterUserList.size()];
        for (int i = 0; i < crmCounterUserList.size(); i++) {
            userIds[i] = crmCounterUserList.get(i).getCounterUserId();
        }
        return userIds;
    }
    
    /**
     * 根据部门id查询包含删除的柜台人员
     */
    public String getAllCrmCounterUser(String ids) {
    	String uids = "";
    	List<CrmCounterUser> clist = this.crmCounterUserDao.getAllCrmCounterUser(ids);
    	for(CrmCounterUser user : clist){
    		uids += user.getCounterUserId()+",";
    	}
    	if(!StringUtil.isBlank(uids)){
    		uids = uids.substring(0, uids.length()-1);
    	}
    	return uids;
    }
    
    /**
     * 根据userId查询所有柜台人员
     * @param userId：用户id  isDel：是否删除（false:删除,true:所有）
     * @return
     */
    public List<CrmCounterUser> getCounterUserByUserId(int userId,boolean flag){
    	return this.crmCounterUserDao.getCounterUserByUserId(userId, flag);
    }
}
