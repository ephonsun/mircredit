package com.banger.mobile.webapp.action.loan;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.banger.mobile.domain.model.loan.LnLoanInfoDictionary;
import com.banger.mobile.domain.model.loan.LnLoanProfitandlossXfDetail;
import com.banger.mobile.facade.loan.LnLoanInfoDictionaryService;
import com.banger.mobile.facade.loan.LnLoanProfitandlossXfDetailService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * 消费贷详情
 * @author Administrator
 *
 */
public class LnLoanProfitandlossXfDetailAction extends BaseAction {
	  private static Logger         logger = Logger.getLogger(LnLoanProfitandlossXfDetailAction.class);
	  private LnLoanProfitandlossXfDetailService  lnLoanProfitandlossXfDetailService;
	  private LnLoanProfitandlossXfDetail  lnLoanProfitandlossXfDetail;
	  private LnLoanInfoDictionaryService lnLoanInfoDictionaryService;
	  
	  
	public void setLnLoanInfoDictionaryService(
			LnLoanInfoDictionaryService lnLoanInfoDictionaryService) {
		this.lnLoanInfoDictionaryService = lnLoanInfoDictionaryService;
	}
	public LnLoanProfitandlossXfDetail getLnLoanProfitandlossXfDetail() {
		return lnLoanProfitandlossXfDetail;
	}
	public void setLnLoanProfitandlossXfDetail(
			LnLoanProfitandlossXfDetail lnLoanProfitandlossXfDetail) {
		this.lnLoanProfitandlossXfDetail = lnLoanProfitandlossXfDetail;
	}
	public void setLnLoanProfitandlossXfDetailService(
			LnLoanProfitandlossXfDetailService lnLoanProfitandlossXfDetailService) {
		this.lnLoanProfitandlossXfDetailService = lnLoanProfitandlossXfDetailService;
	}
	  
	
	
	/**
	    * 跳转编辑页面
	    */
		public String selectXfDetailUI(){
			String loanProfitandlossId = this.getRequest().getParameter("loanProfitandlossId");
			//查询基础数据
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("dictionaryName", "LNXFDE");
			List<LnLoanInfoDictionary> loanInfoList = lnLoanInfoDictionaryService
					.selectLoanInfoDictionaryList(param);
		
			this.getRequest().setAttribute("dictionary", loanInfoList);
			param.put("dictionaryName", "LNXFXF");
			List<LnLoanInfoDictionary> loanInfoListItem = lnLoanInfoDictionaryService
					.selectLoanInfoDictionaryList(param);
		
			this.getRequest().setAttribute("dictionaryItem",loanInfoListItem);
			
			
			this.getRequest().setAttribute("loanProfitandlossId",loanProfitandlossId);
			return SUCCESS;
		}
	
	
	/**
	    * 删除
	    */
		public void deleteXfDetail(){
			PrintWriter out = null;
			try {
				HttpServletResponse response = this.getResponse();
				out = response.getWriter();
				response.setContentType("text/html");
				
			lnLoanProfitandlossXfDetailService.deleteXfDetail(lnLoanProfitandlossXfDetail);
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
		public String selectXfDetail(){
			
			try {
				LnLoanProfitandlossXfDetail asset=null;
				List<LnLoanProfitandlossXfDetail> assetList=lnLoanProfitandlossXfDetailService.selectXfDetailByPrimary(lnLoanProfitandlossXfDetail);
				//查询基础数据
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("dictionaryName", "LNXFDE");
				List<LnLoanInfoDictionary> loanInfoList = lnLoanInfoDictionaryService
						.selectLoanInfoDictionaryList(param);
			
				this.getRequest().setAttribute("dictionary", loanInfoList);
				
				
				param.put("dictionaryName", "LNXFXF");
				List<LnLoanInfoDictionary> loanInfoListItem = lnLoanInfoDictionaryService
						.selectLoanInfoDictionaryList(param);
			
				this.getRequest().setAttribute("dictionaryItem",loanInfoListItem);
				if(assetList!=null&&assetList.size()>0){
					 asset=assetList.get(0);
					 this.getRequest().setAttribute("loanBalanceId",asset.getLoanProfitandlossId());
				}
					this.getRequest().setAttribute("asset", asset);
					
					
			} catch (Exception e) {
				
				e.printStackTrace();
			}
				return SUCCESS;
			
		  }
/**
* 保存修改经营贷经营模式
*/
	public void insertXfDetail(){
		PrintWriter out = null;
		try {
			HttpServletResponse response = this.getResponse();
			out = response.getWriter();
			response.setContentType("text/html");
			
			LnLoanProfitandlossXfDetail in;
			if(lnLoanProfitandlossXfDetail.getProfitandlossJyDetailId()==null){
				in =lnLoanProfitandlossXfDetailService.insertXfDetail(lnLoanProfitandlossXfDetail);
			}else{
				in=lnLoanProfitandlossXfDetailService.updateXfDetail(lnLoanProfitandlossXfDetail);
			}      
			
			String json = "{\"success\":true,\"id\":"+in.getProfitandlossJyDetailId()+"}";
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
	public String XfDetailList(){
		
			try {
				Map<String ,Object> map = lnLoanProfitandlossXfDetailService.profitandlossXfDetail(lnLoanProfitandlossXfDetail.getLoanProfitandlossId());
				
				this.getRequest().setAttribute("profitandlossXfDetailMap",map);
				
			} catch (Exception e) {
				
				
				e.printStackTrace();
				logger.error("loanInfoList", e);
				return "error";
			}
			
			
			return SUCCESS;
	
	  }

	  

	
	
}
