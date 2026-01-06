package com.xitian.djrlpwst.domain.query;

import com.xitian.djrlpwst.bean.base.BaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "DishCategoryQuery", description = "菜品分类查询对象")
public class DishCategoryQuery extends BaseQuery {

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "是否启用：1-启用，0-禁用")
    private Integer isEnabled;

    @Schema(description = "创建时间开始，格式：yyyy-MM-dd")
    private String createTimeStart;

    @Schema(description = "创建时间结束，格式：yyyy-MM-dd")
    private String createTimeEnd;
}
