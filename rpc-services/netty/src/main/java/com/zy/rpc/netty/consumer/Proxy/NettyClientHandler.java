package com.zy.rpc.netty.consumer.Proxy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @AUTHOR zhangy
 * 2020-04-06  22:50
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    private Object resultObject;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        this.resultObject = msg;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    public Object getResponse() {
        return  this.resultObject;
    }
}
