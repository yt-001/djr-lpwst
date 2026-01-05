package com.xitian.djrlpwst.domain.query;

import com.xitian.djrlpwst.bean.base.BaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "RestaurantCategoryQuery", description = "餐厅分类查询对象")
public class RestaurantCategoryQuery extends BaseQuery {

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "创建时间开始，格式：yyyy-MM-dd")
    private String createTimeStart;

    @Schema(description = "创建时间结束，格式：yyyy-MM-dd")
    private String createTimeEnd;
}
