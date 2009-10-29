package com.momentumsystems.eeoc.cts.util.system;

/**
 * Created by IntelliJ IDEA.
 * User: jspyker
 * Date: Mar 23, 2004
 * Time: 5:54:58 PM
 * To change this template use Options | File Templates.
 */

import com.momentumsystems.configuration.ConfigurationException;
import com.momentumsystems.util.configuration.Configuration;
import com.momentumsystems.util.configuration.ConfigurationTool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Feb 3, 2004
 * Time: 3:50:06 PM
 * To change this template use Options | File Templates.
 */
public final class ConfigUtils
{

    private static Log log = LogFactory.getLog(ConfigUtils.class);

    private ConfigUtils()
    {
    }

    public static Configuration getConfiguration() throws ConfigurationException
    {
        Configuration configuration = ConfigurationTool.getConfiguration();
        Configuration appConfig = configuration.getConfiguration("Application");
        Configuration nvsConfig = appConfig.getConfiguration("CTS");

        return nvsConfig;
    }

    public static Object getProperty(String key) throws ConfigurationException
    {
        Configuration config = getConfiguration();
        return config.getAttribute(key);
    }

    public static String getString(String key) throws ConfigurationException
    {
        Configuration config = getConfiguration();
        Object attribute = config.getAttribute(key);
        if (attribute != null)
            return (String) attribute;
        else
            return null;
    }

    public static Integer getInteger(String key) throws ConfigurationException
    {
        Configuration config = getConfiguration();
        Object attribute = config.getAttribute(key);
        if (attribute != null)
        {
            if (attribute instanceof Integer)
                return (Integer) attribute;
            else if (attribute instanceof String)
                return Integer.valueOf( attribute.toString() );
        }
        return null;
    }

}
