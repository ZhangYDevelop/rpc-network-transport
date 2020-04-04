package com.zy.rpc.services.api.impl.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @AUTHOR zhangy
 * 2020-04-03  22:05
 */
public class SpringBootStrap {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        ( (AnnotationConfigApplicationContext ) applicationContext).start();
    }
}
