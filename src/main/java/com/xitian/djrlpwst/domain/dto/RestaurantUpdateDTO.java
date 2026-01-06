package com.xitian.djrlpwst.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(name = "RestaurantUpdateDTO", description = "餐厅更新DTO")
public class RestaurantUpdateDTO {
    
    @Schema(description = "餐厅ID")
    @NotNull(message = "餐厅ID不能为空")
    private Long id;
    
    @Schema(description = "餐厅/美食名称")
    private String name;
    
    @Schema(description = "描述")
    private String description;
    
    @Schema(description = "地理位置")
    private String location;
    
    @Schema(description = "纬度")
    private BigDecimal latitude;
    
    @Schema(description = "经度")
    private BigDecimal longitude;
    
    @Schema(description = "封面图片")
    private String coverImage;
    
    @Schema(description = "图片JSON数组")
    private String images;
    
    @Schema(description = "营业时间")
    private String openHours;
    
    @Schema(description = "价格区间(如:￥50-100)")
    private String priceRange;
    
    @Schema(description = "招牌菜ID")
    private Long specialty;
    
    @Schema(description = "联系电话")
    @Pattern(regexp = "^1[3-9]\\d{9}$|^0\\d{2,3}-?\\d{7,8}$", message = "联系电话格式不正确")
    private String contactPhone;
    
    @Schema(description = "推荐指数(0-5)")
    private BigDecimal rating;
}
