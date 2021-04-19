//package com.mrisk.monitoreo.rule.infrastructure.config.spring;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Component;
//
//import lombok.extern.log4j.Log4j2;
//
//@Component
//@Log4j2
//public class HeaderFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        if (httpServletRequest.getHeader("X-Acco-Id") == null || httpServletRequest.getHeader("X-Tena-Id") == null) {
//            // Bad request, required Header dont set
//            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            log.warn("Error BadRequest");
//        } else {
//            chain.doFilter(request, response);
//        }
//    }
//
//}