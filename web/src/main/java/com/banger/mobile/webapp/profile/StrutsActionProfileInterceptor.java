package com.banger.mobile.webapp.profile;

import org.apache.commons.lang.ClassUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class StrutsActionProfileInterceptor implements Interceptor {
    private static final long serialVersionUID = -7426093006479528765L;

    public String intercept(ActionInvocation invocation) throws Exception {
        if (TimeProfiler.isProfileEnable()) {
            return invokeWithProfile(invocation);
        } else {
            return invocation.invoke();
        }
    }

    private String invokeWithProfile(ActionInvocation invocation) throws Exception {
        String actionName = "action";
        Object action = invocation.getAction();
        if (action != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(ClassUtils.getShortClassName(action.getClass()));
            sb.append('.');
            sb.append(invocation.getProxy().getMethod());
            actionName = sb.toString();
        }
        TimeProfiler.beginTask(actionName);
        try {
            return invocation.invoke();
        } finally {
            TimeProfiler.endTask();
        }
    }

    public void destroy() {
        // TODO Auto-generated method stub

    }

    public void init() {
        // TODO Auto-generated method stub

    }

}
