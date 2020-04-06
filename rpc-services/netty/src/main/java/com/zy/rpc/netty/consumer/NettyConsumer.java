package com.zy.rpc.netty.consumer;

import com.zy.rpc.netty.api.IHelloService;
import com.zy.rpc.netty.common.BaseResult;
import com.zy.rpc.netty.consumer.Proxy.NettyProxy;

/**
 * @AUTHOR zhangy
 * 2020-04-06  22:41
 */
public class NettyConsumer {


    public static void main(String[] args) {
        IHelloService iHelloService = NettyProxy.createProxy(IHelloService.class, 8081, "localhost");
        BaseResult baseResult = iHelloService.sayHello(" hello world");

        System.out.println(baseResult.toString());
    }

}
