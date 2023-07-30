package com.huifer.netty.hello;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * <p>Title : TcpHandler </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-24
 */
public class TcpHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel active。。。。。。。。。。。。。");
    }

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    	// 通道的内容是什么
		System.out.println("服务端接到: " + msg.toString());
		ctx.channel().writeAndFlush("服务端返回信息: 你好");
		ctx.close();
	}

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("服务端异常" + cause.getLocalizedMessage());

    }
}
