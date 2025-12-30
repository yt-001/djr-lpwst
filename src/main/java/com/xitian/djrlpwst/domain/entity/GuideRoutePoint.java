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

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("guide_route_points")
@Schema(name = "GuideRoutePoint", description = "旅游向导路线节点实体类")
public class GuideRoutePoint extends BaseEntity {

    @TableField("route_id")
    @Schema(description = "所属路线ID")
    private Integer routeId;

    @TableField("step_order")
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

    @TableField("stay_minutes")
    @Schema(description = "建议停留时长(分钟)")
    private Integer stayMinutes;

    @Schema(description = "备注说明")
    private String remark;
}

