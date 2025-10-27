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
@TableName("intangible_culture")
@Schema(name = "IntangibleCulture", description = "非物质文化实体类")
public class IntangibleCulture extends BaseEntity {
    
    @Schema(description = "非遗项目名称")
    private String name;
    
    @Schema(description = "描述")
    private String description;
    
    @Schema(description = "非遗类型")
    private String type;
    
    @Schema(description = "传承人")
    private String inheritor;
    
    @Schema(description = "封面图片")
    private String coverImage;
    
    @Schema(description = "图片JSON数组")
    private String images;
}