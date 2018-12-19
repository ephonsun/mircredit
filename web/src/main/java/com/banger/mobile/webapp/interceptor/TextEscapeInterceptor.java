package com.banger.mobile.webapp.interceptor;

import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.banger.mobile.util.JsUtil;
import com.banger.mobile.util.PropertyInfo;
import com.banger.mobile.util.ReflectorUtil;
import com.banger.mobile.util.TypeUtil;
import com.banger.mobile.webapp.action.BaseAction;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class TextEscapeInterceptor implements Interceptor {
	
	private static final long serialVersionUID = -5892627295296304479L;
	private Logger log  = Logger.getLogger(this.getClass());
    
	@Override
	public void destroy() {
		log.info(">>>>>>>>>>>>>>>>>>>>>>>结束特殊字符转义拦截器!!!!<<<<<<<<<<<<<<<<<<<<<");
	}

	@Override
	public void init() {
		log.info(">>>>>>>>>>>>>>>>>>>>>>>开始特殊字符转义拦截器!!!!<<<<<<<<<<<<<<<<<<<<<");
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String result = invocation.invoke();
		if("GET".equalsIgnoreCase(request.getMethod())){
			if (invocation.getAction() instanceof BaseAction) {
				Object a = invocation.getAction();
				ActionEscape.escapeText(a);
			}
		}
		return result;
	}
	
	static class ActionEscape{
		static void escapeText(Object action){
			try {
				PropertyInfo[] pis = ReflectorUtil.getProperties(action.getClass());
				for(PropertyInfo pi : pis){
					if(pi.getGetMethod()!=null && pi.getField()!=null){
						if(TypeUtil.isValueType(pi.getType())){
							if(pi.getType().equals(String.class)){					//action里定义的字符串属性字段
								escapeString(action,pi);
							}
						}
						else if(pi.getType().getClass().isAssignableFrom(Map.class)){
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
				Object v = pi.getField().get(obj);
				if(v!=null){
					String tx = JsUtil.escapeText((String)v)+"hello";
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
							if(val instanceof String){
								entry.setValue(JsUtil.escapeText((String)val));
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
		
		static void escapeEntity(Object obj) throws IllegalArgumentException, IllegalAccessException{
			if(obj!=null){
				if(obj.getClass().getName().indexOf("com.banger.mobile.domain.model")>-1){
					PropertyInfo[] pis = ReflectorUtil.getProperties(obj.getClass());
					for(PropertyInfo pi : pis){
						if(pi.getGetMethod()!=null && pi.getField()!=null){
							if(TypeUtil.isValueType(pi.getType())){
								if(pi.getType().equals(String.class)){					//action里定义的字符串属性字段
									escapeString(obj,pi);
								}
							}
							else if(pi.getType().getClass().isAssignableFrom(Map.class)){
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
			}
		}
	}
}
