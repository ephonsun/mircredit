package com.banger.mobile.domain.model.report;

import java.util.Comparator;

import com.banger.mobile.domain.model.user.SysUserBean;

public class ComparatorSysUserBean implements Comparator<SysUserBean> {

	public int compare(SysUserBean o1, SysUserBean o2) {
		return o1.getUserName().compareTo(o2.getUserName());
	}

}
