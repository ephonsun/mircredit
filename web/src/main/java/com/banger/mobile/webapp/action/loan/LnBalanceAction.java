package com.banger.mobile.webapp.action.loan;

import com.banger.mobile.domain.model.data.Form;
import com.banger.mobile.domain.model.data.LoanData;
import com.banger.mobile.domain.model.data.Photo;
import com.banger.mobile.domain.model.loan.*;
import com.banger.mobile.domain.model.system.SysTeam;
import com.banger.mobile.facade.loan.*;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 资产负债表
 * @author Administrator
 *
 */
public class LnBalanceAction extends BaseAction {

	  private static Logger         logger = Logger.getLogger(LnBalanceAction.class);
	  private LnBalanceService lnBalanceService;
	  private LnProfitLossBaseService lnProfitLossBaseService;
	  private LnProfitLossProdService lnProfitLossProdService;
	  private LnBalanceCash balanceCash;
	  private LnBalanceAccount balanceAccount;
	  private LnBalanceFixed balanceFixed;
	  private LnBalanceDebt balanceDebt;
	  private LnValidationItem validationItem;
	  private LnLoanInfoDictionaryService lnLoanInfoDictionaryService;

	public LnLoanInfoDictionaryService getLnLoanInfoDictionaryService() {
		return lnLoanInfoDictionaryService;
	}

	public void setLnLoanInfoDictionaryService(LnLoanInfoDictionaryService lnLoanInfoDictionaryService) {
		this.lnLoanInfoDictionaryService = lnLoanInfoDictionaryService;
	}

	public LnProfitLossProdService getLnProfitLossProdService() {
		return lnProfitLossProdService;
	}

	public void setLnProfitLossProdService(LnProfitLossProdService lnProfitLossProdService) {
		this.lnProfitLossProdService = lnProfitLossProdService;
	}

	public LnProfitLossBaseService getLnProfitLossBaseService() {
		return lnProfitLossBaseService;
	}

	public void setLnProfitLossBaseService(LnProfitLossBaseService lnProfitLossBaseService) {
		this.lnProfitLossBaseService = lnProfitLossBaseService;
	}

	public LnValidationItem getValidationItem() {
		return validationItem;
	}

	public void setValidationItem(LnValidationItem validationItem) {
		this.validationItem = validationItem;
	}

	public LnBalanceFixed getBalanceFixed() {
		return balanceFixed;
	}

	public void setBalanceFixed(LnBalanceFixed balanceFixed) {
		this.balanceFixed = balanceFixed;
	}

	public LnBalanceDebt getBalanceDebt() {
		return balanceDebt;
	}

	public void setBalanceDebt(LnBalanceDebt balanceDebt) {
		this.balanceDebt = balanceDebt;
	}

	public LnBalanceAccount getBalanceAccount() {
		return balanceAccount;
	}

	public void setBalanceAccount(LnBalanceAccount balanceAccount) {
		this.balanceAccount = balanceAccount;
	}

	public LnBalanceCash getBalanceCash() {
		return balanceCash;
	}

	public void setBalanceCash(LnBalanceCash balanceCash) {
		this.balanceCash = balanceCash;
	}

	public LnBalanceService getLnBalanceService() {
		return lnBalanceService;
	}

	public void setLnBalanceService(LnBalanceService lnBalanceService) {
		this.lnBalanceService = lnBalanceService;
	}




	public String getLnBalanceTab() {
		logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 进入贷款 资产负债页面");
		try{
			String loanId =  request.getParameter("loanId");
			String appLoanTypeId =  request.getParameter("appLoanTypeId");
			String write =  request.getParameter("write");

			request.setAttribute("loanId", loanId);
			request.setAttribute("appLoanTypeId", appLoanTypeId);
			request.setAttribute("write", write);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}

	public String getLnFinancialTab() {
		logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 进入贷款 财务分析页面");
		try{
			String loanIdStr =  request.getParameter("loanId");
			String appLoanTypeId =  request.getParameter("appLoanTypeId");
			if(StringUtils.isNotBlank(loanIdStr)&&StringUtils.isNumeric(loanIdStr)){
				Integer loanId  = Integer.valueOf(loanIdStr);

				LnFinancialAnalysis financial = new LnFinancialAnalysis();

				financial.setAppLoanTypeId(appLoanTypeId);

//				银行及现金存款	cash1
				financial.setCash1(lnBalanceService.sumByFlagAndType(loanId, "cash","1"));
//				应收账款	account1
				financial.setAccount1(lnBalanceService.sumByFlagAndType(loanId, "account", "1"));
//				预付账款	account2
				financial.setAccount2(lnBalanceService.sumByFlagAndType(loanId, "account", "2"));
//				存货	fixed2
				financial.setFixed2(lnBalanceService.sumByFlagAndType(loanId, "fixed", "2"));
//				固定资产	fixed1
				financial.setFixed1(lnBalanceService.sumByFlagAndType(loanId, "fixed", "1"));
//				其他经营资产	cash2
				financial.setCash2(lnBalanceService.sumByFlagAndType(loanId, "cash", "2"));
//				其他非经营资产	cash3
				financial.setCash3(lnBalanceService.sumByFlagAndType(loanId, "cash", "3"));
//				其他资产	cash4
				financial.setCash4(lnBalanceService.sumByFlagAndType(loanId, "cash", "4"));
//				投资性资产	cash6
				financial.setCash6(lnBalanceService.sumByFlagAndType(loanId, "cash", "6"));
//				对外债权	cash7
				financial.setCash7(lnBalanceService.sumByFlagAndType(loanId, "cash", "7"));
//				预付款	cash8
				financial.setCash8(lnBalanceService.sumByFlagAndType(loanId, "cash", "8"));


//				应付账款	account3
				financial.setAccount3(lnBalanceService.sumByFlagAndType(loanId, "account", "3"));
//				预收账款	account4
				financial.setAccount4(lnBalanceService.sumByFlagAndType(loanId, "account", "4"));
//				短期负债	debt1
				financial.setDebt1(lnBalanceService.sumByFlagAndType(loanId, "debt", "1"));
//				长期负债	debt2
				financial.setDebt2(lnBalanceService.sumByFlagAndType(loanId, "debt", "2"));
//				其他负债	cash5
				financial.setCash5(lnBalanceService.sumByFlagAndType(loanId, "cash", "5"));

//				自用性负债	debt3
				financial.setDebt3(lnBalanceService.sumByFlagAndType(loanId, "debt", "3"));
//				投资性负债	debt4
				financial.setDebt4(lnBalanceService.sumByFlagAndType(loanId, "debt", "4"));
//				消费性负债	debt5
				financial.setDebt5(lnBalanceService.sumByFlagAndType(loanId, "debt", "5"));
//				其他负债	debt6
				financial.setDebt6(lnBalanceService.sumByFlagAndType(loanId, "debt", "6"));


//				表外资产	offBalance	表外负债	offDebt
				LnBalanceOff balanceOff = lnBalanceService.getLnBalanceOffById(loanId);
				if(null!=balanceOff){
					if(null!=balanceOff.getBalanceAmount()){
						financial.setOffBalance(balanceOff.getBalanceAmount());
					}
					if(null!=balanceOff.getDebtAmount()){
						financial.setOffDebt(balanceOff.getDebtAmount());
					}
					if(StringUtils.isNotBlank(balanceOff.getFinancialAnalysis())){
						financial.setFinancialAnalysis(balanceOff.getFinancialAnalysis());
					}
				}


				//损益（xx个月）
				LnProfitLossBase lnProfitLossBase = lnProfitLossBaseService.selectProfitLossBaseByPrimary(loanId);
				if(null!=lnProfitLossBase){
					financial.setMonthNumber(lnProfitLossBase.getMonthNumber());
				}

//				营业收入	profitLoss1
				financial.setProfitLoss1(lnProfitLossBaseService.sumItemByType(loanId, "1"));
//				固定成本支出	profitLoss2
				financial.setProfitLoss2(lnProfitLossBaseService.sumItemByType(loanId, "2"));
//				所得税	profitLoss3
				financial.setProfitLoss3(lnProfitLossBaseService.sumItemByType(loanId, "3"));
//				其他收入	profitLoss4
				financial.setProfitLoss4(lnProfitLossBaseService.sumItemByType(loanId, "4"));
//				其他支出	profitLoss5
				financial.setProfitLoss5(lnProfitLossBaseService.sumItemByType(loanId, "5"));

//				毛利率	totalGrossRate
				financial.setTotalGrossRate(lnProfitLossProdService.getWeightedGrossRate(loanId));

//				家庭收入	profitLoss6
				financial.setProfitLoss6(lnProfitLossBaseService.sumItemByType(loanId, "6"));
//				固定支出	profitLoss7
				financial.setProfitLoss7(lnProfitLossBaseService.sumItemByType(loanId, "7"));
//				个人所得税	profitLoss8
				financial.setProfitLoss8(lnProfitLossBaseService.sumItemByType(loanId, "8"));
				request.setAttribute("financial", financial);

			}


			request.setAttribute("appLoanTypeId", appLoanTypeId);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}

	public String getLnValidationTab() {
		logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 进入贷款 交叉权益页面");
		try{

			String loanId =  request.getParameter("loanId");
			String appLoanTypeId =  request.getParameter("appLoanTypeId");
			String write =  request.getParameter("write");
			if(StringUtils.isNotBlank(loanId)&&StringUtils.isNumeric(loanId)){
				//年销售额 totalSalesAmount = 营业收入合计 profitLoss1
				BigDecimal totalSalesAmount = lnProfitLossBaseService.sumItemByType(Integer.valueOf(loanId),"1");
				request.setAttribute("totalSalesAmount",totalSalesAmount );
				//毛利率 = 加权毛利率 totalGrossRate
				BigDecimal totalGrossRate = lnProfitLossProdService.getWeightedGrossRate(Integer.valueOf(loanId));
				request.setAttribute("totalGrossRate", totalGrossRate);
				// 总收入 totalIncomeAmount = profitLoss6
				request.setAttribute("totalIncomeAmount", lnProfitLossBaseService.sumItemByType(Integer.valueOf(loanId), "6"));
				// 净利率 totalNetRate = 总净利润/总销售额  总净利润（毛利润-固定支出-个税-其他支出+其他收入  等等）
//				固定成本支出	profitLoss2
				BigDecimal profitLoss2 = lnProfitLossBaseService.sumItemByType(Integer.valueOf(loanId), "2");
				if(null==profitLoss2){
					profitLoss2 =  new BigDecimal(0);
				}
//				所得税	profitLoss3
				BigDecimal profitLoss3 = lnProfitLossBaseService.sumItemByType(Integer.valueOf(loanId), "3");
				if(null==profitLoss3){
					profitLoss3 =  new BigDecimal(0);
				}
//				其他收入	profitLoss4
				BigDecimal profitLoss4 = lnProfitLossBaseService.sumItemByType(Integer.valueOf(loanId), "4");
				if(null==profitLoss4){
					profitLoss4 =  new BigDecimal(0);
				}
//				其他支出	profitLoss5
				BigDecimal profitLoss5 = lnProfitLossBaseService.sumItemByType(Integer.valueOf(loanId), "5");
				if(null==profitLoss5){
					profitLoss5 =  new BigDecimal(0);
				}

				BigDecimal totalNetAmount = totalGrossRate.multiply(totalSalesAmount).subtract(profitLoss2).subtract(profitLoss3).subtract(profitLoss4).subtract(profitLoss5);
				if(totalSalesAmount.compareTo(new BigDecimal(0))!=0){
					request.setAttribute("totalNetRate", totalNetAmount.multiply(new BigDecimal(100)).divide(totalSalesAmount, 2, BigDecimal.ROUND_HALF_UP));
				}else{
					request.setAttribute("totalNetRate", new BigDecimal(0));
				}





			}


			request.setAttribute("appLoanTypeId", appLoanTypeId);
			request.setAttribute("loanId", loanId);
			request.setAttribute("write", write);
			return SUCCESS;

		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}



	public String getBalanceTable() {
		try{

//			request.setAttribute("hisList", hisList);
			String loanId =  request.getParameter("loanId");
			String flag =  request.getParameter("flag");
			String type =  request.getParameter("type");
			String div =  request.getParameter("div");
			String appLoanTypeId =  request.getParameter("appLoanTypeId");
			String write =  request.getParameter("write");
			BigDecimal totalAmount = new BigDecimal(0);

			if(StringUtils.isNotBlank(loanId)&&StringUtils.isNumeric(loanId)&&StringUtils.isNotBlank(flag)&&StringUtils.isNotBlank(type)){
				request.setAttribute("pagename", getPageName(flag,type));

				if(flag.equals("cash")){
					List<LnBalanceCash> balanceList = lnBalanceService.getLnBalanceCashByLoanId(Integer.valueOf(loanId), type);
					if(!CollectionUtils.isEmpty(balanceList)){
						for (LnBalanceCash balance : balanceList) {
							if(null!=balance.getAmount()){
								totalAmount=totalAmount.add(balance.getAmount());
							}
						}
					}
					request.setAttribute("balanceList", balanceList);
				}else if(flag.equals("account")){
					List<LnBalanceAccount> balanceList = lnBalanceService.getLnBalanceAccountByLoanId(Integer.valueOf(loanId), type);
					if(!CollectionUtils.isEmpty(balanceList)){
						for (LnBalanceAccount balance : balanceList) {
							if(null!=balance.getAmount()){
								totalAmount=totalAmount.add(balance.getAmount());
							}
						}
						request.setAttribute("totalAmount", totalAmount);
					}
					request.setAttribute("balanceList", balanceList);
				}else if(flag.equals("fixed")){
					List<LnBalanceFixed> balanceList = lnBalanceService.getLnBalanceFixedByLoanId(Integer.valueOf(loanId), type);
					if(!CollectionUtils.isEmpty(balanceList)){
						for (LnBalanceFixed balance : balanceList) {
							if(null!=balance.getPresentAmount()){
								totalAmount=totalAmount.add(balance.getPresentAmount());
							}
						}
						request.setAttribute("totalAmount", totalAmount);
					}
					request.setAttribute("balanceList", balanceList);
				}else if(flag.equals("debt")){
					List<LnBalanceDebt> balanceList = lnBalanceService.getLnBalanceDebtByLoanId(Integer.valueOf(loanId), type);
					if(!CollectionUtils.isEmpty(balanceList)){
						for (LnBalanceDebt balance : balanceList) {
							if(null!=balance.getAmount()){
								totalAmount=totalAmount.add(balance.getLoanBalance());
							}
						}
						request.setAttribute("totalAmount", totalAmount);
					}
					request.setAttribute("balanceList", balanceList);
				}else if(flag.equals("validation")){
					List<LnValidationItem> validationList = lnBalanceService.getLnValidationItemByLoanId(Integer.valueOf(loanId), type);
					request.setAttribute("validationList", validationList);
				}

			}

			HashMap<String,Object> checkBoxMessageMap = buildLoanCheckBoxMessage("cash1","fixed11","fixed12","cash2","cash3","fixed21","cash6","debt5");
			request.setAttribute("checkBoxMessage", checkBoxMessageMap);
			request.setAttribute("totalAmount", totalAmount);
			request.setAttribute("flag", flag);
			request.setAttribute("type", type);
			request.setAttribute("div", div);
			request.setAttribute("appLoanTypeId", appLoanTypeId);
			request.setAttribute("write", write);

			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}

	public String getSaveBalancePage() {
		try{


			String loanId =  request.getParameter("loanId");
			String flag =  request.getParameter("flag");
			String type =  request.getParameter("type");
			String id =  request.getParameter("id");
			String appLoanTypeId =  request.getParameter("appLoanTypeId");

			if(StringUtils.isNotBlank(flag)&&StringUtils.isNotBlank(type)){
				request.setAttribute("pagename", getPageName(flag,type));

				if(flag.equals("cash")){
					//现金资产项目表 LN_BALANCE_CASH  (1银行及现金存款 2其他经营资产 3其他非经营资产 4其他资产 5其他负债 6投资性资产 7对外债权 8预付款)
					if(StringUtils.isNotBlank(id)&&StringUtils.isNumeric(id)){
						LnBalanceCash balanceCash = lnBalanceService.getLnBalanceCashById(Integer.valueOf(id));
						request.setAttribute("balanceCash", balanceCash);
					}
				} else if(flag.equals("account")){
					//账款项目表 LN_BALANCE_ACCOUNT (1应收账款 2预付账款 3应付账款 4预收账款)
					if(StringUtils.isNotBlank(id)&&StringUtils.isNumeric(id)){
						LnBalanceAccount balanceAccount = lnBalanceService.getLnBalanceAccountById(Integer.valueOf(id));
						request.setAttribute("balanceAccount", balanceAccount);
					}
				}else if(flag.equals("fixed")){
//					固定资产项目表 LN_BALANCE_FIXED (1固定资产 2存货)
					if(StringUtils.isNotBlank(id)&&StringUtils.isNumeric(id)){
						LnBalanceFixed balanceFixed = lnBalanceService.getLnBalanceFixedById(Integer.valueOf(id));
						request.setAttribute("balanceFixed", balanceFixed);
					}
				}else if(flag.equals("debt")){
//					负债项目表 LN_BALANCE_DEBT （1短期负债 2长期负债 3自用性负债 4投资性负债 5消费性负债 6其他负债）
					if(StringUtils.isNotBlank(id)&&StringUtils.isNumeric(id)){
						LnBalanceDebt balanceDebt = lnBalanceService.getLnBalanceDebtById(Integer.valueOf(id));
						request.setAttribute("balanceDebt", balanceDebt);
					}
				}else if(flag.equals("validation")){
//					LN_VALIDATION_ITEM  （1销售额检验 2毛利检验 3净利检验 4收入检验 ）
					if(StringUtils.isNotBlank(id)&&StringUtils.isNumeric(id)){
						LnValidationItem validationItem = lnBalanceService.getLnValidationItemById(Integer.valueOf(id));
						request.setAttribute("validationItem", validationItem);
					}
				}


			}

			HashMap<String,Object> checkBoxMessageMap = buildLoanCheckBoxMessage("cash1","fixed11","fixed12","cash2","cash3","fixed21","cash6","debt5");
			request.setAttribute("checkBoxMessage", checkBoxMessageMap);

			request.setAttribute("flag", flag);
			request.setAttribute("type", type);
			request.setAttribute("id", id);
			request.setAttribute("loanId", loanId);
			request.setAttribute("appLoanTypeId", appLoanTypeId);

			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}

	private HashMap<String,Object> buildLoanCheckBoxMessage(String...param){

		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		for (int i = 0; i < param.length; i++) {
			paramMap.put("dictionaryName", param[i]);
			List<LnLoanInfoDictionary> lnLoanInfoDictionaryList = lnLoanInfoDictionaryService.selectLoanInfoDictionaryList(paramMap);
			resultMap.put(param[i], lnLoanInfoDictionaryList);
			paramMap.remove("dictionaryKey");
		}
		return resultMap;
	}


	private String getPageName(String flag,String type){
		String pageName = "";
		if(flag.equals("cash")) {
			if(type.equals("1")){
				pageName = "银行及现金存款";
			}else if(type.equals("2")){
				pageName = "其他经营资产";
			}else if(type.equals("3")){
				pageName = "其他非经营资产";
			}else if(type.equals("4")){
				pageName = "其他资产";
			}else if(type.equals("5")){
				pageName = "其他负债";
			}else if(type.equals("6")){
				pageName = "投资性资产";
			}else if(type.equals("7")){
				pageName = "对外债权";
			}else if(type.equals("8")){
				pageName = "预付款";
			}
		}else if(flag.equals("account")) {
			if(type.equals("1")){
				pageName = "应收账款";
			}else if(type.equals("2")){
				pageName = "预付账款";
			}else if(type.equals("3")){
				pageName = "应付账款";
			}else if(type.equals("4")){
				pageName = "预收账款";
			}
		}else if(flag.equals("fixed")) {
			if(type.equals("1")){
				pageName = "固定资产";
			}else if(type.equals("2")){
				pageName = "存货";
			}
		}else if(flag.equals("debt")) {
			if(type.equals("1")){
				pageName = "短期负债";
			}else if(type.equals("2")){
				pageName = "长期负债";
			}else if(type.equals("3")){
				pageName = "自用性负债";
			}else if(type.equals("4")){
				pageName = "投资性负债";
			}else if(type.equals("5")){
				pageName = "消费性负债";
			}else if(type.equals("6")){
				pageName = "其他负债";
			}
		}else if(flag.equals("validation")) {
			if(type.equals("1")){
				pageName = "销售额检验";
			}else if(type.equals("2")){
				pageName = "毛利检验";
			}else if(type.equals("3")){
				pageName = "净利检验";
			}else if(type.equals("4")){
				pageName = "收入检验";
			}
		}
		return pageName;
	}

	public void saveBalance(){
		try{

			this.getResponse().setContentType("text/html;charset=utf-8");
			PrintWriter out = this.getResponse().getWriter();

			String loanId =  request.getParameter("loanId");
			String flag =  request.getParameter("flag");
			String type =  request.getParameter("type");
			String id =  request.getParameter("oldId");
			out.print("success");

			if(StringUtils.isNotBlank(flag)&&StringUtils.isNotBlank(type)){
				if(flag.equals("cash")){
					if(StringUtils.isNotBlank(id)&&StringUtils.isNumeric(id)&&!id.equals("0")){
						//修改
						balanceCash.setUpdateDate(new Date());
						balanceCash.setUpdateUser(this.getLoginInfo().getUserId());
						balanceCash.setId(Integer.valueOf(id));
						lnBalanceService.updateLnBalanceCashById(balanceCash);
					}else if(StringUtils.isNotBlank(loanId)&&StringUtils.isNumeric(loanId)&&!loanId.equals("0")){
						// 新增
						balanceCash.setCreateDate(new Date());
						balanceCash.setCreateUser(this.getLoginInfo().getUserId());
						balanceCash.setLoanId(Integer.valueOf(loanId));
						balanceCash.setType(type);
						lnBalanceService.insertLnBalanceCash(balanceCash);
					}else{
						out.print("error");
					}
				}else if(flag.equals("account")){
					if(StringUtils.isNotBlank(id)&&StringUtils.isNumeric(id)&&!id.equals("0")){
						//修改
						balanceAccount.setUpdateDate(new Date());
						balanceAccount.setUpdateUser(this.getLoginInfo().getUserId());
						balanceAccount.setId(Integer.valueOf(id));
						lnBalanceService.updateLnBalanceAccountById(balanceAccount);
					}else if(StringUtils.isNotBlank(loanId)&&StringUtils.isNumeric(loanId)&&!loanId.equals("0")){
						// 新增
						balanceAccount.setCreateDate(new Date());
						balanceAccount.setCreateUser(this.getLoginInfo().getUserId());
						balanceAccount.setLoanId(Integer.valueOf(loanId));
						balanceAccount.setType(type);
						lnBalanceService.insertLnBalanceAccount(balanceAccount);
					}else{
						out.print("error");
					}
				}else if(flag.equals("fixed")){
					if(StringUtils.isNotBlank(id)&&StringUtils.isNumeric(id)&&!id.equals("0")){
						//修改
						balanceFixed.setUpdateDate(new Date());
						balanceFixed.setUpdateUser(this.getLoginInfo().getUserId());
						balanceFixed.setId(Integer.valueOf(id));
						lnBalanceService.updateLnBalanceFixedById(balanceFixed);
					}else if(StringUtils.isNotBlank(loanId)&&StringUtils.isNumeric(loanId)&&!loanId.equals("0")){
						// 新增
						balanceFixed.setCreateDate(new Date());
						balanceFixed.setCreateUser(this.getLoginInfo().getUserId());
						balanceFixed.setLoanId(Integer.valueOf(loanId));
						balanceFixed.setType(type);
						lnBalanceService.insertLnBalanceFixed(balanceFixed);
					}else{
						out.print("error");
					}
				}else if(flag.equals("debt")){
					if(StringUtils.isNotBlank(id)&&StringUtils.isNumeric(id)&&!id.equals("0")){
						//修改
						balanceDebt.setUpdateDate(new Date());
						balanceDebt.setUpdateUser(this.getLoginInfo().getUserId());
						balanceDebt.setId(Integer.valueOf(id));
						lnBalanceService.updateLnBalanceDebtById(balanceDebt);
					}else if(StringUtils.isNotBlank(loanId)&&StringUtils.isNumeric(loanId)&&!loanId.equals("0")){
						// 新增
						balanceDebt.setCreateDate(new Date());
						balanceDebt.setCreateUser(this.getLoginInfo().getUserId());
						balanceDebt.setLoanId(Integer.valueOf(loanId));
						balanceDebt.setType(type);
						lnBalanceService.insertLnBalanceDebt(balanceDebt);
					}else{
						out.print("error");
					}
				}else if(flag.equals("validation")){
					if(StringUtils.isNotBlank(id)&&StringUtils.isNumeric(id)&&!id.equals("0")){
						//修改
						validationItem.setUpdateDate(new Date());
						validationItem.setUpdateUser(this.getLoginInfo().getUserId());
						validationItem.setId(Integer.valueOf(id));
						lnBalanceService.updateLnValidationItemById(validationItem);
					}else if(StringUtils.isNotBlank(loanId)&&StringUtils.isNumeric(loanId)&&!loanId.equals("0")){
						// 新增
						validationItem.setCreateDate(new Date());
						validationItem.setCreateUser(this.getLoginInfo().getUserId());
						validationItem.setLoanId(Integer.valueOf(loanId));
						validationItem.setType(type);
						lnBalanceService.insertLnValidationItem(validationItem);
					}else{
						out.print("error");
					}
				}


			}

		}catch (Exception e){
			e.printStackTrace();
		}

	}

	public void saveLnBalanceOff(){
		try{

			this.getResponse().setContentType("text/html;charset=utf-8");
			PrintWriter out = this.getResponse().getWriter();

			String loanId =  request.getParameter("loanId");
			String balanceAdviceTime =  request.getParameter("balanceAdviceTime");
			String balanceAmount =  request.getParameter("balanceAmount");
			String debtAmount =  request.getParameter("debtAmount");
			String remark =  request.getParameter("remark");

			out.print("success");

			if(StringUtils.isNotBlank(loanId)&&StringUtils.isNumeric(loanId)){
				LnBalanceOff lnBalanceOff = lnBalanceService.getLnBalanceOffById(Integer.valueOf(loanId));
				if(null!=lnBalanceOff){
					//修改
					lnBalanceOff.setUpdateDate(new Date());
					lnBalanceOff.setUpdateUser(this.getLoginInfo().getUserId());
					if(StringUtils.isNotBlank(balanceAdviceTime)){
						lnBalanceOff.setBalanceAdviceTime(new SimpleDateFormat("yyyy-MM-dd").parse(balanceAdviceTime));
					}
					if(StringUtils.isNotBlank(balanceAmount)&&StringUtils.isNumeric(balanceAmount)){
						lnBalanceOff.setBalanceAmount(new BigDecimal(balanceAmount));
					}
					if(StringUtils.isNotBlank(debtAmount)&&StringUtils.isNumeric(debtAmount)) {
						lnBalanceOff.setDebtAmount(new BigDecimal(debtAmount));
					}
					if(StringUtils.isNotBlank(remark)) {
						lnBalanceOff.setRemark(remark);
					}
					lnBalanceService.updateLnBalanceOffById(lnBalanceOff);
				}else{
					//新增
					lnBalanceOff = new LnBalanceOff();
					if(StringUtils.isNotBlank(balanceAdviceTime)){
						lnBalanceOff.setBalanceAdviceTime(new SimpleDateFormat("yyyy-MM-dd").parse(balanceAdviceTime));
					}
					if(StringUtils.isNotBlank(balanceAmount)&&StringUtils.isNumeric(balanceAmount)){
						lnBalanceOff.setBalanceAmount(new BigDecimal(balanceAmount));
					}
					if(StringUtils.isNotBlank(debtAmount)&&StringUtils.isNumeric(debtAmount)) {
						lnBalanceOff.setDebtAmount(new BigDecimal(debtAmount));
					}
					if(StringUtils.isNotBlank(remark)) {
						lnBalanceOff.setRemark(remark);
					}
					lnBalanceOff.setCreateDate(new Date());
					lnBalanceOff.setCreateUser(this.getLoginInfo().getUserId());
					lnBalanceOff.setLoanId(Integer.valueOf(loanId));
					lnBalanceService.insertLnBalanceOff(lnBalanceOff);
				}

			}

		}catch (Exception e){
			e.printStackTrace();
		}

	}

	public void getLnBalanceOff(){
		try{

			this.getResponse().setContentType("text/html;charset=utf-8");
			PrintWriter out = this.getResponse().getWriter();

			String loanId =  request.getParameter("loanId");

			if(StringUtils.isNotBlank(loanId)&&StringUtils.isNumeric(loanId)){
				LnBalanceOff lnBalanceOff = lnBalanceService.getLnBalanceOffById(Integer.valueOf(loanId));
				if(null!=lnBalanceOff){
					out.print(getJsonString(lnBalanceOff));
				}else{
					out.print("");
				}
			}

		}catch (Exception e){
			e.printStackTrace();
		}

	}

	public void getLnValidationRights(){
		try{

			this.getResponse().setContentType("text/html;charset=utf-8");
			PrintWriter out = this.getResponse().getWriter();

			String loanId =  request.getParameter("loanId");
			String appLoanTypeId =  request.getParameter("appLoanTypeId");

			if(StringUtils.isNotBlank(loanId)&&StringUtils.isNumeric(loanId)){
				LnValidationRights lnValidationRights = lnBalanceService.getLnValidationRightsById(Integer.valueOf(loanId));
				if(null!=lnValidationRights){

					out.print(getJsonString(lnValidationRights));
				}else{
					out.print("");
				}
			}

		}catch (Exception e){
			e.printStackTrace();
		}

	}

	private void setValidation(LnValidationRights lnValidationRights,Integer loanId,String appLoanTypeId){

		//总资产
		BigDecimal cash1 = lnBalanceService.sumByFlagAndType(loanId, "cash", "1");
		if(null==cash1){
			cash1=new BigDecimal(0);
		}
		BigDecimal account1 = lnBalanceService.sumByFlagAndType(loanId, "account", "1");
		if(null==account1){
			account1 = new BigDecimal(0);
		}
		BigDecimal account2 = lnBalanceService.sumByFlagAndType(loanId, "account", "2");
		if(null==account2){
			account2 = new BigDecimal(0);
		}
		BigDecimal fixed2 = lnBalanceService.sumByFlagAndType(loanId, "fixed", "2");
		if(null==fixed2){
			fixed2 = new BigDecimal(0);
		}
		BigDecimal fixed1 = lnBalanceService.sumByFlagAndType(loanId, "fixed", "1");
		if(null==fixed1){
			fixed1 = new BigDecimal(0);
		}
		BigDecimal cash2 = lnBalanceService.sumByFlagAndType(loanId, "cash", "2");
		if(null==cash2){
			cash2 = new BigDecimal(0);
		}
		BigDecimal cash3 = lnBalanceService.sumByFlagAndType(loanId, "cash", "3");
		if(null==cash3){
			cash3 = new BigDecimal(0);
		}
		BigDecimal cash4 = lnBalanceService.sumByFlagAndType(loanId, "cash", "4");
		if(null==cash4){
			cash4 = new BigDecimal(0);
		}
		BigDecimal cash6 = lnBalanceService.sumByFlagAndType(loanId, "cash", "6");
		if(null==cash6){
			cash6 = new BigDecimal(0);
		}
		BigDecimal cash7 = lnBalanceService.sumByFlagAndType(loanId, "cash", "7");
		if(null==cash7){
			cash7 = new BigDecimal(0);
		}
		BigDecimal cash8 = lnBalanceService.sumByFlagAndType(loanId, "cash", "8");
		if(null==cash8){
			cash8 = new BigDecimal(0);
		}

		BigDecimal totalAmount = (cash1.add(account1).add(account1).add(fixed2).add(fixed1)
				.add(cash2).add(cash3).add(cash4).add(cash6).add(cash7).add(cash8));


		//总负债
		BigDecimal account3 = lnBalanceService.sumByFlagAndType(loanId, "account", "3");
		if(null==account3){
			account3 = new BigDecimal(0);
		}
		BigDecimal account4 = lnBalanceService.sumByFlagAndType(loanId, "account", "4");
		if(null==account4){
			account4 = new BigDecimal(0);
		}
		BigDecimal debt1 = lnBalanceService.sumByFlagAndType(loanId, "debt", "1");
		if(null==debt1){
			debt1 = new BigDecimal(0);
		}
		BigDecimal debt2 = lnBalanceService.sumByFlagAndType(loanId, "debt", "2");
		if(null==debt2){
			debt2 = new BigDecimal(0);
		}
		BigDecimal cash5 = lnBalanceService.sumByFlagAndType(loanId, "cash", "5");
		if(null==cash5){
			cash5 = new BigDecimal(0);
		}
		BigDecimal debt3 = lnBalanceService.sumByFlagAndType(loanId, "debt", "3");
		if(null==debt3){
			debt3 = new BigDecimal(0);
		}
		BigDecimal debt4 = lnBalanceService.sumByFlagAndType(loanId, "debt", "4");
		if(null==debt4){
			debt4 = new BigDecimal(0);
		}
		BigDecimal debt5 = lnBalanceService.sumByFlagAndType(loanId, "debt", "5");
		if(null==debt5) {
			debt5 = new BigDecimal(0);
		}
		BigDecimal debt6 = lnBalanceService.sumByFlagAndType(loanId, "debt", "6");
		if (null == debt6) {
			debt6 = new BigDecimal(0);
		}

		BigDecimal totalDebtAmount = (account3.add(account4).add(debt1).add(debt2).add(cash5).add(debt3)
				.add(debt4).add(debt5).add(debt6));

		//实际权益
		lnValidationRights.setRightsRealAmount(totalAmount.subtract(totalDebtAmount));

		//实际权益
		//期间利润 营业收入*净利率（损益表）
		//期间收入 profitLoss6
		if("1".equals(appLoanTypeId)){
			BigDecimal profitLoss1 = lnProfitLossBaseService.sumItemByType(loanId, "1");
			if (null == profitLoss1) {
				profitLoss1 = new BigDecimal(0);
			}
			BigDecimal weightedGrossRate = lnProfitLossProdService.getWeightedGrossRate(loanId);

			BigDecimal profitLoss2 = lnProfitLossBaseService.sumItemByType(loanId, "2");
			if (null == profitLoss2) {
				profitLoss2 = new BigDecimal(0);
			}
			BigDecimal profitLoss3 = lnProfitLossBaseService.sumItemByType(loanId, "3");
			if (null == profitLoss3) {
				profitLoss3 = new BigDecimal(0);
			}
			BigDecimal profitLoss4 = lnProfitLossBaseService.sumItemByType(loanId, "4");
			if (null == profitLoss4) {
				profitLoss4 = new BigDecimal(0);
			}
			BigDecimal profitLoss5 = lnProfitLossBaseService.sumItemByType(loanId, "5");
			if (null == profitLoss5) {
				profitLoss5 = new BigDecimal(0);
			}
			lnValidationRights.setIncreaseAmount(weightedGrossRate.multiply(profitLoss1).add(profitLoss4).subtract(profitLoss2).subtract(profitLoss3).subtract(profitLoss5));

//			profitLoss2固定支出
//					profitLoss3个税
//			profitLoss4其他收入
//					profitLoss5其他支出
//			加权毛利率*营业收入总额-(固定支出-个税-其他支出+其他收入)


		}else{
			BigDecimal profitLoss6 = lnProfitLossBaseService.sumItemByType(loanId, "6");
			if (null == profitLoss6) {
				profitLoss6 = new BigDecimal(0);
			}
			lnValidationRights.setIncreaseAmount(profitLoss6);
		}


	}

	public void saveLnValidationRights(){
		try{

			this.getResponse().setContentType("text/html;charset=utf-8");
			PrintWriter out = this.getResponse().getWriter();

			String loanId =  request.getParameter("loanId");
			String rightsBeginDate  =  request.getParameter("rightsBeginDate");
			String rightsBeginAmount  =  request.getParameter("rightsBeginAmount");
			String rightsBeginRemark =  request.getParameter("rightsBeginRemark");
			String increaseAmount =  request.getParameter("increaseAmount");
			String increaseFinancing =  request.getParameter("increaseFinancing");
			String increaseValue =  request.getParameter("increaseValue");
			String increaseRemark =  request.getParameter("increaseRemark");
			String lessenAmount =  request.getParameter("lessenAmount");
			String lessenValue =  request.getParameter("lessenValue");
			String lessenRemark =  request.getParameter("lessenRemark");
			String rightsDueAmount =  request.getParameter("rightsDueAmount");
			String rightsRealAmount =  request.getParameter("rightsRealAmount");
			String rightsDepreciationValue =  request.getParameter("rightsDepreciationValue");
			String rightsDepreciationRate =  request.getParameter("rightsDepreciationRate");


			out.print("success");

			if(StringUtils.isNotBlank(loanId)&&StringUtils.isNumeric(loanId)){
				LnValidationRights lnValidationRights = lnBalanceService.getLnValidationRightsById(Integer.valueOf(loanId));
				if(null!=lnValidationRights){
					//修改
					lnValidationRights.setUpdateDate(new Date());
					lnValidationRights.setUpdateUser(this.getLoginInfo().getUserId());
					if(StringUtils.isNotBlank(rightsBeginDate)){
						lnValidationRights.setRightsBeginDate(new SimpleDateFormat("yyyy-MM-dd").parse(rightsBeginDate));
					}
					if(StringUtils.isNotBlank(rightsBeginAmount)&&StringUtils.isNumeric(rightsBeginAmount)){
						lnValidationRights.setRightsBeginAmount(new BigDecimal(rightsBeginAmount));
					}
					if(StringUtils.isNotBlank(rightsBeginRemark)) {
						lnValidationRights.setRightsBeginRemark(rightsBeginRemark);
					}
					if(StringUtils.isNotBlank(increaseAmount)&&StringUtils.isNumeric(increaseAmount)){
						lnValidationRights.setIncreaseAmount(new BigDecimal(increaseAmount));
					}
					if(StringUtils.isNotBlank(increaseFinancing)&&StringUtils.isNumeric(increaseFinancing)){
						lnValidationRights.setIncreaseFinancing(new BigDecimal(increaseFinancing));
					}
					if(StringUtils.isNotBlank(increaseValue)&&StringUtils.isNumeric(increaseValue)){
						lnValidationRights.setIncreaseValue(new BigDecimal(increaseValue));
					}
					if(StringUtils.isNotBlank(increaseRemark)){
						lnValidationRights.setIncreaseRemark(increaseRemark);
					}
					if(StringUtils.isNotBlank(lessenAmount)&&StringUtils.isNumeric(lessenAmount)){
						lnValidationRights.setLessenAmount(new BigDecimal(lessenAmount));
					}
					if(StringUtils.isNotBlank(lessenValue)&&StringUtils.isNumeric(lessenValue)){
						lnValidationRights.setLessenValue(new BigDecimal(lessenValue));
					}
					if(StringUtils.isNotBlank(lessenRemark)){
						lnValidationRights.setLessenRemark(lessenRemark);
					}
					if(StringUtils.isNotBlank(rightsDueAmount)&&StringUtils.isNumeric(rightsDueAmount)){
						lnValidationRights.setRightsDueAmount(new BigDecimal(rightsDueAmount));
					}
					if(StringUtils.isNotBlank(rightsRealAmount)&&StringUtils.isNumeric(rightsRealAmount)){
						lnValidationRights.setRightsRealAmount(new BigDecimal(rightsRealAmount));
					}
					if(StringUtils.isNotBlank(rightsDepreciationValue)&&StringUtils.isNumeric(rightsDepreciationValue)){
						lnValidationRights.setRightsDepreciationValue(new BigDecimal(rightsDepreciationValue));
					}
					if(StringUtils.isNotBlank(rightsDepreciationRate)&&StringUtils.isNumeric(rightsDepreciationRate)){
						lnValidationRights.setRightsDepreciationRate(Double.valueOf(rightsDepreciationRate));
					}
					lnBalanceService.updateLnValidationRightsById(lnValidationRights);
				}else{
					//新增
					lnValidationRights = new LnValidationRights();
					if(StringUtils.isNotBlank(rightsBeginDate)){
						lnValidationRights.setRightsBeginDate(new SimpleDateFormat("yyyy-MM-dd").parse(rightsBeginDate));
					}
					if(StringUtils.isNotBlank(rightsBeginAmount)&&StringUtils.isNumeric(rightsBeginAmount)){
						lnValidationRights.setRightsBeginAmount(new BigDecimal(rightsBeginAmount));
					}
					if(StringUtils.isNotBlank(rightsBeginRemark)) {
						lnValidationRights.setRightsBeginRemark(rightsBeginRemark);
					}
					if(StringUtils.isNotBlank(increaseAmount)&&StringUtils.isNumeric(increaseAmount)){
						lnValidationRights.setIncreaseAmount(new BigDecimal(increaseAmount));
					}
					if(StringUtils.isNotBlank(increaseFinancing)&&StringUtils.isNumeric(increaseFinancing)){
						lnValidationRights.setIncreaseFinancing(new BigDecimal(increaseFinancing));
					}
					if(StringUtils.isNotBlank(increaseValue)&&StringUtils.isNumeric(increaseValue)){
						lnValidationRights.setIncreaseValue(new BigDecimal(increaseValue));
					}
					if(StringUtils.isNotBlank(increaseRemark)){
						lnValidationRights.setIncreaseRemark(increaseRemark);
					}
					if(StringUtils.isNotBlank(lessenAmount)&&StringUtils.isNumeric(lessenAmount)){
						lnValidationRights.setLessenAmount(new BigDecimal(lessenAmount));
					}
					if(StringUtils.isNotBlank(lessenValue)&&StringUtils.isNumeric(lessenValue)){
						lnValidationRights.setLessenValue(new BigDecimal(lessenValue));
					}
					if(StringUtils.isNotBlank(lessenRemark)){
						lnValidationRights.setLessenRemark(lessenRemark);
					}
					if(StringUtils.isNotBlank(rightsDueAmount)&&StringUtils.isNumeric(rightsDueAmount)){
						lnValidationRights.setRightsDueAmount(new BigDecimal(rightsDueAmount));
					}
					if(StringUtils.isNotBlank(rightsRealAmount)&&StringUtils.isNumeric(rightsRealAmount)){
						lnValidationRights.setRightsRealAmount(new BigDecimal(rightsRealAmount));
					}
					if(StringUtils.isNotBlank(rightsDepreciationValue)&&StringUtils.isNumeric(rightsDepreciationValue)){
						lnValidationRights.setRightsDepreciationValue(new BigDecimal(rightsDepreciationValue));
					}
					if(StringUtils.isNotBlank(rightsDepreciationRate)&&StringUtils.isNumeric(rightsDepreciationRate)){
						lnValidationRights.setRightsDepreciationRate(Double.valueOf(rightsDepreciationRate));
					}
					lnValidationRights.setCreateDate(new Date());
					lnValidationRights.setCreateUser(this.getLoginInfo().getUserId());
					lnValidationRights.setLoanId(Integer.valueOf(loanId));
					lnBalanceService.insertLnValidationRights(lnValidationRights);
				}

			}

		}catch (Exception e){
			e.printStackTrace();
		}

	}

	private String getJsonString(Object object) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new com.banger.mobile.util.JsonDateValueProcessor());
		String result = JSONObject.fromObject(object, jsonConfig).toString();
		return result;
	}

	public void removeBalance(){
		try{

			this.getResponse().setContentType("text/html;charset=utf-8");
			PrintWriter out = this.getResponse().getWriter();

			String flag =  request.getParameter("flag");
			String id =  request.getParameter("id");
			out.print("success");

			if(StringUtils.isNotBlank(flag)&&StringUtils.isNotBlank(id)&&StringUtils.isNumeric(id)){
				if(flag.equals("cash")){
					// 删除
					lnBalanceService.deleteLnBalanceCashById(Integer.valueOf(id));
				}else if(flag.equals("account")){
					// 删除
					lnBalanceService.deleteLnBalanceAccountById(Integer.valueOf(id));
				}else if(flag.equals("fixed")){
					// 删除
					lnBalanceService.deleteLnBalanceFixedById(Integer.valueOf(id));
				}else if(flag.equals("debt")){
					// 删除
					lnBalanceService.deleteLnBalanceDebtById(Integer.valueOf(id));
				}else if(flag.equals("validation")){
					// 删除
					lnBalanceService.deleteLnValidationItemById(Integer.valueOf(id));
				}
			}

		}catch (Exception e){
			e.printStackTrace();
		}

	}
	

}
