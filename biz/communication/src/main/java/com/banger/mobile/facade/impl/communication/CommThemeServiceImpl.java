/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :交流主题service实现类
 * Author     :liyb
 * Create Date:2012-12-24
 */
package com.banger.mobile.facade.impl.communication;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.communication.CommThemeDao;
import com.banger.mobile.domain.model.communication.CommTheme;
import com.banger.mobile.domain.model.communication.ThemePanel;
import com.banger.mobile.facade.communication.CommThemeService;
import com.banger.mobile.util.DateUtil;

/**
 * @author liyb
 * @version $Id: CommThemeServiceImpl.java,v 0.1 2012-12-24 下午03:04:30 liyb Exp $
 */
public class CommThemeServiceImpl implements CommThemeService {
    
    private CommThemeDao commThemeDao;

    public void setCommThemeDao(CommThemeDao commThemeDao) {
        this.commThemeDao = commThemeDao;
    }

    /**
     * 交流区分页
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<CommTheme> getCommThemeListPage(Map<String, Object> parameters, Page page) {
        return commThemeDao.getCommThemeListPage(parameters, page);
    }
    
    /**
     * 查询指定的主题(最多显示5条)
     * @param parameters
     * @param page
     * @return
     */
    public List<CommTheme> getTopCommThemeList(Map<String, Object> parameters) {
        return commThemeDao.getTopCommThemeList(parameters);
    }

    /**
     * 发表主题
     * @param theme
     * @return
     */
    public Integer insertCommTheme(CommTheme theme) {
        return commThemeDao.insertCommTheme(theme);
    }
    
    /**
     * 查询某一分区当天 的主题COUNT
     * @param parameters
     * @return
     */
    public Integer getDateThemeCount(Map<String, Object> parameters) {
        parameters.put("createDate", DateUtil.getDateToString(new Date()));
        return commThemeDao.getUserThemeCount(parameters);
    }
    
    /**
     * 查询一分区主题COUNT
     * @param partitionId 分区ID
     * @return
     */
    public Integer getUserThemeCount(Integer partitionId) {
        Map<String, Object> parameters=new HashMap<String, Object>();
        parameters.put("partitionId", partitionId);
        return commThemeDao.getUserThemeCount(parameters);
    }

    /**
     * 查询用户的某一分区主题COUNT
     * @param partitionId 分区ID
     * @param userId 用户ID
     * @return
     */
    public Integer getUserThemeCount(Integer partitionId, Integer userId) {
        Map<String, Object> parameters=new HashMap<String, Object>();
        parameters.put("partitionId", partitionId);
        parameters.put("userId", userId);
        return commThemeDao.getUserThemeCount(parameters);
    }

    /**
     * 统计用户的某一分区收藏COUNT
     * @param partitionId 分区ID
     * @param userId 用户ID
     * @return
     */
    public Integer getUserPartitionCount(Integer partitionId, Integer userId) {
        Map<String, Object> parameters=new HashMap<String, Object>();
        parameters.put("partitionId", partitionId);
        parameters.put("userId", userId);
        return commThemeDao.getUserPartitionCount(parameters);
    }
    
    /**
     * 统计某一分区帖子COUNT
     * @param partitionId 分区ID
     * @return
     */
    public Integer getUserReplyCount(Integer partitionId) {
        Map<String, Object> parameters=new HashMap<String, Object>();
        parameters.put("partitionId", partitionId);
        return commThemeDao.getUserReplyCount(parameters);
    }

    /**
     * 统计用户的某一分区帖子COUNT
     * @param partitionId 分区ID
     * @param userId 用户ID
     * @return
     */
    public Integer getUserReplyCount(Integer partitionId, Integer userId) {
        Map<String, Object> parameters=new HashMap<String, Object>();
        parameters.put("partitionId", partitionId);
        parameters.put("userId", userId);
        return commThemeDao.getUserReplyCount(parameters);
    }

    /**
     * 统计置顶的主题数
     * @param parameters
     * @return
     */
    public Integer getTopCommThemeCount(Map<String, Object> parameters) {
        return commThemeDao.getTopCommThemeCount(parameters);
    }

    /**
     * 查看帖子内容
     * @param themeId 帖子ID
     * @return
     */
    public CommTheme getCommThemeById(Integer themeId) {
        return commThemeDao.getCommThemeById(themeId);
    }

    /**
     * 修改帖子查看数(查看一次+1)
     * @param themeId 帖子ID
     * @return
     */
    public Integer updateThemeReadCount(Integer themeId) {
        return commThemeDao.updateThemeReadCount(themeId);
    }

    /**
     * 根据版块ID统计主题数
     * @param templateId
     * @return
     */
    public Integer getTemplateThemeCount(Integer templateId) {
        return commThemeDao.getTemplateThemeCount(templateId);
    }

    /**
     * 帖子置顶
     * @param themeId 帖子ID
     * @param userId 用户ID
     * @return
     */
    public Integer themeIsTop(Integer themeId, Integer userId,Integer isTop) {
        CommTheme theme=new CommTheme();
        theme.setThemeId(themeId);
        theme.setUpdateUser(userId);
        theme.setIsTop(isTop);
        return commThemeDao.themeIsTop(theme);
    }

    /**
     * 编辑主题
     * @param theme
     * @return
     */
    public Integer updateCommTheme(CommTheme theme) {
        return commThemeDao.updateCommTheme(theme);
    }

    /**
     * 删除主题/投票
     * @param themeId 帖子ID
     * @param userId 用户ID
     * @return
     */
    public Integer deleteCommTheme(Integer themeId, Integer userId) {
        CommTheme theme=new CommTheme();
        theme.setThemeId(themeId);
        theme.setUpdateUser(userId);
        return commThemeDao.deleteCommTheme(theme);
    }

    /**
     * 修改帖子回复数(新增回复+1，删除回复-1)
     * @param themeId 主题ID
     * @param flag 1：新增    2：删除
     * @return
     */
    public Integer updateThemeReplyCount(Integer themeId, Integer flag) {
        Map<String,Object> parameters=new HashMap<String, Object>();
        parameters.put("themeId", themeId);
        if(flag==1){
            parameters.put("add", 1);
        }else if(flag==2){
            parameters.put("del", 2);
        }
        return commThemeDao.updateThemeReplyCount(parameters);
    }

    /**
     * 管理面板、个人主题、个人帖子、个人收藏
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<ThemePanel> getThemePanelListPage(Map<String, Object> parameters, Page page) {
        return commThemeDao.getThemePanelListPage(parameters, page);
    }

    /**
     * 帖子是否删除
     * @param themeId
     * @return
     */
    public Integer isThemeDelete(Integer themeId) {
        return commThemeDao.isThemeDelete(themeId);
    }

}
