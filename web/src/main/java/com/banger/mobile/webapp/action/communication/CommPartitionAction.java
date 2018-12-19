package com.banger.mobile.webapp.action.communication;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.communication.CommPartition;
import com.banger.mobile.domain.model.communication.CommTemplate;
import com.banger.mobile.facade.communication.CommThemeService;
import com.banger.mobile.facade.communication.CommonTemplateService;
import com.banger.mobile.facade.communication.PartitionService;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * 交流区维护Action
 * 
 * @author huyb
 * 
 */
public class CommPartitionAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7053048689879639473L;

	private PartitionService partitionService;// 分区service
	private CommonTemplateService commonTemplateService;// 版块service
	private CommThemeService commThemeService;// 主题service
	private JSONArray partitionJson;

	public CommThemeService getCommThemeService() {
		return commThemeService;
	}

	public void setCommThemeService(CommThemeService commThemeService) {
		this.commThemeService = commThemeService;
	}

	private CommPartition commPartition;
	private PageUtil<CommTemplate> commTemplatePage;
	private CommTemplate commTemplate;

	public void setPartitionService(PartitionService partitionService) {
		this.partitionService = partitionService;
	}

	/**
	 * 交流区维护列表
	 * 
	 * @return
	 */
	public String getPartitionList() {
		try {
			partitionJson = partitionService.getCommPartitionList();
			Map<String, Object> map = new HashMap<String, Object>();
			commTemplatePage = commonTemplateService.getCommTemplateList(map,
					this.getPage());
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("CommPartitionAction getPartitionList action error:"
					+ e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 交流区维护列表(搜索)
	 * 
	 * @return
	 */
	public String getPartitionListBySearch() {
		try {
			partitionJson = partitionService.getCommPartitionList();
			Map<String, Object> map = new HashMap<String, Object>();
			if (null != commTemplate) {
				if (!StringUtil.isBlank(commTemplate.getTemplateName())) {
					map.put("templateName", StringUtil.ReplaceSQLChar(commTemplate.getTemplateName()));
				}
				if (null != commTemplate.getPartitionId()) {
					map.put("partitionId", commTemplate.getPartitionId());
				}
			}
			commTemplatePage = commonTemplateService.getCommTemplateList(map,
					this.getPage());
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("CommPartitionAction getPartitionList action error:"
					+ e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 跳转编辑版块页面
	 * 
	 * @return
	 */
	public String toUpdateTemplatePage() {
		try {
			List<CommPartition> list = partitionService.getAllCommPartitions();
			request.setAttribute("list", list);
			commTemplate = commonTemplateService.getCommTemplate(commTemplate
					.getTemplateId());
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("CommPartitionAction toUpdatePartitionPage action error:"
					+ e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 编辑版块
	 */
	public void updateTemplate() {
		try {
			if (null != commTemplate) {
				commTemplate.setUpdateDate(new Timestamp(new Date().getTime()));
				commTemplate.setUpdateUser(this.getLoginInfo().getUserId());
				Integer i = commonTemplateService.updateTemplate(commTemplate);
				String result = (i > 0) ? "0" : i.toString();
				renderText(result);
			} else {
				renderText("-1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("CommPartitionAction updateTemplate action error:"
					+ e.getMessage());
			renderText("-1");
		}
	}

	/**
	 * 删除版块
	 */
	public void deleteTemplate() {
		try {
			if (null != commTemplate) {
				// 判断版块下面是否有贴子存在
				if (commThemeService.getTemplateThemeCount(commTemplate
						.getTemplateId()) > 0) {
					renderText("-1");
				} else {
					commTemplate.setUpdateDate(new Timestamp(new Date()
							.getTime()));
					commTemplate.setUpdateUser(this.getLoginInfo().getUserId());
					commTemplate.setIsDel(1);
					commonTemplateService.deleteTemplate(commTemplate);
					renderText("0");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("CommPartitionAction deleteTemplate action error:"
					+ e.getMessage());
			renderText("-2");
		}

	}

	/**
	 * 移动版块
	 */
	public void moveTemplate() {
		try {
			if (null != commTemplate) {
				commTemplate.setUpdateDate(new Timestamp(new Date().getTime()));
				commTemplate.setUpdateUser(this.getLoginInfo().getUserId());
				String type = request.getParameter("moveType");
				if ("up".equals(type)) {
					commonTemplateService.moveUpTemplate((commTemplate));
				} else {
					commonTemplateService.moveDownTemplate((commTemplate));
				}
				renderText("SUCCESS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("CommPartitionAction moveTemplate action error:"
					+ e.getMessage());
		}
	}

	/**
	 * 跳转新增版块页面
	 * 
	 * @return
	 */
	public String toInsertTemplatePage() {
		try {
			List<CommPartition> list = partitionService.getAllCommPartitions();
			request.setAttribute("list", list);
			request.setAttribute("pId", request.getParameter("pId"));
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("CommPartitionAction toUpdatePartitionPage action error:"
					+ e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 新增版块
	 */
	public void insertTemplate() {
		try {
			if (null != commTemplate) {
				commTemplate.setCreateDate(new Timestamp(new Date().getTime()));
				commTemplate.setCreateUser(this.getLoginInfo().getUserId());
				commTemplate.setIsBuiltin(0);
				commTemplate.setIsDel(0);
				Integer i = commonTemplateService.insertTemplate(commTemplate);
				String result = (i > 0) ? "0" : i.toString();
				renderText(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("CommPartitionAction insertTemplate action error:"
					+ e.getMessage());
		}
	}

	/**
	 * 跳转新增页面
	 * 
	 * @return
	 */
	public String toInsertPartitionPage() {
		return "toAddPartition";
	}

	/**
	 * 跳转编辑页面
	 * 
	 * @return
	 */
	public String toUpdatePartitionPage() {
		try {
			commPartition = partitionService.getCommPartition(commPartition
					.getPartitionId());
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("CommPartitionAction toUpdatePartitionPage action error:"
					+ e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 插入分区
	 */
	public void insertPartition() {
		try {
			if (null != commPartition) {
				commPartition
						.setCreateDate(new Timestamp(new Date().getTime()));
				commPartition.setCreateUser(this.getLoginInfo().getUserId());
				commPartition.setIsBuiltin(0);
				commPartition.setIsDel(0);
				Integer i = partitionService.insertPartition(commPartition);
				
				String result = (i > 0) ? "0" : i.toString();
				renderText(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("CommPartitionAction insertPartition action error:"
					+ e.getMessage());
		}
	}

	/**
	 * 编辑分区
	 * 
	 * @return
	 */
	public void updatePartition() {
		try {
			commPartition.setUpdateDate(new Timestamp(new Date().getTime()));
			commPartition.setUpdateUser(this.getLoginInfo().getUserId());
			Integer i = partitionService.updatePartition(commPartition);
			String result = (i > 0) ? "0" : i.toString();
			renderText(result);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("CommPartitionAction updatePartition action error:"
					+ e.getMessage());
		}
	}

	/**
	 * 删除分区
	 */
	public void deletePartition() {
		try {
			if (null != commPartition) {
				commPartition
						.setUpdateDate(new Timestamp(new Date().getTime()));
				commPartition.setUpdateUser(this.getLoginInfo().getUserId());
				commPartition.setIsDel(1);
				partitionService.deletePartition((commPartition));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("CommPartitionAction deletePartition action error:"
					+ e.getMessage());
		}
	}

	/**
	 * 移动分区
	 */
	public void movePartition() {
		try {
			if (null != commPartition) {
				commPartition
						.setUpdateDate(new Timestamp(new Date().getTime()));
				commPartition.setUpdateUser(this.getLoginInfo().getUserId());
				String type = request.getParameter("moveType");
				if ("up".equals(type)) {
					partitionService.moveUpPartition((commPartition));
				} else {
					partitionService.moveDownPartition((commPartition));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("CommPartitionAction deletePartition action error:"
					+ e.getMessage());
		}
	}

	public CommPartition getCommPartition() {
		return commPartition;
	}

	public void setCommPartition(CommPartition commPartition) {
		this.commPartition = commPartition;
	}

	public PartitionService getPartitionService() {
		return partitionService;
	}

	public JSONArray getPartitionJson() {
		return partitionJson;
	}

	public void setPartitionJson(JSONArray partitionJson) {
		this.partitionJson = partitionJson;
	}

	public CommonTemplateService getCommonTemplateService() {
		return commonTemplateService;
	}

	public void setCommonTemplateService(
			CommonTemplateService commonTemplateService) {
		this.commonTemplateService = commonTemplateService;
	}

	public PageUtil<CommTemplate> getCommTemplatePage() {
		return commTemplatePage;
	}

	public void setCommTemplatePage(PageUtil<CommTemplate> commTemplatePage) {
		this.commTemplatePage = commTemplatePage;
	}

	public CommTemplate getCommTemplate() {
		return commTemplate;
	}

	public void setCommTemplate(CommTemplate commTemplate) {
		this.commTemplate = commTemplate;
	}

}
