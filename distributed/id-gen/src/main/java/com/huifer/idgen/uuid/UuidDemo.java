package com.huifer.idgen.uuid;

import com.fasterxml.uuid.Generators;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * @author: wang
 * @description: uuid
 */
@Slf4j
public class UuidDemo {

    public static void main(String[] args) {
        randomly();
        nameBased();
        timeBased01();
    }

    private static void timeBased01() {
        UUID uuid = Generators.timeBasedGenerator().generate();
        log.info("{}", uuid);
    }

    private static void nameBased() {
        UUID uuid = UUID.nameUUIDFromBytes("huifer".getBytes());
        log.info("{}", uuid);
    }

    private static void randomly() {
        UUID uuid = UUID.randomUUID();
        log.info("{}", uuid);
    }
}