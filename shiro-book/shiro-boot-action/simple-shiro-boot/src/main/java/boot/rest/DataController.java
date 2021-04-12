package boot.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

  @GetMapping("/authc")
  public String authc() {
    return "authc";
  }
  @GetMapping("/anon")
  public String anon() {
    return "anon";
  }
}
