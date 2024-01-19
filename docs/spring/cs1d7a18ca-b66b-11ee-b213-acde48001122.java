package com.github.huifer.netty.learn.hello;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioServerSocketChannel.class).handler(new ChannelInitializer(){
            @Override
            protected void initChannel(Channel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8));
                pipeline.addLast("stringEncoder", new StringEncoder(CharsetUtil.UTF_8));
                pipeline.addLast("handler", new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg)
                            throws Exception {
                        super.channelRead(ctx, msg);
                    }
                });
            }
        })
        ;

        for (int i = 0; i < 3; i++) {
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 1010).sync();
            channelFuture.channel().writeAndFlush(
                    "hello - server " + Thread.currentThread().getName() + " ----> "
                            + i);
            channelFuture.channel().closeFuture().sync();
        }
    }

}
