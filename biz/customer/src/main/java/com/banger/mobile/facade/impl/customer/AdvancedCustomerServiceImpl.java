/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :高级搜索客户接口实现类
 * Author     :liyb
 * Create Date:2012-5-24
 */
package com.banger.mobile.facade.impl.customer;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.customer.AdvancedCustomerDao;
import com.banger.mobile.dao.templateField.CrmTemplateFieldDao;
import com.banger.mobile.domain.model.customer.AdvanceQueryBean;
import com.banger.mobile.domain.model.customer.CrmExportBean;
import com.banger.mobile.domain.model.customer.CrmUserQueryBean;
import com.banger.mobile.domain.model.customer.CustomerBean;
import com.banger.mobile.domain.model.customer.CustomerExtendFieldBean;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.templateField.CrmTemplateField;
import com.banger.mobile.facade.customer.AdvancedCustomerService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.util.TypeUtil;
import org.apache.log4j.Logger;

/**
 * @author liyb
 * @version $Id: AdvancedCustomerServiceImpl.java,v 0.1 2012-5-24 下午04:44:32 liyb Exp $
 */
public class AdvancedCustomerServiceImpl implements AdvancedCustomerService {
    private static final Logger logger = Logger.getLogger(AdvancedCustomerServiceImpl.class);

    protected AdvancedCustomerDao advancedCustomerDao;
    protected CrmTemplateFieldDao crmTemplateFieldDao;
    protected DeptFacadeService deptService;
    
    public DeptFacadeService getDeptService() {
		return deptService;
	}
	public void setDeptService(DeptFacadeService deptService) {
		this.deptService = deptService;
	}
	public CrmTemplateFieldDao getCrmTemplateFieldDao() {
		return crmTemplateFieldDao;
	}
	public void setCrmTemplateFieldDao(CrmTemplateFieldDao crmTemplateFieldDao) {
		this.crmTemplateFieldDao = crmTemplateFieldDao;
	}
	public void setAdvancedCustomerDao(AdvancedCustomerDao advancedCustomerDao) {
        this.advancedCustomerDao = advancedCustomerDao;
    }
    /**
     * 高级搜索客户分页列表
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<CustomerBean> getAdvancedCustomerPage(Map<String, Object> parameters, Page page) {
        return advancedCustomerDao.getAdvancedCustomerPage(parameters, page);
    }

    /**
     * 高级搜索客户分页列表
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<CustomerBean> getAdvancedCustomerPage(AdvanceQueryBean query, Page page) {
        return advancedCustomerDao.getAdvancedCustomerPage(query, page);
    }
    
    /**
     * 选择客户Id集合
     * @param condition
     * @return
     */
    public List<Integer> getAllSelectCustomerIds(Map<String, Object> condition){
    	return advancedCustomerDao.getAllSelectCustomerIds(condition);
    }
    
    /**
     * 修改自定义查询
     * @param condition
     */
    public void editUserQueryConditionName(CrmUserQueryBean condition)
    {
    	this.advancedCustomerDao.editUserQueryConditionName(condition);
    }
    
    /**
     * 交换两个条件的排序号
     * @param firstId
     * @param secondId
     */
    public void changerUserQueryCondition(Integer firstId,Integer secondId)
    {
    	CrmUserQueryBean first = this.getUserQueryConditionById(firstId);
    	CrmUserQueryBean second = this.getUserQueryConditionById(secondId);
    	Integer sortno = first.getSortno();
    	first.setSortno(second.getSortno());
    	second.setSortno(sortno);
    	this.editUserQueryConditionSortNo(first);
    	this.editUserQueryConditionSortNo(second);
    }
    
    /**
     * 修改排序号
     * @param condition
     */
    public void editUserQueryConditionSortNo(CrmUserQueryBean condition)
    {
    	this.advancedCustomerDao.editUserQueryConditionSortNo(condition);
    }
    
    /**
     * 得到高级查询条件条件对像
     * @param id
     * @return
     */
    public CrmUserQueryBean getUserQueryConditionById(Integer id){
    	return this.advancedCustomerDao.getUserQueryConditionById(id);
    }
    
    /**
     * 添加高级查询条件对像
     * @param condition
     */
    public Integer addUserQueryCondition(CrmUserQueryBean condition){
    	Integer sortNo = this.advancedCustomerDao.getUserQueryConditionTopSortNo(condition.getUserId());
    	condition.setSortno(sortNo+1);
    	condition.setIsDel(0);
    	this.advancedCustomerDao.addUserQueryCondition(condition);
    	return condition.getUserQueryId();
    }
    
    /**
     * 得到高级查询条件列表
     * @param userId
     * @return
     */
    public List<CrmUserQueryBean> getUserQueryMenuList(Integer userId){
    	return this.advancedCustomerDao.getUserQueryMenuList(userId);
    }
    
    /**
     * 得到自定义搜索列表
     * @param userId
     * @return
     */
    public PageUtil<CrmUserQueryBean> getUserQueryList(Integer userId,Page page){
    	return this.advancedCustomerDao.getUserQueryList(userId,page);
    }
    
    /**
     * 删除自定义搜索
     * @param queryId
     */
    public void delUserQueryById(Integer queryId){
    	this.advancedCustomerDao.delUserQueryById(queryId);
    }
    
    /**
     * 判断是否有相同的名称
     * @param conditon
     * @return
     */
    public boolean existUserQueryResultByName(CrmUserQueryBean conditon){
    	Integer count = this.advancedCustomerDao.getUserQueryCountByName(conditon);
    	return count>0;
    }
    
    /**
     * 
     * @param parameters
     * @param offset
     * @param limit
     * @return
     */
    public List<CrmExportBean> getExportAdvancedCustomers(Map<String, Object> parameters,int offset,int limit){
    	return advancedCustomerDao.getExportAdvancedCustomers(parameters,offset,limit);
    }
    
    /**
     * 得到自定义字段条件
     * @return
     */
    @SuppressWarnings("unchecked")
    public String getExtendFieldCondition(CustomerExtendFieldBean exField){
    	StringBuilder sb = new StringBuilder();
    	DateUtil.format(exField);
		Map<String, Object> map = null;
		try {
			map = BeanUtils.describe(exField);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
    	List<CrmTemplateField> fields = crmTemplateFieldDao.getAllCrmTemplateField();
    	for(CrmTemplateField field : fields)
    	{
    		String type=field.getTemplateFieldType();
    		String alias = "ex";
    		String colName = field.getExtFieldName();
    		String endFieldName = field.getFieldName()+"End";
    		
    		Object valObj = map.containsKey(field.getFieldName())?map.get(field.getFieldName()):null;
    		String val = (String)TypeUtil.changeType(valObj,String.class);
    		Object endValObj = map.containsKey(endFieldName)?map.get(endFieldName):null;
    		String endVal = (String)TypeUtil.changeType(endValObj,String.class);
    		
    		if(type.equals("Text") || type.equals("TextArea")){
    			if(!StringUtil.isNullOrEmpty(val))sb.append(StringUtil.format(" and {0}.{1} like '%{2}%'",alias,colName,StringUtil.ReplaceSQLChar(val)));
    		}else if(type.equals("Date")){
    			if(!StringUtil.isNullOrEmpty(val))sb.append(StringUtil.format(" and {0}.{1} >= TO_DATE('{2}','yyyy-MM-dd')",alias,colName,TypeUtil.changeType(val,String.class)));
    			if(!StringUtil.isNullOrEmpty(endVal))sb.append(StringUtil.format(" and {0}.{1} <= TO_DATE('{2}','yyyy-MM-dd')",alias,colName,TypeUtil.changeType(endVal,String.class)));
    		}else if(type.equals("Number")){
    			if(!StringUtil.isNullOrEmpty(val))sb.append(StringUtil.format(" and {0}.{1} >= {2}",alias,colName,TypeUtil.changeType(val,String.class)));
    			if(!StringUtil.isNullOrEmpty(endVal))sb.append(StringUtil.format(" and {0}.{1} <= {2}",alias,colName,TypeUtil.changeType(endVal,String.class)));
    		}else if(type.equals("Select")){
    			if(!StringUtil.isNullOrEmpty(val)){
    				String[] vs = val.split("\\,");
    				String vsStr="(";
    				for(int i=0;i<vs.length;i++)
    				{
    					String part = StringUtil.format(" VARCHAR({0}.{1}) = '{2}' ",alias,colName,vs[i]);
    					vsStr += (vsStr.length()>1)?" or "+part:part;
    				}
    				vsStr+=")";
    				sb.append(" and "+vsStr);
    			}
    		}
    		else if(type.equals("MultipleSelect")){
    			if(!StringUtil.isNullOrEmpty(val)){
    				String[] vs = val.split("\\,");
    				String vsStr="(";
    				for(int i=0;i<vs.length;i++)
    				{
    					String part = StringUtil.format(" {0}.{1} like '%{2}%' ",alias,colName,vs[i]);
    					vsStr += (vsStr.length()>1)?" or "+part:part;
    				}
    				vsStr+=")";
    				sb.append(" and "+vsStr);
    			}
    		}
    		else if(type.equals("YesNo")){
    			if(!StringUtil.isNullOrEmpty(val))sb.append(StringUtil.format(" and {0}.{1} = '{2}'",alias,colName,val));
    		}
    	}
    	return sb.toString();
    }
    
    /**
     * 得到号码规则条件
     * @param condition
     * @return
     */
    public String getPhoneRuleCondition(AdvanceQueryBean condition){
    	StringBuilder sb = new StringBuilder();
    	if(!StringUtil.isNullOrEmpty(condition.getNumberRules()))
    	{
    		sb.append(" and (");
    		String[] conditions = condition.getNumberRules().split("\\,");
    		for(int i=0;i<conditions.length;i++)
    		{
    			String c = conditions[i];
    			int n = c.indexOf("+");
    			int m = c.indexOf("A");
    			String condStr = "";
    			if(n>-1)
    			{
    				String sStr = c.substring(0,n);
    				String eStr = c.substring(n+1);
    				condStr = StringUtil.format(" (cus.MOBILE_PHONE1 like '{0}%' and cus.MOBILE_PHONE1_REGULAR = '{1}') or (cus.MOBILE_PHONE2 like '{0}%' and cus.MOBILE_PHONE2_REGULAR = '{1}') ",sStr,eStr);
    			}
    			else
    			{
    				if(m>-1)
    				{
    					condStr = StringUtil.format(" cus.MOBILE_PHONE1_REGULAR = '{0}' or cus.MOBILE_PHONE2_REGULAR = '{0}' ",c);
    				}
    				else
    				{
    					condStr = StringUtil.format(" cus.MOBILE_PHONE1 like '{0}%' or cus.MOBILE_PHONE2 like '{0}%' ",c);
    				}
    			}
    			if(i==0)sb.append(condStr);
    			else sb.append(" or "+condStr);
    		}
    		sb.append(")");
    	}
    	return sb.toString();
    }
    
    /**
     * 上移自定义查询
     */
    public void upUserQueryById(Integer queryId,Integer userId)
    {
    	List<CrmUserQueryBean> list = this.advancedCustomerDao.getUserQueryList(userId);
    	for(int i=0;i<list.size();i++)
    	{
    		if(list.get(i).getUserQueryId().equals(queryId))
    		{
    			this.changerUserQueryCondition(queryId,list.get(i-1).getUserQueryId());
    		}
    	}
    }
    
    /**
     * 下移自定义查询
     * @param queryId
     */
    public void downUserQueryById(Integer queryId,Integer userId)
    {
    	List<CrmUserQueryBean> list = this.advancedCustomerDao.getUserQueryList(userId);
    	for(int i=0;i<list.size();i++)
    	{
    		if(list.get(i).getUserQueryId().equals(queryId))
    		{
    			if(i<list.size()-1)
    			{
    				this.changerUserQueryCondition(queryId,list.get(i+1).getUserQueryId());
    			}
    		}
    	}
    }
    
    /**
     * 获得归属人员ids
     * @param userId
     * @return
     */
    private String getBelongToUserIds(Integer userId)
    {
		String userIds = "";
		Integer[] arr = deptService.getInChargeOfDeptUserIds(userId);
		if(arr!=null){
			for(int i=0; i<arr.length; i++){
				if(userIds.equals(""))
					userIds = arr[i].toString();
				else
					userIds = userIds + "," + arr[i];
			}
		}
		if(userIds.equals("")){
			userIds = userId.toString();
		}else{
			userIds = userIds + "," +userId.toString(); 
		}
		return userIds;
    }
    
    /**
     * 设置归属
     */
    public void setBelongToCondition(AdvanceQueryBean condition,Integer userId)
    {
    	boolean flag = deptService.isInChargeOfDepartment(userId.toString());
    	//执行任务时添加在行客户
    	if(!condition.getBelongTo().equals("my")){
    	   if(!flag)condition.setBelongTo("my");
    	}
    	String bt=condition.getBelongTo();
    	if("my".equals(bt))
    	{
    		condition.setUserIds(userId.toString());
    		condition.setDeptIds("-1");
    	}
    	else if("sub".equals(bt) && !StringUtil.isNullOrEmpty(condition.getUserIds()))
    	{
    		if(!"unShare".equals(condition.getShareTo())){
    			String deptIds = StringUtil.getIdsString(deptService.getInChargeOfDeptIds(userId));
        		if(deptIds.length()>0)condition.setDeptIds(deptIds);
    		}else{
    			condition.setDeptIds("-1");
    		}
    	}
    	else if("dept".equals(bt) && !StringUtil.isNullOrEmpty(condition.getDeptIds()))
    	{
    		if(!StringUtil.isNullOrEmpty(condition.getContainSub())&&condition.getContainSub().equals("1")){
    			String deptIds = condition.getDeptIds();
    			String allDeptIds = getContainSubDeptIds(deptIds);
    			condition.setDeptIds(allDeptIds);
    		}
    		if(!"unShare".equals(condition.getShareTo()))
    		{
    			String userIds = getBelongToUserIds(userId);
        		condition.setUserIds(userIds);
    		}
    	}
    	else if("un".equals(bt))
    	{
    		String deptIds = StringUtil.getIdsString(deptService.getInChargeOfDeptIds());
    		if(deptIds.length()>0)condition.setDeptIds(deptIds);
    		condition.setUserIds("0");
    	}
    	else
    	{
    		condition.setBelongTo("all");
	    	String deptIds = StringUtil.getIdsString(deptService.getInChargeOfDeptIds(userId));
	    	if(deptIds.length()>0)condition.setDeptIds(deptIds);
    		String userIds = getBelongToUserIds(userId);
    		condition.setUserIds(userIds);
    	}
    }
    
    /**
     * 过滤条件
     */
    public void filterCondition(AdvanceQueryBean condition)
	{
		if(condition!=null)
		{
			if(!StringUtil.isNullOrEmpty(condition.getCustomerName()))
				condition.setCustomerName(StringUtil.ReplaceSQLChar(condition.getCustomerName()));
			if(!StringUtil.isNullOrEmpty(condition.getCustomerTitle()))
				condition.setCustomerTitle(StringUtil.ReplaceSQLChar(condition.getCustomerTitle()));
			if(!StringUtil.isNullOrEmpty(condition.getCustomerNo()))
				condition.setCustomerNo(StringUtil.ReplaceSQLChar(condition.getCustomerNo()));
			if(!StringUtil.isNullOrEmpty(condition.getIdCard()))
				condition.setIdCard(StringUtil.ReplaceSQLChar(condition.getIdCard()));
			if(!StringUtil.isNullOrEmpty(condition.getCompany()))
				condition.setCompany(StringUtil.ReplaceSQLChar(condition.getCompany()));
			if(!StringUtil.isNullOrEmpty(condition.getRemark()))
				condition.setRemark(StringUtil.ReplaceSQLChar(condition.getRemark()));
			if(!StringUtil.isNullOrEmpty(condition.getAddress()))
				condition.setAddress(StringUtil.ReplaceSQLChar(condition.getAddress()));
			if(!StringUtil.isNullOrEmpty(condition.getEmail()))
				condition.setEmail(StringUtil.ReplaceSQLChar(condition.getEmail()));
		}
	}
    
    /**
	 * 包含本身选中的
	 * @param deptids
	 * @return 下属机构id集合
	 */
	private String getContainSubDeptIds(String deptids){
		List<SysDept> deptList = deptService.getContainDeptListByDeptIds(deptids);
		String newDeptIds = "";
		for(SysDept dept: deptList){
			if(newDeptIds.equals("")){
				newDeptIds = dept.getDeptId().toString();
			}else{
				newDeptIds = newDeptIds + "," + dept.getDeptId().toString();
			}
		}
		return newDeptIds;
	}
    
	/**
     * 获取自定义结果集合长度
     * @param  
     */
    public boolean customResultLength(CrmUserQueryBean conditon) {
        Integer count = this.advancedCustomerDao.customResultLength(conditon);
        return count>=20;
    }
}
