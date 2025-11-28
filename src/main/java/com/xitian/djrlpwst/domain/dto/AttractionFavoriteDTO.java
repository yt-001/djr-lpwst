package com.xitian.djrlpwst.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "AttractionFavoriteDTO", description = "景点收藏DTO")
public class AttractionFavoriteDTO {
    
    @Schema(description = "用户ID")
    @NotNull(message = "用户ID不能为空")
    private Long userId;
    
    @Schema(description = "景点ID")
    @NotNull(message = "景点ID不能为空")
    private Long attractionId;
}