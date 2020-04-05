package com.zy.rpc.services.api.impl.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @AUTHOR zhangy
 * 2020-04-03  21:36
 */
@SuppressWarnings("all")
@Configuration
@ComponentScan(basePackages = "com.zy.rpc.services.api.impl.spring")
public class SpringConfig {

    @Bean("springRpcService") // 此处有坑，Spring的获取Bean  value值必须和方法名一样，要么不赋值
    public SpringRpcService springRpcService() {
        return  new SpringRpcService(8080);
    }

}
