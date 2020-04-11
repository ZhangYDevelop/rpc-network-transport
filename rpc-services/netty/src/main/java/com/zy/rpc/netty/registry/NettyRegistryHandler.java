package com.zy.rpc.netty.registry;

import com.zy.rpc.netty.common.BaseResult;
import com.zy.rpc.netty.protocol.InvokeProcotol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @AUTHOR zhangy
 * 2020-04-06  16:58
 * 注册中心启动后逻辑处理
 */
public class NettyRegistryHandler extends ChannelInboundHandlerAdapter {

    private List<String> classNameList = new ArrayList<String>();

    private Map<String , Object> serviceInstance =  new HashMap();

    public NettyRegistryHandler() {

        scanPacaage("com.zy.rpc.netty.provider");

        doRegistery();

    }

    /**
     *  服务注册
     */
    private void doRegistery() {

        for (String s : classNameList) {
            try {
                Class<?> clazz = Class.forName(s);
                Class<?> i = clazz.getInterfaces()[0];
                String name = i.getName();
                serviceInstance.put(name, clazz.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     *  扫描包
     * @param s
     */
    private void scanPacaage(String s) {

        URL url = this.getClass().getClassLoader().getResource(s.replaceAll("\\.", "/"));

        File file = new File(url.getFile());

        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                String packageName = s + "." + f.getName();
                scanPacaage(packageName);
            } else {
                String className = s + "." + f.getName().replaceAll(".class" , "") ;
                classNameList.add(className);
            }
        }
    }

    /**
     * 有客户端连上时回调
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        InvokeProcotol invokeProcotol = (InvokeProcotol)msg;
        Object result ;
        if (serviceInstance.containsKey(invokeProcotol.getClassName())) {
            Object service = serviceInstance.get(invokeProcotol.getClassName());
            Method method = service.getClass().getMethod(invokeProcotol.getMethodName(), invokeProcotol.getParams());
            result = method.invoke(service, invokeProcotol.getValues());
            ctx.writeAndFlush(result);
            ctx.close(); // ctx 处理完需要关闭，客户端才会收到数据
        } else {
            BaseResult baseResult = new BaseResult();
            baseResult.setSuccess(false);
            baseResult.setMeaaage("服务调用失败");
            ctx.writeAndFlush(baseResult);
            ctx.close(); // ctx 处理完需要关闭，客户端才会收到数据
        }
    }

    /**
     * 连接发生异常回调
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    }
}
