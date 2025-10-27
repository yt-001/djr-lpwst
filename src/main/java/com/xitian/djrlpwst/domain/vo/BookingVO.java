package com.xitian.djrlpwst.domain.vo;

import com.xitian.djrlpwst.bean.base.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "BookingVO", description = "预订视图对象")
public class BookingVO extends BaseVO {
    
    @Schema(description = "用户ID")
    private Long userId;
    
    @Schema(description = "住宿ID")
    private Long accommodationId;
    
    @Schema(description = "景点ID(可选)")
    private Long attractionId;
    
    @Schema(description = "入住日期")
    private LocalDate checkInDate;
    
    @Schema(description = "退房日期")
    private LocalDate checkOutDate;
    
    @Schema(description = "客人数量")
    private Integer guestCount;
    
    @Schema(description = "总价格")
    private BigDecimal totalPrice;
    
    @Schema(description = "状态(0-待确认,1-已确认,2-已取消,3-已完成)")
    private Integer status;
    
    @Schema(description = "特殊要求")
    private String specialRequests;
}