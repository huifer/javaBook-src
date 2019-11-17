package com.huifer.security.browser;

import com.huifer.security.browser.authentication.MyAuthenticationFailHandler;
import com.huifer.security.browser.authentication.MyAuthenticationSuccessHandler;
import com.huifer.security.properties.SecurityProperties;
import com.huifer.security.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-11-17
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService myUserDetailsService;
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailHandler myAuthenticationFailHandler;
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter v = new ValidateCodeFilter();
        v.setAuthenticationFailureHandler(myAuthenticationFailHandler);

        http
                .addFilterBefore(v, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                // 存放的地址别忘记再加一层resources!!!
                .loginPage("/auth/require")
                // 自定义的请求修改，原生地址 org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.UsernamePasswordAuthenticationFilter
                .loginProcessingUrl("/auth/form")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailHandler)
                .and()
                .rememberMe()
                .tokenRepository(this.persistentTokenRepository())
                .tokenValiditySeconds(this.securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(myUserDetailsService)
                .and()
                .authorizeRequests()
                // http://localhost:8060/login.html 没有设置
                .antMatchers(
                        "/auth/require", securityProperties.getBrowser().getLoginPage(),
                        "/code/image"
                ).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
        ;

    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        // 自动创建表
//        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
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
