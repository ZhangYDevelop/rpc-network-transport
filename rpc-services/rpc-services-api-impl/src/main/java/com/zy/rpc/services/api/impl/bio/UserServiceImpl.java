package com.zy.rpc.services.api.impl.bio;

import com.zy.rpc.api.BaseRestlt;
import com.zy.rpc.api.ISayHello;
import com.zy.rpc.api.User;

/**
 * @AUTHOR zhangy
 * 2020-03-30  22:09
 */
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
