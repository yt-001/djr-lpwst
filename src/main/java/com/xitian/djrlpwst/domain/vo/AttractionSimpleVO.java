package com.xitian.djrlpwst.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "AttractionSimpleVO", description = "景点简单信息VO")
public class AttractionSimpleVO {
    
    @Schema(description = "景点ID")
    private Long id;
    
    @Schema(description = "景点名称")
    private String name;
}