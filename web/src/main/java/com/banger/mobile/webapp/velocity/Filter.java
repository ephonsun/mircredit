package com.banger.mobile.webapp.velocity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Filter {
    
 
    public static String  filterString(String message){ 
        String regEx = "���ֹ�|����|sex|��˵�|";
          
        Pattern pattern = Pattern.compile(regEx); 
        Matcher matcher = pattern.matcher(message); 
        return matcher.replaceAll("*").trim();
       
    }
    public static void main(String[] args){
        String   str   =   "�й������˵�";   
        System.out.println(filterString(str));
    }

    
}
