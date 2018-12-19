/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :模版字段类型
 * Author     :QianJie
 * Create Date:May 28, 2012
 */
package com.banger.mobile.dao.fieldType;

import java.util.List;

import com.banger.mobile.domain.model.fieldType.CrmFieldType;

/**
 * @author QianJie
 * @version $Id: CrmFieldType.java,v 0.1 May 28, 2012 11:14:18 AM QianJie Exp $
 */
public interface CrmFieldTypeDao {

    /**
     * 获取所有模版字段类型
     * @return
     */
    public List<CrmFieldType> getAllCrmFieldType();
}
