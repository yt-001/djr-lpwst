package com.xitian.djrlpwst.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * 用户角色枚举类
 */
@Getter
@RequiredArgsConstructor
public enum UserRole {
    /**
     * 管理员角色 - 拥有所有权限
     */
    ADMIN(0, "管理员", Arrays.asList(
            // 认证相关
            "/auth/login",
            "/auth/logout",
            "/auth/refresh",
            "/auth/check",
            
            // 管理员专属接口
            "/admin/**",
            
            // 景点管理
            "/attractions/**",
            
            // 住宿管理
            "/accommodations/**",
            
            // 美食管理
            "/restaurants/**",
            
            // 非遗文化管理
            "/intangible-cultures/**",
            
            // 用户管理
            "/users/**",
            
            // 评论管理
            "/comments/**",
            
            // 收藏管理
            "/favorites/**",
            
            // 预订管理
            "/bookings/**",
            
            // 文件上传
            "/files/upload-images"
    )),
    
    /**
     * 普通用户角色
     */
    USER(1, "普通用户", Arrays.asList(
            // 认证相关
            "/auth/login",
            "/auth/logout",
            "/auth/refresh",
            "/auth/check",
            
            // 用户个人相关
            "/user/**",
            
            // 景点浏览
            "/attractions/page",
            "/attractions/admin/page",
            "/attractions/{id}",
            
            // 住宿浏览
            "/accommodations/page",
            "/accommodations/{id}",
            
            // 美食浏览
            "/restaurants/page",
            "/restaurants/{id}",
            
            // 非遗文化浏览
            "/intangible-cultures/page",
            "/intangible-cultures/{id}",
            
            // 评论相关
            "/comments/**",
            
            // 收藏相关
            "/favorites/**",
            
            // 预订相关
            "/bookings/**",
            
            // 文件上传
            "/files/upload-images"
    )),
    
    /**
     * 游客角色（未登录用户）
     */
    GUEST(-1, "游客", Arrays.asList(
            // 认证相关
            "/auth/login",
            "/auth/refresh",
            
            // 景点浏览
            "/attractions/page",
            "/attractions/{id}",
            
            // 住宿浏览
            "/accommodations/page",
            "/accommodations/{id}",
            
            // 美食浏览
            "/restaurants/page",
            "/restaurants/{id}",
            
            // 非遗文化浏览
            "/intangible-cultures/page",
            "/intangible-cultures/{id}",
            
            // 测试数据接口
            "/test-data"
    )),
    
    /**
     * 公开访问角色 - 无需认证即可访问的接口
     */
    PUBLIC(-2, "公开访问", Arrays.asList(
            // 认证相关
            "/auth/login",
            "/auth/refresh",
            
            // 景点浏览
            "/attractions/page",
            "/attractions/{id}",
            
            // 住宿浏览
            "/accommodations/page",
            "/accommodations/{id}",
            
            // 美食浏览
            "/restaurants/page",
            "/restaurants/{id}",
            
            // 非遗文化浏览
            "/intangible-cultures/page",
            "/intangible-cultures/{id}",
            
            // 测试数据接口
            "/test-data",
            
            // 公开接口
            "/public/**"
    ));
    
    private final Integer code;
    private final String description;
    private final List<String> allowedPaths;
    
    /**
     * 根据角色代码获取角色枚举
     * @param code 角色代码
     * @return 用户角色枚举
     */
    public static UserRole fromCode(Integer code) {
        if (code == null) {
            return GUEST;
        }
        
        for (UserRole role : UserRole.values()) {
            if (role.getCode().equals(code)) {
                return role;
            }
        }
        
        return GUEST; // 默认返回游客角色
    }
    
    /**
     * 检查角色是否允许访问指定路径
     * @param path 请求路径
     * @return 是否允许访问
     */
    public boolean isAllowedPath(String path) {
        // 公开访问角色可以访问所有PUBLIC角色允许的路径
        if (this == PUBLIC) {
            return true;
        }
        
        // 简单的路径匹配逻辑，实际项目中可能需要更复杂的匹配规则
        for (String allowedPath : allowedPaths) {
            // 处理通配符路径
            if (allowedPath.endsWith("/**")) {
                String prefix = allowedPath.substring(0, allowedPath.length() - 3);
                if (path.startsWith(prefix)) {
                    return true;
                }
            } else if (allowedPath.contains("{") && allowedPath.contains("}")) {
                // 处理路径参数，如 /attractions/{id}
                String prefix = allowedPath.substring(0, allowedPath.indexOf("{"));
                if (path.startsWith(prefix)) {
                    return true;
                }
            } else if (allowedPath.equals(path)) {
                return true;
            }
        }
        return false;
    }
}