package com.zy.rpc.services.api.impl.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @AUTHOR zhangy
 * 2020-03-30  22:13
 */
public class ServerSocketProxy {

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void startSocketServer(int port, UserServiceImpl service) {
        ServerSocket serverSocket = null;
        UserServiceImpl userService = service;

        try {
             serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket  = serverSocket.accept();
                executorService.execute(new SocketHandler(socket, userService));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != serverSocket) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
