/**
 * created since Apr 1, 2009
 */
package com.banger.mobile.webapp.velocity;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.sitemesh.VelocityPageFilter;


import com.banger.mobile.webapp.profile.TimeProfiler;
import com.opensymphony.module.sitemesh.Decorator;
import com.opensymphony.module.sitemesh.Page;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author zhangxiang
 */
public class PlatformVelocityPageFilter extends VelocityPageFilter {
    public void init(FilterConfig filterConfig) {
        super.init(filterConfig);
        if (filterConfig.getInitParameter("customEncoding") != null) {
            super.setCustomEncoding(filterConfig.getInitParameter("customEncoding"));
        } else {
            super.setCustomEncoding("utf-8");
        }
    }

    @Override
    protected Page parsePage(HttpServletRequest request, HttpServletResponse response,
                             FilterChain chain) throws IOException, ServletException {
        if (!TimeProfiler.isProfileEnable()) {
            return super.parsePage(request, response, chain);
        }

        TimeProfiler.beginTask("sitemesh parsePage");
        try {
            return super.parsePage(request, response, chain);
        } finally {
            TimeProfiler.endTask();
        }
    }

    @Override
    protected void applyDecorator(Page page, Decorator decorator, HttpServletRequest req,
                                  HttpServletResponse res, ServletContext servletContext,
                                  ActionContext ctx) throws ServletException, IOException {
        if (!TimeProfiler.isProfileEnable()) {
            super.applyDecorator(page, decorator, req, res, servletContext, ctx);
            return;
        }

        TimeProfiler.beginTask("sitemesh applyDecorator");
        try {
            super.applyDecorator(page, decorator, req, res, servletContext, ctx);
        } finally {
            TimeProfiler.endTask();
        }
    }

}
