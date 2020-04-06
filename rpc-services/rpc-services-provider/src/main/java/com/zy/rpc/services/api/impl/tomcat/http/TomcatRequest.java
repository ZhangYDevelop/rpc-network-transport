package com.zy.rpc.services.api.impl.tomcat.http;

import java.io.IOException;
import java.io.InputStream;

/**
 * @AUTHOR zhangy
 * 2020-04-05  20:28
 */
public class TomcatRequest {

    private  String url ;

    public TomcatRequest(InputStream is ) {

        byte[] buffer = new byte[1024];

        int len = 0;

        try {
            if ((len = is.read(buffer)) > 0) {
                String content = new String(buffer, 0, len);
                System.out.println(content);

                // 处理URL 参数
                String url = "";
                setUrl(url);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  String getUrl () {
        return  this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
