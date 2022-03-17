package com.ddmit.auth.service.impl;

import com.ddmit.auth.service.ILoginService;
import com.ddmit.auth.util.JwtUtil;
import com.ddmit.common.core.constant.TokenConstants;
import com.ddmit.common.core.model.LoginUser;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Winbert
 * @Date 2022/3/16 15:18
 */
@Service
public class LoginServiceImpl implements ILoginService {
    @Override
    public LoginUser login(String username, String password) {
        // TODO 用户登录信息校验

        LoginUser loginUser = new LoginUser();
        loginUser.setUserid(1L);
        loginUser.setUsername(username);
        loginUser.setLoginTime(new Date().getTime());
        return loginUser;
    }

    @Override
    public Map<String, Object> createToken(LoginUser loginUser) {
        String token = JwtUtil.createToken(loginUser.getUserid(), loginUser.getUsername());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("access_token", TokenConstants.TOKEN_PREFIX + token);
        map.put("userInfo", loginUser);
        return map;
    }

    @Override
    public boolean verifyToken(String token) {
        return JwtUtil.verifyToken(token);
    }
}
