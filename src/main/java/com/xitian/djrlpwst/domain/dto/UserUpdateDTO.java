package com.xitian.djrlpwst.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "UserUpdateDTO", description = "用户更新DTO")
public class UserUpdateDTO {
    
    @Schema(description = "用户ID")
    @NotNull(message = "用户ID不能为空")
    private Long id;
    
    @Schema(description = "用户名")
    private String username;
    
    @Schema(description = "邮箱")
    private String email;
    
    @Schema(description = "手机号")
    private String phone;
    
    @Schema(description = "头像URL")
    private String avatarUrl;
    
    @Schema(description = "状态(0-禁用,1-启用)")
    private Integer status;
    
    @Schema(description = "角色(0-管理员,1-普通用户)")
    private Integer role;
}