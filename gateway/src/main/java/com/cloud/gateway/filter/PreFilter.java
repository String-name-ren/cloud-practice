package com.cloud.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 描述 前置拦截器一般会做校验，权限拦截、限流等
 *
 * @author: renpenghui
 * @date: 2019-08-12 17:59
 **/
@Component
public class PreFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        //返回true拦截
        //返回false不拦截
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //执行拦截的代码

        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        //如果token是空，标识没有权限访问
//        String token = request.getParameter("token");
//        if(StringUtils.isEmpty(token)){
//            context.setSendZuulResponse(false);
//            context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
//        }
        return null;
    }
}
