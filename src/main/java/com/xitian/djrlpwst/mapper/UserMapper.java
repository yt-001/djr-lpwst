package com.xitian.djrlpwst.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xitian.djrlpwst.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户数据访问层接口
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    
}