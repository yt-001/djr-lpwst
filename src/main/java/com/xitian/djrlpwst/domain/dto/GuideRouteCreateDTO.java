package com.xitian.djrlpwst.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(name = "GuideRouteCreateDTO", description = "向导路线创建DTO")
public class GuideRouteCreateDTO {

    @Schema(description = "路线名称")
    @NotBlank(message = "路线名称不能为空")
    private String name;

    @Schema(description = "路线描述")
    private String description;

    @Schema(description = "封面图片地址")
    private String coverImage;

    @Schema(description = "预估总距离(公里)")
    private BigDecimal totalDistance;

    @Schema(description = "预估总时长(分钟)")
    private Integer totalDuration;

    @Schema(description = "编辑状态：0-草稿(暂存)，1-已发布，默认1")
    private Byte editStatus;
}
