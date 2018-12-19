package com.banger.mobile.dao.finance.ibatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.finance.FeArticleDao;
import com.banger.mobile.domain.model.finance.FeArticle;
import com.banger.mobile.ibatis.GenericDaoiBatis;

public class FeArticleDaoiBatis extends GenericDaoiBatis implements FeArticleDao {

    public FeArticleDaoiBatis(Class persistentClass) {
        super(FeArticleDao.class);
        // TODO Auto-generated constructor stub
    }

    public FeArticleDaoiBatis() {
        super(FeArticleDao.class);
        // TODO Auto-generated constructor stub
    }

    public Integer insertActicle(FeArticle feArticle) {

        return (Integer) this.getSqlMapClientTemplate().insert("insertFeArticle", feArticle);
    }

    public Integer selectActicle(Map<String, Object> map) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("selectFeArticleByTitle",
                map);
    }

    public Integer updateActicle(FeArticle feArticle) {
        return this.getSqlMapClientTemplate().update("updateFeArticle", feArticle);
    }

    public Integer deleteActicle(Integer acticleId) {
        return this.getSqlMapClientTemplate().update("deleteArticle", acticleId);
    }

    public Integer publishActicle(String acticleIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("articleIds", acticleIds);
        return this.getSqlMapClientTemplate().update("publishFeArticle", map);
    }

    public Integer classifyActicles(String ids, String columnId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("articleIds", ids);
        map.put("articleColumnId", columnId);
        return this.getSqlMapClientTemplate().update("classifyFeArticle", map);
    }

    public FeArticle getArticle(Integer acticleId) {
        return (FeArticle) this.getSqlMapClientTemplate()
                .queryForObject("selectArticle", acticleId);
    }

    public PageUtil<FeArticle> getArticlePageList(Map<String, Object> parameters, Page page) {
        ArrayList<FeArticle> list = (ArrayList<FeArticle>) this.findQueryPage("getArticeList",
                "getArticeListCount", parameters, page);
        if (list == null) {
            list = new ArrayList<FeArticle>();
        }
        return new PageUtil<FeArticle>(list, page);

    }

    public Integer getArticleByColumnId(Map map) {
        Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("getArticleByColumnId", map);

        return count;
    }
    public PageUtil<FeArticle> showFinanceMainPage(
            Map<String, Object> parameters, Page page) {
        ArrayList<FeArticle> list = (ArrayList<FeArticle>) this.findQueryPage(
                "showFinanceMainPage", "showFinanceMainPageCount", parameters, page);
        if (list == null) {
            list = new ArrayList<FeArticle>();
        }
        return new PageUtil<FeArticle>(list, page);
    }

    public Integer getToadyFinanceCount(Map<String, Object> parameters) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getToadyCount",parameters);
    }

    public Integer getMustReadInUnReadCount(Map<String, Object> parameters) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getMustReadInUnReadCount",parameters);
    }

    public Integer getReadedCount(Map<String, Object> parameters) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getReadedCount",parameters);
    }

    public Integer getMustReadInReadCount(Map<String, Object> parameters) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getMustReadInReadCount",parameters);
    }

    public Integer getAttachmentCount(Map<String, Object> parameters) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getAttachmentCount",parameters);
    }


    /**
     * 更新参与数，收藏数，评论数
     * @param map
     */
    public void updateArticleCount(Map<String, Object> map){
        this.getSqlMapClientTemplate().update("updateArticleCount",map);
    }

    public PageUtil<FeArticle> showInChargeOfFinanceMainPage(
            Map<String, Object> parameters, Page page) {
        ArrayList<FeArticle> list = (ArrayList<FeArticle>) this.findQueryPage(
                "showInChargeOfFinanceMainPage", "showInChargeOfFinanceMainPageCount", parameters, page);
        if (list == null) {
            list = new ArrayList<FeArticle>();
        }
        return new PageUtil<FeArticle>(list, page);
    }

}
