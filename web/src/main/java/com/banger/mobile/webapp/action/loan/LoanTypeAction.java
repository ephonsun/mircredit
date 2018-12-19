package com.banger.mobile.webapp.action.loan;

import java.io.PrintWriter;
import java.util.List;

import org.apache.log4j.Logger;

import com.banger.mobile.domain.model.loan.LnLoanType;
import com.banger.mobile.facade.loan.LnLoanTypeService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * Created with IntelliJ IDEA. User: yuanme Date: 13-2-5 Time: 下午4:13 To change
 * this template use File | Settings | File Templates.
 * <p/>
 * 贷款类型维护 Action
 */
public class LoanTypeAction extends BaseAction {

	private static Logger logger = Logger.getLogger(LoanTypeAction.class);

	private LnLoanTypeService lnLoanTypeService;

	// 查询条件
	private int id;
	private String moveType;
	private String loanTypeName;

	/**
	 * 调转到贷款维护页面
	 * 
	 * @return
	 */
	public String toEditLoanType() {
		try {
			List<LnLoanType> loanTypeList = lnLoanTypeService.getLoanTypeList();
			request.setAttribute("loanTypeList", loanTypeList);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("toEditLoanType", e);
			return ERROR;
		}
	}

	/**
	 * 验证是否有数据，用于子类型的跳转
	 */
	public void selLoanType() {
		try {
			PrintWriter out = this.getResponse().getWriter();
			List<LnLoanType> loanTypeList = lnLoanTypeService.getLoanTypeList();
			if (loanTypeList == null || loanTypeList.size() < 1) {
				out.write("noData");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public String toUpdateLoanTypePage() {
		try {
			LnLoanType t = lnLoanTypeService.getLoanTypeById(id);
			loanTypeName = t.getLoanTypeName();
			return SUCCESS;
		} catch (Exception e) {
			logger.error("toUpdateLoanTypePage", e);
			return ERROR;
		}
	}

	public void updateLoanType() {
		try {
			PrintWriter out = this.getResponse().getWriter();
			LnLoanType loanType = new LnLoanType();
			loanType = lnLoanTypeService.getLoanTypeByName(loanTypeName);
			if (loanType != null) {
				out.write("same");
			} else {
				loanType = new LnLoanType();
				loanType.setLoanTypeId(id);
				loanType.setLoanTypeName(loanTypeName);
				loanType.setUpdateUser(this.getLoginInfo().getUserId());
				lnLoanTypeService.updateLoanType(loanType);
				out.write("SUCCESS");
			}
		} catch (Exception e) {
			log.error("updateLoanType action error:", e);
		}
	}

	public void deleteLoanType() {
		try {
			LnLoanType loanType = lnLoanTypeService.getLoanTypeById(id);
			loanType.setIsDel(1);
			loanType.setUpdateUser(this.getLoginInfo().getUserId());
			lnLoanTypeService.updateLoanType(loanType);
			PrintWriter out = this.getResponse().getWriter();
			out.write("SUCCESS");
		} catch (Exception e) {
			log.error("deleteLoanType action error:", e);
		}
	}

	public void addLoanType() {
		try {
			LnLoanType max = lnLoanTypeService.getMinSortNoLoanType();
			Integer sortNo = 1;
			if (max != null && max.getSortNo() != null) {
				sortNo = max.getSortNo() + 1;
			}
			LnLoanType loanType = new LnLoanType();
			loanType.setLoanTypeName(loanTypeName.trim());
			loanType.setIsActived(1);
			loanType.setIsDel(0);

			loanType.setSortNo(sortNo);
			loanType.setUpdateUser(this.getLoginInfo().getUserId());
			PrintWriter out = this.getResponse().getWriter();
			if (lnLoanTypeService.getLoanTypeByName(loanType.getLoanTypeName()) != null) {
				out.write("exists");
			} else {
				lnLoanTypeService.insertLoanType(loanType);
				out.write("SUCCESS");
			}
		} catch (Exception e) {
			log.error("addLoanType action error:", e);
		}
	}

	/**
	 * 上移和下移
	 * 
	 * @return
	 */
	public String moveTypeItems() {
		try {
			lnLoanTypeService.moveTypeItems(id, moveType);
			return SUCCESS;
		} catch (Exception e) {
			log.error("moveTypeItems action error:", e);
		}
		return null;
	}

	/**
	 * getter setter *
	 */
	public void setLnLoanTypeService(LnLoanTypeService lnLoanTypeService) {
		this.lnLoanTypeService = lnLoanTypeService;
	}

	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoanTypeName() {
		return loanTypeName;
	}

	public void setLoanTypeName(String loanTypeName) {
		this.loanTypeName = loanTypeName;
	}

	public int getId() {
		return id;
	}

	public String getMoveType() {
		return moveType;
	}
}
