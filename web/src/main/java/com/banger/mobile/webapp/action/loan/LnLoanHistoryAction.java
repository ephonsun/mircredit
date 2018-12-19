package com.banger.mobile.webapp.action.loan;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.banger.mobile.domain.model.loan.LnLoanHistory;
import com.banger.mobile.domain.model.loan.LnLoanHistory;
import com.banger.mobile.domain.model.loan.LnLoanInfoDictionary;
import com.banger.mobile.domain.model.loan.LnOpHistory;
import com.banger.mobile.facade.loan.LnBusinessModelService;
import com.banger.mobile.facade.loan.LnLoanHistoryService;
import com.banger.mobile.facade.loan.LnLoanInfoDictionaryService;
import com.banger.mobile.facade.loan.LnOpHistoryService;
import com.banger.mobile.webapp.action.BaseAction;

public class LnLoanHistoryAction extends BaseAction {

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger         logger = Logger.getLogger(LnLoanHistoryAction.class);
	  private LnLoanHistoryService lnLoanHistoryService;
	  private LnLoanHistory lnLoanHistory;
	  private LnOpHistoryService lnOpHistoryService;
	  
	  
	public LnOpHistoryService getLnOpHistoryService() {
		return lnOpHistoryService;
	}
	public void setLnOpHistoryService(LnOpHistoryService lnOpHistoryService) {
		this.lnOpHistoryService = lnOpHistoryService;
	}
	public void setLnLoanHistoryService(LnLoanHistoryService lnLoanHistoryService) {
		this.lnLoanHistoryService = lnLoanHistoryService;
	}
	public LnLoanHistory getLnLoanHistory() {
		return lnLoanHistory;
	}
	public void setLnLoanHistory(LnLoanHistory lnLoanHistory) {
		this.lnLoanHistory = lnLoanHistory;
	}
	/**
	    * 单个查询
	    */
		public void selectLnLoanHistoryModel(){
			PrintWriter out = null;
			try {
				HttpServletResponse response = this.getResponse();
				out = response.getWriter();
				response.setContentType("text/html");
				LnLoanHistory in=lnLoanHistoryService.selectByPrimary(lnLoanHistory);
				
				JSONObject json=new JSONObject();  
		        json.accumulate("success", true);  
		        json.accumulate("LnLoanHistory", in);  
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
		
		
		private String LNHIDA;
		private String LNHIFA;
		
		
		
  
        public String getLNHIDA() {
			return LNHIDA;
		}
		public void setLNHIDA(String lNHIDA) {
			LNHIDA = lNHIDA;
		}
		public String getLNHIFA() {
			return LNHIFA;
		}
		public void setLNHIFA(String lNHIFA) {
			LNHIFA = lNHIFA;
		}
/**
    * 保存修改经营贷经营模式
    */
	public void insertLnLoanHistory(){
		PrintWriter out = null;
		try {
			HttpServletResponse response = this.getResponse();
			
			response.setContentType("text/html;charset=utf-8");

			out = response.getWriter();
			
			
			LnLoanHistory lnLoanHistory=new LnLoanHistory();
			String historyId = request.getParameter("historyId");
			if(!StringUtils.isBlank(historyId)){
				lnLoanHistory.setHistoryId(Integer.parseInt(historyId));
			}
			
			lnLoanHistory.setFamilyInfo(request.getParameter("familyInfo"));
			lnLoanHistory.setFlowPrinciple(request.getParameter("flowPrinciple"));
			lnLoanHistory.setGuaranteeInfo(request.getParameter("guaranteeInfo"));
			lnLoanHistory.setLoanId(Integer.parseInt(request.getParameter("loanId")));
			lnLoanHistory.setOtherConditions(request.getParameter("otherConditions"));
			lnLoanHistory.setSpecialExplanation(request.getParameter("specialExplanation"));
			lnLoanHistory.setWorkHistory(request.getParameter("workHistory"));
			lnLoanHistory.setExplanationAmount(request.getParameter("explanationAmount"));
			LnLoanHistory returnObj=new LnLoanHistory();
			if(lnLoanHistory.getHistoryId()==null){
				returnObj =lnLoanHistoryService.insertLoanHistory(lnLoanHistory);
			}else{
				returnObj=lnLoanHistoryService.updateLoanHistory(lnLoanHistory);
			}
			
			
			LnOpHistory lnOpHistory = new LnOpHistory();
            lnOpHistory.setLoanId(Integer.parseInt(request.getParameter("loanId")));
            lnOpHistory.setOpHistoryDate(Calendar.getInstance().getTime());
            lnOpHistory.setUserId(this.getLoginInfo().getUserId());
            lnOpHistory.setContent("保存个人发展历史信息");
            lnOpHistory.setRemark("");
            lnOpHistory.setBeforeStatusId(3);
            lnOpHistory.setAfterStatusId(3);
            lnOpHistoryService.insertLnOpHistory(lnOpHistory);
            
			String json = "{\"success\":true,\"id\":"+returnObj.getHistoryId()+"}";
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
	
	
}
