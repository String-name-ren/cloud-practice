package com.cloud.include.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="order",path = "/")
public interface OrderApi {

    @GetMapping("get")
    public String getMsg();
}
