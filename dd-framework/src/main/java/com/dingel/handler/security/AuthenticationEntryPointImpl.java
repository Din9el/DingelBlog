package com.dingel.handler.security;

import com.alibaba.fastjson.JSON;
import com.dingel.domain.ResponseResult;
import com.dingel.enums.AppHttpCodeEnum;
import com.dingel.utils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        e.printStackTrace();
        ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR);
        //响应给前端
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}
