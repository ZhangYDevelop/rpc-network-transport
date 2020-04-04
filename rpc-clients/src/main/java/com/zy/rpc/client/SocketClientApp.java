package com.zy.rpc.client;

import com.zy.rpc.api.BaseRestlt;
import com.zy.rpc.api.ISayHello;

/**
 * @AUTHOR zhangy
 * 2020-03-31  20:13
 */
public class SocketClientApp {

    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy = new RpcClientProxy();
        ISayHello iSayHello = rpcClientProxy.proxy(ISayHello.class, "localhost", 8080);
        BaseRestlt baseRestlt =  iSayHello.sayHello("hello");
        System.out.println(baseRestlt.toString());

    }
}
