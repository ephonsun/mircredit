package com.banger.mobile.dao.feedBack;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.feedback.FeedBack;

import java.util.List;
import java.util.Map;

/**
 * Created by BH-TCL on 15-3-9.
 */
public interface FeedBackDao {
    void insertFeedBack(FeedBack feedBack);

    PageUtil<FeedBack> queryFeedBacksPage(Map<String, Object> conds, Page curPage);

    void updateFeedBack(FeedBack feedBack);
}
