package com.rj.interceptor;

import lombok.Setter;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileInterceptor implements HandlerInterceptor {
    @Setter
    private long fileSize;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ServletRequestContext src = new ServletRequestContext(request);
        long realSize = src.contentLength();
        if (fileSize >= realSize) {
            return true;
        }
        response.sendRedirect(request.getContextPath() + "/fileError.jsp");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
