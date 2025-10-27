package com.xitian.djrlpwst.service.impl;

import com.xitian.djrlpwst.bean.base.service.BaseServiceImpl;
import com.xitian.djrlpwst.domain.entity.User;
import com.xitian.djrlpwst.domain.enums.LoginResult;
import com.xitian.djrlpwst.mapper.UserMapper;
import com.xitian.djrlpwst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    @Override
    public User findByUsername(String username) {
        // 根据用户名查找用户
        return userMapper.selectOne(
            com.baomidou.mybatisplus.core.toolkit.Wrappers.<User>lambdaQuery()
                .eq(User::getUsername, username)
        );
    }
    
    @Override
    public User findByEmail(String email) {
        // 根据邮箱查找用户
        return userMapper.selectOne(
            com.baomidou.mybatisplus.core.toolkit.Wrappers.<User>lambdaQuery()
                .eq(User::getEmail, email)
        );
    }
    
    @Override
    public User findByPhone(String phone) {
        // 根据手机号查找用户
        return userMapper.selectOne(
            com.baomidou.mybatisplus.core.toolkit.Wrappers.<User>lambdaQuery()
                .eq(User::getPhone, phone)
        );
    }
    
    @Override
    public LoginResult login(String emailOrPhone, String password) {
        User user;
        // 判断是邮箱还是手机号
        if (emailOrPhone.contains("@")) {
            user = findByEmail(emailOrPhone);
        } else {
            user = findByPhone(emailOrPhone);
        }
        
        // 检查用户是否存在
        if (user == null) {
            return LoginResult.USER_NOT_EXISTS;
        }
        
        // 检查用户状态（假设1为启用，0为禁用）
        if (user.getStatus() == null || user.getStatus() != 1) {
            return LoginResult.USER_DISABLED;
        }
        
        // 验证密码
        if (passwordEncoder.matches(password, user.getPasswordHash())) {
            return LoginResult.SUCCESS;
        } else {
            return LoginResult.PASSWORD_ERROR;
        }
    }
    
        @Override
    public boolean register(User user) {
        // 检查用户是否已存在
        if (findByUsername(user.getUsername()) != null || 
            findByEmail(user.getEmail()) != null || 
            findByPhone(user.getPhone()) != null) {
            return false;
        }
        
        // 对密码进行加密
        String encodedPassword = passwordEncoder.encode(user.getPasswordHash());
        user.setPasswordHash(encodedPassword);
        
        // 保存用户信息到数据库
        return this.save(user);
    }
}