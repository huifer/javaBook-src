package com.huifer.zk.perception;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ZkClientDemoTest {

    ZkClientDemo zkClientDemo;

    @Before
    public void init() {
        zkClientDemo = new ZkClientDemo();
    }

    @Test
    public void add() {
        zkClientDemo.add("/", "fjl");
    }

}