/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :高级搜索客户接口
 * Author     :liyb
 * Create Date:2012-5-24
 */
package com.banger.mobile.facade.customer;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.customer.AdvanceQueryBean;
import com.banger.mobile.domain.model.customer.CrmExportBean;
import com.banger.mobile.domain.model.customer.CrmUserQueryBean;
import com.banger.mobile.domain.model.customer.CustomerBean;
import com.banger.mobile.domain.model.customer.CustomerExtendFieldBean;

/**
 * @author liyb
 * @version $Id: AdvancedCustomerService.java,v 0.1 2012-5-24 下午04:42:52 liyb Exp $
 */
public interface AdvancedCustomerService {
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
     * 交换两个条件的排序号
     * @param firstId
     * @param secondId
     */
    public void changerUserQueryCondition(Integer firstId,Integer secondId);
    
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
    public Integer addUserQueryCondition(CrmUserQueryBean condition);
    
    /**
     * 选择客户Id集合
     * @param condition
     * @return
     */
    public List<Integer> getAllSelectCustomerIds(Map<String, Object> condition);
    
    /**
     * 得到高级查询条件列表
     * @param userId
     * @return
     */
    public List<CrmUserQueryBean> getUserQueryMenuList(Integer userId);
    
    /**
     * 得到高级搜索列表
     * @param userId
     * @return
     */
    public PageUtil<CrmUserQueryBean> getUserQueryList(Integer userId,Page page);
    
    /**
     * 导出高级搜索客户
     * @param parameters
     * @param offset
     * @param limit
     * @return
     */
    public List<CrmExportBean> getExportAdvancedCustomers(Map<String, Object> parameters,int offset,int limit);
    
    /**
     * 删除自定义搜索
     * @param queryId
     */
    public void delUserQueryById(Integer queryId);
    
    /**
     * 判断是否有相同的名称
     * @param conditon
     * @return
     */
    public boolean existUserQueryResultByName(CrmUserQueryBean conditon);
    
    /**
     * 得到自定义字段条件
     * @return
     */
    public String getExtendFieldCondition(CustomerExtendFieldBean exField);
    
    /**
     * 得到号码规则条件
     * @param condition
     * @return
     */
    public String getPhoneRuleCondition(AdvanceQueryBean condition);
    
    /**
     * 上移自定义查询
     */
    public void upUserQueryById(Integer queryId,Integer userId);
    
    /**
     * 下移自定义查询
     * @param queryId
     */
    public void downUserQueryById(Integer queryId,Integer userId);
    
    /**
     * 设置客户归属条件
     * @param map
     * @param condition
     * @param userId
     */
    public void setBelongToCondition(AdvanceQueryBean condition,Integer userId);
    
    /**
     * 防sql注入
     * @param condition
     */
    public void filterCondition(AdvanceQueryBean condition);
    
    /**
     * 获取自定义结果集合长度
     * @param  
     */
    public boolean customResultLength(CrmUserQueryBean conditon);
}
