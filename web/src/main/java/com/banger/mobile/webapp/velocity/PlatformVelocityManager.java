package com.banger.mobile.webapp.velocity;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.VelocityStrutsUtil;
import org.apache.struts2.views.util.ContextUtil;
import org.apache.struts2.views.velocity.StrutsVelocityContext;
import org.apache.struts2.views.velocity.VelocityManager;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.context.ChainedContext;
import org.springframework.aop.framework.ProxyFactory;

import com.banger.mobile.util.TypeUtil;
import com.banger.mobile.webapp.action.BaseAction;
import com.opensymphony.xwork2.util.ValueStack;

public class PlatformVelocityManager extends VelocityManager {

    private static final Logger LOG = Logger.getLogger(PlatformVelocityManager.class);

    public Context createContext(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
    	
    	Enumeration<?> attrs = req.getAttributeNames();
    	while (attrs.hasMoreElements()){
    		 Object attr = attrs.nextElement();
    		 if(attr instanceof String){
    			 String attrName = (String)attr;
    			 if(attrName.equals("currentAction")){
    				 Object action = req.getAttribute("currentAction");
    				 if(action instanceof BaseAction){
			    		BaseAction ba = (BaseAction)action;
			    		ba.escapeText();
			    	}
    			 }
    			 else{
    				 Object other = req.getAttribute(attrName);
    				 if(other!=null){
    					 if(!TypeUtil.isValue(other)){
    						 BaseAction.escapeText(other);
    					 }
    				 }
    			 }
    		 }
    	}
    	
        VelocityContext[] chainedContexts = prepareChainedContexts(req, res, stack.getContext());
        StrutsVelocityContext context = new StrutsVelocityContext(chainedContexts, stack);
        Map standardMap = ContextUtil.getStandardContext(stack, req, res);
        for (Iterator iterator = standardMap.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry entry = (Map.Entry) iterator.next();
            context.put((String) entry.getKey(), entry.getValue());
        }
        
        /*
        VelocityEngine engine = getVelocityEngine();
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(engine);
        pf.setExposeProxy(true);
        pf.addAdvice(new VelocityAdvice());
        VelocityEngine proxy = (VelocityEngine)pf.getProxy();
        */
        
        context.put(STRUTS, new VelocityStrutsUtil(getVelocityEngine(), context, stack, req, res));

        ServletContext ctx = null;
        try {
            ctx = ServletActionContext.getServletContext();
        } catch (NullPointerException npe) {
            // in case this was used outside the lifecycle of struts servlet
            LOG.debug("internal toolbox context ignored");
        }

        if (toolboxManager != null && ctx != null) {

            // here is the new constructor :
            ChainedContext chained = new ChainedContext(context, getVelocityEngine(), req, res, ctx);

            chained.setToolbox(toolboxManager.getToolbox(chained));
            return chained;
        } else {
            return context;
        }

    }
}
