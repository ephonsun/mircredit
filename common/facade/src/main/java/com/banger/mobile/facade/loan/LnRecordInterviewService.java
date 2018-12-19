package com.banger.mobile.facade.loan;

import com.banger.mobile.domain.model.loan.LnBusinessModel;
import com.banger.mobile.domain.model.loan.LnRecordInterview;

public interface LnRecordInterviewService {

	LnRecordInterview selectByPrimary(LnRecordInterview lnRecordInterview);

	LnRecordInterview insertLnRecordInterview(LnRecordInterview lnRecordInterview);

	LnRecordInterview updateLnRecordInterview(LnRecordInterview lnRecordInterview);

	LnRecordInterview selectByPrimary(Integer loanId);

	
    }
