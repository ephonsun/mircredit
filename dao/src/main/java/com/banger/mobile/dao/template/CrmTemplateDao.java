/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :业务模版dao接口
 * Author     :liyb
 * Create Date:2012-5-25
 */
package com.banger.mobile.dao.template;
 
import java.util.List;
import java.util.Map; 
 
 

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.template.CrmTemplate;


public interface CrmTemplateDao { 
	
	/**
	 * 添加业务模版
	 * @param crmTemplate
	 */
    public void addCrmTemplate(CrmTemplate crmTemplate);
 
    /**
     * 删除业务模版
     * @param id
     */
    public void deleteCrmTemplate(int id);
     
    /**
     * 修改业务模版
     * @param crmTemplate
     */
    public void updateCrmTemplate(CrmTemplate crmTemplate);
 
    /**
     * 根据id获取业务模版实体信息
     * @param id
     * @return
     */
    public CrmTemplate getCrmTemplateById(int id);
    
    /**
     * 校验业务模版
     * @param map
     * @return
     */
    public int CheckCrmTemplateByName(Map<String, Object> map);
		 
    /**
     * 获得需要移动的模版
     * @param map
     * @return
     */
	public List<CrmTemplate> getNeedMoveCrmTemplate(Map<String , Object> map);
    /**
     * 分页查询业务模版
     * @param parameters
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageUtil<CrmTemplate> getCrmTemplatePage(Map<String, Object> parameters, Page page);

    /**
     * 获取所有客户模版
     * @return
     */
    public List<CrmTemplate> getAllCrmTemplate();
    
    /**
     * 获取所有产品模版
     * @return
     */
    public List<CrmTemplate> getAllPdtCrmTemplate();
}
