package com.banger.mobile.webapp.action.finance;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.Enum.finance.EnumArticle;
import com.banger.mobile.domain.Enum.finance.EnumFinance;
import com.banger.mobile.domain.model.finance.FeArticle;
import com.banger.mobile.domain.model.finance.FeArticleAttachment;
import com.banger.mobile.domain.model.finance.FeColumn;
import com.banger.mobile.domain.model.finance.FeFinanceUser;
import com.banger.mobile.domain.model.finance.FeUserRelation;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.finance.FeArticleAttachmentService;
import com.banger.mobile.facade.finance.FeArticleService;
import com.banger.mobile.facade.finance.FeColumnService;
import com.banger.mobile.facade.finance.FeUserRelationService;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;

public class FeArticleAction extends BaseAction {

	/**
	 * 财经热点文章管理ACTION
	 */
	private static final long serialVersionUID = -1108869730005671924L;

	private FeArticleService feArticleService;
	private FeUserRelationService feUserRelationService;

	private FeArticleAttachmentService feArticleAttachmentService;
	private SysParamService sysParamService;
	private FeColumnService feColumnService;// 文章分类service
	private DeptFacadeService deptFacadeService;

	private PageUtil<FeArticle> feArticleList;// 文章列表
	private List<FeArticleAttachment> attrList; // 文章附件List
	private FeArticleAttachment articleAttr;

	private List<FeColumn> articleColumnList;// 文章分类列表

	private FeArticle feArticle;// 文章
	private Integer submitType;// 提交类型
	private Map<String, Integer> countMap;// 今日文章数目
	private PageUtil<FeFinanceUser> feFinanceUserPage;
	private String actionType;
	/**
	 * 附件上传参数
	 */
	private static final int BUFFERED_SIZE = 4 * 1024;
	private File fileInput;
	private String fileInputFileName;
	private String fileNameOld;
	private String fileNameTask;
	private String fileSize;

	/**
	 * 查询文章管理列表页面
	 * 
	 * @return
	 */
	public String showFeArticlePage() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			feArticleList = feArticleService.getArticlePageList(map,
					this.getPage());
			articleColumnList = feColumnService.getAllColumnList();
			return SUCCESS;
		} catch (Exception e) {
			log.error("FeActiceAction showFeArticlePage error:"
					+ e.getMessage());
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 显示文章列表,客户经理业务主管调用
	 * 
	 * @return
	 */
	public String showFinanceMainPage() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String columnId = request.getParameter("columnId");
			if (!StringUtil.isBlank(columnId)) {
				request.setAttribute("columnId", columnId);
				map.put("articleColumnId", columnId);
			}

			if (deptFacadeService.isInChargeOfDepartment()) {
				String userIds = deptFacadeService.getInChargeOfDeptUserIds(
						this.getLoginInfo().getUserId(), 1);
				if (!StringUtil.isBlank(columnId)) {
					map.put("userIds", userIds);
				}
				if (!"".equals(userIds)) {
					List<String> numList = new ArrayList<String>();
					for (String s : userIds.split(","))
						numList.add(s);
					Set<String> numSet = new HashSet<String>();
					numSet.addAll(numList);
					map.put("userIdCount", numSet.size());
				} else {
					map.put("userIdCount", 0);
				}
				map.put("articleReadtimeBegin",
						DateUtil.convertDateToString(new Date()));
				map.put("articleReadtimeEnd",
						DateUtil.convertDateToString(new Date()));
				if (deptFacadeService.isCommon()) {// 是主管还是客户经理
					request.setAttribute("isCommon", "1");
					map.put("userId", this.getLoginInfo().getUserId());
					map.put("unread", "unread");
				}
				feArticleList = feArticleService.showInChargeOfFinanceMainPage(
						map, this.getPage());

				request.setAttribute("myUserId", this.getLoginInfo()
						.getUserId());
				request.setAttribute("myUserName", this.getLoginInfo()
						.getUserName());
				return "executivePage";
			} else {
				map.put("articleReadtimeBegin",
						DateUtil.convertDateToString(new Date()));
				map.put("articleReadtimeEnd",
						DateUtil.convertDateToString(new Date()));
				map.put("isPublish", 1);
				map.put("userId", this.getLoginInfo().getUserId());
				map.put("unread", "unread");
				feArticleList = feArticleService.showFinanceMainPage(map,
						this.getPage());
				Map<String, Object> tmap = new HashMap<String, Object>();
				tmap.put("articleColumnId", columnId);
				tmap.put("userId", this.getLoginInfo().getUserId());
				tmap.put("isPublish", 1);
				countMap = feArticleService.getTodayFinanceCount(tmap);
				return "customerManager";
			}
		} catch (Exception e) {
			log.error("FeActiceAction showFinanceMainPage error:"
					+ e.getMessage());
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 阅读文章
	 * 
	 * @return
	 */
	public String readFeArticle() {
		try {
			Integer articleId = Integer.parseInt(request
					.getParameter("articleId"));
			FeUserRelation feUseRelation = new FeUserRelation();
			feUseRelation.setUserId(this.getLoginInfo().getUserId());
			feUseRelation.setFeId(articleId);
			feUseRelation.setIsRead(1);
			feUseRelation.setRelationType(0);
			feUserRelationService.userRelationOperate(feUseRelation,
					EnumFinance.FE_READ);
			feArticleService.articleOperation(articleId,
					EnumArticle.ART_PARTAKE);
			feArticle = feArticleService.getArticle(articleId);
			loadArticleAttr(articleId);
			Integer isCollect = (feUserRelationService.isUserCollectArticle(
					this.getLoginInfo().getUserId(), articleId) == true) ? 1
					: 0;
			request.setAttribute("isCollect", isCollect);
			return SUCCESS;
		} catch (Exception e) {
			log.error("FeActiceAction readFeArticle error:" + e.getMessage());
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 显示文章列表,客户经理,业务主管调用
	 * 
	 * @return
	 */
	public String showFinanceMainPageBySearch() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String columnId = request.getParameter("columnId");
			if (!StringUtil.isBlank(columnId)) {
				map.put("articleColumnId", columnId);
			}

			if (deptFacadeService.isInChargeOfDepartment()) {
				String userIds = deptFacadeService.getInChargeOfDeptUserIds(
						this.getLoginInfo().getUserId(), 1);
				if (!StringUtil.isBlank(userIds)) {
					map.put("userIds", userIds);
				}
				if (!"".equals(userIds)) {
					List<String> numList = new ArrayList<String>();
					for (String s : userIds.split(","))
						numList.add(s);
					Set<String> numSet = new HashSet<String>();
					numSet.addAll(numList);
					map.put("userIdCount", numSet.size());
				} else {
					map.put("userIdCount", 0);
				}
				if (null != feArticle.getArticleReadtimeBegin())
					map.put("articleReadtimeBegin", new Timestamp(feArticle
							.getArticleReadtimeBegin().getTime()));
				if (null != feArticle.getArticleReadtimeEnd())
					map.put("articleReadtimeEnd", new Timestamp(feArticle
							.getArticleReadtimeEnd().getTime()));
				if (!StringUtil.isBlank(feArticle.getUnReadCount())) {
					map.put("unReadCount", feArticle.getUnReadCount());
				}
				if (feArticle.getArticleDiscessCount() != null) {
					map.put("articleDiscessCount",
							feArticle.getArticleDiscessCount());
				}
				if (feArticle.getArticleCollectCount() != null) {
					map.put("articleCollectCount",
							feArticle.getArticleCollectCount());
				}
				String userId = request.getParameter("userId");
				String readType = request.getParameter("readType");
				if (!StringUtil.isBlank(userId)
						&& !StringUtil.isBlank(readType)) {
					map.put("userId", userId);
					if ("unread".equals(readType)) {
						map.put("unread", 1);
					} else if ("mustRead".equals(readType)) {
						map.put("unread", 1);
						map.put("isMastread", 1);
					} else if ("readed".equals(readType)) {
						map.put("readed", 1);
					}

				}

				feArticleList = feArticleService.showInChargeOfFinanceMainPage(
						map, this.getPage());
				return "executivePage";
			} else {
				String seachType = request.getParameter("seachType");
				map.put("articleReadtimeBegin",
						DateUtil.convertDateToString(new Date()));
				map.put("articleReadtimeEnd",
						DateUtil.convertDateToString(new Date()));
				if (("readed").equals(seachType))
					map.put("readed", "readed");
				if (("unread").equals(seachType))
					map.put("unread", "unread");
				if (("mustReadInUnRead").equals(seachType)) {
					map.put("isMastread", "1");
					map.put("unread", "unread");
				}
				if (("mustReadInRead").equals(seachType)) {
					map.put("isMastread", "1");
					map.put("readed", "readed");
				}
				if (("hasAttachment").equals(seachType)) {
					map.put("isAttachment", "isAttachment");
				}
				map.put("isPublish", 1);
				map.put("userId", this.getLoginInfo().getUserId());
				feArticleList = feArticleService.showFinanceMainPage(map,
						this.getPage());
				Map<String, Object> tmap = new HashMap<String, Object>();
				tmap.put("articleColumnId", columnId);
				tmap.put("userId", this.getLoginInfo().getUserId());
				tmap.put("isPublish", 1);
				countMap = feArticleService.getTodayFinanceCount(tmap);
				return "customerManager";
			}
		} catch (Exception e) {
			log.error("FeActiceAction showFinanceMainPageBySearch error:"
					+ e.getMessage());
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 搜索文章
	 * 
	 * @return
	 */
	public String showFeArticlePageBySearch() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (null != feArticle) {
				if (!StringUtil.isBlank(feArticle.getArticleTitle()))
					map.put("articleTitle", StringUtil.ReplaceSQLChar(feArticle.getArticleTitle()));
				if (null != feArticle.getArticleReadtimeBegin())
					map.put("articleReadtimeBegin", new Timestamp(feArticle
							.getArticleReadtimeBegin().getTime()));
				if (null != feArticle.getArticleReadtimeEnd())
					map.put("articleReadtimeEnd", new Timestamp(feArticle
							.getArticleReadtimeEnd().getTime()));
				if (feArticle.getArticleColumnId() > 0)
					map.put("articleColumnId", feArticle.getArticleColumnId());
				if (feArticle.getIsMastread() > 0)
					map.put("isMastread", feArticle.getIsMastread());

			}
			String isAttachment = request.getParameter("isAttachment");
			if (!StringUtil.isBlank(isAttachment)) {
				map.put("isAttachment", isAttachment);
			}
			String publishDateBegin = request.getParameter("publishDateBegin");
			if (!StringUtil.isBlank(publishDateBegin)) {
				map.put("publishDateBegin", publishDateBegin);
			}
			String publishDateEnd = request.getParameter("publishDateEnd");
			if (!StringUtil.isBlank(publishDateEnd)) {
				map.put("publishDateEnd", publishDateEnd);
			}

			feArticleList = feArticleService.getArticlePageList(map,
					this.getPage());
			articleColumnList = feColumnService.getAllColumnList();
			return SUCCESS;
		} catch (Exception e) {
			log.error("FeActiceAction showFeArticlePageBySearch error:"
					+ e.getMessage());
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 新增文章
	 * 
	 * @return
	 */
	public String addFeArticle() {
		try {
			articleColumnList = feColumnService.getAllColumnList();
			request.setAttribute("toadyDate",
					DateUtil.convertDateToString("yyyy-MM-dd", new Date()));
			return SUCCESS;
		} catch (Exception e) {
			log.error("FeActiceAction AddFeArticle error:" + e.getMessage());
			e.printStackTrace();
			return ERROR;
		}

	}

	/**
	 * 更新文章
	 */
	public void updateFeArticle() {
		try {
			feArticle.setIsDel(0);
			feArticle.setUpdateUser(getLoginInfo().getUserId());
			feArticle.setUpdateDate(new Date());
			// if(feArticle.getIsPublish() == 1){
			// feArticle.setPublishDate(new Date());
			// }
			Integer articleId = feArticleService.updateActicle(feArticle);
			if (articleId > 0) {
				saveArticleUploadFile(feArticle.getArticleId());
				renderText("0");
			} else {
				renderText("-2");
			}
		} catch (Exception e) {
			log.error("FeActiceAction updateFeArticle error:" + e.getMessage());
			e.printStackTrace();
			renderText("-1");
		}

	}

	/**
	 * 跳转到编辑页面
	 * 
	 * @return
	 */
	public String toArticleUpdatePage() {
		try {
			feArticle = feArticleService.getArticle(feArticle.getArticleId());
			articleColumnList = feColumnService.getAllColumnList();
			loadArticleAttr(feArticle.getArticleId());
			String todayDate = DateUtil.convertDateToString("yyyy-MM-dd",
					new Date());
			request.setAttribute("toadyDate", todayDate);
			Date now = DateUtil.convertStringToDate("yyyy-MM-dd",todayDate);
			Date tom = DateUtil.getRelativeDate(now, 1);
			String readTime = "default";
			if (feArticle.getArticleReadtimeBegin().equals(
					feArticle.getArticleReadtimeEnd())) {
				if (feArticle.getArticleReadtimeBegin().equals(tom)) {
					readTime = "tom";
				} else if (feArticle.getArticleReadtimeBegin().equals(now)) {
					readTime = "today";
				}
			}
			request.setAttribute("readTime", readTime);
			return SUCCESS;
		} catch (Exception e) {
			log.error("FeActiceAction updateFeArticle error:" + e.getMessage());
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 跳转到分类页面
	 * 
	 * @return
	 */
	public String toClassifyArticlePage() {
		try {
			articleColumnList = feColumnService.getAllColumnList();
			return SUCCESS;
		} catch (Exception e) {
			log.error("FeActiceAction toClassifyArticlePage error:"
					+ e.getMessage());
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 批量分类文章
	 */
	public void classifyArticle() {
		try {
			String articleIds = request.getParameter("articleIds");
			String columnId = request.getParameter("columnId");
			feArticleService.classifyActicles(articleIds, columnId);
			renderText("0");
		} catch (Exception e) {
			log.error("FeActiceAction classifyArticle error:" + e.getMessage());
			e.printStackTrace();
			renderText("-1");
		}
	}

	/**
	 * 发布文章
	 */
	public void publishArticle() {
		String articleIds = request.getParameter("articleIds");
		if (!StringUtil.isBlank(articleIds)) {
			feArticleService.publishActicle(articleIds);
			renderText("0");
		}
	}

	/**
	 * 保存文章
	 * 
	 * @return
	 */
	public void saveFeArticle() {
		try {

			feArticle.setCreateUser(getLoginInfo().getUserId());
			feArticle.setUpdateDate(new Date());
			if (feArticle.getArticleColumnId() == null) {
				feArticle.setArticleColumnId(0);
			}
			if (feArticle.getIsPublish() > 0) {
				feArticle.setPublishDate(new Timestamp(new Date().getTime()));
			}
			Integer articleId = feArticleService.insertActicle(feArticle);
			if (articleId > 0) {
				feArticle.setArticleId(articleId);
				saveArticleUploadFile(articleId);
				renderText("0");
			} else {
				renderText("-2");
			}

		} catch (Exception e) {
			log.error("FeActiceAction saveFeArticle error:" + e.getMessage());
			e.printStackTrace();
			renderText("-1");
		}
	}

	/**
	 * 删除文章
	 */
	public void deleteFeArticle() {
		try {
			if (null == feArticle) {
				return;
			}
			// 如果文章已被阅读,则不能删除
			if (feUserRelationService.isArticleReaded(feArticle.getArticleId())) {
				renderText("-2");
				return;
			}
			Integer articleId = feArticleService.deleteActicle(feArticle
					.getArticleId());
			if (articleId > 0) {
				feArticle.setArticleId(articleId);
				feArticleAttachmentService
						.deleteFeArticleAttachmentById(articleId);
				renderText("0");
			}

		} catch (Exception e) {
			log.error("FeActiceAction deleteFeArticle error:" + e.getMessage());
			e.printStackTrace();
			renderText("-1");
		}
	}

	/**
	 * 插入文章附件
	 * 
	 * @param articleId
	 */
	public void saveArticleUploadFile(Integer articleId) {
		if (fileNameTask != null && !"".equals(fileNameTask)) {
			String[] fileNameTasks = fileNameTask.split(",");
			String[] fileNameOlds = fileNameOld.split(",");
			String[] fileSizes = fileSize.split(",");
			FeArticleAttachment attr = new FeArticleAttachment();
			String savePath = sysParamService.getTaskAttachmentPath() + "/"
					+ DateUtil.convertDateToString("yyyyMMdd", new Date());
			attr.setFilePath(savePath);
			for (int i = 0; i < fileNameTasks.length; i++) {
				attr.setArticleId(articleId);
				attr.setFileName(fileNameTasks[i].trim());
				attr.setFileNameOld(fileNameOlds[i].trim());
				attr.setFileSize(Long.parseLong(fileSizes[i].trim()));
				attr.setCreateUser(getLoginInfo().getUserId());
				attr.setUploadDate(new Timestamp(new Date().getTime()));
				feArticleAttachmentService.saveFeArticleAttachment(attr);
			}
		}
	}

	/**
	 * 上传文件
	 */
	public void uploadFile() {
		HttpServletResponse response = null;
		String oldName = "";
		try {
			if (fileInputFileName != null && !fileInputFileName.equals("")) {
				String savePath = sysParamService.getTaskAttachmentPath()
						+ "/"
						+ DateUtil.convertDateToString("yyyyMMdd", new Date());
				File f = new File(savePath);
				if (!f.exists()) {// 文件不存在则创建
					f.mkdirs();
				}
				oldName = String.valueOf(System.currentTimeMillis())
						+ new Random().nextInt()
						+ fileInputFileName.substring(fileInputFileName
								.lastIndexOf("."));
				// 将文件上传到服务器
				File imageFile = new File(savePath + File.separator + oldName);
				copy(fileInput, imageFile);
				// 返回成功信息
				response = ServletActionContext.getResponse();
				response.setCharacterEncoding("utf-8");
				response.getWriter().append(fileInputFileName + "||" + oldName);
			}
		} catch (Exception e) {
			try {
				response = ServletActionContext.getResponse();
				response.getWriter().println("");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 下载文章附件
	 */
	public void downArticleFile() {
		BufferedInputStream br = null;
		BufferedOutputStream bos = null;
		try {
			articleAttr = feArticleAttachmentService
					.getFeArticleAttachmentById(articleAttr.getAttachmentId());
			if (articleAttr != null) {
				String fileName = articleAttr.getFileName();
				String fileNameOld = articleAttr.getFileNameOld();
				File file = new File(articleAttr.getFilePath() + File.separator
						+ fileNameOld);
				byte[] buf = new byte[2 * 1024];
				int len = 0;
				getResponse().reset();// 必须加，不然保存不了临时文件
				getResponse().setContentType("application/octet-stream");
				getResponse().setHeader(
						"Content-Disposition",
						"attachment; filename="
								+ new String(fileName.getBytes("gbk"),
										"iso8859-1"));
				getResponse().setCharacterEncoding("UTF-8");
				br = new BufferedInputStream(new FileInputStream(file));
				bos = new java.io.BufferedOutputStream(getResponse()
						.getOutputStream());
				while ((len = br.read(buf, 0, buf.length)) != -1) {
					bos.write(buf, 0, len);
					len++;
				}
				bos.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				br = null;
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				bos = null;
			}
		}
	}

	/**
	 * 加载文章附件
	 */
	public void loadArticleAttr(Integer acticleId) {
		attrList = feArticleAttachmentService
				.getFeArticleAttachmentByAttr(acticleId);
	}

	/**
	 * 移除附件
	 */
	public void removeAttr() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter out = response.getWriter();
			Integer count = feArticleAttachmentService
					.deleteFeArticleAttachmentByAttr(articleAttr);
			out.print(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void copy(File src, File target) {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(src),
					BUFFERED_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(target),
					BUFFERED_SIZE);
			byte[] bs = new byte[BUFFERED_SIZE];
			int i;
			while ((i = in.read(bs)) > 0) {
				out.write(bs, 0, i);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 查看阅读/未阅读/收藏文章的客户经理(列表)
	 */
	public String showFinanceUsersList() {
		try {
			if (feArticle != null && feArticle.getArticleId() != null) {
				String userIds = deptFacadeService.getInChargeOfDeptUserIds(
						this.getLoginInfo().getUserId(), 1);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("articleId", feArticle.getArticleId());
				this.getPage().setPageSize(5);
				if (actionType.equals("collect")) {
					map.put("type", "collect");
					feFinanceUserPage = feUserRelationService
							.getReadAndCollectUser(map, this.getPage());
				} else if (actionType.equals("read")) {
					map.put("userIds", userIds);
					map.put("type", "read");
					feFinanceUserPage = feUserRelationService
							.getReadAndCollectUser(map, this.getPage());
				} else if (actionType.equals("unread")) {
					map.put("userIds", userIds);
					feFinanceUserPage = feUserRelationService.getUnReadUser(
							map, this.getPage());
				}
				return SUCCESS;
			} else {
				log.error("showFinanceUsersPage action error:feArticle.getArticleId() is null");
				return ERROR;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("showFinanceUsersPage action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 查看阅读/未阅读/收藏文章的客户经理
	 */
	public String showFinanceUsersPage() {
		try {
			showFinanceUsersList();
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("showFinanceUsersPage action error:" + e.getMessage());
			return ERROR;
		}
	}

	public void setFeArticleList(PageUtil<FeArticle> feArticleList) {
		this.feArticleList = feArticleList;
	}

	public void setFeArticle(FeArticle feArticle) {
		this.feArticle = feArticle;
	}

	public void setSysParamService(SysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}

	public void setFeArticleAttachmentService(
			FeArticleAttachmentService feArticleAttachmentService) {
		this.feArticleAttachmentService = feArticleAttachmentService;
	}

	public Integer getSubmitType() {
		return submitType;
	}

	public void setSubmitType(Integer submitType) {
		this.submitType = submitType;
	}

	public File getFileInput() {
		return fileInput;
	}

	public void setFileInput(File fileInput) {
		this.fileInput = fileInput;
	}

	public String getFileInputFileName() {
		return fileInputFileName;
	}

	public void setFileInputFileName(String fileInputFileName) {
		this.fileInputFileName = fileInputFileName;
	}

	public FeArticleAttachmentService getFeArticleAttachmentService() {
		return feArticleAttachmentService;
	}

	public PageUtil<FeArticle> getFeArticleList() {
		return feArticleList;
	}

	public FeArticle getFeArticle() {
		return feArticle;
	}

	public SysParamService getSysParamService() {
		return sysParamService;
	}

	public List<FeArticleAttachment> getAttrList() {
		return attrList;
	}

	public void setAttrList(List<FeArticleAttachment> attrList) {
		this.attrList = attrList;
	}

	public FeArticleAttachment getArticleAttr() {
		return articleAttr;
	}

	public void setArticleAttr(FeArticleAttachment articleAttr) {
		this.articleAttr = articleAttr;
	}

	public List<FeColumn> getArticleColumnList() {
		return articleColumnList;
	}

	public void setArticleColumnList(List<FeColumn> articleColumnList) {
		this.articleColumnList = articleColumnList;
	}

	public FeColumnService getFeColumnService() {
		return feColumnService;
	}

	public void setFeColumnService(FeColumnService feColumnService) {
		this.feColumnService = feColumnService;
	}

	public String getFileNameOld() {
		return fileNameOld;
	}

	public void setFileNameOld(String fileNameOld) {
		this.fileNameOld = fileNameOld;
	}

	public String getFileNameTask() {
		return fileNameTask;
	}

	public void setFileNameTask(String fileNameTask) {
		this.fileNameTask = fileNameTask;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public void setFeArticleService(FeArticleService feArticleService) {
		this.feArticleService = feArticleService;
	}

	public FeArticleService getFeArticleService() {
		return feArticleService;
	}

	public DeptFacadeService getDeptFacadeService() {
		return deptFacadeService;
	}

	public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
		this.deptFacadeService = deptFacadeService;
	}

	public Map<String, Integer> getCountMap() {
		return countMap;
	}

	public void setCountMap(Map<String, Integer> countMap) {
		this.countMap = countMap;
	}

	public FeUserRelationService getFeUserRelationService() {
		return feUserRelationService;
	}

	public void setFeUserRelationService(
			FeUserRelationService feUserRelationService) {
		this.feUserRelationService = feUserRelationService;
	}

	public PageUtil<FeFinanceUser> getFeFinanceUserPage() {
		return feFinanceUserPage;
	}

	public void setFeFinanceUserPage(PageUtil<FeFinanceUser> feFinanceUserPage) {
		this.feFinanceUserPage = feFinanceUserPage;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
}
