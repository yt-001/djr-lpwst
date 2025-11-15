package com.xitian.djrlpwst.config;

import com.xitian.djrlpwst.domain.enums.UserRole;
import com.xitian.djrlpwst.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * JWT认证过滤器，基于HttpOnly Cookie实现认证
 * 验证前端传送的刷新令牌并进行权限检查
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain) throws ServletException, IOException {
        
        String refreshToken = extractRefreshTokenFromCookie(request);
        
        if (refreshToken != null) {
            try {
                // 验证刷新令牌有效性
                Claims claims = jwtUtil.extractAllClaims(refreshToken);
                String emailOrPhone = (String) claims.get("emailOrPhone");
                Integer role = (Integer) claims.get("role");
                
                if (emailOrPhone != null && !jwtUtil.isTokenExpired(refreshToken)) {
                    // 构建权限列表
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    if (role != null) {
                        UserRole userRole = UserRole.fromCode(role);
                        if (userRole == UserRole.ADMIN) { // 管理员
                            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                        } else if (userRole == UserRole.USER) { // 普通用户
                            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                        }
                    }
                    
                    UsernamePasswordAuthenticationToken authToken = 
                        new UsernamePasswordAuthenticationToken(emailOrPhone, null, authorities);
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (Exception e) {
                logger.error("Refresh Token validation failed", e);
            }
        }
        
        filterChain.doFilter(request, response);
    }
    
    /**
     * 从Cookie中提取刷新令牌
     * @param request HTTP请求
     * @return 刷新令牌
     */
    private String extractRefreshTokenFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("refresh_token".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}