package com.xitian.djrlpwst.domain.query;

import com.xitian.djrlpwst.bean.base.BaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "IntangibleCultureQuery", description = "非物质文化查询对象")
public class IntangibleCultureQuery extends BaseQuery {
    
    @Schema(description = "非遗项目名称")
    private String name;
    
    @Schema(description = "非遗类型")
    private String type;
}