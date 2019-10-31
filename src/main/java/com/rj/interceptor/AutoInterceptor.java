package com.rj.interceptor;

import com.rj.utils.AutoLoginCookie;
import com.rj.utils.Base64Utils;
import com.rj.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AutoInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String username = (String) request.getSession().getAttribute("username");
        if (!StringUtils.isEmpty(username)) {
            return true;
        }
        Cookie[] cookies = request.getCookies();
        if (cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("info")) {
                    String info = cookie.getValue();
                    String str = Base64Utils.decode(info);
                    String[] strs = str.split("#");
                    UsernamePasswordToken token = new UsernamePasswordToken(strs[0], strs[1]);
                    SecurityUtils.getSubject().login(token);
                    return true;
                }
//                Cookie cookie1 = new Cookie("info", "");
//                cookie1.setMaxAge(0);
//                cookie1.setPath("/");
//                cookie1.setHttpOnly(true);
//                response.addCookie(cookie1);
                AutoLoginCookie.logoutCookie("info", response);
            }
        }
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
