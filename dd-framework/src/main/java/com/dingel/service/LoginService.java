package com.dingel.service;

import com.dingel.domain.ResponseResult;
import com.dingel.domain.entity.User;

public interface LoginService {
    ResponseResult login(User user);

}