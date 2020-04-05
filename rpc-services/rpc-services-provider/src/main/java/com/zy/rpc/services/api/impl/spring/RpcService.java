package com.zy.rpc.services.api.impl.spring;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @AUTHOR zhangy
 * 2020-04-03  21:29
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface RpcService {

    Class<?> value();
}
