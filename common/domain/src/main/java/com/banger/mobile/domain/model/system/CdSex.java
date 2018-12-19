package com.banger.mobile.domain.model.system;

import com.banger.mobile.domain.model.base.BaseObject;

public class CdSex extends BaseObject {
	private static final long serialVersionUID = -3384246601510581769L;
	
	private String sexCode;			//值
	private String sexName;			//名称
	private String icon;			//图标
	private Integer sortNo;			//排序号
	
	public String getSexCode() {
		return sexCode;
	}
	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
	}
	public String getSexName() {
		return sexName;
	}
	public void setSexName(String sexName) {
		this.sexName = sexName;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getSortNo() {
		return sortNo;
	}
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
}
