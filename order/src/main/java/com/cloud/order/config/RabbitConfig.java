package com.cloud.order.config;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

/**
 * 描述
 *
 * @author: renpenghui
 * @date: 2019-06-13 16:13
 **/
public class RabbitConfig {

    /**
     * 定义队列名
     */
    private final static String STRING = "string";


    /**
     * 定义string队列
     * @return
     */
    @Bean
    public Queue string() {
        return new Queue(STRING);
    }
}
