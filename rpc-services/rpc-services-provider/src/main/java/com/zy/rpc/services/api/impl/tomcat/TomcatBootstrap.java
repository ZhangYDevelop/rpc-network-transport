package com.zy.rpc.services.api.impl.tomcat;

import com.zy.rpc.services.api.impl.tomcat.http.AbstracTomcatHttp;
import com.zy.rpc.services.api.impl.tomcat.http.TomcatRequest;
import com.zy.rpc.services.api.impl.tomcat.http.TomcatRespose;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @AUTHOR zhangy
 * 2020-04-05  20:26
 */
public class TomcatBootstrap {

    private int port;

    private ServerSocket serverSocket;

    private Map<String, AbstracTomcatHttp> tomcatHttpMap = new HashMap<>();


    public TomcatBootstrap(int port) {
        this.port = port;
    }

    public static void main(String[] args) {

        try {
            new TomcatBootstrap(8080).start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void start() throws Exception {

        initWebParam();

        initServer();
    }

    private void initServer() {

        try {
            serverSocket = new ServerSocket(port);

            while (true) {
                Socket socket = serverSocket.accept(); // 阻获取连接

                processHandler(socket);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initWebParam() throws Exception {
        Properties properties = new Properties();

        String webPath = this.getClass().getResource("/").getPath();

        FileInputStream fis = new FileInputStream(webPath + "web.properties");

        properties.load(fis);

        for (Object o : properties.keySet()) {
            String key = o.toString();
            if (key.endsWith("url")) {

                String servername = key.replace("\\.url", "");

                String url = properties.getProperty(key);

                String className = properties.getProperty(servername + ".className");

                AbstracTomcatHttp abstracTomcatHttp = (AbstracTomcatHttp) Class.forName(className).newInstance();

                tomcatHttpMap.put(url, abstracTomcatHttp);

            }
        }
    }

    private void processHandler(Socket socket) {

        InputStream is = null;

        OutputStream os = null;

        try {
            is = socket.getInputStream();
            os = socket.getOutputStream();

            TomcatRequest tomcatRequest = new TomcatRequest(is);
            TomcatRespose tomcatRespose = new TomcatRespose(os);

            String url =  tomcatRequest.getUrl();

            if (!StringUtils.isEmpty(url)) {
                tomcatHttpMap.get(url).service(tomcatRequest, tomcatRespose);
            } else {
                os = socket.getOutputStream();
                os.write("404 Not Found".getBytes());
                os.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != socket) {
                    socket.close();
                }
                if (null != is) {
                    is.close();
                }
                if (null != os) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
