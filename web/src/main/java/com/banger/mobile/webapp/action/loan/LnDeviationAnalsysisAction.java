package com.banger.mobile.webapp.action.loan;

import com.banger.mobile.domain.model.loan.InBusinessReaches;
import com.banger.mobile.domain.model.loan.LnDeviationAnalsysis;
import com.banger.mobile.domain.model.loan.LnLoanProfitandloss;
import com.banger.mobile.facade.loan.InBusinessReachesService;
import com.banger.mobile.facade.loan.LnDeviationAnalsysisService;
import com.banger.mobile.webapp.action.BaseAction;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.*;


public class LnDeviationAnalsysisAction extends BaseAction {

	private static Logger         logger = Logger.getLogger(LnDeviationAnalsysisAction.class);
	private LnDeviationAnalsysisService lnDeviationAnalsysisService;

	public LnDeviationAnalsysisService getLnDeviationAnalsysisService() {
		return lnDeviationAnalsysisService;
	}

	public void setLnDeviationAnalsysisService(LnDeviationAnalsysisService lnDeviationAnalsysisService) {
		this.lnDeviationAnalsysisService = lnDeviationAnalsysisService;
	}

	//跳转到添加
	/*public String addUp(){
		request.setAttribute("loanId", request.getParameter("loanId"));
		request.setAttribute("businessCustomer", request.getParameter("businessCustomer"));
		request.setAttribute("businessRate",request.getParameter("businessRate"));
		request.setAttribute("businessYear",request.getParameter("businessYear"));
		request.setAttribute("businessRemark",request.getParameter("businessRemark"));
		request.setAttribute("reachesType",request.getParameter("reachesType"));
		return SUCCESS;
	}
	public String addDown(){
		request.setAttribute("loanId", request.getParameter("loanId"));
		request.setAttribute("businessCustomer", request.getParameter("businessCustomer"));
		request.setAttribute("businessRate",request.getParameter("businessRate"));
		request.setAttribute("businessYear",request.getParameter("businessYear"));
		request.setAttribute("businessRemark",request.getParameter("businessRemark"));
		request.setAttribute("reachesType",request.getParameter("reachesType"));
			return SUCCESS;
	}*/



	//保存
	public void  insertLnDeviationAnalsysis(){
		try {
			this.getResponse().setContentType("text/html;charset=utf-8");
			PrintWriter out =this.getResponse().getWriter();
			LnDeviationAnalsysis lnDeviationAnalsysis =new LnDeviationAnalsysis();

			logger.info("保存偏差分析");

			String remark= request.getParameter("remark");
			Integer loanId= Integer.parseInt(request.getParameter("loanId"));
			Map<String,Object> paramMap=new HashMap<String, Object>();
			if(StringUtils.isNotBlank(request.getParameter("loanMaritalStatus"))&& StringUtils.isNumeric(request.getParameter("loanMaritalStatus"))){
				Integer loanMaritalStatus= Integer.parseInt(request.getParameter("loanMaritalStatus"));
				paramMap.put ("loanMaritalStatus",loanMaritalStatus);
			}
			if(StringUtils.isNotBlank(request.getParameter("loanAge"))&& StringUtils.isNumeric(request.getParameter("loanAge"))){
				Integer loanAge= Integer.parseInt(request.getParameter("loanAge"));paramMap.put ("loanAge",loanAge);
			}
			if(StringUtils.isNotBlank(request.getParameter("loanWorkStatus"))&& StringUtils.isNumeric(request.getParameter("loanWorkStatus"))){
				Integer loanWorkStatus= Integer.parseInt(request.getParameter("loanWorkStatus"));	paramMap.put ("loanWorkStatus",loanWorkStatus);
			}
			if(StringUtils.isNotBlank(request.getParameter("loanBizYear")) && StringUtils.isNumeric(request.getParameter("loanBizYear"))){
				Integer loanBizYear= Integer.parseInt(request.getParameter("loanBizYear"));paramMap.put ("loanBizYear",loanBizYear);
			}
			if(StringUtils.isNotBlank(request.getParameter("loanLocalYear"))&& StringUtils.isNumeric(request.getParameter("loanLocalYear"))){
				Integer loanLocalYear= Integer.parseInt(request.getParameter("loanLocalYear"));	paramMap.put ("loanLocalYear",loanLocalYear);
			}
			if(StringUtils.isNotBlank(request.getParameter("loanFinanceStatus"))&& StringUtils.isNumeric(request.getParameter("loanFinanceStatus"))){
				Integer loanFinanceStatus= Integer.parseInt(request.getParameter("loanFinanceStatus"));paramMap.put ("loanFinanceStatus",loanFinanceStatus);
			}
			if(StringUtils.isNotBlank(request.getParameter("loanCreditStatus"))&& StringUtils.isNumeric(request.getParameter("loanCreditStatus"))){
				Integer loanCreditStatus= Integer.parseInt(request.getParameter("loanCreditStatus"));paramMap.put ("loanCreditStatus",loanCreditStatus);
			}
			if(StringUtils.isNotBlank(request.getParameter("loanChildStatus"))&& StringUtils.isNumeric(request.getParameter("loanChildStatus"))){
				Integer loanChildStatus= Integer.parseInt(request.getParameter("loanChildStatus"));paramMap.put ("loanChildStatus",loanChildStatus);
			}
			if(StringUtils.isNotBlank(request.getParameter("loanSpouseStatus"))&& StringUtils.isNumeric(request.getParameter("loanSpouseStatus"))){
				Integer loanSpouseStatus= Integer.parseInt(request.getParameter("loanSpouseStatus"));	paramMap.put ("loanSpouseStatus",loanSpouseStatus);
			}
			if(StringUtils.isNotBlank(request.getParameter("guMaritalStatus"))&& StringUtils.isNumeric(request.getParameter("guMaritalStatus"))){
				Integer guMaritalStatus= Integer.parseInt(request.getParameter("guMaritalStatus"));	paramMap.put ("guMaritalStatus",guMaritalStatus);
			}
			if(StringUtils.isNotBlank(request.getParameter("guAge"))&& StringUtils.isNumeric(request.getParameter("guAge"))){
				Integer guAge= Integer.parseInt(request.getParameter("guAge"));	paramMap.put ("guAge",guAge);
			}
			if(StringUtils.isNotBlank(request.getParameter("guWorkStatus"))&& StringUtils.isNumeric(request.getParameter("guWorkStatus"))){
				Integer guWorkStatus= Integer.parseInt(request.getParameter("guWorkStatus"));paramMap.put("guWorkStatus", guWorkStatus);
			}
			if(StringUtils.isNotBlank(request.getParameter("guBizYear"))&& StringUtils.isNumeric(request.getParameter("guBizYear"))){
				Integer guBizYear= Integer.parseInt(request.getParameter("guBizYear"));paramMap.put("guBizYear", guBizYear);
			}
			if(StringUtils.isNotBlank(request.getParameter("guFinanceStatus"))&& StringUtils.isNumeric(request.getParameter("guFinanceStatus"))){
				Integer guFinanceStatus= Integer.parseInt(request.getParameter("guFinanceStatus"));paramMap.put("guFinanceStatus", guFinanceStatus);
			}
			if(StringUtils.isNotBlank(request.getParameter("guCreditStatus"))&& StringUtils.isNumeric(request.getParameter("guCreditStatus"))){
				Integer guCreditStatus= Integer.parseInt(request.getParameter("guCreditStatus"));paramMap.put("guCreditStatus", guCreditStatus);
			}
			lnDeviationAnalsysis=lnDeviationAnalsysisService.selectById(loanId);
			paramMap.put ("loanId",loanId);
			paramMap.put ("remark",remark);
			if (lnDeviationAnalsysis!=null){
				paramMap.put ("updateUser",loanId);
				paramMap.put ("updateDate",new Date());
				lnDeviationAnalsysisService.alterLnDeviationAnalsysis(paramMap);
			}
			else{
				paramMap.put ("createUser",loanId);
				paramMap.put ("createDate",new Date());
				lnDeviationAnalsysisService.creatrLnDeviationAnalsysis(paramMap);
			}
			out.print("success");
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	//按loanId
	public void   queryOneLnDeviationAnalsysis(){
		PrintWriter out = null;
		HttpServletResponse response = this.getResponse();

		LnDeviationAnalsysis lnDeviationAnalsysis =new LnDeviationAnalsysis();
		try {
			out = response.getWriter();
			this.getResponse().setContentType("text/html;charset=utf-8");
			Integer loanId= Integer.parseInt(request.getParameter("loanId"));
			lnDeviationAnalsysis = lnDeviationAnalsysisService.selectById(loanId);

			JSONObject json=new JSONObject();
			json.accumulate("success", true);
			json.accumulate("data", lnDeviationAnalsysis);
			//json.accumulate("remark", lnDeviationAnalsysis.getRemark());
			/*json.accumulate("deviationRemark",lnDeviationAnalsysis.getRemark());*/
			out.println(json.toString());



		} catch (Exception e) {
			e.printStackTrace();

		}		 finally {

			out.flush();
			out.close();

		}



	}


	/*//删除
	public void dropInBusinessReaches(){
		try{
			this.getResponse().setContentType("text/html;charset=utf-8");
			PrintWriter out =this.getResponse().getWriter();
			String  id = request.getParameter("id");
			InBusinessReaches inBusinessReaches =new InBusinessReaches();
			inBusinessReachesService.deleteInBusinessReaches(Integer.parseInt(id));
			out.print("success");
		}
		catch (Exception e){
			e.printStackTrace();
		}

	}



	//按类型查找
	public String   queryInBusinessReachesDown(){
		List<InBusinessReaches> listInBusinessReaches1 =new ArrayList<InBusinessReaches>();
		try {
			this.getResponse().setContentType("text/html;charset=utf-8");
			Integer loanId = Integer.parseInt(request.getParameter("loanId"));
			listInBusinessReaches1 = inBusinessReachesService.selectReachesType(0,loanId);
			request.setAttribute("listInBusinessReaches1", listInBusinessReaches1);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String queryInBusinessReachesUp() {
		List<InBusinessReaches> listInBusinessReaches =new ArrayList<InBusinessReaches>();
		try {
			this.getResponse().setContentType("text/html;charset=utf-8");
			//Integer reachesType = Integer.parseInt(request.getParameter("reachesType"));
			Integer loanId = Integer.parseInt(request.getParameter("loanId"));
			listInBusinessReaches = inBusinessReachesService.selectReachesType(1,loanId);
			request.setAttribute("listInBusinessReaches", listInBusinessReaches);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		//return listInBusinessReaches;
	}*/

}

