package com.xitian.djrlpwst.domain.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(name = "LoginResult", description = "登录结果枚举")
public enum LoginResult {
    @Schema(description = "登录成功")
    SUCCESS(0, "登录成功"),
    
    @Schema(description = "用户不存在")
    USER_NOT_EXISTS(1, "用户不存在"),
    
    @Schema(description = "用户被禁用")
    USER_DISABLED(2, "用户已被禁用"),
    
    @Schema(description = "密码错误")
    PASSWORD_ERROR(3, "密码错误");
    
    private final int code;
    private final String message;
    
    LoginResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

}