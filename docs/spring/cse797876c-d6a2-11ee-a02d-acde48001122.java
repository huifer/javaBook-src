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
    ValidateCodeProperties code = new ValidateCodeProperties();

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
