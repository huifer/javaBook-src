package com.huifer.netty.hello;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * <p>Title : MyHandler </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-24
 */
public class MyHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("客户端接收消息" + msg.toString());
    }

}
