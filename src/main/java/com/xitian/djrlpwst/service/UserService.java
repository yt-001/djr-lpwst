package com.xitian.djrlpwst.service;

import com.xitian.djrlpwst.bean.base.service.BaseService;
import com.xitian.djrlpwst.domain.entity.User;
import com.xitian.djrlpwst.domain.enums.LoginResult;

/**
 * 用户业务逻辑层接口
 */
public interface UserService extends BaseService<User> {

    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 用户实体
     */
    User findByUsername(String username);
    
    /**
     * 根据邮箱查找用户
     * @param email 邮箱
     * @return 用户实体
     */
    User findByEmail(String email);
    
    /**
     * 根据手机号查找用户
     * @param phone 手机号
     * @return 用户实体
     */
    User findByPhone(String phone);
    
    /**
     * 用户登录
     * @param emailOrPhone 邮箱或手机号
     * @param password 密码
     * @return 登录结果
     */
    LoginResult login(String emailOrPhone, String password);
    
    /**
     * 用户注册
     * @param user 用户信息
     * @return 是否注册成功
     */
    boolean register(User user);
}