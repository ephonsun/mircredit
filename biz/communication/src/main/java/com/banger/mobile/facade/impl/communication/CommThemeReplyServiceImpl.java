/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :主题回复service接口实现
 * Author     :liyb
 * Create Date:2012-12-26
 */
package com.banger.mobile.facade.impl.communication;

import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.communication.CommThemeReplyDao;
import com.banger.mobile.domain.model.communication.CommThemeReply;
import com.banger.mobile.facade.communication.CommThemeReplyService;

/**
 * @author liyb
 * @version $Id: CommThemeReplyServiceImpl.java,v 0.1 2012-12-26 下午02:06:24 liyb Exp $
 */
public class CommThemeReplyServiceImpl implements CommThemeReplyService {

    private CommThemeReplyDao commThemeReplyDao;
    
    public void setCommThemeReplyDao(CommThemeReplyDao commThemeReplyDao) {
        this.commThemeReplyDao = commThemeReplyDao;
    }

    /**
     * 查询回复帖子
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<CommThemeReply> getCommThemeReplyListPage(Map<String, Object> parameters,Page page) {
        return commThemeReplyDao.getCommThemeReplyListPage(parameters, page);
    }

    /**
     * 删除主题回复
     * @param parameters
     * @return
     */
    public Integer deleteThemeReply(Map<String, Object> parameters) {
        return commThemeReplyDao.deleteThemeReply(parameters);
    }

    /**
     * 新建回复
     * @param reply
     * @return
     */
    public Integer insertThemeReply(CommThemeReply reply) {
        return commThemeReplyDao.insertThemeReply(reply);
    }

    /**
     * 编辑回复
     * @param reply
     * @return
     */
    public Integer updateThemeReply(CommThemeReply reply) {
        return commThemeReplyDao.updateThemeReply(reply);
    }

    /**
     * 查询回复信息
     * @param replyId
     * @return
     */
    public CommThemeReply getThemeReplyById(Integer replyId) {
        return commThemeReplyDao.getThemeReplyById(replyId);
    }

}
