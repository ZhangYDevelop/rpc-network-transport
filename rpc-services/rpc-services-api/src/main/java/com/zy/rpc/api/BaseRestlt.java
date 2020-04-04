package com.zy.rpc.api;

import java.io.Serializable;

/**
 * @AUTHOR zhangy
 * 2020-04-01  21:50
 */
public class BaseRestlt implements Serializable {

    private  Boolean success;
    private  Object data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseRestlt{" +
                "success=" + success +
                ", data=" + data +
                '}';
    }
}
