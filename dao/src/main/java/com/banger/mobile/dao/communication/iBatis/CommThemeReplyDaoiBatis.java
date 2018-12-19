/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :liyb
 * Create Date:2012-12-26
 */
package com.banger.mobile.dao.communication.iBatis;

import java.util.ArrayList;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.communication.CommThemeReplyDao;
import com.banger.mobile.domain.model.communication.CommThemeReply;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author liyb
 * @version $Id: CommThemeReplyDaoiBatis.java,v 0.1 2012-12-26 下午01:54:13 liyb Exp $
 */
public class CommThemeReplyDaoiBatis extends GenericDaoiBatis implements CommThemeReplyDao {

    public CommThemeReplyDaoiBatis(){
        super(CommThemeReply.class);
    }
    /**
     * 查询回复帖子
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<CommThemeReply> getCommThemeReplyListPage(Map<String, Object> parameters,Page page) {
        ArrayList<CommThemeReply> list = (ArrayList<CommThemeReply>) this.findQueryPage(
                "GetThemeReplyPageMap", "GetThemeReplyCount", parameters, page);
        if (list == null) {
            list = new ArrayList<CommThemeReply>();
        }
        return new PageUtil<CommThemeReply>(list, page);
    }

    /**
     * 删除主题回复
     * @param parameters
     * @return
     */
    public Integer deleteThemeReply(Map<String, Object> parameters) {
        return this.getSqlMapClientTemplate().update("DeleteThemeReply",parameters);
    }

    /**
     * 新建回复
     * @param reply
     * @return
     */
    public Integer insertThemeReply(CommThemeReply reply) {
        return (Integer) this.getSqlMapClientTemplate().insert("InsertThemeReply",reply);
    }

    /**
     * 编辑回复
     * @param reply
     * @return
     */
    public Integer updateThemeReply(CommThemeReply reply) {
        return this.getSqlMapClientTemplate().update("UpdateThemeReply",reply);
    }

    /**
     * 查询回复信息
     * @param replyId
     * @return
     */
    public CommThemeReply getThemeReplyById(Integer replyId) {
        return (CommThemeReply) this.getSqlMapClientTemplate().queryForObject("GetThemeReplyById",replyId);
    }

}
