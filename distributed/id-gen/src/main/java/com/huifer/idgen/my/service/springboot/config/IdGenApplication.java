package com.huifer.idgen.my.service.springboot.config;

import com.huifer.idgen.my.service.GenIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 1
 * @description:
 */
@SpringBootApplication
@RestController
public class IdGenApplication {

	@Autowired
	GenIdService genIdService;


	public static void main(String[] args) {
		SpringApplication.run(IdGenApplication.class, args);
	}

	@GetMapping("/")
	public long createId() {
		return genIdService.genId();
	}

}