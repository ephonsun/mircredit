/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :模版字段维护...
 * Author     :QianJie
 * Create Date:May 28, 2012
 */
package com.banger.mobile.facade.fieldType;

import java.util.List;

import com.banger.mobile.domain.model.fieldType.CrmFieldType;


/**
 * @author QianJie
 * @version $Id: CrmFieldTypeService.java,v 0.1 May 28, 2012 11:27:22 AM QianJie Exp $
 */
public interface CrmFieldTypeService {

    /**
     * 获取所有模版字段类型的数据
     * @return
     */
    public List<CrmFieldType> getAllCrmFieldType();

}
