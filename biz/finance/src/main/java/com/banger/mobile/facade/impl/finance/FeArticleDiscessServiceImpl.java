package com.banger.mobile.facade.impl.finance;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.finance.FeArticleDiscessDao;
import com.banger.mobile.domain.Enum.finance.EnumFinance;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.finance.FeArticleDiscess;
import com.banger.mobile.domain.model.finance.FeArticleReply;
import com.banger.mobile.domain.model.finance.FeDiscessWithReply;
import com.banger.mobile.domain.model.finance.FeUserRelation;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.dept.DeptService;
import com.banger.mobile.facade.finance.FeArticleDiscessService;
import com.banger.mobile.facade.finance.FeArticleReplyService;
import com.banger.mobile.facade.finance.FeUserRelationService;
import com.banger.mobile.util.DateUtil;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 10, 2012 9:29:32 AM
 * 类说明
 */
public class FeArticleDiscessServiceImpl implements FeArticleDiscessService {

	private FeArticleDiscessDao           feArticleDiscessDao;
	private DeptService                   deptService;
	private FeUserRelationService         feUserRelationService;
	private FeArticleReplyService         feArticleReplyService;
	private DeptFacadeService             deptFacadeService;

	/**
	 * 发表评论
	 * @param feArticleDiscess
	 * @return
	 */
	public Integer addDiscess(FeArticleDiscess feArticleDiscess){
		feArticleDiscess.setCreateDate(new Date());
		FeUserRelation feUserRelation = new FeUserRelation();
		feUserRelation.setIsDiscess(1);
		feUserRelation.setFeId(feArticleDiscess.getArticleId());
		feUserRelation.setUserId(feArticleDiscess.getCreateUser());
		feUserRelation.setRelationType(0);
		Integer discessId = feArticleDiscessDao.addDiscess(feArticleDiscess);
		feUserRelationService.userRelationOperate(feUserRelation, EnumFinance.FE_DISCESS);
		return discessId;
	}


	/**
	 * 获取评论列表
	 * @param map
	 * @param page
	 * @return
	 */
	public PageUtil<FeDiscessWithReply> getArticleDiscessPage(Map<String, Object> map, Page page){
		try{
			PageUtil<FeArticleDiscess> discessPageUtil = feArticleDiscessDao.getArticleDiscessPage(map, page);
			List<SysDept> deptList = deptFacadeService.getAdminInCharegeDeptTreeList();
			List<FeDiscessWithReply> discessList = new ArrayList<FeDiscessWithReply>();
			FeDiscessWithReply disWithRepl = new FeDiscessWithReply();
			for (int i = 0; i < discessPageUtil.getItems().size(); i++) {
				List<FeArticleReply> replyList = feArticleReplyService.getReplyListByDiscessId(discessPageUtil.getItems().get(i).getDiscessId());
				for (FeArticleReply reply : replyList) {
					reply.setPublishTime(processPublishTime(reply.getCreateDate()));
					reply.setDeptName(getDeptName(deptList, reply.getDeptId()));
				}
				discessPageUtil.getItems().get(i).setPublishTime(processPublishTime(discessPageUtil.getItems().get(i).getCreateDate()));
				discessPageUtil.getItems().get(i).setDeptName(getDeptName(deptList, discessPageUtil.getItems().get(i).getDeptId()));
				disWithRepl.setDiscess(discessPageUtil.getItems().get(i));
				disWithRepl.setReplyList(replyList);
				discessList.add((FeDiscessWithReply)disWithRepl.clone());
			}
			return new PageUtil<FeDiscessWithReply>(discessList,page);
		}catch (Exception e) {
			// TODO: handle exception
			return new PageUtil<FeDiscessWithReply>(null,page);
		}
	}

	/**
	 * 支持评论
	 * @param feArticleDiscess updateUser articleId 必需 
	 * @return
	 */
	public boolean supportDiscess(FeArticleDiscess feArticleDiscess){
		try{
			FeUserRelation feUserRelation = new FeUserRelation();
			feUserRelation.setIsSupport(1);
			feUserRelation.setUserId(feArticleDiscess.getUpdateUser());
			feUserRelation.setFeId(feArticleDiscess.getDiscessId());
			feUserRelation.setRelationType(1);
			feArticleDiscessDao.supportDiscess(feArticleDiscess);
			feUserRelationService.userRelationOperate(feUserRelation, EnumFinance.FE_SUPPORT);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	private String processPublishTime(Date date){
		Date now = new Date();
		String reStr;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long ss = (now.getTime() - date.getTime())/1000;
		int mm = (int)ss/60;     //共计分钟数 
		int hh=(int)ss/3600;     //共计小时数 
		int dd=(int)hh/24;       //共计天数
		if (dd!=0) {
			reStr = formatter.format(date);
		}else if(hh!=0){
			if(hh>3){
				reStr = formatter.format(date);
			}else{
				reStr = String.valueOf(hh)+"小时前";
			}
		}else if(mm!=0){
			if(mm>30){
				reStr = "1小时前";
			}else if(mm<=30 && mm>15){
				reStr = "30分钟前";
			}else if(mm<=15 && mm>10){
				reStr = "15分钟前";
			}else {
				reStr = String.valueOf(mm)+"分钟前";
			}
		}else {
			reStr = String.valueOf(ss)+"秒前";
		}
		return reStr;
	}

	/**
	 * 回复评论
	 * @param discessId
	 * @return
	 */
	public boolean replyDiscess(FeArticleDiscess feArticleDiscess){
		return feArticleDiscessDao.replyDiscess(feArticleDiscess);
	}

	/**
	 * 获取逐级部门名称
	 * @param deptList
	 * @param deptId
	 * @return
	 */
	private String getDeptName(List<SysDept> deptList,Integer deptId){
		String deptName = "";
		for(SysDept dept : deptList ){
			if(deptId.equals(3)){
				return deptService.getDeptById(deptId).getDeptName();
			}
			if(dept.getDeptId().equals(deptId)){
				return getDeptName(deptList,dept.getDeptParentId())+ ">>" + deptService.getDeptById(deptId).getDeptName();
			}
		}
		return deptName;
	}

	public FeArticleDiscessDao getFeArticleDiscessDao() {
		return feArticleDiscessDao;
	}

	public void setFeArticleDiscessDao(FeArticleDiscessDao feArticleDiscessDao) {
		this.feArticleDiscessDao = feArticleDiscessDao;
	}


	public FeUserRelationService getFeUserRelationService() {
		return feUserRelationService;
	}


	public void setFeUserRelationService(FeUserRelationService feUserRelationService) {
		this.feUserRelationService = feUserRelationService;
	}


	public FeArticleReplyService getFeArticleReplyService() {
		return feArticleReplyService;
	}


	public void setFeArticleReplyService(FeArticleReplyService feArticleReplyService) {
		this.feArticleReplyService = feArticleReplyService;
	}


	public DeptFacadeService getDeptFacadeService() {
		return deptFacadeService;
	}


	public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
		this.deptFacadeService = deptFacadeService;
	}


	public DeptService getDeptService() {
		return deptService;
	}


	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}
}



