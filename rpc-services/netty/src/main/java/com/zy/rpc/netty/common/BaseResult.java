package com.zy.rpc.netty.common;

import java.io.Serializable;

/**
 * @AUTHOR zhangy
 * 2020-04-06  16:28
 */
public class BaseResult implements Serializable {

    private boolean success;

    private Object resultData;

    private String Meaaage;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getResultData() {
        return resultData;
    }

    public void setResultData(Object resultData) {
        this.resultData = resultData;
    }

    public String getMeaaage() {
        return Meaaage;
    }

    public void setMeaaage(String meaaage) {
        Meaaage = meaaage;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "success=" + success +
                ", resultData=" + resultData +
                ", Meaaage='" + Meaaage + '\'' +
                '}';
    }
}
