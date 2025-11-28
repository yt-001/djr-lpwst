package com.xitian.djrlpwst.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "AttractionCommentDTO", description = "景点评论DTO")
public class AttractionCommentDTO {
    
    @Schema(description = "用户ID")
    @NotNull(message = "用户ID不能为空")
    private Long userId;
    
    @Schema(description = "景点ID")
    @NotNull(message = "景点ID不能为空")
    private Long attractionId;
    
    @Schema(description = "评论内容")
    @NotBlank(message = "评论内容不能为空")
    private String content;
    
    @Schema(description = "评分(1-5星)")
    @Min(value = 1, message = "评分不能小于1")
    @Max(value = 5, message = "评分不能大于5")
    private Integer rating;
}