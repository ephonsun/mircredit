package com.banger.mobile.facade.record;

public interface IWriteLendingLoanJob {

	/**
	 * 每天凌晨1点 查询昨天完成放款的贷款 写入文件
	 */
    public void doWrite();

}