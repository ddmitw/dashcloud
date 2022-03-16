package com.ddmit.auth.controller;

import com.ddmit.auth.form.LoginBody;
import com.ddmit.auth.service.ILoginService;
import com.ddmit.common.core.domain.R;
import com.ddmit.common.core.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Winbert
 * @Date 2022/3/16 14:57
 */
@RestController
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @PostMapping("login")
    public R<?> login(@RequestBody LoginBody form) {
        // 用户登录
        LoginUser userInfo = loginService.login(form.getUsername(), form.getPassword());
        // 获取登录token
        return R.ok(loginService.createToken(userInfo));
    }
}
