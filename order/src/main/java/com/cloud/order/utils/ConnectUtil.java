package com.cloud.order.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 描述
 *
 * @author: renpenghui
 * @date: 2019-06-10 15:20
 **/
public class ConnectUtil {

    public static Connection getConnection() throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("testMQ");
        factory.setUsername("renph");
        factory.setPassword("123456");
        return factory.newConnection();
    }

}
