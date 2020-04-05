package com.zy.rpc.client.nioclient;

import java.io.*;
import java.net.Socket;

/**
 * @AUTHOR zhangy
 * 2020-03-31  20:13
 */
public class NioSocketClient {

    public static void main(String[] args) {
        Socket socket = null;
        OutputStream os = null;
        try {
            socket = new Socket("localhost", 8081);
            os = socket.getOutputStream();
            os.write("客户端发送消息22233333".getBytes());
            os.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
