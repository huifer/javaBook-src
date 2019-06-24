package com.huifer.netty.hello;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * <p>Title : NettyServer </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-24
 */
public class NettyServer {

    public static final String IP = "127.0.0.1";
    public static final int PORT = 9999;
    /**
     * 最大线程数
     */
    public static final int BIG_GROUP_SIZE = 2 * Runtime.getRuntime().availableProcessors();

    public static final int BIG_THREAD_SIZE = 100;
    public static final EventLoopGroup WORK_GROUP = new NioEventLoopGroup();
    private static final EventLoopGroup BOOS_GROUP = new NioEventLoopGroup();

    public static void start() throws Exception {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        // 设置boos group 和 work group
        serverBootstrap
                .group(BOOS_GROUP, WORK_GROUP)

                // 设置channel
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<Channel>() {
                    // 设置 channel handler 处理 ,childHandler 针对连接进行处理
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        ChannelPipeline pipeline = channel.pipeline();

                        // 编解码器添加
                        pipeline.addLast(
                                new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4));
                        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                        pipeline.addLast(new TcpHandler());
                    }
                })
                .childOption(ChannelOption.ALLOCATOR, UnpooledByteBufAllocator.DEFAULT);
        ChannelFuture channelFuture = serverBootstrap.bind(IP, PORT).sync();
        // 关闭时的监听器
        channelFuture.channel().closeFuture().sync();
        System.out.println(" 服务启动 ");
    }


    protected static void shutdown() {
        WORK_GROUP.shutdownGracefully();
        BOOS_GROUP.shutdownGracefully();
    }

    public static void main(String[] args) throws Exception {
        NettyServer.start();
    }


}
