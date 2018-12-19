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

import com.banger.mobile.domain.model.loan.LnBusinessModel;
import com.banger.mobile.domain.model.loan.LnLoanInfoDictionary;
import com.banger.mobile.domain.model.loan.LnOpHistory;
import com.banger.mobile.domain.model.loan.LnRecordInterview;
import com.banger.mobile.facade.loan.LnBusinessModelService;
import com.banger.mobile.facade.loan.LnLoanInfoDictionaryService;
import com.banger.mobile.facade.loan.LnOpHistoryService;
import com.banger.mobile.facade.loan.LnRecordInterviewService;
import com.banger.mobile.webapp.action.BaseAction;

public class LnRecordInterviewAction extends BaseAction {

	  private static Logger         logger = Logger.getLogger(LnRecordInterviewAction.class);
	  private LnRecordInterviewService lnRecordInterviewService;
	  private LnRecordInterview lnRecordInterview;
	  private LnOpHistoryService lnOpHistoryService;
	  
	  
	  
	public LnOpHistoryService getLnOpHistoryService() {
		return lnOpHistoryService;
	}
	public void setLnOpHistoryService(LnOpHistoryService lnOpHistoryService) {
		this.lnOpHistoryService = lnOpHistoryService;
	}
	public void setLnRecordInterviewService(
			LnRecordInterviewService lnRecordInterviewService) {
		this.lnRecordInterviewService = lnRecordInterviewService;
	}
	public LnRecordInterview getLnRecordInterview() {
		return lnRecordInterview;
	}
	public void setLnRecordInterview(LnRecordInterview lnRecordInterview) {
		this.lnRecordInterview = lnRecordInterview;
	}
	/**
	    * 单个查询
	    */
		public void selectLnRecordInterview(){
			PrintWriter out = null;
			try {
				HttpServletResponse response = this.getResponse();
				out = response.getWriter();
				response.setContentType("text/html");
				LnRecordInterview in=lnRecordInterviewService.selectByPrimary(lnRecordInterview);
				
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
    * 保存修改经营贷经营模式
    */
	public void insertLnRecordInterview(){
		PrintWriter out = null;
		try {
			HttpServletResponse response = this.getResponse();
			out = response.getWriter();
			response.setContentType("text/html;charset=utf-8");
			LnRecordInterview lnRecordInterview=new LnRecordInterview();
			String interviewId = request.getParameter("interviewId");
			if(!StringUtils.isBlank(interviewId)){				
				lnRecordInterview.setInterviewId(Integer.parseInt(interviewId));
			}
			
			lnRecordInterview.setLoanId(Integer.parseInt(request.getParameter("loanId")));
			lnRecordInterview.setInterviewOjectName(request.getParameter("interviewOjectName"));
			lnRecordInterview.setInterviewObjectCompany(request.getParameter("interviewObjectCompany"));
			lnRecordInterview.setInterviewObjectPosition(request.getParameter("interviewObjectPosition"));
			lnRecordInterview.setInterviewObjectPhone(request.getParameter("interviewObjectPhone"));
			lnRecordInterview.setInterviewPeopleName(request.getParameter("interviewPeopleName"));
			lnRecordInterview.setInterviewDate(request.getParameter("interviewDate"));
			lnRecordInterview.setCheckInformation(request.getParameter("checkInformation"));
			lnRecordInterview.setCheckCompany(request.getParameter("checkCompany"));
			lnRecordInterview.setWorkTime(request.getParameter("workTime"));
			lnRecordInterview.setIncome(request.getParameter("income"));
			lnRecordInterview.setMajorBusiness(request.getParameter("majorBusiness"));
			lnRecordInterview.setCompanyAge(request.getParameter("companyAge"));
			lnRecordInterview.setCompanyNum(request.getParameter("companyNum"));
			lnRecordInterview.setApplicantEvaluation(request.getParameter("applicantEvaluation"));
			lnRecordInterview.setApplicantFamliyInfo(request.getParameter("applicantFamliyInfo"));
			
			LnRecordInterview returnObj;
			if(lnRecordInterview.getInterviewId()==null){
				returnObj =lnRecordInterviewService.insertLnRecordInterview(lnRecordInterview);
			}else{
				returnObj=lnRecordInterviewService.updateLnRecordInterview(lnRecordInterview);
			}
			
			LnOpHistory lnOpHistory = new LnOpHistory();
            lnOpHistory.setLoanId(Integer.parseInt(request.getParameter("loanId")));
            lnOpHistory.setOpHistoryDate(Calendar.getInstance().getTime());
            lnOpHistory.setUserId(this.getLoginInfo().getUserId());
            lnOpHistory.setContent("保存访谈记录信息");
            lnOpHistory.setRemark("");
            lnOpHistory.setBeforeStatusId(3);
            lnOpHistory.setAfterStatusId(3);
            lnOpHistoryService.insertLnOpHistory(lnOpHistory);
            
			String json = "{\"success\":true,\"id\":"+returnObj.getInterviewId()+"}";
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
