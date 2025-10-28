package com.xitian.djrlpwst.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "RestaurantListVO", description = "美食列表展示VO")
public class RestaurantListVO {
    
    @Schema(description = "美食ID")
    private Long id;
    
    @Schema(description = "餐厅/美食名称")
    private String name;
    
    @Schema(description = "封面图片")
    private String coverImage;
    
    @Schema(description = "营业时间")
    private String openHours;
    
    @Schema(description = "价格区间(如:￥50-100)")
    private String priceRange;
    
    @Schema(description = "联系电话")
    private String contactPhone;
}