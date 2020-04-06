package com.zy.rpc.netty.protocol;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @AUTHOR zhangy
 * 2020-04-06  16:32
 */
public class InvokeProcotol implements Serializable {

    private String className;

    private String methodName ;

    private Class<?>[] params;

    private Object[] values;


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

    public Class<?>[] getParams() {
        return params;
    }

    public void setParams(Class<?>[] params) {
        this.params = params;
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "InvokeProcotol{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", params=" + params +
                ", values=" + Arrays.toString(values) +
                '}';
    }
}
