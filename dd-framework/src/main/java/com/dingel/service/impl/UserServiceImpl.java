package com.dingel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingel.domain.entity.User;
import com.dingel.mapper.UserMapper;
import com.dingel.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2022-09-29 09:43:47
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

