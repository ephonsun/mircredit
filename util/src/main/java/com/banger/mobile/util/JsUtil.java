package com.banger.mobile.util;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.banger.mobile.domain.annotation.EscapeClassFilter;
import com.banger.mobile.domain.annotation.EscapeFieldFilter;

public class JsUtil {
	
	public static void main(String[] args) throws Exception {
		 String str1 = JsUtil.escapeText("ab\\c\\<>\"'");
		 System.out.print(str1);
	}
	 
	/**
	 * 转义javascript字符串的特殊字符
	 * @param text
	 * @return
	 */
	public static String escapeText(String text){
		String str="";
		if(text!=null && text.length()>0){
			str = text.replaceAll("\\'","&#8242;").replaceAll("\\\"", "&#8243;").replaceAll("\\<","&lt;").replaceAll("\\>","&gt;");
		}
		return str;
	}
	
	public static String escapeJs(String text){
        String str="";
        if(text!=null && text.length()>0){
            str = text.replaceAll("\\\\","\\\\\\\\");
        }
        return str;
    }
	
	private static void escapeString(Object obj,PropertyInfo pi) throws IllegalArgumentException, IllegalAccessException{
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
	private static void escapeMap(Object obj,PropertyInfo pi) throws IllegalArgumentException, IllegalAccessException{
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
	public static void escapeList(Object obj) throws IllegalArgumentException, IllegalAccessException{
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

	public static void escapeEntity(Object obj) throws IllegalArgumentException, IllegalAccessException{
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
		}
	}
	
}
