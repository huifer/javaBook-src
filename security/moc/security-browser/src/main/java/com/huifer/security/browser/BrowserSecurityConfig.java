package com.huifer.security.browser;

import com.huifer.security.browser.authentication.MyAuthenticationFailHandler;
import com.huifer.security.browser.authentication.MyAuthenticationSuccessHandler;
import com.huifer.security.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-11-17
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;


    @Autowired
    private MyAuthenticationFailHandler myAuthenticationFailHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                // 存放的地址别忘记再加一层resources!!!
                .loginPage("/auth/require")
                // 自定义的请求修改，原生地址 org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.UsernamePasswordAuthenticationFilter
                .loginProcessingUrl("/auth/form")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailHandler)

                .and()
                .authorizeRequests()
                // http://localhost:8060/login.html 没有设置
                .antMatchers("/auth/require", securityProperties.getBrowser().getLoginPage()).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
        ;

    }

    /**
     * 加密设置
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
