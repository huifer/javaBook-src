package com.huifer.rmi.rpc;

/**
 * <p>Title : HelloServiceImpl </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-11
 */
public class HelloServiceImpl implements HelloService {

    public String hello(String msg) {
        return "msg:" + msg;
    }
}
