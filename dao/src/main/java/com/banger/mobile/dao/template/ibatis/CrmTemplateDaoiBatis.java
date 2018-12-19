/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :业务模版dao实现
 * Author     :liyb
 * Create Date:2012-5-25
 */
package com.banger.mobile.dao.template.ibatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.template.CrmTemplateDao;
import com.banger.mobile.domain.model.template.CrmTemplate;
import com.banger.mobile.ibatis.GenericDaoiBatis;

public class CrmTemplateDaoiBatis extends GenericDaoiBatis implements CrmTemplateDao  {

	@SuppressWarnings("unchecked")
	public CrmTemplateDaoiBatis(Class persistentClass) {
		super(CrmTemplate.class);
	}
	public CrmTemplateDaoiBatis( ) {
		super(CrmTemplate.class);
	}

	private static final long serialVersionUID = 4249606618406988851L;

	/**
	 * 添加业务模版
	 * @param crmTemplate
	 */
	@Override
	public void addCrmTemplate(CrmTemplate crmTemplate) {
		this.getSqlMapClientTemplate().insert("addCrmTemplate",crmTemplate);
	}

	/**
	 * 删除业务模版
	 * @param id
	 */
	@Override
	public void deleteCrmTemplate(int id) {
		this.getSqlMapClientTemplate().update("deleteCrmTemplateById",id);
	}

	/**
	 * 修改业务模版
	 * @param crmTemplate
	 */
	@Override
	public void updateCrmTemplate(CrmTemplate crmTemplate) {
		this.getSqlMapClientTemplate().update("updateCrmTemplate",crmTemplate);
	}

	/**
	 * 根据id获取业务模版实体信息
	 * @param id
	 * @return
	 */
	@Override
	public CrmTemplate getCrmTemplateById(int id) {
		return (CrmTemplate) this.getSqlMapClientTemplate().queryForObject("getCrmTemplateById", id);
	}

	/**
	 * 分页查询业务模版
	 * @param parameters
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	@Override
	public PageUtil<CrmTemplate> getCrmTemplatePage( Map<String, Object> parameters, Page page) {
		ArrayList<CrmTemplate> list = (ArrayList<CrmTemplate>) this.findQueryPage(
				"getCrmTemplatefoParameterPageMap", "getCrmTemplateCount", parameters, page);
		if (list == null) {
			list = new ArrayList<CrmTemplate>();
		}
		return new PageUtil<CrmTemplate>(list, page);
	}
	/**
	 * 校验业务模版
	 * @param map
	 * @return
	 */
	@Override
	public int CheckCrmTemplateByName(Map<String, Object> map) {
		int count =(Integer)this.getSqlMapClientTemplate().queryForObject("CheckCrmTemplateByName", map);
		return count;
	}
	/**
	 * 获得需要移动的模版
	 * @param map
	 * @return
	 */
	@Override
	public List<CrmTemplate> getNeedMoveCrmTemplate(Map<String , Object> map){
		return this.getSqlMapClientTemplate().queryForList("getNeedMoveCrmTemplate", map);
	}
	/**
	 * 获取所有客户模版
	 * @return
	 */
	@Override
	public List<CrmTemplate> getAllCrmTemplate() {
		return this.getSqlMapClientTemplate().queryForList("getAllCrmTemplate");
	}
	/**
	 * 获取所有产品模版
	 * @return
	 */
	@Override
	public List<CrmTemplate> getAllPdtCrmTemplate() {
		return this.getSqlMapClientTemplate().queryForList("getAllPdtCrmTemplate");
	}

}
