package com.dingel.controller;


import com.dingel.annotation.SystemLog;
import com.dingel.domain.ResponseResult;
import com.dingel.domain.entity.User;
import com.dingel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")

    public ResponseResult userInfo(){
        return userService.userInfo();
    }

    @PutMapping("/userInfo")
    @SystemLog(businessName="更新用户信息")
    public ResponseResult updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }


    @PostMapping("/register")

    public ResponseResult register(@RequestBody User user){
        return userService.register(user);
    }




}



