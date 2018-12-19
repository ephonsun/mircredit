/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :Administrator
 * Create Date:2012-10-9
 */
package com.banger.mobile.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @version $Id: CallTypeConstants.java,v 0.1 2012-10-9 下午12:59:32 Administrator Exp $
 */
public class CallTypeConstants {
    public final static Map<String, String> callTypeMap = new HashMap<String, String>();
    static {
        callTypeMap.put("1", "呼入");
        callTypeMap.put("2", "呼出");
        callTypeMap.put("3", "未接");
        callTypeMap.put("4", "座谈记录");
        callTypeMap.put("5", "拜访记录");
        callTypeMap.put("6", "未读留言");
        callTypeMap.put("7", "已发短信");
        callTypeMap.put("8", "已收短信");
        callTypeMap.put("9", "彩信");
    }
}
