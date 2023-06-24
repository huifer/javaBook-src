package com.huifer.bigfile.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Title : UpLoadFileConfig </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-27
 */
@Configuration
public class UpLoadFileConfig {

    public static String uploadPath;


    @Value("${upload.path}")
    public void setUploadPath(String path) {
        UpLoadFileConfig.uploadPath = path;
    }

}
