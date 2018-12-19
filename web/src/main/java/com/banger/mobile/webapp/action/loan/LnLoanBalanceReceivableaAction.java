package com.banger.mobile.webapp.action.loan;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.banger.mobile.domain.model.loan.LnLoanBalanceReceivable;
import com.banger.mobile.domain.model.loan.LnLoanInfoDictionary;
import com.banger.mobile.facade.loan.LnLoanBalanceReceivableService;
import com.banger.mobile.facade.loan.LnLoanInfoDictionaryService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * 应收账款明细
 * @author Administrator
 *
 */
public class LnLoanBalanceReceivableaAction extends BaseAction {

	  private static Logger         logger = Logger.getLogger(LnLoanBalanceReceivableaAction.class);
	  private LnLoanBalanceReceivableService lnLoanBalanceReceivableService;   
	  private LnLoanBalanceReceivable lnLoanBalanceReceivable;
	  private LnLoanInfoDictionaryService lnLoanInfoDictionaryService;
	 
	  

	  	

	public void setLnLoanInfoDictionaryService(
			LnLoanInfoDictionaryService lnLoanInfoDictionaryService) {
		this.lnLoanInfoDictionaryService = lnLoanInfoDictionaryService;
	}


	public LnLoanBalanceReceivable getLnLoanBalanceReceivable() {
		return lnLoanBalanceReceivable;
	}


	public void setLnLoanBalanceReceivable(
			LnLoanBalanceReceivable lnLoanBalanceReceivable) {
		this.lnLoanBalanceReceivable = lnLoanBalanceReceivable;
	}


	public void setLnLoanBalanceReceivableService(
			LnLoanBalanceReceivableService lnLoanBalanceReceivableService) {
		this.lnLoanBalanceReceivableService = lnLoanBalanceReceivableService;
	}


	/**
	    * 跳转编辑页面
	    */
		public String selectReceivableUI(){
			String loanBalanceId = this.getRequest().getParameter("loanBalanceId");
			//查询基础数据
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("dictionaryName", "LNJK");
			List<LnLoanInfoDictionary> loanInfoList = lnLoanInfoDictionaryService
					.selectLoanInfoDictionaryList(param);
			this.getRequest().setAttribute("dictionary", loanInfoList);
			this.getRequest().setAttribute("loanBalanceId",loanBalanceId);
			return SUCCESS;
		}
	
	
	/**
	    * 删除
	    */
		public void deleteReceivable(){
			PrintWriter out = null;
			try {
				HttpServletResponse response = this.getResponse();
				out = response.getWriter();
				response.setContentType("text/html");
				
			lnLoanBalanceReceivableService.deleteReceivable(lnLoanBalanceReceivable);
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
		public String selectReceivable(){
			
			try {
				LnLoanBalanceReceivable asset=null;
				List<LnLoanBalanceReceivable> assetList=lnLoanBalanceReceivableService.selectReceivableByPrimary(lnLoanBalanceReceivable);
					
				if(assetList!=null&&assetList.size()>0){
					 asset=assetList.get(0);
					 this.getRequest().setAttribute("loanBalanceId",asset.getLoanBalanceId());
				}
					this.getRequest().setAttribute("asset", asset);
					
					//查询基础数据
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("dictionaryName", "LNJK");
					List<LnLoanInfoDictionary> loanInfoList = lnLoanInfoDictionaryService
							.selectLoanInfoDictionaryList(param);
					this.getRequest().setAttribute("dictionary", loanInfoList);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
				return SUCCESS;
			
		  }
/**
* 保存修改经营贷经营模式
*/
	public void insertReceivable(){
		PrintWriter out = null;
		try {
			HttpServletResponse response = this.getResponse();
			out = response.getWriter();
			response.setContentType("text/html");
			
			LnLoanBalanceReceivable in;
			if(lnLoanBalanceReceivable.getLoanBalanceReceivableId()==null){
				in =lnLoanBalanceReceivableService.insertReceivable(lnLoanBalanceReceivable);
			}else{
				in=lnLoanBalanceReceivableService.updateReceivable(lnLoanBalanceReceivable);
			}
			
			String json = "{\"success\":true,\"id\":"+in.getLoanBalanceReceivableId()+"}";
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
 * 列表页
 * @return
 */
	public String receivableList(){
		
			//根据资产负债_应收账明细
			List<LnLoanBalanceReceivable> assetList = lnLoanBalanceReceivableService.selectReceivableByPrimary(lnLoanBalanceReceivable);
			
			this.getRequest().setAttribute("lnLoanBalanceReceivableList", assetList);
			
			
			return SUCCESS;
	
	  }

	  

	
}
