package com.rj.ExceptionResolver;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ex.printStackTrace();
        ModelAndView mv = new ModelAndView();
        if(ex instanceof IncorrectCredentialsException || ex instanceof UnknownAccountException ||ex instanceof UnauthenticatedException ){
            //跳转登录页面，重新登录
            mv.setViewName("redirect:/user/login");
        }else if(ex instanceof UnauthorizedException){// 角色不足  权限不足
            //跳转权限不足的页面
            mv.setViewName("redirect:/user/perms/error");
        }else if(ex instanceof IOException){//没有登录 没有合法身份
            //跳转登录页面，重新登录
            mv.setViewName("redirect:/user/IOError");
        }
        System.out.println(mv);
        return mv;
    }
}
