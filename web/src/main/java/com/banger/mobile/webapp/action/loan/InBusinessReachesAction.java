package com.banger.mobile.webapp.action.loan;

import com.banger.mobile.domain.model.loan.InBusinessReaches;

import com.banger.mobile.facade.loan.InBusinessReachesService;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.apache.log4j.Logger;
import com.banger.mobile.webapp.action.BaseAction;

import java.io.PrintWriter;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.*;


public class InBusinessReachesAction extends BaseAction {

	  private static Logger         logger = Logger.getLogger(InBusinessReachesAction.class);
	private InBusinessReachesService inBusinessReachesService;

	public InBusinessReachesService getInBusinessReachesService() {
		return inBusinessReachesService;
	}

	public void setInBusinessReachesService(InBusinessReachesService inBusinessReachesService) {
		this.inBusinessReachesService = inBusinessReachesService;
	}


	//跳转到添加
	public String addUp(){
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
	}



	//保存
	public void  insertInBusinessReaches(){
		try {
			this.getResponse().setContentType("text/html;charset=utf-8");
			PrintWriter out =this.getResponse().getWriter();
			logger.info("保存上下游");
			String  reachesType = request.getParameter("reachesType");
			//String  id = request.getParameter("id");
			String  loanId = request.getParameter("loanId");
			String  businessCustomer = request.getParameter("businessCustomer");
			String  businessRate = request.getParameter("businessRate");
			String  businessYear = request.getParameter("businessYear");
			String  businessRemark = request.getParameter("businessRemark");
			InBusinessReaches inBusinessReaches =new InBusinessReaches();
			inBusinessReaches.setLoanId(Integer.parseInt(loanId));

			//inBusinessReaches.setId(Integer.parseInt(id));
			inBusinessReaches.setBusinessCustomer(businessCustomer);
			inBusinessReaches.setBusinessRate(Integer.parseInt(businessRate));
			inBusinessReaches.setBusinessYear(Integer.parseInt(businessYear));
			inBusinessReaches.setBusinessRemark(businessRemark);
			inBusinessReaches.setReachesType(Integer.parseInt(reachesType));

			inBusinessReachesService.creatrInBusinessReaches(inBusinessReaches);
			out.print("success");
		} catch (Exception e){
			e.printStackTrace();
		}
	}


	//删除
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
	}

}

