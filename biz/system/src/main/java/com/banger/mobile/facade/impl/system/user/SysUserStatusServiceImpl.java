package com.banger.mobile.facade.impl.system.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.banger.mobile.constants.OnLineSessionContext;
import com.banger.mobile.domain.Enum.user.EnumUserStatus;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.facade.user.SysUserStatusService;

public class SysUserStatusServiceImpl implements SysUserStatusService {
	private SysUserService userService;
	
	public SysUserService getUserService() {
		return userService;
	}

	public void setUserService(SysUserService userService) {
		this.userService = userService;
	}

	/**
	 * 检查用户登入状态,
	 * 1:当用户在另外终端登入时，踢出
	 */
	public String checkLoginStatus(String account) {
		SysUser user = userService.getAllUserByAccount(account);
		if(user.getIsDel().equals(1))
		{
			return "系统退出通知：此帐号已被删除!";
		}
		else if(user.getIsActived().equals(0))
		{
			return "系统退出通知: 此帐号已被禁用!";
		}
		IUserInfo  kick = OnLineSessionContext.getInstance().getUserKickLine(user.getUserId().toString());
		if(kick!=null)
		{
			String ip = this.getLoginUserIP();
			if(!kick.getIP().equals(ip))
			{
				return "系统退出通知：此帐号在别的电脑登入!";
			}
		}
		else
		{
			IUserInfo info = this.getUserLoginInfo();
			if(info!=null)
			{
				this.setUserLoginInfo(info);
			}
			else
			{
				userService.autoLogin(account);
			}
		}
		return "Success";
	}

	/**
	 * 得到当前请求的IP
	 */
	public String getLoginUserIP() {
		HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
		return request.getRemoteAddr();
	}
	
	/**
	 * 当前登录用户信息
	 * @return
	 */
	private IUserInfo getUserLoginInfo(){
        HttpServletRequest req = org.apache.struts2.ServletActionContext.getRequest();
        HttpSession session = req.getSession();
        return (IUserInfo)session.getAttribute("LoginInfo");
    }
	
	private void setUserLoginInfo(IUserInfo info){
		HttpServletRequest req = org.apache.struts2.ServletActionContext.getRequest();
        HttpSession session = req.getSession();
        session.setAttribute("LoginInfo",info);
	}
	
}
