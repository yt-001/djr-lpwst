package com.xitian.djrlpwst.converter;

import com.xitian.djrlpwst.domain.entity.IntangibleCulture;
import com.xitian.djrlpwst.domain.vo.IntangibleCultureVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 非物质文化遗产转换器
 */
@Component
public class IntangibleCultureConverter {
    
    /**
     * 将实体转换为VO
     * @param entity 实体对象
     * @return VO对象
     */
    public IntangibleCultureVO toVO(IntangibleCulture entity) {
        if (entity == null) {
            return null;
        }
        
        IntangibleCultureVO vo = new IntangibleCultureVO();
        vo.setId(entity.getId());
        vo.setName(entity.getName());
        vo.setDescription(entity.getDescription());
        vo.setType(entity.getType());
        vo.setInheritor(entity.getInheritor());
        vo.setCoverImage(entity.getCoverImage());
        vo.setImages(entity.getImages());
        vo.setCreateTime(entity.getCreateTime());
        
        return vo;
    }
    
    /**
     * 将实体列表转换为VO列表
     * @param entityList 实体列表
     * @return VO列表
     */
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
