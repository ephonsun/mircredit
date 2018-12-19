package com.banger.mobile.domain.model.finance;


import com.banger.mobile.domain.model.base.finance.BaseFeKnowledgebaseContent;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 6, 2012 11:27:31 AM
 * 类说明
 */
public class FeKnowledgebaseContent extends BaseFeKnowledgebaseContent implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4414196933784280155L;
	
	
	private String                         knowTypeName;

	public FeKnowledgebaseContent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getKnowTypeName() {
		return knowTypeName;
	}

	public void setKnowTypeName(String knowTypeName) {
		this.knowTypeName = knowTypeName;
	}

}



