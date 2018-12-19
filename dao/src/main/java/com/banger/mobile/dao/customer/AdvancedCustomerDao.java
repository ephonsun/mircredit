/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :高级搜索客户实体数据接口
 * Author     :liyb
 * Create Date:2012-5-24
 */
package com.banger.mobile.dao.customer;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.customer.AdvanceQueryBean;
import com.banger.mobile.domain.model.customer.CrmExportBean;
import com.banger.mobile.domain.model.customer.CrmUserQueryBean;
import com.banger.mobile.domain.model.customer.CustomerBean;

/**
 * @author liyb
 * @version $Id: AdvancedCustomerDao.java,v 0.1 2012-5-24 下午04:31:14 liyb Exp $
 */
public interface AdvancedCustomerDao {
    /**
     * 高级搜索客户分页列表
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<CustomerBean> getAdvancedCustomerPage(Map<String, Object> parameters, Page page);
    
    /**
     * 高级搜索客户分页列表
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<CustomerBean> getAdvancedCustomerPage(AdvanceQueryBean query, Page page);
    
    /**
     * 得到高级搜索导出客户
     * @param parameters
     * @param offset
     * @param limit
     * @return
     */
    public List<CrmExportBean> getExportAdvancedCustomers(Map<String, Object> parameters,int offset,int limit);
    
    /**
     * 根据客户ids得到客户
     * @param condition
     * @return
     */
    public List<CustomerBean> getSmsCustomersByIds(String custIds);
    
    /**
     * 查询短信客户
     * @param parameters
     * @return
     */
    public List<CustomerBean> getSmsCustomers(Map<String, Object> condition);
    
    /**
     * 选择客户Id集合
     * @param condition
     * @return
     */
    public List<Integer> getAllSelectCustomerIds(Map<String, Object> condition);
    
    /**
     * 查询短信客户Id集合
     * @param parameters
     * @return
     */
    public List<Integer> getSmsCustomerCount(Map<String, Object> condition);
    
    /**
     * 得到自定义查询的最大排序号
     * @param condition
     * @return
     */
    public Integer getUserQueryConditionTopSortNo(Integer userId);
    
    /**
     * 修改自定义查询
     * @param condition
     */
    public void editUserQueryConditionName(CrmUserQueryBean condition);
    
    /**
     * 修改排序号
     * @param condition
     */
    public void editUserQueryConditionSortNo(CrmUserQueryBean condition);
    
    /**
     * 得到高级查询条件条件对像
     * @param id
     * @return
     */
    public CrmUserQueryBean getUserQueryConditionById(Integer id);
    
    /**
     * 添加高级查询条件对像
     * @param condition
     */
    public void addUserQueryCondition(CrmUserQueryBean condition);
    
    /**
     * 得到自定义查询条件列表,前10条
     * @param userId
     * @return
     */
    public List<CrmUserQueryBean> getUserQueryMenuList(Integer userId);
    
    /**
     * 得到自定义查询条件列表
     * @param userId
     * @return
     */
	public List<CrmUserQueryBean> getUserQueryList(Integer userId);
    
    /**
     * 得到自定义搜索列表
     * @param userId
     * @return
     */
    public PageUtil<CrmUserQueryBean> getUserQueryList(Integer userId,Page page);
    
    /**
     * 删除自定义搜索
     * @param queryId
     */
    public void delUserQueryById(Integer queryId);
    
    /**
     * 查询重名的自定义查询结果
     * @return
     */
    public Integer getUserQueryCountByName(CrmUserQueryBean condition);
    
    /**
     * 获取自定义结果集合长度
     * @param  
     */
    public Integer customResultLength(CrmUserQueryBean condition);
}
