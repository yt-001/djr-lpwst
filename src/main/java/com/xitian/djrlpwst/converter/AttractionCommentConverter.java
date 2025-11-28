package com.xitian.djrlpwst.converter;

import com.xitian.djrlpwst.domain.entity.Attraction;
import com.xitian.djrlpwst.domain.entity.Comment;
import com.xitian.djrlpwst.domain.entity.User;
import com.xitian.djrlpwst.domain.vo.AttractionCommentVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AttractionCommentConverter {
    
    public AttractionCommentVO toVO(Comment comment, User user, Attraction attraction) {
        if (comment == null) {
            return null;
        }
        
        AttractionCommentVO vo = new AttractionCommentVO();
        vo.setId(comment.getId());
        vo.setCreateTime(comment.getCreateTime());
        vo.setUpdateTime(comment.getUpdateTime());
        vo.setUserId(comment.getUserId());
        vo.setAttractionId(comment.getAttractionId());
        vo.setContent(comment.getContent());
        vo.setRating(comment.getRating());
        vo.setIsApproved(comment.getIsApproved());
        
        if (user != null) {
            vo.setUsername(user.getUsername());
            vo.setUserAvatar(user.getAvatarUrl());
        }
        
        if (attraction != null) {
            vo.setAttractionName(attraction.getName());
        }
        
        return vo;
    }
    
    public List<AttractionCommentVO> toVOList(List<Comment> commentList, List<User> userList, List<Attraction> attractionList) {
        if (commentList == null) {
            return null;
        }
        
        List<AttractionCommentVO> voList = new ArrayList<>();
        for (Comment comment : commentList) {
            User user = userList.stream()
                .filter(u -> u.getId().equals(comment.getUserId()))
                .findFirst()
                .orElse(null);
                
            Attraction attraction = attractionList.stream()
                .filter(a -> a.getId().equals(comment.getAttractionId()))
                .findFirst()
                .orElse(null);
                
            voList.add(toVO(comment, user, attraction));
        }
        
        return voList;
    }
}