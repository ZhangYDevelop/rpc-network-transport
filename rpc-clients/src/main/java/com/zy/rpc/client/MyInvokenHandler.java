package com.zy.rpc.client;

import com.zy.rpc.api.RequestParams;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @AUTHOR zhangy
 * 2020-03-31  20:20
 */
@SuppressWarnings("all")
public class MyInvokenHandler implements InvocationHandler {
    private  String host;
    private  int port;

    public MyInvokenHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {

            RequestParams requestParams = new RequestParams();
            requestParams.setMethodName(method.getName());
            requestParams.setClassName(method.getDeclaringClass().getName());
            requestParams.setMethodParam(args);
            return new RpcNetRransport(host, port).send(requestParams);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  null;
    }
}
