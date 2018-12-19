package com.banger.mobile.domain.model.finance;

import com.banger.mobile.domain.model.base.finance.BaseFeArticle;

public class FeArticle extends BaseFeArticle {
	
	private String columnName;//分类名字
	
	private String unReadCount;//未阅读数
	
	private String readCount;//已阅读数
	public FeArticle() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 6199263204996420397L;
	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getUnReadCount() {
		return unReadCount;
	}

	public void setUnReadCount(String unReadCount) {
		this.unReadCount = unReadCount;
	}

	public String getReadCount() {
		return readCount;
	}

	public void setReadCount(String readCount) {
		this.readCount = readCount;
	}

	

}
