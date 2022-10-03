package com.dingel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dingel.domain.ResponseResult;
import com.dingel.domain.entity.User;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2022-09-29 09:43:46
 */
public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);
}

