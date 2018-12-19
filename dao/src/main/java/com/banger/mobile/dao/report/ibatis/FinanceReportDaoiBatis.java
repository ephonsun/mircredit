/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :财经要点报表Dao实现
 * Author     :liyb
 * Create Date:2012-12-10
 */
package com.banger.mobile.dao.report.ibatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.report.FinanceReportDao;
import com.banger.mobile.domain.model.base.finance.BaseFeArticle;
import com.banger.mobile.domain.model.report.FinanceReportBean;
import com.banger.mobile.domain.model.report.UserRelationReportBean;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author liyb
 * @version $Id: FinanceReportDaoiBatis.java,v 0.1 2012-12-10 下午01:50:41 liyb Exp $
 */
public class FinanceReportDaoiBatis extends GenericDaoiBatis implements FinanceReportDao {

    public FinanceReportDaoiBatis(){
        super(FinanceReportBean.class);
    }

    /**
     * 查询财经要点报表
     * @param map
     * @return
     */
    public List<FinanceReportBean> getFinanceReportList(Map<String, Object> map) {
        return this.getSqlMapClientTemplate().queryForList("GetFinanceReportList",map);
    }

    /**
     * 财经要点用户/财经报表统计List
     * @param map
     * @return
     */
    public List<UserRelationReportBean> getUserRelationReportList(Map<String, Object> map) {
        return this.getSqlMapClientTemplate().queryForList("GetUserRelationReportList", map);
    }

    /**
     * 查询财经报表基础数据存储过程
     * @param map
     * @return
     */
    public void getFinanceReportProcedure(Map<String, Object> map,String sql) {
        this.getSqlMapClientTemplate().queryForList(sql,map);
    }

    /**
     * 财经要点报表明细
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<BaseFeArticle> getFianceArticlePage(Map<String, Object> parameters, Page page) {
        ArrayList<BaseFeArticle> list = (ArrayList<BaseFeArticle>) this.findQueryPage(
                "GetFianceReportArticlePage", "GetFianceReportArticleCount", parameters, page);
        if (list == null) {
            list = new ArrayList<BaseFeArticle>();
        }
        return new PageUtil<BaseFeArticle>(list, page);
    }

}
