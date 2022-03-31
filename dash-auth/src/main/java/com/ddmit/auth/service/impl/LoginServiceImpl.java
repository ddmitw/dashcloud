package com.ddmit.auth.service.impl;

import com.ddmit.auth.service.ILoginService;
import com.ddmit.common.core.model.LoginUser;
import org.springframework.stereotype.Service;

import java.util.Date;

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
}
