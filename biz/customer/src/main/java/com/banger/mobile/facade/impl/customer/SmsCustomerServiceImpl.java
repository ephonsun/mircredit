package com.banger.mobile.facade.impl.customer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;

import com.banger.mobile.domain.model.customer.AdvanceQueryBean;
import com.banger.mobile.domain.model.customer.CrmUserQueryBean;
import com.banger.mobile.domain.model.customer.CustomerBean;
import com.banger.mobile.domain.model.customer.CustomerExtendFieldBean;
import com.banger.mobile.facade.customer.SmsCustomerService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.SerializeUtil;
import com.banger.mobile.util.StringUtil;

public class SmsCustomerServiceImpl extends AdvancedCustomerServiceImpl implements SmsCustomerService {

	/**
	 * 查询发送短信的客户
	 * @param conditionIds	条件集合
	 * @param cusIds	客户集合
	 * @return
	 */
	public List<CustomerBean> querySmsCustomer(String conditionIds,String cusIds){
		Set<Integer> set = new HashSet<Integer>();
		List<CustomerBean> custs = new ArrayList<CustomerBean>();
		if(!StringUtil.isNullOrEmpty(cusIds))
		{
			List<CustomerBean> list = this.advancedCustomerDao.getSmsCustomersByIds(cusIds);
			for(CustomerBean cust : list)
			{
				if(!set.contains(cust.getCustomerId()))
				{
					custs.add(cust);
					set.add(cust.getCustomerId());
				}
			}
		}
		
		if(!StringUtil.isNullOrEmpty(conditionIds))
		{
			String[] queryIds = conditionIds.split("\\,");
			for(String queryId : queryIds)
			{
				List<CustomerBean> list = this.querySmsCustomerByQueryId(Integer.parseInt(queryId));
				for(CustomerBean cust : list)
				{
					if(!set.contains(cust.getCustomerId()))
					{
						custs.add(cust);
						set.add(cust.getCustomerId());
					}
				}
			}
		}
		
		return custs;
	}
	
	/**
	 * 根据自定义查询Id返回客户
	 * @param queryId
	 * @return
	 */
	@SuppressWarnings({ "unchecked"})
	private List<CustomerBean> querySmsCustomerByQueryId(Integer queryId)
	{
		CrmUserQueryBean query = this.getUserQueryConditionById(queryId);
		if(query.getQueryDetail()!=null && !"".equals(query.getQueryDetail()))
		{
			AdvanceQueryBean condition = (AdvanceQueryBean)SerializeUtil.formXML(query.getQueryDetail());
			if(condition.getExFields()!=null)
			{
				CustomerExtendFieldBean exField = condition.getExFields();
				this.filterCondition(condition);
				DateUtil.format(condition);
				this.setBelongToCondition(condition, query.getUserId());
		    	Map<String, Object> map=null;
				try {
					map = BeanUtils.describe(condition);
				} catch (Exception e) {
					e.printStackTrace();
				}
		    	String exCondition = this.getExtendFieldCondition(exField);
		    	if(!StringUtil.isNullOrEmpty(exCondition))
		    		map.put("exCondition",exCondition);
		    	String phoneRules = this.getPhoneRuleCondition(condition);
		    	if(!StringUtil.isNullOrEmpty(phoneRules))
		    		map.put("rules",phoneRules);
		    	
		    	return this.advancedCustomerDao.getSmsCustomers(map);
			}
		}
		return null;
	}
	
	/**
	 * 根据自定义查询Id返回客户
	 * @param queryId
	 * @return
	 */
	@SuppressWarnings({ "unchecked"})
	private List<Integer> querySmsCustomerIdsByQueryId(Integer queryId)
	{
		CrmUserQueryBean query = this.getUserQueryConditionById(queryId);
		if(query.getQueryDetail()!=null && !"".equals(query.getQueryDetail()))
		{
			AdvanceQueryBean condition = (AdvanceQueryBean)SerializeUtil.formXML(query.getQueryDetail());
			if(condition.getExFields()!=null)
			{
				CustomerExtendFieldBean exField = condition.getExFields();
				this.filterCondition(condition);
				DateUtil.format(condition);
				this.setBelongToCondition(condition, query.getUserId());
		    	Map<String, Object> map=null;
				try {
					map = BeanUtils.describe(condition);
				} catch (Exception e) {
					e.printStackTrace();
				}
		    	String exCondition = this.getExtendFieldCondition(exField);
		    	if(!StringUtil.isNullOrEmpty(exCondition))
		    		map.put("exCondition",exCondition);
		    	String phoneRules = this.getPhoneRuleCondition(condition);
		    	if(!StringUtil.isNullOrEmpty(phoneRules))
		    		map.put("rules",phoneRules);
		    	
		    	return this.advancedCustomerDao.getSmsCustomerCount(map);
			}
		}
		return null;
	}
	
	/**
	 * 查询发送短信的数量
	 * @param conditionIds	条件集合
	 * @param cusIds	客户集合
	 * @return
	 */
	public int querySmsCustomerCount(String conditionIds,String cusIds){
		Set<Integer> set = new HashSet<Integer>();
		if(!StringUtil.isNullOrEmpty(cusIds))
		{
			List<CustomerBean> list = this.advancedCustomerDao.getSmsCustomersByIds(cusIds);
			for(CustomerBean cust : list)
			{
				if(!set.contains(cust.getCustomerId()))
				{
					set.add(cust.getCustomerId());
				}
			}
		}
		
		if(!StringUtil.isNullOrEmpty(conditionIds))
		{
			String[] queryIds = conditionIds.split("\\,");
			for(String queryId : queryIds)
			{
				List<Integer> list = this.querySmsCustomerIdsByQueryId(Integer.parseInt(queryId));
				for(Integer custId : list)
				{
					if(!set.contains(custId))
					{
						set.add(custId);
					}
				}
			}
		}
		
		return set.size();
	}
}
