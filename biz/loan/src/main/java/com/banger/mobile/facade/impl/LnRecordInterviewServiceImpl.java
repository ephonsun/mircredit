package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.LnRecordInterviewDao;
import com.banger.mobile.domain.model.loan.LnRecordInterview;
import com.banger.mobile.facade.loan.LnRecordInterviewService;

public class LnRecordInterviewServiceImpl implements LnRecordInterviewService {

	private LnRecordInterviewDao lnRecordInterviewDao;
	
	
	public void setLnRecordInterviewDao(LnRecordInterviewDao lnRecordInterviewDao) {
		this.lnRecordInterviewDao = lnRecordInterviewDao;
	}


	public LnRecordInterview selectByPrimary(LnRecordInterview lnRecordInterview) {

		return  lnRecordInterviewDao.selectByPrimary(lnRecordInterview);
	}

	
	public LnRecordInterview insertLnRecordInterview(LnRecordInterview lnRecordInterview) {
		LnRecordInterview isExist = lnRecordInterviewDao.selectByPrimary(lnRecordInterview);
		if(isExist==null){
			LnRecordInterview ln=	lnRecordInterviewDao.insertLnRecordInterview(lnRecordInterview);
			return ln;
		}
		return isExist;
	}

	
	public LnRecordInterview updateLnRecordInterview(
			LnRecordInterview lnRecordInterview) {
		LnRecordInterview ln = lnRecordInterviewDao.selectByPrimary(lnRecordInterview);
		ln.setApplicantEvaluation(lnRecordInterview.getApplicantEvaluation());
		ln.setApplicantFamliyInfo(lnRecordInterview.getApplicantFamliyInfo());
		ln.setCheckCompany(lnRecordInterview.getCheckCompany());
		ln.setCheckInformation(lnRecordInterview.getCheckInformation());
		ln.setCompanyNum(lnRecordInterview.getCompanyNum());
		ln.setMajorBusiness(lnRecordInterview.getMajorBusiness());
		ln.setCompanyAge(lnRecordInterview.getCompanyAge());
		ln.setIncome(lnRecordInterview.getIncome());
		ln.setInterviewDate(lnRecordInterview.getInterviewDate());
		ln.setInterviewPeopleName(lnRecordInterview.getInterviewPeopleName());
		ln.setInterviewObjectCompany(lnRecordInterview.getInterviewObjectCompany());
		ln.setInterviewObjectPhone(lnRecordInterview.getInterviewObjectPhone());
		ln.setInterviewObjectPosition(lnRecordInterview.getInterviewObjectPosition());
		ln.setCheckCompany(lnRecordInterview.getCheckCompany());
		ln.setCheckInformation(lnRecordInterview.getCheckInformation());
		ln.setInterviewOjectName(lnRecordInterview.getInterviewOjectName());
		ln.setWorkTime(lnRecordInterview.getWorkTime());
		 lnRecordInterviewDao.updateLnRecordInterview(ln);
		
		return ln;
		
	}


	@Override
	public LnRecordInterview selectByPrimary(Integer loanId) {
		LnRecordInterview lnRecordInterview =new LnRecordInterview();
		lnRecordInterview.setLoanId(loanId);
		return  lnRecordInterviewDao.selectByPrimary(lnRecordInterview);
	}

   
}
