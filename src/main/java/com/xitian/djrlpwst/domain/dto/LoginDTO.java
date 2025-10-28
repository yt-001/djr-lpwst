package com.xitian.djrlpwst.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Data
@Schema(name = "LoginDTO", description = "用户登录请求DTO")
public class LoginDTO {

    @Schema(description = "邮箱或手机号", example = "user@example.com 或 13800138000")
    @NotBlank(message = "邮箱或手机号不能为空")
    @Pattern(regexp = "(^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$)|(^1[3-9]\\d{9}$)", 
             message = "请输入正确的邮箱或手机号")
    private String emailOrPhone;

    @Schema(description = "密码", example = "password123")
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 20, message = "密码长度必须在6-20个字符之间")
    private String password;
    
    @Schema(description = "用户角色", example = "USER 或 ADMIN")
    private String role;
}