package com.xitian.djrlpwst.converter;

import com.xitian.djrlpwst.domain.entity.Attraction;
import com.xitian.djrlpwst.domain.entity.PopularAttraction;
import com.xitian.djrlpwst.domain.vo.AttractionMapVO;
import com.xitian.djrlpwst.domain.vo.AttractionSimpleVO;
import org.springframework.stereotype.Component;

@Component
public class PopularAttractionConverter {
    
    /**
     * 将PopularAttraction实体转换为AttractionMapVO
     * @param popularAttraction 热门景点实体
     * @return 地图展示用的景点VO
     */
    public AttractionMapVO toMapVO(PopularAttraction popularAttraction) {
        if (popularAttraction == null) {
            return null;
        }
        
        return AttractionMapVO.builder()
                .id(popularAttraction.getId())
                .name(popularAttraction.getName())
                .description(popularAttraction.getDescription())
                .latitude(popularAttraction.getLatitude())
                .longitude(popularAttraction.getLongitude())
                .build();
    }
    
    /**
     * 将Attraction实体转换为AttractionSimpleVO
     * @param attraction 景点实体
     * @return 景点简单信息VO
     */
    public AttractionSimpleVO toSimpleVO(Attraction attraction) {
        if (attraction == null) {
            return null;
        }
        
        return AttractionSimpleVO.builder()
                .id(attraction.getId())
                .name(attraction.getName())
                .build();
    }
}