package com.huifer.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-11-17
 */
@ConfigurationProperties(prefix = "hf.security")
public class SecurityProperties {
    BrowserProperties browser = new BrowserProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
