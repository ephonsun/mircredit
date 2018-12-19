package com.banger.mobile.dao.feedBack.ibatis;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.feedBack.FeedBackDao;
import com.banger.mobile.domain.model.feedback.FeedBack;
import com.banger.mobile.ibatis.GenericDaoiBatis;
import com.banger.mobile.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BH-TCL on 15-3-9.
 */
public class FeedBackDaoiBatis extends GenericDaoiBatis implements FeedBackDao {
    /**
     * Constructor that takes in a class to see which type of entity to persist
     *
     * @param persistentClass the class type you'd like to persist
     */
    public FeedBackDaoiBatis(Class persistentClass) {
        super(persistentClass);
    }

    public FeedBackDaoiBatis(){
        super(FeedBack.class);
    }

    @Override
    public void insertFeedBack(FeedBack feedBack) {
        this.getSqlMapClientTemplate().insert("insertFeedBack",feedBack);
    }

    @Override
    public PageUtil<FeedBack> queryFeedBacksPage(Map<String, Object> conds, Page curPage) {
        Map<String, Object> fConds = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : conds.entrySet()) {
            if (entry.getValue() instanceof String) {
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil
                        .ReplaceSQLChar(entry.getValue().toString())));
            } else {
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        List<FeedBack> list = (List<FeedBack>) this.findQueryPage("queryFeedBacksPage", "queryFeedBacksPageCount",
                fConds, curPage);
        return new PageUtil<FeedBack>(list, curPage);
    }

    @Override
    public void updateFeedBack(FeedBack feedBack) {
        this.getSqlMapClientTemplate().update("updateFeedBack",feedBack);
    }
}
