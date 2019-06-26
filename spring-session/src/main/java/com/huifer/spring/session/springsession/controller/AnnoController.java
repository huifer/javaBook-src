package com.huifer.spring.session.springsession.controller;

import static com.huifer.spring.session.springsession.util.ResultUtil.error;
import static com.huifer.spring.session.springsession.util.ResultUtil.success;

import com.huifer.spring.session.springsession.entity.enums.ResultEnum;
import com.huifer.spring.session.springsession.util.ShrioUtil;
import com.huifer.spring.session.springsession.vo.ResultVo;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title : AnnoController </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-26
 */
@RestController
@RequestMapping("/anno")
@Slf4j
public class AnnoController {

    @PostMapping("/login")
    public ResultVo login(@RequestBody Map<String, String> map) {
        try {

            Subject subject = ShrioUtil.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(map.get("account"),
                    map.get("password"));
            subject.login(token);
        } catch (Exception e) {
            log.error("{}" + e.getLocalizedMessage());
        }
        return success();
    }

    @GetMapping("/logout")
    public ResultVo logout() {
        ShrioUtil.logout();
        return success();
    }

    @GetMapping("/notLogin")
    public ResultVo notLogin() {
        return error(ResultEnum.NOT_LOGIN.getCode(), ResultEnum.NOT_LOGIN.getMessage());
    }
}
