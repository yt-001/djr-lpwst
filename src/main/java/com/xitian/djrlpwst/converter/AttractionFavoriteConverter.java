package com.xitian.djrlpwst.converter;

import com.xitian.djrlpwst.domain.entity.Attraction;
import com.xitian.djrlpwst.domain.entity.Favorite;
import com.xitian.djrlpwst.domain.vo.AttractionFavoriteVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AttractionFavoriteConverter {
    
    public AttractionFavoriteVO toVO(Favorite favorite, Attraction attraction) {
        if (favorite == null || attraction == null) {
            return null;
        }
        
        AttractionFavoriteVO vo = new AttractionFavoriteVO();
        vo.setId(favorite.getId());
        vo.setCreateTime(favorite.getCreateTime());
        vo.setUpdateTime(favorite.getUpdateTime());
        vo.setUserId(favorite.getUserId());
        vo.setAttractionId(attraction.getId());
        vo.setName(attraction.getName());
        vo.setDescription(attraction.getDescription());
        vo.setLocation(attraction.getLocation());
        vo.setCoverImage(attraction.getCoverImage());
        vo.setTicketPrice(attraction.getTicketPrice());
        vo.setContactPhone(attraction.getContactPhone());
        
        return vo;
    }
    
    public List<AttractionFavoriteVO> toVOList(List<Favorite> favoriteList, List<Attraction> attractionList) {
        if (favoriteList == null || attractionList == null) {
            return null;
        }
        
        List<AttractionFavoriteVO> voList = new ArrayList<>();
        // 这里简化处理，实际项目中应该通过ID关联
        for (int i = 0; i < favoriteList.size() && i < attractionList.size(); i++) {
            voList.add(toVO(favoriteList.get(i), attractionList.get(i)));
        }
        
        return voList;
    }
}