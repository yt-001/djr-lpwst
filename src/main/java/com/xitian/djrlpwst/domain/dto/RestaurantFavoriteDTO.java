package com.xitian.djrlpwst.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "RestaurantFavoriteDTO", description = "美食收藏DTO")
public class RestaurantFavoriteDTO {
    
    @Schema(description = "用户ID")
    @NotNull(message = "用户ID不能为空")
    private Long userId;
    
    @Schema(description = "美食ID")
    @NotNull(message = "美食ID不能为空")
    private Long restaurantId;
}