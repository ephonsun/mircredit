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
import com.banger.mobile.facade.loan.LnBusinessModelService;
import com.banger.mobile.facade.loan.LnLoanInfoDictionaryService;
import com.banger.mobile.facade.loan.LnOpHistoryService;
import com.banger.mobile.webapp.action.BaseAction;
import com.ibm.db2.jcc.am.ln;

public class LnBusinessModeAction extends BaseAction {

	  private static Logger         logger = Logger.getLogger(LnBusinessModeAction.class);
	  private LnBusinessModelService businessModelService;   
	  private LnLoanInfoDictionaryService lnLoanInfoDictionaryService;
	  private LnBusinessModel lnBusinessModel;
	  private LnOpHistoryService lnOpHistoryService;
     

	public LnOpHistoryService getLnOpHistoryService() {
		return lnOpHistoryService;
	}



	public void setLnOpHistoryService(LnOpHistoryService lnOpHistoryService) {
		this.lnOpHistoryService = lnOpHistoryService;
	}



	public LnBusinessModelService getBusinessModelService() {
		return businessModelService;
	}



	public void setBusinessModelService(LnBusinessModelService businessModelService) {
		this.businessModelService = businessModelService;
	}



	public LnBusinessModel getLnBusinessModel() {
		return lnBusinessModel;
	}



	public LnLoanInfoDictionaryService getLnLoanInfoDictionaryService() {
		return lnLoanInfoDictionaryService;
	}



	public void setLnLoanInfoDictionaryService(
			LnLoanInfoDictionaryService lnLoanInfoDictionaryService) {
		this.lnLoanInfoDictionaryService = lnLoanInfoDictionaryService;
	}



	public void setLnBusinessModel(LnBusinessModel lnBusinessModel) {
		this.lnBusinessModel = lnBusinessModel;
	}

    private String sorftInfoArr;
    
   
	public String getSorftInfoArr() {
		return sorftInfoArr;
	}

	public void setSorftInfoArr(String sorftInfoArr) {
		this.sorftInfoArr = sorftInfoArr;
	}


	
	   /**
	    * 单个查询
	    */
		public void selectLnBusinessModel(){
			PrintWriter out = null;
			try {
				HttpServletResponse response = this.getResponse();
				out = response.getWriter();
				response.setContentType("text/html");
				LnBusinessModel in=businessModelService.selectByPrimary(lnBusinessModel);
				
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
	public void insertLnBusinessModel(){
		PrintWriter out = null;
		try {
			HttpServletResponse response = this.getResponse();
			out = response.getWriter();
			response.setContentType("text/html;charset=utf-8");
			LnBusinessModel lnBusinessModel = new LnBusinessModel();
			
			//lnBusinessModel=businessModelService.selectByPrimary(Integer.parseInt(request.getParameter("loanId")));
			
			String businessId = request.getParameter("businessId");
			if(!StringUtils.isBlank(businessId)){
				lnBusinessModel.setBusinessId(Integer.parseInt(businessId));
			}
			
			lnBusinessModel.setLoanId(Integer.parseInt(request.getParameter("loanId")));
			lnBusinessModel.setUpperReaches(request.getParameter("upperReaches"));
			lnBusinessModel.setLowerReaches(request.getParameter("lowerReaches"));
			lnBusinessModel.setWorkFlow(request.getParameter("workFlow"));
			lnBusinessModel.setChange(request.getParameter("change"));
			lnBusinessModel.setSorftInfo(request.getParameter("sorftInfo"));
			lnBusinessModel.setOfficeLeaseContract(request.getParameter("officeLeaseContract"));
			lnBusinessModel.setOther(request.getParameter("other"));
			lnBusinessModel.setBusinessHistory(request.getParameter("businessHistory"));
			lnBusinessModel.setBusinessOrg(request.getParameter("businessOrg"));
			lnBusinessModel.setBusinessFinance(request.getParameter("businessFinance"));

			LnBusinessModel returnObj = new LnBusinessModel();
			if(lnBusinessModel.getBusinessId()==null){
				returnObj =businessModelService.insertLnBusinessModel(lnBusinessModel);
			}else{
				returnObj=businessModelService.updateLnBusinessModel(lnBusinessModel);
			}
			
			
			LnOpHistory lnOpHistory = new LnOpHistory();
            lnOpHistory.setLoanId(Integer.parseInt(request.getParameter("loanId")));
            lnOpHistory.setOpHistoryDate(Calendar.getInstance().getTime());
            lnOpHistory.setUserId(this.getLoginInfo().getUserId());
            lnOpHistory.setContent("保存经营模式信息");
            lnOpHistory.setRemark("");
            lnOpHistory.setBeforeStatusId(3);
            lnOpHistory.setAfterStatusId(3);
            lnOpHistoryService.insertLnOpHistory(lnOpHistory);
            
			String json = "{\"success\":true,\"id\":"+returnObj.getBusinessId()+"}";
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
	 * 加载基础数据
	 * @throws Exception
	 */
	public void sorftInfoAjax() {
		PrintWriter out = null;
		try {
			String dictionaryName = this.getRequest().getParameter("dictionaryName");
			
			HttpServletResponse response = this.getResponse();
			
			response.setContentType("text/html");
             
			out = response.getWriter();

			Map<String, Object> param = new HashMap<String, Object>();
			param.put("dictionaryName", dictionaryName);
			List<LnLoanInfoDictionary> loanInfoList = lnLoanInfoDictionaryService
					.selectLoanInfoDictionaryList(param);
			// 将要被返回到客户端的对象
			JSONArray json = JSONArray.fromObject(loanInfoList);

			out.println(json.toString());
			

		} catch (Exception e) {
			
			e.printStackTrace();
			logger.error("loanInfoList", e);
		} finally {

			out.flush();
			out.close();

		}
	}
	
}
