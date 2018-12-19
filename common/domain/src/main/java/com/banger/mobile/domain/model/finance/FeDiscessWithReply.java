package com.banger.mobile.domain.model.finance;

import java.util.List;

import com.banger.mobile.domain.model.base.BaseObject;


/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 10, 2012 2:03:02 PM
 * 类说明
 */
public class FeDiscessWithReply extends BaseObject implements java.io.Serializable,Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1779102791759931998L;
	
	private FeArticleDiscess                 discess;
	
	private List<FeArticleReply>             replyList;
	
	
	public Object clone() throws CloneNotSupportedException {
		// 实现clone方法
		return super.clone();
	}

	public List<FeArticleReply> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<FeArticleReply> replyList) {
		this.replyList = replyList;
	}

	public FeArticleDiscess getDiscess() {
		return discess;
	}

	public void setDiscess(FeArticleDiscess discess) {
		this.discess = discess;
	}           	
}



