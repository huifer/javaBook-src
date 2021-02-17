# hello Netty
从本章开始笔者江河各位读者一起来学习 netty 相关技术，这一章我们先来简单认识一下 netty. 


## Netty 简介
Netty是一个NIO客户端服务器框架，可快速轻松地开发网络应用程序，例如协议服务器和客户端。它极大地简化和简化了网络编程，例如TCP和UDP套接字服务器。

“快速简便”并不意味着最终的应用程序将遭受可维护性或性能问题的困扰。Netty经过精心设计，结合了许多协议（例如FTP，SMTP，HTTP以及各种基于二进制和文本的旧式协议）的实施经验。结果，Netty成功地找到了一种无需妥协即可轻松实现开发，性能，稳定性和灵活性的方法。



## 第一个程序
在简单了解 netty 的信息后，我们来编写一个 Hello world 程序。在编写 netty 程序的时候他的复杂程度和我们以往编写 SpringMVC 的复杂程度要高得多. 下面我们来开始编写程序吧. 
编写程序的第一步: 选择一个 Netty 版本, 这里笔者选择的版本号是 `4.1.42.Final`

```xml
    <dependency>
      <groupId>io.netty</groupId>
      <artifactId>netty-all</artifactId>
      <version>4.1.42.Final</version>
    </dependency>
```

准备完成 Jar 依赖后我们来编写主要程序代码. 先来编写启动类



```java
public class HelloNetty {

    public static void main(String[] args) {
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap
                    = new ServerBootstrap()
                    .group(boosGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(
                            
                    );
            ChannelFuture sync = serverBootstrap.bind(1010).sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

}
```

上述代码是 netty 中最小的启动类。在这段代码中我们创建了两个 `EventLoopGroup` 一个是 `boosGroup` 另一个是 `workerGroup` ，这两个对象正如它们的名字一样，`workerGroup` 是一个用来处理请求的对象，`boosGroup` 是一个用来分配请求的对象，继续往下我们可以看到对象 `ServerBootstrap` 这个对象是一个服务启动类，在这个启动类中我们需要设置各类属性

1.  group：组别，传递的是 `EventLoopGroup` 对象
2. channel：传递的是通道对象
3. child handler：处理器，传递的是处理对象`ChannelHandler`

目前我们还没有传递 `ChannelHandler` 对象，这个处理对象是我们开发者自定义的，一般的情况下我们会编写一个继承 `SimpleChannelInboundHandler` 对象将其作为 `ChannelHandler`参数传递。

接下来我们就来编写这个`SimpleChannelInboundHandler` 



```JAVA
public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

   @Override
   protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
      // 读取客户端请求并返回响应
      ByteBuf context = Unpooled.copiedBuffer("hello netty", CharsetUtil.UTF_8);
      HttpVersion version = HttpVersion.HTTP_1_1;
      HttpResponseStatus status = HttpResponseStatus.OK;
      // 编写响应信息
      FullHttpResponse response = new DefaultFullHttpResponse(version, status, context);
      response.headers().set(HttpHeaderNames.CONTENT_TYPE, "test/plain");
      response.headers().set(HttpHeaderNames.CONTENT_LENGTH, context.readableBytes());
      // 最终返回
      channelHandlerContext.writeAndFlush(response);
   }
}
```

这里我们编写一个返回 `hello netty` 的相关代码，这段代码的主要目的是通过 `ChannelHandlerContext` 对象将一个 `response` 返回

