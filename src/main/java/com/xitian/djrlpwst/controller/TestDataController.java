package com.xitian.djrlpwst.controller;

import com.xitian.djrlpwst.bean.ResultBean;
import com.xitian.djrlpwst.domain.entity.User;
import com.xitian.djrlpwst.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test-data")
@Tag(name = "测试数据管理", description = "用于生成测试数据的接口")
public class TestDataController {

    @Autowired
    private UserService userService;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/generate-users")
    @Operation(summary = "生成测试用户", description = "生成一个普通用户和一个管理员用户用于测试")
    public ResultBean<String> generateTestUsers() {
        StringBuilder result = new StringBuilder();

        // 创建普通用户
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("user@example.com");
        user.setPhone("13800138000");
        user.setPasswordHash(passwordEncoder.encode("123456")); // 密码加密
        user.setStatus(1); // 启用状态
        user.setRole(1); // 普通用户角色

        // 保存普通用户
        if (userService.findByUsername("testuser") == null) {
            userService.save(user);
            result.append("普通用户创建成功\n");
        } else {
            result.append("普通用户已存在\n");
        }

        // 创建管理员用户
        User admin = new User();
        admin.setUsername("admin");
        admin.setEmail("admin@example.com");
        admin.setPhone("13800138001");
        admin.setPasswordHash(passwordEncoder.encode("123456")); // 密码加密
        admin.setStatus(1); // 启用状态
        admin.setRole(0); // 管理员角色

        // 保存管理员用户
        if (userService.findByUsername("admin") == null) {
            userService.save(admin);
            result.append("管理员用户创建成功\n");
        } else {
            result.append("管理员用户已存在\n");
        }

        return ResultBean.success(result.toString());
    }
}