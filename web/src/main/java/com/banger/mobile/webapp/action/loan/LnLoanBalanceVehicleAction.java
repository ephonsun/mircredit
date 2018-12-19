package com.banger.mobile.webapp.action.loan;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.banger.mobile.domain.model.loan.LnLoanBalanceVehicle;
import com.banger.mobile.facade.loan.LnLoanBalanceVehicleService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * 机动车
 * @author Administrator
 *
 */
public class LnLoanBalanceVehicleAction extends BaseAction {

	  private static Logger         logger = Logger.getLogger(LnLoanBalanceVehicleAction.class);
	  private LnLoanBalanceVehicleService lnLoanBalanceVehicleService;   
	  private LnLoanBalanceVehicle lnLoanBalanceVehicle;
	  
	 
	  

	public LnLoanBalanceVehicle getLnLoanBalanceVehicle() {
		return lnLoanBalanceVehicle;
	}


	public void setLnLoanBalanceVehicle(LnLoanBalanceVehicle lnLoanBalanceVehicle) {
		this.lnLoanBalanceVehicle = lnLoanBalanceVehicle;
	}


	public void setLnLoanBalanceVehicleService(
			LnLoanBalanceVehicleService lnLoanBalanceVehicleService) {
		this.lnLoanBalanceVehicleService = lnLoanBalanceVehicleService;
	}


	/**
	    * 跳转编辑页面
	    */
		public String selectVehicleUI(){
			String loanBalanceId = this.getRequest().getParameter("loanBalanceId");
			
			this.getRequest().setAttribute("loanBalanceId",loanBalanceId);
			return SUCCESS;
		}
	
	
	/**
	    * 删除
	    */
		public void deleteVehicleing(){
			PrintWriter out = null;
			try {
				HttpServletResponse response = this.getResponse();
				out = response.getWriter();
				response.setContentType("text/html");
				
			lnLoanBalanceVehicleService.deleteVehicle(lnLoanBalanceVehicle);
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
		public String selectVehicle(){
			
			try {
				LnLoanBalanceVehicle asset=null;
				List<LnLoanBalanceVehicle> assetList=lnLoanBalanceVehicleService.selectVehicleByPrimary(lnLoanBalanceVehicle);
					
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
	public void insertVehicle(){
		PrintWriter out = null;
		try {
			HttpServletResponse response = this.getResponse();
			out = response.getWriter();
			response.setContentType("text/html");
			
			LnLoanBalanceVehicle in;
			if(lnLoanBalanceVehicle.getLoanBalanceVehicleId()==null){
				in =lnLoanBalanceVehicleService.insertVehicle(lnLoanBalanceVehicle);
			}else{
				in=lnLoanBalanceVehicleService.updateVehicle(lnLoanBalanceVehicle);
			}      
			
			String json = "{\"success\":true,\"id\":"+in.getLoanBalanceVehicleId()+"}";
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
	public String vehicleList(){
		
			//根据资产负债表机动车
			List<LnLoanBalanceVehicle> assetList = lnLoanBalanceVehicleService.selectVehicleByPrimary(lnLoanBalanceVehicle);
			
			this.getRequest().setAttribute("lnLoanBalanceVehicleList", assetList);
			
			
			return SUCCESS;
	
	  }

	  

	
}
