package com.xitian.djrlpwst.converter;

import com.xitian.djrlpwst.domain.entity.User;
import com.xitian.djrlpwst.domain.vo.UserVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {
    
    public UserVO toVO(User entity) {
        if (entity == null) {
            return null;
        }
        
        UserVO vo = new UserVO();
        vo.setId(entity.getId());
        vo.setCreateTime(entity.getCreateTime());
        vo.setUpdateTime(entity.getUpdateTime());
        vo.setUsername(entity.getUsername());
        vo.setEmail(entity.getEmail());
        vo.setPhone(entity.getPhone());
        vo.setAvatarUrl(entity.getAvatarUrl());
        vo.setStatus(entity.getStatus());
        
        return vo;
    }
    
    public List<UserVO> toVOList(List<User> entityList) {
        if (entityList == null) {
            return null;
        }
        
        List<UserVO> voList = new ArrayList<>();
        for (User entity : entityList) {
            voList.add(toVO(entity));
        }
        
        return voList;
    }
}