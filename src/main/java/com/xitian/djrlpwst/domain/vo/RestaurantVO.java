package com.xitian.djrlpwst.domain.vo;

import com.xitian.djrlpwst.bean.base.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "RestaurantVO", description = "美食视图对象")
public class RestaurantVO extends BaseVO {
    
    @Schema(description = "餐厅/美食名称")
    private String name;
    
    @Schema(description = "描述")
    private String description;
    
    @Schema(description = "地理位置")
    private String location;
    
    @Schema(description = "纬度")
    private java.math.BigDecimal latitude;
    
    @Schema(description = "经度")
    private java.math.BigDecimal longitude;
    
    @Schema(description = "封面图片")
    private String coverImage;
    
    @Schema(description = "图片JSON数组")
    private String images;
    
    @Schema(description = "营业时间")
    private String openHours;
    
    @Schema(description = "价格区间(如:￥50-100)")
    private String priceRange;
    
    @Schema(description = "招牌菜")
    private String specialty;
    
    @Schema(description = "联系电话")
    private String contactPhone;
    
    @Schema(description = "推荐指数(0-5)")
    private java.math.BigDecimal rating;
}