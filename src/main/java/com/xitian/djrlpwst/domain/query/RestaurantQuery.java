package com.xitian.djrlpwst.domain.query;

import com.xitian.djrlpwst.bean.base.BaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "RestaurantQuery", description = "美食查询对象")
public class RestaurantQuery extends BaseQuery {
    
    @Schema(description = "餐厅/美食名称")
    private String name;
    
    @Schema(description = "地理位置")
    private String location;
}