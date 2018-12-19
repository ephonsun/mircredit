package com.banger.mobile.webapp.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.constants.Constants;
import com.banger.mobile.domain.annotation.EscapeClassFilter;
import com.banger.mobile.domain.annotation.EscapeFieldFilter;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.util.PropertyInfo;
import com.banger.mobile.util.ReflectorUtil;
import com.banger.mobile.util.TypeUtil;
import com.hundsun.common.lang.StringUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class BaseAction extends ActionSupport implements Preparable, ServletRequestAware ,ServletContextAware {

    private static final long            serialVersionUID = -2521026016299139301L;
    protected HttpServletRequest         request          = null;
    

    protected Page                       page;
    protected String                     msg;
    protected transient final Log        log              = LogFactory.getLog(getClass());

    protected static Map<String, String> sessionMap       = new HashMap<String, String>();

    protected int                        pageSize         = 1;

    protected int                        currentPage      = 1;

    protected int                        totalPages;

    protected int                        id;

    protected String					 rules;			  //服务端校验规则
    
    private boolean ecapseFlag = false;
    
	private String rootPath;				//项目站点路径
	
	protected String filter;//拦截器过滤标记
	
	public String getRootPath()
	{
		return this.rootPath.replaceAll("\\\\", "/");
	}
    
    private ServletContext context;
    
    public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}
	
	public void setServletContext(ServletContext context) {
		this.context = context;
		this.rootPath = context.getRealPath("/");
	}
	
	public Object getBean(String name)
	{
		WebApplicationContext container  = WebApplicationContextUtils.getWebApplicationContext(context);
		return container.getBean(name);
	}
	
	public void setServletRequest(HttpServletRequest request) {
        this.request = request;
        request.setAttribute("currentAction",this);
    }

    public HttpServletRequest getRequest() {
        return ServletActionContext.getRequest();
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public Page getPage() {
        page = (page == null) ? new Page() : page;
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public String getRules(){
    	return this.rules;
    }
    
    public void setRules(String rules){
    	this.rules = rules;
    }

    public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	/**
     * Convenience method to get the response
     * 
     * @return current response
     */
    public HttpServletResponse getResponse() {
        return ServletActionContext.getResponse();
    }

    /**
     * Convenience method to get the session. This will create a session if one
     * doesn't exist.
     * 
     * @return the session from the request (request.getSession()).
     */
    public HttpSession getSession() {
        return getRequest().getSession();
    }

    protected String render(String text, String contentType) {
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType(contentType);
            response.getWriter().write(text);
        } catch (IOException e) {
        }
        return null;
    }

    protected String renderText(String text) {
        return render(text, "text/plain;charset=UTF-8");
    }

    protected String renderHtml(String html) {
        return render(html, "text/html;charset=UTF-8");
    }

    protected String renderXML(String xml) {
        return render(xml, "text/xml;charset=UTF-8");
    }

    /**
     * @throws Exception
     * @see com.opensymphony.xwork2.Preparable#prepare()
     */
    public void prepare() throws Exception {

    }

    public Log getLog() {
        return log;
    }

    public String getFormValue(String name) {
        String result = "";
        String va = getRequest().getParameter(name);
        if (StringUtil.isNotBlank(va)) {
            result = StringUtil.trim(va);
        }
        return result;
    }

    /**
     * 清理已登录用户信息
     * @return
     */
    protected boolean clear(String userName) {
        sessionMap.remove(userName);
        System.out.println(sessionMap.size());
        return false;
    }
    /**
     * 获取登录用户信息
     * @return
     */
    public IUserInfo getLoginInfo(){
     try{
             return (IUserInfo)this.getRequest().getSession().getAttribute(Constants.SESSION_LOGININFO);
        }
        catch(Exception e)
        {
            return null;
        }
        
    }
    
    /**
     * 
     * @return
     * @throws UnsupportedEncodingException 
     */
    public String getAutoLogin() throws UnsupportedEncodingException
    {
    	if(request.getParameterMap().containsKey("autoLogin")){
        	if(request.getParameterMap().containsKey("account"))
        	{
        		String account = URLDecoder.decode(request.getParameter("account"),"UTF-8");
        		return "autoLogin=true&account=\"+encodeURI(encodeURI('"+account+"'))+\"&";
        	}
    	}
    	return "";
    }
    
    /**
     * 判断是否有对应访问权限
     * @param action
     * @return
     */
    public boolean hasPermission(String action)
    {
        try
        {
        boolean flag=false;
        String actions = new String();
        List<String> privilegeFunc = new ArrayList<String>();
       
        // 获取权限列表
        privilegeFunc=this.getLoginInfo().getFuncCodes();//得到操作数据权限列表
        actions = this.getLoginInfo().getExcludeActions();
        if (actions.indexOf(action) != -1 || privilegeFunc.indexOf(action)!=-1)
        { // 如果找到了，则说明有权限
            flag = true;
        }
        return flag;
      
        }catch(Exception e)
        {
            return false;
      }
      }
    
    public void escapeText(){
    	if(!this.ecapseFlag){
    		this.ecapseFlag=true;
    		ActionEscape.escapeText(this);
    	}
    }
    
    public static void escapeText(Object obj){
    	if(obj!=null){
    		try {
	    		if(obj instanceof List){
	    			ActionEscape.escapeList(obj);
	    		}
	    		else if(obj instanceof Map){
	    			
	    		}
	    		else{
	    			ActionEscape.escapeEntity(obj);
	    		}
    		} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
    static class ActionEscape{
		static void escapeText(Object action){
			try {
				PropertyInfo[] pis = ReflectorUtil.getProperties(action.getClass());
				for(PropertyInfo pi : pis){
					if(pi.getGetMethod()!=null && pi.getField()!=null){
						if(TypeUtil.isValueType(pi.getType())){
							if(pi.getType().equals(String.class)){					//action里定义的字符串属性字段
								//escapeString(action,pi);
							}
						}
						else if(pi.getType().isAssignableFrom(List.class)){
							escapeList(action,pi);
						}
						else if(pi.getType().isAssignableFrom(Map.class)){
							escapeMap(action,pi);
						}else{
							if(pi.getField()!=null){
								Object v = pi.getField().get(action);
								if(v!=null){
									escapeEntity(v);
								}
							}
						}
					}
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		static void escapeString(Object obj,PropertyInfo pi) throws IllegalArgumentException, IllegalAccessException{
			if(obj!=null){
				Annotation a = pi.getField().getAnnotation(EscapeFieldFilter.class);
				if(a!=null)return;
				Object v = pi.getField().get(obj);
				if(v!=null){
					String tx = escapeText((String)v);
					pi.getField().set(obj,tx);
				}
			}
		}
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		static void escapeMap(Object obj,PropertyInfo pi) throws IllegalArgumentException, IllegalAccessException{
			if(obj!=null){
				Object v = pi.getField().get(obj);
				if(v instanceof Map){
					for(Entry entry : ((Map<?,?>)v).entrySet()){
						Object val = entry.getValue();
						if(val!=null){
							if(TypeUtil.isValueType(val.getClass())){
								if(val instanceof String){
									entry.setValue(escapeText((String)val));
								}
							}else if(val.getClass().isAssignableFrom(List.class)){
								
							}
							else if(val.getClass().isAssignableFrom(Map.class)){
								
							}
							else{
								escapeEntity(val);
							}
						}
					}
				}
			}
		}
		
		@SuppressWarnings("unchecked")
		static void escapeList(Object obj) throws IllegalArgumentException, IllegalAccessException{
			if(obj!=null){
				if(obj instanceof List){
					List<Object> list = (List<Object>)obj;
					for(int i=0;i<list.size();i++){
						Object val = list.get(i);
						if(val!=null){
							if(TypeUtil.isValueType(val.getClass())){
								if(val instanceof String){
									list.set(i,escapeText((String)val));
								}
							}else if(val.getClass().isAssignableFrom(List.class)){
								
							}
							else if(val.getClass().isAssignableFrom(Map.class)){
								
							}
							else{
								escapeEntity(val);
							}
						}
					}
				}
			}
		}
		
		static void escapeList(Object obj,PropertyInfo pi) throws IllegalArgumentException, IllegalAccessException{
			if(obj!=null){
				Object v = pi.getField().get(obj);
				if(v instanceof List){
					escapeList(v);
				}
			}
		}
		
		@SuppressWarnings("unchecked")
		static void escapeEntity(Object obj) throws IllegalArgumentException, IllegalAccessException{
			if(obj!=null){
				Annotation a = obj.getClass().getAnnotation(EscapeClassFilter.class);
				if(a!=null)return;
				if(obj.getClass().getName().indexOf("com.banger.mobile.domain.model")>-1){
					PropertyInfo[] pis = ReflectorUtil.getProperties(obj.getClass());
					for(PropertyInfo pi : pis){
						if(pi.getGetMethod()!=null && pi.getField()!=null){
							if(TypeUtil.isValueType(pi.getType())){
								if(pi.getType().equals(String.class)){					//action里定义的字符串属性字段
									escapeString(obj,pi);
								}
							}
							else if(pi.getType().isAssignableFrom(List.class)){
								escapeList(obj,pi);
							}
							else if(pi.getType().isAssignableFrom(Map.class)){
								escapeMap(obj,pi);
							}else{
								if(pi.getField()!=null){
									Object v = pi.getField().get(obj);
									if(v!=null){
										escapeEntity(v);
									}
								}
							}
						}
					}
				}
				else if(obj instanceof PageUtil){
					PageUtil<Object> po = (PageUtil<Object>)obj;
					if(po.getItems()!=null){
						escapeList(po.getItems());
					}
				}
			}
		}
		
		public static String escapeText(String text){
			String str="";
			if(text!=null && text.length()>0){
				str = text.replaceAll("\\'","&#8242;").replaceAll("\\\"", "&#8243;").replaceAll("\\<","&lt;").replaceAll("\\>","&gt;");
			}
			return str;
		}
	}

}
