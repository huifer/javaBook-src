package com.example;

import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.Micronaut;

public class Application {

	public static void main(String[] args) {
		ApplicationContext run = Micronaut.run(Application.class, args);
	}
}
