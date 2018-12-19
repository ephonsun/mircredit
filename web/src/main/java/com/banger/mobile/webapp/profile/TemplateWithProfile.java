package com.banger.mobile.webapp.profile;

import java.io.IOException;
import java.io.Writer;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
 
public class TemplateWithProfile extends Template {
    private Template target = null;

    public TemplateWithProfile(Template t) {
        target = t;
    }

    @Override
    public void merge(Context context, Writer writer)
            throws ResourceNotFoundException, ParseErrorException,
            MethodInvocationException, IOException {
        if (!TimeProfiler.isProfileEnable()) {
            try {
                target.merge(context, writer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                mergeWithProfile(context, writer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void mergeWithProfile(Context context, Writer writer)
            throws Exception {
        TimeProfiler.beginTask("render:" + target.getName());
        try {
            target.merge(context, writer);
        } finally {
            TimeProfiler.endTask();
        }
    }
}
