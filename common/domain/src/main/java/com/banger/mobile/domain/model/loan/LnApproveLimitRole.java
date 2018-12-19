package com.banger.mobile.domain.model.loan;

import com.banger.mobile.domain.model.base.loan.BaseLnApproveLimitRole;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-27
 * Time: 下午3:21
 * To change this template use File | Settings | File Templates.
 *
 * 角色审批额度设置表
 */
public class LnApproveLimitRole extends BaseLnApproveLimitRole{
	private String roleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((roleName == null) ? 0 : roleName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LnApproveLimitRole other = (LnApproveLimitRole) obj;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		return true;
	}

	public LnApproveLimitRole(String roleName) {
		super();
		this.roleName = roleName;
	}

	public LnApproveLimitRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
