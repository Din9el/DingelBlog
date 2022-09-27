package com.dingel.service;

import com.dingel.domain.ResponseResult;
import com.dingel.domain.entity.User;

public interface BlogLoginService {
    ResponseResult login(User user);
}
