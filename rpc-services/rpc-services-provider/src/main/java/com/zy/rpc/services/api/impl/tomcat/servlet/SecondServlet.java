package com.zy.rpc.services.api.impl.tomcat.servlet;

import com.zy.rpc.services.api.impl.tomcat.http.AbstracTomcatHttp;
import com.zy.rpc.services.api.impl.tomcat.http.TomcatRequest;
import com.zy.rpc.services.api.impl.tomcat.http.TomcatRespose;

/**
 * @AUTHOR zhangy
 * 2020-04-05  20:31
 */
public class SecondServlet extends AbstracTomcatHttp {

    @Override
    public void doGet(TomcatRequest request, TomcatRespose respose) {
        super.doGet(request, respose);
    }

    @Override
    public void doPost(TomcatRequest request, TomcatRespose respose) {
        super.doPost(request, respose);
    }


}
