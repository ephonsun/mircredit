package com.banger.mobile.facade.template;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil; 
import com.banger.mobile.domain.model.template.CrmTemplate;
import com.banger.mobile.domain.model.templateField.CrmTemplateField;

public interface CrmTemplateService {
 
	/**
	 * 新增业务模版
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
     * 根据id获得业务模版实体
     * @param id
     * @return
     */
    public CrmTemplate getCrmTemplateById(int id);
 
    /**
     * 分页查询模版
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<CrmTemplate> getCrmTemplatePage(Map<String, Object> parameters, Page page);
    
    /**
     * 根据name校验业务模版
     * @param map
     * @return
     */
    public int CheckCrmTemplateByName(Map<String, Object> map);
    
    /**
     * 移动业务模版
     * @param id
     * @param moveType
     * @return
     */
    public String moveCrmTemplate(int id,String moveType )  ;
    /**
     * 获取所有客户模版集合
     * @return
     */
    public List<CrmTemplate> getAllCrmTemplate();
    
    /**
     * 获取所有产品模版集合
     * @return
     */
    public List<CrmTemplate> getAllPdtCrmTemplate();
    
    /**
     * 获得所有自定义字段
     * @return
     */
    public List<CrmTemplateField> getAllTemplateFields();
    
    /**
     * 得到来电弹屏自定义字段
     * @return
     */
    public List<CrmTemplateField> getCallInFields();
    
    /**
     * 得到自定义模板信息
     * @return
     */
    public List<CrmTemplate> getTemplates();
  
}
