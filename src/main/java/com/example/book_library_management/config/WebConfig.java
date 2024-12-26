package com.example.book_library_management.config;

import com.example.book_library_management.interceptor.MyCustomInterceptor;
import com.example.book_library_management.interceptor.RateLimitingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public RateLimitingInterceptor rateLimitingInterceptor() {
        return new RateLimitingInterceptor();
    }

    @Bean
    public MyCustomInterceptor myCustomInterceptor() {
        return new MyCustomInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rateLimitingInterceptor())
                .addPathPatterns("/**")
                .order(1);  // Order 1, will be executed before myCustomInterceptor

        registry.addInterceptor(myCustomInterceptor())
                .addPathPatterns("/library/**")
                .order(2);  // Order 2, will be executed after rateLimitingInterceptor
    }
}