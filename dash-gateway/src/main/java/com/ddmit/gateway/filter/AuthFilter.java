package com.ddmit.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.ddmit.auth.service.ILoginService;
import com.ddmit.common.core.constant.HttpStatusConstants;
import com.ddmit.common.core.constant.TokenConstants;
import com.ddmit.common.core.domain.R;
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
    private ILoginService loginService;


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
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        // 过滤器的具体业务逻辑
        String tokenHeader = request.getHeader(TokenConstants.TOKEN_HEADER);
        if (StringUtils.isEmpty(tokenHeader) || !loginService.verifyToken(StringUtils.substringAfter(tokenHeader, TokenConstants.TOKEN_PREFIX))) {
            logger.info("无token信息或token失效……");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(HttpStatusConstants.UNAUTHORIZED);
            ctx.setResponseBody(JSON.toJSONString(R.unAuth("凭证已过期，请重新登录")));
        }
        return response;
    }

}
