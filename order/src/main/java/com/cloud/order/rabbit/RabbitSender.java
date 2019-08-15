package com.cloud.order.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 描述
 *
 * @author: renpenghui
 * @date: 2019-06-13 15:38
 **/
@Component
public class RabbitSender implements RabbitTemplate.ConfirmCallback{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String message){
//        rabbitTemplate.convertAndSend(message);
//        amqpTemplate.convertAndSend("string",message);
        rabbitTemplate.setConfirmCallback(this);
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId("111");
        rabbitTemplate.convertAndSend("order-exchange","order.abcd",message,correlationData);
        System.out.println("消息发送成功！");
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        System.out.println("消息回调了："+correlationData.getId());
        System.out.println("消息回调成功："+b);
        System.out.println("消息回调了："+s);
    }

}
