package com.example;

import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

@MicronautTest
class RedisTest {

	@Inject
	EmbeddedApplication<?> application;
	@Inject
	StatefulRedisConnection<String, String> connection;

	@Test
	void testItWorks() {
		RedisCommands<String, String> sync = connection.sync();

		sync.set("fac", "fac");
	}

}
