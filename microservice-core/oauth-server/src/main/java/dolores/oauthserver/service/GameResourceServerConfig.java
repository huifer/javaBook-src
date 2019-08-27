package dolores.oauthserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableResourceServer
public class GameResourceServerConfig extends ResourceServerConfigurerAdapter {
	@Autowired
	private AuthenticationSuccessHandler handler;


	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.formLogin()
				.successHandler(handler).and()
				.authorizeRequests().anyRequest().authenticated().and()
				.csrf().disable();
	}
}
