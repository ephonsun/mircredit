package com.banger.mobile.facade.feedBack.impl;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.feedBack.FeedBackDao;
import com.banger.mobile.domain.model.feedback.FeedBack;
import com.banger.mobile.facade.feedBack.FeedBackService;

import java.util.Map;

/**
 * 在线反馈模块
 * Created by BH-TCL on 15-3-9.
 */
public class FeedBackServiceImpl implements FeedBackService {

    private FeedBackDao feedBackDao;

    public FeedBackDao getFeedBackDao() {
        return feedBackDao;
    }

    public void setFeedBackDao(FeedBackDao feedBackDao) {
        this.feedBackDao = feedBackDao;
    }

    @Override
    public void insertFeedBack(FeedBack feedBack) {
        feedBackDao.insertFeedBack(feedBack);
    }

    /**
     * 分页查询
     * @param conds
     * @param curPage
     * @return
     */
    @Override
    public PageUtil<FeedBack> queryFeedBacksPage(Map<String, Object> conds, Page curPage) {
        return feedBackDao.queryFeedBacksPage(conds,curPage);
    }

    @Override
    public void updateFeedBack(FeedBack feedBack) {
        feedBackDao.updateFeedBack(feedBack);
    }
}
