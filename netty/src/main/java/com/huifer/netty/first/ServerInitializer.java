package com.huifer.netty.first;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.SocketChannelConfig;
import io.netty.handler.codec.http.HttpServerCodec;

public class ServerInitializer extends ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel socketChannel) throws Exception {
		SocketChannelConfig config = socketChannel.config();
		ChannelPipeline pipeline = socketChannel.pipeline();

		// 添加一个 httpServerCodec
		pipeline.addLast("httpServerCodec", new HttpServerCodec());
		pipeline.addLast("testHttpServerHandler", new TestHttpServerHandler());
	}
}
