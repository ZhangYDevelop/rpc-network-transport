package com.zy.rpc.netty.consumer.Proxy;

import com.zy.rpc.netty.protocol.InvokeProcotol;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @AUTHOR zhangy
 * 2020-04-06  22:42
 */
@SuppressWarnings("all")
public class NettyProxy {


    public static <T> T createProxy(Class<?> clazz, int port, String  host) {

        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new NettyInvokeHandler(port, host));

    }

    public static class NettyInvokeHandler implements InvocationHandler {

        private int port;

        private String host;

        public NettyInvokeHandler(int port, String host) {
            this.port = port;
            this.host = host;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            InvokeProcotol procotol = new InvokeProcotol();
            procotol.setClassName(method.getDeclaringClass().getName());
            procotol.setValues(args);
            procotol.setParams(method.getParameterTypes());
            procotol.setMethodName(method.getName());

            return createRemote(port, host, procotol);
        }

        private Object createRemote(int port, String host, InvokeProcotol procotol) {
            Bootstrap clientServer = null;
            EventLoopGroup group = null;
            try {
                group = new NioEventLoopGroup();

                clientServer = new Bootstrap();

                final NettyClientHandler clientHandler = new NettyClientHandler();

                clientServer.group(group).channel(NioSocketChannel.class)
                        .option(ChannelOption.TCP_NODELAY, true)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel socketChannel) throws Exception {
                                ChannelPipeline cp = socketChannel.pipeline();
                                cp.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4));
                                cp.addLast(new LengthFieldPrepender(4));
                                cp.addLast("decoder", new ObjectEncoder());
                                cp.addLast("encoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
                                cp.addLast(clientHandler);
                            }
                        });

                ChannelFuture future = clientServer.connect(host, port).sync();
                future.channel().writeAndFlush(procotol).sync();
                future.channel().closeFuture().sync();

                return clientHandler.getResponse();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                group.shutdownGracefully();
            }
            return  null;
        }


    }
}
