package com.xitian.djrlpwst.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xitian.djrlpwst.bean.ResultBean;
import com.xitian.djrlpwst.bean.StatusCode;
import com.xitian.djrlpwst.domain.enums.UserRole;
import com.xitian.djrlpwst.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 权限检查过滤器
 * 在每次请求时检查用户是否有权限访问指定路径
 */
@Component
public class PermissionCheckFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String refreshToken = extractRefreshTokenFromCookie(request);

        // 如果是公开接口，直接放行
        if (isPublicEndpoint(requestURI)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 检查权限
        if (refreshToken != null) {
            try {
                // 验证刷新令牌有效性
                Claims claims = jwtUtil.extractAllClaims(refreshToken);
                String emailOrPhone = (String) claims.get("emailOrPhone");
                Integer role = (Integer) claims.get("role");

                if (emailOrPhone != null && role != null && !jwtUtil.isTokenExpired(refreshToken)) {
                    UserRole userRole = UserRole.fromCode(role);
                    // 检查用户角色是否允许访问该路径
                    if (!userRole.isAllowedPath(requestURI)) {
                        handleForbiddenAccess(response);
                        return;
                    }
                } else {
                    // 令牌过期或无效，视为游客
                    UserRole guestRole = UserRole.GUEST;
                    if (!guestRole.isAllowedPath(requestURI)) {
                        handleForbiddenAccess(response);
                        return;
                    }
                }
            } catch (Exception e) {
                // 令牌解析失败，视为游客
                UserRole guestRole = UserRole.GUEST;
                if (!guestRole.isAllowedPath(requestURI)) {
                    handleForbiddenAccess(response);
                    return;
                }
            }
        } else {
            // 没有令牌，视为游客
            UserRole guestRole = UserRole.GUEST;
            if (!guestRole.isAllowedPath(requestURI)) {
                handleForbiddenAccess(response);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    /**
     * 从Cookie中提取刷新令牌
     *
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

    /**
     * 判断是否为公开接口
     *
     * @param requestURI 请求URI
     * @return 是否为公开接口
     */
    private boolean isPublicEndpoint(String requestURI) {
        // 公开接口白名单
        return requestURI.startsWith("/auth/login") ||
                requestURI.startsWith("/auth/refresh") ||
                requestURI.equals("/test-data") ||
                UserRole.PUBLIC.isAllowedPath(requestURI);
    }

    /**
     * 处理无权限访问的情况
     *
     * @param response HTTP响应
     * @throws IOException IO异常
     */
    private void handleForbiddenAccess(HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        ResultBean<Void> result = ResultBean.fail(StatusCode.FORBIDDEN, "您没有权限访问该资源");
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}