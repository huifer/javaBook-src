package com.huifer.utils.utils;


import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class PropertyUtil {


    private static Properties props;


    synchronized static private void loadProps(String path) {
        log.info("开始加载properties文件内容.......");
        props = new Properties();
        InputStream in = null;
        try {
            in = PropertyUtil.class.getClassLoader().getResourceAsStream(path);

            props.load(in);
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            log.error("出现IOException");
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                log.error("zkhq.properties文件流关闭出现异常");
            }
        }
        log.info("加载properties文件内容完成...........");
    }

    public static String getProperty(String key, String path) {
        if (null == props) {
            loadProps(path);
        }
        return props.getProperty(key);
    }

    public static String getProperty(String path, String key, String defaultValue) {
        if (null == props) {
            loadProps(path);
        }
        return props.getProperty(key, defaultValue);
    }

}