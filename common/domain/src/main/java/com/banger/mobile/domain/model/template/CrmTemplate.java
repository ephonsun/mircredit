package com.banger.mobile.domain.model.template;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.HashCodeBuilder;
 
import com.banger.mobile.domain.model.base.template.BaseCrmTemplate;
import com.banger.mobile.domain.model.templateField.CrmTemplateField;

public class CrmTemplate extends BaseCrmTemplate {
	private List<CrmTemplateField> fields;		//自定义字段

	public List<CrmTemplateField> getFields() {
		return fields;
	}

	public void setFields(List<CrmTemplateField> fields) {
		this.fields = fields;
	}

	private static final long serialVersionUID = 1617084534102727103L;
	 
	public CrmTemplate() {
		super();
		this.fields = new ArrayList<CrmTemplateField>();
	    }

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(1787969387, -1699529435).appendSuper(
				super.hashCode()).toHashCode();
	} 
}
