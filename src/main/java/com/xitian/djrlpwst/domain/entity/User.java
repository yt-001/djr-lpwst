package com.xitian.djrlpwst.domain.entity;

import com.xitian.djrlpwst.bean.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("users")
@Schema(name = "User", description = "用户实体类")
public class User extends BaseEntity {
    @Schema(description = "用户名")
    private String username;
    
    @Schema(description = "密码")
    private String passwordHash;
    
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