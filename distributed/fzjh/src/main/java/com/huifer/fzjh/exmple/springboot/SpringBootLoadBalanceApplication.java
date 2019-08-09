package com.huifer.fzjh.exmple.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootLoadBalanceApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootLoadBalanceApplication.class, args);
	}


	@GetMapping("/")
	public String s() {

		return "";
	}
}
