package com.banger.mobile.util;

public class DebugUtil {
	static ThreadLocal<StringBuilder> log;
	
	static{
		log = new ThreadLocal<StringBuilder>();
	}
	
	public static void write(String context){
		if(log.get()==null)log.set(new StringBuilder());
		String txt = DateUtil.getNowTimeString()+":"+context+"\r\n";
		log.get().append(txt);
	}
	
	public static void save(String fullFileName){
		if(log.get()!=null)
		{
			FileUtil.writeFile(fullFileName,log.get().toString(),false);
			log.set(null);
		}
	}
}
