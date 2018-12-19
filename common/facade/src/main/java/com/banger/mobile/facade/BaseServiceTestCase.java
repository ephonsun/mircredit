package com.banger.mobile.facade;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

public abstract class BaseServiceTestCase extends AbstractTransactionalDataSourceSpringContextTests {
    //~ Static fields/initializers =============================================

    protected final Log             log = LogFactory.getLog(getClass());
    protected static ResourceBundle rb  = null;

    protected String[] getConfigLocations() {
        setAutowireMode(AUTOWIRE_BY_NAME);
        return new String[] { "/applicationContext-resources.xml",
                "classpath:/applicationContext-dao.xml",
                "classpath*:/applicationContext-service.xml",
                "classpath*:/**/applicationContext.xml" };
        // classpath*:/**/applicationContext.xml has to be used since this file does not
        // exist in AppFuse, but may exist in projects that depend on it
    }

    //~ Constructors ===========================================================

    public BaseServiceTestCase() {
        // Since a ResourceBundle is not required for each class, just
        // do a simple check to see if one exists
        String className = this.getClass().getName();

        try {
            rb = ResourceBundle.getBundle(className);
        } catch (MissingResourceException mre) {
            //log.warn("No resource bundle found for: " + className);
        }
    }

    //~ Methods ================================================================
}