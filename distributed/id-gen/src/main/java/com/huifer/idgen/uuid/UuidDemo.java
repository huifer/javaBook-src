package com.huifer.idgen.uuid;

import com.fasterxml.uuid.Generators;
import java.util.UUID;

/**
 * @author: wang
 * @description: uuid
 */
public class UuidDemo {

	public static void main(String[] args) {
		randomly();
		nameBased();
		timeBased01();
	}

	private static void timeBased01() {
		UUID uuid = Generators.timeBasedGenerator().generate();
	}

	private static void nameBased() {
		UUID uuid = UUID.nameUUIDFromBytes("huifer".getBytes());
		System.out.println(uuid);
	}

	private static void randomly() {
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid.toString());
	}
}