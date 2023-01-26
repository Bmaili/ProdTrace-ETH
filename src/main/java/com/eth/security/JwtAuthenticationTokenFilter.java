package com.eth.security;

import com.eth.utils.JwtUtils;
import com.eth.utils.RedisCache;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// @Component 在SecurityConfig.java中注入
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // System.out.println("请求经过了TestFilter！！！！！！！");
        //获取Token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        // 解析Token
        String userId;
        try {
            Claims claims = JwtUtils.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            log.info("token非法");
            // throw new RuntimeException("token非法ex");
            request.getRequestDispatcher("/").forward(request, response);
            return;
        }
        //    从redis中获取用户信息
        String redisKey = "login:" + userId;

        JwtUser loginUser = (JwtUser) redisCache.getCacheObject(redisKey);
        // Object loginUser = redisCache.getCacheObject(redisKey);
        if (loginUser == null || (!token.equals(loginUser.getToken()))) {
            log.info("用户未登录");
            request.getRequestDispatcher("/").forward(request, response);
            return;
        }

        request.setAttribute("userId", userId);

        //    存入securityContextHandel
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }

}
