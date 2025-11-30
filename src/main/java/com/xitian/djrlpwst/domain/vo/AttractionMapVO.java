package com.xitian.djrlpwst.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "AttractionMapVO", description = "景点地图视图对象")
public class AttractionMapVO {
    
    @Schema(description = "景点ID")
    private Long id;
    
    @Schema(description = "景点名称")
    private String name;
    
    @Schema(description = "景点描述")
    private String description;
    
    @Schema(description = "纬度")
    private BigDecimal latitude;
    
    @Schema(description = "经度")
    private BigDecimal longitude;
}