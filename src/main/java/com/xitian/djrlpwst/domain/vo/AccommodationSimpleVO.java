package com.xitian.djrlpwst.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Schema(name = "AccommodationSimpleVO", description = "住宿简化视图对象")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationSimpleVO {
    
    @Schema(description = "编号")
    private Long id;
    
    @Schema(description = "住宿名称")
    private String name;
    
    @Schema(description = "类型名称")
    private String typeName;
    
    @Schema(description = "地理位置")
    private String location;
    
    @Schema(description = "封面图片")
    private String coverImage;
    
    @Schema(description = "每晚价格")
    private BigDecimal pricePerNight;
    
    @Schema(description = "联系电话")
    private String contactPhone;
}