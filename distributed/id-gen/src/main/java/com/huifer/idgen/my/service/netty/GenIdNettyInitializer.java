package com.huifer.idgen.my.service.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class GenIdNettyInitializer extends ChannelInitializer<SocketChannel> {
    private GenIdNettyHandler handler = new GenIdNettyHandler();

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("codec", new HttpServerCodec());
        pipeline.addLast("handler", handler);
    }
}
