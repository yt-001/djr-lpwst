package com.xitian.djrlpwst.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xitian.djrlpwst.bean.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("guide_route_edges")
@Schema(name = "GuideRouteEdge", description = "旅游向导路线连线实体类（点与点之间的连接）")
public class GuideRouteEdge extends BaseEntity {

    @TableField("route_id")
    @Schema(description = "所属路线ID")
    private Integer routeId;

    @TableField("source_point_id")
    @Schema(description = "起点节点ID")
    private Integer sourcePointId;

    @TableField("target_point_id")
    @Schema(description = "终点节点ID")
    private Integer targetPointId;

    @Schema(description = "连线备注，例如：步行7分钟、乘车2站")
    private String label;
}

