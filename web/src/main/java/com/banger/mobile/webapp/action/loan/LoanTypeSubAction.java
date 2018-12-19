package com.banger.mobile.webapp.action.loan;

import com.banger.mobile.domain.model.loan.LnLoanSubType;
import com.banger.mobile.domain.model.loan.LnLoanType;
import com.banger.mobile.facade.loan.LnLoanSubTypeService;
import com.banger.mobile.facade.loan.LnLoanTypeService;
import com.banger.mobile.webapp.action.BaseAction;

import java.util.List;

public class LoanTypeSubAction extends BaseAction {

	private static final long serialVersionUID = 7508463758062532163L;

	private LnLoanSubTypeService lnLoanSubTypeService; // 贷款子类型业务注入
	private LnLoanTypeService lnLoanTypeService; // 贷款类型注入
	private List<LnLoanSubType> lnLoanSubTypes; // 贷款子类型集合
	private List<LnLoanType> lnLoanTypes; // 贷款类型集合
	private LnLoanSubType lnLoanSubType; // 贷款子类型对象
	private LnLoanType lnLoanType; // 贷款类型对象
	private String moveFlag; // 移动标记
	private String loanTypeName; // 贷款类型
	private String loanSubTypeId; // 贷款子类id

	public String getLoanSubTypeId() {
		return loanSubTypeId;
	}

	public void setLoanSubTypeId(String loanSubTypeId) {
		this.loanSubTypeId = loanSubTypeId;
	}

	/**
	 * 进入贷款子类型页面
	 * 
	 * @return
	 */
	public String typeSubList() {
		try {
			lnLoanTypes = lnLoanTypeService.getLoanTypeList();
			if (lnLoanTypes != null && lnLoanTypes.size() > 0) {
				lnLoanType = lnLoanTypes.get(0);
				lnLoanSubTypes = lnLoanSubTypeService.getLnLoanSubTypesByAsSubTypes(lnLoanType);
			}
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ERROR;
	}

	/**
	 * 通过父类型浏览子类型
	 * 
	 * @return
	 */
	public String typeSelSubList() {
		try {
			lnLoanSubTypes = lnLoanSubTypeService
					.getLnLoanSubTypesBySubType(lnLoanSubType);
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ERROR;
	}


	/**
	 * 通过父类型浏览子类型
	 *
	 * @return
	 */
	public String typeSelSubSelect() {
		try {
			if(null!=lnLoanSubType.getLoanTypeId()&&0!=lnLoanSubType.getLoanTypeId()){
				lnLoanSubTypes = lnLoanSubTypeService.getLnLoanSubTypesBySubType(lnLoanSubType);
			}
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ERROR;
	}

	/**
	 * 删除指定ID的贷款子类型
	 * 
	 * @return
	 */
	public String delLoanTypeSub() {
		try {
			lnLoanSubTypeService.delLnLoanSubType(lnLoanSubType
					.getLoanSubTypeId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 跳转到更新页面
	 * 
	 * @return
	 */
	public String toUpdatePage() {
		lnLoanSubType = lnLoanSubTypeService.getLnLoanSubTypeById(lnLoanSubType
				.getLoanSubTypeId());
		return SUCCESS;
	}

	/**
	 * 更新贷款子类型
	 * 
	 * @return
	 */
	public void updLoanTypeSub() {
		try {
			lnLoanSubTypes=lnLoanSubTypeService.getLnLoanSubTypesBySubType(lnLoanSubType);
	        if(lnLoanSubTypes!=null&&lnLoanSubTypes.size()>0){
	        	this.getResponse().getWriter().print("same");
	        }else {
				lnLoanSubTypeService.updLnLoanSubType(lnLoanSubType);
				this.getResponse().getWriter().print("success");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("updLoanTypeSub action error:" + e.getMessage());
		}
	}

	/**
	 * 新增贷款子类型
	 * 
	 * @return
	 */
	public void insLoanTypeSub() {
		try {
			lnLoanSubTypes=lnLoanSubTypeService.getLnLoanSubTypesBySubType(lnLoanSubType);
		    if(lnLoanSubTypes!=null && lnLoanSubTypes.size()>0){
		    	this.getResponse().getWriter().println("1");
		    }
			else {
				lnLoanSubType.setCreateUser(this.getLoginInfo().getUserId());
				lnLoanSubTypeService.insLnLoanSubType(lnLoanSubType);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 判断是否已经存在
	 * 
	 * @return
	 */
	public String exsLoanTypeSub() {
		return SUCCESS;
	}

	/**
	 * 移动顺序
	 */
	public String upOrDown() {
		try {
			lnLoanSubTypes = lnLoanSubTypeService.moveUpOrDown(lnLoanSubType,
					moveFlag);
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LOG.error("LoanTypeSubAction更新失败！");
		}
		return null;
	}

	// 需要交互的数据

	public List<LnLoanSubType> getLnLoanSubTypes() {
		return lnLoanSubTypes;
	}

	public String getLoanTypeName() {
		return loanTypeName;
	}

	public void setLoanTypeName(String loanTypeName) {
		this.loanTypeName = loanTypeName;
	}

	public void setLnLoanSubTypes(List<LnLoanSubType> lnLoanSubTypes) {
		this.lnLoanSubTypes = lnLoanSubTypes;
	}

	public List<LnLoanType> getLnLoanTypes() {
		return lnLoanTypes;
	}

	public void setLnLoanTypes(List<LnLoanType> lnLoanTypes) {
		this.lnLoanTypes = lnLoanTypes;
	}

	public LnLoanSubType getLnLoanSubType() {
		return lnLoanSubType;
	}

	public void setLnLoanSubType(LnLoanSubType lnLoanSubType) {
		this.lnLoanSubType = lnLoanSubType;
	}

	public LnLoanType getLnLoanType() {
		return lnLoanType;
	}

	public void setLnLoanType(LnLoanType lnLoanType) {
		this.lnLoanType = lnLoanType;
	}

	public String isMoveFlag() {
		return moveFlag;
	}

	public void setMoveFlag(String moveFlag) {
		this.moveFlag = moveFlag;
	}

	// 依赖注入
	public void setLnLoanSubTypeService(
			LnLoanSubTypeService lnLoanSubTypeService) {
		this.lnLoanSubTypeService = lnLoanSubTypeService;
	}

	public void setLnLoanTypeService(LnLoanTypeService lnLoanTypeService) {
		this.lnLoanTypeService = lnLoanTypeService;
	}

}
