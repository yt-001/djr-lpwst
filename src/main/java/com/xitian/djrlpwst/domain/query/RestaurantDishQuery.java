package com.xitian.djrlpwst.domain.query;

import com.xitian.djrlpwst.bean.base.BaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "RestaurantDishQuery", description = "美食菜品查询对象")
public class RestaurantDishQuery extends BaseQuery {

    @Schema(description = "餐厅ID")
    private Long restaurantId;

    @Schema(description = "菜品分类ID")
    private Long categoryId;

    @Schema(description = "菜品名称")
    private String name;

    @Schema(description = "是否推荐：1-是，0-否")
    private Integer isRecommended;

    @Schema(description = "创建时间开始，格式：yyyy-MM-dd")
    private String createTimeStart;

    @Schema(description = "创建时间结束，格式：yyyy-MM-dd")
    private String createTimeEnd;
}

