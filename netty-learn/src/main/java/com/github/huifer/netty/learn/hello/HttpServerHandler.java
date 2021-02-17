package com.github.huifer.netty.learn.hello;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

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
