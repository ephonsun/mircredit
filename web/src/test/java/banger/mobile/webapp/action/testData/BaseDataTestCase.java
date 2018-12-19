package banger.mobile.webapp.action.testData;

import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;


import com.banger.mobile.constants.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

/**
 * Base class for running Struts 2 Action tests.
 * @author mraible
 */
public abstract class BaseDataTestCase extends AbstractTransactionalDataSourceSpringContextTests {

    protected final Log             log = LogFactory.getLog(getClass());
    protected static ResourceBundle rb  = null;

    protected String[] getConfigLocations() {
        setAutowireMode(AUTOWIRE_BY_NAME);
        return new String[] { 
                    "applicationContext-resources.xml",
                    "classpath*:/applicationContext-dao.xml",
                    "classpath*:/applicationContext-service.xml"
                };
    }

    public BaseDataTestCase() {
        String className = this.getClass().getName();

        try {
            rb = ResourceBundle.getBundle(className);
        } catch (MissingResourceException mre) {
            //log.warn("No resource bundle found for: " + className);
        }
    }
}
