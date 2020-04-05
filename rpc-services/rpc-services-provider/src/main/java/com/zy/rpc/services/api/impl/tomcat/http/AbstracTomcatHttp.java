package com.zy.rpc.services.api.impl.tomcat.http;

/**
 * @AUTHOR zhangy
 * 2020-04-05  20:28
 */
public abstract class AbstracTomcatHttp {

    public final  void  service(TomcatRequest request, TomcatRespose respose) {

    }

    public void doGet(TomcatRequest request, TomcatRespose respose){}

    public void doPost(TomcatRequest request, TomcatRespose respose){}

}
