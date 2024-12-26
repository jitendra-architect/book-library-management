package com.example.book_library_management.interceptor;

import com.example.book_library_management.ratelimiter.RateLimiterService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

public class RateLimitingInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(RateLimitingInterceptor.class);

    @Autowired
    private RateLimiterService rateLimiterService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userKey = request.getRemoteAddr();  // You can also use a user identifier (e.g., username)
        logger.debug("userKey:{}", userKey);
        if (!rateLimiterService.isAllowed(userKey)) {
            response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            response.getWriter().write("Rate limit exceeded. Try again later.");
            return false;
        }

        return true;
    }
}