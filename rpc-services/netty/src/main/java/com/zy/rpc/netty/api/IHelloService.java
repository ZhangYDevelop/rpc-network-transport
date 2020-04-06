package com.zy.rpc.netty.api;

import com.zy.rpc.netty.common.BaseResult;

/**
 * @AUTHOR zhangy
 * 2020-04-06  16:27
 */
public interface IHelloService {

    BaseResult sayHello(String  message);
}
