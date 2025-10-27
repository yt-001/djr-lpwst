package com.xitian.djrlpwst.domain.entity;

import com.xitian.djrlpwst.bean.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("favorites")
@Schema(name = "Favorite", description = "收藏实体类")
public class Favorite extends BaseEntity {
    
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