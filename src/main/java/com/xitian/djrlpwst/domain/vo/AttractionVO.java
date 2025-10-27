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
@Schema(name = "AttractionVO", description = "景点视图对象")
public class AttractionVO extends BaseVO {
    
    @Schema(description = "景点名称")
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
    private String contactPhone;
}