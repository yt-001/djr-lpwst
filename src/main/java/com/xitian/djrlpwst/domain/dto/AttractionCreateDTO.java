package com.xitian.djrlpwst.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(name = "AttractionCreateDTO", description = "景点创建DTO")
public class AttractionCreateDTO {
    
    @Schema(description = "景点名称")
    @NotBlank(message = "景点名称不能为空")
    private String name;
    
    @Schema(description = "景点描述")
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
    
    @Schema(description = "开放时间")
    private String openHours;
    
    @Schema(description = "门票价格")
    private BigDecimal ticketPrice;
    
    @Schema(description = "联系电话")
    @Pattern(regexp = "^1[3-9]\\d{9}$|^0\\d{2,3}-?\\d{7,8}$", message = "联系电话格式不正确")
    private String contactPhone;
}