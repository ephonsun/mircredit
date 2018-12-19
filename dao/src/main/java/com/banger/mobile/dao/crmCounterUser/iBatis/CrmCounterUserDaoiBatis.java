/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :柜台人员
 * Author     :yujh
 * Create Date:Sep 4, 2012
 */
package com.banger.mobile.dao.crmCounterUser.iBatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.crmCounterUser.CrmCounterUserDao;
import com.banger.mobile.domain.model.crmCounterUser.CrmCounterUser;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author yujh
 * @version $Id: CrmCounterUserDaoiBatis.java,v 0.1 Sep 4, 2012 11:42:52 AM Administrator Exp $
 */
public class CrmCounterUserDaoiBatis extends GenericDaoiBatis implements CrmCounterUserDao{

    public CrmCounterUserDaoiBatis(Class persistentClass) {
        super(persistentClass);
    }
    public CrmCounterUserDaoiBatis(){
        super(CrmCounterUserDaoiBatis.class);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteCounterUser(int id) {
        this.getSqlMapClientTemplate().delete("deleteCounterUser",id);
    }

    /**
     * 插入
     * @param user
     */
    public void insertCounterUser(CrmCounterUser user) {
        this.getSqlMapClientTemplate().insert("insertCounterUser",user);
    }

    /**
     * id查询对象
     * @param id
     * @return
     */
    public CrmCounterUser queryById(int id) {
        return (CrmCounterUser)this.getSqlMapClientTemplate().queryForObject("queryById", id);
    }

    /**
     * 更新
     * @param user
     */
    public void updateCounterUser(CrmCounterUser user) {
        this.getSqlMapClientTemplate().update("updateCounterUser",user);
    }
    /**
     * 分页
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.dao.crmCounterUser.CrmCounterUserDao#getAllCrmCounterUser(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<CrmCounterUser> getAllCrmCounterUser(Map<String, Object> parameters, Page page) {
        ArrayList<CrmCounterUser> list = (ArrayList<CrmCounterUser>) this.findQueryPage("getAllCrmCounterUser", "getAllCrmCounterUserCount", parameters, page);
        if(list==null){
            list  = new ArrayList<CrmCounterUser>();
        }
        return new PageUtil<CrmCounterUser>(list,page);
    }
    /**
     * 验证是否已存在
     * @param crmCounterUser
     * @return
     * @see com.banger.mobile.dao.crmCounterUser.CrmCounterUserDao#validateCrmCounterUser(com.banger.mobile.domain.model.crmCounterUser.CrmCounterUser)
     */
    public List<CrmCounterUser> validateCrmCounterUser(CrmCounterUser crmCounterUser) {
        return this.getSqlMapClientTemplate().queryForList("validateCrmCounterUser", crmCounterUser);
    }
    /**
     * 查询所有柜台人员，返回list集合
     */
    @SuppressWarnings("unchecked")
    public List<CrmCounterUser> getAllCrmCounterUserForList(String ids) {
        return this.getSqlMapClientTemplate().queryForList("getAllCrmCounterUserForList", ids);
    }
    /**
     * 获取所有柜台人员
     * @return
     */
    public List<CrmCounterUser> getAllCrmCounterUser(String value) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("value", value);
        return this.getSqlMapClientTemplate().queryForList("getAllCounterUser",parameters);
    }

    /**
     * 根据userId查询所有柜台人员
     * @param userId：用户id  isDel：是否删除（false:删除,true:所有）
     * @return
     */
    public List<CrmCounterUser> getCounterUserByUserId(int userId,boolean flag){
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("userId", userId);
        if(!flag){
            parameters.put("isDel", 1);
        }
        return this.getSqlMapClientTemplate().queryForList("getCounterUserByUserId",parameters);
    }
}
