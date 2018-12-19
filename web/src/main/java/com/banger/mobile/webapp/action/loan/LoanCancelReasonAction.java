package com.banger.mobile.webapp.action.loan;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.banger.mobile.domain.model.loan.LnCancelReason;
import com.banger.mobile.facade.loan.LnCancelReasonService;
import com.banger.mobile.webapp.action.BaseAction;

@SuppressWarnings("serial")
public class LoanCancelReasonAction extends BaseAction {
	private LnCancelReasonService lnCancelReasonService; // 撤销原因service
	private LnCancelReason lnCancelReason; // 撤销原因实体
	private List<LnCancelReason> lReasons; // 撤销原因实体list
	private String moveFlag; // 上移或下移的标识
	private String canReaId; // 撤销原因编号
	private Map<Integer, String> map = new LinkedHashMap<Integer, String>(); // 按照插入的顺序排列

	// 维护贷款撤销原因的增删改查操作

	/**
	 * 查询所有的字段
	 * 
	 * @return
	 */
	public String cancelReasonList() {
		try {
			lReasons = new ArrayList<LnCancelReason>();
			lReasons = lnCancelReasonService.getCancelReasonList();
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ERROR;
	}

	/**
	 * 删除撤销原因（伪删除）
	 * 
	 * @return
	 */
	public String deleteCancelReason() {
		try {
			lnCancelReasonService.deleteCancelReason(lnCancelReason
					.getCancelReasonId());
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("删除失败" + e.getMessage());
		}
		return ERROR;
	}

	/**
	 * 重新排序
	 */
	public void sortList() {
		List<LnCancelReason> list = new ArrayList<LnCancelReason>();
		list = lnCancelReasonService.getCancelReasonList();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				LnCancelReason ls = list.get(i);
				ls.setSortNo(i + 1);
			}
		}
	}

	/**
	 * 跳转增加页面
	 * 
	 * @return
	 */
	public String toInsertPage() {
		return SUCCESS;
	}

	/**
	 * 跳转到更新页面
	 * 
	 * @return
	 */
	public String toUpdatePage() {
		lnCancelReason = lnCancelReasonService
				.getCancelReasonById(lnCancelReason.getCancelReasonId());
		return SUCCESS;
	}

	/**
	 * 验证撤销原因是否已存在
	 */
	public void exsitsCancelReason() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter writer = response.getWriter();
			LnCancelReason ln = new LnCancelReason();
			ln.setCancelReasonName(lnCancelReason.getCancelReasonName().trim());
			LnCancelReason dd = lnCancelReasonService.getCancelReasonByName(ln
					.getCancelReasonName());
			if (dd != null) {
				writer.print("已存在！");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 新增撤销原因
	 * 
	 * @return
	 */
	public String insertCancelReason() {
		try {
			if (lnCancelReason != null) {
				if (!lnCancelReason.getCancelReasonName().trim().equals("")) {
					LnCancelReason ln = new LnCancelReason();
					ln.setCancelReasonName(lnCancelReason.getCancelReasonName()
							.trim());
					LnCancelReason dd = lnCancelReasonService
							.getCancelReasonByName(ln.getCancelReasonName());
					if (dd != null) {
						this.getResponse().getWriter().print("same");
					} else {
						lnCancelReason.setCreateUser(this.getLoginInfo()
								.getUserId());
						lnCancelReason.setCancelReasonName(lnCancelReason
								.getCancelReasonName().trim());
						lnCancelReasonService
								.insertCancelReason(lnCancelReason);
					}
				}
				return SUCCESS;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("新建撤销原因失败！");
		}
		return ERROR;
	}

	/**
	 * 更新撤销原因
	 * 
	 * @return
	 */
	public String updateCancelReason() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			LnCancelReason ln = new LnCancelReason();
			if (lnCancelReason != null) {
				ln.setCancelReasonName(lnCancelReason.getCancelReasonName()
						.trim());
				ln.setCancelReasonId(lnCancelReason.getCancelReasonId());
				LnCancelReason dd = lnCancelReasonService
						.getCancelReasonByName(ln.getCancelReasonName(),ln.getCancelReasonId());
				if (dd != null) {
					this.getResponse().getWriter().print("same");
				} else {
					lnCancelReason.setUpdateUser(this.getLoginInfo()
							.getUserId());
					lnCancelReasonService.updateCancelReason(lnCancelReason);
				}
//				if (dd != null) {
//					if(!dd.getCancelReasonName().equals(lnCancelReason.getCancelReasonName())){
//						this.getResponse().getWriter().print("same");
//					}else{
//						lnCancelReason.setUpdateUser(this.getLoginInfo()
//								.getUserId());
//						lnCancelReasonService.updateCancelReason(lnCancelReason);
//					}
//				}else{
//					lnCancelReason.setUpdateUser(this.getLoginInfo()
//							.getUserId());
//					lnCancelReasonService.updateCancelReason(lnCancelReason);
//				}
			}
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("撤销原因更新失败！" + e.getMessage());
		}
		return ERROR;
	}

	/**
	 * 上下移动操作
	 */
	public void upOrDown() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter out = response.getWriter();
			// 获取总条数
			int count = lnCancelReasonService.getCancelReasonList().size();
			// 获取当前的位置
			lnCancelReason = lnCancelReasonService
					.getCancelReasonById(lnCancelReason.getCancelReasonId());
			if (moveFlag.equals("up")) {
				if (lnCancelReason.getSortNo() == 1) {
					out.print("当前已经处于第一位了！");
				} else {
					Integer sortIndex = lnCancelReason.getSortNo() - 1;
					LnCancelReason newCancelReason = new LnCancelReason();
					newCancelReason = lnCancelReasonService
							.getCancelReasonBySortNo(sortIndex);
					newCancelReason.setSortNo(sortIndex + 1);
					lnCancelReason.setSortNo(sortIndex);
					lnCancelReasonService.updateCancelReason(newCancelReason);
					lnCancelReasonService.updateCancelReason(lnCancelReason);
				}
			} else {
				if (lnCancelReason.getSortNo() == count) {
					out.print("当前已经处于最后一位！");
				} else {
					// 获取当前的sortNo并减1
					Integer index = lnCancelReason.getSortNo();
					// 获取排在前一位的数据
					LnCancelReason newCancelReason = new LnCancelReason();
					newCancelReason = lnCancelReasonService
							.getCancelReasonBySortNo(index + 1);
					// 将排位靠后一位
					newCancelReason.setSortNo(index);
					lnCancelReason.setSortNo(index + 1);
					// 更新操作
					lnCancelReasonService.updateCancelReason(lnCancelReason);
					lnCancelReasonService.updateCancelReason(newCancelReason);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// 页面要显示的数据
	public LnCancelReason getLnCancelReason() {
		return lnCancelReason;
	}

	public void setLnCancelReason(LnCancelReason lnCancelReason) {
		this.lnCancelReason = lnCancelReason;
	}

	public List<LnCancelReason> getlReasons() {
		return lReasons;
	}

	public void setlReasons(List<LnCancelReason> lReasons) {
		this.lReasons = lReasons;
	}

	public String getMoveFlag() {
		return moveFlag;
	}

	public void setMoveFlag(String moveFlag) {
		this.moveFlag = moveFlag;
	}

	public String getCanReaId() {
		return canReaId;
	}

	public void setCanReaId(String canReaId) {
		this.canReaId = canReaId;
	}

	public Map<Integer, String> getMap() {
		return map;
	}

	public void setMap(Map<Integer, String> map) {
		this.map = map;
	}

	// service依赖注入
	public void setLnCancelReasonService(
			LnCancelReasonService lnCancelReasonService) {
		this.lnCancelReasonService = lnCancelReasonService;
	}

}
