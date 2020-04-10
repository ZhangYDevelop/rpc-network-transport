package com.zy.rpc.netty.provider;

import com.zy.rpc.netty.api.IHelloService;
import com.zy.rpc.netty.common.BaseResult;

/**
 * @AUTHOR zhangy
 * 2020-04-06  16:34
 */
public class IHelloServiceImpl implements IHelloService {

    public BaseResult sayHello(String message) {
        BaseResult baseResult = new BaseResult();
        baseResult.setSuccess(true);
        baseResult.setMeaaage("服务端收到消息");
        baseResult.setResultData("服务端返回消息");
        return  baseResult;
    }
}
