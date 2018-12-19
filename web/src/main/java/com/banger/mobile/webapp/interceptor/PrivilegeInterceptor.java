/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :权限拦截器...
 * Author     :cheny
 * Create Date:2012-6-5
 */
package com.banger.mobile.webapp.interceptor;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.banger.mobile.constants.Constants;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.domain.model.user.SysUserOnline;
import com.banger.mobile.domain.model.user.UserInfo;
import com.banger.mobile.facade.user.SysUserOnlineService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;
import com.banger.mobile.webapp.valid.ValidEngine;
import com.banger.mobile.webapp.valid.ValidResult;
import com.banger.mobile.webapp.valid.ValidResultItem;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * @author cheny
 * @version $Id: PrivilegeInterceptor.java,v 0.1 2012-6-5 下午1:32:32 cheny Exp $
 */
public class PrivilegeInterceptor implements Interceptor {

    private static final long serialVersionUID = -7487859482536159415L;

    private Logger            log              = Logger.getLogger(this.getClass());

    private String            excludeAction;                                       //不被权限检测的action

    private String            excludeNameSpace;                                    //不被权限检测的action命名空间

    /**
     * 
     * @see com.opensymphony.xwork2.interceptor.Interceptor#destroy()
     */
    public void destroy() {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>结束权限拦截器!!!!<<<<<<<<<<<<<<<<<<<<<");
    }

    /**
     * 
     * @see com.opensymphony.xwork2.interceptor.Interceptor#init()
     */
    public void init() {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>开始初始化权限拦截器!!!!<<<<<<<<<<<<<<<<<<<<<");
    }

    /**
     * @param invocation
     * @return
     * @throws Exception
     * @see com.opensymphony.xwork2.interceptor.Interceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
     */
    public String intercept(ActionInvocation invocation) throws Exception {
    	//return excuteAction(invocation);
    	
        SysUserOnlineService sysUserOnlineService = (SysUserOnlineService) com.banger.mobile.util.SpringContextUtil
            .getBean("sysUserOnlineService");
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpSession session = request.getSession();
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        String url = "";
        String namespace = "";
        url = invocation.getProxy().getActionName();
        namespace = invocation.getProxy().getNamespace();
        namespace = namespace.substring(1);
        url = getActionUrlName(url); // 得到ACTION的名字
        if (request.getParameterMap().containsKey("autoLogin")) {
            if (request.getParameterMap().containsKey("account")) {
                String account = URLDecoder.decode(request.getParameter("account"), "UTF-8");
                UserInfo loginUser = (UserInfo) session.getAttribute(Constants.SESSION_LOGININFO);
                if (loginUser == null) {
                    if (invocation.getAction() instanceof BaseAction) {
                        SysUserService login = (SysUserService) ((BaseAction) invocation
                            .getAction()).getBean("sysUserService");
                        login.autoLogin(account);
                    }
                } else {
                    if (!loginUser.getAccount().equals(account)) {
                        SysUserService login = (SysUserService) ((BaseAction) invocation
                            .getAction()).getBean("sysUserService");
                        login.autoLogin(account);
                    }
                }
            }
            return this.excuteAction(invocation);
        }
        if (invocation.getAction() instanceof BaseAction) {
        	BaseAction action = (BaseAction)invocation.getAction();
        	if("none".equalsIgnoreCase(action.getFilter())){
        		return this.excuteAction(invocation);
        	}
        }
        if (excludeNameSpace.indexOf(namespace) != -1) {//如果是不需要验证的包
            if (namespace.equals("talk"))
                return this.excuteAction(invocation);
            else if (namespace.equals("upgrade"))
                return this.excuteAction(invocation);
            else {
                //当前操作对象
                UserInfo loginUser = (UserInfo) session.getAttribute(Constants.SESSION_LOGININFO);
                if(loginUser!=null){
                	if(url.equals("showLogin")){
                		Integer userId = loginUser.getUserId();
                		sysUserOnlineService.userLoginState(userId);
                	}
                }
                if (loginUser == null && !namespace.equals("login")) {//登录超时
                    if (isAjax(request)) {
                        out = response.getWriter();
                        out.print("expired");
                        System.out.println("登入用户超时 line:119 url:"+url);
                        return null;
                    } else {
                        System.out.println("登入用户超时 line:122 url:"+url);
                        return "timeoutlogin";
                    }
                } else {
                    if (namespace.equals("login")) {
                        if ("getMainPage,getCallMessage,initMessage,changeUserStatus,getWorkBench,getSystemRemind,"
                            .contains(url)) {
                            if (loginUser == null) {
                                if (isAjax(request)) {
                                    out = response.getWriter();
                                    out.print("expired");
                                    System.out.println("登入用户超时 line:131 url:"+url);
                                    return null;
                                } else {
                                    System.out.println("登入用户超时 line:133 url:"+url);
                                    return "timeoutlogin";
                                }
                            }
                        } else
                            return this.excuteAction(invocation);
                    }
                    //取出当前用户在线状态实体。
                    SysUserOnline  sysUserOnline = sysUserOnlineService.getSysUserOnlineByUserId(
                        getUserLoginInfo().getUserId()).get(0);
                    
                    if (getUserLoginInfo() == null) {//取不到session里面的用户，当前用户则是超时状态。
                        if (isAjax(request)) {
                            out = response.getWriter();
                            out.print("expired");
                            System.out.println("登入用户超时 line:147 url:"+url);
                            return null;
                        } else {
                            System.out.println("登入用户超时 line:150 url:"+url);
                            return "timeoutlogin";
                        }
                    } else {
                        if(getUserLoginInfo().getSessionId().equals(sysUserOnline.getUserSessionId())) { //如果当前会话sessionid等于在线列表sessionid
                            if (sysUserOnline.getOnlineStatusId().equals(5)) {//当前用户状态为删除时，则进行删除页面跳转。
                                if (isAjax(request)) {
                                    out = response.getWriter();
                                    out.print("deleted");
                                    System.out.println("登入用户被删除");
                                    return null;
                                } else {
                                    return "deleteuser";
                                }
                            }
                            if (sysUserOnline.getOnlineStatusId().equals(4)) {//当前用户状态为禁用时，则进行禁用页面跳转。
                                if (isAjax(request)) {
                                    out = response.getWriter();
                                    out.print("disabled");
                                    System.out.println("登入用户被禁用");
                                    return null;
                                } else {
                                    return "disableduser";
                                }
                            }
                            if (sysUserOnline.getOnlineStatusId().equals(6)) {//当前用户角色变化时，则进行角色变化页面跳转。
                                if (isAjax(request)) {
                                    out = response.getWriter();
                                    out.print("rolechang");
                                    System.out.println("登入用户角色被更改");
                                    return null;
                                } else {
                                    return "rolechanguser";
                                }
                            }
                            return this.excuteAction(invocation);
                        } 
                        else {                      //当前的sessionId和用户状态内的sessionID不一致时。做T除操作。
                            log.info(">>已经被踢下线！！！<<");
                            if (isAjax(request)) {
                                out = response.getWriter();
                                out.print("kicked");
                                return null;
                            } else {
                                return "kickerror";
                            }
                        }
                    }
                }
            }
        } else {
            if (excludeAction != null && excludeAction.indexOf(url) != -1) { //如果是不需要权限验证的action
                return this.excuteAction(invocation);
            } else {//否则就是需要验证Action
                IUserInfo crmUser = getUserLoginInfo();
                boolean flag = false;
                if (crmUser != null) {
                    //log.info(">>当前用户" + crmUser.getUserName() + " 进入" + url + ",开始操作!!!!!<<");
                    //log.info(">>判断当前用户权限开始<<");
                    //不被权限拦截的action
                    String actions = new String();
                    actions = crmUser.getExcludeActions();
                    //拥有的操作权限
                    List<String> privilegeFunc = new ArrayList<String>();
                    privilegeFunc = crmUser.getFuncCodes();
                    if (actions.indexOf(url) != -1 || privilegeFunc.indexOf(url) != -1) { // 如果找到了，则说明有权限
                        flag = true;
                    }
                } else {//登录超时
                    //log.info(">>您尚未登录或者登录失效！，请重新登录!<<");
                    if (isAjax(request)) {
                        out = response.getWriter();
                        out.print("expired");
                        return null;
                    } else {
                        return "timeoutlogin";
                    }
                }
                if (flag) {
                    //log.info(">>验证成功,你有访问权限!!!!开始操作!!!<<");
                    //取出当前用户在线状态实体。
                    SysUserOnline  sysUserOnline = sysUserOnlineService.getSysUserOnlineByUserId(
                        getUserLoginInfo().getUserId()).get(0);
                    if (getUserLoginInfo() == null) {
                        if (isAjax(request)) {
                            out = response.getWriter();
                            out.print("expired");
                            return null;
                        } else {
                            return "timeoutlogin";
                        }
                    } else {
                        if (getUserLoginInfo().getSessionId().equals(sysUserOnline.getUserSessionId())) { //如果当前会话sessionid等于在线列表sessionid
                            if (sysUserOnline.getOnlineStatusId().equals(5)) {//当前用户状态为删除时，则进行删除页面跳转。
                                if (isAjax(request)) {
                                    out = response.getWriter();
                                    out.print("deleted");
                                    return null;
                                } else {
                                    return "deleteuser";
                                }
                            }
                            if (sysUserOnline.getOnlineStatusId().equals(4)) {//当前用户状态为禁用时，则进行禁用页面跳转。
                                if (isAjax(request)) {
                                    out = response.getWriter();
                                    out.print("disabled");
                                    return null;
                                } else {
                                    return "disableduser";
                                }
                            }
                            if (sysUserOnline.getOnlineStatusId().equals(6)) {//当前用户角色变化时，则进行角色变化页面跳转。
                                if (isAjax(request)) {
                                    out = response.getWriter();
                                    out.print("rolechang");
                                    return null;
                                } else {
                                    return "rolechanguser";
                                }
                            }
                            return this.excuteAction(invocation);
                        } else //当前的sessionId和用户状态内的sessionID不一致时。做T除操作。
                        {
                            //log.info(">>已经被踢下线！！！<<");
                            if (isAjax(request)) {
                                out = response.getWriter();
                                out.print("kicked");
                                return null;
                            } else {
                                return "kickerror";
                            }
                        }
                    }
                } else {
                    //log.info(">>您没有访问权限!!<<");
                    return "privilegeerror";
                }
            }
        }
    }
    
    /**
     * 执行Action
     * @param invocation
     * @return
     * @throws Exception
     */
    private String excuteAction(ActionInvocation invocation){
    	HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
    	try{
    		response.setCharacterEncoding("utf-8");
    		if(isAjax(request) && invocation.getAction() instanceof BaseAction){		//在执行Action函数前,先校验数据			
	    		BaseAction action = (BaseAction)invocation.getAction();
	    		if(!StringUtil.isNullOrEmpty(action.getRules())){
	    			ValidResult result = ValidEngine.valids(action.getRules(),action);
	    			if(result.getItems().size()>0){
	    				JSONObject jo = new JSONObject();
	    				JSONArray ja = new JSONArray();
	    				for(ValidResultItem item : result.getItems()){
	    					JSONObject jItem = new JSONObject();
	    					jItem.put("index",item.getIndex());
	    					jItem.put("message",(item.getErrorMessages().length>1)?item.getErrorMessages():item.getErrorMessages()[0]);
	    					jItem.put("type", item.getType());
	    					ja.add(jItem);
	    				}
	    				jo.put("errors",ja);
	    				response.getWriter().print(jo.toString());
	    				return null;
	    			}
	    		}
	    	}
	    	return invocation.invoke();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    		log.error(e.toString());
    		StringBuilder sb = new StringBuilder();
    		for(StackTraceElement traceElement : e.getStackTrace()){
    			log.error(traceElement.toString());
    			sb.append(traceElement.toString());
    			sb.append("\r\n");
    		}
    		request.setAttribute("errorMsg", sb.toString());
    	}
        
    	return "error";
    }

    //获得登录信息
    private IUserInfo getUserLoginInfo() {
        HttpServletRequest req = org.apache.struts2.ServletActionContext.getRequest();
        HttpSession session = req.getSession();
        return (IUserInfo) session.getAttribute("LoginInfo");
    }

    /**
     * 判断提交方式是否是ajax
     */
    public boolean isAjax(HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        if (requestType != null && requestType.equals("XMLHttpRequest")) {
            return true;
        } else {
            return false;
        }
    }

    public static String getActionUrlName(String _url) { // 切地址字符串，取得ACTION的名字
        int pos = 0;
        String actionUrlName = "";
        pos = _url.lastIndexOf(".");
        actionUrlName = _url.substring(pos + 1, _url.length());
        return actionUrlName;
    }

    public String getExcludeAction() {
        return excludeAction;
    }

    public void setExcludeAction(String excludeAction) {
        this.excludeAction = excludeAction;
    }

    public String getExcludeNameSpace() {
        return excludeNameSpace;
    }

    public void setExcludeNameSpace(String excludeNameSpace) {
        this.excludeNameSpace = excludeNameSpace;
    }

}
