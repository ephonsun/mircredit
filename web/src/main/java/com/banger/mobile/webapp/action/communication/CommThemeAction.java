/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :交流主题Action
 * Author     :liyb
 * Create Date:2012-12-24
 */
package com.banger.mobile.webapp.action.communication;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.communication.CommPartition;
import com.banger.mobile.domain.model.communication.CommTemplate;
import com.banger.mobile.domain.model.communication.CommTheme;
import com.banger.mobile.domain.model.communication.CommThemeOption;
import com.banger.mobile.domain.model.communication.CommThemeReply;
import com.banger.mobile.domain.model.communication.CommUserRelation;
import com.banger.mobile.domain.model.communication.ThemePanel;
import com.banger.mobile.facade.communication.CommThemeOptionService;
import com.banger.mobile.facade.communication.CommThemeReplyService;
import com.banger.mobile.facade.communication.CommThemeService;
import com.banger.mobile.facade.communication.CommUserRelationService;
import com.banger.mobile.facade.communication.CommonTemplateService;
import com.banger.mobile.facade.communication.PartitionService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author liyb
 * @version $Id: CommThemeAction.java,v 0.1 2012-12-24 下午03:12:12 liyb Exp $
 */
public class CommThemeAction extends BaseAction {

	private static final long serialVersionUID = 8933764565079789149L;
	private CommThemeService commThemeService; // 交流主题service
	private PartitionService partitionService; // 交流分区service
	private CommonTemplateService commonTemplateService; // 交流分区模版service
	private CommThemeReplyService commThemeReplyService; // 主题回复service
	private CommUserRelationService commUserRelationService; // 用户/交流关系service
	private CommThemeOptionService commThemeOptionService; // 主题投票service
	private CommTheme theme; // 发表主题Bean
	private CommPartition partition; // 交流分区Bean
	private CommTemplate temp;//版块Bean
	private CommThemeReply reply;
	private CommUserRelation relation; // 用户/交流关系Bean
	private PageUtil<CommTheme> themePage; // 主题分页
	private PageUtil<CommThemeReply> replyPage; // 主题回复分页
	private PageUtil<ThemePanel> panelPage;
	private List<CommTheme> topThemeList;
	private List<CommTemplate> templateList;

	private String content_html; // 内容
	private Integer partitionId; // 分区ID
	private Integer templateId; // 模版ID
	private String userName; // 用户名称
	private Integer userId; // 用户ID
	private Integer templateUserId; // 板块管理员ID
	private String startDate;
	private String endDate;
	private Integer dateFlag;

	/**
	 * 返回COUNT
	 */
	private Integer themeCount = 0;
	private Integer collectCount = 0;
	private Integer replyCount = 0;
	private Integer partitionThemeCount = 0;
	private Integer partitionReplyCount = 0;
	private Integer dateThemeCount = 0;
	private Integer commThemeCount = 0;
	private String themeIds;

	private Integer themeFlag;
	private Integer replyFlag;
	private Integer panelFlag;// 0:管理面板 1:个人主题 2:个人帖子 3:个人收藏
	private Integer cols = 4;

	/**
	 * 初始化交流区数据
	 * 
	 * @return
	 */
	public String initThemePage() {
		try {
			userName = getLoginInfo().getUserName();
			userId = getLoginInfo().getUserId();
			// 分区主题总数
			partitionThemeCount = commThemeService
					.getUserThemeCount(partitionId);
			// 分区帖子总数
			partitionReplyCount = commThemeService
					.getUserReplyCount(partitionId);
			// 返回交流区实体
			partition = partitionService.getCommPartition(partitionId);
			// 主题总数
			themeCount = commThemeService.getUserThemeCount(partitionId,
					getLoginInfo().getUserId());
			// 收藏总数
			collectCount = commThemeService.getUserPartitionCount(partitionId,
					getLoginInfo().getUserId());
			// 帖子总数
			replyCount = commThemeService.getUserReplyCount(partitionId,
					getLoginInfo().getUserId());

			// 初始化交流分区模版
			templateList = commonTemplateService
					.getCommTemplateListByPid(partitionId);
			if (templateList.size() > 0) {
				templateId = templateList.get(0).getTemplateId();
				templateUserId = templateList.get(0).getCreateUser();
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.equals("initThemePage action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 主题查询
	 * 
	 * @return
	 */
	public String getCommThemeListPage() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("partitionId", partitionId);
			map.put("templateId", templateId);
			if (theme != null && !StringUtil.isBlank(theme.getThemeTitle())) {
				map.put("themeTitle", StringUtil.ReplaceSQLChar(theme.getThemeTitle().trim()));
			}
			if (theme != null && theme.getThemeType() != null
					&& theme.getThemeType() == 2) {
				map.put("themeType", theme.getThemeType());
			}
			if (dateFlag != null) {
				String sDate = "";
				if (dateFlag == 3) {// 一天
//					sDate = DateUtil.getDateToString(DateUtil.getRelativeDate(
//							new Date(), -1));
					sDate = DateUtil.getDateToString(new Date());
				} else if (dateFlag == 4) {// 二天
					sDate = DateUtil.getDateToString(DateUtil.getRelativeDate(
							new Date(), -1));
				} else if (dateFlag == 5) {// 一周
					sDate = DateUtil.getDateToString(DateUtil.getRelativeDate(
							new Date(), -7));
				} else if (dateFlag == 6) {// 一个月
					sDate = DateUtil.getAfterNMonth(-1);
				} else if (dateFlag == 7) {// 三个月
					sDate = DateUtil.getAfterNMonth(-3);
				}
				map.put("sDate", sDate);
				map.put("eDate", DateUtil.getDateToString(new Date()));
			}
			// 置顶主题
			topThemeList = commThemeService.getTopCommThemeList(map);
			// 板块主题
			this.getPage().setPageSize(10);
			themePage = commThemeService.getCommThemeListPage(map,
					this.getPage());
			// 分区模块今日主题总数
			dateThemeCount = commThemeService.getDateThemeCount(map);
			// 分区模块主题总数
			Integer topCount = commThemeService.getTopCommThemeCount(map);
			commThemeCount = topCount + this.getPage().getTotalRowsAmount();
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getCommThemeListPage action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 发表主题
	 * 
	 * @return
	 */
	public String insertTheme() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			if (theme == null) {
			    // 返回交流区实体
	            partition = partitionService.getCommPartition(partitionId);
	            temp=commonTemplateService.getCommTemplate(templateId);
				return "addTheme";
			} else {
				out = response.getWriter();
				theme.setCreateUser(getLoginInfo().getUserId());
				Integer count = commThemeService.insertCommTheme(theme);
				out.print(count);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("insertTheme action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 查看帖子内容
	 * 
	 * @return
	 */
	public String themeDetail() {
		try {
			userId = getLoginInfo().getUserId();
			Map<String, Object> map = new HashMap<String, Object>();
			theme = commThemeService.getCommThemeById(theme.getThemeId());
			map.put("themeId", theme.getThemeId());
			this.getPage().setPageSize(10);
			replyPage = commThemeReplyService.getCommThemeReplyListPage(map,
					this.getPage());
			CommPartition commPartition = partitionService.getCommPartition(theme.getPartitionId());
			if(commPartition.getUserId().equals(userId)){
				request.setAttribute("isPartitionManager", 1);
			}else{
				request.setAttribute("isPartitionManager", 0);
			}
			// 查询投票帖子是否已经有人投票过
			Integer isUserVote = commUserRelationService.isUserRelation(2,
					theme.getThemeId(), null, 2);
			request.setAttribute("isUserVote", isUserVote);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("themeDetail action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 修改查看数+1
	 */
	public void updateReadCount() {
		if (theme != null) {
			commThemeService.updateThemeReadCount(theme.getThemeId());
		}
	}

	/**
	 * 验证是否收藏/投票
	 */
	public void isUserRelation() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			Integer i = commUserRelationService.isUserRelation(theme
					.getThemeType(), theme.getThemeId(), getLoginInfo()
					.getUserId(), 1);
			out.print(i);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("isUserRelation action error:" + e.getMessage());
		}
	}

	/**
	 * 收藏/投票
	 */
	public void insertUserRelation() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			relation.setUserId(getLoginInfo().getUserId());
			commUserRelationService.insertUserRelation(relation);
			out.print(1);
		} catch (Exception e) {
			out.print(0);
			e.printStackTrace();
			log.error("insertUserRelation action error:" + e.getMessage());
		}
	}
    
    
    /**
     * 帖子置顶
     */
    public void themeTop(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            commThemeService.themeIsTop(theme.getThemeId(), getLoginInfo().getUserId(),theme.getIsTop());
            out.print(1);
        } catch (Exception e) {
            out.print(0);
            e.printStackTrace();
            log.error("themeTop action error:"+e.getMessage());
        }
    }
    
    /**
     * 编辑主题
     * @return
     */
    public String updateCommTheme(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = null;
        try {
            if(themeFlag==0){//初始化主题
                theme=commThemeService.getCommThemeById(theme.getThemeId());
                return "toUpTheme";
            }else{//编辑主题
                out = response.getWriter();
                theme.setUpdateUser(getLoginInfo().getUserId());
                Integer i=commThemeService.updateCommTheme(theme);
                out.print(i);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("updateCommTheme action error:"+e.getMessage());
            return ERROR;
        } 
    }
    
    /**
     * 删除主题/投票
     */
    public void deleteCommTheme(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            Integer i=commThemeService.deleteCommTheme(theme.getThemeId(), getLoginInfo().getUserId());
            out.print(i);
            if(i>0){
                Map<String,Object> map=new HashMap<String, Object>();
                map.put("themeId", theme.getThemeId());
                commThemeReplyService.deleteThemeReply(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("deleteCommTheme action error:"+e.getMessage());
        } 
    }
    
    /**
     * 新建回复
     * @return
     */
    public void saveThemeReply(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            reply.setCreateUser(getLoginInfo().getUserId());
            Integer i=commThemeReplyService.insertThemeReply(reply);
            out.print(i);
            if(i>0){
                commThemeService.updateThemeReplyCount(reply.getThemeId(), 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("saveThemeReply action error:"+e.getMessage());
        }
    }
    
    /**
     * 删除回复
     */
    public void deleteThemeReply(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("replyId", reply.getReplyId());
            Integer i=commThemeReplyService.deleteThemeReply(map);
            out.print(i);
            if(i>0){
                commThemeService.updateThemeReplyCount(reply.getThemeId(), 2);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("deleteThemeReply action error:"+e.getMessage());
        } 
    }
    
    /**
     * 修改回复
     */
    public String updateThemeReply(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = null;
        try {
            if(replyFlag==0){
                reply=commThemeReplyService.getThemeReplyById(reply.getReplyId());
                return "toReplyUpdate";
            }else{
                out = response.getWriter();
                reply.setUpdateUser(getLoginInfo().getUserId());
                Integer i=commThemeReplyService.updateThemeReply(reply);
                out.print(i);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("updateThemeReply action error:"+e.getMessage());
            return ERROR;
        } 
    }
    /**
     * 管理面板、个人主题、个人帖子、个人收藏
     * @return
     */
    public String getThemePanelPage(){
        try {
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("userId", getLoginInfo().getUserId());
            map.put("partitionId", partitionId);
            if(panelFlag==2){
                cols=3;
                map.put("replyTheme", 2);
                map.put("orderby", " ORDER BY LAST_REPLAY_DATE DESC ");
            }else if(panelFlag==3){
                cols=5;
                map.put("relation", 3);
                map.put("orderby", " ORDER BY COLLECT_DATE DESC ");
            }else if(panelFlag==1){
                map.put("theme", 1);
                map.put("orderby", " ORDER BY THEME.CREATE_DATE DESC ");
            }else if(panelFlag==0){
                cols=6;
                templateList=commonTemplateService.getCommTemplateListByPid(partitionId);
                map.put("orderby", " ORDER BY THEME.CREATE_DATE DESC ");
                if(theme!=null){
                    if(theme.getTemplateId()!=null){
                        map.put("templateId", theme.getTemplateId());
                    }
                    if(!StringUtil.isBlank(theme.getThemeTitle())){
                        map.put("themeTitle", StringUtil.ReplaceSQLChar(theme.getThemeTitle().trim()));
                    }
                }
                if(!StringUtil.isBlank(startDate)){
                    map.put("startDate", startDate);
                }
                if(!StringUtil.isBlank(endDate)){
                    map.put("endDate", endDate);
                }
            }
            panelPage=commThemeService.getThemePanelListPage(map, this.getPage());
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("getThemePanelPage action error:"+e.getMessage());
            return ERROR;
        }
    }


	/**
	 * 删除收藏
	 */
	public void deleteCollect() {
		try {
			Integer i = 0;
			if (panelFlag == 0) {// 删除帖子
				String[] tids = themeIds.split(",");
				Map<String, Object> map = new HashMap<String, Object>();
				for (int j = 0; j < tids.length; j++) {
					i = commThemeService.deleteCommTheme(Integer
							.parseInt(tids[j]), getLoginInfo().getUserId());
					if (i > 0) {
						map.put("themeId", Integer.parseInt(tids[j]));
						commThemeReplyService.deleteThemeReply(map);
						map.clear();
					}
				}
			} else if (panelFlag == 3) {// 删除收藏
				commUserRelationService.deleteCollect(themeIds, getLoginInfo()
						.getUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("deleteCollect action error:" + e.getMessage());
		}
	}

	/**
	 * 帖子是否删除
	 */
	public void isCommThemeDel() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			Integer i = commThemeService.isThemeDelete(theme.getThemeId());
			out.print(i);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("isCommThemeDel action error:" + e.getMessage());
		}
	}

	/**
	 * 发表投票
	 * 
	 * @return
	 */
	public String insertOption() {
		try {
			if (theme == null) {
				// 返回交流区实体
	            partition = partitionService.getCommPartition(partitionId);
	            temp=commonTemplateService.getCommTemplate(templateId);
				return "addThemeOption";
			} else {
				String options = request.getParameter("optionList");
				JSONArray jsonArray = JSONArray.fromObject(options);
				List<CommThemeOption> list = jsonArray.toList(jsonArray,
						CommThemeOption.class);
				theme.setCreateUser(getLoginInfo().getUserId());
				Integer themeId = commThemeService.insertCommTheme(theme);
				for (CommThemeOption c : list) {
					c.setThemeId(themeId);
					c.setSelectOptionCount(0);
				}
				commThemeOptionService.insertOption(list);
				renderText(themeId.toString());
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("insertOption action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 编辑投票主题
	 * 
	 * @return
	 */
	public String updateCommOption() {
		try {
			if (theme == null) {
				Integer themeId = Integer.parseInt(request
						.getParameter("themeId"));
				theme = commThemeService.getCommThemeById(themeId);
				List<CommThemeOption> optionList = commThemeOptionService
						.getOptionList(themeId);
				request.setAttribute("optionList", optionList);
				return "updateCommOption";
			} else {
				String options = request.getParameter("optionList");
				JSONArray jsonArray = JSONArray.fromObject(options);
				List<CommThemeOption> list = jsonArray.toList(jsonArray,
						CommThemeOption.class);
				theme.setUpdateUser(getLoginInfo().getUserId());
				if(null == theme.getCountVotesDays()){
					theme.setCountVotesDays(0);
				}
				commThemeService.updateCommTheme(theme);
				commThemeOptionService.deleteOption(theme.getThemeId());
				commThemeOptionService.insertOption(list);
				renderText("1");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("insertOption action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 查看投票帖子内容
	 * 
	 * @return
	 */
	public String themeOptionDetail() {
		try {
			userId = getLoginInfo().getUserId();
			Map<String, Object> map = new HashMap<String, Object>();
			theme = commThemeService.getCommThemeById(theme.getThemeId());
			Integer countDays = 1;
			String expriteDate = "";
			if (null != theme.getCountVotesDays() && theme.getCountVotesDays() > 0) {// 等于0则表示不过期
				// 截止时间
				expriteDate = DateUtil  
						.convertDateToString("yyyy-MM-dd",DateUtil.getRelativeDate(theme.getCreateDate(),theme.getCountVotesDays()));
				countDays = DateUtil.countDays(
						DateUtil.convertDateToString("yyyy-MM-dd", new Date()),
						expriteDate, "yyyy-MM-dd");
			}
			request.setAttribute("expriteDate", expriteDate);
			request.setAttribute("countDays", countDays);
			List<CommThemeOption> optionList = commThemeOptionService
					.getOptionList(theme.getThemeId());
			request.setAttribute("optionList", optionList);
			// 判断用户是否已经投过票
			Integer isUserVote = commUserRelationService.isUserRelation(2,
					theme.getThemeId(), this.getLoginInfo().getUserId(), 2);
			request.setAttribute("isUserVote", isUserVote);
			map.put("themeId", theme.getThemeId());
			this.getPage().setPageSize(10);
			replyPage = commThemeReplyService.getCommThemeReplyListPage(map,
					this.getPage());
			CommPartition commPartition = partitionService.getCommPartition(theme.getPartitionId());
			if(commPartition.getUserId().equals(userId)){
				request.setAttribute("isPartitionManager", 1);
			}else{
				request.setAttribute("isPartitionManager", 0);
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("themeDetail action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 显示投票用户页面
	 * 
	 * @return
	 */
	public String showVoteUserPage() {
		try {
			String showVoteFlag = request.getParameter("showVoteFlag");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("commId", theme.getThemeId());

			if ("0".equals(showVoteFlag)) {
				List<CommThemeOption> optionList = commThemeOptionService
						.getOptionList(theme.getThemeId());
				request.setAttribute("optionList", optionList);
				List<CommUserRelation> urList = commUserRelationService
						.getUserRelationList(map);
				request.setAttribute("urList", urList);
				return SUCCESS;
			} else {
				String isOptionSelect = request.getParameter("isOptionSelect");
				if (!StringUtil.isBlank(isOptionSelect)) {
					map.put("isOptionSelect", isOptionSelect);
				}
				List<CommUserRelation> urList = commUserRelationService
						.getUserRelationList(map);
				JSONArray json = JSONArray.fromObject(urList);
				renderText(json.toString());
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("showVoteUserPage action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 投票
	 */
	public void voteCommTheme() {
		try {
			String options = request.getParameter("optionList");
			JSONArray jsonArray = JSONArray.fromObject(options);
			List<CommThemeOption> list = jsonArray.toList(jsonArray,
					CommThemeOption.class);
			// 更新选项票数
			commThemeOptionService.updateOption(list);
			List<CommUserRelation> urList = new ArrayList<CommUserRelation>();
			for (CommThemeOption c : list) {
				CommUserRelation relation = new CommUserRelation();
				relation.setUserId(getLoginInfo().getUserId());
				relation.setCommId(c.getThemeId());
				relation.setRelationType(2);
				relation.setIsThemeSelect(1);
				relation.setIsOptionSelect(c.getOptionId());
				urList.add(relation);
			}
			commUserRelationService.insertUserRelations(urList);
			renderText("1");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("voteCommTheme action error:" + e.getMessage());
		}

	}

	public void setCommThemeService(CommThemeService commThemeService) {
		this.commThemeService = commThemeService;
	}

	public CommTheme getTheme() {
		return theme;
	}

	public void setTheme(CommTheme theme) {
		this.theme = theme;
	}

	public String getContent_html() {
		return content_html;
	}

	public void setContent_html(String contentHtml) {
		content_html = contentHtml;
	}

	public Integer getPartitionId() {
		return partitionId;
	}

	public void setPartitionId(Integer partitionId) {
		this.partitionId = partitionId;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public Integer getThemeCount() {
		return themeCount;
	}

	public void setThemeCount(Integer themeCount) {
		this.themeCount = themeCount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getCollectCount() {
		return collectCount;
	}

	public void setCollectCount(Integer collectCount) {
		this.collectCount = collectCount;
	}

	public void setPartitionService(PartitionService partitionService) {
		this.partitionService = partitionService;
	}

	public CommPartition getPartition() {
		return partition;
	}

	public void setPartition(CommPartition partition) {
		this.partition = partition;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public Integer getPartitionThemeCount() {
		return partitionThemeCount;
	}

	public void setPartitionThemeCount(Integer partitionThemeCount) {
		this.partitionThemeCount = partitionThemeCount;
	}

	public Integer getPartitionReplyCount() {
		return partitionReplyCount;
	}

	public void setPartitionReplyCount(Integer partitionReplyCount) {
		this.partitionReplyCount = partitionReplyCount;
	}

	public Integer getDateThemeCount() {
		return dateThemeCount;
	}

	public void setDateThemeCount(Integer dateThemeCount) {
		this.dateThemeCount = dateThemeCount;
	}

	public PageUtil<CommTheme> getThemePage() {
		return themePage;
	}

	public void setThemePage(PageUtil<CommTheme> themePage) {
		this.themePage = themePage;
	}

	public List<CommTheme> getTopThemeList() {
		return topThemeList;
	}

	public void setTopThemeList(List<CommTheme> topThemeList) {
		this.topThemeList = topThemeList;
	}

	public Integer getCommThemeCount() {
		return commThemeCount;
	}

	public void setCommThemeCount(Integer commThemeCount) {
		this.commThemeCount = commThemeCount;
	}

	public void setCommonTemplateService(
			CommonTemplateService commonTemplateService) {
		this.commonTemplateService = commonTemplateService;
	}

	public List<CommTemplate> getTemplateList() {
		return templateList;
	}

	public void setTemplateList(List<CommTemplate> templateList) {
		this.templateList = templateList;
	}

	public void setCommThemeReplyService(
			CommThemeReplyService commThemeReplyService) {
		this.commThemeReplyService = commThemeReplyService;
	}

	public PageUtil<CommThemeReply> getReplyPage() {
		return replyPage;
	}

	public void setReplyPage(PageUtil<CommThemeReply> replyPage) {
		this.replyPage = replyPage;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTemplateUserId() {
		return templateUserId;
	}

	public void setTemplateUserId(Integer templateUserId) {
		this.templateUserId = templateUserId;
	}

	public void setCommUserRelationService(
			CommUserRelationService commUserRelationService) {
		this.commUserRelationService = commUserRelationService;
	}

	public CommUserRelation getRelation() {
		return relation;
	}

	public void setRelation(CommUserRelation relation) {
		this.relation = relation;
	}

	public void setCommThemeOptionService(
			CommThemeOptionService commThemeOptionService) {
		this.commThemeOptionService = commThemeOptionService;
	}

	public Integer getThemeFlag() {
		return themeFlag;
	}

	public void setThemeFlag(Integer themeFlag) {
		this.themeFlag = themeFlag;
	}

	public CommThemeReply getReply() {
		return reply;
	}

	public void setReply(CommThemeReply reply) {
		this.reply = reply;
	}

	public Integer getReplyFlag() {
		return replyFlag;
	}

	public void setReplyFlag(Integer replyFlag) {
		this.replyFlag = replyFlag;
	}

	public PageUtil<ThemePanel> getPanelPage() {
		return panelPage;
	}

	public void setPanelPage(PageUtil<ThemePanel> panelPage) {
		this.panelPage = panelPage;
	}

	public Integer getPanelFlag() {
		return panelFlag;
	}

	public void setPanelFlag(Integer panelFlag) {
		this.panelFlag = panelFlag;
	}

	public Integer getCols() {
		return cols;
	}

	public void setCols(Integer cols) {
		this.cols = cols;
	}

	public String getThemeIds() {
		return themeIds;
	}

	public void setThemeIds(String themeIds) {
		this.themeIds = themeIds;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getDateFlag() {
		return dateFlag;
	}

	public void setDateFlag(Integer dateFlag) {
		this.dateFlag = dateFlag;
	}

    public CommTemplate getTemp() {
        return temp;
    }

    public void setTemp(CommTemplate temp) {
        this.temp = temp;
    }
}
