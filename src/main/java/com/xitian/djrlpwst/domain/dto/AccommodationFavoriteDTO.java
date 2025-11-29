package com.xitian.djrlpwst.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "AccommodationFavoriteDTO", description = "住宿收藏DTO")
public class AccommodationFavoriteDTO {
    
    @Schema(description = "用户ID")
    @NotNull(message = "用户ID不能为空")
    private Long userId;
    
    @Schema(description = "住宿ID")
    @NotNull(message = "住宿ID不能为空")
    private Long accommodationId;
}