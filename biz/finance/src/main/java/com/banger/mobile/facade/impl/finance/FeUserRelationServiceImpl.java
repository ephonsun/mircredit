package com.banger.mobile.facade.impl.finance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.finance.FeUserRelationDao;
import com.banger.mobile.domain.Enum.finance.EnumFinance;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.finance.FeFinanceUser;
import com.banger.mobile.domain.model.finance.FeUserRelation;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.dept.DeptService;
import com.banger.mobile.facade.finance.FeUserRelationService;
import com.ibm.db2.jcc.am.in;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 8, 2012 11:14:09 AM
 * 类说明
 */
public class FeUserRelationServiceImpl implements FeUserRelationService{

	private FeUserRelationDao             feUserRelationDao;
	private DeptFacadeService             deptFacadeService;
	private DeptService                   deptService;
	/**
	 * 财经要点用户操作
	 * @param feUserRelation
	 * @param enumFinance
	 * @return
	 */
	public boolean userRelationOperate(FeUserRelation feUserRelation,
									   EnumFinance enumFinance) {
		try {
			FeUserRelation userRelation1 = new FeUserRelation();
			FeUserRelation userRelation2 = feUserRelationDao.getUserRelation(feUserRelation);
			boolean bool = false;
			if(userRelation2!=null&&userRelation2.getRelationId()!=null){
				userRelation1 = userRelation2;
				bool = true;
			}else {
				userRelation1=(FeUserRelation)feUserRelation.clone();
				userRelation1.setIsCollect(0);
				userRelation1.setIsDiscess(0);
				userRelation1.setIsRead(0);
				userRelation1.setIsReply(0);
				userRelation1.setIsSupport(0);
			}

			switch (enumFinance) {
				case FE_READ:
					userRelation1.setIsRead(feUserRelation.getIsRead());
					userRelation1.setReadDate(new Date());
					break;
				case FE_COLLECT:
					userRelation1.setIsCollect(feUserRelation.getIsCollect());
					userRelation1.setCollectDate(new Date());
					break;
				case FE_DISCESS:
					userRelation1.setIsDiscess(feUserRelation.getIsDiscess());
					break;
				case FE_REPLY:
					userRelation1.setIsReply(feUserRelation.getIsReply());
					break;
				case FE_SUPPORT:
					userRelation1.setIsSupport(feUserRelation.getIsSupport());
					break;
				default:
					break;
			}
			if(bool){
				feUserRelationDao.updateUserRelation(userRelation1);
			}else {
				feUserRelationDao.insertUserRelation(userRelation1);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}


	/**
	 * 用户是否已收藏文章
	 * @param userId
	 * @param articleId
	 * @return
	 */
	public boolean isUserCollectArticle(Integer userId, Integer articleId){
		FeUserRelation feUserRelation = new FeUserRelation();
		feUserRelation.setUserId(userId);
		feUserRelation.setFeId(articleId);
		feUserRelation.setRelationType(0);
		feUserRelation = feUserRelationDao.getUserRelation(feUserRelation);
		if(feUserRelation!=null && feUserRelation.getIsCollect()==1){
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 查看已阅读/已收藏客户经理
	 * @param map
	 * @param page
	 * @return
	 */
	public PageUtil<FeFinanceUser> getReadAndCollectUser(Map<String, Object> map,Page page){
		List<FeFinanceUser> list = feUserRelationDao.getReadAndCollectUser(map, page);
		if(list == null){
			list = new ArrayList<FeFinanceUser>();
		}
		List<SysDept> deptList = deptFacadeService.getAdminInCharegeDeptTreeList();
		for (FeFinanceUser user : list) {
			user.setDeptName(this.getDeptName(deptList, user.getDeptId()));
		}
		return new PageUtil<FeFinanceUser>(list,page);
	}

	/**
	 * 查看未阅读客户经理
	 * @param map
	 * @param page
	 * @return
	 */
	public PageUtil<FeFinanceUser> getUnReadUser(Map<String, Object> map,Page page){
		List<FeFinanceUser> list = feUserRelationDao.getUnReadUser(map, page);
		if(list == null){
			list = new ArrayList<FeFinanceUser>();
		}
		List<SysDept> deptList = deptFacadeService.getAdminInCharegeDeptTreeList();
		for (FeFinanceUser user : list) {
			user.setDeptName(this.getDeptName(deptList, user.getDeptId()));
		}
		return new PageUtil<FeFinanceUser>(list,page);
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


	/**
	 * 判断文章是否被阅读过
	 * @param articleId
	 * @return
	 */
	public boolean isArticleReaded(Integer articleId){
		return feUserRelationDao.isArticleReaded(articleId);
	}

	public FeUserRelationDao getFeUserRelationDao() {
		return feUserRelationDao;
	}

	public void setFeUserRelationDao(FeUserRelationDao feUserRelationDao) {
		this.feUserRelationDao = feUserRelationDao;
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



