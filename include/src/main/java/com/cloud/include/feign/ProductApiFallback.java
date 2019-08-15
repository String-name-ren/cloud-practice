package com.cloud.include.feign;

import org.springframework.stereotype.Component;

/**
 * 描述
 *
 * @author: renpenghui
 * @date: 2019-08-14 15:27
 **/
@Component
public class ProductApiFallback implements ProductApi{

    @Override
    public String test() {
        return null;
    }

    @Override
    public String getProduct() {
        return "服务访问超时了，稍后在操作";
    }
}
