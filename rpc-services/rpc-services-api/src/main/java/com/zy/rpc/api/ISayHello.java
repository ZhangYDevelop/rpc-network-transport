package com.zy.rpc.api;

/**
 * @AUTHOR zhangy
 * 2020-03-30  22:05
 */
public interface ISayHello {

    BaseRestlt   getUser(String  userId ) ;

    BaseRestlt  saveuser(User  user);

    BaseRestlt sayHello(String message);
}
