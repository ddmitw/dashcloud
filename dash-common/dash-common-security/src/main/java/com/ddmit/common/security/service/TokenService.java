package com.ddmit.common.security.service;

import com.ddmit.common.core.constant.CacheConstants;
import com.ddmit.common.core.constant.TokenConstants;
import com.ddmit.common.core.model.LoginUser;
import com.ddmit.common.redis.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Winbert
 * @Date 2022/3/18 15:05
 */
public class TokenService {

    @Autowired
    private RedisService redisService;

    /**
     * 登录成功后创建token
     *
     * @param loginUser 登录实体
     * @return token信息
     */
    public Map<String, Object> createToken(LoginUser loginUser) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userInfo", loginUser);

        redisService.setCacheObject(CacheConstants.LOGIN_TOKEN_KEY + loginUser.getUsername(), map);
        return map;
    }

    /**
     * 获取token字符串，不带前缀
     *
     * @param request 请求信息
     * @return 认证token字符串
     */
    public String getToken(HttpServletRequest request) {
        String token = request.getHeader(TokenConstants.TOKEN_HEADER);
        if (StringUtils.isNotEmpty(token) && token.startsWith(TokenConstants.TOKEN_PREFIX)) {
            token = token.replace(TokenConstants.TOKEN_PREFIX, "");
        }
        return token;
    }
}
