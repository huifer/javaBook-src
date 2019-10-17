# rmi

- 接口继承`java.rmi.Remote`
- 实现类继承`java.rmi.server.UnicastRemoteObject`

## 简单demo

```java
public interface HelloService extends Remote {

    /**
     * @return hello
     */
    String hello(String msg) throws RemoteException;

}
```

```java
public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {

    public HelloServiceImpl() throws RemoteException {
        super();
    }

    public String hello(String msg) throws RemoteException {
        return "msg:" + msg;
    }
}
```

```java
public class Server {

    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        // 注册中心?
        Registry registry = LocateRegistry.createRegistry(1099);
        Naming.rebind("rmi://192.168.1.215/hello", service);
        System.out.println("服务启动成功");

    }

}
```

```java
public class Client {

    public static void main(String[] args) throws Exception {
        HelloService helloService = (HelloService) Naming.lookup("rmi://localhost/hello");
        String msg = helloService.hello("msg");
        System.out.println(msg);
    }

}
```

----

