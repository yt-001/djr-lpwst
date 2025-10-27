package com.xitian.djrlpwst.converter;

import com.xitian.djrlpwst.domain.entity.Favorite;
import com.xitian.djrlpwst.domain.vo.FavoriteVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FavoriteConverter {
    
    public FavoriteVO toVO(Favorite entity) {
        if (entity == null) {
            return null;
        }
        
        FavoriteVO vo = new FavoriteVO();
        vo.setId(entity.getId());
        vo.setCreateTime(entity.getCreateTime());
        vo.setUpdateTime(entity.getUpdateTime());
        vo.setUserId(entity.getUserId());
        vo.setAttractionId(entity.getAttractionId());
        vo.setRestaurantId(entity.getRestaurantId());
        vo.setCultureId(entity.getCultureId());
        vo.setAccommodationId(entity.getAccommodationId());
        
        return vo;
    }
    
    public List<FavoriteVO> toVOList(List<Favorite> entityList) {
        if (entityList == null) {
            return null;
        }
        
        List<FavoriteVO> voList = new ArrayList<>();
        for (Favorite entity : entityList) {
            voList.add(toVO(entity));
        }
        
        return voList;
    }
}