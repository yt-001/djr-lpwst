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
@Schema(name = "AccommodationVO", description = "住宿视图对象")
public class AccommodationVO extends BaseVO {
    
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
    private String contactPhone;
}