package com.xitian.djrlpwst.domain.query;

import com.xitian.djrlpwst.bean.base.BaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "AccommodationTypeQuery", description = "住宿类型查询对象")
public class AccommodationTypeQuery extends BaseQuery {
    
    @Schema(description = "类型名称")
    private String name;
}