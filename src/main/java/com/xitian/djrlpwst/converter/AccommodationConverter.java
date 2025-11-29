package com.xitian.djrlpwst.converter;

import com.xitian.djrlpwst.domain.entity.Accommodation;
import com.xitian.djrlpwst.domain.entity.AccommodationFacility;
import com.xitian.djrlpwst.domain.entity.AccommodationType;
import com.xitian.djrlpwst.domain.vo.AccommodationAdminVO;
import com.xitian.djrlpwst.domain.vo.AccommodationVO;
import com.xitian.djrlpwst.domain.vo.AccommodationSimpleVO;
import com.xitian.djrlpwst.domain.vo.AccommodationDetailVO;
import com.xitian.djrlpwst.domain.vo.AccommodationFacilitySimpleVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccommodationConverter {
    
    public AccommodationVO toVO(Accommodation entity, AccommodationType type, List<AccommodationFacility> facilities) {
        if (entity == null) {
            return null;
        }
        
        AccommodationVO vo = new AccommodationVO();
        vo.setId(entity.getId());
        vo.setCreateTime(entity.getCreateTime());
        vo.setUpdateTime(entity.getUpdateTime());
        vo.setName(entity.getName());
        vo.setDescription(entity.getDescription());
        vo.setTypeId(entity.getTypeId());
        if (type != null) {
            vo.setTypeName(type.getName());
        }
        vo.setLocation(entity.getLocation());
        vo.setLatitude(entity.getLatitude());
        vo.setLongitude(entity.getLongitude());
        vo.setCoverImage(entity.getCoverImage());
        vo.setImages(entity.getImages());
        vo.setPricePerNight(entity.getPricePerNight());
        vo.setCapacity(entity.getCapacity());
        vo.setFacilities(facilities);
        vo.setContactPhone(entity.getContactPhone());
        
        return vo;
    }
    
    // 新增方法：转换为简化版VO对象（用于列表接口）
    public AccommodationSimpleVO toSimpleVO(Accommodation entity, AccommodationType type, List<AccommodationFacility> facilities) {
        if (entity == null) {
            return null;
        }
        
        AccommodationSimpleVO vo = new AccommodationSimpleVO();
        vo.setId(entity.getId());
        vo.setName(entity.getName());
        if (type != null) {
            vo.setTypeName(type.getName());
        }
        vo.setLocation(entity.getLocation());
        vo.setCoverImage(entity.getCoverImage());
        vo.setPricePerNight(entity.getPricePerNight());
        vo.setContactPhone(entity.getContactPhone());
        
        return vo;
    }
    
    // 新增方法：转换为详情VO对象（用于详情接口）
    public AccommodationDetailVO toDetailVO(Accommodation entity, AccommodationType type, List<AccommodationFacility> facilities) {
        if (entity == null) {
            return null;
        }
        
        // 转换设施列表为简化版
        List<AccommodationFacilitySimpleVO> simpleFacilities = facilities.stream()
                .map(facility -> AccommodationFacilitySimpleVO.builder()
                        .name(facility.getName())
                        .description(facility.getDescription())
                        .icon(facility.getIcon())
                        .build())
                .collect(Collectors.toList());
        
        AccommodationDetailVO vo = new AccommodationDetailVO();
        vo.setId(entity.getId());
        vo.setName(entity.getName());
        vo.setDescription(entity.getDescription());
        vo.setTypeId(entity.getTypeId());
        if (type != null) {
            vo.setTypeName(type.getName());
        }
        vo.setLocation(entity.getLocation());
        vo.setLatitude(entity.getLatitude());
        vo.setLongitude(entity.getLongitude());
        vo.setCoverImage(entity.getCoverImage());
        vo.setImages(entity.getImages());
        vo.setPricePerNight(entity.getPricePerNight());
        vo.setCapacity(entity.getCapacity());
        vo.setContactPhone(entity.getContactPhone());
        vo.setFacilities(simpleFacilities);
        
        return vo;
    }
    
    public AccommodationAdminVO toAdminVO(Accommodation entity, AccommodationType type) {
        if (entity == null) {
            return null;
        }
        
        AccommodationAdminVO vo = new AccommodationAdminVO();
        vo.setId(entity.getId());
        vo.setCreateTime(entity.getCreateTime());
        vo.setUpdateTime(entity.getUpdateTime());
        vo.setName(entity.getName());
        vo.setTypeId(entity.getTypeId());
        if (type != null) {
            vo.setTypeName(type.getName());
        }
        vo.setLocation(entity.getLocation());
        vo.setPricePerNight(entity.getPricePerNight());
        vo.setCapacity(entity.getCapacity());
        vo.setContactPhone(entity.getContactPhone());
        
        return vo;
    }
    
    public List<AccommodationVO> toVOList(List<Accommodation> entityList, 
                                         List<AccommodationType> types, 
                                         List<List<AccommodationFacility>> facilitiesList) {
        if (entityList == null || types == null || facilitiesList == null) {
            return null;
        }
        
        List<AccommodationVO> voList = new ArrayList<>();
        for (int i = 0; i < entityList.size(); i++) {
            Accommodation entity = entityList.get(i);
            AccommodationType type = i < types.size() ? types.get(i) : null;
            List<AccommodationFacility> facilities = i < facilitiesList.size() ? facilitiesList.get(i) : new ArrayList<>();
            voList.add(toVO(entity, type, facilities));
        }
        
        return voList;
    }
    
    // 新增方法：转换为简化版VO列表（用于列表接口）
    public List<AccommodationSimpleVO> toSimpleVOList(List<Accommodation> entityList, 
                                         List<AccommodationType> types, 
                                         List<List<AccommodationFacility>> facilitiesList) {
        if (entityList == null || types == null || facilitiesList == null) {
            return null;
        }
        
        List<AccommodationSimpleVO> voList = new ArrayList<>();
        for (int i = 0; i < entityList.size(); i++) {
            Accommodation entity = entityList.get(i);
            AccommodationType type = i < types.size() ? types.get(i) : null;
            List<AccommodationFacility> facilities = i < facilitiesList.size() ? facilitiesList.get(i) : new ArrayList<>();
            voList.add(toSimpleVO(entity, type, facilities));
        }
        
        return voList;
    }
    
    public List<AccommodationAdminVO> toAdminVOList(List<Accommodation> entityList, List<AccommodationType> types) {
        if (entityList == null || types == null) {
            return null;
        }
        
        List<AccommodationAdminVO> voList = new ArrayList<>();
        for (int i = 0; i < entityList.size(); i++) {
            Accommodation entity = entityList.get(i);
            AccommodationType type = i < types.size() ? types.get(i) : null;
            voList.add(toAdminVO(entity, type));
        }
        
        return voList;
    }
}