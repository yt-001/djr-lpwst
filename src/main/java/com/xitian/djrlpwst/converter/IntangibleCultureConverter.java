package com.xitian.djrlpwst.converter;

import com.xitian.djrlpwst.domain.entity.IntangibleCulture;
import com.xitian.djrlpwst.domain.vo.IntangibleCultureVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IntangibleCultureConverter {
    
    public IntangibleCultureVO toVO(IntangibleCulture entity) {
        if (entity == null) {
            return null;
        }
        
        IntangibleCultureVO vo = new IntangibleCultureVO();
        vo.setId(entity.getId());
        vo.setCreateTime(entity.getCreateTime());
        vo.setUpdateTime(entity.getUpdateTime());
        vo.setName(entity.getName());
        vo.setDescription(entity.getDescription());
        vo.setType(entity.getType());
        vo.setInheritor(entity.getInheritor());
        vo.setCoverImage(entity.getCoverImage());
        vo.setImages(entity.getImages());
        
        return vo;
    }
    
    public List<IntangibleCultureVO> toVOList(List<IntangibleCulture> entityList) {
        if (entityList == null) {
            return null;
        }
        
        List<IntangibleCultureVO> voList = new ArrayList<>();
        for (IntangibleCulture entity : entityList) {
            voList.add(toVO(entity));
        }
        
        return voList;
    }
}