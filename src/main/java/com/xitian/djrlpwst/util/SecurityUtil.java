package com.xitian.djrlpwst.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * 安全工具类
 * 提供获取当前用户信息等安全相关的方法
 */
@Component
public class SecurityUtil {

    /**
     * 获取当前认证的用户名
     * @return 当前用户名，未认证则返回null
     */
    public static String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof String) {
            return (String) authentication.getPrincipal();
        }
        return null;
    }

    /**
     * 检查当前用户是否已认证
     * @return 是否已认证
     */
    public static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }
    
    /**
     * 检查当前用户是否具有管理员角色
     * @return 是否为管理员
     */
    public static boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof String) {
            // 这里应该从JWT token中获取角色信息
            // 简化实现，实际项目中应该从认证信息中获取角色
            return false;
        }
        return false;
    }
    
    /**
     * 检查当前用户是否具有普通用户角色
     * @return 是否为普通用户
     */
    public static boolean isUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof String) {
            // 这里应该从JWT token中获取角色信息
            // 简化实现，实际项目中应该从认证信息中获取角色
            return true;
        }
        return false;
    }
}