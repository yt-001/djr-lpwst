package com.xitian.djrlpwst.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.base.service.BaseServiceImpl;
import com.xitian.djrlpwst.converter.UserConverter;
import com.xitian.djrlpwst.domain.entity.User;
import com.xitian.djrlpwst.domain.enums.LoginResult;
import com.xitian.djrlpwst.domain.query.UserQuery;
import com.xitian.djrlpwst.domain.vo.UserVO;
import com.xitian.djrlpwst.mapper.UserMapper;
import com.xitian.djrlpwst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserConverter userConverter;
    
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public PageBean<UserVO> page(PageParam<UserQuery> param) {
        // 1. 构建分页对象
        Page<User> page = new Page<>(param.getPageNum(), param.getPageSize());
        
        // 2. 构建查询条件
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        UserQuery query = param.getQuery();
        
        if (query != null) {
            // 关键字模糊查询 (用户名/手机号/邮箱)
            if (StringUtils.hasText(query.getKeyword())) {
                wrapper.and(w -> w
                    .like(User::getUsername, query.getKeyword())
                    .or().like(User::getPhone, query.getKeyword())
                    .or().like(User::getEmail, query.getKeyword())
                );
            }
            
            // 精确筛选角色
            if (StringUtils.hasText(query.getRole())) {
                try {
                    wrapper.eq(User::getRole, Integer.parseInt(query.getRole()));
                } catch (NumberFormatException ignored) {}
            }
        }
        
        // 倒序排列 (根据ID或创建时间)
        wrapper.orderByDesc(User::getId);
        
        // 3. 执行查询
        this.page(page, wrapper);
        
        // 4. 转换并返回
        List<UserVO> voList = userConverter.toVOList(page.getRecords());
        return PageBean.of(voList, page.getTotal(), param);
    }
    
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
    public User findByEmailOrPhoneAndRole(String emailOrPhone, Integer role) {
        // 根据邮箱或手机号和角色查找用户
        if (emailOrPhone.contains("@")) {
            return userMapper.selectOne(
                com.baomidou.mybatisplus.core.toolkit.Wrappers.<User>lambdaQuery()
                    .eq(User::getEmail, emailOrPhone)
                    .eq(User::getRole, role)
            );
        } else {
            return userMapper.selectOne(
                com.baomidou.mybatisplus.core.toolkit.Wrappers.<User>lambdaQuery()
                    .eq(User::getPhone, emailOrPhone)
                    .eq(User::getRole, role)
            );
        }
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
    public LoginResult login(String emailOrPhone, String password, Integer role) {
        User user = findByEmailOrPhoneAndRole(emailOrPhone, role);
        
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