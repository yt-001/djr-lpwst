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
@Schema(name = "IntangibleCultureVO", description = "非物质文化视图对象")
public class IntangibleCultureVO extends BaseVO {
    
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