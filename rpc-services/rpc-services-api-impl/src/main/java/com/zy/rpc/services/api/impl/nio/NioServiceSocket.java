package com.zy.rpc.services.api.impl.nio;

import sun.misc.JavaNioAccess;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @AUTHOR zhangy
 * 2020-04-04  20:42
 * nio 同步非阻塞
 */
public class NioServiceSocket {

    private ByteBuffer buffer = ByteBuffer.allocate(1024);

    private Selector selector = null;

    private int port;

    public NioServiceSocket(int port) {
        this.port = port;

        try {
            ServerSocketChannel service = ServerSocketChannel.open();

            service.bind(new InetSocketAddress(this.port));  // 绑定IP和端口 默认localhost

            service.configureBlocking(false);

            selector = Selector.open(); // 初始化轮询器

            service.register(selector, SelectionKey.OP_ACCEPT);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void listen() {

        System.out.println("NioServer Listen on : " + this.port);

        while (true) {  // 主线程轮询（单线程）体现同步
            try {
                selector.select();

                Set<SelectionKey> keys = selector.selectedKeys();

                Iterator<SelectionKey> its = keys.iterator();

                while (its.hasNext()) {
                    SelectionKey key = its.next();

                    its.remove();

                    process(key); // 非阻塞体现，有请求就处理  每一个可以代表一种状态，可读  可写 等
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 处理key
     *
     * @param key
     */
    private void process(SelectionKey key) {
        try {
            if (key.isAcceptable()) {
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();


                SocketChannel socketChannel = serverSocketChannel.accept();

                socketChannel.configureBlocking(false);

                socketChannel.register(selector, SelectionKey.OP_READ);

            }

            if (key.isReadable()) { // key  可读
                SocketChannel channel = (SocketChannel) key.channel();
                buffer.clear(); //晴空缓冲区
                int len = channel.read(buffer);
                if (len > 0) {
                    buffer.flip();
                    String string = new String(buffer.array(), 0, len);
                    System.out.println("客户端消息可读， 消息内容：" + string);
                    key = channel.register(selector, SelectionKey.OP_WRITE);
                    key.attach(string);

                }
            }

            if (key.isWritable()) { // key  可写
                SocketChannel channel = (SocketChannel) key.channel();

                buffer.flip();
                String content = (String) key.attachment();
                System.out.println("客户端消息可写， 消息内容：" + content);
                channel.write(ByteBuffer.wrap(("服务段收到数据:" + content).getBytes()));
                channel.close();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new NioServiceSocket(8081).listen();
    }


}
