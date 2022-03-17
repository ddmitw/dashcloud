package com.ddmit.auth.service;

import com.ddmit.common.core.model.LoginUser;

import java.util.Map;

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

    /**
     * 登录成功后创建token
     *
     * @param loginUser 登录实体
     * @return token信息
     */
    Map<String, Object> createToken(LoginUser loginUser);

    /**
     * token验证
     *
     * @param token 认证信息
     * @return 认证结果
     */
    boolean verifyToken(String token);

}
