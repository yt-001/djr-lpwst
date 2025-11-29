package com.xitian.djrlpwst.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "AccommodationFacilityDTO", description = "住宿设施DTO")
public class AccommodationFacilityDTO {
    
    @Schema(description = "设施ID")
    private Integer id;
    
    @NotBlank(message = "设施名称不能为空")
    @Schema(description = "设施名称")
    private String name;
    
    @Schema(description = "设施描述")
    private String description;
    
    @Schema(description = "设施图标")
    private String icon;
}