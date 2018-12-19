package com.banger.mobile.webapp.action.finance;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;


import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.Enum.finance.EnumArticle;
import com.banger.mobile.domain.model.finance.FeArticle;
import com.banger.mobile.domain.model.finance.FeArticleDiscess;
import com.banger.mobile.domain.model.finance.FeArticleReply;
import com.banger.mobile.domain.model.finance.FeDiscessWithReply;
import com.banger.mobile.facade.finance.FeArticleDiscessService;
import com.banger.mobile.facade.finance.FeArticleReplyService;
import com.banger.mobile.facade.finance.FeArticleService;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 10, 2012 3:57:03 PM
 * 类说明
 */
public class FeDiscessAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4885424837238317734L;
	
	private FeArticleDiscessService              feArticleDiscessService;
	private FeArticleReplyService                feArticleReplyService;
	private FeArticleService                     feArticleService;
	
	private PageUtil<FeDiscessWithReply>         discessReplyPage;
	private FeArticle                            feArticle;
	private FeArticleDiscess                     feArticleDiscess;
	private FeArticleReply                       feArticleReply;
	/**
	 * 文章评论分页界面
	 */
	public String showArticleDiscessPage(){
		try {
			showArticleDiscessList();
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("showArticleDiscessPage action error:" + e.getMessage());
			return ERROR;			
		}
	}
	
	/**
	 * 文章评论分页列表
	 */
	public String showArticleDiscessList(){
		try {
			if(feArticle!=null){
				feArticle = feArticleService.getArticle(feArticle.getArticleId());
				String content = StringUtil.removeHTML(feArticle
						.getArticleContent());
				if(content.length() > 150){
					content = content.substring(0, 150)+"...";
				}
				feArticle.setArticleContent(content);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userId", this.getLoginInfo().getUserId());
				map.put("articleId", feArticle.getArticleId());
				this.getPage().setPageSize(5);
				discessReplyPage = feArticleDiscessService.getArticleDiscessPage(map, this.getPage());
				request.setAttribute("count", this.getPage().getTotalRowsAmount());
			}
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("showArticleDiscessList action error:" + e.getMessage());
			return ERROR;
		}		
	}
	
	/**
	 * 发表评论
	 */
	public void addArticleDiscess(){
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
	        response.setContentType("text/html;charset=utf-8");
	        PrintWriter out = response.getWriter();
			if(feArticleDiscess!=null){
				feArticleDiscess.setCreateUser(this.getLoginInfo().getUserId());
				feArticleDiscessService.addDiscess(feArticleDiscess);
				feArticleService.articleOperation(feArticleDiscess.getArticleId(), EnumArticle.ART_DISCESS);
				out.print(feArticleService.getArticle(feArticleDiscess.getArticleId()).getArticleDiscessCount());
			}			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("addArticleDiscess action error:"+ e.getMessage());
		}
	}
	
	/**
	 * 支持评论
	 */
	public void supportDiscess(){
		try {
			if(feArticleDiscess!=null){
				feArticleDiscess.setUpdateUser(this.getLoginInfo().getUserId());
				feArticleDiscessService.supportDiscess(feArticleDiscess);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("supportDiscess action error:"+ e.getMessage());
		}
	}
	
	/**
	 * 回复评论
	 */
	public void replyDiscess(){
		try {
			if(feArticleReply!=null){
				feArticleReply.setCreateUser(this.getLoginInfo().getUserId());
				feArticleReplyService.addReply(feArticleReply);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("replyDiscess action error:"+ e.getMessage());
		}
	}
	
	public FeArticleDiscessService getFeArticleDiscessService() {
		return feArticleDiscessService;
	}
	public void setFeArticleDiscessService(
			FeArticleDiscessService feArticleDiscessService) {
		this.feArticleDiscessService = feArticleDiscessService;
	}
	public FeArticleReplyService getFeArticleReplyService() {
		return feArticleReplyService;
	}
	public void setFeArticleReplyService(FeArticleReplyService feArticleReplyService) {
		this.feArticleReplyService = feArticleReplyService;
	}
	public PageUtil<FeDiscessWithReply> getDiscessReplyPage() {
		return discessReplyPage;
	}
	public void setDiscessReplyPage(PageUtil<FeDiscessWithReply> discessReplyPage) {
		this.discessReplyPage = discessReplyPage;
	}
	public FeArticle getFeArticle() {
		return feArticle;
	}
	public void setFeArticle(FeArticle feArticle) {
		this.feArticle = feArticle;
	}

	public FeArticleService getFeArticleService() {
		return feArticleService;
	}

	public void setFeArticleService(FeArticleService feArticleService) {
		this.feArticleService = feArticleService;
	}

	public FeArticleDiscess getFeArticleDiscess() {
		return feArticleDiscess;
	}

	public void setFeArticleDiscess(FeArticleDiscess feArticleDiscess) {
		this.feArticleDiscess = feArticleDiscess;
	}

	public FeArticleReply getFeArticleReply() {
		return feArticleReply;
	}

	public void setFeArticleReply(FeArticleReply feArticleReply) {
		this.feArticleReply = feArticleReply;
	}
}



