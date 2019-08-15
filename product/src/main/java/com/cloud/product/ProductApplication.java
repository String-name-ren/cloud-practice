package com.cloud.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages={"com.cloud"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.cloud.include.feign")
//@EnableRabbit
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

    @Bean
    @LoadBalanced //使用此注解可以使用应用名字直接调用，如http://order/get
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
