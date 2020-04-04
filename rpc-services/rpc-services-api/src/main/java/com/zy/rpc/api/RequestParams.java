package com.zy.rpc.api;

import java.io.Serializable;

/**
 * @AUTHOR zhangy
 * 2020-03-30  22:06
 */
public class RequestParams implements Serializable {

    private  String methodName ;

    private Object[] methodParam;

    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }


    public Object[] getMethodParam() {
        return methodParam;
    }

    public void setMethodParam(Object[] methodParam) {
        this.methodParam = methodParam;
    }
}
