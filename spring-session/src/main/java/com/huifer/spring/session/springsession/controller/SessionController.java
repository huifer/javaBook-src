package com.huifer.spring.session.springsession.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title : SessionController </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-26
 */
@RequestMapping("/")
@RestController
public class SessionController {

    @GetMapping("/")
    public Object getSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object name = session.getAttribute("name");
        if (name == null) {
            session.setAttribute("name", "huifer");
            return name;
        } else {
            return name;
        }

    }
}
