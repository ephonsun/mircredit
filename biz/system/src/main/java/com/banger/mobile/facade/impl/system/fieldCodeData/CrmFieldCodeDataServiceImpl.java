/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :字段代码维护类...
 * Author     :QianJie
 * Create Date:Jun 1, 2012
 */
package com.banger.mobile.facade.impl.system.fieldCodeData;

import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.fieldCodeData.CrmFieldCodeDataDao;
import com.banger.mobile.domain.model.fieldCodeData.CrmFieldCodeData;
import com.banger.mobile.facade.fieldCodeData.CrmFieldCodeDataService;

/**
 * @author QianJie
 * @version $Id: CrmFieldCodeDataServiceImpl.java,v 0.1 Jun 1, 2012 10:10:10 AM QianJie Exp $
 */
public class CrmFieldCodeDataServiceImpl implements CrmFieldCodeDataService{

    private CrmFieldCodeDataDao crmFieldCodeDataDao;
    public void setCrmFieldCodeDataDao(CrmFieldCodeDataDao crmFieldCodeDataDao) {
        this.crmFieldCodeDataDao = crmFieldCodeDataDao;
    }

    /**
     * 添加一个字段代码数据
     */
    public void addCrmFieldCodeData(CrmFieldCodeData crmFieldCodeData) {
        crmFieldCodeDataDao.addCrmFieldCodeData(crmFieldCodeData);
    }

    /**
     * 删除一个字段代码数据
     */
    public void deleteCrmFieldCodeData(int id) {
        crmFieldCodeDataDao.deleteCrmFieldCodeData(id);
    }

    /**
     * 获取所有字段代码数据
     */
    public List<CrmFieldCodeData> getAllCrmFieldCodeData() {
        return crmFieldCodeDataDao.getAllCrmFieldCodeData();
    }

    /**
     * 根据Id获取字段代码数据
     */
    public CrmFieldCodeData getCrmFieldCodeDataById(int id) {
        return crmFieldCodeDataDao.getCrmFieldCodeDataById(id);
    }

    /**
     * 根据字段ID得到字段代码表集合
     */
    public List<CrmFieldCodeData> getCrmFieldCodeDataListByFieldId(int fieldId) {
        return crmFieldCodeDataDao.getCrmFieldCodeDataListByFieldId(fieldId);
    }

    /**
     * 根据模版ID获取现有字段代码数据数据按SORTNO排序最大的
     */
    public CrmFieldCodeData getMaxSortNoCrmFieldCodeData() {
        return crmFieldCodeDataDao.getMaxSortNoCrmFieldCodeData();
    }

    /**
     * 根据模版ID获取现有字段代码数据数据按SORTNO排序最小的
     */
    public CrmFieldCodeData getMinSortNoCrmFieldCodeData() {
        return crmFieldCodeDataDao.getMinSortNoCrmFieldCodeData();
    }

    /**
     * 获取要移动的字段代码数据对象
     */
    public CrmFieldCodeData getNeedMoveCrmFieldCodeData(Map<String, Object> parameters) {
        return crmFieldCodeDataDao.getNeedMoveCrmFieldCodeData(parameters);
    }

    /**
     * 根据字段代码数据名称获取拥有相同字段代码数据名称的数据
     */
    public List<CrmFieldCodeData> getSameCrmFieldCodeDataByName(CrmFieldCodeData crmFieldCodeData) {
        return crmFieldCodeDataDao.getSameCrmFieldCodeDataByName(crmFieldCodeData);
    }

    /**
     * 修改一个字段代码数据
     */
    public void updateCrmFieldCodeData(CrmFieldCodeData crmFieldCodeData) {
        crmFieldCodeDataDao.updateCrmFieldCodeData(crmFieldCodeData);
    }

    /**
     * 根据字段ID删除对应代码表的值
     */
    public void deleteCrmFieldCodeDataByFieldId(int fieldId) {
        crmFieldCodeDataDao.deleteCrmFieldCodeDataByFieldId(fieldId);
    }



    
}
