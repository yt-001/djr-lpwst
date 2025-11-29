package com.xitian.djrlpwst.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "AccommodationFacilitySimpleVO", description = "住宿设施简化视图对象")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationFacilitySimpleVO {
    
    @Schema(description = "设施名称")
    private String name;
    
    @Schema(description = "设施描述")
    private String description;
    
    @Schema(description = "设施图标")
    private String icon;
}