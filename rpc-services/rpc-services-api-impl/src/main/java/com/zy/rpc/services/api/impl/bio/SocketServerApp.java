package com.zy.rpc.services.api.impl.bio;

/**
 * @AUTHOR zhangy
 * 2020-03-31  22:17
 */
public class SocketServerApp {

    public static void main(String[] args) {
        // 启动socket
        ServerSocketProxy.startSocketServer(8080, new UserServiceImpl());
    }
}
