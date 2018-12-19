package com.banger.mobile.webapp.action.loan;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.banger.mobile.domain.model.loan.LnLoanBalanceOther;
import com.banger.mobile.facade.loan.LnLoanBalanceOtherService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * 房产表
 * @author Administrator
 *
 */
public class LnLoanBalanceOtherAction extends BaseAction {

	  private static Logger         logger = Logger.getLogger(LnLoanBalanceOtherAction.class);
	  private LnLoanBalanceOtherService lnLoanBalanceOtherService;   
	  private LnLoanBalanceOther lnLBalanceOther;
	  
	 
	  

	 

	public LnLoanBalanceOther getLnLBalanceOther() {
		return lnLBalanceOther;
	}


	public void setLnLBalanceOther(LnLoanBalanceOther lnLBalanceOther) {
		this.lnLBalanceOther = lnLBalanceOther;
	}


	public void setLnLoanBalanceOtherService(
			LnLoanBalanceOtherService lnLoanBalanceOtherService) {
		this.lnLoanBalanceOtherService = lnLoanBalanceOtherService;
	}


	/**
	    * 跳转编辑页面
	    */
		public String selectOtherUI(){
			String loanBalanceId = this.getRequest().getParameter("loanBalanceId");
			
			this.getRequest().setAttribute("loanBalanceId",loanBalanceId);
			return SUCCESS;
		}
	
	
	/**
	    * 删除
	    */
		public void deleteOther(){
			PrintWriter out = null;
			try {
				HttpServletResponse response = this.getResponse();
				out = response.getWriter();
				response.setContentType("text/html");
				
			lnLoanBalanceOtherService.deleteOher(lnLBalanceOther);
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
		public String selectOther(){
			
			try {
				LnLoanBalanceOther asset=null;
				List<LnLoanBalanceOther> assetList=lnLoanBalanceOtherService.selectOtherByPrimary(lnLBalanceOther);
					
				if(assetList!=null&&assetList.size()>0){
					 asset=assetList.get(0);
					 this.getRequest().setAttribute("loanBalanceId",asset.getLoanBalanceId());
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
	public void insertOther(){
		PrintWriter out = null;
		try {
			HttpServletResponse response = this.getResponse();
			out = response.getWriter();
			response.setContentType("text/html");
			
			LnLoanBalanceOther in;
			if(lnLBalanceOther.getLoanBalanceOtherId()==null){
				in =lnLoanBalanceOtherService.insertOther(lnLBalanceOther);
			}else{
				in=lnLoanBalanceOtherService.updateOther(lnLBalanceOther);
			}
			
			String json = "{\"success\":true,\"id\":"+in.getLoanBalanceOtherId()+"}";
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
	public String otherList(){
		
			//根据资产负债表查_可变成本及其他交叉检验
			List<LnLoanBalanceOther> assetList = lnLoanBalanceOtherService.selectOtherByPrimary(lnLBalanceOther);
			
			this.getRequest().setAttribute("lnLoanBalanceOtherList", assetList);
			
			
			return SUCCESS;
	
	  }

	  

	
}
