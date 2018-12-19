/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :模版字段DAO
 * Author     :QianJie
 * Create Date:May 29, 2012
 */
package com.banger.mobile.dao.templateField;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.system.CrmCustomerType;
import com.banger.mobile.domain.model.template.CrmTemplate;
import com.banger.mobile.domain.model.templateField.CrmTemplateField;

/**
 * @author QianJie
 * @version $Id: CrmTemplateFieldDao.java,v 0.1 May 29, 2012 1:35:02 PM QianJie Exp $
 */
public interface CrmTemplateFieldDao {

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
     * 根据模版ID获取现有模版字段数据按SORTNO排序最大的
     * @return
     */
    public CrmTemplateField getMaxSortNoCrmTemplateField(int templateId);
    
    /**
     * 根据模版ID获取现有模版字段数据按SORTNO排序最小的
     * @return
     */
    public CrmTemplateField getMinSortNoCrmTemplateField(int templateId);
    
    /**
     * 获取要移动的模版字段对象
     * @return
     */
    public CrmTemplateField getNeedMoveCrmTemplateField(Map<String, Object> parameters);
    
    /**
     * 获取现有模版字段是弹屏显示的数据
     * @return
     */
    public List<CrmTemplateField> getPopupCrmTemplateField();
    
    /**
     * 获取所有客户模版字段
     * @return
     */
    public List<CrmTemplateField> getAllCrmTemplateField();
    
    /**
     * 获取所有客户模版字段
     * @return
     */
    public List<CrmTemplateField> getAllCrmTemplateField(boolean isCache);
    
    /**
     * 获取所有产品模版字段
     * @return
     */
    public List<CrmTemplateField> getAllPdtCrmTemplateField();
    
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
    public List<CrmTemplateField> getHasSameBasicCrmTemplateField(CrmTemplateField crmTemplateField);
}
