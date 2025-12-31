package com.xitian.djrlpwst.domain.vo;

import com.xitian.djrlpwst.bean.base.BaseVO;
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
@Schema(name = "GuideRouteCardVO", description = "向导路线卡片视图对象")
public class GuideRouteCardVO extends BaseVO {

    @Schema(description = "路线名称")
    private String name;

    @Schema(description = "路线描述")
    private String description;

    @Schema(description = "封面图片地址")
    private String coverImage;

    @Schema(description = "节点数量")
    private Integer pointCount;

    @Schema(description = "状态：0-禁用，1-启用")
    private Byte status;

    @Schema(description = "编辑状态：0-草稿(暂存)，1-已发布")
    private Byte editStatus;
}

