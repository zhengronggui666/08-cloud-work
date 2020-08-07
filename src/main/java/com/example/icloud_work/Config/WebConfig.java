package com.example.icloud_work.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor()).addPathPatterns("/**");
    }

    @Autowired
    public com.example.icloud_work.Interceptor.TokenInterceptor tokenInterceptor() {
        return new com.example.icloud_work.Interceptor.TokenInterceptor();
    }

}
