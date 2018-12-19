/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :字段类型维护类...
 * Author     :QianJie
 * Create Date:May 28, 2012
 */
package com.banger.mobile.facade.impl.system.fieldType;

import java.util.List;

import com.banger.mobile.dao.fieldType.CrmFieldTypeDao;
import com.banger.mobile.domain.model.fieldType.CrmFieldType;
import com.banger.mobile.facade.fieldType.CrmFieldTypeService;

/**
 * @author QianJie
 * @version $Id: CrmFieldTypeService.java,v 0.1 May 28, 2012 11:26:10 AM QianJie Exp $
 */
public class CrmFieldTypeServiceImpl implements CrmFieldTypeService{

    private CrmFieldTypeDao crmFieldTypeDao;
    public void setCrmFieldTypeDao(CrmFieldTypeDao crmFieldTypeDao) {
        this.crmFieldTypeDao = crmFieldTypeDao;
    }
    
    /**
     * 获取所有模版字段类型的数据
     * @return
     * @see com.banger.mobile.facade.fieldType.CrmFieldTypeService#getAllCrmFieldType()
     */
    public List<CrmFieldType> getAllCrmFieldType() {
        return crmFieldTypeDao.getAllCrmFieldType();
    }

    

}
