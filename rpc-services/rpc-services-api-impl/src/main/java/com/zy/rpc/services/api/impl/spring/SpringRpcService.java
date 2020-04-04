package com.zy.rpc.services.api.impl.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @AUTHOR zhangy
 * 2020-04-03  21:28
 */
@SuppressWarnings("all")
@Component
public class SpringRpcService implements ApplicationContextAware , InitializingBean {

    private int prot;

    private Map<String, Object> serviceMap = new HashMap<>();

    private ExecutorService executorService = Executors.newCachedThreadPool();

    public SpringRpcService(int prot) {
        this.prot = prot;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ServerSocket serverSocket = null;

        serverSocket = new ServerSocket(prot);



        while (true) {
           Socket socket =  serverSocket.accept();
           executorService.execute(new SpringSocketHandler(socket, serviceMap ));
        }

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
       Map<String, Object> servicenams =  applicationContext.getBeansWithAnnotation(RpcService.class);
        for (Object value : servicenams.values()) {
           RpcService rpcService =  value.getClass().getAnnotation(RpcService.class);
           String serviceName = rpcService.value().getName();
           serviceMap.put(serviceName, value);

        }

    }
}
