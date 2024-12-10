package com.huifer.rmi.jdk;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * <p>Title : HelloServiceImpl </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-11
 */
public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {

    public HelloServiceImpl() throws RemoteException {
        super();
    }

    public String hello(String msg) throws RemoteException {
        return "msg:" + msg;
    }
}
