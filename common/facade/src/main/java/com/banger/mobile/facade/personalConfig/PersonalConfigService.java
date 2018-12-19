/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :个人配置所有信息
 * Author     :yujh
 * Create Date:Sep 19, 2012
 */
package com.banger.mobile.facade.personalConfig;

import com.banger.mobile.domain.model.phoneConfig.PhoneSetting;

/**
 * @author 个人配置所有信息
 * @version $Id: PersonalConfigService.java,v 0.1 Sep 19, 2012 4:10:10 PM Administrator Exp $
 */
public interface PersonalConfigService {
	/**
	 * 获得个人配置信息
	 * @param userId
	 * @return
	 */
    public PhoneSetting getPersonConfig(int userId);
}
