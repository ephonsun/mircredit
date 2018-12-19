package com.banger.mobile.domain.model.customer;

import java.util.Map;

import com.banger.mobile.domain.model.templateField.CrmTemplateField;

public class CustomerExportContext {
	private String data;
	private Map<String,CrmTemplateField> fields;
	private Map<Integer,String> userNames;
	private Map<String, String> params;
	private int recordCount;
	private int sheetMaxRows;
	private int sheetRows;
	private int batchCount;//批次
	private int pageSize=5000;//每批大小
	
	
	public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getBatchCount() {
        return batchCount;
    }
    public void setBatchCount(int batchCount) {
        this.batchCount = batchCount;
    }
    public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Map<String, CrmTemplateField> getFields() {
		return fields;
	}
	public void setFields(Map<String, CrmTemplateField> fields) {
		this.fields = fields;
	}
	public Map<Integer, String> getUserNames() {
		return userNames;
	}
	public void setUserNames(Map<Integer, String> userNames) {
		this.userNames = userNames;
	}
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public int getSheetMaxRows() {
		return sheetMaxRows;
	}
	public void setSheetMaxRows(int sheetMaxRows) {
		this.sheetMaxRows = sheetMaxRows;
	}
	public int getSheetRows() {
		return sheetRows;
	}
	public void setSheetRows(int sheetRows) {
		this.sheetRows = sheetRows;
	}
}
