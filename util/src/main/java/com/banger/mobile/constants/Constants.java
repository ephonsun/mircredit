package com.banger.mobile.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Constant values used throughout the application.
 */
public class Constants {

	// ~ Static fields/initializers
	// =============================================

	public static final String SESSION_MEMBER = "memberInfo";

	/** 设置登陆标志 */
	public static final String SESSION_LOGININFO = "LoginInfo";
	
	/** 设置默认密码 */
	public static final String SESSION_DEFAULTPWD ="111111";
	
	public static final String CRM_SESSION = "crmSession";

	public static final String AGENCY_MEMBER = "agencySession";

	/**
	 * The name of the ResourceBundle used in this application
	 */
	public static final String BUNDLE_KEY = "ApplicationResources";

	/**
	 * ͼƬ�ϴ�·���ָ�б�� /
	 */
	public static final String FILE_SEP = "/";

	/**
	 * User home from System properties
	 */
	public static final String USER_HOME = System.getProperty("user.home")
			+ FILE_SEP;

	/**
	 * The name of the configuration hashmap stored in application scope.
	 */
	public static final String CONFIG = "appConfig";

	/**
	 * Session scope attribute that holds the locale set by the user. By setting
	 * this key to the same one that Struts uses, we get synchronization in
	 * Struts w/o having to do extra work or have two session-level variables.
	 */
	public static final String PREFERRED_LOCALE_KEY = "org.apache.struts2.action.LOCALE";

	/**
	 * The request scope attribute under which an editable user form is stored
	 */
	public static final String USER_KEY = "userForm";

	/**
	 * The request scope attribute that holds the user list
	 */
	public static final String USER_LIST = "userList";

	/**
	 * The request scope attribute for indicating a newly-registered user
	 */
	public static final String REGISTERED = "registered";

	/**
	 * The name of the Administrator role, as specified in web.xml
	 */
	public static final String ADMIN_ROLE = "ROLE_ADMIN";

	/**
	 * The name of the Administrator role, as specified in web.xml
	 */
	public static final String ROLE_CUSTOMER = "ROLE_CUSTOMER";

	public static final String GOODS_ADMIN_ROLE = "ROLE_ADMIN_GOODS";
	public static final String TRADE_ADMIN_ROLE = "ROLE_ADMIN_TRADE";
	public static final String SHOP_ADMIN_ROLE = "ROLE_ADMIN_SHOP";

//	public static  Map<String, Object> ONLINEMAP = new HashMap<String, Object>();//在线用户   
//	public static  Map<String, Object> OFFLINEMAP = new HashMap<String, Object>();//离线用户   
//	
//	
//	public static synchronized Map<String, Object> getONLINEMAP() {
//        return ONLINEMAP;
//    }
//
//    public static void setONLINEMAP(Map<String, Object> oNLINEMAP) {
//        ONLINEMAP = oNLINEMAP;
//    }
//
//    public static synchronized Map<String, Object> getOFFLINEMAP() {
//        return OFFLINEMAP;
//    }
//
//    public static void setOFFLINEMAP(Map<String, Object> oFFLINEMAP) {
//        OFFLINEMAP = oFFLINEMAP;
//    }

    /**
	 * The name of the User role, as specified in web.xml
	 */
	public static final String USER_ROLE = "ROLE_USER";

	/**
	 * The name of the user's role list, a request-scoped attribute when
	 * adding/editing a user.
	 */
	public static final String USER_ROLES = "userRoles";

	/**
	 * The name of the available roles list, a request-scoped attribute when
	 * adding/editing a user.
	 */
	public static final String AVAILABLE_ROLES = "availableRoles";

	/**
	 * The name of the CSS Theme setting.
	 */
	public static final String CSS_THEME = "csstheme";

	public static final String URL_RESOURCES = "1";

	public static final String METHOD_RESOURCES = "2";

	public static final Integer SHOPPINGCART_MAXNUM = 20;

	public static final Integer LAST_DAY = 2;

	public static final Integer CONFIRM_LAST_DAY = 7;

	public static final Integer TRADE_LAST_DAY = 7;

	public static final Integer ROWNUM = 10;

	// (1:前台网站咨询 2:个人中心投诉 3:crm后台咨询 4:crm后台投诉)
	public static final String SITE_CONSULTATIVE = "1";
	public static final String PERSONAL_CONSULTATIVE = "2";
	public static final String CRM_CONSULTATIVE = "3";
	public static final String COMPLIAINTS_CONSULTATIVE = "4";

	/**
	 * Attribute input style
	 */
	public enum InputStyle {
		text, textArea, radio, checkBOx, list
	}
	
	public static final boolean isStartMmsSms = true;
}
