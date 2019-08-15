package com.cloud.include.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 描述
 *
 * @author: renpenghui
 * @date: 2019-06-06 11:55
 **/
@FeignClient(name = "product",fallback = ProductApiFallback.class)
public interface ProductApi {

    @GetMapping("test")
    String test();

    @GetMapping("getProduct")
    String getProduct();
}
