package com.huifer.netty.first;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TestServer {
	public static void main(String[] args) throws InterruptedException {

		// NioEventLoopGroup 简单理解是一个死循环
		// bossGroup 指令发出者，接受链接处理
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		// 指令处理者，进行后续处理
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {

			// 服务端编写
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap
					.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new ServerInitializer())
			;
			ChannelFuture sync = serverBootstrap.bind(9090).sync();


			sync.channel().closeFuture().sync();
		}
		finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
