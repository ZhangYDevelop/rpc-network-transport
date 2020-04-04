package com.zy.rpc.client;

import java.lang.reflect.Proxy;

/**
 * @AUTHOR zhangy
 * 2020-04-01  20:55
 */
public class RpcClientProxy {

    public    <T> T proxy(final Class<T> interfaces , final String host , final  int port){
        return (T) Proxy.newProxyInstance( interfaces.getClassLoader(), new Class[]{interfaces},new MyInvokenHandler(host, port));
    }
}
