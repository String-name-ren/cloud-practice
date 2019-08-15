package com.cloud.config.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;

//@Component
//@WebFilter(filterName = "actuatorFilter", urlPatterns = "/actuator/*")
public class ActuatorFilter implements Filter {
    @Override
    public void init(FilterConfig config)  {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;

        String uri = httpServletRequest.getRequestURI();

        //只过滤/actuator/bus-refresh请求
        if (!uri.equals("/actuator/bus-refresh")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //使用HttpServletRequest包装原始请求达到修改post请求中body内容的目的
        CustomerRequestWrapper requestWrapper = new CustomerRequestWrapper(httpServletRequest);

        filterChain.doFilter(requestWrapper, servletResponse);
    }

    @Override
    public void destroy() {}

    class CustomerRequestWrapper extends HttpServletRequestWrapper{
        public CustomerRequestWrapper(HttpServletRequest request) {
            super(request);
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            byte[] bytes = new byte[0];
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

            return new ServletInputStream() {
                @Override
                public boolean isFinished() {
                    return byteArrayInputStream.read() == -1 ? true:false;
                }

                @Override
                public boolean isReady() {
                    return false;
                }

                @Override
                public void setReadListener(ReadListener readListener) {

                }

                @Override
                public int read() throws IOException {
                    return byteArrayInputStream.read();
                }
            };
        }
    }
}


