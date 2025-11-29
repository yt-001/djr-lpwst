package com.xitian.djrlpwst.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Schema(name = "AccommodationDetailVO", description = "住宿详情视图对象")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationDetailVO {
    
    @Schema(description = "编号")
    private Long id;
    
    @Schema(description = "住宿名称")
    private String name;
    
    @Schema(description = "描述")
    private String description;
    
    @Schema(description = "类型ID")
    private Integer typeId;
    
    @Schema(description = "类型名称")
    private String typeName;
    
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
    
    @Schema(description = "设施列表")
    private List<AccommodationFacilitySimpleVO> facilities;
    
    @Schema(description = "联系电话")
    private String contactPhone;
}