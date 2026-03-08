package com.xitian.djrlpwst.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(name = "AiKnowledgeCreateDTO", description = "AI 知识创建 DTO")
public class AiKnowledgeCreateDTO {

    @Schema(description = "标题")
    @NotBlank(message = "标题不能为空")
    private String title;

    @Schema(description = "内容")
    @NotBlank(message = "内容不能为空")
    private String content;
}
