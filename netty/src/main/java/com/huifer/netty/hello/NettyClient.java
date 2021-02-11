package com.huifer.netty.hello;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * <p>Title : NettyClient </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-24
 */
public class NettyClient implements Runnable {

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new NettyClient(), "thread-name >>>> " + i).start();
        }
    }

    @Override
    public void run() {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup);
            bootstrap.channel(NioSocketChannel.class)
                    .option(ChannelOption.ALLOCATOR, UnpooledByteBufAllocator.DEFAULT)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 120000)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_REUSEADDR, true)


                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("frameDecoder",
                                    new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4));
                            pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                            pipeline.addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8));
                            pipeline.addLast("stringEncoder", new StringEncoder(CharsetUtil.UTF_8));
                            pipeline.addLast("myHandler", new MyHandler());
                        }
                    });

            for (int i = 0; i < 3; i++) {
                ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9999).sync();
                channelFuture.channel().writeAndFlush(
                        "hello - server " + Thread.currentThread().getName() + " ----> "
                                + i);
                channelFuture.channel().closeFuture().sync();
            }



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
