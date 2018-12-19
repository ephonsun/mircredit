package com.banger.mobile.webapp.action.loan;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.banger.mobile.domain.model.loan.LnLoanBalanceDebt;
import com.banger.mobile.domain.model.loan.LnLoanInfoDictionary;
import com.banger.mobile.facade.loan.LnLoanBalanceDebtService;
import com.banger.mobile.facade.loan.LnLoanInfoDictionaryService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * 资产负债表 负债
 * @author Administrator
 *
 */
public class LnLoanBalanceDebtAction extends BaseAction {

	  private static Logger         logger = Logger.getLogger(LnLoanBalanceDebtAction.class);
	  private LnLoanBalanceDebtService lnLoanBalanceDebtService;   
	  private LnLoanBalanceDebt lnLoanBalanceDebt;
	  private LnLoanInfoDictionaryService lnLoanInfoDictionaryService;
	
	  
	
	
	public LnLoanBalanceDebt getLnLoanBalanceDebt() {
		return lnLoanBalanceDebt;
	}
	public void setLnLoanBalanceDebt(LnLoanBalanceDebt lnLoanBalanceDebt) {
		this.lnLoanBalanceDebt = lnLoanBalanceDebt;
	}
	public void setLnLoanBalanceDebtService(
			LnLoanBalanceDebtService lnLoanBalanceDebtService) {
		this.lnLoanBalanceDebtService = lnLoanBalanceDebtService;
	}
	public void setLnLoanInfoDictionaryService(
			LnLoanInfoDictionaryService lnLoanInfoDictionaryService) {
		this.lnLoanInfoDictionaryService = lnLoanInfoDictionaryService;
	}
	
	 /**
	    * 跳转编辑页面
	    */
		public String selectDebtUI(){
			String loanBalanceId = getRequest().getParameter("loanBalanceId");
			//加载下拉框数据
			dictionaryNameList();
		    getRequest().setAttribute("loanBalanceId", loanBalanceId);
			return SUCCESS;
		}
	
		//加载下拉框数据
		private void dictionaryNameList(){
					
			String dictionaryName = getRequest().getParameter("dictionaryName");

		    Map param = new HashMap();
		    param.put("dictionaryName", dictionaryName);
		    List loanInfoList = this.lnLoanInfoDictionaryService.selectLoanInfoDictionaryList(param);

		    getRequest().setAttribute("dictionary", loanInfoList);
			
		}
	/**
	    * 删除
	    */
		public void deleteDebt(){
			PrintWriter out = null;
			try {
				HttpServletResponse response = this.getResponse();
				out = response.getWriter();
				response.setContentType("text/html");
				
			lnLoanBalanceDebtService.deleteDebt(lnLoanBalanceDebt);
				String json = "{\"success\":true}";
			out.println(json.toString());
		} catch (Exception e) {
			String json = "{\"success\":false}";
			out.println(json.toString());
			e.printStackTrace();
			logger.error("loanInfoList", e);
		} finally {

			out.flush();
			out.close();

		}
			
			
		  }
	 /**
	    * 单个查询
	    */
		public String selectDebt(){
			
			try {
				LnLoanBalanceDebt asset=null;
				List<LnLoanBalanceDebt> assetList=lnLoanBalanceDebtService.selectDebtByPrimary(lnLoanBalanceDebt);
					
				if(assetList!=null&&assetList.size()>0){
					 asset=assetList.get(0);
					 this.getRequest().setAttribute("loanBalanceId",asset.getLoanBalanceId());
				}
					this.getRequest().setAttribute("asset", asset);
					
					//加载下拉框数据
					dictionaryNameList();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
				return SUCCESS;
			
		  }
/**
* 保存修改经营贷经营模式
*/
	public void insertDebt(){
		PrintWriter out = null;
		try {
			HttpServletResponse response = this.getResponse();
			out = response.getWriter();
			response.setContentType("text/html");
			
			LnLoanBalanceDebt in;
			if(lnLoanBalanceDebt.getLoanBalanceDebtId()==null){
				in =lnLoanBalanceDebtService.insertAsset(lnLoanBalanceDebt);
			}else{
				in=lnLoanBalanceDebtService.updateAsset(lnLoanBalanceDebt);
			}
			
			String json = "{\"success\":true,\"id\":"+in.getLoanBalanceDebtId()+"}";
			out.println(json.toString());
		} catch (Exception e) {
			String json = "{\"success\":false}";
			out.println(json.toString());
			e.printStackTrace();
			logger.error("loanInfoList", e);
		} finally {

			out.flush();
			out.close();

		}
	  }
	/**
  * 资产列表页
  * @return
  */
	public String debtList(){
		
			//根据资产负债表查旬资产
			List<LnLoanBalanceDebt> assetList = lnLoanBalanceDebtService.selectDebtByPrimary(lnLoanBalanceDebt);
			
			this.getRequest().setAttribute("lnLoanBalanceDedtList", assetList);
			
			
			return SUCCESS;
	
	  }

	  
	
}
