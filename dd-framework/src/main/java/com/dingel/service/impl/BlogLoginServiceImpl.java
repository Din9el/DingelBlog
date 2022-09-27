package com.dingel.service.impl;

import com.dingel.domain.ResponseResult;
import com.dingel.domain.entity.LoginUser;
import com.dingel.domain.entity.User;
import com.dingel.domain.vo.BlogUserLoginVo;
import com.dingel.domain.vo.UserInfoVo;
import com.dingel.service.BlogLoginService;
import com.dingel.utils.BeanCopyUtils;
import com.dingel.utils.JwtUtil;
import com.dingel.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BlogLoginServiceImpl implements BlogLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private RedisCache redisCache;


    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if (Objects.isNull(authenticate)){
            throw  new RuntimeException("用户名或密码错误");
        }
        //获取userid 生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);

        //把用户信息存入redis
        redisCache.setCacheObject("bloglogin:"+userId,loginUser);


        //把User装换成userInfoVo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);

        //把token和userinfo封装 返回

        BlogUserLoginVo vo = new BlogUserLoginVo(jwt,userInfoVo);
        return ResponseResult.okResult(vo);


    }
}