package com.xitian.djrlpwst.domain.query;

import com.xitian.djrlpwst.bean.base.BaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "BookingQuery", description = "预订查询对象")
public class BookingQuery extends BaseQuery {
    
    @Schema(description = "用户ID")
    private Long userId;
    
    @Schema(description = "状态")
    private Integer status;
}