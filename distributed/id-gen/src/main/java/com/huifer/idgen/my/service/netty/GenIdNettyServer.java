package com.huifer.idgen.my.service.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GenIdNettyServer {
	private final int port;

	public GenIdNettyServer(int port) {
		this.port = port;
	}

	public void run() throws Exception {
		NioEventLoopGroup bossGroup = new NioEventLoopGroup();
		NioEventLoopGroup workGoup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.option(ChannelOption.SO_BACKLOG, 1024);
			b.group(bossGroup, workGoup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new GenIdNettyInitializer());

			Channel ch = b.bind(port).sync().channel();

			if (log.isDebugEnabled())
				log.debug("VestaRestNettyServer is started.");

			ch.closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workGoup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		int port = 10086;
		new GenIdNettyServer(port).run();
	}
}
