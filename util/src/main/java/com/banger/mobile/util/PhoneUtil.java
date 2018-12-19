package com.banger.mobile.util;

public class PhoneUtil {
	/**
	 * 生成号码规则
	 * @param phone1
	 * @param phone2
	 * @return
	 */
	public static String getPhoneRule(String phone1,String phone2)
	{
		String p1 = getPhoneRule(phone1);
		String p2 = getPhoneRule(phone2);
		if(!"".equals(p1) && !"".equals(p2))return p1+","+p2;
		else return p1+p2;
	}
	
	public static String getPhoneRule(String phone)
	{
		if(phone!=null && phone.length()==11)
		{
			String endNum = phone.substring(7);
			int a=Integer.parseInt(endNum.charAt(0)+"");
			int b=Integer.parseInt(endNum.charAt(1)+"");
			int c=Integer.parseInt(endNum.charAt(2)+"");
			int d=Integer.parseInt(endNum.charAt(3)+"");
			
			if(a==b && b == c && c == d)
			{
				return "AAAA";
			}
			else if(a==b && c==d)
			{
				return "AABB";
			}
			else if(a==c && b==d)
			{
				return "ABAB";
			}
			else if(a==b && b==c)
			{
				return "AAAB";
			}
			else if(a==d && b==c)
			{
				return "ABBA";
			}
			else if(d==c+1 && c==b+1 && b==a+1)
			{
				return "ABCD";
			}
		}
		return "";
	}
}
