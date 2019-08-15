package com.cloud.product.controller;

import com.cloud.include.feign.OrderApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 描述
 *
 * @author: renpenghui
 * @date: 2019-05-30 17:21
 **/
@RestController
@Slf4j
public class ClientController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private OrderApi orderApi;

    @GetMapping("test")
    public String test(){
        return "this is a test hahahahah ";
    }

    @GetMapping("getProduct")
    public String getProduct() throws Exception{
        log.debug("feign来调用了");
        Thread.sleep(2000);
        return "我是一个电风扇！";
    }

    @GetMapping("get")
    public String getServerInfo(){
        ServiceInstance order = loadBalancerClient.choose("ORDER");
        System.out.println("=================");

        System.out.println(order.getHost());
        System.out.println(order.getPort());
        System.out.println("======================");


        return orderApi.getMsg();

//        restTemplate.getForObject() get方法
//        restTemplate.postForObject()   post方法
//        URI uri = restTemplate.postForLocation("", ""); 返回一个资源URI
//        restTemplate.put();  put请求
//        restTemplate.delete();  delete请求
//        return restTemplate.getForEntity("http://order/get",String.class).getBody();
    }
}
