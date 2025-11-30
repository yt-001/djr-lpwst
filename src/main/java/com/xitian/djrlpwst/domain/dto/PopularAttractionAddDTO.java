package com.xitian.djrlpwst.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "PopularAttractionAddDTO", description = "热门景点添加DTO")
public class PopularAttractionAddDTO {
    
    @Schema(description = "景点ID")
    private Long attractionId;
}