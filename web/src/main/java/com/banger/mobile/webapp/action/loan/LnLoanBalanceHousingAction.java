package com.banger.mobile.webapp.action.loan;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.banger.mobile.domain.model.loan.LnLoanBalanceHousing;
import com.banger.mobile.facade.loan.LnLoanBalanceHousingService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * 房产表
 * @author Administrator
 *
 */
public class LnLoanBalanceHousingAction extends BaseAction {

	  private static Logger         logger = Logger.getLogger(LnLoanBalanceHousingAction.class);
	  private LnLoanBalanceHousingService lnLoanBalanceHousingService;   
	  private LnLoanBalanceHousing lnLoanBalanceHousing;
	  
	 
	  public LnLoanBalanceHousing getLnLoanBalanceHousing() {
		return lnLoanBalanceHousing;
	}


	public void setLnLoanBalanceHousing(LnLoanBalanceHousing lnLoanBalanceHousing) {
		this.lnLoanBalanceHousing = lnLoanBalanceHousing;
	}


	public void setLnLoanBalanceHousingService(
			LnLoanBalanceHousingService lnLoanBalanceHousingService) {
		this.lnLoanBalanceHousingService = lnLoanBalanceHousingService;
	}


	/**
	    * 跳转编辑页面
	    */
		public String selectHousingUI(){
			String loanBalanceId = this.getRequest().getParameter("loanBalanceId");
			
			this.getRequest().setAttribute("loanBalanceId",loanBalanceId);
			return SUCCESS;
		}
	
	
	/**
	    * 删除
	    */
		public void deleteHousing(){
			PrintWriter out = null;
			try {
				HttpServletResponse response = this.getResponse();
				out = response.getWriter();
				response.setContentType("text/html");
				
			lnLoanBalanceHousingService.deleteHousing(lnLoanBalanceHousing);
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
		public String selectHousing(){
			
			try {
				LnLoanBalanceHousing asset=null;
				List<LnLoanBalanceHousing> assetList=lnLoanBalanceHousingService.selectHousingByPrimary(lnLoanBalanceHousing);
					
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
	public void insertHousing(){
		PrintWriter out = null;
		try {
			HttpServletResponse response = this.getResponse();
			out = response.getWriter();
			response.setContentType("text/html");
			LnLoanBalanceHousing in;
			if(lnLoanBalanceHousing.getLoanBalanceHousingId()==null){
				in =lnLoanBalanceHousingService.insertHousing(lnLoanBalanceHousing);
			}else{
				in=lnLoanBalanceHousingService.updateHousing(lnLoanBalanceHousing);
			}
			
			String json = "{\"success\":true,\"id\":"+in.getLoanBalanceHousingId()+"}";
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
	public String housingtList(){
		
			try {
				//根据资产负债表查旬资产
				List<LnLoanBalanceHousing> assetList = lnLoanBalanceHousingService.selectHousingByPrimary(lnLoanBalanceHousing);
				
				this.getRequest().setAttribute("lnLoanBalanceHousingList", assetList);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return SUCCESS;
	
	  }

	  

	
}
