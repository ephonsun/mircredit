/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :高级搜索客户接口实现类
 * Author     :liyb
 * Create Date:2012-5-24
 */
package com.banger.mobile.dao.customer.ibatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.customer.AdvancedCustomerDao;
import com.banger.mobile.domain.model.customer.AdvanceQueryBean;
import com.banger.mobile.domain.model.customer.CrmExportBean;
import com.banger.mobile.domain.model.customer.CrmUserQueryBean;
import com.banger.mobile.domain.model.customer.CustomerBean;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author liyb
 * @version $Id: AdvancedCustomerDaoiBatis.java,v 0.1 2012-5-24 下午04:34:42 liyb Exp $
 */
@SuppressWarnings("rawtypes")
public class AdvancedCustomerDaoiBatis extends GenericDaoiBatis implements AdvancedCustomerDao {

    @SuppressWarnings("unchecked")
    public AdvancedCustomerDaoiBatis(){
        super(CustomerBean.class);
    }

    /**
     * 高级搜索客户分页列表
     * @param parameters
     * @param page
     * @return
     */
    @SuppressWarnings("unchecked")
    public PageUtil<CustomerBean> getAdvancedCustomerPage(Map<String, Object> parameters, Page page) {
        parameters.put("result", "count");
        ArrayList<CustomerBean> list = (ArrayList<CustomerBean>) this.findQueryPage(
                "getAdvancedCustomerPageMap", "getAdvancedCustomerCount", parameters, page);
        if (list == null) {
            list = new ArrayList<CustomerBean>();
        }
        return new PageUtil<CustomerBean>(list, page);
    }

    /**
     * 高级搜索客户分页列表
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<CustomerBean> getAdvancedCustomerPage(AdvanceQueryBean query, Page page) {
        @SuppressWarnings("unchecked")
        ArrayList<CustomerBean> list = (ArrayList<CustomerBean>) this.findQueryPage(
                "getAdvancedCustomerPageMap", "getAdvancedCustomerCount", query, page);
        if (list == null) {
            list = new ArrayList<CustomerBean>();
        }
        return new PageUtil<CustomerBean>(list, page);
    }

    /**
     * 根据客户id得到客户
     * @param condition
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<CustomerBean> getSmsCustomersByIds(String custIds){
        ArrayList<CustomerBean> list = (ArrayList<CustomerBean>)this.getSqlMapClientTemplate().queryForList("selectSmsCustomerByIds",custIds);
        return list;
    }

    /**
     * 得到高级搜索导出客户
     * @param parameters
     * @param offset
     * @param limit
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<CrmExportBean> getExportAdvancedCustomers(Map<String, Object> parameters,int offset,int limit){
        parameters.put("startRow", offset);
        parameters.put("endRow", limit);
        return this.getSqlMapClientTemplate().queryForList("getExportAdvancedCustomers",parameters);
    }

    /**
     * 查询短信客户
     * @param parameters
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<CustomerBean> getSmsCustomers(Map<String, Object> condition){
        condition.remove("startRow");
        condition.remove("endRow");
        return this.getSqlMapClientTemplate().queryForList("getAdvancedCustomerPageMap",condition);
    }

    /**
     * 查询短信客户Id集合
     * @param parameters
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Integer> getSmsCustomerCount(Map<String, Object> condition){
        //condition.put("result","id");
        return this.getSqlMapClientTemplate().queryForList("getAdvancedCustomerIds",condition);
    }

    /**
     * 选择客户Id集合
     * @param parameters
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Integer> getAllSelectCustomerIds(Map<String, Object> condition){
        //condition.put("result","id");
        return this.getSqlMapClientTemplate().queryForList("getAdvancedCustomerIds",condition);
    }

    /**
     * 得到自定义搜索列表
     * @param userId
     * @return
     */
    public PageUtil<CrmUserQueryBean> getUserQueryList(Integer userId, Page page){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userId", userId);
        map.put("result", "count");
        @SuppressWarnings("unchecked")
        ArrayList<CrmUserQueryBean> list = (ArrayList<CrmUserQueryBean>) this.findQueryPage(
                "getUserQueryList", "getUserQueryListCount",map, page);
        if (list == null) {
            list = new ArrayList<CrmUserQueryBean>();
        }
        return new PageUtil<CrmUserQueryBean>(list, page);
    }

    /**
     * 得到自定义查询的最大排序号
     * @param condition
     * @return
     */
    public Integer getUserQueryConditionTopSortNo(Integer userId){
        Object obj = this.getSqlMapClientTemplate().queryForObject("getUserQueryConditionTopSortNo",userId);
        if(obj!=null)return (Integer)obj;
        else return new Integer(0);
    }

    /**
     * 删除自定义搜索
     * @param queryId
     */
    public void delUserQueryById(Integer queryId){
        this.getSqlMapClientTemplate().delete("delUserQueryById", queryId);
    }

    /**
     * 修改自定义查询
     * @param condition
     */
    public void editUserQueryConditionName(CrmUserQueryBean condition){
        this.getSqlMapClientTemplate().update("editUserQueryConditionName", condition);
    }

    /**
     * 修改排序号
     * @param condition
     */
    public void editUserQueryConditionSortNo(CrmUserQueryBean condition){
        this.getSqlMapClientTemplate().update("editUserQueryConditionSortNo", condition);
    }

    /**
     * 得到高级查询条件条件对像
     * @param id
     * @return
     */
    public CrmUserQueryBean getUserQueryConditionById(Integer id){
        return (CrmUserQueryBean)this.getSqlMapClientTemplate().queryForObject("getUserQueryConditionById",id);
    }

    /**
     * 添加高级查询条件对像
     * @param condition
     */
    public void addUserQueryCondition(CrmUserQueryBean condition){
        this.getSqlMapClientTemplate().insert("addUserQueryCondition", condition);
    }

    /**
     * 得到自定义查询条件列表
     * @param userId
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<CrmUserQueryBean> getUserQueryMenuList(Integer userId){
        return (List<CrmUserQueryBean>)this.getSqlMapClientTemplate().queryForList("getUserQueryMenuList",userId);
    }

    /**
     * 获得用户查询列表
     */
    @SuppressWarnings("unchecked")
    public List<CrmUserQueryBean> getUserQueryList(Integer userId){
        return (List<CrmUserQueryBean>)this.getSqlMapClientTemplate().queryForList("getUserQueryListTop100",userId);
    }

    /**
     * 查询重名的自定义查询结果
     * @return
     */
    public Integer getUserQueryCountByName(CrmUserQueryBean condition){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getUserQueryCountByName",condition);
    }

    /**
     * 获取自定义结果集合长度
     * @param
     */
    public Integer customResultLength(CrmUserQueryBean condition){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getCustomResultLength",condition);
    }
}
