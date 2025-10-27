package com.xitian.djrlpwst.converter;

import com.xitian.djrlpwst.domain.entity.Restaurant;
import com.xitian.djrlpwst.domain.vo.RestaurantVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantConverter {
    
    public RestaurantVO toVO(Restaurant entity) {
        if (entity == null) {
            return null;
        }
        
        RestaurantVO vo = new RestaurantVO();
        vo.setId(entity.getId());
        vo.setCreateTime(entity.getCreateTime());
        vo.setUpdateTime(entity.getUpdateTime());
        vo.setName(entity.getName());
        vo.setDescription(entity.getDescription());
        vo.setLocation(entity.getLocation());
        vo.setLatitude(entity.getLatitude());
        vo.setLongitude(entity.getLongitude());
        vo.setCoverImage(entity.getCoverImage());
        vo.setImages(entity.getImages());
        vo.setOpenHours(entity.getOpenHours());
        vo.setPriceRange(entity.getPriceRange());
        vo.setSpecialty(entity.getSpecialty());
        vo.setContactPhone(entity.getContactPhone());
        
        return vo;
    }
    
    public List<RestaurantVO> toVOList(List<Restaurant> entityList) {
        if (entityList == null) {
            return null;
        }
        
        List<RestaurantVO> voList = new ArrayList<>();
        for (Restaurant entity : entityList) {
            voList.add(toVO(entity));
        }
        
        return voList;
    }
}