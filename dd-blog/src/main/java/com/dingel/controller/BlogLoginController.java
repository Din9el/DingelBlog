package com.dingel.controller;


import com.dingel.annotation.SystemLog;
import com.dingel.domain.ResponseResult;
import com.dingel.domain.entity.User;
import com.dingel.enums.AppHttpCodeEnum;
import com.dingel.handler.exception.SystemException;
import com.dingel.service.BlogLoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogLoginController {


    @Autowired
    private BlogLoginService blogLoginService;


    @SystemLog(businessName="用户登陆")
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        if (!StringUtils.hasText(user.getUserName())){
            //提示 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.login(user);
    }


    @SystemLog(businessName="退出登陆")
    @PostMapping("/logout")
    public ResponseResult logout(){
        return blogLoginService.logout();
    }

}
