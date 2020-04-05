package com.zy.rpc.services.api.impl.spring;

import com.zy.rpc.api.BaseRestlt;
import com.zy.rpc.api.ISayHello;
import com.zy.rpc.api.User;
import org.springframework.stereotype.Component;

/**
 * @AUTHOR zhangy
 * 2020-04-03  21:35
 */
@Component
@RpcService(ISayHello.class)
@SuppressWarnings("all")
public class UserServiceImpl implements ISayHello {
    @Override
    public BaseRestlt getUser(String userId) {
        return null;
    }

    @Override
    public BaseRestlt saveuser(User user) {
        return null;
    }

    @Override
    public BaseRestlt sayHello(String message) {
        System.out.println("来自客户端的消息：" + message);
        BaseRestlt baseRestlt = new BaseRestlt();
        baseRestlt.setData("success");
        baseRestlt.setSuccess(true);
        return baseRestlt;
    }
}
