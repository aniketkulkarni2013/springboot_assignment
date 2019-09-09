package com.clairvoyantsoft.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoggerInterceptor  extends HandlerInterceptorAdapter {

    Logger log = LoggerFactory.getLogger(LoggerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("[preHandle][" + request + "]" + "[" + request.getMethod()
                + "]" + request.getRequestURI() );
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request,HttpServletResponse response,Object handler,
                              ModelAndView modelAndView) throws Exception {
        super.postHandle(request,response,handler,null);

        log.info("[postHandle][" + request + "]");
    }


}
