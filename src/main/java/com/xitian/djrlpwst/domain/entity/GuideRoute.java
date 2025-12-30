package com.xitian.djrlpwst.domain.entity;

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
@TableName("guide_routes")
@Schema(name = "GuideRoute", description = "旅游向导路线实体类")
public class GuideRoute extends BaseEntity {

    @Schema(description = "路线名称")
    private String name;

    @Schema(description = "路线描述")
    private String description;

    @Schema(description = "预估总距离(公里)")
    private BigDecimal totalDistance;

    @Schema(description = "预估总时长(分钟)")
    private Integer totalDuration;

    @Schema(description = "状态：0-禁用，1-启用")
    private Byte status;

    @Schema(description = "编辑状态：0-草稿(暂存)，1-已发布")
    private Byte editStatus;
}

