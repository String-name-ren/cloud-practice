package com.cloud.order.controller;


import com.cloud.include.feign.ProductApi;
import com.cloud.order.rabbit.RabbitSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 描述
 *
 * @author: renpenghui
 * @date: 2019-05-30 16:53
 **/
@RestController
@RefreshScope
public class ServerController {

    @Value("${env}")
    private String env;

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private Registration registration;

    @Autowired
    private ProductApi productApi;


    @Autowired
    @Lazy
    private RabbitSender rabbitSender;

    @GetMapping("getEnv")
    public String getEnv(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies){
            if(cookie.getName().equals("JSESSIONID")){
                System.out.println(cookie.getValue());
            }
        }
        return env;
    }

    @GetMapping("test")
    public String test(){
        rabbitSender.send("我有一只小毛驴，我从来也不骑！");
        return productApi.test();
    }




    @GetMapping("get")
    public String getServerInfo(){
        List<String> services = client.getServices();
        for(String str : services){
            System.out.println("str:"+str);
        }
        System.out.println("host:"+ registration.getHost()+"，port:"+ registration.getPort());
        return registration.getHost()+":"+registration.getPort();
    }

}
