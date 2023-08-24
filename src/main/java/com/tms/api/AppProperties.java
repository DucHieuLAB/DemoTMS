package com.tms.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class AppProperties {
    private static final Logger log = LoggerFactory.getLogger(AppProperties.class);

    private static Properties prop;

    public static String getPropertyValue(String key) {
        return prop.getProperty(key);
    }

    static {
        try {
            prop = new Properties();
            File file = new File("App.properties");
            log.info("LINK FILE CONFIG: {}", file.getAbsolutePath());
            FileInputStream is = new FileInputStream(file);
            prop.load(is);
        } catch (FileNotFoundException e) {
            log.error("CAN NOT FOUND FILE App.properties");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
