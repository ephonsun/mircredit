/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :主题回复service接口
 * Author     :liyb
 * Create Date:2012-12-26
 */
package com.banger.mobile.facade.communication;

import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.communication.CommThemeReply;

/**
 * @author liyb
 * @version $Id: CommThemeReplyService.java,v 0.1 2012-12-26 下午02:03:36 liyb Exp $
 */
public interface CommThemeReplyService {
    /**
     * 查询回复帖子
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<CommThemeReply> getCommThemeReplyListPage(Map<String, Object> parameters, Page page);
    
    /**
     * 删除主题回复
     * @param parameters
     * @return
     */
    public Integer deleteThemeReply(Map<String, Object> parameters);
    
    /**
     * 新建回复
     * @param reply
     * @return
     */
    public Integer insertThemeReply(CommThemeReply reply);
    
    /**
     * 编辑回复
     * @param reply
     * @return
     */
    public Integer updateThemeReply(CommThemeReply reply);
    
    /**
     * 查询回复信息
     * @param replyId
     * @return
     */
    public CommThemeReply getThemeReplyById(Integer replyId);
}
