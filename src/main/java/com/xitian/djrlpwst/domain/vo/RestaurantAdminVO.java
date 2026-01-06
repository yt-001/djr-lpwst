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
@Schema(name = "RestaurantAdminVO", description = "美食管理视图对象")
public class RestaurantAdminVO extends BaseVO {
    
    @Schema(description = "餐厅/美食名称")
    private String name;
    
    @Schema(description = "描述")
    private String description;
    
    @Schema(description = "地理位置")
    private String location;
    
    @Schema(description = "营业时间")
    private String openHours;
    
    @Schema(description = "价格区间(如:￥50-100)")
    private String priceRange;
    
    @Schema(description = "招牌菜ID")
    private Long specialty;
    
    @Schema(description = "联系电话")
    private String contactPhone;
    
    @Schema(description = "推荐指数(0-5)")
    private BigDecimal rating;
}
