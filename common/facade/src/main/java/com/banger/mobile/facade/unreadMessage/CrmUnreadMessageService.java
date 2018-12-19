/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :未读留言 来电...
 * Author     :yangy
 * Create Date:2012-6-11
 */
package com.banger.mobile.facade.unreadMessage;

import com.banger.mobile.domain.model.unreadMessage.CrmUnreadMessage;

/**
 * @author yangyang
 * @version $Id: CrmUnreadMessageService.java,v 0.1 2012-6-11 下午5:05:03 yangyong Exp $
 */
public interface CrmUnreadMessageService {

    /**
     * 查询未接留意，未接来电
     * @return
     */
    public CrmUnreadMessage queryCrmUnreadMessage(Integer userid);
}
