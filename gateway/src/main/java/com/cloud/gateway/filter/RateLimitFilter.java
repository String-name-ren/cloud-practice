package com.cloud.gateway.filter;


import com.cloud.gateway.exception.RateLimitException;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

/**
 * 描述
 *
 * @author: renpenghui
 * @date: 2019-08-13 09:27
 **/
@Component
public class RateLimitFilter extends ZuulFilter {

    //每秒中往令牌桶中放多少个令牌
   private static final RateLimiter RATE_LIMITER = RateLimiter.create(100);

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        //可以判断是否应该进行拦截
        //这里练习用，直接返回true
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //去取令牌，取不到，说明服务器忙
        double rate = RATE_LIMITER.getRate();
        System.out.println(rate);

        if(!RATE_LIMITER.tryAcquire()){
            throw new RateLimitException("服务器忙，请稍后访问！");
        }

        return null;
    }
}
