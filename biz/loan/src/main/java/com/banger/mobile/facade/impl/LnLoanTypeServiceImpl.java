package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.LnLoanTypeDao;
import com.banger.mobile.domain.model.loan.LnLoanSubType;
import com.banger.mobile.domain.model.loan.LnLoanType;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.facade.loan.LnLoanTypeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-2-6 Time: 下午3:36 To
 * change this template use File | Settings | File Templates.
 */
public class LnLoanTypeServiceImpl implements LnLoanTypeService {

	private LnLoanTypeDao lnLoanTypeDao;

	public void setLnLoanTypeDao(LnLoanTypeDao lnLoanTypeDao) {
		this.lnLoanTypeDao = lnLoanTypeDao;
	}

	/**
	 * 取得所有启用的贷款类型
	 * 
	 * @return
	 */
	public List<LnLoanType> getLoanTypeList() {
		return lnLoanTypeDao.getLoanTypeList();
	}

	/**
	 * 子类型
	 * 
	 * @param loanTypeId
	 * @return
	 */
	public List<LnLoanSubType> getLoanSubTypeList(Integer loanTypeId) {
		return lnLoanTypeDao.getLoanSubTypeList(loanTypeId);
	}

	/**
	 * 贷款类型上下移动 by schema
	 * 
	 * @param id
	 *            当前选中的id
	 * @param moveType
	 *            移动的类型(up or down)
	 * @see com.banger.mobile.facade.loan.LnLoanTypeService#moveTypeItems(int,
	 *      java.lang.String)
	 */
	public void moveTypeItems(int id, String moveType) {
		int move = 1;
		if (moveType.equals("Up")) {
			move = -1;
		}
		List<LnLoanType> loanTypeList = lnLoanTypeDao.getLoanTypeList();
		for (int i = 0; i < loanTypeList.size(); i++) {
			if (loanTypeList.get(i).getLoanTypeId() == id) {
				LnLoanType upIndex = new LnLoanType();
				upIndex = loanTypeList.get(i + move);
				LnLoanType nowIndex = new LnLoanType();
				nowIndex = loanTypeList.get(i);
				int indexSort = upIndex.getSortNo();
				int userId = getUserLoginInfo().getUserId();
				upIndex.setSortNo(nowIndex.getSortNo());
				upIndex.setUpdateUser(userId);
				nowIndex.setSortNo(indexSort);
				nowIndex.setUpdateUser(userId);
				loanTypeList.clear();
				loanTypeList.add(nowIndex);
				loanTypeList.add(upIndex);
				lnLoanTypeDao.updateLoanTypes(loanTypeList);
			}
		}
	}

	/**
	 * 获取登录信息
	 * 
	 * @return
	 */
	private IUserInfo getUserLoginInfo() {
		HttpServletRequest res = org.apache.struts2.ServletActionContext
				.getRequest();
		HttpSession session = res.getSession();
		return (IUserInfo) session.getAttribute("LoginInfo");
	}

	public LnLoanType getMaxSortNoLoanType() {
		return lnLoanTypeDao.getMaxSortNoLoanType();
	}

	public void updateLoanType(LnLoanType loanType) {
		lnLoanTypeDao.updateLoanType(loanType);
	}

	public LnLoanType getMinSortNoLoanType() {
		return lnLoanTypeDao.getMaxSortNoLoanType();
	}

	public LnLoanType getLoanTypeById(int id) {
		return lnLoanTypeDao.getLoanTypeById(id);
	}

	public void insertLoanType(LnLoanType loanType) {
		lnLoanTypeDao.insertLoanType(loanType);
	}

	public List<LnLoanType> getAllActivedType(Map<String, Object> paramMap) {
		return lnLoanTypeDao.getAllActivedType(paramMap);
	}

	/**
	 * @param 贷款类型的名称
	 * @return
	 * @see com.banger.mobile.facade.loan.LnLoanTypeService#getLoanTypeByName(java.lang.String)
	 */
	public LnLoanType getLoanTypeByName(String name) {
		return lnLoanTypeDao.getLoanTypeByName(name);
	}

	/**
	 * @param id
	 * @return
	 * @see com.banger.mobile.facade.loan.LnLoanTypeService#getLnLoanTypeBySortNo(int)
	 */
	public LnLoanType getLnLoanTypeBySortNo(int id) {
		return null;
	}
    /**
     *通过Id和name查询
     */
	public List<LnLoanType> getLoanTypesByIdAndName(LnLoanType ln) {
		// TODO Auto-generated method stub
		return lnLoanTypeDao.getLoanTypesByIdAndName(ln);
	}

}
