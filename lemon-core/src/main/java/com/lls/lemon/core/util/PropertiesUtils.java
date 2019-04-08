package com.lls.lemon.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/************************************
 * PropertiesUtils
 * @author liliangshan
 * @date 2019-03-27
 ************************************/
public class PropertiesUtils {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

    private static Properties properties = new Properties();

    static {
        try {
            properties.load(PropertiesUtils.class.getClassLoader().getResourceAsStream("lemon_sso.properties"));
        } catch (IOException e) {
            logger.warn("load lemon_sso.properties exception:" + e.getMessage(), e);
        }
    }

    public static Properties getProperties() {
        return properties;
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

}
