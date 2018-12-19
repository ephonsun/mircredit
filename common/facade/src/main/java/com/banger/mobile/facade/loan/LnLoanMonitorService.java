package com.banger.mobile.facade.loan;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.base.loan.BaseLnLoanMonitor;
import com.banger.mobile.domain.model.loan.LnLoanMonitor;

/**
 * 
 * 
 * @author linkin
 * @version $Id: LnLoanMonitorService.java, v 0.1 2016-1-10 下午3:33:38 linkin Exp $
 */
public interface LnLoanMonitorService {
    

	   /**
     * 取得监控数据
     * @param loanId
     * @return
     */
    public List<LnLoanMonitor> getLoanMonitorList(Integer loanId);
    /**
     * 添加数据
     * @param lanMonitor
     * @return
     */
    public void createLoanMonitor(BaseLnLoanMonitor lanMonitor);
    /**
     * 添加数据
     * @param lanMonitor
     * @return
     */
    public void updateLoanMonitor(BaseLnLoanMonitor lanMonitor);
    /**
     * 修改附件数据
     * @param lanMonitor
     * @return
     */
    public void updateLoanMonitorfile(BaseLnLoanMonitor lanMonitor);
    /**
     * 取得监控数据
     * @param loanId
     * @return
     */
    public BaseLnLoanMonitor getLoanMonitor(Integer loanLmId);
    
    public void updateReadTag(Map<String,Object> map);
   
}
