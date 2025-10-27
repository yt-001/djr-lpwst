package com.xitian.djrlpwst.domain.vo;

import com.xitian.djrlpwst.bean.base.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "FavoriteVO", description = "收藏视图对象")
public class FavoriteVO extends BaseVO {
    
    @Schema(description = "用户ID")
    private Long userId;
    
    @Schema(description = "景点ID")
    private Long attractionId;
    
    @Schema(description = "美食ID")
    private Long restaurantId;
    
    @Schema(description = "非遗ID")
    private Long cultureId;
    
    @Schema(description = "住宿ID")
    private Long accommodationId;
}