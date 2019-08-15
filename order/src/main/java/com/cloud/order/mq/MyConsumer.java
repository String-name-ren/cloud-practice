package com.cloud.order.mq;

import com.cloud.order.utils.ConnectUtil;
import com.rabbitmq.client.*;


import java.io.IOException;

/**
 * 描述
 *
 * @author: renpenghui
 * @date: 2019-06-10 15:54
 **/
public class MyConsumer {

    public static void main(String[] args) throws Exception{
        Connection connection = ConnectUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1);//能者多劳模式
        //声明队列
//        channel.queueDeclare(MqConstants.BASIC_PUBLISH,false,false,false,null);
//        channel.exchangeDeclare("tongzhi", "direct");
        channel.exchangeDeclare("movie", "topic");

        //自4.0+ 版本后无法再使用QueueingConsumer，而官方推荐
        // 使用DefaultConsumer


//        String queueName = channel.queueDeclare().getQueue();
//        channel.queueBind(queueName, "tongzhi", "n");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, "movie", "American.*.13");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [消费者] Received '" + message + "'");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                finally {
//                    // 这里手动地确定
//                    channel.basicAck(envelope.getDeliveryTag(), false);
//                }
            }
        };
        //监听队列，当b为true时，为自动提交（只要消息从队列中获取，无论消费者获取到消息后是否成功消息，都认为是消息已经成功消费），
        // 当b为false时，为手动提交（消费者从队列中获取消息后，服务器会将该消息标记为不可用状态，等待消费者的反馈，
        // 如果消费者一直没有反馈，那么该消息将一直处于不可用状态。
        //如果选用自动确认,在消费者拿走消息执行过程中出现宕机时,消息可能就会丢失！！）
        //使用channel.basicAck(envelope.getDeliveryTag(),false);进行消息确认
        channel.basicConsume(queueName,true,consumer);
//        channel.close();
//        connection.close();
    }


    private static void doWork(String task) {
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException _ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

}
