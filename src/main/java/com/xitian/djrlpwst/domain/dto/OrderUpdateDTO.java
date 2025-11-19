package com.xitian.djrlpwst.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "OrderUpdateDTO", description = "订单更新DTO")
public class OrderUpdateDTO {
    
    @Schema(description = "订单ID")
    @NotNull(message = "订单ID不能为空")
    private Long id;
    
    @Schema(description = "订单状态(0-待支付,1-已支付,2-已使用,3-已取消,4-已退款)")
    private Integer status;
    
    @Schema(description = "使用时间")
    private LocalDateTime usedTime;
}