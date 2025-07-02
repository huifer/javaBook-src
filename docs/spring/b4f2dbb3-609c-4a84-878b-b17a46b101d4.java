package example.micronaut.ctr;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.netty.DefaultHttpClient;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class HelloControllerTests {
	@Inject
	@Client("/")
	DefaultHttpClient client;

	@Test
	void index() {
		HttpRequest<String> request = HttpRequest.GET("/hello"); // ③
		String body = client.toBlocking().retrieve(request);

		assertNotNull(body);
		assertEquals("Hello World", body);
	}
}
