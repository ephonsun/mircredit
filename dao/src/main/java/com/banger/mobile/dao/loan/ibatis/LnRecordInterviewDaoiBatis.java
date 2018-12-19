package com.banger.mobile.dao.loan.ibatis;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.banger.mobile.dao.loan.LnRecordInterviewDao;
import com.banger.mobile.domain.model.loan.LnRecordInterview;
import com.banger.mobile.ibatis.GenericDaoiBatis;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class LnRecordInterviewDaoiBatis extends GenericDaoiBatis implements LnRecordInterviewDao{

	public LnRecordInterviewDaoiBatis(Class persistentClass) {
		super(LnRecordInterview.class);
	
	}

	public LnRecordInterviewDaoiBatis() {
		super(LnRecordInterview.class);
		
	}

	@Override
	public LnRecordInterview selectByPrimary(LnRecordInterview lnRecordInterview) {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("loanId", lnRecordInterview.getLoanId());
		param.put("interviewId", lnRecordInterview.getInterviewId());
		LnRecordInterview list =(LnRecordInterview) this.getSqlMapClientTemplate().queryForObject(
				"selectByInterviewPrimary",param);
				return list;
	}

	@Override
	public int updateLnRecordInterview(LnRecordInterview ln) {
		
		return this.getSqlMapClientTemplate().update("updateLnRecordInterview",ln);
	}

	@Override
	public LnRecordInterview insertLnRecordInterview(
			LnRecordInterview lnRecordInterview) {
		Integer	lnTer=(Integer) this.getSqlMapClientTemplate().insert("insertLnRecordInterview",lnRecordInterview);
		LnRecordInterview ln = new LnRecordInterview();
		ln.setInterviewId(lnTer);
		return ln;
	}

	
}
