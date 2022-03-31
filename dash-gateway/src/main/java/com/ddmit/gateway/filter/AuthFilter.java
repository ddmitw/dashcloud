package com.ddmit.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.ddmit.common.core.constant.HttpStatusConstants;
import com.ddmit.common.core.domain.R;
import com.ddmit.common.redis.service.RedisService;
import com.ddmit.common.security.service.TokenService;
import com.ddmit.gateway.config.IgnoreWhiteConfig;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger((AuthFilter.class));

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private IgnoreWhiteConfig ignoreWhite;

    @Override
    public String filterType() {
        // 过滤器拦截位置
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        // 过滤器执行顺序，数值越小，优先级越高
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        // 对请求是否需要执行run方法进行判断，返回false不执行run方法
        return true;
    }

    @Override
    public Object run() {
        // 过滤器的具体业务逻辑

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        String url = request.getRequestURI();
        // TODO spinrg cloud config 配置读取不到
        // 跳过不需要验证的路径
        if (ignoreWhite.getWhites().contains(url)) {
            return response;
        }

        // 获取token字符串
        String token = tokenService.getToken(request);
        if (StringUtils.isBlank(token)) {
            ctx.setSendZuulResponse(false);
            logger.info("请求无效，token信息为空");
            ctx.setResponseBody(JSON.toJSONString(R.unAuth("令牌不能为空")));
            return response;
        }

        // 根据token获取用户信息
        String userInfoString = redisService.getCacheObject(token);
        if (StringUtils.isBlank(userInfoString)) {
            ctx.setSendZuulResponse(false);
            logger.info("请求无效，登录状态已过期");
            ctx.setResponseStatusCode(HttpStatusConstants.UNAUTHORIZED);
            ctx.setResponseBody(JSON.toJSONString(R.unAuth("登录状态已过期")));
            return response;
        }

        return response;
    }

}
