package com.xitian.djrlpwst.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "GuideRouteWorkflowUpdateDTO", description = "向导路线工作流布局更新DTO")
public class GuideRouteWorkflowUpdateDTO {

    @Schema(description = "节点布局列表")
    private List<PointLayout> points;

    @Schema(description = "连线列表")
    private List<Edge> edges;

    @Data
    @Schema(name = "GuideRoutePointLayout", description = "单个节点布局信息")
    public static class PointLayout {

        @Schema(description = "节点ID")
        @NotNull(message = "节点ID不能为空")
        private Long id;

        @Schema(description = "画布X坐标")
        private Integer canvasX;

        @Schema(description = "画布Y坐标")
        private Integer canvasY;
    }

    @Data
    @Schema(name = "GuideRouteEdgeUpdate", description = "单条连线信息")
    public static class Edge {

        @Schema(description = "起点节点ID")
        @NotNull(message = "起点节点ID不能为空")
        private Integer sourcePointId;

        @Schema(description = "终点节点ID")
        @NotNull(message = "终点节点ID不能为空")
        private Integer targetPointId;

        @Schema(description = "连线备注")
        private String label;
    }
}

