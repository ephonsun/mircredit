package com.banger.mobile.domain.model.credentialType;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.banger.mobile.domain.model.base.credentialType.BaseCredentialType;

public class CredentialType extends BaseCredentialType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 433774721612314424L;

    public CredentialType(){
    	super();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(120821503, 605718547).appendSuper(super.hashCode()).toHashCode();
    }
    
}
