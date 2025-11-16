package com.xitian.djrlpwst.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(name = "AccommodationUpdateDTO", description = "住宿更新DTO")
public class AccommodationUpdateDTO {
    
    @Schema(description = "住宿ID")
    @NotNull(message = "住宿ID不能为空")
    private Long id;
    
    @Schema(description = "住宿名称")
    private String name;
    
    @Schema(description = "描述")
    private String description;
    
    @Schema(description = "类型(农家乐/民宿/酒店)")
    private String type;
    
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
    
    @Schema(description = "每晚价格")
    private BigDecimal pricePerNight;
    
    @Schema(description = "可容纳人数")
    private Integer capacity;
    
    @Schema(description = "设施JSON数组")
    private String facilities;
    
    @Schema(description = "联系电话")
    @Pattern(regexp = "^1[3-9]\\d{9}$|^0\\d{2,3}-?\\d{7,8}$", message = "联系电话格式不正确")
    private String contactPhone;
}