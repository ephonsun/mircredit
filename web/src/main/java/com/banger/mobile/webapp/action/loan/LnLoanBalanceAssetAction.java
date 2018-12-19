package com.banger.mobile.webapp.action.loan;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.banger.mobile.domain.model.loan.LnBusinessModel;
import com.banger.mobile.domain.model.loan.LnLoanBalanceAsset;
import com.banger.mobile.domain.model.loan.LnLoanInfoDictionary;
import com.banger.mobile.facade.loan.LnLoanBalanceAssetService;
import com.banger.mobile.facade.loan.LnLoanInfoDictionaryService;
import com.banger.mobile.webapp.action.BaseAction;

public class LnLoanBalanceAssetAction extends BaseAction {

	  private static Logger         logger = Logger.getLogger(LnLoanBalanceAssetAction.class);
	  private LnLoanBalanceAssetService lnLoanBalanceAssetService;   
	  private LnLoanBalanceAsset lnLoanBalanceAsset;
	  private LnLoanInfoDictionaryService lnLoanInfoDictionaryService;
	  
	public void setLnLoanInfoDictionaryService(
			LnLoanInfoDictionaryService lnLoanInfoDictionaryService) {
		this.lnLoanInfoDictionaryService = lnLoanInfoDictionaryService;
	}
	public LnLoanBalanceAsset getLnLoanBalanceAsset() {
		return lnLoanBalanceAsset;
	}
	public void setLnLoanBalanceAsset(LnLoanBalanceAsset lnLoanBalanceAsset) {
		this.lnLoanBalanceAsset = lnLoanBalanceAsset;
	}
	public void setLnLoanBalanceAssetService(
			LnLoanBalanceAssetService lnLoanBalanceAssetService) {
		this.lnLoanBalanceAssetService = lnLoanBalanceAssetService;
	}
	
	/**
	    * 跳转编辑页面
	    */
		public String selectAssetUI(){
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
		public void deleteAsset(){
			PrintWriter out = null;
			try {
				HttpServletResponse response = this.getResponse();
				out = response.getWriter();
				response.setContentType("text/html");
			
			lnLoanBalanceAssetService.deleteAsset(lnLoanBalanceAsset);
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
		public String selectAsset(){
			
			try {
				LnLoanBalanceAsset asset=null;
				List<LnLoanBalanceAsset> assetList=lnLoanBalanceAssetService.selectAssetByPrimary(lnLoanBalanceAsset);
					
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
	public void insertAsset(){
		PrintWriter out = null;
		try {
			HttpServletResponse response = this.getResponse();
			out = response.getWriter();
			response.setContentType("text/html");
			
			LnLoanBalanceAsset in;
			if(lnLoanBalanceAsset.getLoanBalanceAssetId()==null){
				in =lnLoanBalanceAssetService.insertAsset(lnLoanBalanceAsset);
			}else{
				in=lnLoanBalanceAssetService.updateAsset(lnLoanBalanceAsset);
			}
			
			String json = "{\"success\":true,\"id\":"+in.getLoanBalanceAssetId()+"}";
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
	public String assetList(){
		
			//根据资产负债表查旬资产
			List<LnLoanBalanceAsset> assetList = lnLoanBalanceAssetService
					.selectAssetByPrimary(lnLoanBalanceAsset);
			
			this.getRequest().setAttribute("lnLoanBalanceAssetList", assetList);
			
			
			return SUCCESS;
	
	  }

	  
	
}
