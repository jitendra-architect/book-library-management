package com.example.book_library_management.interceptor;

import com.example.book_library_management.controller.BookLibraryController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyCustomInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(BookLibraryController.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("Request received at:{}",request.getRequestURI());
        // You can also check for conditions and stop the request from proceeding
        //        if (request.getHeader("Authorization") == null) {
        //            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        //            response.getWriter().write("Unauthorized request");
        //            return false;
        //        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
            // Example of modifying the response before it is sent
            response.setHeader("X-Response-Time", String.valueOf(System.currentTimeMillis()));
            // You can also log the handler or the response here
            logger.debug("Request completed: {}",request.getRequestURI());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        logger.debug("Request and Response completed {}",response.encodeURL("Jitendra kumar"));
    }
}