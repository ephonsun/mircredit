package com.banger.mobile.dao.loan;

import com.banger.mobile.domain.model.loan.LnBusinessModel;
import com.banger.mobile.domain.model.loan.LnRecordInterview;

public interface LnRecordInterviewDao {

	LnRecordInterview selectByPrimary(LnRecordInterview lnRecordInterview);

	int updateLnRecordInterview(LnRecordInterview ln);

	LnRecordInterview insertLnRecordInterview(
			LnRecordInterview lnRecordInterview);

	
}
