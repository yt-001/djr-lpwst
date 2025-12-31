package com.xitian.djrlpwst.domain.vo;

import com.xitian.djrlpwst.bean.base.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "GuideRouteDetailVO", description = "向导路线详情视图对象，包含节点与连线")
public class GuideRouteDetailVO extends BaseVO {

    @Schema(description = "路线名称")
    private String name;

    @Schema(description = "路线描述")
    private String description;

    @Schema(description = "封面图片地址")
    private String coverImage;

    @Schema(description = "预估总距离(公里)")
    private BigDecimal totalDistance;

    @Schema(description = "预估总时长(分钟)")
    private Integer totalDuration;

    @Schema(description = "状态：0-禁用，1-启用")
    private Byte status;

    @Schema(description = "编辑状态：0-草稿(暂存)，1-已发布")
    private Byte editStatus;

    @Schema(description = "节点列表")
    private List<GuideRouteDetailVO.Point> points;

    @Schema(description = "连线列表")
    private List<GuideRouteDetailVO.Edge> edges;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "GuideRoutePointDetail", description = "向导路线节点详情")
    public static class Point {

        @Schema(description = "节点ID")
        private Long id;

        @Schema(description = "所属路线ID")
        private Integer routeId;

        @Schema(description = "在路线中的顺序")
        private Integer stepOrder;

        @Schema(description = "地点名称")
        private String name;

        @Schema(description = "地点地址")
        private String address;

        @Schema(description = "纬度")
        private BigDecimal latitude;

        @Schema(description = "经度")
        private BigDecimal longitude;

        @Schema(description = "画布X坐标")
        private Integer canvasX;

        @Schema(description = "画布Y坐标")
        private Integer canvasY;

        @Schema(description = "建议停留时长(分钟)")
        private Integer stayMinutes;

        @Schema(description = "备注说明")
        private String remark;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "GuideRouteEdgeDetail", description = "向导路线连线详情")
    public static class Edge {

        @Schema(description = "连线ID")
        private Long id;

        @Schema(description = "所属路线ID")
        private Integer routeId;

        @Schema(description = "起点节点ID")
        private Integer sourcePointId;

        @Schema(description = "终点节点ID")
        private Integer targetPointId;

        @Schema(description = "连线备注，例如：步行7分钟、乘车2站")
        private String label;
    }
}
