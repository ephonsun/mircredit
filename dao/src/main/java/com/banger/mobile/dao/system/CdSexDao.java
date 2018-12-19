/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :性别代码表
 * Author     :zsw
 * Create Date:May 21, 2012
 */
package com.banger.mobile.dao.system;

import java.util.List;

import com.banger.mobile.domain.model.system.CdSex;

public interface CdSexDao {
	
	/**
	 * 反回性别列表
	 * @return
	 */
	List<CdSex> getSexs();
	
}
