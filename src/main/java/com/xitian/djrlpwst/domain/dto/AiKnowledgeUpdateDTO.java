package com.xitian.djrlpwst.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "AiKnowledgeUpdateDTO", description = "AI 知识更新 DTO")
public class AiKnowledgeUpdateDTO {

    @Schema(description = "标题")
    private String title;

    @Schema(description = "内容")
    private String content;
}
