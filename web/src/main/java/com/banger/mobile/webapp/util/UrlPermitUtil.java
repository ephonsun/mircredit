/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-6-5
 */
package com.banger.mobile.webapp.util;

import com.banger.mobile.util.SpringContextUtil;
import com.banger.mobile.webapp.action.dept.DeptAction;

/**
 * @author cheny
 * @version $Id: UrlPermitUtil.java,v 0.1 2012-6-5 下午4:18:52 cheny Exp $
 */
public class UrlPermitUtil {

    public static Boolean hasPermission(String action) {
        try {
            DeptAction deptAction=(DeptAction)SpringContextUtil.getBean("deptAction");
            return deptAction.hasPermission(action);

        } catch (Exception e) {
            return false;
        }
    }
    
}
