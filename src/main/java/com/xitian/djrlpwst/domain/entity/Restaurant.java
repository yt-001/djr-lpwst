package com.xitian.djrlpwst.domain.entity;

import com.xitian.djrlpwst.bean.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("restaurants")
@Schema(name = "Restaurant", description = "美食实体类")
public class Restaurant extends BaseEntity {

    @TableField("category_id")
    @Schema(description = "餐厅分类ID")
    private Long categoryId;

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
    
    @Schema(description = "招牌菜ID")
    private Long specialty;
    
    @Schema(description = "联系电话")
    private String contactPhone;
    
    @Schema(description = "推荐指数(0-5)")
    private java.math.BigDecimal rating;
}
