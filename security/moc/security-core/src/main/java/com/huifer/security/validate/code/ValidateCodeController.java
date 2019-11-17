package com.huifer.security.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-11-17
 */
@RestController
public class ValidateCodeController {
    public static final String session_key = "session_key_image_code";

    @Autowired
    ValidateCodeGenerator imageCodeGenerator;
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();


    @GetMapping("/code/image")
    public void createCode(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        ImageCode imageCode = imageCodeGenerator.generate(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request), session_key, imageCode.getCode());
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }


}
