package com.cloud.order.controller;

import com.cloud.include.feign.ProductApi;
import com.esotericsoftware.minlog.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 描述
 *
 * @author: renpenghui
 * @date: 2019-08-14 10:14
 **/
@RestController
@RequestMapping("hystrix")
//@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {

    @Autowired
    private ProductApi productApi;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("getProduct")
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "circuitBreaker.enabled", value = "true")
//            , @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10")
//            , @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
//            , @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
//    },fallbackMethod = "fallback")
//    @HystrixCommand(fallbackMethod = "fallback")
    public String getProduct(@RequestParam("num") int num) {

        if( num % 2 == 0 ){
            return "success";
        }
        Log.debug("我feign要来了。。。。。。");
        String product = productApi.getProduct();
//        String product = restTemplate.getForObject("http://product/getProduct", String.class, new HashMap<>());
        return product;
    }

    //调用服务发生异常，设置的回调函数
    public String fallback(@RequestParam("num") int num) {
        return "服务降级了，不可用～～";
    }

    //默认的类全局回调函数
    public String defaultFallback() {
        return "默认fallback：服务降级了，不可用～～";
    }

}
