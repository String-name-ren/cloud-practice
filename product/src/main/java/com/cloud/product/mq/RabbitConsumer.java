package com.cloud.product.mq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * 描述
 *
 * @author: renpenghui
 * @date: 2019-06-17 16:27
 **/
@Component
public class RabbitConsumer {


    @RabbitHandler
    @RabbitListener(
        bindings = @QueueBinding(
            value = @Queue(value = "order-queue",durable = "true"),
            exchange = @Exchange(name = "order-exchange",durable = "true",type = "topic"),
            key = "order.*"
        )
    )
    public void consumer(@Payload String message, @Headers Map<String,Object> headers, Channel channel) throws IOException {
        System.out.println("获取消息:"+ message);
//        Long DELIVERY_TAG = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
//        channel.basicAck(DELIVERY_TAG,false);
    }

}
