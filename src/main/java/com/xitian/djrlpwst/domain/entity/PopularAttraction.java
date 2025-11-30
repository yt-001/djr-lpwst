package com.xitian.djrlpwst.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xitian.djrlpwst.bean.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("popular_attractions")
@Schema(name = "PopularAttraction", description = "热门景点实体类")
public class PopularAttraction extends BaseEntity {
    
    @Schema(description = "对应景点ID")
    private Long attractionId;
    
    @Schema(description = "热门景点名称")
    private String name;
    
    @Schema(description = "描述")
    private String description;
    
    @Schema(description = "纬度")
    private BigDecimal latitude;
    
    @Schema(description = "经度")
    private BigDecimal longitude;
}