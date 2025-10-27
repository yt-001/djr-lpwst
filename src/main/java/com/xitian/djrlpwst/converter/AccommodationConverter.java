package com.xitian.djrlpwst.converter;

import com.xitian.djrlpwst.domain.entity.Accommodation;
import com.xitian.djrlpwst.domain.vo.AccommodationVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccommodationConverter {
    
    public AccommodationVO toVO(Accommodation entity) {
        if (entity == null) {
            return null;
        }
        
        AccommodationVO vo = new AccommodationVO();
        vo.setId(entity.getId());
        vo.setCreateTime(entity.getCreateTime());
        vo.setUpdateTime(entity.getUpdateTime());
        vo.setName(entity.getName());
        vo.setDescription(entity.getDescription());
        vo.setType(entity.getType());
        vo.setLocation(entity.getLocation());
        vo.setLatitude(entity.getLatitude());
        vo.setLongitude(entity.getLongitude());
        vo.setCoverImage(entity.getCoverImage());
        vo.setImages(entity.getImages());
        vo.setPricePerNight(entity.getPricePerNight());
        vo.setCapacity(entity.getCapacity());
        vo.setFacilities(entity.getFacilities());
        vo.setContactPhone(entity.getContactPhone());
        
        return vo;
    }
    
    public List<AccommodationVO> toVOList(List<Accommodation> entityList) {
        if (entityList == null) {
            return null;
        }
        
        List<AccommodationVO> voList = new ArrayList<>();
        for (Accommodation entity : entityList) {
            voList.add(toVO(entity));
        }
        
        return voList;
    }
}