package com.xitian.djrlpwst.domain.query;

import com.xitian.djrlpwst.bean.base.BaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "OrderQuery", description = "订单查询对象")
public class OrderQuery extends BaseQuery {
    
    @Schema(description = "订单编号")
    private String orderNo;
    
    @Schema(description = "用户ID")
    private Long userId;
    
    @Schema(description = "产品类型(1-景点门票,2-美食消费券,3-住宿消费券)")
    private Integer productType;
    
    @Schema(description = "订单状态列表(可查询多个状态)")
    private List<Integer> statusList;
}