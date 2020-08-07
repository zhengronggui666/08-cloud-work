package com.example.icloud_work.Interceptor;


import com.example.icloud_work.Annotation.TokenAnnotation;
import com.example.icloud_work.Exception.CustomException;
import com.example.icloud_work.Util.TokenUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        TokenAnnotation methodAnnotation = method.getAnnotation(TokenAnnotation.class);
        if (methodAnnotation!=null){
            if(TokenUtil.buckets.get("bucket").getToken()){
                return true;
            }
            else{

                throw new CustomException("Too many requests");
            }
        }else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
