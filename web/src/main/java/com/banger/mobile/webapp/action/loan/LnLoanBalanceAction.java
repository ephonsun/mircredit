package com.banger.mobile.webapp.action.loan;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.banger.mobile.domain.model.loan.LnLoanBalance;
import com.banger.mobile.domain.model.loan.LnOpHistory;
import com.banger.mobile.facade.loan.LnLoanBalanceService;
import com.banger.mobile.facade.loan.LnOpHistoryService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * 资产负债表
 * @author Administrator
 *
 */
public class LnLoanBalanceAction extends BaseAction {

	  private static Logger         logger = Logger.getLogger(LnLoanBalanceAction.class);
	  private LnLoanBalanceService lnLoanBalanceService;   
	  private LnLoanBalance lnLoanBalance;
	  private LnOpHistoryService lnOpHistoryService;

	  
	
	 public LnOpHistoryService getLnOpHistoryService() {
		return lnOpHistoryService;
	}
	public void setLnOpHistoryService(LnOpHistoryService lnOpHistoryService) {
		this.lnOpHistoryService = lnOpHistoryService;
	}
	public LnLoanBalance getLnLoanBalance() {
		return lnLoanBalance;
	}
	public void setLnLoanBalance(LnLoanBalance lnLoanBalance) {
		this.lnLoanBalance = lnLoanBalance;
	}
	public void setLnLoanBalanceService(LnLoanBalanceService lnLoanBalanceService) {
		this.lnLoanBalanceService = lnLoanBalanceService;
	}
	/**
	    * 单个查询
	    */
		public void selectBalance(){
			
			PrintWriter out = null;
			try {
				HttpServletResponse response = this.getResponse();
				out = response.getWriter();
				response.setContentType("text/html");
			LnLoanBalance in=lnLoanBalanceService.selectBalanceByPrimary(lnLoanBalance);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		      if (in.getDcrq() != null) {
		        String dcrqStr = sdf.format(in.getDcrq());
		        in.setDcrqStr(dcrqStr);
		      }
		      if (in.getCsqyrq() != null)
		      {
		        String csqyrqStr = sdf.format(in.getCsqyrq());
		        in.setCsqyrqStr(csqyrqStr);
		      }

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
 * 保存修改
 * @throws ParseException 
 */
	public void insertBalance() throws ParseException{
		PrintWriter out = null;
		try {
			HttpServletResponse response = this.getResponse();
			out = response.getWriter();
			response.setContentType("text/html;charset=utf-8");
			LnLoanBalance lnLoanBalance= new LnLoanBalance();
			String balanceId=request.getParameter("loanBalanceId");
			if(!StringUtils.isBlank(balanceId)){
				lnLoanBalance.setLoanBalanceId(Integer.parseInt(balanceId));
			}
			
			lnLoanBalance.setLoanId(Integer.parseInt(request.getParameter("loanId")));
			String dcrq = request.getParameter("dcrq");
			if(!StringUtils.isBlank(dcrq)){
				lnLoanBalance.setDcrq(DateUtil.strToDate(dcrq,"yyyy-MM-dd"));
			}
			
			lnLoanBalance.setBw(request.getParameter("bw"));
			lnLoanBalance.setBwzc(request.getParameter("bwzc"));
			lnLoanBalance.setBwfz(request.getParameter("bwfz"));
			String fcsl=request.getParameter("fcsl");
			if(!StringUtils.isBlank(fcsl)){
				lnLoanBalance.setFcsl(Integer.parseInt(fcsl));
			}
			String fcajsl =request.getParameter("fcajsl");
			if(!StringUtils.isBlank(fcajsl)){
				lnLoanBalance.setFcajsl(Integer.parseInt(fcajsl));
			}
			String jdcsl = request.getParameter("jdcsl");
			if(!StringUtils.isBlank(jdcsl)){
				lnLoanBalance.setJdcsl(Integer.parseInt(jdcsl));
			}
			String jdcajsl = request.getParameter("jdcajsl");
			if(!StringUtils.isBlank(jdcajsl)){
				lnLoanBalance.setJdcajsl(Integer.parseInt(jdcajsl));
			}
			
			lnLoanBalance.setHsfs(request.getParameter("hsfs"));
			String csqyrq = request.getParameter("csqyrq");
			if(!StringUtils.isBlank(csqyrq)){
				lnLoanBalance.setCsqyrq(DateUtil.strToDate(csqyrq,"yyyy-MM-dd"));
			}
			String csqy = request.getParameter("csqy");
			if(!StringUtils.isBlank(csqy)){
				BigDecimal bDcsqy = new BigDecimal(csqy);
				lnLoanBalance.setCsqy(bDcsqy);
			}
			
			lnLoanBalance.setCsqymx(request.getParameter("csqymx"));
			String qjlr = request.getParameter("qjlr");
			if(!StringUtils.isBlank(qjlr)){
				BigDecimal bDqjlr = new BigDecimal(qjlr);
				lnLoanBalance.setQjlr(bDqjlr);
			}

			String hkbj = request.getParameter("hkbj");
			if(!StringUtils.isBlank(hkbj)){
				BigDecimal bDhkbj = new BigDecimal(hkbj);
				lnLoanBalance.setHkbj(bDhkbj);
			}
			
			String qjzz = request.getParameter("qjzz");
			if(!StringUtils.isBlank(qjzz)){
				BigDecimal bDqjzz = new BigDecimal(qjzz);
				lnLoanBalance.setQjzz(bDqjzz);
			}
			String sz = request.getParameter("sz");
			if(!StringUtils.isBlank(sz)){
				BigDecimal bDsz = new BigDecimal(sz);
				lnLoanBalance.setSz(bDsz);
			}
			String qntq = request.getParameter("qntq");
			if(!StringUtils.isBlank(qntq)){
				BigDecimal bDqntq = new BigDecimal(qntq);
				lnLoanBalance.setQntq(bDqntq);
			}
			String zj = request.getParameter("zj");
			if(!StringUtils.isBlank(zj)){
				BigDecimal bDzj = new BigDecimal(zj);
				lnLoanBalance.setZj(bDzj);
			}
			String bz = request.getParameter("bz");
			if(!StringUtils.isBlank(bz)){
				BigDecimal bDbz = new BigDecimal(bz);
				lnLoanBalance.setBz(bDbz);
			}
			lnLoanBalance.setNote(request.getParameter("note"));
			lnLoanBalance.setFx(request.getParameter("fx"));
			lnLoanBalance.setSm(request.getParameter("sm"));
			LnLoanBalance returnObj= new LnLoanBalance();
			if(lnLoanBalance.getLoanBalanceId()==null){
				returnObj =lnLoanBalanceService.insertBalance(lnLoanBalance);
			}else{
				returnObj=lnLoanBalanceService.updateBalance(lnLoanBalance);
			}
			
			LnOpHistory lnOpHistory = new LnOpHistory();
            lnOpHistory.setLoanId(Integer.parseInt(request.getParameter("loanId")));
            lnOpHistory.setOpHistoryDate(Calendar.getInstance().getTime());
            lnOpHistory.setUserId(this.getLoginInfo().getUserId());
            lnOpHistory.setContent("保存资产负债信息");
            lnOpHistory.setRemark("");
            lnOpHistory.setBeforeStatusId(3);
            lnOpHistory.setAfterStatusId(3);
            lnOpHistoryService.insertLnOpHistory(lnOpHistory);
            
			String json = "{\"success\":true,\"id\":"+returnObj.getLoanBalanceId()+"}";
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
