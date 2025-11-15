package com.xitian.djrlpwst.converter;

import com.xitian.djrlpwst.domain.entity.Attraction;
import com.xitian.djrlpwst.domain.vo.AttractionDetailVO;
import com.xitian.djrlpwst.domain.vo.AttractionListVO;
import com.xitian.djrlpwst.domain.vo.AttractionVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AttractionConverter {
    
    public AttractionVO toVO(Attraction entity) {
        if (entity == null) {
            return null;
        }
        
        AttractionVO vo = new AttractionVO();
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
        vo.setTicketPrice(entity.getTicketPrice());
        vo.setContactPhone(entity.getContactPhone());
        
        return vo;
    }
    
    public AttractionDetailVO toDetailVO(Attraction entity) {
        if (entity == null) {
            return null;
        }
        
        AttractionDetailVO vo = new AttractionDetailVO();
        vo.setId(entity.getId());
        vo.setCreateTime(entity.getCreateTime());
        vo.setUpdateTime(entity.getUpdateTime());
        vo.setName(entity.getName());
        vo.setDescription(entity.getDescription());
        vo.setLocation(entity.getLocation());
        vo.setOpenHours(entity.getOpenHours());
        vo.setTicketPrice(entity.getTicketPrice());
        vo.setContactPhone(entity.getContactPhone());
        
        return vo;
    }
    
    public AttractionListVO toListVO(Attraction entity) {
        if (entity == null) {
            return null;
        }
        
        AttractionListVO vo = new AttractionListVO();
        vo.setId(entity.getId());
        vo.setCreateTime(entity.getCreateTime());
        vo.setUpdateTime(entity.getUpdateTime());
        vo.setName(entity.getName());
        vo.setDescription(entity.getDescription());
        vo.setCoverImage(entity.getCoverImage());
        vo.setOpenHours(entity.getOpenHours());
        vo.setTicketPrice(entity.getTicketPrice());
        
        return vo;
    }
    
    public List<AttractionVO> toVOList(List<Attraction> entityList) {
        if (entityList == null) {
            return null;
        }
        
        List<AttractionVO> voList = new ArrayList<>();
        for (Attraction entity : entityList) {
            voList.add(toVO(entity));
        }
        
        return voList;
    }
    
    public List<AttractionDetailVO> toDetailVOList(List<Attraction> entityList) {
        if (entityList == null) {
            return null;
        }
        
        List<AttractionDetailVO> voList = new ArrayList<>();
        for (Attraction entity : entityList) {
            voList.add(toDetailVO(entity));
        }
        
        return voList;
    }
    
    public List<AttractionListVO> toListVOList(List<Attraction> entityList) {
        if (entityList == null) {
            return null;
        }
        
        List<AttractionListVO> voList = new ArrayList<>();
        for (Attraction entity : entityList) {
            voList.add(toListVO(entity));
        }
        
        return voList;
    }
}