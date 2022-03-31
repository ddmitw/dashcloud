package com.ddmit.auth.service;

import com.ddmit.common.core.model.LoginUser;

/**
 * @author Winbert
 * @Date 2022/3/16 15:08
 */
public interface ILoginService {

    /**
     * 实现用户登录方法
     *
     * @param username 用户名
     * @param password 密码
     * @return 返回用户登陆实体
     */
    LoginUser login(String username, String password);

}
