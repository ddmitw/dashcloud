package com.ddmit.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

@Component
public class AuthFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger((AuthFilter.class));

    @Override
    public String filterType() {
        // 过滤器拦截位置
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        // 过滤器执行顺序
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        // 对请求是否需要执行run方法进行判断，返回false不执行run方法
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        logger.info("Zuul登录请求拦截……");
        System.out.println("请求拦截……");
        return null;
    }
}
