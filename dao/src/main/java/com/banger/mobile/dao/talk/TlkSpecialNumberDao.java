package com.banger.mobile.dao.talk;

import java.util.List;

import com.banger.mobile.domain.model.talk.TlkSpecialNumber;

public interface TlkSpecialNumberDao {
	/**
	 * 得到所有特殊号码
	 */
	public List<TlkSpecialNumber> getSpecialNumbers();
}
