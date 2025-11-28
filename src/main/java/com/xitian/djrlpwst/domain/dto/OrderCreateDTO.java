package com.xitian.djrlpwst.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(name = "OrderCreateDTO", description = "订单创建DTO")
public class OrderCreateDTO {
    
    @Schema(description = "用户ID")
    @NotNull(message = "用户ID不能为空")
    private Long userId;
    
    @Schema(description = "产品类型(1-景点门票,2-美食消费券,3-住宿消费券)")
    @NotNull(message = "产品类型不能为空")
    private Integer productType;
    
    @Schema(description = "产品ID(根据product_type关联不同表)")
    @NotNull(message = "产品ID不能为空")
    private Long productId;
    
    @Schema(description = "产品名称")
    @NotBlank(message = "产品名称不能为空")
    private String productName;
    
    @Schema(description = "消费描述(如:住宿套餐类型/美食具体菜品等)")
    private String description;
    
    @Schema(description = "数量")
    private Integer quantity = 1;
    
    @Schema(description = "单价")
    @NotNull(message = "单价不能为空")
    private BigDecimal unitPrice;
    
    @Schema(description = "总金额")
    @NotNull(message = "总金额不能为空")
    private BigDecimal totalAmount;
    
    @Schema(description = "订单状态(0-待支付,1-已支付,2-已使用,3-已取消,4-已退款)")
    private Integer status;
}