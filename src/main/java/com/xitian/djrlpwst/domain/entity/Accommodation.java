package com.xitian.djrlpwst.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.xitian.djrlpwst.bean.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("accommodations")
@Schema(name = "Accommodation", description = "住宿实体类")
public class Accommodation extends BaseEntity {
    
    @NotBlank(message = "住宿名称不能为空")
    @Schema(description = "住宿名称")
    private String name;
    
    @Schema(description = "描述")
    private String description;
    
    @NotNull(message = "类型ID不能为空")
    @TableField("type_id")
    @Schema(description = "类型ID")
    private Integer typeId;
    
    @Schema(description = "地理位置")
    private String location;
    
    @Schema(description = "纬度")
    private BigDecimal latitude;
    
    @Schema(description = "经度")
    private BigDecimal longitude;
    
    @TableField("cover_image")
    @Schema(description = "封面图片")
    private String coverImage;
    
    @Schema(description = "图片JSON数组")
    private String images;
    
    @TableField("price_per_night")
    @Schema(description = "每晚价格")
    private BigDecimal pricePerNight;
    
    @Schema(description = "可容纳人数")
    private Integer capacity;
    
    @TableField("contact_phone")
    @Schema(description = "联系电话")
    private String contactPhone;
}