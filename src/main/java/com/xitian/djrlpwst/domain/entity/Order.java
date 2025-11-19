package com.xitian.djrlpwst.domain.entity;

import com.xitian.djrlpwst.bean.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("orders")
@Schema(name = "Order", description = "订单实体类")
public class Order extends BaseEntity {
    
    @Schema(description = "订单编号")
    private String orderNo;
    
    @Schema(description = "用户ID")
    private Long userId;
    
    @Schema(description = "产品类型(1-景点门票,2-美食消费券,3-住宿消费券)")
    private Integer productType;
    
    @Schema(description = "产品ID(根据product_type关联不同表)")
    private Long productId;
    
    @Schema(description = "产品名称")
    private String productName;
    
    @Schema(description = "消费描述(如:住宿套餐类型/美食具体菜品等)")
    private String description;
    
    @Schema(description = "数量")
    private Integer quantity;
    
    @Schema(description = "单价")
    private BigDecimal unitPrice;
    
    @Schema(description = "总金额")
    private BigDecimal totalAmount;
    
    @Schema(description = "订单状态(0-待支付,1-已支付,2-已使用,3-已取消,4-已退款)")
    private Integer status;
    
    @Schema(description = "支付时间")
    private LocalDateTime paymentTime;
    
    @Schema(description = "使用时间")
    private LocalDateTime usedTime;
    
    @Schema(description = "过期时间")
    private LocalDateTime expireTime;
}