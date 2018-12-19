package com.banger.mobile.webapp.profile;

import org.apache.struts2.dispatcher.VelocityResult;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * 
 * @author fish
 * 
 */
public class VelocityProfileResult extends VelocityResult {

    private static final long serialVersionUID = 1L;

    @Override
    protected Template getTemplate(ValueStack stack, VelocityEngine velocity,
                                   ActionInvocation invocation, String location, String encoding)
                                                                                                 throws Exception {
        Template origin = super.getTemplate(stack, velocity, invocation, location, encoding);
        return new TemplateWithProfile(origin);
    }

}
