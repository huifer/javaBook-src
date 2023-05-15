package com.huifer.rmi.jdk;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * <p>Title : HelloService </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-11
 */
public interface HelloService extends Remote {

    /**
     * @return hello
     */
    String hello(String msg) throws RemoteException;

}
