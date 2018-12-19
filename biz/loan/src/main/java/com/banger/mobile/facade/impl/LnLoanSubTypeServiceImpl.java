/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :贷款子类型-Service-接口实现
 * Author     :QianJie
 * Create Date:Feb 17, 2013
 */
package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.LnLoanSubTypeDao;
import com.banger.mobile.domain.model.loan.LnLoanSubType;
import com.banger.mobile.domain.model.loan.LnLoanType;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.facade.loan.LnLoanSubTypeService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author QianJier
 * @version $Id: LnLoanSubTypeServiceImpl.java,v 0.1 Feb 17, 2013 3:50:26 PM
 *          QianJie Exp $
 */
public class LnLoanSubTypeServiceImpl implements LnLoanSubTypeService {
    private static final Logger logger = Logger
            .getLogger(LnLoanSubTypeServiceImpl.class);

	private LnLoanSubTypeDao lnLoanSubTypeDao;

	public void setLnLoanSubTypeDao(LnLoanSubTypeDao lnLoanSubTypeDao) {
		this.lnLoanSubTypeDao = lnLoanSubTypeDao;
	}

	public List<LnLoanSubType> getLnLoanSubTypes() {
		// TODO Auto-generated method stub
		return lnLoanSubTypeDao.getLnLoanSubTypes();
	}

	public List<LnLoanSubType> getLnLoanSubTypesByAsSubTypes(LnLoanType ln) {
		// TODO Auto-generated method stub
		return lnLoanSubTypeDao.getLnLoanSubTypesByType(ln);
	}

	public LnLoanSubType getLnLoanSubTypeById(Integer lnId) {
		// TODO Auto-generated method stub
		return lnLoanSubTypeDao.getLoanSubTypeById(lnId);
	}

	public List<LnLoanSubType> getLnLoanSubTypeByName(String lnName) {
		// TODO Auto-generated method stub
		return lnLoanSubTypeDao.getLoanSubTypeByName(lnName);
	}

	public LnLoanSubType getLnLoanSubTypeBySortNo(Integer sortNo) {
		// TODO Auto-generated method stub
		return lnLoanSubTypeDao.getLoanSubTypeBySortNo(sortNo);
	}

	public LnLoanSubType getLnLoanSubTypeByAssign(LnLoanSubType ln) {
		// TODO Auto-generated method stub
		return lnLoanSubTypeDao.getLoanSubTypeByAssign(ln);
	}

	public void delLnLoanSubType(Integer Id) {
		// TODO Auto-generated method stub
		lnLoanSubTypeDao.delLoanSubType(Id);
	}

	public void updLnLoanSubType(LnLoanSubType ln) {
		// TODO Auto-generated method stub
		lnLoanSubTypeDao.updLoanSubType(ln);
	}

	public void insLnLoanSubType(LnLoanSubType ln) {
		// TODO Auto-generated method stub
		lnLoanSubTypeDao.insLoanSubType(ln);
	}

	public List<LnLoanSubType> getAllActivedSubType(Map<String, Object> paramMap) {
		return lnLoanSubTypeDao.getAllActivedSubType(paramMap);
	}

	/**
	 * 根据贷款类型Id获取贷款子类型
	 */
	public List<LnLoanSubType> getLnLoanSubTypesBySubType(LnLoanSubType ln) {
		// TODO Auto-generated method stub
		return lnLoanSubTypeDao.getLnLoanSubTypeByType(ln);
	}

	/**
	 * 批量更新
	 */
	public void batchUpdLnLoanSubType(List<LnLoanSubType> lns) {
		// TODO Auto-generated method stub
		lnLoanSubTypeDao.batchUpdLoanSubType(lns);
	}

	/**
	 * 移动顺序
	 */
	public List<LnLoanSubType> moveUpOrDown(LnLoanSubType ln, String moveFlag) {
		// TODO Auto-generated method stub
		try {
			int upOrDown = 1;
			// 获取贷款类型ID相同的贷款子类型
			List<LnLoanSubType> lns = lnLoanSubTypeDao
					.getLnLoanSubTypeByType(ln);
			if (moveFlag.equals("Up")) {
				upOrDown = -1;
			}
			if (lns.size() > 1)
				for (int i = 0; i < lns.size(); i++) {
					if (lns.get(i).getLoanSubTypeId()
							.equals(ln.getLoanSubTypeId())) {
						LnLoanSubType lv = lns.get(i);
						int sortNo = lv.getSortNo();
						LnLoanSubType ls = lns.get(i + upOrDown);
						// 清空list
						lns.clear();
						// 获取当前登录对象ID
						int userId = getUserLoginInfo().getUserId();
						// 互换sortNo
						lv.setSortNo(ls.getSortNo());
						// 更新者
						lv.setUpdateUser(userId);
						lns.add(lv);
						ls.setSortNo(sortNo);
						ls.setUpdateUser(userId);
						lns.add(ls);
						// 批量更新
						lnLoanSubTypeDao.batchUpdLoanSubType(lns);
					}
				}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return lnLoanSubTypeDao.getLnLoanSubTypeByType(ln);
	}

	/**
	 * 获取登录信息
	 * 
	 * @return
	 */
	private IUserInfo getUserLoginInfo() {
		HttpServletRequest req = org.apache.struts2.ServletActionContext
				.getRequest();
		HttpSession session = req.getSession();
		return (IUserInfo) session.getAttribute("LoginInfo");
	}
}
