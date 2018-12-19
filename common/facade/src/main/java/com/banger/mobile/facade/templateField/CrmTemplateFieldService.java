/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户模版字段...
 * Author     :QianJie
 * Create Date:May 29, 2012
 */
package com.banger.mobile.facade.templateField;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.templateField.CrmTemplateField;

/**
 * @author QianJie
 * @version $Id: CrmTemplateFieldService.java,v 0.1 May 29, 2012 2:39:19 PM QianJie Exp $
 */
public interface CrmTemplateFieldService {

    /**
     * 获取所有客户模版字段集合
     * @return
     */
    public List<CrmTemplateField> getAllCrmTemplateFieldList();
    
    /**
     * 获取所有客户模版字段集合
     * @return
     */
    public List<CrmTemplateField> getAllCrmTemplateFieldList(boolean isCache);
    
    /**
     * 获取所有产品模版字段集合
     * @return
     */
    public List<CrmTemplateField> getAllPdtCrmTemplateFieldList();
    
    /**
     * 根据模版ID得到模版字段集合
     * @return
     */
    public List<CrmTemplateField> getCrmTemplateFieldListByTemplateId(int templateId);
    
    /**
     * 添加一个模版字段
     * @param crmTemplateField
     */
    public int addCrmTemplateField(CrmTemplateField crmTemplateField);
    
    /**
     * 修改一个模版字段
     * @param crmTemplateField
     */
    public void updateCrmTemplateField(CrmTemplateField crmTemplateField);
    
    /**
     * 删除一个模版字段
     * @param id
     */
    public void deleteCrmTemplateField(int id);
    
    /**
     * 根据Id获取模版字段
     * @return
     */
    public CrmTemplateField getCrmTemplateFieldById(int id);
    
    /**
     * 根据模版字段名称获取拥有相同模版字段名称的数据
     * @param crmTemplateField
     * @return
     */
    public List<CrmTemplateField> getSameCrmTemplateFieldByName(CrmTemplateField crmTemplateField);

    /**
     * 获取现有模版字段是弹屏显示的数据
     * @return
     */
    public List<CrmTemplateField> getPopupCrmTemplateField();
    
    /**
     * 上移或下移模版字段
     * @param id
     * @param moveType
     */
    public String moveFieldItems(int id,String moveType,int templateId);
    
    /**
     * 根据数据类型返回未被使用的客户自定义字段，如果没有可以使用的则返回空
     * @param dataType
     * @return
     */
    public String getNotUsedExtField(String dataType);
    
    /**
     * 根据数据类型返回未被使用的产品自定义字段，如果没有可以使用的则返回空
     * @param dataType
     * @return
     */
    public String getNotUsedPdtExtField(String dataType);
    
    /**
     * 获取所有模版自定义字段值
     * @param parameters
     * @return
     */
    public List getCrmCustomerExtFieldByCustomerId(Map<String, Object> parameters);
    
    /**
     * 根据模版Id删除模版字段
     * @param templateId
     */
    public void deleteCrmTemplateFieldByTemplateId(int templateId);
    
    /**
     * 获取某个客户自定义字段值不为空的数据
     * @param parameters
     * @return
     */
    public List getCrmCustomerExtFieldIsNotNull(Map<String, Object> parameters);
    
    /**
     * 获取某个产品自定义字段值不为空的数据
     * @param parameters
     * @return
     */
    public List getPdtProductextFieldIsNotNull(Map<String, Object> parameters);

    /**
     *  获取新增或编辑模版字段是否已存在在基础模版字段中
     * @return
     */
    public boolean getHasSameBasicCrmTemplateField(CrmTemplateField crmTemplateField);
}
