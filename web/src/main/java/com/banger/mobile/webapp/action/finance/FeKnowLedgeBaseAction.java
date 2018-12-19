package com.banger.mobile.webapp.action.finance;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.Enum.finance.EnumArticle;
import com.banger.mobile.domain.Enum.finance.EnumFinance;
import com.banger.mobile.domain.model.finance.FeKnowledgebaseContent;
import com.banger.mobile.domain.model.finance.FeKnowledgebaseType;
import com.banger.mobile.domain.model.finance.FeUserRelation;
import com.banger.mobile.facade.finance.FeArticleService;
import com.banger.mobile.facade.finance.FeKnowLedgeContentService;
import com.banger.mobile.facade.finance.FeKnowledgeTypeService;
import com.banger.mobile.facade.finance.FeUserRelationService;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 4, 2012 3:40:46 PM
 * 类说明
 */
public class FeKnowLedgeBaseAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 92621974962838912L;
	
	//service 注入
	private FeKnowledgeTypeService             feKnowledgeTypeService;         //知识库service
	private FeKnowLedgeContentService          feKnowLedgeContentService;      //收藏内容service
	private FeUserRelationService              feUserRelationService;		   //财经--用户关系
	private FeArticleService                   feArticleService;
	//界面参数
	private FeKnowledgebaseType                knowledgeType;                   //知识库分类
	private JSONArray                          typeJson;	                    //知识库分类树
	private PageUtil<FeKnowledgebaseContent>   knowContentPage;                 //所有收藏内容分页
	private FeKnowledgebaseContent             knowContent;                     //收藏内容
	private String                             parentId;
	private String                             parentTypeName;
	//界面搜索元素
	private String                             startDate;
	private String                             endDate;
	/**
	 * 新建知识库分类页面
	 * @return
	 */
	public String addKnowledgeType(){
		try{
			parentTypeName = feKnowledgeTypeService.getFeKnowledgebaseTypeById(Integer.parseInt(parentId)).getKnowledgebaseTypeName();
			showTypeTree();
			return SUCCESS;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("addKnowledgeType action error: " + e.getMessage());
			return ERROR;
		}
	}	
	
	/**
	 * 展示知识库分类树
	 */
	public void showTypeTree(){
		try{
			typeJson = feKnowledgeTypeService.getSelfKnowledgeTypeTree(this.getLoginInfo().getUserId());
		}catch(Exception e){
			e.printStackTrace();
			log.error("showTypeTree action error:" + e.getMessage());			
		}
	}
	
	/**
	 * 保存知识库分类
	 */
	public void saveKnowledgeType(){
		try{
			HttpServletResponse response = ServletActionContext.getResponse();
	        response.setContentType("text/html;charset=utf-8");
	        PrintWriter out = response.getWriter();
			if(knowledgeType!=null){
				int userId = this.getLoginInfo().getUserId();
				if(knowledgeType.getKnowledgebaseTypeId()== null){					
					knowledgeType.setUserId(userId);
					knowledgeType.setCreateUser(userId);
					knowledgeType.setIsDel(0);
					if(feKnowledgeTypeService.isExistSameNameInSameLevel(knowledgeType)){
						out.print("同层中已存在相同的分类名，请重新输入");
				        out.close();
					}else {
						feKnowledgeTypeService.addKnowledgeType(knowledgeType);
					}
											
				}else{
					knowledgeType.setIsDel(0);
					knowledgeType.setUserId(userId);
					knowledgeType.setUpdateUser(userId);
					if(feKnowledgeTypeService.parentIsSelfChildren(knowledgeType)){
						out.print("fail");
				        out.close();	
					}else {
						if(feKnowledgeTypeService.isExistSameNameInSameLevel(knowledgeType)){
								out.print("同层中已存在相同的分类名，请重新输入");
						        out.close();
						}else {
							feKnowledgeTypeService.updateKnowledgeType(knowledgeType);	
						}
					}
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("saveKnowledgeType action error: " + e.getMessage());
		}
	}
	
	/**
	 * 显示知识库分类页面
	 * @return
	 */
	public String showKnowledgeBasePage(){
		try{
			showTypeTree();
			return SUCCESS;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("showKnowledgeBaseType action error: " + e.getMessage());
			return ERROR;
		}
	}
	
	/**
	 * 显示知识库收藏内容列表
	 * @return
	 */
	public String showKnowledgeBaseList(){
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", this.getLoginInfo().getUserId());
			if(knowContent!=null){
				if(knowContent.getKnowledgebaseTypeId()!=null){
					map.put("typeId", knowContent.getKnowledgebaseTypeId());
				}
				if(!StringUtil.isBlank(knowContent.getKnowledgebaseTitle())){
					map.put("title", StringUtil.ReplaceSQLChar(knowContent.getKnowledgebaseTitle()));
				}
				if(!StringUtil.isBlank(knowContent.getKnowledgebaseNote())){
					map.put("note", StringUtil.ReplaceSQLChar(knowContent.getKnowledgebaseNote()));
				}
			}		
			if(!StringUtil.isBlank(startDate)){
				map.put("startDate", StringUtil.ReplaceSQLChar(startDate + " 00:00:00"));
			}
			if(!StringUtil.isBlank(endDate)){
				map.put("endDate", StringUtil.ReplaceSQLChar(endDate + " 23:59:59"));
			}
			this.getPage().setPageSize(5);
			knowContentPage = feKnowLedgeContentService.getKnowLedgeContentList(map, this.getPage());
			request.setAttribute("count", this.getPage().getTotalRowsAmount());
			return SUCCESS;			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("showKnowledgeBaseList action error:"+ e.getMessage());
			return ERROR;
		}
	}
	
	/**
	 * 上移知识库分类
	 */
	public void moveUpKnowledgeType(){
		try{
			if(knowledgeType!=null){
				feKnowledgeTypeService.moveUpKnowledgeType(knowledgeType);
			}	
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("moveUpKnowledgeType action error:" + e.getMessage());
		}
	}
	/**
	 * 下移知识库分类
	 */
	public void moveDownknowledgeType(){
		try{
			if(knowledgeType!=null){
				feKnowledgeTypeService.moveDownKnowledgeType(knowledgeType);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("moveDownknowledgeType action error:" + e.getMessage());
		}
	}
	/**
	 * 删除知识库分类
	 */
	public void deleteKnowType(){
		try {
			if(knowledgeType!=null){
				feKnowledgeTypeService.deleteKnowledgeType(knowledgeType);
			}			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("deleteKnowType action error:" + e.getMessage());
		}
		
	}
	
	/**
	 * 跳转到编辑页面
	 * @return
	 */
	public String toUpdateKnowType(){
		try{
			if(knowledgeType!= null){
				showTypeTree();
				knowledgeType = feKnowledgeTypeService.getFeKnowledgebaseTypeById(knowledgeType.getKnowledgebaseTypeId());
			}
			return SUCCESS;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("toUpdateKnowType action error:" + e.getMessage());
			return ERROR;
		}
	}
	/**
	 * 跳至编辑收藏内容页面
	 * @return
	 */
	public String toEditKnowContent(){
		try {
			if(knowContent!=null){
				knowContent = feKnowLedgeContentService.getKnowContentById(knowContent.getKnowledgebaseContentId());
				showTypeTree();
			}
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("toEditKnowContent action error:" + e.getMessage());
			return ERROR;
		}
	}
	
	/**
	 * 跳转到新建分类
	 * @return
	 */
	public String toAddKnowContent(){
		try {
			if(knowContent!=null){
				knowContent.setKnowledgebaseTitle(feArticleService.getArticle(knowContent.getArticleId()).getArticleTitle());
			}
			showTypeTree();
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("toAddKnowContent action error:" + e.getMessage());
			return ERROR;
		}
	}
	
	/**
	 * 编辑收藏内容
	 */
	public void saveKnowContent() {
		try {
			if(knowContent!=null){
				HttpServletResponse response = ServletActionContext.getResponse();
		        response.setContentType("text/html;charset=utf-8");
		        PrintWriter out = response.getWriter();
				if(knowContent.getKnowledgebaseContentId()==null){
					knowContent.setUserId(this.getLoginInfo().getUserId());
					knowContent.setCreateUser(this.getLoginInfo().getUserId());									
					FeUserRelation feUserRelation = new FeUserRelation();
					feUserRelation.setFeId(knowContent.getArticleId());
					feUserRelation.setIsCollect(1);
					feUserRelation.setUserId(this.getLoginInfo().getUserId());
					feUserRelation.setRelationType(0);
					feKnowLedgeContentService.addKnowLedgeContent(knowContent);
					feArticleService.articleOperation(knowContent.getArticleId(), EnumArticle.ART_ADD_COLLECT);	
					feUserRelationService.userRelationOperate(feUserRelation, EnumFinance.FE_COLLECT);
					out.print(feArticleService.getArticle(knowContent.getArticleId()).getArticleCollectCount());
				}else{
					knowContent.setUpdateUser(this.getLoginInfo().getUserId());
					feKnowLedgeContentService.editKnowLedgeContent(knowContent);					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("saveKnowContent action error：" + e.getMessage());
		}
	}
	
	/**
	 * 删除收藏内容
	 */
	public void deleteKnowContent(){
		try {
			if(knowContent!=null){
				knowContent.setUpdateDate(new Date());
				knowContent.setUpdateUser(this.getLoginInfo().getUserId());
				FeUserRelation feUserRelation = new FeUserRelation();
				feUserRelation.setIsCollect(0);
				feUserRelation.setUserId(this.getLoginInfo().getUserId());
				feUserRelation.setRelationType(0);
				feUserRelation.setFeId(knowContent.getArticleId());
				feKnowLedgeContentService.deleteKnowLedgeContent(knowContent);
				feArticleService.articleOperation(knowContent.getArticleId(), EnumArticle.ART_DEL_COLLECT);
				feUserRelationService.userRelationOperate(feUserRelation, EnumFinance.FE_COLLECT);
			}			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("deleteKnowContent action error:"+e.getMessage());
		}
	}	

	public FeKnowledgebaseType getKnowledgeType() {
		return knowledgeType;
	}

	public void setKnowledgeType(FeKnowledgebaseType knowledgeType) {
		this.knowledgeType = knowledgeType;
	}


	public FeKnowledgeTypeService getFeKnowledgeTypeService() {
		return feKnowledgeTypeService;
	}


	public void setFeKnowledgeTypeService(
			FeKnowledgeTypeService feKnowledgeTypeService) {
		this.feKnowledgeTypeService = feKnowledgeTypeService;
	}

	public JSONArray getTypeJson() {
		return typeJson;
	}

	public void setTypeJson(JSONArray typeJson) {
		this.typeJson = typeJson;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}


	public String getParentTypeName() {
		return parentTypeName;
	}


	public void setParentTypeName(String parentTypeName) {
		this.parentTypeName = parentTypeName;
	}

	public PageUtil<FeKnowledgebaseContent> getKnowContentPage() {
		return knowContentPage;
	}

	public void setKnowContentPage(PageUtil<FeKnowledgebaseContent> knowContentPage) {
		this.knowContentPage = knowContentPage;
	}

	public FeKnowLedgeContentService getFeKnowLedgeContentService() {
		return feKnowLedgeContentService;
	}

	public void setFeKnowLedgeContentService(
			FeKnowLedgeContentService feKnowLedgeContentService) {
		this.feKnowLedgeContentService = feKnowLedgeContentService;
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

	public FeKnowledgebaseContent getKnowContent() {
		return knowContent;
	}

	public void setKnowContent(FeKnowledgebaseContent knowContent) {
		this.knowContent = knowContent;
	}

	public FeUserRelationService getFeUserRelationService() {
		return feUserRelationService;
	}

	public void setFeUserRelationService(FeUserRelationService feUserRelationService) {
		this.feUserRelationService = feUserRelationService;
	}

	public FeArticleService getFeArticleService() {
		return feArticleService;
	}

	public void setFeArticleService(FeArticleService feArticleService) {
		this.feArticleService = feArticleService;
	}

}



