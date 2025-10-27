package com.xitian.djrlpwst.converter;

import com.xitian.djrlpwst.domain.entity.Comment;
import com.xitian.djrlpwst.domain.vo.CommentVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentConverter {
    
    public CommentVO toVO(Comment entity) {
        if (entity == null) {
            return null;
        }
        
        CommentVO vo = new CommentVO();
        vo.setId(entity.getId());
        vo.setCreateTime(entity.getCreateTime());
        vo.setUpdateTime(entity.getUpdateTime());
        vo.setUserId(entity.getUserId());
        vo.setAttractionId(entity.getAttractionId());
        vo.setRestaurantId(entity.getRestaurantId());
        vo.setCultureId(entity.getCultureId());
        vo.setAccommodationId(entity.getAccommodationId());
        vo.setContent(entity.getContent());
        vo.setRating(entity.getRating());
        vo.setIsApproved(entity.getIsApproved());
        
        return vo;
    }
    
    public List<CommentVO> toVOList(List<Comment> entityList) {
        if (entityList == null) {
            return null;
        }
        
        List<CommentVO> voList = new ArrayList<>();
        for (Comment entity : entityList) {
            voList.add(toVO(entity));
        }
        
        return voList;
    }
}