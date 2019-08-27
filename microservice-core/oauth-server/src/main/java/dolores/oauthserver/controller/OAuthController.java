package dolores.oauthserver.controller;

import dolores.oauthserver.config.OAuth2Properties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.Principal;

@RestController
@Slf4j
public class OAuthController {
	@Autowired
	private OAuth2Properties oAuth2Properties;

	@GetMapping("/userJwt")
	public Object getCurrentUserJwt(Authentication authentication, HttpServletRequest request) throws UnsupportedEncodingException {
		log.info("【SecurityOauth2Application】 getCurrentUserJwt authentication={}", authentication);

		String header = request.getHeader("Authorization");
		String token = StringUtils.substringAfter(header, "bearer ");

		Claims claims = Jwts.parser().setSigningKey(oAuth2Properties.getJwtSigningKey().getBytes("UTF-8")).parseClaimsJws(token).getBody();
		String blog = (String) claims.get("blog");
		log.info("【SecurityOauth2Application】 getCurrentUser1 blog={}", blog);

		return authentication;
	}

	@GetMapping("/userRedis")
	public Object getCurrentUserRedis(Authentication authentication) {
		log.info("【SecurityOauth2Application】 getCurrentUserRedis authentication={}", authentication);


		return authentication;
	}

	@GetMapping("/user/me")
	public Principal user(Principal user) {
		return user;
	}
}
