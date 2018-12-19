/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :QianJie
 * Create Date:Feb 17, 2013
 */
package com.banger.mobile.webapp.action.loan;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.banger.mobile.domain.model.loan.LnBusinessModel;
import com.banger.mobile.domain.model.loan.LnLoanBalanceVehicle;
import com.banger.mobile.domain.model.loan.LnLoanInfoDictionary;
import com.banger.mobile.domain.model.loan.LnLoanProfitandloss;
import com.banger.mobile.domain.model.loan.LnLoanProfitandlossXfDetail;
import com.banger.mobile.domain.model.loan.LnProfitLossProd;
import com.banger.mobile.facade.loan.LnLoanProfitandlossService;
import com.banger.mobile.facade.loan.LnProfitLossProdService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * 消费贷和经营贷损益表
 * @author Administrator
 *
 */
public class LnLoanProfitandlossAction extends BaseAction {

    private static Logger logger = Logger.getLogger(LnLoanProfitandlossAction.class);
    private  LnLoanProfitandlossService lnLoanProfitandlossService;
    private  LnLoanProfitandloss lnLoanProfitandloss;
    private  LnProfitLossProdService lnProfitLossProdService;
	public LnLoanProfitandloss getLnLoanProfitandloss() {
		return lnLoanProfitandloss;
	}
	public void setLnLoanProfitandloss(LnLoanProfitandloss lnLoanProfitandloss) {
		this.lnLoanProfitandloss = lnLoanProfitandloss;
	}
	public void setLnLoanProfitandlossService(
			LnLoanProfitandlossService lnLoanProfitandlossService) {
		this.lnLoanProfitandlossService = lnLoanProfitandlossService;
	}
    
	/**
	    * 跳转编辑页面
	    */
		public String selectProfitandlossUI(){
			String loanId = this.getRequest().getParameter("loanId");
			LnLoanProfitandloss asset=null;
			List<LnLoanProfitandloss> assetList=lnLoanProfitandlossService.selectProfitandlossByPrimary(lnLoanProfitandloss);
			if(assetList!=null&&assetList.size()>0){
				 asset=assetList.get(0);
			}
			this.getRequest().setAttribute("asset", asset);
			this.getRequest().setAttribute("loanId",loanId);
			return SUCCESS;
		}
	
	
		 /**
		    * 单个查询
		    */
			public void selecProfitandloss(){
				PrintWriter out = null;
				try {
					HttpServletResponse response = this.getResponse();
					out = response.getWriter();
					response.setContentType("text/html;charset=utf-8");
					List<LnLoanProfitandloss> assetList=lnLoanProfitandlossService.selectProfitandlossByPrimary(lnLoanProfitandloss);
					LnLoanProfitandloss in = assetList.get(0);
					 JSONObject json=new JSONObject();  
				        json.accumulate("success", true);  
				        json.accumulate("lnBusinessModel", in);  
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
			    * ******
			    */
			public void selecProfitandlossNew(){
				PrintWriter out = null;
				HttpServletResponse response = this.getResponse();
				response.setContentType("text/html;charset=utf-8");
				String loanId = this.request.getParameter("lnLoanProfitandloss.loanId");
				System.out.println("loanId: "+loanId);
				List<LnProfitLossProd> lnProfitLossProdList = lnProfitLossProdService.selectProfitLossProdByPrimary(Integer.parseInt(loanId));
				System.out.println("lnProfitLossProdList: "+lnProfitLossProdList);
				try {
					out = response.getWriter();
					
					out.print("");
					
				} catch (Exception e) {
					String json = "{\"success\":false}";
					out.println(json.toString());
					e.printStackTrace();
					logger.error("loanInfoList", e);
				}finally{
				
				out.flush();
				out.close();
				}
			}
			
			
			
	/**
	* 保存修改基础信息
	*/
		public void updateProfitandlossBaseInfo(){
			PrintWriter out = null;
			try {
				HttpServletResponse response = this.getResponse();
				out = response.getWriter();
				response.setContentType("text/html;charset=utf-8");
				
			if(lnLoanProfitandloss.getLoanProfitandlossId()!=null){
				LnLoanProfitandloss in=lnLoanProfitandlossService.updateProfitandlossBaseInfo(lnLoanProfitandloss);
				
					String json = "{\"success\":true}";
					out.println(json.toString());	
			}      
				
				
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
		* 修改损益表
		*/
	public void updateProfitandloss(){
		PrintWriter out = null;
		try {
			
			HttpServletResponse response = this.getResponse();
			out = response.getWriter();
			response.setContentType("text/html;charset=utf-8");
		LnLoanProfitandloss loanProfitandloss = new LnLoanProfitandloss();
		String loanProfitandlossId=request.getParameter("loanProfitandlossId");
		if(!StringUtils.isBlank(loanProfitandlossId)){
			loanProfitandloss.setLoanProfitandlossId(Integer.parseInt(loanProfitandlossId));
			loanProfitandloss.setTurnover(request.getParameter("turnover"));
			loanProfitandloss.setCrosscheck(request.getParameter("crosscheck"));
			LnLoanProfitandloss profitandloss=lnLoanProfitandlossService.updateProfitandloss(loanProfitandloss);
			String json = "{\"success\":true}";
			out.println(json.toString());
		}
		}catch (Exception e) {
			String json = "{\"success\":false}";
			out.println(json.toString());
			e.printStackTrace();
			logger.error("loanInfoList", e);
		} finally {

			out.flush();
			out.close();

		}
	}
		public LnProfitLossProdService getLnProfitLossProdService() {
			return lnProfitLossProdService;
		}
		public void setLnProfitLossProdService(LnProfitLossProdService lnProfitLossProdService) {
			this.lnProfitLossProdService = lnProfitLossProdService;
		}
		
}
	