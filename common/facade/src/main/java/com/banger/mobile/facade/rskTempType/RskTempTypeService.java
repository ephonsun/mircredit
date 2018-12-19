/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :风险测评模板类型
 * Author     :yujh
 * Create Date:Jul 16, 2012
 */
package com.banger.mobile.facade.rskTempType;

import java.util.List;

import com.banger.mobile.domain.model.rskTempType.RskTempType;

/**
 * @author yujh
 * @version $Id: RskTempTypeService.java,v 0.1 Jul 16, 2012 12:06:31 PM Administrator Exp $
 */
public interface RskTempTypeService {
	/**
	 * 风险测评模板类型列表
	 * @return
	 */
    public List<RskTempType> getRskTempTypeList();
}
