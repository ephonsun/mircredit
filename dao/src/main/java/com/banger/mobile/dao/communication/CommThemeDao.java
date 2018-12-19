/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :交流主题Dao接口
 * Author     :liyb
 * Create Date:2012-12-24
 */
package com.banger.mobile.dao.communication;
import java.util.List;
import java.util.Map;
import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.communication.CommTheme;
import com.banger.mobile.domain.model.communication.ThemePanel;

/**
 * @author liyb
 * @version $Id: CommThemeDao.java,v 0.1 2012-12-24 下午02:33:41 liyb Exp $
 */
public interface CommThemeDao {
    /**
     * 交流区分页
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<CommTheme> getCommThemeListPage(Map<String, Object> parameters, Page page);
    
    /**
     * 查询指定的主题(最多显示5条)
     * @param parameters
     * @param page
     * @return
     */
    public List<CommTheme> getTopCommThemeList(Map<String, Object> parameters);
    
    /**
     * 发表主题
     * @param theme
     * @return
     */
    public Integer insertCommTheme(CommTheme theme);
    
    /**
     * 查询用户的某一分区主题COUNT
     * @param parameters
     * @return
     */
    public Integer getUserThemeCount(Map<String, Object> parameters);
    
    /**
     * 统计用户的某一分区收藏COUNT
     * @param parameters
     * @return
     */
    public Integer getUserPartitionCount(Map<String, Object> parameters);
    
    /**
     * 统计用户的某一分区帖子COUNT
     * @param parameters
     * @return
     */
    public Integer getUserReplyCount(Map<String, Object> parameters);
    
    /**
     * 统计置顶的主题数
     * @param parameters
     * @return
     */
    public Integer getTopCommThemeCount(Map<String, Object> parameters);
    
    /**
     * 查看帖子内容
     * @param themeId 帖子ID
     * @return
     */
    public CommTheme getCommThemeById(Integer themeId);
    
    /**
     * 修改帖子查看数(查看一次+1)
     * @param themeId 帖子ID
     * @return
     */
    public Integer updateThemeReadCount(Integer themeId);
    
    /**
     * 根据版块ID统计主题数
     * @param templateId
     * @return
     */
    public Integer getTemplateThemeCount(Integer templateId);
    
    /**
     * 帖子置顶
     * @param theme
     * @return
     */
    public Integer themeIsTop(CommTheme theme);
    
    /**
     * 编辑主题
     * @param theme
     * @return
     */
    public Integer updateCommTheme(CommTheme theme);
    
    /**
     * 删除主题/投票
     * @param theme
     * @return
     */
    public Integer deleteCommTheme(CommTheme theme);
    
    /**
     * 修改帖子回复数(新增回复+1，删除回复-1)
     * @param parameters
     * @return
     */
    public Integer updateThemeReplyCount(Map<String, Object> parameters);
    
    /**
     * 管理面板、个人主题、个人帖子、个人收藏
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<ThemePanel> getThemePanelListPage(Map<String, Object> parameters, Page page);
    
    /**
     * 帖子是否删除
     * @param themeId
     * @return
     */
    public Integer isThemeDelete(Integer themeId);
}
