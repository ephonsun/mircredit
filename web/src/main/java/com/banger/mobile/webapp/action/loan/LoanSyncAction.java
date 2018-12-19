package com.banger.mobile.webapp.action.loan;

import com.banger.mobile.domain.model.loan.LnLoan;
import com.banger.mobile.domain.model.loan.LnLoanInfo;
import com.banger.mobile.domain.model.loan.LnLoanType;
import com.banger.mobile.facade.loan.LnLoanInfoService;
import com.banger.mobile.facade.loan.LnLoanService;
import com.banger.mobile.facade.loan.LnLoanSyncService;
import com.banger.mobile.facade.loan.LnLoanTypeService;
import com.banger.mobile.facade.transport.ClientSocketLoanStatusService;
import com.banger.mobile.webapp.action.BaseAction;
import org.apache.log4j.Logger;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yuanme
 * Date: 13-2-5
 * Time: 下午4:13
 * To change this template use File | Settings | File Templates.
 * <p/>
 * 贷款同步维护 Action
 */
public class LoanSyncAction extends BaseAction {

    private static Logger logger = Logger.getLogger(LoanSyncAction.class);

    private LnLoanSyncService lnLoanSyncService;
    private LnLoanInfoService lnLoanInfoService;
    private ClientSocketLoanStatusService clientSocketLoanStatusService;

    //查询条件
    private int loanId;

    //同步贷款状态
    public void syncLoanStatus() {
        try {
            lnLoanSyncService.syncLoanStatus(loanId, this.getLoginInfo().getUserId());
            PrintWriter out = this.getResponse().getWriter();
            out.write("SUCCESS");
        } catch (Exception e) {
            log.error("syncLoanStatus", e);
        }
    }

    //同步还款信息，余额
    public void syncLoanDunInfo() {
        try {
            //同步还款状态
            lnLoanSyncService.syncLoanDunInfoRepayment(loanId, this.getLoginInfo().getUserId());

            //同步客户账户余额
            lnLoanSyncService.syncLoanDunInfoRemaining(loanId, this.getLoginInfo().getUserId());

            PrintWriter out = this.getResponse().getWriter();
            out.write("SUCCESS");
        } catch (Exception e) {
            log.error("syncLoanDunInfo", e);
        }
    }

    public void synchronousLoanStatus(){
    	//TODO:修改
    	/*
        try {
            String serialNumber =null;
            String loanId = request.getParameter("loanId");
            Map<String ,Object> param = new HashMap<String, Object>();
            param.put("loanId",Integer.parseInt(loanId.trim()));
            List<LnLoanInfo> lnLoanInfoList= lnLoanInfoService.selectLoanInfoList(param);
            if(lnLoanInfoList.size()>0){
                serialNumber=lnLoanInfoList.get(0).getSerialNumber();
            }
            clientSocketLoanStatusService.getLoanStatusMessage(serialNumber);
            PrintWriter out = this.getResponse().getWriter();
            out.write("SUCCESS");
        }catch (Exception e){
            log.error("synchronousLoanStatus", e);
        }
    */}


    /**
     * getter setter *
     */
    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public void setLnLoanSyncService(LnLoanSyncService lnLoanSyncService) {
        this.lnLoanSyncService = lnLoanSyncService;
    }

    public LnLoanInfoService getLnLoanInfoService() {
        return lnLoanInfoService;
    }

    public void setLnLoanInfoService(LnLoanInfoService lnLoanInfoService) {
        this.lnLoanInfoService = lnLoanInfoService;
    }

    public ClientSocketLoanStatusService getClientSocketLoanStatusService() {
        return clientSocketLoanStatusService;
    }

    public void setClientSocketLoanStatusService(ClientSocketLoanStatusService clientSocketLoanStatusService) {
        this.clientSocketLoanStatusService = clientSocketLoanStatusService;
    }
}
