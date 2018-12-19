package com.banger.mobile.facade.webservice;

import javax.jws.WebService;

/**
 * @author zhangfp
 * @version $Id: LoanAnaWebService v 0.1 ${} 上午9:21 zhangfp Exp $
 */
@WebService
public interface LoanAnaWebService {
    String getAnalyzesAppInfo(String account, Integer loanId);

    String saveAnalyzesAppInfo(String account, Integer loaned, String jsonString);

    String getAnalyzesCobList(String account, Integer loanId);

    String saveAnalyzesCobInfo(String account, Integer loanId, String jsonString);

    String getAnalyzesGuaList(String account, Integer loanId);

    String saveAnalyzesGuaInfo(String account, Integer loanId, String jsonString);
}
