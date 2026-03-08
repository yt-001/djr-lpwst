package com.xitian.djrlpwst.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(name = "AiChatDTO", description = "AI 聊天请求 DTO")
public class AiChatDTO {

    @Schema(description = "用户输入内容", example = "帮我推荐重庆三日游路线")
    @NotBlank(message = "消息内容不能为空")
    private String message;
}
