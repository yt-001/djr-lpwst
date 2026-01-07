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
@Schema(name = "RestaurantFavoriteVO", description = "美食收藏视图对象")
public class RestaurantFavoriteVO extends BaseVO {
    
    @Schema(description = "用户ID")
    private Long userId;
    
    @Schema(description = "美食ID")
    private Long restaurantId;
    
    @Schema(description = "美食名称")
    private String name;
    
    @Schema(description = "美食描述")
    private String description;
    
    @Schema(description = "地理位置")
    private String location;
    
    @Schema(description = "封面图片")
    private String coverImage;
    
    @Schema(description = "价格区间")
    private String priceRange;

    @Schema(description = "营业时间")
    private String openHours;

    @Schema(description = "联系电话")
    private String contactPhone;

    @Schema(description = "推荐指数(0-5)")
    private BigDecimal rating;
}
