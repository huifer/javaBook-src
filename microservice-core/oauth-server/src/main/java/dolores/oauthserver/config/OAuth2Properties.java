package dolores.oauthserver.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "game.security.oauth2")
public class OAuth2Properties {

	private String jwtSigningKey = "game";
	private OAuth2ClientProperties[] clients = {};
}
