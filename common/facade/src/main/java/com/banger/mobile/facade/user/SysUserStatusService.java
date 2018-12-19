package com.banger.mobile.facade.user;

/**
 * 用户状态信息
 * @author zsw
 */
public interface SysUserStatusService {
	/**
	 * 检查用户登入状态,
	 * 1:当用户在另外终端登入时，踢出
	 * @return
	 */
	public String checkLoginStatus(String account);
	
	/**
	 * 得到当前请求的IP
	 * @return
	 */
	public String getLoginUserIP();
}
