package com.cloud.order.mq;

import com.cloud.order.utils.ConnectUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 描述
 *
 * @author: renpenghui
 * @date: 2019-06-10 15:34
 **/
public class MyProducer {


    public static void main(String[] args) throws Exception {
        Connection connection = ConnectUtil.getConnection();
        Channel channel = connection.createChannel();
        /**
         * 第一个参数队列名称
         * 第二个参数是否持久化
         */
//        channel.queueDeclare(MqConstants.BASIC_PUBLISH, false, false, false, null);
//        channel.exchangeDeclare("tongzhi", "direct");
        channel.exchangeDeclare("movie", "topic");
        String message = "this is a MQ test!";
//        for (int i = 0; i < 20; i++) {
//            message = message + i;
//            channel.basicPublish("", MqConstants.BASIC_PUBLISH, null, message.getBytes());
//            Thread.sleep(1000);
//        }
//        channel.basicPublish( "notice", "", null, "Hello China".getBytes());
//        for (int i = 0; i < 10; i++) {
//            String routingKey = "n"; // normal
//            if (i % 3 == 0) {
//                routingKey = "s"; // secret
//            }
//            if (i % 3 == 1) {
//                routingKey = "x"; // secret
//            }
//            channel.basicPublish("tongzhi", routingKey, null, String.valueOf(i).getBytes());
//        }


        channel.basicPublish("movie", "American.action.13", null, "The Bourne Ultimatum".getBytes());
        channel.basicPublish("movie", "American.comedy.R", null, "The Big Lebowski".getBytes());
        channel.basicPublish("movie", "American.romantic.13", null, "Titanic".getBytes());

        channel.basicPublish("movie", "Chinese.action.13", null, "卧虎藏龙".getBytes());
        channel.basicPublish("movie", "Chinese.comedy.13", null, "大话西游".getBytes());
        channel.basicPublish("movie", "Chinese.romantic.13", null, "梁山伯与祝英台".getBytes());


//        for (int i = 0; i < 10; i++) {
//            channel.basicPublish("", MqConstants.BASIC_PUBLISH, MessageProperties.PERSISTENT_TEXT_PLAIN, (message+i).getBytes());
//            System.out.println("生产者 send ：" + message+i);
//        }


        channel.close();
        connection.close();

    }

}
