package com.banger.mobile.domain.model.system;

import com.banger.mobile.domain.model.base.system.BaseCommProgress;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：May 21, 2012 10:05:58 AM
 * 类说明
 */
public class CommProgress extends BaseCommProgress {

	/**
	 * 
	 */
	private static final long serialVersionUID = 584616731852741235L;

	public CommProgress() {
		
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-1010944283, 1080707371).appendSuper(
				super.hashCode()).toHashCode();
	}

}



