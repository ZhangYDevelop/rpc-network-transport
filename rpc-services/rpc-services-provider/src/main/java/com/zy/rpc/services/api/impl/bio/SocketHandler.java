package com.zy.rpc.services.api.impl.bio;

import com.zy.rpc.api.RequestParams;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @AUTHOR zhangy
 * 2020-03-31  20:02
 */
@SuppressWarnings("all")
public class SocketHandler implements Runnable {

    private final Socket socket;
    private final Object userService;

    public SocketHandler(Socket socket, Object objectService) {
        this.socket = socket;
        this.userService = objectService;
    }

    @Override
    public void run() {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            RequestParams requestParams = (RequestParams) objectInputStream.readObject();
            Object object = invoke(requestParams);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != objectInputStream) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != objectOutputStream) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Object invoke(RequestParams requestParams) {

        Object[] args = requestParams.getMethodParam();

        Class<?>[] types = new Class[args.length];

        for (int i = 0; i < args.length; i++) {
            types[i] = args[i].getClass();
        }

        try {
            Class clazz = Class.forName(requestParams.getClassName());
            try {
                Method method = clazz.getMethod(requestParams.getMethodName(), types);
                try {
                    Object object = method.invoke(userService, args);
                    return object;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
