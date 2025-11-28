package com.xitian.djrlpwst.domain.vo;

import com.xitian.djrlpwst.bean.base.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "AttractionFavoriteVO", description = "景点收藏视图对象")
public class AttractionFavoriteVO extends BaseVO {
    
    @Schema(description = "用户ID")
    private Long userId;
    
    @Schema(description = "景点ID")
    private Long attractionId;
    
    @Schema(description = "景点名称")
    private String name;
    
    @Schema(description = "景点描述")
    private String description;
    
    @Schema(description = "地理位置")
    private String location;
    
    @Schema(description = "封面图片")
    private String coverImage;
    
    @Schema(description = "门票价格")
    private BigDecimal ticketPrice;
    
    @Schema(description = "联系电话")
    private String contactPhone;
}