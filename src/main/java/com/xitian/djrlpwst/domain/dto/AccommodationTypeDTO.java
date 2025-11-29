package com.xitian.djrlpwst.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "AccommodationTypeDTO", description = "住宿类型DTO")
public class AccommodationTypeDTO {
    
    @Schema(description = "类型ID")
    private Integer id;
    
    @NotBlank(message = "类型名称不能为空")
    @Schema(description = "类型名称")
    private String name;
    
    @Schema(description = "类型描述")
    private String description;
}