package com.banger.mobile.webapp.action.loan;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.banger.mobile.domain.model.loan.LnLoanInfoDictionary;
import com.banger.mobile.domain.model.loan.LnLoanProfitandlossJyDetail;
import com.banger.mobile.facade.loan.LnLoanInfoDictionaryService;
import com.banger.mobile.facade.loan.LnLoanProfitandlossJyDetailService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * 经营贷详情
 * @author Administrator
 *
 */
public class LnLoanProfitandlossJyDetailAction extends BaseAction {

    private static Logger         logger = Logger.getLogger(LnLoanProfitandlossJyDetailAction.class);
    private LnLoanProfitandlossJyDetailService lnLoanProfitandlossJyDetailService; 
   
	private LnLoanProfitandlossJyDetail lnLoanProfitandlossJyDetail;
    private LnLoanInfoDictionaryService lnLoanInfoDictionaryService;
	  
	
		
	

	public LnLoanProfitandlossJyDetail getLnLoanProfitandlossJyDetail() {
		return lnLoanProfitandlossJyDetail;
	}


	public void setLnLoanProfitandlossJyDetail(
			LnLoanProfitandlossJyDetail lnLoanProfitandlossJyDetail) {
		this.lnLoanProfitandlossJyDetail = lnLoanProfitandlossJyDetail;
	}


	public void setLnLoanProfitandlossJyDetailService(
			LnLoanProfitandlossJyDetailService lnLoanProfitandlossJyDetailService) {
		this.lnLoanProfitandlossJyDetailService = lnLoanProfitandlossJyDetailService;
	}


	public void setLnLoanInfoDictionaryService(
			LnLoanInfoDictionaryService lnLoanInfoDictionaryService) {
		this.lnLoanInfoDictionaryService = lnLoanInfoDictionaryService;
	}


	/**
	    * 跳转编辑页面
	    */
		public String selectJyDetailUI(){
			String loanProfitandlossId = this.getRequest().getParameter("loanProfitandlossId");
			//查询基础数据
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("dictionaryName", "LNJYDE");
			List<LnLoanInfoDictionary> loanInfoList = lnLoanInfoDictionaryService
					.selectLoanInfoDictionaryList(param);
		
			this.getRequest().setAttribute("dictionary", loanInfoList);
			param.put("dictionaryName", "LNJYXF");
			List<LnLoanInfoDictionary> loanInfoListItem = lnLoanInfoDictionaryService
					.selectLoanInfoDictionaryList(param);
		
			this.getRequest().setAttribute("dictionaryItem",loanInfoListItem);
		
			this.getRequest().setAttribute("loanProfitandlossId",loanProfitandlossId);
			return SUCCESS;
		}
	
	
	/**
	    * 删除
	    */
		public void deleteJyDetail(){
			PrintWriter out = null;
			try {
				HttpServletResponse response = this.getResponse();
				out = response.getWriter();
				response.setContentType("text/html");
				
				lnLoanProfitandlossJyDetailService.deleteXfDetail(lnLoanProfitandlossJyDetail);
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
		public String selectJyDetai(){
			
			try {
				LnLoanProfitandlossJyDetail asset=null;
				List<LnLoanProfitandlossJyDetail> assetList=lnLoanProfitandlossJyDetailService.selectJyDetaiByPrimary(lnLoanProfitandlossJyDetail);
				//查询基础数据
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("dictionaryName", "LNJYDE");
				List<LnLoanInfoDictionary> loanInfoList = lnLoanInfoDictionaryService
						.selectLoanInfoDictionaryList(param);
			
				this.getRequest().setAttribute("dictionary", loanInfoList);
				param.put("dictionaryName", "LNJYXF");
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
* 保存修改
*/
	public void insertJyDetail(){
		PrintWriter out = null;
		try {
			HttpServletResponse response = this.getResponse();
			out = response.getWriter();
			response.setContentType("text/html");
			
			LnLoanProfitandlossJyDetail in;
			if(lnLoanProfitandlossJyDetail.getProfitandlossJyDetailId()==null){
				in =lnLoanProfitandlossJyDetailService.insertJyDetai(lnLoanProfitandlossJyDetail);
			}else{
				in=lnLoanProfitandlossJyDetailService.updateXfJyDetai(lnLoanProfitandlossJyDetail);
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
	public String jyDetaiList(){
		
		try {
			Map<String,Object> map = lnLoanProfitandlossJyDetailService.profitandlossJyDetail(lnLoanProfitandlossJyDetail.getLoanProfitandlossId());
			this.getRequest().setAttribute("profitandlossJyDetailMap",map);
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		return SUCCESS;

  }

	  }

	  

	
	

	

