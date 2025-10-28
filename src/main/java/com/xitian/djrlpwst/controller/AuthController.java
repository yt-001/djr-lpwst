package com.xitian.djrlpwst.controller;

import com.xitian.djrlpwst.bean.ResultBean;
import com.xitian.djrlpwst.bean.StatusCode;
import com.xitian.djrlpwst.config.CookieProperties;
import com.xitian.djrlpwst.domain.dto.LoginDTO;
import com.xitian.djrlpwst.domain.entity.User;
import com.xitian.djrlpwst.domain.enums.LoginResult;
import com.xitian.djrlpwst.domain.vo.UserVO;
import com.xitian.djrlpwst.converter.UserConverter;
import com.xitian.djrlpwst.service.UserService;
import com.xitian.djrlpwst.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "认证管理", description = "用户认证相关接口")
public class AuthController {

    @Autowired
    private CookieProperties cookieProperties;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserConverter userConverter;

    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public ResultBean<UserVO> login(@Valid @RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        // 从loginDTO中获取邮箱或手机号和密码
        String emailOrPhone = loginDTO.getEmailOrPhone();
        String password = loginDTO.getPassword();
        String roleStr = loginDTO.getRole();
        
        // 获取用户信息
        User user;
        if (emailOrPhone.contains("@")) {
            user = userService.findByEmail(emailOrPhone);
        } else {
            user = userService.findByPhone(emailOrPhone);
        }
        
        // 检查用户是否存在
        if (user == null) {
            return ResultBean.fail(StatusCode.USER_NOT_FOUND, "用户不存在");
        }
        
        // 如果提供了role参数，则验证用户角色是否匹配
        if (roleStr != null && !roleStr.isEmpty()) {
            try {
                Integer role = Integer.valueOf(roleStr);
                // 检查用户角色是否匹配
                if (!role.equals(user.getRole())) {
                    return ResultBean.fail(StatusCode.USERNAME_OR_PASSWORD_ERROR, "角色参数错误");
                }
            } catch (NumberFormatException e) {
                return ResultBean.fail(StatusCode.USERNAME_OR_PASSWORD_ERROR, "角色参数无效");
            }
        }
        
        // 验证用户密码
        LoginResult loginResult = userService.login(emailOrPhone, password);
        if (loginResult != LoginResult.SUCCESS) {
            return switch (loginResult) {
                case USER_NOT_EXISTS -> ResultBean.fail(StatusCode.USER_NOT_FOUND, "用户不存在");
                case USER_DISABLED -> ResultBean.fail(StatusCode.USER_DISABLED, "用户已被禁用");
                case PASSWORD_ERROR -> ResultBean.fail(StatusCode.USERNAME_OR_PASSWORD_ERROR, "密码错误");
                default -> ResultBean.fail(StatusCode.USERNAME_OR_PASSWORD_ERROR, "用户名或密码错误");
            };
        }
        
        // 生成访问令牌和刷新令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("userId", user.getId());
        claims.put("emailOrPhone", emailOrPhone);
        claims.put("role", user.getRole());
        // 可以添加更多用户信息到claims中
        
        String accessToken = JwtUtil.generateAccessToken(claims);
        String refreshToken = JwtUtil.generateRefreshToken(claims);
        
        // 将令牌设置到HttpOnly Cookie中
        Cookie accessCookie = new Cookie("access_token", accessToken);
        accessCookie.setHttpOnly(true);
        accessCookie.setSecure(false); // 在HTTPS环境下应设为true
        accessCookie.setPath("/");
        accessCookie.setMaxAge(cookieProperties.getAccessTokenExpiry());
        response.addCookie(accessCookie);
        
        Cookie refreshCookie = new Cookie("refresh_token", refreshToken);
        refreshCookie.setHttpOnly(true);
        refreshCookie.setSecure(false); // 在HTTPS环境下应设为true
        refreshCookie.setPath("/");
        refreshCookie.setMaxAge(cookieProperties.getRefreshTokenExpiry());
        response.addCookie(refreshCookie);
        
        // 使用转换器转换User为UserVO并返回
        UserVO userVO = userConverter.toVO(user);
        
        return ResultBean.success(userVO);
    }

    @PostMapping("/logout")
    @Operation(summary = "用户登出")
    public ResultBean<UserVO> logout(HttpServletResponse response) {
        // 清除HttpOnly Cookie中的令牌
        Cookie accessCookie = new Cookie("access_token", null);
        accessCookie.setHttpOnly(true);
        accessCookie.setSecure(false);
        accessCookie.setPath("/");
        accessCookie.setMaxAge(0); // 立即过期
        response.addCookie(accessCookie);
        
        Cookie refreshCookie = new Cookie("refresh_token", null);
        refreshCookie.setHttpOnly(true);
        refreshCookie.setSecure(false);
        refreshCookie.setPath("/");
        refreshCookie.setMaxAge(0); // 立即过期
        response.addCookie(refreshCookie);
        
        return ResultBean.success(null);
    }

    @PostMapping("/refresh")
    @Operation(summary = "刷新令牌")
    public ResultBean<UserVO> refresh(HttpServletRequest request, HttpServletResponse response) {
        // 从请求中获取刷新令牌
        String refreshToken = extractRefreshTokenFromCookie(request);
        
        if (refreshToken == null) {
            return ResultBean.fail(StatusCode.INVALID_TOKEN, "未找到刷新令牌");
        }
        
        try {
            // 验证刷新令牌有效性
            String emailOrPhone = JwtUtil.extractClaim(refreshToken, claims -> claims.get("emailOrPhone", String.class));
            
            if (emailOrPhone == null || JwtUtil.isTokenExpired(refreshToken)) {
                return ResultBean.fail(StatusCode.TOKEN_EXPIRED, "刷新令牌已过期");
            }
            
            // 获取用户信息
            User user;
            if (emailOrPhone.contains("@")) {
                user = userService.findByEmail(emailOrPhone);
            } else {
                user = userService.findByPhone(emailOrPhone);
            }
            
            if (user == null) {
                return ResultBean.fail(StatusCode.USER_NOT_FOUND, "用户不存在");
            }
            
            // 只生成新的访问令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", user.getUsername());
            claims.put("userId", user.getId());
            claims.put("emailOrPhone", user.getEmail() != null ? user.getEmail() : user.getPhone());
            claims.put("role", user.getRole());
            
            String newAccessToken = JwtUtil.generateAccessToken(claims);
            
            // 只更新访问令牌的Cookie
            Cookie accessCookie = new Cookie("access_token", newAccessToken);
            accessCookie.setHttpOnly(true);
            accessCookie.setSecure(false);
            accessCookie.setPath("/");
            accessCookie.setMaxAge(cookieProperties.getAccessTokenExpiry());
            response.addCookie(accessCookie);
            
            // 使用转换器转换User为UserVO并返回
            UserVO userVO = userConverter.toVO(user);
            
            return ResultBean.success(userVO);
        } catch (ExpiredJwtException e) {
            return ResultBean.fail(StatusCode.TOKEN_EXPIRED, "刷新令牌已过期");
        } catch (Exception e) {
            return ResultBean.fail(StatusCode.INVALID_TOKEN, "刷新令牌无效");
        }
    }
    
    @GetMapping("/check")
    @Operation(summary = "检查访问令牌")
    public ResultBean<UserVO> check(HttpServletRequest request) {
        // 从请求中获取访问令牌
        String accessToken = extractAccessTokenFromCookie(request);
        
        if (accessToken == null) {
            return ResultBean.fail(com.xitian.djrlpwst.bean.StatusCode.INVALID_TOKEN, "未找到访问令牌");
        }
        
        try {
            // 验证访问令牌有效性
            String emailOrPhone = JwtUtil.extractClaim(accessToken, claims -> claims.get("emailOrPhone", String.class));
            
            if (emailOrPhone == null || JwtUtil.isTokenExpired(accessToken)) {
                return ResultBean.fail(StatusCode.TOKEN_EXPIRED, "访问令牌已过期");
            }
            
            // 获取用户信息
            User user;
            if (emailOrPhone.contains("@")) {
                user = userService.findByEmail(emailOrPhone);
            } else {
                user = userService.findByPhone(emailOrPhone);
            }
            
            if (user == null) {
                return ResultBean.fail(StatusCode.USER_NOT_FOUND, "用户不存在");
            }
            
            // 使用转换器转换User为UserVO并返回
            UserVO userVO = userConverter.toVO(user);
            
            return ResultBean.success(userVO);
        } catch (ExpiredJwtException e) {
            return ResultBean.fail(StatusCode.TOKEN_EXPIRED, "访问令牌已过期");
        } catch (Exception e) {
            return ResultBean.fail(StatusCode.INVALID_TOKEN, "访问令牌无效");
        }
    }
    
    /**
     * 从Cookie中提取访问令牌
     * @param request HTTP请求
     * @return 访问令牌
     */
    private String extractAccessTokenFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("access_token".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
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