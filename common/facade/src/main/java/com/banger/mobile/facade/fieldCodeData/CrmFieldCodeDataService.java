/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :字段代码表维护...
 * Author     :QianJie
 * Create Date:Jun 1, 2012
 */
package com.banger.mobile.facade.fieldCodeData;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.fieldCodeData.CrmFieldCodeData;

/**
 * @author QianJie
 * @version $Id: CrmFieldCodeDataService.java,v 0.1 Jun 1, 2012 10:09:29 AM QianJie Exp $
 */
public interface CrmFieldCodeDataService {

    /**
     * 根据字段ID得到字段代码表集合
     * @return
     */
    public List<CrmFieldCodeData> getCrmFieldCodeDataListByFieldId(int fieldId);
    
    /**
     * 添加一个字段代码数据
     * @param crmFieldCodeData
     */
    public void addCrmFieldCodeData(CrmFieldCodeData crmFieldCodeData);
    
    /**
     * 修改一个字段代码数据
     * @param crmFieldCodeData
     */
    public void updateCrmFieldCodeData(CrmFieldCodeData crmFieldCodeData);
    
    /**
     * 删除一个字段代码数据
     * @param id
     */
    public void deleteCrmFieldCodeData(int id);
    
    /**
     * 根据Id获取字段代码数据
     * @return
     */
    public CrmFieldCodeData getCrmFieldCodeDataById(int id);
    
    /**
     * 根据字段代码数据名称获取拥有相同字段代码数据名称的数据
     * @param crmFieldCodeData
     * @return
     */
    public List<CrmFieldCodeData> getSameCrmFieldCodeDataByName(CrmFieldCodeData crmFieldCodeData);
    
    /**
     * 根据模版ID获取现有字段代码数据数据按SORTNO排序最大的
     * @return
     */
    public CrmFieldCodeData getMaxSortNoCrmFieldCodeData();
    
    /**
     * 根据模版ID获取现有字段代码数据数据按SORTNO排序最小的
     * @return
     */
    public CrmFieldCodeData getMinSortNoCrmFieldCodeData();
    
    /**
     * 获取要移动的字段代码数据对象
     * @return
     */
    public CrmFieldCodeData getNeedMoveCrmFieldCodeData(Map<String, Object> parameters);
    
    
    /**
     * 获取所有字段代码数据
     * @return
     */
    public List<CrmFieldCodeData> getAllCrmFieldCodeData();
    
    /**
     * 根据字段ID删除对应代码表的值
     * @param fieldId
     */
    public void deleteCrmFieldCodeDataByFieldId(int fieldId);
}
