package example.micronaut.ctr;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

@Controller
public class HelloController {
	@Get("/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String index() {
		return "Hello World";
	}
}
