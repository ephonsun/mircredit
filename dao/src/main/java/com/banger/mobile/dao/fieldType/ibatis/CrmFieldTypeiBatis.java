/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :模版字段类型
 * Author     :QianJie
 * Create Date:May 28, 2012
 */
package com.banger.mobile.dao.fieldType.ibatis;

import java.util.List;

import com.banger.mobile.dao.fieldType.CrmFieldTypeDao;
import com.banger.mobile.domain.model.fieldType.CrmFieldType;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author QianJie
 * @version $Id: CrmFieldTypeiBatis.java,v 0.1 May 28, 2012 11:14:40 AM QianJie Exp $
 */
public class CrmFieldTypeiBatis extends GenericDaoiBatis implements CrmFieldTypeDao{

    public CrmFieldTypeiBatis() {
        super(CrmFieldType.class);
    }
    
    public CrmFieldTypeiBatis(Class persistentClass) {
        super(CrmFieldType.class);
    }

    /**
     * 获取所有模版字段类型
     * @return
     * @see com.banger.mobile.dao.fieldType.CrmFieldTypeDao#getAllCrmFieldType()
     */
    @Override
    public List<CrmFieldType> getAllCrmFieldType() {
        return this.getSqlMapClientTemplate().queryForList("getAllCrmFieldType");
    }

}
