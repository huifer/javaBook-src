package com.huifer.security.validate.code;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-11-17
 */
public interface ValidateCodeGenerator {
    ImageCode generate(HttpServletRequest request);
}
