/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :百家姓
 * Author     :xuhj
 * Version    :1.0
 * Create Date:May 2, 2012
 */
package com.banger.mobile.dao.customer;

import java.util.List;

import com.banger.mobile.domain.model.base.customer.BaseFamilyName;

public interface FamilyNameDao {
   /**
    * 获得所有百家姓
    * @return
    */
   public List<BaseFamilyName> getNickName();
}
