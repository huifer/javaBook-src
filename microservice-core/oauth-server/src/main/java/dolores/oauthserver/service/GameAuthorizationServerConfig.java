package dolores.oauthserver.service;

import dolores.oauthserver.config.OAuth2ClientProperties;
import dolores.oauthserver.config.OAuth2Properties;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableAuthorizationServer
public class GameAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private OAuth2Properties oAuth2Properties;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	private TokenStore tokenStore;

	@Autowired(required = false)
	@Qualifier("jwtAccessTokenConverter")
	private JwtAccessTokenConverter jwtAccessTokenConverter;

	@Autowired(required = false)
	@Qualifier("jwtTokenEnhancer")
	private TokenEnhancer jwtTokenEnhancer;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()");
		security.checkTokenAccess("isAuthenticated()");
	}

	public GameAuthorizationServerConfig() {
		super();
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		InMemoryClientDetailsServiceBuilder builder = clients.inMemory();

		if (ArrayUtils.isNotEmpty(oAuth2Properties.getClients())) {
			for (OAuth2ClientProperties config : oAuth2Properties.getClients()) {
				builder.withClient(config.getClientId())
						.secret(passwordEncoder.encode(config.getClientSecret()))
						.accessTokenValiditySeconds(config.getAccessTokenValiditySeconds())
						.refreshTokenValiditySeconds(60 * 60 * 24 * 15)
						.authorizedGrantTypes("refresh_token", "password", "authorization_code")//OAuth2支持的验证模式
						.redirectUris("http://www.ixx.com")
						.scopes("all");
			}
		}
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
				.tokenStore(tokenStore)
				.authenticationManager(authenticationManager)
				.userDetailsService(userDetailsService);

		if (jwtAccessTokenConverter != null && jwtTokenEnhancer != null) {
			TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
			List<TokenEnhancer> enhancers = new ArrayList<>();
			enhancers.add(jwtTokenEnhancer);
			enhancers.add(jwtAccessTokenConverter);
			tokenEnhancerChain.setTokenEnhancers(enhancers);
			endpoints
					.tokenEnhancer(tokenEnhancerChain)
					.accessTokenConverter(jwtAccessTokenConverter);
		}
	}
}
